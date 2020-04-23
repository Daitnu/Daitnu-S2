package daitnu.daitnus2.util;

import daitnu.daitnus2.accounts.AccountsDTO;
import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;

import javax.servlet.http.HttpSession;

public class ControllerUtil {
  public static AccountsDTO.SessionUserDTO getSessionUser(HttpSession session) {
    AccountsDTO.SessionUserDTO user = (AccountsDTO.SessionUserDTO) session.getAttribute("user");
    if (user == null) {
      throw new BusinessException(ErrorCode.UNAUTHORIZED);
    }
    return user;
  }
}
