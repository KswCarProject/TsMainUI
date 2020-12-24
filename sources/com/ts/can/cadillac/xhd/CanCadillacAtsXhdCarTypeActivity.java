package com.ts.can.cadillac.xhd;

import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanCadillacAtsXhdCarTypeActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_TYPE = 0;
    protected String[] mCarType;
    private CanItemCarType mItemType;
    private CanScrollList mManager;

    public void onClick(View v) {
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            FtSet.SetCanSubT(item);
            if (CanJni.GetSubType() == 1) {
                Mcu.SendXKey(51);
            } else if (CanJni.GetSubType() == 2) {
                Mcu.SendXKey(52);
            } else {
                Mcu.SendXKey(50);
            }
            byte[] fileMsg = new byte[8];
            fileMsg[0] = (byte) FtSet.GetCanTp();
            fileMsg[1] = (byte) FtSet.GetCanSubT();
            CanFunc.getInstance();
            CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Factory_Set_fileName);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Mcu.SendXKey(20);
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mCarType = getResources().getStringArray(R.array.can_fs_declare_136);
        this.mItemType = this.mManager.addItemCarType(R.string.can_car_type_select, this.mCarType, 0, (CanItemPopupList.onPopItemClick) this);
        this.mItemType.GetPopItem().SetSel(CanJni.GetSubType());
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
