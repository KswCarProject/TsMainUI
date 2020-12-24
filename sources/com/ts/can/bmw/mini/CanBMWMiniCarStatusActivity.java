package com.ts.can.bmw.mini;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanBMWMiniCarStatusActivity extends CanBaseActivity implements View.OnClickListener {
    public static final int ITEM_CHECK_CONTROL = 3;
    public static final int ITEM_IAP = 4;
    public static final int ITEM_OIL = 1;
    public static final int ITEM_RPA = 0;
    public static final int ITEM_SERVICE_NEED = 2;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        initUI();
    }

    private void initUI() {
        this.mManager = new CanScrollList(this);
        AddIcoItem(R.string.can_rpa, 0);
        AddIcoItem(R.string.can_oil, 1);
        AddIcoItem(R.string.can_by_service, 2);
        AddIcoItem(R.string.can_check_control, 3);
        AddIcoItem(R.string.can_can_iap, 4);
    }

    /* access modifiers changed from: protected */
    public CanItemTextBtnList AddIcoItem(int text, int id) {
        CanItemTextBtnList item = new CanItemTextBtnList((Context) this, text);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanBMWMiniStatusRPAActivity.class);
                return;
            case 1:
                enterSubWin(CanBMWMiniStatusOilActivity.class);
                return;
            case 2:
                enterSubWin(CanBMWMiniStatusServiceActivity.class);
                return;
            case 3:
                enterSubWin(CanBMWMiniStatusCheckActivity.class);
                return;
            case 4:
                CanFunc.getInstance();
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, -1);
                return;
            default:
                return;
        }
    }
}
