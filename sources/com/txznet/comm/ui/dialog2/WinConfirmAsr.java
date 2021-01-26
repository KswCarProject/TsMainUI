package com.txznet.comm.ui.dialog2;

import com.txznet.comm.ui.dialog2.WinConfirm;
import com.txznet.comm.ui.dialog2.WinDialog;

/* compiled from: Proguard */
public abstract class WinConfirmAsr extends WinConfirm {

    /* compiled from: Proguard */
    public static class T extends WinConfirm.T {

        /* renamed from: T  reason: collision with root package name */
        String[] f586T;
        String[] Tr;

        /* renamed from: Ty */
        public T TZ(String text) {
            super.TZ(text);
            if (this.T6 == null) {
                super.TE(text);
            }
            return this;
        }

        /* renamed from: T */
        public T Tr(String text, boolean tts) {
            super.TZ(text);
            if (this.T6 == null && tts) {
                super.TE(text);
            }
            return this;
        }

        /* renamed from: Tn */
        public T TE(String tts) {
            super.TE(tts);
            if (this.TK == null) {
                super.TZ(this.TK);
            }
            return this;
        }

        /* renamed from: T9 */
        public T T(String text) {
            T(text, this.Tr == null ? new String[]{text} : this.Tr);
            return this;
        }

        public T T(String text, String[] cmds) {
            super.T(text);
            this.Tr = cmds;
            if (this.Tr != null) {
                T(new WinDialog.Tr() {
                    public void T(WinDialog win, String cmd) {
                        ((WinConfirmAsr) win).onSpeakCancel();
                        win.Tv();
                    }

                    public String T(WinDialog win) {
                        return "cancel";
                    }
                }, this.Tr);
            }
            return this;
        }

        /* renamed from: Tk */
        public T Tr(String text) {
            Tr(text, this.f586T == null ? new String[]{text} : this.f586T);
            return this;
        }

        public T Tr(String text, String[] cmds) {
            super.Tr(text);
            this.f586T = cmds;
            if (this.f586T != null) {
                T(new WinDialog.Tr() {
                    public void T(WinDialog win, String cmd) {
                        ((WinConfirmAsr) win).onSpeakOk();
                        win.Tv();
                    }

                    public String T(WinDialog win) {
                        return "ok";
                    }
                }, this.f586T);
            }
            return this;
        }
    }

    public WinConfirmAsr(T data) {
        this(data, true);
    }

    protected WinConfirmAsr(T data, boolean init) {
        super(data, false);
        if (init) {
            T9();
        }
    }

    public void onSpeakOk() {
        onClickLeft();
    }

    public void onSpeakCancel() {
        onClickRight();
    }
}
