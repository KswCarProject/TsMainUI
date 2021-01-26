package com.txznet.sdk.media;

/* compiled from: Proguard */
public enum PlayerLoopMode {
    SEQUENTIAL("sequential"),
    SINGLE_LOOP("singleLoop"),
    LIST_LOOP("listLoop"),
    SHUFFLE("shuffle");
    

    /* renamed from: T  reason: collision with root package name */
    private String f874T;

    private PlayerLoopMode(String modeStr) {
        this.f874T = modeStr;
    }

    public String toModeStr() {
        return this.f874T;
    }

    public static PlayerLoopMode fromModeStr(String modeStr) {
        if (SEQUENTIAL.toModeStr().equals(modeStr)) {
            return SEQUENTIAL;
        }
        if (SINGLE_LOOP.toModeStr().equals(modeStr)) {
            return SINGLE_LOOP;
        }
        if (LIST_LOOP.toModeStr().equals(modeStr)) {
            return LIST_LOOP;
        }
        if (SHUFFLE.toModeStr().equals(modeStr)) {
            return SHUFFLE;
        }
        return SEQUENTIAL;
    }
}
