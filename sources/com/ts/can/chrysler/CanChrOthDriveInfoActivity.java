package com.ts.can.chrysler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.main.common.MainSet;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanChrOthDriveInfoActivity extends CanChrOthBaseActivity implements UserCallBack, View.OnClickListener {
    public static final int ITEM_DEL_LOG = 3;
    public static final int ITEM_PAGE_0 = 0;
    public static final int ITEM_PAGE_1 = 1;
    public static final int ITEM_PAGE_2 = 2;
    public static final String TAG = "CanChrOthDriveInfoActivity";
    private static CanChrOthDriveInfoActivity mThis;
    private ParamButton mBtnDelLog;
    private ParamButton[] mBtnLt = new ParamButton[3];
    private int mChrFuelQueryTiem = 0;
    private CanDataInfo.ChrOthConsumpCur mConsumpCur = new CanDataInfo.ChrOthConsumpCur();
    private CanDataInfo.ChrOthConsumpRang mConsumpRang = new CanDataInfo.ChrOthConsumpRang();
    private CanDataInfo.ChrOthTripAConsump mConsumpTripa = new CanDataInfo.ChrOthTripAConsump();
    private CanDataInfo.ChrOthTripBConsump mConsumpTripb = new CanDataInfo.ChrOthTripBConsump();
    private int mCurPage = -1;
    private CustomTextView[] mIvIco0 = new CustomTextView[3];
    private CustomTextView[] mIvIco1 = new CustomTextView[5];
    private CustomTextView[] mIvIco2 = new CustomTextView[5];
    protected RelativeLayoutManager mManager;
    private CanDataInfo.ChrOthTripARange mTripaRang = new CanDataInfo.ChrOthTripARange();
    private CanDataInfo.ChrOthTripASpeed mTripaSpeed = new CanDataInfo.ChrOthTripASpeed();
    private CanDataInfo.ChrOthTripATime mTripaTime = new CanDataInfo.ChrOthTripATime();
    private CanDataInfo.ChrOthTripBRange mTripbRang = new CanDataInfo.ChrOthTripBRange();
    private CanDataInfo.ChrOthTripBSpeed mTripbSpeed = new CanDataInfo.ChrOthTripBSpeed();
    private CanDataInfo.ChrOthTripBTime mTripbTime = new CanDataInfo.ChrOthTripBTime();
    private CustomTextView[] mTvDW0 = new CustomTextView[3];
    private CustomTextView[] mTvDW1 = new CustomTextView[5];
    private CustomTextView[] mTvDW2 = new CustomTextView[5];
    private CustomTextView[] mTvVal0 = new CustomTextView[3];
    private CustomTextView[] mTvVal1 = new CustomTextView[5];
    private CustomTextView[] mTvVal2 = new CustomTextView[5];

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        if (MainSet.GetScreenType() == 3) {
            initScreen_768x1024();
        } else {
            initCommonScreen();
        }
    }

    private void initScreen_768x1024() {
        this.mManager.AddImage(0, 0, R.drawable.can_psa_bg);
        this.mBtnLt[0] = AddBtn(0, 5, 30, R.drawable.can_psa_car_up, R.drawable.can_psa_car_dn);
        this.mBtnLt[1] = AddBtn(1, 5, Can.CAN_CHANA_CS75_WC, R.drawable.can_psa_01_up, R.drawable.can_psa_01_dn);
        this.mBtnLt[2] = AddBtn(2, 5, 290, R.drawable.can_psa_02_up, R.drawable.can_psa_02_dn);
        this.mBtnDelLog = this.mManager.AddButton(507, KeyDef.RKEY_RDS_TA, 180, 60);
        this.mBtnDelLog.setTextSize(0, 26.0f);
        SetCommBtn(this.mBtnDelLog, R.string.can_clear, 3, this);
        this.mIvIco0[0] = AddText(240, 38, 300, 60, 55);
        this.mIvIco0[0].setText(R.string.can_jsxx);
        this.mIvIco0[1] = AddText(240, Can.CAN_HYUNDAI_WC, 350, 60, 40);
        this.mIvIco0[1].setText(R.string.can_range);
        this.mIvIco0[2] = AddText(240, 203, 350, 60, 40);
        this.mIvIco0[2].setText(R.string.can_cur_consump);
        this.mTvVal0[0] = AddText(CanCameraUI.BTN_CHANA_CS75_MODE1, Can.CAN_BJ20_WC, 100, 60, 40);
        this.mTvDW0[0] = AddText(670, Can.CAN_BJ20_WC, 100, 60, 38);
        this.mTvVal0[1] = AddText(CanCameraUI.BTN_CHANA_CS75_MODE1, 208, 100, 60, 40);
        this.mTvDW0[1] = AddText(670, 208, 100, 60, 38);
        this.mIvIco1[0] = AddText(240, 38, 300, 60, 55);
        this.mIvIco1[0].setText(R.string.can_trip_a);
        this.mIvIco1[1] = AddText(240, 128, 350, 60, 40);
        this.mIvIco1[1].setText(R.string.can_pjyh);
        this.mIvIco1[2] = AddText(240, 178, 350, 60, 40);
        this.mIvIco1[2].setText(R.string.can_avg_speed);
        this.mIvIco1[3] = AddText(240, Can.CAN_TEANA_OLD_DJ, 350, 60, 40);
        this.mIvIco1[3].setText(R.string.can_dis_trav);
        this.mIvIco1[4] = AddText(240, 278, 350, 60, 40);
        this.mIvIco1[4].setText(R.string.can_trav_time);
        this.mIvIco2[0] = AddText(240, 38, 300, 60, 55);
        this.mIvIco2[0].setText(R.string.can_display_t_b);
        this.mIvIco2[1] = AddText(240, 128, 350, 60, 40);
        this.mIvIco2[1].setText(R.string.can_pjyh);
        this.mIvIco2[2] = AddText(240, 178, 350, 60, 40);
        this.mIvIco2[2].setText(R.string.can_avg_speed);
        this.mIvIco2[3] = AddText(240, Can.CAN_TEANA_OLD_DJ, 350, 60, 40);
        this.mIvIco2[3].setText(R.string.can_dis_trav);
        this.mIvIco2[4] = AddText(240, 278, 350, 60, 40);
        this.mIvIco2[4].setText(R.string.can_trav_time);
        for (int i = 0; i < 4; i++) {
            this.mTvVal1[i] = AddText(500, (i * 50) + 128, 100, 60, 40);
            this.mTvDW1[i] = AddText(CanCameraUI.BTN_GOLF_WC_MODE1, (i * 50) + 128, 100, 60, 38);
            this.mTvVal2[i] = AddText(500, (i * 50) + 128, 100, 60, 40);
            this.mTvDW2[i] = AddText(CanCameraUI.BTN_GOLF_WC_MODE1, (i * 50) + 128, 100, 60, 38);
        }
        this.mTvDW0[0].setText("MI");
        this.mTvDW0[1].setText("mpg");
        this.mTvDW1[0].setText("MPG");
        this.mTvDW1[1].setText("MPH");
        this.mTvDW1[2].setText("MI");
        this.mTvDW2[0].setText("MPG");
        this.mTvDW2[1].setText("MPH");
        this.mTvDW2[2].setText("MI");
        ShowPage(0);
    }

    private void initCommonScreen() {
        this.mManager.AddImage(35, 17, R.drawable.can_psa_bg);
        this.mBtnLt[0] = AddBtn(0, 66, 41, R.drawable.can_psa_car_up, R.drawable.can_psa_car_dn);
        this.mBtnLt[1] = AddBtn(1, 66, Can.CAN_LEXUS_ZMYT, R.drawable.can_psa_01_up, R.drawable.can_psa_01_dn);
        this.mBtnLt[2] = AddBtn(2, 66, 370, R.drawable.can_psa_02_up, R.drawable.can_psa_02_dn);
        this.mBtnDelLog = this.mManager.AddButton(CanCameraUI.BTN_GEELY_YJX6_FXP, 408, Can.CAN_LEXUS_IZ, 77);
        SetCommBtn(this.mBtnDelLog, R.string.can_clear, 3, this);
        this.mIvIco0[0] = AddText(284, 38, 300, 60, 55);
        this.mIvIco0[0].setText(R.string.can_jsxx);
        this.mIvIco0[1] = AddText(284, Can.CAN_BJ20_WC, 350, 60, 40);
        this.mIvIco0[1].setText(R.string.can_range);
        this.mIvIco0[2] = AddText(284, 208, 350, 60, 40);
        this.mIvIco0[2].setText(R.string.can_cur_consump);
        this.mTvVal0[0] = AddText(CanCameraUI.BTN_GOLF_WC_MODE1, Can.CAN_BJ20_WC, 100, 60, 40);
        this.mTvDW0[0] = AddText(CanCameraUI.BTN_CC_WC_DIRECTION1, Can.CAN_BJ20_WC, 100, 60, 38);
        this.mTvVal0[1] = AddText(CanCameraUI.BTN_GOLF_WC_MODE1, 208, 100, 60, 40);
        this.mTvDW0[1] = AddText(CanCameraUI.BTN_CC_WC_DIRECTION1, 208, 100, 60, 38);
        this.mIvIco1[0] = AddText(284, 38, 300, 60, 55);
        this.mIvIco1[0].setText(R.string.can_trip_a);
        this.mIvIco1[1] = AddText(284, Can.CAN_BJ20_WC, 350, 60, 40);
        this.mIvIco1[1].setText(R.string.can_pjyh);
        this.mIvIco1[2] = AddText(284, 208, 350, 60, 40);
        this.mIvIco1[2].setText(R.string.can_avg_speed);
        this.mIvIco1[3] = AddText(284, 258, 350, 60, 40);
        this.mIvIco1[3].setText(R.string.can_dis_trav);
        this.mIvIco1[4] = AddText(284, KeyDef.RKEY_MEDIA_MENU, 350, 60, 40);
        this.mIvIco1[4].setText(R.string.can_trav_time);
        this.mIvIco2[0] = AddText(284, 38, 300, 60, 55);
        this.mIvIco2[0].setText(R.string.can_display_t_b);
        this.mIvIco2[1] = AddText(284, Can.CAN_BJ20_WC, 350, 60, 40);
        this.mIvIco2[1].setText(R.string.can_pjyh);
        this.mIvIco2[2] = AddText(284, 208, 350, 60, 40);
        this.mIvIco2[2].setText(R.string.can_avg_speed);
        this.mIvIco2[3] = AddText(284, 258, 350, 60, 40);
        this.mIvIco2[3].setText(R.string.can_dis_trav);
        this.mIvIco2[4] = AddText(284, KeyDef.RKEY_MEDIA_MENU, 350, 60, 40);
        this.mIvIco2[4].setText(R.string.can_trav_time);
        for (int i = 0; i < 4; i++) {
            this.mTvVal1[i] = AddText(CanCameraUI.BTN_GOLF_WC_MODE1, (i * 50) + Can.CAN_BJ20_WC, 100, 60, 40);
            this.mTvDW1[i] = AddText(CanCameraUI.BTN_CC_WC_DIRECTION1, (i * 50) + Can.CAN_BJ20_WC, 100, 60, 38);
            this.mTvVal2[i] = AddText(CanCameraUI.BTN_GOLF_WC_MODE1, (i * 50) + Can.CAN_BJ20_WC, 100, 60, 40);
            this.mTvDW2[i] = AddText(CanCameraUI.BTN_CC_WC_DIRECTION1, (i * 50) + Can.CAN_BJ20_WC, 100, 60, 38);
        }
        this.mTvDW0[0].setText("MI");
        this.mTvDW0[1].setText("mpg");
        this.mTvDW1[0].setText("MPG");
        this.mTvDW1[1].setText("MPH");
        this.mTvDW1[2].setText("MI");
        this.mTvDW2[0].setText("MPG");
        this.mTvDW2[1].setText("MPH");
        this.mTvDW2[2].setText("MI");
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
        text.setText("--");
        text.SetPxSize(size);
        text.setGravity(3);
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
                return "L/100";
            case 1:
                return "KM/L";
            case 2:
            case 3:
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
        if (1 == dw) {
            return "MI";
        }
        return "KM";
    }

    /* access modifiers changed from: protected */
    public String GetSpeedDW(int dw) {
        if (2 == dw || 3 == dw) {
            return "MPH";
        }
        return "KM/H";
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        Query(7, 0);
        Sleep(40);
        Query(64, 1);
        ResetData(false);
        mThis = this;
        this.mChrFuelQueryTiem = 0;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        mThis = null;
    }

    private void ResetData(boolean check) {
        GetSetData();
        if (i2b(this.mSetData.UnitsUpdateOnce) && (!check || i2b(this.mSetData.UnitsUpdate))) {
            check = false;
            this.mSetData.UnitsUpdate = 0;
            Log.d(TAG, "mSetData.DWDistance =" + this.mSetData.DWDistance);
            Log.d(TAG, "mSetData.DWFuel =" + this.mSetData.DWFuel);
            this.mTvDW0[0].setText(GetRangeDW(this.mSetData.DWDistance));
            this.mTvDW0[1].setText(GetConsumpDW(this.mSetData.DWFuel));
            this.mTvDW1[0].setText(GetConsumpDW(this.mSetData.DWFuel));
            this.mTvDW1[1].setText(GetSpeedDW(this.mSetData.DWFuel));
            this.mTvDW1[2].setText(GetRangeDW(this.mSetData.DWDistance));
            this.mTvDW2[0].setText(GetConsumpDW(this.mSetData.DWFuel));
            this.mTvDW2[1].setText(GetSpeedDW(this.mSetData.DWFuel));
            this.mTvDW2[2].setText(GetRangeDW(this.mSetData.DWDistance));
        }
        CanJni.ChrOthGetCurFuel(this.mConsumpRang, this.mConsumpCur);
        if (i2b(this.mConsumpRang.UpdateOnce) && (!check || i2b(this.mConsumpRang.Update))) {
            this.mConsumpRang.Update = 0;
            this.mTvVal0[0].setText(String.format("%d", new Object[]{Integer.valueOf(this.mConsumpRang.Val)}));
        }
        if (i2b(this.mConsumpCur.UpdateOnce) && (!check || i2b(this.mConsumpCur.Update))) {
            this.mConsumpCur.Update = 0;
            if (this.mConsumpCur.Data[1] == 255 && this.mConsumpCur.Data[0] == 255) {
                this.mTvVal0[1].setText("--");
            } else {
                this.mTvVal0[1].setText(String.format("%d.%d", new Object[]{Integer.valueOf(this.mConsumpCur.Data[0]), Integer.valueOf(this.mConsumpCur.Data[1])}));
            }
        }
        CanJni.ChrOthGetTripAFuel(this.mConsumpTripa, this.mTripaSpeed, this.mTripaRang, this.mTripaTime);
        if (i2b(this.mConsumpTripa.UpdateOnce) && (!check || i2b(this.mConsumpTripa.Update))) {
            this.mConsumpTripa.Update = 0;
            if (this.mConsumpTripa.Data[1] == 255 && this.mConsumpTripa.Data[0] == 255) {
                this.mTvVal1[0].setText("--");
            } else {
                this.mTvVal1[0].setText(String.format("%d.%d", new Object[]{Integer.valueOf(this.mConsumpTripa.Data[0]), Integer.valueOf(this.mConsumpTripa.Data[1])}));
            }
        }
        if (i2b(this.mTripaSpeed.UpdateOnce) && (!check || i2b(this.mTripaSpeed.Update))) {
            this.mTripaSpeed.Update = 0;
            if (this.mTripaSpeed.Val == 65535) {
                this.mTvVal1[1].setText("--");
            } else {
                this.mTvVal1[1].setText(String.format("%d", new Object[]{Integer.valueOf(this.mTripaSpeed.Val)}));
            }
        }
        if (i2b(this.mTripaRang.UpdateOnce) && (!check || i2b(this.mTripaRang.Update))) {
            this.mTripaRang.Update = 0;
            if (this.mTripaRang.Val == 16777215) {
                this.mTvVal1[2].setText("--");
            } else {
                this.mTvVal1[2].setText(String.format("%.1f", new Object[]{Double.valueOf(((double) this.mTripaRang.Val) / 10.0d)}));
            }
        }
        if (i2b(this.mTripaTime.UpdateOnce) && (!check || i2b(this.mTripaTime.Update))) {
            this.mTripaTime.Update = 0;
            this.mTvVal1[3].setText(String.format("%d:%d", new Object[]{Integer.valueOf((this.mTripaTime.Data[0] * 256) + this.mTripaTime.Data[1]), Integer.valueOf(this.mTripaTime.Data[2])}));
        }
        CanJni.ChrOthGetTripBFuel(this.mConsumpTripb, this.mTripbSpeed, this.mTripbRang, this.mTripbTime);
        if (i2b(this.mConsumpTripb.UpdateOnce) && (!check || i2b(this.mConsumpTripb.Update))) {
            this.mConsumpTripb.Update = 0;
            if (this.mConsumpTripb.Data[1] == 255 && this.mConsumpTripb.Data[0] == 255) {
                this.mTvVal2[0].setText("--");
            } else {
                this.mTvVal2[0].setText(String.format("%d.%d", new Object[]{Integer.valueOf(this.mConsumpTripb.Data[0]), Integer.valueOf(this.mConsumpTripb.Data[1])}));
            }
        }
        if (i2b(this.mTripbSpeed.UpdateOnce) && (!check || i2b(this.mTripbSpeed.Update))) {
            this.mTripbSpeed.Update = 0;
            if (this.mTripbSpeed.Val == 65535) {
                this.mTvVal2[1].setText("--");
            } else {
                this.mTvVal2[1].setText(String.format("%d", new Object[]{Integer.valueOf(this.mTripbSpeed.Val)}));
            }
        }
        if (i2b(this.mTripbRang.UpdateOnce) && (!check || i2b(this.mTripbRang.Update))) {
            this.mTripbRang.Update = 0;
            if (this.mTripbRang.Val == 16777215) {
                this.mTvVal2[2].setText("--");
            } else {
                this.mTvVal2[2].setText(String.format("%.1f", new Object[]{Double.valueOf(((double) this.mTripbRang.Val) / 10.0d)}));
            }
        }
        if (!i2b(this.mTripbTime.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTripbTime.Update)) {
            this.mTripbTime.Update = 0;
            this.mTvVal2[3].setText(String.format("%d:%d", new Object[]{Integer.valueOf((this.mTripbTime.Data[0] * 256) + this.mTripbTime.Data[1]), Integer.valueOf(this.mTripbTime.Data[2])}));
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
                    CarSet(10, 1);
                    return;
                } else {
                    CarSet(11, 1);
                    return;
                }
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
        this.mChrFuelQueryTiem++;
        if (this.mChrFuelQueryTiem == 1) {
            Query(7, 1);
        } else if (this.mChrFuelQueryTiem == 10) {
            Query(7, 2);
        } else if (this.mChrFuelQueryTiem == 20) {
            Query(7, 3);
        } else if (this.mChrFuelQueryTiem == 30) {
            Query(7, 4);
        } else if (this.mChrFuelQueryTiem == 40) {
            Query(7, 5);
        } else if (this.mChrFuelQueryTiem == 50) {
            Query(7, 6);
        } else if (this.mChrFuelQueryTiem == 60) {
            Query(7, 7);
        } else if (this.mChrFuelQueryTiem == 70) {
            Query(7, 8);
        } else if (this.mChrFuelQueryTiem == 80) {
            Query(7, 9);
        } else if (this.mChrFuelQueryTiem == 90) {
            Query(7, 10);
        } else if (this.mChrFuelQueryTiem >= 100) {
            this.mChrFuelQueryTiem = 0;
        }
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
            for (int i = 0; i < 2; i++) {
                CustomTextView customTextView = this.mTvVal0[i];
                if (this.mCurPage == 0) {
                    z9 = true;
                } else {
                    z9 = false;
                }
                customTextView.ShowGone(z9);
                CustomTextView customTextView2 = this.mTvDW0[i];
                if (this.mCurPage == 0) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                customTextView2.ShowGone(z10);
            }
            for (int i2 = 0; i2 < 3; i2++) {
                CustomTextView customTextView3 = this.mIvIco0[i2];
                if (this.mCurPage == 0) {
                    z7 = true;
                } else {
                    z7 = false;
                }
                customTextView3.ShowGone(z7);
                ParamButton paramButton = this.mBtnLt[i2];
                if (i2 == this.mCurPage) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                paramButton.setSelected(z8);
            }
            for (int i3 = 0; i3 < 5; i3++) {
                CustomTextView customTextView4 = this.mIvIco1[i3];
                if (this.mCurPage == 1) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                customTextView4.ShowGone(z5);
                CustomTextView customTextView5 = this.mIvIco2[i3];
                if (this.mCurPage == 2) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                customTextView5.ShowGone(z6);
            }
            for (int i4 = 0; i4 < 4; i4++) {
                CustomTextView customTextView6 = this.mTvVal1[i4];
                if (this.mCurPage == 1) {
                    z = true;
                } else {
                    z = false;
                }
                customTextView6.ShowGone(z);
                CustomTextView customTextView7 = this.mTvDW1[i4];
                if (this.mCurPage == 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                customTextView7.ShowGone(z2);
                CustomTextView customTextView8 = this.mTvVal2[i4];
                if (this.mCurPage == 2) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                customTextView8.ShowGone(z3);
                CustomTextView customTextView9 = this.mTvDW2[i4];
                if (this.mCurPage == 2) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                customTextView9.ShowGone(z4);
            }
            this.mTvDW1[3].ShowGone(false);
            this.mTvDW2[3].ShowGone(false);
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

    public static void DealPage() {
        if (mThis == null) {
            CanFunc.showCanActivity(CanChrOthDriveInfoActivity.class);
            Log.d(TAG, "Show CanChrOthDriveInfoActivity");
            return;
        }
        mThis.PageInc();
        Log.d(TAG, "PageInc");
    }
}
