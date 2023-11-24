package KNUChat.User.dto;

import KNUChat.User.entity.Department;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DepartmentDto {

    @NotBlank(message = "단과대학은 필수 정보입니다.")
    private final String college;
    private final String department;
    @NotBlank(message = "학과(전공)은 필수 정보입니다.")
    private final String major;
    @NotBlank(message = "카테고리는 필수 정보입니다.")
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
