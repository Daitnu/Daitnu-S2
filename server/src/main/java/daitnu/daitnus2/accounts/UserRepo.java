package daitnu.daitnus2.accounts;

import daitnu.daitnus2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

  User findByUserId(String userId);
}
