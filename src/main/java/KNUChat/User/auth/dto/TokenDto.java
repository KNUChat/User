package KNUChat.User.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class TokenDto {
    private final String grantType;
    private final String accessToken;
    private final String refreshToken;
    private final Long accessTokenExpiresIn;
}
