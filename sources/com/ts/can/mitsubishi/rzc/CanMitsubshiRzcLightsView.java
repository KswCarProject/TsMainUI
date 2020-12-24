package com.ts.can.mitsubishi.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMitsubshiRzcLightsView extends CanScrollCarInfoView {
    private static final int ITEM_EXT_LIGHTS_ON_WITCH_REMOTE_UNLOCK = 2;
    private static final int ITEM_HEADLIGHT_AUTO_OFF = 0;
    private static final int ITEM_HEADLIGHT_TIMEOUT_EXIT_VEH = 1;
    private static final int ITEM_INT_LIGHT_TIMEOUT = 3;
    private static final int ITEM_INT_LIGHT_TIMEOUT_AFT_DOOR_IS_CLS = 4;
    private CanDataInfo.MitSubishiRzcSet m_SetData;

    public CanMitsubshiRzcLightsView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(9, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(9, 144);
                    return;
                } else {
                    return;
                }
            case 1:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(10, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(10, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(10, Can.CAN_CHANA_CS75_WC);
                    return;
                } else if (item == 3) {
                    CanJni.MitSubishiRzcCarSet(10, 176);
                    return;
                } else if (item == 4) {
                    CanJni.MitSubishiRzcCarSet(10, 192);
                    return;
                } else {
                    return;
                }
            case 2:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(11, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(11, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(11, Can.CAN_CHANA_CS75_WC);
                    return;
                } else {
                    return;
                }
            case 3:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(12, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(12, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(12, Can.CAN_CHANA_CS75_WC);
                    return;
                } else if (item == 3) {
                    CanJni.MitSubishiRzcCarSet(12, 176);
                    return;
                } else {
                    return;
                }
            case 4:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(13, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(13, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(13, Can.CAN_CHANA_CS75_WC);
                    return;
                } else if (item == 3) {
                    CanJni.MitSubishiRzcCarSet(13, 176);
                    return;
                } else if (item == 4) {
                    CanJni.MitSubishiRzcCarSet(13, 192);
                    return;
                } else if (item == 5) {
                    CanJni.MitSubishiRzcCarSet(13, 208);
                    return;
                } else if (item == 6) {
                    CanJni.MitSubishiRzcCarSet(13, Can.CAN_ZH_H530);
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
        this.mItemTitleIds = new int[]{R.string.can_headlight_auto_off, R.string.can_headlight_timeout_exit_veh, R.string.can_ext_light_on_witch_rem_unlock, R.string.can_int_light_timeout, R.string.can_int_light_timeout_aft_doorclose};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_15s, R.string.can_30s, R.string.can_1min, R.string.can_3min};
        this.mPopValueIds[2] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_front_post_lamp_on, R.string.can_head_lights_on};
        this.mPopValueIds[3] = new int[]{R.string.can_never, R.string.can_3min, R.string.can_mzd_cx4_time_30min, R.string.can_mzd_cx4_time_60min};
        this.mPopValueIds[4] = new int[]{R.string.can_0s, R.string.can_mzd_cx4_time_7_5s, R.string.can_mzd_cx4_time_15s, R.string.can_mzd_cx4_time_30s, R.string.can_1min, R.string.can_2min, R.string.can_3min};
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
            if (this.m_SetData.HeadlightAutoOff == 128) {
                temp = 0;
            } else if (this.m_SetData.HeadlightAutoOff == 144) {
                temp = 1;
            }
            updateItem(0, temp);
            int temp2 = 0;
            if (this.m_SetData.HeadlightTimeOutWhenExitVeh == 128) {
                temp2 = 0;
            } else if (this.m_SetData.HeadlightTimeOutWhenExitVeh == 144) {
                temp2 = 1;
            } else if (this.m_SetData.HeadlightTimeOutWhenExitVeh == 160) {
                temp2 = 2;
            } else if (this.m_SetData.HeadlightTimeOutWhenExitVeh == 176) {
                temp2 = 3;
            } else if (this.m_SetData.HeadlightTimeOutWhenExitVeh == 192) {
                temp2 = 4;
            }
            updateItem(1, temp2);
            int temp3 = 0;
            if (this.m_SetData.ExtLightONwithRemoteUnlock == 128) {
                temp3 = 0;
            } else if (this.m_SetData.ExtLightONwithRemoteUnlock == 144) {
                temp3 = 1;
            } else if (this.m_SetData.ExtLightONwithRemoteUnlock == 160) {
                temp3 = 2;
            }
            updateItem(2, temp3);
            int temp4 = 0;
            if (this.m_SetData.IntLightTimeOut == 128) {
                temp4 = 0;
            } else if (this.m_SetData.IntLightTimeOut == 144) {
                temp4 = 1;
            } else if (this.m_SetData.IntLightTimeOut == 160) {
                temp4 = 2;
            } else if (this.m_SetData.IntLightTimeOut == 176) {
                temp4 = 3;
            }
            updateItem(3, temp4);
            int temp5 = 0;
            if (this.m_SetData.IntLightTimeAfterDoorisClose == 128) {
                temp5 = 0;
            } else if (this.m_SetData.IntLightTimeAfterDoorisClose == 144) {
                temp5 = 1;
            } else if (this.m_SetData.IntLightTimeAfterDoorisClose == 160) {
                temp5 = 2;
            } else if (this.m_SetData.IntLightTimeAfterDoorisClose == 176) {
                temp5 = 3;
            } else if (this.m_SetData.IntLightTimeAfterDoorisClose == 192) {
                temp5 = 4;
            } else if (this.m_SetData.IntLightTimeAfterDoorisClose == 208) {
                temp5 = 5;
            } else if (this.m_SetData.IntLightTimeAfterDoorisClose == 224) {
                temp5 = 6;
            }
            updateItem(4, temp5);
        }
    }

    public void QueryData() {
        CanJni.MitSubishiRzcQuery(80, 9);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 10);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 11);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 12);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 13);
        Sleep(5);
    }
}
