package daitnu.daitnus2.domain;

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
  private List<MailReceiver> to = new ArrayList<>();

  @OneToMany(mappedBy = "mailTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Mail> mails = new ArrayList<>();

  @NotBlank
  @Column(nullable = false)
  private String title;

  @NotNull
  @Column(columnDefinition = "LONGTEXT")
  private String subject;

  public MailTemplate(String from, List<MailReceiver> to, List<Mail> mails, String title, String subject) {
    this.from = from;
    this.to = to;
    this.mails = mails;
    this.title = title;
    this.subject = subject;
  }
}
