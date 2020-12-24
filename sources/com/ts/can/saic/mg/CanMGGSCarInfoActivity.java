package com.ts.can.saic.mg;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanMGGSCarInfoActivity extends CanMGGSBaseActivity implements View.OnClickListener, UserCallBack, CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_AC = 5;
    public static final int ITEM_AVM_SET = 8;
    public static final int ITEM_CAR_TYPE = 1;
    public static final int ITEM_CONV = 6;
    public static final int ITEM_FIND_LIGHT = 4;
    public static final int ITEM_FS_SET = 7;
    public static final int ITEM_HOME_LIGHT = 3;
    public static final int ITEM_LOCK = 2;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanMGGSCarInfoActivity";
    private CanItemIcoList mItemAC;
    private CanItemIcoList mItemAvmSet;
    private CanItemIcoList mItemCarType;
    private CanItemIcoList mItemConv;
    private CanItemIcoList mItemFindLight;
    private CanItemIcoList mItemFsSet;
    private CanItemIcoList mItemHomeLight;
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
        this.mItemCarType = AddIcoItem(R.drawable.can_icon_esc, R.string.can_car_type_select, 1);
        this.mItemConv = AddIcoItem(R.drawable.can_golf_icon12, R.string.can_ssblx, 6);
        this.mItemAvmSet = AddIcoItem(R.drawable.can_icon_setup, R.string.can_360qjsz, 8);
        this.mItemLock = AddIcoItem(R.drawable.can_icon_lock3, R.string.can_car_lock_set, 2);
        this.mItemHomeLight = AddIcoItem(R.drawable.can_icon_light, R.string.can_bwhj_light, 3);
        this.mItemFindLight = AddIcoItem(R.drawable.can_icon_light2, R.string.can_xcd, 4);
        this.mItemAC = AddIcoItem(R.drawable.can_icon_ac, R.string.can_ac_set, 5);
        this.mItemFsSet = AddIcoItem(R.drawable.can_icon_factory, R.string.can_factory_set, 7);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 8; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                if (4 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
                break;
            case 2:
                if (1 != CanJni.GetSubType() && 2 != CanJni.GetSubType() && 3 != CanJni.GetSubType() && 4 != CanJni.GetSubType() && 5 != CanJni.GetSubType() && 6 != CanJni.GetSubType() && 7 != CanJni.GetSubType() && 8 != CanJni.GetSubType() && 9 != CanJni.GetSubType() && 10 != CanJni.GetSubType() && 11 != CanJni.GetSubType() && 12 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 3:
                if (1 != CanJni.GetSubType() && 2 != CanJni.GetSubType() && 3 != CanJni.GetSubType() && 4 != CanJni.GetSubType() && 5 != CanJni.GetSubType() && 6 != CanJni.GetSubType() && 7 != CanJni.GetSubType() && 8 != CanJni.GetSubType() && 9 != CanJni.GetSubType() && 10 != CanJni.GetSubType() && 11 != CanJni.GetSubType() && 12 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 4:
                if (1 != CanJni.GetSubType() && 2 != CanJni.GetSubType() && 3 != CanJni.GetSubType() && 4 != CanJni.GetSubType() && 5 != CanJni.GetSubType() && 6 != CanJni.GetSubType() && 7 != CanJni.GetSubType() && 8 != CanJni.GetSubType() && 9 != CanJni.GetSubType() && 10 != CanJni.GetSubType() && 11 != CanJni.GetSubType() && 12 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 5:
                if (1 != CanJni.GetSubType() && 2 != CanJni.GetSubType() && 3 != CanJni.GetSubType() && 4 != CanJni.GetSubType() && 5 != CanJni.GetSubType() && 6 != CanJni.GetSubType() && 7 != CanJni.GetSubType() && 8 != CanJni.GetSubType() && 9 != CanJni.GetSubType() && 10 != CanJni.GetSubType() && 11 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 6:
                if (1 == CanJni.GetSubType() || 2 == CanJni.GetSubType() || 3 == CanJni.GetSubType() || 4 == CanJni.GetSubType() || 5 == CanJni.GetSubType() || 6 == CanJni.GetSubType() || 7 == CanJni.GetSubType() || 8 == CanJni.GetSubType() || 9 == CanJni.GetSubType() || 10 == CanJni.GetSubType() || 11 == CanJni.GetSubType() || 12 == CanJni.GetSubType()) {
                    ret = 1;
                    break;
                }
            case 7:
                if (1 != CanJni.GetSubType() && 2 != CanJni.GetSubType() && 3 != CanJni.GetSubType() && 4 != CanJni.GetSubType() && 5 != CanJni.GetSubType() && 6 != CanJni.GetSubType() && 7 != CanJni.GetSubType() && 8 != CanJni.GetSubType() && 9 != CanJni.GetSubType() && 10 != CanJni.GetSubType() && 11 != CanJni.GetSubType() && 12 != CanJni.GetSubType()) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 8:
                if (CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9) {
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
                this.mItemLock.ShowGone(show);
                return;
            case 3:
                this.mItemHomeLight.ShowGone(show);
                return;
            case 4:
                this.mItemFindLight.ShowGone(show);
                return;
            case 5:
                this.mItemAC.ShowGone(show);
                return;
            case 6:
                this.mItemConv.ShowGone(show);
                return;
            case 7:
                this.mItemFsSet.ShowGone(show);
                return;
            case 8:
                this.mItemAvmSet.ShowGone(show);
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
                enterSubWin(CanMGCarTypeActivity.class);
                return;
            case 2:
                enterSubWin(CanMGGSLockActivity.class);
                return;
            case 3:
                enterSubWin(CanMGGSHomeLightActivity.class);
                return;
            case 4:
                enterSubWin(CanMGGSFindLightActivity.class);
                return;
            case 5:
                enterSubWin(CanMGGSACActivity.class);
                return;
            case 6:
                if (2 == CanJni.GetSubType()) {
                    enterSubWin(CanMGRx5ConvActivity.class);
                    return;
                } else {
                    enterSubWin(CanMGConvActivity.class);
                    return;
                }
            case 7:
                new CanItemMsgBox(7, this, R.string.can_sure_setup, this);
                return;
            case 8:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, -1);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onOK(int param) {
        if (param == 7) {
            CarSet(3, 1, 1);
        }
    }
}
