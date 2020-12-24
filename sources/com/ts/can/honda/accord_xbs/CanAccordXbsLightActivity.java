package com.ts.can.honda.accord_xbs;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanAccordXbsLightActivity extends CanAccordXbsBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_HEDALIGHT_TIMER = 2;
    public static final int ITEM_INTERIOR_TIME = 3;
    public static final int ITEM_LIGHT_SENS = 1;
    private static final int ITEM_MAX = 3;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanAccordLightActivity";
    private static final int[] mHeadLightsArr = {R.string.can_0s, R.string.can_15s, R.string.can_30s, R.string.can_60s};
    private static final int[] mInteriorArr = {R.string.can_15s, R.string.can_30s, R.string.can_60s};
    private static final int[] mLightSensArr = {R.string.can_cdzd, R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg, R.string.can_cdzg};
    private CanItemPopupList mItemHeadlight;
    private CanItemPopupList mItemInterior;
    private CanItemPopupList mItemLightSens;
    private CanScrollList mManager;
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemLightSens.SetSel(this.mSetData.AutoLightSen);
            this.mItemHeadlight.SetSel(this.mSetData.HeadlightAutoOffTime);
            this.mItemInterior.SetSel(this.mSetData.InteriorTime);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(10, 0);
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
        this.mItemLightSens = AddPopupItem(R.string.can_zdddlmd, mLightSensArr, 1);
        this.mItemHeadlight = AddPopupItem(R.string.can_ddzdxmsj, mHeadLightsArr, 2);
        this.mItemInterior = AddPopupItem(R.string.can_cndgjgsj, mInteriorArr, 3);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 3; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.AutoLightSen;
                break;
            case 2:
                ret = this.mAdtData.HeadlightAutoOffTime;
                break;
            case 3:
                ret = this.mAdtData.InteriorTime;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemLightSens.ShowGone(show);
                return;
            case 2:
                this.mItemHeadlight.ShowGone(show);
                return;
            case 3:
                this.mItemInterior.ShowGone(show);
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

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CarSet(6, item);
                return;
            case 2:
                CarSet(5, item);
                return;
            case 3:
                CarSet(4, item);
                return;
            default:
                return;
        }
    }
}
