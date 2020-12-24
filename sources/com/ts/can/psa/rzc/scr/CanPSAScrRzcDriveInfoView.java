package com.ts.can.psa.rzc.scr;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanPSAScrRzcDriveInfoView extends CanRelativeCarInfoView {
    public static final int ITEM_PAGE_0 = 0;
    public static final int ITEM_PAGE_1 = 1;
    public static final int ITEM_PAGE_2 = 2;
    private static CanPSAScrRzcDriveInfoView mThis;
    private ParamButton[] mBtnLt;
    private int mCurPage;
    private CanDataInfo.PeugDataDW mDWData;
    private CustomImgView[] mIvIco0;
    private CustomImgView[] mIvIco1;
    private CustomImgView[] mIvIco2;
    private CanDataInfo.PeugPageInfo mPage0Data;
    private CanDataInfo.PeugPageInfo mPage1Data;
    private CanDataInfo.PeugPageInfo mPage2Data;
    private CanDataInfo.PeugAutoTimer mTimerData;
    private TextView[] mTvDW0;
    private TextView[] mTvDW1;
    private TextView[] mTvDW2;
    private TextView[] mTvVal0;
    private TextView[] mTvVal1;
    private TextView[] mTvVal2;

    public CanPSAScrRzcDriveInfoView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                ShowPage(0);
                return;
            case 1:
                ShowPage(1);
                return;
            case 2:
                ShowPage(2);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mBtnLt = new ParamButton[3];
        this.mIvIco0 = new CustomImgView[3];
        this.mIvIco1 = new CustomImgView[3];
        this.mIvIco2 = new CustomImgView[3];
        addImage(35, 17, R.drawable.can_psa_bg);
        this.mBtnLt[0] = AddBtn(0, 66, 41, R.drawable.can_psa_car_up, R.drawable.can_psa_car_dn);
        this.mBtnLt[1] = AddBtn(1, 66, Can.CAN_LEXUS_ZMYT, R.drawable.can_psa_01_up, R.drawable.can_psa_01_dn);
        this.mBtnLt[2] = AddBtn(2, 66, 370, R.drawable.can_psa_02_up, R.drawable.can_psa_02_dn);
        this.mIvIco0[0] = addImage(354, 170, R.drawable.can_psa_icon_station);
        this.mIvIco0[1] = addImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, 170, R.drawable.can_psa_icon_oil);
        this.mIvIco0[2] = addImage(767, 170, R.drawable.can_psa_icon_flags);
        this.mIvIco1[0] = addImage(355, 170, R.drawable.can_psa_icon_radar);
        this.mIvIco1[1] = addImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, 170, R.drawable.can_psa_icon_oil);
        this.mIvIco1[2] = addImage(767, 170, R.drawable.can_psa_icon_the);
        this.mIvIco2[0] = addImage(354, 170, R.drawable.can_psa_icon_radar);
        this.mIvIco2[1] = addImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, 170, R.drawable.can_psa_icon_oil);
        this.mIvIco2[2] = addImage(767, 170, R.drawable.can_psa_icon_the);
        this.mTvVal0 = new TextView[3];
        this.mTvDW0 = new TextView[3];
        this.mTvVal1 = new TextView[3];
        this.mTvDW1 = new TextView[3];
        this.mTvVal2 = new TextView[3];
        this.mTvDW2 = new TextView[3];
        int[] mX = {354, CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, 767};
        for (int i = 0; i < 3; i++) {
            this.mTvVal0[i] = AddText(mX[i], 268, 172, 60, 55);
            this.mTvDW0[i] = AddText(mX[i], KeyDef.RKEY_RADIO_6S, 172, 60, 40);
            this.mTvVal1[i] = AddText(mX[i], 268, 172, 60, 55);
            this.mTvDW1[i] = AddText(mX[i], KeyDef.RKEY_RADIO_6S, 172, 60, 40);
            this.mTvVal2[i] = AddText(mX[i], 268, 172, 60, 55);
            this.mTvDW2[i] = AddText(mX[i], KeyDef.RKEY_RADIO_6S, 172, 60, 40);
        }
        this.mTvDW0[0].setText("KM");
        this.mTvDW0[1].setText("L/100");
        this.mTvDW0[2].setText("KM");
        this.mTvDW1[0].setText("KM");
        this.mTvDW1[1].setText("L/100");
        this.mTvDW1[2].setText("KM/H");
        this.mTvDW2[0].setText("KM");
        this.mTvDW2[1].setText("L/100");
        this.mTvDW2[2].setText("KM/H");
        ShowPage(0);
        this.mPage0Data = new CanDataInfo.PeugPageInfo();
        this.mPage1Data = new CanDataInfo.PeugPageInfo();
        this.mPage2Data = new CanDataInfo.PeugPageInfo();
        this.mDWData = new CanDataInfo.PeugDataDW();
        this.mTimerData = new CanDataInfo.PeugAutoTimer();
    }

    private void ShowPage(int page) {
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
            setViewShow((View) customImgView, z2);
            TextView textView = this.mTvVal0[i];
            if (this.mCurPage == 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            setViewShow((View) textView, z3);
            TextView textView2 = this.mTvDW0[i];
            if (this.mCurPage == 0) {
                z4 = true;
            } else {
                z4 = false;
            }
            setViewShow((View) textView2, z4);
            CustomImgView customImgView2 = this.mIvIco1[i];
            if (this.mCurPage == 1) {
                z5 = true;
            } else {
                z5 = false;
            }
            setViewShow((View) customImgView2, z5);
            TextView textView3 = this.mTvVal1[i];
            if (this.mCurPage == 1) {
                z6 = true;
            } else {
                z6 = false;
            }
            setViewShow((View) textView3, z6);
            TextView textView4 = this.mTvDW1[i];
            if (this.mCurPage == 1) {
                z7 = true;
            } else {
                z7 = false;
            }
            setViewShow((View) textView4, z7);
            CustomImgView customImgView3 = this.mIvIco2[i];
            if (this.mCurPage == 2) {
                z8 = true;
            } else {
                z8 = false;
            }
            setViewShow((View) customImgView3, z8);
            TextView textView5 = this.mTvVal2[i];
            if (this.mCurPage == 2) {
                z9 = true;
            } else {
                z9 = false;
            }
            setViewShow((View) textView5, z9);
            TextView textView6 = this.mTvDW2[i];
            if (this.mCurPage == 2) {
                z10 = true;
            } else {
                z10 = false;
            }
            setViewShow((View) textView6, z10);
        }
    }

    public void ResetData(boolean check) {
        CanJni.PSAGetPage(this.mPage0Data, this.mPage1Data, this.mPage2Data, this.mDWData, this.mTimerData);
        int dw = 1;
        if (i2b(this.mDWData.UpdateOnce)) {
            dw = this.mDWData.FuelDW;
            if (!check || i2b(this.mDWData.Update)) {
                check = false;
                this.mDWData.Update = 0;
                this.mTvDW0[0].setText(GetRangeDW(this.mDWData.FuelDW));
                this.mTvDW0[1].setText(GetConsumpDW(this.mDWData.FuelDW));
                this.mTvDW0[2].setText(GetRangeDW(this.mDWData.FuelDW));
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

    public void doOnResume() {
        super.doOnResume();
        mThis = this;
    }

    public void doOnPause() {
        super.doOnPause();
        mThis = null;
    }

    public void PageInc() {
        ShowPage((this.mCurPage + 1) % 3);
    }

    public static void DealPage() {
        if (mThis == null) {
            CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 0);
        } else {
            mThis.PageInc();
        }
    }

    public void QueryData() {
        CanJni.PsaSrcRzcQuery(51);
        Sleep(10);
        CanJni.PsaSrcRzcQuery(52);
        Sleep(10);
        CanJni.PsaSrcRzcQuery(53);
    }

    private ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = addButtonState(x, y, up, dn, dn);
        btn.setOnClickListener(this);
        btn.setTag(Integer.valueOf(id));
        return btn;
    }

    private TextView AddText(int x, int y, int w, int h, int size) {
        TextView tv = addText(x, y, w, h);
        tv.setTextSize(0, (float) ((int) (((double) size) / 1.3d)));
        tv.setGravity(17);
        tv.setTextColor(-1);
        return tv;
    }

    /* access modifiers changed from: protected */
    public String GetConsumpVal(int val, int dw) {
        if (-1 == val) {
            return "--.-";
        }
        float fval = ((float) val) * 0.1f;
        if (val != 0) {
            switch (dw) {
                case 0:
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
                return "KM/L";
            case 1:
                return "L/100";
            case 2:
                return "MPG";
            default:
                return "";
        }
    }

    /* access modifiers changed from: protected */
    public String GetSpeedVal(int val, int dw) {
        if (-1 == val) {
            return "---";
        }
        if (2 == dw) {
            val = (int) (((float) val) * 0.62137f);
        }
        return new StringBuilder().append(val).toString();
    }

    /* access modifiers changed from: protected */
    public String GetRangeVal(int val, int dw) {
        if (-1 == val) {
            return "----";
        }
        if (2 == dw) {
            val = (int) (((float) val) * 0.62137f);
        }
        return new StringBuilder().append(val).toString();
    }

    /* access modifiers changed from: protected */
    public String GetRangeDW(int dw) {
        if (2 == dw) {
            return "MI";
        }
        return "KM";
    }

    /* access modifiers changed from: protected */
    public String GetSpeedDW(int dw) {
        if (2 == dw) {
            return "MPH";
        }
        return "KM/H";
    }
}
