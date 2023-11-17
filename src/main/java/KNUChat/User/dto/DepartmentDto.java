package KNUChat.User.dto;

import KNUChat.User.domain.DepCategory;
import KNUChat.User.domain.Department;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DepartmentDto {

    private final String college;
    private final String department;
    private final String major;
    private final String depCategory;

    public static DepartmentDto from(Department department) {
        return new DepartmentDto(
                department.getCollege(),
                department.getDepartment(),
                department.getMajor(),
                department.getDepCategory().toString()
        );
    }
}
