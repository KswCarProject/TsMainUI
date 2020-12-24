package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanHondaADACarDistanceIllActivity extends CanBaseActivity implements CanItemPopupList.onPopItemClick, UserCallBack {
    public static final int ITEM_AUTOLIGHTSENS = 1;
    public static final int ITEM_HEADLIGHTAUTOOFTIME = 2;
    public static final int ITEM_INTERIORLIGHT = 3;
    public static final String TAG = "CanHondaADACarDistanceIllActivity";
    private static final int[] mAutoLightSensArr = {R.string.can_sensitivity_min, R.string.can_sensitivity_low, R.string.can_sensitivity_mid, R.string.can_sensitivity_high, R.string.can_sensitivity_max};
    private static final int[] mHeadlightAutoOfTimeArr = {R.string.can_headlightautoofftime_0s, R.string.can_headlightautoofftime_15s, R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s};
    private static final int[] mInteriorlightDimTimeArr = {R.string.can_headlightautoofftime_15s, R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s};
    protected CanItemPopupList mItemAutoLightSens;
    protected CanItemPopupList mItemHeadlightAutoOfTime;
    protected CanItemPopupList mItemInteriorlightDimTime;
    protected CanScrollList mManager;
    protected CanDataInfo.HondaSetData mSetData = new CanDataInfo.HondaSetData();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemAutoLightSens = AddPopupItem(R.string.can_autolightsensitivity, mAutoLightSensArr, 1);
        this.mItemHeadlightAutoOfTime = AddPopupItem(R.string.can_headlightautoofftime, mHeadlightAutoOfTimeArr, 2);
        this.mItemInteriorlightDimTime = AddPopupItem(R.string.can_interiorlightdimmingtime, mInteriorlightDimTimeArr, 3);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.HondaDACarSet(6, item);
                return;
            case 2:
                CanJni.HondaDACarSet(5, item);
                return;
            case 3:
                CanJni.HondaDACarSet(4, item);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.DisIllUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.DisIllUpdate)) {
            this.mSetData.DisIllUpdate = 0;
            this.mItemAutoLightSens.SetSel(this.mSetData.AutoLightSens);
            this.mItemHeadlightAutoOfTime.SetSel(this.mSetData.lightofftime);
            this.mItemInteriorlightDimTime.SetSel(this.mSetData.lightdimmingtime);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
