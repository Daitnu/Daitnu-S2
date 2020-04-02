package daitnu.daitnus2.accounts;

import daitnu.daitnus2.accounts.exception.InvalidLoginInput;
import daitnu.daitnus2.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountsService {

  private final UserRepo userRepository;
  private final PasswordEncoder passwordEncoder;


  public User login(AccountsDTO.LoginDTO dto) {
    System.out.println("user null");
    String userId = dto.getId();
    String userPw = dto.getPassword();

    User user = userRepository.findByUserId(userId);
    if(user == null) {
      throw new InvalidLoginInput();
    }

    String encodedPw = user.getPw();
    boolean isValidPw = passwordEncoder.matches(userPw,encodedPw);
    if(isValidPw == false) {
      throw new InvalidLoginInput();
    }

    return user;
  }

  public User register(AccountsDTO.RegisterDTO dto) {
    String userId = dto.getId();
    String userPw = dto.getPassword();
    String userSubEmail = dto.getSubEmail();
    String userName = dto.getName();
    User newUser = new User(userId,userPw,userSubEmail,userName);

    // TODO : SubEmail unique 확인, Id duplicate 확인
    User createdUser = userRepository.save(newUser);
    return createdUser;
  }
}
