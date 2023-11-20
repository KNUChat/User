package KNUChat.User.dto;

import KNUChat.User.entity.Gender;
import KNUChat.User.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDto {
    private final Long id;
    private final String name;
    private final String gender;
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
