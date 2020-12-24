package com.ts.can.zotye.x5;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.can.zotye.x7.CanZotyetX7CarInfoActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanZotyetX5CarInfoActivity extends CanBaseActivity implements View.OnClickListener {
    public static final int ITEM_CAR_SET = 3;
    public static final int ITEM_CAR_TMPS = 2;
    public static final int ITEM_CAR_TYPE = 1;
    private CanItemIcoList mItemCarSet;
    private CanItemIcoList mItemCarTmps;
    private CanItemIcoList mItemCarType;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        initUI();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        layoutUI();
    }

    private void initUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCarType = AddIcoItem(R.drawable.can_icon_esc, R.string.can_car_type_select, 1);
        this.mItemCarTmps = AddIcoItem(R.drawable.can_icon_tpms, R.string.can_tmps, 2);
        this.mItemCarSet = AddIcoItem(R.drawable.can_icon_setup, R.string.can_car_set, 3);
    }

    private void layoutUI() {
        int GetSubType = CanJni.GetSubType();
        this.mItemCarType.ShowGone(true);
        this.mItemCarTmps.ShowGone(true);
        this.mItemCarSet.ShowGone(true);
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
                enterSubWin(CanZotyeX5CarTypeActivity.class);
                return;
            case 2:
                if (CanJni.GetSubType() == 0) {
                    enterSubWin(CanZotyeX5NewTmpsActivity.class);
                    return;
                } else {
                    enterSubWin(CanZotyeX5TmpsActivity.class);
                    return;
                }
            case 3:
                enterSubWin(CanZotyetX7CarInfoActivity.class);
                return;
            default:
                return;
        }
    }
}
