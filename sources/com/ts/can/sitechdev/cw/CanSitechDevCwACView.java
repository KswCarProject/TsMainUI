package com.ts.can.sitechdev.cw;

import android.app.Activity;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.can.CanCameraUI;
import com.ts.can.toyota.dj.CanToyotaDJCarDeviceView;
import com.ts.canview.RelativeLayoutContainer;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanSitechDevCwACView extends CanBaseACView {
    private static final int ITEM_AC = 4;
    private static final int ITEM_AC_MAX = 5;
    private static final int ITEM_AUTO = 12;
    private static final int ITEM_FRONT_HEAT = 7;
    private static final int ITEM_LOOPER = 9;
    private static final int ITEM_OFF = 13;
    private static final int ITEM_PTC = 14;
    private static final int ITEM_REAR_HEAT = 8;
    private static final int ITEM_TEMP_DECREASE = 1;
    private static final int ITEM_TEMP_INCREASE = 0;
    private static final int ITEM_TEMP_VALUE = 10;
    private static final int ITEM_WIND_DECREASE = 3;
    private static final int ITEM_WIND_DIRECTION = 6;
    private static final int ITEM_WIND_INCREASE = 2;
    private static final int ITEM_WIND_VALUE = 11;
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private ParamButton mBtnAc;
    private ParamButton mBtnAcMax;
    private ParamButton mBtnAuto;
    private ParamButton mBtnFrontHeat;
    private ParamButton mBtnLooper;
    private ParamButton mBtnModeHead;
    private ParamButton mBtnOff;
    private ParamButton mBtnPtc;
    private ParamButton mBtnRearHeat;
    private RelativeLayoutContainer mContainer;
    private long mDelayTime;
    private ImageView mIvWindCc;
    private ImageView mIvWindLc;
    private ImageView mIvWindLs;
    private ImageView mIvWindLx;
    private ImageView mIvWindRc;
    private ImageView mIvWindRs;
    private ImageView mIvWindRx;
    private LinearLayout mTempLayout;
    private TextView mTvTemp;
    private LinearLayout mWindLayout;

    public CanSitechDevCwACView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        int action = event.getAction();
        int windIndex = getWindIndex(event);
        int tempIndex = getTempIndex(event);
        if (action == 0) {
            if (id == 11) {
                CanJni.SitechDevCwAcKey(187, windIndex);
            } else if (id == 10) {
                CanJni.SitechDevCwAcKey(186, tempIndex);
            } else {
                SendKey(id, 1);
            }
            this.mDelayTime = 0;
        } else if (action == 1) {
            if (id == 11) {
                CanJni.SitechDevCwAcKey(187, windIndex);
            } else if (id == 10) {
                CanJni.SitechDevCwAcKey(186, tempIndex);
            } else {
                SendKey(id, 0);
            }
            this.mDelayTime = 0;
        } else if (action == 2 && SystemClock.uptimeMillis() - this.mDelayTime >= 150) {
            this.mDelayTime = SystemClock.uptimeMillis();
            if (id == 11) {
                CanJni.SitechDevCwAcKey(187, windIndex);
            } else if (id == 10) {
                CanJni.SitechDevCwAcKey(186, tempIndex);
            }
        }
        return true;
    }

    private void SendKey(int id, int para) {
        switch (id) {
            case 0:
                CanJni.SitechDevCwAcKey(Can.CAN_CHANA_CS75_WC, para);
                return;
            case 1:
                CanJni.SitechDevCwAcKey(161, para);
                return;
            case 2:
                CanJni.SitechDevCwAcKey(162, para);
                return;
            case 3:
                CanJni.SitechDevCwAcKey(163, para);
                return;
            case 4:
                CanJni.SitechDevCwAcKey(164, para);
                return;
            case 5:
                CanJni.SitechDevCwAcKey(165, para);
                return;
            case 6:
                CanJni.SitechDevCwAcKey(166, para);
                return;
            case 7:
                CanJni.SitechDevCwAcKey(167, para);
                return;
            case 8:
                CanJni.SitechDevCwAcKey(168, para);
                return;
            case 9:
                CanJni.SitechDevCwAcKey(169, para);
                return;
            case 12:
                CanJni.SitechDevCwAcKey(174, para);
                return;
            case 13:
                CanJni.SitechDevCwAcKey(173, para);
                return;
            case 14:
                CanJni.SitechDevCwAcKey(170, para);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void QueryData() {
        super.QueryData();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mContainer = new RelativeLayoutContainer(getActivity());
        if (CanJni.GetSubType() == 1) {
            this.mContainer.setBackgroundResource(R.drawable.can_yd_bg);
        } else if (CanJni.GetSubType() == 2) {
            this.mContainer.setBackgroundResource(R.drawable.can_e300_bg);
        } else if (CanJni.GetSubType() == 3) {
            this.mContainer.setBackgroundResource(R.drawable.can_e1_bg);
        } else {
            this.mContainer.setBackgroundResource(R.drawable.can_xt_bg1);
        }
        if (CanJni.GetSubType() == 2) {
            this.mBtnAc = this.mContainer.addButton(165, 30);
            this.mBtnLooper = this.mContainer.addButton(165, 130);
            this.mBtnFrontHeat = this.mContainer.addButton(165, Can.CAN_CC_HF_DJ);
            this.mBtnRearHeat = this.mContainer.addButton(165, KeyDef.RKEY_RDS_TA);
            this.mBtnAuto = this.mContainer.addButton(668, 430);
            this.mBtnPtc = this.mContainer.addButton(CanToyotaDJCarDeviceView.ITEM_PLAY, 430);
            this.mBtnOff = this.mContainer.addButton(868, 430);
            this.mBtnModeHead = this.mContainer.addButton(568, 430);
            this.mBtnAcMax = this.mContainer.addButton(668, 430);
            showGoneView((View) this.mBtnAcMax, false);
        } else if (CanJni.GetSubType() == 3) {
            this.mBtnAc = this.mContainer.addButton(165, 30);
            this.mBtnLooper = this.mContainer.addButton(165, 130);
            this.mBtnFrontHeat = this.mContainer.addButton(165, Can.CAN_CC_HF_DJ);
            this.mBtnRearHeat = this.mContainer.addButton(165, KeyDef.RKEY_RDS_TA);
            this.mBtnAuto = this.mContainer.addButton(668, 430);
            this.mBtnPtc = this.mContainer.addButton(CanToyotaDJCarDeviceView.ITEM_PLAY, 430);
            this.mBtnOff = this.mContainer.addButton(568, 430);
            this.mBtnModeHead = this.mContainer.addButton(165, KeyDef.RKEY_RDS_TA);
            this.mBtnAcMax = this.mContainer.addButton(668, 430);
            showGoneView((View) this.mBtnAcMax, false);
            showGoneView((View) this.mBtnRearHeat, false);
            showGoneView((View) this.mBtnAuto, false);
            showGoneView((View) this.mBtnPtc, false);
        } else {
            this.mBtnAc = this.mContainer.addButton(165, 30);
            this.mBtnLooper = this.mContainer.addButton(165, 130);
            this.mBtnFrontHeat = this.mContainer.addButton(165, Can.CAN_CC_HF_DJ);
            this.mBtnRearHeat = this.mContainer.addButton(165, KeyDef.RKEY_RDS_TA);
            this.mBtnAuto = this.mContainer.addButton(668, 430);
            this.mBtnPtc = this.mContainer.addButton(CanToyotaDJCarDeviceView.ITEM_PLAY, 430);
            this.mBtnOff = this.mContainer.addButton(868, 430);
            this.mBtnModeHead = this.mContainer.addButton(568, 430);
            this.mBtnAcMax = this.mContainer.addButton(668, 430);
            showGoneView((View) this.mBtnAuto, false);
            showGoneView((View) this.mBtnPtc, false);
            showGoneView((View) this.mBtnOff, false);
        }
        ImageView tempPoint = new ImageView(getActivity());
        tempPoint.setImageResource(R.drawable.can_ky3x_point);
        ImageView windPoint = new ImageView(getActivity());
        windPoint.setImageResource(R.drawable.can_ky3x_point);
        this.mTempLayout = new LinearLayout(getActivity());
        this.mTempLayout.setOrientation(1);
        this.mTempLayout.setGravity(80);
        this.mTempLayout.addView(tempPoint);
        this.mWindLayout = new LinearLayout(getActivity());
        this.mWindLayout.setOrientation(0);
        this.mWindLayout.addView(windPoint);
        this.mContainer.addView(this.mTempLayout, 110, 42, 38, 363);
        this.mContainer.addView(this.mWindLayout, 208, 473, KeyDef.RKEY_MEDIA_ZOOM, 38);
        this.mIvWindLs = this.mContainer.addImage(CanCameraUI.BTN_TRUMPCHI_GS4_MODE3, 32, R.drawable.can_ky3x_shine_ls);
        this.mIvWindRs = this.mContainer.addImage(675, 32, R.drawable.can_ky3x_shine_rs);
        this.mIvWindLc = this.mContainer.addImage(281, 203, R.drawable.can_ky3x_shine_lc);
        this.mIvWindCc = this.mContainer.addImage(CanCameraUI.BTN_CHANA_ALSVINV7_MODE3, 191, R.drawable.can_ky3x_shine_cc);
        this.mIvWindRc = this.mContainer.addImage(KeyDef.SKEY_CALLUP_1, 186, R.drawable.can_ky3x_shine_rc);
        this.mIvWindLx = this.mContainer.addImage(279, 275, R.drawable.can_ky3x_shine_lx);
        this.mIvWindRx = this.mContainer.addImage(698, 275, R.drawable.can_ky3x_shine_rx);
        this.mTvTemp = this.mContainer.addText(60, 455, 100, 36);
        this.mTvTemp.setTextColor(-1);
        this.mTvTemp.setTextSize(20.0f);
        this.mTvTemp.setGravity(17);
        this.mContainer.setDrawableUpDnSel(this.mBtnAc, R.drawable.can_ky3x_ac_up, R.drawable.can_ky3x_ac_dn).setDrawableUpDnSel(this.mBtnLooper, R.drawable.can_ky3x_wxh_up, R.drawable.can_ky3x_nxh_dn).setDrawableUpDnSel(this.mBtnFrontHeat, R.drawable.can_ky3x_window_up, R.drawable.can_ky3x_window_dn).setDrawableUpDnSel(this.mBtnRearHeat, R.drawable.can_ky3x_window0_up, R.drawable.can_ky3x_window0_dn).setDrawableUpDnSel(this.mBtnAuto, R.drawable.can_ky3x_auto_up, R.drawable.can_ky3x_auto_dn).setDrawableUpDnSel(this.mBtnPtc, R.drawable.can_ky3x_ptc_up, R.drawable.can_ky3x_ptc_dn).setDrawableUpDnSel(this.mBtnOff, R.drawable.can_ky3x_snow_up, R.drawable.can_ky3x_snow_dn).setDrawableUpDnSel(this.mBtnModeHead, R.drawable.can_ky3x_00_up, R.drawable.can_ky3x_00_dn).setDrawableUpDnSel(this.mBtnAcMax, R.drawable.can_ky3x_acmax_up, R.drawable.can_ky3x_acmax_dn).setIdTouchListener(this.mBtnAc, 4, this).setIdTouchListener(this.mBtnLooper, 9, this).setIdTouchListener(this.mBtnFrontHeat, 7, this).setIdTouchListener(this.mBtnRearHeat, 8, this).setIdTouchListener(this.mBtnModeHead, 6, this).setIdTouchListener(this.mBtnAcMax, 5, this).setIdTouchListener(this.mBtnAuto, 12, this).setIdTouchListener(this.mBtnOff, 13, this).setIdTouchListener(this.mBtnPtc, 14, this).setIdTouchListener(this.mTempLayout, 10, this).setIdTouchListener(this.mWindLayout, 11, this);
        showDownWind(false);
        showFrontWind(false);
        showParalleWind(false);
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        setViewSelected((View) this.mBtnAc, this.mAcInfo.fgAC);
        setViewSelected((View) this.mBtnAuto, this.mAcInfo.nAutoLight);
        setViewSelected((View) this.mBtnPtc, this.mAcInfo.fgPTC);
        setViewSelected((View) this.mBtnOff, this.mAcInfo.PWR);
        if (i2b(this.mAcInfo.fgInnerLoop)) {
            this.mBtnLooper.setDrawable(R.drawable.can_ky3x_nxh_up, R.drawable.can_ky3x_nxh_dn);
        } else {
            this.mBtnLooper.setDrawable(R.drawable.can_ky3x_wxh_up, R.drawable.can_ky3x_wxh_dn);
        }
        setViewSelected((View) this.mBtnFrontHeat, this.mAcInfo.fgMaxFornt);
        setViewSelected((View) this.mBtnRearHeat, this.mAcInfo.fgRearLight);
        setWindMode(this.mAcInfo.fgUpWind, this.mAcInfo.fgParallelWind, this.mAcInfo.fgDownWind);
        if (this.mAcInfo.nLeftTemp == 0) {
            setTempValue(this.mAcInfo.nLeftTemp);
            this.mTvTemp.setText("LO");
        } else if (this.mAcInfo.nLeftTemp == 47) {
            setTempValue(34);
            this.mTvTemp.setText("HI");
        } else {
            setTempValue(this.mAcInfo.nLeftTemp);
            this.mTvTemp.setText(String.valueOf(16.0d + (((double) (this.mAcInfo.nLeftTemp - 1)) * 0.5d)) + "℃");
        }
        setWindValue(this.mAcInfo.nWindValue);
    }

    private int getTempIndex(MotionEvent event) {
        float f = 0.0f;
        if (event.getY() > 0.0f) {
            f = event.getY();
        }
        int eventY = (int) f;
        int scrollY = 340 - eventY <= 0 ? 0 : 340 - eventY;
        Log.d("lh", "scrollY = " + scrollY);
        if (scrollY >= 340) {
            scrollY = 340;
        }
        int tempIndex = scrollY / 10;
        if (scrollY % 10 > 5) {
            tempIndex++;
        }
        Log.d("lh", "tempIndex = " + tempIndex);
        return tempIndex;
    }

    private int getWindIndex(MotionEvent event) {
        float f = 0.0f;
        if (event.getX() > 0.0f) {
            f = event.getX();
        }
        int scrollX = (int) f;
        if (scrollX >= 271) {
            scrollX = 271;
        }
        Log.d("lh", "scrollX = " + scrollX);
        int windIndex = scrollX / 38;
        if (scrollX % 38 > 19) {
            windIndex++;
        }
        Log.d("lh", "windIndex = " + windIndex);
        return windIndex;
    }

    private void setTempValue(int tempValue) {
        if (tempValue > 0) {
            this.mTempLayout.scrollTo(0, (int) (9.5d * ((double) tempValue)));
        } else {
            this.mTempLayout.scrollTo(0, 0);
        }
    }

    private void setWindValue(int windValue) {
        if (windValue >= 0) {
            this.mWindLayout.scrollTo(-(windValue * 38), 0);
        }
    }

    private void setWindMode(int fgDFBL, int fgParalle, int fgDown) {
        if (i2b(fgParalle) && i2b(fgDown) && !i2b(fgDFBL)) {
            showParalleWind(true);
            showDownWind(true);
            showFrontWind(false);
            this.mBtnModeHead.setDrawable(R.drawable.can_ky3x_02_up, R.drawable.can_ky3x_02_dn);
        } else if (i2b(fgDown) && i2b(fgDFBL) && !i2b(fgParalle)) {
            showFrontWind(true);
            showDownWind(true);
            showParalleWind(false);
            this.mBtnModeHead.setDrawable(R.drawable.can_ky3x_04_up, R.drawable.can_ky3x_04_dn);
        } else if (i2b(fgParalle) && !i2b(fgDown) && !i2b(fgDFBL)) {
            showParalleWind(true);
            showFrontWind(false);
            showDownWind(false);
            this.mBtnModeHead.setDrawable(R.drawable.can_ky3x_01_up, R.drawable.can_ky3x_01_dn);
        } else if (i2b(fgDown) && !i2b(fgParalle) && !i2b(fgDFBL)) {
            showDownWind(true);
            showFrontWind(false);
            showParalleWind(false);
            this.mBtnModeHead.setDrawable(R.drawable.can_ky3x_03_up, R.drawable.can_ky3x_03_dn);
        } else if (!i2b(fgDown) && !i2b(fgParalle) && i2b(fgDFBL)) {
            showFrontWind(true);
            showParalleWind(false);
            showDownWind(false);
            this.mBtnModeHead.setDrawable(R.drawable.can_ky3x_05_up, R.drawable.can_ky3x_05_dn);
        } else if (!i2b(fgDown) && !i2b(fgParalle) && !i2b(fgDFBL)) {
            showFrontWind(false);
            showParalleWind(false);
            showDownWind(false);
            this.mBtnModeHead.setDrawable(R.drawable.can_ky3x_00_up, R.drawable.can_ky3x_00_dn);
        }
    }

    private void showFrontWind(boolean show) {
        showGoneView((View) this.mIvWindLs, show);
        showGoneView((View) this.mIvWindRs, show);
    }

    private void showParalleWind(boolean show) {
        showGoneView((View) this.mIvWindLc, show);
        showGoneView((View) this.mIvWindCc, show);
        showGoneView((View) this.mIvWindRc, show);
    }

    private void showDownWind(boolean show) {
        showGoneView((View) this.mIvWindLx, show);
        showGoneView((View) this.mIvWindRx, show);
    }

    public void showHideView(View view, int show) {
        showHideView(view, i2b(show));
    }

    public void showGoneView(View view, int show) {
        showGoneView(view, i2b(show));
    }

    public void showHideView(View view, boolean show) {
        view.setVisibility(show ? 0 : 4);
    }

    public void showGoneView(View view, boolean show) {
        view.setVisibility(show ? 0 : 8);
    }

    public void setViewSelected(View view, boolean selected) {
        view.setSelected(selected);
    }

    public void setViewSelected(View view, int selected) {
        view.setSelected(i2b(selected));
    }
}
