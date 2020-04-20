package daitnu.daitnus2.mail;

import lombok.Getter;
import lombok.Setter;

public class MailDTO {

  @Getter @Setter
  public static class ResponseMails {
    private Long mailId;
    private Long categoryId;
    private Long mailTemplateId;
    private String mailTemplateFrom;
    private String mailTemplateTitle;
    private String mailTemplateReceivers;
    private boolean isImportant;
    private boolean isRead;
    private boolean isRemoved;
  }
}
