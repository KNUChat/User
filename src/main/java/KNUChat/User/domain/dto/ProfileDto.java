package KNUChat.User.domain.dto;

import KNUChat.User.domain.entity.Profile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProfileDto {

    @NotNull(message = "학번은 필수 정보입니다.")
    private final short stdNum;
    @NotBlank(message = "학적상태는 필수 정보입니다.")
    private final String academicStatus;
    private final short grade;
    @NotBlank(message = "입학연도는 필수 정보입니다.")
    private final String admissionDate;
    @NotBlank(message = "졸업연도는 필수 정보입니다.")
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
