package com.ts.can.mitsubishi.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMitsubshiRzcDoorView extends CanScrollCarInfoView {
    private static final int ITEM_AUTO_DOOR_UNLOCK = 2;
    private static final int ITEM_DOOR_TOBE_UNLOCKED = 1;
    private static final int ITEM_TIME_OF_AUTO_RELOCK = 0;
    private CanDataInfo.MitSubishiRzcSet m_SetData;

    public CanMitsubshiRzcDoorView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(17, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(17, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(17, Can.CAN_CHANA_CS75_WC);
                    return;
                } else if (item == 3) {
                    CanJni.MitSubishiRzcCarSet(17, 176);
                    return;
                } else {
                    return;
                }
            case 1:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(18, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(18, 144);
                    return;
                } else {
                    return;
                }
            case 2:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(19, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(19, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(19, Can.CAN_CHANA_CS75_WC);
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
        this.mItemTitleIds = new int[]{R.string.can_timing_of_auto_relock, R.string.can_doors_tobe_unlocked, R.string.can_automatic_door_unlock};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_30s, R.string.can_1min, R.string.can_2min, R.string.can_3min};
        this.mPopValueIds[1] = new int[]{R.string.can_keyandremoteunlockmode_2, R.string.can_keyandremoteunlockmode_1};
        this.mPopValueIds[2] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_connect_to_gear_in_position, R.string.can_connect_to_ign_switch_off};
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
            if (this.m_SetData.TimOfAutoRelock == 128) {
                temp = 0;
            } else if (this.m_SetData.TimOfAutoRelock == 144) {
                temp = 1;
            } else if (this.m_SetData.TimOfAutoRelock == 160) {
                temp = 2;
            } else if (this.m_SetData.TimOfAutoRelock == 176) {
                temp = 3;
            }
            updateItem(0, temp);
            int temp2 = 0;
            if (this.m_SetData.DoorToBeUnlocked == 128) {
                temp2 = 0;
            } else if (this.m_SetData.DoorToBeUnlocked == 144) {
                temp2 = 1;
            }
            updateItem(1, temp2);
            int temp3 = 0;
            if (this.m_SetData.AutoDoorUnlcok == 128) {
                temp3 = 0;
            } else if (this.m_SetData.AutoDoorUnlcok == 144) {
                temp3 = 1;
            } else if (this.m_SetData.AutoDoorUnlcok == 160) {
                temp3 = 2;
            }
            updateItem(2, temp3);
        }
    }

    public void QueryData() {
        CanJni.MitSubishiRzcQuery(80, 17);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 18);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 19);
        Sleep(5);
    }
}
