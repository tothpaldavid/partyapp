package com.realtimeweb.partyapp.repository;

import com.realtimeweb.partyapp.entity.Song;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SongRepository extends MongoRepository<Song, String> {
    List<Song> findByRoomId(String roomId);
    List<Song> findByTitle(String title);
    List<Song> findByRoomIdAndStatus(String roomId, String status);
    List<Song> findByRoomIdOrderByLikesDesc(String roomId);
    List<Song> findByRoomIdAndStatusOrderByLikesDesc(String roomId, String status);
}
