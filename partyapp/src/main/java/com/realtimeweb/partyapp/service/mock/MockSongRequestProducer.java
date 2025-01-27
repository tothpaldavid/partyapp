package com.realtimeweb.partyapp.service.mock;


import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Service;

/**
 * Ideiglenes
 * Mintha egy Paraszt Androidja lenne, csinál song_requesteket
 * */
@Service
public class MockSongRequestProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public MockSongRequestProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendSongRequest(String songName, String roomId) {
        kafkaTemplate.send("song-requests", roomId, songName);
        System.out.println("Produced message to topic song-request: " +  songName + " in roomId: " + roomId);
    }
}

