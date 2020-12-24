package com.ts.can.gm.sb;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCommonActivity;
import com.ts.can.CanFunc;
import com.ts.canview.RelativeLayoutContainer;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZPoiSearchManager;
import com.yyw.ts70xhw.KeyDef;

public class CanSBGL8ACActivity extends CanCommonActivity implements View.OnClickListener, View.OnTouchListener {
    private static int AC_SHOW_TIME = TXZPoiSearchManager.DEFAULT_NEARBY_RADIUS;
    private static final int ITEM_AC = 10;
    private static final int ITEM_AUTO = 12;
    private static final int ITEM_ENTER_REAR_AC = 13;
    private static final int ITEM_FOOT_FRONT_MODE = 7;
    private static final int ITEM_FOOT_MODE = 6;
    private static final int ITEM_HEAD_FOOT_MODE = 5;
    private static final int ITEM_HEAD_MODE = 4;
    private static final int ITEM_LEFT_DECREASE = 1;
    private static final int ITEM_LEFT_INCREASE = 0;
    private static final int ITEM_LOOP = 11;
    private static final int ITEM_RIGHT_DECREASE = 3;
    private static final int ITEM_RIGHT_INCREASE = 2;
    private static final int ITEM_TEMP_SYNC = 14;
    private static final int ITEM_WIND_DECREASE = 9;
    private static final int ITEM_WIND_INCREASE = 8;
    private int cmd;
    private boolean isACJump = false;
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private ParamButton mBtnAC;
    private ParamButton mBtnFootFrontMode;
    private ParamButton mBtnFootMode;
    private ParamButton mBtnHeadFootMode;
    private ParamButton mBtnHeadMode;
    private ParamButton mBtnLoop;
    private RelativeLayoutContainer mContainer;
    private ImageView mIvWindIcon;
    private TextView mTvACToggle;
    private TextView mTvAuto;
    private TextView mTvLeftTemp;
    private TextView mTvRearSeat;
    private TextView mTvRightTemp;
    private TextView mTvSyncState;
    private TextView mTvWindState;
    private int[] mWindValues = {R.drawable.can_gl18_ac_fan_00, R.drawable.can_gl18_ac_fan_001, R.drawable.can_gl18_ac_fan_002, R.drawable.can_gl18_ac_fan_003, R.drawable.can_gl18_ac_fan_004, R.drawable.can_gl18_ac_fan_005, R.drawable.can_gl18_ac_fan_006, R.drawable.can_gl18_ac_fan_007, R.drawable.can_gl18_ac_fan_008};

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_relative;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.isACJump = CanFunc.IsCanActivityJumped(this);
        this.mContainer = new RelativeLayoutContainer(this);
        this.mContainer.setBackgroundResource(R.drawable.can_gl18_ac_bg01);
        this.mTvLeftTemp = this.mContainer.addText(49, Can.CAN_BJ20_WC, 110, 75);
        this.mTvRightTemp = this.mContainer.addText(862, Can.CAN_BJ20_WC, 110, 75);
        this.mTvACToggle = this.mContainer.addText(290, 115, 50, 35);
        this.mTvAuto = this.mContainer.addText(CanCameraUI.BTN_GEELY_YJX6_FXP, 73, 111, 47);
        this.mTvRearSeat = this.mContainer.addText(655, 75, 111, 47);
        this.mTvSyncState = this.mContainer.addText(455, 183, 113, 44);
        this.mTvWindState = this.mContainer.addText(372, 280);
        this.mContainer.setTextStyle(this.mTvLeftTemp, 0, 17, -1, 30).setTextStyle(this.mTvRightTemp, 0, 17, -1, 30).setTextStyle2(this.mTvACToggle, R.string.can_gl8_2017_close, 17, 14).setColorUpDnSelList(this.mTvACToggle, -1, Color.parseColor("#FFCC00")).setTextStyle2(this.mTvAuto, R.string.can_gl8_2017_auto, 17, 20).setColorUpDnSelList(this.mTvAuto, -1, Color.parseColor("#FFCC00")).setIdTouchListener(this.mTvAuto, 12, this).setTextStyle2(this.mTvRearSeat, R.string.can_gl8_2017_rear_seat, 17, 20).setColorUpDnList(this.mTvRearSeat, -1, Color.parseColor("#FFCC00")).setIdClickListener(this.mTvRearSeat, 13, this).setTextStyle2(this.mTvSyncState, R.string.can_gl8_2017_sync_already, 17, 18).setColorUpDnSelList(this.mTvSyncState, -1, Color.parseColor("#06ebf9")).setIdTouchListener(this.mTvSyncState, 14, this).setTextStyle(this.mTvWindState, 0, 17, -1, 18);
        this.mIvWindIcon = this.mContainer.addImage(375, KeyDef.RKEY_FR, R.drawable.can_gl18_ac_fan_00);
        this.mBtnAC = this.mContainer.addButton(261, 70);
        this.mBtnLoop = this.mContainer.addButton(389, 67);
        ParamButton windDecrease = this.mContainer.addButton(Can.CAN_TOYOTA_SP_XP, 284);
        ParamButton windIncrease = this.mContainer.addButton(671, 284);
        ParamButton leftIncrease = this.mContainer.addButton(49, 68);
        ParamButton leftDecrease = this.mContainer.addButton(49, KeyDef.RKEY_FR);
        ParamButton rightIncrease = this.mContainer.addButton(858, 68);
        ParamButton rightDecrease = this.mContainer.addButton(858, KeyDef.RKEY_FR);
        this.mBtnHeadMode = this.mContainer.addButton(176, 445);
        this.mBtnHeadFootMode = this.mContainer.addButton(360, 445);
        this.mBtnFootMode = this.mContainer.addButton(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST2, 445);
        this.mBtnFootFrontMode = this.mContainer.addButton(722, 445);
        this.mContainer.setDrawableUpDnSel(this.mBtnAC, R.drawable.can_gl18_ac_icon_ac_up, R.drawable.can_gl18_ac_icon_ac_dn).setIdTouchListener(this.mBtnAC, 10, this).setDrawableUpDnSel(this.mBtnLoop, R.drawable.can_gl18_ac_icon_wxh_dn, R.drawable.can_gl18_ac_icon_nxh_dn).setIdTouchListener(this.mBtnLoop, 11, this).setDrawableUpDnSel(windIncrease, R.drawable.can_gl18_ac_next_up, R.drawable.can_gl18_ac_next_dn).setIdTouchListener(windIncrease, 8, this).setDrawableUpDnSel(windDecrease, R.drawable.can_gl18_ac_prv_up, R.drawable.can_gl18_ac_prv_dn).setIdTouchListener(windDecrease, 9, this).setDrawableUpDnSel(leftIncrease, R.drawable.can_gl18_ac_icon_sh_up, R.drawable.can_gl18_ac_icon_sh_dn).setIdTouchListener(leftIncrease, 0, this).setDrawableUpDnSel(leftDecrease, R.drawable.can_gl18_ac_icon_xia_up, R.drawable.can_gl18_ac_icon_xia_dn).setIdTouchListener(leftDecrease, 1, this).setDrawableUpDnSel(rightIncrease, R.drawable.can_gl18_ac_icon_sh_up, R.drawable.can_gl18_ac_icon_sh_dn).setIdTouchListener(rightIncrease, 2, this).setDrawableUpDnSel(rightDecrease, R.drawable.can_gl18_ac_icon_xia_up, R.drawable.can_gl18_ac_icon_xia_dn).setIdTouchListener(rightDecrease, 3, this).setDrawableUpDnSel(this.mBtnHeadMode, R.drawable.can_gl18_ac_icon01_up, R.drawable.can_gl18_ac_icon01_dn).setIdTouchListener(this.mBtnHeadMode, 4, this).setDrawableUpDnSel(this.mBtnHeadFootMode, R.drawable.can_gl18_ac_icon02_up, R.drawable.can_gl18_ac_icon02_dn).setIdTouchListener(this.mBtnHeadFootMode, 5, this).setDrawableUpDnSel(this.mBtnFootMode, R.drawable.can_gl18_ac_icon03_up, R.drawable.can_gl18_ac_icon03_dn).setIdTouchListener(this.mBtnFootMode, 6, this).setDrawableUpDnSel(this.mBtnFootFrontMode, R.drawable.can_gl18_ac_icon04_up, R.drawable.can_gl18_ac_icon04_dn).setIdTouchListener(this.mBtnFootFrontMode, 7, this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Can.updateAC();
        updateACUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }

