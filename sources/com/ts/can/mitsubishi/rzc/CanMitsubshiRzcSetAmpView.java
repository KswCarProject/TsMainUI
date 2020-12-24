package com.ts.can.mitsubishi.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanMitsubshiRzcSetAmpView extends CanScrollCarInfoView {
    private static final int ALL_RESET = 15;
    private static final int BAL = 1;
    private static final int BAL_FAD_RESET = 13;
    private static final int BASS = 3;
    private static final int DOLBY_VOL = 12;
    private static final int EQ = 2;
    private static final int EQ_RESET = 14;
    private static final int FAD = 0;
    private static final int LISTEN_POSITION = 9;
    private static final int MID = 4;
    private static final int PREMIDIA_HD = 10;
    private static final int PUNCH = 6;
    private static final int SCV = 11;
    private static final int SUR_TYPE = 8;
    private static final int TRE = 5;
    private static final int VOL = 7;
    private CanDataInfo.MitSubishiRzcAmp mAmpData;

    public CanMitsubshiRzcSetAmpView(Activity activity) {
        super(activity, 16);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.MitSubishiRzcAmpSet(3, item);
                return;
            case 8:
                CanJni.MitSubishiRzcAmpSet(10, item);
                return;
            case 9:
                CanJni.MitSubishiRzcAmpSet(11, item);
                return;
            case 10:
                CanJni.MitSubishiRzcAmpSet(12, item);
                return;
            case 11:
                CanJni.MitSubishiRzcAmpSet(13, item);
                return;
            case 12:
                CanJni.MitSubishiRzcAmpSet(14, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.MitSubishiRzcAmpSet(1, pos);
                return;
            case 1:
                CanJni.MitSubishiRzcAmpSet(2, pos);
                return;
            case 3:
                CanJni.MitSubishiRzcAmpSet(4, pos);
                return;
            case 4:
                CanJni.MitSubishiRzcAmpSet(6, pos);
                return;
            case 5:
                CanJni.MitSubishiRzcAmpSet(5, pos);
                return;
            case 6:
                CanJni.MitSubishiRzcAmpSet(7, pos);
                return;
            case 7:
                CanJni.MitSubishiRzcAmpSet(8, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 13:
                new CanItemMsgBox(id, getActivity(), R.string.can_sure_reset, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.MitSubishiRzcAmpSet(16, 0);
                    }
                });
                return;
            case 14:
                new CanItemMsgBox(id, getActivity(), R.string.can_sure_reset, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.MitSubishiRzcAmpSet(17, 0);
                    }
                });
                return;
            case 15:
                new CanItemMsgBox(id, getActivity(), R.string.can_sure_reset, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.MitSubishiRzcAmpSet(18, 0);
                    }
                });
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_balance_front_rear, R.string.can_balance_left_right, R.string.can_eq, R.string.can_vol_low, R.string.can_vol_middle, R.string.can_vol_high, R.string.can_amp_punch, R.string.can_vol, R.string.can_amp_surround_type, R.string.can_amp_listening_position, R.string.can_amp_premidia_hd, R.string.can_amp_scv, R.string.can_amp_dolby_volume, R.string.can_amp_balfad_reset, R.string.can_amp_eq_reset, R.string.can_amp_all_reset};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 22;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 22;
        iArr4[2] = 1;
        iArr3[1] = iArr4;
        this.mPopValueIds[2] = new int[]{R.string.can_eq_rock, R.string.can_eq_pop, R.string.can_eq_hiphop, R.string.can_psa_eq_Jazz, R.string.can_mode_normal};
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[0] = 2;
        iArr6[1] = 12;
        iArr6[2] = 1;
        iArr5[3] = iArr6;
        int[][] iArr7 = this.mProgressAttrs;
        int[] iArr8 = new int[4];
        iArr8[0] = 2;
        iArr8[1] = 12;
        iArr8[2] = 1;
        iArr7[4] = iArr8;
        int[][] iArr9 = this.mProgressAttrs;
        int[] iArr10 = new int[4];
        iArr10[0] = 2;
        iArr10[1] = 12;
        iArr10[2] = 1;
        iArr9[5] = iArr10;
        int[][] iArr11 = this.mProgressAttrs;
        int[] iArr12 = new int[4];
        iArr12[0] = 2;
        iArr12[1] = 8;
        iArr12[2] = 1;
        iArr11[6] = iArr12;
        int[][] iArr13 = this.mProgressAttrs;
        int[] iArr14 = new int[4];
        iArr14[1] = 45;
        iArr14[2] = 1;
        iArr13[7] = iArr14;
        this.mPopValueIds[8] = new int[]{R.string.can_amp_off, R.string.can_amp_surround_type2, R.string.can_amp_surround_type3};
        this.mPopValueIds[9] = new int[]{R.string.can_amp_listening_position_1, R.string.can_amp_listening_position_2, R.string.can_amp_listening_position_3};
        this.mPopValueIds[10] = new int[]{R.string.can_amp_off, R.string.can_amp_low, R.string.can_amp_high};
        this.mPopValueIds[11] = new int[]{R.string.can_amp_off, R.string.can_amp_low, R.string.can_amp_mid, R.string.can_amp_high};
        this.mPopValueIds[12] = new int[]{R.string.can_amp_off, R.string.can_amp_low, R.string.can_amp_mid, R.string.can_amp_high};
        this.mAmpData = new CanDataInfo.MitSubishiRzcAmp();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        updateItem(0, 11, setValText(11, false));
        updateItem(1, 11, setValText(11, true));
        updateItem(2, 4);
        updateItem(3, 7, String.format("%d", new Object[]{0}));
        updateItem(4, 7, String.format("%d", new Object[]{0}));
        updateItem(5, 7, String.format("%d", new Object[]{0}));
        updateItem(6, 5, String.format("%d", new Object[]{0}));
        updateItem(7, 0);
        updateItem(8, 0);
        updateItem(9, 1);
        updateItem(10, 0);
        updateItem(11, 2);
        updateItem(12, 0);
    }

    public void ResetData(boolean check) {
        CanJni.MitSubishiRzcGetAmpData(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            updateItem(0, this.mAmpData.Fad, setValText(this.mAmpData.Fad, false));
            updateItem(1, this.mAmpData.Bal, setValText(this.mAmpData.Bal, true));
            updateItem(2, this.mAmpData.EQ);
            updateItem(3, this.mAmpData.Bass, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Bass - 7)}));
            updateItem(4, this.mAmpData.Mid, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Mid - 7)}));
            updateItem(5, this.mAmpData.Tre, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Tre - 7)}));
            updateItem(6, this.mAmpData.Punch, String.format("%d", new Object[]{Integer.valueOf(this.mAmpData.Punch - 5)}));
            updateItem(7, this.mAmpData.Vol);
            updateItem(8, this.mAmpData.SurroundType);
            updateItem(9, this.mAmpData.ListeningPos);
            updateItem(10, this.mAmpData.PremidiaHD);
            updateItem(11, this.mAmpData.SCV);
            updateItem(12, this.mAmpData.DolbyVolume);
        }
    }

    public void QueryData() {
    }

    private String setValText(int val, boolean isLtRt) {
        if (isLtRt) {
            if (val == 11) {
                return "0";
            }
            if (val < 11) {
                return "L" + String.valueOf(11 - val);
            }
            if (val > 11) {
                return "R" + String.valueOf(val - 11);
            }
        } else if (val == 11) {
            return "0";
        } else {
            if (val < 11) {
                return "F" + String.valueOf(11 - val);
            }
            if (val > 11) {
                return "R" + String.valueOf(val - 11);
            }
        }
        return "0";
    }
}
