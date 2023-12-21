package KNUChat.User.domain.api;

import KNUChat.User.domain.application.UserService;
import KNUChat.User.domain.dto.request.UserProfileCreateRequest;
import KNUChat.User.domain.dto.request.UserProfileUpdateRequest;
import KNUChat.User.domain.dto.response.UserBatchResponse;
import KNUChat.User.domain.dto.response.UserProfileResponse;
import KNUChat.User.domain.dto.request.UserCreateRequest;
import KNUChat.User.domain.dto.response.UserIdResponse;
import KNUChat.User.kafka.application.Logger;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final Logger logger;

    @PostMapping("/users/signup")
    public ResponseEntity<UserIdResponse> createUser(@RequestBody UserCreateRequest request) {
        Long id = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserIdResponse(id));
    }

    @PostMapping("/users")
    public ResponseEntity<UserIdResponse> createUserProfile(@RequestBody @Valid UserProfileCreateRequest request) {
        Long id = userService.createUserProfile(request);
        logger.infoLog("User create user-profile", id);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserIdResponse(id));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable Long id) {
        UserProfileResponse response = userService.getUserProfileByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/users")
    public ResponseEntity<UserBatchResponse> searchUser(@RequestParam("page") int page, @RequestParam("major") String major) {
        UserBatchResponse response = userService.getUserBatch(major, page);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/users")
    public ResponseEntity<Void> updateUserProfile(@RequestBody @Valid UserProfileUpdateRequest request) {
        Long id = userService.updateUserProfile(request);
        logger.infoLog("User create user-profile", id);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Long userId = userService.deleteUserProfile(id);
        logger.infoLog("User create user-profile", userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
