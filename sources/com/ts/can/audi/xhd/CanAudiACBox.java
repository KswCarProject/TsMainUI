package com.ts.can.audi.xhd;

import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanFunc;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.KeyDef;

public class CanAudiACBox implements UserCallBack {
    private static final String TAG = "CanAudiACBox";
    private static boolean mIsAC = false;
    private static final int[] mModeDnIds = {R.drawable.aic_right_auto_dn, R.drawable.aic_right_01_dn, R.drawable.aic_right_02_dn, R.drawable.aic_right_03_dn, R.drawable.aic_right_04_dn, R.drawable.aic_right_05_dn, R.drawable.aic_right_06_dn, R.drawable.aic_right_07_dn};
    private static final int[] mModeUpIds = {R.drawable.aic_right_auto_up, R.drawable.aic_right_01_up, R.drawable.aic_right_02_up, R.drawable.aic_right_03_up, R.drawable.aic_right_04_up, R.drawable.aic_right_05_up, R.drawable.aic_right_06_up, R.drawable.aic_right_07_up};
    private static boolean mfgJump;
    private static CanAudiACBox sInstance;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private CanDataInfo.AudiZmyt_AirInfo mAirInfo = new CanDataInfo.AudiZmyt_AirInfo();
    private Context mAppContext;
    private ImageView[] mIvLtArrows = new ImageView[3];
    private CustomImgView[] mIvLtModes = new CustomImgView[8];
    private ImageView[] mIvRtArrows = new ImageView[3];
    private CustomImgView[] mIvRtModes = new CustomImgView[8];
    private int[] mLastDatas = new int[12];
    private int mLeftBoxDelayTime = 0;
    private RelativeLayoutManager mLeftManager;
    private RelativeLayoutManager mManager;
    private int mRightBoxDelayTime = 0;
    private RelativeLayoutManager mRightManager;
    private TextView[] mTvLtWinds = new TextView[12];
    private TextView[] mTvMaxTemps = new TextView[2];
    private TextView[] mTvMinTemps = new TextView[2];
    private TextView[] mTvRtWinds = new TextView[12];
    private TextView[] mTvTemps = new TextView[2];
    private WindowManager mWinManager;
    private WindowManager.LayoutParams mWinParams;
    private int nDelayCheck = 0;

    private CanAudiACBox() {
    }

    public static CanAudiACBox GetInstance() {
        if (sInstance == null) {
            sInstance = new CanAudiACBox();
        }
        return sInstance;
    }

    public void Init(Context context) {
        if (this.mWinManager != null) {
            Log.d(TAG, "Already have instance");
            return;
        }
        Log.d(TAG, "Init");
        this.mAppContext = context.getApplicationContext();
        this.mWinManager = (WindowManager) this.mAppContext.getSystemService("window");
        this.mWinParams = new WindowManager.LayoutParams();
        this.mWinParams.type = 2003;
        this.mWinParams.format = 1;
        this.mWinParams.flags = 24;
        this.mWinParams.width = -1;
        this.mWinParams.height = -1;
        this.mWinParams.alpha = 30.0f;
        this.mManager = new RelativeLayoutManager(new RelativeLayout(this.mAppContext));
        if (MainSet.GetScreenType() == 5) {
            initViews();
        } else if (MainSet.GetScreenType() == 9) {
            initViews_1920x720();
        } else {
            initViews_800x480();
        }
    }

