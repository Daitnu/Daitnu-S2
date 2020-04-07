package daitnu.daitnus2.mail.exception;

import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;

public class NotFoundMail extends BusinessException {
  public NotFoundMail() {
    super(ErrorCode.MAIL_NOT_FOUNT);
  }
}
