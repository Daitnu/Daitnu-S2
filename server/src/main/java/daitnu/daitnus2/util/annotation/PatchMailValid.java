package daitnu.daitnus2.util.annotation;

import daitnu.daitnus2.util.annotation.validator.PatchMailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PatchMailValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PatchMailValid {
  String message() default "Invalid value";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
