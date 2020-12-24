package com.ts.can.se.rzc.dx7;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanSeDx7RzcPcInfoView extends CanScrollCarInfoView {
    private static final int ITEM_BGLYH = 0;
    private static final int ITEM_KXSLC = 2;
    private static final int ITEM_SYYL = 1;
    private static final int ITEM_XSLC = 3;
    private static final int MAX_ITEM = 4;
    private CanDataInfo.CAN_Msg m_SetData;

    public CanSeDx7RzcPcInfoView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_bglyh, R.string.can_rest_oil, R.string.can_range_xhlc, R.string.can_driving_mileage};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.m_SetData = new CanDataInfo.CAN_Msg();
    }

    public void ResetData(boolean check) {
        CanJni.GetCarInfo(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(0, this.m_SetData.AveOil, String.format("%.1f L", new Object[]{Double.valueOf(((double) this.m_SetData.AveOil) * 0.1d)}));
            updateItem(1, this.m_SetData.Syyl, String.format("%d %%", new Object[]{Integer.valueOf(this.m_SetData.Syyw)}));
            updateItem(2, this.m_SetData.EndurOil, String.format("%d Km", new Object[]{Integer.valueOf(this.m_SetData.EndurOil)}));
            updateItem(3, this.m_SetData.Distance, String.format("%d Km", new Object[]{Integer.valueOf(this.m_SetData.Distance)}));
        }
    }

    public void QueryData() {
        CanJni.SeDx7RzcCarQuery(51, 0);
    }
}
