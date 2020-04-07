package daitnu.daitnus2.Accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import daitnu.daitnus2.accounts.AccountsDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
public class AccountsControllerTest {
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;

  final String userId = "newuser";
  final String password = "password";
  final String passwordCheck = "password";
  final String subEmail = "newuser@daitnu.com";
  final String name = "다잇누";
  final String domain = "@daitnu2.com";

  @Test
  public void 회원가입_성공케이스() throws Exception {
    final AccountsDTO.RegisterDTO newUser = new AccountsDTO.RegisterDTO();
    newUser.setId(userId);
    newUser.setPassword(password);
    newUser.setPasswordCheck(passwordCheck);
    newUser.setSubEmail(subEmail);
    newUser.setName(name);

    ResultActions result = mockMvc.perform(post("/register")
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE)
      .content(objectMapper.writeValueAsString(newUser)))
      .andExpect(status().isCreated());

    result.andExpect(jsonPath("$.email").value(userId + domain));
    result.andExpect(jsonPath("$.name").value(name));
    result.andExpect(jsonPath("$.subEmail").value(subEmail));
  }


  @Test
  public void 회원가입_실패케이스_비밀번호확인() throws Exception {
    final AccountsDTO.RegisterDTO newUser = new AccountsDTO.RegisterDTO();
    newUser.setId(userId);
    newUser.setPassword(password);
    newUser.setPasswordCheck(passwordCheck + "1");
    newUser.setSubEmail(subEmail);
    newUser.setName(name);

    ResultActions result = mockMvc.perform(post("/register")
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE)
      .content(objectMapper.writeValueAsString(newUser)))
      .andExpect(status().isBadRequest());

    result.andExpect(jsonPath("$.message").value("Invalid Input Value"));
    result.andExpect(jsonPath("$.status").value(400));
    result.andExpect(jsonPath("$.errors[0].field").value("password"));
    result.andExpect(jsonPath("$.errors[0].value").value("secret"));
  }

  @Test
  public void 회원가입_실패케이스_아이디가_짧은경우() throws Exception {
    final AccountsDTO.RegisterDTO newUser = new AccountsDTO.RegisterDTO();
    newUser.setId("id");
    newUser.setPassword(password);
    newUser.setPasswordCheck(passwordCheck);
    newUser.setSubEmail(subEmail);
    newUser.setName(name);

    ResultActions result = mockMvc.perform(post("/register")
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON_VALUE)
      .content(objectMapper.writeValueAsString(newUser)))
      .andExpect(status().isBadRequest());

    result.andExpect(jsonPath("$.message").value("Invalid Input Value"));
    result.andExpect(jsonPath("$.status").value(400));
    result.andExpect(jsonPath("$.errors[0].field").value("id"));
    result.andExpect(jsonPath("$.errors[0].value").value("Length"));
  }

}
