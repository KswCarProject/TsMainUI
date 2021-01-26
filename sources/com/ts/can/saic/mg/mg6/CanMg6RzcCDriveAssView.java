package com.ts.can.saic.mg.mg6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMg6RzcCDriveAssView extends CanScrollCarInfoView {
    public static final int ITEM_WHSJZDZD = 2;
    public static final int ITEM_XSBJ = 0;
    public static final int ITEM_XSBJZ = 1;
    private CanDataInfo.MG_GS_SET m_SetData;

    public CanMg6RzcCDriveAssView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                CanJni.MGGSCarSet(6, 22, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.MGGSCarSet(6, 21, Neg(this.m_SetData.Xsbj));
                return;
            case 2:
                CanJni.MGGSCarSet(8, 1, Neg(this.m_SetData.Whsjzdzdzk));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_tigger7_speed_warn, R.string.can_csbj, R.string.can_zdzdwhsj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = 30;
        iArr2[1] = 240;
        iArr2[2] = 5;
        iArr[1] = iArr2;
        this.m_SetData = new CanDataInfo.MG_GS_SET();
    }

    public void doOnResume() {
    }

    public void ResetData(boolean check) {
        CanJni.MGGSGetSetData4(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(0, this.m_SetData.Xsbj);
            updateItem(1, this.m_SetData.Xsbjz);
            updateItem(2, this.m_SetData.Whsjzdzdzk);
        }
    }

    public void QueryData() {
        CanJni.MGGSQuery(57);
    }
}
