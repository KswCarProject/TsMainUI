package com.ts.can.saic.rw950;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanRW950LightSetActivity extends CanRW950BaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_DDYS = 2;
    private static final int ITEM_MAX = 2;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_XCD = 1;
    public static final String TAG = "CanRW950LightSetActivity";
    private static final int[] mDdysArr = {R.string.can_off, R.string.can_30s, R.string.can_1min, R.string.can_2min};
    private CanItemPopupList mItemDdys;
    private CanItemSwitchList mItemXcd;
    private CanScrollList mManager;

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
            this.mItemXcd.SetCheck(this.mSetData.ubXCD);
            this.mItemDdys.SetSel(this.mSetData.ubLSDDYS);
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
        this.mItemXcd = AddCheckItem(R.string.can_xcd, 1);
        this.mItemDdys = new CanItemPopupList((Context) this, R.string.can_lsddys, mDdysArr, (CanItemPopupList.onPopItemClick) this);
        this.mItemDdys.SetId(2);
        this.mManager.AddView(this.mItemDdys.GetView());
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
                CarSet(0, Neg(this.mSetData.ubXCD));
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
            CarSet(1, item);
        }
    }
}
