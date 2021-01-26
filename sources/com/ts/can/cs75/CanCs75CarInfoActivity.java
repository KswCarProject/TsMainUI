package com.ts.can.cs75;

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
import com.txznet.sdk.TXZResourceManager;

public class CanCs75CarInfoActivity extends CanBaseActivity implements UserCallBack {
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
        addIcon(420, 50, 124, Can.CAN_ZH_WC, R.drawable.can_door_car);
        addText(350, 300, "剩余保养里程：");
        this.mTripRest = addText(CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 350, "0 KM");
        this.mTripRest.setTextSize(0, 45.0f);
        this.mOilMark = addText(350, 430, TXZResourceManager.STYLE_DEFAULT);
    }

    private TextView addText(int x, int y, String textId) {
        TextView tv = this.mManager.AddText(x, y);
        tv.setText(textId);
        tv.setTextSize(0, 38.0f);
        tv.setTextColor(-1);
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
            if (this.mData.fgFuelLow == 0) {
                this.mOilMark.setText("油量：正常");
            } else {
                this.mOilMark.setText("油量：低");
            }
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