    private void initViews_1920x720() {
        this.mLeftManager = AddChildManager(0, 120, 442, 425, R.drawable.aic_bg_left_03);
        this.mRightManager = AddChildManager(1478, 120, 442, 425, R.drawable.aic_bg_right_03);
        for (int i = 0; i < 3; i++) {
            this.mIvLtArrows[i] = AddImage(this.mLeftManager, 128, 115, 190, 190, R.drawable.aic_arrow);
            this.mIvRtArrows[i] = AddImage(this.mRightManager, 124, 115, 190, 190, R.drawable.aic_arrow);
        }
        this.mTvLtWinds[0] = AddText(this.mLeftManager, 103, 286, "1");
        this.mTvLtWinds[1] = AddText(this.mLeftManager, 79, Can.CAN_GM_CAPTIVA_OD, "2");
        this.mTvLtWinds[2] = AddText(this.mLeftManager, 74, 180, "3");
        this.mTvLtWinds[3] = AddText(this.mLeftManager, 92, 124, MainSet.SP_KS_QOROS);
        this.mTvLtWinds[4] = AddText(this.mLeftManager, 130, 83, MainSet.SP_TW_CJW);
        this.mTvLtWinds[5] = AddText(this.mLeftManager, 185, 57, MainSet.SP_XS_DZ);
        this.mTvLtWinds[6] = AddText(this.mLeftManager, Can.CAN_MZD_TXB, 56, MainSet.SP_PCBA_VOL);
        this.mTvLtWinds[7] = AddText(this.mLeftManager, 292, 75, "8");
        this.mTvLtWinds[8] = AddText(this.mLeftManager, KeyDef.RKEY_EJECT_L, 120, "9");
        this.mTvLtWinds[9] = AddText(this.mLeftManager, 352, 178, "10");
        this.mTvLtWinds[10] = AddText(this.mLeftManager, 349, Can.CAN_BYD_S6_S7, "11");
        this.mTvLtWinds[11] = AddText(this.mLeftManager, KeyDef.RKEY_RADIO_3S, 285, "12");
        this.mTvRtWinds[0] = AddText(this.mRightManager, 99, 286, "1");
        this.mTvRtWinds[1] = AddText(this.mRightManager, 75, Can.CAN_GM_CAPTIVA_OD, "2");
        this.mTvRtWinds[2] = AddText(this.mRightManager, 70, 180, "3");
        this.mTvRtWinds[3] = AddText(this.mRightManager, 88, 124, MainSet.SP_KS_QOROS);
        this.mTvRtWinds[4] = AddText(this.mRightManager, 126, 83, MainSet.SP_TW_CJW);
        this.mTvRtWinds[5] = AddText(this.mRightManager, 181, 57, MainSet.SP_XS_DZ);
        this.mTvRtWinds[6] = AddText(this.mRightManager, Can.CAN_GM_CAPTIVA_OD, 55, MainSet.SP_PCBA_VOL);
        this.mTvRtWinds[7] = AddText(this.mRightManager, 288, 75, "8");
        this.mTvRtWinds[8] = AddText(this.mRightManager, KeyDef.RKEY_RDS_TA, 120, "9");
        this.mTvRtWinds[9] = AddText(this.mRightManager, 348, 178, "10");
        this.mTvRtWinds[10] = AddText(this.mRightManager, 345, Can.CAN_BYD_S6_S7, "11");
        this.mTvRtWinds[11] = AddText(this.mRightManager, KeyDef.RKEY_MEDIA_PROG, 285, "12");
        this.mTvMinTemps[0] = AddText(this.mLeftManager, 121, 311, this.mAppContext.getString(R.string.can_ac_low));
        this.mTvMinTemps[1] = AddText(this.mRightManager, 123, 311, this.mAppContext.getString(R.string.can_ac_low));
        this.mTvMaxTemps[0] = AddText(this.mLeftManager, 283, 311, this.mAppContext.getString(R.string.can_ac_high));
        this.mTvMaxTemps[1] = AddText(this.mRightManager, 278, 311, this.mAppContext.getString(R.string.can_ac_high));
        this.mTvTemps[0] = AddText(this.mLeftManager, 183, 170, 80, 83, TXZResourceManager.STYLE_DEFAULT);
        this.mTvTemps[1] = AddText(this.mRightManager, 179, 170, 80, 83, TXZResourceManager.STYLE_DEFAULT);
        this.mIvLtModes[0] = AddImage(this.mLeftManager, 54, 200, 68, 21, mModeUpIds[0], mModeDnIds[0]);
        this.mIvLtModes[1] = AddImage(this.mLeftManager, 104, 92, 55, 50, mModeUpIds[1], mModeDnIds[1]);
        this.mIvLtModes[2] = AddImage(this.mLeftManager, 190, 60, 55, 50, mModeUpIds[2], mModeDnIds[2]);
        this.mIvLtModes[3] = AddImage(this.mLeftManager, 276, 95, 55, 50, mModeUpIds[3], mModeDnIds[3]);
        this.mIvLtModes[4] = AddImage(this.mLeftManager, KeyDef.RKEY_ANGLEDN, 180, 55, 50, mModeUpIds[4], mModeDnIds[4]);
        this.mIvLtModes[5] = AddImage(this.mLeftManager, 294, 275, 55, 50, mModeUpIds[5], mModeDnIds[5]);
        this.mIvLtModes[6] = AddImage(this.mLeftManager, 204, 308, 55, 50, mModeUpIds[6], mModeDnIds[6]);
        this.mIvLtModes[7] = AddImage(this.mLeftManager, 107, 272, 55, 50, mModeUpIds[7], mModeDnIds[7]);
        this.mIvRtModes[0] = AddImage(this.mRightManager, 50, 200, 68, 21, mModeUpIds[0], mModeDnIds[0]);
        this.mIvRtModes[1] = AddImage(this.mRightManager, 100, 92, 55, 50, mModeUpIds[1], mModeDnIds[1]);
        this.mIvRtModes[2] = AddImage(this.mRightManager, 186, 60, 55, 50, mModeUpIds[2], mModeDnIds[2]);
        this.mIvRtModes[3] = AddImage(this.mRightManager, 272, 95, 55, 50, mModeUpIds[3], mModeDnIds[3]);
        this.mIvRtModes[4] = AddImage(this.mRightManager, KeyDef.RKEY_POWER, 180, 55, 50, mModeUpIds[4], mModeDnIds[4]);
        this.mIvRtModes[5] = AddImage(this.mRightManager, 290, 275, 55, 50, mModeUpIds[5], mModeDnIds[5]);
        this.mIvRtModes[6] = AddImage(this.mRightManager, 200, 308, 55, 50, mModeUpIds[6], mModeDnIds[6]);
        this.mIvRtModes[7] = AddImage(this.mRightManager, 103, 272, 55, 50, mModeUpIds[7], mModeDnIds[7]);
    }

