package com.ts.can.gac.trumpchi_wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanTrumpchiWcDrvAssSetView extends CanScrollCarInfoView {
    private CanDataInfo.GCWcCarSet mCarSet;

    public CanTrumpchiWcDrvAssSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.TrumpchiWcCarSet(8, item);
        } else if (id == 4) {
            CanJni.TrumpchiWcCarSet(11, item);
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 0) {
            CanJni.TrumpchiWcCarSet(7, pos / 10);
        } else if (id == 2) {
            CanJni.TrumpchiWcCarSet(9, pos);
        } else if (id == 3) {
            CanJni.TrumpchiWcCarSet(10, pos);
        }
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_csbj, R.string.can_zhbjyl, R.string.can_ycsdsj, R.string.can_ycqdsj, R.string.can_zxms};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
        this.mPopValueIds[4] = new int[]{R.string.can_sport, R.string.can_mode_normal, R.string.can_mode_ss};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 200;
        iArr2[2] = 10;
        iArr2[3] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 30;
        iArr4[2] = 1;
        iArr3[2] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 30;
        iArr6[2] = 1;
        iArr5[3] = iArr6;
        this.mCarSet = new CanDataInfo.GCWcCarSet();
    }

    public void ResetData(boolean check) {
        CanJni.TrumpchiWcGetCarSet(this.mCarSet);
        if (!i2b(this.mCarSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSet.Update)) {
            this.mCarSet.Update = 0;
            updateItem(0, this.mCarSet.Jsfzcsbj * 10, String.valueOf(this.mCarSet.Jsfzcsbj * 10) + "Km/h");
            updateItem(1, this.mCarSet.Jsfzzhybbjyl);
            updateItem(2, this.mCarSet.Jsfzycsdsj);
            updateItem(3, this.mCarSet.Jsfzycqdsj);
            updateItem(4, this.mCarSet.Jsfzzxms);
        }
    }

    public void QueryData() {
    }
}