    private void updateACUI() {
        this.mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        updateACState(this.mAcInfo.fgAutoAC, this.mAcInfo.fgAC);
        updateTempValue(this.mAcInfo.szLtTemp, this.mAcInfo.szRtTemp);
        updateWindValue(this.mAcInfo.fgAutoWind, this.mAcInfo.nWindValue);
        updateWindMode(this.mAcInfo.fgDFBL, this.mAcInfo.fgParallelWind, this.mAcInfo.fgDownWind, this.mAcInfo.fgUpWind);
        this.mBtnLoop.setSelected(i2b(this.mAcInfo.fgInnerLoop));
        this.mTvAuto.setSelected(i2b(this.mAcInfo.fgAQS));
        this.mTvSyncState.setSelected(i2b(this.mAcInfo.fgDual));
    }

    private void updateWindMode(int fgDFBL, int fgParalle, int fgDown, int fgUp) {
        this.mBtnHeadMode.setSelected(false);
        this.mBtnHeadFootMode.setSelected(false);
        this.mBtnFootMode.setSelected(false);
        this.mBtnFootFrontMode.setSelected(false);
        if (i2b(fgParalle) && i2b(fgDown) && !i2b(fgDFBL) && !i2b(fgUp)) {
            this.mBtnHeadFootMode.setSelected(true);
        } else if (i2b(fgDown) && i2b(fgUp) && !i2b(fgDFBL) && !i2b(fgParalle)) {
            this.mBtnFootFrontMode.setSelected(true);
        } else if (i2b(fgParalle) && !i2b(fgDown) && !i2b(fgDFBL) && !i2b(fgUp)) {
            this.mBtnHeadMode.setSelected(true);
        } else if (i2b(fgDown) && !i2b(fgParalle) && !i2b(fgDFBL) && !i2b(fgUp)) {
            this.mBtnFootMode.setSelected(true);
        }
    }

