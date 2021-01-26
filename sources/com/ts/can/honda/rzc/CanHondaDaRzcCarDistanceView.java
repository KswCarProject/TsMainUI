package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaDaRzcCarDistanceView extends CanScrollCarInfoView {
    public static final int ITEM_ADJUSTOUTSIDETEMP = 2;
    public static final int ITEM_RYXHLXSSDBJZM = 3;
    public static final int ITEM_TRIPARESETTIMING = 1;
    public static final int ITEM_TRIPBRESETTIMING = 0;
    protected CanDataInfo.HondaSetData mSetData;

    public CanHondaDaRzcCarDistanceView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.HondaDACarSet(3, item);
                return;
            case 1:
                CanJni.HondaDACarSet(2, item);
                return;
            case 3:
                CanJni.HondaDACarSet(1, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 2) {
            CanJni.HondaDACarSet(0, pos);
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_tripbresettiming, R.string.can_triparesettiming, R.string.can_adjustoutsidetemp, R.string.can_rybjtm};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP};
        if (CanJni.GetSubType() == 6) {
            this.mPopValueIds[0] = new int[]{R.string.can_tripbresettiming_5, R.string.can_tripbresettiming_2, R.string.can_tripbresettiming_3};
            this.mPopValueIds[1] = new int[]{R.string.can_tripbresettiming_5, R.string.can_tripbresettiming_2, R.string.can_tripbresettiming_3};
        } else {
            this.mPopValueIds[0] = new int[]{R.string.can_tripbresettiming_1, R.string.can_tripbresettiming_2, R.string.can_tripbresettiming_3};
            this.mPopValueIds[1] = new int[]{R.string.can_tripbresettiming_1, R.string.can_tripbresettiming_2, R.string.can_tripbresettiming_3};
        }
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 6;
        iArr2[2] = 1;
        iArr[2] = iArr2;
        this.mPopValueIds[3] = new int[]{R.string.can_trunk_close, R.string.can_trunk_open};
        this.mSetData = new CanDataInfo.HondaSetData();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    public void ResetData(boolean check) {
        CanJni.HondaDAGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.DistanceUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.DistanceUpdate)) {
            this.mSetData.DistanceUpdate = 0;
            updateItem(0, this.mSetData.TripBReset);
            updateItem(1, this.mSetData.TripAReset);
            updateItem(2, this.mSetData.AdjustTemp, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.AdjustTemp - 3)}));
        }
    }

    public void QueryData() {
    }
}
