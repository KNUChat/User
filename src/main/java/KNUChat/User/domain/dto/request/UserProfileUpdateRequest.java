package KNUChat.User.domain.dto.request;

import KNUChat.User.domain.dto.CertificationDto;
import KNUChat.User.domain.dto.DepartmentDto;
import KNUChat.User.domain.dto.ProfileDto;
import KNUChat.User.domain.dto.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserProfileUpdateRequest {

    @NotNull(message = "사용자 객체는 반드시 포함되어야 합니다.")
    private UserDto userDto;
    private ProfileDto profileDto;
    @NotNull(message = "본 전공은 필수로 포함되어야 합니다.")
    private List<DepartmentDto> departmentDtos;
    private List<CertificationDto> certificationDtos;
    private List<String> urlDtos;
}
