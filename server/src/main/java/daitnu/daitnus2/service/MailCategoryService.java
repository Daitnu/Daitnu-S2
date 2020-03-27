package daitnu.daitnus2.service;

import daitnu.daitnus2.domain.MailCategory;
import daitnu.daitnus2.repository.MailCategoryRepository;
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
        mailCategoryRepository.save(mailCategory);
        return mailCategory.getId();
    }

    private void validateMakeDir(MailCategory mailCategory) {
        // TODO: 길이 제한
        List<MailCategory> mailCategories = mailCategoryRepository.
                findByUserIdAndCategoryName(mailCategory.getUser().getUserId(), mailCategory.getName());
        if (!mailCategories.isEmpty()) {
            throw new IllegalStateException("같은 이름의 메일함은 만들 수 없습니다!");
        }
    }

    // 메일 폴더 삭제
    @Transactional
    public Long removeDir(MailCategory mailCategory) {
        mailCategoryRepository.remove(mailCategory);
        return mailCategory.getId();
    }
}
