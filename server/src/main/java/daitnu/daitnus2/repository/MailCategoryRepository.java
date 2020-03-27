package daitnu.daitnus2.repository;

import daitnu.daitnus2.domain.MailCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MailCategoryRepository {

    private final EntityManager em;

    public void save(MailCategory mailCategory) {
        if (mailCategory.getId() == null) {
            em.persist(mailCategory);
        } else {
            em.merge(mailCategory);
        }
    }

    public void delete(MailCategory mailCategory) {
        em.remove(mailCategory);
    }

    public List<MailCategory> findAll() {
        return em.createQuery("select m from MailCategory m", MailCategory.class).getResultList();
    }

    public MailCategory findOne(Long id) {
        return em.find(MailCategory.class, id);
    }

    public List<MailCategory> findByUserId(String userId) {
        return em.createQuery("select m from MailCategory m where m.user.userId = :userId", MailCategory.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
