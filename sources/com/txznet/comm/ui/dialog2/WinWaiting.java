package com.txznet.comm.ui.dialog2;

import com.txznet.comm.ui.dialog2.WinProgress;

/* compiled from: Proguard */
public abstract class WinWaiting extends WinProgress {
    public WinWaiting(String txt) {
        super((WinProgress.T) new WinProgress.T().T(txt).Tr(false).Ty(false));
    }

    public void onBackPressed() {
    }
}
