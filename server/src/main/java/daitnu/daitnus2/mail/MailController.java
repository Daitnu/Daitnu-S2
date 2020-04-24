package daitnu.daitnus2.mail;

import daitnu.daitnus2.accounts.AccountsDTO;
import daitnu.daitnus2.database.entity.Mail;
import daitnu.daitnus2.exception.ErrorCode;
import daitnu.daitnus2.exception.ErrorResponse;
import daitnu.daitnus2.util.ControllerUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/mail")
public class MailController {

  private final MailService mailService;
  private final ModelMapper modelMapper;

  @GetMapping
  public ResponseEntity<?> getMails(HttpServletRequest req) {
    AccountsDTO.SessionUserDTO sessionUser = ControllerUtil.getSessionUser(req.getSession());
    List<Mail> mails = mailService.findAll(sessionUser.getId()); // TODO: pagination
    return new ResponseEntity<>(modelMapper.map(mails, MailDTO.ResponseMailDTO[].class), HttpStatus.OK);
  }

  @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> patchMail(@RequestBody @Valid MailDTO.PatchMailDTO dto,
                                     HttpServletRequest req,
                                     BindingResult result) {
    AccountsDTO.SessionUserDTO sessionUser = ControllerUtil.getSessionUser(req.getSession());
    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    mailService.patchMail(dto, sessionUser.getId());
    Mail mail = mailService.findOne(dto.getMailId());
    return new ResponseEntity<>(modelMapper.map(mail, MailDTO.PatchMailDTO.class), HttpStatus.OK);
  }
}
