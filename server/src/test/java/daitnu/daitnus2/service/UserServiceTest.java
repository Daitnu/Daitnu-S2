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

    @Test(expected = IllegalStateException.class)
    public void 같은아이디_중복처리() throws Exception {
        // given
        User user1 = new User("kimsoso1", "1234", "kss1",
                "kimsoso1@daitnu2.com", "kimsoso1@gaver.com");
        User user2 = new User("kimsoso1", "1234", "kss2",
                "kimsoso1@daitnu2.com", "kimsoso1@gaver.com");

        // when
        userService.register(user1);
        userService.register(user2);

        // then
        fail("중복 아이디 가입은 예외가 발생해야 함");
    }

    @Test
    public void 해당하는_아이디와_서브이메일을_가진_유저가_존재하지_않습니다() throws Exception {

    }
}
