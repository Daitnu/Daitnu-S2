package daitnu.daitnus2.accounts.exception;

import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;

@SuppressWarnings("serial")
public class DuplicateSubEmail extends BusinessException {

  public DuplicateSubEmail() {
    super(ErrorCode.EMAIL_DUPLICATION);
  }
}
