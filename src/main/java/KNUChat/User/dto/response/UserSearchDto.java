package KNUChat.User.dto.response;

import KNUChat.User.dto.DepartmentDto;
import KNUChat.User.entity.Department;
import KNUChat.User.entity.Profile;
import KNUChat.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchDto {
    private Long id;
    private String name;
    private short stdNum;
    private String academicStatus;
    private List<DepartmentDto> departmentDtos;

    public static UserSearchDto from(User user, Profile profile, List<Department> departments) {
        List<DepartmentDto> departmentDtos = departments.stream()
                .map(DepartmentDto::from)
                .toList();

        return new UserSearchDto(
                user.getId(),
                user.getName(),
                profile.getStdNum(),
                profile.getAcademicStatus().toString(),
                departmentDtos
        );
    }
}
