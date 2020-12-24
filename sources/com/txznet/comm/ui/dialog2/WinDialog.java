package com.txznet.comm.ui.dialog2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.txznet.comm.Tr.Tr.T;
import com.txznet.comm.Tr.Tr.TE;
import com.txznet.comm.Tr.Tr.TZ;
import com.txznet.comm.Tr.Tr.Th;
import com.txznet.comm.ui.IKeepClass;
import com.txznet.sdk.TXZWheelControlManager;
import com.txznet.sdk.tongting.IConstantData;
import com.txznet.txz.comm.R;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/* compiled from: Proguard */
public abstract class WinDialog implements IKeepClass {
    public static int Tr = -1;
    /* access modifiers changed from: private */

    /* renamed from: T  reason: collision with root package name */
    public int f590T;
    /* access modifiers changed from: private */
    public com.txznet.comm.Ty.Ty T5;
    /* access modifiers changed from: private */
    public long T6;
    protected View T9;
    private Runnable TB;
    private T.Tk TE;
    /* access modifiers changed from: private */
    public int TF;
    private Runnable TK;
    private boolean TO;
    protected boolean TZ;
    /* access modifiers changed from: private */
    public long Te;
    /* access modifiers changed from: private */
    public long Th;
    /* access modifiers changed from: private */
    public T Tj;
    TXZWheelControlManager.OnTXZWheelControlListener Tk;
    protected Tn Tn;
    /* access modifiers changed from: private */
    public List<T9> Tq;
    private long Tv;
    protected Ty Ty;

    /* compiled from: Proguard */
    public interface Tr {
        String T(WinDialog winDialog);

        void T(WinDialog winDialog, String str);
    }

    /* access modifiers changed from: protected */
    public abstract View TE();

    public abstract String getReportDialogId();

    public static int getSystemDialogWindowType() {
        return 2007;
    }

    public static void removeUiGroundCallback(Runnable r) {
        com.txznet.T.T.Tn(r);
    }

    public static void runOnUiGround(Runnable r, long delay) {
        com.txznet.T.T.Ty(r, delay);
    }

    /* compiled from: Proguard */
    protected class Tn extends Dialog {
        public Tn(Context context, int theme) {
            super(context, theme);
        }

        /* access modifiers changed from: protected */
        public void onCreate(Bundle savedInstanceState) {
            WinDialog.this.T(savedInstanceState);
        }

        public void T(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        /* access modifiers changed from: protected */
        public void onStart() {
            WinDialog.this.Ty();
        }

        public void T() {
            super.onStart();
        }

        /* access modifiers changed from: protected */
        public void onStop() {
            WinDialog.this.Tn();
        }

        public void Tr() {
            super.onStop();
        }

        public void onWindowFocusChanged(boolean newFocus) {
            WinDialog.this.Tr(newFocus);
            super.onWindowFocusChanged(newFocus);
        }

        public void onBackPressed() {
            WinDialog.this.doReport("back", new String[0]);
            WinDialog.this.onBackPressed();
        }

        public void Ty() {
            super.onBackPressed();
        }

        public boolean onKeyDown(int keyCode, KeyEvent event) {
            WinDialog.this.doReport("key", "" + event.getKeyCode());
            return WinDialog.this.onKeyDown(keyCode, event);
        }

        public boolean T(int keyCode, KeyEvent event) {
            return super.onKeyDown(keyCode, event);
        }

        public boolean onKeyUp(int keyCode, KeyEvent event) {
            return WinDialog.this.onKeyUp(keyCode, event);
        }

        public boolean Tr(int keyCode, KeyEvent event) {
            return super.onKeyUp(keyCode, event);
        }

        public boolean onKeyLongPress(int keyCode, KeyEvent event) {
            return WinDialog.this.onKeyLongPress(keyCode, event);
        }

        public boolean Ty(int keyCode, KeyEvent event) {
            return super.onKeyLongPress(keyCode, event);
        }

        public boolean onTouchEvent(MotionEvent event) {
            return WinDialog.this.onTouchEvent(event);
        }

        public boolean T(MotionEvent event) {
            return super.onTouchEvent(event);
        }

        public boolean dispatchKeyEvent(KeyEvent event) {
            return WinDialog.this.dispatchKeyEvent(event);
        }

        public boolean T(KeyEvent event) {
            return super.dispatchKeyEvent(event);
        }

        public boolean dispatchTouchEvent(MotionEvent ev) {
            return WinDialog.this.dispatchTouchEvent(ev);
        }

        public boolean Tr(MotionEvent ev) {
            return super.dispatchTouchEvent(ev);
        }
    }

