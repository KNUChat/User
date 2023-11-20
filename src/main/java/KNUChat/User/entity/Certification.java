package KNUChat.User.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date obtainDate;

    @Column(name = "expire_date")
    @Temporal(TemporalType.DATE)
    private Date expireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Builder
    public Certification(String name, String achievement, Date obtainDate, Date expireDate, Profile profile) {
        this.name = name;
        this.achievement = achievement;
        this.obtainDate = obtainDate;
        this.expireDate = expireDate;
        this.profile = profile;
    }
}
