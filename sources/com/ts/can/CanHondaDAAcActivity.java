package com.ts.can;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanHondaDAAcActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener {
    private static final int ITEM_AC_OF = 2;
    private static final int ITEM_AC_ON = 1;
    private static final int ITEM_MODE_J = 5;
    private static final int ITEM_MODE_JC = 6;
    private static final int ITEM_MODE_PX = 3;
    private static final int ITEM_MODE_PXJ = 4;
    private static final int ITEM_MODE_WIND1 = 7;
    private static final int ITEM_MODE_WIND2 = 8;
    private static final int ITEM_MODE_WIND3 = 9;
    private static final int ITEM_MODE_WIND4 = 10;
    private static final int ITEM_MODE_WIND5 = 11;
    private static final int ITEM_MODE_WIND6 = 12;
    private static final int ITEM_MODE_WIND7 = 13;
    public static final String TAG = "CanHondaDaAcActivity";
    protected static boolean mfgJump;
    private ParamButton mAcOf;
    private ParamButton mAcOn;
    protected boolean mAutoFinish = false;
    private TextView mLtTemp;
    private RelativeLayoutManager mManager;
    private ParamButton mMode_Px;
    private ParamButton mMode_Pxj;
    private ParamButton mMode_j;
    private ParamButton mMode_jc;
    private TextView mRtTemp;
    private ParamButton[] mWindLevel = new ParamButton[7];

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_hondda_sy_ac);
        mfgJump = CanFunc.IsCanActivityJumped(this);
        if (MainSet.GetScreenType() == 5) {
            initScreen_1280x480();
        } else if (MainSet.GetScreenType() == 3) {
            initScreen_768x432();
        } else {
            initCommonScreen();
        }
    }

    private void initScreen_768x432() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_hondda_sy_ac_layout);
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_syac_sp_bg);
        this.mLtTemp = this.mManager.AddText(28, 25, 95, 59);
        this.mLtTemp.setTextSize(0, 32.0f);
        this.mLtTemp.setTextColor(-1);
        this.mLtTemp.setText("13℃");
        this.mLtTemp.setGravity(17);
        this.mRtTemp = this.mManager.AddText(CanCameraUI.BTN_LANDWIND_2D_RIGHT, 25, 95, 59);
        this.mRtTemp.setTextSize(0, 32.0f);
        this.mRtTemp.setTextColor(-1);
        this.mRtTemp.setGravity(17);
        this.mRtTemp.setText("13℃");
        this.mAcOn = AddBtn(208, 98, Can.CAN_CHRYSLER_TXB, 57, 1);
        this.mAcOn.setDrawable(R.drawable.can_syac_on_up, R.drawable.can_syac_on_dn);
        this.mAcOf = AddBtn(460, 98, Can.CAN_CHRYSLER_TXB, 57, 2);
        this.mAcOf.setDrawable(R.drawable.can_syac_off_up, R.drawable.can_syac_off_dn);
        this.mMode_Px = AddBtn(208, 167, 120, 57, 3);
        this.mMode_Px.setDrawable(R.drawable.can_syac_bt01_up, R.drawable.can_syac_bt01_dn);
        this.mMode_Pxj = AddBtn(KeyDef.RKEY_EJECT_L, 167, 120, 57, 4);
        this.mMode_Pxj.setDrawable(R.drawable.can_syac_bt02_up, R.drawable.can_syac_bt02_dn);
        this.mMode_j = AddBtn(460, 167, 120, 57, 5);
        this.mMode_j.setDrawable(R.drawable.can_syac_bt03_up, R.drawable.can_syac_bt03_dn);
        this.mMode_jc = AddBtn(586, 167, 120, 57, 6);
        this.mMode_jc.setDrawable(R.drawable.can_syac_bt04_up, R.drawable.can_syac_bt04_dn);
        for (int i = 0; i < 7; i++) {
            this.mWindLevel[i] = AddBtn((i * 72) + 208, 270, 67, 141, i + 7);
        }
        this.mWindLevel[0].setDrawable(R.drawable.can_syac_fl01_up, R.drawable.can_syac_fl01_dn);
        this.mWindLevel[1].setDrawable(R.drawable.can_syac_fl02_up, R.drawable.can_syac_fl02_dn);
        this.mWindLevel[2].setDrawable(R.drawable.can_syac_fl03_up, R.drawable.can_syac_fl03_dn);
        this.mWindLevel[3].setDrawable(R.drawable.can_syac_fl04_up, R.drawable.can_syac_fl04_dn);
        this.mWindLevel[4].setDrawable(R.drawable.can_syac_fl05_up, R.drawable.can_syac_fl05_dn);
        this.mWindLevel[5].setDrawable(R.drawable.can_syac_fl06_up, R.drawable.can_syac_fl06_dn);
        this.mWindLevel[6].setDrawable(R.drawable.can_syac_fl07_up, R.drawable.can_syac_fl07_dn);
    }

    private void initScreen_1280x480() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_hondda_sy_ac_layout);
        this.mManager.GetLayout().setBackgroundResource(R.drawable.can_syac_hp_bg);
        this.mLtTemp = this.mManager.AddText(57, 2, 131, 59);
        this.mLtTemp.setTextSize(0, 40.0f);
        this.mLtTemp.setTextColor(-1);
        this.mLtTemp.setText("13℃");
        this.mLtTemp.setGravity(17);
        this.mRtTemp = this.mManager.AddText(1093, 2, 131, 59);
        this.mRtTemp.setTextSize(0, 40.0f);
        this.mRtTemp.setTextColor(-1);
        this.mRtTemp.setGravity(17);
        this.mRtTemp.setText("13℃");
        this.mAcOn = AddBtn(394, 73, 1);
        this.mAcOn.setDrawable(R.drawable.can_syac_on_up, R.drawable.can_syac_on_dn);
        this.mAcOf = AddBtn(744, 73, 2);
        this.mAcOf.setDrawable(R.drawable.can_syac_off_up, R.drawable.can_syac_off_dn);
        this.mMode_Px = AddBtn(394, Can.CAN_DFFG_S560, 3);
        this.mMode_Px.setDrawable(R.drawable.can_syac_bt01_up, R.drawable.can_syac_bt01_dn);
        this.mMode_Pxj = AddBtn(569, Can.CAN_DFFG_S560, 4);
        this.mMode_Pxj.setDrawable(R.drawable.can_syac_bt02_up, R.drawable.can_syac_bt02_dn);
        this.mMode_j = AddBtn(744, Can.CAN_DFFG_S560, 5);
        this.mMode_j.setDrawable(R.drawable.can_syac_bt03_up, R.drawable.can_syac_bt03_dn);
        this.mMode_jc = AddBtn(919, Can.CAN_DFFG_S560, 6);
        this.mMode_jc.setDrawable(R.drawable.can_syac_bt04_up, R.drawable.can_syac_bt04_dn);
        for (int i = 0; i < 7; i++) {
            this.mWindLevel[i] = AddBtn((i * 100) + 395, Can.CAN_TOYOTA_SP_XP, i + 7);
        }
        this.mWindLevel[0].setDrawable(R.drawable.can_syac_fl01_up, R.drawable.can_syac_fl01_dn);
        this.mWindLevel[1].setDrawable(R.drawable.can_syac_fl02_up, R.drawable.can_syac_fl02_dn);
        this.mWindLevel[2].setDrawable(R.drawable.can_syac_fl03_up, R.drawable.can_syac_fl03_dn);
        this.mWindLevel[3].setDrawable(R.drawable.can_syac_fl04_up, R.drawable.can_syac_fl04_dn);
        this.mWindLevel[4].setDrawable(R.drawable.can_syac_fl05_up, R.drawable.can_syac_fl05_dn);
        this.mWindLevel[5].setDrawable(R.drawable.can_syac_fl06_up, R.drawable.can_syac_fl06_dn);
        this.mWindLevel[6].setDrawable(R.drawable.can_syac_fl07_up, R.drawable.can_syac_fl07_dn);
    }

    private void initCommonScreen() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_hondda_sy_ac_layout);
        this.mLtTemp = this.mManager.AddText(17, 7, 131, 59);
        this.mLtTemp.setTextSize(0, 40.0f);
        this.mLtTemp.setTextColor(-1);
        this.mLtTemp.setText("13℃");
        this.mLtTemp.setGravity(19);
        this.mRtTemp = this.mManager.AddText(872, 7, 131, 59);
        this.mRtTemp.setTextSize(0, 40.0f);
        this.mRtTemp.setTextColor(-1);
        this.mRtTemp.setGravity(21);
        this.mRtTemp.setText("13℃");
        this.mAcOn = AddBtn(266, 98, 1);
        this.mAcOn.setDrawable(R.drawable.can_syac_on_up, R.drawable.can_syac_on_dn);
        this.mAcOf = AddBtn(CanCameraUI.BTN_CCH9_MODE7, 98, 2);
        this.mAcOf.setDrawable(R.drawable.can_syac_off_up, R.drawable.can_syac_off_dn);
        this.mMode_Px = AddBtn(266, 194, 3);
        this.mMode_Px.setDrawable(R.drawable.can_syac_bt01_up, R.drawable.can_syac_bt01_dn);
        this.mMode_Pxj = AddBtn(441, 194, 4);
        this.mMode_Pxj.setDrawable(R.drawable.can_syac_bt02_up, R.drawable.can_syac_bt02_dn);
        this.mMode_j = AddBtn(CanCameraUI.BTN_CCH9_MODE7, 194, 5);
        this.mMode_j.setDrawable(R.drawable.can_syac_bt03_up, R.drawable.can_syac_bt03_dn);
        this.mMode_jc = AddBtn(KeyDef.SKEY_SEEKDN_3, 194, 6);
        this.mMode_jc.setDrawable(R.drawable.can_syac_bt04_up, R.drawable.can_syac_bt04_dn);
        for (int i = 0; i < 7; i++) {
            this.mWindLevel[i] = AddBtn((i * 100) + 267, KeyDef.RKEY_BT, i + 7);
        }
        this.mWindLevel[0].setDrawable(R.drawable.can_syac_fl01_up, R.drawable.can_syac_fl01_dn);
        this.mWindLevel[1].setDrawable(R.drawable.can_syac_fl02_up, R.drawable.can_syac_fl02_dn);
        this.mWindLevel[2].setDrawable(R.drawable.can_syac_fl03_up, R.drawable.can_syac_fl03_dn);
        this.mWindLevel[3].setDrawable(R.drawable.can_syac_fl04_up, R.drawable.can_syac_fl04_dn);
        this.mWindLevel[4].setDrawable(R.drawable.can_syac_fl05_up, R.drawable.can_syac_fl05_dn);
        this.mWindLevel[5].setDrawable(R.drawable.can_syac_fl06_up, R.drawable.can_syac_fl06_dn);
        this.mWindLevel[6].setDrawable(R.drawable.can_syac_fl07_up, R.drawable.can_syac_fl07_dn);
    }

    private ParamButton AddBtn(int x, int y, int id) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setOnTouchListener(this);
        btn.setTag(Integer.valueOf(id));
        return btn;
    }

    private ParamButton AddBtn(int x, int y, int w, int h, int id) {
        ParamButton btn = this.mManager.AddButton(x, y, w, h);
        btn.setOnTouchListener(this);
        btn.setTag(Integer.valueOf(id));
        return btn;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Can.updateAC();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause----- mAutoFinish = " + this.mAutoFinish);
        if (!CanFunc.mfgShowAC) {
            if (!this.mAutoFinish) {
                finish();
                Log.d(TAG, "-----onPause finish-----");
            }
            mfgJump = false;
            this.mAutoFinish = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        updateACUI();
        MainTask.GetInstance().SetUserCallBack(this);
        CanFunc.mfgShowAC = false;
    }

    public void UserAll() {
        Can.updateAC();
        if (Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (mfgJump && GetTickCount() > CanFunc.mLastACTick + 3000) {
            finish();
            Log.d(TAG, "UserAll Exit Ac");
            this.mAutoFinish = true;
        }
    }

    protected static int uint2Bool(int val) {
        if (val == 0) {
            return 1;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        CanDataInfo.CAN_ACInfo mACInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mAcOn.SetSel(mACInfo.fgAC);
        this.mAcOf.SetSel(uint2Bool(mACInfo.fgAC));
        if (int2Bool(mACInfo.fgParallelWind) && int2Bool(mACInfo.fgDownWind)) {
            this.mMode_Pxj.setSelected(true);
            this.mMode_jc.setSelected(false);
            this.mMode_Px.setSelected(false);
            this.mMode_j.setSelected(false);
        } else if (int2Bool(mACInfo.fgUpWind) && int2Bool(mACInfo.fgDownWind)) {
            this.mMode_jc.setSelected(true);
            this.mMode_Pxj.setSelected(false);
            this.mMode_Px.setSelected(false);
            this.mMode_j.setSelected(false);
        } else if (int2Bool(mACInfo.fgParallelWind)) {
            this.mMode_Px.setSelected(true);
            this.mMode_Pxj.setSelected(false);
            this.mMode_jc.setSelected(false);
            this.mMode_j.setSelected(false);
        } else if (int2Bool(mACInfo.fgDownWind)) {
            this.mMode_j.setSelected(true);
            this.mMode_Pxj.setSelected(false);
            this.mMode_jc.setSelected(false);
            this.mMode_Px.setSelected(false);
        }
        for (int i = 0; i < 7; i++) {
            if (i < mACInfo.nWindValue) {
                this.mWindLevel[i].setSelected(true);
            } else {
                this.mWindLevel[i].setSelected(false);
            }
        }
        try {
            this.mLtTemp.setText(mACInfo.szLtTemp);
            this.mRtTemp.setText(mACInfo.szRtTemp);
        } catch (Exception e) {
            Log.e(TAG, "set Temp text Exception!");
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            switch (((Integer) v.getTag()).intValue()) {
                case 1:
                    CanJni.HondaDACarSet(172, 1);
                    break;
                case 2:
                    CanJni.HondaDACarSet(172, 2);
                    break;
                case 3:
                    CanJni.HondaDACarSet(172, 3);
                    break;
                case 4:
                    CanJni.HondaDACarSet(172, 4);
                    break;
                case 5:
                    CanJni.HondaDACarSet(172, 5);
                    break;
                case 6:
                    CanJni.HondaDACarSet(172, 6);
                    break;
                case 7:
                    CanJni.HondaDACarSet(173, 1);
                    break;
                case 8:
                    CanJni.HondaDACarSet(173, 2);
                    break;
                case 9:
                    CanJni.HondaDACarSet(173, 3);
                    break;
                case 10:
                    CanJni.HondaDACarSet(173, 4);
                    break;
                case 11:
                    CanJni.HondaDACarSet(173, 5);
                    break;
                case 12:
                    CanJni.HondaDACarSet(173, 6);
                    break;
                case 13:
                    CanJni.HondaDACarSet(173, 7);
                    break;
            }
            CanFunc.mLastACTick = GetTickCount();
            return false;
        } else if (1 != action) {
            return false;
        } else {
            Log.d(TAG, "****ACTION_UP*****");
            return false;
        }
    }
}