    /* access modifiers changed from: protected */
    public void T(Bundle savedInstanceState) {
        this.Tn.T(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void Ty() {
        this.Tn.T();
    }

    /* access modifiers changed from: protected */
    public void Tn() {
        this.Tn.Tr();
    }

    /* access modifiers changed from: protected */
    public void onShow() {
    }

    /* access modifiers changed from: private */
    public void T() {
        Tr(-4);
        if (this.TZ) {
            this.TZ = false;
            TK();
        }
        cancelHintTts();
        cancelScreenLock();
        TXZWheelControlManager.getInstance().unregisterWheelControlListener(this.Tk);
        if (!this.TO) {
            com.txznet.comm.Tr.Tr.Tn.T("onDismiss: " + getDebugString());
            this.TO = true;
            this.T6 = 0;
            this.Th = 0;
            this.Te = 0;
            this.Tv = 0;
            this.Tq = null;
            this.TF = -1;
            onDismiss();
            TZ().sendBroadcast(new Intent("com.txznet.txz.action.FLOAT_WIN_DISMISS"));
        }
    }

    /* access modifiers changed from: protected */
    public void onDismiss() {
    }

    public void onBackPressed() {
        this.Tn.Ty();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.Tq != null) {
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    T(keyCode);
                    return true;
            }
        }
        return this.Tn.T(keyCode, event);
    }

