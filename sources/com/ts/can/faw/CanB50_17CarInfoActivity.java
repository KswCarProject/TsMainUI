package com.ts.can.faw;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanB50_17CarInfoActivity extends CanBaseActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_TYPE = 0;
    private CanItemCarType mItemCarType;
    private CanScrollList mManager;
    private String[] mTypeArr;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mTypeArr = getResources().getStringArray(R.array.can_fs_declare_71);
        InitUI();
    }

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemCarType = new CanItemCarType((Context) this, R.string.can_car_type_select, this.mTypeArr, 0, (CanItemPopupList.onPopItemClick) this);
        this.mManager.AddView(this.mItemCarType.GetView());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.d(CanBaseActivity.TAG, "subtype = " + CanJni.GetSubType());
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            Log.d(CanBaseActivity.TAG, "Select = " + item);
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }
}
