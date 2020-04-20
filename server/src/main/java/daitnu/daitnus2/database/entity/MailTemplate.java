package daitnu.daitnus2.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor
public class MailTemplate {

  @Id @GeneratedValue
  private Long id;

  @NotBlank
  @Column(name = "from_email", nullable = false)
  private String from;

  @NotBlank
  @Column(nullable = false)
  private String mailReceivers;

  @OneToMany(mappedBy = "mailTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Mail> mails = new ArrayList<>();

  @OneToMany(mappedBy = "mailTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MailAttachment> mailAttachments = new ArrayList<>();

  @NotBlank
  @Column(nullable = false)
  private String title;

  @NotNull
  @Column(columnDefinition = "LONGTEXT")
  private String subject;

  public MailTemplate(String from, String mailReceivers, String title, String subject) {
    this.from = from;
    this.mailReceivers = mailReceivers;
    this.title = title;
    this.subject = subject;
  }

  public void addMail(Mail mail) {
    this.mails.add(mail);
  }

  public void removeMail(Mail mail) {
    this.mails.remove(mail);
  }

  public void addAttachment(MailAttachment mailAttachment) {
    this.mailAttachments.add(mailAttachment);
  }

  public void removeAttachment(MailAttachment mailAttachment) {
    this.mailAttachments.remove(mailAttachment);
  }
}
