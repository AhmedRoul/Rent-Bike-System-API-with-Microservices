package com.RentBikeSystem.RentalRecordService.Kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class BikeProducer {

    @Autowired
    private KafkaTemplate<String, Map<String ,Object>> kafkaTemplate;
    @Autowired
    private NewTopic newTopic;

    public  void sendMessageBikeAvailability(Map<String ,Object> objectMap) {
        Message<Map<String ,Object>> message =  MessageBuilder
                .withPayload(objectMap)
                .setHeader(KafkaHeaders.TOPIC,newTopic.name())
                .build();
        kafkaTemplate.send(message);
        log.info(String.format("Message is sent %s"+objectMap.toString()));
    }

}
