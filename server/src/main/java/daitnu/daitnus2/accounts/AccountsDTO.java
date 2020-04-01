package daitnu.daitnus2.accounts;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class AccountsDTO {

  @Getter
  @Setter
  public static class LoginDTO {

    @NotBlank(message = "아이디는 반드시 입력해야 합니다.")
    @Length(min = 5, message = "아이디는 최소 5자리 이상입니다.")
    @Length(max = 20, message = "아이디는 최대 20자리 입니다.")
    private String id;

    @NotBlank(message = "비밀번호는 반드시 입력해야 합니다.")
    @Length(min = 5, message = "비밀번호는 최소 5자리 이상입니다.")
    @Length(max = 100, message = "비밀번호는 최대 30자리 입니다.")
    private String password;
  }
}
