package daitnu.daitnus2.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor
public class MailCategory {

    @Id @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private MailCategory parent;

    @OneToMany(mappedBy = "parent")
    private List<MailCategory> children = new ArrayList<>();

    public MailCategory(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public void addChildCategory(MailCategory childCategory) {
        this.children.add(childCategory);
        childCategory.parent = this;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
