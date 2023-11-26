package KNUChat.User.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateRequest {

    @NotNull(message = "사용자의 학교 이메일은 필수 정보입니다.")
    private String email;
}
