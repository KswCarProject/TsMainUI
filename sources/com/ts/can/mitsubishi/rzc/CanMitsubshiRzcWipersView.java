package com.ts.can.mitsubishi.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMitsubshiRzcWipersView extends CanScrollCarInfoView {
    private static final int ITEM_AUTO_WASH_FUNC_BY_ONE_TOUCH_WASH_LEV = 2;
    private static final int ITEM_REAR_WIPER_ACTIVATED_WHEN_INR = 4;
    private static final int ITEM_REAR_WIPER_INT_INTERVAL = 3;
    private static final int ITEM_WINDSHIELD_WIPERS_INT_OPERA = 0;
    private static final int ITEM_WIPERS_LINKED_TO_WASHER = 1;
    private CanDataInfo.MitSubishiRzcSet m_SetData;

    public CanMitsubshiRzcWipersView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(4, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(4, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(4, Can.CAN_CHANA_CS75_WC);
                    return;
                } else if (item == 3) {
                    CanJni.MitSubishiRzcCarSet(4, 176);
                    return;
                } else {
                    return;
                }
            case 1:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(5, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(5, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(5, Can.CAN_CHANA_CS75_WC);
                    return;
                } else {
                    return;
                }
            case 2:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(6, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(6, 144);
                    return;
                } else {
                    return;
                }
            case 3:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(7, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(7, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(7, Can.CAN_CHANA_CS75_WC);
                    return;
                } else if (item == 3) {
                    CanJni.MitSubishiRzcCarSet(7, 176);
                    return;
                } else {
                    return;
                }
            case 4:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(8, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(8, 144);
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
        this.mItemTitleIds = new int[]{R.string.can_windshield_wiper_inter_opera, R.string.can_wipers_link_to_washer, R.string.can_auto_wash_func_by_one_touchofwash, R.string.can_rear_wiper_int_interval, R.string.can_rear_wiper_activated_when_in_r};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_4s, R.string.can_variable, R.string.can_variable_by_vehicle_speed, R.string.can_variable_by_sensing};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on, R.string.can_on_with_delayed_finishing_wipe};
        this.mPopValueIds[2] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on};
        this.mPopValueIds[3] = new int[]{R.string.can_0s, R.string.can_4s, R.string.can_8s, R.string.can_16s};
        this.mPopValueIds[4] = new int[]{R.string.can_rear_wiper_switch_on, R.string.can_front_rear_wiper_switch_on};
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
            if (this.m_SetData.WindshieldWipersIntOpera == 128) {
                temp = 0;
            } else if (this.m_SetData.WindshieldWipersIntOpera == 144) {
                temp = 1;
            } else if (this.m_SetData.WindshieldWipersIntOpera == 160) {
                temp = 2;
            } else if (this.m_SetData.WindshieldWipersIntOpera == 176) {
                temp = 3;
            }
            updateItem(0, temp);
            int temp2 = 0;
            if (this.m_SetData.WipersLinktoWasher == 128) {
                temp2 = 0;
            } else if (this.m_SetData.WipersLinktoWasher == 144) {
                temp2 = 1;
            } else if (this.m_SetData.PowerWindWingMirrOpera == 160) {
                temp2 = 2;
            }
            updateItem(1, temp2);
            int temp3 = 0;
            if (this.m_SetData.AutoWashFunbyOneTouchofWashLev == 128) {
                temp3 = 0;
            } else if (this.m_SetData.AutoWashFunbyOneTouchofWashLev == 144) {
                temp3 = 1;
            }
            updateItem(2, temp3);
            int temp4 = 0;
            if (this.m_SetData.RearWiperInterVal == 128) {
                temp4 = 0;
            } else if (this.m_SetData.RearWiperInterVal == 144) {
                temp4 = 1;
            } else if (this.m_SetData.RearWiperInterVal == 160) {
                temp4 = 2;
            } else if (this.m_SetData.RearWiperInterVal == 176) {
                temp4 = 3;
            }
            updateItem(3, temp4);
            int temp5 = 0;
            if (this.m_SetData.RearWiperActWhenInR == 128) {
                temp5 = 0;
            } else if (this.m_SetData.RearWiperActWhenInR == 144) {
                temp5 = 1;
            }
            updateItem(4, temp5);
        }
    }

    public void QueryData() {
        CanJni.MitSubishiRzcQuery(80, 4);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 5);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 6);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 7);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 8);
        Sleep(5);
    }
}
