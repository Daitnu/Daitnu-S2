package daitnu.daitnus2.database.repository;

import daitnu.daitnus2.database.entity.MailCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailCategoryRepository extends JpaRepository<MailCategory, Long> {

  MailCategory findByNameAndUserId(String categoryName, Long userId);

  /**
   * 메일함 조회시 사용할 예점
   * @param userId string id of user
   * @return List of MailCategory
   */
  List<MailCategory> findAllByUserId(Long userId);

  /**
   * 메일함 생성할 때 사용할 예정
   * @param userId string id of user
   * @param categoryName name of category
   * @return List of MailCategory
   */
  List<MailCategory> findAllByUserUserIdAndName(String userId, String categoryName);

  /**
   * 카테고리(메일함) 삭제시, 수정시 사용할 예정
   * @param userId string id of user
   * @param categoryName name of category
   * @param categoryId id of category
   * @return List of MailCategory
   */
  List<MailCategory> findAllByUserUserIdAndNameAndId(String userId, String categoryName, Long categoryId);

  /**
   * 메일을 해당 메일로 이동시킬 때 validation 하기 위해 추가함
   * @param id id of mailCategory
   * @param userId id of user
   * @return List of MailCategory
   */
  List<MailCategory> findAllByIdAndUserId(Long id, Long userId);
}
