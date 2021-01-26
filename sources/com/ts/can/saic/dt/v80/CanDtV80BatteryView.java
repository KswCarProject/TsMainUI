package com.ts.can.saic.dt.v80;

import android.app.Activity;
import android.support.v4.internal.view.SupportMenu;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanScrollCarInfoView;

public class CanDtV80BatteryView extends CanScrollCarInfoView {
    private String[] mBatteryWorkArray;
    private CanDataInfo.DT_V80_BMS_ERR mErr;
    private CanDataInfo.DT_V80_DRIVE_INFO mInfo;
    private CanDataInfo.DT_V80_BMS_MSG_1 mMsg1;
    private CanDataInfo.DT_V80_BMS_MSG_2 mMsg2;
    private CanDataInfo.DT_V80_BMS_MSG_5 mMsg5;
    private CanDataInfo.DT_V80_BMS_WARN mWarn;

    public CanDtV80BatteryView(Activity activity) {
        super(activity, 11);
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
        int i;
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        if (CanJni.GetSubType() == 0) {
            this.mItemTitleIds = new int[]{R.string.can_cdscdy, R.string.can_cdscdl, R.string.can_yjcdwcsj, R.string.can_soc, R.string.can_cdjqdzt, R.string.can_cdzt, R.string.can_srdyzt, R.string.can_txzt, R.string.can_wdzt, R.string.can_cdjzxzt, R.string.can_cdyjgz};
            this.mMsg1 = new CanDataInfo.DT_V80_BMS_MSG_1();
            this.mMsg2 = new CanDataInfo.DT_V80_BMS_MSG_2();
            this.mErr = new CanDataInfo.DT_V80_BMS_ERR();
            return;
        }
        if (CanJni.GetSubType() == 1) {
            this.mItemTitleIds = new int[]{R.string.can_dcxtgzzt, R.string.can_xtjyzz, R.string.can_dwzt, R.string.can_dfqc_motor_r, R.string.can_djbh, R.string.can_djsl, R.string.can_vcujszs, R.string.can_dkwd, R.string.can_dfqc_motor_c, R.string.can_dyzddxwdid, R.string.can_dyzxdxwdid};
            this.mItemVisibles[9] = 0;
            this.mItemVisibles[10] = 0;
        } else if (CanJni.GetSubType() == 2) {
            this.mItemTitleIds = new int[]{R.string.can_dwzt, R.string.can_dfqc_motor_r, R.string.can_soc, R.string.can_total_mile, R.string.can_speed, R.string.can_djsl, R.string.can_vcujszs, R.string.can_dkwd, R.string.can_dfqc_motor_c, R.string.can_dyzddxwdid, R.string.can_dyzxdxwdid};
            for (int i2 = 0; i2 < this.mItemVisibles.length; i2++) {
                int[] iArr = this.mItemVisibles;
                if (i2 < 5) {
                    i = 1;
                } else {
                    i = 0;
                }
                iArr[i2] = i;
            }
        }
        this.mWarn = new CanDataInfo.DT_V80_BMS_WARN();
        this.mInfo = new CanDataInfo.DT_V80_DRIVE_INFO();
        this.mMsg5 = new CanDataInfo.DT_V80_BMS_MSG_5();
        this.mBatteryWorkArray = getStringArray(R.array.can_dcxtgzzt_array);
    }

    public void ResetData(boolean check) {
        if (CanJni.GetSubType() == 0) {
            updateDtV80Type1(check);
        } else if (CanJni.GetSubType() == 1) {
            updateDtV80Type2(check);
        } else if (CanJni.GetSubType() == 2) {
            updateDtV80Type3(check);
        }
    }

