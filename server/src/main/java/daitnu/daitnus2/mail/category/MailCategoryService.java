package daitnu.daitnus2.mail.category;

import daitnu.daitnus2.database.entity.MailCategory;
import daitnu.daitnus2.database.entity.User;
import daitnu.daitnus2.database.repository.MailCategoryRepository;
import daitnu.daitnus2.mail.category.exception.DuplicateName;
import daitnu.daitnus2.mail.category.exception.NotFoundCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<MailCategory> mailCategories = mailCategoryRepository.
                findAllByUserUserIdAndName(mailCategory.getUser().getUserId(), mailCategory.getName());
        if (!mailCategories.isEmpty()) {
            throw new DuplicateName();
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
        List<MailCategory> mailCategories = mailCategoryRepository.
                findAllByUserUserIdAndNameAndId(user.getUserId(), mailCategory.getName(), mailCategory.getId());
        if (mailCategories.isEmpty()) {
            throw new NotFoundCategory();
        }
    }

    public MailCategory findOne(Long id) {
      return mailCategoryRepository.getOne(id);
    }

    public List<MailCategory> findAll() {
        return mailCategoryRepository.findAll();
    }

    public List<MailCategory> findAll(String userId) {
        return mailCategoryRepository.findAllByUserUserId(userId);
    }
}
