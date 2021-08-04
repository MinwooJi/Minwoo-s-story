package com.dima.test.dto;

import java.sql.Timestamp;

public class Mycal {

    // CAL_NUMBER 
    private int calNumber;

    // USER_ID 
    private String userId;

    // START_DATE 
    private Timestamp startDate;

    // END_DATE 
    private Timestamp endDate;

    // CAL_TITLE 
    private String calTitle;

    // CAL_CONTENT 
    private String calContent;

    public int getCalNumber() {
        return calNumber;
    }

    public void setCalNumber(int calNumber) {
        this.calNumber = calNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getCalTitle() {
        return calTitle;
    }

    public void setCalTitle(String calTitle) {
        this.calTitle = calTitle;
    }

    public String getCalContent() {
        return calContent;
    }

    public void setCalContent(String calContent) {
        this.calContent = calContent;
    }
}
