package com.dima.test.dto;

import java.sql.Timestamp;

public class Comment {

    // POST_NUMBER 
    private Integer postNumber;

    // COMMENT_NUMBER 
    private Integer commentNumber;

    // COMMENT_CONTENT 
    private String commentContent;

    // USER_ID 
    private String userId;

    // COMMENT_DATE 
    private Timestamp commentDate;

    // TARGETUSER_ID 
    private String targetuserId;

    public Integer getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(Integer postNumber) {
        this.postNumber = postNumber;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

    public String getTargetuserId() {
        return targetuserId;
    }

    public void setTargetuserId(String targetuserId) {
        this.targetuserId = targetuserId;
    }
}
