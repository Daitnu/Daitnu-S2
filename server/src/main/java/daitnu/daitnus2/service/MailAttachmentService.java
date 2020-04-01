package daitnu.daitnus2.service;

import daitnu.daitnus2.domain.MailAttachment;
import daitnu.daitnus2.repository.MailAttachmentRepository;
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
    mailAttachmentRepository.save(mailAttachment);
    return mailAttachment.getId();
  }

  public Long removeMailAttachment(MailAttachment mailAttachment) {
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
