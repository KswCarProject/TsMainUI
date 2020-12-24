package com.ts.can.gm.comm;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGMSetLightActivity extends CanGMBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_DDYS = 2;
    private static final int ITEM_MAX = 2;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_XCD = 1;
    public static final String TAG = "CanGMCarInfoActivity";
    private static final int[] mDdysArr = {R.string.can_off, R.string.can_30s, R.string.can_1min, R.string.can_2min};
    private CanDataInfo.GM_AdtLight mAdtLightData = new CanDataInfo.GM_AdtLight();
    private CanItemPopupList mItemDdys;
    private CanItemSwitchList mItemXcd;
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
        CanJni.GMGetAdtLight(this.mAdtLightData);
        if (i2b(this.mAdtLightData.UpdateOnce)) {
            if (!check || i2b(this.mAdtLightData.Update)) {
                this.mAdtLightData.Update = 0;
                LayoutUI();
                check = false;
                this.mbLayout = true;
            }
            if (!i2b(this.mSetData.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mSetData.Update)) {
                this.mSetData.Update = 0;
                this.mItemXcd.SetCheck(this.mSetData.XCD);
                this.mItemDdys.SetSel(this.mSetData.LSDDYS);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        if (this.mAdtLightData.UpdateOnce == 0) {
            CanJni.GMQuery(26);
        }
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
        this.mItemXcd = AddCheckItem(R.string.can_xcd, 1);
        this.mItemDdys = new CanItemPopupList((Context) this, R.string.can_lsddys, mDdysArr, (CanItemPopupList.onPopItemClick) this);
        this.mItemDdys.SetId(2);
        this.mManager.AddView(this.mItemDdys.GetView());
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 2; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtLightData.XCD;
                break;
            case 2:
                ret = this.mAdtLightData.LSDDYS;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        Log.d("CanGMCarInfoActivity", "item = " + item + ", show = " + show);
        switch (item) {
            case 1:
                this.mItemXcd.ShowGone(show);
                return;
            case 2:
                this.mItemDdys.ShowGone(show);
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
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GMCarCtrl(0, Neg(this.mSetData.XCD));
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int Id, int item) {
        if (Id == 2) {
            CanJni.GMCarCtrl(1, item);
        }
    }
}
