package daitnu.daitnus2.mail.attachment;

import daitnu.daitnus2.database.entity.MailAttachment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MailAttachmentService {

  private final MailAttachmentRepository mailAttachmentRepository;

  public Long makeMailAttachment(MailAttachment mailAttachment) {
    // TODO: validation
    mailAttachmentRepository.save(mailAttachment);
    return mailAttachment.getId();
  }

  public Long removeMailAttachment(MailAttachment mailAttachment) {
    // TODO: validation
    mailAttachmentRepository.remove(mailAttachment);
    return mailAttachment.getId();
  }

  public MailAttachment findOne(Long id) {
    return mailAttachmentRepository.findOne(id);
  }

  public List<MailAttachment> findAll() {
    return mailAttachmentRepository.findAll();
  }

  public List<MailAttachment> findAll(Long mailTemplateId) {
    return mailAttachmentRepository.findAll(mailTemplateId);
  }
}
