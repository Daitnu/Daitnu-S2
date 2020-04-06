package daitnu.daitnus2.database.repository;

import daitnu.daitnus2.database.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailRepository extends JpaRepository<Mail,Long> {

  List<Mail> findAllByOwnerId(Long ownerId);
  List<Mail> findAllByOwnerIdAndCategoryId(Long ownerId, Long categoryId);
}
