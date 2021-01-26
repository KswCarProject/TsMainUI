package com.ts.can.mitsubishi.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanMitsubshiRzcOtherSetView extends CanScrollCarInfoView {
    private static final int ITEM_ACCESS_POWER_TIMEOUT = 1;
    private static final int ITEM_AUTO_FOLD_WING_MIRR = 0;
    private static final int ITEM_RST_ALL = 2;
    private CanDataInfo.MitSubishiRzcSet m_SetData;

    public CanMitsubshiRzcOtherSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(26, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(26, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(26, 160);
                    return;
                } else if (item == 3) {
                    CanJni.MitSubishiRzcCarSet(26, 176);
                    return;
                } else {
                    return;
                }
            case 1:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(27, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(27, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(27, 160);
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
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 2:
                new CanItemMsgBox(id, getActivity(), R.string.can_sure_reset, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.MitSubishiRzcCarSet(31, 1);
                    }
                });
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_auto_fold_wing_mirror, R.string.can_access_power_timeout, R.string.can_hfcssz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.string.can_none, R.string.can_fold_out, R.string.can_fold_in_out_by_ignition_switch_onoff, R.string.can_fold_in_out_by_keyless_entry};
        this.mPopValueIds[1] = new int[]{R.string.can_never, R.string.can_mzd_cx4_time_30min, R.string.can_mzd_cx4_time_60min};
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
            if (this.m_SetData.AutoFoldWingMirrors == 128) {
                temp = 0;
            } else if (this.m_SetData.AutoFoldWingMirrors == 144) {
                temp = 1;
            } else if (this.m_SetData.AutoFoldWingMirrors == 160) {
                temp = 2;
            } else if (this.m_SetData.AutoFoldWingMirrors == 176) {
                temp = 3;
            }
            updateItem(0, temp);
            int temp2 = 0;
            if (this.m_SetData.AccessoryPowerTimeout == 128) {
                temp2 = 0;
            } else if (this.m_SetData.AccessoryPowerTimeout == 144) {
                temp2 = 1;
            } else if (this.m_SetData.AccessoryPowerTimeout == 160) {
                temp2 = 2;
            }
            updateItem(1, temp2);
        }
    }

    public void QueryData() {
        CanJni.MitSubishiRzcQuery(80, 26);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 27);
        Sleep(5);
    }
}
