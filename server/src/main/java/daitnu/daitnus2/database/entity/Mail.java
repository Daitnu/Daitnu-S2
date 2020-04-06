package daitnu.daitnus2.database.entity;

import daitnu.daitnus2.database.entity.MailCategory;
import daitnu.daitnus2.database.entity.MailTemplate;
import daitnu.daitnus2.database.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Getter
@NoArgsConstructor
public class Mail {

  @Id @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private MailCategory category;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "owner_id", nullable = false)
  private User owner;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mail_template_id", nullable = false)
  private MailTemplate mailTemplate;

  @NotNull
  private boolean isImportant;

  @NotNull
  private boolean isRead;

  @NotNull
  private boolean isRemoved;

  public Mail(MailCategory category, User owner, MailTemplate mailTemplate) {
    this.category = category;
    this.owner = owner;
    this.mailTemplate = mailTemplate;
    this.isImportant = false;
    this.isRead = false;
    this.isRemoved = false;
  }

  public Mail(MailCategory category, User owner, MailTemplate mailTemplate, boolean isImportant, boolean isRead, boolean isRemoved) {
    this.category = category;
    this.owner = owner;
    this.mailTemplate = mailTemplate;
    this.isImportant = isImportant;
    this.isRead = isRead;
    this.isRemoved = isRemoved;
  }

  public void updateCategory(MailCategory category) {
    this.category = category;
  }

  public void updateProperties(boolean isImportant, boolean isRead, boolean isRemoved) {
    this.isImportant = isImportant;
    this.isRead = isRead;
    this.isRemoved = isRemoved;
  }
}
