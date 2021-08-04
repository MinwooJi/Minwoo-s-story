package com.dima.test.dto;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class UserImage {

    private String userId;

    private String userimagePath;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    

    public String getUserimagePath() {
        return userimagePath;
    }

    public void setUserimagePath(String userimagePath) {
        this.userimagePath = userimagePath;
    }
}