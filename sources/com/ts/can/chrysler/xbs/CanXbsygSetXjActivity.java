package com.ts.can.chrysler.xbs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanXbsygSetXjActivity extends CanXbsygBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CLJZMS = 5;
    public static final int ITEM_LTQJD = 3;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SXCZDTJXJ = 1;
    public static final int ITEM_XSXJXX = 2;
    public static final int ITEM_YSMS = 4;
    public static final String TAG = "CanXbsygSetXjActivity";
    protected CanItemSwitchList mItemCljzms;
    protected CanItemSwitchList mItemLtqjd;
    protected CanItemSwitchList mItemSxczdtjxj;
    protected CanItemSwitchList mItemXsxjxx;
    protected CanItemSwitchList mItemYsms;
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
        if (!i2b(this.mSetData.XJUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.XJUpdate)) {
            this.mSetData.XJUpdate = 0;
            this.mItemSxczdtjxj.SetCheck(this.mSetData.Sxzdtjxj);
            this.mItemXsxjxx.SetCheck(this.mSetData.Xsxjxx);
            this.mItemLtqjd.SetCheck(this.mSetData.Ltqjd);
            this.mItemYsms.SetCheck(this.mSetData.Ysms);
            this.mItemCljzms.SetCheck(this.mSetData.Cljzms);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query2(12);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        LayoutUI();
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
        this.mItemSxczdtjxj = AddCheckItem(R.string.can_sxczdtjxj, 1);
        this.mItemXsxjxx = AddCheckItem(R.string.can_xsxjxx, 2);
        this.mItemLtqjd = AddCheckItem(R.string.can_ltqjd, 3);
        this.mItemCljzms = AddCheckItem(R.string.can_cljzms, 5);
        this.mItemYsms = AddCheckItem(R.string.can_ysms, 4);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 5; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.Sxzdtjxj;
                break;
            case 2:
                ret = this.mAdtData.Xsxjxx;
                break;
            case 3:
                ret = this.mAdtData.Ltqjd;
                break;
            case 4:
                ret = this.mAdtData.Ysms;
                break;
            case 5:
                ret = this.mAdtData.Cljzms;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemSxczdtjxj.ShowGone(show);
                return;
            case 2:
                this.mItemXsxjxx.ShowGone(show);
                return;
            case 3:
                this.mItemLtqjd.ShowGone(show);
                return;
            case 4:
                this.mItemYsms.ShowGone(show);
                return;
            case 5:
                this.mItemCljzms.ShowGone(show);
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
            case 1:
                CarSWSet(65, this.mSetData.Sxzdtjxj);
                return;
            case 2:
                CarSWSet(66, this.mSetData.Xsxjxx);
                return;
            case 3:
                CarSWSet(67, this.mSetData.Ltqjd);
                return;
            case 4:
                CarSWSet(68, this.mSetData.Ysms);
                return;
            case 5:
                CarSWSet(69, this.mSetData.Cljzms);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
    }
}
