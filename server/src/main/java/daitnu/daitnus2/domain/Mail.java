package daitnu.daitnus2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

  @NotBlank
  @Column(nullable = false)
  private boolean isImportant;

  @NotBlank
  @Column(nullable = false)
  private boolean isRead;

  @NotBlank
  @Column(nullable = false)
  private boolean isRemoved;

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
