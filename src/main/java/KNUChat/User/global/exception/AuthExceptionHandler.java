package KNUChat.User.global.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {
    private final HttpStatus unauth = HttpStatus.UNAUTHORIZED;
    private final HttpStatus forbidden = HttpStatus.FORBIDDEN;

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorResponse> securityExceptionHandler(SecurityException e) {
        return ResponseEntity
                .status(unauth)
                .body(new ErrorResponse(unauth.value(), "인증 정보가 유효하지 않습니다."));
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponse> malformedJwtExceptionHandler(MalformedJwtException e) {
        return ResponseEntity
                .status(unauth)
                .body(new ErrorResponse(unauth.value(), "손상된 토큰입니다."));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> expiredJwtExceptionHandler(ExpiredJwtException e) {
        return ResponseEntity
                .status(unauth)
                .body(new ErrorResponse(unauth.value(), "만료된 토큰입니다."));
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ErrorResponse> unsupportedJwtExceptionHandler(UnsupportedJwtException e) {
        return ResponseEntity
                .status(unauth)
                .body(new ErrorResponse(unauth.value(), "지원하지 않는 토큰입니다."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return ResponseEntity
                .status(unauth)
                .body(new ErrorResponse(unauth.value(), "시그니처 검증에 실패했습니다."));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> invalidTokenExceptionHandler(InvalidTokenException e) {
        return ResponseEntity
                .status(e.getHttpStatus().value())
                .body(new ErrorResponse(e.getHttpStatus().value(), e.getMessage()));
    }
}
