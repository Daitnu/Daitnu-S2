package daitnu.daitnus2.validation.validator;

import daitnu.daitnus2.validation.annotation.Enum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<Enum, String> {

  private Enum annotation;

  @Override
  public void initialize(Enum constraintAnnotation) {
    this.annotation = constraintAnnotation;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    java.lang.Enum<?>[] constants = this.annotation.enumClass().getEnumConstants();
    if (constants != null) {
      for (java.lang.Enum<?> constant: constants) {
        if (value.equals(constant.toString())
            || (this.annotation.ignoreCase() && value.equalsIgnoreCase(constant.toString()))) {
          return true;
        }
      }
    }
    return false;
  }
}
