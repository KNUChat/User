package KNUChat.User.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserBatchResponse {
    private List<UserSearchDto> userSearchDtos;
    private int totalPages;

    public static UserBatchResponse of(List<UserSearchDto> userSearchDtos, int totalPages) {
        return new UserBatchResponse(userSearchDtos, totalPages);
    }
}
