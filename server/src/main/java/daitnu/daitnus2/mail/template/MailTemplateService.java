package daitnu.daitnus2.mail.template;

import daitnu.daitnus2.database.entity.MailTemplate;
import daitnu.daitnus2.database.repository.MailTemplateRepository;
import daitnu.daitnus2.mail.template.exception.NotFoundMailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MailTemplateService {

  private final MailTemplateRepository mailTemplateRepository;

  // 메일 템플릿 추가
  @Transactional
  public MailTemplate makeMailTemplate(String from, String mailReceivers, String title, String subject) {
    MailTemplate mailTemplate = new MailTemplate(from, mailReceivers, title, subject);
    mailTemplateRepository.save(mailTemplate);
    return mailTemplate;
  }

  // 메일 템플릿 삭제
  @Transactional
  public Long removeMailTemplate(MailTemplate mailTemplate) {
    mailTemplateRepository.delete(mailTemplate);
    return mailTemplate.getId();
  }

  // find one
  public MailTemplate findOne(Long id) {
    Optional<MailTemplate> mailTemplate = mailTemplateRepository.findById(id);
    if (mailTemplate.isPresent()) {
      return mailTemplate.get();
    }
    throw new NotFoundMailTemplate();
  }

  // find all
  public List<MailTemplate> findAll() {
    return mailTemplateRepository.findAll();
  }
}