    private void initViews_800x480() {
        this.mLeftManager = AddChildManager(0, 0, 399, 425, R.drawable.aic_bg_left_03_s);
        this.mRightManager = AddChildManager(625, 0, 399, 425, R.drawable.aic_bg_right_03_s);
        for (int i = 0; i < 3; i++) {
            this.mIvLtArrows[i] = AddImage(this.mLeftManager, 85, 115, 190, 190, R.drawable.aic_arrow);
            this.mIvRtArrows[i] = AddImage(this.mRightManager, 124, 115, 190, 190, R.drawable.aic_arrow);
        }
        this.mTvLtWinds[0] = AddText(this.mLeftManager, 60, 286, "1");
        this.mTvLtWinds[1] = AddText(this.mLeftManager, 36, Can.CAN_GM_CAPTIVA_OD, "2");
        this.mTvLtWinds[2] = AddText(this.mLeftManager, 31, 180, "3");
        this.mTvLtWinds[3] = AddText(this.mLeftManager, 49, 124, MainSet.SP_KS_QOROS);
        this.mTvLtWinds[4] = AddText(this.mLeftManager, 87, 83, MainSet.SP_TW_CJW);
        this.mTvLtWinds[5] = AddText(this.mLeftManager, 142, 57, MainSet.SP_XS_DZ);
        this.mTvLtWinds[6] = AddText(this.mLeftManager, 200, 56, MainSet.SP_PCBA_VOL);
        this.mTvLtWinds[7] = AddText(this.mLeftManager, Can.CAN_LUXGEN_WC, 75, "8");
        this.mTvLtWinds[8] = AddText(this.mLeftManager, 291, 120, "9");
        this.mTvLtWinds[9] = AddText(this.mLeftManager, 309, 178, "10");
        this.mTvLtWinds[10] = AddText(this.mLeftManager, 306, Can.CAN_BYD_S6_S7, "11");
        this.mTvLtWinds[11] = AddText(this.mLeftManager, 282, 285, "12");
        this.mTvRtWinds[0] = AddText(this.mRightManager, 99, 286, "1");
        this.mTvRtWinds[1] = AddText(this.mRightManager, 75, Can.CAN_GM_CAPTIVA_OD, "2");
        this.mTvRtWinds[2] = AddText(this.mRightManager, 70, 180, "3");
        this.mTvRtWinds[3] = AddText(this.mRightManager, 88, 124, MainSet.SP_KS_QOROS);
        this.mTvRtWinds[4] = AddText(this.mRightManager, 126, 83, MainSet.SP_TW_CJW);
        this.mTvRtWinds[5] = AddText(this.mRightManager, 181, 57, MainSet.SP_XS_DZ);
        this.mTvRtWinds[6] = AddText(this.mRightManager, Can.CAN_GM_CAPTIVA_OD, 55, MainSet.SP_PCBA_VOL);
        this.mTvRtWinds[7] = AddText(this.mRightManager, 288, 75, "8");
        this.mTvRtWinds[8] = AddText(this.mRightManager, KeyDef.RKEY_RDS_TA, 120, "9");
        this.mTvRtWinds[9] = AddText(this.mRightManager, 348, 178, "10");
        this.mTvRtWinds[10] = AddText(this.mRightManager, 345, Can.CAN_BYD_S6_S7, "11");
        this.mTvRtWinds[11] = AddText(this.mRightManager, KeyDef.RKEY_MEDIA_PROG, 285, "12");
        this.mTvMinTemps[0] = AddText(this.mLeftManager, 78, 311, this.mAppContext.getString(R.string.can_ac_low));
        this.mTvMinTemps[1] = AddText(this.mRightManager, 123, 311, this.mAppContext.getString(R.string.can_ac_low));
        this.mTvMaxTemps[0] = AddText(this.mLeftManager, Can.CAN_VOLKS_XP, 311, this.mAppContext.getString(R.string.can_ac_high));
        this.mTvMaxTemps[1] = AddText(this.mRightManager, 278, 311, this.mAppContext.getString(R.string.can_ac_high));
        this.mTvTemps[0] = AddText(this.mLeftManager, Can.CAN_BENC_ZMYT, 170, 80, 83, TXZResourceManager.STYLE_DEFAULT);
        this.mTvTemps[1] = AddText(this.mRightManager, 179, 170, 80, 83, TXZResourceManager.STYLE_DEFAULT);
        this.mIvLtModes[0] = AddImage(this.mLeftManager, 11, 200, 68, 21, mModeUpIds[0], mModeDnIds[0]);
        this.mIvLtModes[1] = AddImage(this.mLeftManager, 61, 92, 55, 50, mModeUpIds[1], mModeDnIds[1]);
        this.mIvLtModes[2] = AddImage(this.mLeftManager, 147, 60, 55, 50, mModeUpIds[2], mModeDnIds[2]);
        this.mIvLtModes[3] = AddImage(this.mLeftManager, Can.CAN_SGMW_WC, 95, 55, 50, mModeUpIds[3], mModeDnIds[3]);
        this.mIvLtModes[4] = AddImage(this.mLeftManager, 275, 180, 55, 50, mModeUpIds[4], mModeDnIds[4]);
        this.mIvLtModes[5] = AddImage(this.mLeftManager, Can.CAN_MG_ZS_WC, 275, 55, 50, mModeUpIds[5], mModeDnIds[5]);
        this.mIvLtModes[6] = AddImage(this.mLeftManager, 161, 308, 55, 50, mModeUpIds[6], mModeDnIds[6]);
        this.mIvLtModes[7] = AddImage(this.mLeftManager, 64, 272, 55, 50, mModeUpIds[7], mModeDnIds[7]);
        this.mIvRtModes[0] = AddImage(this.mRightManager, 50, 200, 68, 21, mModeUpIds[0], mModeDnIds[0]);
        this.mIvRtModes[1] = AddImage(this.mRightManager, 100, 92, 55, 50, mModeUpIds[1], mModeDnIds[1]);
        this.mIvRtModes[2] = AddImage(this.mRightManager, 186, 60, 55, 50, mModeUpIds[2], mModeDnIds[2]);
        this.mIvRtModes[3] = AddImage(this.mRightManager, 272, 95, 55, 50, mModeUpIds[3], mModeDnIds[3]);
        this.mIvRtModes[4] = AddImage(this.mRightManager, KeyDef.RKEY_POWER, 180, 55, 50, mModeUpIds[4], mModeDnIds[4]);
        this.mIvRtModes[5] = AddImage(this.mRightManager, 290, 275, 55, 50, mModeUpIds[5], mModeDnIds[5]);
        this.mIvRtModes[6] = AddImage(this.mRightManager, 200, 308, 55, 50, mModeUpIds[6], mModeDnIds[6]);
        this.mIvRtModes[7] = AddImage(this.mRightManager, 103, 272, 55, 50, mModeUpIds[7], mModeDnIds[7]);
    }

