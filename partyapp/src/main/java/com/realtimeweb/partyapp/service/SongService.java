package com.realtimeweb.partyapp.service;

import com.realtimeweb.partyapp.entity.Song;
import com.realtimeweb.partyapp.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void addSongRequest(String title, String roomId) {
        // letezik-e mar
        List <Song>  roomList = getQueuedSongsByRoomId(roomId);
        String songID  = null;

        for(Song s : roomList){
            if(s.getTitle().equals(title)){
                songID=s.getSongId();
            }
        }
        //ha nem letezik
        if (songID == null){
            Song newSong =  new Song();

            newSong.setSongId(UUID.randomUUID().toString());
            newSong.setRoomId(roomId);
            newSong.setRequestedBy("user");
            newSong.setTitle(title);
            newSong.setLikes(0);
            newSong.setDislikes(0);
            newSong.setStatus("queued");
            songRepository.save(newSong);
        }
        // ha letezik
        else {
            addFeedback(songID, true);
        }

        // TODO: KafkaProducerService.sendMessage(topic = "updates", message = "a kor:5:2"
        //  azt jelenti h "a kor" változott, 5 like, 2 dislike
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
