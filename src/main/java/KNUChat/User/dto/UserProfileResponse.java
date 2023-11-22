package KNUChat.User.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {

    private UserDto userDto;
    private ProfileDto ProfileDto;
    private List<DepartmentDto> departmentDtos;
    private List<CertificationDto> certificationDtos;
    private List<UrlDto> urlDtos;
}
