package daitnu.daitnus2.mail.category.exception;

import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;

public class DuplicateName extends BusinessException {

  public DuplicateName() {
    super(ErrorCode.DUPLICATED_MAIL_CATEGORY_NAME);
  }
}
