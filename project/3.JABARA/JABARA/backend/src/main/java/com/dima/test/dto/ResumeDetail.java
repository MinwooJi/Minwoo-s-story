package com.dima.test.dto;

public class ResumeDetail {

    // RESUME_NUMBER 
    private int resumeNumber;

    // QUESTION_NUMBER 
    private int questionNumber;

    // RESUME_Q 
    private String resumeQ;

    // RESUME_A 
    private String resumeA;
    
    // USER_ID
    private String userId;
    
    

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

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getResumeQ() {
        return resumeQ;
    }

    public void setResumeQ(String resumeQ) {
        this.resumeQ = resumeQ;
    }

    public String getResumeA() {
        return resumeA;
    }

    public void setResumeA(String resumeA) {
        this.resumeA = resumeA;
    }
}
