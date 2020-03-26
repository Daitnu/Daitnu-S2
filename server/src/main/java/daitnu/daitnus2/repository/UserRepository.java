package daitnu.daitnus2.repository;

import daitnu.daitnus2.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public User findOneByUserId(String userId) {
        return em.createQuery("select u from User u where u.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    public User findOneByUserEmail(String email) {
        return em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public User findOneByUserNameAndSubEmail(String name, String subEmail) {
        return em.createQuery("select u from User u where u.name = :name and u.subEmail = :subEmail", User.class)
                .setParameter("name", name)
                .setParameter("subEmail", subEmail)
                .getSingleResult();
    }
}
