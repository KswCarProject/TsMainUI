package com.ts.can.se.rzc.dx7;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSeDx7RzcAvmSetView extends CanScrollCarInfoView {
    private static final int ITEM_DCSXS = 3;
    private static final int ITEM_MDJS = 2;
    private static final int ITEM_YTGCGN = 4;
    private static final int ITEM_ZXFZSY = 0;
    private static final int ITEM_ZXFZZM = 1;
    private static final int MAX_ITEM = 5;
    private CanDataInfo.SeDx7Rzc_SetData2 m_SetData;

    public CanSeDx7RzcAvmSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.SeDx7RzcCarSetData2(4, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.SeDx7RzcCarSetData2(1, Neg(this.m_SetData.Zxfzsy));
                return;
            case 1:
                CanJni.SeDx7RzcCarSetData2(2, Neg(this.m_SetData.Zxfzzm));
                return;
            case 2:
                CanJni.SeDx7RzcCarSetData2(3, Neg(this.m_SetData.Mdjs));
                return;
            case 4:
                CanJni.SeDx7RzcCarSetData2(5, Neg(this.m_SetData.Ytgcgn));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_zxfzsy, R.string.can_zxfzzm, R.string.can_jp_mdjb, R.string.can_dcsxs, R.string.can_ytgcgn};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[3] = new int[]{R.string.can_rev_cam, R.string.can_geely_boy_all_view};
        this.m_SetData = new CanDataInfo.SeDx7Rzc_SetData2();
    }

    public void ResetData(boolean check) {
        CanJni.SeDx7RzcGetSetData2(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(0, this.m_SetData.Zxfzsy);
            updateItem(1, this.m_SetData.Zxfzzm);
            updateItem(2, this.m_SetData.Mdjs);
            updateItem(3, this.m_SetData.Dcsxs);
            updateItem(4, this.m_SetData.Ytgcgn);
        }
    }

    public void QueryData() {
        CanJni.SeDx7RzcCarQuery(64, 0);
    }
}
