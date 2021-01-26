package com.ts.can.hyundai;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;

public class CanHyundaiAmpSetView extends CanScrollCarInfoView {
    public static final int ITEM_AMP_SW = 0;
    private static final int ITEM_MAX = 2;
    public static final int ITEM_VOL = 1;
    public static final String TAG = "CanHyundaiAmpSetView";
    private static int nSwb = 255;
    private static int nVolb = 255;

    public CanHyundaiAmpSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
        switch (id) {
        }
    }

    public static int IsSw() {
        return FtSet.GetCanS(0) >> 7;
    }

    public static int IsVol() {
        return FtSet.GetCanS(0) & 127;
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                FtSet.SetCanS((byte) ((FtSet.GetCanS(0) & 128) | pos), 0);
                CanJni.HyundaiXpVolSet(pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View arg0) {
        switch (((Integer) arg0.getTag()).intValue()) {
            case 0:
                int temp = FtSet.GetCanS(0) & 127;
                if (IsSw() > 0) {
                    FtSet.SetCanS((byte) temp, 0);
                    return;
                } else {
                    FtSet.SetCanS((byte) (temp | 128), 0);
                    return;
                }
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_shoudong, R.string.can_vol};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 45;
        iArr2[2] = 1;
        iArr[1] = iArr2;
    }

    public void ResetData(boolean check) {
        if (nSwb != IsSw() || !check) {
            nSwb = IsSw();
            updateItem(0, nSwb);
            if (nSwb == 0) {
                showItem(1, 0);
            } else {
                showItem(1, 1);
            }
        }
        if (nVolb != IsVol() || !check) {
            nVolb = IsVol();
            updateItem(1, nVolb);
        }
    }

    public void QueryData() {
    }
}
