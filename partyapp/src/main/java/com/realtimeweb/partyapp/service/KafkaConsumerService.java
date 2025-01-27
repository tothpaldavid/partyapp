package com.realtimeweb.partyapp.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final SimpMessagingTemplate messagingTemplate;
    private final SongService songService;

    public KafkaConsumerService(SimpMessagingTemplate messagingTemplate, SongService songService) {
        this.messagingTemplate = messagingTemplate;
        this.songService = songService;
    }

    @KafkaListener(topics = "song-requests", groupId = "party-app")
    public void consume(ConsumerRecord<String, String> record) {
        String topic = record.topic();
        String roomId = record.key();
        String title = record.value();
        songService.addSongRequest(title, roomId);
        System.out.printf("Consumed message from topic %s: roomId = %s, value = %s%n", topic, roomId, title);
//        messagingTemplate.convertAndSend("/topic/songs", value);
    }
}
