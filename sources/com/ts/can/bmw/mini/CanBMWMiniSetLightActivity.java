package com.ts.can.bmw.mini;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.ts.MainUI.R;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanNumInuptDlg;

public class CanBMWMiniSetLightActivity extends CanBMWMiniBaseActivity implements CanItemProgressList.onPosChange {
    public static final int ITEM_CIRCLE_LIGHT_BRI = 5;
    public static final int ITEM_CIRCLE_LIGHT_SW = 4;
    public static final int ITEM_DAY_LIGHT = 1;
    public static final int ITEM_HOME_LIGHT = 3;
    public static final int ITEM_STEERING_LIGHT = 0;
    public static final int ITEM_YB_LIGHT = 2;
    private static final int[] mZxdArr = {R.string.can_1c, R.string.can_3c, R.string.can_5c};
    private CanItemProgressList mItemCircleLightBri;
    private CanItemSwitchList mItemCircleLightSw;
    private CanItemSwitchList mItemDayLight;
    /* access modifiers changed from: private */
    public CanItemTitleValList mItemHomeLight;
    private CanItemPopupList mItemSteeringLight;
    private CanItemSwitchList mItemYbLight;
    protected CanDataInfo.CanMiniCircleLight m_Light = new CanDataInfo.CanMiniCircleLight();

    /* access modifiers changed from: protected */
    public void AddItemView() {
        this.mItemSteeringLight = AddItemPopup(R.string.can_zxdsszs, mZxdArr, 0);
        this.mItemDayLight = AddItemCheck(R.string.can_rjxcd, 1);
        this.mItemYbLight = AddItemCheck(R.string.can_yb_light, 2);
        this.mItemHomeLight = AddItemTitleVal(R.string.can_light_time, 3);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetMiniSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemSteeringLight.SetSel(this.mSetData.fgZxd);
            this.mItemDayLight.SetCheck(this.mSetData.fgRxd);
            this.mItemYbLight.SetCheck(this.mSetData.fgYbd);
            int time = this.mSetData.HomeLightTime;
            if (time < 0 || time > 240) {
                this.mItemHomeLight.SetVal("");
            } else {
                this.mItemHomeLight.SetVal(String.valueOf(String.valueOf(time)) + " 秒");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        Query(33);
        Sleep(1);
        Query(34);
        Sleep(1);
        Query(35);
        Sleep(1);
        Query(36);
        Sleep(1);
        Query(25);
    }

    public void onClick(View v) {
        int param;
        int param2;
        int id = ((Integer) v.getTag()).intValue();
        int param3 = getCarSetParam();
        switch (id) {
            case 1:
                if (this.mSetData.fgRxd == 1) {
                    param2 = param3 - 64;
                } else {
                    param2 = param3 + 64;
                }
                CarSet(34, param2);
                return;
            case 2:
                if (this.mSetData.fgYbd == 1) {
                    param = param3 - 32;
                } else {
                    param = param3 + 32;
                }
                CarSet(35, param);
                return;
            case 3:
                new CanNumInuptDlg(this, new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 240) {
                            CanBMWMiniSetLightActivity.this.mItemHomeLight.SetVal(String.valueOf(val) + " 秒");
                            CanBMWMiniSetLightActivity.this.CarSet(36, num);
                        }
                    }
                }, 3, id);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        super.onItem(id, item);
        switch (id) {
            case 0:
                if (item == 0) {
                    CarSet(33, 0);
                    return;
                } else if (item == 1) {
                    CarSet(33, 128);
                    return;
                } else if (item == 2) {
                    CarSet(33, 192);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private int getCarSetParam() {
        int param = 0;
        if (this.mSetData.fgZxd == 1) {
            param = 0 + 128;
        }
        if (this.mSetData.fgRxd == 1) {
            param += 64;
        }
        if (this.mSetData.fgYbd == 1) {
            return param + 32;
        }
        return param;
    }

    public void onChanged(int id, int pos) {
        switch (id) {
        }
    }
}
