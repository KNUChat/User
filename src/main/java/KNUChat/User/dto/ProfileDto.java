package KNUChat.User.dto;

import KNUChat.User.domain.AcademicStatus;

import KNUChat.User.domain.Profile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ProfileDto {

    private final short stdNum;
    private final AcademicStatus academicStatus;
    private final short grade;
    private final Date admissionDate;
    private final Date graduateDate;
    private final String introduction;

    public static ProfileDto from(Profile profile) {
        return new ProfileDto(
                profile.getStdNum(),
                profile.getAcademicStatus(),
                profile.getGrade(),
                profile.getAdmissionDate(),
                profile.getGraduateDate(),
                profile.getIntroduction()
        );
    }
}
