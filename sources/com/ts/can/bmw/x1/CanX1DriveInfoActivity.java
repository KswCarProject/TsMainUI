package com.ts.can.bmw.x1;

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

public class CanX1DriveInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_CLDHZS = 4;
    public static final int ITEM_CLYDZS = 3;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_RANGE = 1;
    public static final int ITEM_RYSL = 5;
    public static final int ITEM_RYZXLC = 7;
    public static final int ITEM_SPEED = 2;
    public static final int ITEM_XSLC = 6;
    public static final String TAG = "CanX1DriveInfoActivity";
    protected CanDataInfo.BMW_X1_Drive mDrive = new CanDataInfo.BMW_X1_Drive();
    protected CanItemIcoVal mItemCldhzs;
    protected CanItemIcoVal mItemClydzs;
    protected CanItemIcoVal mItemRange;
    protected CanItemIcoVal mItemRysl;
    protected CanItemIcoVal mItemRyxhlc;
    protected CanItemIcoVal mItemSpeed;
    protected CanItemIcoVal mItemXslc;
    private CanScrollList mManager;
    protected String[] mRangeArr = {"rpm"};
    protected String[] mRyslArr = {"L"};
    protected String[] mSpeedArr = {"km/h"};
    protected CanDataInfo.BMW_X1_State mState = new CanDataInfo.BMW_X1_State();
    protected CanDataInfo.BMW_X1_Trip mTripData = new CanDataInfo.BMW_X1_Trip();
    private boolean mbLayout;
    protected String[] mlcArr = {"公里"};

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.BmwX1GetTrip(this.mTripData);
        if (i2b(this.mTripData.UpdateOnce) && (!check || i2b(this.mTripData.Update))) {
            if (this.mTripData.Range > 10000) {
                this.mItemRange.SetVal("----");
            } else {
                this.mItemRange.SetVal(String.format("%d %s", new Object[]{Integer.valueOf(this.mTripData.Range), this.mRangeArr[0]}));
            }
            if (this.mTripData.Speed > 400) {
                this.mItemSpeed.SetVal("--.-");
            } else {
                this.mItemSpeed.SetVal(String.format("%d %s", new Object[]{Integer.valueOf(this.mTripData.Speed), this.mSpeedArr[0]}));
            }
        }
        CanJni.BmwX1GetState(this.mState);
        if (i2b(this.mState.UpdateOnce) && (!check || i2b(this.mState.Update))) {
            if (i2b(this.mState.Clydzs)) {
                this.mItemClydzs.SetVal("移动");
            } else {
                this.mItemClydzs.SetVal("静止");
            }
            if (i2b(this.mState.Cldhzs)) {
                this.mItemCldhzs.SetVal("启动");
            } else {
                this.mItemCldhzs.SetVal("熄火");
            }
            if (this.mState.Rysl > 80) {
                this.mItemRysl.SetVal("--.-");
            } else {
                this.mItemRysl.SetVal(String.format("%d %s", new Object[]{Integer.valueOf(this.mState.Rysl), this.mRyslArr[0]}));
            }
        }
        CanJni.BmwX1GetDrive(this.mDrive);
        if (!i2b(this.mDrive.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mDrive.Update)) {
            if (this.mDrive.Xskc > 10000000) {
                this.mItemXslc.SetVal("--.-");
            } else {
                this.mItemXslc.SetVal(String.format("%d %s", new Object[]{Integer.valueOf(this.mDrive.Xskc), this.mlcArr[0]}));
            }
            if (this.mDrive.ryxhlc > 2000) {
                this.mItemRyxhlc.SetVal("--.-");
                return;
            }
            this.mItemRyxhlc.SetVal(String.format("%d %s", new Object[]{Integer.valueOf(this.mDrive.ryxhlc), this.mlcArr[0]}));
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.BmwX1CarQuery(17);
        CanJni.BmwX1CarQuery(18);
        CanJni.BmwX1CarQuery(64);
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
        this.mItemRange = AddIcoValItem(R.drawable.can_icon_tyres, R.string.can_rpm, 1);
        this.mItemSpeed = AddIcoValItem(R.drawable.can_icon_sudu, R.string.can_speed, 2);
        this.mItemClydzs = AddIcoValItem(R.drawable.can_icon_pm, R.string.can_xc_info, 3);
        this.mItemCldhzs = AddIcoValItem(R.drawable.can_icon_lock3, R.string.can_vehi_status, 4);
        this.mItemRysl = AddIcoValItem(R.drawable.can_icon_units, R.string.can_rest_oil, 5);
        this.mItemXslc = AddIcoValItem(R.drawable.can_icon_youhao, R.string.can_yslc, 6);
        this.mItemRyxhlc = AddIcoValItem(R.drawable.can_icon_mfd, R.string.can_range_xhlc, 7);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 7; i++) {
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
