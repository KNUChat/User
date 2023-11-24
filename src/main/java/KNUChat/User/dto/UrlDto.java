package KNUChat.User.dto;

import KNUChat.User.entity.Url;
import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class UrlDto {

    @NotBlank(message = "URL은 필수 정보입니다.")
    private final String link;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UrlDto(String link) {
        this.link = link;
    }

    public static UrlDto from(Url url) {
        return new UrlDto(url.getLink());
    }
}
