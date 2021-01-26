package com.txznet.comm.ui.dialog;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.Ty.Tk;
import com.txznet.comm.ui.Tr;
import com.txznet.sdk.TXZResourceManager;
import com.txznet.sdk.TXZWheelControlManager;
import com.txznet.txz.comm.R;

/* compiled from: Proguard */
public class WinMessageBox extends WinDialog implements View.OnClickListener {

    /* renamed from: T  reason: collision with root package name */
    private static int f572T = 0;
    public static int TB = 32;
    public static int TF = 36;
    public static int TK = 28;
    public static int TN = 28;
    public static int TO = 28;
    public static int Tj = 30;
    protected View[] TG;
    protected T Te;
    private Tr.T Tn = new Tr.T() {
        public void T() {
            WinMessageBox.this.dismiss();
        }
    };
    protected boolean Tq = false;
    /* access modifiers changed from: private */
    public boolean Tr;
    Object Ts;
    TXZWheelControlManager.OnTXZWheelControlListener Tt = new TXZWheelControlManager.OnTXZWheelControlListener() {
        public void onKeyEvent(int eventId) {
            switch (eventId) {
                case 13:
                    WinMessageBox.this.onKeyUp(4, new KeyEvent(1, 4));
                    return;
                case 100:
                case 105:
                    WinMessageBox.this.onKeyUp(21, new KeyEvent(1, 21));
                    return;
                case 101:
                case 106:
                    WinMessageBox.this.onKeyUp(22, new KeyEvent(1, 22));
                    return;
                case 102:
                    WinMessageBox.this.onKeyUp(23, new KeyEvent(1, 23));
                    return;
                default:
                    return;
            }
        }
    };
    protected int Tu = -1;
    private boolean Ty = false;

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public View f581T;
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

    public WinMessageBox() {
    }

    public WinMessageBox(boolean isSystem) {
        super(isSystem);
    }

    public static void setMessageDialogType(int type) {
        f572T = type;
        if (f572T == 1) {
            TF = 27;
            Tj = 22;
            TB = 24;
            TK = 21;
            TO = 21;
            TN = 21;
            return;
        }
        TF = 36;
        Tj = 30;
        TB = 32;
        TK = 28;
        TO = 28;
        TN = 28;
    }

    public void setTextScroll(boolean scroll) {
        if (this.Tq != scroll) {
            String curMsg = this.Tq ? this.Te.TZ.getText() + TXZResourceManager.STYLE_DEFAULT : this.Te.Tn.getText() + TXZResourceManager.STYLE_DEFAULT;
            this.Tq = scroll;
            if (!TextUtils.isEmpty(curMsg)) {
                setMessage(curMsg);
            }
        }
    }

    /* access modifiers changed from: protected */
    public WinMessageBox setTitle(String s) {
        this.Te.Ty.setVisibility(0);
        this.Te.Ty.setText(com.txznet.txz.util.Tr.T(s));
        return this;
    }

