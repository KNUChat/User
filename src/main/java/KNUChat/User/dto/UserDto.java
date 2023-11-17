package KNUChat.User.dto;

import KNUChat.User.domain.Gender;
import KNUChat.User.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDto {
    private final String id;
    private final String name;
    private final Gender gender;

    public static UserDto from(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getGender()
        );
    }
}
