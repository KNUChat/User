package KNUChat.User.domain.dto.request;

import KNUChat.User.domain.dto.CertificationDto;
import KNUChat.User.domain.dto.DepartmentDto;
import KNUChat.User.domain.dto.ProfileDto;
import KNUChat.User.domain.dto.UserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class UserProfileCreateRequest {

    @Valid
    @NotNull(message = "사용자 객체는 반드시 포함되어야 합니다.")
    private UserDto userDto;
    @Valid
    @NotNull(message = "프로필 객체는 반드시 포함되어야 합니다.")
    private ProfileDto profileDto;
    @Valid
    @NotNull(message = "소속 학과 객체는 하나 이상 포함되어야 합니다.")
    private List<DepartmentDto> departmentDtos;
    @Valid
    private List<CertificationDto> certificationDtos;
    @Valid
    private List<String> urlDtos;
}
