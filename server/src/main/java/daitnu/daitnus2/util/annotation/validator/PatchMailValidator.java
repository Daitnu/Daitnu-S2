package daitnu.daitnus2.util.annotation.validator;

import daitnu.daitnus2.mail.MailDTO;
import daitnu.daitnus2.util.annotation.PatchMailValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PatchMailValidator implements ConstraintValidator<PatchMailValid, MailDTO.PatchMailDTO> {

  private boolean moveArgumentNullCheck(MailDTO.PatchMailDTO dto) {
    return dto.getCategoryId() != null && dto.getImportant() == null &&
      dto.getRemoved() == null && dto.getRead() == null;
  }

  private boolean alterArgumentNullCheck(MailDTO.PatchMailDTO dto) {
    return dto.getCategoryId() == null && dto.getImportant() != null &&
      dto.getRemoved() != null && dto.getRead() != null;
  }

  @Override
  public boolean isValid(MailDTO.PatchMailDTO dto, ConstraintValidatorContext context) {
    if (dto.getType() != null) {
      if (dto.getType().equals(MailDTO.PatchType.MOVE.toString())) {
        if (moveArgumentNullCheck(dto)) {
          return true;
        }
      } else if (dto.getType().equals(MailDTO.PatchType.ALTER.toString())) {
        if (alterArgumentNullCheck(dto)) {
          return true;
        }
      }
    }
    return false;
  }
}
