package KNUChat.User.dto;

import KNUChat.User.entity.Profile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProfileDto {

    private final short stdNum;
    private final String academicStatus;
    private final short grade;
    private final String admissionDate;
    private final String graduateDate;
    private final String introduction;

    public static ProfileDto from(Profile profile) {
        return new ProfileDto(
                profile.getStdNum(),
                profile.getAcademicStatus().toString(),
                profile.getGrade(),
                profile.getAdmissionDate(),
                profile.getGraduateDate(),
                profile.getIntroduction()
        );
    }
}
