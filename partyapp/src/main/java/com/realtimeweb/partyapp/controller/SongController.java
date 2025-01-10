package com.realtimeweb.partyapp.controller;

import com.realtimeweb.partyapp.entity.Song;
import com.realtimeweb.partyapp.service.KafkaProducerService;
import com.realtimeweb.partyapp.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;
    private final KafkaProducerService kafkaProducerService;

    public SongController(SongService songService, KafkaProducerService kafkaProducerService) {
        this.songService = songService;
        this.kafkaProducerService = kafkaProducerService;
    }

    // Új zenekérés hozzáadása
    @PostMapping
    public ResponseEntity<Song> addSongRequest(@RequestBody Song song) {
        // Új zenekérés mentése az adatbázisba
        Song createdSong = songService.addSongRequest(song);

        // Kafka üzenet küldése
        kafkaProducerService.sendMessage("song-requests", String.format("New song requested: %s", createdSong.getTitle()));

        return ResponseEntity.ok(createdSong);
    }

    // Zenék listázása szoba ID alapján
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Song>> getSongsByRoomId(@PathVariable String roomId) {
        List<Song> songs = songService.getSongsByRoomId(roomId);
        return ResponseEntity.ok(songs);
    }

    // Zenék listázása státusz alapján
    @GetMapping("/room/{roomId}/queued")
    public ResponseEntity<List<Song>> getQueuedSongsByRoomId(@PathVariable String roomId) {
        List<Song> songs = songService.getQueuedSongsByRoomId(roomId);
        return ResponseEntity.ok(songs);
    }

    // Zene státuszának frissítése
    @PatchMapping("/{songId}/status")
    public ResponseEntity<Song> updateSongStatus(@PathVariable String songId, @RequestParam String status) {
        // Státusz frissítése az adatbázisban
        Optional<Song> updatedSong = songService.updateSongStatus(songId, status);

        updatedSong.ifPresent(song -> {
            // Kafka üzenet küldése
            kafkaProducerService.sendMessage("song-updates", String.format("Song status updated: %s -> %s", song.getTitle(), status));
        });

        return updatedSong.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Like vagy Dislike hozzáadása
    @PatchMapping("/{songId}/feedback")
    public ResponseEntity<Song> addFeedback(@PathVariable String songId, @RequestParam boolean like) {
        // Like vagy dislike hozzáadása az adatbázishoz
        Optional<Song> updatedSong = songService.addFeedback(songId, like);

        updatedSong.ifPresent(song -> {
            String feedbackType = like ? "like" : "dislike";
            // Kafka üzenet küldése
            kafkaProducerService.sendMessage("song-feedback", String.format("Song %s updated: %s (%d likes, %d dislikes)",
                    feedbackType, song.getTitle(), song.getLikes(), song.getDislikes()));
        });

        return updatedSong.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
