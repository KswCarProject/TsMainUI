package com.ts.main.radio;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainVolume;
import com.ts.main.common.WinShow;
import com.ts.main.radio.RdsDlg;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.ValCal;
import com.txznet.sdk.TXZPoiSearchManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.KeyDef;
import com.yyw.ts70xhw.Radio;
import java.util.ArrayList;

@SuppressLint({"NewApi"})
public class RadioMainActivity extends TsMode implements View.OnClickListener, View.OnLongClickListener, UserCallBack {
    public static final boolean DEBUG_FLAG = false;
    public static final boolean DEBUG_RADIO = false;
    public static boolean FIRST_UPDATE = true;
    public static final String TAG = "RadioMainActivity1";
    public static final int btn_af = 13;
    public static final int btn_am = 4;
    public static final int btn_ams = 6;
    public static final int btn_band = 8;
    public static final int btn_close = 22;
    public static final int btn_eq = 7;
    public static final int btn_fm = 3;
    public static final int btn_mute = 1;
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
    private static int[] mBandNum = {R.drawable.radio_band_fm1, R.drawable.radio_band_fm2, R.drawable.radio_band_fm3, R.drawable.radio_band_ot, R.drawable.radio_band_am1, R.drawable.radio_band_am2};
    private static int[] mBandNum_b = {R.drawable.radio_band_fm1, R.drawable.radio_band_fm2, R.drawable.radio_band_fm3, R.drawable.radio_band_ot, R.drawable.radio_band_am1, R.drawable.radio_band_am2};
    private static final int[] mBandNum_s = {R.drawable.radio_band_fm1_s, R.drawable.radio_band_fm2_s, R.drawable.radio_band_fm3_s, R.drawable.radio_band_ot_s, R.drawable.radio_band_am1_s, R.drawable.radio_band_am2_s};
    /* access modifiers changed from: private */
    public static int[] mFreqNum = {R.drawable.radio_num00_up, R.drawable.radio_num01_up, R.drawable.radio_num02_up, R.drawable.radio_num03_up, R.drawable.radio_num04_up, R.drawable.radio_num05_up, R.drawable.radio_num06_up, R.drawable.radio_num07_up, R.drawable.radio_num08_up, R.drawable.radio_num09_up};
    /* access modifiers changed from: private */
    public static Handler mHandler = null;
    /* access modifiers changed from: private */
    public static int mRunCnt = 0;
    int MemDnColor = Color.parseColor("#ffb401");
    int MemUpColor = -1;
    int RdsColor = Color.parseColor("#0096ff");
    int[] TvFreq = {367, 124, 284, 40};
    int[] TvFreq_b;
    int[] TvFreq_s;
    int YoubiaoOffsetY;
    int YoubiaoOffsetY_b;
    int YoubiaoOffsetY_s;
    int amXStart = (this.ptMFNum[1] - ((this.ptMFNum[1] - this.ptMFNum[0]) / 2));
    int amXStart_b;
    int amXStart_s;
    int amXdt = (this.ptMFNum[1] - this.ptMFNum[0]);
    int amXdt_b;
    int amXdt_s;
    private int flag = 0;
    boolean freqCenter;
    boolean isInMultiWindowMode = false;
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
    private ParamButton mBtnPty;
    private ParamButton mBtnRdsSet;
    private ParamButton mBtnScan;
    private ParamButton mBtnSeekDec;
    private ParamButton mBtnSeekInc;
    private ParamButton mBtnSt;
    private ParamButton mBtnTa;
    /* access modifiers changed from: private */
    public int mCurBand;
    private int mCurtotalStep = 0;
    private Evc mEvc = Evc.GetInstance();
    boolean mIsHaveMoveFreq = true;
    private ImageView mIvAf;
    private ImageView mIvBand;
    private ImageView mIvDW;
    private ImageView mIvEon;
    private CustomImgView mIvFreqAdj;
    private ImageView mIvLine;
    private CustomImgView mIvMainFreq;
    private ImageView mIvStR;
    private ImageView mIvStS;
    private ImageView mIvTa;
    private ImageView mIvTp;
    private int mLastBand = -1;
    private RelativeLayout mLayout;
    private MainVolume mMainVolume = MainVolume.GetInstance();
    int mOrientation;
    private boolean mStepMode = false;
    private long mStepTick;
    private String[] mStrCmd = {TXZResourceManager.STYLE_DEFAULT, "btn_mute", "btn_st", "btn_fm", "btn_am", "btn_scan", "btn_ams", "btn_eq", "btn_band", "btn_seek_dec", "btn_seek_inc", "btn_step_dec", "btn_step_inc", "btn_af", "btn_ta", "btn_pty", "btn_t1", "btn_t2", "btn_t3", "btn_t4", "btn_t5", "btn_t6", "btn_close", "btn_rds_set"};
    private TextView mTvAdjMax;
    private TextView mTvAdjMin;
    private TextView mTvCurPty;
    private TextView mTvFreq;
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
    int[] ptAdjTxtMax_b;
    int[] ptAdjTxtMax_s;
    int[] ptAdjTxtMin;
    int[] ptAdjTxtMin_b;
    int[] ptAdjTxtMin_s;
    int[] ptBtnAf = {186, 74, 78, 34};
    int[] ptBtnAf_b;
    int[] ptBtnAf_s;
    int[] ptBtnAm = {439, 467, 146, 78};
    int[] ptBtnAm_b;
    int[] ptBtnAm_s;
    int[] ptBtnAms = {585, 467, 146, 78};
    int[] ptBtnAms_b;
    int[] ptBtnAms_s;
    int[] ptBtnBand = new int[4];
    int[] ptBtnBand_b;
    int[] ptBtnBand_s;
    int[] ptBtnClose = {877, 467, 146, 78};
    int[] ptBtnClose_b;
    int[] ptBtnClose_s;
    int[] ptBtnEq = {731, 467, 146, 78};
    int[] ptBtnEq_b;
    int[] ptBtnEq_s;
    int[] ptBtnFm = {293, 467, 146, 78};
    int[] ptBtnFm_b;
    int[] ptBtnFm_s;
    int[] ptBtnFreq1 = {8, 298, KeyDef.RKEY_RDS_PTY, 73};
    int[] ptBtnFreq1_b;
    int[] ptBtnFreq1_s;
    int[] ptBtnFreq2 = {346, 298, KeyDef.RKEY_RDS_PTY, 73};
    int[] ptBtnFreq2_b;
    int[] ptBtnFreq2_s;
    int[] ptBtnFreq3 = {685, 298, KeyDef.RKEY_RDS_PTY, 73};
    int[] ptBtnFreq3_b;
    int[] ptBtnFreq3_s;
    int[] ptBtnFreq4 = {8, 382, KeyDef.RKEY_RDS_PTY, 73};
    int[] ptBtnFreq4_b;
    int[] ptBtnFreq4_s;
    int[] ptBtnFreq5 = {346, 382, KeyDef.RKEY_RDS_PTY, 73};
    int[] ptBtnFreq5_b;
    int[] ptBtnFreq5_s;
    int[] ptBtnFreq6 = {685, 382, KeyDef.RKEY_RDS_PTY, 73};
    int[] ptBtnFreq6_b;
    int[] ptBtnFreq6_s;
    int[] ptBtnPty = {765, 74, 78, 34};
    int[] ptBtnPty_b;
    int[] ptBtnPty_s;
    int[] ptBtnRds = {57, 74, 78, 34};
    int[] ptBtnRds_b;
    int[] ptBtnRds_s;
    int[] ptBtnSkDec = {71, 150, 95, 95};
    int[] ptBtnSkDec_b;
    int[] ptBtnSkDec_s;
    int[] ptBtnSkInc = {857, 150, 95, 95};
    int[] ptBtnSkInc_b;
    int[] ptBtnSkInc_s;
    int[] ptBtnSound = {1, 467, 169, 78};
    int[] ptBtnSound_b;
    int[] ptBtnSound_s;
    int[] ptBtnSt = {147, 467, 146, 78};
    int[] ptBtnSt_b;
    int[] ptBtnSt_s;
    int[] ptBtnTa = {882, 74, 78, 34};
    int[] ptBtnTa_b;
    int[] ptBtnTa_s;
    int[] ptFreqAdj;
    int[] ptFreqAdj_b;
    int[] ptFreqAdj_s;
    int[] ptIvAf = {639, 80, 46, 19};
    int[] ptIvAf_b;
    int[] ptIvAf_s;
    int[] ptIvBand = {Can.CAN_FORD_MONDEO_XFY, 200, 73, 25};
    int[] ptIvBand_b;
    int[] ptIvBand_s;
    int[] ptIvBndDw = {709, 200, 72, 25};
    int[] ptIvBndDw_b;
    int[] ptIvBndDw_s;
    int[] ptIvEon = {405, 80, 46, 19};
    int[] ptIvEon_b;
    int[] ptIvEon_s;
    int[] ptIvMF = {367, 164, 284, 65};
    int[] ptIvMF_b;
    int[] ptIvMF_s;
    int[] ptIvPs = {AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, Can.CAN_NISSAN_XFY, 142, 29};
    int[] ptIvPs_b;
    int[] ptIvPs_s;
    int[] ptIvPty = {367, Can.CAN_NISSAN_XFY, 284, 29};
    int[] ptIvPty_b;
    int[] ptIvPty_s;
    int[] ptIvStR = {CanCameraUI.BTN_GEELY_YJX6_MODE4, 80, 46, 19};
    int[] ptIvStR_b;
    int[] ptIvStR_s;
    int[] ptIvStS = {470, 80, 46, 19};
    int[] ptIvStS_b;
    int[] ptIvStS_s;
    int[] ptIvTa = {584, 80, 46, 19};
    int[] ptIvTa_b;
    int[] ptIvTa_s;
    int[] ptIvTp = {KeyDef.RKEY_res2, 80, 46, 19};
    int[] ptIvTp_b;
    int[] ptIvTp_s;
    int[] ptMFNum = {367, 419, 472, 551, 604};
    int[] ptMFNum_b;
    int[] ptMFNum_s;
    private Runnable runnable;
    int wYoubiao;
    int wYoubiao_b;
    int wYoubiao_s;
    int xAdjMax;
    int xAdjMax_b;
    int xAdjMax_s;
    int xAdjMin;
    int xAdjMin_b;
    int xAdjMin_s;
    int xMFDot = CanCameraUI.BTN_CHANA_ALSVINV7_MODE1;
    int xMFDot_b;
    int xMFDot_s;
    int xMFDt = (-this.ptIvMF[0]);
    int xMFDt_b;
    int xMFDt_s;
    int yMFDot = 217;
    int yMFDot_b;
    int yMFDot_s;
    int yMFDt = (-this.ptIvMF[1]);
    int yMFDt_b;
    int yMFDt_s;
    int yMFNum = this.ptIvMF[1];
    int yMFNum_b;
    int yMFNum_s;

