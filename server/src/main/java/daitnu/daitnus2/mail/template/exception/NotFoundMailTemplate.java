package daitnu.daitnus2.mail.template.exception;

import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;

public class NotFoundMailTemplate extends BusinessException {
  public NotFoundMailTemplate() {
    super(ErrorCode.MAIL_TEMPLATE_NOT_FOUND);
  }
}
