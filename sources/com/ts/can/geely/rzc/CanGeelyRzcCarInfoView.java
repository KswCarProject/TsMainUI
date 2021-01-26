package com.ts.can.geely.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGeelyRzcCarInfoView extends CanScrollCarInfoView {
    private String[] mChargeArr;
    private CanDataInfo.Geely_ChargeInfo mChargeData;
    private CanDataInfo.Geely_MainTain mMtData;
    private String[] mXsmsArr;
    private CanDataInfo.Geely_XsmsInfo mXsmsData;

    public CanGeelyRzcCarInfoView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_sybylc, R.string.can_xsmsdqzt, R.string.can_cdzzt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mMtData = new CanDataInfo.Geely_MainTain();
        this.mChargeData = new CanDataInfo.Geely_ChargeInfo();
        this.mXsmsData = new CanDataInfo.Geely_XsmsInfo();
    }

    public void ResetData(boolean check) {
        CanJni.GeelyGetMainTain(this.mMtData);
        if (i2b(this.mMtData.UpdateOnce) && (!check || i2b(this.mMtData.Update))) {
            updateItem(0, this.mMtData.Dis, String.format("%d Km", new Object[]{Integer.valueOf(this.mMtData.Dis)}));
        }
        CanJni.GeelyGetXsmsData(this.mXsmsData);
        if (i2b(this.mXsmsData.UpdateOnce) && ((!check || i2b(this.mXsmsData.Update)) && this.mXsmsData.CurSta < this.mXsmsArr.length)) {
            updateItem(1, this.mXsmsData.CurSta, this.mXsmsArr[this.mXsmsData.CurSta]);
        }
        CanJni.GeelyGetChargeData(this.mChargeData);
        if (!i2b(this.mChargeData.UpdateOnce)) {
            return;
        }
        if ((!check || i2b(this.mChargeData.Update)) && this.mChargeData.ChargeSta < this.mChargeArr.length) {
            updateItem(2, this.mChargeData.ChargeSta, this.mChargeArr[this.mChargeData.ChargeSta]);
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        this.mXsmsArr = new String[]{"COMFORT", "ECO", "SPORT"};
        this.mChargeArr = new String[]{getString(R.string.can_cdqwlj), getString(R.string.can_zzcd)};
    }

    public void QueryData() {
        CanJni.GeelyCarQuery(83, 0);
        Sleep(10);
        CanJni.GeelyCarQuery(84, 0);
        Sleep(10);
        CanJni.GeelyCarQuery(96, 0);
    }
}
