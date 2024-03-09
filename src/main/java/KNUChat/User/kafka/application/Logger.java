package KNUChat.User.kafka.application;

import KNUChat.User.kafka.dto.LogType;
import KNUChat.User.kafka.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Logger {
    private final KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(LogType logType, String logMessage, Long userId) {
        Message message;
        if (logType.equals(LogType.INFO))
            message = buildInfoLog(logMessage, userId);
        else if (logType.equals(LogType.ERROR))
            message = buildErrorLog(logMessage, userId);
        else
            message = buildErrorLog(logType + ": 로그 타입 불일치로 인해 Message 빌드 실패" , userId);

        kafkaTemplate.send("log", message);
    }

    public Message buildInfoLog(String logMessage, Long userId) {
        Message message = Message.builder()
                .logMessage(logMessage)
                .service("User")
                .type(LogType.INFO)
                .userId(userId)
                .build();

        return message;
    }

    public Message buildErrorLog(String logMessage, Long userId) {
        Message message = Message.builder()
                .logMessage(logMessage)
                .service("User")
                .type(LogType.ERROR)
                .userId(userId)
                .build();

        return message;
    }
}
