package com.ts.can.honda.accord_xbs;

import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanHondaDAConsumpCurrentActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanAccordXbsCarInfoActivity extends CanAccordXbsBaseActivity implements View.OnClickListener, UserCallBack, CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_CAR_TYPE = 1;
    public static final int ITEM_CONSUMP = 3;
    public static final int ITEM_DISTAMCE_SET = 4;
    public static final int ITEM_DRIVE_ASS = 8;
    public static final int ITEM_FACTORY = 7;
    public static final int ITEM_LIGHT = 5;
    public static final int ITEM_LOCK = 6;
    private static final int ITEM_MAX = 9;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SCREEN = 2;
    public static final int ITEM_TPMS_SET = 9;
    public static final String TAG = "CanAccordCarInfoActivity";
    private CanItemIcoList mItemCarType;
    private CanItemIcoList mItemConsump;
    private CanItemIcoList mItemDistance;
    private CanItemIcoList mItemDriveAss;
    private CanItemIcoList mItemFactory;
    private CanItemIcoList mItemLight;
    private CanItemIcoList mItemLock;
    private CanItemIcoList mItemScreen;
    private CanItemIcoList mItemTpmsSet;
    private CanScrollList mManager;

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
        Query(10, 0);
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
        this.mItemCarType = new CanItemIcoList(this, R.drawable.can_icon_light2, R.string.can_car_type_select);
        this.mItemScreen = new CanItemIcoList(this, R.drawable.can_icon_light2, R.string.can_screen_setup);
        this.mItemConsump = new CanItemIcoList(this, R.drawable.can_icon_mfd, R.string.can_consumption);
        this.mItemDistance = new CanItemIcoList(this, R.drawable.can_icon_sudu, R.string.can_distance_sz);
        this.mItemLight = new CanItemIcoList(this, R.drawable.can_icon_light, R.string.can_light_setup);
        this.mItemLock = new CanItemIcoList(this, R.drawable.can_icon_lock2, R.string.can_car_lock_set);
        this.mItemDriveAss = new CanItemIcoList(this, R.drawable.can_icon_service, R.string.can_jsfzxysz);
        this.mItemTpmsSet = new CanItemIcoList(this, R.drawable.can_icon_tyres, R.string.can_tpms_set);
        this.mItemFactory = new CanItemIcoList(this, R.drawable.can_icon_setup, R.string.can_factory_set);
        this.mItemCarType.SetIdClickListener(this, 1);
        this.mItemScreen.SetIdClickListener(this, 2);
        this.mItemConsump.SetIdClickListener(this, 3);
        this.mItemDistance.SetIdClickListener(this, 4);
        this.mItemLight.SetIdClickListener(this, 5);
        this.mItemLock.SetIdClickListener(this, 6);
        this.mItemDriveAss.SetIdClickListener(this, 8);
        this.mItemTpmsSet.SetIdClickListener(this, 9);
        this.mItemFactory.SetIdClickListener(this, 7);
        this.mManager.AddView(this.mItemCarType.GetView());
        this.mManager.AddView(this.mItemScreen.GetView());
        this.mManager.AddView(this.mItemConsump.GetView());
        this.mManager.AddView(this.mItemDistance.GetView());
        this.mManager.AddView(this.mItemLight.GetView());
        this.mManager.AddView(this.mItemLock.GetView());
        this.mManager.AddView(this.mItemDriveAss.GetView());
        this.mManager.AddView(this.mItemTpmsSet.GetView());
        this.mManager.AddView(this.mItemFactory.GetView());
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 9; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = 0;
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
            case 5:
                ret = 1;
                break;
            case 6:
                ret = 1;
                break;
            case 7:
                ret = 1;
                break;
            case 8:
                ret = 1;
                break;
            case 9:
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
                this.mItemCarType.ShowGone(show);
                return;
            case 2:
                this.mItemScreen.ShowGone(show);
                return;
            case 3:
                this.mItemConsump.ShowGone(show);
                return;
            case 4:
                this.mItemDistance.ShowGone(show);
                return;
            case 5:
                this.mItemLight.ShowGone(show);
                return;
            case 6:
                this.mItemLock.ShowGone(show);
                return;
            case 7:
                this.mItemFactory.ShowGone(show);
                return;
            case 8:
                this.mItemDriveAss.ShowGone(show);
                return;
            case 9:
                this.mItemTpmsSet.ShowGone(show);
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

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                enterSubWin(CanAccordXbsScreenActivity.class);
                return;
            case 3:
                enterSubWin(CanHondaDAConsumpCurrentActivity.class);
                return;
            case 4:
                enterSubWin(CanAccordXbsDistanceActivity.class);
                return;
            case 5:
                enterSubWin(CanAccordXbsLightActivity.class);
                return;
            case 6:
                enterSubWin(CanAccordXbsLockActivity.class);
                return;
            case 7:
                new CanItemMsgBox(7, this, R.string.can_sure_setup, this);
                return;
            case 8:
                enterSubWin(CanAccordXbsSetDriveAssActivity.class);
                return;
            case 9:
                new CanItemMsgBox(9, this, R.string.can_sure_tybd, this);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onOK(int param) {
        switch (param) {
            case 7:
                CarSet(15, 0);
                return;
            case 9:
                CarSet(17, 0);
                return;
            default:
                return;
        }
    }
}
