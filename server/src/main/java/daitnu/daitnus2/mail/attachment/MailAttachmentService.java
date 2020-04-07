package daitnu.daitnus2.mail.attachment;

import daitnu.daitnus2.database.entity.MailAttachment;
import daitnu.daitnus2.database.repository.MailAttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MailAttachmentService {

  private final MailAttachmentRepository mailAttachmentRepository;

  @Transactional
  public Long makeMailAttachment(MailAttachment mailAttachment) {
    mailAttachmentRepository.save(mailAttachment);
    return mailAttachment.getId();
  }

  @Transactional
  public Long removeMailAttachment(MailAttachment mailAttachment) {
    mailAttachmentRepository.delete(mailAttachment);
    return mailAttachment.getId();
  }

  public MailAttachment findOne(Long id) {
    return mailAttachmentRepository.getOne(id);
  }

  public List<MailAttachment> findAll() {
    return mailAttachmentRepository.findAll();
  }

  public List<MailAttachment> findAll(Long mailTemplateId) {
    return mailAttachmentRepository.findAllByMailTemplateId(mailTemplateId);
  }
}
