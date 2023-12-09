package KNUChat.User.global.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class KnuchatExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(new ErrorResponse(e.getStatusCode().value(), String.join(", ", getMessage(e))));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundExceptionHandler(NotFoundException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ErrorResponse(e.getHttpStatus().value(), e.getMessage()));
    }

    public List<String> getMessage(MethodArgumentNotValidException e) {
        return e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다."));

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> DataIntegrityViolationExceptionHandler() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "데이터가 유효하지 않습니다."));
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorResponse> InvalidDataExceptionHandler(InvalidEmailException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ErrorResponse(e.getHttpStatus().value(), e.getMessage()));
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ErrorResponse> UnsupportedJwtExceptionHandler(UnsupportedJwtException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> ExpiredJwtExceptionHandler(ExpiredJwtException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponse> MalformedJwtExceptionHandler(MalformedJwtException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }
}
