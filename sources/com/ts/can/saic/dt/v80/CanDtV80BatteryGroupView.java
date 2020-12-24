package com.ts.can.saic.dt.v80;

import android.app.Activity;
import android.support.v4.internal.view.SupportMenu;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.dvdplayer.definition.MediaDef;
import com.ts.factoryset.FsCanActivity;
import com.txznet.sdk.TXZPoiSearchManager;

public class CanDtV80BatteryGroupView extends CanScrollCarInfoView {
    private CanDataInfo.DT_V80_BMS_ERR mErr;
    private CanDataInfo.DT_V80_BMS_MSG_1 mMsg1;
    private CanDataInfo.DT_V80_BMS_MSG_5 mMsg5;
    private String[] mPtModeStr = {"  ", "纯电", "  ", "异常"};

    public CanDtV80BatteryGroupView(Activity activity) {
        super(activity, 12);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        if (CanJni.GetSubType() == 1) {
            this.mItemTitleIds = new int[]{R.string.can_dczzdy, R.string.can_dczzdl, R.string.can_soc, R.string.can_zgwd, R.string.can_zdwd, R.string.can_zgdtdy, R.string.can_zgdtdxdyxh, R.string.can_dyzddxwdid, R.string.can_zddtdy, R.string.can_zddtdxdyxh, R.string.can_dyzxdxwdid, R.string.can_running_status};
        } else {
            this.mItemTitleIds = new int[]{R.string.can_dczzdy, R.string.can_dczzdl, R.string.can_soc, R.string.can_zgwd, R.string.can_zdwd, R.string.can_zgdtdy, R.string.can_zgdtdyxhm, R.string.can_zgdtdyxnbhb, R.string.can_zddtdy, R.string.can_zddtdyxhm, R.string.can_zddtdyxnbhb, R.string.can_bms_error};
        }
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mItemVisibles = new int[12];
        for (int i = 0; i < this.mItemVisibles.length; i++) {
            if (i <= 1 || CanJni.GetSubType() != 2) {
                this.mItemVisibles[i] = 1;
            } else {
                this.mItemVisibles[i] = 0;
            }
        }
        this.mMsg1 = new CanDataInfo.DT_V80_BMS_MSG_1();
        this.mMsg5 = new CanDataInfo.DT_V80_BMS_MSG_5();
        this.mErr = new CanDataInfo.DT_V80_BMS_ERR();
    }

