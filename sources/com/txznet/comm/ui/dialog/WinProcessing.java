package com.txznet.comm.ui.dialog;

/* compiled from: Proguard */
public abstract class WinProcessing extends WinProgress {
    public abstract void onCancelProcess();

    public WinProcessing(String txt) {
        super(txt);
    }

    public WinProcessing(String txt, boolean isSystem) {
        super(txt, isSystem);
    }

    public void onBackPressed() {
        onCancelProcess();
        dismiss();
    }
}
