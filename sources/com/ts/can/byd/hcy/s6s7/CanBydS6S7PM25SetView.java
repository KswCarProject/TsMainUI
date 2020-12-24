package com.ts.can.byd.hcy.s6s7;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBydS6S7PM25SetView extends CanScrollCarInfoView {
    private static final int S6S7_PM_CNDJ = 9;
    private static final int S6S7_PM_CNJC = 3;
    private static final int S6S7_PM_CNSJ = 7;
    private static final int S6S7_PM_CWDJ = 10;
    private static final int S6S7_PM_CWJC = 4;
    private static final int S6S7_PM_CWSJ = 8;
    private static final int S6S7_PM_DSJC = 2;
    private static final int S6S7_PM_JSZT = 11;
    private static final int S6S7_PM_KMJC = 1;
    private static final int S6S7_PM_SDJC = 0;
    private static final int S6S7_PM_SZBJ = 5;
    private static final int S6S7_PM_ZXSBZW = 6;
    private CanDataInfo.BYDS6S7PM25SetData mSetData;

    public CanBydS6S7PM25SetView(Activity activity) {
        super(activity, 12);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.BYDS6S7PmSet(getCNJC(this.mSetData.innerCheck) + getCWJC(this.mSetData.outterCheck) + getSDJC(Neg(this.mSetData.SDJC)) + getKMJC(this.mSetData.KMJC) + getDSJC(this.mSetData.DSJC));
                return;
            case 1:
                CanJni.BYDS6S7PmSet(getCNJC(this.mSetData.innerCheck) + getCWJC(this.mSetData.outterCheck) + getSDJC(this.mSetData.SDJC) + getKMJC(Neg(this.mSetData.KMJC)) + getDSJC(this.mSetData.DSJC));
                return;
            case 2:
                CanJni.BYDS6S7PmSet(getCNJC(this.mSetData.innerCheck) + getCWJC(this.mSetData.outterCheck) + getSDJC(this.mSetData.SDJC) + getKMJC(this.mSetData.KMJC) + getDSJC(Neg(this.mSetData.DSJC)));
                return;
            case 3:
                CanJni.BYDS6S7PmSet(getCNJC(Neg(this.mSetData.innerCheck)) + getCWJC(this.mSetData.outterCheck) + getSDJC(this.mSetData.SDJC) + getKMJC(this.mSetData.KMJC) + getDSJC(this.mSetData.DSJC));
                return;
            case 4:
                CanJni.BYDS6S7PmSet(getCNJC(this.mSetData.innerCheck) + getCWJC(Neg(this.mSetData.outterCheck)) + getSDJC(this.mSetData.SDJC) + getKMJC(this.mSetData.KMJC) + getDSJC(this.mSetData.DSJC));
                return;
            default:
                return;
        }
    }

    private int getCNJC(int CNJC) {
        if (i2b(CNJC)) {
            return 128;
        }
        return 0;
    }

    private int getCWJC(int CWJC) {
        if (i2b(CWJC)) {
            return 64;
        }
        return 0;
    }

    private int getSDJC(int SDJC) {
        if (i2b(SDJC)) {
            return 32;
        }
        return 0;
    }

    private int getKMJC(int KMJC) {
        if (i2b(KMJC)) {
            return 16;
        }
        return 0;
    }

    private int getDSJC(int DSJC) {
        if (i2b(DSJC)) {
            return 8;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mSetData = new CanDataInfo.BYDS6S7PM25SetData();
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mItemTitleIds = new int[]{R.string.can_pm25_sdjc, R.string.can_pm25_kmjc, R.string.can_pm25_dsjc, R.string.can_pm25_cnjc, R.string.can_pm25_cwjc, R.string.can_pm25_szbj, R.string.can_pm25_zxsbzw, R.string.can_pm25_cnsj, R.string.can_pm25_cwsj, R.string.can_pm25_cndj, R.string.can_pm25_cwdj, R.string.can_pm25_jszt};
        this.mPopValueIds[5] = new int[]{R.string.can_normal, R.string.can_pm25_szcgbj};
        this.mPopValueIds[6] = new int[]{R.string.can_invalid, R.string.can_pm25_carin, R.string.can_pm25_carout};
        this.mPopValueIds[9] = new int[]{R.array.can_s6s7_pm25_arrays};
        this.mPopValueIds[10] = new int[]{R.array.can_s6s7_pm25_arrays};
        this.mPopValueIds[11] = new int[]{R.string.can_normal, R.string.can_pm25_carincb, R.string.can_pm25_caroutcb};
    }

    public void ResetData(boolean check) {
        CanJni.BYDS6S7GetPM25SetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.SDJC);
            updateItem(1, this.mSetData.KMJC);
            updateItem(2, this.mSetData.DSJC);
            updateItem(3, this.mSetData.innerCheck);
            updateItem(4, this.mSetData.outterCheck);
            updateItem(5, this.mSetData.SZCG);
            updateItem(6, this.mSetData.ZXSBZW);
            if (this.mSetData.innerNum == 4095) {
                updateItem(7, this.mSetData.innerNum, "--");
            } else {
                updateItem(7, this.mSetData.innerNum, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.innerNum)}));
            }
            if (this.mSetData.outterNum == 4095) {
                updateItem(8, this.mSetData.outterNum, "--");
            } else {
                updateItem(8, this.mSetData.outterNum, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.outterNum)}));
            }
            updateItem(9, this.mSetData.innerLevel);
            updateItem(10, this.mSetData.outterLevel);
            updateItem(11, this.mSetData.warnNum);
        }
    }

    public void QueryData() {
    }
}
