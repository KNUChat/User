package KNUChat.User.api;

import KNUChat.User.application.UserService;
import KNUChat.User.dto.request.UserProfileRequest;
import KNUChat.User.dto.response.UserProfileResponse;
import KNUChat.User.dto.request.UserRequest;
import KNUChat.User.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        Long id = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(id));
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUserProfile(@RequestBody @Valid UserProfileRequest request) {
        Long id = userService.createUserProfile(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(id));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable Long id) {
        UserProfileResponse response = userService.getUserProfileByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/users")
    public ResponseEntity<Void> updateUserProfile(@RequestBody UserProfileRequest request) {
        userService.updateUserProfile(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
