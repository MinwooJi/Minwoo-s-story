package com.dima.test.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

public class SocketChat {

    // CHAT_ROOM 
    private String chatRoom;

    // MESSAGE_NUMBER 
    private Integer messageNumber;

    // OTHER_ID 
    private String otherId;

    // USER_ID 
    private String userId;

    // CONTENT 
    private String content;

    // DATE 
    private Timestamp date;

    public String getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(String chatRoom) {
        this.chatRoom = chatRoom;
    }

    public Integer getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(Integer messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
	