package com.ts.can.mitsubishi.outlander;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanMitSubiShiOutLanderCarInfoActivity extends CanBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick {
    public static final int ITEM_AMP_SET = 2;
    public static final int ITEM_CAR_SET = 3;
    public static final int ITEM_TYPE = 1;
    public static final String TAG = "CanMitSubiShiOutLanderCarInfoActivity";
    private static final int[] mTypeArr = {R.string.can_car_type1, R.string.can_car_type2};
    protected CanItemTextBtnList mItemAmpSet;
    protected CanItemTextBtnList mItemCarSet;
    protected CanItemPopupList mItemCarType;
    protected CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemCarType = AddPopupItem(R.string.can_car_type_select, mTypeArr, 1);
        this.mItemAmpSet = new CanItemTextBtnList((Context) this, R.string.can_amp_set);
        this.mItemAmpSet.SetIdClickListener(this, 2);
        this.mManager.AddView(this.mItemAmpSet.GetView());
        this.mItemCarSet = new CanItemTextBtnList((Context) this, R.string.can_vehi_setup);
        this.mItemCarSet.SetIdClickListener(this, 3);
        this.mManager.AddView(this.mItemCarSet.GetView());
        if (CanJni.GetSubType() == 0) {
            this.mItemAmpSet.ShowGone(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.d(TAG, "subtype = " + CanJni.GetSubType());
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                enterSubWin(CanMitSubiShiOutLanderAMPSetActivity.class);
                return;
            case 3:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 0);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            Log.d(TAG, "Select = " + item);
            CanJni.OutLanderCarTypeSet(item);
        }
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }
}
