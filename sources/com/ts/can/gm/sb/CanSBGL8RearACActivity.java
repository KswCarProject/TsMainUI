package com.ts.can.gm.sb;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCommonActivity;
import com.ts.can.CanFunc;
import com.ts.canview.RelativeLayoutContainer;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZResourceManager;

public class CanSBGL8RearACActivity extends CanCommonActivity implements UserCallBack, View.OnClickListener {
    private static final long AC_SHOW_TIME = 3000;
    private static final int ITEM_BACK_FRONT = 0;
    private static final int ITEM_FOOT_MODE = 7;
    private static final int ITEM_HEAD_FOOT_MODE = 6;
    private static final int ITEM_HEAD_MODE = 5;
    private static final int ITEM_REAR_AUTO = 8;
    private static final int ITEM_TEMP_DECREASE = 4;
    private static final int ITEM_TEMP_INCREASE = 3;
    private static final int ITEM_WIND_DECREASE = 2;
    private static final int ITEM_WIND_INCREASE = 1;
    private boolean isAcJump;
    private CanDataInfo.GM_ACSet mAcSet = new CanDataInfo.GM_ACSet();
    private ParamButton mBtnFootMode;
    private ParamButton mBtnHeadFootMode;
    private ParamButton mBtnHeadMode;
    private RelativeLayoutContainer mContainer;
    private ImageView mIvWindIcon;
    private TextView mTvRearSeatState;
    private TextView mTvTemp;
    private TextView mTvWindValue;
    private int[] mWindValues = {R.drawable.can_gl18_ac_fan_00, R.drawable.can_gl18_ac_fan_01, R.drawable.can_gl18_ac_fan_02, R.drawable.can_gl18_ac_fan_03, R.drawable.can_gl18_ac_fan_04, R.drawable.can_gl18_ac_fan_05, R.drawable.can_gl18_ac_fan_06};

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_relative;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.isAcJump = CanFunc.IsCanActivityJumped(this);
        this.mContainer = new RelativeLayoutContainer(this);
        this.mContainer.setBackgroundResource(R.drawable.can_gl18_ac_bg02);
        TextView frontSeat = this.mContainer.addText(80, 173, 40, 86);
        this.mTvRearSeatState = this.mContainer.addText(CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, 74, 221, 46);
        this.mTvWindValue = this.mContainer.addText(372, 165);
        TextView rearSeat = this.mContainer.addText(858, 21, 110, 46);
        this.mTvTemp = this.mContainer.addText(862, Can.CAN_BJ20_WC, 110, 75);
        this.mContainer.setTextStyle(frontSeat, R.string.can_gl8_2017_front_seat, 17, -1, 20).setTextStyle2(this.mTvRearSeatState, R.string.can_gl8_2017_rear_seat_auto, 17, 20).setColorUpDnSelList(this.mTvRearSeatState, -1, Color.parseColor("#06ebf9")).setIdClickListener(this.mTvRearSeatState, 8, this).setTextStyle(this.mTvWindValue, 0, 17, -1, 18).setTextStyle(rearSeat, R.string.can_gl8_2017_rear_seat, 17, -1, 20).setTextStyle(this.mTvTemp, 0, 17, -1, 30);
        this.mIvWindIcon = this.mContainer.addImage(375, 178, R.drawable.can_gl18_ac_fan_00);
        ParamButton backFront = this.mContainer.addButton(10, 173);
        ParamButton windDecrease = this.mContainer.addButton(Can.CAN_TOYOTA_SP_XP, 168);
        ParamButton windIncrease = this.mContainer.addButton(671, 168);
        ParamButton tempIncrease = this.mContainer.addButton(858, 68);
        ParamButton tempDecrease = this.mContainer.addButton(858, 294);
        this.mBtnHeadMode = this.mContainer.addButton(266, 445);
        this.mBtnHeadFootMode = this.mContainer.addButton(450, 445);
        this.mBtnFootMode = this.mContainer.addButton(CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE2, 445);
        this.mContainer.setDrawableUpDn(backFront, R.drawable.can_gl18_ac_qz_up, R.drawable.can_gl18_ac_qz_dn).setIdClickListener(backFront, 0, this).setDrawableUpDn(windIncrease, R.drawable.can_gl18_ac_next_up, R.drawable.can_gl18_ac_next_up).setIdClickListener(windIncrease, 1, this).setDrawableUpDn(windDecrease, R.drawable.can_gl18_ac_prv_up, R.drawable.can_gl18_ac_prv_up).setIdClickListener(windDecrease, 2, this).setDrawableUpDn(tempIncrease, R.drawable.can_gl18_ac_icon_sh_up, R.drawable.can_gl18_ac_icon_sh_up).setIdClickListener(tempIncrease, 3, this).setDrawableUpDn(tempDecrease, R.drawable.can_gl18_ac_icon_xia_up, R.drawable.can_gl18_ac_icon_xia_up).setIdClickListener(tempDecrease, 4, this).setDrawableUpSel(this.mBtnHeadMode, R.drawable.can_gl18_ac_icon01_up, R.drawable.can_gl18_ac_icon01_dn).setIdClickListener(this.mBtnHeadMode, 5, this).setDrawableUpSel(this.mBtnHeadFootMode, R.drawable.can_gl18_ac_icon02_up, R.drawable.can_gl18_ac_icon02_dn).setIdClickListener(this.mBtnHeadFootMode, 6, this).setDrawableUpSel(this.mBtnFootMode, R.drawable.can_gl18_ac_icon03_up, R.drawable.can_gl18_ac_icon03_dn).setIdClickListener(this.mBtnFootMode, 7, this);
    }

    public void UserAll() {
        ResetData(true);
        if (GetTickCount() > CanFunc.mLastACTick + AC_SHOW_TIME && this.isAcJump) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GMGetACSet(this.mAcSet);
        if (!i2b(this.mAcSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAcSet.Update)) {
            this.mAcSet.Update = 0;
            CanFunc.mLastACTick = GetTickCount();
            updateTempValue(this.mAcSet.HQWD);
            updateWindValue(this.mAcSet.HQFS);
            updateWindMode(this.mAcSet.SFMS);
            if (this.mAcSet.HQFS == 15 && this.mAcSet.SFMS == 8) {
                this.mTvRearSeatState.setSelected(true);
            } else {
                this.mTvRearSeatState.setSelected(false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    private void updateTempValue(int temp) {
        if (temp == 0) {
            this.mTvTemp.setText("LO");
        } else if (temp == 63) {
            this.mTvTemp.setText("HI");
        } else if (temp <= 10 || temp >= 45) {
            this.mTvTemp.setText(TXZResourceManager.STYLE_DEFAULT);
        } else {
            this.mTvTemp.setText(String.format("%.1f°", new Object[]{Float.valueOf((((float) (temp - 11)) * 0.5f) + 15.0f)}));
        }
    }

    private void updateWindValue(int wind) {
        if (wind >= 0 && wind <= 6) {
            this.mTvWindValue.setText(String.valueOf(wind) + getString(R.string.can_gl8_2017_wind_unit));
            this.mIvWindIcon.setImageResource(this.mWindValues[wind]);
        } else if (wind == 15) {
            this.mTvWindValue.setText(R.string.can_gl8_2017_auto_wind);
            this.mIvWindIcon.setImageResource(R.drawable.can_gl18_ac_fan_dn);
        }
    }

    private void updateWindMode(int mode) {
        this.mBtnHeadMode.setSelected(false);
        this.mBtnHeadFootMode.setSelected(false);
        this.mBtnFootMode.setSelected(false);
        switch (mode) {
            case 1:
                this.mBtnHeadMode.setSelected(true);
                return;
            case 2:
                this.mBtnFootMode.setSelected(true);
                return;
            case 3:
                this.mBtnHeadFootMode.setSelected(true);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanFunc.showCanActivity(CanSBGL8ACActivity.class);
                return;
            default:
                return;
        }
    }
}
