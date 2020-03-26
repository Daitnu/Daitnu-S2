package daitnu.daitnus2.service;

import daitnu.daitnus2.domain.User;
import daitnu.daitnus2.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserRepository userRepository;
    @Autowired UserService userService;

    @Test
    public void 회원가입() throws Exception {
        // given
        User user = new User("kimsoso", "1234", "kss",
                "kimsoso@daitnu2.com", "kimsoso@gaver.com");

        // when
        Long newUserId = userService.register(user);

        // then
        assertEquals(user, userRepository.findOne(newUserId));
    }
}
