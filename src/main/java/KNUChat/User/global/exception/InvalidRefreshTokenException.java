package KNUChat.User.global.exception;

import org.springframework.http.HttpStatus;

public class InvalidRefreshTokenException extends KnuchatException {
    public InvalidRefreshTokenException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
