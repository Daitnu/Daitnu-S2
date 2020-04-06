package daitnu.daitnus2.service;

import daitnu.daitnus2.user.UserService;
import daitnu.daitnus2.database.entity.MailCategory;
import daitnu.daitnus2.database.entity.User;

import daitnu.daitnus2.mail.category.MailCategoryService;
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
public class MailCategoryServiceTest {

    @Autowired UserService userService;
    @Autowired MailCategoryService mailCategoryService;

    @Test
    public void 메일함_생성() {
        // given
        String mailboxName = "mailbox1";
        User user = new User("kimsoso", "1234", "kss", "kimsoso@gaver.com");
        MailCategory mailCategory = new MailCategory(mailboxName, user);

        // when
        Long newUserId = userService.register(user);
        mailCategoryService.makeDir(mailCategory);

        // then
        List<MailCategory> mailCategories = mailCategoryService.findAll(user.getUserId());
        MailCategory madeMailCategory = mailCategories.get(0);
        User one = userService.findOne(newUserId);

        assertEquals(user.getUserId(), madeMailCategory.getUser().getUserId());
        assertEquals(mailboxName, madeMailCategory.getName());
        assertEquals(newUserId, one.getId());
        assertEquals(1, one.getMailCategories().size());
    }

    @Test
    public void 메일함_수정() {
        // given
        String mailboxName = "mailbox1";
        User user = new User("kimsoso", "1234", "kss", "kimsoso@gaver.com");
        MailCategory mailCategory = new MailCategory(mailboxName, user);

        // when
        userService.register(user);
        mailCategoryService.makeDir(mailCategory);

        // then
        String otherMailboxName = "hoho";
        mailCategory.updateName(otherMailboxName);
        MailCategory mailBox = mailCategoryService.findOne(mailCategory.getId());
        assertEquals(otherMailboxName, mailBox.getName());
        assertEquals(otherMailboxName, user.getMailCategories().get(0).getName());
    }

    @Test
    public void 메일함_삭제() {
        // given
        String mailboxName = "mailbox1";
        User user = new User("kimsoso", "1234", "kss", "kimsoso@gaver.com");
        MailCategory mailCategory = new MailCategory(mailboxName, user);

        // when
        userService.register(user);
        Long madeMailboxId = mailCategoryService.makeDir(mailCategory);
        Long removedMailboxId = mailCategoryService.removeDir(mailCategory, user);

        // then
        List<MailCategory> mailCategories = mailCategoryService.findAll();

        assertEquals(madeMailboxId, removedMailboxId);
        assertEquals(0, mailCategories.size());
        assertEquals(0, user.getMailCategories().size());
    }

    @Test(expected = IllegalStateException.class)
    public void 타인의_메일함_삭제_불가() {
        // given
        User user1 = new User("kimsoso1", "1234", "kss1", "kimsoso1@gaver.com");
        User user2 = new User("kimsoso2", "12345", "kss2", "kimsoso2@gaver.com");
        MailCategory mailCategory1 = new MailCategory("mailboxName1", user1);
        MailCategory mailCategory2 = new MailCategory("mailboxName2", user2);

        // when
        userService.register(user1);
        userService.register(user2);
        mailCategoryService.makeDir(mailCategory1);
        mailCategoryService.makeDir(mailCategory2);
        mailCategoryService.removeDir(mailCategory1, user2);

        // then
        fail("타인의 메일함 삭제는 불가합니다");
    }

    @Test(expected = IllegalStateException.class)
    public void 유저는_메일함_이름_중복_생성_불가() {
        // given
        String mailboxName = "mailbox1";
        User user = new User("kimsoso", "1234", "kss", "kimsoso@gaver.com");
        MailCategory mailCategory1 = new MailCategory(mailboxName, user);
        MailCategory mailCategory2 = new MailCategory(mailboxName, user);

        // when
        userService.register(user);
        mailCategoryService.makeDir(mailCategory1);
        mailCategoryService.makeDir(mailCategory2);

        // then
        fail("메일함 이름 중복 생성 불가!");
    }
}
