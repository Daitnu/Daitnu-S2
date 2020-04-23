package daitnu.daitnus2.mail;

import daitnu.daitnus2.database.entity.Mail;
import daitnu.daitnus2.database.entity.User;
import daitnu.daitnus2.exception.ErrorCode;
import daitnu.daitnus2.exception.ErrorResponse;
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
    User user = (User) req.getSession().getAttribute("user");
    List<Mail> mails = mailService.findAll(user.getId()); // TODO: pagination
    return new ResponseEntity<>(modelMapper.map(mails, MailDTO.ResponseMailsDTO[].class), HttpStatus.OK);
  }

  @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> patchMail(@RequestBody @Valid MailDTO.MoveMailDTO dto,
                                     HttpServletRequest req,
                                     BindingResult result) {
    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    User user = (User) req.getSession().getAttribute("user");
    mailService.updateCategory(dto.getMailId(), user.getId(), dto.getCategoryId());
    Mail mail = mailService.findOne(dto.getMailId());
    return new ResponseEntity<>(modelMapper.map(mail, MailDTO.MoveMailDTO.class), HttpStatus.OK);
  }
}
