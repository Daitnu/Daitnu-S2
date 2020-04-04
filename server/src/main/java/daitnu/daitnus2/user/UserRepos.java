package daitnu.daitnus2.user;

import daitnu.daitnus2.database.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepos {

    private final EntityManager em;

    public void save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    public void remove(User user) {
        em.remove(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public User findOne(String userId) {
        List<User> result = em.createQuery("select u from User u where u.userId = :userId", User.class)
            .setParameter("userId", userId)
            .getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public User findOne(String userId, String subEmail) {
        List<User> result = em.createQuery("select u from User u where u.userId = :userId and u.subEmail = :subEmail", User.class)
                .setParameter("userId", userId)
                .setParameter("subEmail", subEmail)
                .getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }
}
