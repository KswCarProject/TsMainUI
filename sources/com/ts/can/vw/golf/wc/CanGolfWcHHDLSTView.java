package com.ts.can.vw.golf.wc;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanVerticalBar;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.ts.other.ValCal;
import com.yyw.ts70xhw.KeyDef;

public class CanGolfWcHHDLSTView extends CanRelativeCarInfoView {
    public static final int BTN_TOP_LEFT = 3;
    public static final int BTN_TOP_RIGHT = 4;
    public static final int PAGE_LCJSQ = 0;
    public static final int PAGE_LPF = 2;
    public static final int PAGE_NLLDT = 1;
    protected CustomImgView mBattery;
    protected ParamButton mBtnTopLeft;
    protected ParamButton mBtnTopRight;
    protected TextView mCenterTitle;
    protected int mCurPage;
    protected CustomImgView mEngine;
    protected CustomImgView mLCJSCar;
    private CanVerticalBar mLPFBLBar;
    protected RelativeLayoutManager mManager;
    protected CustomImgView mNLLDCar;
    protected CustomImgView mNLLXLine;
    private CanDataInfo.GolfWcMixView mSetData;
    protected TextView[] mTextVal;
    protected CustomImgView mTireDriveLine;
    protected CustomImgView mTireDriveLine2;
    private TextView mTxtDqdcdl;
    private TextView mTxtLPFBL;
    private TextView mTxtLPFLC;
    private TextView mTxtXHQN;
    private TextView mTxtXSLC;

