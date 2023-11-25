package KNUChat.User.dto.request;

import KNUChat.User.dto.*;
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
    private List<DepartmentDto> departmentDtos;
    private List<CertificationDto> certificationDtos;
    private List<UrlDto> urlDtos;
}
