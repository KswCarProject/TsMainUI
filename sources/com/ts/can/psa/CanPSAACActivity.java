package com.ts.can.psa;

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

public class CanPSAACActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_AC = 10;
    public static final int ITEM_ACMAX = 11;
    public static final int ITEM_AUTO = 15;
    public static final int ITEM_DUAL = 16;
    public static final int ITEM_LOOP = 17;
    public static final int ITEM_LTEMP_DEC = 2;
    public static final int ITEM_LTEMP_INC = 1;
    public static final int ITEM_MODE_DN = 14;
    public static final int ITEM_MODE_PX = 13;
    public static final int ITEM_MODE_UP = 12;
    public static final int ITEM_RTEMP_DEC = 4;
    public static final int ITEM_RTEMP_INC = 3;
    public static final int ITEM_WIND_DEC = 8;
    public static final int ITEM_WIND_INC = 9;
    public static final int ITEM_WLEVEL_HI = 7;
    public static final int ITEM_WLEVEL_LO = 5;
    public static final int ITEM_WLEVEL_MID = 6;
    public static final String TAG = "CanPSAACActivity";
    private CanDataInfo.CAN_ACInfo mACInfo;
    protected boolean mAutoFinish = false;
    private ParamButton mBtnAC;
    private ParamButton mBtnAcMax;
    private ParamButton mBtnAuto;
    private ParamButton mBtnDual;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnModeDn;
    private ParamButton mBtnModePx;
    private ParamButton mBtnModeUp;
    private ParamButton mBtnRtTempDec;
    private ParamButton mBtnRtTempInc;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private ParamButton[] mBtnWindLevel;
    private CustomImgView mIvForeWind;
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
        this.mIvForeWind = this.mManager.AddImage(KeyDef.RKEY_ST, 29);
        this.mIvForeWind.setStateDrawable(R.drawable.can_psa_408_wind_up, R.drawable.can_psa_408_wind_dn);
        this.mIvRearWind = this.mManager.AddImage(483, 29);
        this.mIvRearWind.setStateDrawable(R.drawable.can_psa_408_heat_up, R.drawable.can_psa_408_heat_dn);
        this.mBtnWindLevel = new ParamButton[3];
        this.mBtnWindLevel[0] = AddBtn(5, 275, 79, R.drawable.can_psa_408_fs01_up, R.drawable.can_psa_408_fs01_dn);
        this.mBtnWindLevel[1] = AddBtn(6, 461, 79, R.drawable.can_psa_408_fs02_up, R.drawable.can_psa_408_fs02_dn);
        this.mBtnWindLevel[2] = AddBtn(7, CanCameraUI.BTN_LANDWIND_3D_RIGHT_DOWN, 79, R.drawable.can_psa_408_fs03_up, R.drawable.can_psa_408_fs03_dn);
        this.mTvWindLevel = new CustomTextView[3];
        for (int i = 0; i < this.mTvWindLevel.length; i++) {
            this.mTvWindLevel[i] = AddTemp((i * 186) + 275, 188, 105, 40);
            this.mTvWindLevel[i].SetPxSize(40);
        }
        this.mTvWindLevel[0].setText(R.string.can_ac_low);
        this.mTvWindLevel[1].setText(R.string.can_ac_mid);
        this.mTvWindLevel[2].setText(R.string.can_ac_high);
        this.mWindProg = new MyProgressBar(this, R.drawable.can_yl_rect_up, R.drawable.can_yl_rect_dn);
        this.mWindProg.SetMinMax(0, 8);
        this.mWindProg.SetCurPos(1);
        this.mManager.AddViewWrapContent(this.mWindProg, 261, KeyDef.RKEY_PRE);
        this.mBtnWindDec = AddBtn(8, 196, 271, R.drawable.can_yl_jian_up, R.drawable.can_yl_jian_dn);
        this.mBtnWindInc = AddBtn(9, 755, 271, R.drawable.can_yl_jia_up, R.drawable.can_yl_jia_dn);
        this.mIvWindAuto = this.mManager.AddImage(474, KeyDef.RKEY_RADIO_SCAN, R.drawable.can_yl_wind_auto);
        this.mBtnAC = AddBtn(10, 38, 418, R.drawable.can_psa_408_ac_up, R.drawable.can_psa_408_ac_dn);
        this.mBtnAcMax = AddBtn(11, 181, 418, R.drawable.can_psa_408_acmax_up, R.drawable.can_psa_408_acmax_dn);
        this.mBtnModeUp = AddBtn(12, KeyDef.RKEY_RADIO_1S, 418, R.drawable.can_psa_408_show03_up, R.drawable.can_psa_408_show03_dn);
        this.mBtnModePx = AddBtn(13, 466, 418, R.drawable.can_psa_408_show02_up, R.drawable.can_psa_408_show02_dn);
        this.mBtnModeDn = AddBtn(14, 609, 418, R.drawable.can_psa_408_show01_up, R.drawable.can_psa_408_show01_dn);
        this.mBtnAuto = AddBtn(15, 751, 418, R.drawable.can_psa_408_auto_up, R.drawable.can_psa_408_auto_dn);
        this.mBtnDual = AddBtn(16, 894, 418, R.drawable.can_psa_408_dual_up, R.drawable.can_psa_408_dual_dn);
        this.mBtnLoop = AddBtn(17, 665, 29, 62, 29, R.drawable.can_psa_408_nxh_up, R.drawable.can_psa_408_nxh_dn);
        Log.d("CanPSAACActivity", "jump = " + this.mfgJump);
        if (!this.mfgJump) {
            CanJni.PSAQuery(33, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        Log.d("CanPSAACActivity", "-----onPostResume-----");
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
    public ParamButton AddBtn(int id, int x, int y, int w, int h, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
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
        Log.d("CanPSAACActivity", "-----onResume-----");
        Log.d("CanPSAACActivity", "Current Activity = " + getCurrentActivityName());
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
        Log.d("CanPSAACActivity", "-----onPause----- mAutoFinish = " + this.mAutoFinish);
        if (!CanFunc.mfgShowAC) {
            if (!this.mAutoFinish) {
                finish();
                Log.d("CanPSAACActivity", "-----onPause finish-----");
            }
            this.mfgJump = false;
            this.mAutoFinish = false;
        }
    }

    private void updateACUI() {
        boolean z;
        boolean z2;
        this.mACInfo = Can.mACInfo;
        this.mTvLtTemp.setText(this.mACInfo.szLtTemp);
        this.mTvRtTemp.setText(this.mACInfo.szRtTemp);
        this.mIvForeWind.SetSel(this.mACInfo.fgDFBL);
        this.mIvRearWind.SetSel(this.mACInfo.fgRearLight);
        if (this.mACInfo.fgInnerLoop != 0) {
            this.mBtnLoop.setDrawable(R.drawable.can_psa_408_nxh_up, R.drawable.can_psa_408_nxh_dn);
        } else {
            this.mBtnLoop.setDrawable(R.drawable.can_psa_408_wxh_up, R.drawable.can_psa_408_wxh_dn);
        }
        CustomImgView customImgView = this.mIvWindAuto;
        if (this.mACInfo.nWindValue == 0) {
            z = true;
        } else {
            z = false;
        }
        customImgView.Show(z);
        if (this.mACInfo.nWindValue > 0) {
            this.mWindProg.SetCurPos(this.mACInfo.nWindValue - 1);
        } else {
            this.mWindProg.SetCurPos(0);
        }
        for (int i = 0; i < 3; i++) {
            ParamButton paramButton = this.mBtnWindLevel[i];
            if (this.mACInfo.nWindAutoLevel == i) {
                z2 = true;
            } else {
                z2 = false;
            }
            paramButton.setSelected(z2);
        }
        this.mBtnAC.SetSel(this.mACInfo.fgAC);
        this.mBtnAcMax.SetSel(this.mACInfo.fgACMax);
        this.mBtnModeUp.SetSel(this.mACInfo.fgUpWind);
        this.mBtnModePx.SetSel(this.mACInfo.fgParallelWind);
        this.mBtnModeDn.SetSel(this.mACInfo.fgDownWind);
        this.mBtnAuto.SetSel(this.mACInfo.nAutoLight);
        this.mBtnDual.SetSel(this.mACInfo.fgDual);
    }

    private void ResetData(boolean check) {
        Can.updateAC();
        if (!check || Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (this.mfgJump && GetTickCount() > CanFunc.mLastACTick + 3000) {
            finish();
            this.mAutoFinish = true;
            Log.d("CanPSAACActivity", "UserAll Exit Ac");
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.PSAACSet(4, 1);
                return;
            case 2:
                CanJni.PSAACSet(4, 2);
                return;
            case 3:
                CanJni.PSAACSet(5, 1);
                return;
            case 4:
                CanJni.PSAACSet(5, 2);
                return;
            case 5:
                CanJni.PSAACSet(9, 0);
                return;
            case 6:
                CanJni.PSAACSet(9, 1);
                return;
            case 7:
                CanJni.PSAACSet(9, 2);
                return;
            case 8:
                CanJni.PSAACSet(10, 2);
                return;
            case 9:
                CanJni.PSAACSet(10, 1);
                return;
            case 10:
                CanJni.PSAACSet(2, Neg(this.mACInfo.fgAC));
                return;
            case 11:
                CanJni.PSAACSet(3, Neg(this.mACInfo.fgACMax));
                return;
            case 12:
                CanJni.PSAACSet(7, Neg(this.mACInfo.fgUpWind));
                return;
            case 13:
                CanJni.PSAACSet(6, Neg(this.mACInfo.fgParallelWind));
                return;
            case 14:
                CanJni.PSAACSet(8, Neg(this.mACInfo.fgDownWind));
                return;
            case 15:
                CanJni.PSAACSet(1, Neg(this.mACInfo.nAutoLight));
                return;
            case 16:
                CanJni.PSAACSet(11, Neg(this.mACInfo.fgDual));
                return;
            case 17:
                if (CanJni.GetCanFsTp() == 127) {
                    CanJni.PSAACSet(14, Neg(this.mACInfo.fgInnerLoop));
                    return;
                }
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
