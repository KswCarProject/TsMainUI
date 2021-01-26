package com.txznet.comm.ui.dialog;

import android.view.View;
import com.txznet.comm.Tr.Tr.T;
import com.txznet.txz.util.T.T;

/* compiled from: Proguard */
public abstract class WinConfirmAsr extends WinMessageBox {

    /* renamed from: T  reason: collision with root package name */
    String f568T;
    boolean T9 = true;
    private Runnable TD;
    Runnable Tn;
    String[] Tr;
    String[] Ty;

    public abstract void onClickOk();

    public WinConfirmAsr() {
        TE();
    }

    public WinConfirmAsr(boolean isSystem) {
        super(isSystem);
        TE();
    }

    private void TE() {
        T("确定");
        Ty("取消");
        this.TG = new View[2];
        this.TG[0] = this.Te.TE;
        this.TG[1] = this.Te.Tv;
        requestScreenLock();
        this.TD = new T<WinConfirmAsr>(this) {
            public void run() {
                if (this.Ty != null) {
                    ((WinConfirmAsr) this.Ty).cancelScreenLock();
                }
            }
        };
    }

    public WinConfirmAsr setTitle(String s) {
        super.setTitle(s);
        return this;
    }

    public WinConfirmAsr setMessage(String s) {
        super.setMessage(s);
        return this;
    }

    public WinConfirmAsr setMessageData(Object data) {
        super.setMessageData(data);
        return this;
    }

    public WinConfirmAsr setCancelText(String s, String[] cmds) {
        this.Ty = cmds;
        super.Ty(s);
        return this;
    }

    public WinConfirmAsr setSureText(String s, String[] cmds) {
        this.Tr = cmds;
        super.T(s);
        return this;
    }

    public WinConfirmAsr setHintTts(String text) {
        this.f568T = text;
        return this;
    }

    public WinConfirmAsr setTtsEndRunnable(Runnable runnable) {
        this.Tn = runnable;
        return this;
    }

    public void onSpeakOk() {
        onClickLeft();
    }

    public void onSpeakCancel() {
        onClickRight();
    }

    public void onClickCancel() {
    }

    public void onClickLeft() {
        onClickOk();
        dismiss();
    }

    public void onClickRight() {
        onClickCancel();
        dismiss();
    }

    public void onClickBlank() {
        onClickRight();
    }

    public void onBackPressed() {
        onClickCancel();
        dismiss();
    }

    /* access modifiers changed from: protected */
    public void Tn() {
        onClickRight();
    }

    /* access modifiers changed from: protected */
    public void Ty() {
        com.txznet.comm.Tr.Tr.T.TZ(toString());
        super.Ty();
    }

    /* access modifiers changed from: protected */
    public void Tr() {
        com.txznet.comm.Tr.Tr.T.Ty();
        T.Tr mWakeupAsrCallback = null;
        if (!(this.Tr == null || this.Ty == null)) {
            mWakeupAsrCallback = new T.Tr(this.Tr, this.Ty) {
                public String getTaskId() {
                    return WinConfirmAsr.this.toString();
                }

                public boolean needAsrState() {
                    return true;
                }

                public void T() {
                    WinConfirmAsr.this.onSpeakOk();
                }

                public void Tr() {
                    WinConfirmAsr.this.onSpeakCancel();
                }

                public String needTts() {
                    if (!WinConfirmAsr.this.T9) {
                        return null;
                    }
                    WinConfirmAsr.this.T9 = false;
                    return WinConfirmAsr.this.f568T;
                }

                public void onTtsEnd() {
                    if (WinConfirmAsr.this.Tn != null) {
                        WinConfirmAsr.this.Tn.run();
                    }
                }

                public int getPriority() {
                    return 1;
                }
            };
        }
        if (mWakeupAsrCallback != null) {
            com.txznet.comm.Tr.Tr.T.T((T.Tk) mWakeupAsrCallback);
        }
        super.Tr();
    }

    public void show() {
        super.show();
        if (this.f568T != null && this.f568T.length() > 0) {
            long delay = (long) ((this.f568T.length() / 2) * 1000);
            if (delay < 10000) {
                delay = 10000;
            }
            com.txznet.T.T.T(this.TD, delay);
        }
    }
}
