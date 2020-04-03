package daitnu.daitnus2.database.repository;

import daitnu.daitnus2.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

  User findByUserId(String userId);
}
