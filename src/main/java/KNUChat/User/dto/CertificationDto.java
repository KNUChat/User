package KNUChat.User.dto;

import KNUChat.User.entity.Certification;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CertificationDto {

    @NotEmpty(message = "자격증 이름은 필수 정보입니다.")
    private final String name;
    private final String achievement;
    @NotEmpty(message = "자격증 취득일자는 필수 정보입니다.")
    private final String obtainDate;
    private final String expireDate;

    public static CertificationDto from(Certification certification) {
        return new CertificationDto(
                certification.getName(),
                certification.getAchievement(),
                certification.getObtainDate(),
                certification.getExpireDate()
        );
    }
}
