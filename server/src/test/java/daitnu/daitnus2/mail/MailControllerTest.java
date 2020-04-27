package daitnu.daitnus2.Mail;

import com.fasterxml.jackson.databind.ObjectMapper;
import daitnu.daitnus2.accounts.AccountsDTO;
import daitnu.daitnus2.accounts.AccountsService;
import daitnu.daitnus2.database.entity.*;
import daitnu.daitnus2.mail.MailDTO;
import daitnu.daitnus2.mail.MailService;
import daitnu.daitnus2.mail.attachment.MailAttachmentService;
import daitnu.daitnus2.mail.category.MailCategoryService;
import daitnu.daitnus2.mail.template.MailTemplateService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class MailControllerTest {
  @Autowired AccountsService accountsService;
  @Autowired MailCategoryService mailCategoryService;
  @Autowired MailTemplateService mailTemplateService;
  @Autowired MailService mailService;
  @Autowired MailAttachmentService mailAttachmentService;
  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper objectMapper;

  private Long userId;
  private Long mailCategoryId1;
  private Long mailCategoryId2;
  private Long mailTemplateId;
  private Long mailAttachmentId;
  private Long mailId;

  @Before
  public void init() {
    AccountsDTO.RegisterDTO registerDTO = new AccountsDTO.RegisterDTO();
    registerDTO.setId("ksss012"); registerDTO.setPassword("pwpw123123");  registerDTO.setPasswordCheck("pwpw123123");
    registerDTO.setName("ksss");  registerDTO.setSubEmail("ksss012@daitnu.com");
    User user = accountsService.register(registerDTO);
    userId = user.getId();

    MailCategory mailbox1 = mailCategoryService.makeDir("mailbox1", userId);
    MailCategory mailbox2 = mailCategoryService.makeDir("mailbox2", userId);
    mailCategoryId1 = mailbox1.getId();
    mailCategoryId2 = mailbox2.getId();

    MailTemplate mailTemplate = mailTemplateService
      .makeMailTemplate("kyokyo@daitnu.com", "ksss012@daitnu2.com", "this is title", "this is subject");
    mailTemplateId = mailTemplate.getId();

    MailAttachment mailAttachment = mailAttachmentService
      .makeMailAttachment(mailTemplateId, "type hi", "name hi", "url hi", 10L);
    mailAttachmentId = mailAttachment.getId();

    Mail mail = mailService.makeMail(mailCategoryId1, userId, mailTemplateId);
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
    AccountsDTO.SessionUserDTO sessionUserDTO =
      new AccountsDTO.SessionUserDTO(userId, "ksss012", "ksss012@daitnu.com");
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
  public void 메일_정보_수정_실패_테스트_타입_잘못_지정() throws Exception {
    // given
    MailDTO.PatchMailDTO dto = new MailDTO.PatchMailDTO();
    dto.setType("LOTTOT");

    // when
    ResultActions result = mockMvc.perform(patch("/mail")
      .content(objectMapper.writeValueAsString(dto))
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
    AccountsDTO.SessionUserDTO sessionUserDTO =
      new AccountsDTO.SessionUserDTO(userId, "ksss012", "ksss012@daitnu.com");
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
    AccountsDTO.SessionUserDTO sessionUserDTO =
      new AccountsDTO.SessionUserDTO(userId, "ksss012", "ksss012@daitnu.com");
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
    AccountsDTO.SessionUserDTO sessionUserDTO =
      new AccountsDTO.SessionUserDTO(userId, "ksss012", "ksss012@daitnu.com");
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
    AccountsDTO.SessionUserDTO sessionUserDTO =
      new AccountsDTO.SessionUserDTO(userId, "ksss012", "ksss012@daitnu.com");
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
