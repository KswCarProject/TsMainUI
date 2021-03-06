package com.ts.can.gm.rzc;

import android.app.Activity;
import android.graphics.Color;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACActivity;
import com.ts.can.CanBaseACView;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.canview.RelativeLayoutContainer;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZResourceManager;

public class CanGL8RearACView extends CanBaseACView {
    private static final int ITEM_BACK_FRONT = 0;
    private static final int ITEM_FOOT_MODE = 7;
    private static final int ITEM_HEAD_FOOT_MODE = 6;
    private static final int ITEM_HEAD_MODE = 5;
    private static final int ITEM_REAR_AUTO = 8;
    private static final int ITEM_REAR_LOCK = 9;
    private static final int ITEM_REAR_SYNC = 10;
    private static final int ITEM_TEMP_DECREASE = 4;
    private static final int ITEM_TEMP_INCREASE = 3;
    private static final int ITEM_WIND_DECREASE = 2;
    private static final int ITEM_WIND_INCREASE = 1;
    protected static DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    private CanDataInfo.GM_ACSet mAcSet = new CanDataInfo.GM_ACSet();
    private CanDataInfo.GM_ACSet mAcSetB = new CanDataInfo.GM_ACSet();
    private ParamButton mBtnFootMode;
    private ParamButton mBtnHeadFootMode;
    private ParamButton mBtnHeadMode;
    private ParamButton mBtnRearLock;
    private ParamButton mBtnRearSync;
    private RelativeLayoutContainer mContainer;
    private ImageView mIvWindIcon;
    private TextView mTvRearSeatState;
    private TextView mTvTemp;
    private TextView mTvWindValue;
    private int[] mWindValues = {R.drawable.can_gl18_ac_fan_00, R.drawable.can_gl18_ac_fan_01, R.drawable.can_gl18_ac_fan_02, R.drawable.can_gl18_ac_fan_03, R.drawable.can_gl18_ac_fan_04, R.drawable.can_gl18_ac_fan_05, R.drawable.can_gl18_ac_fan_06};

