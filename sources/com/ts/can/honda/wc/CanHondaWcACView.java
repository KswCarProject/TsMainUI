package com.ts.can.honda.wc;

import android.app.Activity;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanHondaWcACView extends CanBaseACView {
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
    private ParamButton mAcOf;
    private ParamButton mAcOn;
    protected boolean mAutoFinish = false;
    private TextView mLtTemp;
    private ParamButton mMode_Px;
    private ParamButton mMode_Pxj;
    private ParamButton mMode_j;
    private ParamButton mMode_jc;
    private TextView mRtTemp;
    private ParamButton[] mWindLevel;

    public CanHondaWcACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (1 == action) {
                switch (Id) {
                    case 3:
                        CanJni.HondaWcAirCmd(9, 0);
                        break;
                    case 4:
                        CanJni.HondaWcAirCmd(24, 0);
                        break;
                    case 5:
                        CanJni.HondaWcAirCmd(10, 0);
                        break;
                    case 6:
                        CanJni.HondaWcAirCmd(23, 0);
                        break;
                }
            }
        } else {
            switch (Id) {
                case 1:
                    CanJni.HondaWcAirCmd(2, 1);
                    break;
                case 2:
                    CanJni.HondaWcAirCmd(2, 0);
                    break;
                case 3:
                    CanJni.HondaWcAirCmd(9, 1);
                    break;
                case 4:
                    CanJni.HondaWcAirCmd(24, 1);
                    break;
                case 5:
                    CanJni.HondaWcAirCmd(10, 1);
                    break;
                case 6:
                    CanJni.HondaWcAirCmd(23, 1);
                    break;
                case 7:
                    CanJni.HondaWcAirCmd(25, 1);
                    break;
                case 8:
                    CanJni.HondaWcAirCmd(25, 2);
                    break;
                case 9:
                    CanJni.HondaWcAirCmd(25, 3);
                    break;
                case 10:
                    CanJni.HondaWcAirCmd(25, 4);
                    break;
                case 11:
                    CanJni.HondaWcAirCmd(25, 5);
                    break;
                case 12:
                    CanJni.HondaWcAirCmd(25, 6);
                    break;
                case 13:
                    CanJni.HondaWcAirCmd(25, 7);
                    break;
            }
            CanFunc.mLastACTick = SystemClock.uptimeMillis();
        }
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
        setBackgroundResource(R.drawable.can_syac_bg);
        initCommonScreen();
    }

    private void initCommonScreen() {
        this.mWindLevel = new ParamButton[7];
        this.mLtTemp = addText(17, 7, 131, 59);
        this.mLtTemp.setTextSize(0, 40.0f);
        this.mLtTemp.setTextColor(-1);
        this.mLtTemp.setText("13℃");
        this.mLtTemp.setGravity(19);
        this.mRtTemp = addText(872, 7, 131, 59);
        this.mRtTemp.setTextSize(0, 40.0f);
        this.mRtTemp.setTextColor(-1);
        this.mRtTemp.setGravity(21);
        this.mRtTemp.setText("13℃");
        this.mAcOn = addButton(266, 98);
        this.mAcOn.setDrawable(R.drawable.can_syac_on_up, R.drawable.can_syac_on_dn);
        this.mAcOf = addButton(CanCameraUI.BTN_CCH9_MODE7, 98);
        this.mAcOf.setDrawable(R.drawable.can_syac_off_up, R.drawable.can_syac_off_dn);
        this.mMode_Px = addButton(266, 194);
        this.mMode_Px.setDrawable(R.drawable.can_syac_bt01_up, R.drawable.can_syac_bt01_dn);
        this.mMode_Pxj = addButton(441, 194);
        this.mMode_Pxj.setDrawable(R.drawable.can_syac_bt02_up, R.drawable.can_syac_bt02_dn);
        this.mMode_j = addButton(CanCameraUI.BTN_CCH9_MODE7, 194);
        this.mMode_j.setDrawable(R.drawable.can_syac_bt03_up, R.drawable.can_syac_bt03_dn);
        this.mMode_jc = addButton(KeyDef.SKEY_SEEKDN_3, 194);
        this.mMode_jc.setDrawable(R.drawable.can_syac_bt04_up, R.drawable.can_syac_bt04_dn);
        setIdTouchListener(this.mAcOn, 1).setIdTouchListener(this.mAcOf, 2).setIdTouchListener(this.mMode_Px, 3).setIdTouchListener(this.mMode_Pxj, 4).setIdTouchListener(this.mMode_j, 5).setIdTouchListener(this.mMode_jc, 6);
        for (int i = 0; i < 7; i++) {
            this.mWindLevel[i] = addButton((i * 100) + 267, KeyDef.RKEY_BT);
            setIdTouchListener(this.mWindLevel[i], i + 7);
        }
        this.mWindLevel[0].setDrawable(R.drawable.can_syac_fl01_up, R.drawable.can_syac_fl01_dn);
        this.mWindLevel[1].setDrawable(R.drawable.can_syac_fl02_up, R.drawable.can_syac_fl02_dn);
        this.mWindLevel[2].setDrawable(R.drawable.can_syac_fl03_up, R.drawable.can_syac_fl03_dn);
        this.mWindLevel[3].setDrawable(R.drawable.can_syac_fl04_up, R.drawable.can_syac_fl04_dn);
        this.mWindLevel[4].setDrawable(R.drawable.can_syac_fl05_up, R.drawable.can_syac_fl05_dn);
        this.mWindLevel[5].setDrawable(R.drawable.can_syac_fl06_up, R.drawable.can_syac_fl06_dn);
        this.mWindLevel[6].setDrawable(R.drawable.can_syac_fl07_up, R.drawable.can_syac_fl07_dn);
    }

    protected static int uint2Bool(int val) {
        if (val == 0) {
            return 1;
        }
        return 0;
    }

    protected static boolean int2Bool(int val) {
        if (val == 0) {
            return false;
        }
        return true;
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
        }
    }
}
