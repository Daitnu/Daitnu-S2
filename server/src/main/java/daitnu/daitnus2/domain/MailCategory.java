package daitnu.daitnus2.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class MailCategory {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
