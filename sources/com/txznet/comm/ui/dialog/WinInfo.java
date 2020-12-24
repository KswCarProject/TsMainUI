package com.txznet.comm.ui.dialog;

import android.view.View;

/* compiled from: Proguard */
public class WinInfo extends WinMessageBox {
    public WinInfo() {
        this.TG = new View[0];
    }

    public WinInfo(boolean isSystem) {
        super(isSystem);
        this.TG = new View[0];
    }

    public WinInfo setTitle(String s) {
        super.setTitle(s);
        return this;
    }

    public WinInfo setMessage(String s) {
        super.setMessage(s);
        return this;
    }

    public WinInfo setMessageData(Object data) {
        super.setMessageData(data);
        return this;
    }

    public void onClickBlank() {
        onBackPressed();
    }
}
