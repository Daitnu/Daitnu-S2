package daitnu.daitnus2.mail.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MailCategoryDTO {

  @Getter @Setter
  public static class MakeDTO {
    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z가-힣 ]{1,20}$",
            message = "메일함 이름은 1자이상 20자 이하, 완성된 한글, 영문, 숫자의 조합만 가능합니다")
    private String name;
  }

  @Getter @Setter
  public static class RenameDTO {
    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z가-힣 ]{1,20}$",
            message = "메일함 이름은 1자이상 20자 이하, 완성된 한글, 영문, 숫자의 조합만 가능합니다")
    private String oldName;

    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z가-힣 ]{1,20}$",
            message = "메일함 이름은 1자이상 20자 이하, 완성된 한글, 영문, 숫자의 조합만 가능합니다")
    private String newName;

    @NotNull
    private Long categoryId;
  }

  @Getter @Setter
  public static class DeleteDTO {
    @NotNull
    private Long id;

    @NotBlank
    private String name;
  }

  @Getter @Setter
  public static class Response {
    private Long id;
    private String name;
  }
}
