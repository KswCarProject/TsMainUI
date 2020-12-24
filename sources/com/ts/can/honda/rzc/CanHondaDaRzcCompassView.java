package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaDaRzcCompassView extends CanScrollCarInfoView {
    public static final int ID_ANGLE = 1;
    public static final int ID_STATUS = 0;
    public static final int ID_ZONE = 2;
    protected CanDataInfo.HondaCompass mCompassData;

    public CanHondaDaRzcCompassView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        if (id == 2) {
            switch (CanJni.GetCanType()) {
                case 288:
                    CanJni.HondaDACarSet(193, pos);
                    CanJni.HondaDACarSet(192, 1);
                    return;
                default:
                    return;
            }
        }
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() == 0 && this.mCompassData.Status == 0) {
            switch (CanJni.GetCanType()) {
                case 288:
                    CanJni.HondaDACarSet(192, 1);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_status, R.string.can_angle, R.string.can_zone};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = 1;
        iArr2[1] = 15;
        iArr2[2] = 1;
        iArr[2] = iArr2;
        this.mCompassData = new CanDataInfo.HondaCompass();
    }

    public void ResetData(boolean check) {
        CanJni.HondaGetCompass(this.mCompassData);
        if (!i2b(this.mCompassData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCompassData.Update)) {
            this.mCompassData.Update = 0;
            updateItem(2, this.mCompassData.Zone);
            updateItem(1, this.mCompassData.Angle, String.format("%d °", new Object[]{Integer.valueOf(this.mCompassData.Angle)}));
            if (this.mCompassData.Status == 0) {
                updateItem(0, 0, getString(R.string.can_fjz));
            } else {
                updateItem(0, 0, getString(R.string.can_zzjz));
            }
        }
    }

    public void QueryData() {
    }
}
