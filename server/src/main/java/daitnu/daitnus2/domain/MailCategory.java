package daitnu.daitnus2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity @Getter
@NoArgsConstructor
public class MailCategory {

    @Id @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public MailCategory(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
