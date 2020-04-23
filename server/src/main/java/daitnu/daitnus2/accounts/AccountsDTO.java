package daitnu.daitnus2.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
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

  @Getter
  @Setter
  public static class RegisterDTO {
    @NotBlank(message = "아이디는 반드시 입력해야 합니다.")
    @Length(min = 5, message = "아이디는 최소 5자리 이상입니다.")
    @Length(max = 20, message = "아이디는 최대 20자리 입니다.")
    private String id;

    @NotBlank(message = "비밀번호는 반드시 입력해야 합니다.")
    @Length(min = 5, message = "비밀번호는 최소 5자리 이상입니다.")
    @Length(max = 100, message = "비밀번호는 최대 30자리 입니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 반드시 입력해야 합니다.")
    @Length(min = 5, message = "비밀번호 확인은 최소 5자리 이상입니다.")
    @Length(max = 100, message = "비밀번호 확인은 최대 30자리 입니다.")
    private String passwordCheck;

    @NotBlank(message = "이름은 반드시 입력해야 합니다.")
    @Length(min = 2, message = "이름은 최소 2자리 이상입니다.")
    @Length(max = 10, message = "이름은 최대 길이 10입니다.")
    private String name;

    @NotBlank
    @Email(message = "서브 이메일은 이메일 주소여야 합니다.")
    @Length(max = 50, message = "서브 이메일의 최대 길이는 50입니다.")
    private String subEmail;
  }

  @Getter @Setter
  @AllArgsConstructor
  public static class SessionUserDTO {
    private Long id;
    private String userId;
    private String subEmail;
  }

  @Setter
  @Getter
  public static class ResponseLogin {
    private String id;
  }

  @Getter
  @Setter
  public static class ResponseRegister {
    private Long no;
    private String email;
    private String name;
    private String subEmail;
  }
}