    public RadioMainActivity() {
        int[] iArr = new int[4];
        iArr[2] = 1024;
        iArr[3] = 67;
        this.ptFreqAdj = iArr;
        this.xAdjMin = 11;
        this.xAdjMax = 1013;
        this.YoubiaoOffsetY = 0;
        this.wYoubiao = 14;
        this.ptAdjTxtMin = new int[]{7, 40, 100, 29};
        this.ptAdjTxtMax = new int[]{917, 40, 100, 29};
        this.freqCenter = false;
        this.ptBtnRds_b = new int[]{57, 74, 78, 34};
        this.ptBtnAf_b = new int[]{186, 74, 78, 34};
        this.ptBtnTa_b = new int[]{882, 74, 78, 34};
        this.ptBtnPty_b = new int[]{765, 74, 78, 34};
        this.ptBtnSound_b = new int[]{1, 467, 169, 78};
        this.ptBtnSt_b = new int[]{147, 467, 146, 78};
        this.ptBtnBand_b = new int[4];
        this.ptBtnFm_b = new int[]{293, 467, 146, 78};
        this.ptBtnAm_b = new int[]{439, 467, 146, 78};
        this.ptBtnClose_b = new int[]{877, 467, 146, 78};
        this.ptBtnAms_b = new int[]{585, 467, 146, 78};
        this.ptBtnEq_b = new int[]{731, 467, 146, 78};
        this.ptBtnSkDec_b = new int[]{71, 150, 95, 95};
        this.ptBtnSkInc_b = new int[]{857, 150, 95, 95};
        this.ptIvAf_b = new int[]{639, 80, 46, 19};
        this.ptIvTa_b = new int[]{584, 80, 46, 19};
        this.ptIvTp_b = new int[]{KeyDef.RKEY_res2, 80, 46, 19};
        this.ptIvEon_b = new int[]{405, 80, 46, 19};
        this.ptIvStR_b = new int[]{CanCameraUI.BTN_GEELY_YJX6_MODE4, 80, 46, 19};
        this.ptIvStS_b = new int[]{470, 80, 46, 19};
        this.ptIvBand_b = new int[]{Can.CAN_FORD_MONDEO_XFY, 200, 73, 25};
        this.ptIvBndDw_b = new int[]{709, 200, 72, 25};
        this.ptIvPty_b = new int[]{367, Can.CAN_NISSAN_XFY, 284, 29};
        this.ptIvPs_b = new int[]{AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, Can.CAN_NISSAN_XFY, 142, 29};
        this.ptBtnFreq1_b = new int[]{8, 298, KeyDef.RKEY_RDS_PTY, 73};
        this.ptBtnFreq2_b = new int[]{346, 298, KeyDef.RKEY_RDS_PTY, 73};
        this.ptBtnFreq3_b = new int[]{685, 298, KeyDef.RKEY_RDS_PTY, 73};
        this.ptBtnFreq4_b = new int[]{8, 382, KeyDef.RKEY_RDS_PTY, 73};
        this.ptBtnFreq5_b = new int[]{346, 382, KeyDef.RKEY_RDS_PTY, 73};
        this.ptBtnFreq6_b = new int[]{685, 382, KeyDef.RKEY_RDS_PTY, 73};
        this.TvFreq_b = new int[]{367, 124, 284, 40};
        this.ptIvMF_b = new int[]{367, 164, 284, 65};
        this.ptMFNum_b = new int[]{367, 419, 472, 551, 604};
        this.xMFDt_b = -this.ptIvMF_b[0];
        this.yMFDt_b = -this.ptIvMF_b[1];
        this.yMFNum_b = this.ptIvMF_b[1];
        this.xMFDot_b = CanCameraUI.BTN_CHANA_ALSVINV7_MODE1;
        this.yMFDot_b = 217;
        this.amXStart_b = this.ptMFNum_b[1] - ((this.ptMFNum_b[1] - this.ptMFNum_b[0]) / 2);
        this.amXdt_b = this.ptMFNum_b[1] - this.ptMFNum_b[0];
        int[] iArr2 = new int[4];
        iArr2[2] = 1024;
        iArr2[3] = 67;
        this.ptFreqAdj_b = iArr2;
        this.xAdjMin_b = 11;
        this.xAdjMax_b = 1013;
        this.YoubiaoOffsetY_b = 0;
        this.wYoubiao_b = 14;
        this.ptAdjTxtMin_b = new int[]{7, 40, 100, 29};
        this.ptAdjTxtMax_b = new int[]{917, 40, 100, 29};
        this.ptBtnRds_s = new int[4];
        this.ptBtnAf_s = new int[4];
        this.ptBtnTa_s = new int[4];
        this.ptBtnPty_s = new int[4];
        this.ptBtnSound_s = new int[4];
        int[] iArr3 = new int[4];
        iArr3[0] = 3;
        iArr3[1] = 294;
        this.ptBtnSt_s = iArr3;
        this.ptBtnBand_s = new int[4];
        this.ptBtnFm_s = new int[]{124, 444, 121, 102};
        this.ptBtnAm_s = new int[]{263, 444, 121, 102};
        this.ptBtnClose_s = new int[4];
        int[] iArr4 = new int[4];
        iArr4[0] = 179;
        iArr4[1] = 294;
        this.ptBtnAms_s = iArr4;
        int[] iArr5 = new int[4];
        iArr5[0] = 343;
        iArr5[1] = 294;
        this.ptBtnEq_s = iArr5;
        this.ptBtnSkDec_s = new int[]{1, 441, 121, 102};
        this.ptBtnSkInc_s = new int[]{391, 441, 121, 102};
        this.ptIvAf_s = new int[]{383, 116, 46, 19};
        this.ptIvTa_s = new int[]{KeyDef.RKEY_RADIO_6S, 116, 46, 19};
        this.ptIvTp_s = new int[]{82, 116, 46, 19};
        this.ptIvEon_s = new int[]{149, 116, 46, 19};
        this.ptIvStR_s = new int[]{267, 116, 46, 19};
        this.ptIvStS_s = new int[]{214, 116, 46, 19};
        this.ptIvBand_s = new int[]{32, Can.CAN_SGMW_WC, 57, 20};
        this.ptIvBndDw_s = new int[]{438, Can.CAN_CHRYSLER_ONE_HC, 57, 20};
        this.ptIvPty_s = new int[]{103, Can.CAN_VOLKS_XP, 305, 50};
        this.ptIvPs_s = new int[]{103, 186, 305, 70};
        this.ptBtnFreq1_s = new int[]{7, 307, 162, 58};
        this.ptBtnFreq2_s = new int[]{176, 307, 162, 58};
        this.ptBtnFreq3_s = new int[]{345, 307, 162, 58};
        this.ptBtnFreq4_s = new int[]{7, 372, 162, 58};
        this.ptBtnFreq5_s = new int[]{176, 372, 162, 58};
        this.ptBtnFreq6_s = new int[]{345, 372, 162, 58};
        this.TvFreq_s = new int[]{103, Can.CAN_HONDA_WC, 305, 30};
        this.ptIvMF_s = new int[]{103, 186, 305, 70};
        this.ptMFNum_s = new int[]{103, 160, 216, 302, 359};
        this.xMFDt_s = -this.ptIvMF_s[0];
        this.yMFDt_s = -this.ptIvMF_s[1];
        this.yMFNum_s = this.ptIvMF_s[1];
        this.xMFDot_s = 279;
        this.yMFDot_s = Can.CAN_BYD_M6_DJ;
        this.amXStart_s = this.ptMFNum_s[1] - ((this.ptMFNum_s[1] - this.ptMFNum_s[0]) / 2);
        this.amXdt_s = this.ptMFNum_s[1] - this.ptMFNum_s[0];
        int[] iArr6 = new int[4];
        iArr6[2] = 512;
        iArr6[3] = 67;
        this.ptFreqAdj_s = iArr6;
        this.xAdjMin_s = 7;
        this.xAdjMax_s = CanCameraUI.BTN_HMS7_HELP_LINE;
        this.YoubiaoOffsetY_s = 0;
        this.wYoubiao_s = 14;
        this.ptAdjTxtMin_s = new int[]{3, 46, 70, 25};
        this.ptAdjTxtMax_s = new int[]{432, 46, 70, 25};
        this.mXYoubiao = 0;
        this.runnable = new Runnable() {
            public void run() {
                RadioMainActivity.mRunCnt = RadioMainActivity.mRunCnt + 1;
                RadioMainActivity.mHandler.postDelayed(this, 30);
            }
        };
        this.mOrientation = 0;
        this.onPtyOK = new RdsDlg.onInputOK() {
            public void onOK(int id) {
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        this.aPort = 3;
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        this.mOrientation = getResources().getConfiguration().orientation;
        updateRadioLayout(this.mOrientation);
        dbgInit();
    }

    private void updateRadioLayout(int curOrientation) {
        if (curOrientation == 1) {
            Log.d(TAG, "onConfigurationChanged 分屏模式");
            this.isInMultiWindowMode = true;
            setContentView(R.layout.activity_radio_main_windowmode);
            setData();
            initUI();
            setWindowDrawable();
            return;
        }
        Log.d(TAG, "onConfigurationChanged 横屏模式");
        this.isInMultiWindowMode = false;
        setContentView(R.layout.activity_radio_main);
        setData();
        initUI();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "mOrientation = " + this.mOrientation + "---newConfig.orientation=" + newConfig.orientation);
        if (this.mOrientation != newConfig.orientation) {
            this.mOrientation = newConfig.orientation;
            updateRadioLayout(this.mOrientation);
            FIRST_UPDATE = true;
            MainSet.PushActivityForMul(1, this.isInMultiWindowMode);
            this.mLastBand = -1;
            this.mCurBand = -1;
            this.mCurtotalStep = 1;
            RdsShow(Radio.GetDisp(2));
            this.mXYoubiao = this.xAdjMin;
            MainTask.GetInstance().SetRadioUserCallBack(this);
        }
    }

    public void onMultiWindowModeChanged(boolean isInMultiWindowMode2, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode2, newConfig);
        if (this.mOrientation != newConfig.orientation) {
            this.mOrientation = newConfig.orientation;
            updateRadioLayout(this.mOrientation);
            FIRST_UPDATE = true;
            MainSet.PushActivityForMul(1, this.isInMultiWindowMode);
            this.mLastBand = -1;
            this.mCurBand = -1;
            this.mCurtotalStep = 1;
            RdsShow(Radio.GetDisp(2));
            this.mXYoubiao = this.xAdjMin;
            MainTask.GetInstance().SetRadioUserCallBack(this);
        }
    }

