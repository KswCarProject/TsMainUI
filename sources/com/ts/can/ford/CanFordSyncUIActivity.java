package com.ts.can.ford;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.KeyDef;

public class CanFordSyncUIActivity extends CanFordBaseActivity implements UserCallBack, View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {
    public static final int ITEM_DLG_BTN0 = 37;
    public static final int ITEM_DLG_BTN1 = 38;
    public static final int ITEM_DLG_BTN2 = 39;
    public static final int ITEM_DLG_BTN3 = 40;
    public static final int ITEM_DLG_LINE0 = 32;
    public static final int ITEM_DLG_LINE1 = 33;
    public static final int ITEM_DLG_LINE2 = 34;
    public static final int ITEM_DLG_LINE3 = 35;
    public static final int ITEM_DLG_LINE4 = 36;
    public static final int ITEM_LT_KB = 4;
    public static final int ITEM_LT_MUSIC = 1;
    public static final int ITEM_LT_PHONE = 2;
    public static final int ITEM_LT_SPEECH = 3;
    public static final int ITEM_MENU_BTN0 = 21;
    public static final int ITEM_MENU_BTN1 = 22;
    public static final int ITEM_MENU_BTN2 = 23;
    public static final int ITEM_MENU_BTN3 = 24;
    public static final int ITEM_MENU_LINE0 = 16;
    public static final int ITEM_MENU_LINE1 = 17;
    public static final int ITEM_MENU_LINE2 = 18;
    public static final int ITEM_MENU_LINE3 = 19;
    public static final int ITEM_MENU_LINE4 = 20;
    public static final int ITEM_RT_BTN = 80;
    public static final String TAG = "CanFordSyncUIActivity";
    protected static DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    public static final int[] mDlgBgArr = {R.drawable.can_sync_box01, R.drawable.can_sync_box02, R.drawable.can_sync_box03, R.drawable.can_sync_box04, R.drawable.can_sync_box05, R.drawable.can_sync_box};
    public static final int[] mDlgYArr = {218, 182, 144, 114, 84, 48};
    public static final int[] mDlgYArr_768x1024 = new int[6];
    public static final int[] mIcoBatArr = {R.drawable.can_sync_stutas_61, R.drawable.can_sync_stutas_62, R.drawable.can_sync_stutas_63, R.drawable.can_sync_stutas_64, R.drawable.can_sync_stutas_65};
    public static final int[] mIcoSigArr = {R.drawable.can_sync_stutas_67, R.drawable.can_sync_stutas_68, R.drawable.can_sync_stutas_69, R.drawable.can_sync_stutas_6a, R.drawable.can_sync_stutas_6b};
    public static final int[] mIconArr;
    public static boolean mIsJump = false;
    public static boolean mIsNeedFinish = false;
    public static boolean mIsSyncWin = false;
    protected static boolean mVerUS;
    protected ParamButton mBtnCall;
    protected ParamButton mBtnDev;
    protected ParamButton mBtnDn;
    protected ParamButton mBtnHang;
    protected ParamButton mBtnInfo;
    protected ParamButton mBtnLeft;
    protected ParamButton mBtnLtKeyboard;
    protected ParamButton mBtnLtMusic;
    protected ParamButton mBtnLtPhone;
    protected ParamButton mBtnLtSpeech;
    protected ParamButton mBtnMenu;
    protected ParamButton mBtnNext;
    protected ParamButton[] mBtnNum = new ParamButton[10];
    protected ParamButton mBtnNumJ;
    protected ParamButton mBtnNumX;
    protected ParamButton mBtnOK;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnRight;
    protected ParamButton mBtnRnd;
    protected ParamButton mBtnUp;
    protected int mDlgBtnNum;
    protected SyncSKey[] mDlgKey = new SyncSKey[4];
    protected SyncLine[] mDlgLine = new SyncLine[5];
    protected int mDlgLineNum;
    protected int mDlgType;
    protected CustomImgView mIcoBattery;
    protected CustomImgView mIcoBt;
    protected CustomImgView mIcoCall;
    protected CustomImgView mIcoCurSrc;
    protected CustomImgView mIcoInfo;
    protected CustomImgView mIcoSM;
    protected CustomImgView mIcoSignal;
    protected CustomImgView mIcoSpk;
    protected CustomImgView mIcoSync;
    protected CustomImgView[] mImgDlgBg = new CustomImgView[6];
    protected long mLastTimeTick;
    protected RelativeLayout mLayoutDlg;
    protected RelativeLayout mLayoutMenu;
    protected RelativeLayout mLayoutUS;
    protected RelativeLayoutManager mManDlg;
    protected RelativeLayoutManager mManMenu;
    protected RelativeLayoutManager mManUS;
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.SyncMediaTime mMediaTimeData = new CanDataInfo.SyncMediaTime();
    protected CanDataInfo.SyncMenuInfo mMenuInfoData = new CanDataInfo.SyncMenuInfo();
    protected SyncSKey[] mMenuKey = new SyncSKey[4];
    protected SyncLine[] mMenuLine = new SyncLine[5];
    protected CanDataInfo.SyncPhoneTime mPhoneTimeData = new CanDataInfo.SyncPhoneTime();
    private Runnable mSendTask = new Runnable() {
        public void run() {
            if (CanFordSyncUIActivity.this.mView != null) {
                CanFordSyncUIActivity.this.sendKey(CanFordSyncUIActivity.this.mView);
            }
        }
    };
    protected CanDataInfo.SyncStatus mStatusData = new CanDataInfo.SyncStatus();
    protected CanDataInfo.SyncStr mStrDnData = new CanDataInfo.SyncStr();
    protected CanDataInfo.SyncStr mStrShortData = new CanDataInfo.SyncStr();
    protected CanDataInfo.SyncStr mStrUpData = new CanDataInfo.SyncStr();
    protected CustomTextView mSyncTime;
    protected boolean mTimeUpdate;
    protected SyncLine[] mUSLine = new SyncLine[3];
    /* access modifiers changed from: private */
    public View mView;
    protected CanDataInfo.SyncWin mWinData = new CanDataInfo.SyncWin();
    protected int mX = Can.CAN_BENZ_SMART_OD;
    protected int mY = 48;
    protected boolean mfgShowKb;

