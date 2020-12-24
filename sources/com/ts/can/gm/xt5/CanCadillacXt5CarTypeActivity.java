package com.ts.can.gm.xt5;

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

public class CanCadillacXt5CarTypeActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_TYPE = 0;
    private static final int[] mTypeArray = {R.string.can_ty, R.string.can_yl15, R.string.can_gl8_2017, R.string.can_cadillac_xt5, R.string.can_Lincs_mkc, R.string.can_Lincs_mkz, R.string.can_cadillac_ats_low, R.string.can_cadillac_ats_high};
    private CanItemCarType mItemType;
    private CanScrollList mManager;

    public void onClick(View v) {
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            FtSet.SetCanSubT(item);
            byte[] fileMsg = new byte[8];
            fileMsg[0] = (byte) FtSet.GetCanTp();
            fileMsg[1] = (byte) FtSet.GetCanSubT();
            if (FtSet.GetCanSubT() == 6) {
                fileMsg[1] = 0;
            } else if (FtSet.GetCanSubT() == 7 || FtSet.GetCanSubT() == 11) {
                fileMsg[1] = 1;
            } else if (FtSet.GetCanSubT() == 8) {
                fileMsg[1] = 2;
            } else if (FtSet.GetCanSubT() == 9) {
                fileMsg[1] = 3;
            }
            CanFunc.getInstance();
            CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Factory_Set_fileName);
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
        this.mItemType = this.mManager.addItemCarType(R.string.can_car_type_select, mTypeArray, 0, (CanItemPopupList.onPopItemClick) this);
        this.mItemType.GetPopItem().SetSel(CanJni.GetSubType());
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
