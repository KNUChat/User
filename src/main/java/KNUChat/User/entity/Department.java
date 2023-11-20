package KNUChat.User.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "department")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Builder
    public Department(String college, String department, String major, DepCategory depCategory, Profile profile) {
        this.college = college;
        this.department = department;
        this.major = major;
        this.depCategory = depCategory;
        this.profile = profile;
    }
}
