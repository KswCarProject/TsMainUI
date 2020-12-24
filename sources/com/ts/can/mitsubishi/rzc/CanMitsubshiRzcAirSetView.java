package com.ts.can.mitsubishi.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMitsubshiRzcAirSetView extends CanScrollCarInfoView {
    private static final int ITEM_AIR_CONDITION_AUTO_CONTROL = 1;
    private static final int ITEM_AUTO_REAR_DEFOROST_AT_ENGINE_S = 5;
    private static final int ITEM_ECO_MODE = 2;
    private static final int ITEM_FACE_FOOT_AIRFLOW_RATIO = 4;
    private static final int ITEM_FOOT_WIND_AIRFLOW_RATIO = 3;
    private static final int ITEM_RECIRCU_AUTO_CONTROL = 0;
    private CanDataInfo.MitSubishiRzcSet m_SetData;

    public CanMitsubshiRzcAirSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(20, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(20, 144);
                    return;
                } else {
                    return;
                }
            case 1:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(21, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(21, 144);
                    return;
                } else {
                    return;
                }
            case 2:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(22, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(22, 144);
                    return;
                } else {
                    return;
                }
            case 3:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(23, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(23, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(23, Can.CAN_CHANA_CS75_WC);
                    return;
                } else {
                    return;
                }
            case 4:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(24, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(24, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(24, Can.CAN_CHANA_CS75_WC);
                    return;
                } else {
                    return;
                }
            case 5:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(25, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(25, 144);
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
        this.mItemTitleIds = new int[]{R.string.can_revir_auto_control, R.string.can_air_condition_auto_control, R.string.can_eco_mode, R.string.can_foot_wind_airflow_ratio, R.string.can_face_foot_airflow_ratio, R.string.can_Auto_rear_defrost_at_engine_s};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_soudong, R.string.can_type_mode_auto};
        this.mPopValueIds[1] = new int[]{R.string.can_soudong, R.string.can_type_mode_auto};
        this.mPopValueIds[2] = new int[]{R.string.can_eco, R.string.can_comfort};
        this.mPopValueIds[3] = new int[]{R.string.can_mode_normal, R.string.can_more_airflow_to_foot, R.string.can_more_airflow_to_windshield};
        this.mPopValueIds[4] = new int[]{R.string.can_mode_normal, R.string.can_more_airflow_to_face, R.string.can_more_airflow_to_foot};
        this.mPopValueIds[5] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on};
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
            if (this.m_SetData.RecAutoCtl == 128) {
                temp = 0;
            } else if (this.m_SetData.RecAutoCtl == 144) {
                temp = 1;
            }
            updateItem(0, temp);
            int temp2 = 0;
            if (this.m_SetData.AirConditAutomaticCtl == 128) {
                temp2 = 0;
            } else if (this.m_SetData.AirConditAutomaticCtl == 144) {
                temp2 = 1;
            }
            updateItem(1, temp2);
            int temp3 = 0;
            if (this.m_SetData.EcoMode == 128) {
                temp3 = 0;
            } else if (this.m_SetData.EcoMode == 144) {
                temp3 = 1;
            }
            updateItem(2, temp3);
            int temp4 = 0;
            if (this.m_SetData.FootWindAirflowRatio == 128) {
                temp4 = 0;
            } else if (this.m_SetData.FootWindAirflowRatio == 144) {
                temp4 = 1;
            } else if (this.m_SetData.FootWindAirflowRatio == 160) {
                temp4 = 2;
            }
            updateItem(3, temp4);
            int temp5 = 0;
            if (this.m_SetData.FaceFootAirflowRatio == 128) {
                temp5 = 0;
            } else if (this.m_SetData.FaceFootAirflowRatio == 144) {
                temp5 = 1;
            } else if (this.m_SetData.FaceFootAirflowRatio == 160) {
                temp5 = 2;
            }
            updateItem(4, temp5);
            int temp6 = 0;
            if (this.m_SetData.AutoRearDefrostAtEngStart == 128) {
                temp6 = 0;
            } else if (this.m_SetData.AutoRearDefrostAtEngStart == 144) {
                temp6 = 1;
            }
            updateItem(5, temp6);
        }
    }

    public void QueryData() {
        CanJni.MitSubishiRzcQuery(80, 20);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 21);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 22);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 23);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 24);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 25);
        Sleep(5);
    }
}
