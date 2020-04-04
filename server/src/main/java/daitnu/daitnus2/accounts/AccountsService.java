package daitnu.daitnus2.accounts;

import daitnu.daitnus2.accounts.exception.DuplicatUserId;
import daitnu.daitnus2.accounts.exception.DuplicateSubEmail;
import daitnu.daitnus2.accounts.exception.InvalidLoginInput;
import daitnu.daitnus2.database.entity.User;
import daitnu.daitnus2.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountsService {

  private final UserRepository userRepository;
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

    User userInstance = new User(userId, userPw, userName, userSubEmail);
    User newUser = userRepository.save(userInstance);
    return newUser;
  }
}
