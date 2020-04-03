package daitnu.daitnus2.mail;

import daitnu.daitnus2.mail.category.MailCategory;
import daitnu.daitnus2.mail.category.MailCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MailService {

  private final MailRepository mailRepository;
  private final MailCategoryRepository mailCategoryRepository;

  // 메일 생성
  @Transactional
  public Long makeMail(Mail mail) {
    mail.getMailTemplate().addMail(mail);
    mailRepository.save(mail);
    return mail.getId();
  }

  // 메일 삭제
  @Transactional
  public Long removeMail(Mail mail, Long userId) {
    validateMailOwner(mail.getId(), userId);
    mail.getMailTemplate().removeMail(mail);
    mailRepository.remove(mail);
    return mail.getId();
  }

  // 메일 속성 변경
  @Transactional
  public void updateProperties(Long mailId, Long userId, boolean isImportant, boolean isRead, boolean isRemoved) {
    validateMailOwner(mailId, userId);
    Mail one = mailRepository.findOne(mailId);
    one.updateProperties(isImportant, isRead, isRemoved);
  }

  private void validateMailOwner(Long mailId, Long userId) {
    Mail mail = mailRepository.findOne(mailId);
    if (!mail.getOwner().getId().equals(userId)) {
      throw new IllegalStateException("해당 메일은 본인의 소유가 아님"); // TODO: retype error msg
    }
  }

  // 메일함(Category) 수정(== 메일 이동)
  @Transactional
  public void updateCategory(Long mailId, Long userId, MailCategory mailCategory) {
    validateCategory(userId, mailCategory.getId(), mailId);
    Mail one = mailRepository.findOne(mailId);
    one.updateCategory(mailCategory);
  }

  private void validateCategory(Long userId, Long mailCategoryId, Long mailId) {
    MailCategory mailCategory = mailCategoryRepository.findOne(mailCategoryId);
    if (mailCategory == null) {
      throw new IllegalStateException("해당 메일함이 존재하지 않습니다"); // TODO: retype error msg
    }

    List<MailCategory> categories = mailCategoryRepository.findAll(userId, mailCategoryId);
    if (categories.isEmpty()) {
      throw new IllegalStateException("해당 메일함은 본인의 소유가 아님"); // TODO: retype error msg
    }

    validateMailOwner(mailId, userId);
  }

  // 메일 find
  public Mail findOne(Long id) {
    return mailRepository.findOne(id);
  }

  public List<Mail> findAll() {
    return mailRepository.findAll();
  }

  public List<Mail> findAll(Long userId) {
    return mailRepository.findAll(userId);
  }

  public List<Mail> findAll(Long userId, Long categoryId) {
    return mailRepository.findAll(userId, categoryId);
  }
}