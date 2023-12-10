package KNUChat.User.kafka.config;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.MessageHeaders;

@Configuration
@Slf4j
//@PropertySource("classpath:application-kafka.properties")
public class KafkaConfig {
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapAddress;

    @Bean
    public ProducerFactory<Object, Object> producerFactory(){
        Map<String, Object> props = new HashMap<>();

        //Customized
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        //Default
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaListenerErrorHandler kafkaListenerErrorHandler(){
        return (message, exception) -> {
            MessageHeaders headers = message.getHeaders();
            String topic = headers.get(KafkaHeaders.RECEIVED_TOPIC, String.class);
            Long offset = headers.get(KafkaHeaders.OFFSET, Long.class);

            log.info("Error in Listener at topic: " + topic + ", partition: "  + ", offset: " + offset);
            log.info("Error: " + exception.getMessage());

            return null;
        };
    }
}
