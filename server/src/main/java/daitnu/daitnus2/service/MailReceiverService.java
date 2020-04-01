package daitnu.daitnus2.service;

import daitnu.daitnus2.domain.MailReceiver;
import daitnu.daitnus2.repository.MailReceiverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailReceiverService {

  private final MailReceiverRepository mailReceiverRepository;

  public Long makeReceiver(MailReceiver mailReceiver) {
    mailReceiver.getMailTemplate().addReceiver(mailReceiver);
    mailReceiverRepository.save(mailReceiver);
    return mailReceiver.getId();
  }

  public Long removeReceiver(MailReceiver mailReceiver) {
    mailReceiver.getMailTemplate().removeReceiver(mailReceiver);
    mailReceiverRepository.remove(mailReceiver);
    return mailReceiver.getId();
  }

  public MailReceiver findOne(Long id) {
    return mailReceiverRepository.findOne(id);
  }

  public List<MailReceiver> findAll() {
    return mailReceiverRepository.findAll();
  }

  public List<MailReceiver> findAll(Long mailTemplateId) {
    return mailReceiverRepository.findAll(mailTemplateId);
  }
}
