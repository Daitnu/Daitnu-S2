package daitnu.daitnus2.MailCategory;

import daitnu.daitnus2.accounts.AccountsDTO;
import daitnu.daitnus2.accounts.AccountsService;
import daitnu.daitnus2.mail.category.exception.DuplicateCategoryName;
import daitnu.daitnus2.mail.category.exception.NotFoundCategory;
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

    @Autowired AccountsService accountsService;
    @Autowired MailCategoryService mailCategoryService;

    @Test
    public void 메일함_생성() {
        // given
        AccountsDTO.RegisterDTO registerDTO = new AccountsDTO.RegisterDTO();
        registerDTO.setId("kimsoso"); registerDTO.setPassword("12345"); registerDTO.setPasswordCheck("12345");
        registerDTO.setName("kss"); registerDTO.setSubEmail("kimsoso@gaver.com");
        String mailboxName = "mailbox1";

        // when
        User user = accountsService.register(registerDTO);
        mailCategoryService.makeDir(mailboxName, user.getId());

        // then
        List<MailCategory> mailCategories = mailCategoryService.findAll(user.getId());
        MailCategory madeMailCategory = mailCategories.get(0);

        assertEquals(user.getUserId(), madeMailCategory.getUser().getUserId());
        assertEquals(mailboxName, madeMailCategory.getName());
        assertEquals(1, user.getMailCategories().size());
    }

    @Test
    public void 메일함_수정() {
        // given
        String mailboxName = "mailbox1";
        AccountsDTO.RegisterDTO registerDTO = new AccountsDTO.RegisterDTO();
        registerDTO.setId("kimsoso"); registerDTO.setPassword("12345"); registerDTO.setPasswordCheck("12345");
        registerDTO.setName("kss"); registerDTO.setSubEmail("kimsoso@gaver.com");

        // when
        User user = accountsService.register(registerDTO);
        MailCategory mailCategory = mailCategoryService.makeDir(mailboxName, user.getId());

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
        AccountsDTO.RegisterDTO registerDTO = new AccountsDTO.RegisterDTO();
        registerDTO.setId("kimsoso"); registerDTO.setPassword("12345"); registerDTO.setPasswordCheck("12345");
        registerDTO.setName("kss"); registerDTO.setSubEmail("kimsoso@gaver.com");

        // when
        User user = accountsService.register(registerDTO);
        MailCategory mailCategory = mailCategoryService.makeDir(mailboxName, user.getId());
        Long removedMailboxId = mailCategoryService.removeDir(mailCategory.getId(), user.getId()).getId();

        // then
        List<MailCategory> mailCategories = mailCategoryService.findAll(user.getId());

        assertEquals(mailCategory.getId(), removedMailboxId);
        assertEquals(0, mailCategories.size());
        assertEquals(0, user.getMailCategories().size());
    }

    @Test(expected = NotFoundCategory.class)
    public void 타인의_메일함_삭제_불가() {
        // given
        AccountsDTO.RegisterDTO registerDTO1 = new AccountsDTO.RegisterDTO();
        registerDTO1.setId("kimsoso"); registerDTO1.setPassword("12345"); registerDTO1.setPasswordCheck("12345");
        registerDTO1.setName("kss"); registerDTO1.setSubEmail("kimsoso@gaver.com");
        AccountsDTO.RegisterDTO registerDTO2 = new AccountsDTO.RegisterDTO();
        registerDTO2.setId("kimsoso2"); registerDTO2.setPassword("12345"); registerDTO2.setPasswordCheck("12345");
        registerDTO2.setName("kss2"); registerDTO2.setSubEmail("kimsoso2@gaver.com");
        String mailboxName = "mailbox1";

        // when
        User user1 = accountsService.register(registerDTO1);
        User user2 = accountsService.register(registerDTO2);
        MailCategory mailCategory1 = mailCategoryService.makeDir(mailboxName, user1.getId());
        mailCategoryService.removeDir(mailCategory1.getId(), user2.getId());

        // then
        fail("타인의 메일함 삭제는 불가합니다");
    }

    @Test(expected = DuplicateCategoryName.class)
    public void 유저는_메일함_이름_중복_생성_불가() {
        // given
        String mailboxName = "mailbox1";
        AccountsDTO.RegisterDTO registerDTO = new AccountsDTO.RegisterDTO();
        registerDTO.setId("kimsoso"); registerDTO.setPassword("12345"); registerDTO.setPasswordCheck("12345");
        registerDTO.setName("kss"); registerDTO.setSubEmail("kimsoso@gaver.com");

        // when
        User user = accountsService.register(registerDTO);
        mailCategoryService.makeDir(mailboxName, user.getId());
        mailCategoryService.makeDir(mailboxName, user.getId());

        // then
        fail("메일함 이름 중복 생성 불가!");
    }
}
