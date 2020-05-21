package daitnu.daitnus2.mail;

import daitnu.daitnus2.accounts.AccountsSession;
import daitnu.daitnus2.database.entity.Mail;
import daitnu.daitnus2.exception.ErrorCode;
import daitnu.daitnus2.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/mail")
public class MailController {

  private final MailService mailService;
  private final ModelMapper modelMapper;

  @GetMapping
  public ResponseEntity<?> getMails(HttpSession session) {
    AccountsSession sessionUser = (AccountsSession) session.getAttribute("user");
    List<Mail> mails = mailService.findAll(sessionUser.getId());
    return new ResponseEntity<>(modelMapper.map(mails, MailDTO.ResponseMailDTO[].class), HttpStatus.OK);
  }

  @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> patchMail(@RequestBody @Valid MailDTO.PatchMailDTO dto, HttpSession session,
                                     BindingResult result) {
    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    AccountsSession sessionUser = (AccountsSession) session.getAttribute("user");
    mailService.patchMail(dto, sessionUser.getId());
    Mail mail = mailService.findOne(dto.getMailId());
    return new ResponseEntity<>(modelMapper.map(mail, MailDTO.ResponsePatchDTO.class), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<?> addMail(MailDTO.AddMailDTO dto, BindingResult result, HttpSession session) {
    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, result);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    AccountsSession sessionUser = (AccountsSession) session.getAttribute("user");
    Mail mail = mailService.addMail(sessionUser.getId(), dto);
    return new ResponseEntity<>(modelMapper.map(mail, MailDTO.ResponseAddMailDTO.class), HttpStatus.CREATED);
  }
}
