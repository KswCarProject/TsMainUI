package com.ts.can.se.rzc.dx7;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanNumInuptDlg;

public class CanSeDx7RzcCarSetView extends CanScrollCarInfoView {
    private static final int ITEM_LANG = 4;
    private static final int ITEM_LANG_TITLE = 3;
    private static final int ITEM_TIME_HOUR = 1;
    private static final int ITEM_TIME_MIN = 2;
    private static final int ITEM_TIME_TITLE = 0;
    private static final int MAX_ITEM = 5;
    /* access modifiers changed from: private */
    public CanDataInfo.SeDx7Rzc_SetData m_SetData;

    public CanSeDx7RzcCarSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 4:
                CanJni.SeDx7RzcCarSetData(this.m_SetData.Hour, this.m_SetData.Min, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 1:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 23) {
                            CanJni.SeDx7RzcCarSetData(num, CanSeDx7RzcCarSetView.this.m_SetData.Min, CanSeDx7RzcCarSetView.this.m_SetData.Lang);
                        }
                    }
                }, 2, id);
                return;
            case 2:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 59) {
                            CanJni.SeDx7RzcCarSetData(CanSeDx7RzcCarSetView.this.m_SetData.Hour, num, CanSeDx7RzcCarSetView.this.m_SetData.Lang);
                        }
                    }
                }, 2, id);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_time_set, R.string.can_hour, R.string.can_min, R.string.can_system, R.string.can_car_lang};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[4] = new int[]{R.string.lang_cn, R.string.lang_cn_ft, R.string.lang_en_uk};
        this.m_SetData = new CanDataInfo.SeDx7Rzc_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.SeDx7RzcGetSetData(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            updateItem(1, this.m_SetData.Hour, String.format("%02d", new Object[]{Integer.valueOf(this.m_SetData.Hour)}));
            updateItem(2, this.m_SetData.Min, String.format("%02d", new Object[]{Integer.valueOf(this.m_SetData.Min)}));
            updateItem(4, this.m_SetData.Lang);
        }
    }

    public void QueryData() {
        CanJni.SeDx7RzcCarQuery(50, 0);
    }
}
