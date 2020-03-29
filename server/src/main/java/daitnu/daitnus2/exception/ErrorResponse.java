package daitnu.daitnus2.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {

  private int status;
  private String message;
  private String code;
  private List<FieldError> errors;

  public boolean hasFiledError() {
    return this.errors != null;
  }

  public ErrorResponse(final ErrorCode errorCode, final List<FieldError> errors) {
    this.status = errorCode.getStatus();
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
    this.errors = errors;
  }

  public ErrorResponse(final ErrorCode errorCode) {
    this.status = errorCode.getStatus();
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
  }

  public static ErrorResponse of(final BusinessException e) {
    return new ErrorResponse(e.getErrorCode());
  }

  public static ErrorResponse of(final ErrorCode code) {
    return new ErrorResponse(code);
  }

  public static ErrorResponse of(final ErrorCode code, final List<FieldError> errors) {
    return new ErrorResponse(code, errors);
  }

  public static ErrorResponse of(final ErrorCode code, final BindingResult result) {
    return new ErrorResponse(code, FieldError.of(result));
  }

  @NoArgsConstructor
  @Getter
  public static class FieldError {
    private String field;
    private String value;
    private String reason;

    private FieldError(final String field, final String value, final String reason) {
      this.field = field;
      this.value = value;
      this.reason = reason;
    }

    private static List<FieldError> of(final BindingResult result) {
      return result.getFieldErrors().stream()
        .map(obj -> new FieldError(obj.getField(), obj.getCode(), obj.getDefaultMessage()))
        .collect(Collectors.toList());
    }
  }
}
