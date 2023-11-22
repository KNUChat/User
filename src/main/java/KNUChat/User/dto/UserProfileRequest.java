package KNUChat.User.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserProfileRequest {

    private UserDto userDto;
    private ProfileDto ProfileDto;
    private List<DepartmentDto> departmentDtos;
    private List<CertificationDto> certificationDtos;
    private List<UrlDto> urlDtos;
}
