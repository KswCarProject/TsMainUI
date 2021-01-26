package com.ts.can.psa;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanNumInuptDlg;
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZPoiSearchManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanPSADriveInfoActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange, CanNumInuptDlg.onInputOK {
    public static final int ITEM_DEL_LOG = 3;
    public static final int ITEM_GO_HOME = 4;
    public static final int ITEM_PAGE_0 = 0;
    public static final int ITEM_PAGE_1 = 1;
    public static final int ITEM_PAGE_2 = 2;
    public static final String TAG = "CanPSADriveInfoActivity";
    private static CanPSADriveInfoActivity mThis;
    private ParamButton mBtnDelLog;
    private ParamButton mBtnGohome;
    private ParamButton[] mBtnLt = new ParamButton[3];
    private int mCurPage = -1;
    private CanDataInfo.PeugDataDW mDWData = new CanDataInfo.PeugDataDW();
    private CustomImgView[] mIvIco0 = new CustomImgView[4];
    private CustomImgView[] mIvIco1 = new CustomImgView[3];
    private CustomImgView[] mIvIco2 = new CustomImgView[3];
    protected RelativeLayoutManager mManager;
    private CanDataInfo.PeugPageInfo mPage0Data = new CanDataInfo.PeugPageInfo();
    private CanDataInfo.PeugPageInfo mPage1Data = new CanDataInfo.PeugPageInfo();
    private CanDataInfo.PeugPageInfo mPage2Data = new CanDataInfo.PeugPageInfo();
    private CanDataInfo.PeugAutoTimer mTimerData = new CanDataInfo.PeugAutoTimer();
    private CustomTextView[] mTvDW0 = new CustomTextView[4];
    private CustomTextView[] mTvDW1 = new CustomTextView[3];
    private CustomTextView[] mTvDW2 = new CustomTextView[3];
    private CustomTextView[] mTvVal0 = new CustomTextView[4];
    private CustomTextView[] mTvVal1 = new CustomTextView[3];
    private CustomTextView[] mTvVal2 = new CustomTextView[3];
    private int[] mX = {354, CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, 767};
    private boolean mfgType408;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        boolean z;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        if (CanJni.GetSubType() == 2) {
            z = true;
        } else {
            z = false;
        }
        this.mfgType408 = z;
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.AddImage(35, 17, R.drawable.can_psa_bg);
        this.mBtnLt[0] = AddBtn(0, 66, 41, R.drawable.can_psa_car_up, R.drawable.can_psa_car_dn);
        this.mBtnLt[1] = AddBtn(1, 66, Can.CAN_LEXUS_ZMYT, R.drawable.can_psa_01_up, R.drawable.can_psa_01_dn);
        this.mBtnLt[2] = AddBtn(2, 66, AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, R.drawable.can_psa_02_up, R.drawable.can_psa_02_dn);
        this.mBtnDelLog = this.mManager.AddButton(CanCameraUI.BTN_GEELY_YJX6_FXP, 408, Can.CAN_LEXUS_IZ, 77);
        SetCommBtn(this.mBtnDelLog, R.string.can_clear, 3, this);
        this.mIvIco0[0] = this.mManager.AddImage(354, 70, R.drawable.can_psa_icon_station);
        this.mIvIco0[1] = this.mManager.AddImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, 70, R.drawable.can_psa_icon_oil);
        this.mIvIco0[2] = this.mManager.AddImage(767, 70, R.drawable.can_psa_icon_flags);
        this.mIvIco0[3] = this.mManager.AddImage(354, KeyDef.RKEY_res3, R.drawable.can_psa_icon_all);
        this.mBtnGohome = AddBtn(4, 880, 70, R.drawable.can_golf_bel_set_up, R.drawable.can_golf_bel_set_dn);
        this.mIvIco1[0] = this.mManager.AddImage(355, 170, R.drawable.can_psa_icon_radar);
        this.mIvIco1[1] = this.mManager.AddImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, 170, R.drawable.can_psa_icon_oil);
        this.mIvIco1[2] = this.mManager.AddImage(767, 170, R.drawable.can_psa_icon_the);
        this.mIvIco2[0] = this.mManager.AddImage(354, 170, R.drawable.can_psa_icon_radar);
        this.mIvIco2[1] = this.mManager.AddImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, 170, R.drawable.can_psa_icon_oil);
        this.mIvIco2[2] = this.mManager.AddImage(767, 170, R.drawable.can_psa_icon_the);
        for (int i = 0; i < 3; i++) {
            this.mTvVal0[i] = AddText(this.mX[i], 168, 172, 60, 55);
            this.mTvDW0[i] = AddText(this.mX[i], Can.CAN_TEANA_OLD_DJ, 172, 60, 40);
            this.mTvVal1[i] = AddText(this.mX[i], 268, 172, 60, 55);
            this.mTvDW1[i] = AddText(this.mX[i], KeyDef.RKEY_RADIO_6S, 172, 60, 40);
            this.mTvVal2[i] = AddText(this.mX[i], 268, 172, 60, 55);
            this.mTvDW2[i] = AddText(this.mX[i], KeyDef.RKEY_RADIO_6S, 172, 60, 40);
        }
        this.mTvVal0[3] = AddText(354, 438, 172, 60, 55);
        this.mTvDW0[3] = AddText(354, 498, 172, 60, 40);
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
                return TXZResourceManager.STYLE_DEFAULT;
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

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        CanJni.PSAQuery(56, 0);
        ResetData(false);
        mThis = this;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        mThis = null;
    }

    private void ResetData(boolean check) {
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
        if (i2b(this.mPage2Data.UpdateOnce) && (!check || i2b(this.mPage2Data.Update))) {
            this.mPage2Data.Update = 0;
            this.mTvVal2[0].setText(GetRangeVal(this.mPage2Data.Data[2], dw));
            this.mTvVal2[1].setText(GetConsumpVal(this.mPage2Data.Data[0], dw));
            this.mTvVal2[2].setText(GetSpeedVal(this.mPage2Data.Data[1], dw));
        }
        if (!i2b(this.mTimerData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTimerData.Update)) {
            this.mTimerData.Update = 0;
            if (255 == this.mTimerData.Hour || this.mTimerData.Min > 59 || this.mTimerData.Sec > 59) {
                this.mTvVal0[3].setText("--");
                return;
            }
            this.mTvVal0[3].setText(String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(this.mTimerData.Hour), Integer.valueOf(this.mTimerData.Min), Integer.valueOf(this.mTimerData.Sec)}));
        }
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
                    CanJni.PSAClearPage(1);
                    return;
                } else {
                    CanJni.PSAClearPage(0);
                    return;
                }
            case 4:
                new CanNumInuptDlg(this, this, 4, id);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
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
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        boolean z15 = true;
        if (this.mCurPage != page) {
            this.mCurPage = page;
            Log.d("CanPSADriveInfoActivity", "ShowPage " + page);
            for (int i = 0; i < 3; i++) {
                ParamButton paramButton = this.mBtnLt[i];
                if (i == this.mCurPage) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                paramButton.setSelected(z5);
                CustomImgView customImgView = this.mIvIco0[i];
                if (this.mCurPage == 0) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                customImgView.Show(z6);
                CustomTextView customTextView = this.mTvVal0[i];
                if (this.mCurPage == 0) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                customTextView.ShowGone(z7);
                CustomTextView customTextView2 = this.mTvDW0[i];
                if (this.mCurPage == 0) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                customTextView2.ShowGone(z8);
                CustomImgView customImgView2 = this.mIvIco1[i];
                if (this.mCurPage == 1) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                customImgView2.Show(z9);
                CustomTextView customTextView3 = this.mTvVal1[i];
                if (this.mCurPage == 1) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                customTextView3.ShowGone(z10);
                CustomTextView customTextView4 = this.mTvDW1[i];
                if (this.mCurPage == 1) {
                    z11 = true;
                } else {
                    z11 = false;
                }
                customTextView4.ShowGone(z11);
                CustomImgView customImgView3 = this.mIvIco2[i];
                if (this.mCurPage == 2) {
                    z12 = true;
                } else {
                    z12 = false;
                }
                customImgView3.Show(z12);
                CustomTextView customTextView5 = this.mTvVal2[i];
                if (this.mCurPage == 2) {
                    z13 = true;
                } else {
                    z13 = false;
                }
                customTextView5.ShowGone(z13);
                CustomTextView customTextView6 = this.mTvDW2[i];
                if (this.mCurPage == 2) {
                    z14 = true;
                } else {
                    z14 = false;
                }
                customTextView6.ShowGone(z14);
            }
            CustomImgView customImgView4 = this.mIvIco0[3];
            if (this.mCurPage == 0) {
                z = true;
            } else {
                z = false;
            }
            customImgView4.Show(z);
            CustomTextView customTextView7 = this.mTvVal0[3];
            if (this.mCurPage == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            customTextView7.ShowGone(z2);
            CustomTextView customTextView8 = this.mTvDW0[3];
            if (this.mCurPage == 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            customTextView8.ShowGone(z3);
            ParamButton paramButton2 = this.mBtnGohome;
            if (this.mCurPage == 0) {
                z4 = true;
            } else {
                z4 = false;
            }
            paramButton2.Show(z4);
            ParamButton paramButton3 = this.mBtnDelLog;
            if (this.mCurPage <= 0) {
                z15 = false;
            }
            paramButton3.Show(z15);
            CanJni.PSASetPage(page);
        }
    }

    public void PageInc() {
        ShowPage((this.mCurPage + 1) % 3);
    }

    public static void DealPage() {
        if (mThis == null) {
            CanFunc.showCanActivity(CanPSADriveInfoActivity.class);
            Log.d("CanPSADriveInfoActivity", "Show CanPSADriveInfoActivity");
            return;
        }
        mThis.PageInc();
        Log.d("CanPSADriveInfoActivity", "PageInc");
    }

    public void onOK(String val, int num, int id) {
        if (num > 3000) {
            num = TXZPoiSearchManager.DEFAULT_NEARBY_RADIUS;
        }
        CanJni.PSASetDis(num);
    }
}
