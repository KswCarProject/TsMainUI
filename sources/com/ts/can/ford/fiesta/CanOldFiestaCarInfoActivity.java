package com.ts.can.ford.fiesta;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollBaseActivity;

public class CanOldFiestaCarInfoActivity extends CanScrollBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    protected static final int ITEM_INFO = 3;
    protected static final int ITEM_MAX = 3;
    protected static final int ITEM_MIN = 2;
    protected static final int ITEM_SET = 2;
    public static final String TAG = "CanOldFiestaCarInfoActivity";
    protected CanItemIcoList mItemInfo;
    protected CanItemIcoList mItemSet;
    protected boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mItemSet = AddIcoItem((View.OnClickListener) this, R.drawable.can_icon_setup, R.string.can_car_set, 2);
        this.mItemInfo = AddIcoItem((View.OnClickListener) this, R.drawable.can_icon_service, R.string.can_info_title, 3);
        LayoutUI();
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
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 2; i <= 3; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        switch (item) {
            case 2:
                break;
            case 3:
                break;
        }
        return i2b(1);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 2:
                this.mItemSet.ShowGone(show);
                return;
            case 3:
                this.mItemInfo.ShowGone(show);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                enterSubWin(CanOldFiestaSetActivity.class);
                return;
            case 3:
                enterSubWin(CanOldFiestaLogActivity.class);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
    }
}
