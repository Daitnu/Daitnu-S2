package daitnu.daitnus2.accounts;

import com.fasterxml.jackson.databind.util.JSONPObject;
import daitnu.daitnus2.dto.AccountsDTO;
import daitnu.daitnus2.exception.ErrorCode;
import daitnu.daitnus2.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AccountsController {

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid AccountsDTO.LoginDTO dto , BindingResult result) {

    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //TODO : SERVICE 계층 작성하기

    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping("/register")
  public String register() {
    return "register page";
  }

  @GetMapping("/password")
  public String changePassword() {
    return "change page";
  }

}
