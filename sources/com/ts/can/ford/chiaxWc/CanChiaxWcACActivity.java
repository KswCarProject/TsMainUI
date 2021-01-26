package com.ts.can.ford.chiaxWc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarACActivity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.MyProgressBar;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanChiaxWcACActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_AC = 10;
    public static final int ITEM_AUTO = 15;
    public static final int ITEM_DUAL = 16;
    public static final int ITEM_FRONTWIND = 11;
    public static final int ITEM_LOOP = 18;
    public static final int ITEM_LTEMP_DEC = 2;
    public static final int ITEM_LTEMP_INC = 1;
    public static final int ITEM_MODE_DN = 14;
    public static final int ITEM_MODE_PX = 13;
    public static final int ITEM_MODE_UP = 12;
    public static final int ITEM_POWER = 17;
    public static final int ITEM_RTEMP_DEC = 4;
    public static final int ITEM_RTEMP_INC = 3;
    public static final int ITEM_WIND_DEC = 8;
    public static final int ITEM_WIND_INC = 9;
    public static final int ITEM_WLEVEL_HI = 7;
    public static final int ITEM_WLEVEL_LO = 5;
    public static final int ITEM_WLEVEL_MID = 6;
    public static final String TAG = "CanChiaxWcACActivity";
    private CanDataInfo.CAN_ACInfo mACInfo;
    protected boolean mAutoFinish = false;
    private ParamButton mBtnAC;
    private ParamButton mBtnAuto;
    private ParamButton mBtnDual;
    private ParamButton mBtnFrontWind;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnModeDn;
    private ParamButton mBtnModePx;
    private ParamButton mBtnModeUp;
    private ParamButton mBtnPwr;
    private ParamButton mBtnRtTempDec;
    private ParamButton mBtnRtTempInc;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private ParamButton[] mBtnWindLevel;
    private CustomImgView mIvForeWind;
    private CustomImgView mIvLoop;
    private CustomImgView mIvRearWind;
    private CustomImgView mIvWindAuto;
    protected RelativeLayoutManager mManager;
    private CustomTextView mTvLtTemp;
    private CustomTextView mTvRtTemp;
    private CustomTextView[] mTvWindLevel;
    private MyProgressBar mWindProg;
    protected boolean mfgJump;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mfgJump = CanFunc.IsCanActivityJumped(this);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.AddImage(0, 0, R.drawable.can_psa_408_bg);
        this.mACInfo = Can.mACInfo;
        this.mTvLtTemp = AddTemp(18, 18, 131, 62);
        this.mBtnLtTempInc = AddBtn(1, 47, 107, R.drawable.can_yl_upward_up, R.drawable.can_yl_upward_dn);
        this.mBtnLtTempDec = AddBtn(2, 47, 287, R.drawable.can_yl_down_up, R.drawable.can_yl_down_dn);
        this.mTvRtTemp = AddTemp(878, 18, 131, 62);
        this.mBtnRtTempInc = AddBtn(3, 907, 107, R.drawable.can_yl_upward_up, R.drawable.can_yl_upward_dn);
        this.mBtnRtTempDec = AddBtn(4, 907, 287, R.drawable.can_yl_down_up, R.drawable.can_yl_down_dn);
        this.mWindProg = new MyProgressBar(this, R.drawable.can_yl_rect_up, R.drawable.can_yl_rect_dn);
        this.mWindProg.SetMinMax(0, 7);
        this.mWindProg.SetCurPos(1);
        this.mManager.AddViewWrapContent(this.mWindProg, 261, 292);
        this.mBtnWindDec = AddBtn(8, 196, 271, R.drawable.can_yl_jian_up, R.drawable.can_yl_jian_dn);
        this.mBtnWindInc = AddBtn(9, 755, 271, R.drawable.can_yl_jia_up, R.drawable.can_yl_jia_dn);
        this.mBtnAC = AddBtn(10, 38, 418, R.drawable.can_psa_408_ac_up, R.drawable.can_psa_408_ac_dn);
        this.mBtnFrontWind = AddBtn(11, 181, 418, R.drawable.can_mg_wind_up, R.drawable.can_mg_wind_dn);
        this.mBtnModeUp = AddBtn(12, KeyDef.RKEY_RADIO_1S, 418, R.drawable.can_psa_408_show03_up, R.drawable.can_psa_408_show03_dn);
        this.mBtnModePx = AddBtn(13, 466, 418, R.drawable.can_psa_408_show02_up, R.drawable.can_psa_408_show02_dn);
        this.mBtnModeDn = AddBtn(14, 609, 418, R.drawable.can_psa_408_show01_up, R.drawable.can_psa_408_show01_dn);
        this.mBtnAuto = AddBtn(15, 751, 418, R.drawable.can_psa_408_auto_up, R.drawable.can_psa_408_auto_dn);
        this.mBtnDual = AddBtn(16, 894, 418, R.drawable.can_psa_408_dual_up, R.drawable.can_psa_408_dual_dn);
        this.mBtnPwr = AddBtn(17, 275, 79, R.drawable.can_mg_del_up, R.drawable.can_mg_del_dn2);
        this.mBtnLoop = AddBtn(18, CanCameraUI.BTN_LANDWIND_3D_RIGHT_DOWN, 79, R.drawable.can_mg_car01_up, R.drawable.can_mg_car01_dn);
        Log.d(TAG, "jump = " + this.mfgJump);
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        Log.d(TAG, "-----onPostResume-----");
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTemp(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPxSize(55);
        temp.setText("88.8℃");
        temp.setGravity(17);
        return temp;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.d(TAG, "-----onResume-----");
        Log.d(TAG, "Current Activity = " + getCurrentActivityName());
        if (CanCarACActivity.mfgAcIconClick) {
            CanCarACActivity.mfgAcIconClick = false;
            this.mfgJump = false;
        }
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
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
        this.mACInfo = Can.mACInfo;
        this.mTvLtTemp.setText(this.mACInfo.szLtTemp);
        this.mTvRtTemp.setText(this.mACInfo.szRtTemp);
        if (this.mACInfo.nWindValue > 0) {
            this.mWindProg.SetCurPos(this.mACInfo.nWindValue);
        } else {
            this.mWindProg.SetCurPos(0);
        }
        this.mBtnAC.SetSel(this.mACInfo.fgAC);
        this.mBtnFrontWind.SetSel(this.mACInfo.fgDFBL);
        this.mBtnModeUp.SetSel(this.mACInfo.fgForeWindMode);
        this.mBtnModePx.SetSel(this.mACInfo.fgParallelWind);
        this.mBtnModeDn.SetSel(this.mACInfo.fgDownWind);
        this.mBtnAuto.SetSel(this.mACInfo.nAutoLight);
        this.mBtnDual.SetSel(this.mACInfo.fgDual);
        if (this.mACInfo.fgInnerLoop != 0) {
            this.mBtnLoop.setDrawable(R.drawable.can_mg_car01_up, R.drawable.can_mg_car01_dn);
        } else {
            this.mBtnLoop.setDrawable(R.drawable.can_mg_car02_up, R.drawable.can_mg_car02_dn);
        }
        this.mBtnPwr.SetSel(1 - this.mACInfo.PWR);
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
            case 1:
                CanJni.FordMondeoChiaxWcAcKey(2, 0);
                return;
            case 2:
                CanJni.FordMondeoChiaxWcAcKey(3, 0);
                return;
            case 3:
                CanJni.FordMondeoChiaxWcAcKey(11, 0);
                return;
            case 4:
                CanJni.FordMondeoChiaxWcAcKey(12, 0);
                return;
            case 8:
                CanJni.FordMondeoChiaxWcAcKey(5, 0);
                return;
            case 9:
                CanJni.FordMondeoChiaxWcAcKey(4, 0);
                return;
            case 10:
                CanJni.FordMondeoChiaxWcAcKey(14, 0);
                return;
            case 11:
                CanJni.FordMondeoChiaxWcAcKey(1, 0);
                return;
            case 12:
                CanJni.FordMondeoChiaxWcAcKey(10, 0);
                return;
            case 13:
                CanJni.FordMondeoChiaxWcAcKey(9, 0);
                return;
            case 14:
                CanJni.FordMondeoChiaxWcAcKey(8, 0);
                return;
            case 15:
                CanJni.FordMondeoChiaxWcAcKey(13, 0);
                return;
            case 16:
                CanJni.FordMondeoChiaxWcAcKey(15, 0);
                return;
            case 17:
                CanJni.FordMondeoChiaxWcAcKey(7, 0);
                return;
            case 18:
                CanJni.FordMondeoChiaxWcAcKey(6, 0);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }
}
