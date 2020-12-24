package com.ts.can.honda.accord;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.can.CanHondaDAConsumpCurrentActivity;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanAccordCarInfoActivity extends CanAccordBaseActivity implements View.OnClickListener, UserCallBack, CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_ASSIST = 10;
    public static final int ITEM_CAMERA_STATUS = 7;
    public static final int ITEM_CAM_MODE = 2;
    public static final int ITEM_CAR_TYPE = 1;
    public static final int ITEM_CONSUMP = 3;
    public static final int ITEM_FACTORY = 9;
    public static final int ITEM_LIGHT = 5;
    public static final int ITEM_LOCK = 6;
    private static final int ITEM_MAX = 10;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SCREEN = 4;
    public static final int ITEM_TPMS_SET = 8;
    public static final String TAG = "CanAccordCarInfoActivity";
    private boolean isCh2016;
    private CanItemTextBtnList mItemAssist;
    private CanItemTextBtnList mItemCamMode;
    private CanItemTextBtnList mItemCameraStatus;
    private CanItemTextBtnList mItemCarType;
    private CanItemTextBtnList mItemConsump;
    private CanItemTextBtnList mItemFactory;
    private CanItemTextBtnList mItemLight;
    private CanItemTextBtnList mItemLock;
    private CanItemTextBtnList mItemScreen;
    private CanItemTextBtnList mItemTpmsSet;
    private int mLowVer = 0;
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
        Query(50, 0);
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
        this.mItemCarType = AddTextBtn(R.string.can_car_type_select, 1);
        this.mItemCamMode = AddTextBtn(R.string.can_screen_setup, 2);
        this.mItemConsump = AddTextBtn(R.string.can_consumption, 3);
        this.mItemScreen = AddTextBtn(R.string.can_screen_setup, 4);
        this.mItemLight = AddTextBtn(R.string.can_light_setup, 5);
        this.mItemLock = AddTextBtn(R.string.can_car_lock_set, 6);
        this.mItemAssist = AddTextBtn(R.string.can_jsfz, 10);
        this.mItemCameraStatus = AddTextBtn(R.string.can_camera_status, 7);
        this.mItemTpmsSet = AddTextBtn(R.string.can_tpms_set, 8);
        this.mItemFactory = AddTextBtn(R.string.can_factory_set, 9);
    }

    private CanItemTextBtnList AddTextBtn(int text, int id) {
        CanItemTextBtnList btn = new CanItemTextBtnList((Context) this, text);
        btn.SetIdClickListener(this, id);
        this.mManager.AddView(btn.GetView());
        btn.ShowGone(false);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        int i;
        boolean z = true;
        int subType = CanJni.GetSubType();
        if (subType == 2) {
            i = 0;
        } else {
            i = 1;
        }
        this.mLowVer = i;
        if (subType != 3) {
            z = false;
        }
        this.isCh2016 = z;
        for (int i2 = 1; i2 <= 10; i2++) {
            ShowItem(i2);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        int lowVer = this.mLowVer;
        switch (item) {
            case 1:
                ret = 1;
                break;
            case 2:
                ret = 1 - lowVer;
                break;
            case 3:
                ret = lowVer;
                break;
            case 4:
                ret = lowVer;
                break;
            case 5:
                ret = lowVer;
                break;
            case 6:
                ret = lowVer;
                break;
            case 7:
                if (!this.isCh2016) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 8:
                if (!this.isCh2016) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 9:
                ret = lowVer;
                break;
            case 10:
                if (CanJni.GetCanFsTp() == 270) {
                    ret = 1;
                    break;
                }
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
                this.mItemCamMode.ShowGone(show);
                return;
            case 3:
                this.mItemConsump.ShowGone(show);
                return;
            case 4:
                this.mItemScreen.ShowGone(show);
                return;
            case 5:
                this.mItemLight.ShowGone(show);
                return;
            case 6:
                this.mItemLock.ShowGone(show);
                return;
            case 7:
                this.mItemCameraStatus.ShowGone(show);
                return;
            case 8:
                this.mItemTpmsSet.ShowGone(show);
                return;
            case 9:
                this.mItemFactory.ShowGone(show);
                return;
            case 10:
                this.mItemAssist.ShowGone(show);
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
            case 1:
                enterSubWin(CanAccordCarTypeActivity.class);
                return;
            case 2:
                enterSubWin(CanAccordCamModeActivity.class);
                return;
            case 3:
                enterSubWin(CanHondaDAConsumpCurrentActivity.class);
                return;
            case 4:
                enterSubWin(CanAccordScreenActivity.class);
                return;
            case 5:
                enterSubWin(CanAccordLightActivity.class);
                return;
            case 6:
                enterSubWin(CanAccordLockActivity.class);
                return;
            case 7:
                enterSubWin(CanAccordCameraStatusActivity.class);
                return;
            case 8:
                new CanItemMsgBox(8, this, R.string.can_sure_tybd, this);
                return;
            case 9:
                new CanItemMsgBox(9, this, R.string.can_sure_setup, this);
                return;
            case 10:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 0);
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
            case 8:
                CarSet(17, 0);
                return;
            case 9:
                CarSet(15, 0);
                return;
            default:
                return;
        }
    }
}
