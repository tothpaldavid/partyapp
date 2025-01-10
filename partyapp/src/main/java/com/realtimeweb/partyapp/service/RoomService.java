package com.realtimeweb.partyapp.service;

import com.realtimeweb.partyapp.entity.Room;
import com.realtimeweb.partyapp.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom(Room room) {
        room.setCreatedAt(LocalDateTime.now());
        return roomRepository.save(room);
    }

    public Optional<Room> findRoomById(String roomId) {
        return roomRepository.findById(roomId);
    }

    public List<Room> findRoomsByDjId(String djId) {
        return roomRepository.findByDjId(djId);
    }

    public void deleteRoom(String roomId) {
        roomRepository.deleteById(roomId);
    }
}