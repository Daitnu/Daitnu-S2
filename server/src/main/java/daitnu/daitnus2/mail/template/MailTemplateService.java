package daitnu.daitnus2.mail.template;

import daitnu.daitnus2.database.entity.MailTemplate;
import daitnu.daitnus2.database.repository.MailTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MailTemplateService {

  private final MailTemplateRepository mailTemplateRepository;

  // 메일 템플릿 추가
  @Transactional
  public Long makeMailTemplate(MailTemplate mailTemplate) {
    mailTemplateRepository.save(mailTemplate);
    return mailTemplate.getId();
  }

  // 메일 템플릿 삭제
  @Transactional
  public Long removeMailTemplate(MailTemplate mailTemplate) {
    mailTemplateRepository.delete(mailTemplate);
    return mailTemplate.getId();
  }

  // find one
  public MailTemplate findOne(Long id) {
    return mailTemplateRepository.getOne(id);
  }

  // find all
  public List<MailTemplate> findAll() {
    return mailTemplateRepository.findAll();
  }
}
