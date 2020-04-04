package daitnu.daitnus2.accounts.exception;

import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;


@SuppressWarnings("serial")
public class DuplicatUserId extends BusinessException {

  public DuplicatUserId() {
    super(ErrorCode.ID_DUPLICATION);
  }
}
