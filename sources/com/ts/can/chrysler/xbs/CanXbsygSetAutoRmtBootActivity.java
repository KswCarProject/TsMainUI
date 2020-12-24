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

public class CanXbsygSetAutoRmtBootActivity extends CanXbsygBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CLQDS = 1;
    private static final int ITEM_MAX = 3;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_ZYBLJC = 3;
    public static final int ITEM_ZZYJR = 2;
    public static final String TAG = "CanRZygSetAutoRmtBootActivity";
    private static final int[] mClqdsArr = {R.string.can_off, R.string.can_jp_yc, R.string.can_jp_qb};
    protected CanItemPopupList mItemClqds;
    protected CanItemSwitchList mItemZybljc;
    protected CanItemSwitchList mItemZzyjr;
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
        if (!i2b(this.mSetData.RmtBootUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.RmtBootUpdate)) {
            this.mSetData.RmtBootUpdate = 0;
            this.mItemClqds.SetSel(this.mSetData.AutoBootSys);
            this.mItemZzyjr.SetCheck(this.mSetData.RmtBootSta);
            this.mItemZybljc.SetCheck(this.mSetData.Zybljc);
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
        LayoutUI();
        ResetData(false);
        QueryData();
        Log.d("CanRZygSetAutoRmtBootActivity", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanRZygSetAutoRmtBootActivity", "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemClqds = AddPopupItem(R.string.can_zdkqssxt, mClqdsArr, 1);
        this.mItemZzyjr = AddCheckItem(R.string.can_clqds, 2);
        this.mItemZybljc = AddCheckItem(R.string.can_zybljc, 3);
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
                ret = this.mAdtData.AutoBootSys;
                break;
            case 2:
                ret = this.mAdtData.RmtBootSta;
                break;
            case 3:
                ret = this.mAdtData.Zybljc;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemClqds.ShowGone(show);
                return;
            case 2:
                this.mItemZzyjr.ShowGone(show);
                return;
            case 3:
                this.mItemZybljc.ShowGone(show);
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
            case 2:
                CarSWSet(58, this.mSetData.RmtBootSta);
                return;
            case 3:
                CarSWSet(57, this.mSetData.Zybljc);
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
                CarSet(17, item);
                return;
            default:
                return;
        }
    }
}
