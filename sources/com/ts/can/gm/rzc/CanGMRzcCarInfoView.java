package com.ts.can.gm.rzc;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
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

public class CanGMRzcCarInfoView extends CanRelativeCarInfoView {
    protected static DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    private CustomImgView mCarBg;
    private CustomImgView mCarLine;
    private CustomTextView mDW;
    protected RelativeLayoutManager mManager;
    private int[] mPressure;
    private CustomTextView[] mTempValue;
    private CanDataInfo.GM_CarInfo3 mTpmsData;
    private CustomTextView mTxtPjyh;
    private CustomTextView mTxtSyyl;
    private CustomTextView mTxtXhlc;
    private CustomTextView mTxtXslc;
    private CustomTextView[] mTyreValue;
    private CustomImgView[] mTyres;
    private CanDataInfo.GM_CarInfo3 mYhData;

    public CanGMRzcCarInfoView(Activity activity) {
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
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        Log.d("nyw", String.format("%d,%d", new Object[]{Integer.valueOf(mDisplayMetrics.widthPixels), Integer.valueOf(mDisplayMetrics.heightPixels)}));
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
        lp.width = 1024;
        lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
        lp.gravity = 17;
        this.mManager.GetLayout().setLayoutParams(lp);
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
            SetText(this.mTempValue[i]);
        }
        this.mTyres[4] = this.mManager.AddImage(481, 358);
        this.mTyreValue[4] = this.mManager.AddCusText(410, 451, 209, 60);
        SetText(this.mTyreValue[4]);
        this.mTempValue[4] = this.mManager.AddCusText(410, 280, 209, 60);
        SetText(this.mTempValue[4]);
        this.mTxtXslc = this.mManager.AddCusText(CanCameraUI.BTN_CC_WC_DIRECTION1, 420, 309, 60);
        SetConsumpText(this.mTxtXslc);
        this.mTxtXhlc = this.mManager.AddCusText(CanCameraUI.BTN_CC_WC_DIRECTION1, 480, 309, 60);
        SetConsumpText(this.mTxtXhlc);
        this.mTxtPjyh = this.mManager.AddCusText(10, 480, 359, 60);
        SetConsumpText(this.mTxtPjyh);
        this.mTxtSyyl = this.mManager.AddCusText(10, 480, 309, 60);
        SetConsumpText(this.mTxtSyyl);
        this.mTpmsData = new CanDataInfo.GM_CarInfo3();
        this.mYhData = new CanDataInfo.GM_CarInfo3();
        this.mManager.GetLayout().setScaleX((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f);
        this.mManager.GetLayout().setScaleY((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f);
        Log.d("nyw", String.format("%.2f,%.2f", new Object[]{Float.valueOf((((float) mDisplayMetrics.widthPixels) * 1.0f) / 1024.0f), Float.valueOf((((float) mDisplayMetrics.heightPixels) * 1.0f) / 600.0f)}));
    }

    public void ResetData(boolean check) {
        CanJni.GmRzcGetCarInfo3(this.mTpmsData, 0);
        if (i2b(this.mTpmsData.TpmsUpdateOnce) && (!check || i2b(this.mTpmsData.TpmsUpdate))) {
            this.mTpmsData.TpmsUpdate = 0;
            showTyres(this.mTpmsData);
        }
        CanJni.GmRzcGetCarInfo3(this.mYhData, 1);
        if (!i2b(this.mYhData.YhUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mYhData.YhUpdate)) {
            this.mYhData.YhUpdate = 0;
            showYh(this.mYhData);
        }
    }

    private void showYh(CanDataInfo.GM_CarInfo3 mYhData2) {
        this.mTxtXhlc.setText(String.valueOf(getString(R.string.can_range_xhlc)) + ": " + mYhData2.Xhlc + " KM");
        this.mTxtPjyh.setText(String.valueOf(getString(R.string.can_pjyh)) + ": " + String.format("%.1f L/100km", new Object[]{Double.valueOf(((double) mYhData2.Pjyh) * 0.1d)}));
    }

    public void QueryData() {
    }

    private void showTyres(CanDataInfo.GM_CarInfo3 mTpmsData2) {
        TextView[] tvVal = this.mTyreValue;
        for (int i = 0; i < 4; i++) {
            this.mPressure[i] = mTpmsData2.TpmsVal[i];
        }
        if (mTpmsData2.TpmsDw == 0) {
            this.mDW.setText("KPA");
        }
        for (int i2 = 0; i2 < 4; i2++) {
            if (255 < this.mPressure[i2]) {
                tvVal[i2].setText("--");
            } else {
                tvVal[i2].setText(String.format("%.1f", new Object[]{Double.valueOf(4.0d * ((double) this.mPressure[i2]))}));
            }
        }
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
