package com.softtech.hafy.model;


import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class MChatPerson {

    private String keyChat;
    private String keyChatRoom;
    private String message;
    private String sender;
    private String receiver;
    private @ServerTimestamp Date timestamp;

    public MChatPerson() {
        //
    }

    public MChatPerson(String keyChat,
                       String keyChatRoom,
                       String message,
                       String sender,
                       String receiver) {
        this.keyChat = keyChat;
        this.keyChatRoom = keyChatRoom;
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getKeyChat() {
        return keyChat;
    }

    public void setKeyChat(String keyChat) {
        this.keyChat = keyChat;
    }

    public String getKeyChatRoom() {
        return keyChatRoom;
    }

    public void setKeyChatRoom(String keyChatRoom) {
        this.keyChatRoom = keyChatRoom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
