package com.ts.main.radio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsMode;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.dvdplayer.definition.MediaDef;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainVolume;
import com.ts.main.common.WinShow;
import com.ts.main.radio.RdsDlg;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.ValCal;
import com.txznet.sdk.TXZPoiSearchManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Radio;
import java.util.ArrayList;

public class RadioMainActivity extends TsMode implements View.OnClickListener, View.OnLongClickListener, UserCallBack {
    public static final boolean DEBUG_FLAG = false;
    public static final boolean DEBUG_RADIO = false;
    public static final String TAG = "RadioMainActivity";
    public static final String TAG_VER = "lq__20170601_0930";
    public static final int btn_af = 13;
    public static final int btn_am = 4;
    public static final int btn_ams = 6;
    public static final int btn_band = 8;
    public static final int btn_close = 22;
    public static final int btn_eq = 7;
    public static final int btn_fm = 3;
    public static final int btn_mute = 1;
    public static final int btn_next = 24;
    public static final int btn_pty = 15;
    public static final int btn_rds_set = 23;
    public static final int btn_scan = 5;
    public static final int btn_seek_dec = 9;
    public static final int btn_seek_inc = 10;
    public static final int btn_st = 2;
    public static final int btn_step_dec = 11;
    public static final int btn_step_inc = 12;
    public static final int btn_t1 = 16;
    public static final int btn_t2 = 17;
    public static final int btn_t3 = 18;
    public static final int btn_t4 = 19;
    public static final int btn_t5 = 20;
    public static final int btn_t6 = 21;
    public static final int btn_ta = 14;
    private static final int[] mBandNum = {R.drawable.radio_band_fm1, R.drawable.radio_band_fm2, R.drawable.radio_band_fm3, R.drawable.radio_band_ot, R.drawable.radio_band_am1, R.drawable.radio_band_am2};
    /* access modifiers changed from: private */
    public static final int[] mFreqNum = {R.drawable.radio_num00_up, R.drawable.radio_num01_up, R.drawable.radio_num02_up, R.drawable.radio_num03_up, R.drawable.radio_num04_up, R.drawable.radio_num05_up, R.drawable.radio_num06_up, R.drawable.radio_num07_up, R.drawable.radio_num08_up, R.drawable.radio_num09_up};
    /* access modifiers changed from: private */
    public static Handler mHandler = null;
    /* access modifiers changed from: private */
    public static int mRunCnt = 0;
    int RdsColor;
    int YoubiaoOffsetY;
    int adjTxtGravity;
    int amXStart;
    int amXdt;
    private boolean isBand = true;
    /* access modifiers changed from: private */
    public int mAdjStep;
    private ParamButton mBtnAf;
    private ParamButton mBtnAm;
    private ParamButton mBtnAms;
    private ParamButton mBtnBand;
    private ParamButton mBtnClose;
    private ParamButton mBtnEq;
    private ParamButton mBtnFm;
    private ParamButton[] mBtnFreq = new ParamButton[6];
    private ArrayList<ParamButton> mBtnList = new ArrayList<>();
    private ParamButton mBtnMute;
    private ParamButton mBtnNext;
    private ParamButton mBtnPty;
    private ParamButton mBtnRdsSet;
    private ParamButton mBtnScan;
    private ParamButton mBtnSeekDec;
    private ParamButton mBtnSeekInc;
    private ParamButton mBtnSt;
    private ParamButton mBtnTa;
    /* access modifiers changed from: private */
    public int mCurBand;
    private Evc mEvc = Evc.GetInstance();
    boolean mIsHaveMoveFreq;
    private ImageView mIvAf;
    private ImageView mIvBand;
    private ImageView mIvDW;
    private ImageView mIvEon;
    private CustomImgView mIvFreqAdj;
    private CustomImgView mIvMainFreq;
    private ImageView mIvStR;
    private ImageView mIvStS;
    private ImageView mIvTa;
    private ImageView mIvTp;
    private int mLastBand = -1;
    private RelativeLayout mLayout;
    private MainVolume mMainVolume = MainVolume.GetInstance();
    private boolean mStepMode = false;
    private long mStepTick;
    private String[] mStrCmd = {"", "btn_mute", "btn_st", "btn_fm", "btn_am", "btn_scan", "btn_ams", "btn_eq", "btn_band", "btn_seek_dec", "btn_seek_inc", "btn_step_dec", "btn_step_inc", "btn_af", "btn_ta", "btn_pty", "btn_t1", "btn_t2", "btn_t3", "btn_t4", "btn_t5", "btn_t6", "btn_close", "btn_rds_set", "btn_next"};
    private TextView mTvAdjMax;
    private TextView mTvAdjMin;
    private TextView mTvCurPty;
    private TextView mTvPs;
    int mXYoubiao;
    /* access modifiers changed from: private */
    public boolean mbAdjMove;
    private char[] mcFreq = new char[16];
    /* access modifiers changed from: private */
    public char[] mcPs = new char[16];
    private char[] mcPty = new char[64];
    private RdsDlg.onInputOK onPtyOK;
    int[] ptAdjTxtMax;
    int[] ptAdjTxtMin;
    int[] ptBtnAf;
    int[] ptBtnAms;
    int[] ptBtnBand;
    int[] ptBtnClose;
    int[] ptBtnEq;
    int[] ptBtnFreq1;
    int[] ptBtnFreq2;
    int[] ptBtnFreq3;
    int[] ptBtnFreq4;
    int[] ptBtnFreq5;
    int[] ptBtnFreq6;
    int[] ptBtnNext;
    int[] ptBtnPty;
    int[] ptBtnRds;
    int[] ptBtnSkDec;
    int[] ptBtnSkInc;
    int[] ptBtnSt;
    int[] ptBtnTa;
    int[] ptFreqAdj;
    int[] ptIvAf;
    int[] ptIvBand;
    int[] ptIvBndDw;
    int[] ptIvEon;
    int[] ptIvMF;
    int[] ptIvPs;
    int[] ptIvPty;
    int[] ptIvStR;
    int[] ptIvStS;
    int[] ptIvTa;
    int[] ptIvTp;
    int[] ptMFNum;
    private Runnable runnable;
    int wYoubiao;
    int xAdjMax;
    int xAdjMin;
    int xMFDot;
    int xMFDt;
    int yMFDot;
    int yMFDt;
    int yMFNum;

