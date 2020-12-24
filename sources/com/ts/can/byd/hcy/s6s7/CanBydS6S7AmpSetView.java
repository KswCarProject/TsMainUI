package com.ts.can.byd.hcy.s6s7;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBydS6S7AmpSetView extends CanScrollCarInfoView {
    private CanDataInfo.BYDS6S7EQSetData mAmpData;

    public CanBydS6S7AmpSetView(Activity activity) {
        super(activity, 7);
    }

    public void onItem(int id, int item) {
        if (id == 3) {
            CanJni.BYDS6S7EQSet(103, item + 1, 0);
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.BYDS6S7EQSet(97, pos, 0);
                return;
            case 1:
                CanJni.BYDS6S7EQSet(101, pos, this.mAmpData.Fad);
                return;
            case 2:
                CanJni.BYDS6S7EQSet(101, this.mAmpData.Bal, pos);
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
        switch (((Integer) v.getTag()).intValue()) {
            case 4:
                CanJni.BYDS6S7EQSet(106, Neg(this.mAmpData.Dirac), 0);
                return;
            case 5:
                CanJni.BYDS6S7EQSet(108, Neg(this.mAmpData.DXDYX), 0);
                return;
            case 6:
                if (this.mAmpData.Mute == 1) {
                    CanJni.BYDS6S7EQSet(98, 0, 0);
                    return;
                } else {
                    CanJni.BYDS6S7EQSet(98, 1, 0);
                    return;
                }
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_amp_musicstyle, R.string.can_amp_dirac, R.string.can_amp_dxd, R.string.can_vol_mute};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 63;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[] attr = new int[4];
        attr[1] = 14;
        attr[2] = 1;
        this.mProgressAttrs[1] = attr;
        this.mProgressAttrs[2] = attr;
        this.mPopValueIds[3] = new int[]{R.array.can_byds6s7_musicstyle};
        this.mAmpData = new CanDataInfo.BYDS6S7EQSetData();
    }

    public void ResetData(boolean check) {
        CanJni.BYDS6S7GetEQSetData(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Volumn);
            updateItem(1, this.mAmpData.Bal);
            updateItem(2, this.mAmpData.Fad);
            updateItem(3, this.mAmpData.MusicStyle - 1);
            updateItem(4, this.mAmpData.Dirac);
            updateItem(5, this.mAmpData.DXDYX);
            if (this.mAmpData.Mute == 1) {
                updateItem(6, this.mAmpData.Mute);
            } else {
                updateItem(6, 0);
            }
        }
    }

    public void QueryData() {
    }
}
