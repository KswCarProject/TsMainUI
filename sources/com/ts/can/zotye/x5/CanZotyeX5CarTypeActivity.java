package com.ts.can.zotye.x5;

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

public class CanZotyeX5CarTypeActivity extends CanBaseActivity implements CanItemPopupList.onPopItemClick {
    public static final int ITEM_TYPE = 1;
    private CanItemCarType mItemCarType;
    private CanScrollList mManager;
    private String[] mTypeArr;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mTypeArr = getResources().getStringArray(R.array.can_fs_declare_60);
        this.mItemCarType = new CanItemCarType((Context) this, R.string.can_car_type_select, this.mTypeArr, 1, (CanItemPopupList.onPopItemClick) this);
        this.mManager.AddView(this.mItemCarType.GetView());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        int sub = CanJni.GetSubType();
        Log.d(CanBaseActivity.TAG, "subtype = " + sub);
        this.mItemCarType.GetPopItem().SetSel(sub);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            Log.d(CanBaseActivity.TAG, "Select = " + item);
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }
}
