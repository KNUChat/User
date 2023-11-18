package KNUChat.User.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
@Table(name = "certification")
public class Certification {

    @Id
    @Column(name = "certification_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "achievement")
    private String achievement;

    @Column(name = "obtain_date")
    private Date obtainDate;

    @Column(name = "expire_date")
    private Date expireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