    /* access modifiers changed from: private */
    public void T(int keyCode) {
        if (this.Tq == null || this.Tq.size() == 0) {
            com.txznet.comm.Tr.Tr.Tn.Tn("WinDialog mFocusViews empty");
            return;
        }
        switch (keyCode) {
            case 19:
            case 21:
                if (this.TF <= 0 || this.TF >= this.Tq.size()) {
                    this.TF = this.Tq.size() - 1;
                } else {
                    this.TF--;
                }
                Tr();
                return;
            case 20:
            case 22:
                if (this.TF < 0 || this.TF >= this.Tq.size() - 1) {
                    this.TF = 0;
                } else {
                    this.TF++;
                }
                Tr();
                return;
            case 23:
                if (this.TF >= 0 && this.TF < this.Tq.size()) {
                    this.Tq.get(this.TF).f616T.post(new com.txznet.txz.util.T.T<Integer>(Integer.valueOf(this.TF)) {
                        public void run() {
                            WinDialog.this.doReport("focus", "clickFocus", ((T9) WinDialog.this.Tq.get(((Integer) this.Ty).intValue())).Tr);
                            ((T9) WinDialog.this.Tq.get(((Integer) this.Ty).intValue())).f616T.performClick();
                        }
                    });
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void Tr() {
        if (this.Tq == null || this.Tq.size() == 0) {
            com.txznet.comm.Tr.Tr.Tn.Tn("WinDialog mFocusViews empty");
        } else if (this.TF > this.Tq.size()) {
            com.txznet.comm.Tr.Tr.Tn.Tn("WinDialog mFocusPosition out of range.mFocusPosition:" + this.TF + ",focus size:" + this.Tq.size());
        } else {
            com.txznet.comm.Tr.Tr.Tn.T("update focus :" + this.Tq.size());
            for (int i = 0; i < this.Tq.size(); i++) {
                this.Tq.get(i).f616T.post(new com.txznet.txz.util.T.T<Integer>(Integer.valueOf(i)) {
                    public void run() {
                        boolean z;
                        ((T9) WinDialog.this.Tq.get(((Integer) this.Ty).intValue())).f616T.setFocusable(WinDialog.this.TF == ((Integer) this.Ty).intValue());
                        View view = ((T9) WinDialog.this.Tq.get(((Integer) this.Ty).intValue())).f616T;
                        if (WinDialog.this.TF == ((Integer) this.Ty).intValue()) {
                            z = true;
                        } else {
                            z = false;
                        }
                        view.setFocusableInTouchMode(z);
                        if (((Integer) this.Ty).intValue() == WinDialog.this.TF) {
                            com.txznet.comm.Tr.Tr.Tn.T("update focus position:" + this.Ty + ",id:" + ((T9) WinDialog.this.Tq.get(((Integer) this.Ty).intValue())).Tr);
                            WinDialog.this.doReport("focus", "obtainFocus", ((T9) WinDialog.this.Tq.get(((Integer) this.Ty).intValue())).Tr);
                            ((T9) WinDialog.this.Tq.get(((Integer) this.Ty).intValue())).f616T.requestFocus();
                        }
                    }
                });
            }
        }
    }

    public boolean onWheelControlKeyEvent(int event) {
        return false;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return this.Tn.Tr(keyCode, event);
    }

    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return this.Tn.Ty(keyCode, event);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return this.Tn.T(event);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return this.Tn.T(event);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        return this.Tn.Tr(ev);
    }

    /* compiled from: Proguard */
    public static class Ty {
        boolean T5;
        String T6;
        WinDialog T9;
        boolean TE;
        int TF = 0;
        Integer TZ;
        Th.Tr Te;
        Boolean Th;
        boolean Tj;
        Object Tk;
        Context Tn;
        HashMap<String, Tr> Tq;
        Boolean Tv;

        public void T() {
            if (this.Tn == null) {
                this.Tn = com.txznet.comm.Tr.T.Ty();
                this.T5 = true;
            }
            if (this.Te == null) {
                this.Te = Th.Tr.PREEMPT_TYPE_NONE;
            }
            if (this.TZ == null) {
                if (com.txznet.comm.base.T.T().Ty() == null) {
                    this.TZ = 2007;
                }
                if (this.T5) {
                    this.TZ = Integer.valueOf(WinDialog.getSystemDialogWindowType());
                }
                if (WinDialog.Tr != -1) {
                    this.TZ = Integer.valueOf(WinDialog.Tr);
                }
            }
        }

        public Ty T(boolean flag) {
            this.T5 = flag;
            return this;
        }

        public Ty Tr(boolean flag) {
            this.Tv = Boolean.valueOf(flag);
            return this;
        }

        public Ty Ty(boolean flag) {
            this.Th = Boolean.valueOf(flag);
            return this;
        }

        public Ty TE(String tts) {
            this.T6 = tts;
            return this;
        }

        public Ty T(Th.Tr type) {
            this.Te = type;
            return this;
        }

        public Ty T(Tr callback, String... cmds) {
            if (this.Tq == null) {
                this.Tq = new HashMap<>();
            }
            for (String cmd : cmds) {
                this.Tq.put(cmd, callback);
            }
            return this;
        }
    }

    /* compiled from: Proguard */
    private static abstract class T implements Runnable {
        int Tr;
        int Ty;

        public T(int time) {
            this.Tr = time;
            this.Ty = time;
        }

        public int T() {
            return this.Ty;
        }
    }

    public WinDialog(Ty data) {
        this(data, true);
    }

    protected WinDialog(Ty data, boolean init) {
        this.f590T = 0;
        this.TB = new Runnable() {
            public void run() {
                if (WinDialog.this.T5 == null) {
                    com.txznet.comm.Tr.Tr.Tn.T("RequestScreenLock: " + WinDialog.this.getDebugString());
                    com.txznet.comm.Ty.Ty unused = WinDialog.this.T5 = new com.txznet.comm.Ty.Ty(WinDialog.this.Tn.getContext());
                }
            }
        };
        this.TK = new Runnable() {
            public void run() {
                if (WinDialog.this.T5 != null) {
                    com.txznet.comm.Tr.Tr.Tn.T("CancelScreenLock: " + WinDialog.this.getDebugString());
                    WinDialog.this.T5.Tr();
                    com.txznet.comm.Ty.Ty unused = WinDialog.this.T5 = null;
                }
            }
        };
        this.Tk = new TXZWheelControlManager.OnTXZWheelControlListener() {
            public void onKeyEvent(int eventId) {
                WinDialog.this.doReport("wheelControl", "onKeyEvent", "" + eventId);
                if (!WinDialog.this.onWheelControlKeyEvent(eventId)) {
                    switch (eventId) {
                        case 13:
                            WinDialog.this.onKeyDown(4, new KeyEvent(1, 4));
                            return;
                        case 100:
                        case 105:
                            WinDialog.this.T(21);
                            return;
                        case 101:
                        case 106:
                            WinDialog.this.T(22);
                            return;
                        case 102:
                            WinDialog.this.onKeyUp(23, new KeyEvent(1, 23));
                            return;
                        case 103:
                            WinDialog.this.T(20);
                            return;
                        case 104:
                            WinDialog.this.T(20);
                            return;
                        default:
                            return;
                    }
                }
            }
        };
        this.TZ = false;
        this.TO = true;
        this.Ty = data;
        data.T9 = this;
        if (init) {
            T9();
        }
    }

    /* access modifiers changed from: protected */
    public void T9() {
        this.Ty.T();
        T5();
        runOnUiGround(new Runnable() {
            public void run() {
                WinDialog.this.Tj();
            }
        }, 0);
    }

    /* access modifiers changed from: protected */
    public void Tk() {
    }

    /* access modifiers changed from: private */
    @SuppressLint({"InlinedApi"})
    public void Tj() {
        this.Tn = new Tn(this.Ty.Tn, this.Ty.TE ? R.style.TXZ_Dialog_Style_Full : R.style.TXZ_Dialog_Style);
        if (this.Ty.TZ != null) {
            this.Tn.getWindow().setType(this.Ty.TZ.intValue());
        }
        Boolean bCancelable = this.Ty.Tv;
        if (bCancelable != null) {
            com.txznet.comm.Tr.Tr.Tn.T("DialogBuildData mCancelable : " + bCancelable);
            this.Tn.setCancelable(bCancelable.booleanValue());
        }
        Boolean bCancelOutside = this.Ty.Th;
        if (bCancelOutside != null) {
            com.txznet.comm.Tr.Tr.Tn.T("DialogBuildData mCancelOutside : " + bCancelOutside);
            this.Tn.setCanceledOnTouchOutside(bCancelOutside.booleanValue());
        }
        this.Tn.getWindow().setLayout(-1, -1);
        this.Tn.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialog) {
                WinDialog.this.T();
            }
        });
        this.T9 = TE();
        this.Tn.setContentView(this.T9);
        TB();
        Tk();
    }

