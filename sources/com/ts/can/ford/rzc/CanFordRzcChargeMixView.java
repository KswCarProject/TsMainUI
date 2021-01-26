package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanFordRzcChargeMixView extends CanRelativeCarInfoView {
    private CustomTextView mBatV;
    private CustomImgView mCarBg;
    private CanDataInfo.FordRzcChargeInfo mChargeData;
    private CustomTextView[] mChargeTime;
    private CustomImgView[] mDlliu;
    protected RelativeLayoutManager mManager;
    private CanDataInfo.FordRzcMixInfo mMixData;
    private CustomTextView[] mMixPara;
    private CustomTextView mSta;
    private CustomTextView[] mStartSrc;
    private String[] mStrMixArr;

    public CanFordRzcChargeMixView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_comm_bg);
        this.mManager = getRelativeManager();
        this.mCarBg = this.mManager.AddImage(45, 134, R.drawable.can_mondeo_mix_bg);
        this.mChargeData = new CanDataInfo.FordRzcChargeInfo();
        this.mMixData = new CanDataInfo.FordRzcMixInfo();
        this.mStartSrc = new CustomTextView[2];
        this.mChargeTime = new CustomTextView[3];
        this.mSta = this.mManager.AddCusText(50, 10, 500, 60);
        SetText(this.mSta, 50);
        this.mSta.setText(" ");
        this.mStartSrc[0] = this.mManager.AddCusText(30, 130, 300, 60);
        SetText(this.mStartSrc[0], 30);
        this.mStartSrc[0].setText(String.format("%s", new Object[]{String.valueOf(getString(R.string.can_ford_yqqdyy)) + ':'}));
        this.mStartSrc[1] = this.mManager.AddCusText(30, 160, 300, 60);
        SetText(this.mStartSrc[1], 30);
        this.mStartSrc[1].setText(" ");
        this.mBatV = this.mManager.AddCusText(720, 280, KeyDef.RKEY_RDS_TA, 60);
        SetText(this.mBatV, 38);
        this.mBatV.setText(" ");
        for (int i = 0; i < 3; i++) {
            this.mChargeTime[i] = this.mManager.AddCusText(CanCameraUI.BTN_CC_WC_DIRECTION1, (i * 30) + 130, 300, 60);
            SetText(this.mChargeTime[i], 30);
        }
        this.mChargeTime[0].setText(String.format("%s", new Object[]{String.valueOf(getString(R.string.can_yjcdwcsj)) + ':'}));
        this.mChargeTime[1].setText(" ");
        this.mChargeTime[2].setText(" ");
        this.mDlliu = new CustomImgView[9];
        this.mDlliu[0] = this.mManager.AddImage(262, 275, R.drawable.can_mondeo_mix_above02_dn);
        this.mDlliu[1] = this.mManager.AddImage(174, 352, R.drawable.can_mondeo_mix_under03_dn);
        this.mDlliu[2] = this.mManager.AddImage(179, KeyDef.RKEY_RADIO_1S, R.drawable.can_mondeo_mix_above01_dn);
        this.mDlliu[3] = this.mManager.AddImage(259, KeyDef.RKEY_res4, R.drawable.can_mondeo_mix_above05_nt);
        this.mDlliu[4] = this.mManager.AddImage(393, 276, R.drawable.can_mondeo_mix_above03_dn);
        this.mDlliu[5] = this.mManager.AddImage(489, Can.CAN_TOYOTA_SP_XP, R.drawable.can_mondeo_mix_above04_dn);
        this.mDlliu[6] = this.mManager.AddImage(KeyDef.RKEY_RADIO_6S, 267, R.drawable.can_mondeo_mix_above06_dn);
        this.mDlliu[7] = this.mManager.AddImage(278, 352, R.drawable.can_mondeo_mix_under02_dn);
        this.mDlliu[8] = this.mManager.AddImage(259, KeyDef.RKEY_res4, R.drawable.can_mondeo_mix_above05_dn);
        for (int i2 = 0; i2 < 9; i2++) {
            this.mDlliu[i2].Show(false);
        }
        this.mMixPara = new CustomTextView[7];
        this.mMixPara[0] = this.mManager.AddCusText(348, Can.CAN_CHRYSLER_ONE_HC, 133, 50);
        this.mMixPara[1] = this.mManager.AddCusText(508, 214, 133, 50);
        this.mMixPara[2] = this.mManager.AddCusText(656, 352, 133, 50);
        this.mMixPara[3] = this.mManager.AddCusText(280, 417, 133, 50);
        this.mMixPara[4] = this.mManager.AddCusText(120, 399, 133, 50);
        this.mMixPara[5] = this.mManager.AddCusText(180, KeyDef.RKEY_EJECT, 133, 50);
        this.mMixPara[6] = this.mManager.AddCusText(300, 290, 133, 50);
        this.mStrMixArr = getStringArray(R.array.can_ford_mondeo_mix_para);
        for (int i3 = 0; i3 < 7; i3++) {
            this.mMixPara[i3].setText(this.mStrMixArr[i3]);
            SetText(this.mMixPara[i3], 33);
            this.mMixPara[i3].setTextColor(-16711681);
        }
    }

    public void ResetData(boolean check) {
        CanJni.FordRzcGetChargeInfo(this.mChargeData);
        if (i2b(this.mChargeData.UpdateOnce) && (!check || i2b(this.mChargeData.Update))) {
            this.mChargeData.Update = 0;
            if (this.mChargeData.Sta <= 10) {
                this.mSta.setText(String.format("%s", new Object[]{String.valueOf(getString(R.string.can_status)) + ":" + getStringArray(R.array.can_ford_mondeo_mix)[this.mChargeData.Sta]}));
            }
            String Str = " ";
            if (this.mChargeData.Start1 <= 12) {
                Str = String.format("%s", new Object[]{getStringArray(R.array.can_ford_mondeo_start_tip)[this.mChargeData.Start1]});
            }
            if (this.mChargeData.Start2 <= 12) {
                Str = String.format("%s  %s", new Object[]{Str, getStringArray(R.array.can_ford_mondeo_start_tip)[this.mChargeData.Start2]});
            }
            this.mStartSrc[1].setText(Str);
            this.mBatV.setText(String.format("%s: %d %%", new Object[]{getString(R.string.can_dqdcdl), Integer.valueOf(this.mChargeData.BatV)}));
            this.mChargeTime[1].setText(String.format("%.1f h(hi)", new Object[]{Double.valueOf(((double) this.mChargeData.TimeH) * 0.1d)}));
            this.mChargeTime[2].setText(String.format("%.1f h(lo)", new Object[]{Double.valueOf(((double) this.mChargeData.TimeL) * 0.1d)}));
        }
        CanJni.FordRzcGetMixInfo(this.mMixData);
        if (!i2b(this.mMixData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mMixData.Update)) {
            this.mMixData.Update = 0;
            if (this.mMixData.Dcldj > 0) {
                this.mDlliu[0].Show(true);
            } else {
                this.mDlliu[0].Show(false);
            }
            if (this.mMixData.QdlyqW > 0) {
                this.mDlliu[1].Show(true);
            } else {
                this.mDlliu[1].Show(false);
            }
            if (this.mMixData.Qdldj > 0) {
                this.mDlliu[2].Show(true);
            } else {
                this.mDlliu[2].Show(false);
            }
            if (this.mMixData.Djlyq > 0) {
                this.mDlliu[3].Show(true);
            } else {
                this.mDlliu[3].Show(false);
            }
            if (this.mMixData.Dclqt > 0) {
                this.mDlliu[4].Show(true);
            } else {
                this.mDlliu[4].Show(false);
            }
            if (this.mMixData.Dclkt > 0) {
                this.mDlliu[5].Show(true);
            } else {
                this.mDlliu[5].Show(false);
            }
            if (this.mMixData.Dclcd > 0) {
                this.mDlliu[6].Show(true);
            } else {
                this.mDlliu[6].Show(false);
            }
            if (this.mMixData.RylyqW > 0) {
                this.mDlliu[7].Show(true);
            } else {
                this.mDlliu[7].Show(false);
            }
            if (this.mMixData.DjlyqW > 0) {
                this.mDlliu[8].Show(true);
            } else {
                this.mDlliu[8].Show(false);
            }
        }
    }

    public void QueryData() {
        CanJni.FordQuery(50, 0);
        Sleep(10);
        CanJni.FordQuery(49, 0);
    }

    private void SetText(CustomTextView tv, int size) {
        tv.SetPxSize(size);
        tv.setTextColor(-1);
        tv.setGravity(3);
    }
}
