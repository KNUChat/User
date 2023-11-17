package KNUChat.User.dto;

import KNUChat.User.domain.Url;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UrlDto {

    private final String url;

    public static UrlDto from(Url url) {
        return new UrlDto(url.getLink());
    }
}