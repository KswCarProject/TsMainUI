package com.ts.can.mzd.cx5;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanMzdCx5CarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack {
    public static final int ITEM_CONV = 3;
    public static final int ITEM_LANG = 4;
    public static final int ITEM_LINGHT = 2;
    public static final int ITEM_LOCK = 1;
    private static final int ITEM_MAX = 4;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanMzdCx5CarInfoActivity";
    private CanItemIcoList mItemConv;
    private CanItemIcoList mItemLang;
    private CanItemIcoList mItemLight;
    private CanItemIcoList mItemLock;
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
        CanJni.MzdCx5Query(50);
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
        this.mItemLock = AddIcoItem(R.drawable.can_icon_lock3, R.string.can_car_lock_set, 1);
        this.mItemLight = AddIcoItem(R.drawable.can_icon_light, R.string.can_light_set, 2);
        this.mItemConv = AddIcoItem(R.drawable.can_golf_icon12, R.string.can_ssblx, 3);
        this.mItemLang = AddIcoItem(R.drawable.can_icon_light2, R.string.can_car_lang, 4);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 4; i++) {
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
                ret = 1;
                break;
            case 3:
                ret = 1;
                break;
            case 4:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemLock.ShowGone(show);
                return;
            case 2:
                this.mItemLight.ShowGone(show);
                return;
            case 3:
                this.mItemConv.ShowGone(show);
                return;
            case 4:
                this.mItemLang.ShowGone(show);
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
                enterSubWin(CanMzdCx5LockActivity.class);
                return;
            case 2:
                enterSubWin(CanMzdCx5LightActivity.class);
                return;
            case 3:
                enterSubWin(CanMzdCx5ConvActivity.class);
                return;
            case 4:
                enterSubWin(CanMzdCx5LanguageActivity.class);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
