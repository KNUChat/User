package KNUChat.User.kafka.application;

import KNUChat.User.kafka.dto.LogType;
import KNUChat.User.kafka.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Logger {

    private final KafkaProducer kafkaProducer;

    public void infoLog(String logMessage, Long userId) {
        Message message = Message.builder()
                .logMessage(logMessage)
                .service("User")
                .type(LogType.INFO)
                .userId(userId)
                .build();

        kafkaProducer.sendMessage(message);
    }

    public void errorLog(String logMessage, Long userId) {
        Message message = Message.builder()
                .logMessage(logMessage)
                .service("User")
                .type(LogType.ERROR)
                .userId(userId)
                .build();

        kafkaProducer.sendMessage(message);
    }
}
