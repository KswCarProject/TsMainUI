package com.ts.can.hm;

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
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanHmV70AcActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener {
    private static final int ITEM_AC = 6;
    private static final int ITEM_AC_STATE = 8;
    private static final int ITEM_INFO = 15;
    private static final int ITEM_MODE_C = 5;
    private static final int ITEM_MODE_J = 4;
    private static final int ITEM_MODE_JC = 3;
    private static final int ITEM_MODE_PX = 1;
    private static final int ITEM_MODE_PXJ = 2;
    private static final int ITEM_RC = 10;
    private static final int ITEM_R_AC = 9;
    private static final int ITEM_TEMP_DEC = 11;
    private static final int ITEM_TEMP_INC = 12;
    private static final int ITEM_WIND_DEC = 13;
    private static final int ITEM_WIND_INC = 14;
    private static final int ITEM_XH = 7;
    public static final String TAG = "CanHmV70AcActivity";
    protected static boolean mfgJump;
    private ParamButton mACstate;
    private ParamButton mAcBut;
    protected boolean mAutoFinish = false;
    protected CustomImgView[] mImgTemp = new CustomImgView[15];
    protected CustomImgView[] mImgWind = new CustomImgView[7];
    private ParamButton mInfo;
    private RelativeLayoutManager mManager;
    private ParamButton mMode_Px;
    private ParamButton mMode_Pxj;
    private ParamButton mMode_c;
    private ParamButton mMode_j;
    private ParamButton mMode_jc;
    private ParamButton mRac;
    private ParamButton mRc;
    private ParamButton mTempDec;
    private ParamButton mTempInc;
    private ParamButton mWindDec;
    private ParamButton mWindInc;
    private ParamButton mXh;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_hm_v70_ac);
        mfgJump = CanFunc.IsCanActivityJumped(this);
        this.mManager = new RelativeLayoutManager(this, R.id.can_hm_v70_ac_layout);
        this.mMode_Px = AddBtn(79, 15, 1);
        this.mMode_Px.setDrawable(R.drawable.can_haima_icon01_up, R.drawable.can_haima_icon01_dn);
        this.mMode_Pxj = AddBtn(255, 15, 2);
        this.mMode_Pxj.setDrawable(R.drawable.can_haima_icon02_up, R.drawable.can_haima_icon02_dn);
        this.mMode_jc = AddBtn(431, 15, 3);
        this.mMode_jc.setDrawable(R.drawable.can_haima_icon03_up, R.drawable.can_haima_icon03_dn);
        this.mMode_j = AddBtn(CanCameraUI.BTN_CCH9_MODE14, 15, 4);
        this.mMode_j.setDrawable(R.drawable.can_haima_icon04_up, R.drawable.can_haima_icon04_dn);
        this.mMode_c = AddBtn(800, 15, 5);
        this.mMode_c.setDrawable(R.drawable.can_haima_icon05_up, R.drawable.can_haima_icon05_dn);
        this.mAcBut = AddBtn(79, 453, 6);
        this.mAcBut.setDrawable(R.drawable.can_haima_icon06_up, R.drawable.can_haima_icon06_dn);
        this.mXh = AddBtn(255, 453, 7);
        this.mXh.setDrawable(R.drawable.can_haima_icon08_dn, R.drawable.can_haima_icon07_dn);
        this.mACstate = AddBtn(431, 453, 8);
        this.mACstate.setDrawable(R.drawable.can_haima_icon09_up, R.drawable.can_haima_icon09_dn);
        this.mRac = AddBtn(CanCameraUI.BTN_CCH9_MODE14, 453, 9);
        this.mRac.setDrawable(R.drawable.can_haima_icon10_up, R.drawable.can_haima_icon10_dn);
        this.mRc = AddBtn(800, 453, 10);
        this.mRc.setDrawable(R.drawable.can_haima_icon11_up, R.drawable.can_haima_icon11_dn);
        this.mTempDec = AddBtn(28, 255, 11);
        this.mTempDec.setDrawable(R.drawable.can_haima_jian_up, R.drawable.can_haima_jian_dn);
        this.mTempInc = AddBtn(468, 255, 12);
        this.mTempInc.setDrawable(R.drawable.can_haima_jia_up, R.drawable.can_haima_jia_dn);
        for (int i = 0; i < 15; i++) {
            if (i == 0) {
                this.mImgTemp[i] = this.mManager.AddImage(130, Can.CAN_GM_CAPTIVA_OD);
                this.mImgTemp[i].setStateDrawable(R.drawable.can_haima_tmp01_up, R.drawable.can_haima_tmp01_dn);
            } else if (i == 14) {
                this.mImgTemp[i] = this.mManager.AddImage(426, Can.CAN_GM_CAPTIVA_OD);
                this.mImgTemp[i].setStateDrawable(R.drawable.can_haima_tmp03_up, R.drawable.can_haima_tmp03_dn);
            } else {
                this.mImgTemp[i] = this.mManager.AddImage(((i - 1) * 21) + Can.CAN_HYUNDAI_WC, Can.CAN_GM_CAPTIVA_OD);
                this.mImgTemp[i].setStateDrawable(R.drawable.can_haima_tmp02_up, R.drawable.can_haima_tmp02_dn);
            }
        }
        this.mWindDec = AddBtn(586, 262, 13);
        this.mWindDec.setDrawable(R.drawable.can_haima_xfs_up, R.drawable.can_haima_xfs_dn);
        this.mWindInc = AddBtn(906, 262, 14);
        this.mWindInc.setDrawable(R.drawable.can_haima_dfs_up, R.drawable.can_haima_dfs_dn);
        this.mImgWind[0] = this.mManager.AddImage(673, 184);
        this.mImgWind[0].setStateDrawable(R.drawable.can_haima_fl01_up, R.drawable.can_haima_fl01_dn);
        this.mImgWind[1] = this.mManager.AddImage(706, 184);
        this.mImgWind[1].setStateDrawable(R.drawable.can_haima_fl02_up, R.drawable.can_haima_fl02_dn);
        this.mImgWind[2] = this.mManager.AddImage(739, 184);
        this.mImgWind[2].setStateDrawable(R.drawable.can_haima_fl03_up, R.drawable.can_haima_fl03_dn);
        this.mImgWind[3] = this.mManager.AddImage(KeyDef.SKEY_POWEWR_4, 184);
        this.mImgWind[3].setStateDrawable(R.drawable.can_haima_fl04_up, R.drawable.can_haima_fl04_dn);
        this.mImgWind[4] = this.mManager.AddImage(KeyDef.SKEY_MUTE_2, 184);
        this.mImgWind[4].setStateDrawable(R.drawable.can_haima_fl05_up, R.drawable.can_haima_fl05_dn);
        this.mImgWind[5] = this.mManager.AddImage(KeyDef.SKEY_HOME_5, 184);
        this.mImgWind[5].setStateDrawable(R.drawable.can_haima_fl06_up, R.drawable.can_haima_fl06_dn);
        this.mImgWind[6] = this.mManager.AddImage(871, 184);
        this.mImgWind[6].setStateDrawable(R.drawable.can_haima_fl07_up, R.drawable.can_haima_fl07_dn);
        this.mInfo = AddBtn(909, 126, 15);
        this.mInfo.setDrawable(R.drawable.can_haima_info_up, R.drawable.can_haima_info_dn);
    }

    private ParamButton AddBtn(int x, int y, int id) {
        ParamButton btn = this.mManager.AddButton(x, y);
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
        int temp;
        CanDataInfo.CAN_ACInfo mACInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.mAcBut.SetSel(mACInfo.fgAC);
        this.mXh.SetSel(mACInfo.fgInnerLoop);
        this.mACstate.SetSel(uint2Bool(mACInfo.PWR));
        this.mRac.SetSel(mACInfo.fgRac);
        this.mRc.SetSel(mACInfo.fgRearLight);
        this.mMode_c.SetSel(mACInfo.fgForeWindMode);
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
        } else {
            this.mMode_Pxj.setSelected(false);
            this.mMode_jc.setSelected(false);
            this.mMode_Px.setSelected(false);
            this.mMode_j.setSelected(false);
        }
        for (int i = 0; i < 7; i++) {
            if (i < mACInfo.nWindValue) {
                this.mImgWind[i].setSelected(true);
            } else {
                this.mImgWind[i].setSelected(false);
            }
        }
        if (mACInfo.nLeftTemp >= 32) {
            temp = 8;
        } else {
            temp = 5;
        }
        if (mACInfo.nLeftTemp == 31) {
            mACInfo.nLeftTemp = 36;
        }
        if (mACInfo.nLeftTemp <= 4) {
            mACInfo.nLeftTemp = 5;
        }
        for (int i2 = 0; i2 < 15; i2++) {
            if (i2 <= (mACInfo.nLeftTemp - temp) / 2) {
                this.mImgTemp[i2].setSelected(true);
            } else {
                this.mImgTemp[i2].setSelected(false);
            }
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        CanDataInfo.CAN_ACInfo mACInfo = Can.mACInfo;
        int action = event.getAction();
        if (action != 0) {
            if (1 == action) {
                Log.d(TAG, "****ACTION_UP*****");
                switch (((Integer) v.getTag()).intValue()) {
                    case 15:
                        CanJni.HmV70CarSet(176, 0);
                        break;
                }
            }
        } else {
            switch (((Integer) v.getTag()).intValue()) {
                case 1:
                    CanJni.HmV70CarSet(163, 0);
                    break;
                case 2:
                    CanJni.HmV70CarSet(163, 1);
                    break;
                case 3:
                    CanJni.HmV70CarSet(163, 2);
                    break;
                case 4:
                    CanJni.HmV70CarSet(163, 3);
                    break;
                case 5:
                    CanJni.HmV70CarSet(163, 4);
                    break;
                case 6:
                    if (mACInfo.fgAC != 0) {
                        CanJni.HmV70CarSet(164, 0);
                        break;
                    } else {
                        CanJni.HmV70CarSet(164, 1);
                        break;
                    }
                case 7:
                    if (mACInfo.fgInnerLoop != 0) {
                        CanJni.HmV70CarSet(165, 1);
                        break;
                    } else {
                        CanJni.HmV70CarSet(165, 0);
                        break;
                    }
                case 8:
                    CanJni.HmV70CarSet(168, 1);
                    break;
                case 9:
                    if (mACInfo.fgRac != 0) {
                        CanJni.HmV70CarSet(166, 0);
                        break;
                    } else {
                        CanJni.HmV70CarSet(166, 1);
                        break;
                    }
                case 10:
                    if (mACInfo.fgRearLight != 0) {
                        CanJni.HmV70CarSet(167, 0);
                        break;
                    } else {
                        CanJni.HmV70CarSet(167, 1);
                        break;
                    }
                case 11:
                    CanJni.HmV70CarSet(161, 0);
                    break;
                case 12:
                    CanJni.HmV70CarSet(161, 1);
                    break;
                case 13:
                    CanJni.HmV70CarSet(Can.CAN_CHANA_CS75_WC, 0);
                    break;
                case 14:
                    CanJni.HmV70CarSet(Can.CAN_CHANA_CS75_WC, 1);
                    break;
                case 15:
                    CanJni.HmV70CarSet(176, 1);
                    break;
            }
            CanFunc.mLastACTick = GetTickCount();
        }
        return false;
    }
}