    private void initViews() {
        this.mLeftManager = AddChildManager(0, 0, 442, 425, R.drawable.aic_bg_left_03);
        this.mRightManager = AddChildManager(KeyDef.SKEY_HOME_5, 0, 442, 425, R.drawable.aic_bg_right_03);
        for (int i = 0; i < 3; i++) {
            this.mIvLtArrows[i] = AddImage(this.mLeftManager, 128, 115, 190, 190, R.drawable.aic_arrow);
            this.mIvRtArrows[i] = AddImage(this.mRightManager, 124, 115, 190, 190, R.drawable.aic_arrow);
        }
        this.mTvLtWinds[0] = AddText(this.mLeftManager, 103, 286, "1");
        this.mTvLtWinds[1] = AddText(this.mLeftManager, 79, Can.CAN_GM_CAPTIVA_OD, "2");
        this.mTvLtWinds[2] = AddText(this.mLeftManager, 74, 180, "3");
        this.mTvLtWinds[3] = AddText(this.mLeftManager, 92, 124, MainSet.SP_KS_QOROS);
        this.mTvLtWinds[4] = AddText(this.mLeftManager, 130, 83, MainSet.SP_TW_CJW);
        this.mTvLtWinds[5] = AddText(this.mLeftManager, 185, 57, MainSet.SP_XS_DZ);
        this.mTvLtWinds[6] = AddText(this.mLeftManager, Can.CAN_MZD_TXB, 56, MainSet.SP_PCBA_VOL);
        this.mTvLtWinds[7] = AddText(this.mLeftManager, 292, 75, "8");
        this.mTvLtWinds[8] = AddText(this.mLeftManager, KeyDef.RKEY_EJECT_L, 120, "9");
        this.mTvLtWinds[9] = AddText(this.mLeftManager, 352, 178, "10");
        this.mTvLtWinds[10] = AddText(this.mLeftManager, 349, Can.CAN_BYD_S6_S7, "11");
        this.mTvLtWinds[11] = AddText(this.mLeftManager, KeyDef.RKEY_RADIO_3S, 285, "12");
        this.mTvRtWinds[0] = AddText(this.mRightManager, 99, 286, "1");
        this.mTvRtWinds[1] = AddText(this.mRightManager, 75, Can.CAN_GM_CAPTIVA_OD, "2");
        this.mTvRtWinds[2] = AddText(this.mRightManager, 70, 180, "3");
        this.mTvRtWinds[3] = AddText(this.mRightManager, 88, 124, MainSet.SP_KS_QOROS);
        this.mTvRtWinds[4] = AddText(this.mRightManager, 126, 83, MainSet.SP_TW_CJW);
        this.mTvRtWinds[5] = AddText(this.mRightManager, 181, 57, MainSet.SP_XS_DZ);
        this.mTvRtWinds[6] = AddText(this.mRightManager, Can.CAN_GM_CAPTIVA_OD, 55, MainSet.SP_PCBA_VOL);
        this.mTvRtWinds[7] = AddText(this.mRightManager, 288, 75, "8");
        this.mTvRtWinds[8] = AddText(this.mRightManager, KeyDef.RKEY_RDS_TA, 120, "9");
        this.mTvRtWinds[9] = AddText(this.mRightManager, 348, 178, "10");
        this.mTvRtWinds[10] = AddText(this.mRightManager, 345, Can.CAN_BYD_S6_S7, "11");
        this.mTvRtWinds[11] = AddText(this.mRightManager, KeyDef.RKEY_MEDIA_PROG, 285, "12");
        this.mTvMinTemps[0] = AddText(this.mLeftManager, 121, 311, this.mAppContext.getString(R.string.can_ac_low));
        this.mTvMinTemps[1] = AddText(this.mRightManager, 123, 311, this.mAppContext.getString(R.string.can_ac_low));
        this.mTvMaxTemps[0] = AddText(this.mLeftManager, 283, 311, this.mAppContext.getString(R.string.can_ac_high));
        this.mTvMaxTemps[1] = AddText(this.mRightManager, 278, 311, this.mAppContext.getString(R.string.can_ac_high));
        this.mTvTemps[0] = AddText(this.mLeftManager, 183, 170, 80, 83, TXZResourceManager.STYLE_DEFAULT);
        this.mTvTemps[1] = AddText(this.mRightManager, 179, 170, 80, 83, TXZResourceManager.STYLE_DEFAULT);
        this.mIvLtModes[0] = AddImage(this.mLeftManager, 54, 200, 68, 21, mModeUpIds[0], mModeDnIds[0]);
        this.mIvLtModes[1] = AddImage(this.mLeftManager, 104, 92, 55, 50, mModeUpIds[1], mModeDnIds[1]);
        this.mIvLtModes[2] = AddImage(this.mLeftManager, 190, 60, 55, 50, mModeUpIds[2], mModeDnIds[2]);
        this.mIvLtModes[3] = AddImage(this.mLeftManager, 276, 95, 55, 50, mModeUpIds[3], mModeDnIds[3]);
        this.mIvLtModes[4] = AddImage(this.mLeftManager, KeyDef.RKEY_ANGLEDN, 180, 55, 50, mModeUpIds[4], mModeDnIds[4]);
        this.mIvLtModes[5] = AddImage(this.mLeftManager, 294, 275, 55, 50, mModeUpIds[5], mModeDnIds[5]);
        this.mIvLtModes[6] = AddImage(this.mLeftManager, 204, 308, 55, 50, mModeUpIds[6], mModeDnIds[6]);
        this.mIvLtModes[7] = AddImage(this.mLeftManager, 107, 272, 55, 50, mModeUpIds[7], mModeDnIds[7]);
        this.mIvRtModes[0] = AddImage(this.mRightManager, 50, 200, 68, 21, mModeUpIds[0], mModeDnIds[0]);
        this.mIvRtModes[1] = AddImage(this.mRightManager, 100, 92, 55, 50, mModeUpIds[1], mModeDnIds[1]);
        this.mIvRtModes[2] = AddImage(this.mRightManager, 186, 60, 55, 50, mModeUpIds[2], mModeDnIds[2]);
        this.mIvRtModes[3] = AddImage(this.mRightManager, 272, 95, 55, 50, mModeUpIds[3], mModeDnIds[3]);
        this.mIvRtModes[4] = AddImage(this.mRightManager, KeyDef.RKEY_POWER, 180, 55, 50, mModeUpIds[4], mModeDnIds[4]);
        this.mIvRtModes[5] = AddImage(this.mRightManager, 290, 275, 55, 50, mModeUpIds[5], mModeDnIds[5]);
        this.mIvRtModes[6] = AddImage(this.mRightManager, 200, 308, 55, 50, mModeUpIds[6], mModeDnIds[6]);
        this.mIvRtModes[7] = AddImage(this.mRightManager, 103, 272, 55, 50, mModeUpIds[7], mModeDnIds[7]);
    }

