package com.ts.can.renault.renault;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.txznet.sdk.TXZResourceManager;

public class CanRenaultDriveComputeView extends CanScrollCarInfoView {
    protected static final int ITEM_MAX = 5;
    protected static final int ITEM_MIN = 0;
    protected static final int ITEM_PJCS = 3;
    protected static final int ITEM_PJYH = 0;
    protected static final int ITEM_RST = 4;
    protected static final int ITEM_XSLC = 1;
    protected static final int ITEM_XSYH = 2;
    private CanDataInfo.RenaulXpDriveInfo mPcInfos;

    public CanRenaultDriveComputeView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 4) {
            new CanItemMsgBox(id, getActivity(), R.string.can_cmp_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.RenaultCarSet(2, 1);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_pjyh, R.string.can_yslc, R.string.can_consumption, R.string.can_avg_speed, R.string.can_setup_reset};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TITLE};
        this.mPcInfos = new CanDataInfo.RenaulXpDriveInfo();
    }

    /* access modifiers changed from: protected */
    public String GetConsumpDW(int val) {
        switch (val) {
            case 0:
                return "L/100KM";
            case 1:
                return "KM/L";
            case 2:
                return "MPG(US)";
            case 3:
                return "MPG(UK)";
            default:
                return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    /* access modifiers changed from: protected */
    public String GetDisDW(int val) {
        switch (val) {
            case 0:
                return "KM";
            case 1:
                return "MILE";
            default:
                return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    /* access modifiers changed from: protected */
    public String GetLcyhDW(int val) {
        switch (val) {
            case 0:
            case 1:
                return "L";
            case 2:
            case 3:
                return "GAL";
            default:
                return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    public void ResetData(boolean check) {
        CanJni.RenaultGetDriveData(this.mPcInfos);
        if (!i2b(this.mPcInfos.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mPcInfos.Update)) {
            this.mPcInfos.Update = 0;
            if (this.mPcInfos.Pjyh == 65535) {
                updateItem(0, 0, "--");
            } else {
                updateItem(0, 0, String.valueOf(String.format("%.1f ", new Object[]{Float.valueOf(((float) this.mPcInfos.Pjyh) / 10.0f)})) + GetConsumpDW(this.mPcInfos.Yhdw));
            }
            if (this.mPcInfos.Xslc == 16777215) {
                updateItem(1, 0, "--");
            } else {
                updateItem(1, 0, String.valueOf(String.format("%.1f ", new Object[]{Float.valueOf(((float) this.mPcInfos.Xslc) / 10.0f)})) + GetDisDW(this.mPcInfos.Lcdw));
            }
            if (this.mPcInfos.Xsyh == 65535) {
                updateItem(2, 0, "--");
            } else {
                updateItem(2, 0, String.valueOf(String.format("%.1f ", new Object[]{Float.valueOf(((float) this.mPcInfos.Xsyh) / 10.0f)})) + GetLcyhDW(this.mPcInfos.Yhdw));
            }
            if (this.mPcInfos.Pjcs == 65535) {
                updateItem(3, 0, "--");
                return;
            }
            updateItem(3, 0, String.format("%.1f KM/H", new Object[]{Float.valueOf(((float) this.mPcInfos.Pjcs) / 10.0f)}));
        }
    }

    public void QueryData() {
        CanJni.RenaultQuery(56, 0);
    }
}
