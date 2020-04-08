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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
  final String pw = "12345";
  final String name = "kss";
  final String subEmail = "kimsoso@gaver.com";

  @Test
  public void 메일함_추가_성공_테스트() throws Exception {
    // given
    MockHttpSession mockHttpSession = new MockHttpSession();
    User user = new User(userId, pw, name, subEmail);
    MailCategoryDTO.MakeDTO newCategory = new MailCategoryDTO.MakeDTO();

    // when
    newCategory.setMailCategoryName("메일함이름1");
    userService.register(user);
    mockHttpSession.setAttribute("user", user);
    mockMvc.perform(post("/mail/category")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(newCategory))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isCreated())
    ;

    // then
    List<MailCategory> categories = mailCategoryService.findAll("kimsoso");
    assertEquals(1, categories.size());
    assertEquals("메일함이름1", categories.get(0).getName());
  }

  @Test
  public void 메일함_추가_실패_테스트1() throws Exception{
    // given
    User user = new User(userId, pw, name, subEmail);
    MockHttpSession mockHttpSession = new MockHttpSession();
    MailCategoryDTO.MakeDTO newCategory = new MailCategoryDTO.MakeDTO();

    // when
    newCategory.setMailCategoryName("ㅁㄴㅇㄹ");
    userService.register(user);
    mockHttpSession.setAttribute("user", user);
    ResultActions result = mockMvc.perform(post("/mail/category")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(newCategory))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print())
      .andExpect(status().isBadRequest());

    // then
    result
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
      .andExpect(jsonPath("errors[0].field").value("mailCategoryName"))
    ;
  }
}
