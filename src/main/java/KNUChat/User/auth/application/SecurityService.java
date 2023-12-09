package KNUChat.User.auth.application;

import KNUChat.User.auth.entity.RefreshToken;
import KNUChat.User.auth.dto.TokenDto;
import KNUChat.User.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final RefreshTokenRepository tokenRepository;
    private final JwtProvider jwtProvider;

    public TokenDto getTokenDto(Long userId) {

        TokenDto tokenDto = jwtProvider.generateTokenDto(userId);

        tokenRepository.save(new RefreshToken(userId, tokenDto.getRefreshToken()));

        return tokenDto;
    }

    public HttpHeaders setTokenHeaders(TokenDto tokenDto) {
        HttpHeaders headers = new HttpHeaders();
        ResponseCookie cookie = ResponseCookie.from("RefreshToken", tokenDto.getRefreshToken())
                .path("/")
                .maxAge(60*60*24*7)  // 쿠키 유효기간: 7일
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        headers.add("Set-cookie", cookie.toString());
        headers.add("Authorization", tokenDto.getAccessToken());

        return headers;
    }
}