    static {
        int[] iArr = new int[257];
        iArr[3] = R.drawable.can_sync_icon_03;
        iArr[6] = R.drawable.can_sync_icon_06;
        iArr[9] = R.drawable.can_sync_icon_09;
        iArr[11] = R.drawable.can_sync_icon_0b;
        iArr[13] = R.drawable.can_sync_icon_0d;
        iArr[17] = R.drawable.can_sync_icon_11;
        iArr[18] = R.drawable.can_sync_icon_12;
        iArr[21] = R.drawable.can_sync_icon_15;
        iArr[23] = R.drawable.can_sync_icon_17;
        iArr[28] = R.drawable.can_sync_icon_1c;
        iArr[29] = R.drawable.can_sync_icon_1d;
        iArr[30] = R.drawable.can_sync_icon_1e;
        iArr[31] = R.drawable.can_sync_icon_1f;
        iArr[33] = R.drawable.can_sync_icon_21;
        iArr[34] = R.drawable.can_sync_icon_22;
        iArr[35] = R.drawable.can_sync_icon_23;
        iArr[36] = R.drawable.can_sync_icon_24;
        iArr[37] = R.drawable.can_sync_icon_25;
        iArr[38] = R.drawable.can_sync_icon_26;
        iArr[39] = R.drawable.can_sync_icon_27;
        iArr[40] = R.drawable.can_sync_icon_28;
        iArr[41] = R.drawable.can_sync_icon_29;
        iArr[44] = R.drawable.can_sync_icon_2c;
        iArr[64] = R.drawable.can_sync_icon_40;
        iArr[65] = R.drawable.can_sync_icon_41;
        iArr[67] = R.drawable.can_sync_icon_43;
        iArr[68] = R.drawable.can_sync_icon_44;
        iArr[69] = R.drawable.can_sync_icon_45;
        iArr[70] = R.drawable.can_sync_icon_46;
        iArr[71] = R.drawable.can_sync_icon_47;
        iArr[73] = R.drawable.can_sync_icon_49;
        iArr[74] = R.drawable.can_sync_icon_4a;
        iArr[75] = R.drawable.can_sync_icon_4b;
        iArr[76] = R.drawable.can_sync_icon_4c;
        iArr[77] = R.drawable.can_sync_icon_4d;
        iArr[78] = R.drawable.can_sync_icon_4e;
        iArr[79] = R.drawable.can_sync_icon_4f;
        iArr[80] = R.drawable.can_sync_icon_50;
        iArr[81] = R.drawable.can_sync_icon_51;
        iArr[82] = R.drawable.can_sync_icon_52;
        iArr[83] = R.drawable.can_sync_icon_53;
        iArr[84] = R.drawable.can_sync_icon_54;
        iArr[85] = R.drawable.can_sync_icon_55;
        iArr[86] = R.drawable.can_sync_icon_56;
        iArr[87] = R.drawable.can_sync_icon_57;
        iArr[88] = R.drawable.can_sync_icon_58;
        iArr[89] = R.drawable.can_sync_icon_59;
        iArr[90] = R.drawable.can_sync_icon_5a;
        iArr[91] = R.drawable.can_sync_icon_5b;
        iArr[92] = R.drawable.can_sync_icon_5c;
        iArr[93] = R.drawable.can_sync_icon_5d;
        iArr[94] = R.drawable.can_sync_icon_5e;
        iArr[95] = R.drawable.can_sync_icon_5f;
        iArr[96] = R.drawable.can_sync_icon_60;
        iArr[97] = R.drawable.can_sync_icon_61;
        iArr[98] = R.drawable.can_sync_icon_62;
        iArr[99] = R.drawable.can_sync_icon_63;
        iArr[100] = R.drawable.can_sync_icon_64;
        iArr[101] = R.drawable.can_sync_icon_65;
        iArr[102] = R.drawable.can_sync_icon_66;
        iArr[103] = R.drawable.can_sync_icon_67;
        iArr[104] = R.drawable.can_sync_icon_68;
        iArr[105] = R.drawable.can_sync_icon_69;
        iArr[106] = R.drawable.can_sync_icon_6a;
        iArr[107] = R.drawable.can_sync_icon_6b;
        iArr[109] = R.drawable.can_sync_icon_6d;
        iArr[110] = R.drawable.can_sync_icon_6e;
        iArr[111] = R.drawable.can_sync_icon_6f;
        iArr[112] = R.drawable.can_sync_icon_70;
        iArr[113] = R.drawable.can_sync_icon_71;
        iArr[114] = R.drawable.can_sync_icon_72;
        iArr[115] = R.drawable.can_sync_icon_73;
        iArr[116] = R.drawable.can_sync_icon_74;
        iArr[117] = R.drawable.can_sync_icon_75;
        iArr[118] = R.drawable.can_sync_icon_76;
        iArr[119] = R.drawable.can_sync_icon_77;
        iArr[120] = R.drawable.can_sync_icon_78;
        iArr[121] = R.drawable.can_sync_icon_79;
        iArr[122] = R.drawable.can_sync_icon_7a;
        iArr[123] = R.drawable.can_sync_icon_7b;
        iArr[124] = R.drawable.can_sync_icon_7c;
        iArr[125] = R.drawable.can_sync_icon_7d;
        iArr[126] = R.drawable.can_sync_icon_7e;
        iArr[127] = R.drawable.can_sync_icon_7f;
        iArr[128] = R.drawable.can_sync_icon_80;
        iArr[129] = R.drawable.can_sync_icon_81;
        iArr[130] = R.drawable.can_sync_icon_82;
        iArr[131] = R.drawable.can_sync_icon_83;
        iArr[132] = R.drawable.can_sync_icon_84;
        iArr[133] = R.drawable.can_sync_icon_85;
        iArr[134] = R.drawable.can_sync_icon_86;
        iArr[135] = R.drawable.can_sync_icon_87;
        iArr[136] = R.drawable.can_sync_icon_88;
        iArr[137] = R.drawable.can_sync_icon_89;
        iArr[138] = R.drawable.can_sync_icon_8a;
        iArr[139] = R.drawable.can_sync_icon_8b;
        iArr[140] = R.drawable.can_sync_icon_8c;
        iArr[141] = R.drawable.can_sync_icon_8d;
        iArr[142] = R.drawable.can_sync_icon_8e;
        iArr[143] = R.drawable.can_sync_icon_8f;
        iArr[144] = R.drawable.can_sync_icon_90;
        iArr[145] = R.drawable.can_sync_icon_91;
        iArr[146] = R.drawable.can_sync_icon_92;
        iArr[147] = R.drawable.can_sync_icon_93;
        iArr[148] = R.drawable.can_sync_icon_94;
        iArr[149] = R.drawable.can_sync_icon_95;
        iArr[150] = R.drawable.can_sync_icon_96;
        iArr[151] = R.drawable.can_sync_icon_97;
        iArr[152] = R.drawable.can_sync_icon_98;
        iArr[153] = R.drawable.can_sync_icon_99;
        iArr[154] = R.drawable.can_sync_icon_9a;
        iArr[155] = R.drawable.can_sync_icon_9b;
        iArr[156] = R.drawable.can_sync_icon_9c;
        iArr[157] = R.drawable.can_sync_icon_9d;
        iArr[158] = R.drawable.can_sync_icon_9e;
        iArr[159] = R.drawable.can_sync_icon_9f;
        iArr[160] = R.drawable.can_sync_icon_a0;
        iArr[161] = R.drawable.can_sync_icon_a1;
        iArr[162] = R.drawable.can_sync_icon_a2;
        iArr[163] = R.drawable.can_sync_icon_a3;
        iArr[164] = R.drawable.can_sync_icon_a4;
        iArr[165] = R.drawable.can_sync_icon_a5;
        iArr[166] = R.drawable.can_sync_icon_a6;
        iArr[167] = R.drawable.can_sync_icon_a7;
        iArr[168] = R.drawable.can_sync_icon_a8;
        iArr[169] = R.drawable.can_sync_icon_a9;
        iArr[170] = R.drawable.can_sync_icon_aa;
        iArr[171] = R.drawable.can_sync_icon_ab;
        iArr[172] = R.drawable.can_sync_icon_ac;
        iArr[174] = R.drawable.can_sync_icon_ae;
        iArr[175] = R.drawable.can_sync_icon_af;
        iArr[176] = R.drawable.can_sync_icon_b0;
        iArr[177] = R.drawable.can_sync_icon_b1;
        iArr[178] = R.drawable.can_sync_icon_b2;
        iArr[179] = R.drawable.can_sync_icon_b3;
        iArr[180] = R.drawable.can_sync_icon_b4;
        iArr[181] = R.drawable.can_sync_icon_b5;
        iArr[182] = R.drawable.can_sync_icon_b6;
        iArr[183] = R.drawable.can_sync_icon_b7;
        iArr[184] = R.drawable.can_sync_icon_b8;
        iArr[185] = R.drawable.can_sync_icon_b9;
        iArr[186] = R.drawable.can_sync_icon_ba;
        iArr[187] = R.drawable.can_sync_icon_bb;
        iArr[188] = R.drawable.can_sync_icon_bc;
        iArr[189] = R.drawable.can_sync_icon_bd;
        iArr[190] = R.drawable.can_sync_icon_be;
        iArr[191] = R.drawable.can_sync_icon_bf;
        iArr[192] = R.drawable.can_sync_icon_c0;
        iArr[193] = R.drawable.can_sync_icon_c1;
        iArr[194] = R.drawable.can_sync_icon_c2;
        iArr[195] = R.drawable.can_sync_icon_c3;
        iArr[196] = R.drawable.can_sync_icon_c4;
        iArr[197] = R.drawable.can_sync_icon_c5;
        iArr[198] = R.drawable.can_sync_icon_c6;
        iArr[204] = R.drawable.can_sync_icon_cc;
        iArr[205] = R.drawable.can_sync_icon_cd;
        iArr[207] = R.drawable.can_sync_icon_cf;
        iArr[208] = R.drawable.can_sync_icon_d0;
        iArr[209] = R.drawable.can_sync_icon_d1;
        iArr[210] = R.drawable.can_sync_icon_d2;
        iArr[211] = R.drawable.can_sync_icon_d3;
        iArr[212] = R.drawable.can_sync_icon_d4;
        iArr[213] = R.drawable.can_sync_icon_d5;
        iArr[214] = R.drawable.can_sync_icon_d6;
        iArr[215] = R.drawable.can_sync_icon_d7;
        iArr[216] = R.drawable.can_sync_icon_d8;
        iArr[217] = R.drawable.can_sync_icon_d9;
        iArr[218] = R.drawable.can_sync_icon_da;
        iArr[219] = R.drawable.can_sync_icon_db;
        iArr[220] = R.drawable.can_sync_icon_dc;
        iArr[221] = R.drawable.can_sync_icon_dd;
        iArr[222] = R.drawable.can_sync_icon_de;
        iArr[223] = R.drawable.can_sync_icon_df;
        iArr[224] = R.drawable.can_sync_icon_e0;
        iArr[229] = R.drawable.can_sync_icon_e5;
        iArr[247] = R.drawable.can_sync_icon_f7;
        iArr[248] = R.drawable.can_sync_icon_f8;
        iArr[249] = R.drawable.can_sync_icon_f9;
        iArr[250] = R.drawable.can_sync_icon_fa;
        iArr[251] = R.drawable.can_sync_icon_fb;
        mIconArr = iArr;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (13 == CanJni.GetCanType() || 146 == CanJni.GetCanType()) {
            setContentView(R.layout.activity_can_comm_relative);
            InitUI();
            return;
        }
        Log.d(TAG, "-----NOT FORD PROTOCAL-----");
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        mIsJump = false;
        mIsNeedFinish = false;
    }

