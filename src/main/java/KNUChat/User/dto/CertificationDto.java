package KNUChat.User.dto;

import KNUChat.User.entity.Certification;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class CertificationDto {

    private final String name;
    private final String achievement;
    private final Date obtainDate;
    private final Date expireDate;

    public static CertificationDto from(Certification certification) {
        return new CertificationDto(
                certification.getName(),
                certification.getAchievement(),
                certification.getObtainDate(),
                certification.getExpireDate()
        );
    }
}
