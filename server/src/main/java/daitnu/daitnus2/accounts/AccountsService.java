package daitnu.daitnus2.accounts;

import daitnu.daitnus2.accounts.exception.DuplicatUserId;
import daitnu.daitnus2.accounts.exception.DuplicateSubEmail;
import daitnu.daitnus2.accounts.exception.InvalidLoginInput;
import daitnu.daitnus2.database.entity.User;
import daitnu.daitnus2.database.repository.UserRepository;
import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;
import daitnu.daitnus2.mail.category.MailCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountsService {

  private final UserRepository userRepository;
  private final MailCategoryService mailCategoryService;
  private final PasswordEncoder passwordEncoder;


  public User login(AccountsDTO.LoginDTO dto) {
    System.out.println("user null");
    String userId = dto.getId();
    String userPw = dto.getPassword();

    User user = userRepository.findByUserId(userId);
    if (user == null) {
      throw new InvalidLoginInput();
    }

    String encodedPw = user.getPw();
    boolean isValidPw = passwordEncoder.matches(userPw, encodedPw);
    if (isValidPw == false) {
      throw new InvalidLoginInput();
    }

    return user;
  }

  public User register(AccountsDTO.RegisterDTO dto) {
    String userId = dto.getId();
    String userPw = dto.getPassword();
    String userSubEmail = dto.getSubEmail();
    String userName = dto.getName();


    User searchedUser = userRepository.findByUserIdOrSubEmail(userId, userSubEmail);
    if (searchedUser != null) {
      String searchedUserId = searchedUser.getUserId();
      boolean isDuplicateUserId = searchedUserId.equals(userId);
      throw isDuplicateUserId ? new DuplicatUserId() : new DuplicateSubEmail();
    }

    userPw = passwordEncoder.encode(userPw);
    User userInstance = new User(userId, userPw, userName, userSubEmail);
    User newUser = userRepository.save(userInstance);
    mailCategoryService.makeDir("받은메일함", newUser.getId());
    mailCategoryService.makeDir("보낸메일함", newUser.getId());
    mailCategoryService.makeDir("내게쓴메일함", newUser.getId());
    mailCategoryService.makeDir("휴지통", newUser.getId());
    return newUser;
  }

  public User findOne(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      return user.get();
    }
    throw new BusinessException(ErrorCode.UNAUTHORIZED);
  }
}
