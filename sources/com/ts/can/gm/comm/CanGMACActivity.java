package com.ts.can.gm.comm;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.MyProgressBar;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanGMACActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange, View.OnTouchListener {
    public static final int ITEM_AC = 3;
    public static final int ITEM_AUTO = 4;
    public static final int ITEM_LOOP = 5;
    public static final int ITEM_MODE_DN = 10;
    public static final int ITEM_MODE_FORE = 12;
    public static final int ITEM_MODE_PX = 8;
    public static final int ITEM_MODE_PX_DN = 9;
    public static final int ITEM_MODE_UP_DN = 11;
    public static final int ITEM_TEMP_DEC = 2;
    public static final int ITEM_TEMP_INC = 1;
    public static final int ITEM_WIND_DEC = 6;
    public static final int ITEM_WIND_INC = 7;
    public static final String TAG = "CanGMACActivity";
    protected CanDataInfo.CAN_ACInfo mACInfo;
    protected boolean mAutoFinish = false;
    private ParamButton mBtnAC;
    private ParamButton mBtnAuto;
    private ParamButton mBtnLoop;
    private ParamButton mBtnModeDn;
    private ParamButton mBtnModeFore;
    private ParamButton mBtnModePx;
    private ParamButton mBtnModePxDn;
    private ParamButton mBtnModeUpDn;
    private ParamButton mBtnTempDec;
    private ParamButton mBtnTempInc;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private CustomImgView mIvModeAuto;
    private CustomImgView mIvWindAuto;
    protected RelativeLayoutManager mManager;
    private CustomTextView mTvTemp;
    private CustomTextView mTvWindAuto;
    private MyProgressBar mWindProg;
    protected boolean mfgJump;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_gm_ac);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mfgJump = CanFunc.IsCanActivityJumped(this);
        this.mWindProg = new MyProgressBar(this, R.drawable.can_yl_rect_up, R.drawable.can_yl_rect_dn);
        this.mWindProg.SetMinMax(0, 6);
        this.mWindProg.SetCurPos(1);
        this.mManager.AddViewWrapContent(this.mWindProg, KeyDef.RKEY_res2, 272);
        this.mTvTemp = this.mManager.AddCusText(40, 23, 131, 50);
        this.mTvTemp.SetPxSize(55);
        this.mTvTemp.setText("35.0℃");
        this.mTvTemp.setGravity(17);
        this.mBtnTempInc = AddBtn(68, 107, 1);
        this.mBtnTempInc.setDrawable(R.drawable.can_yl_upward_up, R.drawable.can_yl_upward_dn);
        this.mBtnTempDec = AddBtn(68, 287, 2);
        this.mBtnTempDec.setDrawable(R.drawable.can_yl_down_up, R.drawable.can_yl_down_dn);
        this.mBtnAC = AddBtn(Can.CAN_VOLVO_XFY, 41, 3);
        this.mBtnAC.setDrawable(R.drawable.can_yl_snow01_up, R.drawable.can_yl_snow01_dn);
        this.mBtnAuto = AddBtn(499, 41, 4);
        this.mBtnAuto.setDrawable(R.drawable.can_yl_auto_up, R.drawable.can_yl_auto_dn);
        this.mBtnLoop = AddBtn(765, 41, 5);
        this.mBtnLoop.setDrawable(R.drawable.can_yl_car01_up, R.drawable.can_yl_car01_dn);
        this.mBtnWindDec = AddBtn(284, Can.CAN_MG_ZS_WC, 6);
        this.mBtnWindDec.setDrawable(R.drawable.can_yl_jian_up, R.drawable.can_yl_jian_dn);
        this.mBtnWindInc = AddBtn(KeyDef.SKEY_CALLDN_5, Can.CAN_MG_ZS_WC, 7);
        this.mBtnWindInc.setDrawable(R.drawable.can_yl_jia_up, R.drawable.can_yl_jia_dn);
        this.mBtnModePx = AddBtn(114, 442, 8);
        this.mBtnModePx.setDrawable(R.drawable.can_yl_show01_up, R.drawable.can_yl_show01_dn);
        this.mBtnModePxDn = AddBtn(285, 442, 9);
        this.mBtnModePxDn.setDrawable(R.drawable.can_yl_show02_up, R.drawable.can_yl_show02_dn);
        this.mBtnModeDn = AddBtn(456, 442, 10);
        this.mBtnModeDn.setDrawable(R.drawable.can_yl_show03_up, R.drawable.can_yl_show03_dn);
        this.mBtnModeUpDn = AddBtn(626, 442, 11);
        this.mBtnModeUpDn.setDrawable(R.drawable.can_yl_show04_up, R.drawable.can_yl_show04_dn);
        this.mBtnModeFore = AddBtn(KeyDef.SKEY_CHUP_3, 442, 12);
        this.mBtnModeFore.setDrawable(R.drawable.can_yl_wind_up, R.drawable.can_yl_wind_dn);
        this.mIvModeAuto = this.mManager.AddImage(473, 414, R.drawable.can_yl_show_auto);
        this.mIvWindAuto = this.mManager.AddImage(555, KeyDef.RKEY_ANGLEDN, R.drawable.can_yl_wind_auto);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d(TAG, "-----onResume-----");
        CanFunc.mfgShowAC = false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause----- mAutoFinish = " + this.mAutoFinish);
        if (!CanFunc.mfgShowAC) {
            if (!this.mAutoFinish) {
                finish();
                Log.d(TAG, "-----onPause finish-----");
            }
            this.mfgJump = false;
            this.mAutoFinish = false;
        }
    }

    private void updateACUI() {
        boolean fgModeAutoDisable;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        this.mACInfo = Can.mACInfo;
        this.mTvTemp.setText(this.mACInfo.szLtTemp);
        Log.d(TAG, "fgAutoAC =" + this.mACInfo.fgAutoAC);
        Log.d(TAG, "nWindAutoLevel = " + this.mACInfo.nWindAutoLevel);
        Log.d(TAG, "fgAutoWind = " + this.mACInfo.fgAutoWind);
        if (this.mACInfo.fgAutoAC != 0) {
            this.mBtnAC.setDrawable(R.drawable.can_yl_snow03_up, R.drawable.can_yl_snow03_dn);
        } else if (this.mACInfo.fgAC != 0) {
            this.mBtnAC.setDrawable(R.drawable.can_yl_snow02_up, R.drawable.can_yl_snow02_dn);
        } else {
            this.mBtnAC.setDrawable(R.drawable.can_yl_snow01_up, R.drawable.can_yl_snow01_dn);
        }
        if (this.mACInfo.nAutoLight == 0 || this.mACInfo.fgAutoAC == 0 || this.mACInfo.fgAutoWind == 0 || this.mACInfo.fgAQS == 0) {
            this.mBtnAuto.setSelected(false);
        } else {
            this.mBtnAuto.setSelected(true);
        }
        if (this.mACInfo.fgAQS != 0) {
            if (this.mACInfo.fgInnerLoop != 0) {
                this.mBtnLoop.setDrawable(R.drawable.can_yl_car02_auto_up, R.drawable.can_yl_car02_auto_dn);
            } else {
                this.mBtnLoop.setDrawable(R.drawable.can_yl_car01_auto_up, R.drawable.can_yl_car01_auto_dn);
            }
        } else if (this.mACInfo.fgInnerLoop != 0) {
            this.mBtnLoop.setDrawable(R.drawable.can_yl_car02_up, R.drawable.can_yl_car02_dn);
        } else {
            this.mBtnLoop.setDrawable(R.drawable.can_yl_car01_up, R.drawable.can_yl_car01_dn);
        }
        if (this.mACInfo.nAutoLight == 0) {
            fgModeAutoDisable = true;
        } else {
            fgModeAutoDisable = false;
        }
        int mode = this.mACInfo.fgUpWind + (this.mACInfo.fgParallelWind * 2) + (this.mACInfo.fgDownWind * 4);
        ParamButton paramButton = this.mBtnModePx;
        if (mode != 2 || !fgModeAutoDisable) {
            z = false;
        } else {
            z = true;
        }
        paramButton.setSelected(z);
        ParamButton paramButton2 = this.mBtnModePxDn;
        if (mode != 6 || !fgModeAutoDisable) {
            z2 = false;
        } else {
            z2 = true;
        }
        paramButton2.setSelected(z2);
        ParamButton paramButton3 = this.mBtnModeDn;
        if (mode != 4 || !fgModeAutoDisable) {
            z3 = false;
        } else {
            z3 = true;
        }
        paramButton3.setSelected(z3);
        ParamButton paramButton4 = this.mBtnModeUpDn;
        if (mode != 5 || !fgModeAutoDisable) {
            z4 = false;
        } else {
            z4 = true;
        }
        paramButton4.setSelected(z4);
        ParamButton paramButton5 = this.mBtnModeFore;
        if (mode != 1 || !fgModeAutoDisable) {
            z5 = false;
        }
        paramButton5.setSelected(z5);
        this.mIvWindAuto.Show(this.mACInfo.fgAutoWind);
        if (this.mACInfo.fgAutoWind != 0) {
            this.mWindProg.SetCurPos(0);
        } else {
            this.mWindProg.SetCurPos(this.mACInfo.nWindValue);
        }
        this.mIvModeAuto.Show(this.mACInfo.nAutoLight);
    }

    private ParamButton AddBtn(int x, int y, int id) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setOnTouchListener(this);
        btn.setTag(Integer.valueOf(id));
        return btn;
    }

    private void ResetData(boolean check) {
        Can.updateAC();
        if (!check || Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (this.mfgJump && GetTickCount() > CanFunc.mLastACTick + 3000) {
            finish();
            this.mAutoFinish = true;
            Log.d(TAG, "UserAll Exit Ac");
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    private void ACSet(int cmd) {
        CanJni.GMACCtrl(7, cmd);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            Log.d(TAG, "****ACTION_DOWN*****");
            switch (((Integer) v.getTag()).intValue()) {
                case 1:
                    ACSet(4);
                    break;
                case 2:
                    ACSet(5);
                    break;
                case 3:
                    ACSet(1);
                    break;
                case 4:
                    ACSet(2);
                    break;
                case 5:
                    ACSet(3);
                    break;
                case 6:
                    ACSet(7);
                    break;
                case 7:
                    ACSet(6);
                    break;
                case 8:
                    ACSet(8);
                    break;
                case 9:
                    ACSet(9);
                    break;
                case 10:
                    ACSet(11);
                    break;
                case 11:
                    ACSet(10);
                    break;
                case 12:
                    ACSet(12);
                    break;
            }
        } else if (1 == action) {
            Log.d(TAG, "****ACTION_UP*****");
            ACSet(0);
        }
        return false;
    }
}
