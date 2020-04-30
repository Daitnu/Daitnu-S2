package daitnu.daitnus2.accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountsSession {
  private Long id;
  private String userId;
  private String subEmail;
}
