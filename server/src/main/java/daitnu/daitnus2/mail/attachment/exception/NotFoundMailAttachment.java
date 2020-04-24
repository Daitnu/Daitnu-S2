package daitnu.daitnus2.mail.attachment.exception;

import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;

public class NotFoundMailAttachment extends BusinessException {
  public NotFoundMailAttachment() {
    super(ErrorCode.MAIL_ATTACHMENT_NOT_FOUNT);
  }
}
