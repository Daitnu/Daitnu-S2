package daitnu.daitnus2.mail.category;

import daitnu.daitnus2.database.entity.MailCategory;
import daitnu.daitnus2.database.entity.User;
import daitnu.daitnus2.database.repository.MailCategoryRepository;
import daitnu.daitnus2.exception.BusinessException;
import daitnu.daitnus2.exception.ErrorCode;
import daitnu.daitnus2.mail.category.exception.DuplicateCategoryName;
import daitnu.daitnus2.mail.category.exception.NotFoundCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MailCategoryService {

    private final MailCategoryRepository mailCategoryRepository;

    // 메일 폴더 만들기
    @Transactional
    public MailCategory makeDir(MailCategory mailCategory) {
        validateMakeDir(mailCategory);
        mailCategory.getUser().addMailCategory(mailCategory);
        mailCategoryRepository.save(mailCategory);
        return mailCategory;
    }

    private void validateMakeDir(MailCategory mailCategory) {
        if (mailCategory.getUser() == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }

        List<MailCategory> mailCategories = mailCategoryRepository.
                findAllByUserUserIdAndName(mailCategory.getUser().getUserId(), mailCategory.getName());
        if (!mailCategories.isEmpty()) {
            throw new DuplicateCategoryName();
        }
    }

    // 메일 폴더 삭제
    @Transactional
    public Long removeDir(MailCategory mailCategory, User user) {
        validateRemoveDir(mailCategory, user);
        mailCategoryRepository.delete(mailCategory);
        user.removeMailCategory(mailCategory);
        return mailCategory.getId();
    }

    private void validateRemoveDir(MailCategory mailCategory, User user) {
        if (mailCategory.getUser() == null || user == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }

        List<MailCategory> mailCategories = mailCategoryRepository.
                findAllByUserUserIdAndNameAndId(user.getUserId(), mailCategory.getName(), mailCategory.getId());
        if (mailCategories.isEmpty()) {
            throw new NotFoundCategory();
        }
    }

    // 메일함 이름 수정
    @Transactional
    public MailCategory renameDir(Long mailCategoryId, String oldName, String newName, User user) {
        validateRenameDir(mailCategoryId, oldName, newName, user);
        MailCategory category = mailCategoryRepository.findById(mailCategoryId).get();
        category.updateName(newName);
        return category;
    }

    private void validateRenameDir(Long mailCategoryId, String oldName, String newName, User user) {
        if (user == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }

        Optional<MailCategory> foundWithId = mailCategoryRepository.findById(mailCategoryId);
        if (!foundWithId.isPresent()
            || !foundWithId.get().getName().equals(oldName)
            || !foundWithId.get().getUser().getUserId().equals(user.getUserId())
            || !foundWithId.get().getUser().getId().equals(user.getId())) {
            throw new NotFoundCategory();
        }

        List<MailCategory> foundWithNewName =
            mailCategoryRepository.findAllByUserUserIdAndName(user.getUserId(), newName);
        if (!foundWithNewName.isEmpty()) {
            throw new DuplicateCategoryName();
        }
    }

    public MailCategory findOne(Long id) {
      return mailCategoryRepository.getOne(id);
    }

    public List<MailCategory> findAll(User user) {
        if (user == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }
        return mailCategoryRepository.findAllByUserId(user.getId());
    }
}
