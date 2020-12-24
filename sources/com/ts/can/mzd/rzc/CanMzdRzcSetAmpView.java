package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanMzdRzcSetAmpView extends CanScrollCarInfoView {
    private static final int ALC = 4;
    private static final int BAL = 3;
    private static final int BASS = 0;
    private static final int BOSE_AUDIO_POLOT = 7;
    private static final int BOSE_CENTER_POINT = 6;
    private static final int FAD = 2;
    private static final int TRE = 1;
    private static final int VOL = 5;
    private CanDataInfo.Mzd_Rzc_Amp mAmpData;

    public CanMzdRzcSetAmpView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                int dPos = Math.abs(pos - this.mAmpData.Bass);
                if (dPos > 2) {
                    dPos = 2;
                }
                if (pos - this.mAmpData.Bass > 0) {
                    CanJni.MzdRzcCarAmpSet(1, dPos);
                    return;
                } else {
                    CanJni.MzdRzcCarAmpSet(1, dPos + 128);
                    return;
                }
            case 1:
                int dPos2 = Math.abs(pos - this.mAmpData.Tre);
                if (dPos2 > 2) {
                    dPos2 = 2;
                }
                if (pos - this.mAmpData.Tre > 0) {
                    CanJni.MzdRzcCarAmpSet(2, dPos2);
                    return;
                } else {
                    CanJni.MzdRzcCarAmpSet(2, dPos2 + 128);
                    return;
                }
            case 2:
                int dPos3 = Math.abs(pos - this.mAmpData.Fad);
                if (dPos3 > 2) {
                    dPos3 = 2;
                }
                if (pos - this.mAmpData.Fad > 0) {
                    CanJni.MzdRzcCarAmpSet(3, dPos3);
                    return;
                } else {
                    CanJni.MzdRzcCarAmpSet(3, dPos3 + 128);
                    return;
                }
            case 3:
                int dPos4 = Math.abs(pos - this.mAmpData.Bal);
                if (dPos4 > 2) {
                    dPos4 = 2;
                }
                if (pos - this.mAmpData.Bal > 0) {
                    CanJni.MzdRzcCarAmpSet(4, dPos4);
                    return;
                } else {
                    CanJni.MzdRzcCarAmpSet(4, dPos4 + 128);
                    return;
                }
            case 4:
                int dPos5 = Math.abs(pos - this.mAmpData.Alc);
                if (dPos5 > 1) {
                    dPos5 = 1;
                }
                if (pos - this.mAmpData.Alc > 0) {
                    CanJni.MzdRzcCarAmpSet(5, dPos5);
                    return;
                } else {
                    CanJni.MzdRzcCarAmpSet(5, dPos5 + 128);
                    return;
                }
            case 5:
                int dPos6 = Math.abs(pos - this.mAmpData.Vol);
                if (dPos6 > 5) {
                    dPos6 = 5;
                }
                if (pos - this.mAmpData.Vol > 0) {
                    CanJni.MzdRzcCarAmpSet(6, dPos6);
                    return;
                } else {
                    CanJni.MzdRzcCarAmpSet(6, dPos6 + 128);
                    return;
                }
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 6:
                CanJni.MzdRzcCarAmpSet(7, Neg(this.mAmpData.CenterPoint));
                return;
            case 7:
                CanJni.MzdRzcCarAmpSet(8, Neg(this.mAmpData.fgAudioPLT));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_vol_low, R.string.can_vol_high, R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_mzd_cx4_alc, R.string.can_vol, R.string.can_bose_centerpoint, R.string.can_bose_audiopilot};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 16;
        iArr2[2] = 1;
        iArr[2] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 16;
        iArr4[2] = 1;
        iArr3[3] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 12;
        iArr6[2] = 1;
        iArr5[0] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[1] = 12;
        iArr8[2] = 1;
        iArr7[1] = iArr8;
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[1] = 7;
        iArr10[2] = 1;
        iArr9[4] = iArr10;
        int[][] iArr11 = this.mProgressAttrs;
        int[] iArr12 = new int[4];
        iArr12[0] = 1;
        iArr12[1] = 63;
        iArr12[2] = 1;
        iArr11[5] = iArr12;
        this.mAmpData = new CanDataInfo.Mzd_Rzc_Amp();
    }

    public void ResetData(boolean check) {
        CanJni.MzdRzcGetCarAmpData(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Bass, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Bass - 6)}));
            updateItem(1, this.mAmpData.Tre, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Tre - 6)}));
            updateItem(2, this.mAmpData.Fad, setValText(this.mAmpData.Fad, false));
            updateItem(3, this.mAmpData.Bal, setValText(this.mAmpData.Bal, true));
            updateItem(4, this.mAmpData.Alc);
            updateItem(5, this.mAmpData.Vol);
            updateItem(6, this.mAmpData.CenterPoint);
            updateItem(7, this.mAmpData.fgAudioPLT);
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(112, 0);
    }

    private String setValText(int val, boolean isLtRt) {
        if (isLtRt) {
            if (val == 8) {
                return "0";
            }
            if (val < 8) {
                return "L" + String.valueOf(8 - val);
            }
            if (val > 8) {
                return "R" + String.valueOf(val - 8);
            }
        } else if (val == 8) {
            return "0";
        } else {
            if (val < 8) {
                return "F" + String.valueOf(8 - val);
            }
            if (val > 8) {
                return "R" + String.valueOf(val - 8);
            }
        }
        return "0";
    }
}
