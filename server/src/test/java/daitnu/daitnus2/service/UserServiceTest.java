package daitnu.daitnus2.service;

import daitnu.daitnus2.user.UserService;
import daitnu.daitnus2.user.User;
import daitnu.daitnus2.user.UserRepository;

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
    @Autowired
    UserService userService;

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
        User foundUser1 = userService.findOne(userId + "1", subEmail);
        User foundUser2 = userService.findOne(userId, subEmail + "1");
        User foundUser3 = userService.findOne(userId, subEmail);

        // then
        assertNull(foundUser1);
        assertNull(foundUser2);
        assertEquals(user.getId(), foundUser3.getId());
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
