package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.os.Bundle;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanGolfWcReportsActivity extends CanBaseActivity implements UserCallBack {
    private static String[] mStrReports;
    private CanItemTextBtnList[] mItemReports = new CanItemTextBtnList[16];
    private CanScrollList mManager;
    private CanDataInfo.GolfWcVehicleWarn mVehicleWarn = new CanDataInfo.GolfWcVehicleWarn();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        getReportsArrays(this);
        this.mManager = new CanScrollList(this);
        for (int i = 0; i < this.mItemReports.length; i++) {
            this.mItemReports[i] = new CanItemTextBtnList((Context) this, 0);
            this.mItemReports[i].ShowArrow(false);
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GolfWcGetVehicleWarn(this.mVehicleWarn);
        if (!i2b(this.mVehicleWarn.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mVehicleWarn.Update)) {
            this.mVehicleWarn.Update = 0;
            this.mManager.RemoveAllViews();
            int num = this.mVehicleWarn.Num;
            int[] indexs = this.mVehicleWarn.Warn;
            if (num >= 0 && num <= 16) {
                for (int i = 0; i < num; i++) {
                    if (indexs[i] < mStrReports.length) {
                        this.mItemReports[i].SetVal(mStrReports[indexs[i]]);
                        this.mManager.AddView(this.mItemReports[i].GetView());
                    }
                }
            }
        }
    }

    public static String[] getReportsArrays(Context context) {
        if (mStrReports == null) {
            mStrReports = context.getResources().getStringArray(R.array.can_golf_wc_vehicle_warn);
        }
        return mStrReports;
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 116);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void UserAll() {
        ResetData(true);
    }
}
