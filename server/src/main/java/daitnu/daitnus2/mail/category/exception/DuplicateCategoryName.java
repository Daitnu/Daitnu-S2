package daitnu.daitnus2.mail.category.exception;

import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;

public class DuplicateCategoryName extends BusinessException {

  public DuplicateCategoryName() {
    super(ErrorCode.DUPLICATED_MAIL_CATEGORY_NAME);
  }
}
