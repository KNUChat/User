package KNUChat.User.dto;

import KNUChat.User.entity.Certification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CertificationDto {

    private final String name;
    private final String achievement;
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
