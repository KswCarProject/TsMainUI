package com.ts.can.byd.dj.m6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBydM6DjCarSetView extends CanScrollCarInfoView {
    private static final int ITEM_DDHMGN = 0;
    private static final int ITEM_MAX = 2;
    private static final int ITEM_YKSJC = 1;
    private CanDataInfo.BydM6Dj_SetData m_SetData;

    public CanBydM6DjCarSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        switch (id) {
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.BydM6DjCarSet(1, Neg(this.m_SetData.Ddhmgn));
                return;
            case 1:
                CanJni.BydM6DjCarSet(2, Neg(this.m_SetData.Yksjc));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ddhmgn, R.string.can_yksjc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK};
        this.m_SetData = new CanDataInfo.BydM6Dj_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.BydM6DjGetCarSetData(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(0, this.m_SetData.Ddhmgn);
            updateItem(1, this.m_SetData.Yksjc);
        }
    }

    public void QueryData() {
        CanJni.BydM6DjDataQuery(6);
    }
}
