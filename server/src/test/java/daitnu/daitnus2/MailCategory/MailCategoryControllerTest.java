package daitnu.daitnus2.MailCategory;

import com.fasterxml.jackson.databind.ObjectMapper;
import daitnu.daitnus2.database.entity.MailCategory;
import daitnu.daitnus2.database.entity.User;
import daitnu.daitnus2.mail.category.MailCategoryDTO;
import daitnu.daitnus2.mail.category.MailCategoryService;
import daitnu.daitnus2.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class MailCategoryControllerTest {
  @Autowired UserService userService;
  @Autowired MailCategoryService mailCategoryService;
  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;

  final String userId = "kimsoso";
  final String userId2 = "kimsoso2";
  final String pw = "12345";
  final String name = "kss";
  final String subEmail = "kimsoso@gaver.com";
  final String subEmail2 = "kimsoso2@gaver.com";

  @Test
  public void 메일함_추가_성공_테스트() throws Exception {
    // given
    MockHttpSession mockHttpSession = new MockHttpSession();
    User user = new User(userId, pw, name, subEmail);
    MailCategoryDTO.MakeDTO newCategory = new MailCategoryDTO.MakeDTO();

    // when
    newCategory.setName("메일함이름1");
    userService.register(user);
    mockHttpSession.setAttribute("user", user);
    ResultActions result = mockMvc.perform(post("/mail/category")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(newCategory))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result.andExpect(status().isCreated());
    List<MailCategory> categories = mailCategoryService.findAll(user);
    assertEquals(1, categories.size());
    assertEquals("메일함이름1", categories.get(0).getName());
  }

  @Test
  public void 메일함_추가_실패_테스트_완성되지_않은_한글() throws Exception{
    // given
    User user = new User(userId, pw, name, subEmail);
    MockHttpSession mockHttpSession = new MockHttpSession();
    MailCategoryDTO.MakeDTO newCategory = new MailCategoryDTO.MakeDTO();

    // when
    newCategory.setName("ㅁㄴㅇㄹ");
    userService.register(user);
    mockHttpSession.setAttribute("user", user);
    ResultActions result = mockMvc.perform(post("/mail/category")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(newCategory))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
      .andExpect(jsonPath("errors[0].field").value("name"))
    ;
  }

  @Test
  public void 메일함_추가_실패_테스트_길이() throws Exception{
    // given
    User user = new User(userId, pw, name, subEmail);
    MockHttpSession mockHttpSession = new MockHttpSession();
    MailCategoryDTO.MakeDTO newCategory = new MailCategoryDTO.MakeDTO();

    // when
    newCategory.setName("012345678901234567890");
    userService.register(user);
    mockHttpSession.setAttribute("user", user);
    ResultActions result = mockMvc.perform(post("/mail/category")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(newCategory))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
      .andExpect(jsonPath("errors[0].field").value("name"))
    ;
  }

  @Test
  public void 메일함_이름_수정_성공_테스트() throws Exception {
    // given
    User user = new User(userId, pw, name, subEmail);
    MockHttpSession mockHttpSession = new MockHttpSession();
    MailCategoryDTO.RenameDTO renameDTO = new MailCategoryDTO.RenameDTO();
    String oldName = "123456";
    String newName = "하하호호히헤";

    // when
    userService.register(user);
    MailCategory mailCategory = new MailCategory(oldName, user);
    mailCategoryService.makeDir(mailCategory);
    renameDTO.setCategoryId(mailCategory.getId());
    renameDTO.setOldName(oldName);
    renameDTO.setNewName(newName);

    mockHttpSession.setAttribute("user", user);
    ResultActions result = mockMvc.perform(patch("/mail/category")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(renameDTO))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isOk())
      .andExpect(jsonPath("id").value(mailCategory.getId()))
      .andExpect(jsonPath("name").value(newName))
    ;
  }

  @Test
  public void 메일함_이름_수정_실패_테스트_길이() throws Exception {
    // given
    User user = new User(userId, pw, name, subEmail);
    MockHttpSession mockHttpSession = new MockHttpSession();
    MailCategoryDTO.RenameDTO renameDTO = new MailCategoryDTO.RenameDTO();
    String oldName = "123456";
    String newName = "하하호호히히헤헤흐흐허허후후해해효효휴휴햐";

    // when
    userService.register(user);
    MailCategory mailCategory = new MailCategory(oldName, user);
    mailCategoryService.makeDir(mailCategory);
    renameDTO.setCategoryId(mailCategory.getId());
    renameDTO.setOldName(oldName);
    renameDTO.setNewName(newName);

    mockHttpSession.setAttribute("user", user);
    ResultActions result = mockMvc.perform(patch("/mail/category")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(renameDTO))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
      .andExpect(jsonPath("errors[0].field").value("newName"))
    ;
  }

  @Test
  public void 메일함_이름_수정_실패_테스트_완성되지_않은_한글() throws Exception {
    // given
    User user = new User(userId, pw, name, subEmail);
    MockHttpSession mockHttpSession = new MockHttpSession();
    MailCategoryDTO.RenameDTO renameDTO = new MailCategoryDTO.RenameDTO();
    String oldName = "123456";
    String newName = "며ㅗㅈ얃ㅁㄴㅁㄴㄷ";

    // when
    userService.register(user);
    MailCategory mailCategory = new MailCategory(oldName, user);
    mailCategoryService.makeDir(mailCategory);
    renameDTO.setCategoryId(mailCategory.getId());
    renameDTO.setOldName(oldName);
    renameDTO.setNewName(newName);

    mockHttpSession.setAttribute("user", user);
    ResultActions result = mockMvc.perform(patch("/mail/category")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(renameDTO))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
      .andExpect(jsonPath("errors[0].field").value("newName"))
    ;
  }

  @Test
  public void 메일함_이름_수정_실패_테스트_이미_존재하는_이름() throws Exception {
    // given
    User user = new User(userId, pw, name, subEmail);
    MockHttpSession mockHttpSession = new MockHttpSession();
    MailCategoryDTO.RenameDTO renameDTO = new MailCategoryDTO.RenameDTO();
    String oldName = "123456";
    String newName = "123456";

    // when
    userService.register(user);
    MailCategory mailCategory = new MailCategory(oldName, user);
    mailCategoryService.makeDir(mailCategory);
    renameDTO.setCategoryId(mailCategory.getId());
    renameDTO.setOldName(oldName);
    renameDTO.setNewName(newName);

    mockHttpSession.setAttribute("user", user);
    ResultActions result = mockMvc.perform(patch("/mail/category")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(renameDTO))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("같은 이름의 메일함이 이미 존재합니다."))
      .andExpect(jsonPath("status").value(400))
    ;
  }

  @Test
  public void 메일함_이름_수정_실패_테스트_타유저의_메일함_수정() throws Exception {
    // given
    User user1 = new User(userId, pw, name, subEmail);
    User user2 = new User(userId2, pw, name, subEmail2);
    MockHttpSession mockHttpSession = new MockHttpSession();
    MailCategoryDTO.RenameDTO renameDTO = new MailCategoryDTO.RenameDTO();
    String oldName = "123456";
    String newName = "하하호호";

    // when
    userService.register(user1);
    userService.register(user2);
    MailCategory mailCategory = new MailCategory(oldName, user1);
    mailCategoryService.makeDir(mailCategory);
    renameDTO.setCategoryId(mailCategory.getId());
    renameDTO.setOldName(oldName);
    renameDTO.setNewName(newName);

    mockHttpSession.setAttribute("user", user2);
    ResultActions result = mockMvc.perform(patch("/mail/category")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(renameDTO))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("message").value("존재하지 않는 메일함입니다."))
      .andExpect(jsonPath("status").value(404))
    ;
  }


  @Test
  public void 메일함_이름_수정_실패_테스트_없는_메일함을_수정() throws Exception {
    // given
    User user = new User(userId, pw, name, subEmail);
    MockHttpSession mockHttpSession = new MockHttpSession();
    MailCategoryDTO.RenameDTO renameDTO = new MailCategoryDTO.RenameDTO();
    String oldName = "123456";
    String newName = "하하하호호호";

    // when
    userService.register(user);
    MailCategory mailCategory = new MailCategory(oldName, user);
    mailCategoryService.makeDir(mailCategory);
    renameDTO.setCategoryId(mailCategory.getId());
    renameDTO.setOldName(oldName + "7");
    renameDTO.setNewName(newName);

    mockHttpSession.setAttribute("user", user);
    ResultActions result = mockMvc.perform(patch("/mail/category")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(renameDTO))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("message").value("존재하지 않는 메일함입니다."))
      .andExpect(jsonPath("status").value(404))
    ;
  }

  @Test
  public void 메일함_전체_조회_성공_테스트() throws Exception {
    // given
    String categoryName1 = "메일함1";
    String categoryName2 = "메일함2";
    User user = new User(userId, pw, name, subEmail);
    MailCategory mailCategory1 = new MailCategory(categoryName1, user);
    MailCategory mailCategory2 = new MailCategory(categoryName2, user);
    MockHttpSession mockHttpSession = new MockHttpSession();
    userService.register(user);
    mailCategoryService.makeDir(mailCategory1);
    mailCategoryService.makeDir(mailCategory2);

    // when
    mockHttpSession.setAttribute("user", user);
    ResultActions result = mockMvc.perform(get("/mail/category")
      .session(mockHttpSession)
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isOk())
      .andExpect(jsonPath("[0].name").value(categoryName1))
      .andExpect(jsonPath("[1].name").value(categoryName2))
    ;
  }

  @Test
  public void 메일함_전체_조회_실패_테스_로그인_하지_않았을_때() throws Exception {
    // given
    String categoryName1 = "메일함1";
    String categoryName2 = "메일함2";
    User user = new User(userId, pw, name, subEmail);
    MailCategory mailCategory1 = new MailCategory(categoryName1, user);
    MailCategory mailCategory2 = new MailCategory(categoryName2, user);
    userService.register(user);
    mailCategoryService.makeDir(mailCategory1);
    mailCategoryService.makeDir(mailCategory2);

    // when
    ResultActions result = mockMvc.perform(get("/mail/category")
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isUnauthorized())
      .andExpect(jsonPath("status").value(401))
      .andExpect(jsonPath("code").value("AUTH004"))
    ;
  }
}
