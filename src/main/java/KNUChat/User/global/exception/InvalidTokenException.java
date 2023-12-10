package KNUChat.User.global.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends KnuchatException {
    public InvalidTokenException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
