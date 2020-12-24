package com.ts.can.mzd.cx5;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanMzdCx5LightActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_3_FLASH_TURN_SIGNAL = 4;
    public static final int ITEM_AUTO_HEADLIGHT_ON = 5;
    public static final int ITEM_HEADLIGHT_OFF_TIMER = 3;
    public static final int ITEM_INT_LIGHT_TIMEOUT_DOOR_CLOSE = 2;
    public static final int ITEM_INT_LIGHT_TIMEOUT_DOOR_OPEN = 1;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanMzdCx5LightActivity";
    private static final int[] mAutoHeadlightOnArr = {R.string.can_mzd_cx4_light_lighter, R.string.can_mzd_cx4_light_middle_lighter, R.string.can_mzd_cx4_light_middle, R.string.can_mzd_cx4_light_middle_dark, R.string.can_mzd_cx4_light_dark};
    private static final int[] mHedalightOffTimeArr = {R.string.can_mzd_cx4_time_120s, R.string.can_mzd_cx4_time_90s, R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_30s, R.string.can_off};
    private static final int[] mIntLightTimeoutDoorCloseArr = {R.string.can_mzd_cx4_time_60s, R.string.can_mzd_cx4_time_30s, R.string.can_mzd_cx4_time_15s, R.string.can_mzd_cx4_time_7_5s};
    private static final int[] mIntLightTimeoutDoorOPenArr = {R.string.can_mzd_cx4_time_60min, R.string.can_mzd_cx4_time_30min, R.string.can_mzd_cx4_time_10min, R.string.can_tripbresettiming_4};
    private CanItemSwitchList mItem3flashTurnSignal;
    private CanItemPopupList mItemAutoHeadlightOn;
    private CanItemPopupList mItemHeadlightOffTimer;
    private CanItemPopupList mItemIntLightTimeoutDoorClose;
    private CanItemPopupList mItemIntLightTimeoutDoorOpen;
    private CanScrollList mManager;
    protected CanDataInfo.MZD_CX5_Info mSetData = new CanDataInfo.MZD_CX5_Info();
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.MzdCx5GetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemIntLightTimeoutDoorOpen.SetSel(this.mSetData.InLightTimeout_DoorOpen);
            this.mItemIntLightTimeoutDoorClose.SetSel(this.mSetData.InLightTimeout_DoorClose);
            this.mItemHeadlightOffTimer.SetSel(this.mSetData.HeadLightOffTimer);
            this.mItem3flashTurnSignal.SetCheck(this.mSetData.FlashTurnSignal);
            this.mItemAutoHeadlightOn.SetSel(this.mSetData.AutoHeadlightOn);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.MzdCx5Query(50);
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
        this.mItemIntLightTimeoutDoorOpen = AddPopupItem(R.string.can_int_door_open, mIntLightTimeoutDoorOPenArr, 1);
        this.mItemIntLightTimeoutDoorClose = AddPopupItem(R.string.can_int_door_close, mIntLightTimeoutDoorCloseArr, 2);
        this.mItemHeadlightOffTimer = AddPopupItem(R.string.can_headlight_off_timer, mHedalightOffTimeArr, 3);
        this.mItem3flashTurnSignal = AddCheckItem(R.string.can3_flash_signal, 4);
        this.mItemAutoHeadlightOn = AddPopupItem(R.string.can_auto_headlight_on, mAutoHeadlightOnArr, 5);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 5; i++) {
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
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 4:
                CanJni.MzdCx5CarSet(7, Neg(this.mSetData.FlashTurnSignal));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.MzdCx5CarSet(5, item);
                return;
            case 2:
                CanJni.MzdCx5CarSet(6, item);
                return;
            case 3:
                CanJni.MzdCx5CarSet(8, item);
                return;
            case 5:
                CanJni.MzdCx5CarSet(9, item);
                return;
            default:
                return;
        }
    }
}
