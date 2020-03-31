package daitnu.daitnus2.service;

import daitnu.daitnus2.domain.User;
import daitnu.daitnus2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 가입
    @Transactional
    public Long register(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    // 회원 가입 validation
    private void validateDuplicateUser(User user) {
        List<User> foundUser = userRepository.findOneByUserId(user.getUserId());
        if (!foundUser.isEmpty()) {
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
        User one = userRepository.findOne(id);
        one.updateUserNameAndSubEmail(name, subEmail);
    }

    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findOneByUserId(String userId) {
        return userRepository.findOneByUserId(userId);
    }

    public List<User> findOneByUserEmail(String userEmail) {
        return userRepository.findOneByUserEmail(userEmail);
    }

    // 비밀번호 찾기시 사용할 예정
    public List<User> findOneByUserIdAndSubEmail(String userId, String subEmail) {
        return userRepository.findOneByUserIdAndSubEmail(userId, subEmail);
    }
}
