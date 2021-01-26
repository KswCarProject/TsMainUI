package com.ts.can.toyota.wc;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;

public class CanToyotaWCTpmsActivity extends CanToyotaWCBaseActivity implements UserCallBack, View.OnClickListener {
    public static final String TAG = "CanToyotaTpmsActivity";
    private CustomImgView mCarBg;
    private CustomImgView mCarLine;
    private CustomTextView mDW;
    protected RelativeLayoutManager mManager;
    private int[] mPressure = new int[5];
    private CanDataInfo.ToyotaWcTpmsInfo mTpmsData = new CanDataInfo.ToyotaWcTpmsInfo();
    private CustomImgView[] mTyres1;
    private CustomTextView[] mValue1;
    private boolean mbShowLine;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
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
            this.mValue1[i].setText(TXZResourceManager.STYLE_DEFAULT);
            SetText(this.mValue1[i]);
        }
        this.mTyres1[4] = this.mManager.AddImage(481, 358);
        this.mTyres1[4].setStateDrawable(R.drawable.tpms_car_bk_up, R.drawable.tpms_car_bk_dn);
        this.mValue1[4] = this.mManager.AddCusText(410, 451, 209, 60);
        this.mValue1[4].setText(TXZResourceManager.STYLE_DEFAULT);
        SetText(this.mValue1[4]);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        CarSet(5, 1, 72);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void ResetData(boolean check) {
        CanJni.ToyotaWcGetTpmsInfo(this.mTpmsData);
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
        TextView[] tvVal = this.mValue1;
        this.mPressure[0] = this.mTpmsData.LFVal;
        this.mPressure[1] = this.mTpmsData.RFVal;
        this.mPressure[2] = this.mTpmsData.LRVal;
        this.mPressure[3] = this.mTpmsData.RRVal;
        this.mPressure[4] = this.mTpmsData.BTVal;
        for (int i = 0; i < 4; i++) {
            this.mTyres1[i].SetSel(this.mTpmsData.fgShowWarn);
        }
        this.mDW.setText("kPA");
        for (int i2 = 0; i2 < 5; i2++) {
            if (254 == this.mPressure[i2]) {
                tvVal[i2].setText("--");
            } else {
                tvVal[i2].setText(String.format("%.1f", new Object[]{Double.valueOf(1.0d * ((double) this.mPressure[i2]))}));
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
