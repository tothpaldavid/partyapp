package com.realtimeweb.partyapp.controller;

import com.realtimeweb.partyapp.entity.Song;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    // Zenekérések valós idejű broadcastja
    @MessageMapping("/songs") // Kliens üzenetet küld ide: "/app/songs"
    @SendTo("/topic/songs") // Broadcast üzenet a klienseknek: "/topic/songs"
    public Song broadcastSongRequest(Song song) {
        return song; // A beérkező üzenetet broadcastoljuk
    }
}
