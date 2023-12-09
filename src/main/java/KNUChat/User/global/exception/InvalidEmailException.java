package KNUChat.User.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidEmailException extends KnuchatException {
    public InvalidEmailException(String source) {
        super(HttpStatus.BAD_REQUEST, source + "는 학교 이메일이 아닙니다.");

    }
}
