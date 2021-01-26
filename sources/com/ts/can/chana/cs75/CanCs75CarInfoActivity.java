package com.ts.can.chana.cs75;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanCs75CarInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_AIR = 5;
    public static final int ITEM_CAMERA = 10;
    public static final int ITEM_DEFAULT = 7;
    public static final int ITEM_DISPLAY = 2;
    public static final int ITEM_DOOR = 4;
    public static final int ITEM_ILL = 6;
    private static final int ITEM_MAX = 10;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_SSCZ = 3;
    public static final int ITEM_TPMS = 9;
    public static final int ITEM_TPMS_SET = 8;
    public static final int ITEM_TYPE = 1;
    public static final String TAG = "CanCs75CarInfoActivity";
    private CanItemIcoList mItemAir;
    private CanItemIcoList mItemCamera;
    private CanItemCarType mItemCarType;
    private CanItemIcoList mItemDefault;
    private CanItemIcoList mItemDisplay;
    private CanItemIcoList mItemDoor;
    private CanItemIcoList mItemIll;
    private CanItemIcoList mItemSscz;
    private CanItemIcoList mItemTpms;
    private CanItemIcoList mItemTpmsSet;
    private CanScrollList mManager;
    private String[] mTypeArr;
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
        CanJni.Cs75CarQuery(82, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
        ResetData(false);
        QueryData();
        Log.d(TAG, "subtype = " + CanJni.GetSubType());
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mTypeArr = getResources().getStringArray(R.array.can_fs_declare_73);
        this.mItemCarType = new CanItemCarType((Context) this, R.string.can_car_type_select, this.mTypeArr, 1, (CanItemPopupList.onPopItemClick) this);
        this.mManager.AddView(this.mItemCarType.GetView());
        this.mItemDisplay = AddIcoItem(R.drawable.can_icon_mfd, R.string.can_by_service, 2);
        this.mItemSscz = AddIcoItem(R.drawable.can_icon_driver_assist, R.string.can_jsfz, 3);
        this.mItemDoor = AddIcoItem(R.drawable.can_icon_lock, R.string.can_win_opt, 4);
        this.mItemAir = AddIcoItem(R.drawable.can_icon_ac, R.string.can_ac_set, 5);
        this.mItemIll = AddIcoItem(R.drawable.can_icon_light, R.string.can_light_set, 6);
        this.mItemTpms = AddIcoItem(R.drawable.can_icon_pm, R.string.can_tyres_tpms, 9);
        this.mItemTpmsSet = AddIcoItem(R.drawable.can_icon_tyres, R.string.can_tpms_set, 8);
        this.mItemCamera = AddIcoItem(R.drawable.can_golf_icon14, R.string.can_camera_status, 10);
        this.mItemDefault = AddIcoItem(R.drawable.can_icon_service, R.string.can_factory_set, 7);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 10; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 2:
                if (CanJni.GetSubType() != 2 && CanJni.GetSubType() != 5 && CanJni.GetSubType() != 7 && CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 3:
                if (CanJni.GetSubType() != 2) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 4:
                if (CanJni.GetSubType() != 2) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 5:
                if (CanJni.GetSubType() != 2) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 6:
                if (CanJni.GetSubType() != 2) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 7:
                if (CanJni.GetSubType() != 2 && CanJni.GetSubType() != 5 && CanJni.GetSubType() != 11) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
                break;
            case 8:
                if (CanJni.GetSubType() != 11 && CanJni.GetSubType() != 12) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 9:
                if (CanJni.GetSubType() != 4 && CanJni.GetSubType() != 5 && CanJni.GetSubType() != 9 && CanJni.GetSubType() != 8 && CanJni.GetSubType() != 13 && CanJni.GetSubType() != 15) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 10:
                ret = 1;
                break;
        }
        if (CanJni.GetSubType() == 8) {
            ret = 1;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 2:
                this.mItemDisplay.ShowGone(show);
                return;
            case 3:
                this.mItemSscz.ShowGone(show);
                return;
            case 4:
                this.mItemDoor.ShowGone(show);
                return;
            case 5:
                this.mItemAir.ShowGone(show);
                return;
            case 6:
                this.mItemIll.ShowGone(show);
                return;
            case 7:
                this.mItemDefault.ShowGone(show);
                return;
            case 8:
                this.mItemTpmsSet.ShowGone(show);
                return;
            case 9:
                this.mItemTpms.ShowGone(show);
                return;
            case 10:
                this.mItemCamera.ShowGone(show);
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
            case 2:
                enterSubWin(CanCs75CarDisplayInfoActivity.class);
                return;
            case 3:
                enterSubWin(CanCs75CarDriveAssisActivity.class);
                return;
            case 4:
                enterSubWin(CanCs75CarDoorSetActivity.class);
                return;
            case 5:
                enterSubWin(CanCs75CarAirSetActivity.class);
                return;
            case 6:
                enterSubWin(CanCs75CarIllSetActivity.class);
                return;
            case 7:
                new CanItemMsgBox(7, this, R.string.can_sure_setup, this);
                return;
            case 8:
                new CanItemMsgBox(8, this, R.string.can_sure_tybd, this);
                return;
            case 9:
                enterSubWin(CanChanATpmsActivity.class);
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

    public void onItem(int id, int item) {
        if (id == 1) {
            Log.d(TAG, "Select = " + item);
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }

    public void onOK(int param) {
        switch (param) {
            case 7:
                CanJni.Cs75CarSet(0, 1);
                return;
            case 8:
                CanJni.Cs75CarSet(12, 1);
                return;
            default:
                return;
        }
    }
}
