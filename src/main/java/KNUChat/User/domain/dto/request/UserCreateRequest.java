package KNUChat.User.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotNull(message = "사용자의 학교 이메일은 필수 정보입니다.")
    private String email;
}
