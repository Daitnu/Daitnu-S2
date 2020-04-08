package daitnu.daitnus2.MailCategory;

import daitnu.daitnus2.database.entity.MailCategory;
import daitnu.daitnus2.database.entity.User;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

  final String userId = "kimsoso";
  final String pw = "12345";
  final String name = "kss";
  final String subEmail = "kimsoso@gaver.com";

  @Test
  public void 메일함_추가_컨트롤러_테스트() throws Exception {
    // given
    User user = new User(userId, pw, name, subEmail);
    MockHttpSession mockHttpSession = new MockHttpSession();

    // when
    userService.register(user);
    mockHttpSession.setAttribute("user", user);
    mockMvc.perform(post("/mail/category")
      .session(mockHttpSession)
      .content("{\"mailCategoryName\": \"메일함이름1\"}")
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
}
