package daitnu.daitnus2.mail.attachment;

import daitnu.daitnus2.mail.template.MailTemplate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity @Getter
@NoArgsConstructor
public class MailAttachment {

  @Id @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "mail_template_id", nullable = false)
  private MailTemplate mailTemplate;

  @NotBlank
  @Column(nullable = false)
  private String type;

  @NotBlank
  @Column(nullable = false)
  private String name;

  @NotBlank
  @Column(nullable = false)
  private String url;

  @NotNull
  @Column(nullable = false)
  private Long size;

  public MailAttachment(MailTemplate mailTemplate, String type, String name, String url, Long size) {
    this.mailTemplate = mailTemplate;
    this.type = type;
    this.name = name;
    this.url = url;
    this.size = size;
  }

}
