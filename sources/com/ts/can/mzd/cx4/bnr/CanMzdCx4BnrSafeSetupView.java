package com.ts.can.mzd.cx4.bnr;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanMzdCx4BnrSafeSetupView extends CanScrollCarInfoView {
    private static final int ITEM_JGQZ = 16;
    private static final int ITEM_LANE_KEEP_ASS_SYS_GRDS = 8;
    private static final int ITEM_LANE_KEEP_ASS_SYS_GY = 7;
    private static final int ITEM_LANE_KEEP_ASS_SYS_JG = 10;
    private static final int ITEM_LANE_KEEP_ASS_SYS_JGLX = 11;
    private static final int ITEM_LANE_KEEP_ASS_SYS_JGQD = 12;
    private static final int ITEM_LANE_KEEP_ASS_SYS_LMD = 9;
    private static final int ITEM_LANE_KEEP_ASS_SYS_TITLE = 6;
    private static final int ITEM_XSJG_TITLE = 13;
    private static final int ITEM_XSJS = 15;
    private static final int ITEM_XSXS = 14;
    private CanDataInfo.Cx4_CarSet_Data mSetData;
    private CanDataInfo.Mzd_Bnr_Set mSetData2;

    public CanMzdCx4BnrSafeSetupView(Activity activity) {
        super(activity, 17);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.MzdCx4CarSet(26, item);
        } else if (id == 2) {
            CanJni.MzdCx4CarSet(27, item);
        } else if (id == 4) {
            CanJni.MzdCx4CarSet(29, item);
        } else if (id == 8) {
            CanJni.MzdCx4CarSet(49, item);
        } else if (id == 9) {
            CanJni.MzdCx4CarSet(50, item);
        } else if (id == 11) {
            CanJni.MzdCx4CarSet(52, item);
        } else if (id == 12) {
            CanJni.MzdCx4CarSet(53, item);
        } else if (id == 15) {
            CanJni.MzdCx4CarSet(65, item);
        } else if (id == 16) {
            CanJni.MzdCx4CarSet(66, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.MzdCx4CarSet(25, Neg(this.mSetData.SBS_SCBS));
        } else if (id == 3) {
            CanJni.MzdCx4CarSet(28, Neg(this.mSetData.DRSS_Ms));
        } else if (id == 5) {
            new CanItemMsgBox(id, getActivity(), R.string.can_teramont_reset_notice, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanJni.MzdCx4CarSet(24, 0);
                }
            }, (CanItemMsgBox.onMsgBoxClick2) null);
        } else if (id == 7) {
            CanJni.MzdCx4CarSet(48, Neg(this.mSetData2.LaneKeepAssSysGy));
        } else if (id == 10) {
            CanJni.MzdCx4CarSet(51, Neg(this.mSetData2.LaneKeepAssSysLaneDepartureJg));
        } else if (id == 14) {
            CanJni.MzdCx4CarSet(64, Neg(this.mSetData2.Xsxs));
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_mzd_wc_sbs_mode, R.string.can_mzd_wc_sbs_jgjl, R.string.can_mzd_wc_sbs_jgyl, R.string.can_mzd_wc_drss_mode, R.string.can_mzd_wc_drss_jgjl, R.string.can_reset_all, R.string.can_lane_departure_xt, R.string.can_gy, R.string.can_js, R.string.can_cdfz_lmd, R.string.can_jg, R.string.can_jg_type, R.string.can_jinggaoqingdu, R.string.can_sxjg, R.string.can_sxxs, R.string.can_xsms, R.string.can_jsfaz};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_1};
        this.mPopValueIds[2] = new int[]{R.string.can_off, R.string.can_cdjd, R.string.can_cdjg};
        this.mPopValueIds[4] = new int[]{R.string.can_sdqfwxjgjl_3, R.string.can_sdqfwxjgjl_2, R.string.can_sdqfwxjgjl_1};
        this.mPopValueIds[8] = new int[]{R.string.can_early, R.string.can_late};
        this.mPopValueIds[9] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mPopValueIds[11] = new int[]{R.string.can_zd, R.string.can_bbs, R.string.can_lls};
        this.mPopValueIds[12] = new int[]{R.string.can_ac_low, R.string.can_ac_mid, R.string.can_ac_high};
        this.mPopValueIds[15] = new int[]{R.string.can_display, R.string.can_vision, R.string.can_yxysj};
        this.mPopValueIds[16] = new int[]{R.string.can_0_km_h, R.string.can_5_km_h, R.string.can_10_km_h};
        this.mSetData = new CanDataInfo.Cx4_CarSet_Data();
        this.mSetData2 = new CanDataInfo.Mzd_Bnr_Set();
    }

    public void ResetData(boolean check) {
        CanJni.MzdCx4GetCarSetInfo(this.mSetData);
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.SBS_SCBS, this.mSetData.SBS_Jgjl, this.mSetData.SBS_Jgyl, this.mSetData.DRSS_Ms, this.mSetData.DRSS_Jgjl});
        }
        CanJni.MzdBnrGetCarSet2(this.mSetData2);
        if (!i2b(this.mSetData2.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData2.Update)) {
            this.mSetData2.Update = 0;
            updateItem(7, this.mSetData2.LaneKeepAssSysGy);
            updateItem(8, this.mSetData2.LaneKeepAssSysGrds);
            updateItem(9, this.mSetData2.LaneKeepAssSysLmd);
            updateItem(10, this.mSetData2.LaneKeepAssSysLaneDepartureJg);
            updateItem(11, this.mSetData2.LaneKeepAssistSysJglx);
            updateItem(12, this.mSetData2.LaneKeepAssistSysJgqd);
            updateItem(14, this.mSetData2.Xsxs);
            updateItem(15, this.mSetData2.Xsjs);
            updateItem(16, this.mSetData2.Jgfz);
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(9, 0);
        Sleep(5);
        CanJni.MzdCx4Query(13, 0);
    }
}
