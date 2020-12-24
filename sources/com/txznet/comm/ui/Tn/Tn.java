package com.txznet.comm.ui.Tn;

import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import com.txznet.comm.ui.T5.Tr.T;
import com.txznet.comm.ui.T5.Tr.T0;
import com.txznet.comm.ui.T5.Tr.T5;
import com.txznet.comm.ui.T5.Tr.T6;
import com.txznet.comm.ui.T5.Tr.T9;
import com.txznet.comm.ui.T5.Tr.TA;
import com.txznet.comm.ui.T5.Tr.TB;
import com.txznet.comm.ui.T5.Tr.TD;
import com.txznet.comm.ui.T5.Tr.TE;
import com.txznet.comm.ui.T5.Tr.TF;
import com.txznet.comm.ui.T5.Tr.TG;
import com.txznet.comm.ui.T5.Tr.TI;
import com.txznet.comm.ui.T5.Tr.TM;
import com.txznet.comm.ui.T5.Tr.TO;
import com.txznet.comm.ui.T5.Tr.TP;
import com.txznet.comm.ui.T5.Tr.TZ;
import com.txznet.comm.ui.T5.Tr.Th;
import com.txznet.comm.ui.T5.Tr.Tj;
import com.txznet.comm.ui.T5.Tr.Tq;
import com.txznet.comm.ui.T5.Tr.Tr;
import com.txznet.comm.ui.T5.Tr.Ts;
import com.txznet.comm.ui.T5.Tr.Tt;
import com.txznet.comm.ui.T5.Tr.Tu;
import com.txznet.comm.ui.T5.Tr.Tv;
import com.txznet.comm.ui.T5.Tr.Tx;
import com.txznet.comm.ui.T5.Tr.Ty;
import com.txznet.comm.ui.T9.Tk;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: Proguard */
public class Tn {
    private static Tn Ty = new Tn();

    /* renamed from: T  reason: collision with root package name */
    public boolean f554T = false;
    private Ts T0;
    private Tu T5;
    private TI T6;
    private TB T9;
    private T9 TA;
    private Tr TB;
    private TO TD;
    private TD TE;
    private TP TF;
    private T5 TG;
    private T0 TI;
    private Tq TK;
    private String TL = "";
    private TA TM;
    private Tv TN;
    private T6 TO;
    private Tx TP;
    private TF TV;
    private TZ TX;
    private com.txznet.comm.ui.T5.Tr TZ;
    private Th Tb;
    private Ty Te;
    private Tt Tf;
    private T Th;
    private TM Tj;
    private boolean Tk = false;
    /* access modifiers changed from: private */
    public Ty Tn;
    private TE Tq;
    public com.txznet.comm.ui.T5.T Tr = new com.txznet.comm.ui.T5.T() {
        public void T(Animation animation, int state) {
            Tk.Tr().T(1, animation, Integer.valueOf(state));
        }
    };
    private com.txznet.comm.ui.T5.Tr.Tn Ts;
    private Tj Tt;
    private TG Tu;
    private com.txznet.comm.ui.T5.Tr.T.TE Tv;
    private LinkedList<com.txznet.comm.ui.T5.Tr> Tw = new LinkedList<>();
    private com.txznet.comm.ui.T5.Tr.Tk Tx;

    private Tn() {
    }

    public static Tn T() {
        return Ty;
    }

