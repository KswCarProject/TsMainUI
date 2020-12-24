package com.ts.can.volvo.lz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanVolvoLZAmpSetView extends CanScrollCarInfoView {
    private CanDataInfo.VolvoXc60_AmpSet mAmpData;

    public CanVolvoLZAmpSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.VolvoLzCx60AmpSet(0, pos);
                return;
            case 1:
                CanJni.VolvoLzCx60AmpSet(1, pos);
                return;
            case 2:
                CanJni.VolvoLzCx60AmpSet(2, pos);
                return;
            case 3:
                CanJni.VolvoLzCx60AmpSet(3, pos);
                return;
            case 4:
                CanJni.VolvoLzCx60AmpSet(4, pos);
                return;
            default:
                return;
        }
    }

    private int checkPos(int pos, int value) {
        int result = Math.abs(pos - value);
        if (result > 1) {
            result = 1;
        }
        if (pos - value > 0) {
            return value + result;
        }
        return value - result;
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_vol_low, R.string.can_vol_middle};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 33;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[] attr = new int[4];
        attr[1] = 20;
        attr[2] = 1;
        this.mProgressAttrs[1] = attr;
        this.mProgressAttrs[2] = attr;
        this.mProgressAttrs[3] = attr;
        this.mProgressAttrs[4] = attr;
        this.mAmpData = new CanDataInfo.VolvoXc60_AmpSet();
    }

    public void ResetData(boolean check) {
        CanJni.VolvoLzCx60GetAmpSet(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Vol);
            updateItem(1, this.mAmpData.Fad, setValText(this.mAmpData.Fad, 1));
            updateItem(2, this.mAmpData.Bal, setValText(this.mAmpData.Bal, 2));
            updateItem(3, this.mAmpData.Bas, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Bas - 10)}));
            updateItem(4, this.mAmpData.Mid, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Mid - 10)}));
        }
    }

    public void QueryData() {
        CanJni.VolvoLzCx60Query(122, 0);
    }

    private String setValText(int val, int sta) {
        switch (sta) {
            case 1:
                if (val == 10) {
                    return "0";
                }
                if (val < 10) {
                    return "F" + String.valueOf(10 - val);
                }
                if (val > 10) {
                    return "R" + String.valueOf(val - 10);
                }
                break;
            case 2:
                if (val == 10) {
                    return "0";
                }
                if (val < 10) {
                    return "L" + String.valueOf(10 - val);
                }
                if (val > 10) {
                    return "R" + String.valueOf(val - 10);
                }
                break;
        }
        return "0";
    }
}
