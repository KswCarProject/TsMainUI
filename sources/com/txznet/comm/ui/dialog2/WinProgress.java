package com.txznet.comm.ui.dialog2;

import android.widget.ImageView;
import android.widget.TextView;
import com.txznet.comm.Tr.Tr.Th;
import com.txznet.comm.ui.dialog2.WinDialog;

/* compiled from: Proguard */
public abstract class WinProgress extends WinDialog {

    /* renamed from: T  reason: collision with root package name */
    protected TextView f642T;
    T T5;
    protected ImageView TE;

    /* compiled from: Proguard */
    public static class T extends WinDialog.Ty {

        /* renamed from: T  reason: collision with root package name */
        String f648T;
        int Tr;

        public void T() {
            if (this.Te == null) {
                T(Th.Tr.PREEMPT_TYPE_IMMEADIATELY);
            }
            if (this.f648T == null) {
                this.f648T = "正在处理中...";
            }
            super.T();
        }

        public T T(String text) {
            this.f648T = text;
            return this;
        }

        public T T(int id) {
            this.Tr = id;
            return this;
        }
    }

    public WinProgress() {
        this(new T());
    }

    public WinProgress(String text) {
        this(new T().T(text));
    }

    public WinProgress(String text, int drawableId) {
        this(new T().T(text).T(drawableId));
    }

    public WinProgress(String text, boolean isSystem) {
        this((T) new T().T(text).T(isSystem));
    }

    public WinProgress(String text, int drawableId, boolean isSystem) {
        this((T) new T().T(text).T(drawableId).T(isSystem));
    }

    public WinProgress(T data) {
        this(data, true);
    }

    protected WinProgress(T data, boolean init) {
        super(data, false);
        this.T5 = data;
        if (init) {
            T9();
        }
    }

    public void updateProgress(String txt) {
        this.T5.f648T = txt;
        runOnUiGround(new Runnable() {
            public void run() {
                WinProgress.this.f642T.setText(WinProgress.this.T5.f648T);
            }
        }, 0);
    }

    public void updateProgress(int drawableId) {
        this.T5.Tr = drawableId;
        runOnUiGround(new Runnable() {
            public void run() {
                WinProgress.this.TE.setImageResource(WinProgress.this.T5.Tr);
            }
        }, 0);
    }

    public void updateProgress(int drawableId, String txt) {
        this.T5.Tr = drawableId;
        this.T5.f648T = txt;
        runOnUiGround(new Runnable() {
            public void run() {
                WinProgress.this.TE.setImageResource(WinProgress.this.T5.Tr);
                WinProgress.this.f642T.setText(WinProgress.this.T5.f648T);
            }
        }, 0);
    }

    public void dismissCountDown(final String text, final int time, final Runnable end) {
        runOnUiGround(new Runnable() {
            public void run() {
                WinProgress.this.T(WinProgress.this.f642T, text, time, new Runnable() {
                    public void run() {
                        if (end != null) {
                            end.run();
                        }
                        WinProgress.this.Tv();
                    }
                });
            }
        }, 0);
    }

    public String getDebugString() {
        return toString() + "[" + this.T5.f648T + "]";
    }
}
