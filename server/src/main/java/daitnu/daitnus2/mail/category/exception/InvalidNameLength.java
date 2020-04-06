package daitnu.daitnus2.mail.category.exception;

import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;

public class InvalidNameLength extends BusinessException {
  public InvalidNameLength() {
    super(ErrorCode.INVALID_CATEGORY_NAME_LENGTH);
  }
}
