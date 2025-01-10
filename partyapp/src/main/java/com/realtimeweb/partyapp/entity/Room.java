package com.realtimeweb.partyapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "rooms")
public class Room {

    @Id
    private String roomId;
    private String roomName;
    private String djId;
    private String qrCode;
    private LocalDateTime createdAt;

    public Room() {}

    public Room(String roomId, String roomName, String djId, String qrCode, LocalDateTime createdAt) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.djId = djId;
        this.qrCode = qrCode;
        this.createdAt = createdAt;
    }

    // Getterek és setterek
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getDjId() {
        return djId;
    }

    public void setDjId(String djId) {
        this.djId = djId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
