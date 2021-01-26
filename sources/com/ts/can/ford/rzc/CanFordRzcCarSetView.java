package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.yyw.ts70xhw.FtSet;

public class CanFordRzcCarSetView extends CanScrollCarInfoView {
    private static final int ITEM_AUTO_PARK_SW = 26;
    private static final int ITEM_BWHJSC = 21;
    private static final int ITEM_CDZSD = 25;
    private static final int ITEM_COMPASS = 22;
    private static final int ITEM_CSSS = 17;
    private static final int ITEM_HCSSC = 20;
    private static final int ITEM_HSJZDMS = 19;
    private static final int ITEM_PDQDFZ = 23;
    private static final int ITEM_SNDSC = 18;
    private static final int ITEM_WXCD = 16;
    private static final int ITEM_YBDSC = 14;
    private static final int ITEM_YBFXJ = 24;
    private static final int ITEM_YKCC = 15;
    private static final int ITEM_ZDDDLMD = 12;
    private static final int ITEM_ZNJBS = 13;
    private static int m_AutoParkb = 255;
    private static int m_Compassb = 0;
    private CanDataInfo.FordAdt mAdtData;
    private CanDataInfo.FordSet mSetData;
    /* access modifiers changed from: private */
    public boolean mTpmsReset;

    public CanFordRzcCarSetView(Activity activity) {
        super(activity, 27);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_traction_control_sys, R.string.can_message_tone, R.string.can_alert_tone, R.string.can_zqxbcfz, R.string.can_rvs_keep, R.string.can_fmqbj, R.string.can_rain_sensor, R.string.can_environment_light, R.string.can_zxdsszs, R.string.can_lcdw, R.string.can_tpms_set, R.string.can_hsfp, R.string.can_zdddlmd, R.string.can_znjbs, R.string.can_ybdsc, R.string.can_ykcckz, R.string.can_wxcd, R.string.can_df_jyx5_csss, R.string.can_sndsc, R.string.can_hsjzdms, R.string.can_hcssc, R.string.can_dgsjkz_bwhj, R.string.can_compass, R.string.can_teramont_pdqbfz, R.string.can_instrument_arrow_key, R.string.can_charge_light, R.string.can_active_park};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mAdtData = new CanDataInfo.FordAdt();
        this.mSetData = new CanDataInfo.FordSet();
        CanJni.FordGetAdt(this.mAdtData);
        this.mItemVisibles = new int[]{this.mAdtData.Qylxt, this.mAdtData.Xytsy, this.mAdtData.Jgtsy, this.mAdtData.Zqxbcfz, this.mAdtData.RvsKeep, this.mAdtData.Fmqbj, this.mAdtData.Ysgyq, this.mAdtData.Hjdsd, this.mAdtData.Zxd, this.mAdtData.RangeDW, this.mAdtData.TpmsSet, this.mAdtData.Hsfp, this.mAdtData.Zdddlmd, this.mAdtData.Znjbs, this.mAdtData.Ybdsc, this.mAdtData.Ykcc, this.mAdtData.Wxcd, this.mAdtData.Csss, this.mAdtData.Sndsc, this.mAdtData.Hsjzdms, this.mAdtData.Hcssc, this.mAdtData.Bwhjsj, 1, this.mAdtData.Pdqdfz, this.mAdtData.Ybfxj, this.mAdtData.Cdzsd, 1};
        this.mPopValueIds[8] = new int[]{R.array.can_fist_zxd};
        this.mPopValueIds[9] = new int[]{R.string.can_service_distance_km, R.string.can_service_distance_mi};
        this.mPopValueIds[12] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mPopValueIds[14] = new int[]{R.string.can_headlightautoofftime_0s, R.string.can_headlightautoofftime_15s, R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_60s};
        this.mPopValueIds[18] = new int[]{R.string.can_headlightautoofftime_5m, R.string.can_headlightautoofftime_10m, R.string.can_headlightautoofftime_15m};
        this.mPopValueIds[19] = new int[]{R.string.can_auto_ylgyq, R.string.can_soudong};
        this.mPopValueIds[20] = new int[]{R.string.can_headlightautoofftime_5m, R.string.can_headlightautoofftime_10m};
        this.mPopValueIds[21] = new int[]{R.string.can_headlightautoofftime_30s, R.string.can_headlightautoofftime_1m, R.string.can_headlightautoofftime_2m, R.string.can_headlightautoofftime_3m};
        this.mPopValueIds[25] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_mzd_cx4_mode_on, R.string.can_restricted};
    }

    public void onItem(int id, int item) {
        if (id == 8) {
            CanJni.FordCarSet(163, item + 3);
        } else if (id == 9) {
            CanJni.FordCarSet(163, item + 14);
        } else if (id == 12) {
            CanJni.FordRzcCarSet(176, 0, item);
        } else if (id == 14) {
            CanJni.FordRzcCarSet(176, 2, item);
        } else if (id == 18) {
            CanJni.FordRzcCarSet(176, 5, item);
        } else if (id == 19) {
            CanJni.FordRzcCarSet(176, 6, item);
        } else if (id == 20) {
            CanJni.FordRzcCarSet(176, 7, item);
        } else if (id == 21) {
            CanJni.FordRzcCarSet(176, 9, item);
        } else if (id == 25) {
            CanJni.FordRzcCarSet(163, item + 25, 0);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 0:
                CanJni.FordCarSet(163, GetSWVal(this.mSetData.Qylxt, 1, 2));
                return;
            case 1:
                CanJni.FordCarSet(163, GetSWVal(this.mSetData.Xytsy, 6, 5));
                return;
            case 2:
                CanJni.FordCarSet(163, GetSWVal(this.mSetData.Jgtsy, 8, 7));
                return;
            case 3:
                CanJni.FordCarSet(173, Neg(this.mSetData.Zqxbcfz));
                return;
            case 4:
                CanJni.FordCarSet(174, Neg(this.mSetData.RvsKeep));
                return;
            case 5:
                CanJni.FordCarSet(166, Neg(this.mSetData.Fmqbj));
                return;
            case 6:
                CanJni.FordCarSet(165, Neg(this.mSetData.Ysgyq));
                return;
            case 7:
                CanJni.FordCarSet(163, GetSWVal(this.mSetData.Hjdsd, 19, 20));
                return;
            case 10:
                new CanItemMsgBox(id, getActivity(), R.string.can_tpms_notice, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanFordRzcCarSetView.this.mTpmsReset = true;
                        CanJni.FordCarSet(163, 18);
                    }
                }, (CanItemMsgBox.onMsgBoxClick2) null);
                return;
            case 11:
                CanJni.FordCarSet(175, Neg(this.mSetData.Hsfp));
                return;
            case 13:
                CanJni.FordRzcCarSet(176, 1, Neg(this.mSetData.Znjbs));
                return;
            case 15:
                CanJni.FordRzcCarSet(176, 3, Neg(this.mSetData.Ykcc));
                return;
            case 16:
                CanJni.FordRzcCarSet(176, 4, Neg(this.mSetData.Wxcd));
                return;
            case 17:
                CanJni.FordRzcCarSet(176, 8, Neg(this.mSetData.Csss));
                return;
            case 22:
                int temp = FtSet.Getyw2();
                if ((temp & Can.CAN_VOLKS_XP) > 0) {
                    FtSet.Setyw2(65295 & temp);
                    return;
                } else {
                    FtSet.Setyw2(temp | 16);
                    return;
                }
            case 23:
                if (this.mSetData.Pdqdfz > 0) {
                    CanJni.FordRzcCarSet(163, 22, 0);
                    return;
                } else {
                    CanJni.FordRzcCarSet(163, 21, 0);
                    return;
                }
            case 24:
                if (this.mSetData.Ybfxj > 0) {
                    CanJni.FordRzcCarSet(163, 24, 0);
                    return;
                } else {
                    CanJni.FordRzcCarSet(163, 23, 0);
                    return;
                }
            case 26:
                if (FtSet.GetCanS(2) > 0) {
                    FtSet.SetCanS((byte) 0, 2);
                    return;
                } else {
                    FtSet.SetCanS((byte) 1, 2);
                    return;
                }
            default:
                return;
        }
    }

    public void ResetData(boolean check) {
        CanJni.FordGetSetup(this.mSetData);
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Qylxt);
            updateItem(1, this.mSetData.Xytsy);
            updateItem(2, this.mSetData.Jgtsy);
            updateItem(3, this.mSetData.Zqxbcfz);
            updateItem(4, this.mSetData.RvsKeep);
            updateItem(5, this.mSetData.Fmqbj);
            updateItem(6, this.mSetData.Ysgyq);
            updateItem(7, this.mSetData.Hjdsd);
            updateItem(8, this.mSetData.Zxd);
            updateItem(9, this.mSetData.RangeDW);
            updateItem(11, this.mSetData.Hsfp);
            updateItem(12, this.mSetData.Zdddlmd);
            updateItem(13, this.mSetData.Znjbs);
            updateItem(14, this.mSetData.Ybdsc);
            updateItem(15, this.mSetData.Ykcc);
            updateItem(16, this.mSetData.Wxcd);
            updateItem(17, this.mSetData.Csss);
            updateItem(18, this.mSetData.Sndsc);
            updateItem(19, this.mSetData.Hsjzdms);
            updateItem(20, this.mSetData.Hcssc);
            updateItem(21, this.mSetData.Bwhjsj);
            if (this.mSetData.TpmsSet == 1 && this.mTpmsReset) {
                this.mTpmsReset = false;
                showToast(R.string.can_tpms_reset);
            }
            updateItem(23, this.mSetData.Pdqdfz);
            updateItem(24, this.mSetData.Ybfxj);
            updateItem(25, this.mSetData.Cdzsd);
        }
        if (m_Compassb != (FtSet.Getyw2() & Can.CAN_VOLKS_XP) || !check) {
            m_Compassb = (FtSet.Getyw2() & Can.CAN_VOLKS_XP) >> 4;
            updateItem(22, m_Compassb);
        }
        if (m_AutoParkb != FtSet.GetCanS(2) || !check) {
            m_AutoParkb = FtSet.GetCanS(2);
            updateItem(26, m_AutoParkb);
        }
    }

    public void QueryData() {
        CanJni.FordQuery(36, 0);
    }
}
