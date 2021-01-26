package com.txznet.comm.ui.dialog2;

import com.txznet.comm.ui.dialog2.WinDialog;
import com.txznet.comm.ui.dialog2.WinMessageBox;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Proguard */
public abstract class WinNotice extends WinMessageBox {
    public abstract void onClickOk();

    /* compiled from: Proguard */
    public static class T extends WinMessageBox.Tr {

        /* renamed from: T  reason: collision with root package name */
        String[] f640T;

        public void T() {
            if (this.TG == null) {
                Ty("确定");
            }
            Tr(false);
            Ty(false);
            super.T();
        }

        /* renamed from: T */
        public T TZ(String text) {
            return Tr(text, true);
        }

        /* renamed from: T */
        public T Tr(String text, boolean tts) {
            super.TZ(text);
            if (this.T6 == null && tts) {
                super.TE(text);
            }
            return this;
        }

        /* renamed from: Tr */
        public T TE(String tts) {
            super.TE(tts);
            if (this.TK == null) {
                super.TZ(tts);
            }
            return this;
        }

        public T Ty(String text) {
            return T(text, new String[]{text});
        }

        public T T(String text, String[] cmds) {
            super.T6(text);
            this.f640T = cmds;
            if (this.f640T != null) {
                T(new WinDialog.Tr() {
                    public void T(WinDialog win, String cmd) {
                        ((WinNotice) win).onSpeakOk();
                        win.Tv();
                    }

                    public String T(WinDialog win) {
                        return "ok";
                    }
                }, this.f640T);
            }
            return this;
        }
    }

    public WinNotice(boolean isSystem, String msg) {
        super((T) new T().TZ(msg).T(isSystem));
    }

    public WinNotice(String msg) {
        super(new T().TZ(msg));
    }

    public WinNotice(String title, String msg) {
        super((T) new T().TZ(msg).T5(title));
    }

    public WinNotice(boolean isSystem, String title, String msg) {
        super((T) new T().TZ(msg).T5(title).T(isSystem));
    }

    public WinNotice(T data) {
        this(data, true);
    }

    protected WinNotice(T data, boolean init) {
        super(data, false);
        if (init) {
            T9();
        }
    }

    /* access modifiers changed from: protected */
    public void T() {
        List<WinDialog.T9> focusViews = new ArrayList<>();
        focusViews.add(new WinDialog.T9(this.Tq.T5, "BtnOk"));
        setFocusViews(focusViews, -1);
    }

    public void onSpeakOk() {
        onClickOk();
    }

    public void onClickMid() {
        onClickOk();
        Tv();
    }

    public void clickOkCountDown(int time) {
        super.Tr(this.TF.TG + " (%TIME%)", time);
    }

    public void clickOkCountDown(String text, int time) {
        super.Tr(text, time);
    }
}
