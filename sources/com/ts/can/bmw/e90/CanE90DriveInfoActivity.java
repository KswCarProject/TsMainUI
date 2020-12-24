package com.ts.can.bmw.e90;

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
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanE90DriveInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_CONSUMP = 2;
    private static final int ITEM_MAX = 3;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_RANGE = 1;
    public static final int ITEM_SPEED = 3;
    public static final String TAG = "CanE90DriveInfoActivity";
    protected String[] mConsumpArr = {"l/100km", "mpg(US)", "mpg(UK)", "km/l"};
    protected CanItemIcoVal mItemConsump;
    protected CanItemIcoVal mItemRange;
    protected CanItemIcoVal mItemSpeed;
    private CanScrollList mManager;
    protected String[] mRangeArr = {"km", "mls"};
    protected CanDataInfo.BMW_Settings mSetData = new CanDataInfo.BMW_Settings();
    protected String[] mSpeedArr = {"km/h", "mls/h"};
    protected CanDataInfo.BMW_Trip mTripData = new CanDataInfo.BMW_Trip();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.E90GetSetData(this.mSetData);
        CanJni.E90GetDriveData(this.mTripData);
        if (i2b(this.mSetData.UpdateOnce) && i2b(this.mTripData.UpdateOnce)) {
            if (!check || i2b(this.mSetData.Update) || i2b(this.mTripData.Update)) {
                this.mSetData.Update = 0;
                this.mTripData.Update = 0;
                if (this.mTripData.Range > 4000) {
                    this.mItemRange.SetVal("----");
                } else {
                    this.mItemRange.SetVal(String.format("%d %s", new Object[]{Integer.valueOf(this.mTripData.Range), this.mRangeArr[this.mSetData.DWJl & 1]}));
                }
                if (this.mTripData.Speed > 4000) {
                    this.mItemSpeed.SetVal("--.-");
                } else {
                    this.mItemSpeed.SetVal(String.format("%.1f %s", new Object[]{Float.valueOf(((float) this.mTripData.Speed) * 0.1f), this.mSpeedArr[this.mSetData.DWJl & 1]}));
                }
                if (this.mTripData.Consumption > 4000) {
                    this.mItemConsump.SetVal("--.-");
                    return;
                }
                this.mItemConsump.SetVal(String.format("%.1f %s", new Object[]{Float.valueOf(((float) this.mTripData.Consumption) * 0.1f), this.mConsumpArr[this.mSetData.DWConsumption & 3]}));
            }
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
        this.mItemRange = AddIcoValItem(R.drawable.can_icon_kdlc, R.string.can_kd_range, 1);
        this.mItemConsump = AddIcoValItem(R.drawable.can_icon_youhao, R.string.can_consumption, 2);
        this.mItemSpeed = AddIcoValItem(R.drawable.can_icon_sudu, R.string.can_speed, 3);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 3; i++) {
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
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemIcoVal AddIcoValItem(int ico, int text, int id) {
        CanItemIcoVal item = new CanItemIcoVal(this, ico, text);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }
}
