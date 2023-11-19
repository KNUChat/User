package KNUChat.User.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class UserProfileRequest {

    private final UserDto userDto;
    private final ProfileDto ProfileDto;
    private final List<DepartmentDto> departmentDtos;
    private final List<CertificationDto> certificationDtos;
    private final List<UrlDto> urlDtos;
}