    private void updateDtV80Type3(boolean check) {
        String val;
        CanJni.SaicDtV80GetBmsMsg5(this.mMsg5);
        if (i2b(this.mMsg5.UpdateOnce) && (!check || i2b(this.mMsg5.Update))) {
            this.mMsg5.Update = 0;
            if (this.mMsg5.Dwzt == 0) {
                val = "P";
            } else if (this.mMsg5.Dwzt == 1) {
                val = "R";
            } else if (this.mMsg5.Dwzt == 2) {
                val = "N";
            } else if (this.mMsg5.Dwzt == 3) {
                val = "D";
            } else {
                val = "- -";
            }
            updateValue(0, 0, 3, new int[0], this.mMsg5.Dwzt, val);
            updateValue(1, 0, 24000, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg5.Djzs, String.format("%.1frpm", new Object[]{Float.valueOf(((float) this.mMsg5.Djzs) * 0.5f)}));
        }
        CanJni.SaicDtV80GetDriveInfo(this.mInfo);
        if (i2b(this.mInfo.UpdateOnce) && (!check || i2b(this.mInfo.Update))) {
            this.mInfo.Update = 0;
            updateValue(3, 0, 16777214, new int[]{16777215}, this.mInfo.Zlc, String.format("%.1f", new Object[]{Float.valueOf(((float) this.mInfo.Zlc) * 0.1f)}));
            updateValue(4, 0, 2000, new int[]{65535}, this.mInfo.Speed, String.format("%.1f", new Object[]{Float.valueOf(((float) this.mInfo.Speed) * 0.1f)}));
        }
        CanJni.SaicDtV80GetBmsWarn(this.mWarn);
        if (!i2b(this.mWarn.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWarn.Update)) {
            this.mWarn.Update = 0;
            updateValue(2, 0, 1000, new int[0], this.mWarn.Soc, String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(((float) this.mWarn.Soc) * 0.1f)})) + "%");
        }
    }

    private void updateDtV80Type2(boolean check) {
        String val;
        CanJni.SaicDtV80GetBmsMsg5(this.mMsg5);
        if (!i2b(this.mMsg5.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mMsg5.Update)) {
            this.mMsg5.Update = 0;
            updateValue(0, 0, 15, new int[]{-1}, this.mMsg5.Dcxtgzzt, this.mMsg5.Dcxtgzzt < this.mBatteryWorkArray.length ? this.mBatteryWorkArray[this.mMsg5.Dcxtgzzt] : "- -");
            updateValue(1, 0, 1023, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg5.Xtjyzz, String.valueOf(this.mMsg5.Xtjyzz * 10) + "Kom");
            if (this.mMsg5.Dwzt == 1) {
                val = "D";
            } else if (this.mMsg5.Dwzt == 2) {
                val = "R";
            } else if (this.mMsg5.Dwzt == 3) {
                val = "P";
            } else {
                val = "- -";
            }
            updateValue(2, 1, 3, new int[0], this.mMsg5.Dwzt, val);
            updateValue(3, 0, SupportMenu.USER_MASK, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg5.Djzs, String.format("%.1frpm", new Object[]{Float.valueOf((((float) this.mMsg5.Djzs) * 0.5f) - 16384.0f)}));
            updateValue(4, 0, 3, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg5.Djbh, new StringBuilder(String.valueOf(this.mMsg5.Djbh)).toString());
            updateValue(5, 0, 3, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg5.Djsl, new StringBuilder(String.valueOf(this.mMsg5.Djsl)).toString());
            updateValue(6, 0, 1023, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg5.VcuJsZs, String.format("%.2fkm/h", new Object[]{Float.valueOf(((float) this.mMsg5.VcuJsZs) * 0.25f)}));
            updateValue(7, 0, 215, new int[]{Can.CAN_FLAT_RZC, 255}, this.mMsg5.Dkwd, String.format("%d°C", new Object[]{Integer.valueOf(this.mMsg5.Dkwd - 40)}));
            updateValue(8, 0, 215, new int[]{Can.CAN_FLAT_RZC, 255}, this.mMsg5.Djwd, String.format("%d°C", new Object[]{Integer.valueOf(this.mMsg5.Djwd - 40)}));
        }
    }

    private void updateDtV80Type1(boolean check) {
        CanJni.SaicDtV80GetBmsMsg1(this.mMsg1);
        if (i2b(this.mMsg1.UpdateOnce) && (!check || i2b(this.mMsg1.Update))) {
            this.mMsg1.Update = 0;
            updateValue(3, 0, 100, new int[]{Can.CAN_FLAT_RZC, 255}, this.mMsg1.Soc, String.valueOf(this.mMsg1.Soc) + "%");
        }
        CanJni.SaicDtV80GetBmsMsg2(this.mMsg2);
        if (i2b(this.mMsg2.UpdateOnce) && (!check || i2b(this.mMsg2.Update))) {
            this.mMsg2.Update = 0;
            int i = this.mMsg2.Cdjscdy;
            String format = String.format("%.1fV", new Object[]{Float.valueOf(((float) this.mMsg2.Cdjscdy) * 0.1f)});
            updateValue(0, 0, 4500, new int[0], i, format);
            updateValue(1, 0, 20000, new int[]{65534, SupportMenu.USER_MASK}, this.mMsg2.Cdjscdl, String.format("%.1fA", new Object[]{Float.valueOf((((float) this.mMsg2.Cdjscdl) * 0.1f) - 1000.0f)}));
            updateValue(2, 0, CanCameraUI.BTN_PORSCHE_LZ_CAR, new int[0], this.mMsg2.Yjcdwcsj, String.valueOf(this.mMsg2.Yjcdwcsj) + " minute");
            updateStatus(10, this.mMsg2.CdjSta, 1, "故障", "正常");
            updateStatus(8, this.mMsg2.CdjSta, 2, "过温", "正常");
            updateStatus(6, this.mMsg2.CdjSta, 4, "错误", "正常");
            updateStatus(4, this.mMsg2.CdjSta, 8, "关闭", "启动");
            updateStatus(7, this.mMsg2.CdjSta, 16, "超时", "正常");
            updateStatus(9, this.mMsg2.CdjSta, 128, "在线", "离线");
        }
        CanJni.SaicDtV80GetWarn(this.mErr);
        if (!i2b(this.mErr.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mErr.Update)) {
            this.mErr.Update = 0;
            String[] array = {"--", "停车充电", "行驶充电", "未充电", "充电完成"};
            if (this.mErr.CdSta < array.length) {
                updateItem(5, 0, array[this.mErr.CdSta]);
            } else {
                updateItem(5, 0, "- -");
            }
        }
    }

    private void updateStatus(int index, int status, int bit, String onStr, String offStr) {
        if ((status & bit) > 0) {
            updateItem(index, 0, onStr);
        } else {
            updateItem(index, 0, offStr);
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
