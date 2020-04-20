package daitnu.daitnus2.Integration;

import daitnu.daitnus2.database.entity.Mail;
import daitnu.daitnus2.mail.MailService;
import daitnu.daitnus2.database.entity.MailAttachment;
import daitnu.daitnus2.mail.attachment.MailAttachmentService;
import daitnu.daitnus2.database.entity.MailCategory;
import daitnu.daitnus2.mail.category.MailCategoryService;
import daitnu.daitnus2.database.entity.MailTemplate;
import daitnu.daitnus2.mail.template.MailTemplateService;
import daitnu.daitnus2.database.entity.User;
import daitnu.daitnus2.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MailRelatedIntegrationTest {

  @Autowired UserService userService;
  @Autowired MailService mailService;
  @Autowired MailCategoryService mailCategoryService;
  @Autowired MailTemplateService mailTemplateService;
  @Autowired MailAttachmentService mailAttachmentService;

  @Test
  public void 메일_생성_통합테스트() {
    // given
    User user = new User("kimsoso", "1234", "kss", "kimsoso@gaver.com");
    MailCategory category = new MailCategory("category1", user);
    MailTemplate mailTemplate = new MailTemplate("kimgogo@daitnu2.com", "kimsoso@daitnu2.com", "mail title1", "mail subject1");
    MailAttachment mailAttachment =
      new MailAttachment(mailTemplate, "png", "image1.png", "https://www.naver.com", 10L);
    Mail mail = new Mail(category, user, mailTemplate);


    // when
    user.addMailCategory(category);
    mailTemplate.addMail(mail);
    mailTemplate.addAttachment(mailAttachment);

    userService.register(user);
    mailTemplateService.makeMailTemplate(mailTemplate);
    mailService.makeMail(mail);


    // then
    User foundUser = userService.findOne(user.getId());
    MailCategory foundCategory = mailCategoryService.findOne(category.getId());
    MailAttachment foundAttachment = mailAttachmentService.findOne(mailAttachment.getId());
    MailTemplate foundTemplate = mailTemplateService.findOne(mailTemplate.getId());
    Mail foundMail = mailService.findOne(mail.getId());

    // - User Assert
    assertEquals(user.getId(), foundUser.getId());
    assertEquals(1, userService.findAll().size());
    assertEquals(1, user.getMailCategories().size());

    // - Category Assert
    assertEquals(category.getId(), foundCategory.getId());
    assertEquals(1, mailCategoryService.findAll(user).size());
    assertEquals(foundUser.getId(), foundCategory.getUser().getId());
    assertEquals(foundCategory.getId(), foundUser.getMailCategories().get(0).getId());

    // - Mail Attachment
    assertEquals(mailAttachment.getId(), foundAttachment.getId());
    assertEquals(foundAttachment.getId(), foundTemplate.getMailAttachments().get(0).getId());

    // - Mail Template
    assertEquals(mailTemplate.getId(), foundTemplate.getId());
    assertEquals(1, mailTemplateService.findAll().size());
    assertEquals(1, foundTemplate.getMailAttachments().size());
    assertEquals("kimsoso@daitnu2.com", mailTemplate.getMailReceivers());

    // - Mail
    assertEquals(mail.getId(), foundMail.getId());
    assertEquals(1, mailService.findAll().size());
    assertEquals(foundUser.getId(), foundMail.getOwner().getId());
    assertEquals(foundCategory.getId(), foundMail.getCategory().getId());
    assertEquals(foundTemplate.getId(), foundMail.getMailTemplate().getId());
  }
}
