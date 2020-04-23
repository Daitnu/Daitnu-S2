package daitnu.daitnus2.util;

import daitnu.daitnus2.accounts.AccountsDTO;
import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

@Component
public class ControllerUtil {
  public static AccountsDTO.SessionUserDTO getSessionUser(@NotNull HttpSession session) {
    AccountsDTO.SessionUserDTO user = (AccountsDTO.SessionUserDTO) session.getAttribute("user");
    if (user == null) {
      throw new BusinessException(ErrorCode.UNAUTHORIZED);
    }
    return user;
  }
}
