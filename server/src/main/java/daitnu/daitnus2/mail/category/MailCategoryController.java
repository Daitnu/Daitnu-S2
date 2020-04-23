package daitnu.daitnus2.mail.category;

import daitnu.daitnus2.accounts.AccountsDTO;
import daitnu.daitnus2.database.entity.MailCategory;
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
@RequestMapping(path = "/mail/category")
public class MailCategoryController {
  private final MailCategoryService mailCategoryService;
  private final ModelMapper modelMapper;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> make(@RequestBody @Valid MailCategoryDTO.MakeDTO dto,
                                HttpServletRequest req,
                                BindingResult result) {
    AccountsDTO.SessionUserDTO user = ControllerUtil.getSessionUser(req.getSession());

    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    MailCategory mailCategory = mailCategoryService.makeDir(dto.getName(), user.getId());
    return new ResponseEntity<>(modelMapper.map(mailCategory, MailCategoryDTO.Response.class), HttpStatus.CREATED);
  }

  @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> rename(@RequestBody @Valid MailCategoryDTO.RenameDTO dto,
                                  HttpServletRequest req,
                                  BindingResult result) {
    AccountsDTO.SessionUserDTO user = ControllerUtil.getSessionUser(req.getSession());

    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    MailCategory renamedDir =
      mailCategoryService.renameDir(dto.getCategoryId(), dto.getOldName(), dto.getNewName(), user.getId());
    return new ResponseEntity<>(modelMapper.map(renamedDir, MailCategoryDTO.Response.class), HttpStatus.OK);
  }

  @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteCategory(@RequestBody @Valid MailCategoryDTO.DeleteDTO dto,
                                          HttpServletRequest req,
                                          BindingResult result) {
    AccountsDTO.SessionUserDTO user = ControllerUtil.getSessionUser(req.getSession());

    if (result.hasErrors()) {
      ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
      return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    MailCategory removedCategory = mailCategoryService.removeDir(dto.getId(), dto.getName(), user.getId());
    return new ResponseEntity<>(modelMapper.map(removedCategory, MailCategoryDTO.DeleteDTO.class), HttpStatus.OK);
  }

  @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getCategories(HttpServletRequest req) {
    AccountsDTO.SessionUserDTO user = ControllerUtil.getSessionUser(req.getSession());
    List<MailCategory> userCategories = mailCategoryService.findAll(user.getId());
    return new ResponseEntity<>(modelMapper.map(userCategories, MailCategoryDTO.Response[].class), HttpStatus.OK);
  }
}
