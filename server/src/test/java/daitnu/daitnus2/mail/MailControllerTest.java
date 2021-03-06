package daitnu.daitnus2.mail;

import com.fasterxml.jackson.databind.ObjectMapper;
import daitnu.daitnus2.mail.category.MailCategoryService;
import daitnu.daitnus2.accounts.AccountsDTO;
import daitnu.daitnus2.accounts.AccountsService;
import daitnu.daitnus2.accounts.AccountsSession;
import daitnu.daitnus2.database.entity.*;
import org.junit.Before;
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

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class MailControllerTest {
  @Autowired
  AccountsService accountsService;
  @Autowired
  MailCategoryService mailCategoryService;
  @Autowired
  MailService mailService;
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;

  private Long userId;
  private Long mailCategoryId1;
  private Long mailCategoryId2;
  private Long mailId;

  @Before
  public void init() {
    AccountsDTO.RegisterDTO registerDTO = new AccountsDTO.RegisterDTO();
    registerDTO.setId("ksss012");
    registerDTO.setPassword("pwpw123123");
    registerDTO.setPasswordCheck("pwpw123123");
    registerDTO.setName("ksss");
    registerDTO.setSubEmail("ksss012@daitnu.com");
    User user = accountsService.register(registerDTO);
    userId = user.getId();

    MailCategory mailbox1 = mailCategoryService.makeDir("mailbox1", userId);
    MailCategory mailbox2 = mailCategoryService.makeDir("mailbox2", userId);
    mailCategoryId1 = mailbox1.getId();
    mailCategoryId2 = mailbox2.getId();

    MailDTO.AddMailDTO addMailDTO = new MailDTO.AddMailDTO();
    addMailDTO.setFrom("kyokyo@daitnu.com");
    addMailDTO.setMailReceivers("ksss012@daitnu2.com");
    addMailDTO.setTitle("this is title");
    addMailDTO.setSubject("this is subject");
    addMailDTO.setMailCategoryName(mailbox1.getName());

    Mail mail = mailService.addMail(userId, addMailDTO);
    mailId = mail.getId();
  }

  @Test
  public void 메일_정보_수정_성공_테스트_메일함_이동() throws Exception {
    // given
    MailDTO.PatchMailDTO dto = new MailDTO.PatchMailDTO();
    dto.setType(MailDTO.PatchType.MOVE.toString());
    dto.setCategoryId(mailCategoryId2);
    dto.setMailId(mailId);
    MockHttpSession mockHttpSession = new MockHttpSession();

    // when
    AccountsSession sessionUserDTO =
      new AccountsSession(userId, "ksss012", "ksss012@daitnu.com");

    mockHttpSession.setAttribute("user", sessionUserDTO);
    ResultActions result = mockMvc.perform(patch("/mail")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(dto))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isOk())
      .andExpect(jsonPath("id").value(dto.getMailId()))
      .andExpect(jsonPath("categoryId").value(dto.getCategoryId()))
      .andExpect(jsonPath("important").value(false))
      .andExpect(jsonPath("read").value(false))
      .andExpect(jsonPath("removed").value(false))
    ;
  }

  @Test
  public void 메일_정보_수정_성공_테스트() throws Exception {
    // given
    MailDTO.PatchMailDTO dto = new MailDTO.PatchMailDTO();
    dto.setType(MailDTO.PatchType.ALTER.toString());
    dto.setMailId(mailId);
    dto.setImportant(true);
    dto.setRead(true);
    dto.setRemoved(false);
    MockHttpSession mockHttpSession = new MockHttpSession();

    // when
    AccountsSession sessionUserDTO =
      new AccountsSession(userId, "ksss012", "ksss012@daitnu.com");
    mockHttpSession.setAttribute("user", sessionUserDTO);
    ResultActions result = mockMvc.perform(patch("/mail")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(dto))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isOk())
      .andExpect(jsonPath("id").value(dto.getMailId()))
      .andExpect(jsonPath("important").value(true))
      .andExpect(jsonPath("read").value(true))
      .andExpect(jsonPath("removed").value(false))
    ;

    Mail mail = mailService.findOne(mailId);
    assertEquals(mail.isImportant(), true);
    assertEquals(mail.isRead(), true);
    assertEquals(mail.isRemoved(), false);
  }

  @Test
  public void 메일_정보_수정_실패_테스트_타입_잘못_지정() throws Exception {
    // given
    MailDTO.PatchMailDTO dto = new MailDTO.PatchMailDTO();
    dto.setType("LOTTOT");
    MockHttpSession mockHttpSession = new MockHttpSession();

    // when
    AccountsSession sessionUserDTO =
      new AccountsSession(userId, "ksss012", "ksss012@daitnu.com");
    mockHttpSession.setAttribute("user", sessionUserDTO);

    // when
    ResultActions result = mockMvc.perform(patch("/mail")
      .content(objectMapper.writeValueAsString(dto))
      .session(mockHttpSession)
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
      .andExpect(jsonPath("errors[0].field").value("type"))
    ;
  }

  @Test
  public void 메일_정보_수정_실패_테스트_아무것도_넘기지_않음() throws Exception {
    // given
    MailDTO.PatchMailDTO dto = new MailDTO.PatchMailDTO();
    MockHttpSession mockHttpSession = new MockHttpSession();

    // when
    AccountsSession sessionUserDTO =
      new AccountsSession(userId, "ksss012", "ksss012@daitnu.com");
    mockHttpSession.setAttribute("user", sessionUserDTO);

    // when
    ResultActions result = mockMvc.perform(patch("/mail")
      .content(objectMapper.writeValueAsString(dto))
      .session(mockHttpSession)
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
    ;
  }

  @Test
  public void 메일_정보_수정_실패_테스트_메일_아이디만_넘김() throws Exception {
    // given
    MailDTO.PatchMailDTO dto = new MailDTO.PatchMailDTO();
    dto.setMailId(mailId);
    MockHttpSession mockHttpSession = new MockHttpSession();

    // when
    AccountsSession sessionUserDTO =
      new AccountsSession(userId, "ksss012", "ksss012@daitnu.com");
    mockHttpSession.setAttribute("user", sessionUserDTO);

    // when
    ResultActions result = mockMvc.perform(patch("/mail")
      .content(objectMapper.writeValueAsString(dto))
      .session(mockHttpSession)
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
      .andExpect(jsonPath("errors[0].field").value("type"))
    ;
  }

  @Test
  public void 메일_정보_수정_실패_테스트_로그인_하지_않음() throws Exception {
    // given
    MailDTO.PatchMailDTO dto = new MailDTO.PatchMailDTO();
    dto.setType(MailDTO.PatchType.MOVE.toString());
    dto.setCategoryId(mailCategoryId2);
    dto.setMailId(mailId);

    // when
    ResultActions result = mockMvc.perform(patch("/mail")
      .content(objectMapper.writeValueAsString(dto))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isUnauthorized())
      .andExpect(jsonPath("code").value("AUTH004"))
    ;
  }

  @Test
  public void 메일_정보_수정_실패_테스트_메일함_이동_케이스_메일함_아이디_null() throws Exception {
    // given
    MailDTO.PatchMailDTO dto = new MailDTO.PatchMailDTO();
    dto.setType(MailDTO.PatchType.MOVE.toString());
    dto.setMailId(mailId);
    dto.setCategoryId(null);
    MockHttpSession mockHttpSession = new MockHttpSession();

    // when
    AccountsSession sessionUserDTO =
      new AccountsSession(userId, "ksss012", "ksss012@daitnu.com");
    mockHttpSession.setAttribute("user", sessionUserDTO);
    ResultActions result = mockMvc.perform(patch("/mail")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(dto))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
    ;
  }

  @Test
  public void 메일_정보_수정_실패_테스트_메일함_이동_케이스_메일_아이디_null() throws Exception {
    // given
    MailDTO.PatchMailDTO dto = new MailDTO.PatchMailDTO();
    dto.setType(MailDTO.PatchType.MOVE.toString());
    dto.setMailId(null);
    dto.setCategoryId(mailCategoryId2);
    MockHttpSession mockHttpSession = new MockHttpSession();

    // when
    AccountsSession sessionUserDTO =
      new AccountsSession(userId, "ksss012", "ksss012@daitnu.com");
    mockHttpSession.setAttribute("user", sessionUserDTO);
    ResultActions result = mockMvc.perform(patch("/mail")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(dto))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
    ;
  }

  @Test
  public void 메일_정보_수정_실패_테스트_메일함_이동_케이스_다른_값_null_아님() throws Exception {
    // given
    MailDTO.PatchMailDTO dto = new MailDTO.PatchMailDTO();
    dto.setType(MailDTO.PatchType.MOVE.toString());
    dto.setMailId(mailId);
    dto.setCategoryId(mailCategoryId2);
    dto.setRemoved(false);
    MockHttpSession mockHttpSession = new MockHttpSession();

    // when
    AccountsSession sessionUserDTO =
      new AccountsSession(userId, "ksss012", "ksss012@daitnu.com");
    mockHttpSession.setAttribute("user", sessionUserDTO);
    ResultActions result = mockMvc.perform(patch("/mail")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(dto))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
    ;
  }

  @Test
  public void 메일_정보_수정_실패_테스트_세개의_특성중_하나라도_null() throws Exception {
    // given
    MailDTO.PatchMailDTO dto = new MailDTO.PatchMailDTO();
    dto.setType(MailDTO.PatchType.ALTER.toString());
    dto.setMailId(mailId);
    dto.setImportant(null);
    dto.setRead(true);
    dto.setRemoved(true);
    MockHttpSession mockHttpSession = new MockHttpSession();

    // when
    AccountsSession sessionUserDTO =
      new AccountsSession(userId, "ksss012", "ksss012@daitnu.com");
    mockHttpSession.setAttribute("user", sessionUserDTO);
    ResultActions result = mockMvc.perform(patch("/mail")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(dto))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
    ;
  }

  @Test
  public void 메일_정보_수정_실패_테스트_메일함_아이디가_null_아님() throws Exception {
    // given
    MailDTO.PatchMailDTO dto = new MailDTO.PatchMailDTO();
    dto.setType(MailDTO.PatchType.ALTER.toString());
    dto.setMailId(mailId);
    dto.setCategoryId(mailCategoryId2);
    dto.setImportant(true);
    dto.setRead(true);
    dto.setRemoved(true);
    MockHttpSession mockHttpSession = new MockHttpSession();

    // when
    AccountsSession sessionUserDTO =
      new AccountsSession(userId, "ksss012", "ksss012@daitnu.com");
    mockHttpSession.setAttribute("user", sessionUserDTO);
    ResultActions result = mockMvc.perform(patch("/mail")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(dto))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
    ;
  }

  @Test
  public void 메일_정보_수정_실패_테스트_메일_아이디_null() throws Exception {
    // given
    MailDTO.PatchMailDTO dto = new MailDTO.PatchMailDTO();
    dto.setType(MailDTO.PatchType.ALTER.toString());
    dto.setMailId(null);
    dto.setImportant(true);
    dto.setRead(true);
    dto.setRemoved(true);
    MockHttpSession mockHttpSession = new MockHttpSession();

    // when
    AccountsSession sessionUserDTO =
      new AccountsSession(userId, "ksss012", "ksss012@daitnu.com");
    mockHttpSession.setAttribute("user", sessionUserDTO);
    ResultActions result = mockMvc.perform(patch("/mail")
      .session(mockHttpSession)
      .content(objectMapper.writeValueAsString(dto))
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    // then
    result
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("message").value("Invalid Input Value"))
      .andExpect(jsonPath("status").value(400))
    ;
  }
}
