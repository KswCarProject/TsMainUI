package com.ts.can.geely.yjx1;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.MyProgressBar;
import com.ts.main.common.KeyTouch;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanGeelyYjX1ACActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange, View.OnTouchListener {
    public static final int ITEM_AC = 11;
    public static final int ITEM_AC_MAX = 10;
    public static final int ITEM_FORE_WIND = 8;
    public static final int ITEM_LOOP = 7;
    public static final int ITEM_LTEMP_DEC = 2;
    public static final int ITEM_LTEMP_INC = 1;
    public static final int ITEM_MODE = 4;
    public static final int ITEM_PWR = 3;
    public static final int ITEM_REAR_WIND = 9;
    public static final int ITEM_WIND_DEC = 5;
    public static final int ITEM_WIND_INC = 6;
    public static final String TAG = "CanGeelyYjX1ACActivity";
    protected static boolean mIsAC;
    protected static boolean mfgJump;
    private CanDataInfo.CAN_ACInfo mACInfo;
    private ParamButton mBtnAC;
    private ParamButton mBtnAcMax;
    private ParamButton mBtnForeWind;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtTempDec;
    private ParamButton mBtnLtTempInc;
    private ParamButton mBtnMode;
    private ParamButton mBtnOff;
    private ParamButton mBtnRearWind;
    private ParamButton mBtnWindDec;
    private ParamButton mBtnWindInc;
    private CustomImgView mIvWindAuto;
    private CustomImgView mIvWindDirectAuto;
    private CustomImgView mIvWindDn;
    private CustomImgView mIvWindPx;
    private CustomImgView mIvWindUp;
    protected RelativeLayoutManager mManager;
    private CustomTextView mTvLtTemp;
    private MyProgressBar mWindProg;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        ((RelativeLayout) findViewById(R.id.can_comm_layout)).setBackgroundResource(R.drawable.can_mg_bg);
        mfgJump = CanFunc.IsCanActivityJumped(this);
        this.mACInfo = Can.mACInfo;
        this.mTvLtTemp = AddTemp(109, 18, 131, 62);
        this.mBtnLtTempInc = AddBtn(1, 138, 107, R.drawable.can_yl_upward_up, R.drawable.can_yl_upward_dn);
        this.mBtnLtTempDec = AddBtn(2, 138, 287, R.drawable.can_yl_down_up, R.drawable.can_yl_down_dn);
        this.mWindProg = new MyProgressBar(this, R.drawable.can_yl_rect_up, R.drawable.can_yl_rect_dn);
        this.mWindProg.SetMinMax(0, 8);
        this.mWindProg.SetCurPos(1);
        this.mManager.AddViewWrapContent(this.mWindProg, 352, 292);
        this.mBtnWindDec = AddBtn(5, 287, 271, R.drawable.can_yl_jian_up, R.drawable.can_yl_jian_dn);
        this.mBtnWindInc = AddBtn(6, KeyDef.SKEY_SPEECH_3, 271, R.drawable.can_yl_jia_up, R.drawable.can_yl_jia_dn);
        this.mIvWindAuto = this.mManager.AddImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE6, 296, R.drawable.can_yl_wind_auto);
        this.mManager.AddImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE4, 76, R.drawable.can_mg_people);
        this.mIvWindUp = this.mManager.AddImage(CanCameraUI.BTN_VW_WC_MODE1, 62, R.drawable.can_mg_wind);
        this.mIvWindPx = this.mManager.AddImage(569, 89, R.drawable.can_mg_right);
        this.mIvWindDn = this.mManager.AddImage(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST9, 112, R.drawable.can_mg_down);
        this.mIvWindDirectAuto = this.mManager.AddImage(535, 84, R.drawable.can_mg_auto);
        this.mBtnOff = AddBtn(3, KeyTouch.GAMMA_MAX_NUM, 82, R.drawable.can_mg_del_up, R.drawable.can_mg_del_dn2);
        this.mBtnMode = AddBtn(4, 747, 82, R.drawable.can_mg_mode_up, R.drawable.can_mg_mode_dn);
        this.mBtnLoop = AddBtn(7, 134, 418, R.drawable.can_mg_car01_up, R.drawable.can_mg_car01_dn);
        this.mBtnForeWind = AddBtn(8, 300, 418, R.drawable.can_mg_wind_up, R.drawable.can_mg_wind_dn);
        this.mBtnRearWind = AddBtn(9, 466, 418, R.drawable.can_mg_rear_up, R.drawable.can_mg_rear_dn);
        this.mBtnAcMax = AddBtn(10, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE3, 418, R.drawable.can_psa_408_acmax_up, R.drawable.can_psa_408_acmax_dn);
        this.mBtnAC = AddBtn(11, KeyDef.SKEY_CHUP_5, 418, R.drawable.can_psa_408_ac_up, R.drawable.can_psa_408_ac_dn);
        Log.d(TAG, "jump = " + mfgJump);
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
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
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d(TAG, "-----onResume-----");
        mIsAC = true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
        mIsAC = false;
        mfgJump = false;
    }

    private void updateACUI() {
        this.mACInfo = Can.mACInfo;
        this.mTvLtTemp.setText(this.mACInfo.szLtTemp);
        this.mBtnOff.SetSel(1 - this.mACInfo.PWR);
        this.mIvWindAuto.Show(this.mACInfo.fgAutoWind);
        if (this.mACInfo.fgAutoWind != 0) {
            this.mWindProg.SetCurPos(0);
        } else {
            this.mWindProg.SetCurPos(this.mACInfo.nWindValue);
        }
        if (this.mACInfo.fgInnerLoop != 0) {
            this.mBtnLoop.setDrawable(R.drawable.can_mg_car01_up, R.drawable.can_mg_car01_dn);
        } else {
            this.mBtnLoop.setDrawable(R.drawable.can_mg_car02_up, R.drawable.can_mg_car02_dn);
        }
        this.mIvWindUp.Show(this.mACInfo.fgUpWind);
        this.mIvWindPx.Show(this.mACInfo.fgParallelWind);
        this.mIvWindDn.Show(this.mACInfo.fgDownWind);
        this.mIvWindDirectAuto.Show(this.mACInfo.nWindAutoLevel);
        this.mBtnForeWind.SetSel(this.mACInfo.fgDFBL);
        this.mBtnRearWind.SetSel(this.mACInfo.fgRearLight);
        this.mBtnAcMax.SetSel(this.mACInfo.fgACMax);
        Log.d(TAG, "mACInfo.nAutoLight = " + this.mACInfo.nAutoLight);
        this.mBtnAC.SetSel(this.mACInfo.fgAC);
    }

    private void ResetData(boolean check) {
        Can.updateAC();
        if (!check || Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (mfgJump && GetTickCount() > CanFunc.mLastACTick + 6000) {
            finish();
            Log.d(TAG, "UserAll Exit Ac");
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    public static void ShowAC() {
        if (!mIsAC) {
            mfgJump = true;
            CanFunc.showCanActivity(CanGeelyYjX1ACActivity.class);
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        CanFunc.mLastACTick = GetTickCount();
        if (action == 0) {
            Log.d(TAG, "****ACTION_DOWN*****");
            switch (Id) {
                case 1:
                    CanIF.DealGeelyYjx1ACKey(1);
                    return false;
                case 2:
                    CanIF.DealGeelyYjx1ACKey(2);
                    return false;
                case 3:
                    CanIF.DealGeelyYjx1ACKey(10);
                    return false;
                case 4:
                    CanIF.DealGeelyYjx1ACKey(5);
                    return false;
                case 5:
                    CanIF.DealGeelyYjx1ACKey(4);
                    return false;
                case 6:
                    CanIF.DealGeelyYjx1ACKey(3);
                    return false;
                case 7:
                    CanIF.DealGeelyYjx1ACKey(9);
                    return false;
                case 8:
                    CanIF.DealGeelyYjx1ACKey(11);
                    return false;
                case 9:
                    CanIF.DealGeelyYjx1ACKey(14);
                    return false;
                case 10:
                    CanIF.DealGeelyYjx1ACKey(12);
                    return false;
                case 11:
                    CanIF.DealGeelyYjx1ACKey(8);
                    return false;
                default:
                    return false;
            }
        } else if (1 != action) {
            return false;
        } else {
            Log.d(TAG, "****ACTION_UP*****");
            return false;
        }
    }
}
