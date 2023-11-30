package KNUChat.User.api;

import KNUChat.User.application.UserService;
import KNUChat.User.dto.request.UserProfileCreateRequest;
import KNUChat.User.dto.request.UserProfileUpdateRequest;
import KNUChat.User.dto.response.UserBatchResponse;
import KNUChat.User.dto.response.UserProfileResponse;
import KNUChat.User.dto.request.UserCreateRequest;
import KNUChat.User.dto.response.UserIdResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<UserIdResponse> createUser(@RequestBody UserCreateRequest request) {
        Long id = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserIdResponse(id));
    }

    @PostMapping("/users")
    public ResponseEntity<UserIdResponse> createUserProfile(@RequestBody @Valid UserProfileCreateRequest request) {
        Long id = userService.createUserProfile(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserIdResponse(id));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserProfileResponse> getUserProfile(@PathVariable Long id) {
        UserProfileResponse response = userService.getUserProfileByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/users")
    public ResponseEntity<UserBatchResponse> searchRecord(@RequestParam("page") int page, @RequestParam("major") String major) {
        UserBatchResponse response = new UserBatchResponse(userService.getPaging(major, page));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/users")
    public ResponseEntity<Void> updateUserProfile(@RequestBody @Valid UserProfileUpdateRequest request) {
        userService.updateUserProfile(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
