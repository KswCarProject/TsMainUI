package com.ts.can.baic.senova.m50f;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCommonACActivity;
import com.yyw.ts70xhw.KeyDef;

public class CanM50FCarACActivity extends CanCommonACActivity {
    public void onClick(View v) {
        int cmd = -1;
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                cmd = 13;
                break;
            case 1:
                cmd = 14;
                break;
            case 4:
                cmd = 11;
                break;
            case 5:
                cmd = 12;
                break;
            case 6:
                cmd = 7;
                break;
            case 7:
                cmd = 8;
                break;
            case 8:
                cmd = 9;
                break;
            case 9:
                cmd = 10;
                break;
            case 10:
                cmd = 1;
                break;
            case 12:
                cmd = 4;
                break;
            case 15:
                cmd = 6;
                break;
            case 16:
                cmd = 18;
                break;
            case 18:
                cmd = 0;
                break;
        }
        CanJni.SenovaAcSet(cmd, 1);
    }

    /* access modifiers changed from: protected */
    public void InitACButtons() {
        InitTempButtons(false);
        InitWindButtons(false);
        InitModeButtons(false, true);
        InitClickableStatusButtons(false);
        this.mContainer.setBackgroundResource(R.drawable.can_rh7_bg02);
    }

    /* access modifiers changed from: protected */
    public void InitWindButtons(boolean isTouchable) {
        this.mWindIncrease = this.mContainer.addButton(188, 117);
        this.mWindDecrease = this.mContainer.addButton(188, KeyDef.RKEY_POWER);
        this.mContainer.addImage(191, Can.CAN_TEANA_OLD_DJ, R.drawable.can_rh7_6signal_up);
        int[] mWindArrays = {R.drawable.can_rh7_6signal01_dn, R.drawable.can_rh7_6signal02_dn, R.drawable.can_rh7_6signal03_dn, R.drawable.can_rh7_6signal04_dn, R.drawable.can_rh7_6signal05_dn, R.drawable.can_rh7_6signal06_dn};
        this.mIvWinds = new ImageView[mWindArrays.length];
        for (int i = 0; i < this.mIvWinds.length; i++) {
            this.mIvWinds[i] = this.mContainer.addImage(191, Can.CAN_TEANA_OLD_DJ, mWindArrays[i]);
        }
        this.mContainer.setDrawableUpDnSel(this.mWindIncrease, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn).setDrawableUpDnSel(this.mWindDecrease, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn);
        if (isTouchable) {
            this.mContainer.setIdTouchListener(this.mWindIncrease, 4, this);
            this.mContainer.setIdTouchListener(this.mWindDecrease, 5, this);
            return;
        }
        this.mContainer.setIdClickListener(this.mWindIncrease, 4, this);
        this.mContainer.setIdClickListener(this.mWindDecrease, 5, this);
    }

    /* access modifiers changed from: protected */
    public void InitTempButtons(boolean isTouchable) {
        this.mLeftTempIncrease = this.mContainer.addButton(50, 117);
        this.mLeftTempDecrease = this.mContainer.addButton(50, KeyDef.RKEY_POWER);
        this.mTvLeftTemp = this.mContainer.addText(53, Can.CAN_TEANA_OLD_DJ, 92, 61);
        this.mContainer.setDrawableUpDnSel(this.mLeftTempIncrease, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn).setDrawableUpDnSel(this.mLeftTempDecrease, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn).setTextStyle(this.mTvLeftTemp, 0, 17, Color.parseColor("#08D2D3"), 18).setIdClickListener(this.mLeftTempIncrease, 0, this).setIdClickListener(this.mLeftTempDecrease, 1, this);
    }

    /* access modifiers changed from: protected */
    public void InitClickableStatusButtons(boolean isTouchable) {
        this.mBtnAc = this.mContainer.addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 130);
        this.mBtnLooperOutter = this.mContainer.addButton(KeyDef.SKEY_POWEWR_2, 130);
        this.mBtnFront = this.mContainer.addButton(CanCameraUI.BTN_LANDWIND_3D_LEFT_DOWN, 285);
        this.mBtnRear = this.mContainer.addButton(KeyDef.SKEY_POWEWR_2, 285);
        this.mBtnAcToggle = this.mContainer.addButton(462, 426);
        this.mContainer.setDrawableUpDnSel(this.mBtnAc, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn).setDrawableUpDnSel(this.mBtnLooperOutter, R.drawable.can_rh7_wxh_up, R.drawable.can_rh7_nxh_dn).setDrawableUpDnSel(this.mBtnFront, R.drawable.can_rh7_window_up, R.drawable.can_rh7_window_dn).setDrawableUpDnSel(this.mBtnRear, R.drawable.can_rh7_window02_up, R.drawable.can_rh7_window02_dn).setDrawableUpDnSel(this.mBtnAcToggle, R.drawable.can_rh7_del_up, R.drawable.can_rh7_del_dn).setIdClickListener(this.mBtnAc, 10, this).setIdClickListener(this.mBtnLooperOutter, 12, this).setIdClickListener(this.mBtnFront, 15, this).setIdClickListener(this.mBtnRear, 16, this).setIdClickListener(this.mBtnAcToggle, 18, this);
    }
}
