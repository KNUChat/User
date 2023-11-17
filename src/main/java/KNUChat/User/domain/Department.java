package KNUChat.User.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "dep_id")
    private String id;

    @Column(name = "college")
    private String college;

    @Column(name = "department")
    private String department;

    @Column(name = "major")
    private String major;

    @Column(name = "dep_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private DepCategory depCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;
}