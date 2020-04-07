package daitnu.daitnus2.service;

import daitnu.daitnus2.database.entity.MailCategory;
import daitnu.daitnus2.database.entity.User;
import daitnu.daitnus2.mail.category.MailCategoryService;
import daitnu.daitnus2.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class MailCategoryControllerTest {
  @Autowired UserService userService;
  @Autowired MailCategoryService mailCategoryService;
  @Autowired MockMvc mockMvc;

  @Test
  public void postControllerTest() throws Exception {
    // given
    MockHttpSession mockHttpSession = new MockHttpSession();
    String mailboxName = "mailbox1";
    User user = new User("kimsoso", "1234", "kss", "kimsoso@gaver.com");
    MailCategory mailCategory = new MailCategory(mailboxName, user);

    // when
    userService.register(user);
    mailCategoryService.makeDir(mailCategory);
    mockHttpSession.setAttribute("user", user);

    // then
    mockMvc.perform(post("/mail/category").session(mockHttpSession))
      .andExpect(status().isCreated())
    ;
  }
}
