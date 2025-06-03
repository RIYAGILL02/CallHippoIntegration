package com.callhippo.integration;

public class CallLogResponse {

    private String date;
    private String msisdn;
    private String calledNumber;
    private int duration;
    private String status;
    private String direction;

    public CallLogResponse() {
    }

    public CallLogResponse(String date, String msisdn, String calledNumber, int duration, String status) {
        this.date = date;
        this.msisdn = msisdn;
        this.calledNumber = calledNumber;
        this.duration = duration;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(String calledNumber) {
        this.calledNumber = calledNumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
