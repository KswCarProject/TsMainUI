package com.ts.can.psa.hc;

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
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanBZDriveInfoActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_DEL_LOG = 3;
    public static final int ITEM_PAGE_0 = 0;
    public static final int ITEM_PAGE_1 = 1;
    public static final int ITEM_PAGE_2 = 2;
    public static final String TAG = "CanBZDriveInfoActivity";
    private static CanBZDriveInfoActivity mThis;
    private ParamButton mBtnDelLog;
    private ParamButton[] mBtnLt = new ParamButton[3];
    private int mCurPage = -1;
    private CanDataInfo.PeugDataDW mDWData = new CanDataInfo.PeugDataDW();
    private CustomImgView[] mIvIco0 = new CustomImgView[3];
    private CustomImgView[] mIvIco1 = new CustomImgView[3];
    private CustomImgView[] mIvIco2 = new CustomImgView[3];
    protected RelativeLayoutManager mManager;
    private CanDataInfo.PeugPageInfo mPage0Data = new CanDataInfo.PeugPageInfo();
    private CanDataInfo.PeugPageInfo mPage1Data = new CanDataInfo.PeugPageInfo();
    private CanDataInfo.PeugPageInfo mPage2Data = new CanDataInfo.PeugPageInfo();
    private CanDataInfo.PeugAutoTimer mTimerData = new CanDataInfo.PeugAutoTimer();
    private CustomTextView[] mTvDW0 = new CustomTextView[3];
    private CustomTextView[] mTvDW1 = new CustomTextView[3];
    private CustomTextView[] mTvDW2 = new CustomTextView[3];
    private CustomTextView[] mTvVal0 = new CustomTextView[3];
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
        this.mBtnLt[2] = AddBtn(2, 66, 370, R.drawable.can_psa_02_up, R.drawable.can_psa_02_dn);
        this.mBtnDelLog = this.mManager.AddButton(CanCameraUI.BTN_GEELY_YJX6_FXP, 408, Can.CAN_LEXUS_IZ, 77);
        SetCommBtn(this.mBtnDelLog, R.string.can_clear, 3, this);
        this.mIvIco0[0] = this.mManager.AddImage(354, 170, R.drawable.can_psa_icon_station);
        this.mIvIco0[1] = this.mManager.AddImage(CanCameraUI.BTN_TRUMPCHI_GS7_MODE7, 170, R.drawable.can_psa_icon_oil);
        this.mIvIco0[2] = this.mManager.AddImage(767, 170, R.drawable.can_psa_icon_flags);
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

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
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
        CanJni.BZGetPage(this.mPage0Data, this.mPage1Data, this.mPage2Data, this.mDWData, this.mTimerData);
        this.mDWData.FuelDW = 1;
        if (i2b(this.mPage0Data.UpdateOnce) && (!check || i2b(this.mPage0Data.Update))) {
            this.mPage0Data.Update = 0;
            this.mTvVal0[0].setText(GetRangeVal(this.mPage0Data.Data[1], 1));
            this.mTvVal0[1].setText(GetConsumpVal(this.mPage0Data.Data[0], 1));
            this.mTvVal0[2].setText(GetRangeVal(this.mPage0Data.Data[2], 1));
        }
        if (i2b(this.mPage1Data.UpdateOnce) && (!check || i2b(this.mPage1Data.Update))) {
            this.mPage1Data.Update = 0;
            this.mTvVal1[0].setText(GetRangeVal(this.mPage1Data.Data[2], 1));
            this.mTvVal1[1].setText(GetConsumpVal(this.mPage1Data.Data[0], 1));
            this.mTvVal1[2].setText(GetSpeedVal(this.mPage1Data.Data[1], 1));
        }
        if (!i2b(this.mPage2Data.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPage2Data.Update)) {
            this.mPage2Data.Update = 0;
            this.mTvVal2[0].setText(GetRangeVal(this.mPage2Data.Data[2], 1));
            this.mTvVal2[1].setText(GetConsumpVal(this.mPage2Data.Data[0], 1));
            this.mTvVal2[2].setText(GetSpeedVal(this.mPage2Data.Data[1], 1));
        }
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
            case 3:
                if (1 == this.mCurPage) {
                    CanJni.BZClearPage(1);
                    return;
                } else {
                    CanJni.BZClearPage(0);
                    return;
                }
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
        boolean z11 = true;
        if (this.mCurPage != page) {
            this.mCurPage = page;
            Log.d(TAG, "ShowPage " + page);
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
            ParamButton paramButton2 = this.mBtnDelLog;
            if (this.mCurPage <= 0) {
                z11 = false;
            }
            paramButton2.Show(z11);
            CanJni.BZSetPage(page);
        }
    }

    public void PageInc() {
        ShowPage((this.mCurPage + 1) % 3);
    }

    public static void DealPage() {
        if (mThis == null) {
            CanFunc.showCanActivity(CanBZDriveInfoActivity.class);
            Log.d(TAG, "Show CanBZDriveInfoActivity");
            return;
        }
        mThis.PageInc();
        Log.d(TAG, "PageInc");
    }
}
