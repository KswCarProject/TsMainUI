package com.txznet.comm.ui.dialog;

/* compiled from: Proguard */
public class WinWaiting extends WinProgress {
    public WinWaiting(String txt) {
        super(txt);
        setCancelable(false);
    }

    public WinWaiting setMessageData(Object data) {
        super.setMessageData(data);
        return this;
    }

    public void onBackPressed() {
    }
}
