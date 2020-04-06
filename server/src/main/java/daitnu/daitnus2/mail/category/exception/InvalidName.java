package daitnu.daitnus2.mail.category.exception;

import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;

public class InvalidName extends BusinessException {
  public InvalidName() {
    super(ErrorCode.INVALID_CATEGORY_NAME);
  }
}