    private void showWindViews(boolean ltShow, boolean rtShow) {
        int rightBg;
        int leftBg;
        if (MainSet.GetScreenType() == 5 || MainSet.GetScreenType() == 9) {
            leftBg = R.drawable.aic_bg_left_01;
            rightBg = R.drawable.aic_bg_right_01;
        } else {
            leftBg = R.drawable.aic_bg_left_01_s;
            rightBg = R.drawable.aic_bg_right_01_s;
        }
        if (ltShow) {
            this.mLeftManager.GetLayout().setBackgroundResource(leftBg);
        }
        if (rtShow) {
            this.mRightManager.GetLayout().setBackgroundResource(rightBg);
        }
        for (View view : this.mTvLtWinds) {
            showGone(view, ltShow);
        }
        for (View view2 : this.mTvRtWinds) {
            showGone(view2, rtShow);
        }
        showGone(this.mIvLtArrows[0], ltShow);
        showGone(this.mIvRtArrows[0], rtShow);
    }

    private void showTempViews(boolean ltShow, boolean rtShow) {
        int rightBg;
        int leftBg;
        if (MainSet.GetScreenType() == 5 || MainSet.GetScreenType() == 9) {
            leftBg = R.drawable.aic_bg_left_02;
            rightBg = R.drawable.aic_bg_right_02;
        } else {
            leftBg = R.drawable.aic_bg_left_02_s;
            rightBg = R.drawable.aic_bg_right_02_s;
        }
        if (ltShow) {
            this.mLeftManager.GetLayout().setBackgroundResource(leftBg);
        }
        if (rtShow) {
            this.mRightManager.GetLayout().setBackgroundResource(rightBg);
        }
        showGone(this.mTvTemps[0], ltShow);
        showGone(this.mTvMinTemps[0], ltShow);
        showGone(this.mTvMaxTemps[0], ltShow);
        showGone(this.mTvTemps[1], rtShow);
        showGone(this.mTvMinTemps[1], rtShow);
        showGone(this.mTvMaxTemps[1], rtShow);
        showGone(this.mIvLtArrows[1], ltShow);
        showGone(this.mIvRtArrows[1], rtShow);
    }

