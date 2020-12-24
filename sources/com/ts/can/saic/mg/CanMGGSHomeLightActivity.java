package com.ts.can.saic.mg;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemBlankTextList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanMGGSHomeLightActivity extends CanMGGSBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_DURATION = 4;
    private static final int ITEM_MAX = 4;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_NEAR_LIGHT = 2;
    public static final int ITEM_REV_LIGHT = 1;
    public static final int ITEM_RFOG_LIGHT = 3;
    public static final String TAG = "CanMGGSHomeLightActivity";
    private static final String[] mStrTime = {"30秒", "1分钟", "1分30秒", "2分钟", "2分30秒", "3分钟", "3分30秒", "4分钟", "4分30秒", "5分钟"};
    private CanItemPopupList mItemDuration;
    private CanItemSwitchList mItemNearLight;
    private CanItemSwitchList mItemRFogLight;
    private CanItemSwitchList mItemRevLight;
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
            this.mItemRevLight.SetCheck(this.mSetData.fgHomeRevLight);
            this.mItemNearLight.SetCheck(this.mSetData.fgHomeNearLigh);
            this.mItemRFogLight.SetCheck(this.mSetData.fgHomeRearLight);
            this.mItemDuration.SetSel(this.mSetData.HomeTime);
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
        this.mManager.AddView(new CanItemBlankTextList((Context) this, R.string.can_bwhj_light).GetView());
        this.mItemRevLight = AddCheckItem(R.string.can_dcd, 1);
        this.mItemNearLight = AddCheckItem(R.string.can_jgd, 2);
        this.mItemRFogLight = AddCheckItem(R.string.can_hwd, 3);
        this.mItemDuration = AddPopupItem(R.string.can_cxsj, mStrTime, 4);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 4; i++) {
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
    public CanItemPopupList AddPopupItem(int text, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CarSet(2, 1, GetLightVal(Neg(this.mSetData.fgHomeRevLight), this.mSetData.fgHomeNearLigh, this.mSetData.fgHomeRearLight));
                return;
            case 2:
                CarSet(2, 1, GetLightVal(this.mSetData.fgHomeRevLight, Neg(this.mSetData.fgHomeNearLigh), this.mSetData.fgHomeRearLight));
                return;
            case 3:
                CarSet(2, 1, GetLightVal(this.mSetData.fgHomeRevLight, this.mSetData.fgHomeNearLigh, Neg(this.mSetData.fgHomeRearLight)));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        if (id == 4) {
            CarSet(2, 2, item);
        }
    }

    public int GetLightVal(int rev, int near, int fog) {
        int ret = 0;
        if (rev != 0) {
            ret = 0 | 128;
        }
        if (near != 0) {
            ret |= 64;
        }
        if (fog != 0) {
            return ret | 32;
        }
        return ret;
    }
}
