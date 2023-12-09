package KNUChat.User.global.exception;

import KNUChat.User.global.exception.KnuchatException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidEmailException extends KnuchatException {
    private String source;

    public InvalidEmailException(String source) {
        super(HttpStatus.BAD_REQUEST, source + "는 학교 이메일이 아닙니다.");

    }
}