    /* access modifiers changed from: protected */
    public WinMessageBox setMessage(String s) {
        if (this.Tq) {
            this.Te.Tn.setText(TXZResourceManager.STYLE_DEFAULT);
            this.Te.Tn.setVisibility(8);
            this.Te.Tk.setVisibility(0);
            this.Te.TZ.setVisibility(0);
            this.Te.TZ.setText(com.txznet.txz.util.Tr.T(s));
        } else {
            this.Te.TZ.setText(TXZResourceManager.STYLE_DEFAULT);
            this.Te.TZ.setVisibility(8);
            this.Te.Tk.setVisibility(8);
            this.Te.Tn.setVisibility(0);
            this.Te.Tn.setText(com.txznet.txz.util.Tr.T(s));
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public WinMessageBox T(String s) {
        T(this.Te.TE, s);
        return this;
    }

    /* access modifiers changed from: protected */
    public WinMessageBox Tr(String s) {
        T(this.Te.T5, s);
        return this;
    }

    /* access modifiers changed from: protected */
    public WinMessageBox Ty(String s) {
        T(this.Te.Tv, s);
        return this;
    }

    /* access modifiers changed from: protected */
    public void T(Button bt, String s) {
        if (s == null || s.length() <= 0) {
            bt.setText(TXZResourceManager.STYLE_DEFAULT);
            bt.setVisibility(8);
        } else {
            bt.setVisibility(0);
            bt.setText(com.txznet.txz.util.Tr.T(s));
        }
        T9();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"NewApi"})
    public void T9() {
        int count = 0;
        if (this.Te.TE.getVisibility() == 0) {
            count = 0 + 1;
        }
        if (this.Te.T5.getVisibility() == 0) {
            count++;
        }
        if (this.Te.Tv.getVisibility() == 0) {
            count++;
        }
        if (count == 1) {
            if (this.Te.TE.getVisibility() == 0) {
                this.Te.TE.setBackground(com.txznet.comm.Tr.T.Tr().getResources().getDrawable(R.drawable.comm_win_messagebox_btn_bg_single));
            }
            if (this.Te.T5.getVisibility() == 0) {
                this.Te.T5.setBackground(com.txznet.comm.Tr.T.Tr().getResources().getDrawable(R.drawable.comm_win_messagebox_btn_bg_single));
            }
            if (this.Te.Tv.getVisibility() == 0) {
                this.Te.Tv.setBackground(com.txznet.comm.Tr.T.Tr().getResources().getDrawable(R.drawable.comm_win_messagebox_btn_bg_single));
                return;
            }
            return;
        }
        this.Te.TE.setBackground(com.txznet.comm.Tr.T.Tr().getResources().getDrawable(R.drawable.comm_win_messagebox_btn_bg_left));
        this.Te.T5.setBackground(com.txznet.comm.Tr.T.Tr().getResources().getDrawable(R.drawable.comm_win_messagebox_btn_bg_middle));
        this.Te.Tv.setBackground(com.txznet.comm.Tr.T.Tr().getResources().getDrawable(R.drawable.comm_win_messagebox_btn_bg_right));
    }

    public void onClickLeft() {
    }

    public void onClickMid() {
    }

    public void onClickRight() {
    }

    public void onClickBlank() {
    }

    /* access modifiers changed from: protected */
    public View T() {
        int layoutId = R.layout.comm_win_messagebox;
        if (f572T == 1) {
            layoutId = R.layout.comm_win_messagebox_small;
        }
        View context = LayoutInflater.from(getContext()).inflate(layoutId, (ViewGroup) null);
        this.Te = new T();
        this.Te.f581T = context.findViewById(R.id.frmMessageBox_Blank);
        this.Te.Tr = context.findViewById(R.id.llMessageBox_shadow);
        this.Te.Ty = (TextView) context.findViewById(R.id.txtMessageBox_Title);
        this.Te.Tn = (TextView) context.findViewById(R.id.txtMessageBox_Message);
        this.Te.T9 = (ListView) context.findViewById(R.id.lvMessageBox_Message);
        this.Te.Tk = (ScrollView) context.findViewById(R.id.slMessageBox_Scroll);
        this.Te.TZ = (TextView) context.findViewById(R.id.txtMessageBox_Scroll_Message);
        this.Te.TE = (Button) context.findViewById(R.id.btnMessageBox_Button1);
        this.Te.T5 = (Button) context.findViewById(R.id.btnMessageBox_Button3);
        this.Te.Tv = (Button) context.findViewById(R.id.btnMessageBox_Button2);
        TE();
        return context;
    }

    private void TE() {
        Tk.T(this.Te.Ty, com.txznet.comm.ui.Tr.T.Tr().T(TF));
        Tk.T(this.Te.Tn, com.txznet.comm.ui.Tr.T.Tr().T(Tj));
        Tk.T(this.Te.TZ, com.txznet.comm.ui.Tr.T.Tr().T(TB));
        this.Te.TE.setTextSize(0, com.txznet.comm.ui.Tr.T.Tr().T(TK));
        this.Te.T5.setTextSize(0, com.txznet.comm.ui.Tr.T.Tr().T(TO));
        this.Te.Tv.setTextSize(0, com.txznet.comm.ui.Tr.T.Tr().T(TN));
        this.Te.TE.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!WinMessageBox.this.Tr) {
                    WinMessageBox.this.Te.Tr.setBackgroundColor(Color.parseColor("#444a51"));
                    boolean unused = WinMessageBox.this.Tr = true;
                    return;
                }
                WinMessageBox.this.Te.Tr.setBackgroundColor(Color.parseColor("#444a50"));
                boolean unused2 = WinMessageBox.this.Tr = false;
            }
        });
        this.Te.T5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!WinMessageBox.this.Tr) {
                    WinMessageBox.this.Te.Tr.setBackgroundColor(Color.parseColor("#444a51"));
                    boolean unused = WinMessageBox.this.Tr = true;
                    return;
                }
                WinMessageBox.this.Te.Tr.setBackgroundColor(Color.parseColor("#444a50"));
                boolean unused2 = WinMessageBox.this.Tr = false;
            }
        });
        this.Te.Tv.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!WinMessageBox.this.Tr) {
                    WinMessageBox.this.Te.Tr.setBackgroundColor(Color.parseColor("#444a51"));
                    boolean unused = WinMessageBox.this.Tr = true;
                    return;
                }
                WinMessageBox.this.Te.Tr.setBackgroundColor(Color.parseColor("#444a50"));
                boolean unused2 = WinMessageBox.this.Tr = false;
            }
        });
        this.Te.f581T.setOnClickListener(this);
        this.Te.TE.setOnClickListener(this);
        this.Te.T5.setOnClickListener(this);
        this.Te.Tv.setOnClickListener(this);
        this.Te.Tn.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                WinMessageBox.this.T(WinMessageBox.this.Te.Tn);
                return true;
            }
        });
    }

    public void onClick(View v) {
        if (v.getId() == R.id.frmMessageBox_Blank) {
            onClickBlank();
        } else if (v.getId() == R.id.btnMessageBox_Button1) {
            onClickLeft();
        } else if (v.getId() == R.id.btnMessageBox_Button2) {
            onClickRight();
        } else if (v.getId() == R.id.btnMessageBox_Button3) {
            onClickMid();
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"NewApi"})
    public void T(TextView textView) {
        boolean shouldVisible;
        if (Build.VERSION.SDK_INT < 16) {
            shouldVisible = true;
        } else if (textView.getHeight() == textView.getMaxHeight()) {
            shouldVisible = false;
            int postTextSize = ((int) ((textView.getTextSize() / getContext().getResources().getDisplayMetrics().scaledDensity) + 0.5f)) - 1;
            if (postTextSize < 1) {
                postTextSize = 1;
            }
            textView.setTextSize(2, (float) postTextSize);
        } else {
            shouldVisible = true;
        }
        textView.setVisibility(shouldVisible ? 0 : 4);
    }

    /* access modifiers changed from: protected */
    public WinMessageBox setMessageData(Object data) {
        this.Ts = data;
        return this;
    }

    public Object getMessageData() {
        return this.Ts;
    }

    public <T> T getMessageData(Class<T> cls) {
        return this.Ts;
    }

    /* access modifiers changed from: protected */
    public void Tk() {
        if (this.TG == null || this.TG.length == 0) {
            Tn.Tn("WinDialog mFocusViews empty");
        } else if (this.Tu > this.TG.length) {
            Tn.Tn("WinDialog mFocusPosition out of range.mFocusPosition:" + this.Tu + ",focus size:" + this.TG.length);
        } else {
            Tn.T("update focus :" + this.TG.length);
            for (int i = 0; i < this.TG.length; i++) {
                this.TG[i].post(new com.txznet.txz.util.T.T<Integer>(Integer.valueOf(i)) {
                    public void run() {
                        boolean z = true;
                        WinMessageBox.this.TG[((Integer) this.Ty).intValue()].setFocusable(WinMessageBox.this.Tu == ((Integer) this.Ty).intValue());
                        View view = WinMessageBox.this.TG[((Integer) this.Ty).intValue()];
                        if (WinMessageBox.this.Tu != ((Integer) this.Ty).intValue()) {
                            z = false;
                        }
                        view.setFocusableInTouchMode(z);
                        if (((Integer) this.Ty).intValue() == WinMessageBox.this.Tu) {
                            WinMessageBox.this.TG[((Integer) this.Ty).intValue()].requestFocus();
                        }
                    }
                });
            }
        }
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 4:
                onBackPressed();
                return true;
            case 21:
                if (this.Tu <= 0 || this.Tu >= this.TG.length) {
                    this.Tu = this.TG.length - 1;
                } else {
                    this.Tu--;
                }
                Tk();
                return true;
            case 22:
                if (this.Tu < 0 || this.Tu >= this.TG.length - 1) {
                    this.Tu = 0;
                } else {
                    this.Tu++;
                }
                Tk();
                return true;
            case 23:
            case 66:
                if (this.Tu < 0 || this.Tu >= this.TG.length) {
                    return true;
                }
                this.TG[this.Tu].post(new com.txznet.txz.util.T.T<Integer>(Integer.valueOf(this.Tu)) {
                    public void run() {
                        WinMessageBox.this.TG[WinMessageBox.this.Tu].performClick();
                    }
                });
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    public void dismiss() {
        super.dismiss();
        if (this.Ty) {
            this.Ty = false;
            try {
                com.txznet.comm.ui.T.T().unregisterObserver(this.Tn);
                TXZWheelControlManager.getInstance().unregisterWheelControlListener(this.Tt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void show() {
        super.show();
        if (!this.Ty) {
            this.Ty = true;
            try {
                com.txznet.comm.ui.T.T().registerObserver(this.Tn);
                TXZWheelControlManager.getInstance().registerWheelControlListener(this.Tt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        TZ();
        Tk();
    }

    /* access modifiers changed from: protected */
    public void TZ() {
        this.Tu = -1;
    }
}
