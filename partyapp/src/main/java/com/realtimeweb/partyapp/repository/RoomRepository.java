package com.realtimeweb.partyapp.repository;

import com.realtimeweb.partyapp.entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String> {
    List<Room> findByDjId(String djId);
    Optional<Room> findByQrCode(String qrCode);
}
