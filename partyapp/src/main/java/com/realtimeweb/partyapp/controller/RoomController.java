package com.realtimeweb.partyapp.controller;

import com.realtimeweb.partyapp.entity.Room;
import com.realtimeweb.partyapp.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Új szoba létrehozása
    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room createdRoom = roomService.createRoom(room);
        return ResponseEntity.ok(createdRoom);
    }

    // Szoba keresése ID alapján
    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable String roomId) {
        Optional<Room> room = roomService.findRoomById(roomId);
        return room.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Szobák listázása DJ ID alapján
    @GetMapping("/dj/{djId}")
    public ResponseEntity<List<Room>> getRoomsByDjId(@PathVariable String djId) {
        List<Room> rooms = roomService.findRoomsByDjId(djId);
        return ResponseEntity.ok(rooms);
    }

    // Szoba törlése
    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable String roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.noContent().build();
    }
}
