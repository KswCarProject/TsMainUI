package com.ts.can.chana.cs75;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanCs75CarDisplayInfoActivity extends CanBaseActivity implements UserCallBack {
    private CanDataInfo.CS75Data mData = new CanDataInfo.CS75Data();
    private RelativeLayoutManager mManager;
    private TextView mOilMark;
    private TextView mTripRest;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        initViews();
    }

    private void initViews() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        addIcon(450, 50, 124, Can.CAN_ZH_WC, R.drawable.can_door_car);
        addText(100, 300, (int) KeyDef.SKEY_PP_1, 50, R.string.can_sybylc);
        this.mTripRest = addText((int) CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 350, (int) Can.CAN_ZH_H530, 70, "-- KM");
        this.mTripRest.setTextSize(0, 45.0f);
        this.mOilMark = addText(100, 430, (int) KeyDef.SKEY_PP_1, 50, "");
    }

    private TextView addText(int x, int y, int w, int h, String textId) {
        TextView tv = this.mManager.AddText(x, y, w, h);
        tv.setText(textId);
        tv.setTextSize(0, 38.0f);
        tv.setTextColor(-1);
        tv.setGravity(17);
        return tv;
    }

    private TextView addText(int x, int y, int w, int h, int textId) {
        TextView tv = this.mManager.AddText(x, y, w, h);
        tv.setText(textId);
        tv.setTextSize(0, 38.0f);
        tv.setTextColor(-1);
        tv.setGravity(17);
        return tv;
    }

    private ImageView addIcon(int x, int y, int w, int h, int resId) {
        return this.mManager.AddImageEx(x, y, w, h, resId);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        String str_s;
        CanJni.Cs75GetData(this.mData);
        if (!i2b(this.mData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mData.Update)) {
            this.mData.Update = 0;
            int range = this.mData.Range;
            if (range >= 0 && range <= 8191) {
                this.mTripRest.setText(String.valueOf(range) + " KM");
            }
            int oilMark = this.mData.fgFuelLow;
            String str = getString(R.string.can_oil_quantity);
            if (oilMark == 0) {
                str_s = getString(R.string.can_oil_status_normal);
            } else {
                str_s = getString(R.string.can_mzd_cx4_voice_low);
            }
            this.mOilMark.setText(String.valueOf(str) + ": " + str_s);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
