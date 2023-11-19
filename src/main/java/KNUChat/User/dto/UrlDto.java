package KNUChat.User.dto;

import KNUChat.User.entity.Url;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UrlDto {

    private final String link;

    public static UrlDto from(Url url) {
        return new UrlDto(url.getLink());
    }
}
