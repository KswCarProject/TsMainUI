package com.txznet.sdk.media;

/* compiled from: Proguard */
public enum PlayerStatus {
    IDLE("idle"),
    BUFFERING("buffering"),
    PLAYING("playing"),
    PAUSED("paused"),
    STOPPED("stopped");
    

    /* renamed from: T  reason: collision with root package name */
    private final String f871T;

    private PlayerStatus(String str) {
        this.f871T = str;
    }

    public String toStatusString() {
        return this.f871T;
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
