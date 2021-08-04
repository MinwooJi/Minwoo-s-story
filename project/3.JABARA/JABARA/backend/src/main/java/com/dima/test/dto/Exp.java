package com.dima.test.dto;
public class Exp {

    // EXP_NUMBER 
    private Integer expNumber;

    // EXP_DATE 
    private String expDate;

    // EXP_WORKING 
    private String expWorking;

    // EXP_SUBJECT 
    private String expSubject;

    // EXP_ROLE 
    private String expRole;

    // EXP_CONTENT 
    private String expContent;

    // EXP_SOLVE 
    private String expSolve;

    // EXP_LEARN 
    private String expLearn;

    // EXP_ETC 
    private String expEtc;

    // USER_ID 
    private String userId;

    // EXP_HARD 
    private String expHard;

    public Integer getExpNumber() {
        return expNumber;
    }

    public void setExpNumber(Integer expNumber) {
        this.expNumber = expNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getExpWorking() {
        return expWorking;
    }

    public void setExpWorking(String expWorking) {
        this.expWorking = expWorking;
    }

    public String getExpSubject() {
        return expSubject;
    }

    public void setExpSubject(String expSubject) {
        this.expSubject = expSubject;
    }

    public String getExpRole() {
        return expRole;
    }

    public void setExpRole(String expRole) {
        this.expRole = expRole;
    }

    public String getExpContent() {
        return expContent;
    }

    public void setExpContent(String expContent) {
        this.expContent = expContent;
    }

    public String getExpSolve() {
        return expSolve;
    }

    public void setExpSolve(String expSolve) {
        this.expSolve = expSolve;
    }

    public String getExpLearn() {
        return expLearn;
    }

    public void setExpLearn(String expLearn) {
        this.expLearn = expLearn;
    }

    public String getExpEtc() {
        return expEtc;
    }

    public void setExpEtc(String expEtc) {
        this.expEtc = expEtc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExpHard() {
        return expHard;
    }

    public void setExpHard(String expHard) {
        this.expHard = expHard;
    }

    // Exp 모델 복사
    public void CopyData(Exp param)
    {
        this.expNumber = param.getExpNumber();
        this.expDate = param.getExpDate();
        this.expWorking = param.getExpWorking();
        this.expSubject = param.getExpSubject();
        this.expRole = param.getExpRole();
        this.expContent = param.getExpContent();
        this.expSolve = param.getExpSolve();
        this.expLearn = param.getExpLearn();
        this.expEtc = param.getExpEtc();
        this.userId = param.getUserId();
        this.expHard = param.getExpHard();
    }
}