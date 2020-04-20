package daitnu.daitnus2.mail;

import daitnu.daitnus2.database.entity.Mail;
import daitnu.daitnus2.database.entity.User;
import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    if (user == null) {
      throw new BusinessException(ErrorCode.UNAUTHORIZED);
    }

    List<Mail> mails = mailService.findAll(user.getId()); // TODO: pagination
    return new ResponseEntity<>(modelMapper.map(mails, MailDTO.ResponseMails[].class), HttpStatus.OK);
  }
}
