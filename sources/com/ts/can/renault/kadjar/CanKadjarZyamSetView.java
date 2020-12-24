package com.ts.can.renault.kadjar;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanKadjarZyamSetView extends CanScrollCarInfoView {
    protected static final int ITEM_MAX = 4;
    protected static final int ITEM_ZYAM_MS = 1;
    protected static final int ITEM_ZYAM_QD = 2;
    protected static final int ITEM_ZYAM_SD = 3;
    protected static final int ITEM_ZYAM_SW = 0;
    private CanDataInfo.KadjarSet2Info mCarSet;

    public CanKadjarZyamSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.KadjarCarSet(145, item + 1);
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 2:
                CanJni.KadjarCarSet(146, pos);
                return;
            case 3:
                CanJni.KadjarCarSet(147, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.KadjarCarSet(144, Neg(this.mCarSet.ZyamSw));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_switch, R.string.can_mode, R.string.can_strength, R.string.can_sudu};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS};
        this.mPopValueIds[1] = new int[]{R.string.can_tonic, R.string.can_relaxing, R.string.can_lumbar};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = 1;
        iArr2[1] = 5;
        iArr2[2] = 1;
        iArr[2] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[0] = 1;
        iArr4[1] = 5;
        iArr4[2] = 1;
        iArr3[3] = iArr4;
        this.mCarSet = new CanDataInfo.KadjarSet2Info();
    }

    public void ResetData(boolean check) {
        CanJni.KadjarGetCarSet2(this.mCarSet);
        if (!i2b(this.mCarSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSet.Update)) {
            this.mCarSet.Update = 0;
            updateItem(0, this.mCarSet.ZyamSw);
            updateItem(1, this.mCarSet.ZyamMs - 1);
            updateItem(2, this.mCarSet.ZyamQd);
            updateItem(3, this.mCarSet.ZyamSd);
        }
    }

    public void QueryData() {
        CanJni.KadjarCarSet(114, 144);
        Sleep(5);
        CanJni.KadjarCarSet(114, 145);
        Sleep(5);
        CanJni.KadjarCarSet(114, 146);
        Sleep(5);
        CanJni.KadjarCarSet(114, 147);
        Sleep(5);
    }
}
