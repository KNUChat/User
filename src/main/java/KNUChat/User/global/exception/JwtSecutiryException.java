package KNUChat.User.global.exception;

public class JwtSecutiryException extends RuntimeException {

    public JwtSecutiryException() {
        super("서버 내부 오류. JWT를 처리할 수 없습니다.");
    }
}