    private void updateWindValue(int autoWind, int wind) {
        if (i2b(autoWind)) {
            this.mIvWindIcon.setImageResource(R.drawable.can_gl18_ac_fan_dn);
            this.mTvWindState.setText(R.string.can_gl8_2017_auto_wind);
        } else if (wind >= 0 && wind < this.mWindValues.length) {
            this.mIvWindIcon.setImageResource(this.mWindValues[wind]);
            this.mTvWindState.setText(String.valueOf(wind) + getString(R.string.can_gl8_2017_wind_unit));
        }
    }

    private void updateTempValue(String ltTemp, String rtTemp) {
        this.mTvLeftTemp.setText(TextUtils.isEmpty(ltTemp) ? "" : ltTemp.replace("℃", "°"));
        this.mTvRightTemp.setText(TextUtils.isEmpty(rtTemp) ? "" : rtTemp.replace("℃", "°"));
    }

    private void updateACState(int autoAc, int ac) {
        if (i2b(autoAc)) {
            this.mBtnAC.setSelected(true);
            this.mTvACToggle.setSelected(true);
            this.mTvACToggle.setText(R.string.can_gl8_2017_auto);
        } else if (i2b(ac)) {
            this.mBtnAC.setSelected(true);
            this.mTvACToggle.setSelected(true);
            this.mTvACToggle.setText(R.string.can_gl8_2017_open);
        } else {
            this.mBtnAC.setSelected(false);
            this.mTvACToggle.setSelected(false);
            this.mTvACToggle.setText(R.string.can_gl8_2017_close);
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 13:
                enterSubWin(CanSBGL8RearACActivity.class);
                return;
            default:
                return;
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        int action = event.getAction();
        if (action == 0) {
            setViewSelected(v, true);
            this.cmd = 0;
            switch (id) {
                case 0:
                    this.cmd = 2;
                    CanJni.GmSbAcSet(this.cmd, 1);
                    break;
                case 1:
                    this.cmd = 2;
                    CanJni.GmSbAcSet(this.cmd, 2);
                    break;
                case 2:
                    this.cmd = 7;
                    CanJni.GmSbAcSet(this.cmd, 1);
                    break;
                case 3:
                    this.cmd = 7;
                    CanJni.GmSbAcSet(this.cmd, 2);
                    break;
                case 4:
                    this.cmd = 6;
                    CanJni.GmSbAcSet(this.cmd, 5);
                    break;
                case 5:
                    this.cmd = 6;
                    CanJni.GmSbAcSet(this.cmd, 2);
                    break;
                case 6:
                    this.cmd = 6;
                    CanJni.GmSbAcSet(this.cmd, 1);
                    break;
                case 7:
                    this.cmd = 6;
                    CanJni.GmSbAcSet(this.cmd, 3);
                    break;
                case 8:
                    this.cmd = 5;
                    CanJni.GmSbAcSet(this.cmd, 1);
                    break;
                case 9:
                    this.cmd = 5;
                    CanJni.GmSbAcSet(this.cmd, 2);
                    break;
                case 10:
                    this.cmd = 3;
                    CanJni.GmSbAcSet(this.cmd, 1);
                    break;
                case 11:
                    this.cmd = 1;
                    CanJni.GmSbAcSet(this.cmd, 1);
                    break;
                case 12:
                    this.cmd = 4;
                    CanJni.GmSbAcSet(this.cmd, 1);
                    break;
                case 14:
                    this.cmd = 11;
                    CanJni.GmSbAcSet(this.cmd, 1);
                    break;
            }
        } else if (action == 1) {
            setViewSelected(v, false);
            CanJni.GmSbAcSet(this.cmd, 0);
        }
        return true;
    }

    public void UserAll() {
        Can.updateAC();
        if (Can.mACInfo.Update != 0) {
            updateACUI();
        }
        if (GetTickCount() > CanFunc.mLastACTick + ((long) AC_SHOW_TIME) && this.isACJump) {
            finish();
        }
    }
}