    private void showModeViews(boolean ltShow, boolean rtShow) {
        int rightBg;
        int leftBg;
        if (MainSet.GetScreenType() == 5 || MainSet.GetScreenType() == 9) {
            leftBg = R.drawable.aic_bg_left_03;
            rightBg = R.drawable.aic_bg_right_03;
        } else {
            leftBg = R.drawable.aic_bg_left_03_s;
            rightBg = R.drawable.aic_bg_right_03_s;
        }
        if (ltShow) {
            this.mLeftManager.GetLayout().setBackgroundResource(leftBg);
        }
        if (rtShow) {
            this.mRightManager.GetLayout().setBackgroundResource(rightBg);
        }
        for (View v : this.mIvLtModes) {
            showGone(v, ltShow);
        }
        for (View v2 : this.mIvRtModes) {
            showGone(v2, rtShow);
        }
        showGone(this.mIvLtArrows[2], ltShow);
        showGone(this.mIvRtArrows[2], rtShow);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Init(CanFunc.mContext);
        Log.d(TAG, "-----onResume-----");
        showBox(0);
        this.mWinManager.addView(this.mManager.GetLayout(), this.mWinParams);
        ResetData(false);
        mIsAC = true;
        this.nDelayCheck = 200;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d(TAG, "----onPause-----");
        this.mWinManager.removeView(this.mManager.GetLayout());
        mIsAC = false;
        mfgJump = false;
        this.nDelayCheck = 0;
    }

    private void showBox(int boxStatus) {
        switch (boxStatus) {
            case 0:
                this.mLeftManager.GetLayout().setVisibility(8);
                this.mRightManager.GetLayout().setVisibility(8);
                return;
            case 1:
                this.mLeftBoxDelayTime = 100;
                this.mLeftManager.GetLayout().setVisibility(0);
                this.mRightManager.GetLayout().setVisibility(8);
                return;
            case 2:
                this.mRightBoxDelayTime = 100;
                this.mLeftManager.GetLayout().setVisibility(8);
                this.mRightManager.GetLayout().setVisibility(0);
                return;
            case 3:
                this.mLeftBoxDelayTime = 100;
                this.mRightBoxDelayTime = 100;
                this.mLeftManager.GetLayout().setVisibility(0);
                this.mRightManager.GetLayout().setVisibility(0);
                return;
            default:
                return;
        }
    }

