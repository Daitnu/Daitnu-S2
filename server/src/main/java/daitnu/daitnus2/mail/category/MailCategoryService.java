package daitnu.daitnus2.mail.category;

import daitnu.daitnus2.accounts.AccountsService;
import daitnu.daitnus2.database.entity.MailCategory;
import daitnu.daitnus2.database.entity.User;
import daitnu.daitnus2.database.repository.MailCategoryRepository;
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
    private final AccountsService accountsService;

    // 메일 폴더 만들기
    @Transactional
    public MailCategory makeDir(String name, Long userId) {
        User foundUser = accountsService.findOne(userId);
        MailCategory mailCategory = new MailCategory(name, foundUser);
        validateMakeDir(mailCategory);
        mailCategory.getUser().addMailCategory(mailCategory);
        mailCategoryRepository.save(mailCategory);
        return mailCategory;
    }

    private void validateMakeDir(MailCategory mailCategory) {
        List<MailCategory> mailCategories = mailCategoryRepository.
                findAllByUserUserIdAndName(mailCategory.getUser().getUserId(), mailCategory.getName());
        if (!mailCategories.isEmpty()) {
            throw new DuplicateCategoryName();
        }
    }

    // 메일 폴더 삭제
    @Transactional
    public MailCategory removeDir(Long mailCategoryId, String name, Long userId) {
        User user = accountsService.findOne(userId);
        MailCategory mailCategory = findOne(mailCategoryId);
        validateRemoveDir(mailCategory, user, name);
        mailCategoryRepository.delete(mailCategory);
        user.removeMailCategory(mailCategory);
        return mailCategory;
    }

    private void validateRemoveDir(MailCategory mailCategory, User user, String name) {
        List<MailCategory> mailCategories = mailCategoryRepository.
                findAllByUserUserIdAndNameAndId(user.getUserId(), mailCategory.getName(), mailCategory.getId());
        if (mailCategories.isEmpty() || !mailCategory.getName().equals(name)) {
            throw new NotFoundCategory();
        }
    }

    // 메일함 이름 수정
    @Transactional
    public MailCategory renameDir(Long mailCategoryId, String oldName, String newName, Long userId) {
        User user = accountsService.findOne(userId);
        validateRenameDir(mailCategoryId, oldName, newName, user);
        MailCategory category = findOne(mailCategoryId);
        category.updateName(newName);
        return category;
    }

    private void validateRenameDir(Long mailCategoryId, String oldName, String newName, User user) {
        MailCategory mailCategory = findOne(mailCategoryId);
        if (!mailCategory.getUser().getId().equals(user.getId())
            || !mailCategory.getName().equals(oldName)) {
            throw new NotFoundCategory();
        }

        List<MailCategory> foundWithNewName =
            mailCategoryRepository.findAllByUserUserIdAndName(user.getUserId(), newName);
        if (!foundWithNewName.isEmpty()) {
            throw new DuplicateCategoryName();
        }
    }

    public MailCategory findOne(Long id) {
      Optional<MailCategory> mailCategory = mailCategoryRepository.findById(id);
      if (mailCategory.isPresent()) {
        return mailCategory.get();
      }
      throw new NotFoundCategory();
    }

    public List<MailCategory> findAll(Long userId) {
        return mailCategoryRepository.findAllByUserId(userId);
    }
}
