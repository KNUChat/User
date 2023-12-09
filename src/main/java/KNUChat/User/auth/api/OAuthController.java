package KNUChat.User.auth.api;

import KNUChat.User.domain.application.UserService;
import KNUChat.User.auth.application.OAuthService;
import KNUChat.User.auth.application.SecurityService;
import KNUChat.User.auth.dto.TokenDto;
import KNUChat.User.global.exception.InvalidEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;
    private final SecurityService securityService;
    private final UserService userService;

    @GetMapping(value = "/oauth2")
    public ResponseEntity<Void> socialLoginType(@RequestParam("code") String code) {
        String email = oAuthService.getUserEmail(code);
        if (!validateEmail(email))
            throw new InvalidEmailException(email);

        Long userId = userService.siginIn(email);
        TokenDto tokenDto = securityService.getTokenDto(userId);
        HttpHeaders headers = securityService.setTokenHeaders(tokenDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .build();
    }

    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(".*@knu\\.ac\\.kr$");
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
