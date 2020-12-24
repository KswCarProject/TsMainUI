package com.ts.can.honda.wc.crown;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanCrownWcAmpSetView extends CanScrollCarInfoView {
    private CanDataInfo.CrownWcAudioInfo mAmpData;

    public CanCrownWcAmpSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                int dPos = Math.abs(pos - this.mAmpData.VolStep);
                if (dPos > 5) {
                    dPos = 5;
                }
                if (pos - this.mAmpData.VolStep > 0) {
                    CanJni.CrownWcAudioSet(1, dPos);
                    return;
                } else {
                    CanJni.CrownWcAudioSet(1, -dPos);
                    return;
                }
            case 1:
                CanJni.CrownWcAudioSet(2, pos);
                return;
            case 2:
                CanJni.CrownWcAudioSet(3, pos);
                return;
            case 3:
                CanJni.CrownWcAudioSet(4, pos);
                return;
            case 4:
                CanJni.CrownWcAudioSet(5, pos);
                return;
            case 5:
                CanJni.CrownWcAudioSet(6, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol, R.string.can_balance_left_right, R.string.can_balance_front_rear, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 40;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[] attr = new int[4];
        attr[1] = 31;
        attr[2] = 1;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 31;
        iArr4[2] = 1;
        iArr3[1] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 31;
        iArr6[2] = 1;
        iArr5[2] = iArr6;
        this.mProgressAttrs[3] = attr;
        this.mProgressAttrs[4] = attr;
        this.mProgressAttrs[5] = attr;
        this.mAmpData = new CanDataInfo.CrownWcAudioInfo();
    }

    public void ResetData(boolean check) {
        CanJni.CrownWcGetAudioInfo(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            if (this.mAmpData.VolStep > 40) {
                updateItem(0, 40, new StringBuilder(String.valueOf(this.mAmpData.VolStep)).toString());
            } else {
                updateItem(0, this.mAmpData.VolStep);
            }
            updateItem(1, this.mAmpData.Bal, setValText(this.mAmpData.Bal, true));
            updateItem(2, this.mAmpData.Fad, setValText(this.mAmpData.Fad, false));
            updateItem(3, this.mAmpData.Bas);
            updateItem(4, this.mAmpData.Mid);
            updateItem(5, this.mAmpData.Tre);
        }
    }

    private String setValText(int val, boolean isLtRt) {
        if (isLtRt) {
            if (val == 16) {
                return "0";
            }
            if (val < 16) {
                return "L" + String.valueOf(16 - val);
            }
            if (val > 10) {
                return "R" + String.valueOf(val - 16);
            }
        } else if (val == 16) {
            return "0";
        } else {
            if (val < 16) {
                return "F" + String.valueOf(16 - val);
            }
            if (val > 10) {
                return "R" + String.valueOf(val - 16);
            }
        }
        return "0";
    }

    public void QueryData() {
        CanJni.CrownWcQuery(135, 255);
    }
}
