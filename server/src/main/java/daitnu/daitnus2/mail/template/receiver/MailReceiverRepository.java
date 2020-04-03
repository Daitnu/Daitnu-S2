package daitnu.daitnus2.mail.template.receiver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MailReceiverRepository {

  private final EntityManager em;

  public void save(MailReceiver mailReceiver) {
    if (mailReceiver.getId() == null) {
      em.persist(mailReceiver);
    } else {
      em.merge(mailReceiver);
    }
  }

  public void remove(MailReceiver mailReceiver) {
    em.remove(mailReceiver);
  }

  public MailReceiver findOne(Long id) {
    return em.find(MailReceiver.class, id);
  }

  public List<MailReceiver> findAll() {
    return em.createQuery("select mr from MailReceiver mr", MailReceiver.class).getResultList();
  }

  public List<MailReceiver> findAll(Long mailTemplateId) {
    return em.createQuery("select mr from MailReceiver mr where mr.mailTemplate = :mailTemplateId", MailReceiver.class)
      .setParameter("mailTemplateId", mailTemplateId)
      .getResultList();
  }
}
