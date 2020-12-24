package com.ts.can.ht.od;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;

public class CanHtOdCarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_CAR_SET = 2;
    public static final int ITEM_CAR_TYPE = 1;
    private static final int ITEM_MAX = 3;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_TPMS = 3;
    public static final String TAG = "CanHtOdCarInfoActivity";
    private CanItemIcoList mItemCarSet;
    private CanItemIcoList mItemCarType;
    private CanItemIcoList mItemTpms;
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
        CanJni.HanTOdQuery(65);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCarType = AddIcoItem(R.drawable.can_icon_light2, R.string.can_car_type_select, 1);
        this.mItemCarSet = AddIcoItem(R.drawable.can_icon_setup, R.string.can_car_set, 2);
        this.mItemTpms = AddIcoItem(R.drawable.can_icon_tyres, R.string.can_tyres_tpms, 3);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 3; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = 1;
                break;
            case 2:
                if (CanJni.GetSubType() == 3 || CanJni.GetSubType() == 2) {
                    ret = 1;
                    break;
                }
            case 3:
                if (CanJni.GetSubType() == 3 || CanJni.GetSubType() == 2) {
                    ret = 1;
                    break;
                }
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemCarType.ShowGone(show);
                return;
            case 2:
                this.mItemCarSet.ShowGone(show);
                return;
            case 3:
                this.mItemTpms.ShowGone(show);
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
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemTitleValList AddItemTitleVal(int text, int id) {
        CanItemTitleValList item = new CanItemTitleValList(this, text);
        item.SetIdClickListener(this, id);
        item.SetIconVisibility(0);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        item.SetIconVisibility(8);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemIcoList AddIcoItem(int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                enterSubWin(CanHtOdCarTypeActivity.class);
                return;
            case 2:
                enterSubWin(CanHtOdCarSetActivity.class);
                return;
            case 3:
                enterSubWin(CanHtOdTpmsActivity.class);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
