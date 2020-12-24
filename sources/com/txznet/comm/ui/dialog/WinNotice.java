package com.txznet.comm.ui.dialog;

import android.content.DialogInterface;
import android.view.View;
import com.txznet.T.T;
import com.txznet.comm.Tr.Tr.Th;

/* compiled from: Proguard */
public abstract class WinNotice extends WinMessageBox {
    public abstract void onClickOk();

    public WinNotice() {
        Tr("确定");
        setCancelable(false);
        this.TG = new View[1];
        this.TG[0] = this.Te.T5;
    }

    public WinNotice(boolean isSystem) {
        super(isSystem);
        Tr("确定");
        setCancelable(false);
        this.TG = new View[1];
        this.TG[0] = this.Te.T5;
    }

    public WinNotice setTitle(String s) {
        super.setTitle(s);
        return this;
    }

    public WinNotice setMessage(String s) {
        super.setMessage(s);
        return this;
    }

    public WinNotice setMessageData(Object data) {
        super.setMessageData(data);
        return this;
    }

    public WinNotice setSureText(String s) {
        super.Tr(s);
        return this;
    }

    public void onClickMid() {
        dismiss();
        onClickOk();
    }

    public static void showNotice(final String hint, boolean tts, final boolean system, final Runnable r) {
        final int ttsId;
        if (tts) {
            ttsId = Th.T(hint);
        } else {
            ttsId = 0;
        }
        T.Ty(new Runnable() {
            public void run() {
                WinNotice win = new WinNotice(system) {
                    public /* bridge */ /* synthetic */ WinMessageBox setMessage(String str) {
                        return WinNotice.super.setMessage(str);
                    }

                    public /* bridge */ /* synthetic */ WinMessageBox setMessageData(Object obj) {
                        return WinNotice.super.setMessageData(obj);
                    }

                    public /* bridge */ /* synthetic */ WinMessageBox setTitle(String str) {
                        return WinNotice.super.setTitle(str);
                    }

                    public void onClickOk() {
                        if (r != null) {
                            r.run();
                        }
                    }
                };
                win.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialog) {
                        Th.T(ttsId);
                    }
                });
                win.setMessage(hint);
                win.show();
            }
        }, 0);
    }
}