    private void dbgInit() {
    }

    public void onClick(View v) {
        int id = ((ParamButton) v).getIntParam();
        Log.i(TAG, "onClick " + this.mStrCmd[id]);
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
                new RadioFreqInput(this);
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
                int i = (id - 16) + 1;
                Radio.TuneMset((id - 16) + 1);
                if (this.mIvMainFreq != null) {
                    this.mIvMainFreq.invalidate();
                    return;
                }
                return;
            case 22:
                this.mEvc.evol_workmode_set(0);
                finish();
                return;
            case 23:
                RadioFunc.ShowRdsSet(this);
                return;
            default:
                return;
        }
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
        MainTask.GetInstance().SetRadioUserCallBack(this);
        FIRST_UPDATE = true;
        this.mEvc.evol_workmode_set(1);
        RdsShow(Radio.GetDisp(2));
        this.mXYoubiao = this.xAdjMin;
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetRadioUserCallBack((UserCallBack) null);
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
                Log.d(TAG, "curX = " + curX + ", mXYoubiao = " + this.mXYoubiao);
            }
        }
    }

    private String StepToFreq(int step, boolean fm) {
        int freq = Radio.StepToFreq(step);
        if (!fm) {
            return new StringBuilder(String.valueOf(freq)).toString();
        }
        return String.format("%.2f", new Object[]{Float.valueOf(((float) freq) / 100.0f)});
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
            Log.d(TAG, "SetStep invalidate");
            this.mIvMainFreq.invalidate();
            this.mAdjStep = step;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0266, code lost:
        if (i2b(r10 & 512) != false) goto L_0x0268;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0280, code lost:
        if (i2b(1048576 & r10) != false) goto L_0x0282;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02c3, code lost:
        if (i2b(33554432 & r10) != false) goto L_0x02c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00bb, code lost:
        if (i2b(r10 & 1) != false) goto L_0x00bd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0108, code lost:
        if (i2b(r10 & 2) != false) goto L_0x010a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0139, code lost:
        if (i2b(r10 & 4) != false) goto L_0x013b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x015b, code lost:
        if (i2b(r10 & 8) != false) goto L_0x015d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x017d, code lost:
        if (i2b(r10 & 16) != false) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0197, code lost:
        if (i2b(r10 & 32) != false) goto L_0x0199;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01b1, code lost:
        if (i2b(r10 & 64) != false) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01cb, code lost:
        if (i2b(r10 & 128) != false) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01e6, code lost:
        if (i2b(2097152 & r10) != false) goto L_0x01e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0200, code lost:
        if (i2b(4194304 & r10) != false) goto L_0x0202;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x021a, code lost:
        if (i2b(8388608 & r10) != false) goto L_0x021c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0234, code lost:
        if (i2b(16777216 & r10) != false) goto L_0x0236;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x024d, code lost:
        if (i2b(r10 & 256) != false) goto L_0x024f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onTimer() {
        /*
            r19 = this;
            r0 = r19
            boolean r11 = r0.mStepMode
            if (r11 == 0) goto L_0x001a
            long r1 = android.os.SystemClock.uptimeMillis()
            r0 = r19
            long r11 = r0.mStepTick
            r13 = 3000(0xbb8, double:1.482E-320)
            long r11 = r11 + r13
            int r11 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r11 <= 0) goto L_0x001a
            r11 = 0
            r0 = r19
            r0.mStepMode = r11
        L_0x001a:
            r11 = 2
            int r11 = com.yyw.ts70xhw.Radio.GetDisp(r11)
            r0 = r19
            r0.mCurBand = r11
            int r10 = com.yyw.ts70xhw.Radio.GetDispUpdate()
            int r3 = com.yyw.ts70xhw.Radio.GetDispFlag()
            r11 = 4
            int r9 = com.yyw.ts70xhw.Radio.GetDisp(r11)
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x0044
            r0 = r19
            int r11 = r0.mCurBand
            r0 = r19
            int r12 = r0.mLastBand
            if (r11 != r12) goto L_0x0044
            r0 = r19
            int r11 = r0.mCurtotalStep
            if (r11 == r9) goto L_0x00ac
        L_0x0044:
            r0 = r19
            int r11 = r0.mCurBand
            r0 = r19
            r0.RdsShow(r11)
            r10 = -1
            r0 = r19
            int r11 = r0.mCurBand
            r0 = r19
            r0.mLastBand = r11
            r0 = r19
            android.widget.ImageView r11 = r0.mIvBand
            int[] r12 = mBandNum
            r0 = r19
            int r13 = r0.mCurBand
            r12 = r12[r13]
            r11.setImageResource(r12)
            r0 = r19
            r0.mCurtotalStep = r9
            r0 = r19
            int r11 = r0.mCurBand
            r12 = 4
            if (r11 < r12) goto L_0x02ef
            r0 = r19
            android.widget.ImageView r11 = r0.mIvDW
            int r12 = com.ts.MainUI.R.drawable.radio_band_khz
            r11.setImageResource(r12)
        L_0x0079:
            r0 = r19
            boolean r11 = r0.mIsHaveMoveFreq
            if (r11 == 0) goto L_0x00ac
            r0 = r19
            android.widget.TextView r12 = r0.mTvAdjMin
            r13 = 0
            r0 = r19
            int r11 = r0.mCurBand
            r14 = 4
            if (r11 >= r14) goto L_0x02fa
            r11 = 1
        L_0x008c:
            r0 = r19
            java.lang.String r11 = r0.StepToFreq(r13, r11)
            r12.setText(r11)
            r0 = r19
            android.widget.TextView r12 = r0.mTvAdjMax
            int r13 = r9 + -1
            r0 = r19
            int r11 = r0.mCurBand
            r14 = 4
            if (r11 >= r14) goto L_0x02fd
            r11 = 1
        L_0x00a3:
            r0 = r19
            java.lang.String r11 = r0.StepToFreq(r13, r11)
            r12.setText(r11)
        L_0x00ac:
            r19.CheckFreqMove()
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x00bd
            r11 = r10 & 1
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x00fc
        L_0x00bd:
            java.lang.String r11 = "hddd"
            java.lang.String r12 = "UD_MFREQ update"
            android.util.Log.d(r11, r12)
            r0 = r19
            int r11 = r0.mCurBand
            r12 = 4
            if (r11 >= r12) goto L_0x0300
            r0 = r19
            android.widget.TextView r11 = r0.mTvFreq
            if (r11 == 0) goto L_0x00f5
            r0 = r19
            android.widget.TextView r11 = r0.mTvFreq
            java.lang.String r12 = "%.2f"
            r13 = 1
            java.lang.Object[] r13 = new java.lang.Object[r13]
            r14 = 0
            r15 = 1
            int r15 = com.yyw.ts70xhw.Radio.GetDisp(r15)
            double r15 = (double) r15
            r17 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r15 = r15 / r17
            java.lang.Double r15 = java.lang.Double.valueOf(r15)
            r13[r14] = r15
            java.lang.String r12 = java.lang.String.format(r12, r13)
            r11.setText(r12)
        L_0x00f5:
            r0 = r19
            com.ts.other.CustomImgView r11 = r0.mIvMainFreq
            r11.invalidate()
        L_0x00fc:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x010a
            r11 = r10 & 2
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x012d
        L_0x010a:
            java.lang.String r11 = "hddd"
            java.lang.String r12 = "UD_MEMNO update"
            android.util.Log.d(r11, r12)
            r4 = 0
        L_0x0114:
            r11 = 6
            if (r4 < r11) goto L_0x0312
            r11 = 3
            int r5 = com.yyw.ts70xhw.Radio.GetDisp(r11)
            if (r5 <= 0) goto L_0x012d
            r11 = 7
            if (r5 >= r11) goto L_0x012d
            r0 = r19
            com.ts.other.ParamButton[] r11 = r0.mBtnFreq
            int r12 = r5 + -1
            r11 = r11[r12]
            r12 = 1
            r11.setSelected(r12)
        L_0x012d:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x013b
            r11 = r10 & 4
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x014f
        L_0x013b:
            java.lang.String r11 = "hddd"
            java.lang.String r12 = "UD_MEM1 update"
            android.util.Log.d(r11, r12)
            r11 = 0
            r12 = 6
            int r12 = com.yyw.ts70xhw.Radio.GetDisp(r12)
            r0 = r19
            r0.updateMemFreq(r11, r12)
        L_0x014f:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x015d
            r11 = r10 & 8
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x0171
        L_0x015d:
            java.lang.String r11 = "hddd"
            java.lang.String r12 = "UD_MEM2 update"
            android.util.Log.d(r11, r12)
            r11 = 1
            r12 = 7
            int r12 = com.yyw.ts70xhw.Radio.GetDisp(r12)
            r0 = r19
            r0.updateMemFreq(r11, r12)
        L_0x0171:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x017f
            r11 = r10 & 16
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x018b
        L_0x017f:
            r11 = 2
            r12 = 8
            int r12 = com.yyw.ts70xhw.Radio.GetDisp(r12)
            r0 = r19
            r0.updateMemFreq(r11, r12)
        L_0x018b:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x0199
            r11 = r10 & 32
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x01a5
        L_0x0199:
            r11 = 3
            r12 = 9
            int r12 = com.yyw.ts70xhw.Radio.GetDisp(r12)
            r0 = r19
            r0.updateMemFreq(r11, r12)
        L_0x01a5:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x01b3
            r11 = r10 & 64
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x01bf
        L_0x01b3:
            r11 = 4
            r12 = 10
            int r12 = com.yyw.ts70xhw.Radio.GetDisp(r12)
            r0 = r19
            r0.updateMemFreq(r11, r12)
        L_0x01bf:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x01cd
            r11 = r10 & 128(0x80, float:1.794E-43)
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x01d9
        L_0x01cd:
            r11 = 5
            r12 = 11
            int r12 = com.yyw.ts70xhw.Radio.GetDisp(r12)
            r0 = r19
            r0.updateMemFreq(r11, r12)
        L_0x01d9:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x01e8
            r11 = 2097152(0x200000, float:2.938736E-39)
            r11 = r11 & r10
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x01f3
        L_0x01e8:
            r0 = r19
            android.widget.ImageView r11 = r0.mIvAf
            r12 = r3 & 32
            r0 = r19
            r0.showView(r11, r12)
        L_0x01f3:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x0202
            r11 = 4194304(0x400000, float:5.877472E-39)
            r11 = r11 & r10
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x020d
        L_0x0202:
            r0 = r19
            android.widget.ImageView r11 = r0.mIvTa
            r12 = r3 & 64
            r0 = r19
            r0.showView(r11, r12)
        L_0x020d:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x021c
            r11 = 8388608(0x800000, float:1.17549435E-38)
            r11 = r11 & r10
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x0227
        L_0x021c:
            r0 = r19
            android.widget.ImageView r11 = r0.mIvTp
            r12 = r3 & 128(0x80, float:1.794E-43)
            r0 = r19
            r0.showView(r11, r12)
        L_0x0227:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x0236
            r11 = 16777216(0x1000000, float:2.3509887E-38)
            r11 = r11 & r10
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x0241
        L_0x0236:
            r0 = r19
            android.widget.ImageView r11 = r0.mIvEon
            r12 = r3 & 16
            r0 = r19
            r0.showView(r11, r12)
        L_0x0241:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x024f
            r11 = r10 & 256(0x100, float:3.59E-43)
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x025a
        L_0x024f:
            r0 = r19
            android.widget.ImageView r11 = r0.mIvStR
            r12 = r3 & 1
            r0 = r19
            r0.showView(r11, r12)
        L_0x025a:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x0268
            r11 = r10 & 512(0x200, float:7.175E-43)
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x0273
        L_0x0268:
            r0 = r19
            android.widget.ImageView r11 = r0.mIvStS
            r12 = r3 & 2
            r0 = r19
            r0.showView(r11, r12)
        L_0x0273:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x0282
            r11 = 1048576(0x100000, float:1.469368E-39)
            r11 = r11 & r10
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x02b6
        L_0x0282:
            java.lang.String r11 = "RadioMainActivity1"
            java.lang.String r12 = "update & Radio.UD_PS"
            android.util.Log.d(r11, r12)
            r0 = r19
            char[] r11 = r0.mcPs
            com.yyw.ts70xhw.Radio.GetPsName(r11)
            r0 = r19
            char[] r11 = r0.mcPs
            r12 = 0
            char r11 = r11[r12]
            if (r11 != 0) goto L_0x0320
            r0 = r19
            android.widget.TextView r11 = r0.mTvPs
            java.lang.String r12 = ""
            r11.setText(r12)
            r0 = r19
            android.widget.TextView r11 = r0.mTvFreq
            r12 = 8
            r11.setVisibility(r12)
            r0 = r19
            com.ts.other.CustomImgView r11 = r0.mIvMainFreq
            r12 = 0
            r11.setVisibility(r12)
        L_0x02b6:
            boolean r11 = FIRST_UPDATE
            if (r11 != 0) goto L_0x02c5
            r11 = 33554432(0x2000000, float:9.403955E-38)
            r11 = r11 & r10
            r0 = r19
            boolean r11 = r0.i2b(r11)
            if (r11 == 0) goto L_0x02e7
        L_0x02c5:
            r11 = 65280(0xff00, float:9.1477E-41)
            r11 = r11 & r3
            int r6 = r11 >> 8
            r11 = 30
            if (r6 >= r11) goto L_0x03cb
            if (r6 <= 0) goto L_0x03cb
            r0 = r19
            char[] r11 = r0.mcPty
            com.yyw.ts70xhw.Radio.GetPtyStr(r11, r6)
            r0 = r19
            android.widget.TextView r11 = r0.mTvCurPty
            r0 = r19
            char[] r12 = r0.mcPty
            java.lang.String r12 = java.lang.String.valueOf(r12)
            r11.setText(r12)
        L_0x02e7:
            boolean r11 = FIRST_UPDATE
            if (r11 == 0) goto L_0x02ee
            r11 = 0
            FIRST_UPDATE = r11
        L_0x02ee:
            return
        L_0x02ef:
            r0 = r19
            android.widget.ImageView r11 = r0.mIvDW
            int r12 = com.ts.MainUI.R.drawable.radio_band_mhz
            r11.setImageResource(r12)
            goto L_0x0079
        L_0x02fa:
            r11 = 0
            goto L_0x008c
        L_0x02fd:
            r11 = 0
            goto L_0x00a3
        L_0x0300:
            r0 = r19
            android.widget.TextView r11 = r0.mTvFreq
            if (r11 == 0) goto L_0x00f5
            r0 = r19
            android.widget.TextView r11 = r0.mTvFreq
            java.lang.String r12 = ""
            r11.setText(r12)
            goto L_0x00f5
        L_0x0312:
            r0 = r19
            com.ts.other.ParamButton[] r11 = r0.mBtnFreq
            r11 = r11[r4]
            r12 = 0
            r11.setSelected(r12)
            int r4 = r4 + 1
            goto L_0x0114
        L_0x0320:
            r0 = r19
            android.widget.TextView r11 = r0.mTvFreq
            r12 = 0
            r11.setVisibility(r12)
            r0 = r19
            com.ts.other.CustomImgView r11 = r0.mIvMainFreq
            r12 = 8
            r11.setVisibility(r12)
            r11 = 1
            r0 = r19
            char[] r12 = r0.mcPs
            r13 = 0
            char r12 = r12[r13]
            if (r11 != r12) goto L_0x0372
            java.lang.String r8 = new java.lang.String
            r0 = r19
            char[] r11 = r0.mcPs
            r12 = 1
            r13 = 8
            r8.<init>(r11, r12, r13)
            r0 = r19
            android.widget.TextView r11 = r0.mTvPs
            java.lang.CharSequence r7 = r11.getText()
            java.lang.String r7 = (java.lang.String) r7
            if (r8 == 0) goto L_0x02b6
            if (r7 == 0) goto L_0x02b6
            boolean r11 = r8.equals(r7)
            if (r11 != 0) goto L_0x02b6
            r0 = r19
            android.widget.TextView r11 = r0.mTvPs
            r12 = -1
            r11.setTextColor(r12)
            r0 = r19
            android.widget.TextView r11 = r0.mTvPs
            r11.setText(r8)
            java.lang.String r11 = "Test"
            android.util.Log.d(r11, r8)
            goto L_0x02b6
        L_0x0372:
            r11 = 2
            r0 = r19
            char[] r12 = r0.mcPs
            r13 = 0
            char r12 = r12[r13]
            if (r11 != r12) goto L_0x03ae
            java.lang.String r8 = new java.lang.String
            r0 = r19
            char[] r11 = r0.mcPs
            r12 = 1
            r13 = 8
            r8.<init>(r11, r12, r13)
            r0 = r19
            android.widget.TextView r11 = r0.mTvPs
            java.lang.CharSequence r7 = r11.getText()
            java.lang.String r7 = (java.lang.String) r7
            if (r8 == 0) goto L_0x02b6
            if (r7 == 0) goto L_0x02b6
            boolean r11 = r8.equals(r7)
            if (r11 != 0) goto L_0x02b6
            r0 = r19
            android.widget.TextView r11 = r0.mTvPs
            r12 = -65536(0xffffffffffff0000, float:NaN)
            r11.setTextColor(r12)
            r0 = r19
            android.widget.TextView r11 = r0.mTvPs
            r11.setText(r8)
            goto L_0x02b6
        L_0x03ae:
            r0 = r19
            android.widget.TextView r11 = r0.mTvPs
            java.lang.String r12 = ""
            r11.setText(r12)
            r0 = r19
            android.widget.TextView r11 = r0.mTvFreq
            r12 = 8
            r11.setVisibility(r12)
            r0 = r19
            com.ts.other.CustomImgView r11 = r0.mIvMainFreq
            r12 = 0
            r11.setVisibility(r12)
            goto L_0x02b6
        L_0x03cb:
            r0 = r19
            android.widget.TextView r11 = r0.mTvCurPty
            java.lang.String r12 = ""
            r11.setText(r12)
            goto L_0x02e7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.main.radio.RadioMainActivity.onTimer():void");
    }

    private void updateMemFreq(int mem, int freq) {
        if (this.mCurBand >= 4) {
            this.mBtnFreq[mem].setText(new StringBuilder(String.valueOf(freq)).toString());
            return;
        }
        Radio.GetMemPsName(mem + 1, this.mcFreq);
        if (1 == this.mcFreq[0]) {
            this.mBtnFreq[mem].setText(new String(this.mcFreq, 1, 8));
            if (this.isInMultiWindowMode) {
                this.mBtnFreq[mem].setTextSize(0, 25.0f);
            } else {
                this.mBtnFreq[mem].setTextSize(0, 35.0f);
            }
        } else {
            this.mBtnFreq[mem].setText(String.format("%.2f", new Object[]{Double.valueOf(((double) freq) / 100.0d)}));
            if (this.isInMultiWindowMode) {
                this.mBtnFreq[mem].setTextSize(0, 30.0f);
            } else {
                this.mBtnFreq[mem].setTextSize(0, 35.0f);
            }
        }
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

    private void setData() {
        if (this.isInMultiWindowMode) {
            this.ptBtnRds = this.ptBtnRds_s;
            this.ptBtnAf = this.ptBtnAf_s;
            this.ptBtnTa = this.ptBtnTa_s;
            this.ptBtnPty = this.ptBtnPty_s;
            this.ptBtnSound = this.ptBtnSound_s;
            this.ptBtnSt = this.ptBtnSt_s;
            this.ptBtnBand = this.ptBtnBand_s;
            this.ptBtnFm = this.ptBtnFm_s;
            this.ptBtnAm = this.ptBtnAm_s;
            this.ptBtnClose = this.ptBtnClose_s;
            this.ptBtnAms = this.ptBtnAms_s;
            this.ptBtnEq = this.ptBtnEq_s;
            this.ptBtnSkDec = this.ptBtnSkDec_s;
            this.ptBtnSkInc = this.ptBtnSkInc_s;
            this.ptIvAf = this.ptIvAf_s;
            this.ptIvTa = this.ptIvTa_s;
            this.ptIvTp = this.ptIvTp_s;
            this.ptIvEon = this.ptIvEon_s;
            this.ptIvStR = this.ptIvStR_s;
            this.ptIvStS = this.ptIvStS_s;
            this.ptIvBand = this.ptIvBand_s;
            this.ptIvBndDw = this.ptIvBndDw_s;
            this.ptIvPty = this.ptIvPty_s;
            this.ptIvPs = this.ptIvPs_s;
            this.ptBtnFreq1 = this.ptBtnFreq1_s;
            this.ptBtnFreq2 = this.ptBtnFreq2_s;
            this.ptBtnFreq3 = this.ptBtnFreq3_s;
            this.ptBtnFreq4 = this.ptBtnFreq4_s;
            this.ptBtnFreq5 = this.ptBtnFreq5_s;
            this.ptBtnFreq6 = this.ptBtnFreq6_s;
            this.TvFreq = this.TvFreq_s;
            this.ptIvMF = this.ptIvMF_s;
            this.ptMFNum = this.ptMFNum_s;
            this.xMFDt = this.xMFDt_s;
            this.yMFDt = this.yMFDt_s;
            this.yMFNum = this.yMFNum_s;
            this.xMFDot = this.xMFDot_s;
            this.yMFDot = this.yMFDot_s;
            this.amXStart = this.amXStart_s;
            this.amXdt = this.amXdt_s;
            this.ptFreqAdj = this.ptFreqAdj_s;
            this.xAdjMin = this.xAdjMin_s;
            this.xAdjMax = this.xAdjMax_s;
            this.YoubiaoOffsetY = 0;
            this.wYoubiao = this.wYoubiao_s;
            this.ptAdjTxtMin = this.ptAdjTxtMin_s;
            this.ptAdjTxtMax = this.ptAdjTxtMax_s;
            mBandNum = mBandNum_s;
            return;
        }
        this.ptBtnRds = this.ptBtnRds_b;
        this.ptBtnAf = this.ptBtnAf_b;
        this.ptBtnTa = this.ptBtnTa_b;
        this.ptBtnPty = this.ptBtnPty_b;
        this.ptBtnSound = this.ptBtnSound_b;
        this.ptBtnSt = this.ptBtnSt_b;
        this.ptBtnBand = this.ptBtnBand_b;
        this.ptBtnFm = this.ptBtnFm_b;
        this.ptBtnAm = this.ptBtnAm_b;
        this.ptBtnClose = this.ptBtnClose_b;
        this.ptBtnAms = this.ptBtnAms_b;
        this.ptBtnEq = this.ptBtnEq_b;
        this.ptBtnSkDec = this.ptBtnSkDec_b;
        this.ptBtnSkInc = this.ptBtnSkInc_b;
        this.ptIvAf = this.ptIvAf_b;
        this.ptIvTa = this.ptIvTa_b;
        this.ptIvTp = this.ptIvTp_b;
        this.ptIvEon = this.ptIvEon_b;
        this.ptIvStR = this.ptIvStR_b;
        this.ptIvStS = this.ptIvStS_b;
        this.ptIvBand = this.ptIvBand_b;
        this.ptIvBndDw = this.ptIvBndDw_b;
        this.ptIvPty = this.ptIvPty_b;
        this.ptIvPs = this.ptIvPs_b;
        this.ptBtnFreq1 = this.ptBtnFreq1_b;
        this.ptBtnFreq2 = this.ptBtnFreq2_b;
        this.ptBtnFreq3 = this.ptBtnFreq3_b;
        this.ptBtnFreq4 = this.ptBtnFreq4_b;
        this.ptBtnFreq5 = this.ptBtnFreq5_b;
        this.ptBtnFreq6 = this.ptBtnFreq6_b;
        this.TvFreq = this.TvFreq_b;
        this.ptIvMF = this.ptIvMF_b;
        this.ptMFNum = this.ptMFNum_b;
        this.xMFDt = this.xMFDt_b;
        this.yMFDt = this.yMFDt_b;
        this.yMFNum = this.yMFNum_b;
        this.xMFDot = this.xMFDot_b;
        this.yMFDot = this.yMFDot_b;
        this.amXStart = this.amXStart_b;
        this.amXdt = this.amXdt_b;
        this.ptFreqAdj = this.ptFreqAdj_b;
        this.xAdjMin = this.xAdjMin_b;
        this.xAdjMax = this.xAdjMax_b;
        this.YoubiaoOffsetY = 0;
        this.wYoubiao = this.wYoubiao_b;
        this.ptAdjTxtMin = this.ptAdjTxtMin_b;
        this.ptAdjTxtMax = this.ptAdjTxtMax_b;
        mBandNum = mBandNum_b;
    }

    private void setWindowDrawable() {
        this.mBtnSt.setStateUpDn(R.drawable.radio_button_horn_up_s, R.drawable.radio_button_horn_dn_s);
        this.mBtnFm.setStateUpDn(R.drawable.radio_button_fm_up_s, R.drawable.radio_button_fm_dn_s);
        if (MainSet.isHaveAMBand()) {
            this.mBtnAm.setStateUpDn(R.drawable.radio_button_am_up_s, R.drawable.radio_button_am_dn_s);
            this.mBtnAm.setIntParam(4);
        } else {
            this.mBtnAm.setStateUpDn(R.drawable.radio_button_kb_up_s, R.drawable.radio_button_kb_dn_s);
            this.mBtnAm.setIntParam(5);
        }
        this.mBtnAms.setStateUpDn(R.drawable.radio_button_store_up_s, R.drawable.radio_button_store_dn_s);
        this.mBtnEq.setStateUpDn(R.drawable.radio_button_eq_up_s, R.drawable.radio_button_eq_dn_s);
        this.mBtnSeekDec.setStateUpDn(R.drawable.radio_button_vup_up_s, R.drawable.radio_button_vup_dn_s);
        this.mBtnSeekInc.setStateUpDn(R.drawable.radio_button_vdn_up_s, R.drawable.radio_button_vdn_dn_s);
        this.mIvAf = IvCreateRelative(this.ptIvAf[0], this.ptIvAf[1], this.ptIvAf[2], this.ptIvAf[3], R.drawable.radio_af_s);
        this.mIvTa = IvCreateRelative(this.ptIvTa[0], this.ptIvTa[1], this.ptIvTa[2], this.ptIvTa[3], R.drawable.radio_ta_s);
        this.mIvTp = IvCreateRelative(this.ptIvTp[0], this.ptIvTp[1], this.ptIvTp[2], this.ptIvTp[3], R.drawable.radio_tp_s);
        this.mIvEon = IvCreateRelative(this.ptIvEon[0], this.ptIvEon[1], this.ptIvEon[2], this.ptIvEon[3], R.drawable.radio_eon_s);
        this.mIvStR = IvCreateRelative(this.ptIvStR[0], this.ptIvStR[1], this.ptIvStR[2], this.ptIvStR[3], R.drawable.radio_stereo_s);
        this.mIvStS = IvCreateRelative(this.ptIvStS[0], this.ptIvStS[1], this.ptIvStS[2], this.ptIvStS[3], R.drawable.radio_st_s);
        for (int i = 0; i < this.mBtnFreq.length; i++) {
            this.mBtnFreq[i].setStateDrawable(R.drawable.radio_rect_up, R.drawable.radio_rect_up, R.drawable.radio_rect_up);
            this.mBtnFreq[i].setPadding(0, 0, 0, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void initUI() {
        if (this.isInMultiWindowMode) {
            this.mLayout = (RelativeLayout) findViewById(R.id.rad_main_layout2);
        } else {
            this.mLayout = (RelativeLayout) findViewById(R.id.rad_main_layout);
        }
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
        this.mBtnMute = ParamButton.fsCreateRelative(this.ptBtnSound[0], this.ptBtnSound[1], this.ptBtnSound[2], this.ptBtnSound[3]);
        this.mBtnMute.setStateUpDn(R.drawable.radio_button_sound_up, R.drawable.radio_button_sound_dn);
        this.mBtnMute.setIntParam(1);
        this.mBtnList.add(this.mBtnMute);
        this.mBtnSt = ParamButton.fsCreateRelative(this.ptBtnSt[0], this.ptBtnSt[1], this.ptBtnSt[2], this.ptBtnSt[3]);
        this.mBtnSt.setStateUpDn(R.drawable.radio_button_horn_up, R.drawable.radio_button_horn_dn);
        this.mBtnSt.setIntParam(2);
        this.mBtnList.add(this.mBtnSt);
        this.mBtnClose = ParamButton.fsCreateRelative(this.ptBtnClose[0], this.ptBtnClose[1], this.ptBtnClose[2], this.ptBtnClose[3]);
        this.mBtnClose.setStateUpDn(R.drawable.radio_button_close_up, R.drawable.radio_button_close_dn);
        this.mBtnClose.setIntParam(22);
        this.mBtnList.add(this.mBtnClose);
        this.mBtnFm = ParamButton.fsCreateRelative(this.ptBtnFm[0], this.ptBtnFm[1], this.ptBtnFm[2], this.ptBtnFm[3]);
        this.mBtnFm.setStateUpDn(R.drawable.radio_button_fm_up, R.drawable.radio_button_fm_dn);
        this.mBtnFm.setIntParam(3);
        this.mBtnList.add(this.mBtnFm);
        this.mBtnAm = ParamButton.fsCreateRelative(this.ptBtnAm[0], this.ptBtnAm[1], this.ptBtnAm[2], this.ptBtnAm[3]);
        if (MainSet.isHaveAMBand()) {
            this.mBtnAm.setStateUpDn(R.drawable.radio_button_am_up, R.drawable.radio_button_am_dn);
            this.mBtnAm.setIntParam(4);
        } else {
            this.mBtnAm.setStateUpDn(R.drawable.radio_button_kb_up, R.drawable.radio_button_kb_dn);
            this.mBtnAm.setIntParam(5);
        }
        this.mBtnList.add(this.mBtnAm);
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
            this.mBtnFreq[i].setColor(this.MemUpColor, this.MemDnColor);
            this.mBtnFreq[i].setTextSize(0, 45.0f);
            this.mBtnFreq[i].setPadding(50, 0, 0, 0);
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
        this.mTvFreq = TvCreateRelative(this.TvFreq[0], this.TvFreq[1], this.TvFreq[2], this.TvFreq[3]);
        this.mTvFreq.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTvFreq.setTextSize(0, 25.0f);
        this.mTvFreq.setGravity(17);
        this.mTvFreq.setTextColor(-1);
        this.mTvFreq.setVisibility(8);
        this.mTvCurPty = TvCreateRelative(this.ptIvPty[0], this.ptIvPty[1], this.ptIvPty[2], this.ptIvPty[3]);
        this.mTvCurPty.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTvCurPty.setTextSize(0, 25.0f);
        this.mTvCurPty.setGravity(81);
        this.mTvCurPty.setTextColor(-1);
        this.mTvCurPty.setVisibility(4);
        this.mTvPs = TvCreateRelative(this.ptIvMF[0], this.ptIvMF[1], this.ptIvMF[2], this.ptIvMF[3]);
        this.mTvPs.setText(TXZResourceManager.STYLE_DEFAULT);
        this.mTvPs.setTextSize(0, 50.0f);
        this.mTvPs.setGravity(81);
        this.mTvPs.setTextColor(-1);
        this.mTvPs.setVisibility(4);
        this.mIvMainFreq = new CustomImgView(this);
        setViewPos(this.mIvMainFreq, this.ptIvMF[0], this.ptIvMF[1], this.ptIvMF[2], this.ptIvMF[3]);
        this.mIvMainFreq.setDrawDt(this.xMFDt, this.yMFDt);
        this.mIvMainFreq.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                int curFreq;
                Log.d(RadioMainActivity.TAG, "userPaint");
                if (RadioMainActivity.this.mbAdjMove) {
                    curFreq = Radio.StepToFreq(RadioMainActivity.this.mAdjStep);
                } else {
                    curFreq = Radio.GetDisp(1);
                }
                int nBand = RadioMainActivity.this.mCurBand;
                Radio.GetPsName(RadioMainActivity.this.mcPs);
                if (RadioMainActivity.this.mcPs[0] == 0) {
                    if (nBand >= 4) {
                        if (curFreq > 999) {
                            view.drawImage(RadioMainActivity.mFreqNum[1], RadioMainActivity.this.amXStart + (RadioMainActivity.this.amXdt * 0), RadioMainActivity.this.yMFNum);
                        }
                        view.drawImage(RadioMainActivity.mFreqNum[(curFreq % 1000) / 100], RadioMainActivity.this.amXStart + (RadioMainActivity.this.amXdt * 1), RadioMainActivity.this.yMFNum);
                        view.drawImage(RadioMainActivity.mFreqNum[(curFreq % 100) / 10], RadioMainActivity.this.amXStart + (RadioMainActivity.this.amXdt * 2), RadioMainActivity.this.yMFNum);
                        view.drawImage(RadioMainActivity.mFreqNum[curFreq % 10], RadioMainActivity.this.amXStart + (RadioMainActivity.this.amXdt * 3), RadioMainActivity.this.yMFNum);
                    } else {
                        if (curFreq > 9999) {
                            view.drawImage(RadioMainActivity.mFreqNum[1], RadioMainActivity.this.ptMFNum[0], RadioMainActivity.this.yMFNum);
                        }
                        view.drawImage(RadioMainActivity.mFreqNum[(curFreq % TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT) / 1000], RadioMainActivity.this.ptMFNum[1], RadioMainActivity.this.yMFNum);
                        view.drawImage(RadioMainActivity.mFreqNum[(curFreq % 1000) / 100], RadioMainActivity.this.ptMFNum[2], RadioMainActivity.this.yMFNum);
                        view.drawImage(R.drawable.radio_point_up, RadioMainActivity.this.xMFDot, RadioMainActivity.this.yMFDot);
                        view.drawImage(RadioMainActivity.mFreqNum[(curFreq % 100) / 10], RadioMainActivity.this.ptMFNum[3], RadioMainActivity.this.yMFNum);
                        view.drawImage(RadioMainActivity.mFreqNum[curFreq % 10], RadioMainActivity.this.ptMFNum[4], RadioMainActivity.this.yMFNum);
                    }
                }
                return false;
            }
        });
        if (this.mIsHaveMoveFreq) {
            this.mIvFreqAdj = new CustomImgView(this);
            setViewPos(this.mIvFreqAdj, this.ptFreqAdj[0], this.ptFreqAdj[1], this.ptFreqAdj[2], this.ptFreqAdj[3]);
            this.mIvFreqAdj.setUserPaint(new CustomImgView.onPaint() {
                public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                    Log.d(RadioMainActivity.TAG, "userPaint Youbiao x = " + ((RadioMainActivity.this.mXYoubiao - RadioMainActivity.this.ptFreqAdj[0]) - (RadioMainActivity.this.wYoubiao / 2)));
                    view.drawImage(R.drawable.radio_move_fore, (RadioMainActivity.this.mXYoubiao - RadioMainActivity.this.ptFreqAdj[0]) - (RadioMainActivity.this.wYoubiao / 2), RadioMainActivity.this.YoubiaoOffsetY);
                    return false;
                }
            });
            this.mIvFreqAdj.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case 0:
                            Log.d(RadioMainActivity.TAG, "ACTION_DOWN");
                            RadioMainActivity.this.mbAdjMove = true;
                            RadioMainActivity.this.SetStep(RadioMainActivity.this.PtToStep(event.getRawX()));
                            break;
                        case 1:
                            Log.d(RadioMainActivity.TAG, "ACTION_UP");
                            if (RadioMainActivity.this.mbAdjMove) {
                                Radio.TuneFset(RadioMainActivity.this.mAdjStep);
                                RadioMainActivity.this.mbAdjMove = false;
                                break;
                            }
                            break;
                        case 2:
                            Log.d(RadioMainActivity.TAG, "ACTION_MOVE");
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
            this.mTvAdjMin.setTextSize(0, 20.0f);
            this.mTvAdjMin.setTextColor(this.MemUpColor);
            this.mTvAdjMax = TvCreateRelative(this.ptAdjTxtMax[0], this.ptAdjTxtMax[1], this.ptAdjTxtMax[2], this.ptAdjTxtMax[3]);
            this.mTvAdjMax.setTextSize(0, 20.0f);
            this.mTvAdjMax.setTextColor(this.MemUpColor);
            if (this.freqCenter) {
                this.mTvAdjMin.setGravity(17);
                this.mTvAdjMax.setGravity(17);
            } else {
                this.mTvAdjMin.setGravity(19);
                this.mTvAdjMax.setGravity(21);
            }
        }
        for (int i2 = 0; i2 < this.mBtnList.size(); i2++) {
            this.mBtnList.get(i2).setOnClickListener(this);
        }
        onTimer();
    }

    public boolean onLongClick(View v) {
        int id = ((ParamButton) v).getIntParam();
        Log.i(TAG, "onLongClick " + this.mStrCmd[id]);
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

    public void onWindowFocusChanged(boolean focus) {
        if (focus && this.mEvc.GetWorkMode() != 1) {
            this.mEvc.evol_workmode_set(1);
        }
        super.onWindowFocusChanged(focus);
    }
}
