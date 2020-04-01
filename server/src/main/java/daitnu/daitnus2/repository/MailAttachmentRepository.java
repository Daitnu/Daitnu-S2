package daitnu.daitnus2.repository;

import daitnu.daitnus2.domain.MailAttachment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MailAttachmentRepository {

  private final EntityManager em;

  public void save(MailAttachment mailAttachment) {
    if (mailAttachment.getId() == null) {
      em.persist(mailAttachment);
    } else {
      em.merge(mailAttachment);
    }
  }

  public void remove(MailAttachment mailAttachment) {
    em.remove(mailAttachment);
  }

  public MailAttachment findOne(Long id) {
    return em.find(MailAttachment.class, id);
  }

  public List<MailAttachment> findAll() {
    return em.createQuery("select ma from MailAttachment ma", MailAttachment.class).getResultList();
  }

  public List<MailAttachment> findAll(Long mailTemplateId) {
    return em.createQuery("select ma from MailAttachment ma where ma.mailTemplate = :mailTemplateId", MailAttachment.class)
      .setParameter("mailTemplateId", mailTemplateId)
      .getResultList();
  }
}
