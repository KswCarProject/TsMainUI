package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanFordRzcTpmsView extends CanRelativeCarInfoView {
    private CustomImgView mCarBg;
    private CustomImgView mCarLine;
    private CustomTextView mDW;
    protected RelativeLayoutManager mManager;
    private int[] mPressure;
    private CustomTextView[] mTempValue;
    private CanDataInfo.FordRzcDriveData mTpmsData;
    private CustomTextView mTxtPjyh;
    private CustomTextView mTxtSyyl;
    private CustomTextView mTxtXhlc;
    private CustomTextView mTxtXslc;
    private CustomTextView[] mTyreValue;
    private CustomImgView[] mTyres;

    public CanFordRzcTpmsView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_comm_bg);
        this.mManager = getRelativeManager();
        this.mTpmsData = new CanDataInfo.FordRzcDriveData();
        this.mPressure = new int[5];
        this.mTyres = new CustomImgView[5];
        this.mTyreValue = new CustomTextView[5];
        this.mTempValue = new CustomTextView[5];
        this.mDW = this.mManager.AddCusText(412, 10, 200, 60);
        SetText(this.mDW);
        this.mDW.setText("KPA");
        this.mCarBg = this.mManager.AddImage(214, 79, R.drawable.tpms_car);
        for (int i = 0; i < 4; i++) {
            this.mTyres[i] = this.mManager.AddImage(((i % 2) * 116) + 441, ((i / 2) * 202) + 125);
            this.mTyres[i].setStateDrawable(R.drawable.tpms_car_k_up, R.drawable.tpms_car_k_dn);
            this.mTyreValue[i] = this.mManager.AddCusText(((i % 2) * 410) + 209, ((i / 2) * Can.CAN_LEXUS_IZ) + 123, 209, 60);
            this.mTyreValue[i].setText("--");
            SetText(this.mTyreValue[i]);
            this.mTempValue[i] = this.mManager.AddCusText(((i % 2) * 410) + 209, ((i / 2) * Can.CAN_LEXUS_IZ) + 43, 209, 60);
            this.mTempValue[i].setText("-- ℃");
            SetText(this.mTempValue[i]);
        }
        this.mTyres[4] = this.mManager.AddImage(481, 358);
        this.mTyreValue[4] = this.mManager.AddCusText(410, 451, 209, 60);
        SetText(this.mTyreValue[4]);
        this.mTempValue[4] = this.mManager.AddCusText(410, 280, 209, 60);
        SetText(this.mTempValue[4]);
        this.mTxtXslc = this.mManager.AddCusText(CanCameraUI.BTN_CC_WC_DIRECTION1, 420, KeyDef.RKEY_MEDIA_ZOOM, 60);
        SetConsumpText(this.mTxtXslc);
        this.mTxtXhlc = this.mManager.AddCusText(CanCameraUI.BTN_CC_WC_DIRECTION1, 480, KeyDef.RKEY_MEDIA_ZOOM, 60);
        SetConsumpText(this.mTxtXhlc);
        this.mTxtPjyh = this.mManager.AddCusText(10, 420, 359, 60);
        SetConsumpText(this.mTxtPjyh);
        this.mTxtSyyl = this.mManager.AddCusText(10, 480, KeyDef.RKEY_MEDIA_ZOOM, 60);
        SetConsumpText(this.mTxtSyyl);
    }

    public void ResetData(boolean check) {
        CanJni.FordRzcGetCarDriveData(this.mTpmsData);
        if (!i2b(this.mTpmsData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTpmsData.Update)) {
            this.mTpmsData.Update = 0;
            showTyres();
        }
    }

    public void QueryData() {
        CanJni.FordQuery(99, 0);
    }

    private void showTyres() {
        TextView[] tvVal = this.mTyreValue;
        for (int i = 0; i < 4; i++) {
            this.mTyres[i].SetSel(this.mTpmsData.PreSta[i]);
            this.mPressure[i] = this.mTpmsData.PreVal[i];
        }
        this.mDW.setText("KPA");
        for (int i2 = 0; i2 < 4; i2++) {
            if (255 == this.mPressure[i2]) {
                tvVal[i2].setText("--");
            } else {
                tvVal[i2].setText(String.format("%.1f", new Object[]{Double.valueOf(2.75d * ((double) this.mPressure[i2]))}));
            }
        }
        for (int i3 = 0; i3 < 4; i3++) {
            if (255 == this.mTpmsData.TempVal[i3]) {
                this.mTempValue[i3].setText("--");
            } else {
                this.mTempValue[i3].setText(String.format("%d ℃", new Object[]{Integer.valueOf(this.mTpmsData.TempVal[i3] - 60)}));
            }
        }
        this.mTxtXslc.setText(String.valueOf(getString(R.string.can_driving_mileage)) + ": " + this.mTpmsData.Xslc + "KM");
        if (this.mTpmsData.Xhlc > 999) {
            this.mTxtXhlc.setText(String.valueOf(getString(R.string.can_range_xhlc)) + ": --");
        } else {
            this.mTxtXhlc.setText(String.valueOf(getString(R.string.can_range_xhlc)) + ": " + this.mTpmsData.Xhlc + "KM");
        }
        if (((this.mTpmsData.Pjyh * 2) + 1) / 10 > 30) {
            this.mTxtPjyh.setText(String.valueOf(getString(R.string.can_pjyh)) + ": --");
        } else {
            this.mTxtPjyh.setText(String.valueOf(getString(R.string.can_pjyh)) + ": " + String.format("%.1f", new Object[]{Double.valueOf(((double) ((this.mTpmsData.Pjyh * 2) + 1)) * 0.1d)}) + "L/100km");
        }
        if (this.mTpmsData.Syyl / 2 > 100) {
            this.mTxtSyyl.setText(String.valueOf(getString(R.string.can_rest_oil)) + ": --");
            return;
        }
        this.mTxtSyyl.setText(String.valueOf(getString(R.string.can_rest_oil)) + ": " + String.format("%.1f", new Object[]{Double.valueOf(((double) this.mTpmsData.Syyl) * 0.5d)}) + "%");
    }

    private void SetText(CustomTextView tv) {
        tv.SetPxSize(60);
        tv.setTextColor(-1);
        tv.setGravity(17);
    }

    private void SetConsumpText(CustomTextView tv) {
        tv.SetPxSize(40);
        tv.setTextColor(-1);
        tv.setMaxLines(1);
        tv.setGravity(19);
    }
}
