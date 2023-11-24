package KNUChat.User.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends KnuchatException {
    private String source;

    public NotFoundException(String source) {
        super(HttpStatus.NOT_FOUND, "존재하지 않는 " + source + "입니다.");
    }
}
