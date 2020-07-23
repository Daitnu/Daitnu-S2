package daitnu.daitnus2.mail.category;

import daitnu.daitnus2.accounts.AccountsSession;
import daitnu.daitnus2.database.entity.MailCategory;
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
@RequestMapping(path = "/mail/category")
public class MailCategoryController {
  private final MailCategoryService mailCategoryService;
  private final ModelMapper modelMapper;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> make(@RequestBody @Valid MailCategoryDTO.MakeDTO dto,
                                HttpSession session,
                                BindingResult result) {

    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    AccountsSession sessionUser = (AccountsSession) session.getAttribute("user");
    MailCategory mailCategory = mailCategoryService.makeDir(dto.getName(), sessionUser.getId());
    if (dto.getParentId() != null) {
      mailCategoryService.addChildCategory(dto.getParentId(), mailCategory.getId());
    }
    return new ResponseEntity<>(modelMapper.map(mailCategory, MailCategoryDTO.Response.class), HttpStatus.CREATED);
  }

  @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> rename(@RequestBody @Valid MailCategoryDTO.RenameDTO dto,
                                  HttpSession session,
                                  BindingResult result) {

    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    AccountsSession sessionUser = (AccountsSession) session.getAttribute("user");
    MailCategory renamedDir =
      mailCategoryService.renameDir(dto.getId(), dto.getOldName(), dto.getNewName(), sessionUser.getId());
    return new ResponseEntity<>(modelMapper.map(renamedDir, MailCategoryDTO.Response.class), HttpStatus.OK);
  }

  @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteCategory(@RequestBody @Valid MailCategoryDTO.DeleteDTO dto,
                                          HttpSession session,
                                          BindingResult result) {

    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    AccountsSession sessionUser = (AccountsSession) session.getAttribute("user");
    MailCategory removedCategory = mailCategoryService.removeDir(dto.getId(), dto.getName(), sessionUser.getId());
    return new ResponseEntity<>(modelMapper.map(removedCategory, MailCategoryDTO.DeleteDTO.class), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<?> getCategories(HttpSession session) {
    AccountsSession sessionUser = (AccountsSession) session.getAttribute("user");
    List<MailCategory> userCategories = mailCategoryService.findAll(sessionUser.getId());
    return new ResponseEntity<>(modelMapper.map(userCategories, MailCategoryDTO.Response[].class), HttpStatus.OK);
  }
}
