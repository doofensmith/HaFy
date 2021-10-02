package com.softtech.hafy.model;

public class MChatRoom {
    //atribut
    private String keyChatRoom;
    private String targetName;
    private String lastChat;
    private String targetPic;
    private String lastChatTime;

    public MChatRoom() {
        //
    }

    public MChatRoom(String keyChatRoom,
                     String targetName,
                     String lastChat,
                     String targetPic,
                     String lastChatTime) {
        this.keyChatRoom = keyChatRoom;
        this.targetName = targetName;
        this.lastChat = lastChat;
        this.targetPic = targetPic;
        this.lastChatTime = lastChatTime;
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

    public String getLastChatTime() {
        return lastChatTime;
    }

    public void setLastChatTime(String lastChatTime) {
        this.lastChatTime = lastChatTime;
    }
}
