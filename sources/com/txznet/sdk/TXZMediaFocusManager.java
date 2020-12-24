package com.txznet.sdk;

import android.content.Intent;
import android.util.Log;
import com.txznet.T.T;

/* compiled from: Proguard */
public class TXZMediaFocusManager {
    public static final String INTENT_FOCUS_GAINED = "com.txz.media.focus.gained";
    public static final String INTENT_FOCUS_RELEASED = "com.txz.media.focus.released";
    private static TXZMediaFocusManager Tr = new TXZMediaFocusManager();

    /* renamed from: T  reason: collision with root package name */
    Runnable f746T = new Runnable() {
        public void run() {
            if (TXZMediaFocusManager.this.isFocusGained()) {
                boolean unused = TXZMediaFocusManager.this.Ty = false;
                TXZMediaFocusManager.this.T();
            }
        }
    };
    private long Tn = 0;
    /* access modifiers changed from: private */
    public boolean Ty = false;

    private TXZMediaFocusManager() {
    }

    public static TXZMediaFocusManager getInstance() {
        return Tr;
    }

    public void requestFocus() {
        boolean needNotify = !isFocusGained();
        this.Ty = true;
        if (needNotify) {
            T();
        }
        T.Ty(this.f746T);
        T.T(this.f746T, 8000);
    }

    public void releaseFocus() {
        if (isFocusGained()) {
            this.Ty = false;
            T();
            T.Ty(this.f746T);
        }
    }

    public boolean isFocusGained() {
        return this.Ty;
    }

    /* access modifiers changed from: private */
    public void T() {
        Intent intent;
        Log.d("asd", "media focus changed: " + (isFocusGained() ? "true" : "false"));
        if (isFocusGained()) {
            intent = new Intent(INTENT_FOCUS_GAINED);
        } else {
            intent = new Intent(INTENT_FOCUS_RELEASED);
        }
        com.txznet.comm.Tr.T.Tr().sendBroadcast(intent);
    }
}
