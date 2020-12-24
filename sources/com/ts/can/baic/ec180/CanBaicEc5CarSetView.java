package com.ts.can.baic.ec180;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanBaicEc5CarSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    protected static final int ITEM_DTB = 1;
    private static final int ITEM_MAX = 3;
    protected static final int ITEM_RX = 2;
    protected static final int ITEM_SCTBZT = 0;
    private CanDataInfo.BaicEc_SetData m_SetData;

    public CanBaicEc5CarSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int temp = 0;
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                if (this.m_SetData.Rx > 0) {
                    temp = 64;
                }
                if (this.m_SetData.Dtb == 0) {
                    temp |= 128;
                }
                CanJni.BaicEc180RzcCarSet(temp);
                return;
            case 2:
                if (this.m_SetData.Dtb > 0) {
                    temp = 128;
                }
                if (this.m_SetData.Rx == 0) {
                    temp |= 64;
                }
                CanJni.BaicEc180RzcCarSet(temp);
                return;
            default:
                return;
        }
    }

    public void onOK(int param) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_sctbzt, R.string.can_dtb, R.string.can_rux};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK};
        this.mPopValueIds[0] = new int[]{R.string.can_brake_off, R.string.can_caix};
        this.mItemVisibles[1] = 0;
        this.mItemVisibles[2] = 0;
        this.m_SetData = new CanDataInfo.BaicEc_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.BaicEc180RzcGetSetData(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(0, this.m_SetData.Sctbzt, getString(this.mPopValueIds[0][this.m_SetData.Sctbzt]));
            updateItem(1, this.m_SetData.Dtb);
            updateItem(2, this.m_SetData.Rx);
            if (this.m_SetData.Sctbzt > 0) {
                showItem(1, 1);
                showItem(2, 1);
                return;
            }
            showItem(1, 0);
            showItem(2, 0);
        }
    }

    public void QueryData() {
        CanJni.BaicEcQuery(56);
    }
}
