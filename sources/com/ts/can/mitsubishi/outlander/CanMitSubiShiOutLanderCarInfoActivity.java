package com.ts.can.mitsubishi.outlander;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanMitSubiShiOutLanderCarInfoActivity extends CanBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick {
    public static final int ITEM_AMP_SET = 2;
    public static final int ITEM_TYPE = 1;
    public static final String TAG = "CanMitSubiShiOutLanderCarInfoActivity";
    private static final int[] mTypeArr = {R.string.can_amp_type1, R.string.can_amp_type2};
    protected CanItemTextBtnList mItemAmpSet;
    protected CanItemCarType mItemCarType;
    protected CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemCarType = new CanItemCarType((Context) this, R.string.can_car_type_select, mTypeArr, 1, (CanItemPopupList.onPopItemClick) this);
        this.mManager.AddView(this.mItemCarType.GetView());
        this.mItemAmpSet = new CanItemTextBtnList((Context) this, R.string.can_amp_set);
        this.mItemAmpSet.SetIdClickListener(this, 2);
        this.mManager.AddView(this.mItemAmpSet.GetView());
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
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                enterSubWin(CanMitSubiShiOutLanderAMPSetActivity.class);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            Log.d(TAG, "Select = " + item);
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }
}