    public void Tr() {
        if (!TextUtils.isEmpty(this.TL)) {
            com.txznet.comm.Tr.Tr.Tn.T("[UI2.0]start init view:" + this.TL);
            this.TE = (TD) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "RecordView");
            this.TE.Tr();
            this.T5 = (Tu) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "PoiListView");
            this.T5.Tr();
            this.Tv = (com.txznet.comm.ui.T5.Tr.T.TE) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "DefaultPoiListView");
            if (this.Tv == null) {
                this.Tv = com.txznet.comm.ui.T5.Tr.T.TE.T9();
            }
            this.Tv.Tr();
            this.Th = (T) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "AudioListView");
            this.Th.Tr();
            this.T6 = (TI) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "WeChatListView");
            this.T6.Tr();
            this.Tq = (TE) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "ChatToSysView");
            this.Tq.Tr();
            this.Te = (Ty) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "ChatFromSysView");
            this.Te.Tr();
            this.TF = (TP) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "TtsListView");
            this.TF.Tr();
            this.Tj = (TM) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "SimListView");
            this.Tj.Tr();
            this.TB = (Tr) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "CallListView");
            this.TB.Tr();
            this.TK = (Tq) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "HelpListView");
            this.TK.Tr();
            this.TN = (Tv) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "CinemaListView");
            this.TN.Tr();
            this.Ts = (com.txznet.comm.ui.T5.Tr.Tn) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "ChatShockView");
            this.Ts.Tr();
            this.TG = (T5) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "ChatWeatherView");
            this.TG.Tr();
            this.Tu = (TG) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "NoTtsQrcodeView");
            this.Tu.Tr();
            this.Tt = (Tj) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "ListTitleView");
            this.Tt.Tr();
            try {
                this.TO = (T6) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "HelpDetailListView");
                this.TO.Tr();
            } catch (Exception e) {
                com.txznet.comm.Tr.Tr.Tn.Ty("[UI2.0] init help detail view error!");
            }
            try {
                this.TD = (TO) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "MapPoiListView");
                this.TD.Tr();
            } catch (Exception e2) {
                com.txznet.comm.Tr.Tr.Tn.Ty("[UI2.0] init IMapPoiListView error,use default!");
                this.TD = com.txznet.comm.ui.T5.Tr.T.Tk.T9();
                this.TD.Tr();
            }
            try {
                this.Tf = (Tt) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "QrCodeView");
                this.Tf.Tr();
            } catch (Exception e3) {
                com.txznet.comm.Tr.Tr.Tn.Ty("[UI2.0] init help QrCode view error!");
                this.Tf = com.txznet.comm.ui.T5.Tr.T.TF.T9();
                this.Tf.Tr();
            }
            try {
                this.TA = (T9) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "ChatSysHighlightView");
                this.TA.Tr();
            } catch (Exception e4) {
                com.txznet.comm.Tr.Tr.Tn.Ty("[UI2.0] init help ChatSysHighlightView view error!");
                this.TA = com.txznet.comm.ui.T5.Tr.T.T.T9();
                this.TA.Tr();
            }
            try {
                this.Tx = (com.txznet.comm.ui.T5.Tr.Tk) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "ChatSysInterruptView");
                this.Tx.Tr();
            } catch (Exception e5) {
                com.txznet.comm.Tr.Tr.Tn.Ty("[UI2.0] init help mChatSysInterruptView view error!");
                this.Tx = com.txznet.comm.ui.T5.Tr.T.Tr.T9();
                this.Tx.Tr();
            }
            try {
                this.T0 = (Ts) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "NavAppListView");
                this.T0.Tr();
            } catch (Exception e6) {
                this.T0 = com.txznet.comm.ui.T5.Tr.T.TZ.T9();
                this.T0.Tr();
            }
            try {
                this.TV = (TF) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "HelpTipsView");
                this.TV.Tr();
            } catch (Exception e7) {
                com.txznet.comm.Tr.Tr.Tn.Ty("[UI2.0] init helpTipsView error!");
                this.TV = com.txznet.comm.ui.T5.Tr.T.T9.T9();
                this.TV.Tr();
            }
            try {
                this.Tb = (Th) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "HelpDetailImageView");
                this.Tb.Tr();
            } catch (Exception e8) {
                com.txznet.comm.Tr.Tr.Tn.Ty("[UI2.0] init HelpDetailImageView error!");
                this.Tb = com.txznet.comm.ui.T5.Tr.T.Tn.T9();
                this.Tb.Tr();
            }
            try {
                this.TM = (TA) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "ReminderListView");
                this.TM.Tr();
            } catch (Exception e9) {
                com.txznet.comm.Tr.Tr.Tn.Ty("[UI2.0] init ReminderListView error!");
                this.TM = com.txznet.comm.ui.T5.Tr.T.T5.T9();
                this.TM.Tr();
            }
            try {
                this.TX = (TZ) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "ChatToSysPartView");
                this.TX.Tr();
            } catch (Exception e10) {
                com.txznet.comm.Tr.Tr.Tn.Ty("[UI2.0] init ChatToSysPartView error!");
                this.TX = com.txznet.comm.ui.T5.Tr.T.Ty.T9();
                this.TX.Tr();
            }
            try {
                this.TP = (Tx) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "SearchEditView");
                this.TP.Tr();
            } catch (Exception e11) {
                com.txznet.comm.Tr.Tr.Tn.Ty("[UI2.0] init SearchEditView error!");
                this.TP = com.txznet.comm.ui.T5.Tr.T.Tv.T();
                this.TP.Tr();
            }
            try {
                this.TI = (T0) com.txznet.comm.ui.Tk.T.Tr().T(this.TL + "SelectCityView");
                this.TI.Tr();
            } catch (Exception e12) {
                com.txznet.comm.Tr.Tr.Tn.Ty("[UI2.0] init SelectCityView error!");
                this.TI = com.txznet.comm.ui.T5.Tr.T.Th.T();
                this.TI.Tr();
            }
        }
    }

    public void Ty() {
        this.TL = com.txznet.comm.ui.TE.T.Tn();
        com.txznet.comm.Tr.Tr.Tn.T("init view prefix:" + this.TL);
        try {
            this.Tn = (Ty) com.txznet.comm.ui.Tk.T.Tr().T(com.txznet.comm.ui.TE.T.T9() + "WinLayout");
            this.Tn.T9();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.Tn == null) {
            if (com.txznet.comm.ui.TE.T.Tr() == 1) {
                com.txznet.comm.ui.Tn.T.Tk.Tk().T9();
                this.Tn = com.txznet.comm.ui.Tn.T.Tk.Tk();
            } else {
                com.txznet.comm.ui.Tn.T.Tn.Tk().T9();
                this.Tn = com.txznet.comm.ui.Tn.T.Tn.Tk();
            }
        }
        Tr();
        Tn();
        if (this.T9 == null) {
            this.T9 = this.T5;
        }
        this.f554T = true;
    }

    public void T(View view) {
        com.txznet.comm.Tr.Tr.Tn.T("[UI2.0] add third record view");
        if (view != null) {
            this.Tk = true;
            this.Tn.T(view);
        }
    }

    public void Tn() {
        if (this.TE == null) {
            com.txznet.comm.Tr.Tr.Tn.Tn("mRecordView is null");
        } else if (com.txznet.comm.ui.TE.T.Tr() == 1) {
            Tr(this.TE.T(new com.txznet.comm.ui.T5.T.TM(14)).Tr);
        } else if (com.txznet.comm.ui.TE.T.Tr() == 2) {
            Tr(this.TE.T(new com.txznet.comm.ui.T5.T.TM(15)).Tr);
        }
    }

    public void Tr(final View view) {
        if (this.Tn == null || this.Tk) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.Tn.T(view);
        } else {
            com.txznet.comm.ui.Tn.T((Runnable) new Runnable() {
                public void run() {
                    if (Tn.this.Tn != null) {
                        Tn.this.Tn.T(view);
                    }
                }
            }, 0);
        }
    }

    public void T(com.txznet.comm.ui.T5.Tr msgView) {
        this.TZ = msgView;
        if (msgView != null) {
            this.Tw.add(msgView);
        }
        com.txznet.comm.ui.Ty.Tr.T().T((com.txznet.comm.ui.T5.Ty) msgView);
    }

    public void T9() {
        if (this.Tw.size() != 0) {
            Iterator it = this.Tw.iterator();
            while (it.hasNext()) {
                ((com.txznet.comm.ui.T5.Tr) it.next()).Ty();
            }
            this.Tw.clear();
        }
    }

    public void Tk() {
        if (this.TE != null) {
            this.TE.Ty();
        }
    }

    public com.txznet.comm.ui.T5.Tr TZ() {
        return this.TZ;
    }

    public void T(TB listView) {
        this.T9 = listView;
    }

    public void T(int targetView, View view) {
        if (this.Tn != null) {
            this.Tn.T(targetView, view);
        }
    }

    public void TE() {
        if (this.Tn != null) {
            this.Tn.Ty();
        }
    }

    public void T(int progress, int selection) {
        if (this.T9 != null) {
            this.T9.T(progress, selection);
            com.txznet.comm.ui.Ty.Tr.T().T(selection, progress);
        }
    }

    public void T(int state) {
        Ty(state);
    }

    private void Ty(int state) {
        this.TE.T(state);
    }

    public void Tr(int volume) {
        if (this.TE != null) {
            this.TE.Tr(volume);
        }
    }

    public void T(boolean next) {
        if (this.T9 != null) {
            this.T9.T(next);
        }
    }

    public Ty T5() {
        if (this.Tn == null) {
            com.txznet.comm.ui.Tn.T.Tk.Tk().T9();
            this.Tn = com.txznet.comm.ui.Tn.T.Tk.Tk();
        }
        return this.Tn;
    }

    public Tu Tv() {
        return this.T5;
    }

    public T Th() {
        return this.Th;
    }

    public TI T6() {
        return this.T6;
    }

    public Ty Te() {
        return this.Te;
    }

    public TE Tq() {
        return this.Tq;
    }

    public TP TF() {
        return this.TF;
    }

    public TM Tj() {
        return this.Tj;
    }

    public Tr TB() {
        return this.TB;
    }

    public Tq TK() {
        return this.TK;
    }

    public T6 TO() {
        return this.TO;
    }

    public Tv TN() {
        return this.TN;
    }

    public com.txznet.comm.ui.T5.Tr.Tn Ts() {
        return this.Ts;
    }

    public T5 TG() {
        return this.TG;
    }

    public TG Tu() {
        return this.Tu;
    }

    public TO Tt() {
        return this.TD;
    }

    public Tt TD() {
        return this.Tf;
    }

    public T9 Tf() {
        return this.TA;
    }

    public com.txznet.comm.ui.T5.Tr.Tk TA() {
        return this.Tx;
    }

    public TF Tx() {
        return this.TV;
    }

    public Ts T0() {
        return this.T0;
    }

    public Th TV() {
        return this.Tb;
    }

    public TA Tb() {
        return this.TM;
    }

    public TZ TM() {
        return this.TX;
    }
}