    /* access modifiers changed from: protected */
    public Context TZ() {
        return this.Ty.Tn;
    }

    public boolean isShowing() {
        return this.Tn != null && this.Tn.isShowing();
    }

    @Deprecated
    public void setCancelable(boolean flag) {
        this.Ty.Tv = Boolean.valueOf(flag);
        runOnUiGround(new Runnable() {
            public void run() {
                WinDialog.this.Tn.setCancelable(WinDialog.this.Ty.Tv.booleanValue());
            }
        }, 0);
    }

    @Deprecated
    public void setCanceledOnTouchOutside(boolean cancel) {
        this.Ty.Th = Boolean.valueOf(cancel);
        runOnUiGround(new Runnable() {
            public void run() {
                WinDialog.this.Tn.setCanceledOnTouchOutside(WinDialog.this.Ty.Th.booleanValue());
            }
        }, 0);
    }

    /* access modifiers changed from: private */
    @SuppressLint({"InlinedApi"})
    public void TB() {
        if (this.Ty.TE) {
            this.T9.setSystemUiVisibility(260);
            this.Tn.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                /* access modifiers changed from: private */
                public long Tr = 0;

                public void onSystemUiVisibilityChange(int visibility) {
                    com.txznet.comm.Tr.Tr.Tn.T("onSystemUiVisibilityChange:" + visibility);
                    if (WinDialog.this.Ty.TE && (visibility & 4) == 0) {
                        long t = SystemClock.elapsedRealtime();
                        if (t - this.Tr > 1000) {
                            this.Tr = t;
                            WinDialog.this.T9.setSystemUiVisibility(260);
                            return;
                        }
                        WinDialog.runOnUiGround(new Runnable() {
                            public void run() {
                                long unused = AnonymousClass18.this.Tr = SystemClock.elapsedRealtime();
                                if (WinDialog.this.Ty.TE) {
                                    WinDialog.this.T9.setSystemUiVisibility(260);
                                }
                            }
                        }, 500);
                    }
                }
            });
            return;
        }
        this.T9.setSystemUiVisibility(this.T9.getSystemUiVisibility() & -261);
        this.Tn.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener((View.OnSystemUiVisibilityChangeListener) null);
    }

    @Deprecated
    public void setIsFullSreenDialog(boolean isFullScreen) {
        com.txznet.comm.Tr.Tr.Tn.T("setIsFullScreenDialog:" + isFullScreen);
        this.Ty.TE = isFullScreen;
        runOnUiGround(new Runnable() {
            public void run() {
                WinDialog.this.TB();
            }
        }, 0);
    }

    @Deprecated
    public void updateDialogType(int type) {
        com.txznet.comm.Tr.Tr.Tn.T("updateDialogType type:" + type);
        this.Ty.TZ = Integer.valueOf(type);
        runOnUiGround(new Runnable() {
            public void run() {
                WinDialog.this.Tn.getWindow().setType(WinDialog.this.Ty.TZ.intValue());
            }
        }, 0);
    }

    /* access modifiers changed from: protected */
    public void T5() {
        if (this.Ty.Tq != null && this.Ty.Tq.size() > 0) {
            this.TE = new T.Tk() {
                public boolean needAsrState() {
                    return true;
                }

                public String getTaskId() {
                    return WinDialog.this.getDialogType() + "@" + WinDialog.this.hashCode();
                }

                public String needTts() {
                    return null;
                }

                public boolean onAsrResult(final String text) {
                    final Tr r = WinDialog.this.Ty.Tq.get(text);
                    if (r == null) {
                        return false;
                    }
                    WinDialog.runOnUiGround(new Runnable() {
                        public void run() {
                            WinDialog.this.doReport("speak", r.T(WinDialog.this), text);
                            r.T(WinDialog.this, text);
                        }
                    }, 0);
                    return true;
                }

                public String[] genKeywords() {
                    Set<String> kws = WinDialog.this.Ty.Tq.keySet();
                    return (String[]) kws.toArray(new String[kws.size()]);
                }
            };
        }
    }

    public Object getData() {
        return this.Ty.Tk;
    }

    public <T> T getData(Class<T> cls) {
        return this.Ty.Tk;
    }

    public void requestScreenLock() {
        removeUiGroundCallback(this.TB);
        removeUiGroundCallback(this.TK);
        runOnUiGround(this.TB, 0);
    }

    private void T(long delay) {
        removeUiGroundCallback(this.TB);
        removeUiGroundCallback(this.TK);
        runOnUiGround(this.TK, delay);
    }

    public void cancelScreenLock() {
        T(0);
    }

    public void cancelHintTts() {
        runOnUiGround(new Runnable() {
            public void run() {
                long unused = WinDialog.this.Th = WinDialog.this.T6 = SystemClock.elapsedRealtime();
                if (WinDialog.this.f590T != 0) {
                    Th.T(WinDialog.this.f590T);
                    int unused2 = WinDialog.this.f590T = 0;
                }
            }
        }, 0);
    }

    public void onCountDown(int time) {
    }

    /* access modifiers changed from: private */
    public void Tr(final int reason) {
        runOnUiGround(new Runnable() {
            public void run() {
                long unused = WinDialog.this.Te = 0;
                if (WinDialog.this.Tj != null) {
                    WinDialog.removeUiGroundCallback(WinDialog.this.Tj);
                    T unused2 = WinDialog.this.Tj = null;
                    WinDialog.this.onCountDown(reason);
                }
            }
        }, 0);
    }

    public void cancelCountDown() {
        Tr(-2);
    }

    public boolean clickView(int viewId, boolean fromVoice) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void T(TextView view, CharSequence text) {
        if (text != null) {
            view.setText(com.txznet.txz.util.Tr.T(text.toString()));
            view.setVisibility(0);
            return;
        }
        view.setText("");
        view.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void T(TextView view, String text, int time, Runnable end) {
        final CharSequence rawText = (view == null || text == null) ? null : view.getText();
        Tr(-5);
        final int i = time;
        final TextView textView = view;
        final String str = text;
        final Runnable runnable = end;
        runOnUiGround(new Runnable() {
            public void run() {
                long unused = WinDialog.this.Te = SystemClock.elapsedRealtime();
                T unused2 = WinDialog.this.Tj = new T(i) {
                    public void run() {
                        WinDialog.removeUiGroundCallback(this);
                        if (WinDialog.this.Tj == this) {
                            if (this.Tr <= 0) {
                                if (textView == null) {
                                    WinDialog.this.doReport("countdown", "unknow");
                                } else {
                                    WinDialog.this.doReport("countdown", WinDialog.this.getReportViewId(textView.getId()));
                                }
                                if (!(str == null || textView == null)) {
                                    WinDialog.this.T(textView, rawText);
                                }
                                WinDialog.this.onCountDown(this.Tr);
                                WinDialog.this.Tr(-1);
                                if (runnable != null) {
                                    runnable.run();
                                    return;
                                }
                                return;
                            }
                            if (!(str == null || textView == null)) {
                                int n = str.indexOf("%TIME%");
                                if (n >= 0) {
                                    WinDialog.this.T(textView, (CharSequence) str.substring(0, n) + this.Tr + str.substring("%TIME%".length() + n));
                                } else {
                                    WinDialog.this.T(textView, (CharSequence) str);
                                }
                            }
                            WinDialog.this.onCountDown(this.Tr);
                            this.Tr--;
                            WinDialog.runOnUiGround(this, 1000);
                        }
                    }
                };
                WinDialog.this.Tj.run();
            }
        }, 0);
    }

    /* access modifiers changed from: private */
    public void T(boolean imediately) {
        boolean preemptImediately = Th.Tr.PREEMPT_TYPE_IMMEADIATELY.equals(this.Ty.Te) || Th.Tr.PREEMPT_TYPE_FLUSH.equals(this.Ty.Te) || Th.Tr.PREEMPT_TYPE_IMMEADIATELY_WITHOUT_CANCLE.equals(this.Ty.Te);
        if (preemptImediately || imediately) {
            com.txznet.comm.Tr.Tr.T.Ty();
            TZ.T();
        }
        Th.T callback = new Th.T() {
            public void onBegin() {
                WinDialog.runOnUiGround(new Runnable() {
                    public void run() {
                        WinDialog.this.Tn.show();
                        if (!WinDialog.this.TZ) {
                            WinDialog.this.TZ = true;
                            WinDialog.this.TO();
                        }
                        com.txznet.comm.Tr.Tr.Tn.T("onBeginTts: " + WinDialog.this.getDebugString());
                        long unused = WinDialog.this.Th = SystemClock.elapsedRealtime();
                        WinDialog.this.Te();
                    }
                }, 0);
            }

            public void onEnd() {
                WinDialog.runOnUiGround(new Runnable() {
                    public void run() {
                        if (WinDialog.this.Ty.TF == 0) {
                            WinDialog.this.cancelScreenLock();
                        }
                        com.txznet.comm.Tr.Tr.Tn.T("onEndTts: " + WinDialog.this.getDebugString());
                        long unused = WinDialog.this.T6 = SystemClock.elapsedRealtime();
                        WinDialog.this.Tq();
                    }
                }, 0);
            }
        };
        Th.Tr preemptType = this.Ty.Te;
        if (imediately && !preemptImediately) {
            preemptType = Th.Tr.PREEMPT_TYPE_IMMEADIATELY;
        }
        if (this.Ty.T6 != null || (!imediately && !preemptImediately)) {
            String tts = this.Ty.T6 != null ? this.Ty.T6 : "";
            if (this.TE != null) {
                this.f590T = Th.T(tts, "$BEEP", preemptType, callback);
            } else {
                this.f590T = Th.T(tts, preemptType, callback);
            }
        } else if (this.TE != null) {
            this.f590T = Th.Tr("$BEEP", preemptType, callback);
        } else {
            callback.onBegin();
            callback.onEnd();
        }
    }

    public final void show() {
        runOnUiGround(new Runnable() {
            public void run() {
                WinDialog.this.T(false);
            }
        }, 0);
    }

    public final void showImediately() {
        runOnUiGround(new Runnable() {
            public void run() {
                WinDialog.this.T(true);
            }
        }, 0);
    }

    /* access modifiers changed from: protected */
    public final void Tv() {
        runOnUiGround(new Runnable() {
            public void run() {
                WinDialog.this.T();
                try {
                    Context context = WinDialog.this.Tn.getContext();
                    if (context instanceof Activity) {
                        Activity act = (Activity) context;
                        boolean isValidCtx = act.isFinishing();
                        if (Build.VERSION.SDK_INT >= 17) {
                            isValidCtx |= act.isDestroyed();
                        }
                        if (isValidCtx) {
                            WinDialog.this.Tn.dismiss();
                            return;
                        }
                        return;
                    }
                    WinDialog.this.Tn.dismiss();
                } catch (Exception e) {
                    com.txznet.comm.Tr.Tr.Tn.T("dismissInner error, msg=" + e.getMessage());
                }
            }
        }, 0);
    }

    public final void dismiss(String reason) {
        doReport("dismiss", reason);
        Tv();
    }

    /* compiled from: Proguard */
    public static class T9 {

        /* renamed from: T  reason: collision with root package name */
        public View f616T;
        public String Tr;

        public T9(View view, String id) {
            if (view == null || TextUtils.isEmpty(id)) {
                throw new RuntimeException("view or id can't be null!");
            }
            this.f616T = view;
            this.Tr = id;
        }
    }

    public void setFocusViews(List<T9> views) {
        setFocusViews(views, 0);
    }

    public void setFocusViews(List<T9> views, int curFocusPosition) {
        this.Tq = views;
        if (this.Tq == null || this.Tq.size() == 0) {
            this.TF = -1;
            return;
        }
        this.TF = curFocusPosition;
        Tr();
    }

    public boolean hasFocus() {
        return this.TZ;
    }

    /* access modifiers changed from: private */
    public void Tr(boolean newFocus) {
        com.txznet.comm.Tr.Tr.Tn.T(getDebugString() + " onWindowFocusChanged: from " + this.TZ + " to " + newFocus);
        if (this.TZ != newFocus) {
            this.TZ = newFocus;
            if (this.TZ) {
                TO();
            } else {
                TK();
            }
        }
    }

    private void TK() {
        cancelHintTts();
        if (this.TE != null) {
            com.txznet.comm.Tr.Tr.T.TZ(this.TE.getTaskId());
        }
        if (this.Ty.TF == -3) {
            cancelScreenLock();
        }
        com.txznet.comm.Tr.Tr.Tn.T("onLoseFocus: " + getDebugString());
        Th();
        if (this.Ty.Tj) {
            Tr(-3);
        }
    }

    /* access modifiers changed from: private */
    public void TO() {
        if (this.TE != null) {
            com.txznet.comm.Tr.Tr.T.T(this.TE);
        }
        TXZWheelControlManager.getInstance().registerWheelControlListener(this.Tk);
        if (this.TO) {
            com.txznet.comm.Tr.Tr.Tn.T("onShow: " + getDebugString());
            this.TO = false;
            if (!(this.Ty.TF == -4 || this.Ty.TF == -3)) {
                this.TB.run();
                if (this.Ty.TF > 0) {
                    T((long) this.Ty.TF);
                } else if (this.Ty.TF == -1) {
                    cancelScreenLock();
                }
            }
            this.Tv = SystemClock.elapsedRealtime();
            onShow();
            this.Tn.getContext().sendBroadcast(new Intent("com.txznet.txz.action.FLOAT_WIN_SHOW"));
        }
        if (this.Ty.TF == -3) {
            requestScreenLock();
        }
        com.txznet.comm.Tr.Tr.Tn.T("onGetFocus: " + getDebugString());
        T6();
    }

    /* access modifiers changed from: protected */
    public void Th() {
    }

    /* access modifiers changed from: protected */
    public void T6() {
    }

    /* access modifiers changed from: protected */
    public void Te() {
    }

    /* access modifiers changed from: protected */
    public void Tq() {
    }

    public String getDebugString() {
        return toString() + "[" + this.Ty.T6 + "]";
    }

    public String getDialogType() {
        Class cls = getClass();
        while (cls.isAnonymousClass()) {
            cls = cls.getSuperclass();
        }
        return cls.getSimpleName();
    }

    public String getReportViewId(int viewId) {
        return "unknow";
    }

    /* access modifiers changed from: protected */
    public com.txznet.comm.Ty.Tr TF() {
        T run;
        long t = SystemClock.elapsedRealtime();
        com.txznet.comm.Ty.Tr json = new com.txznet.comm.Ty.Tr();
        json.T(IConstantData.KEY_ID, (Object) getReportDialogId());
        if (this.Tv > 0) {
            json.T("showTime", (Object) Long.valueOf(t - this.Tv));
        }
        if (this.Ty.T6 != null) {
            json.T("tts", (Object) this.Ty.T6);
            if (this.T6 > 0) {
                json.T("ttsTime", (Object) Long.valueOf(this.T6 - t));
            } else if (this.Th > 0) {
                json.T("ttsTime", (Object) Long.valueOf(t - this.Th));
            }
        }
        if (this.Te > 0 && (run = this.Tj) != null) {
            json.T("countdown", (Object) Integer.valueOf(run.T()));
            json.T("countdownTime", (Object) Long.valueOf(t - this.Te));
        }
        return json;
    }

    public void doReport(String type, String... param) {
        com.txznet.comm.Ty.Tr json = TF();
        json.T(IConstantData.KEY_TYPE, (Object) type);
        json.T("param", (Object) param);
        TE.T(7, json.Ty());
    }
}
