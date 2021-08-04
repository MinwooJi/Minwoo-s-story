package com.dima.test.dto;

import java.sql.Timestamp;
import java.util.Date;
public class PostLikeTable {

    // USER_ID 
    private String userId;

    // LIKE_DATE 
    private Timestamp  likeDate;

    // POST_NUMBER 
    private int postNumber;

    // TARGETUSER_ID 
    private String targetuserId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Timestamp likeDate) {
        this.likeDate = likeDate;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    public String getTargetuserId() {
        return targetuserId;
    }

    public void setTargetuserId(String targetuserId) {
        this.targetuserId = targetuserId;
    }

}