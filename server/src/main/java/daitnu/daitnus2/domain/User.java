package daitnu.daitnus2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity @Getter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String userId;

    @NotBlank
    @Column(nullable = false)
    private String pw;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String subEmail;

    public User(String userId, String pw, String name, String email, String subEmail) {
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.subEmail = subEmail;
    }

    public void updateUser(String userId, String pw, String name, String email, String subEmail) {
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.email = email;
        this.subEmail = subEmail;
    }
}
