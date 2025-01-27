package com.realtimeweb.partyapp.controller;

import com.realtimeweb.partyapp.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping ("/test")
    public void broadcastSongRequest(@RequestBody String song) {
        simpMessagingTemplate.convertAndSend("/topic/songs", song);
    }
}
