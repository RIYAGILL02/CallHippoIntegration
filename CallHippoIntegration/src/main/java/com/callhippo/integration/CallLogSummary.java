package com.callhippo.integration;

public class CallLogSummary {

    private int incomingCallCount;
    private int outgoingCallCount;
    private int incomingCallDuration;
    private int outgoingCallDuration;

    public CallLogSummary() {
    }

    public CallLogSummary(int incomingCallCount, int outgoingCallCount, int incomingCallDuration,
            int outgoingCallDuration) {
        this.incomingCallCount = incomingCallCount;
        this.outgoingCallCount = outgoingCallCount;
        this.incomingCallDuration = incomingCallDuration;
        this.outgoingCallDuration = outgoingCallDuration;
    }

    public int getIncomingCallCount() {
        return incomingCallCount;
    }

    public void setIncomingCallCount(int incomingCallCount) {
        this.incomingCallCount = incomingCallCount;
    }

    public int getOutgoingCallCount() {
        return outgoingCallCount;
    }

    public void setOutgoingCallCount(int outgoingCallCount) {
        this.outgoingCallCount = outgoingCallCount;
    }

    public int getIncomingCallDuration() {
        return incomingCallDuration;
    }

    public void setIncomingCallDuration(int incomingCallDuration) {
        this.incomingCallDuration = incomingCallDuration;
    }

    public int getOutgoingCallDuration() {
        return outgoingCallDuration;
    }

    public void setOutgoingCallDuration(int outgoingCallDuration) {
        this.outgoingCallDuration = outgoingCallDuration;
    }
}