    public CanGL8RearACView(Activity activity) {
        super(activity);
        CanFunc.mLastACTick = CanFunc.getTickCount();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mContainer = new RelativeLayoutContainer(getActivity());
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        Log.d("nyw", String.format("%d,%d", new Object[]{Integer.valueOf(mDisplayMetrics.widthPixels), Integer.valueOf(mDisplayMetrics.heightPixels)}));
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mContainer.getContainer().getLayoutParams();
        lp.width = 1024;
        lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
        lp.gravity = 17;
        this.mContainer.getContainer().setLayoutParams(lp);
        this.mContainer.setBackgroundResource(R.drawable.can_gl18_ac_bg02);
        TextView frontSeat = this.mContainer.addText(80, 173, 40, 86);
        this.mTvRearSeatState = this.mContainer.addText(CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, 74, 221, 46);
        this.mTvWindValue = this.mContainer.addText(372, 165);
        TextView rearSeat = this.mContainer.addText(858, 21, 110, 46);
        this.mTvTemp = this.mContainer.addText(862, Can.CAN_BJ20_WC, 110, 75);
        this.mContainer.setTextStyle(frontSeat, R.string.can_gl8_2017_front_seat, 17, -1, 20).setTextStyle2(this.mTvRearSeatState, R.string.can_gl8_2017_rear_seat_auto, 17, 20).setColorUpDnSelList(this.mTvRearSeatState, -1, Color.parseColor("#06ebf9")).setIdTouchListener(this.mTvRearSeatState, 8, this).setTextStyle(this.mTvWindValue, 0, 17, -1, 18).setTextStyle(rearSeat, R.string.can_gl8_2017_rear_seat, 17, -1, 20).setTextStyle(this.mTvTemp, 0, 17, -1, 30);
        this.mIvWindIcon = this.mContainer.addImage(375, 178, R.drawable.can_gl18_ac_fan_00);
        ParamButton backFront = this.mContainer.addButton(10, 173);
        ParamButton windDecrease = this.mContainer.addButton(Can.CAN_TOYOTA_SP_XP, 168);
        ParamButton windIncrease = this.mContainer.addButton(671, 168);
        ParamButton tempIncrease = this.mContainer.addButton(858, 68);
        ParamButton tempDecrease = this.mContainer.addButton(858, 294);
        this.mBtnHeadMode = this.mContainer.addButton(266, 445);
        this.mBtnHeadFootMode = this.mContainer.addButton(450, 445);
        this.mBtnFootMode = this.mContainer.addButton(CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE2, 445);
        this.mBtnRearLock = this.mContainer.addButton(82, 445);
        this.mBtnRearSync = this.mContainer.addButton(812, 445);
        this.mContainer.setDrawableUpDn(backFront, R.drawable.can_gl18_ac_qz_up, R.drawable.can_gl18_ac_qz_dn).setIdClickListener(backFront, 0, this).setDrawableUpDn(windIncrease, R.drawable.can_gl18_ac_next_up, R.drawable.can_gl18_ac_next_dn).setIdTouchListener(windIncrease, 1, this).setDrawableUpDn(windDecrease, R.drawable.can_gl18_ac_prv_up, R.drawable.can_gl18_ac_prv_dn).setIdTouchListener(windDecrease, 2, this).setDrawableUpDn(tempIncrease, R.drawable.can_gl18_ac_icon_sh_up, R.drawable.can_gl18_ac_icon_sh_dn).setIdTouchListener(tempIncrease, 3, this).setDrawableUpDn(tempDecrease, R.drawable.can_gl18_ac_icon_xia_up, R.drawable.can_gl18_ac_icon_xia_dn).setIdTouchListener(tempDecrease, 4, this).setDrawableUpDnSel(this.mBtnHeadMode, R.drawable.can_gl18_ac_icon01_up, R.drawable.can_gl18_ac_icon01_dn).setIdTouchListener(this.mBtnHeadMode, 5, this).setDrawableUpDnSel(this.mBtnHeadFootMode, R.drawable.can_gl18_ac_icon02_up, R.drawable.can_gl18_ac_icon02_dn).setIdTouchListener(this.mBtnHeadFootMode, 6, this).setDrawableUpDnSel(this.mBtnFootMode, R.drawable.can_gl18_ac_icon03_up, R.drawable.can_gl18_ac_icon03_dn).setIdTouchListener(this.mBtnFootMode, 7, this).setDrawableUpDnSel(this.mBtnRearLock, R.drawable.can_gl18_ac_icon_rear_up, R.drawable.can_gl18_ac_icon_rear_dn).setIdTouchListener(this.mBtnRearLock, 9, this).setDrawableUpDnSel(this.mBtnRearSync, R.drawable.can_gl18_ac_icon_sync_up, R.drawable.can_gl18_ac_icon_sync_dn).setIdTouchListener(this.mBtnRearSync, 10, this);
        this.mContainer.getContainer().setScaleX((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f);
        this.mContainer.getContainer().setScaleY((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f);
        Log.d("nyw", String.format("%.2f,%.2f", new Object[]{Float.valueOf((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f), Float.valueOf((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f)}));
    }

    public void ResetData(boolean check) {
        CanJni.GMGetACSet(this.mAcSet);
        if (!i2b(this.mAcSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAcSet.Update)) {
            if (!(this.mAcSetB.HQWD == this.mAcSet.HQWD && this.mAcSetB.HQFS == this.mAcSet.HQFS && this.mAcSetB.SFMS == this.mAcSet.SFMS)) {
                this.mAcSetB.HQWD = this.mAcSet.HQWD;
                this.mAcSetB.HQFS = this.mAcSet.HQFS;
                this.mAcSetB.SFMS = this.mAcSet.SFMS;
                CanFunc.mLastACTick = GetTickCount();
            }
            this.mAcSet.Update = 0;
            updateTempValue(this.mAcSet.HQWD);
            updateWindValue(this.mAcSet.HQFS);
            updateWindMode(this.mAcSet.SFMS);
            this.mBtnRearLock.SetSel(this.mAcSet.Hqktsd);
            this.mBtnRearSync.SetSel(this.mAcSet.Hqktyqqkttb);
            if (this.mAcSet.HQFS == 15 && this.mAcSet.SFMS == 1) {
                this.mTvRearSeatState.setSelected(true);
            } else {
                this.mTvRearSeatState.setSelected(false);
            }
        }
    }

    public static long GetTickCount() {
        return SystemClock.uptimeMillis();
    }

    public void QueryData() {
    }

    private void updateTempValue(int temp) {
        if (temp == 0) {
            this.mTvTemp.setText("LO");
        } else if (temp == 129) {
            this.mTvTemp.setText("HI");
        } else if (temp <= 0 || temp >= 129) {
            this.mTvTemp.setText(TXZResourceManager.STYLE_DEFAULT);
        } else {
            this.mTvTemp.setText(String.format("%.1f°", new Object[]{Float.valueOf(((float) temp) / 2.0f)}));
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
            case 2:
                this.mBtnHeadMode.setSelected(true);
                return;
            case 3:
                this.mBtnHeadFootMode.setSelected(true);
                return;
            case 4:
                this.mBtnFootMode.setSelected(true);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanFunc.showCanActivity(CanBaseACActivity.class);
                return;
            default:
                return;
        }
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        int id = ((Integer) arg0.getTag()).intValue();
        if (arg1.getAction() == 0) {
            switch (id) {
                case 1:
                    CanJni.GMACCtrl(7, 99);
                    break;
                case 2:
                    CanJni.GMACCtrl(7, 100);
                    break;
                case 3:
                    CanJni.GMACCtrl(7, 97);
                    break;
                case 4:
                    CanJni.GMACCtrl(7, 98);
                    break;
                case 5:
                    CanJni.GMACCtrl(7, 101);
                    break;
                case 6:
                    CanJni.GMACCtrl(7, 102);
                    break;
                case 7:
                    CanJni.GMACCtrl(7, 103);
                    break;
                case 8:
                    CanJni.GMACCtrl(7, 104);
                    Sleep(50);
                    CanJni.GMACCtrl(7, 0);
                    break;
                case 9:
                    CanJni.GMACCtrl(7, 105);
                    break;
                case 10:
                    CanJni.GMACCtrl(7, 106);
                    break;
            }
        } else if (arg1.getAction() == 1) {
            CanJni.GMACCtrl(7, 0);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void InitData() {
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
    }
}
