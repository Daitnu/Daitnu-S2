package daitnu.daitnus2.service;

import daitnu.daitnus2.domain.MailCategory;
import daitnu.daitnus2.domain.User;
import daitnu.daitnus2.repository.MailCategoryRepository;
import daitnu.daitnus2.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MailCategoryServiceTest {

    @Autowired UserRepository userRepository;
    @Autowired UserService userService;
    @Autowired MailCategoryRepository mailCategoryRepository;
    @Autowired MailCategoryService mailCategoryService;

    @Test
    public void 메일함_생성() {
        // given
        String mailboxName = "mailbox1";
        User user = new User("kimsoso", "1234", "kss",
                "kimsoso@daitnu2.com", "kimsoso@gaver.com");
        MailCategory mailCategory = new MailCategory(mailboxName, user);

        // when
        userService.register(user);
        mailCategoryService.makeDir(mailCategory);

        // then
        List<MailCategory> mailCategories = mailCategoryService.findByUserId(user.getUserId());
        MailCategory madeMailCategory = mailCategories.get(0);

        assertEquals(user.getUserId(), madeMailCategory.getUser().getUserId());
        assertEquals(mailboxName, madeMailCategory.getName());
    }
}
