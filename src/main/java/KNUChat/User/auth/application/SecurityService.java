package KNUChat.User.auth.application;

import KNUChat.User.auth.entity.RefreshToken;
import KNUChat.User.auth.dto.TokenDto;
import KNUChat.User.auth.repository.RefreshTokenRepository;
import KNUChat.User.global.exception.InvalidRefreshTokenException;
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

    public long refreshAccessToken(String oldAccessToken, String oldRefreshToken) {
        long userId = Long.parseLong(jwtProvider.parseClaims(oldAccessToken).getSubject());

        RefreshToken refreshToken = tokenRepository.findByUserId(userId).orElseThrow(() -> {
            throw new InvalidRefreshTokenException("해당 사용자에게 refresh token이 없습니다.");
        });
        if (!refreshToken.getToken().equals(oldRefreshToken))
            throw new InvalidRefreshTokenException("refresh token이 변조되었습니다.");

        tokenRepository.delete(refreshToken);

        return userId;
    }
}
