package daitnu.daitnus2.accounts;

import daitnu.daitnus2.exception.ErrorCode;
import daitnu.daitnus2.exception.ErrorResponse;
import daitnu.daitnus2.database.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountsController {
  private final AccountsService service;
  private final AccountsValidation accountsValidation;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid AccountsDTO.LoginDTO dto, BindingResult result) {

    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    User user = service.login(dto);
    // todo : 반환형식 mapper
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping("/register")
  public ResponseEntity<?> register(@RequestBody @Valid AccountsDTO.RegisterDTO dto, BindingResult result) {
    accountsValidation.pwEqCheck(dto, result);

    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    User user = service.register(dto);
    return new ResponseEntity<>(dto, HttpStatus.CREATED);
  }

  @GetMapping("/password")
  public String changePassword() {
    return "change page";
  }

}
