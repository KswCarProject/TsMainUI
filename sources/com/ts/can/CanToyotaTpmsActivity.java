package com.ts.can;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.RelativeLayoutManager;

public class CanToyotaTpmsActivity extends CanToyotaBaseActivity implements UserCallBack, View.OnClickListener {
    public static final String TAG = "CanToyotaTpmsActivity";
    private CustomImgView mCarBg;
    private CustomImgView mCarLine;
    private CustomTextView mDW;
    protected RelativeLayoutManager mManager;
    private int[] mPressure = new int[5];
    private CanDataInfo.ToyotaTpmsInfo mTpmsData = new CanDataInfo.ToyotaTpmsInfo();
    private CustomImgView[] mTyres1;
    private CustomImgView[] mTyres2;
    private CustomTextView[] mValue1;
    private CustomTextView[] mValue2;
    private boolean mbShowLine;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        if (MainSet.GetScreenType() == 3) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
        }
        this.mDW = this.mManager.AddCusText(412, 10, 200, 60);
        SetText(this.mDW);
        this.mDW.setText("kPa");
        this.mCarBg = this.mManager.AddImage(214, 79, R.drawable.tpms_car);
        this.mTyres1 = new CustomImgView[5];
        this.mValue1 = new CustomTextView[5];
        for (int i = 0; i < 4; i++) {
            this.mTyres1[i] = this.mManager.AddImage(((i % 2) * 116) + 441, ((i / 2) * 202) + 125);
            this.mTyres1[i].setStateDrawable(R.drawable.tpms_car_k_up, R.drawable.tpms_car_k_dn);
            this.mValue1[i] = this.mManager.AddCusText(((i % 2) * 410) + 209, ((i / 2) * Can.CAN_LEXUS_IZ) + 123, 209, 60);
            this.mValue1[i].setText("888");
            SetText(this.mValue1[i]);
        }
        this.mTyres1[4] = this.mManager.AddImage(481, 358);
        this.mTyres1[4].setStateDrawable(R.drawable.tpms_car_bk_up, R.drawable.tpms_car_bk_dn);
        this.mValue1[4] = this.mManager.AddCusText(410, 451, 209, 60);
        this.mValue1[4].setText("888");
        SetText(this.mValue1[4]);
        this.mCarLine = this.mManager.AddImage(81, 350, R.drawable.tpms_tires_line);
        this.mTyres2 = new CustomImgView[5];
        this.mValue2 = new CustomTextView[5];
        for (int i2 = 0; i2 < 5; i2++) {
            this.mTyres2[i2] = this.mManager.AddImage((i2 * 182) + 85, Can.CAN_CC_WC);
            this.mTyres2[i2].setStateDrawable(R.drawable.tpms_tires_up, R.drawable.tpms_tires_dn);
            this.mValue2[i2] = this.mManager.AddCusText((i2 * 183) + 81, 290, 132, 60);
            this.mValue2[i2].setText("888");
            SetText(this.mValue2[i2]);
        }
        if (MainSet.GetScreenType() == 3) {
            this.mManager.GetLayout().setScaleX(0.78f);
            this.mManager.GetLayout().setScaleY(0.79f);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void ResetData(boolean check) {
        CanJni.ToyotaGetTpms(this.mTpmsData);
        if (!i2b(this.mTpmsData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTpmsData.Update)) {
            this.mTpmsData.Update = 0;
            showTyres();
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    private void showTyres() {
        TextView[] tvVal;
        boolean bCar = i2b(this.mTpmsData.fgShowOneCar);
        boolean bBak = i2b(this.mTpmsData.fgShowBT);
        boolean bLine = !bCar;
        this.mCarBg.Show(bCar);
        this.mCarLine.Show(bLine);
        int showNum = 5;
        if (bCar && !bBak) {
            showNum = 4;
        }
        if (bLine) {
            tvVal = this.mValue2;
        } else {
            tvVal = this.mValue1;
        }
        for (int i = 0; i < 4; i++) {
            this.mTyres1[i].Show(bCar);
            this.mValue1[i].ShowHide(bCar);
            this.mTyres2[i].Show(bLine);
            this.mValue2[i].ShowHide(bLine);
        }
        this.mTyres1[4].Show(bCar && bBak);
        this.mValue1[4].ShowHide(bCar && bBak);
        this.mTyres2[4].Show(bLine);
        this.mValue2[4].ShowHide(bLine);
        this.mPressure[0] = this.mTpmsData.LFVal;
        this.mPressure[1] = this.mTpmsData.RFVal;
        this.mPressure[2] = this.mTpmsData.LRVal;
        this.mPressure[3] = this.mTpmsData.RRVal;
        this.mPressure[4] = this.mTpmsData.BTVal;
        if (this.mTpmsData.DW < 3) {
            switch (this.mTpmsData.DW) {
                case 0:
                    this.mDW.setText("BAR");
                    for (int i2 = 0; i2 < showNum; i2++) {
                        if (255 == this.mPressure[i2]) {
                            tvVal[i2].setText("--");
                        } else {
                            tvVal[i2].setText(String.format("%.1f", new Object[]{Double.valueOf(0.1d * ((double) this.mPressure[i2]))}));
                        }
                    }
                    return;
                case 1:
                    this.mDW.setText("PSI");
                    for (int i3 = 0; i3 < showNum; i3++) {
                        if (255 == this.mPressure[i3]) {
                            tvVal[i3].setText("--");
                        } else {
                            tvVal[i3].setText(new StringBuilder(String.valueOf(this.mPressure[i3])).toString());
                        }
                    }
                    return;
                case 2:
                    this.mDW.setText("kPA");
                    for (int i4 = 0; i4 < showNum; i4++) {
                        if (255 == this.mPressure[i4]) {
                            tvVal[i4].setText("--");
                        } else {
                            tvVal[i4].setText(String.format("%.1f", new Object[]{Double.valueOf(2.5d * ((double) this.mPressure[i4]))}));
                        }
                    }
                    return;
                default:
                    return;
            }
        } else {
            this.mDW.setText("--");
            for (int i5 = 0; i5 < showNum; i5++) {
                tvVal[i5].setText("--");
            }
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    private void SetText(CustomTextView tv) {
        tv.SetPxSize(60);
        tv.setTextColor(-1);
        tv.setGravity(17);
    }
}
