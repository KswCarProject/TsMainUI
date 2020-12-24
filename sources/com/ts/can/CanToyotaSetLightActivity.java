package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollList;

public class CanToyotaSetLightActivity extends CanToyotaBaseActivity implements View.OnClickListener, UserCallBack, CanItemProgressList.onPosChange, CanItemPopupList.onPopItemClick {
    public static final int ITEM_AUTO_OFF_TIMER = 4;
    public static final int ITEM_LIGHT_OFF_TIMER = 3;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_OUTCAR_LIGHT_TIME = 5;
    public static final int ITEM_RXD = 1;
    public static final int ITEM_SENS = 2;
    public static final String TAG = "CanToyotaSetLightActivity";
    private static int[] mStrAutoOffTimer = {R.string.can_off, R.string.can_30s, R.string.can_60s, R.string.can_90s};
    private static int[] mStrLightOffTimer = {R.string.can_off, R.string.can_7dot5s, R.string.can_15s, R.string.can_90s};
    private static int[] mStrOutCarLightOffTimer = {R.string.can_off, R.string.can_7dot5s, R.string.can_15s, R.string.can_30s};
    private CanItemPopupList mItemAutoOffTimer;
    private CanItemPopupList mItemLightOffTimer;
    private CanItemPopupList mItemOutCarLightTime;
    private CanItemCheckList mItemRxd;
    private CanItemProgressList mItemSens;
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
        if (!this.mbLayout) {
            GetAdaptData();
            if (i2b(this.mAdtData.UpdateOnce)) {
                LayoutUI();
                check = false;
                this.mbLayout = true;
            } else {
                return;
            }
        }
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemRxd.SetCheck(this.mSetData.fgRxd);
            this.mItemSens.SetCurVal(this.mSetData.Sensitivity);
            this.mItemLightOffTimer.SetSel(this.mSetData.LightOffTime);
            this.mItemAutoOffTimer.SetSel(this.mSetData.AutoOffTimer);
            this.mItemOutCarLightTime.SetSel(this.mSetData.OutLightOffTime);
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
        for (int i = 1; i <= 5; i++) {
            InitItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        this.mManager.RemoveAllViews();
        for (int i = 1; i <= 5; i++) {
            AddItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.fgRxd;
                break;
            case 2:
                ret = this.mAdtData.Sensitivity;
                break;
            case 3:
                ret = this.mAdtData.LightOffTime;
                break;
            case 4:
                ret = this.mAdtData.AutoOffTimer;
                break;
            case 5:
                ret = this.mAdtData.OutLightOffTime;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void InitItem(int item) {
        switch (item) {
            case 1:
                this.mItemRxd = AddCheckItem(R.string.can_light_rxd, item);
                return;
            case 2:
                this.mItemSens = new CanItemProgressList((Context) this, R.string.can_light_sens);
                this.mItemSens.SetIdCallBack(item, this);
                this.mItemSens.SetMinMax(0, 4);
                return;
            case 3:
                this.mItemLightOffTimer = new CanItemPopupList((Context) this, R.string.can_light_off_time, mStrLightOffTimer, (CanItemPopupList.onPopItemClick) this);
                this.mItemLightOffTimer.SetId(item);
                return;
            case 4:
                this.mItemAutoOffTimer = new CanItemPopupList((Context) this, R.string.can_light_auto_off_timer, mStrAutoOffTimer, (CanItemPopupList.onPopItemClick) this);
                this.mItemAutoOffTimer.SetId(item);
                return;
            case 5:
                this.mItemOutCarLightTime = new CanItemPopupList((Context) this, R.string.can_outcarlightoftime, mStrOutCarLightOffTimer, (CanItemPopupList.onPopItemClick) this);
                this.mItemOutCarLightTime.SetId(item);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void AddItem(int item) {
        if (IsHaveItem(item)) {
            switch (item) {
                case 1:
                    this.mManager.AddView(this.mItemRxd.GetView());
                    return;
                case 2:
                    this.mManager.AddView(this.mItemSens.GetView());
                    return;
                case 3:
                    this.mManager.AddView(this.mItemLightOffTimer.GetView());
                    return;
                case 4:
                    this.mManager.AddView(this.mItemAutoOffTimer.GetView());
                    return;
                case 5:
                    this.mManager.AddView(this.mItemOutCarLightTime.GetView());
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public CanItemCheckList AddCheckItem(int resId, int Id) {
        CanItemCheckList item = new CanItemCheckList(this, resId);
        item.SetIdClickListener(this, Id);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CarSet(4, Neg(this.mSetData.fgRxd));
                return;
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 3:
                CarSet(12, item);
                return;
            case 4:
                CarSet(7, item);
                return;
            case 5:
                CarSet(12, item + 4);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 2:
                CarSet(6, pos);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
