package com.ts.can.mzd.cx4;

import android.view.View;
import com.ts.MainUI.R;
import com.ts.canview.CanItemIcoList;

public class CanMzdCx4CarInfoActivity extends CanMzdCx4BaseActivity {
    private static final int ITEM_OIL = 1;
    private static final int ITEM_SETUP = 0;

    /* access modifiers changed from: protected */
    public void InitUI() {
        AddIconItem(R.drawable.can_icon_setup, R.string.can_mzd_cx4_setup, 0);
        AddIconItem(R.drawable.can_icon_youhao, R.string.can_mzd_cx4_oil, 1);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void Query() {
    }

    /* access modifiers changed from: protected */
    public CanItemIcoList AddIconItem(int icon, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, icon, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanMzdCx4CarSetupActivity.class);
                return;
            case 1:
                enterSubWin(CanMzdCx4CarOilActivity.class);
                return;
            default:
                return;
        }
    }
}
