package com.dima.test.dto;

import java.util.Date;

public class Resume {

    // USER_ID 
    private String userId;

    // RESUME_NUMBER 
    private int resumeNumber;

    // COMPANY_NAME 
    private String companyName;

    // RESUME_PASS 
    private String resumePass;

    // RESUME_DATE 
    private Date resumeDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getResumeNumber() {
        return resumeNumber;
    }

    public void setResumeNumber(int resumeNumber) {
        this.resumeNumber = resumeNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getResumePass() {
        return resumePass;
    }

    public void setResumePass(String resumePass) {
        this.resumePass = resumePass;
    }

    public Date getResumeDate() {
        return resumeDate;
    }

    public void setResumeDate(Date resumeDate) {
        this.resumeDate = resumeDate;
    }

	@Override
	public String toString() {
		return "Resume [userId=" + userId + ", resumeNumber=" + resumeNumber + ", companyName=" + companyName
				+ ", resumePass=" + resumePass + ", resumeDate=" + resumeDate + "]";
	}

    
}