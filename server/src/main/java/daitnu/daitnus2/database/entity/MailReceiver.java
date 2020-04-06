package daitnu.daitnus2.database.entity;

import daitnu.daitnus2.database.entity.MailTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity @Getter
@NoArgsConstructor
public class MailReceiver {

  @Id @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mail_template_id", nullable = false)
  private MailTemplate mailTemplate;

  @NotBlank
  @Column(nullable = false)
  private String email;

  public MailReceiver(MailTemplate mailTemplate, String email) {
    this.mailTemplate = mailTemplate;
    this.email = email;
  }
}