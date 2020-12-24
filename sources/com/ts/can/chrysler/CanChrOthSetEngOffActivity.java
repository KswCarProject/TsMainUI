package com.ts.can.chrysler;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanChrOthSetEngOffActivity extends CanChrOthBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_DDYCXM = 1;
    public static final int ITEM_FDJGBYC = 2;
    private static final int ITEM_MAX = 3;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_ZYBLJC = 3;
    public static final String TAG = "CanChrOthSetEngOffActivity";
    private static final int[] mDdycxmArr = {R.string.can_0s, R.string.can_30s, R.string.can_60s, R.string.can_90s};
    private static final int[] mFdjgbycArr = {R.string.can_0s, R.string.can_time_45s, R.string.can_time_5min, R.string.can_time_10min};
    protected CanItemPopupList mItemDdycxm;
    protected CanItemPopupList mItemFdjgbyc;
    protected CanItemSwitchList mItemZybljc;
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
        if (!i2b(this.mSetData.EngOptUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.EngOptUpdate)) {
            this.mSetData.EngOptUpdate = 0;
            this.mItemDdycxm.SetSel(this.mSetData.EnHeadOffDelay / 30);
            this.mItemZybljc.SetCheck(this.mSetData.Zybljc);
            switch (this.mSetData.EngOffPWRDelay) {
                case 0:
                    this.mItemFdjgbyc.SetSel(0);
                    return;
                case 3:
                    this.mItemFdjgbyc.SetSel(1);
                    return;
                case 20:
                    this.mItemFdjgbyc.SetSel(2);
                    return;
                case 40:
                    this.mItemFdjgbyc.SetSel(3);
                    return;
                default:
                    this.mItemFdjgbyc.SetSel(255);
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(64, 64);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
        ResetData(false);
        QueryData();
        Log.d("CanChrOthSetEngOffActivity", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanChrOthSetEngOffActivity", "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemDdycxm = AddPopupItem(R.string.can_car_ddycxm, mDdycxmArr, 1);
        this.mItemFdjgbyc = AddPopupItem(R.string.can_fdjgbdyyc, mFdjgbycArr, 2);
        this.mItemZybljc = AddCheckItem(R.string.can_zybljc, 3);
        if (CanFunc.getInstance().IsCustomShow("Jeep")) {
            findViewById(R.id.can_comm_list_layout).setBackgroundResource(R.drawable.can_grand_cherokee_bg);
        }
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
                ret = this.mAdtData.EnHeadOffDelay;
                break;
            case 2:
                ret = this.mAdtData.EngOffPWRDelay;
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
                this.mItemDdycxm.ShowGone(show);
                return;
            case 2:
                this.mItemFdjgbyc.ShowGone(show);
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
            case 3:
                CarSWSet(66, this.mSetData.Zybljc);
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
                CarSet(64, item * 30);
                return;
            case 2:
                switch (item) {
                    case 0:
                        CarSet(65, 0);
                        return;
                    case 1:
                        CarSet(65, 3);
                        return;
                    case 2:
                        CarSet(65, 20);
                        return;
                    case 3:
                        CarSet(65, 40);
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }
}
