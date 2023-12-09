package KNUChat.User.auth.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "refresh_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String token;

    @Builder
    public RefreshToken(String token) {
        this.token = token;
    }

    public static RefreshToken of(String refreshToken) {
        return RefreshToken.builder().token(refreshToken).build();
    }
}
