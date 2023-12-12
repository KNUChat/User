package KNUChat.User.global.exception.auth;

import KNUChat.User.global.exception.KnuchatException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends KnuchatException {
    public InvalidTokenException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
