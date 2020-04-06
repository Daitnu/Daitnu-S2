package daitnu.daitnus2.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor
@ToString
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MailCategory> mailCategories = new ArrayList<>();

    public User(String userId, String pw, String name, String subEmail) {
        this.userId = userId;
        this.pw = pw;
        this.name = name;
        this.email = userId + "@daitnu2.com";
        this.subEmail = subEmail;
    }

    public void addMailCategory(MailCategory mailCategory) {
      this.mailCategories.add(mailCategory);
    }

    public void removeMailCategory(MailCategory mailCategory) {
      this.mailCategories.remove(mailCategory);
    }

    public void updateUserNameAndSubEmail(String name, String subEmail) {
        this.name = name;
        this.subEmail = subEmail;
    }

    public void changePassword(String pw) {
        this.pw = pw;
    }
}
