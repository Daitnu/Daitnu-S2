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

    public void remove(MailCategory mailCategory) {
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

    // 메일함 생성할 때 사용할 것 같음
    public List<MailCategory> findByUserIdAndCategoryName(String userId, String categoryName) {
        return em.createQuery("select m from MailCategory m " +
                "where m.user.userId = :userId and m.name = :categoryName", MailCategory.class)
                .setParameter("userId", userId)
                .setParameter("categoryName", categoryName)
                .getResultList();
    }

    // 삭제시, 수정시 사용할 것 같음
    public List<MailCategory> findByUserIdAndCategoryNameAndCategoryNo(String userId, String categoryName, Long categoryNo) {
        return em.createQuery("select m from MailCategory m " +
                "where m.user.userId = :userId and m.name = :categoryName and m.id = :categoryNo", MailCategory.class)
                .setParameter("userId", userId)
                .setParameter("categoryName", categoryName)
                .setParameter("categoryNo", categoryNo)
                .getResultList();
    }
}