    public void ShowAC() {
        if (!mIsAC) {
            CanJni.AudiZmytGetAirInfo(this.mAirInfo);
            if (i2b(this.mAirInfo.fgLtemp) || this.mAirInfo.DriveInfo != 0 || i2b(this.mAirInfo.fgRtemp) || this.mAirInfo.PassengerInfo != 0 || this.mAirInfo.fgWind != 0 || this.mAirInfo.fgMode != 0) {
                mfgJump = true;
                onResume();
            }
        }
    }

    public void UserAll() {
        if (mIsAC) {
            ResetData(true);
        }
    }

    private boolean isDoubleAirMode() {
        if (((FtSet.Getlgb1() & 4) >> 2) == 1) {
            return true;
        }
        return false;
    }

    private void ResetData(boolean check) {
        boolean showRtTemp;
        boolean showLtWind;
        boolean showRtWind;
        boolean showLtMode;
        boolean showRtMode;
        boolean showLt;
        boolean showRt;
        Can.updateAC();
        this.mACInfo = Can.mACInfo;
        if (!check || this.mACInfo.Update != 0) {
            this.mACInfo.Update = 0;
            updateACUI();
        }
        CanJni.AudiZmytGetAirInfo(this.mAirInfo);
        if (this.mAirInfo.UpdateOnce != 0 && (!check || this.mAirInfo.Update != 0)) {
            this.mAirInfo.Update = 0;
            boolean showLtTemp = i2b(this.mAirInfo.fgLtemp) || this.mAirInfo.DriveInfo == 2;
            if (i2b(this.mAirInfo.fgRtemp) || this.mAirInfo.PassengerInfo == 2) {
                showRtTemp = true;
            } else {
                showRtTemp = false;
            }
            if (i2b(this.mAirInfo.fgWind) || this.mAirInfo.DriveInfo == 5) {
                showLtWind = true;
            } else {
                showLtWind = false;
            }
            if ((!isDoubleAirMode() || !i2b(this.mAirInfo.fgWind)) && this.mAirInfo.PassengerInfo != 5) {
                showRtWind = false;
            } else {
                showRtWind = true;
            }
            if (i2b(this.mAirInfo.fgMode) || this.mAirInfo.DriveInfo == 1) {
                showLtMode = true;
            } else {
                showLtMode = false;
            }
            if ((!isDoubleAirMode() || !i2b(this.mAirInfo.fgMode)) && this.mAirInfo.PassengerInfo != 1) {
                showRtMode = false;
            } else {
                showRtMode = true;
            }
            if (showLtTemp || showLtWind || showLtMode) {
                showLt = true;
            } else {
                showLt = false;
            }
            if (showRtTemp || showRtWind || showRtMode) {
                showRt = true;
            } else {
                showRt = false;
            }
            showWindViews(showLtWind, showRtWind);
            showTempViews(showLtTemp, showRtTemp);
            showModeViews(showLtMode, showRtMode);
            if (showLt && showRt) {
                showBox(3);
                this.nDelayCheck = 200;
            } else if (showLt) {
                showBox(1);
                this.nDelayCheck = 200;
            } else if (showRt) {
                showBox(2);
                this.nDelayCheck = 200;
            } else {
                this.nDelayCheck = 1;
                showBox(0);
            }
        }
        if (mfgJump) {
            if (this.mLeftBoxDelayTime != 0) {
                this.mLeftBoxDelayTime--;
            }
            if (this.mRightBoxDelayTime != 0) {
                this.mRightBoxDelayTime--;
            }
            if (this.nDelayCheck != 0) {
                this.nDelayCheck--;
                if (this.nDelayCheck == 0 || CanFunc.IsCamMode() > 0) {
                    onPause();
                    Log.d("HAHA", "UserAll Exit Ac");
                }
            }
        }
    }

    private void updateACUI() {
        boolean isLtShowing;
        boolean isRtShowing;
        updateMode(true, i2b(this.mACInfo.fgAutoMode), i2b(this.mACInfo.fgUpWind), i2b(this.mACInfo.fgParallelWind), i2b(this.mACInfo.fgDownWind));
        updateMode(false, i2b(this.mACInfo.fgAutoWind), i2b(this.mACInfo.bUpWindFlgR), i2b(this.mACInfo.bParallelWindFlgR), i2b(this.mACInfo.bDownWindFlgR));
        updateTemp(true, this.mACInfo.nLeftTemp, this.mACInfo.szLtTemp);
        updateTemp(false, this.mACInfo.nRightTemp, this.mACInfo.szRtTemp);
        updateWind(true, this.mACInfo.nWindValue);
        updateWind(false, this.mACInfo.nWindValueR);
        int[] datas = {this.mACInfo.fgAutoMode, this.mACInfo.fgUpWind, this.mACInfo.fgParallelWind, this.mACInfo.fgDownWind, this.mACInfo.fgAutoWind, this.mACInfo.bUpWindFlgR, this.mACInfo.bParallelWindFlgR, this.mACInfo.bDownWindFlgR, this.mACInfo.nLeftTemp, this.mACInfo.nRightTemp, this.mACInfo.nWindValue, this.mACInfo.nWindValueR};
        for (int i = 0; i < datas.length; i++) {
            if (this.mLastDatas[i] != datas[i]) {
                if (this.mLeftManager.GetLayout().getVisibility() == 0) {
                    isLtShowing = true;
                } else {
                    isLtShowing = false;
                }
                if (this.mRightManager.GetLayout().getVisibility() == 0) {
                    isRtShowing = true;
                } else {
                    isRtShowing = false;
                }
                if (isLtShowing || isRtShowing) {
                    this.nDelayCheck = 200;
                }
            }
        }
        this.mLastDatas = datas;
    }

