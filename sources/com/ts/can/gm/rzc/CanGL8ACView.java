package com.ts.can.gm.rzc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseACView;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.canview.RelativeLayoutContainer;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZResourceManager;
import com.txznet.sdk.music.MusicInvokeConstants;
import com.yyw.ts70xhw.KeyDef;

public class CanGL8ACView extends CanBaseACView {
    private static final int ITEM_AC = 10;
    private static final int ITEM_AUTO = 12;
    private static final int ITEM_ENTER_REAR_AC = 13;
    private static final int ITEM_FOOT_FRONT_MODE = 7;
    private static final int ITEM_FOOT_MODE = 6;
    private static final int ITEM_FRONT_MODE = 16;
    private static final int ITEM_FRONT_WIND = 18;
    private static final int ITEM_HEAD_FOOT_MODE = 5;
    private static final int ITEM_HEAD_MODE = 4;
    private static final int ITEM_LEFT_DECREASE = 1;
    private static final int ITEM_LEFT_INCREASE = 0;
    private static final int ITEM_LOOP = 11;
    private static final int ITEM_LT_FIRE = 17;
    private static final int ITEM_REAR_WIND = 19;
    private static final int ITEM_RIGHT_DECREASE = 3;
    private static final int ITEM_RIGHT_INCREASE = 2;
    private static final int ITEM_RT_FIRE = 20;
    private static final int ITEM_SET = 15;
    private static final int ITEM_TEMP_SYNC = 14;
    private static final int ITEM_WIND_DECREASE = 9;
    private static final int ITEM_WIND_INCREASE = 8;
    protected static DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    private CanDataInfo.CAN_ACInfo mAcInfo;
    private ParamButton mBtnAC;
    private ParamButton mBtnFootFrontMode;
    private ParamButton mBtnFootMode;
    private ParamButton mBtnFrontMode;
    private ParamButton mBtnFrontWin;
    private ParamButton mBtnHeadFootMode;
    private ParamButton mBtnHeadMode;
    private ParamButton mBtnLoop;
    private ParamButton mBtnLtFire;
    private ParamButton mBtnRearWin;
    private ParamButton mBtnRtFire;
    private ParamButton mBtnSet;
    private RelativeLayoutContainer mContainer;
    private boolean mIsShow = false;
    private ImageView[] mIvLtFire;
    private int[] mIvLtFireIds;
    private ImageView[] mIvRtFire;
    private int[] mIvRtFireIds;
    private ImageView mIvWindIcon;
    private RelativeLayout mRelativeLayout;
    private SharedPreferences mSharedPreferences;
    private TextView mTvACToggle;
    private TextView mTvAuto;
    private TextView mTvAutoMode;
    private TextView mTvLeftTemp;
    private TextView mTvRearSeat;
    private TextView mTvRightTemp;
    private TextView mTvSyncState;
    private TextView mTvWindState;
    private int[] mWindValues = {R.drawable.can_gl18_ac_fan_00, R.drawable.can_gl18_ac_fan_001, R.drawable.can_gl18_ac_fan_002, R.drawable.can_gl18_ac_fan_003, R.drawable.can_gl18_ac_fan_004, R.drawable.can_gl18_ac_fan_005, R.drawable.can_gl18_ac_fan_006, R.drawable.can_gl18_ac_fan_007, R.drawable.can_gl18_ac_fan_008};

