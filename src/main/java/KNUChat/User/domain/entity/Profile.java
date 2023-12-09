package KNUChat.User.domain.entity;

import KNUChat.User.domain.dto.ProfileDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Entity
@Table(name = "profile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "std_num", nullable = false)
    private short stdNum;

    @Column(name = "academic_status")
    @Enumerated(EnumType.STRING)
    private AcademicStatus academicStatus;

    @Column(name = "grade")
    private short grade;

    @Column(name = "admission_date")
    private String admissionDate;

    @Column(name = "graduate_date")
    private String graduateDate;

    @Column(name = "introduction")
    private String introduction;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Profile(short stdNum, AcademicStatus academicStatus, short grade, String admissionDate, String graduateDate, String introduction, User user) {
        this.stdNum = stdNum;
        this.academicStatus = academicStatus;
        this.grade = grade;
        this.admissionDate = admissionDate;
        this.graduateDate = graduateDate;
        this.introduction = introduction;
        this.user = user;
    }

    public Profile update(ProfileDto profileDto, User user) {
        if (profileDto.getStdNum() != 0) this.stdNum = profileDto.getStdNum();
        if (profileDto.getAcademicStatus() != null) this.academicStatus = AcademicStatus.valueOf(profileDto.getAcademicStatus());
        if (profileDto.getGrade() != 0) this.grade = profileDto.getGrade();
        if (profileDto.getAdmissionDate() != null) this.admissionDate = profileDto.getAdmissionDate();
        if (profileDto.getGraduateDate() != null) this.graduateDate = profileDto.getGraduateDate();
        if (profileDto.getIntroduction() != null) this.introduction = profileDto.getIntroduction();
        if (user != null) this.user = user;

        return this;
    }
}
