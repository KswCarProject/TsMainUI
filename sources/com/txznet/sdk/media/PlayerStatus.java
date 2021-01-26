package com.txznet.sdk.media;

/* compiled from: Proguard */
public enum PlayerStatus {
    IDLE("idle"),
    BUFFERING("buffering"),
    PLAYING("playing"),
    PAUSED("paused"),
    STOPPED("stopped");
    

    /* renamed from: T  reason: collision with root package name */
    private final String f875T;

    private PlayerStatus(String str) {
        this.f875T = str;
    }

    public String toStatusString() {
        return this.f875T;
    }

    public static PlayerStatus fromStatusString(String str) {
        if (IDLE.toStatusString().equals(str)) {
            return IDLE;
        }
        if (BUFFERING.toStatusString().equals(str)) {
            return BUFFERING;
        }
        if (PLAYING.toStatusString().equals(str)) {
            return PLAYING;
        }
        if (PAUSED.toStatusString().equals(str)) {
            return PAUSED;
        }
        if (STOPPED.toStatusString().equals(str)) {
            return STOPPED;
        }
        return IDLE;
    }
}
