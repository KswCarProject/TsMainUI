package com.ts.can.mitsubishi.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMitsubshiRzcTumSignalView extends CanScrollCarInfoView {
    private static final int ITEM_ONE_TOUCH_FOR_3_FLASHES = 1;
    private static final int ITEM_OPERA_CONDITIONS = 0;
    private static final int ITEM_OPERA_TIME_FOR_LEV_TRIGGER_3_FLASH = 2;
    private CanDataInfo.MitSubishiRzcSet m_SetData;

    public CanMitsubshiRzcTumSignalView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(14, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(14, 144);
                    return;
                } else {
                    return;
                }
            case 1:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(15, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(15, 144);
                    return;
                } else {
                    return;
                }
            case 2:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(16, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(16, 144);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_opera_conditions, R.string.can_one_touch_for_3flash, R.string.can_opera_time_for_the_lever_trigger_3flashes};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_ignit_switch_on_access, R.string.can_ignit_switch_on};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on};
        this.mPopValueIds[2] = new int[]{R.string.can_short, R.string.can_long};
        this.m_SetData = new CanDataInfo.MitSubishiRzcSet();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    public void ResetData(boolean check) {
        CanJni.MitSubishiRzcGetSetData(this.m_SetData);
        if (!i2b(this.m_SetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_SetData.Update)) {
            this.m_SetData.Update = 0;
            int temp = 0;
            if (this.m_SetData.OperConditions == 128) {
                temp = 0;
            } else if (this.m_SetData.OperConditions == 144) {
                temp = 1;
            }
            updateItem(0, temp);
            int temp2 = 0;
            if (this.m_SetData.OneTouchFor3flashes == 128) {
                temp2 = 0;
            } else if (this.m_SetData.OneTouchFor3flashes == 144) {
                temp2 = 1;
            }
            updateItem(1, temp2);
            int temp3 = 0;
            if (this.m_SetData.OperTimeFortheLeverToTrig3flash == 128) {
                temp3 = 0;
            } else if (this.m_SetData.OperTimeFortheLeverToTrig3flash == 144) {
                temp3 = 1;
            }
            updateItem(2, temp3);
        }
    }

    public void QueryData() {
        CanJni.MitSubishiRzcQuery(80, 14);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 15);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 16);
        Sleep(5);
    }
}
