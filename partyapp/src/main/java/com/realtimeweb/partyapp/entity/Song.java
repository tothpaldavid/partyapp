package com.realtimeweb.partyapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "songs")
public class Song {

    @Id
    private String songId;
    private String roomId;
    private String requestedBy;
    private String title;
    private int likes;
    private int dislikes;
    private String status;

    public Song() {}

    public Song(String songId, String roomId, String requestedBy, String title, int likes, int dislikes, String status) {
        this.songId = songId;
        this.roomId = roomId;
        this.requestedBy = requestedBy;
        this.title = title;
        this.likes = likes;
        this.dislikes = dislikes;
        this.status = status;
    }

    // Getterek és setterek
    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
