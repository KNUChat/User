package KNUChat.User.domain.dto;

import KNUChat.User.domain.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDto {
    @NotNull(message = "사용자 ID는 필수 정보입니다.")
    private final Long id;
    @NotBlank(message = "사용자 이름은 필수 정보입니다.")
    private final String name;
    @NotBlank(message = "사용자 성별은 필수 정보입니다.")
    private final String gender;
    @NotBlank(message = "사용자 e-mail은 필수 정보입니다.")
    private final String email;

    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getGender().toString(),
                user.getEmail()
        );
    }
}
