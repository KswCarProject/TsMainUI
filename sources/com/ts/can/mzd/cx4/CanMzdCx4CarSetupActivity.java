package com.ts.can.mzd.cx4;

import android.content.Context;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemTextBtnList;

public class CanMzdCx4CarSetupActivity extends CanMzdCx4BaseActivity {
    private static final int ITEM_DOOR = 0;
    private static final int ITEM_DRIVE = 3;
    private static final int ITEM_LIGHT = 2;
    private static final int ITEM_OTHER = 4;
    private static final int ITEM_TURN = 1;

    /* access modifiers changed from: protected */
    public void InitUI() {
        AddTextItem(R.string.can_mzd_cx4_door, 0);
        AddTextItem(R.string.can_mzd_cx4_turn, 1);
        AddTextItem(R.string.can_mzd_cx4_light, 2);
        AddTextItem(R.string.can_mzd_cx4_drive, 3);
        AddTextItem(R.string.can_mzd_cx4_other, 4);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void Query() {
    }

    /* access modifiers changed from: protected */
    public CanItemTextBtnList AddTextItem(int text, int id) {
        CanItemTextBtnList item = new CanItemTextBtnList((Context) this, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanMzdCx4CarDoorActivity.class);
                return;
            case 1:
                enterSubWin(CanMzdCx4CarTurnActivity.class);
                return;
            case 2:
                enterSubWin(CanMzdCx4CarLightActivity.class);
                return;
            case 3:
                enterSubWin(CanMzdCx4DriveDisplayActivity.class);
                return;
            case 4:
                enterSubWin(CanMzdCx4OtherSetupActivity.class);
                return;
            default:
                return;
        }
    }
}
