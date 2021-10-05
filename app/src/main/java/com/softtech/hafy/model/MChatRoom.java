package com.softtech.hafy.model;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class MChatRoom {
    //atribut
    private String keyChatRoom;
    private String targetName;
    private String targetKey;
    private String lastChat;
    private String targetPic;
    private @ServerTimestamp Date lastChatTime;

    public MChatRoom() {
        //
    }

    public MChatRoom(String keyChatRoom,
                     String targetName,
                     String targetKey,
                     String lastChat,
                     String targetPic) {
        this.keyChatRoom = keyChatRoom;
        this.targetName = targetName;
        this.targetKey = targetKey;
        this.lastChat = lastChat;
        this.targetPic = targetPic;
    }

    public String getKeyChatRoom() {
        return keyChatRoom;
    }

    public void setKeyChatRoom(String keyChatRoom) {
        this.keyChatRoom = keyChatRoom;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getLastChat() {
        return lastChat;
    }

    public void setLastChat(String lastChat) {
        this.lastChat = lastChat;
    }

    public String getTargetPic() {
        return targetPic;
    }

    public void setTargetPic(String targetPic) {
        this.targetPic = targetPic;
    }

    public Date getLastChatTime() {
        return lastChatTime;
    }

    public void setLastChatTime(Date lastChatTime) {
        this.lastChatTime = lastChatTime;
    }

    public String getTargetKey() {
        return targetKey;
    }

    public void setTargetKey(String targetKey) {
        this.targetKey = targetKey;
    }
}
