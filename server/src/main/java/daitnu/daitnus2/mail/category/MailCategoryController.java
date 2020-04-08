package daitnu.daitnus2.mail.category;

import daitnu.daitnus2.database.entity.MailCategory;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/mail/category")
public class MailCategoryController {
  private final MailCategoryService mailCategoryService;
  private final ModelMapper modelMapper;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> make(@RequestBody @Valid MailCategoryDTO.MakeDTO dto, HttpServletRequest req, BindingResult result) {
    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    User user = (User) req.getSession().getAttribute("user");
    MailCategory mailCategory = new MailCategory(dto.getMailCategoryName(), user);
    mailCategoryService.makeDir(mailCategory);
    return new ResponseEntity<>(modelMapper.map(mailCategory, MailCategoryDTO.MakeDTO.class), HttpStatus.CREATED);
  }
}
