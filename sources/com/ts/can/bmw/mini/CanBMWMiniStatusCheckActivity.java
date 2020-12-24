package com.ts.can.bmw.mini;

import android.content.Context;
import com.ts.MainUI.R;
import com.ts.canview.CanItemTextBtnList;

public class CanBMWMiniStatusCheckActivity extends CanBMWMiniBaseActivity {
    private CanItemTextBtnList mItemDoor;
    private CanItemTextBtnList mItemNone;
    private CanItemTextBtnList mItemReset;

    /* access modifiers changed from: protected */
    public void AddItemView() {
        this.mItemNone = AddIcoItem(0);
        this.mItemDoor = AddIcoItem(R.string.can_check_control_door);
        this.mItemReset = AddIcoItem(R.string.can_check_control_reset);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        boolean z = true;
        GetMiniCheck();
        if (!i2b(this.mCarCheck.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarCheck.Update)) {
            this.mCarCheck.Update = 0;
            if (this.mCarCheck.Num == 0) {
                this.mItemNone.SetVal(R.string.can_check_control_none);
                this.mItemNone.ShowGone(true);
                this.mItemDoor.ShowGone(false);
                this.mItemReset.ShowGone(false);
            }
            int curIndex = this.mCarCheck.CurIndex;
            if (curIndex >= 1 && curIndex <= this.mCarCheck.Num) {
                boolean doorFlag = false;
                boolean resetFlag = false;
                for (int i = 0; i < 8; i++) {
                    if (this.mCarCheck.Item[i] == 1) {
                        doorFlag = true;
                    } else if (this.mCarCheck.Item[i] == 2) {
                        resetFlag = true;
                    }
                }
                this.mItemNone.SetVal(R.string.can_check_control_ok);
                CanItemTextBtnList canItemTextBtnList = this.mItemNone;
                if (doorFlag || resetFlag) {
                    z = false;
                }
                canItemTextBtnList.ShowGone(z);
                this.mItemDoor.ShowGone(doorFlag);
                this.mItemReset.ShowGone(resetFlag);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        Query(76);
    }

    private CanItemTextBtnList AddIcoItem(int text) {
        CanItemTextBtnList item = new CanItemTextBtnList((Context) this, text);
        item.ShowArrow(false);
        item.ShowGone(false);
        this.mManager.AddView(item.GetView());
        return item;
    }
}
