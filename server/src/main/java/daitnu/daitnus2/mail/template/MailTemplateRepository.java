package daitnu.daitnus2.mail.template;

import daitnu.daitnus2.database.entity.MailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MailTemplateRepository {

  private final EntityManager em;

  public void save(MailTemplate mailTemplate) {
    if (mailTemplate.getId() == null) {
      em.persist(mailTemplate);
    } else {
      em.merge(mailTemplate);
    }
  }

  public void remove(MailTemplate mailTemplate) {
    em.remove(mailTemplate);
  }

  public MailTemplate findOne(Long id) {
    return em.find(MailTemplate.class, id);
  }

  public List<MailTemplate> findAll() {
    return em.createQuery("select mt from MailTemplate mt", MailTemplate.class).getResultList();
  }
}
