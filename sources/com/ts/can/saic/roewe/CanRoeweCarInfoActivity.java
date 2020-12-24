package com.ts.can.saic.roewe;

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
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanRoeweCarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    private static final int ITEM_MAX = 2;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SCR_COLOR = 1;
    public static final int ITEM_TYPE = 2;
    public static final String TAG = "CanRoeweCarInfoActivity";
    private static final int[] mTypeArr = {R.string.can_roewe_key1, R.string.can_roewe_key2, R.string.can_roewe_360};
    private CanDataInfo.RoeweAdt mAdtData = new CanDataInfo.RoeweAdt();
    private String[] mColorArr = {"无 ", "梦幻蓝 ", "激情红", "炫光金"};
    private CanItemCarType mItemCarType;
    private CanItemPopupList mItemScrColor;
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
        Log.d(TAG, "subtype = " + CanJni.GetSubType());
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCarType = new CanItemCarType((Context) this, R.string.can_car_type_select, mTypeArr, 2, (CanItemPopupList.onPopItemClick) this);
        this.mManager.AddView(this.mItemCarType.GetView());
        CanJni.RoeweGetAdt(this.mAdtData);
        if (this.mAdtData.ScrColor != 0) {
            this.mItemScrColor = AddPopupItem(R.string.can_car_color, this.mColorArr, 1);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 2; i++) {
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
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        if (id == 2) {
            Log.d(TAG, "Select = " + item);
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        } else if (id == 1) {
            CanJni.RoeweCarSet(1, item, 0, 0);
        }
    }
}
