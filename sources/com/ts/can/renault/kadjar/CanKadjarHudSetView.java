package com.ts.can.renault.kadjar;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanKadjarHudSetView extends CanScrollCarInfoView {
    protected static final int ITEM_HUD_GD = 4;
    protected static final int ITEM_HUD_RJMS = 2;
    protected static final int ITEM_HUD_SW = 0;
    protected static final int ITEM_HUD_YJMS = 3;
    protected static final int ITEM_HUD_ZDTJLD = 1;
    protected static final int ITEM_MAX = 5;
    private CanDataInfo.KadjarSet2Info mCarSet;

    public CanKadjarHudSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 2:
                CanJni.KadjarCarSet(162, pos);
                return;
            case 3:
                CanJni.KadjarCarSet(163, pos);
                return;
            case 4:
                CanJni.KadjarCarSet(164, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.KadjarCarSet(Can.CAN_CHANA_CS75_WC, Neg(this.mCarSet.HudSw));
                return;
            case 1:
                CanJni.KadjarCarSet(161, Neg(this.mCarSet.HudZdldtj));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_switch, R.string.can_zdtjld, R.string.can_rjms, R.string.can_yjms, R.string.can_height};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 100;
        iArr2[2] = 5;
        iArr[2] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 100;
        iArr4[2] = 5;
        iArr3[3] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 50;
        iArr6[2] = 1;
        iArr5[4] = iArr6;
        this.mCarSet = new CanDataInfo.KadjarSet2Info();
    }

    public void ResetData(boolean check) {
        CanJni.KadjarGetCarSet2(this.mCarSet);
        if (!i2b(this.mCarSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSet.Update)) {
            this.mCarSet.Update = 0;
            updateItem(0, this.mCarSet.HudSw);
            updateItem(1, this.mCarSet.HudZdldtj);
            updateItem(2, this.mCarSet.HudRjms);
            updateItem(3, this.mCarSet.HudYjms);
            updateItem(4, this.mCarSet.HudGd);
        }
    }

    public void QueryData() {
        CanJni.KadjarCarSet(114, Can.CAN_CHANA_CS75_WC);
        Sleep(5);
        CanJni.KadjarCarSet(114, 161);
        Sleep(5);
        CanJni.KadjarCarSet(114, 162);
        Sleep(5);
        CanJni.KadjarCarSet(114, 163);
        Sleep(5);
        CanJni.KadjarCarSet(114, 164);
        Sleep(5);
    }
}
