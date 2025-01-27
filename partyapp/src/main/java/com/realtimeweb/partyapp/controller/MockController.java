package com.realtimeweb.partyapp.controller;


import com.realtimeweb.partyapp.entity.Song;
import com.realtimeweb.partyapp.service.mock.MockSongRequestProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockController {

    private final MockSongRequestProducer mockSongRequestProducer;

    public MockController(MockSongRequestProducer mockSongRequestProducer) {
        this.mockSongRequestProducer = mockSongRequestProducer;
    }

    @PostMapping("/songrequest")
    public ResponseEntity<Song> addMockSongRequest(@RequestBody Song song) {
        mockSongRequestProducer.sendSongRequest(song.getTitle(), song.getRoomId());
        return ResponseEntity.ok(song);
    }
}