    public CanGL8ACView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void updateACUI() {
        this.mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        updateACState(this.mAcInfo.fgAutoAC, this.mAcInfo.fgAC);
        updateTempValue(this.mAcInfo.szLtTemp, this.mAcInfo.szRtTemp);
        updateWindValue(this.mAcInfo.fgAutoWind, this.mAcInfo.nWindValue);
        updateWindMode(this.mAcInfo.fgDFBL, this.mAcInfo.fgParallelWind, this.mAcInfo.fgDownWind, this.mAcInfo.fgUpWind);
        if (this.mAcInfo.nAutoLight == 1) {
            this.mTvAutoMode.setVisibility(0);
        } else {
            this.mTvAutoMode.setVisibility(8);
        }
        this.mBtnLoop.setSelected(i2b(this.mAcInfo.fgInnerLoop));
        this.mTvAuto.setSelected(i2b(this.mAcInfo.fgAQS));
        this.mTvSyncState.setSelected(i2b(this.mAcInfo.fgDual));
        int ltHot = this.mAcInfo.nLtChairHot & 3;
        int rtHot = this.mAcInfo.nRtChairHot & 3;
        Log.d("lh", "ltHot = " + ltHot);
        Log.d("lh", "rtHot = " + rtHot);
        updateLtImages(ltHot);
        updateRtImages(rtHot);
        this.mBtnRearWin.setSelected(i2b(this.mAcInfo.fgRearLight));
        this.mBtnFrontWin.setSelected(i2b(this.mAcInfo.fgDFBL));
    }

