package daitnu.daitnus2.service;

import daitnu.daitnus2.domain.User;
import daitnu.daitnus2.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {

    @Autowired UserRepository userRepository;
    @Autowired UserService userService;

    @Test
    public void 회원가입() throws Exception {
        // given
        User user = new User("kimsoso", "1234", "kss", "kimsoso@gaver.com");

        // when
        Long newUserId = userService.register(user);

        // then
        assertEquals(user, userRepository.findOne(newUserId));
    }

    @Test(expected = IllegalStateException.class)
    public void 같은아이디_중복처리() throws Exception {
        // given
        User user1 = new User("kimsoso1", "1234", "kss1", "kimsoso1@gaver.com");
        User user2 = new User("kimsoso1", "1234", "kss2", "kimsoso1@gaver.com");

        // when
        userService.register(user1);
        userService.register(user2);

        // then
        fail("중복 아이디 가입은 예외가 발생해야 함");
    }

    @Test
    public void 해당하는_아이디와_서브이메일을_가진_유저가_존재하지_않습니다() throws Exception {
        // given
        String userId = "kimsoso1";
        String subEmail = "kimsoso1@gaver.com";
        User user = new User(userId, "1234", "kss1", subEmail);

        // when
        userService.register(user);
        List<User> foundUser1 = userService.findOneByUserIdAndSubEmail(userId + "1", subEmail);
        List<User> foundUser2 = userService.findOneByUserIdAndSubEmail(userId, subEmail + "1");
        List<User> foundUser3 = userService.findOneByUserIdAndSubEmail(userId, subEmail);

        // then
        assertEquals(0, foundUser1.size());
        assertEquals(0, foundUser2.size());
        assertEquals(1, foundUser3.size());
    }

    @Test
    public void 회원정보_업데이트() throws Exception {
        // given
        User user = new User("kimsoso1", "1234", "kss1", "kimsoso1@gaver.com");

        // when
        userService.register(user);
        userService.updateUserNameAndSubEmail(user.getId(), "kss2", "kimsoso2@gaver.com");

        // then
        User updatedUser = userService.findOne(user.getId());
        assertEquals("kimsoso1", updatedUser.getUserId());
        assertEquals("1234", updatedUser.getPw());
        assertEquals("kss2", updatedUser.getName());
        assertEquals("kimsoso1@daitnu2.com", updatedUser.getEmail());
        assertEquals("kimsoso2@gaver.com", updatedUser.getSubEmail());
    }

    @Test
    public void 비밀번호_변경() throws Exception {
        // given
        User user = new User("kimsoso1", "1234", "kss1", "kimsoso1@gaver.com");

        // when
        userService.register(user);
        userService.changePassword(user.getId(), "5678");

        // then
        User updatedUser = userService.findOne(user.getId());
        assertEquals("kimsoso1", updatedUser.getUserId());
        assertEquals("5678", updatedUser.getPw());
        assertEquals(user.getPw(), updatedUser.getPw());
    }

    @Test
    public void 회원탈퇴() throws Exception {
        // given
        User user = new User("kimsoso1", "1234", "kss1", "kimsoso1@gaver.com");

        // when
        userService.register(user);
        User registeredUser = userService.findOne(user.getId());
        userService.withDraw(registeredUser);

        // then
        List<User> users = userService.findAll();
        assertEquals(0, users.size());
    }
}
