package daitnu.daitnus2.Mail;

import daitnu.daitnus2.accounts.AccountsDTO;
import daitnu.daitnus2.accounts.AccountsService;
import daitnu.daitnus2.database.entity.Mail;
import daitnu.daitnus2.database.entity.MailAttachment;
import daitnu.daitnus2.mail.MailService;
import daitnu.daitnus2.mail.attachment.MailAttachmentService;
import daitnu.daitnus2.database.entity.MailCategory;
import daitnu.daitnus2.mail.category.MailCategoryService;
import daitnu.daitnus2.database.entity.MailTemplate;
import daitnu.daitnus2.mail.template.MailTemplateService;
import daitnu.daitnus2.database.entity.User;
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
public class MailServiceTest {

  @Autowired AccountsService accountsService;
  @Autowired MailService mailService;
  @Autowired MailCategoryService mailCategoryService;
  @Autowired MailTemplateService mailTemplateService;
  @Autowired MailAttachmentService mailAttachmentService;

  @Test
  public void 메일_생성_서비스_테스트() {
    // given
    AccountsDTO.RegisterDTO registerDTO = new AccountsDTO.RegisterDTO();
    registerDTO.setId("kimsoso"); registerDTO.setPassword("12345"); registerDTO.setPasswordCheck("12345");
    registerDTO.setName("kss"); registerDTO.setSubEmail("kimsoso@gaver.com");


    // when
    User user = accountsService.register(registerDTO);
    MailCategory category = mailCategoryService.makeDir("category1", user.getId());
    MailTemplate mailTemplate = mailTemplateService
      .makeMailTemplate("kimgogo@daitnu2.com", "kimsoso@daitnu2.com", "mail title1", "mail subject1");
    MailAttachment mailAttachment = mailAttachmentService
      .makeMailAttachment(mailTemplate.getId(), "png", "image1.png", "https://www.naver.com", 10L);
    Mail mail = mailService.makeMail(category.getId(), user.getId(), mailTemplate.getId());
    mailTemplate.addMail(mail);
    mailTemplate.addAttachment(mailAttachment);


    // then
    User foundUser = accountsService.findOne(user.getId());
    MailCategory foundCategory = mailCategoryService.findOne(category.getId());
    MailAttachment foundAttachment = mailAttachmentService.findOne(mailAttachment.getId());
    MailTemplate foundTemplate = mailTemplateService.findOne(mailTemplate.getId());
    Mail foundMail = mailService.findOne(mail.getId());

    // - User Assert
    assertEquals(user.getId(), foundUser.getId());
    assertEquals(1, user.getMailCategories().size());

    // - Category Assert
    assertEquals(category.getId(), foundCategory.getId());
    assertEquals(1, mailCategoryService.findAll(user.getId()).size());
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
