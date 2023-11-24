package KNUChat.User.dto;

import KNUChat.User.entity.AcademicStatus;

import KNUChat.User.entity.Profile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProfileDto {

    private final short stdNum;
    private final AcademicStatus academicStatus;
    private final short grade;
    private final String admissionDate;
    private final String graduateDate;
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
