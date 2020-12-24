package com.ts.can.baic.ec180;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoVal;
import com.ts.canview.CanScrollList;

public class CanBaicEC180DriveInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_CARSTA = 2;
    public static final int ITEM_CO2VAL = 2;
    public static final int ITEM_DISTANCE = 1;
    private static final int ITEM_MAX = 2;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanBaicEC180DriveInfoActivity";
    protected CanDataInfo.BaicEcInfo mEcInfo = new CanDataInfo.BaicEcInfo();
    protected CanItemIcoVal mItemCarSta;
    protected CanItemIcoVal mItemCo2val;
    protected CanItemIcoVal mItemDistance;
    private CanScrollList mManager;
    protected String[] mStaArr = {"停机", "就绪", "驱动", "能量回收"};
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.BaicEcGetCarInfo(this.mEcInfo);
        if (!i2b(this.mEcInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mEcInfo.Update)) {
            this.mEcInfo.Update = 0;
            this.mItemDistance.SetVal(String.format("%d Km", new Object[]{Integer.valueOf(this.mEcInfo.Distance)}));
            this.mItemCo2val.SetVal(String.format("%d Kg", new Object[]{Integer.valueOf(this.mEcInfo.Co2Val)}));
            this.mItemCarSta.SetVal(String.format("%s", new Object[]{this.mStaArr[this.mEcInfo.CarSta & 3]}));
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.BaicEcQuery(64);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemDistance = AddIcoValItem(R.drawable.can_icon_tyres, R.string.can_yslc, 1);
        this.mItemCo2val = AddIcoValItem(R.drawable.can_golf_icon05, R.string.can_jsco2_pfl, 2);
        this.mItemCarSta = AddIcoValItem(R.drawable.can_icon_setup, R.string.can_vehi_status, 2);
    }

    /* access modifiers changed from: protected */
    public CanItemIcoVal AddIcoValItem(int ico, int text, int id) {
        CanItemIcoVal item = new CanItemIcoVal(this, ico, text);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 2; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        return i2b(0);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean IsHaveItem = IsHaveItem(item);
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }
}
