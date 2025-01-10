package com.realtimeweb.partyapp.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final SimpMessagingTemplate messagingTemplate;

    public KafkaConsumerService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "song-requests", groupId = "party-app")
    public void consume(ConsumerRecord<String, String> record) {
        String topic = record.topic();
        String key = record.key();
        String value = record.value();

        System.out.printf("Consumed message from topic %s: key = %s, value = %s%n", topic, key, value);
        messagingTemplate.convertAndSend("/topic/songs", value);
    }
}
