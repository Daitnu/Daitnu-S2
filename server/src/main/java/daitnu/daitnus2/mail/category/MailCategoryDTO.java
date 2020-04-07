package daitnu.daitnus2.mail.category;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MailCategoryDTO {

  @Getter @Setter
  public static class MakeDTO {
    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z가-힣 ]{1,20}$")
    private String mailCategoryName;
  }

  @Getter @Setter
  public static class ResponseMake {
    private Long id;
    private String name;
  }
}
