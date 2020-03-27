package daitnu.daitnus2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor
public class MailCategory {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public MailCategory(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
