package KNUChat.User.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @Column(name = "profile_id")
    private String id;

    @Column(name = "std_num", nullable = false)
    private short stdNum;

    @Column(name = "academic_status")
    @Enumerated(EnumType.STRING)
    private AcademicStatus academicStatus;

    @Column(name = "grade")
    private short grade;

    @Column(name = "admission_date")
    private Date admissionDate;

    @Column(name = "graduate_date")
    private Date graduateDate;

    @Column(name = "introduction")
    private String introduction;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
