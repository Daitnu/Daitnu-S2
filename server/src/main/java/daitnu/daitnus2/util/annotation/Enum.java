package daitnu.daitnus2.util.annotation;

import daitnu.daitnus2.util.annotation.validator.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnumValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME) // 이 annotation을 언제까지 유지시킬 것인가
public @interface Enum {
  String message() default "Invalid value. Not supported type.";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
  Class<? extends java.lang.Enum<?>> enumClass();
  boolean ignoreCase() default false;
}