    public void ResetData(boolean check) {
        CanJni.SaicDtV80GetBmsMsg1(this.mMsg1);
        if (i2b(this.mMsg1.UpdateOnce) && (!check || i2b(this.mMsg1.Update))) {
            this.mMsg1.Update = 0;
            if (CanJni.GetSubType() == 0) {
                updateValue(0, 0, TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg1.Dczzdy, String.format("%.1fV", new Object[]{Float.valueOf(((float) this.mMsg1.Dczzdy) * 0.1f)}));
                updateValue(1, 0, 20000, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg1.Dczzdl, String.format("%.1fA", new Object[]{Float.valueOf((((float) this.mMsg1.Dczzdl) * 0.1f) - 1000.0f)}));
                updateValue(2, 0, 100, new int[]{Can.CAN_FLAT_RZC, 255}, this.mMsg1.Soc, String.valueOf(this.mMsg1.Soc) + "%");
                updateValue(3, 0, Can.CAN_NISSAN_XFY, new int[]{Can.CAN_FLAT_RZC, 255}, this.mMsg1.Dczzgwd, String.valueOf(this.mMsg1.Dczzgwd - 40) + "°C");
                updateValue(4, 0, Can.CAN_NISSAN_XFY, new int[]{Can.CAN_FLAT_RZC, 255}, this.mMsg1.Dczzdwd, String.valueOf(this.mMsg1.Dczzdwd - 40) + "°C");
                updateValue(5, 0, 15000, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg1.Dczgdtdy, String.format("%.3fV", new Object[]{Float.valueOf(((float) this.mMsg1.Dczgdtdy) * 0.001f)}));
                updateValue(6, 1, Can.CAN_NISSAN_XFY, new int[]{Can.CAN_FLAT_RZC, 255}, this.mMsg1.Zgdtdyxh, new StringBuilder(String.valueOf(this.mMsg1.Zgdtdyxh)).toString());
                updateValue(7, 1, Can.CAN_NISSAN_XFY, new int[]{Can.CAN_FLAT_RZC, 255}, this.mMsg1.Zgdtdyxnbh, new StringBuilder(String.valueOf(this.mMsg1.Zgdtdyxnbh)).toString());
                updateValue(8, 0, 15000, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg1.Dczddtdy, String.format("%.3fV", new Object[]{Float.valueOf(((float) this.mMsg1.Dczddtdy) * 0.001f)}));
                updateValue(9, 1, Can.CAN_NISSAN_XFY, new int[]{Can.CAN_FLAT_RZC, 255}, this.mMsg1.Zddtdyxh, new StringBuilder(String.valueOf(this.mMsg1.Zddtdyxh)).toString());
                updateValue(10, 1, Can.CAN_NISSAN_XFY, new int[]{Can.CAN_FLAT_RZC, 255}, this.mMsg1.Zddtdyxnbh, new StringBuilder(String.valueOf(this.mMsg1.Zddtdyxnbh)).toString());
            } else if (CanJni.GetSubType() == 1) {
                updateValue(0, 0, 4092, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg1.Dczzdy, String.format("%.2fV", new Object[]{Float.valueOf(((float) this.mMsg1.Dczzdy) * 0.25f)}));
                updateValue(1, 0, FsCanActivity.DOOR_UPDATE_ALL, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg1.Dczzdl, String.format("%.2fA", new Object[]{Float.valueOf((((float) this.mMsg1.Dczzdl) * 0.25f) - 512.0f)}));
                updateValue(2, 0, 200, new int[]{Can.CAN_FLAT_RZC, 255}, this.mMsg1.Soc, String.valueOf(((float) this.mMsg1.Soc) * 0.5f) + "%");
                updateValue(3, 0, 255, new int[]{255}, this.mMsg1.Dczzgwd, String.valueOf(this.mMsg1.Dczzgwd - 40) + "°C");
                updateValue(4, 0, 255, new int[]{255}, this.mMsg1.Dczzdwd, String.valueOf(this.mMsg1.Dczzdwd - 40) + "°C");
                updateValue(5, 0, FsCanActivity.DOOR_UPDATE_ALL, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg1.Dczgdtdy, String.format("%dMV", new Object[]{Integer.valueOf(this.mMsg1.Dczgdtdy + MediaDef.PROGRESS_MAX)}));
                updateValue(6, 1, 255, new int[]{255}, this.mMsg1.Zgdtdyxnbh, new StringBuilder(String.valueOf(this.mMsg1.Zgdtdyxnbh)).toString());
                updateValue(8, 0, FsCanActivity.DOOR_UPDATE_ALL, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg1.Dczddtdy, String.format("%dMV", new Object[]{Integer.valueOf(this.mMsg1.Dczddtdy + MediaDef.PROGRESS_MAX)}));
                updateValue(9, 1, 255, new int[]{255}, this.mMsg1.Zddtdyxnbh, new StringBuilder(String.valueOf(this.mMsg1.Zddtdyxnbh)).toString());
            } else if (CanJni.GetSubType() == 2) {
                updateValue(0, 0, 65533, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg1.Dczzdy, String.format("%.1fV", new Object[]{Float.valueOf(((float) this.mMsg1.Dczzdy) * 0.1f)}));
                updateValue(1, 0, 65533, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg1.Dczzdl, String.format("%.1fA", new Object[]{Float.valueOf(((float) this.mMsg1.Dczzdl) * 0.1f)}));
            }
        }
        if (CanJni.GetSubType() == 1) {
            CanJni.SaicDtV80GetBmsMsg5(this.mMsg5);
            if (!i2b(this.mMsg5.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mMsg5.Update)) {
                this.mMsg5.Update = 0;
                updateValue(7, 1, 255, new int[]{255}, this.mMsg5.DxwdMaxId, new StringBuilder(String.valueOf(this.mMsg5.DxwdMaxId)).toString());
                updateValue(10, 1, 255, new int[]{255}, this.mMsg5.DxwdMinId, new StringBuilder(String.valueOf(this.mMsg5.DxwdMinId)).toString());
                int temp = this.mMsg5.PtMode;
                if (temp > 3) {
                    temp = 0;
                }
                updateValue(11, 1, 3, new int[]{255}, this.mMsg5.PtMode, this.mPtModeStr[temp]);
            }
        } else if (CanJni.GetSubType() == 0) {
            CanJni.SaicDtV80GetWarn(this.mErr);
            if (!i2b(this.mErr.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mErr.Update)) {
                this.mErr.Update = 0;
                updateItem(11, 0, new StringBuilder(String.valueOf(this.mErr.WarnNum)).toString());
            }
        }
    }

    private void updateValue(int index, int min, int max, int[] errors, int value, String valueStr) {
        if (value < min || value > max) {
            updateItem(index, 0, "- -");
            return;
        }
        for (int error : errors) {
            if (value == error) {
                updateItem(index, 0, "- -");
                return;
            }
        }
        updateItem(index, 0, valueStr);
    }

    public void QueryData() {
    }

    public void doOnResume() {
        resetValues();
    }

    private void resetValues() {
        for (int i = 0; i < this.mItemTitleIds.length; i++) {
            updateItem(i, 0, "- -");
        }
    }
}
