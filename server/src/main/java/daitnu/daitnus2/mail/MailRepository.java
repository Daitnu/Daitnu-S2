package daitnu.daitnus2.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MailRepository {

  private final EntityManager em;

  public void save(Mail mail) {
    if (mail.getId() == null) {
      em.persist(mail);
    } else {
      em.merge(mail);
    }
  }

  public void remove(Mail mail) {
    em.remove(mail);
  }

  public Mail findOne(Long id) {
    return em.find(Mail.class, id);
  }

  public List<Mail> findAll() {
    return em.createQuery("select m from Mail m", Mail.class).getResultList();
  }

  public List<Mail> findAll(Long userId) {
    return em.createQuery("select m from Mail m where m.owner = :userId", Mail.class)
      .setParameter("userId", userId)
      .getResultList();
  }

  public List<Mail> findAll(Long userId, Long categoryId) {
    return em.createQuery("select m from Mail m where m.owner = :userId and m.category = :categoryId", Mail.class)
      .setParameter("userId", userId)
      .setParameter("categoryId", categoryId)
      .getResultList();
  }
}
