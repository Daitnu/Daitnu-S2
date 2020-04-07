package daitnu.daitnus2.service;

import daitnu.daitnus2.database.entity.User;
import daitnu.daitnus2.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class AccountsControllerTest {
  @Autowired MockMvc mockMvc;
  @Autowired UserService userService;

  @Test
  public void 회원가입_컨트롤러_테스트() throws Exception {
    // given
    mockMvc.perform(post("/register")
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE)
      .content("{\"id\": \"kimsoso\", \"password\": \"123456\", \"passwordCheck\": \"123456\", " +
        "\"name\": \"한글명\", \"subEmail\": \"kimsoso@gaver.com\"}"))
      .andExpect(status().isCreated())
    ;

    // when
    List<User> users = userService.findAll();

    // then
    assertEquals(1, users.size());
    assertEquals("kimsoso", users.get(0).getUserId());
    assertEquals("kimsoso@daitnu2.com", users.get(0).getEmail());
  }
}
