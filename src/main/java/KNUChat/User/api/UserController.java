package KNUChat.User.api;

import KNUChat.User.application.UserService;
import KNUChat.User.dto.UserProfileRequest;
import KNUChat.User.dto.UserProfileResponse;
import KNUChat.User.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable Long id) {
        UserProfileResponse response = userService.findUserProfileById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/users/signup")
    public ResponseEntity<Void> createUser(@RequestBody UserRequest request) {
        userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUserProfile(@RequestBody UserProfileRequest request) {
        userService.createUserProfile(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
