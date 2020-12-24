package com.ts.can.psa.pg307;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;

public class CanPg307CarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_DISTANCE_XSLC = 2;
    public static final int ITEM_DSITANCE_YSLC = 1;
    private static final int ITEM_MAX = 6;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_PJCS = 5;
    public static final int ITEM_PJYH = 3;
    public static final int ITEM_SSCS = 6;
    public static final int ITEM_SSYH = 4;
    public static final String TAG = "CanPg307CarInfoActivity";
    private CanItemTitleValList mItemPjcs;
    private CanItemTitleValList mItemPjyh;
    private CanItemTitleValList mItemSscs;
    private CanItemTitleValList mItemSsyh;
    private CanItemTitleValList mItemXslc;
    private CanItemTitleValList mItemYslc;
    private CanScrollList mManager;
    private CanDataInfo.Pg2xPjcs mPjcs = new CanDataInfo.Pg2xPjcs();
    private CanDataInfo.Pg2xPjyh mPjyh = new CanDataInfo.Pg2xPjyh();
    private CanDataInfo.Pg2xSscs mSscs = new CanDataInfo.Pg2xSscs();
    private CanDataInfo.Pg2xSsyh mSsyh = new CanDataInfo.Pg2xSsyh();
    private CanDataInfo.Pg2xXhlc mXslc = new CanDataInfo.Pg2xXhlc();
    private CanDataInfo.Pg2xYslc mYslc = new CanDataInfo.Pg2xYslc();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.Pg2xGetYslcData(this.mYslc);
        if (i2b(this.mYslc.UpdateOnce) && (!check || i2b(this.mYslc.Update))) {
            this.mYslc.Update = 0;
            this.mItemYslc.SetVal(String.format("%d Km", new Object[]{Integer.valueOf(this.mYslc.uwLC)}));
        }
        CanJni.Pg2xGetXhlcData(this.mXslc);
        if (i2b(this.mXslc.UpdateOnce) && (!check || i2b(this.mXslc.Update))) {
            this.mXslc.Update = 0;
            if (this.mXslc.uwLC == 65535) {
                this.mItemXslc.SetVal(String.format("--", new Object[0]));
            } else {
                this.mItemXslc.SetVal(String.format("%d Km", new Object[]{Integer.valueOf(this.mXslc.uwLC)}));
            }
        }
        CanJni.Pg2xGetPjyhData(this.mPjyh);
        if (i2b(this.mPjyh.UpdateOnce) && (!check || i2b(this.mPjyh.Update))) {
            this.mPjyh.Update = 0;
            if (this.mPjyh.uwYH == 65535) {
                this.mItemPjyh.SetVal(String.format("--", new Object[0]));
            } else {
                this.mItemPjyh.SetVal(String.format("%d L/100km", new Object[]{Integer.valueOf(this.mPjyh.uwYH / 10)}));
            }
        }
        CanJni.Pg2xGetSsyhData(this.mSsyh);
        if (i2b(this.mSsyh.UpdateOnce) && (!check || i2b(this.mSsyh.Update))) {
            this.mSsyh.Update = 0;
            if (this.mSsyh.uwYH == 65535) {
                this.mItemSsyh.SetVal(String.format("--", new Object[0]));
            } else {
                this.mItemSsyh.SetVal(String.format("%d L/100km", new Object[]{Integer.valueOf(this.mSsyh.uwYH / 10)}));
            }
        }
        CanJni.Pg2xGetPjcsData(this.mPjcs);
        if (!i2b(this.mPjcs.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPjcs.Update)) {
            this.mPjcs.Update = 0;
            if (this.mPjcs.uwSpeed > 250) {
                this.mItemPjcs.SetVal(String.format("--", new Object[0]));
                return;
            }
            this.mItemPjcs.SetVal(String.format("%d Km/h", new Object[]{Integer.valueOf(this.mPjcs.uwSpeed)}));
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
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

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemYslc = AddItemTitleVal(R.string.can_dis_trav, 1);
        this.mItemXslc = AddItemTitleVal(R.string.can_range_xhlc, 2);
        this.mItemPjyh = AddItemTitleVal(R.string.can_pjyh, 3);
        this.mItemSsyh = AddItemTitleVal(R.string.can_ssyh, 4);
        this.mItemPjcs = AddItemTitleVal(R.string.can_avg_speed, 5);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 6; i++) {
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

    /* access modifiers changed from: protected */
    public CanItemTitleValList AddItemTitleVal(int text, int id) {
        CanItemTitleValList item = new CanItemTitleValList(this, text);
        item.SetIdClickListener(this, id);
        item.SetIconVisibility(0);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        item.SetIconVisibility(8);
        return item;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }
}