    private void updateWind(boolean isLeft, int wind) {
        TextView[] tvWinds = isLeft ? this.mTvLtWinds : this.mTvRtWinds;
        View arrowView = isLeft ? this.mIvLtArrows[0] : this.mIvRtArrows[0];
        arrowView.setRotation(0.0f);
        for (TextView v : tvWinds) {
            v.setTextColor(-1);
        }
        if (wind >= 1 && wind <= 12) {
            tvWinds[wind - 1].setTextColor(SupportMenu.CATEGORY_MASK);
            arrowView.setRotation(-132.0f + (24.0f * ((float) (wind - 1))));
        }
    }

    private void updateTemp(boolean isLeft, int tempInt, String tempStr) {
        View arrowView;
        TextView tvTemp = isLeft ? this.mTvTemps[0] : this.mTvTemps[1];
        if (isLeft) {
            arrowView = this.mIvLtArrows[1];
        } else {
            arrowView = this.mIvRtArrows[1];
        }
        arrowView.setRotation(0.0f);
        if (!TextUtils.isEmpty(tempStr)) {
            tvTemp.setText(tempStr);
        }
        if (tempInt == 0) {
            arrowView.setRotation(-132.0f);
        } else if (tempInt == 31) {
            arrowView.setRotation(132.0f);
        } else if (tempInt >= 1 && tempInt <= 28) {
            arrowView.setRotation(-120.0f + (8.9f * ((float) (tempInt - 1))));
        }
    }

    private void updateMode(boolean isLeft, boolean auto, boolean up, boolean pallax, boolean down) {
        View[] views = isLeft ? this.mIvLtModes : this.mIvRtModes;
        View arrowView = isLeft ? this.mIvLtArrows[2] : this.mIvRtArrows[2];
        arrowView.setRotation(0.0f);
        for (View v : views) {
            v.setSelected(false);
        }
        int index = -1;
        if (auto) {
            index = 0;
        } else if (up && pallax && down) {
            index = 7;
        } else if (pallax && down) {
            index = 5;
        } else if (up && pallax) {
            index = 3;
        } else if (up && down) {
            index = 1;
        } else if (up) {
            index = 2;
        } else if (pallax) {
            index = 4;
        } else if (down) {
            index = 6;
        }
        if (index != -1) {
            views[index].setSelected(true);
            arrowView.setRotation((float) ((index * 45) - 90));
        }
    }

    private RelativeLayoutManager AddChildManager(int x, int y, int w, int h, int resId) {
        RelativeLayout view = new RelativeLayout(this.mAppContext);
        view.setBackgroundResource(resId);
        this.mManager.AddView(view, x, y, w, h);
        return new RelativeLayoutManager(view);
    }

    private ImageView AddImage(RelativeLayoutManager manager, int x, int y, int w, int h, int resId) {
        ImageView img = manager.AddImage(x, y, w, h);
        if (resId != -1) {
            img.setImageResource(resId);
        }
        img.setVisibility(0);
        return img;
    }

    private CustomImgView AddImage(RelativeLayoutManager manager, int x, int y, int w, int h, int upId, int dnId) {
        CustomImgView img = manager.AddImage(x, y, w, h);
        img.setStateDrawable(upId, dnId);
        img.setVisibility(0);
        return img;
    }

    private TextView AddText(RelativeLayoutManager manager, int x, int y, String text) {
        TextView tv = manager.AddText(x, y);
        tv.setTextColor(-1);
        tv.setText(text);
        tv.setTextSize(0, 24.0f);
        return tv;
    }

    private TextView AddText(RelativeLayoutManager manager, int x, int y, int w, int h, String text) {
        TextView tv = manager.AddText(x, y, w, h);
        tv.setTextColor(SupportMenu.CATEGORY_MASK);
        tv.setGravity(17);
        tv.setText(text);
        tv.setTextSize(0, 36.0f);
        return tv;
    }

    private void showGone(View v, boolean show) {
        v.setVisibility(show ? 0 : 8);
    }

    private boolean i2b(int value) {
        return value > 0;
    }
}
