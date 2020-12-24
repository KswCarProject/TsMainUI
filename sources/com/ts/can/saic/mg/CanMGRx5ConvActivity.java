package com.ts.can.saic.mg;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemFsSetList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanMGRx5ConvActivity extends CanMGGSBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemFsSetList.onFsSetClick {
    public static final int ITEM_BWHJ = 1;
    public static final int ITEM_JSMS = 3;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_TPMS_RST = 5;
    public static final int ITEM_XCZS = 2;
    public static final int ITEM_ZNJCJS = 4;
    public static final String TAG = "CanMGRx5ConvActivity";
    private static final int[] mConvsArr = {R.string.can_only_light, R.string.can_dghlb};
    private static final int[] mJsmsArr = {R.string.can_door_unlock_key2, R.string.can_door_unlock_key1};
    private CanItemSwitchList mItemBwhj;
    private CanItemPopupList mItemJsms;
    private CanItemFsSetList mItemTpmsRst;
    private CanItemPopupList mItemXczs;
    private CanItemPopupList mItemZnjcjs;
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
        GetSetData2();
        if (!i2b(this.mSetData2.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData2.Update)) {
            this.mSetData2.Update = 0;
            this.mItemBwhj.SetCheck(this.mSetData2.fgHomeRevLight);
            this.mItemXczs.SetSel(this.mSetData2.FindIndicator);
            this.mItemJsms.SetSel(this.mSetData2.UnlockMode);
            this.mItemZnjcjs.SetSel(this.mSetData2.SmartUnlock);
            if (i2b(this.mSetData2.TpmsRst)) {
                this.mItemTpmsRst.SetColor(-12303292);
                this.mItemTpmsRst.GetView().setEnabled(false);
                return;
            }
            this.mItemTpmsRst.SetColor(-1);
            this.mItemTpmsRst.GetView().setEnabled(true);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(66);
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
        this.mItemBwhj = AddCheckItem(R.string.can_bwhj, 1);
        this.mItemXczs = AddPopupItem(R.string.can_xcd, mConvsArr, 2);
        this.mItemJsms = AddPopupItem(R.string.can_unlock_mode, mJsmsArr, 3);
        this.mItemZnjcjs = AddPopupItem(R.string.can_smart_near_lock, mJsmsArr, 4);
        this.mItemTpmsRst = new CanItemFsSetList(this, R.string.can_rw_rx5_tyfw);
        this.mItemTpmsRst.SetIdClickListener(5, this);
        this.mManager.AddView(this.mItemTpmsRst.GetView());
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
            case 1:
                CarSet(3, 1, Neg(this.mSetData2.fgHomeRevLight));
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
            case 2:
                CarSet(3, 2, item);
                return;
            case 3:
                CarSet(1, 3, item);
                return;
            case 4:
                CarSet(1, 4, item);
                return;
            default:
                return;
        }
    }

    public void onFsItem(int id, boolean sure) {
        if (sure) {
            switch (id) {
                case 5:
                    CarSet(4, 1, 1);
                    return;
                default:
                    return;
            }
        }
    }
}
