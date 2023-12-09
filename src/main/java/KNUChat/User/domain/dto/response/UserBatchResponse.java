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
}
