package KNUChat.User.global.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends KnuchatException {
    public NotFoundException(String source) {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 " + source + "입니다.");
    }
}
