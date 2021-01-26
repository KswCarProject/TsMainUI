package com.ts.can.cadillac.xhd;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.canview.MyProgressBar;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanCadillacAtsXhdACUI implements UserCallBack {
    public static final String TAG = "CanCadillacAtsXhdACUI";
    protected static Context mContext;
    static CanCadillacAtsXhdACUI mInstance;
    protected static boolean mIsAC;
    protected static boolean mfgJump;
    private CustomImgView mAC;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private CustomImgView[] mAirMode = new CustomImgView[4];
    private CustomImgView mAuto;
    private CustomImgView mBtnLtHot;
    private CustomImgView mBtnLtWind;
    private CustomImgView mBtnRtHot;
    private CustomImgView mBtnRtWind;
    private CustomImgView mFrontMax;
    private CustomImgView mFrontWind;
    private RelativeLayout mLayout;
    private CustomImgView mLoopMode;
    private ParamButton[] mLtHots = new ParamButton[3];
    private RelativeLayoutManager mManager;
    private CustomImgView mPeople;
    private CustomImgView mRearHot;
    private ParamButton[] mRtHots = new ParamButton[3];
    private CustomImgView mSync;
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvRtTemp;
    private CustomImgView[] mViewWind = new CustomImgView[2];
    private MyProgressBar mWindProg;
    private int nDelayCheck = 0;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    protected CanCadillacAtsXhdACUI() {
    }

    public static CanCadillacAtsXhdACUI GetInstance() {
        if (mInstance == null) {
            mInstance = new CanCadillacAtsXhdACUI();
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
        Log.d(TAG, "Init");
        mContext = context;
        this.mLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.activity_can_ac_cadillax_ats, (ViewGroup) null);
        InintWinManage(1018, 114, 0, 0, mContext);
        this.wManager.addView(this.mLayout, this.wmParams);
        this.mManager = new RelativeLayoutManager(this.mLayout);
        if (CanJni.GetSubType() == 1 || CanJni.GetSubType() == 2) {
            initHighViews();
        } else {
            initLowViews();
        }
    }

    private void initHighViews() {
        this.mTvLtTemp = AddTemp(2, 21, 92, 71);
        this.mTvRtTemp = AddTemp(924, 21, 92, 71);
        this.mAuto = this.mManager.AddImage(KeyDef.RKEY_EJECT_L, 66);
        this.mAuto.setStateDrawable(R.drawable.cadillac_ac_auto_up, R.drawable.cadillac_ac_auto_dn);
        this.mFrontWind = this.mManager.AddImage(183, 41);
        this.mFrontWind.setStateDrawable(R.drawable.cadillac_ac_wind_up, R.drawable.cadillac_ac_wind_dn);
        this.mAirMode[0] = this.mManager.AddImage(271, 16);
        this.mAirMode[0].setStateDrawable(R.drawable.cadillac_ac_show01_up, R.drawable.cadillac_ac_show01_dn);
        this.mAirMode[1] = this.mManager.AddImage(Can.CAN_MG_ZS_WC, 63);
        this.mAirMode[1].setStateDrawable(R.drawable.cadillac_ac_show02_up, R.drawable.cadillac_ac_show02_dn);
        this.mAirMode[2] = this.mManager.AddImage(Can.CAN_MG_ZS_WC, 57);
        this.mAirMode[2].setStateDrawable(R.drawable.cadillac_ac_show03_up, R.drawable.cadillac_ac_show03_dn);
        this.mAirMode[3] = this.mManager.AddImage(Can.CAN_MG_ZS_WC, 43);
        this.mAirMode[3].setStateDrawable(R.drawable.cadillac_ac_show04_up, R.drawable.cadillac_ac_show04_dn);
        this.mPeople = this.mManager.AddImage(273, 46, R.drawable.cadillac_ac_show05_up);
        this.mLoopMode = this.mManager.AddImage(KeyDef.RKEY_AVIN, 20);
        this.mLoopMode.setStateDrawable(R.drawable.cadillac_ac_nxh_dn, R.drawable.cadillac_ac_wxh_dn);
        this.mViewWind[0] = this.mManager.AddImage(408, 44, R.drawable.cadillac_ac_xuehua_s);
        this.mViewWind[1] = this.mManager.AddImage(CanCameraUI.BTN_CHANA_CS75_MODE6, 39, R.drawable.cadillac_ac_xuehua_b);
        this.mSync = this.mManager.AddImage(CanCameraUI.BTN_LANDWIND_3D_FRONT, 56);
        this.mSync.setStateDrawable(R.drawable.cadillac_ac_sync_up, R.drawable.cadillac_ac_sync_dn);
        this.mFrontMax = this.mManager.AddImage(737, 36);
        this.mFrontMax.setStateDrawable(R.drawable.cadillac_ac_max_up, R.drawable.cadillac_ac_max_dn);
        this.mRearHot = this.mManager.AddImage(811, 36);
        this.mRearHot.setStateDrawable(R.drawable.cadillac_ac_heat_up, R.drawable.cadillac_ac_heat_dn);
        this.mAC = this.mManager.AddImage(657, 20);
        this.mAC.setStateDrawable(R.drawable.cadillac_ac_ac_up, R.drawable.cadillac_ac_ac_dn);
        this.mBtnLtHot = this.mManager.AddImage(87, 3);
        this.mBtnLtHot.setStateDrawable(R.drawable.cadg_ac_lchair_up, R.drawable.cadg_ac_lchair_dn);
        this.mBtnLtHot.Show(false);
        this.mBtnRtHot = this.mManager.AddImage(852, 3);
        this.mBtnRtHot.setStateDrawable(R.drawable.cadg_ac_rchair_up, R.drawable.cadg_ac_rchair_dn);
        this.mBtnRtHot.Show(false);
        this.mBtnLtWind = this.mManager.AddImage(115, 60);
        this.mBtnLtWind.setStateDrawable(R.drawable.cadg_ac_lfan_up, R.drawable.cadg_ac_lfan_dn);
        this.mBtnLtWind.Show(false);
        this.mBtnRtWind = this.mManager.AddImage(KeyDef.SKEY_NAVI_5, 60);
        this.mBtnRtWind.setStateDrawable(R.drawable.cadg_ac_rfan_up, R.drawable.cadg_ac_rfan_dn);
        this.mBtnRtWind.Show(false);
        this.mLtHots[2] = AddBtn(-1, 96, 60, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mLtHots[1] = AddBtn(-1, 106, 75, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mLtHots[0] = AddBtn(-1, 116, 90, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mRtHots[2] = AddBtn(-1, 918, 60, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mRtHots[1] = AddBtn(-1, 908, 75, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        this.mRtHots[0] = AddBtn(-1, 898, 90, 18, 7, R.drawable.cadg_ac_pro_up, R.drawable.cadg_ac_pro_dn);
        for (int i = 0; i < 3; i++) {
            this.mLtHots[i].Show(false);
            this.mRtHots[i].Show(false);
        }
        this.mWindProg = new MyProgressBar(mContext, R.drawable.cadillac_ac_tiao_up, R.drawable.cadillac_ac_tiao_dn);
        this.mWindProg.SetMinMax(0, 7);
        this.mWindProg.SetCurPos(1);
        this.mManager.AddViewWrapContent(this.mWindProg, 440, 46);
    }

    private void initLowViews() {
        this.mTvLtTemp = AddTemp(2, 21, 92, 71);
        this.mTvRtTemp = AddTemp(924, 21, 92, 71);
        this.mAuto = this.mManager.AddImage(103, 46);
        this.mAuto.setStateDrawable(R.drawable.cadillac_ac_auto_up, R.drawable.cadillac_ac_auto_dn);
        this.mFrontWind = this.mManager.AddImage(183, 41);
        this.mFrontWind.setStateDrawable(R.drawable.cadillac_ac_wind_up, R.drawable.cadillac_ac_wind_dn);
        this.mAirMode[0] = this.mManager.AddImage(271, 16);
        this.mAirMode[0].setStateDrawable(R.drawable.cadillac_ac_show01_up, R.drawable.cadillac_ac_show01_dn);
        this.mAirMode[1] = this.mManager.AddImage(Can.CAN_MG_ZS_WC, 63);
        this.mAirMode[1].setStateDrawable(R.drawable.cadillac_ac_show02_up, R.drawable.cadillac_ac_show02_dn);
        this.mAirMode[2] = this.mManager.AddImage(Can.CAN_MG_ZS_WC, 57);
        this.mAirMode[2].setStateDrawable(R.drawable.cadillac_ac_show03_up, R.drawable.cadillac_ac_show03_dn);
        this.mAirMode[3] = this.mManager.AddImage(Can.CAN_MG_ZS_WC, 43);
        this.mAirMode[3].setStateDrawable(R.drawable.cadillac_ac_show04_up, R.drawable.cadillac_ac_show04_dn);
        this.mPeople = this.mManager.AddImage(273, 46, R.drawable.cadillac_ac_show05_up);
        this.mLoopMode = this.mManager.AddImage(KeyDef.RKEY_POWER_OFF, 44);
        this.mLoopMode.setStateDrawable(R.drawable.cadillac_ac_nxh_dn, R.drawable.cadillac_ac_wxh_dn);
        this.mViewWind[0] = this.mManager.AddImage(408, 44, R.drawable.cadillac_ac_xuehua_s);
        this.mViewWind[1] = this.mManager.AddImage(CanCameraUI.BTN_CHANA_CS75_MODE6, 39, R.drawable.cadillac_ac_xuehua_b);
        this.mSync = this.mManager.AddImage(CanCameraUI.BTN_LANDWIND_2D_REAR, 46);
        this.mSync.setStateDrawable(R.drawable.cadillac_ac_sync_up, R.drawable.cadillac_ac_sync_dn);
        this.mFrontMax = this.mManager.AddImage(737, 36);
        this.mFrontMax.setStateDrawable(R.drawable.cadillac_ac_max_up, R.drawable.cadillac_ac_max_dn);
        this.mRearHot = this.mManager.AddImage(811, 36);
        this.mRearHot.setStateDrawable(R.drawable.cadillac_ac_heat_up, R.drawable.cadillac_ac_heat_dn);
        this.mAC = this.mManager.AddImage(885, 44);
        this.mAC.setStateDrawable(R.drawable.cadillac_ac_ac_up, R.drawable.cadillac_ac_ac_dn);
        this.mWindProg = new MyProgressBar(mContext, R.drawable.cadillac_ac_tiao_up, R.drawable.cadillac_ac_tiao_dn);
        this.mWindProg.SetMinMax(0, 7);
        this.mWindProg.SetCurPos(1);
        this.mManager.AddViewWrapContent(this.mWindProg, 440, 46);
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
        temp.SetPxSize(40);
        temp.setText("88.8℃");
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddText(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(28);
        temp.setText(TXZResourceManager.STYLE_DEFAULT);
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
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        this.mACInfo = Can.mACInfo;
        this.mTvLtTemp.setText(this.mACInfo.szLtTemp);
        this.mTvRtTemp.setText(this.mACInfo.szRtTemp);
        this.mAuto.SetSel(this.mACInfo.nAutoLight);
        this.mFrontWind.SetSel(this.mACInfo.fgDFBL);
        this.mAirMode[0].SetSel(this.mACInfo.fgForeWindMode);
        this.mAirMode[1].SetSel(this.mACInfo.fgDownWind);
        this.mAirMode[2].SetSel(this.mACInfo.fgParallelWind);
        this.mAirMode[3].SetSel(this.mACInfo.fgUpWind);
        if (this.mACInfo.fgAQS == 1) {
            this.mLoopMode.setStateDrawable(R.drawable.cadillac_ac_nxh_a_dn, R.drawable.cadillac_ac_nxh_a_dn);
        } else if (this.mACInfo.fgInnerLoop != 0) {
            this.mLoopMode.setStateDrawable(R.drawable.cadillac_ac_nxh_dn, R.drawable.cadillac_ac_nxh_dn);
        } else {
            this.mLoopMode.setStateDrawable(R.drawable.cadillac_ac_wxh_dn, R.drawable.cadillac_ac_wxh_dn);
        }
        this.mWindProg.SetCurPos(this.mACInfo.nWindValue / 2);
        this.mSync.SetSel(this.mACInfo.fgDual);
        this.mFrontMax.SetSel(this.mACInfo.fgACMax);
        this.mRearHot.SetSel(this.mACInfo.fgRearLight);
        this.mAC.SetSel(this.mACInfo.fgAC);
        if (this.mBtnLtWind != null) {
            int ltWind = this.mACInfo.nLtChairWind;
            this.mBtnLtWind.SetSel(ltWind);
            if (this.mACInfo.nLtChairWind > 0 && this.mACInfo.nLtChairHot > 0) {
                ltWind = 0;
            }
            for (int i = 0; i < this.mLtHots.length; i++) {
                ParamButton paramButton = this.mLtHots[i];
                if (i < ltWind) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                paramButton.setSelected(z4);
            }
        }
        if (this.mBtnRtWind != null) {
            int rtWind = this.mACInfo.nRtChairWind;
            this.mBtnRtWind.SetSel(rtWind);
            if (this.mACInfo.nRtChairWind > 0 && this.mACInfo.nRtChairHot > 0) {
                rtWind = 0;
            }
            for (int i2 = 0; i2 < this.mRtHots.length; i2++) {
                ParamButton paramButton2 = this.mRtHots[i2];
                if (i2 < rtWind) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                paramButton2.setSelected(z3);
            }
        }
        if (this.mBtnLtHot != null) {
            int ltChairHot = this.mACInfo.nLtChairHot;
            this.mBtnLtHot.SetSel(ltChairHot);
            for (int i3 = 0; i3 < this.mLtHots.length; i3++) {
                ParamButton paramButton3 = this.mLtHots[i3];
                if (i3 < ltChairHot) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                paramButton3.setSelected(z2);
            }
        }
        if (this.mBtnRtHot != null) {
            int rtChairHot = this.mACInfo.nRtChairHot;
            this.mBtnRtHot.SetSel(rtChairHot);
            for (int i4 = 0; i4 < this.mRtHots.length; i4++) {
                ParamButton paramButton4 = this.mRtHots[i4];
                if (i4 < rtChairHot) {
                    z = true;
                } else {
                    z = false;
                }
                paramButton4.setSelected(z);
            }
        }
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
        if ((CanJni.GetSubType() == 0 || CanJni.GetSubType() == 1 || CanJni.GetSubType() == 2) && mIsAC) {
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
