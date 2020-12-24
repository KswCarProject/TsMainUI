package com.ts.can.chrysler;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanChrOthSetAutoRmtBootActivity extends CanChrOthBaseActivity implements UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CLQDS = 1;
    private static final int ITEM_MAX = 1;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanChrOthSetAutoRmtBootActivity";
    private static final int[] mCLQD = {R.string.can_trunk_close, R.string.can_jp_yc, R.string.can_jp_qb};
    protected CanItemPopupList mItemClqds;
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
            this.mItemClqds.SetSel(this.mSetData.RmtBootSta);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(64, 144);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
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
        this.mItemClqds = AddPopupItem(R.string.can_clqds, mCLQD, 1);
        if (CanFunc.getInstance().IsCustomShow("Jeep")) {
            findViewById(R.id.can_comm_list_layout).setBackgroundResource(R.drawable.can_grand_cherokee_bg);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 1; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.RmtBootSta;
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
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CarSet(144, item);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
