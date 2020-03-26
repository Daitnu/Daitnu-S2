package daitnu.daitnus2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue
    private Long id;

    @NotBlank
    private String userId;

    @NotBlank
    private String pw;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
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
