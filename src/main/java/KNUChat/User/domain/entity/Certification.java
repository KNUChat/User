package KNUChat.User.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "certification")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "achievement")
    private String achievement;

    @Column(name = "obtain_date")
    private String obtainDate;

    @Column(name = "expire_date")
    private String expireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Builder
    public Certification(String name, String achievement, String obtainDate, String expireDate, Profile profile) {
        this.name = name;
        this.achievement = achievement;
        this.obtainDate = obtainDate;
        this.expireDate = expireDate;
        this.profile = profile;
    }
}
