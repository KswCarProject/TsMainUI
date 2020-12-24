package com.ts.can.cadillac.xhd;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanCadillacAtsXhdCarAirSetActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick, View.OnTouchListener {
    public static final int ITEM_LT_HOT = 0;
    public static final int ITEM_LT_WIND = 2;
    private static final int ITEM_MAX = 3;
    private static final int ITEM_MIN = 0;
    public static final int ITEM_RT_HOT = 1;
    public static final int ITEM_RT_WIND = 3;
    private CanItemIcoList mItemLtHot;
    private CanItemIcoList mItemLtWind;
    private CanItemIcoList mItemRtHot;
    private CanItemIcoList mItemRtWind;
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemLtHot = AddIcoItem(R.drawable.cadg_ac_rchair_dn, R.string.can_lt_hot, 0);
        this.mItemRtHot = AddIcoItem(R.drawable.cadg_ac_lchair_dn, R.string.can_rt_hot, 1);
        this.mItemLtWind = AddIcoItem(R.drawable.cadg_ac_rfan_dn, R.string.can_lt_wind, 2);
        this.mItemLtWind = AddIcoItem(R.drawable.cadg_ac_lfan_dn, R.string.can_rt_wind, 3);
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemIcoList AddIcoItem(int ico, int text, int id) {
        CanItemIcoList item = new CanItemIcoList(this, ico, text);
        item.SetIdTouchListener(this, id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void onItem(int id, int item) {
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            Log.d(CanBaseActivity.TAG, "****ACTION_DOWN*****");
            v.setSelected(true);
            switch (Id) {
                case 0:
                    CanJni.CadillacWithCDSendKey(80, 1);
                    break;
                case 1:
                    CanJni.CadillacWithCDSendKey(81, 1);
                    break;
                case 2:
                    CanJni.CadillacWithCDSendKey(82, 1);
                    break;
                case 3:
                    CanJni.CadillacWithCDSendKey(83, 1);
                    break;
            }
        } else if (1 == action) {
            Log.d(CanBaseActivity.TAG, "****ACTION_UP*****");
            v.setSelected(false);
            CanJni.CadillacWithCDSendKey(0, 0);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
