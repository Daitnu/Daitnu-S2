package daitnu.daitnus2.mail.category;

import daitnu.daitnus2.user.User;
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
    public Long makeDir(MailCategory mailCategory) {
        validateMakeDir(mailCategory);
        mailCategory.getUser().addMailCategory(mailCategory);
        mailCategoryRepository.save(mailCategory);
        return mailCategory.getId();
    }

    private void validateMakeDir(MailCategory mailCategory) {
        // TODO: length validation
        List<MailCategory> mailCategories = mailCategoryRepository.
                findAll(mailCategory.getUser().getUserId(), mailCategory.getName());
        if (!mailCategories.isEmpty()) {
            throw new IllegalStateException("같은 이름의 메일함은 만들 수 없습니다!"); // TODO: retype sentence
        }
    }

    // 메일 폴더 삭제
    @Transactional
    public Long removeDir(MailCategory mailCategory, User user) {
        validateRemoveDir(mailCategory, user);
        mailCategoryRepository.remove(mailCategory);
        user.removeMailCategory(mailCategory);
        return mailCategory.getId();
    }

    private void validateRemoveDir(MailCategory mailCategory, User user) {
        List<MailCategory> mailCategories = mailCategoryRepository.
                findAll(user.getUserId(), mailCategory.getName(), mailCategory.getId());
        if (mailCategories.isEmpty()) {
            throw new IllegalStateException("해당 메일함의 소유자이어야 합니다"); // TODO: retype sentence
        }
    }

    public MailCategory findOne(Long id) {
        return mailCategoryRepository.findOne(id);
    }

    public List<MailCategory> findAll() {
        return mailCategoryRepository.findAll();
    }

    public List<MailCategory> findAll(String userId) {
        return mailCategoryRepository.findAll(userId);
    }
}