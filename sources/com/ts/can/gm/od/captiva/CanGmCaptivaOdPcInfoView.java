package com.ts.can.gm.od.captiva;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGmCaptivaOdPcInfoView extends CanScrollCarInfoView {
    private static final int ITEM_PJYH = 0;
    private static final int ITEM_XXJL = 1;
    private static final int MAX_ITEM = 2;
    private CanDataInfo.GmCaptivaOd_PcInfo m_SetData;

    public CanGmCaptivaOdPcInfoView(Activity activity) {
        super(activity, 2);
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
        this.mItemTitleIds = new int[]{R.string.can_avg_consump, R.string.can_range_xhlc};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.m_SetData = new CanDataInfo.GmCaptivaOd_PcInfo();
    }

    public void ResetData(boolean check) {
        CanJni.GmCaptivaOdGetPcInfo(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(0, this.m_SetData.Pjyh, String.format("%.1f" + YhDw(this.m_SetData.PjyhDw), new Object[]{Double.valueOf(((double) this.m_SetData.Pjyh) * 0.1d)}));
            updateItem(1, this.m_SetData.Xxjl, String.valueOf(this.m_SetData.Xxjl) + XhlcDw(this.m_SetData.XxjlDw));
        }
    }

    public String YhDw(int Dw) {
        if (Dw == 0) {
            return " mpg";
        }
        if (Dw == 1) {
            return " km/l";
        }
        if (Dw == 2) {
            return " l/100km";
        }
        return " ";
    }

    public String XhlcDw(int Dw) {
        if (Dw == 0) {
            return " km";
        }
        return " mile";
    }

    public void QueryData() {
        CanJni.GmCaptivaOdQuery(51, 0);
    }
}
