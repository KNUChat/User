package KNUChat.User.auth.application;

import KNUChat.User.auth.dto.TokenDto;
import KNUChat.User.global.exception.JwtSecutiryException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtProvider {
    private final Key key;
    private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_EXPIRE_TIME = 1000 * 60 * 30; //access 30분
    private static final long REFRESH_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7; //refresh 7일

    public JwtProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto generateTokenDto(Long userId) {

        long now = (new Date()).getTime();

        String accessToken = generateAccessToken(now, userId);
        String refreshToken = generateRefreshToken(now);

        return TokenDto.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpiresIn(new Date(now + ACCESS_EXPIRE_TIME).getTime())
                .refreshToken(refreshToken)
                .build();
    }

    public String generateAccessToken(long now, Long userId) {
        String accessToken = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(new Date(now + ACCESS_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return accessToken;
    }

    public String generateRefreshToken(long now) {
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return refreshToken;
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            throw new JwtSecutiryException();
        }
        catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException |
                 IllegalArgumentException e) {
            throw e;
        }
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}