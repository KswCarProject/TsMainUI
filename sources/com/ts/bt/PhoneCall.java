package com.ts.bt;

public class PhoneCall {
    private long callActiveSecond = 0;
    private long callActiveTick = 0;
    private String callName = "";
    private int callState = -1;
    private String callTime = "";
    private String callType = "";

    public String getCallType() {
        return this.callType;
    }

    public void setCallType(String callType2) {
        this.callType = callType2;
    }

    public String getCallName() {
        return this.callName;
    }

    public void setCallName(String callName2) {
        this.callName = callName2;
    }

    public long getCallActiveTick() {
        return this.callActiveTick;
    }

    public void setCallActiveTick(long callActiveTick2) {
        this.callActiveTick = callActiveTick2;
    }

    public long getCallActiveSecond() {
        return this.callActiveSecond;
    }

    public void setCallActiveSecond(long callActiveSecond2) {
        this.callActiveSecond = callActiveSecond2;
    }

    public int getCallState() {
        return this.callState;
    }

    public void setCallState(int callState2) {
        this.callState = callState2;
    }

    public String getCallTime() {
        return this.callTime;
    }

    public void setCallTime(String callTime2) {
        this.callTime = callTime2;
    }

    public String toString() {
        return "PhoneCall [callType=" + this.callType + ", callName=" + this.callName + ", callActiveTick=" + this.callActiveTick + ", callActiveSecond=" + this.callActiveSecond + ", callState=" + this.callState + ", callTime=" + this.callTime + "]";
    }
}
