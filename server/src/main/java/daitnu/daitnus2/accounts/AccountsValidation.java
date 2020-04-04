package daitnu.daitnus2.accounts;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class AccountsValidation {

  public void pwEqCheck(AccountsDTO.RegisterDTO dto, Errors errors) {
    if(errors.hasErrors()) {
      return;
    }

    String pw = dto.getPassword();
    String pwCheck = dto.getPasswordCheck();
    boolean isSame = pw.equals(pwCheck);
    if(!isSame) {
      errors.rejectValue("password", "secret", "비밀번호 확인값이 올바르지 않습니다.");
    }
  }
}

