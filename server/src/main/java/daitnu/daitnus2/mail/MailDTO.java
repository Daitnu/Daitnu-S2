package daitnu.daitnus2.mail;

import daitnu.daitnus2.util.annotation.Enum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class MailDTO {

  @Getter @Setter
  public static class ResponseMailDTO {
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

  @Getter @Setter
  public static class PatchMailDTO {
    @Enum(enumClass = PatchType.class)
    private String type;

    // MOVE 일 경우 아래의 변수들이 넘어와야 함
    private Long mailId;
    private Long categoryId;

    // ALTER 일 경우 아래의 변수들이 넘어와야 함
    private boolean isImportant;
    private boolean isRead;
    private boolean isRemoved;
  }

  public static class ResponsePatchMove {
    @NotNull
    private Long mailId;

    @NotNull
    private Long categoryId;
  }

  public static class ResponsePatchAlter {
    @NotNull
    private Long mailId;

    @NotNull
    private boolean isImportant;

    @NotNull
    private boolean isRead;

    @NotNull
    private boolean isRemoved;
  }

  public enum PatchType {
    MOVE, ALTER
  }
}
