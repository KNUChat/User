package KNUChat.User.kafka.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Message {
    private Date time;
    private String service;
    private LogType type;
    private String logMessage;
    private Long roomId;
    private Long userId;
}
