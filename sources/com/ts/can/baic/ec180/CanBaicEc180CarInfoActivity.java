package com.ts.can.baic.ec180;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanBaicEc180CarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_CAR_SET = 3;
    public static final int ITEM_KEEP_CLR = 1;
    private static final int ITEM_MAX = 3;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_PWR_ENGRY = 2;
    public static final String TAG = "CanBaicEc180CarInfoActivity";
    private CanItemIcoList mItemCarSet;
    private CanItemIcoList mItemKeepClr;
    private CanItemIcoList mItemPwrEngery;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
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
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemKeepClr = new CanItemIcoList(this, R.drawable.can_icon_units, R.string.can_units);
        this.mItemPwrEngery = new CanItemIcoList(this, R.drawable.can_icon_mfd, R.string.can_ec_nll);
        this.mItemCarSet = new CanItemIcoList(this, R.drawable.can_icon_setup, R.string.can_car_set);
        this.mItemKeepClr.SetIdClickListener(this, 1);
        this.mItemPwrEngery.SetIdClickListener(this, 2);
        this.mItemCarSet.SetIdClickListener(this, 3);
        this.mManager.AddView(this.mItemKeepClr.GetView());
        this.mManager.AddView(this.mItemPwrEngery.GetView());
        this.mManager.AddView(this.mItemCarSet.GetView());
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 3; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = 1;
                break;
            case 2:
                ret = 1;
                break;
            case 3:
                if (CanJni.GetSubType() == 1) {
                    ret = 1;
                    break;
                }
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemKeepClr.ShowGone(show);
                return;
            case 2:
                this.mItemPwrEngery.ShowGone(show);
                return;
            case 3:
                this.mItemCarSet.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                enterSubWin(CanBaicEC180DriveInfoActivity.class);
                return;
            case 2:
                enterSubWin(CanBaicEC180PwrInfoActivity.class);
                return;
            case 3:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 0);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
