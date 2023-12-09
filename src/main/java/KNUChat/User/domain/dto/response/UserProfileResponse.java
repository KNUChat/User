package KNUChat.User.domain.dto.response;

import KNUChat.User.domain.dto.CertificationDto;
import KNUChat.User.domain.dto.DepartmentDto;
import KNUChat.User.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {

    private UserDto userDto;
    private KNUChat.User.domain.dto.ProfileDto ProfileDto;
    private List<DepartmentDto> departmentDtos;
    private List<CertificationDto> certificationDtos;
    private List<String> urlDtos;
}
