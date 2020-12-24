package com.txznet.comm.ui.dialog2;

import com.txznet.comm.ui.dialog2.WinMessageBox;

/* compiled from: Proguard */
public abstract class WinInfo extends WinMessageBox {

    /* compiled from: Proguard */
    public static class T extends WinMessageBox.Tr {
        public T(String msg) {
            super.TZ(msg);
        }

        public T(String title, String msg) {
            super.T5(title);
            super.TZ(msg);
        }
    }

    public WinInfo(String title, String msg) {
        this(new T(title, msg));
    }

    public WinInfo(boolean isSystem, String title, String msg) {
        this((T) new T(title, msg).T(isSystem));
    }

    public WinInfo(String msg) {
        this(new T(msg));
    }

    public WinInfo(boolean isSystem, String msg) {
        this((T) new T(msg).T(isSystem));
    }

    public WinInfo(T data) {
        this(data, true);
    }

    protected WinInfo(T data, boolean init) {
        super(data, false);
        if (init) {
            T9();
        }
    }

    public void onClickBlank() {
        onBackPressed();
    }
}
