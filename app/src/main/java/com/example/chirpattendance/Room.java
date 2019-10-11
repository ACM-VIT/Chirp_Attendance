package com.example.chirpattendance;

public class Room {
    private String hashedKey;
    private String roomName;
    private String unixTime;

    public Room(String hashedKey, String roomName, String unixTime) {
        this.hashedKey = hashedKey;
        this.roomName = roomName;
        this.unixTime = unixTime;
    }

    public Room() {
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(String unixTime) {
        this.unixTime = unixTime;
    }
}