    private boolean isF150US() {
        return CanJni.GetSubType() == 10 && 13 == CanJni.GetCanType();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        boolean verCn;
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        Evc.GetInstance().evol_workmode_set(12);
        if (isF150US()) {
            mVerUS = true;
        } else {
            CanDataInfo.SyncVer ver = new CanDataInfo.SyncVer();
            CanJni.FordGetSyncVer(ver);
            mVerUS = ver.Ver == 1;
        }
        if (mVerUS) {
            verCn = false;
        } else {
            verCn = true;
        }
        setViewShow((View) this.mLayoutMenu, verCn);
        setViewShow((View) this.mLayoutDlg, verCn);
        setViewShow((View) this.mLayoutUS, mVerUS);
        this.mIcoCurSrc.Show(verCn);
        ResetData(false);
        mIsSyncWin = true;
        mIsNeedFinish = false;
        Log.d(TAG, "-----Is jump = " + mIsJump + "-----");
        if (CanJni.GetCanFsTp() != 146) {
            return;
        }
        if (CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9 || CanJni.GetSubType() == 7) {
            CanJni.FordRzcCarSrcCmd(1);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        mIsSyncWin = false;
        if (CanJni.GetCanFsTp() != 146) {
            return;
        }
        if (CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9 || CanJni.GetSubType() == 7) {
            CanJni.FordRzcCarSrcCmd(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        Log.d(TAG, "-----on New Intent-----");
        super.onNewIntent(intent);
    }

    /* access modifiers changed from: protected */
    public ParamButton AddImgBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateUpDn(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddImgBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateUpDn(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new RelativeLayoutManager((RelativeLayout) findViewById(R.id.can_comm_layout));
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        Log.d("nyw", String.format("%d,%d", new Object[]{Integer.valueOf(mDisplayMetrics.widthPixels), Integer.valueOf(mDisplayMetrics.heightPixels)}));
        if (MainSet.GetScreenType() == 3) {
            initScreen_768x1024();
        } else {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
            initCommonScreen();
        }
        ShowRtBtn();
        setViewShow((View) this.mLayoutMenu, false);
        if (isF150US()) {
            this.mBtnInfo.setTag(115);
            this.mBtnInfo.setOnClickListener((View.OnClickListener) null);
            this.mBtnInfo.setStateUpDn(R.drawable.can_sync_kc_up, R.drawable.can_sync_kc_dn);
            this.mBtnDev.setTag(114);
            this.mBtnDev.setOnClickListener((View.OnClickListener) null);
            this.mBtnDev.setStateUpDn(R.drawable.can_sync_kj_up, R.drawable.can_sync_kj_dn);
            this.mBtnRnd.setTag(105);
            this.mBtnRnd.setStateUpDn(R.drawable.can_sync_scan_up, R.drawable.can_sync_scan_dn);
            this.mBtnLtSpeech.setClickable(false);
            this.mBtnLtSpeech.setStateUpDn(R.drawable.can_sync_mike01_up, R.drawable.can_sync_mike01_up);
            this.mBtnLtPhone.setOnClickListener((View.OnClickListener) null);
            this.mBtnLtPhone.setOnTouchListener(this);
            this.mBtnInfo.setOnTouchListener(this);
            this.mBtnDev.setOnTouchListener(this);
            this.mIcoInfo.Show(false);
            this.mIcoSM.Show(false);
            this.mIcoBattery.Show(false);
            this.mIcoSignal.Show(false);
        }
        if (MainSet.GetScreenType() != 3) {
            this.mManager.GetLayout().setScaleX((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f);
            this.mManager.GetLayout().setScaleY((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f);
            Log.d("nyw", String.format("%.2f,%.2f", new Object[]{Float.valueOf((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f), Float.valueOf((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f)}));
        }
    }

    private void initScreen_768x1024() {
        this.mBtnLtMusic = AddImgBtn(1, 0, 0, Can.CAN_FORD_WC, 110, R.drawable.can_sync_music_up, R.drawable.can_sync_music_dn);
        this.mBtnLtPhone = AddImgBtn(2, 0, 110, Can.CAN_FORD_WC, 108, R.drawable.can_sync_phone_up, R.drawable.can_sync_phone_dn);
        this.mBtnLtSpeech = AddImgBtn(3, 0, 218, Can.CAN_FORD_WC, 106, R.drawable.can_sync_mike_up, R.drawable.can_sync_mike_dn);
        this.mBtnLtKeyboard = AddImgBtn(4, 0, KeyDef.RKEY_RADIO_2S, Can.CAN_FORD_WC, 108, R.drawable.can_sync_dial_up, R.drawable.can_sync_dial_dn);
        this.mManager.AddImageEx(CanCameraUI.BTN_CHANA_ALSVINV7_MODE1, 44, 222, 309, R.drawable.can_sync_right_box);
        this.mBtnUp = AddImgBtn(90, 607, 52, 67, 50, R.drawable.can_sync_on_up, R.drawable.can_sync_on_dn);
        this.mBtnLeft = AddImgBtn(105, 534, 112, 67, 50, R.drawable.can_sync_left_up, R.drawable.can_sync_left_dn);
        this.mBtnOK = AddImgBtn(92, 607, 112, 67, 50, R.drawable.can_sync_ok_up, R.drawable.can_sync_ok_dn);
        this.mBtnRight = AddImgBtn(106, 681, 112, 67, 50, R.drawable.can_sync_right_up, R.drawable.can_sync_right_dn);
        this.mBtnDn = AddImgBtn(91, 607, 173, 67, 50, R.drawable.can_sync_dn_up, R.drawable.can_sync_dn_dn);
        this.mBtnInfo = AddImgBtn(86, 534, Can.CAN_ZH_WC, 67, 50, R.drawable.can_sync_info_up, R.drawable.can_sync_info_dn);
        this.mBtnMenu = AddImgBtn(82, 607, Can.CAN_ZH_WC, 67, 50, R.drawable.can_sync_menu_up, R.drawable.can_sync_menu_dn);
        this.mBtnDev = AddImgBtn(209, 681, Can.CAN_ZH_WC, 67, 50, R.drawable.can_sync_dev_up, R.drawable.can_sync_dev_dn);
        this.mBtnPrev = AddImgBtn(88, 534, 296, 67, 50, R.drawable.can_sync_seekup_up, R.drawable.can_sync_seekup_dn);
        this.mBtnRnd = AddImgBtn(87, 607, 296, 67, 50, R.drawable.can_sync_random_up, R.drawable.can_sync_random_dn);
        this.mBtnNext = AddImgBtn(89, 681, 296, 67, 50, R.drawable.can_sync_seekdn_up, R.drawable.can_sync_seekdn_dn);
        this.mBtnNum[1] = AddImgBtn(94, 534, 52, 67, 50, R.drawable.can_sync_num01_up, R.drawable.can_sync_num01_dn);
        this.mBtnNum[2] = AddImgBtn(95, 607, 52, 67, 50, R.drawable.can_sync_num02_up, R.drawable.can_sync_num02_dn);
        this.mBtnNum[3] = AddImgBtn(96, 681, 52, 67, 50, R.drawable.can_sync_num03_up, R.drawable.can_sync_num03_dn);
        this.mBtnNum[4] = AddImgBtn(97, 534, 112, 67, 50, R.drawable.can_sync_num04_up, R.drawable.can_sync_num04_dn);
        this.mBtnNum[5] = AddImgBtn(98, 607, 112, 67, 50, R.drawable.can_sync_num05_up, R.drawable.can_sync_num05_dn);
        this.mBtnNum[6] = AddImgBtn(99, 681, 112, 67, 50, R.drawable.can_sync_num06_up, R.drawable.can_sync_num06_dn);
        this.mBtnNum[7] = AddImgBtn(100, 534, 173, 67, 50, R.drawable.can_sync_num07_up, R.drawable.can_sync_num07_dn);
        this.mBtnNum[8] = AddImgBtn(101, 607, 173, 67, 50, R.drawable.can_sync_num08_up, R.drawable.can_sync_num08_dn);
        this.mBtnNum[9] = AddImgBtn(102, 681, 173, 67, 50, R.drawable.can_sync_num09_up, R.drawable.can_sync_num09_dn);
        this.mBtnNumX = AddImgBtn(103, 534, Can.CAN_ZH_WC, 67, 50, R.drawable.can_sync_aste_up, R.drawable.can_sync_aste_dn);
        this.mBtnNum[0] = AddImgBtn(93, 607, Can.CAN_ZH_WC, 67, 50, R.drawable.can_sync_num00_up, R.drawable.can_sync_num00_dn);
        this.mBtnNumJ = AddImgBtn(104, 681, Can.CAN_ZH_WC, 67, 50, R.drawable.can_sync_well_up, R.drawable.can_sync_well_dn);
        this.mBtnCall = AddImgBtn(85, 534, 296, 67, 50, R.drawable.can_sync_dialout_up, R.drawable.can_sync_dialout_dn);
        this.mBtnHang = AddImgBtn(84, 607, 296, 67, 50, R.drawable.can_sync_hangup_up, R.drawable.can_sync_hangup_dn);
        this.mIcoCurSrc = this.mManager.AddImage(180, 10, 45, 44);
        this.mIcoSync = AddIco(Can.CAN_CC_HF_DJ, R.drawable.can_sync_status_sync_up, R.drawable.can_sync_status_sync_dn);
        this.mIcoInfo = AddIco(265, R.drawable.can_sync_status_info_up, R.drawable.can_sync_status_info_dn);
        this.mIcoBt = AddIco(300, R.drawable.can_sync_stutas_cd, R.drawable.can_sync_stutas_cc);
        this.mIcoSM = AddIco(KeyDef.RKEY_DEL, -1, R.drawable.can_sync_stutas_5d);
        this.mIcoSpk = AddIco(AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, -1, R.drawable.can_sync_stutas_82);
        this.mIcoCall = AddIco(405, -1, R.drawable.can_sync_stutas_29);
        this.mIcoBattery = AddIco(440, R.drawable.can_sync_stutas_61, -1);
        this.mIcoSignal = AddIco(475, R.drawable.can_sync_stutas_67, -1);
        this.mSyncTime = this.mManager.AddCusText(540, 10, 200, 33);
        this.mSyncTime.setGravity(17);
        this.mSyncTime.SetPixelSize(24);
        for (int i = 0; i < 10; i++) {
            this.mBtnNum[i].setIntParam(i);
            this.mBtnNum[i].setOnLongClickListener(this);
        }
        this.mX = 170;
        this.mY = 54;
        this.mLayoutMenu = new RelativeLayout(this);
        this.mManager.AddView(this.mLayoutMenu, this.mX, this.mY, KeyDef.RKEY_res3, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1);
        this.mLayoutDlg = new RelativeLayout(this);
        this.mManager.AddView(this.mLayoutDlg, this.mX, this.mY, KeyDef.RKEY_res3, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1);
        this.mLayoutUS = new RelativeLayout(this);
        this.mManager.AddView(this.mLayoutUS, this.mX, this.mY, KeyDef.RKEY_res3, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1);
        this.mManMenu = new RelativeLayoutManager(this.mLayoutMenu);
        this.mManDlg = new RelativeLayoutManager(this.mLayoutDlg);
        this.mManUS = new RelativeLayoutManager(this.mLayoutUS);
        for (int i2 = 0; i2 < 3; i2++) {
            this.mUSLine[i2] = new SyncLine(this);
            this.mUSLine[i2].SetTextSize(30);
            this.mManUS.AddView(this.mUSLine[i2].GetView(), 20, (((i2 + 2) * 64) + 59) - this.mY, 300, 59);
        }
        for (int i3 = 0; i3 < 5; i3++) {
            this.mMenuLine[i3] = new SyncLine(this, i3 + 16, this);
            this.mMenuLine[i3].SetTextSize(30);
            this.mMenuLine[i3].SetRtIconLeftMargin(Can.CAN_ZH_WC);
            this.mManMenu.AddView(this.mMenuLine[i3].GetView(), 20, ((i3 * 64) + 59) - this.mY, 300, 59);
            this.mDlgLine[i3] = new SyncLine(this, i3 + 32, this);
            this.mDlgLine[i3].SetTextSize(30);
            this.mDlgLine[i3].SetRtIconLeftMargin(Can.CAN_ZH_WC);
        }
        for (int i4 = 0; i4 < 4; i4++) {
            this.mMenuKey[i4] = new SyncSKey(this, i4 + 21, this);
            this.mMenuKey[i4].SetTextSize(20);
            this.mMenuKey[i4].SetIconCenter();
            this.mManMenu.AddView(this.mMenuKey[i4].GetView(), (i4 * 86) + 1, 380 - this.mY, 80, 56);
            this.mMenuKey[i4].Show(false);
            this.mDlgKey[i4] = new SyncSKey(this, i4 + 37, this);
            this.mDlgKey[i4].SetTextSize(20);
            this.mDlgKey[i4].SetIconCenter();
        }
        for (int i5 = 0; i5 < 6; i5++) {
            this.mImgDlgBg[i5] = new CustomImgView(this);
            this.mImgDlgBg[i5].setBackgroundResource(mDlgBgArr[i5]);
        }
    }

    private void initCommonScreen() {
        this.mBtnLtMusic = AddImgBtn(1, 0, 0, R.drawable.can_sync_music_up, R.drawable.can_sync_music_dn);
        this.mBtnLtPhone = AddImgBtn(2, 0, Can.CAN_BENC_ZMYT, R.drawable.can_sync_phone_up, R.drawable.can_sync_phone_dn);
        this.mBtnLtSpeech = AddImgBtn(3, 0, 278, R.drawable.can_sync_mike_up, R.drawable.can_sync_mike_dn);
        this.mBtnLtKeyboard = AddImgBtn(4, 0, 415, R.drawable.can_sync_dial_up, R.drawable.can_sync_dial_dn);
        this.mManager.AddImage(706, 59, R.drawable.can_sync_right_box);
        this.mBtnUp = AddImgBtn(90, KeyDef.SKEY_MODE_1, 69, R.drawable.can_sync_on_up, R.drawable.can_sync_on_dn);
        this.mBtnLeft = AddImgBtn(105, 711, 149, R.drawable.can_sync_left_up, R.drawable.can_sync_left_dn);
        this.mBtnOK = AddImgBtn(92, KeyDef.SKEY_MODE_1, 149, R.drawable.can_sync_ok_up, R.drawable.can_sync_ok_dn);
        this.mBtnRight = AddImgBtn(106, 907, 149, R.drawable.can_sync_right_up, R.drawable.can_sync_right_dn);
        this.mBtnDn = AddImgBtn(91, KeyDef.SKEY_MODE_1, Can.CAN_BYD_S6_S7, R.drawable.can_sync_dn_up, R.drawable.can_sync_dn_dn);
        this.mBtnInfo = AddImgBtn(86, 711, KeyDef.RKEY_CMMB_PBC, R.drawable.can_sync_info_up, R.drawable.can_sync_info_dn);
        this.mBtnMenu = AddImgBtn(82, KeyDef.SKEY_MODE_1, KeyDef.RKEY_CMMB_PBC, R.drawable.can_sync_menu_up, R.drawable.can_sync_menu_dn);
        this.mBtnDev = AddImgBtn(209, 907, KeyDef.RKEY_CMMB_PBC, R.drawable.can_sync_dev_up, R.drawable.can_sync_dev_dn);
        this.mBtnPrev = AddImgBtn(88, 711, 394, R.drawable.can_sync_seekup_up, R.drawable.can_sync_seekup_dn);
        this.mBtnRnd = AddImgBtn(87, KeyDef.SKEY_MODE_1, 394, R.drawable.can_sync_random_up, R.drawable.can_sync_random_dn);
        this.mBtnNext = AddImgBtn(89, 907, 394, R.drawable.can_sync_seekdn_up, R.drawable.can_sync_seekdn_dn);
        this.mBtnNum[1] = AddImgBtn(94, 711, 69, R.drawable.can_sync_num01_up, R.drawable.can_sync_num01_dn);
        this.mBtnNum[2] = AddImgBtn(95, KeyDef.SKEY_MODE_1, 69, R.drawable.can_sync_num02_up, R.drawable.can_sync_num02_dn);
        this.mBtnNum[3] = AddImgBtn(96, 907, 69, R.drawable.can_sync_num03_up, R.drawable.can_sync_num03_dn);
        this.mBtnNum[4] = AddImgBtn(97, 711, 149, R.drawable.can_sync_num04_up, R.drawable.can_sync_num04_dn);
        this.mBtnNum[5] = AddImgBtn(98, KeyDef.SKEY_MODE_1, 149, R.drawable.can_sync_num05_up, R.drawable.can_sync_num05_dn);
        this.mBtnNum[6] = AddImgBtn(99, 907, 149, R.drawable.can_sync_num06_up, R.drawable.can_sync_num06_dn);
        this.mBtnNum[7] = AddImgBtn(100, 711, Can.CAN_BYD_S6_S7, R.drawable.can_sync_num07_up, R.drawable.can_sync_num07_dn);
        this.mBtnNum[8] = AddImgBtn(101, KeyDef.SKEY_MODE_1, Can.CAN_BYD_S6_S7, R.drawable.can_sync_num08_up, R.drawable.can_sync_num08_dn);
        this.mBtnNum[9] = AddImgBtn(102, 907, Can.CAN_BYD_S6_S7, R.drawable.can_sync_num09_up, R.drawable.can_sync_num09_dn);
        this.mBtnNumX = AddImgBtn(103, 711, KeyDef.RKEY_CMMB_PBC, R.drawable.can_sync_aste_up, R.drawable.can_sync_aste_dn);
        this.mBtnNum[0] = AddImgBtn(93, KeyDef.SKEY_MODE_1, KeyDef.RKEY_CMMB_PBC, R.drawable.can_sync_num00_up, R.drawable.can_sync_num00_dn);
        this.mBtnNumJ = AddImgBtn(104, 907, KeyDef.RKEY_CMMB_PBC, R.drawable.can_sync_well_up, R.drawable.can_sync_well_dn);
        this.mBtnCall = AddImgBtn(85, 711, 394, R.drawable.can_sync_dialout_up, R.drawable.can_sync_dialout_dn);
        this.mBtnHang = AddImgBtn(84, KeyDef.SKEY_MODE_1, 394, R.drawable.can_sync_hangup_up, R.drawable.can_sync_hangup_dn);
        this.mIcoCurSrc = this.mManager.AddImage(Can.CAN_NISSAN_XFY, 10, 45, 44);
        this.mIcoSync = AddIco(300, R.drawable.can_sync_status_sync_up, R.drawable.can_sync_status_sync_dn);
        this.mIcoInfo = AddIco(KeyDef.RKEY_DEL, R.drawable.can_sync_status_info_up, R.drawable.can_sync_status_info_dn);
        this.mIcoBt = AddIco(AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, R.drawable.can_sync_stutas_cd, R.drawable.can_sync_stutas_cc);
        this.mIcoSM = AddIco(405, -1, R.drawable.can_sync_stutas_5d);
        this.mIcoSpk = AddIco(440, -1, R.drawable.can_sync_stutas_82);
        this.mIcoCall = AddIco(475, -1, R.drawable.can_sync_stutas_29);
        this.mIcoBattery = AddIco(CanCameraUI.BTN_YG9_XBS_MODE1, R.drawable.can_sync_stutas_61, -1);
        this.mIcoSignal = AddIco(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6, R.drawable.can_sync_stutas_67, -1);
        this.mSyncTime = this.mManager.AddCusText(754, 10, 200, 33);
        this.mSyncTime.setGravity(17);
        this.mSyncTime.SetPixelSize(28);
        for (int i = 0; i < 10; i++) {
            this.mBtnNum[i].setIntParam(i);
            this.mBtnNum[i].setOnLongClickListener(this);
        }
        this.mLayoutMenu = new RelativeLayout(this);
        this.mManager.AddView(this.mLayoutMenu, this.mX, this.mY, 705 - this.mX, 500 - this.mY);
        this.mLayoutDlg = new RelativeLayout(this);
        this.mManager.AddView(this.mLayoutDlg, this.mX, this.mY, 705 - this.mX, 500 - this.mY);
        this.mLayoutUS = new RelativeLayout(this);
        this.mManager.AddView(this.mLayoutUS, this.mX, this.mY, 705 - this.mX, 500 - this.mY);
        this.mManMenu = new RelativeLayoutManager(this.mLayoutMenu);
        this.mManDlg = new RelativeLayoutManager(this.mLayoutDlg);
        this.mManUS = new RelativeLayoutManager(this.mLayoutUS);
        for (int i2 = 0; i2 < 3; i2++) {
            this.mUSLine[i2] = new SyncLine(this);
            this.mManUS.AddView(this.mUSLine[i2].GetView(), 237 - this.mX, (((i2 + 2) * 64) + 59) - this.mY, 441, 59);
        }
        for (int i3 = 0; i3 < 5; i3++) {
            this.mMenuLine[i3] = new SyncLine(this, i3 + 16, this);
            this.mManMenu.AddView(this.mMenuLine[i3].GetView(), 237 - this.mX, ((i3 * 64) + 59) - this.mY, 441, 59);
            this.mDlgLine[i3] = new SyncLine(this, i3 + 32, this);
        }
        for (int i4 = 0; i4 < 4; i4++) {
            this.mMenuKey[i4] = new SyncSKey(this, i4 + 21, this);
            this.mManMenu.AddViewWrapContent(this.mMenuKey[i4].GetView(), ((i4 * 113) + Can.CAN_NISSAN_RICH6_WC) - this.mX, 392 - this.mY);
            this.mMenuKey[i4].Show(false);
            this.mDlgKey[i4] = new SyncSKey(this, i4 + 37, this);
        }
        for (int i5 = 0; i5 < 6; i5++) {
            this.mImgDlgBg[i5] = new CustomImgView(this);
            this.mImgDlgBg[i5].setImageResource(mDlgBgArr[i5]);
        }
    }

    /* access modifiers changed from: protected */
    public CustomImgView AddIco(int x, int up, int sel) {
        CustomImgView ico = this.mManager.AddImage(x, 10, 33, 33);
        if (-1 != sel) {
            ico.setStateDrawable(up, sel);
        }
        return ico;
    }

    /* access modifiers changed from: protected */
    public void CreateDlg(int dlgType) {
        boolean z;
        boolean isScreen_768x1024;
        int y;
        int lines = 0;
        int btn = 0;
        switch (dlgType) {
            case 1:
                lines = 1;
                break;
            case 2:
                lines = 1;
                btn = 1;
                break;
            case 3:
                lines = 2;
                break;
            case 4:
                lines = 2;
                btn = 1;
                break;
            case 5:
                lines = 3;
                break;
            case 6:
                lines = 3;
                btn = 1;
                break;
            case 7:
            case 8:
            case 11:
                lines = 5;
                btn = 1;
                break;
        }
        Log.d(TAG, "CreateDlg type = " + dlgType + ", lines = " + lines + " btn = " + btn);
        this.mDlgLineNum = lines;
        this.mDlgBtnNum = btn;
        int curTyep = (lines << 8) | btn;
        if (this.mDlgType != curTyep) {
            this.mDlgType = curTyep;
            this.mManDlg.RemoveAllViews();
            int totalline = lines + btn;
            if (totalline == 0) {
                z = true;
            } else {
                z = false;
            }
            SetMenuEnable(z);
            if (totalline != 0) {
                if (MainSet.GetScreenType() == 3) {
                    isScreen_768x1024 = true;
                } else {
                    isScreen_768x1024 = false;
                }
                if (isScreen_768x1024) {
                    y = mDlgYArr_768x1024[totalline - 1];
                } else {
                    y = mDlgYArr[totalline - 1] - this.mY;
                }
                this.mManDlg.AddViewWrapContent(this.mImgDlgBg[totalline - 1], 0, y);
                for (int i = 0; i < lines; i++) {
                    if (isScreen_768x1024) {
                        this.mManDlg.AddView(this.mDlgLine[i].GetView(), 20, ((i * 64) + 59) - this.mY, 300, 59);
                    } else {
                        this.mManDlg.AddView(this.mDlgLine[i].GetView(), 237 - this.mX, y + 11 + (i * 64), 441, 59);
                    }
                }
                if (btn != 0) {
                    for (int i2 = 0; i2 < 4; i2++) {
                        if (isScreen_768x1024) {
                            this.mManDlg.AddView(this.mDlgKey[i2].GetView(), (i2 * 86) + 1, 380 - this.mY, 80, 56);
                        } else {
                            this.mManDlg.AddViewWrapContent(this.mDlgKey[i2].GetView(), ((i2 * 113) + Can.CAN_NISSAN_RICH6_WC) - this.mX, y + 15 + (lines * 64));
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void SetMenuEnable(boolean enable) {
        for (int i = 0; i < 5; i++) {
            this.mMenuLine[i].GetView().setClickable(enable);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            this.mMenuKey[i2].SetEnable(enable);
        }
    }

    /* access modifiers changed from: protected */
    public void ShowRtBtn() {
        boolean showMedia = !this.mfgShowKb;
        this.mBtnUp.Show(showMedia);
        this.mBtnDn.Show(showMedia);
        this.mBtnLeft.Show(showMedia);
        this.mBtnRight.Show(showMedia);
        this.mBtnOK.Show(showMedia);
        this.mBtnInfo.Show(showMedia);
        this.mBtnMenu.Show(showMedia);
        this.mBtnDev.Show(showMedia);
        this.mBtnPrev.Show(showMedia);
        this.mBtnRnd.Show(showMedia);
        this.mBtnNext.Show(showMedia);
        for (int i = 0; i < 10; i++) {
            this.mBtnNum[i].Show(this.mfgShowKb);
        }
        this.mBtnNumX.Show(this.mfgShowKb);
        this.mBtnNumJ.Show(this.mfgShowKb);
        this.mBtnCall.Show(this.mfgShowKb);
        this.mBtnHang.Show(this.mfgShowKb);
        if (isF150US()) {
            this.mBtnCall.Show(false);
            this.mBtnHang.Show(false);
            this.mBtnLeft.Show(false);
            this.mBtnRight.Show(false);
        }
    }

    private void SetLine(SyncLine line, CanDataInfo.SyncMenuItem item) {
        line.SetIco(mIconArr[item.LeftIco & 255], mIconArr[item.RightIco & 255]);
        line.SetCanSel(item.CanSelect);
        String strLine = GetUnicodeStr(item.szText);
        Log.d(TAG, "strLine = " + strLine);
        int color = -1;
        switch (item.LineAttrib) {
            case 0:
                color = -1;
                break;
            case 1:
                color = -1;
                break;
            case 2:
                color = -12303292;
                break;
            case 3:
                color = -12303292;
                break;
            case 4:
                color = -12303292;
                break;
            case 5:
                strLine = TXZResourceManager.STYLE_DEFAULT;
                break;
            default:
                strLine = TXZResourceManager.STYLE_DEFAULT;
                break;
        }
        line.SetText(strLine, color);
    }

    private void SetSoftKey(SyncSKey key, CanDataInfo.SyncMenuItem item) {
        String strLine = TXZResourceManager.STYLE_DEFAULT;
        int ico = 0;
        int sel = 0;
        int show = 1;
        switch (item.LineAttrib) {
            case 0:
                strLine = GetUnicodeStr(item.szText);
                show = 0;
                break;
            case 2:
                ico = item.LeftIco;
                break;
            case 3:
                ico = item.LeftIco;
                sel = 1;
                break;
            case 10:
                strLine = GetUnicodeStr(item.szText);
                break;
            case 11:
                strLine = GetUnicodeStr(item.szText);
                sel = 1;
                break;
            default:
                strLine = "--";
                show = 0;
                break;
        }
        key.SetTextIco(strLine, mIconArr[ico]);
        key.SetSel(sel);
        key.Show(show);
    }

    private void UpdateCnUI(boolean check) {
        boolean z;
        boolean z2;
        CanJni.FordGetSyncCnUI(this.mPhoneTimeData, this.mMediaTimeData, this.mMenuInfoData, this.mWinData);
        if (i2b(this.mMenuInfoData.UpdateOnce) && (!check || i2b(this.mMenuInfoData.Update))) {
            this.mMenuInfoData.Update = 0;
            for (int i = 0; i < 5; i++) {
                SyncLine syncLine = this.mMenuLine[i];
                if (this.mMenuInfoData.MenuSelected - 1 == i) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                syncLine.SetSel(z2);
            }
            for (int i2 = 0; i2 < 5; i2++) {
                SyncLine syncLine2 = this.mDlgLine[i2];
                if (this.mMenuInfoData.DialogSelected - 1 == i2) {
                    z = true;
                } else {
                    z = false;
                }
                syncLine2.SetSel(z);
            }
            for (int i3 = 0; i3 < 5; i3++) {
                SetLine(this.mMenuLine[i3], this.mWinData.NormalItem[i3]);
            }
            for (int i4 = 0; i4 < 4; i4++) {
                SetSoftKey(this.mMenuKey[i4], this.mWinData.NormalSoftKey[i4]);
            }
            CreateDlg(this.mMenuInfoData.DialogType);
            for (int i5 = 0; i5 < this.mDlgLineNum; i5++) {
                SetLine(this.mDlgLine[i5], this.mWinData.DialogItem[i5]);
            }
            if (this.mDlgBtnNum != 0) {
                for (int i6 = 0; i6 < 4; i6++) {
                    SetSoftKey(this.mDlgKey[i6], this.mWinData.DialogSoftKey[i6]);
                }
            }
            switch (this.mMenuInfoData.MenuIcon) {
                case 2:
                    this.mIcoCurSrc.setImageResource(R.drawable.can_sync_icon_02);
                    break;
                case 5:
                    this.mIcoCurSrc.setImageResource(R.drawable.can_sync_icon_05);
                    break;
                case 8:
                    this.mIcoCurSrc.setImageResource(R.drawable.can_sync_icon_08);
                    break;
                case 10:
                    this.mIcoCurSrc.setImageResource(R.drawable.can_sync_icon_0a);
                    break;
                case 12:
                    this.mIcoCurSrc.setImageResource(R.drawable.can_sync_icon_0c);
                    break;
                default:
                    this.mIcoCurSrc.setImageResource(0);
                    break;
            }
        }
        if (i2b(this.mPhoneTimeData.UpdateOnce) && (!check || i2b(this.mPhoneTimeData.Update))) {
            Log.d(TAG, "mPhoneTimeData.Update");
            this.mPhoneTimeData.Update = 0;
            this.mSyncTime.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mPhoneTimeData.Min), Integer.valueOf(this.mPhoneTimeData.Sec)}));
            this.mTimeUpdate = true;
            this.mLastTimeTick = (long) this.mPhoneTimeData.Tick;
        }
        if (i2b(this.mMediaTimeData.UpdateOnce) && (!check || i2b(this.mMediaTimeData.Update))) {
            Log.d(TAG, "mMediaTimeData.Update");
            this.mMediaTimeData.Update = 0;
            this.mSyncTime.setText(String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(this.mMediaTimeData.Hour), Integer.valueOf(this.mMediaTimeData.Min), Integer.valueOf(this.mMediaTimeData.Sec)}));
            this.mTimeUpdate = true;
            this.mLastTimeTick = (long) this.mMediaTimeData.Tick;
        }
        if (this.mTimeUpdate) {
            long curTick = GetTickCount();
            if (curTick > this.mLastTimeTick + 2000) {
                Log.d(TAG, "Hide Time");
                this.mLastTimeTick = curTick;
                this.mTimeUpdate = false;
                this.mSyncTime.setText(TXZResourceManager.STYLE_DEFAULT);
            }
        }
    }

    private void UpdateUsUI(boolean check) {
        CanJni.FordGetSyncUsUI(this.mStrUpData, this.mStrDnData, this.mStrShortData);
        if (i2b(this.mStrUpData.UpdateOnce) && (!check || i2b(this.mStrUpData.Update))) {
            this.mStrUpData.Update = 0;
            this.mUSLine[0].SetText(this.mStrUpData.szText);
        }
        if (i2b(this.mStrDnData.UpdateOnce) && (!check || i2b(this.mStrDnData.Update))) {
            this.mStrDnData.Update = 0;
            this.mUSLine[1].SetText(this.mStrDnData.szText);
        }
        if (!i2b(this.mStrShortData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mStrShortData.Update)) {
            this.mStrShortData.Update = 0;
            this.mUSLine[2].SetText(this.mStrShortData.szText);
        }
    }

    private void UpdateStatus(boolean check) {
        CanJni.FordGetSyncSta(this.mStatusData);
        if (!i2b(this.mStatusData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mStatusData.Update)) {
            this.mStatusData.Update = 0;
            this.mIcoSync.SetSel(this.mStatusData.fgModuleExist);
            this.mIcoInfo.SetSel(this.mStatusData.fgInfoKeyAvalid);
            this.mIcoBt.SetSel(this.mStatusData.fgBTConnected);
            this.mIcoSM.SetSel(this.mStatusData.fgShowShortMsg);
            this.mIcoSpk.SetSel(this.mStatusData.fgVoice);
            this.mIcoCall.SetSel(this.mStatusData.fgCalling);
            if (this.mStatusData.PhoneBattery > 4 || this.mStatusData.PhoneBattery < 0) {
                this.mIcoBattery.setImageResource(0);
            } else {
                this.mIcoBattery.setImageResource(mIcoBatArr[this.mStatusData.PhoneBattery]);
            }
            if (this.mStatusData.PhoneSigNum > 4 || this.mStatusData.PhoneSigNum < 0) {
                this.mIcoSignal.setImageResource(0);
            } else {
                this.mIcoSignal.setImageResource(mIcoSigArr[this.mStatusData.PhoneSigNum]);
            }
        }
    }

    private void ResetData(boolean check) {
        if (mVerUS) {
            UpdateUsUI(check);
        } else {
            UpdateCnUI(check);
        }
        UpdateStatus(check);
    }

    protected static boolean IsUsVer() {
        return mVerUS;
    }

    protected static void MediaClick() {
        if (IsUsVer()) {
            CanJni.FordSyncCtrl(129);
        } else {
            CanJni.FordSyncCtrl(27);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        int action = event.getAction();
        if (action == 0) {
            this.mView = v;
            sendKey(v);
            return false;
        } else if (action != 1) {
            return false;
        } else {
            if (id == 2) {
                CanJni.FordSyncCtrl(179);
            }
            v.removeCallbacks(this.mSendTask);
            this.mView = null;
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void sendKey(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 2) {
            CanJni.FordSyncCtrl(3);
        } else {
            CanJni.FordSyncCtrl(id - 80);
        }
        v.postDelayed(this.mSendTask, 300);
    }

    public void onClick(View v) {
        boolean z;
        int id = ((Integer) v.getTag()).intValue();
        Log.d(TAG, "onClick item  = " + id);
        switch (id) {
            case 1:
                MediaClick();
                return;
            case 2:
                CanJni.FordSyncCtrl(3);
                return;
            case 3:
                CanJni.FordSyncCtrl(1);
                return;
            case 4:
                if (this.mfgShowKb) {
                    z = false;
                } else {
                    z = true;
                }
                this.mfgShowKb = z;
                ShowRtBtn();
                return;
            case 16:
                CanJni.FordSyncCtrl(145);
                return;
            case 17:
                CanJni.FordSyncCtrl(146);
                return;
            case 18:
                CanJni.FordSyncCtrl(147);
                return;
            case 19:
                CanJni.FordSyncCtrl(148);
                return;
            case 20:
                CanJni.FordSyncCtrl(149);
                return;
            case 21:
                CanJni.FordSyncCtrl(28);
                return;
            case 22:
                CanJni.FordSyncCtrl(29);
                return;
            case 23:
                CanJni.FordSyncCtrl(30);
                return;
            case 24:
                CanJni.FordSyncCtrl(31);
                return;
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
                return;
            case 37:
                CanJni.FordSyncCtrl(28);
                return;
            case 38:
                CanJni.FordSyncCtrl(29);
                return;
            case 39:
                CanJni.FordSyncCtrl(30);
                return;
            case 40:
                CanJni.FordSyncCtrl(31);
                return;
            default:
                if (id >= 80) {
                    CanJni.FordSyncCtrl(id - 80);
                    return;
                }
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
        if (mIsNeedFinish) {
            mIsNeedFinish = false;
            mIsJump = false;
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void testData() {
        long t1 = GetTickCount();
        CanJni.FordGetSyncCnUI(this.mPhoneTimeData, this.mMediaTimeData, this.mMenuInfoData, this.mWinData);
        long t2 = GetTickCount();
        int i = 0;
        while (i < 5) {
            try {
                Log.d(TAG, "mWinData.NormalItem[" + i + "].szText = " + GetUnicodeStr(this.mWinData.NormalItem[i].szText));
                i++;
            } catch (Exception e) {
            }
        }
        for (int i2 = 0; i2 < 5; i2++) {
            Log.d(TAG, "mWinData.DialogItem[" + i2 + "].szText = " + GetUnicodeStr(this.mWinData.DialogItem[i2].szText));
        }
        for (int i3 = 0; i3 < 4; i3++) {
            Log.d(TAG, "mWinData.NormalSoftKey[" + i3 + "].szText = " + GetUnicodeStr(this.mWinData.NormalSoftKey[i3].szText));
        }
        for (int i4 = 0; i4 < 4; i4++) {
            Log.d(TAG, "mWinData.DialogSoftKey[" + i4 + "].szText = " + GetUnicodeStr(this.mWinData.DialogSoftKey[i4].szText));
        }
        CanJni.FordGetSyncUsUI(this.mStrUpData, this.mStrDnData, this.mStrShortData);
        Log.d(TAG, "StrUp = " + this.mStrUpData.szText);
        Log.d(TAG, "StrDn =  " + this.mStrDnData.szText);
        Log.d(TAG, "StrShort = " + this.mStrShortData.szText);
        Log.d(TAG, "-----onResume-----, time = " + (t2 - t1));
    }

    /* access modifiers changed from: protected */
    public String GetUnicodeStr(byte[] data) {
        try {
            return new String(data, 2, data[0], "UNICODE");
        } catch (Exception e) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    public boolean onLongClick(View v) {
        CanJni.FordSyncCtrl(((ParamButton) v).getIntParam() + 48);
        return true;
    }

    public static void ShowSync() {
        if (!mIsSyncWin) {
            mIsJump = true;
            CanFunc.showCanActivity(CanFordSyncUIActivity.class);
        }
    }

    public static void ShowSyncClick() {
        if (Iop.GetWorkMode() != 12) {
            MediaClick();
        }
        if (!mIsSyncWin) {
            mIsJump = false;
            CanFunc.showCanActivity(CanFordSyncUIActivity.class);
        }
    }

    public static void DealCallEnd() {
        if (mIsJump && mIsSyncWin) {
            mIsNeedFinish = true;
        }
    }

    public static void DealVoiceEnd() {
        if (mIsJump && mIsSyncWin) {
            mIsNeedFinish = true;
        }
    }
}
