package daitnu.daitnus2.mail.template;

import daitnu.daitnus2.mail.template.receiver.MailReceiver;
import daitnu.daitnus2.mail.Mail;
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

  @OneToMany(mappedBy = "mailTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MailReceiver> mailReceivers = new ArrayList<>();

  @OneToMany(mappedBy = "mailTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Mail> mails = new ArrayList<>();

  @NotBlank
  @Column(nullable = false)
  private String title;

  @NotNull
  @Column(columnDefinition = "LONGTEXT")
  private String subject;

  public MailTemplate(String from, String title, String subject) {
    this.from = from;
    this.title = title;
    this.subject = subject;
  }

  public void addReceiver(MailReceiver mailReceiver) {
    this.mailReceivers.add(mailReceiver);
  }

  public void removeReceiver(MailReceiver mailReceiver) {
    this.mailReceivers.remove(mailReceiver);
  }

  public void addMail(Mail mail) {
    this.mails.add(mail);
  }

  public void removeMail(Mail mail) {
    this.mails.remove(mail);
  }
}
