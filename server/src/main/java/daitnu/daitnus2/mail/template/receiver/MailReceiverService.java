package daitnu.daitnus2.mail.template.receiver;

import daitnu.daitnus2.database.entity.MailReceiver;
import daitnu.daitnus2.database.repository.MailReceiverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailReceiverService {

  private final MailReceiverRepository mailReceiverRepository;

  @Transactional
  public Long makeReceiver(MailReceiver mailReceiver) {
    mailReceiver.getMailTemplate().addReceiver(mailReceiver);
    mailReceiverRepository.save(mailReceiver);
    return mailReceiver.getId();
  }

  @Transactional
  public Long removeReceiver(MailReceiver mailReceiver) {
    mailReceiver.getMailTemplate().removeReceiver(mailReceiver);
    mailReceiverRepository.delete(mailReceiver);
    return mailReceiver.getId();
  }

  public MailReceiver findOne(Long id) {
    return mailReceiverRepository.getOne(id);
  }

  public List<MailReceiver> findAll() {
    return mailReceiverRepository.findAll();
  }

  public List<MailReceiver> findAll(Long mailTemplateId) {
    return mailReceiverRepository.findAllByMailTemplateId(mailTemplateId);
  }
}
