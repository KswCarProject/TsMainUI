package com.ts.can.byd.rsw;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanBydRswCarSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    protected static final int ITEM_CAWDKGBSSC = 3;
    protected static final int ITEM_CAWDKGJSJC = 4;
    protected static final int ITEM_LDKG = 0;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 0;
    protected static final int ITEM_YKJC = 2;
    protected static final int ITEM_YKSC = 1;
    private CanDataInfo.BydRsw_CarSet m_SetData;

    public CanBydRswCarSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.BydRswCarSet(1, Neg(this.m_SetData.Ldks));
                return;
            case 1:
                CanJni.BydRswCarSet(3, Neg(this.m_SetData.Yksc));
                return;
            case 2:
                CanJni.BydRswCarSet(4, Neg(this.m_SetData.Ykjc));
                return;
            case 3:
                CanJni.BydRswCarSet(5, Neg(this.m_SetData.Cawdkgbssc));
                return;
            case 4:
                CanJni.BydRswCarSet(6, Neg(this.m_SetData.Cawdkgjsjc));
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_psa_wc_dcld, R.string.can_carset_ykss, R.string.can_carset_ykjc, R.string.can_cawdkgbssc, R.string.can_cawdkgjsjc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK};
        this.m_SetData = new CanDataInfo.BydRsw_CarSet();
    }

    public void ResetData(boolean check) {
        CanJni.BydRswGetCarSetData(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(0, this.m_SetData.Ldks);
            updateItem(1, this.m_SetData.Yksc);
            updateItem(2, this.m_SetData.Ykjc);
            updateItem(3, this.m_SetData.Cawdkgbssc);
            updateItem(4, this.m_SetData.Cawdkgjsjc);
        }
    }

    public void QueryData() {
        CanJni.BydRswQuery(41, 0, 0, 0);
    }
}
