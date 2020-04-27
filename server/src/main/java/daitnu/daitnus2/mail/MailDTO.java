package daitnu.daitnus2.mail;

import daitnu.daitnus2.util.annotation.Enum;
import lombok.Getter;
import lombok.Setter;

public class MailDTO {

  @Getter @Setter
  public static class ResponseMailDTO {
    private Long mailId;
    private Long categoryId;
    private Long mailTemplateId;
    private String mailTemplateFrom;
    private String mailTemplateTitle;
    private String mailTemplateReceivers;
    private Boolean important;
    private Boolean read;
    private Boolean removed;
  }

  @Getter @Setter
  public static class PatchMailDTO {
    @Enum(enumClass = PatchType.class)
    private String type;

    // MOVE 일 경우 아래의 변수들이 넘어와야 함
    private Long mailId;
    private Long categoryId;

    // ALTER 일 경우 아래의 변수들이 넘어와야 함
    private Boolean important;
    private Boolean read;
    private Boolean removed;
  }

  @Getter @Setter
  public static class ResponsePatchDTO {
    private Long id; // mail id
    private Long categoryId;
    private Boolean important;
    private Boolean read;
    private Boolean removed;
  }

  public enum PatchType {
    MOVE, ALTER
  }
}
