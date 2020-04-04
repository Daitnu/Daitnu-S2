package daitnu.daitnus2.user;

import daitnu.daitnus2.database.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepos userRepository;

    // 회원 가입
    @Transactional
    public Long register(User user) {
        // TODO: 비밀번호 암호화
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    // 회원 가입 validation
    private void validateDuplicateUser(User user) {
        // TODO: length validation, regex validation
        User foundUser = userRepository.findOne(user.getUserId());
        if (foundUser != null) {
            throw new IllegalStateException("already registered user");
        }
    }

    // 회원 탈퇴
    @Transactional
    public Long withDraw(User user) {
        userRepository.remove(user);
        return user.getId();
    }

    // 회원 이름, 서브이메일 수정
    @Transactional
    public void updateUserNameAndSubEmail(Long id, String name, String subEmail) {
        // TODO: length + regex validation
        User one = userRepository.findOne(id);
        one.updateUserNameAndSubEmail(name, subEmail);
    }

    // 패스워드 수정
    @Transactional
    public void changePassword(Long id, String newPassword) {
      // TODO: 패스워드 암호화, length + regex validation
      User one = userRepository.findOne(id);
      one.changePassword(newPassword);
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(String userId) {
        return userRepository.findOne(userId);
    }

    // 비밀번호 찾기시 사용할 예정
    public User findOne(String userId, String subEmail) {
        return userRepository.findOne(userId, subEmail);
    }
}
