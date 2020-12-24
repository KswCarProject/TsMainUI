package com.ts.can.renault.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanRenaultWcDriveComputeView extends CanScrollCarInfoView {
    protected static final int ITEM_LJLC = 6;
    protected static final int ITEM_MAX = 11;
    protected static final int ITEM_MIN = 0;
    protected static final int ITEM_PJCS = 5;
    protected static final int ITEM_PJYH = 4;
    protected static final int ITEM_QHQXJ = 2;
    protected static final int ITEM_RST = 10;
    protected static final int ITEM_TITLE_PAGE0 = 0;
    protected static final int ITEM_TITLE_PAGE1 = 3;
    protected static final int ITEM_WXHLC = 9;
    protected static final int ITEM_XSSJ = 7;
    protected static final int ITEM_ZYH = 8;
    protected static final int ITEM_ZYQXJ = 1;
    private CanDataInfo.RenaulWcDrivePage0 mPc0Infos;
    private CanDataInfo.RenaulWcDrivePage1 mPc1Infos;

    public CanRenaultWcDriveComputeView(Activity activity) {
        super(activity, 11);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 10) {
            new CanItemMsgBox(id, getActivity(), R.string.can_cmp_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.RenaultWcPcSet(2, 2, 1, 255);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_xsxjxx, R.string.can_zyqj, R.string.can_qhqj, R.string.can_driving_data, R.string.can_avg_consump, R.string.can_avg_speed, R.string.can_ljlc, R.string.can_trav_time, R.string.can_total_consump, R.string.can_dist_without_con, R.string.can_setup_reset};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TITLE};
        this.mPc0Infos = new CanDataInfo.RenaulWcDrivePage0();
        this.mPc1Infos = new CanDataInfo.RenaulWcDrivePage1();
    }

    /* access modifiers changed from: protected */
    public String GetStr2(int val) {
        if (val == 65535) {
            return "--";
        }
        return String.format("%.1f ", new Object[]{Double.valueOf(((double) val) * 0.1d)});
    }

    /* access modifiers changed from: protected */
    public String GetStr3(int val) {
        if (val == 16777215) {
            return "--";
        }
        return String.format("%.1f ", new Object[]{Double.valueOf(((double) val) * 0.1d)});
    }

    /* access modifiers changed from: protected */
    public String GetTimeStr(int type, int val) {
        if (type == 0) {
            if (val == 65535) {
                return "--";
            }
            return String.format("%d ", new Object[]{Integer.valueOf(val)});
        } else if (val == 255) {
            return "--";
        } else {
            return String.format("%d ", new Object[]{Integer.valueOf(val)});
        }
    }

    /* access modifiers changed from: protected */
    public String GetAngleStr(int type, int val) {
        switch (type) {
            case 0:
                if (val >= 128 && val <= 158) {
                    return String.format("l %d ", new Object[]{Integer.valueOf(val - 127)});
                } else if (val < 1 || val > 30) {
                    return "--";
                } else {
                    return String.format("r %d ", new Object[]{Integer.valueOf(val)});
                }
            case 1:
                if (val >= 128 && val <= 158) {
                    return String.format("f %d ", new Object[]{Integer.valueOf(val - 127)});
                } else if (val < 1 || val > 30) {
                    return "--";
                } else {
                    return String.format("r %d ", new Object[]{Integer.valueOf(val)});
                }
            default:
                return "--";
        }
    }

    public void ResetData(boolean check) {
        CanJni.RenaultWcGetDrivePage0Data(this.mPc0Infos);
        if (i2b(this.mPc0Infos.UpdateOnce) && (!check || i2b(this.mPc0Infos.Update))) {
            this.mPc0Infos.Update = 0;
            updateItem(1, 0, GetAngleStr(0, this.mPc0Infos.Zyqxj));
            updateItem(2, 0, GetAngleStr(1, this.mPc0Infos.Qhqxj));
        }
        CanJni.RenaultWcGetDrivePage1Data(this.mPc1Infos);
        if (!i2b(this.mPc1Infos.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPc1Infos.Update)) {
            this.mPc1Infos.Update = 0;
            updateItem(4, 0, String.valueOf(GetStr2(this.mPc1Infos.Pjyh)) + "L/100km");
            updateItem(5, 0, String.valueOf(GetStr2(this.mPc1Infos.Pjcs)) + "K/H");
            updateItem(6, 0, String.valueOf(GetStr3(this.mPc1Infos.Ljlc)) + "Km");
            updateItem(7, 0, String.valueOf(GetTimeStr(0, this.mPc1Infos.Xssjh)) + ":" + GetTimeStr(1, this.mPc1Infos.Xssjm));
            updateItem(8, 0, String.valueOf(GetStr2(this.mPc1Infos.Zyh)) + "L");
            updateItem(9, 0, String.valueOf(GetStr2(this.mPc1Infos.Wxhlc)) + "Km");
        }
    }

    public void QueryData() {
    }
}
