package daitnu.daitnus2.mail;

import daitnu.daitnus2.accounts.AccountsDTO;
import daitnu.daitnus2.accounts.AccountsService;
import daitnu.daitnus2.database.entity.Mail;
import daitnu.daitnus2.database.entity.MailAttachment;
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

    MailDTO.AddMailDTO addMailDTO = new MailDTO.AddMailDTO();
    addMailDTO.setMailCategoryName(category.getName());
    addMailDTO.setSubject("mail subject1");
    addMailDTO.setTitle("mail title1");
    addMailDTO.setMailReceivers("kimsoso@daitnu2.com");
    addMailDTO.setFrom("kimgogo@daitnu2.com");
    Mail mail = mailService.addMail(user.getId(), addMailDTO);


    // then
    User foundUser = accountsService.findOne(user.getId());
    MailCategory foundCategory = mailCategoryService.findOne(category.getId());
    MailTemplate foundTemplate = mailTemplateService.findAll().get(0);
    Mail foundMail = mailService.findOne(mail.getId());

    // - User Assert
    assertEquals(user.getId(), foundUser.getId());
    assertEquals(5, user.getMailCategories().size());

    // - Category Assert
    assertEquals(category.getId(), foundCategory.getId());
    assertEquals(5, mailCategoryService.findAll(user.getId()).size());
    assertEquals(foundUser.getId(), foundCategory.getUser().getId());

    // TODO: 첨부파일 기능 완료한 후 테스트 예정
    // - Mail Attachment
//    assertEquals(mailAttachment.getId(), foundAttachment.getId());
//    assertEquals(foundAttachment.getId(), foundTemplate.getMailAttachments().get(0).getId());

    // - Mail Template
    assertEquals(1, mailTemplateService.findAll().size());
    assertEquals(0, foundTemplate.getMailAttachments().size());
    assertEquals("kimsoso@daitnu2.com", foundTemplate.getMailReceivers());

    // - Mail
    assertEquals(mail.getId(), foundMail.getId());
    assertEquals(1, mailService.findAll().size());
    assertEquals(foundUser.getId(), foundMail.getOwner().getId());
    assertEquals(foundCategory.getId(), foundMail.getCategory().getId());
    assertEquals(foundTemplate.getId(), foundMail.getMailTemplate().getId());
  }
}
