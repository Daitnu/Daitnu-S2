package daitnu.daitnus2.database.repository;

import daitnu.daitnus2.database.entity.MailReceiver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailReceiverRepository extends JpaRepository<MailReceiver, Long> {
  List<MailReceiver> findAllByMailTemplateId(Long mailTemplateId);
}