    public CanGolfWcHHDLSTView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        initCommonScreen();
    }

    private void initCommonScreen() {
        this.mManager = getRelativeManager();
        this.mCenterTitle = this.mManager.AddText(212, 30, CanCameraUI.BTN_GOLF_WC_MODE1, 90);
        this.mCenterTitle.setTextColor(-1);
        this.mCenterTitle.setTextSize(0, 60.0f);
        this.mCenterTitle.setText(R.string.can_vehi_status);
        this.mCenterTitle.setGravity(17);
        this.mBtnTopLeft = this.mManager.AddButton(28, 30, 74, 74);
        this.mBtnTopLeft.setStateUpDn(R.drawable.can_golf_vup_up, R.drawable.can_golf_vup_dn);
        this.mBtnTopLeft.setTag(3);
        this.mBtnTopLeft.setOnClickListener(this);
        this.mBtnTopRight = this.mManager.AddButton(922, 30, 74, 74);
        this.mBtnTopRight.setStateUpDn(R.drawable.can_golf_vdn_up, R.drawable.can_golf_vdn_dn);
        this.mBtnTopRight.setTag(4);
        this.mBtnTopRight.setOnClickListener(this);
        this.mLCJSCar = this.mManager.AddImage(0, 220, 963, KeyDef.RKEY_RADIO_3S);
        this.mLCJSCar.setImageResource(R.drawable.can_vw_mqb_licheng);
        this.mNLLDCar = this.mManager.AddImage(104, Can.CAN_AUDI_ZMYT, KeyDef.SKEY_CALLUP_4, 393);
        this.mNLLDCar.setImageResource(R.drawable.can_vw_mqb_car);
        this.mNLLXLine = this.mManager.AddImage(KeyDef.RKEY_DEL, 376, 354, 48);
        this.mTireDriveLine = this.mManager.AddImage(Can.CAN_CHRYSLER_ONE_HC, KeyDef.RKEY_UP, 273, Can.CAN_FORD_EDGE_XFY);
        this.mTireDriveLine2 = this.mManager.AddImage(Can.CAN_HONDA_WC, KeyDef.RKEY_UP, 273, Can.CAN_FORD_EDGE_XFY);
        this.mEngine = this.mManager.AddImage(241, 278, 131, 101);
        this.mEngine.setImageResource(R.drawable.can_vw_mqb_energy_normal);
        this.mEngine.setVisibility(8);
        this.mBattery = this.mManager.AddImage(658, KeyDef.RKEY_EJECT, Can.CAN_CC_WC, Can.CAN_BJ20_WC);
        this.mBattery.setImageResource(R.drawable.can_vw_mqb_battery00);
        this.mBattery.setVisibility(8);
        this.mLPFBLBar = new CanVerticalBar(getActivity(), R.drawable.can_vw_mqb_bar_dn, R.drawable.can_vw_mqb_bar_up);
        this.mLPFBLBar.setMinMax(0.0f, 100.0f);
        this.mManager.AddViewWrapContent(this.mLPFBLBar, 800, 170);
        this.mTextVal = new TextView[5];
        this.mTextVal[0] = this.mManager.AddText(62, Can.CAN_JAC_REFINE_OD, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 60);
        this.mTextVal[1] = this.mManager.AddText(62, Can.CAN_JAC_REFINE_OD, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 60);
        this.mTextVal[2] = this.mManager.AddText(62, 210, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 60);
        this.mTextVal[3] = this.mManager.AddText(62, 270, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 60);
        this.mTextVal[4] = this.mManager.AddText(380, 462, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 60);
        for (int i = 0; i < this.mTextVal.length; i++) {
            this.mTextVal[i].setTextColor(-1);
            this.mTextVal[i].setTextSize(0, 40.0f);
            this.mTextVal[i].setGravity(19);
        }
        this.mTextVal[4].setGravity(21);
        this.mTxtXHQN = this.mTextVal[0];
        this.mTxtXSLC = this.mTextVal[1];
        this.mTxtLPFLC = this.mTextVal[2];
        this.mTxtLPFBL = this.mTextVal[3];
        this.mTxtDqdcdl = this.mTextVal[4];
        this.mSetData = new CanDataInfo.GolfWcMixView();
    }

    public void doOnResume() {
        super.doOnResume();
        ShowPage();
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                this.mCurPage = ValCal.dataStepLoop(this.mCurPage, 0, 2, false);
                ShowPage();
                return;
            case 4:
                this.mCurPage = ValCal.dataStepLoop(this.mCurPage, 0, 2, true);
                ShowPage();
                return;
            default:
                return;
        }
    }

    private void ShowTitle() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        switch (this.mCurPage) {
            case 0:
                this.mCenterTitle.setText(R.string.can_ddxslcjsq);
                break;
            case 1:
                this.mCenterTitle.setText(R.string.can_nlldst);
                break;
            case 2:
                this.mCenterTitle.setText(R.string.can_lpf);
                break;
        }
        CustomImgView customImgView = this.mLCJSCar;
        if (this.mCurPage == 0) {
            z = true;
        } else {
            z = false;
        }
        customImgView.Show(z);
        CustomImgView customImgView2 = this.mNLLDCar;
        if (this.mCurPage == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        customImgView2.Show(z2);
        CustomImgView customImgView3 = this.mNLLXLine;
        if (this.mCurPage == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        customImgView3.Show(z3);
        CustomImgView customImgView4 = this.mTireDriveLine;
        if (this.mCurPage == 1) {
            z4 = true;
        } else {
            z4 = false;
        }
        customImgView4.Show(z4);
        CustomImgView customImgView5 = this.mTireDriveLine2;
        if (this.mCurPage == 1) {
            z5 = true;
        } else {
            z5 = false;
        }
        customImgView5.Show(z5);
        if (this.mCurPage != 1) {
            this.mEngine.Show(false);
            this.mBattery.Show(false);
        }
        CanVerticalBar canVerticalBar = this.mLPFBLBar;
        if (this.mCurPage == 2) {
            i = 0;
        } else {
            i = 8;
        }
        canVerticalBar.setVisibility(i);
        TextView textView = this.mTxtXHQN;
        if (this.mCurPage == 0) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        textView.setVisibility(i2);
        TextView textView2 = this.mTxtXSLC;
        if (this.mCurPage == 2) {
            i3 = 0;
        } else {
            i3 = 8;
        }
        textView2.setVisibility(i3);
        TextView textView3 = this.mTxtLPFLC;
        if (this.mCurPage == 2) {
            i4 = 0;
        } else {
            i4 = 8;
        }
        textView3.setVisibility(i4);
        TextView textView4 = this.mTxtLPFBL;
        if (this.mCurPage == 2) {
            i5 = 0;
        } else {
            i5 = 8;
        }
        textView4.setVisibility(i5);
        TextView textView5 = this.mTxtDqdcdl;
        if (this.mCurPage != 2) {
            i6 = 8;
        }
        textView5.setVisibility(i6);
    }

    private void ShowPage() {
        ShowTitle();
        ResetData(false);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void ResetData(boolean check) {
        CanJni.GolfGetMixView(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            Log.d("lq", "mSetData.Dqdcdl:" + ((this.mSetData.Dqdcdl / 10) + 1));
            Log.d("lq", "mSetData.Dqdcdl true:" + this.mSetData.Dqdcdl);
            setBattery(this.mSetData.Dqdcdl);
            setNLLCar(this.mSetData.Nll);
            this.mLPFBLBar.setCurPos(this.mSetData.Dqdcdl);
            this.mTxtDqdcdl.setText(String.format(" %d %%", new Object[]{Integer.valueOf(this.mSetData.Dqdcdl)}));
            this.mTxtXHQN.setText(String.valueOf(getActivity().getResources().getString(R.string.can_xhqn)) + String.format(":  %d km", new Object[]{Integer.valueOf(this.mSetData.Xhqn)}));
            this.mTxtXSLC.setText(String.valueOf(getActivity().getResources().getString(R.string.can_dis_trav)) + String.format(":  %d km", new Object[]{Integer.valueOf(this.mSetData.Xslc)}));
            this.mTxtLPFLC.setText(String.valueOf(getActivity().getResources().getString(R.string.can_lpflc)) + String.format(":  %d km", new Object[]{Integer.valueOf(this.mSetData.Lpflc)}));
            this.mTxtLPFBL.setText(String.valueOf(getActivity().getResources().getString(R.string.can_lpfbl)) + String.format(":  %d %%", new Object[]{Integer.valueOf(this.mSetData.Lpfbl)}));
        }
    }

    private void setBattery(int dcdl) {
        int resId = 0;
        switch ((dcdl / 10) + 1) {
            case 1:
                resId = R.drawable.can_vw_mqb_battery01;
                break;
            case 2:
                resId = R.drawable.can_vw_mqb_battery02;
                break;
            case 3:
                resId = R.drawable.can_vw_mqb_battery03;
                break;
            case 4:
                resId = R.drawable.can_vw_mqb_battery04;
                break;
            case 5:
                resId = R.drawable.can_vw_mqb_battery05;
                break;
            case 6:
                resId = R.drawable.can_vw_mqb_battery06;
                break;
            case 7:
                resId = R.drawable.can_vw_mqb_battery07;
                break;
            case 8:
                resId = R.drawable.can_vw_mqb_battery08;
                break;
            case 9:
                resId = R.drawable.can_vw_mqb_battery09;
                break;
            case 10:
                resId = R.drawable.can_vw_mqb_battery10;
                break;
        }
        if (dcdl == 0) {
            this.mBattery.setImageResource(R.drawable.can_vw_mqb_battery00);
        } else {
            this.mBattery.setImageResource(resId);
        }
    }

    private void setNLLCar(int nll) {
        boolean z;
        int i = 8;
        boolean z2 = false;
        if (this.mCurPage == 1) {
            int mNlResId = 0;
            int mClgjResId = 0;
            switch (nll) {
                case 0:
                    this.mEngine.setVisibility(8);
                    mNlResId = 0;
                    mClgjResId = 0;
                    break;
                case 1:
                    this.mEngine.setVisibility(0);
                    mNlResId = R.drawable.can_vw_mqb_right_energy;
                    mClgjResId = 0;
                    break;
                case 2:
                    this.mEngine.setVisibility(0);
                    mNlResId = R.drawable.can_vw_mqb_left_energy;
                    mClgjResId = R.drawable.can_vw_mqb_tyre_cease;
                    break;
                case 3:
                    this.mEngine.setVisibility(0);
                    mNlResId = R.drawable.can_vw_mqb_right_energy;
                    mClgjResId = R.drawable.can_vw_mqb_tyre_retrieve;
                    break;
                case 4:
                    this.mEngine.setVisibility(8);
                    mNlResId = 0;
                    mClgjResId = 0;
                    break;
                case 5:
                    this.mEngine.setVisibility(8);
                    mNlResId = R.drawable.can_vw_mqb_left_energy;
                    mClgjResId = R.drawable.can_vw_mqb_tyre_conveying;
                    break;
                case 6:
                    this.mEngine.setVisibility(8);
                    mNlResId = R.drawable.can_vw_mqb_right_energy;
                    mClgjResId = R.drawable.can_vw_mqb_tyre_retrieve;
                    break;
                case 7:
                    this.mEngine.setVisibility(0);
                    mNlResId = R.drawable.can_vw_mqb_left_energy;
                    mClgjResId = R.drawable.can_vw_mqb_tyre_mix;
                    break;
            }
            CustomImgView customImgView = this.mBattery;
            if (nll != 0) {
                i = 0;
            }
            customImgView.setVisibility(i);
            this.mNLLXLine.setImageResource(mNlResId);
            this.mTireDriveLine.setImageResource(mClgjResId);
            this.mTireDriveLine2.setImageResource(mClgjResId);
            CustomImgView customImgView2 = this.mTireDriveLine;
            if (nll == 3 || nll == 6) {
                z = false;
            } else {
                z = true;
            }
            customImgView2.Show(z);
            CustomImgView customImgView3 = this.mTireDriveLine2;
            if (nll == 3 || nll == 6) {
                z2 = true;
            }
            customImgView3.Show(z2);
        }
    }

    public void QueryData() {
    }
}
