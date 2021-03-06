package com.ts.can.benc.withcd;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanBencWithCDACUI implements UserCallBack {
    public static final String TAG = "CanBencWithCDACUI";
    protected static Context mContext;
    static CanBencWithCDACUI mInstance;
    protected static boolean mIsAC = false;
    protected static boolean mfgJump = false;
    private CustomImgView mAC;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private CustomImgView[] mAirMode = new CustomImgView[4];
    private CustomImgView[] mAirMode2 = new CustomImgView[4];
    private CustomImgView mFrontMax;
    private RelativeLayout mLayout;
    private CustomImgView mLoopMode;
    private RelativeLayoutManager mManager;
    private CustomImgView mPeople;
    private CustomImgView mPeople2;
    private CustomImgView mRearHot;
    private CustomImgView mSync;
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvRtTemp;
    private CustomTextView mTvWindTemp;
    private CustomTextView mTvWindTemp2;
    private CustomImgView mWindIcon;
    private CustomImgView mWindIcon2;
    private int nDelayCheck = 0;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    protected CanBencWithCDACUI() {
    }

    public static CanBencWithCDACUI GetInstance() {
        if (mInstance == null) {
            mInstance = new CanBencWithCDACUI();
        }
        return mInstance;
    }

    public void InintWinManage(int nSizeX, int nSizeY, int nPosX, int nPosY, Context MyContext) {
        this.wManager = (WindowManager) MyContext.getSystemService("window");
        this.wmParams = new WindowManager.LayoutParams();
        this.wmParams.type = 2003;
        this.wmParams.format = 1;
        this.wmParams.flags = 8;
        this.wmParams.gravity = 81;
        this.wmParams.x = nPosX;
        this.wmParams.y = nPosY;
        this.wmParams.width = nSizeX;
        this.wmParams.height = nSizeY;
    }

    public void InitAc(Context context) {
        if (this.mLayout != null || context == null) {
            Log.d(TAG, "Already have instance");
            return;
        }
        Log.d(TAG, "InitAc");
        mContext = context;
        this.mLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.activity_can_ac_benc_withcd, (ViewGroup) null);
        if (MainSet.GetScreenType() == 5) {
            InintWinManage(1280, 75, 0, 0, mContext);
        } else if (MainSet.GetScreenType() == 9) {
            InintWinManage(1920, 75, 0, 0, mContext);
        } else {
            InintWinManage(1024, 97, 0, 0, mContext);
        }
        this.wManager.addView(this.mLayout, this.wmParams);
        this.mManager = new RelativeLayoutManager(this.mLayout);
        if (MainSet.GetScreenType() == 5) {
            initViews();
        } else if (MainSet.GetScreenType() == 9) {
            initViews_1920x720();
        } else {
            initViews_1024x600();
        }
    }

    private void initViews_1920x720() {
        this.mManager.AddImage(0, 0, 1920, 75).setBackgroundResource(R.drawable.main_ac_bg);
        this.mTvLtTemp = AddTemp(65, 10, 108, 63);
        this.mTvRtTemp = AddTemp(1740, 10, 108, 63);
        this.mWindIcon = this.mManager.AddImage(274, 10, R.drawable.main_ac_fan_up);
        this.mTvWindTemp = AddText(356, 10, 108, 63);
        this.mPeople = this.mManager.AddImage(484, 10, R.drawable.main_ac_fx00_up);
        this.mAirMode[0] = this.mManager.AddImage(484, 10, R.drawable.main_ac_fx01_dn);
        this.mAirMode[1] = this.mManager.AddImage(484, 10, R.drawable.main_ac_fx02_dn);
        this.mAirMode[2] = this.mManager.AddImage(484, 10, R.drawable.main_ac_fx03_dn);
        this.mAirMode[3] = this.mManager.AddImage(484, 10, R.drawable.main_ac_fx04_dn);
        this.mAC = this.mManager.AddImage(693, 10);
        this.mAC.setStateDrawable(R.drawable.main_ac_aco_up, R.drawable.main_ac_aco_dn);
        this.mFrontMax = this.mManager.AddImage(903, 10);
        this.mFrontMax.setStateDrawable(R.drawable.main_ac_max_up, R.drawable.main_ac_max_dn);
        this.mRearHot = this.mManager.AddImage(CanCameraUI.BTN_CCH2WC_MODE3, 10);
        this.mRearHot.setStateDrawable(R.drawable.main_ac_real_up, R.drawable.main_ac_real_dn);
        this.mPeople2 = this.mManager.AddImage(1321, 10, R.drawable.main_ac_fx00_up);
        this.mAirMode2[0] = this.mManager.AddImage(1321, 10, R.drawable.main_ac_fx01_dn);
        this.mAirMode2[1] = this.mManager.AddImage(1321, 10, R.drawable.main_ac_fx02_dn);
        this.mAirMode2[2] = this.mManager.AddImage(1321, 10, R.drawable.main_ac_fx03_dn);
        this.mAirMode2[3] = this.mManager.AddImage(1321, 10, R.drawable.main_ac_fx04_dn);
        this.mWindIcon2 = this.mManager.AddImage(1531, 10, R.drawable.main_ac_fan_up);
        this.mTvWindTemp2 = AddText(CanCameraUI.BTN_MGZS_WC_MODE5, 10, 108, 63);
    }

    private void initViews_1024x600() {
        this.mManager.AddImage(0, 0, 1024, 97).setBackgroundResource(R.drawable.main_ac_bg1);
        this.mTvLtTemp = AddTemp(14, 16, 108, 63);
        this.mTvRtTemp = AddTemp(898, 16, 108, 63);
        this.mTvLtTemp.SetPxSize(50);
        this.mTvRtTemp.SetPxSize(50);
        this.mWindIcon = this.mManager.AddImage(126, 4, R.drawable.main_ac_fan_up1);
        this.mTvWindTemp = AddText(180, 18, 98, 63);
        this.mTvWindTemp.SetPxSize(50);
        this.mPeople = this.mManager.AddImage(256, 8, R.drawable.main_ac_fx00_up1);
        this.mAirMode[0] = this.mManager.AddImage(256, 8, R.drawable.main_ac_fx01_dn1);
        this.mAirMode[1] = this.mManager.AddImage(256, 8, R.drawable.main_ac_fx02_dn1);
        this.mAirMode[2] = this.mManager.AddImage(256, 8, R.drawable.main_ac_fx03_dn1);
        this.mAirMode[3] = this.mManager.AddImage(256, 8, R.drawable.main_ac_fx04_dn1);
        this.mAC = this.mManager.AddImage(360, 4);
        this.mAC.setStateDrawable(R.drawable.main_ac_aco_up1, R.drawable.main_ac_aco_dn1);
        this.mFrontMax = this.mManager.AddImage(500, 8);
        this.mFrontMax.setStateDrawable(R.drawable.main_ac_max_up1, R.drawable.main_ac_max_dn1);
        this.mRearHot = this.mManager.AddImage(CanCameraUI.BTN_CAMERY_2018_MODE1, 8);
        this.mRearHot.setStateDrawable(R.drawable.main_ac_real_up1, R.drawable.main_ac_real_dn1);
        this.mPeople2 = this.mManager.AddImage(660, 8, R.drawable.main_ac_fx00_up1);
        this.mAirMode2[0] = this.mManager.AddImage(660, 8, R.drawable.main_ac_fx01_dn1);
        this.mAirMode2[1] = this.mManager.AddImage(660, 8, R.drawable.main_ac_fx02_dn1);
        this.mAirMode2[2] = this.mManager.AddImage(660, 8, R.drawable.main_ac_fx03_dn1);
        this.mAirMode2[3] = this.mManager.AddImage(660, 8, R.drawable.main_ac_fx04_dn1);
        this.mWindIcon2 = this.mManager.AddImage(KeyDef.SKEY_CALLUP_1, 4, R.drawable.main_ac_fan_up1);
        this.mTvWindTemp2 = AddText(750, 18, 98, 63);
        this.mTvWindTemp2.SetPxSize(50);
    }

    private void initViews() {
        this.mManager.AddImage(0, 0, 1280, 75).setBackgroundResource(R.drawable.main_ac_bg);
        this.mTvLtTemp = AddTemp(14, 8, 108, 63);
        this.mTvRtTemp = AddTemp(1154, 8, 108, 63);
        this.mWindIcon = this.mManager.AddImage(146, 8, R.drawable.main_ac_fan_up);
        this.mTvWindTemp = AddText(Can.CAN_SAIL_RW550_MG6_WC, 8, 108, 63);
        this.mPeople = this.mManager.AddImage(294, 8, R.drawable.main_ac_fx00_up);
        this.mAirMode[0] = this.mManager.AddImage(294, 8, R.drawable.main_ac_fx01_dn);
        this.mAirMode[1] = this.mManager.AddImage(294, 8, R.drawable.main_ac_fx02_dn);
        this.mAirMode[2] = this.mManager.AddImage(294, 8, R.drawable.main_ac_fx03_dn);
        this.mAirMode[3] = this.mManager.AddImage(294, 8, R.drawable.main_ac_fx04_dn);
        this.mAC = this.mManager.AddImage(448, 8);
        this.mAC.setStateDrawable(R.drawable.main_ac_aco_up, R.drawable.main_ac_aco_dn);
        this.mFrontMax = this.mManager.AddImage(595, 8);
        this.mFrontMax.setStateDrawable(R.drawable.main_ac_max_up, R.drawable.main_ac_max_dn);
        this.mRearHot = this.mManager.AddImage(712, 8);
        this.mRearHot.setStateDrawable(R.drawable.main_ac_real_up, R.drawable.main_ac_real_dn);
        this.mPeople2 = this.mManager.AddImage(854, 8, R.drawable.main_ac_fx00_up);
        this.mAirMode2[0] = this.mManager.AddImage(854, 8, R.drawable.main_ac_fx01_dn);
        this.mAirMode2[1] = this.mManager.AddImage(854, 8, R.drawable.main_ac_fx02_dn);
        this.mAirMode2[2] = this.mManager.AddImage(854, 8, R.drawable.main_ac_fx03_dn);
        this.mAirMode2[3] = this.mManager.AddImage(854, 8, R.drawable.main_ac_fx04_dn);
        this.mWindIcon2 = this.mManager.AddImage(CanCameraUI.BTN_TRUMPCHI_WC_MODE4, 8, R.drawable.main_ac_fan_up);
        this.mTvWindTemp2 = AddText(1064, 8, 108, 63);
    }

    public void Destroy() {
        Log.i(TAG, "Destroy");
        if (this.mLayout != null) {
            this.wManager.removeView(this.mLayout);
            this.mLayout = null;
        }
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setTag(Integer.valueOf(id));
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTemp(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(60);
        temp.setText("88.8℃");
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddText(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(50);
        temp.setText("Level 1");
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        InitAc(CanFunc.mContext);
        ResetData(false);
        this.mLayout.setVisibility(0);
        Log.d(TAG, "-----onResume-----");
        mIsAC = true;
        this.nDelayCheck = 100;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d(TAG, "-----onPause-----");
        this.mLayout.setVisibility(4);
        mIsAC = false;
        mfgJump = false;
        this.nDelayCheck = 0;
    }

    private void updateACUI() {
        this.mACInfo = Can.mACInfo;
        this.mTvLtTemp.setText(this.mACInfo.szLtTemp);
        this.mTvRtTemp.setText(this.mACInfo.szRtTemp);
        if (this.mACInfo.fgAutoMode > 0) {
            this.mAirMode[0].setVisibility(8);
            this.mAirMode[1].setVisibility(8);
            this.mAirMode[2].setVisibility(8);
            this.mAirMode[3].setVisibility(0);
        } else {
            this.mAirMode[3].setVisibility(8);
            if (this.mACInfo.fgParallelWind > 0) {
                this.mAirMode[0].setVisibility(0);
            } else {
                this.mAirMode[0].setVisibility(8);
            }
            if (this.mACInfo.fgDownWind > 0) {
                this.mAirMode[1].setVisibility(0);
            } else {
                this.mAirMode[1].setVisibility(8);
            }
            if (this.mACInfo.fgUpWind > 0) {
                this.mAirMode[2].setVisibility(0);
            } else {
                this.mAirMode[2].setVisibility(8);
            }
        }
        if (this.mACInfo.fgAutoModeR > 0) {
            this.mAirMode2[0].setVisibility(8);
            this.mAirMode2[1].setVisibility(8);
            this.mAirMode2[2].setVisibility(8);
            this.mAirMode2[3].setVisibility(0);
        } else {
            this.mAirMode2[3].setVisibility(8);
            if (this.mACInfo.bParallelWindFlgR > 0) {
                this.mAirMode2[0].setVisibility(0);
            } else {
                this.mAirMode2[0].setVisibility(8);
            }
            if (this.mACInfo.bDownWindFlgR > 0) {
                this.mAirMode2[1].setVisibility(0);
            } else {
                this.mAirMode2[1].setVisibility(8);
            }
            if (this.mACInfo.bUpWindFlgR > 0) {
                this.mAirMode2[2].setVisibility(0);
            } else {
                this.mAirMode2[2].setVisibility(8);
            }
        }
        this.mAC.SetSel(this.mACInfo.fgAC);
        this.mFrontMax.SetSel(this.mACInfo.fgMaxFornt);
        if (this.mACInfo.fgAutoWind > 0) {
            this.mTvWindTemp.SetPxSize(30);
            this.mTvWindTemp.setText("AUTO");
        } else {
            this.mTvWindTemp.SetPxSize(50);
            if (this.mACInfo.nWindValue % 10 > 0) {
                this.mTvWindTemp.setText(String.format("%.1f", new Object[]{Double.valueOf(((double) this.mACInfo.nWindValue) * 0.1d)}));
            } else {
                this.mTvWindTemp.setText(String.format("%.0f", new Object[]{Double.valueOf(((double) this.mACInfo.nWindValue) * 0.1d)}));
            }
        }
        if (this.mACInfo.fgAutoWindR > 0) {
            this.mTvWindTemp2.SetPxSize(30);
            this.mTvWindTemp2.setText("AUTO");
        } else {
            this.mTvWindTemp2.SetPxSize(50);
            if (this.mACInfo.nWindValueR % 10 > 0) {
                this.mTvWindTemp2.setText(String.format("%.1f", new Object[]{Double.valueOf(((double) this.mACInfo.nWindValueR) * 0.1d)}));
            } else {
                this.mTvWindTemp2.setText(String.format("%.0f", new Object[]{Double.valueOf(((double) this.mACInfo.nWindValueR) * 0.1d)}));
            }
        }
        this.mRearHot.SetSel(this.mACInfo.fgRearLight);
    }

    private void ResetData(boolean check) {
        Can.updateAC();
        if (!check || Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (mfgJump && this.nDelayCheck != 0) {
            this.nDelayCheck--;
            if (this.nDelayCheck == 0 || CanFunc.IsCamMode() > 0) {
                onPause();
                Log.d(TAG, "UserAll Exit Ac");
            }
        }
    }

    public void UserAll() {
        if (mIsAC) {
            ResetData(true);
        }
    }

    public void ShowAC() {
        if (!mIsAC) {
            mfgJump = true;
            onResume();
            return;
        }
        this.nDelayCheck = 100;
    }
}
