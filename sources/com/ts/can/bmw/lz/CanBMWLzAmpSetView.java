package com.ts.can.bmw.lz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemPopupList;

public class CanBMWLzAmpSetView extends CanScrollCarInfoView {
    protected static final int ITEM_BAL = 3;
    protected static final int ITEM_FAD = 2;
    protected static final int ITEM_HIGH = 0;
    protected static final int ITEM_LOW = 1;
    protected static final int ITEM_SOUND = 4;
    private CanDataInfo.BmwLz_AmpData mAmpData;
    private String[] mSoundValues;

    public CanBMWLzAmpSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 4:
                CanJni.BmwLzAmpSet(5, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.BmwLzAmpSet(1, pos);
                return;
            case 1:
                CanJni.BmwLzAmpSet(2, pos);
                return;
            case 2:
                CanJni.BmwLzAmpSet(3, pos);
                return;
            case 3:
                CanJni.BmwLzAmpSet(4, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol_high, R.string.can_vol_low, R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_amp_musicstyle};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 12;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 12;
        iArr4[2] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 20;
        iArr6[2] = 1;
        iArr5[2] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[1] = 20;
        iArr8[2] = 1;
        iArr7[3] = iArr8;
        this.mPopValueIds[4] = new int[]{R.string.app_name};
        this.mAmpData = new CanDataInfo.BmwLz_AmpData();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        this.mSoundValues = new String[]{"CONCERT HALL", "JAZZ CLUB", "CATHEDRAL", "MEM01", "MEM02", "MEM03"};
        getScrollManager().RemoveView(4);
        this.mItemObjects[4] = getScrollManager().addItemPopupList(this.mItemTitleIds[4], this.mSoundValues, 4, (CanItemPopupList.onPopItemClick) this);
    }

    public void ResetData(boolean check) {
        CanJni.BmwLzGetAmpData(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Bass, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Bass - 6)}));
            updateItem(1, this.mAmpData.Tre, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Tre - 6)}));
            updateItem(2, this.mAmpData.Fad, setValText(this.mAmpData.Fad, 2));
            updateItem(3, this.mAmpData.Bal, setValText(this.mAmpData.Bal, 3));
            updateItem(4, this.mAmpData.Sound);
        }
    }

    private String setValText(int val, int sta) {
        switch (sta) {
            case 2:
                if (val == 10) {
                    return "0";
                }
                if (val < 10) {
                    return "R" + String.valueOf(10 - val);
                }
                if (val > 10) {
                    return "F" + String.valueOf(val - 10);
                }
                break;
            case 3:
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

    public void QueryData() {
        CanJni.BmwLzQueryData(57, 0);
    }
}
