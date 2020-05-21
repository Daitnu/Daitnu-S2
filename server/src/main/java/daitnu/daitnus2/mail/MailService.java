package daitnu.daitnus2.mail;

import daitnu.daitnus2.accounts.AccountsService;
import daitnu.daitnus2.database.entity.*;
import daitnu.daitnus2.database.repository.MailRepository;
import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;
import daitnu.daitnus2.mail.attachment.MailAttachmentService;
import daitnu.daitnus2.mail.category.MailCategoryService;
import daitnu.daitnus2.mail.exception.NotFoundMail;
import daitnu.daitnus2.mail.template.MailTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MailService {

  private final MailCategoryService mailCategoryService;
  private final MailTemplateService mailTemplateService;
  private final MailAttachmentService mailAttachmentService;
  private final AccountsService accountsService;
  private final MailRepository mailRepository;

  @Transactional
  public Mail addMail(Long userId, MailDTO.AddMailDTO dto) {
    MailCategory sentMailBox = mailCategoryService.findByNameAndUserId("보낸메일함", userId);
    MailTemplate mailTemplate = mailTemplateService.makeMailTemplate(dto.getFrom(),
                                                                      dto.getMailReceivers(),
                                                                      dto.getTitle(),
                                                                      dto.getSubject());
    List<MultipartFile> multipartFiles = dto.getFiles();
    if (multipartFiles != null && !multipartFiles.isEmpty()) {
      for (MultipartFile file: multipartFiles) {
        MailAttachment attachment = mailAttachmentService.makeMailAttachment(mailTemplate.getId(),
                                                                              file.getContentType(),
                                                                              file.getOriginalFilename(),
                                                                              "url",
                                                                              file.getSize());
        mailTemplate.addAttachment(attachment);
      }
    }
    User user = accountsService.findOne(userId);
    Mail mail = new Mail(sentMailBox, user, mailTemplate);
    mail.getMailTemplate().addMail(mail);
    mailRepository.save(mail);
    return mail;
  }

  // 메일 삭제
  @Transactional
  public Long removeMail(Mail mail, Long userId) {
    validateMailOwner(mail.getId(), userId);
    mail.getMailTemplate().removeMail(mail);
    mailRepository.delete(mail);
    return mail.getId();
  }

  // 메일 속성 변경
  @Transactional
  public void updateProperties(Long mailId, Long userId, boolean isImportant, boolean isRead, boolean isRemoved) {
    validateMailOwner(mailId, userId);
    Mail one = mailRepository.getOne(mailId);
    one.updateProperties(isImportant, isRead, isRemoved);
  }

  private void validateMailOwner(Long mailId, Long userId) {
    Optional<Mail> mail = mailRepository.findById(mailId);
    if (!mail.isPresent()) {
      throw new NotFoundMail();
    }

    if (!mail.get().getOwner().getId().equals(userId)) {
      throw new NotFoundMail();
    }
  }

  // 메일함(Category) 수정(== 메일 이동)
  @Transactional
  public void updateCategory(Long mailId, Long userId, Long mailCategoryId) {
    if (userId == null) {
      throw new BusinessException(ErrorCode.UNAUTHORIZED);
    }
    validateCategory(userId, mailCategoryId, mailId);
    Mail mail = mailRepository.getOne(mailId);
    MailCategory mailCategory = mailCategoryService.findOne(mailCategoryId);
    mail.updateCategory(mailCategory);
  }

  private void validateCategory(Long userId, Long mailCategoryId, Long mailId) {
    mailCategoryService.findOne(mailCategoryId);
    validateMailOwner(mailId, userId);
  }

  @Transactional
  public void patchMail(MailDTO.PatchMailDTO dto, Long userId) {
    if (dto.getType().equalsIgnoreCase(MailDTO.PatchType.MOVE.toString())) {
      updateCategory(dto.getMailId(), userId, dto.getCategoryId());
    } else if (dto.getType().equalsIgnoreCase(MailDTO.PatchType.ALTER.toString())) {
      updateProperties(dto.getMailId(), userId, dto.getImportant(), dto.getRead(), dto.getRemoved());
    }
  }

  // 메일 find one
  public Mail findOne(Long id) {
    Optional<Mail> mail = mailRepository.findById(id);
    if (mail.isPresent()) {
      return mail.get();
    }
    throw new NotFoundMail();
  }

  public List<Mail> findAll() {
    return mailRepository.findAll();
  }

  public List<Mail> findAll(Long userId) {
    if (userId == null) {
      throw new BusinessException(ErrorCode.UNAUTHORIZED);
    }
    return mailRepository.findAllByOwnerId(userId);
  }

  public List<Mail> findAll(Long userId, Long categoryId) {
    return mailRepository.findAllByOwnerIdAndCategoryId(userId, categoryId);
  }
}
