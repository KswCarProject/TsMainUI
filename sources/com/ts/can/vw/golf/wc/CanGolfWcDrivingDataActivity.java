package com.ts.can.vw.golf.wc;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.ts.other.ValCal;
import com.yyw.ts70xhw.KeyDef;

public class CanGolfWcDrivingDataActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int BTN_TOP_LEFT = 3;
    public static final int BTN_TOP_RIGHT = 4;
    public static final int PAGE_LTERM = 1;
    public static final int PAGE_REFU = 2;
    public static final int PAGE_START = 0;
    protected static DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    private static final String[] mOilDW = {"L/100km", "km/l", "mpg(UK)", "mpg(US)"};
    protected CanDataInfo.GolfDrivingComm mAvgConData = new CanDataInfo.GolfDrivingComm();
    protected TextView mAvgConsumption;
    protected CanDataInfo.GolfDrivingComm mAvgPowerConData = new CanDataInfo.GolfDrivingComm();
    protected TextView mAvgPowerConsumption;
    protected TextView mAvgSpeed;
    protected CanDataInfo.GolfDrivingComm mAvgSpeedData = new CanDataInfo.GolfDrivingComm();
    protected CustomImgView mBotCar;
    protected ParamButton mBtnTopLeft;
    protected ParamButton mBtnTopRight;
    protected TextView mCenterTitle;
    protected int mCurPage;
    protected TextView mDLRange;
    protected CanDataInfo.GolfRange mDLRangeData = new CanDataInfo.GolfRange();
    protected CanDataInfo.GolfDistance mDisData = new CanDataInfo.GolfDistance();
    protected TextView mDistance;
    protected RelativeLayoutManager mManager;
    protected TextView[] mTextVal;
    protected TextView mTotalRange;
    protected TextView mTravelTime;
    protected CanDataInfo.GolfTravellingTime mTravelTimeData = new CanDataInfo.GolfTravellingTime();
    protected TextView mYLRange;
    protected CanDataInfo.GolfRange mYLRangeData = new CanDataInfo.GolfRange();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        Log.d("nyw", String.format("%d,%d", new Object[]{Integer.valueOf(mDisplayMetrics.widthPixels), Integer.valueOf(mDisplayMetrics.heightPixels)}));
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
        lp.width = 1024;
        lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
        lp.gravity = 17;
        this.mManager.GetLayout().setLayoutParams(lp);
        InitUI();
        this.mManager.GetLayout().setScaleX((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f);
        this.mManager.GetLayout().setScaleY((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f);
        Log.d("nyw", String.format("%.2f,%.2f", new Object[]{Float.valueOf((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f), Float.valueOf((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f)}));
    }

    private void InitUI() {
        this.mCenterTitle = this.mManager.AddText(362, 30, 300, 90);
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
        this.mBotCar = this.mManager.AddImage(0, 450, 1024, 83);
        this.mBotCar.setImageResource(R.drawable.can_golf_car01);
        this.mTextVal = new TextView[8];
        this.mTextVal[0] = this.mManager.AddText(62, 160, Can.CAN_NISSAN_XFY, 60);
        this.mTextVal[1] = this.mManager.AddText(KeyDef.RKEY_MEDIA_OSD, 160, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 60);
        this.mTextVal[2] = this.mManager.AddText(712, 160, 300, 60);
        this.mTextVal[3] = this.mManager.AddText(62, 260, Can.CAN_NISSAN_XFY, 60);
        this.mTextVal[4] = this.mManager.AddText(KeyDef.RKEY_MEDIA_OSD, 260, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 60);
        if (CanJni.GetSubType() != 1) {
            this.mTextVal[5] = this.mManager.AddText(712, 360, 0, 0);
            this.mTextVal[6] = this.mManager.AddText(KeyDef.RKEY_MEDIA_OSD, 360, 0, 0);
            this.mTextVal[7] = this.mManager.AddText(CanCameraUI.BTN_CCH9_MODE3, 440, 0, 0);
        } else {
            this.mManager.AddImageEx(12, 260, 80, 60, R.drawable.can_vw_mqb_yh);
            this.mManager.AddImageEx(12, 360, 80, 60, R.drawable.can_vw_mqb_dh);
            this.mTextVal[5] = this.mManager.AddText(712, 360, 300, 60);
            this.mTextVal[6] = this.mManager.AddText(KeyDef.RKEY_MEDIA_OSD, 360, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 60);
            this.mTextVal[7] = this.mManager.AddText(CanCameraUI.BTN_CCH9_MODE3, 440, 300, 60);
        }
        for (int i = 0; i < this.mTextVal.length; i++) {
            this.mTextVal[i].setTextColor(-1);
            this.mTextVal[i].setTextSize(0, 40.0f);
            this.mTextVal[i].setText("0.0");
            this.mTextVal[i].setGravity(17);
        }
        this.mDistance = this.mTextVal[0];
        this.mAvgSpeed = this.mTextVal[1];
        this.mYLRange = this.mTextVal[2];
        this.mTravelTime = this.mTextVal[3];
        this.mAvgConsumption = this.mTextVal[4];
        this.mDLRange = this.mTextVal[5];
        this.mAvgPowerConsumption = this.mTextVal[6];
        this.mTotalRange = this.mTextVal[7];
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
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
        switch (this.mCurPage) {
            case 0:
                this.mCenterTitle.setText(R.string.can_since_start);
                return;
            case 1:
                this.mCenterTitle.setText(R.string.can_long_term);
                return;
            case 2:
                this.mCenterTitle.setText(R.string.can_since_refu);
                return;
            default:
                return;
        }
    }

    private void ShowPage() {
        ShowTitle();
        ResetData(false);
    }

    private void ResetData(boolean check) {
        switch (this.mCurPage) {
            case 0:
                CanJni.GolfGetDrivingSinSatrt(this.mDisData, this.mAvgConData, this.mAvgSpeedData, this.mTravelTimeData);
                CanJni.GolfGetDrivingDataSinSatrt(this.mYLRangeData, this.mDLRangeData, this.mAvgPowerConData);
                break;
            case 1:
                CanJni.GolfGetDrivingLongTerm(this.mDisData, this.mAvgConData, this.mAvgSpeedData, this.mTravelTimeData);
                CanJni.GolfGetDrivingDataLongTerm(this.mYLRangeData, this.mDLRangeData, this.mAvgPowerConData);
                break;
            case 2:
                CanJni.GolfGetDrivingSinRefu(this.mDisData, this.mAvgConData, this.mAvgSpeedData, this.mTravelTimeData);
                CanJni.GolfGetDrivingDataSinRefu(this.mYLRangeData, this.mDLRangeData, this.mAvgPowerConData);
                break;
        }
        if (!check || i2b(this.mDisData.Update)) {
            this.mDisData.Update = 0;
            this.mDistance.setText(String.format("%.1f km", new Object[]{Float.valueOf(((float) this.mDisData.Value) / 10.0f)}));
            if (!check && !i2b(this.mDisData.UpdateOnce)) {
                CanJni.GolfWcQueryData(1, this.mCurPage + 19);
            }
        }
        if (!check || i2b(this.mAvgSpeedData.Update)) {
            this.mAvgSpeedData.Update = 0;
            this.mAvgSpeed.setText(String.format("Av. %d km/h", new Object[]{Integer.valueOf(this.mAvgSpeedData.Value)}));
            if (!check && !i2b(this.mAvgSpeedData.UpdateOnce)) {
                CanJni.GolfWcQueryData(1, this.mCurPage + 19);
            }
        }
        if (!check || i2b(this.mYLRangeData.Update)) {
            this.mYLRangeData.Update = 0;
            this.mYLRange.setText(String.format("%d km", new Object[]{Integer.valueOf(this.mYLRangeData.Value)}));
            this.mTotalRange.setText(String.format("%d km", new Object[]{Integer.valueOf(this.mYLRangeData.Value + this.mDLRangeData.Value)}));
            if (!check && !i2b(this.mYLRangeData.UpdateOnce)) {
                CanJni.GolfWcQueryData(1, this.mCurPage + 19);
            }
        }
        if (!check || i2b(this.mDLRangeData.Update)) {
            this.mDLRangeData.Update = 0;
            this.mDLRange.setText(String.format("%d km", new Object[]{Integer.valueOf(this.mDLRangeData.Value)}));
            this.mTotalRange.setText(String.format("%d km", new Object[]{Integer.valueOf(this.mYLRangeData.Value + this.mDLRangeData.Value)}));
            if (!check && !i2b(this.mDLRangeData.UpdateOnce)) {
                CanJni.GolfWcQueryData(1, 23);
            }
        }
        if (!check || i2b(this.mTravelTimeData.Update)) {
            this.mTravelTimeData.Update = 0;
            this.mTravelTime.setText(String.format("%02d:%02d h", new Object[]{Integer.valueOf((this.mTravelTimeData.Value / 60) % 100), Integer.valueOf(this.mTravelTimeData.Value % 60)}));
            if (!check && !i2b(this.mTravelTimeData.UpdateOnce)) {
                CanJni.GolfWcQueryData(1, this.mCurPage + 19);
            }
        }
        if (!check || i2b(this.mAvgConData.Update)) {
            this.mAvgConData.Update = 0;
            if (i2b(this.mAvgConData.Avalid)) {
                this.mAvgConsumption.setText(String.format("Av. %.1f %s", new Object[]{Float.valueOf(((float) this.mAvgConData.Value) / 10.0f), mOilDW[this.mAvgConData.DW & 3]}));
            } else {
                this.mAvgConsumption.setText("Av. --.-");
            }
            if (!check && !i2b(this.mAvgConData.UpdateOnce)) {
                CanJni.GolfWcQueryData(1, this.mCurPage + 19);
            }
        }
        if (!check || i2b(this.mAvgPowerConData.Update)) {
            this.mAvgPowerConData.Update = 0;
            this.mAvgPowerConsumption.setText(String.format("Av. %.1f %s", new Object[]{Float.valueOf(((float) this.mAvgPowerConData.Value) / 10.0f), "KWh/100km"}));
            if (!check && !i2b(this.mAvgPowerConData.UpdateOnce)) {
                CanJni.GolfWcQueryData(1, 23);
            }
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
