package daitnu.daitnus2.mail.category.exception;

import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;

public class NotFoundCategory extends BusinessException {
  public NotFoundCategory() {
    super(ErrorCode.CATEGORY_NOT_FOUND);
  }
}
