package com.txznet.comm.ui.dialog2;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.txznet.comm.Ty.Tk;
import com.txznet.comm.ui.Tr;
import com.txznet.comm.ui.dialog2.WinDialog;
import com.txznet.txz.comm.R;

/* compiled from: Proguard */
public abstract class WinMessageBox extends WinDialog {

    /* renamed from: T  reason: collision with root package name */
    private static int f618T = 0;
    public static int T5 = 30;
    public static int T6 = 28;
    public static int TE = 36;
    public static int Te = 28;
    public static int Th = 28;
    public static int Tv = 32;
    private boolean TB;
    protected Tr TF;
    private Tr.T TK;
    View.OnClickListener Tj;
    protected T Tq;

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public View f635T;
        public Button T5;
        public ListView T9;
        public Button TE;
        public TextView TZ;
        public ScrollView Tk;
        public TextView Tn;
        public View Tr;
        public Button Tv;
        public TextView Ty;
    }

    /* access modifiers changed from: protected */
    public abstract void T();

    public static void setMessageDialogType(int type) {
        f618T = type;
        if (f618T == 1) {
            TE = 27;
            T5 = 22;
            Tv = 24;
            Th = 21;
            T6 = 21;
            Te = 21;
            return;
        }
        TE = 36;
        T5 = 30;
        Tv = 32;
        Th = 28;
        T6 = 28;
        Te = 28;
    }

    /* compiled from: Proguard */
    public static class Tr extends WinDialog.Ty {
        String TB;
        String TG;
        String TK;
        String TN;
        boolean TO;
        String Ts;

        public Tr T5(String text) {
            this.TB = text;
            return this;
        }

        public Tr TZ(String text) {
            this.TK = text;
            return this;
        }

        public Tr Tr(String text, boolean scroll) {
            this.TK = text;
            this.TO = scroll;
            return this;
        }

        public Tr Tv(String text) {
            this.TN = text;
            return this;
        }

        public Tr Th(String text) {
            this.Ts = text;
            return this;
        }

        public Tr T6(String text) {
            this.TG = text;
            return this;
        }
    }

    public WinMessageBox(Tr data) {
        this(data, true);
    }

    protected WinMessageBox(Tr data, boolean init) {
        super(data, false);
        this.Tj = new View.OnClickListener() {
            public void onClick(View v) {
                if (WinMessageBox.this.clickView(v.getId(), false)) {
                    WinMessageBox.this.doReport("click", WinMessageBox.this.getReportViewId(v.getId()));
                }
            }
        };
        this.TB = false;
        this.TK = new Tr.T() {
            public void T() {
                WinMessageBox.this.doReport("home", new String[0]);
                WinMessageBox.this.Tv();
            }
        };
        this.TF = data;
        if (init) {
            T9();
        }
    }

    /* access modifiers changed from: protected */
    public void Tj() {
        T(this.Tq.Ty, (CharSequence) this.TF.TB);
    }

    @Deprecated
    public void setMessageAllowScroll(boolean scroll) {
        if (this.TF.TO != scroll) {
            T(this.TF.TK, scroll);
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public WinMessageBox T(String msg, boolean scroll) {
        this.TF.Tr(msg, scroll);
        runOnUiGround(new Runnable() {
            public void run() {
                WinMessageBox.this.TB();
            }
        }, 0);
        return this;
    }

    /* access modifiers changed from: protected */
    public void TB() {
        if (this.TF.TO) {
            this.Tq.Tn.setText("");
            this.Tq.Tn.setVisibility(8);
            this.Tq.Tk.setVisibility(0);
            this.Tq.TZ.setVisibility(0);
            this.Tq.TZ.setText(com.txznet.txz.util.Tr.T(this.TF.TK));
            return;
        }
        this.Tq.TZ.setText("");
        this.Tq.TZ.setVisibility(8);
        this.Tq.Tk.setVisibility(8);
        this.Tq.Tn.setVisibility(0);
        this.Tq.Tn.setText(com.txznet.txz.util.Tr.T(this.TF.TK));
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public WinMessageBox T(String text) {
        this.TF.Tv(text);
        runOnUiGround(new Runnable() {
            public void run() {
                WinMessageBox.this.T((TextView) WinMessageBox.this.Tq.TE, (CharSequence) WinMessageBox.this.TF.TN);
            }
        }, 0);
        return this;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public WinMessageBox Tr(String text) {
        this.TF.Th(text);
        runOnUiGround(new Runnable() {
            public void run() {
                WinMessageBox.this.T((TextView) WinMessageBox.this.Tq.Tv, (CharSequence) WinMessageBox.this.TF.Ts);
            }
        }, 0);
        return this;
    }

    /* access modifiers changed from: protected */
    public void T(final String text, final int time) {
        runOnUiGround(new Runnable() {
            public void run() {
                WinMessageBox.this.T(WinMessageBox.this.Tq.TE, text, time, new Runnable() {
                    public void run() {
                        WinMessageBox.this.onClickLeft();
                    }
                });
            }
        }, 0);
    }

    /* access modifiers changed from: protected */
    public void Tr(final String text, final int time) {
        runOnUiGround(new Runnable() {
            public void run() {
                WinMessageBox.this.T(WinMessageBox.this.Tq.T5, text, time, new Runnable() {
                    public void run() {
                        WinMessageBox.this.onClickMid();
                    }
                });
            }
        }, 0);
    }

    /* access modifiers changed from: protected */
    public void Ty(final String text, final int time) {
        runOnUiGround(new Runnable() {
            public void run() {
                WinMessageBox.this.T(WinMessageBox.this.Tq.Tv, text, time, new Runnable() {
                    public void run() {
                        WinMessageBox.this.onClickRight();
                    }
                });
            }
        }, 0);
    }

    public void dismissTitleCountDown(final String text, final int time) {
        runOnUiGround(new Runnable() {
            public void run() {
                WinMessageBox.this.T(WinMessageBox.this.Tq.Ty, text, time, new Runnable() {
                    public void run() {
                        WinMessageBox.this.Tv();
                    }
                });
            }
        }, 0);
    }

    public void dismissMessageCountDown(final String text, final int time) {
        runOnUiGround(new Runnable() {
            public void run() {
                WinMessageBox.this.T(WinMessageBox.this.TF.TO ? WinMessageBox.this.Tq.TZ : WinMessageBox.this.Tq.Tn, text, time, new Runnable() {
                    public void run() {
                        WinMessageBox.this.Tv();
                    }
                });
            }
        }, 0);
    }

    public void onClickLeft() {
        Tv();
    }

    public void onClickMid() {
        Tv();
    }

    public void onClickRight() {
        Tv();
    }

    public void onClickBlank() {
        WinDialog.Ty oBuildData = this.Ty;
        if (oBuildData == null || (oBuildData.Tv.booleanValue() && oBuildData.Th.booleanValue())) {
            Tv();
        }
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"InflateParams"})
    public View TE() {
        int layoutId = R.layout.comm_win_messagebox;
        if (f618T == 1) {
            layoutId = R.layout.comm_win_messagebox_small;
        }
        View context = LayoutInflater.from(TZ()).inflate(layoutId, (ViewGroup) null);
        this.Tq = new T();
        this.Tq.f635T = context.findViewById(R.id.frmMessageBox_Blank);
        this.Tq.Tr = context.findViewById(R.id.llMessageBox_shadow);
        this.Tq.Ty = (TextView) context.findViewById(R.id.txtMessageBox_Title);
        this.Tq.Tn = (TextView) context.findViewById(R.id.txtMessageBox_Message);
        this.Tq.T9 = (ListView) context.findViewById(R.id.lvMessageBox_Message);
        this.Tq.Tk = (ScrollView) context.findViewById(R.id.slMessageBox_Scroll);
        this.Tq.TZ = (TextView) context.findViewById(R.id.txtMessageBox_Scroll_Message);
        this.Tq.TE = (Button) context.findViewById(R.id.btnMessageBox_Button1);
        this.Tq.T5 = (Button) context.findViewById(R.id.btnMessageBox_Button3);
        this.Tq.Tv = (Button) context.findViewById(R.id.btnMessageBox_Button2);
        Tr();
        return context;
    }

    public boolean clickView(int viewId, boolean fromVoice) {
        if (viewId == R.id.frmMessageBox_Blank) {
            onClickBlank();
        } else if (viewId == R.id.btnMessageBox_Button1) {
            onClickLeft();
        } else if (viewId == R.id.btnMessageBox_Button2) {
            onClickRight();
        } else if (viewId != R.id.btnMessageBox_Button3) {
            return super.clickView(viewId, fromVoice);
        } else {
            onClickMid();
        }
        return true;
    }

    private void Tr() {
        this.Tq.f635T.setOnClickListener(this.Tj);
        this.Tq.TE.setOnClickListener(this.Tj);
        this.Tq.T5.setOnClickListener(this.Tj);
        this.Tq.Tv.setOnClickListener(this.Tj);
        this.Tq.Tn.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                WinMessageBox.this.T(WinMessageBox.this.Tq.Tn);
                return true;
            }
        });
        TK();
        Tj();
        TB();
        T((TextView) this.Tq.TE, (CharSequence) this.TF.TN);
        T((TextView) this.Tq.T5, (CharSequence) this.TF.TG);
        T((TextView) this.Tq.Tv, (CharSequence) this.TF.Ts);
        TO();
    }

    private void TK() {
        Tk.T(this.Tq.Ty, com.txznet.comm.ui.Tr.T.Tr().T(TE));
        Tk.T(this.Tq.Tn, com.txznet.comm.ui.Tr.T.Tr().T(T5));
        Tk.T(this.Tq.TZ, com.txznet.comm.ui.Tr.T.Tr().T(Tv));
        this.Tq.TE.setTextSize(0, com.txznet.comm.ui.Tr.T.Tr().T(Th));
        this.Tq.T5.setTextSize(0, com.txznet.comm.ui.Tr.T.Tr().T(T6));
        this.Tq.Tv.setTextSize(0, com.txznet.comm.ui.Tr.T.Tr().T(Te));
    }

    @SuppressLint({"NewApi"})
    private void TO() {
        int count = 0;
        if (this.Tq.TE.getVisibility() == 0) {
            count = 0 + 1;
        }
        if (this.Tq.T5.getVisibility() == 0) {
            count++;
        }
        if (this.Tq.Tv.getVisibility() == 0) {
            count++;
        }
        if (count == 1) {
            if (this.Tq.TE.getVisibility() == 0) {
                this.Tq.TE.setBackground(com.txznet.comm.Tr.T.Tr().getResources().getDrawable(R.drawable.comm_win_messagebox_btn_bg_single));
            }
            if (this.Tq.T5.getVisibility() == 0) {
                this.Tq.T5.setBackground(com.txznet.comm.Tr.T.Tr().getResources().getDrawable(R.drawable.comm_win_messagebox_btn_bg_single));
            }
            if (this.Tq.Tv.getVisibility() == 0) {
                this.Tq.Tv.setBackground(com.txznet.comm.Tr.T.Tr().getResources().getDrawable(R.drawable.comm_win_messagebox_btn_bg_single));
                return;
            }
            return;
        }
        this.Tq.TE.setBackground(com.txznet.comm.Tr.T.Tr().getResources().getDrawable(R.drawable.comm_win_messagebox_btn_bg_left));
        this.Tq.T5.setBackground(com.txznet.comm.Tr.T.Tr().getResources().getDrawable(R.drawable.comm_win_messagebox_btn_bg_middle));
        this.Tq.Tv.setBackground(com.txznet.comm.Tr.T.Tr().getResources().getDrawable(R.drawable.comm_win_messagebox_btn_bg_right));
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void T(TextView textView) {
        boolean shouldVisible;
        if (Build.VERSION.SDK_INT < 16) {
            shouldVisible = true;
        } else if (textView.getHeight() == textView.getMaxHeight()) {
            shouldVisible = false;
            int postTextSize = ((int) ((textView.getTextSize() / TZ().getResources().getDisplayMetrics().scaledDensity) + 0.5f)) - 1;
            if (postTextSize < 1) {
                postTextSize = 1;
            }
            textView.setTextSize(2, (float) postTextSize);
        } else {
            shouldVisible = true;
        }
        textView.setVisibility(shouldVisible ? 0 : 4);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    public void onDismiss() {
        super.onDismiss();
        if (this.TB) {
            this.TB = false;
            try {
                com.txznet.comm.ui.T.T().unregisterObserver(this.TK);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void Tk() {
        super.Tk();
        T();
    }

    public void onShow() {
        if (!this.TB) {
            this.TB = true;
            try {
                com.txznet.comm.ui.T.T().registerObserver(this.TK);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getDebugString() {
        return toString() + "[" + this.TF.TK + "]";
    }

    public String getReportViewId(int viewId) {
        if (viewId == R.id.frmMessageBox_Blank) {
            return "blank";
        }
        if (viewId == R.id.btnMessageBox_Button1) {
            return "left";
        }
        if (viewId == R.id.btnMessageBox_Button2) {
            return "right";
        }
        if (viewId == R.id.btnMessageBox_Button3) {
            return "mid";
        }
        return super.getReportViewId(viewId);
    }
}
