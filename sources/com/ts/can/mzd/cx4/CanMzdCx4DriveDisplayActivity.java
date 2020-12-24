package com.ts.can.mzd.cx4;

import android.content.Context;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;

public class CanMzdCx4DriveDisplayActivity extends CanMzdCx4BaseActivity implements CanItemProgressList.onPosChange {
    private static final int ITEM_DRIVE_DISPLAY = 3;
    private static final int ITEM_DRIVE_HEIGHT = 0;
    private static final int ITEM_DRIVE_LIGHT = 2;
    private static final int ITEM_JIAOZHUN = 5;
    private static final int ITEM_LIGHT_CONTROL = 1;
    private static final int ITEM_NAVIGATION = 4;
    private int[] mControlArray = {R.string.can_mzd_cx4_drive_auto, R.string.can_mzd_cx4_drive_owner};
    private CanItemSwitchList mItemDisplay;
    private CanItemProgressList mItemHeight;
    private CanItemProgressList mItemJiaozhun;
    private CanItemProgressList mItemLight;
    private CanItemPopupList mItemLightControl;
    private CanItemSwitchList mItemNavigation;

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mItemLightControl = AddPopupListItem(R.string.can_mzd_cx4_drive_light_control, this.mControlArray, 1);
        this.mItemDisplay = AddCheckItem(R.string.can_mzd_cx4_drive_display, 3);
        this.mItemNavigation = AddCheckItem(R.string.can_mzd_cx4_drive_navigation, 4);
        this.mItemHeight = AddProgressItem(R.string.can_mzd_cx4_drive_height, -13, 13, 0);
        this.mItemLight = AddProgressItem(R.string.can_mzd_cx4_drive_light, -20, 20, 2);
        this.mItemJiaozhun = AddProgressItem(R.string.can_mzd_cx4_drive_jiaozhun, -2, 2, 5);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        MzdCx4GetCarSetInfo();
        if (!i2b(this.mCarSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSetData.Update)) {
            this.mCarSetData.Update = 0;
            this.mItemLightControl.SetSel(this.mCarSetData.jsxsldkz);
            this.mItemDisplay.SetCheck(this.mCarSetData.jsxszdjsxs);
            this.mItemNavigation.SetCheck(this.mCarSetData.jsxsdh);
            this.mItemHeight.SetCurVal(this.mCarSetData.jsxsgd - 13);
            this.mItemLight.SetCurVal(this.mCarSetData.jsxsld - 20);
            this.mItemJiaozhun.SetCurVal(this.mCarSetData.jsxsjz - 2);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        MzdCx4CarQuery(9, 0);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                MzdCx4SWCarSet(17, this.mCarSetData.jsxszdjsxs);
                return;
            case 4:
                MzdCx4SWCarSet(18, this.mCarSetData.jsxsdh);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                MzdCx4CarSet(15, item);
                return;
            default:
                return;
        }
    }

    private CanItemProgressList AddProgressItem(int text, int min, int max, int id) {
        CanItemProgressList item = new CanItemProgressList((Context) this, text);
        item.SetStep(1);
        item.SetMinMax(min, max);
        item.SetIdCallBack(id, this);
        item.ShowGone(true);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                MzdCx4CarSet(14, pos + 13);
                return;
            case 2:
                MzdCx4CarSet(16, pos + 20);
                return;
            case 5:
                MzdCx4CarSet(19, pos + 2);
                return;
            default:
                return;
        }
    }
}
