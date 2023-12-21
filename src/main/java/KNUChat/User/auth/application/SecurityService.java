package KNUChat.User.auth.application;

import KNUChat.User.auth.entity.RefreshToken;
import KNUChat.User.auth.dto.TokenDto;
import KNUChat.User.auth.repository.RefreshTokenRepository;
import KNUChat.User.global.exception.auth.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final RefreshTokenRepository tokenRepository;
    private final JwtProvider jwtProvider;
    private final String prefix = "Bearer ";

    public TokenDto generateTokenDto(Long userId) {

        TokenDto tokenDto = jwtProvider.generateTokenDto(userId);

        tokenRepository.save(new RefreshToken(userId, tokenDto.getRefreshToken()));

        return tokenDto;
    }

    public HttpHeaders setTokenHeaders(TokenDto tokenDto) {
        HttpHeaders headers = new HttpHeaders();
        ResponseCookie cookie = ResponseCookie.from("RefreshToken", tokenDto.getRefreshToken())
                .path("/")
                .maxAge(60*60*24*7)  // 쿠키 유효기간: 7일
                .secure(false)
                .sameSite("Lax")
                .httpOnly(true)
                .build();
        headers.add("Set-cookie", cookie.toString());
        headers.add("Authorization", prefix + tokenDto.getAccessToken());

        return headers;
    }

    public long deleteRefreshToken(String authHeader, String oldRefreshToken) {
        jwtProvider.validateToken(oldRefreshToken);
        String oldAccessToken = jwtProvider.parseAccessToken(authHeader);
        long userId = Long.parseLong(jwtProvider.parseClaims(oldAccessToken).getSubject());

        List<RefreshToken> refreshTokens = tokenRepository.findAllByUserId(userId);
        if (refreshTokens.isEmpty())
            throw new InvalidTokenException(userId + " 사용자에게 refresh token이 없습니다.");

        Optional<RefreshToken> matchingToken = refreshTokens.stream()
                .filter(token -> oldRefreshToken.equals(token.getToken()))
                .findFirst();
        if (matchingToken.isPresent()) {
            RefreshToken oldToken = matchingToken.get();
            tokenRepository.delete(oldToken);
        } else
            throw new InvalidTokenException(userId + " 사용자에게 일치하는 토큰이 아닙니다.");

        return userId;
    }
}
