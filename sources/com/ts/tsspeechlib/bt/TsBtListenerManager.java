package com.ts.tsspeechlib.bt;

import android.os.RemoteException;

public class TsBtListenerManager {
    private static TsBtListenerManager sInstance;
    private ITsSpeechBtListener mBtListenerBinder;

    private TsBtListenerManager() {
    }

    public static TsBtListenerManager getInstance() {
        if (sInstance == null) {
            sInstance = new TsBtListenerManager();
        }
        return sInstance;
    }

    public void setBinder(ITsSpeechBtListener binder) {
        this.mBtListenerBinder = binder;
    }

    public void onBtStateChange(int state, String phoneNumber) {
        if (this.mBtListenerBinder != null) {
            try {
                this.mBtListenerBinder.onBtStateChange(state, phoneNumber);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void onBtConnectStateChange(int state) {
        if (this.mBtListenerBinder != null) {
            try {
                this.mBtListenerBinder.onBtConnectStateChange(state);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