    private void updateWindMode(int fgDFBL, int fgParalle, int fgDown, int fgUp) {
        this.mBtnHeadMode.setSelected(false);
        this.mBtnHeadFootMode.setSelected(false);
        this.mBtnFootMode.setSelected(false);
        this.mBtnFootFrontMode.setSelected(false);
        this.mBtnFrontMode.setSelected(false);
        if (i2b(fgParalle) && i2b(fgDown) && !i2b(fgDFBL) && !i2b(fgUp)) {
            this.mBtnHeadFootMode.setSelected(true);
        } else if (i2b(fgDown) && i2b(fgUp) && !i2b(fgDFBL) && !i2b(fgParalle)) {
            this.mBtnFootFrontMode.setSelected(true);
        } else if (i2b(fgParalle) && !i2b(fgDown) && !i2b(fgDFBL) && !i2b(fgUp)) {
            this.mBtnHeadMode.setSelected(true);
        } else if (i2b(fgDown) && !i2b(fgParalle) && !i2b(fgDFBL) && !i2b(fgUp)) {
            this.mBtnFootMode.setSelected(true);
        } else if (i2b(fgUp) && !i2b(fgDown) && !i2b(fgParalle) && !i2b(fgDFBL)) {
            this.mBtnFrontMode.setSelected(true);
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
        this.mTvLeftTemp.setText(TextUtils.isEmpty(ltTemp) ? TXZResourceManager.STYLE_DEFAULT : ltTemp.replace("℃", "°"));
        this.mTvRightTemp.setText(TextUtils.isEmpty(rtTemp) ? TXZResourceManager.STYLE_DEFAULT : rtTemp.replace("℃", "°"));
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
                enterSubWin(CanCarInfoSub1Activity.class, -1);
                return;
            case 15:
                if (this.mRelativeLayout.getVisibility() == 0) {
                    this.mRelativeLayout.setVisibility(8);
                    return;
                } else {
                    this.mRelativeLayout.setVisibility(0);
                    return;
                }
            default:
                return;
        }
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        int id = ((Integer) arg0.getTag()).intValue();
        if (arg1.getAction() == 0) {
            switch (id) {
                case 0:
                    CanJni.GMACCtrl(7, 4);
                    break;
                case 1:
                    CanJni.GMACCtrl(7, 5);
                    break;
                case 2:
                    CanJni.GMACCtrl(7, 20);
                    break;
                case 3:
                    CanJni.GMACCtrl(7, 21);
                    break;
                case 4:
                    CanJni.GMACCtrl(7, 8);
                    break;
                case 5:
                    CanJni.GMACCtrl(7, 9);
                    break;
                case 6:
                    CanJni.GMACCtrl(7, 11);
                    break;
                case 7:
                    CanJni.GMACCtrl(7, 10);
                    break;
                case 8:
                    CanJni.GMACCtrl(7, 6);
                    break;
                case 9:
                    CanJni.GMACCtrl(7, 7);
                    break;
                case 10:
                    CanJni.GMACCtrl(7, 1);
                    break;
                case 11:
                    CanJni.GMACCtrl(7, 3);
                    break;
                case 12:
                    CanJni.GMACCtrl(7, 2);
                    Sleep(50);
                    CanJni.GMACCtrl(7, 0);
                    break;
                case 14:
                    CanJni.GMACCtrl(7, 13);
                    Sleep(50);
                    CanJni.GMACCtrl(7, 0);
                    break;
                case 16:
                    CanJni.GMACCtrl(7, 28);
                    break;
                case 17:
                    CanJni.GMACCtrl(7, 22);
                    setShowView(true);
                    break;
                case 18:
                    CanJni.GMACCtrl(7, 12);
                    setShowView(true);
                    break;
                case 19:
                    CanJni.GMACCtrl(7, 27);
                    setShowView(true);
                    break;
                case 20:
                    CanJni.GMACCtrl(7, 24);
                    setShowView(true);
                    break;
            }
        } else if (arg1.getAction() == 1) {
            CanJni.GMACCtrl(7, 0);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean getShowView() {
        if (this.mSharedPreferences == null) {
            this.mSharedPreferences = getActivity().getSharedPreferences("ac_show", 0);
        }
        return this.mSharedPreferences.getBoolean(MusicInvokeConstants.PUSH_SHOW_VIEW, false);
    }

    /* access modifiers changed from: package-private */
    public void setShowView(boolean show) {
        if (this.mSharedPreferences == null) {
            this.mSharedPreferences = getActivity().getSharedPreferences("ac_show", 0);
        }
        SharedPreferences.Editor editor = this.mSharedPreferences.edit();
        editor.putBoolean(MusicInvokeConstants.PUSH_SHOW_VIEW, show);
        editor.commit();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mContainer = new RelativeLayoutContainer(getActivity());
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        Log.d("nyw", String.format("%d,%d", new Object[]{Integer.valueOf(mDisplayMetrics.widthPixels), Integer.valueOf(mDisplayMetrics.heightPixels)}));
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mContainer.getContainer().getLayoutParams();
        lp.width = 1024;
        lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
        lp.gravity = 17;
        this.mContainer.getContainer().setLayoutParams(lp);
        this.mContainer.setBackgroundResource(R.drawable.can_gl18_ac_bg01);
        this.mTvLeftTemp = this.mContainer.addText(49, Can.CAN_BJ20_WC, 110, 75);
        this.mTvRightTemp = this.mContainer.addText(862, Can.CAN_BJ20_WC, 110, 75);
        this.mTvACToggle = this.mContainer.addText(290, 115, 50, 35);
        this.mTvAuto = this.mContainer.addText(CanCameraUI.BTN_GEELY_YJX6_FXP, 73, 111, 47);
        this.mTvRearSeat = this.mContainer.addText(655, 75, 111, 47);
        this.mTvSyncState = this.mContainer.addText(455, 183, 113, 44);
        this.mTvWindState = this.mContainer.addText(372, 280);
        this.mTvAutoMode = this.mContainer.addText(438, 415, 147, -2);
        this.mTvAutoMode.setText("AUTO");
        this.mTvAutoMode.setTextColor(-1);
        this.mTvAutoMode.setTextSize(22.0f);
        this.mTvAutoMode.setGravity(17);
        this.mTvAutoMode.setVisibility(8);
        this.mContainer.setTextStyle(this.mTvLeftTemp, 0, 17, -1, 30).setTextStyle(this.mTvRightTemp, 0, 17, -1, 30).setTextStyle2(this.mTvACToggle, R.string.can_gl8_2017_close, 17, 14).setColorUpSelList(this.mTvACToggle, -1, Color.parseColor("#FFCC00")).setTextStyle2(this.mTvAuto, R.string.can_gl8_2017_auto, 17, 20).setColorUpDnSelList(this.mTvAuto, -1, Color.parseColor("#FFCC00")).setIdTouchListener(this.mTvAuto, 12, this).setTextStyle2(this.mTvRearSeat, R.string.can_gl8_2017_rear_seat, 17, 20).setColorUpDnList(this.mTvRearSeat, -1, Color.parseColor("#FFCC00")).setIdClickListener(this.mTvRearSeat, 13, this).setTextStyle2(this.mTvSyncState, R.string.can_gl8_2017_sync_already, 17, 18).setColorUpDnSelList(this.mTvSyncState, -1, Color.parseColor("#06ebf9")).setIdTouchListener(this.mTvSyncState, 14, this).setTextStyle(this.mTvWindState, 0, 17, -1, 18);
        this.mIvWindIcon = this.mContainer.addImage(375, 294, R.drawable.can_gl18_ac_fan_00);
        this.mBtnAC = this.mContainer.addButton(261, 70);
        this.mBtnLoop = this.mContainer.addButton(389, 67);
        ParamButton windDecrease = this.mContainer.addButton(Can.CAN_TOYOTA_SP_XP, 284);
        ParamButton windIncrease = this.mContainer.addButton(671, 284);
        ParamButton leftIncrease = this.mContainer.addButton(49, 68);
        ParamButton leftDecrease = this.mContainer.addButton(49, 294);
        ParamButton rightIncrease = this.mContainer.addButton(858, 68);
        ParamButton rightDecrease = this.mContainer.addButton(858, 294);
        if (CanJni.GetSubType() == 3) {
            this.mBtnSet = this.mContainer.addButton(35, 445, 113, 100);
            this.mBtnHeadMode = this.mContainer.addButton(KeyDef.RKEY_EJECT, 445);
            this.mBtnFootMode = this.mContainer.addButton(595, 445);
            this.mBtnFrontMode = this.mContainer.addButton(875, 436);
            this.mBtnHeadFootMode = this.mContainer.addButton(360, 445, 0, 0);
            this.mBtnFootFrontMode = this.mContainer.addButton(722, 445, 0, 0);
        } else {
            this.mBtnSet = this.mContainer.addButton(21, 445, 113, 100);
            this.mBtnHeadMode = this.mContainer.addButton(176, 445);
            this.mBtnFootMode = this.mContainer.addButton(CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST2, 445);
            this.mBtnFrontMode = this.mContainer.addButton(883, 436);
            this.mBtnHeadFootMode = this.mContainer.addButton(360, 445);
            this.mBtnFootFrontMode = this.mContainer.addButton(722, 445);
        }
        this.mContainer.setDrawableUpDnSel(this.mBtnAC, R.drawable.can_gl18_ac_icon_ac_up, R.drawable.can_gl18_ac_icon_ac_dn).setIdTouchListener(this.mBtnAC, 10, this).setDrawableUpDnSel(this.mBtnLoop, R.drawable.can_gl18_ac_icon_wxh_dn, R.drawable.can_gl18_ac_icon_nxh_dn).setIdTouchListener(this.mBtnLoop, 11, this).setDrawableUpDn(windIncrease, R.drawable.can_gl18_ac_next_up, R.drawable.can_gl18_ac_next_dn).setIdTouchListener(windIncrease, 8, this).setDrawableUpDn(windDecrease, R.drawable.can_gl18_ac_prv_up, R.drawable.can_gl18_ac_prv_dn).setIdTouchListener(windDecrease, 9, this).setDrawableUpDn(leftIncrease, R.drawable.can_gl18_ac_icon_sh_up, R.drawable.can_gl18_ac_icon_sh_dn).setIdTouchListener(leftIncrease, 0, this).setDrawableUpDn(leftDecrease, R.drawable.can_gl18_ac_icon_xia_up, R.drawable.can_gl18_ac_icon_xia_dn).setIdTouchListener(leftDecrease, 1, this).setDrawableUpDn(rightIncrease, R.drawable.can_gl18_ac_icon_sh_up, R.drawable.can_gl18_ac_icon_sh_dn).setIdTouchListener(rightIncrease, 2, this).setDrawableUpDn(rightDecrease, R.drawable.can_gl18_ac_icon_xia_up, R.drawable.can_gl18_ac_icon_xia_dn).setIdTouchListener(rightDecrease, 3, this).setDrawableUpDnSel(this.mBtnHeadMode, R.drawable.can_gl18_ac_icon01_up, R.drawable.can_gl18_ac_icon01_dn).setIdTouchListener(this.mBtnHeadMode, 4, this).setDrawableUpDnSel(this.mBtnHeadFootMode, R.drawable.can_gl18_ac_icon02_up, R.drawable.can_gl18_ac_icon02_dn).setIdTouchListener(this.mBtnHeadFootMode, 5, this).setDrawableUpDnSel(this.mBtnFootMode, R.drawable.can_gl18_ac_icon03_up, R.drawable.can_gl18_ac_icon03_dn).setIdTouchListener(this.mBtnFootMode, 6, this).setDrawableUpDnSel(this.mBtnFootFrontMode, R.drawable.can_gl18_ac_icon04_up, R.drawable.can_gl18_ac_icon04_dn).setIdTouchListener(this.mBtnFootFrontMode, 7, this).setDrawableUpDnSel(this.mBtnFrontMode, R.drawable.can_gl18_ac_icon06_up, R.drawable.can_gl18_ac_icon06_dn).setIdTouchListener(this.mBtnFrontMode, 16, this).setDrawableUpDnSel(this.mBtnSet, R.drawable.can_gl18_ac_set_up, R.drawable.can_gl18_ac_set_dn).setIdClickListener(this.mBtnSet, 15, this);
        this.mRelativeLayout = new RelativeLayout(getActivity());
        this.mContainer.addView(this.mRelativeLayout, 29, KeyDef.RKEY_POWER_ON, KeyDef.RKEY_CMMB_PBC, 86);
        this.mRelativeLayout.setBackgroundResource(R.drawable.can_gl18_ac_popup);
        this.mBtnLtFire = addButton(11, 8);
        this.mBtnFrontWin = addButton(88, 8);
        this.mBtnRearWin = addButton(164, 8);
        this.mBtnRtFire = addButton(Can.CAN_GM_CAPTIVA_OD, 8);
        setDrawableUpSel(this.mBtnLtFire, R.drawable.can_gl18_ac_lchair_up, R.drawable.can_gl18_ac_lchair_dn);
        setDrawableUpSel(this.mBtnFrontWin, R.drawable.can_gl18_ac_qcs_up, R.drawable.can_gl18_ac_qcs_dn);
        setDrawableUpSel(this.mBtnRearWin, R.drawable.can_gl18_ac_hcs_up, R.drawable.can_gl18_ac_hcs_dn);
        setDrawableUpSel(this.mBtnRtFire, R.drawable.can_gl18_ac_rchair_up, R.drawable.can_gl18_ac_rchair_dn);
        this.mBtnLtFire.setTag(17);
        this.mBtnLtFire.setOnTouchListener(this);
        this.mBtnFrontWin.setTag(18);
        this.mBtnFrontWin.setOnTouchListener(this);
        this.mBtnRearWin.setTag(19);
        this.mBtnRearWin.setOnTouchListener(this);
        this.mBtnRtFire.setTag(20);
        this.mBtnRtFire.setOnTouchListener(this);
        this.mIvLtFire = new ImageView[3];
        this.mIvRtFire = new ImageView[3];
        this.mIvLtFireIds = new int[]{29, 37, 44};
        this.mIvRtFireIds = new int[]{266, 273, 280};
        for (int i = 0; i < this.mIvLtFire.length; i++) {
            this.mIvLtFire[i] = addImage(this.mIvLtFireIds[i], 36, R.drawable.can_gl18_ac_chair_jt);
        }
        for (int i2 = 0; i2 < this.mIvRtFire.length; i2++) {
            this.mIvRtFire[i2] = addImage(this.mIvRtFireIds[i2], 36, R.drawable.can_gl18_ac_chair_jt);
        }
        this.mRelativeLayout.setVisibility(8);
        this.mContainer.getContainer().setScaleX((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f);
        this.mContainer.getContainer().setScaleY((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f);
        Log.d("nyw", String.format("%.2f,%.2f", new Object[]{Float.valueOf((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f), Float.valueOf((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f)}));
    }

    /* access modifiers changed from: package-private */
    public void updateLtImages(int value) {
        for (int i = 0; i < this.mIvRtFire.length; i++) {
            this.mIvLtFire[i].setVisibility(8);
        }
        for (int i2 = 0; i2 < value; i2++) {
            this.mIvLtFire[i2].setVisibility(0);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateRtImages(int value) {
        for (ImageView visibility : this.mIvRtFire) {
            visibility.setVisibility(8);
        }
        for (int i = 0; i < value; i++) {
            this.mIvRtFire[i].setVisibility(0);
        }
    }

    public void AddView(View view, int x, int y, int w, int h) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(w, h);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        this.mRelativeLayout.addView(view);
    }

    public CustomImgView AddImage(int x, int y, int w, int h) {
        CustomImgView img = new CustomImgView(getActivity());
        AddView(img, x, y, w, h);
        return img;
    }

    public CustomImgView addImage(int x, int y, int w, int h, int resId, boolean isBackground) {
        CustomImgView v = AddImage(x, y, w, h);
        if (resId != 0) {
            if (isBackground) {
                v.setBackgroundResource(resId);
            } else {
                v.setImageResource(resId);
            }
        }
        return v;
    }

    public CustomImgView addImage(int x, int y, int w, int h, int resId) {
        return addImage(x, y, w, h, resId, false);
    }

    public CustomImgView addImage(int x, int y, int w, int h) {
        return addImage(x, y, w, h, 0);
    }

    public CustomImgView addImage(int x, int y, int resId) {
        return addImage(x, y, -2, -2, resId);
    }

    public CustomImgView addImage(int x, int y) {
        return addImage(x, y, -2, -2);
    }

    public void setDrawableUpSel(View view, int normal, int selected) {
        setDrawableStateList(view, normal, selected, selected);
    }

    public void setDrawableStateList(View view, int normal, int pressed, int selected) {
        Drawable pressedDrawable;
        Drawable selectedDrawable = null;
        Resources res = getActivity().getResources();
        StateListDrawable drawable = new StateListDrawable();
        Drawable normalDrawable = normal <= 0 ? null : res.getDrawable(normal);
        if (pressed <= 0) {
            pressedDrawable = null;
        } else {
            pressedDrawable = res.getDrawable(pressed);
        }
        if (selected > 0) {
            selectedDrawable = res.getDrawable(selected);
        }
        drawable.addState(new int[]{16842919}, pressedDrawable);
        drawable.addState(new int[]{16842913}, selectedDrawable);
        drawable.addState(new int[0], normalDrawable);
        view.setBackground(drawable);
    }

    public ParamButton addButton(int x, int y, int w, int h) {
        ParamButton button = new ParamButton(getActivity());
        button.setLayoutParams(createLayoutParam(x, y, w, h));
        this.mRelativeLayout.addView(button);
        return button;
    }

    private RelativeLayout.LayoutParams createLayoutParam(int x, int y, int w, int h) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(w, h);
        lp.leftMargin = x;
        lp.topMargin = y;
        return lp;
    }

    public ParamButton addButton(int x, int y) {
        return addButton(x, y, -2, -2);
    }

    /* access modifiers changed from: protected */
    public void InitViews() {
    }

    public void ResetData(boolean check) {
        super.ResetData(check);
    }

    public static long GetTickCount() {
        return SystemClock.uptimeMillis();
    }

    public void QueryData() {
        super.QueryData();
        CanJni.GMQuery(3);
        Can.updateAC();
        updateACUI();
    }
}
