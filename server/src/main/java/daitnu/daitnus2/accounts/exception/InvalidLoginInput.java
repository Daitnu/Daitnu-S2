package daitnu.daitnus2.accounts.exception;

import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;

@SuppressWarnings("serial")
public class InvalidLoginInput extends BusinessException {

  public InvalidLoginInput() {
    super(ErrorCode.INVALID_LOGIN_INPUT);
  }
}
