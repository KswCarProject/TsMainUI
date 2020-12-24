package com.ts.can.honda.accord;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanAccordCameraStatusActivity extends CanAccordBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_MODE_BZ = 2;
    public static final int ITEM_MODE_FJ = 3;
    public static final int ITEM_MODE_GJ = 1;
    protected ParamButton mBtnBz;
    protected ParamButton mBtnFj;
    protected ParamButton mBtnGj;
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.AccordCamMode mModeData = new CanDataInfo.AccordCamMode();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mBtnGj = this.mManager.AddButton(68, Can.CAN_CHANA_CS75_WC);
        this.mBtnGj.setTag(1);
        this.mBtnGj.setDrawable(R.drawable.can_yg_mode_gj_up, R.drawable.can_yg_mode_gj_dn);
        this.mBtnGj.setOnClickListener(this);
        this.mBtnBz = this.mManager.AddButton(387, Can.CAN_CHANA_CS75_WC);
        this.mBtnBz.setTag(2);
        this.mBtnBz.setDrawable(R.drawable.can_yg_mode_bz_up, R.drawable.can_yg_mode_bz_dn);
        this.mBtnBz.setOnClickListener(this);
        this.mBtnFj = this.mManager.AddButton(705, Can.CAN_CHANA_CS75_WC);
        this.mBtnFj.setTag(3);
        this.mBtnFj.setDrawable(R.drawable.can_yg_mode_fj_up, R.drawable.can_yg_mode_fj_dn);
        this.mBtnFj.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Query();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void ResetData(boolean check) {
        boolean z;
        boolean z2 = true;
        CanJni.AccordGetCamMode(this.mModeData);
        if (!i2b(this.mModeData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mModeData.Update)) {
            this.mModeData.Update = 0;
            this.mBtnGj.setSelected(this.mModeData.Mode == 1);
            ParamButton paramButton = this.mBtnBz;
            if (this.mModeData.Mode == 0) {
                z = true;
            } else {
                z = false;
            }
            paramButton.setSelected(z);
            ParamButton paramButton2 = this.mBtnFj;
            if (this.mModeData.Mode != 2) {
                z2 = false;
            }
            paramButton2.setSelected(z2);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        Query(210, 0);
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.AccordCarCtrl(64, 1);
                return;
            case 2:
                CanJni.AccordCarCtrl(64, 0);
                return;
            case 3:
                CanJni.AccordCarCtrl(64, 2);
                return;
            default:
                return;
        }
    }
}
