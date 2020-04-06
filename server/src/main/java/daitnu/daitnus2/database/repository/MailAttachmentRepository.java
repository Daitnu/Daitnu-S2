package daitnu.daitnus2.database.repository;

import daitnu.daitnus2.database.entity.MailAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailAttachmentRepository extends JpaRepository<MailAttachment, Long> {
  List<MailAttachment> findAllByMailTemplateId(Long mailTemplateId);
}
