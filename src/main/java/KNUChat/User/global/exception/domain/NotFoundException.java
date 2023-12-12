package KNUChat.User.global.exception.domain;

import KNUChat.User.global.exception.KnuchatException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends KnuchatException {
    public NotFoundException(String source) {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 " + source + "입니다.");
    }
}
