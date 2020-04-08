package daitnu.daitnus2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<?> handle(NoHandlerFoundException e) {
    return new ResponseEntity<>(ErrorResponse.of(ErrorCode.NOT_FOUND_EXCEPTION), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> handleUserNotExistException(BusinessException e) {
    return new ResponseEntity<>(ErrorResponse.of(e), HttpStatus.valueOf(e.getErrorCode().getStatus()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handle(MethodArgumentNotValidException e) {
    return new ResponseEntity<>(ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult()), HttpStatus.BAD_REQUEST);
  }
}
