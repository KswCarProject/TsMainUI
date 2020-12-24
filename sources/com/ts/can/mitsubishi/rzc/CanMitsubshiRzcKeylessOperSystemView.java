package com.ts.can.mitsubishi.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMitsubshiRzcKeylessOperSystemView extends CanScrollCarInfoView {
    private static final int ITEM_POWER_WINDOW_WINGMIRROR_OPERATION = 1;
    private static final int ITEM_REUNLOCK_TIMING_AFTER_LOCKING = 2;
    private static final int ITEM_TUM_SIGNAL_LIGHTS_ANSWERBACK = 0;
    private CanDataInfo.MitSubishiRzcSet m_SetData;

    public CanMitsubshiRzcKeylessOperSystemView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(1, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(1, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(1, Can.CAN_CHANA_CS75_WC);
                    return;
                } else if (item == 3) {
                    CanJni.MitSubishiRzcCarSet(1, 176);
                    return;
                } else if (item == 4) {
                    CanJni.MitSubishiRzcCarSet(1, 192);
                    return;
                } else if (item == 5) {
                    CanJni.MitSubishiRzcCarSet(1, 208);
                    return;
                } else if (item == 6) {
                    CanJni.MitSubishiRzcCarSet(1, Can.CAN_ZH_H530);
                    return;
                } else {
                    return;
                }
            case 1:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(2, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(2, 192);
                    return;
                } else {
                    return;
                }
            case 2:
                if (item == 0) {
                    CanJni.MitSubishiRzcCarSet(3, 128);
                    return;
                } else if (item == 1) {
                    CanJni.MitSubishiRzcCarSet(3, 144);
                    return;
                } else if (item == 2) {
                    CanJni.MitSubishiRzcCarSet(3, Can.CAN_CHANA_CS75_WC);
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
        this.mItemTitleIds = new int[]{R.string.can_tum_signal_lights_answerback, R.string.can_power_window_wing_mirror_opera, R.string.can_reunlock_timing_after_locking};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_lock_once_unlock_twice, R.string.can_lock_once_unlock_off, R.string.can_lock_off_unlock_twice, R.string.can_lock_twice_unlock_Once, R.string.can_lock_off_unlock_Once, R.string.can_lock_twice_unlock_off};
        this.mPopValueIds[1] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_wing_mirror_foldout_foldin};
        this.mPopValueIds[2] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_3s, R.string.can_5s};
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
            if (this.m_SetData.TumSignalLightsAnswerback == 128) {
                temp = 0;
            } else if (this.m_SetData.TumSignalLightsAnswerback == 144) {
                temp = 1;
            } else if (this.m_SetData.TumSignalLightsAnswerback == 160) {
                temp = 2;
            } else if (this.m_SetData.TumSignalLightsAnswerback == 176) {
                temp = 3;
            } else if (this.m_SetData.TumSignalLightsAnswerback == 192) {
                temp = 4;
            } else if (this.m_SetData.TumSignalLightsAnswerback == 208) {
                temp = 5;
            } else if (this.m_SetData.TumSignalLightsAnswerback == 224) {
                temp = 6;
            }
            updateItem(0, temp);
            if (this.m_SetData.PowerWindWingMirrOpera == 128) {
                temp = 0;
            } else if (this.m_SetData.PowerWindWingMirrOpera == 192) {
                temp = 1;
            }
            updateItem(1, temp);
            if (this.m_SetData.ReunlockTimingAfterLock == 128) {
                temp = 0;
            } else if (this.m_SetData.ReunlockTimingAfterLock == 144) {
                temp = 1;
            } else if (this.m_SetData.ReunlockTimingAfterLock == 160) {
                temp = 2;
            }
            updateItem(2, temp);
        }
    }

    public void QueryData() {
        CanJni.MitSubishiRzcQuery(80, 1);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 2);
        Sleep(5);
        CanJni.MitSubishiRzcQuery(80, 3);
        Sleep(5);
    }
}