    public RadioMainActivity() {
        int[] iArr = new int[4];
        iArr[1] = 324;
        iArr[2] = 213;
        iArr[3] = 101;
        this.ptBtnAf = iArr;
        this.ptBtnTa = new int[]{213, KeyDef.RKEY_RADIO_2S, 213, 101};
        this.ptBtnPty = new int[]{427, KeyDef.RKEY_RADIO_2S, 213, 101};
        this.ptBtnRds = new int[]{CanCameraUI.BTN_LANDWIND_2D_FRONT, KeyDef.RKEY_RADIO_2S, 213, 101};
        int[] iArr2 = new int[4];
        iArr2[1] = 324;
        iArr2[2] = 213;
        iArr2[3] = 101;
        this.ptBtnSt = iArr2;
        this.ptBtnAms = new int[]{213, KeyDef.RKEY_RADIO_2S, 213, 101};
        this.ptBtnBand = new int[]{427, KeyDef.RKEY_RADIO_2S, 213, 101};
        this.ptBtnEq = new int[]{CanCameraUI.BTN_LANDWIND_2D_FRONT, KeyDef.RKEY_RADIO_2S, 213, 101};
        this.ptBtnClose = new int[]{854, KeyDef.RKEY_RADIO_2S, 213, 101};
        this.ptBtnNext = new int[]{1067, KeyDef.RKEY_RADIO_2S, 213, 101};
        this.ptBtnSkDec = new int[]{12, 124, 71, 113};
        this.ptBtnSkInc = new int[]{1198, 124, 71, 113};
        this.ptIvAf = new int[]{1091, 61, 51, 25};
        this.ptIvTa = new int[]{935, 61, 51, 25};
        this.ptIvTp = new int[]{1011, 61, 51, 25};
        this.ptIvEon = new int[]{1091, 28, 51, 25};
        this.ptIvStS = new int[]{935, 28, 51, 25};
        this.ptIvStR = new int[]{1011, 28, 51, 25};
        this.ptIvBand = new int[]{446, 56, 53, 24};
        this.ptIvBndDw = new int[]{KeyDef.SKEY_CHUP_4, 56, 53, 24};
        this.ptIvPty = new int[]{109, 43, Can.CAN_FORD_EDGE_XFY, 41};
        this.ptIvPs = new int[]{CanCameraUI.BTN_YG9_XBS_MODE2, 82, Can.CAN_FORD_EDGE_XFY, 41};
        this.RdsColor = -1;
        this.ptBtnFreq1 = new int[]{5, 256, 203, 60};
        this.ptBtnFreq2 = new int[]{218, 256, 203, 60};
        this.ptBtnFreq3 = new int[]{432, 256, 203, 60};
        this.ptBtnFreq4 = new int[]{CanCameraUI.BTN_LANDWIND_3D_FRONT, 256, 203, 60};
        this.ptBtnFreq5 = new int[]{859, 256, 203, 60};
        this.ptBtnFreq6 = new int[]{1072, 256, 203, 60};
        this.ptIvMF = new int[]{512, 26, Can.CAN_BYD_M6_DJ, 54};
        this.ptMFNum = new int[]{512, 556, CanCameraUI.BTN_GOLF_WC_MODE1, 667, 712};
        this.xMFDt = -this.ptIvMF[0];
        this.yMFDt = -this.ptIvMF[1];
        this.yMFNum = this.ptIvMF[1];
        this.xMFDot = CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE5;
        this.yMFDot = 26;
        this.amXStart = this.ptMFNum[1] - ((this.ptMFNum[1] - this.ptMFNum[0]) / 2);
        this.amXdt = this.ptMFNum[1] - this.ptMFNum[0];
        this.mIsHaveMoveFreq = true;
        this.ptFreqAdj = new int[]{110, 168, 1061, 85};
        this.xAdjMin = 120;
        this.xAdjMax = CanCameraUI.BTN_BAIC_HSS6_QJQH;
        this.YoubiaoOffsetY = 0;
        this.wYoubiao = 21;
        this.ptAdjTxtMin = new int[]{104, 118, Can.CAN_JAC_REFINE_OD, 69};
        this.ptAdjTxtMax = new int[]{1027, 118, Can.CAN_JAC_REFINE_OD, 69};
        this.adjTxtGravity = 17;
        this.mXYoubiao = 0;
        this.runnable = new Runnable() {
            public void run() {
                RadioMainActivity.mRunCnt = RadioMainActivity.mRunCnt + 1;
                RadioMainActivity.mHandler.postDelayed(this, 30);
            }
        };
        this.onPtyOK = new RdsDlg.onInputOK() {
            public void onOK(int id) {
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        this.aPort = 3;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_main);
        initUI();
        dbgInit();
    }

    private void dbgInit() {
    }

    public void onClick(View v) {
        int id = ((ParamButton) v).getIntParam();
        Log.i("RadioMainActivity", "onClick " + this.mStrCmd[id]);
        switch (id) {
            case 1:
                this.mMainVolume.VolWinShow();
                return;
            case 2:
                Radio.TuneStset();
                return;
            case 3:
                Radio.TuneBandFm();
                return;
            case 4:
                Radio.TuneBandAm();
                return;
            case 5:
                Radio.TuneInt();
                return;
            case 6:
                Radio.TuneAms();
                return;
            case 7:
                WinShow.TurnToEq();
                return;
            case 8:
                Radio.TuneBand(1);
                return;
            case 9:
                if (this.mStepMode) {
                    Radio.TuneStep(0);
                    this.mStepTick = SystemClock.uptimeMillis();
                    return;
                }
                Radio.TuneSearch(0);
                return;
            case 10:
                if (this.mStepMode) {
                    Radio.TuneStep(1);
                    this.mStepTick = SystemClock.uptimeMillis();
                    return;
                }
                Radio.TuneSearch(1);
                return;
            case 13:
                Radio.RdsAf();
                return;
            case 14:
                Radio.RdsTa();
                return;
            case 15:
                new RdsDlg().createDlg(this, this.onPtyOK);
                return;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                Radio.TuneMset((id - 16) + 1);
                return;
            case 22:
                this.mEvc.evol_workmode_set(0);
                finish();
                return;
            case 23:
                RadioFunc.ShowRdsSet(this);
                return;
            case 24:
                change();
                return;
            default:
                return;
        }
    }

    private void change() {
        if (this.isBand) {
            this.isBand = false;
            this.mBtnSt.setVisibility(8);
            this.mBtnAms.setVisibility(8);
            this.mBtnBand.setVisibility(8);
            this.mBtnEq.setVisibility(8);
            this.mBtnAf.setVisibility(0);
            this.mBtnTa.setVisibility(0);
            this.mBtnPty.setVisibility(0);
            this.mBtnRdsSet.setVisibility(0);
            return;
        }
        this.isBand = true;
        this.mBtnSt.setVisibility(0);
        this.mBtnAms.setVisibility(0);
        this.mBtnBand.setVisibility(0);
        this.mBtnEq.setVisibility(0);
        this.mBtnAf.setVisibility(8);
        this.mBtnTa.setVisibility(8);
        this.mBtnPty.setVisibility(8);
        this.mBtnRdsSet.setVisibility(8);
    }

    private void RdsShow(int band) {
        boolean brds = i2b(FtSet.GetRDSen()) && band < 3;
        this.mBtnAf.Show(brds);
        this.mBtnTa.Show(brds);
        this.mBtnPty.Show(brds);
        this.mBtnRdsSet.Show(brds);
        int show = 0;
        if (brds) {
            show = 1;
        }
        showView(this.mTvPs, show);
        showView(this.mTvCurPty, show);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        this.mEvc.evol_workmode_set(1);
        RdsShow(Radio.GetDisp(2));
        this.mXYoubiao = this.xAdjMin;
        MainSet.GetInstance().TwShowTitle(getResources().getString(R.string.title_activity_radio_main));
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        MainSet.GetInstance().TwShowTitle("");
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public boolean i2b(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void CheckFreqMove() {
        int curStep;
        if (this.mIsHaveMoveFreq) {
            if (this.mbAdjMove) {
                curStep = this.mAdjStep;
            } else {
                curStep = Radio.GetDisp(5);
            }
            int curX = (((this.xAdjMax - this.xAdjMin) * curStep) / (Radio.GetDisp(4) - 1)) + this.xAdjMin;
            if (curX != this.mXYoubiao) {
                this.mXYoubiao = ValCal.NumApproach(curX, this.mXYoubiao);
                this.mIvFreqAdj.invalidate();
                Log.d("RadioMainActivity", "curX = " + curX + ", mXYoubiao = " + this.mXYoubiao);
            }
        }
    }

    private String StepToFreq(int step, boolean fm) {
        int freq = Radio.StepToFreq(step);
        if (fm) {
            return MainSet.GetInstance().tranalateIntoString(freq);
        }
        return new StringBuilder(String.valueOf(freq)).toString();
    }

    /* access modifiers changed from: private */
    public int PtToStep(float x) {
        int adjX = (int) x;
        if (x < ((float) this.xAdjMin)) {
            adjX = this.xAdjMin;
        } else if (x > ((float) this.xAdjMax)) {
            adjX = this.xAdjMax;
        }
        return ((adjX - this.xAdjMin) * (Radio.GetDisp(4) - 1)) / (this.xAdjMax - this.xAdjMin);
    }

    /* access modifiers changed from: private */
    public void SetStep(int step) {
        if (step != this.mAdjStep) {
            this.mIvMainFreq.invalidate();
            this.mAdjStep = step;
        }
    }

    /* access modifiers changed from: protected */
    public void onTimer() {
        if (this.mStepMode && SystemClock.uptimeMillis() > this.mStepTick + 3000) {
            this.mStepMode = false;
        }
        this.mCurBand = Radio.GetDisp(2);
        int update = Radio.GetDispUpdate();
        int dspFlg = Radio.GetDispFlag();
        if (this.mCurBand != this.mLastBand) {
            RdsShow(this.mCurBand);
            update = -1;
            this.mLastBand = this.mCurBand;
            this.mIvBand.setImageResource(mBandNum[this.mCurBand]);
            int totalStep = Radio.GetDisp(4);
            if (this.mCurBand >= 4) {
                this.mIvDW.setImageResource(R.drawable.radio_band_khz);
            } else {
                this.mIvDW.setImageResource(R.drawable.radio_band_mhz);
            }
            if (this.mIsHaveMoveFreq) {
                this.mTvAdjMin.setText(StepToFreq(0, this.mCurBand < 4));
                this.mTvAdjMax.setText(StepToFreq(totalStep - 1, this.mCurBand < 4));
            }
        }
        CheckFreqMove();
        if (i2b(update & 1)) {
            this.mIvMainFreq.invalidate();
        }
        if (i2b(update & 2)) {
            for (int i = 0; i < 6; i++) {
                this.mBtnFreq[i].setSelected(false);
            }
            int mem = Radio.GetDisp(3);
            if (mem > 0 && mem < 7) {
                this.mBtnFreq[mem - 1].setSelected(true);
            }
        }
        if (i2b(update & 4)) {
            updateMemFreq(0, Radio.GetDisp(6));
        }
        if (i2b(update & 8)) {
            updateMemFreq(1, Radio.GetDisp(7));
        }
        if (i2b(update & 16)) {
            updateMemFreq(2, Radio.GetDisp(8));
        }
        if (i2b(update & 32)) {
            updateMemFreq(3, Radio.GetDisp(9));
        }
        if (i2b(update & 64)) {
            updateMemFreq(4, Radio.GetDisp(10));
        }
        if (i2b(update & 128)) {
            updateMemFreq(5, Radio.GetDisp(11));
        }
        if (i2b(2097152 & update)) {
            showView(this.mIvAf, dspFlg & 32);
        }
        if (i2b(4194304 & update)) {
            showView(this.mIvTa, dspFlg & 64);
        }
        if (i2b(8388608 & update)) {
            showView(this.mIvTp, dspFlg & 128);
        }
        if (i2b(16777216 & update)) {
            showView(this.mIvEon, dspFlg & 16);
        }
        if (i2b(update & 256)) {
            showView(this.mIvStR, dspFlg & 1);
        }
        if (i2b(update & 512)) {
            showView(this.mIvStS, dspFlg & 2);
        }
        if (i2b(1048576 & update)) {
            Log.d("RadioMainActivity", "update & Radio.UD_PS");
            Radio.GetPsName(this.mcPs);
            if (this.mcPs[0] == 0) {
                this.mTvPs.setText("");
                this.mIvMainFreq.setVisibility(0);
            } else {
                this.mIvMainFreq.setVisibility(0);
                if (1 == this.mcPs[0]) {
                    String tmp = new String(this.mcPs, 1, 8);
                    String show = (String) this.mTvPs.getText();
                    if (!(tmp == null || show == null || tmp.equals(show))) {
                        this.mTvPs.setTextColor(-1);
                        this.mTvPs.setText(tmp);
                        Log.d("Test", tmp);
                    }
                } else if (2 == this.mcPs[0]) {
                    String tmp2 = new String(this.mcPs, 1, 8);
                    String show2 = (String) this.mTvPs.getText();
                    if (!(tmp2 == null || show2 == null || tmp2.equals(show2))) {
                        this.mTvPs.setTextColor(SupportMenu.CATEGORY_MASK);
                        this.mTvPs.setText(tmp2);
                    }
                } else {
                    this.mTvPs.setText("");
                    this.mIvMainFreq.setVisibility(0);
                }
            }
        }
        if (i2b(33554432 & update)) {
            int ptyNum = (65280 & dspFlg) >> 8;
            if (ptyNum >= 30 || ptyNum <= 0) {
                this.mTvCurPty.setText("");
                return;
            }
            Radio.GetPtyStr(this.mcPty, ptyNum);
            this.mTvCurPty.setText(String.valueOf(this.mcPty));
        }
    }

    private void updateMemFreq(int mem, int freq) {
        if (this.mCurBand >= 4) {
            this.mBtnFreq[mem].setText(new StringBuilder(String.valueOf(freq)).toString());
            this.mBtnFreq[mem].setTextSize(0, 35.0f);
            return;
        }
        Radio.GetMemPsName(mem + 1, this.mcFreq);
        if (1 == this.mcFreq[0]) {
            this.mBtnFreq[mem].setText(new String(this.mcFreq, 1, 8));
            this.mBtnFreq[mem].setTextSize(0, 25.0f);
            return;
        }
        this.mBtnFreq[mem].setText(MainSet.GetInstance().tranalateIntoString(freq));
        this.mBtnFreq[mem].setTextSize(0, 35.0f);
    }

    /* access modifiers changed from: protected */
    public void showView(View view, int val) {
        if (i2b(val)) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }

    /* access modifiers changed from: protected */
    public void setViewPos(View view, int x, int y, int w, int h) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(w, h);
        rl.leftMargin = x;
        rl.topMargin = y + 0;
        view.setLayoutParams(rl);
        this.mLayout.addView(view);
    }

    /* access modifiers changed from: protected */
    public ImageView IvCreateRelative(int x, int y, int w, int h, int resId) {
        ImageView iv = new ImageView(this);
        setViewPos(iv, x, y, w, h);
        iv.setImageResource(resId);
        return iv;
    }

    /* access modifiers changed from: protected */
    public TextView TvCreateRelative(int x, int y, int w, int h) {
        TextView tv = new TextView(this);
        setViewPos(tv, x, y, w, h);
        return tv;
    }

    /* access modifiers changed from: protected */
    public void initUI() {
        this.mLayout = (RelativeLayout) findViewById(R.id.rad_main_layout);
        ParamButton.initFactory(this, this.mLayout, 0);
        this.mBtnRdsSet = ParamButton.fsCreateRelative(this.ptBtnRds[0], this.ptBtnRds[1], this.ptBtnRds[2], this.ptBtnRds[3]);
        this.mBtnRdsSet.setStateUpDn(R.drawable.radio_button_rds_up, R.drawable.radio_button_rds_dn);
        this.mBtnRdsSet.setIntParam(23);
        this.mBtnList.add(this.mBtnRdsSet);
        this.mBtnAf = ParamButton.fsCreateRelative(this.ptBtnAf[0], this.ptBtnAf[1], this.ptBtnAf[2], this.ptBtnAf[3]);
        this.mBtnAf.setStateUpDn(R.drawable.radio_button_af_up, R.drawable.radio_button_af_dn);
        this.mBtnAf.setIntParam(13);
        this.mBtnList.add(this.mBtnAf);
        this.mBtnTa = ParamButton.fsCreateRelative(this.ptBtnTa[0], this.ptBtnTa[1], this.ptBtnTa[2], this.ptBtnTa[3]);
        this.mBtnTa.setStateUpDn(R.drawable.radio_button_ta_up, R.drawable.radio_button_ta_dn);
        this.mBtnTa.setIntParam(14);
        this.mBtnList.add(this.mBtnTa);
        this.mBtnPty = ParamButton.fsCreateRelative(this.ptBtnPty[0], this.ptBtnPty[1], this.ptBtnPty[2], this.ptBtnPty[3]);
        this.mBtnPty.setStateUpDn(R.drawable.radio_button_pty_up, R.drawable.radio_button_pty_dn);
        this.mBtnPty.setIntParam(15);
        this.mBtnList.add(this.mBtnPty);
        this.mBtnAf.Show(false);
        this.mBtnTa.Show(false);
        this.mBtnPty.Show(false);
        this.mBtnSt = ParamButton.fsCreateRelative(this.ptBtnSt[0], this.ptBtnSt[1], this.ptBtnSt[2], this.ptBtnSt[3]);
        this.mBtnSt.setStateUpDn(R.drawable.radio_button_horn_up, R.drawable.radio_button_horn_dn);
        this.mBtnSt.setIntParam(2);
        this.mBtnList.add(this.mBtnSt);
        this.mBtnBand = ParamButton.fsCreateRelative(this.ptBtnBand[0], this.ptBtnBand[1], this.ptBtnBand[2], this.ptBtnBand[3]);
        this.mBtnBand.setStateUpDn(R.drawable.radio_button_band_up, R.drawable.radio_button_band_dn);
        this.mBtnBand.setIntParam(8);
        this.mBtnList.add(this.mBtnBand);
        this.mBtnNext = ParamButton.fsCreateRelative(this.ptBtnNext[0], this.ptBtnNext[1], this.ptBtnNext[2], this.ptBtnNext[3]);
        this.mBtnNext.setStateUpDn(R.drawable.radio_button_next_up, R.drawable.radio_button_next_dn);
        this.mBtnNext.setIntParam(24);
        this.mBtnList.add(this.mBtnNext);
        this.mBtnClose = ParamButton.fsCreateRelative(this.ptBtnClose[0], this.ptBtnClose[1], this.ptBtnClose[2], this.ptBtnClose[3]);
        this.mBtnClose.setStateUpDn(R.drawable.radio_button_close_up, R.drawable.radio_button_close_dn);
        this.mBtnClose.setIntParam(22);
        this.mBtnList.add(this.mBtnClose);
        this.mBtnAms = ParamButton.fsCreateRelative(this.ptBtnAms[0], this.ptBtnAms[1], this.ptBtnAms[2], this.ptBtnAms[3]);
        this.mBtnAms.setStateUpDn(R.drawable.radio_button_store_up, R.drawable.radio_button_store_dn);
        this.mBtnAms.setIntParam(6);
        this.mBtnList.add(this.mBtnAms);
        this.mBtnEq = ParamButton.fsCreateRelative(this.ptBtnEq[0], this.ptBtnEq[1], this.ptBtnEq[2], this.ptBtnEq[3]);
        this.mBtnEq.setStateUpDn(R.drawable.radio_button_eq_up, R.drawable.radio_button_eq_dn);
        this.mBtnEq.setIntParam(7);
        this.mBtnList.add(this.mBtnEq);
        this.mBtnSeekDec = ParamButton.fsCreateRelative(this.ptBtnSkDec[0], this.ptBtnSkDec[1], this.ptBtnSkDec[2], this.ptBtnSkDec[3]);
        this.mBtnSeekDec.setStateUpDn(R.drawable.radio_button_vup_up, R.drawable.radio_button_vup_dn);
        this.mBtnSeekDec.setIntParam(9);
        this.mBtnList.add(this.mBtnSeekDec);
        this.mBtnSeekDec.setOnLongClickListener(this);
        this.mBtnSeekInc = ParamButton.fsCreateRelative(this.ptBtnSkInc[0], this.ptBtnSkInc[1], this.ptBtnSkInc[2], this.ptBtnSkInc[3]);
        this.mBtnSeekInc.setStateUpDn(R.drawable.radio_button_vdn_up, R.drawable.radio_button_vdn_dn);
        this.mBtnSeekInc.setIntParam(10);
        this.mBtnList.add(this.mBtnSeekInc);
        this.mBtnSeekInc.setOnLongClickListener(this);
        this.mBtnFreq[0] = ParamButton.fsCreateRelative(this.ptBtnFreq1[0], this.ptBtnFreq1[1], this.ptBtnFreq1[2], this.ptBtnFreq1[3]);
        this.mBtnFreq[1] = ParamButton.fsCreateRelative(this.ptBtnFreq2[0], this.ptBtnFreq2[1], this.ptBtnFreq2[2], this.ptBtnFreq2[3]);
        this.mBtnFreq[2] = ParamButton.fsCreateRelative(this.ptBtnFreq3[0], this.ptBtnFreq3[1], this.ptBtnFreq3[2], this.ptBtnFreq3[3]);
        this.mBtnFreq[3] = ParamButton.fsCreateRelative(this.ptBtnFreq4[0], this.ptBtnFreq4[1], this.ptBtnFreq4[2], this.ptBtnFreq4[3]);
        this.mBtnFreq[4] = ParamButton.fsCreateRelative(this.ptBtnFreq5[0], this.ptBtnFreq5[1], this.ptBtnFreq5[2], this.ptBtnFreq5[3]);
        this.mBtnFreq[5] = ParamButton.fsCreateRelative(this.ptBtnFreq6[0], this.ptBtnFreq6[1], this.ptBtnFreq6[2], this.ptBtnFreq6[3]);
        for (int i = 0; i < 6; i++) {
            this.mBtnFreq[i].setIntParam(i + 16);
            this.mBtnFreq[i].setColor(this.RdsColor, Color.rgb(255, 102, 0));
            this.mBtnFreq[i].setTextSize(0, 35.0f);
            this.mBtnFreq[i].setPadding(0, 0, 0, 0);
            this.mBtnFreq[i].setOnClickListener(this);
            this.mBtnFreq[i].setOnLongClickListener(this);
            this.mBtnList.add(this.mBtnFreq[i]);
        }
        this.mBtnFreq[0].setStateDrawable(R.drawable.radio_rect01_up, R.drawable.radio_rect01_dn, R.drawable.radio_rect01_dn);
        this.mBtnFreq[1].setStateDrawable(R.drawable.radio_rect02_up, R.drawable.radio_rect02_dn, R.drawable.radio_rect02_dn);
        this.mBtnFreq[2].setStateDrawable(R.drawable.radio_rect03_up, R.drawable.radio_rect03_dn, R.drawable.radio_rect03_dn);
        this.mBtnFreq[3].setStateDrawable(R.drawable.radio_rect04_up, R.drawable.radio_rect04_dn, R.drawable.radio_rect04_dn);
        this.mBtnFreq[4].setStateDrawable(R.drawable.radio_rect05_up, R.drawable.radio_rect05_dn, R.drawable.radio_rect05_dn);
        this.mBtnFreq[5].setStateDrawable(R.drawable.radio_rect06_up, R.drawable.radio_rect06_dn, R.drawable.radio_rect06_dn);
        this.mIvAf = IvCreateRelative(this.ptIvAf[0], this.ptIvAf[1], this.ptIvAf[2], this.ptIvAf[3], R.drawable.radio_af);
        this.mIvTa = IvCreateRelative(this.ptIvTa[0], this.ptIvTa[1], this.ptIvTa[2], this.ptIvTa[3], R.drawable.radio_ta);
        this.mIvTp = IvCreateRelative(this.ptIvTp[0], this.ptIvTp[1], this.ptIvTp[2], this.ptIvTp[3], R.drawable.radio_tp);
        this.mIvEon = IvCreateRelative(this.ptIvEon[0], this.ptIvEon[1], this.ptIvEon[2], this.ptIvEon[3], R.drawable.radio_eon);
        this.mIvStR = IvCreateRelative(this.ptIvStR[0], this.ptIvStR[1], this.ptIvStR[2], this.ptIvStR[3], R.drawable.radio_stereo);
        this.mIvStS = IvCreateRelative(this.ptIvStS[0], this.ptIvStS[1], this.ptIvStS[2], this.ptIvStS[3], R.drawable.radio_st);
        this.mIvAf.setVisibility(4);
        this.mIvTa.setVisibility(4);
        this.mIvTp.setVisibility(4);
        this.mIvEon.setVisibility(4);
        this.mIvStR.setVisibility(4);
        this.mIvStS.setVisibility(4);
        this.mIvBand = IvCreateRelative(this.ptIvBand[0], this.ptIvBand[1], this.ptIvBand[2], this.ptIvBand[3], R.drawable.radio_band_fm1);
        this.mIvDW = IvCreateRelative(this.ptIvBndDw[0], this.ptIvBndDw[1], this.ptIvBndDw[2], this.ptIvBndDw[3], R.drawable.radio_band_mhz);
        this.mTvCurPty = TvCreateRelative(this.ptIvPty[0], this.ptIvPty[1], this.ptIvPty[2], this.ptIvPty[3]);
        this.mTvCurPty.setText("");
        this.mTvCurPty.setTextSize(0, 35.0f);
        this.mTvCurPty.setGravity(21);
        this.mTvCurPty.setTextColor(this.RdsColor);
        this.mTvCurPty.setVisibility(4);
        this.mTvPs = TvCreateRelative(this.ptIvPs[0], this.ptIvPs[1], this.ptIvPs[2], this.ptIvPs[3]);
        this.mTvPs.setText("");
        this.mTvPs.setTextSize(0, 38.0f);
        this.mTvPs.setGravity(17);
        this.mTvPs.setTextColor(-1);
        this.mTvPs.setVisibility(4);
        this.mIvMainFreq = new CustomImgView(this);
        setViewPos(this.mIvMainFreq, this.ptIvMF[0], this.ptIvMF[1], this.ptIvMF[2], this.ptIvMF[3]);
        this.mIvMainFreq.setDrawDt(this.xMFDt, this.yMFDt);
        this.mIvMainFreq.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                int curFreq;
                Log.d("RadioMainActivity", "userPaint");
                if (RadioMainActivity.this.mbAdjMove) {
                    curFreq = Radio.StepToFreq(RadioMainActivity.this.mAdjStep);
                } else {
                    curFreq = Radio.GetDisp(1);
                }
                int nBand = RadioMainActivity.this.mCurBand;
                Radio.GetPsName(RadioMainActivity.this.mcPs);
                if (nBand >= 4) {
                    if (curFreq > 999) {
                        view.drawImage(RadioMainActivity.mFreqNum[1], RadioMainActivity.this.amXStart + (RadioMainActivity.this.amXdt * 0), RadioMainActivity.this.yMFNum);
                    }
                    view.drawImage(RadioMainActivity.mFreqNum[(curFreq % MediaDef.PROGRESS_MAX) / 100], RadioMainActivity.this.amXStart + (RadioMainActivity.this.amXdt * 1), RadioMainActivity.this.yMFNum);
                    view.drawImage(RadioMainActivity.mFreqNum[(curFreq % 100) / 10], RadioMainActivity.this.amXStart + (RadioMainActivity.this.amXdt * 2), RadioMainActivity.this.yMFNum);
                    view.drawImage(RadioMainActivity.mFreqNum[curFreq % 10], RadioMainActivity.this.amXStart + (RadioMainActivity.this.amXdt * 3), RadioMainActivity.this.yMFNum);
                } else {
                    if (curFreq > 9999) {
                        view.drawImage(RadioMainActivity.mFreqNum[1], RadioMainActivity.this.ptMFNum[0], RadioMainActivity.this.yMFNum);
                    }
                    view.drawImage(RadioMainActivity.mFreqNum[(curFreq % TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT) / MediaDef.PROGRESS_MAX], RadioMainActivity.this.ptMFNum[1], RadioMainActivity.this.yMFNum);
                    view.drawImage(RadioMainActivity.mFreqNum[(curFreq % MediaDef.PROGRESS_MAX) / 100], RadioMainActivity.this.ptMFNum[2], RadioMainActivity.this.yMFNum);
                    view.drawImage(R.drawable.radio_point_up, RadioMainActivity.this.xMFDot, RadioMainActivity.this.yMFDot);
                    view.drawImage(RadioMainActivity.mFreqNum[(curFreq % 100) / 10], RadioMainActivity.this.ptMFNum[3], RadioMainActivity.this.yMFNum);
                    view.drawImage(RadioMainActivity.mFreqNum[curFreq % 10], RadioMainActivity.this.ptMFNum[4], RadioMainActivity.this.yMFNum);
                }
                return false;
            }
        });
        if (this.mIsHaveMoveFreq) {
            this.mIvFreqAdj = new CustomImgView(this);
            setViewPos(this.mIvFreqAdj, this.ptFreqAdj[0], this.ptFreqAdj[1], this.ptFreqAdj[2], this.ptFreqAdj[3]);
            this.mIvFreqAdj.setUserPaint(new CustomImgView.onPaint() {
                public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                    view.drawImage(R.drawable.radio_fb, (RadioMainActivity.this.mXYoubiao - RadioMainActivity.this.ptFreqAdj[0]) - (RadioMainActivity.this.wYoubiao / 2), RadioMainActivity.this.YoubiaoOffsetY);
                    return false;
                }
            });
            this.mIvFreqAdj.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case 0:
                            Log.d("RadioMainActivity", "ACTION_DOWN");
                            RadioMainActivity.this.mbAdjMove = true;
                            RadioMainActivity.this.SetStep(RadioMainActivity.this.PtToStep(event.getRawX()));
                            break;
                        case 1:
                            Log.d("RadioMainActivity", "ACTION_UP");
                            if (RadioMainActivity.this.mbAdjMove) {
                                Radio.TuneFset(RadioMainActivity.this.mAdjStep);
                                RadioMainActivity.this.mbAdjMove = false;
                                break;
                            }
                            break;
                        case 2:
                            Log.d("RadioMainActivity", "ACTION_MOVE");
                            if (RadioMainActivity.this.mbAdjMove) {
                                RadioMainActivity.this.SetStep(RadioMainActivity.this.PtToStep(event.getRawX()));
                                break;
                            }
                            break;
                    }
                    return true;
                }
            });
            this.mTvAdjMin = TvCreateRelative(this.ptAdjTxtMin[0], this.ptAdjTxtMin[1], this.ptAdjTxtMin[2], this.ptAdjTxtMin[3]);
            this.mTvAdjMin.setTextSize(0, 25.0f);
            this.mTvAdjMin.setGravity(19);
            this.mTvAdjMin.setTextColor(-1);
            this.mTvAdjMax = TvCreateRelative(this.ptAdjTxtMax[0], this.ptAdjTxtMax[1], this.ptAdjTxtMax[2], this.ptAdjTxtMax[3]);
            this.mTvAdjMax.setTextSize(0, 25.0f);
            this.mTvAdjMax.setGravity(21);
            this.mTvAdjMax.setTextColor(-1);
        }
        for (int i2 = 0; i2 < this.mBtnList.size(); i2++) {
            this.mBtnList.get(i2).setOnClickListener(this);
        }
    }

    public boolean onLongClick(View v) {
        int id = ((ParamButton) v).getIntParam();
        Log.i("RadioMainActivity", "onLongClick " + this.mStrCmd[id]);
        switch (id) {
            case 9:
                Radio.TuneStep(0);
                this.mStepTick = SystemClock.uptimeMillis();
                this.mStepMode = true;
                break;
            case 10:
                Radio.TuneStep(1);
                this.mStepMode = true;
                this.mStepTick = SystemClock.uptimeMillis();
                break;
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                Radio.TuneMsave((id - 16) + 1);
                break;
        }
        return true;
    }

    public void UserAll() {
        onTimer();
    }
}
