package com.ts.can.psa.wc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanNumInuptDlg;
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanPSAWCDriveInfoView extends CanRelativeCarInfoView implements CanNumInuptDlg.onInputOK {
    public static final int ITEM_DEL_LOG = 3;
    public static final int ITEM_GO_HOME = 4;
    public static final int ITEM_PAGE_0 = 0;
    public static final int ITEM_PAGE_1 = 1;
    public static final int ITEM_PAGE_2 = 2;
    private ParamButton mBtnDelLog;
    private ParamButton mBtnGohome;
    private ParamButton[] mBtnLt;
    private int mCurPage;
    private CanDataInfo.PeugDataDW mDWData = new CanDataInfo.PeugDataDW();
    private CustomImgView[] mIvIco0;
    private CustomImgView[] mIvIco1;
    private CustomImgView[] mIvIco2;
    protected RelativeLayoutManager mManager;
    private CanDataInfo.PeugPageInfo mPage0Data = new CanDataInfo.PeugPageInfo();
    private CanDataInfo.PeugPageInfo mPage1Data = new CanDataInfo.PeugPageInfo();
    private CanDataInfo.PeugPageInfo mPage2Data = new CanDataInfo.PeugPageInfo();
    private CanDataInfo.PeugAutoTimer mTimerData = new CanDataInfo.PeugAutoTimer();
    private CustomTextView[] mTvDW0;
    private CustomTextView[] mTvDW1;
    private CustomTextView[] mTvDW2;
    private CustomTextView[] mTvVal0;
    private CustomTextView[] mTvVal1;
    private CustomTextView[] mTvVal2;
    private int[] mX;

    public CanPSAWCDriveInfoView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 0:
                ShowPage(0);
                return;
            case 1:
                ShowPage(1);
                return;
            case 2:
                ShowPage(2);
                return;
            case 3:
                if (1 == this.mCurPage) {
                    CanJni.PsaWcCarPcSet(2, 2, 1, 255);
                    return;
                } else {
                    CanJni.PsaWcCarPcSet(3, 3, 1, 255);
                    return;
                }
            case 4:
                new CanNumInuptDlg(getActivity(), this, 4, id);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mBtnLt = new ParamButton[3];
        this.mIvIco0 = new CustomImgView[4];
        this.mTvVal0 = new CustomTextView[4];
        this.mTvDW0 = new CustomTextView[4];
        this.mIvIco1 = new CustomImgView[3];
        this.mTvVal1 = new CustomTextView[3];
        this.mTvDW1 = new CustomTextView[3];
        this.mIvIco2 = new CustomImgView[3];
        this.mTvVal2 = new CustomTextView[3];
        this.mTvDW2 = new CustomTextView[3];
        this.mX = new int[]{354, CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, 767};
        this.mCurPage = -1;
        this.mManager = getRelativeManager();
        this.mManager.AddImage(35, 17, R.drawable.can_psa_bg);
        this.mBtnLt[0] = AddBtn(0, 66, 41, R.drawable.can_psa_car_up, R.drawable.can_psa_car_dn);
        this.mBtnLt[1] = AddBtn(1, 66, Can.CAN_LEXUS_ZMYT, R.drawable.can_psa_01_up, R.drawable.can_psa_01_dn);
        this.mBtnLt[2] = AddBtn(2, 66, AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, R.drawable.can_psa_02_up, R.drawable.can_psa_02_dn);
        this.mBtnDelLog = this.mManager.AddButton(CanCameraUI.BTN_GEELY_YJX6_FXP, 408, Can.CAN_LEXUS_IZ, 77);
        SetCommBtn(this.mBtnDelLog, R.string.can_clear, 3, this);
        this.mIvIco0[0] = this.mManager.AddImage(355, 170, R.drawable.can_psa_icon_station);
        this.mIvIco0[1] = this.mManager.AddImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, 170, R.drawable.can_psa_icon_oil);
        this.mIvIco0[2] = this.mManager.AddImage(767, 170, R.drawable.can_psa_icon_flags);
        this.mIvIco0[3] = this.mManager.AddImage(354, KeyDef.RKEY_res3, R.drawable.can_psa_icon_all);
        this.mBtnGohome = AddBtn(4, 880, 70, R.drawable.can_golf_bel_set_up, R.drawable.can_golf_bel_set_dn);
        this.mIvIco1[0] = this.mManager.AddImage(355, 170, R.drawable.can_psa_icon_radar);
        this.mIvIco1[1] = this.mManager.AddImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, 170, R.drawable.can_psa_icon_oil);
        this.mIvIco1[2] = this.mManager.AddImage(767, 170, R.drawable.can_psa_icon_the);
        this.mIvIco2[0] = this.mManager.AddImage(354, 170, R.drawable.can_psa_icon_radar);
        this.mIvIco2[1] = this.mManager.AddImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, 170, R.drawable.can_psa_icon_oil);
        this.mIvIco2[2] = this.mManager.AddImage(767, 170, R.drawable.can_psa_icon_the);
        for (int i = 0; i < 3; i++) {
            this.mTvVal0[i] = AddText(this.mX[i], 268, 172, 60, 55);
            this.mTvDW0[i] = AddText(this.mX[i], KeyDef.RKEY_RADIO_6S, 172, 60, 40);
            this.mTvVal1[i] = AddText(this.mX[i], 268, 172, 60, 55);
            this.mTvDW1[i] = AddText(this.mX[i], KeyDef.RKEY_RADIO_6S, 172, 60, 40);
            this.mTvVal2[i] = AddText(this.mX[i], 268, 172, 60, 55);
            this.mTvDW2[i] = AddText(this.mX[i], KeyDef.RKEY_RADIO_6S, 172, 60, 40);
        }
        this.mTvVal0[3] = AddText(354, 438, 172, 60, 55);
        this.mTvDW0[3] = AddText(354, 498, 172, 60, 40);
        this.mTvDW0[0].setText("KM");
        this.mTvDW0[1].setText("L/100");
        this.mTvDW1[0].setText("KM");
        this.mTvDW1[1].setText("L/100");
        this.mTvDW1[2].setText("KM/H");
        this.mTvDW2[0].setText("KM");
        this.mTvDW2[1].setText("L/100");
        this.mTvDW2[2].setText("KM/H");
        ShowPage(0);
    }

    public void ResetData(boolean check) {
        CanJni.PSAGetPage(this.mPage0Data, this.mPage1Data, this.mPage2Data, this.mDWData, this.mTimerData);
        int dw = 0;
        if (i2b(this.mDWData.UpdateOnce)) {
            dw = this.mDWData.FuelDW;
            if (!check || i2b(this.mDWData.Update)) {
                check = false;
                this.mDWData.Update = 0;
                this.mTvDW0[0].setText(GetRangeDW(this.mDWData.FuelDW));
                this.mTvDW0[1].setText(GetConsumpDW(this.mDWData.FuelDW));
                this.mTvDW1[0].setText(GetRangeDW(this.mDWData.FuelDW));
                this.mTvDW1[1].setText(GetConsumpDW(this.mDWData.FuelDW));
                this.mTvDW1[2].setText(GetSpeedDW(this.mDWData.FuelDW));
                this.mTvDW2[0].setText(GetRangeDW(this.mDWData.FuelDW));
                this.mTvDW2[1].setText(GetConsumpDW(this.mDWData.FuelDW));
                this.mTvDW2[2].setText(GetSpeedDW(this.mDWData.FuelDW));
            }
        }
        if (i2b(this.mPage0Data.UpdateOnce) && (!check || i2b(this.mPage0Data.Update))) {
            this.mPage0Data.Update = 0;
            this.mTvVal0[0].setText(GetRangeVal(this.mPage0Data.Data[1], dw));
            this.mTvVal0[1].setText(GetConsumpVal(this.mPage0Data.Data[0], dw));
            this.mTvVal0[2].setText(GetRangeVal(this.mPage0Data.Data[2], dw));
        }
        if (i2b(this.mPage1Data.UpdateOnce) && (!check || i2b(this.mPage1Data.Update))) {
            this.mPage1Data.Update = 0;
            this.mTvVal1[0].setText(GetRangeVal(this.mPage1Data.Data[2], dw));
            this.mTvVal1[1].setText(GetConsumpVal(this.mPage1Data.Data[0], dw));
            this.mTvVal1[2].setText(GetSpeedVal(this.mPage1Data.Data[1], dw));
        }
        if (!i2b(this.mPage2Data.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPage2Data.Update)) {
            this.mPage2Data.Update = 0;
            this.mTvVal2[0].setText(GetRangeVal(this.mPage2Data.Data[2], dw));
            this.mTvVal2[1].setText(GetConsumpVal(this.mPage2Data.Data[0], dw));
            this.mTvVal2[2].setText(GetSpeedVal(this.mPage2Data.Data[1], dw));
        }
    }

    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void SetCommBtn(ParamButton btn, int text, int id, View.OnClickListener l) {
        SetCommBtn(btn, text);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(l);
    }

    /* access modifiers changed from: protected */
    public void SetCommBtn(ParamButton btn, int text) {
        btn.setStateUpDn(R.drawable.fuel_consumption_rect_up, R.drawable.fuel_consumption_rect_dn);
        if (text != 0) {
            btn.setText(text);
        }
        btn.setPadding(0, 3, 0, 0);
        btn.setTextColor(-1);
        btn.setGravity(17);
        btn.setTextSize(0, 32.0f);
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddText(int x, int y, int w, int h, int size) {
        CustomTextView text = this.mManager.AddCusText(x, y, w, h);
        text.SetPxSize(size);
        text.setGravity(17);
        return text;
    }

    /* access modifiers changed from: protected */
    public String GetConsumpVal(int val, int dw) {
        if (65535 == val) {
            return "--.-";
        }
        float fval = ((float) val) * 0.1f;
        if (val != 0) {
            switch (dw) {
                case 1:
                    fval = 100.0f / fval;
                    break;
                case 2:
                    fval = 282.35f / fval;
                    break;
            }
        }
        return String.format("%.1f", new Object[]{Float.valueOf(fval)});
    }

    /* access modifiers changed from: protected */
    public String GetConsumpDW(int val) {
        switch (val) {
            case 0:
                return "L/100";
            case 1:
                return "KM/L";
            case 2:
                return "MPG";
            default:
                return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    /* access modifiers changed from: protected */
    public String GetSpeedVal(int val, int dw) {
        if (255 == val) {
            return "---";
        }
        return new StringBuilder().append(val).toString();
    }

    /* access modifiers changed from: protected */
    public String GetRangeVal(int val, int dw) {
        if (65535 == val) {
            return "----";
        }
        return new StringBuilder().append(val).toString();
    }

    /* access modifiers changed from: protected */
    public String GetRangeDW(int dw) {
        return "KM";
    }

    /* access modifiers changed from: protected */
    public String GetSpeedDW(int dw) {
        return "KM/H";
    }

    public void ShowPage(int page) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11 = true;
        if (this.mCurPage != page) {
            this.mCurPage = page;
            for (int i = 0; i < 3; i++) {
                ParamButton paramButton = this.mBtnLt[i];
                if (i == this.mCurPage) {
                    z = true;
                } else {
                    z = false;
                }
                paramButton.setSelected(z);
                CustomImgView customImgView = this.mIvIco0[i];
                if (this.mCurPage == 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                customImgView.Show(z2);
                CustomTextView customTextView = this.mTvVal0[i];
                if (this.mCurPage == 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                customTextView.ShowGone(z3);
                CustomTextView customTextView2 = this.mTvDW0[i];
                if (this.mCurPage == 0) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                customTextView2.ShowGone(z4);
                CustomImgView customImgView2 = this.mIvIco1[i];
                if (this.mCurPage == 1) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                customImgView2.Show(z5);
                CustomTextView customTextView3 = this.mTvVal1[i];
                if (this.mCurPage == 1) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                customTextView3.ShowGone(z6);
                CustomTextView customTextView4 = this.mTvDW1[i];
                if (this.mCurPage == 1) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                customTextView4.ShowGone(z7);
                CustomImgView customImgView3 = this.mIvIco2[i];
                if (this.mCurPage == 2) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                customImgView3.Show(z8);
                CustomTextView customTextView5 = this.mTvVal2[i];
                if (this.mCurPage == 2) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                customTextView5.ShowGone(z9);
                CustomTextView customTextView6 = this.mTvDW2[i];
                if (this.mCurPage == 2) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                customTextView6.ShowGone(z10);
            }
            this.mIvIco0[2].Show(false);
            this.mTvVal0[2].ShowGone(false);
            this.mTvDW0[2].ShowGone(false);
            this.mIvIco0[3].Show(false);
            this.mTvVal0[3].ShowGone(false);
            this.mTvDW0[3].ShowGone(false);
            this.mBtnGohome.Show(false);
            ParamButton paramButton2 = this.mBtnDelLog;
            if (this.mCurPage <= 0) {
                z11 = false;
            }
            paramButton2.Show(z11);
        }
    }

    public void PageInc() {
        ShowPage((this.mCurPage + 1) % 3);
    }

    public void onOK(String val, int num, int id) {
    }
}
