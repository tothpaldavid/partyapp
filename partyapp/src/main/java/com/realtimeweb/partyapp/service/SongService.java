package com.realtimeweb.partyapp.service;

import com.realtimeweb.partyapp.entity.Song;
import com.realtimeweb.partyapp.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Song addSongRequest(Song song) {
        song.setLikes(0);
        song.setDislikes(0);
        song.setStatus("queued");
        return songRepository.save(song);
    }

    public List<Song> getSongsByRoomId(String roomId) {
        return songRepository.findByRoomId(roomId);
    }

    public List<Song> getQueuedSongsByRoomId(String roomId) {
        return songRepository.findByRoomIdAndStatus(roomId, "queued");
    }

    public Optional<Song> updateSongStatus(String songId, String status) {
        Optional<Song> song = songRepository.findById(songId);
        song.ifPresent(s -> {
            s.setStatus(status);
            songRepository.save(s);
        });
        return song;
    }

    public Optional<Song> addFeedback(String songId, boolean like) {
        Optional<Song> song = songRepository.findById(songId);
        song.ifPresent(s -> {
            if (like) {
                s.setLikes(s.getLikes() + 1);
            } else {
                s.setDislikes(s.getDislikes() + 1);
            }
            songRepository.save(s);
        });
        return song;
    }
}
