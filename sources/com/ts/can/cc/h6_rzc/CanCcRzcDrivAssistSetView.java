package com.ts.can.cc.h6_rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanCcRzcDrivAssistSetView extends CanScrollCarInfoView {
    private static final int ITEM_BXFZ = 7;
    private static final int ITEM_CDBCFZ = 4;
    private static final int ITEM_CDJZBC = 5;
    private static final int ITEM_CDPLYJ = 3;
    private static final int ITEM_CSBJ = 14;
    private static final int ITEM_CSBJLMD = 15;
    private static final int ITEM_CSBS = 20;
    private static final int ITEM_CYBH = 11;
    private static final int ITEM_DCCXFZ = 9;
    private static final int ITEM_DDCT = 23;
    private static final int ITEM_DSJJZD = 17;
    private static final int ITEM_DTFXWDFZ = 18;
    private static final int ITEM_JJZDSSS = 13;
    private static final int ITEM_JTBZXX = 6;
    private static final int ITEM_KMYJ = 8;
    private static final int ITEM_PLJSTX = 10;
    private static final int ITEM_PZANFZ = 0;
    private static final int ITEM_QJBJKG = 19;
    private static final int ITEM_QXDGSJG = 24;
    private static final int ITEM_XCMS = 25;
    private static final int ITEM_XHJS = 16;
    private static final int ITEM_XHMS = 12;
    private static final int ITEM_XRAQFZ = 1;
    private static final int ITEM_YBPYSSZ = 26;
    private static final int ITEM_YJLMD = 2;
    private static final int ITEM_ZXMS = 21;
    private static final int ITEM_ZYYB = 22;
    private CanDataInfo.H6CarSet mSetData;

    public CanCcRzcDrivAssistSetView(Activity activity) {
        super(activity, 27);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.CCH6CarSetRzc(51, item, 0);
                return;
            case 12:
                CanJni.CCH6CarSetRzc(62, item, 0);
                return;
            case 20:
                CanJni.CCH6CarSetRzc(66, item, 0);
                return;
            case 21:
                CanJni.CCH6CarSetRzc(67, item, 0);
                return;
            case 24:
                CanJni.CCH6CarSetRzc(73, item, 0);
                return;
            case 25:
                CanJni.CCH6CarSetRzc(74, item, 0);
                return;
            case 26:
                CanJni.CCH6CarSetRzc(75, item, 0);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 15:
                CanJni.CCH6CarSetRzc(64, pos, 0);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.CCH6CarSetRzc(48, Neg(this.mSetData.Pzanfz), 0);
                return;
            case 1:
                CanJni.CCH6CarSetRzc(49, Neg(this.mSetData.Xraqfz), 0);
                return;
            case 3:
                CanJni.CCH6CarSetRzc(52, Neg(this.mSetData.Cdplyj), 0);
                return;
            case 4:
                CanJni.CCH6CarSetRzc(53, Neg(this.mSetData.Cdbcfz), 0);
                return;
            case 5:
                CanJni.CCH6CarSetRzc(54, Neg(this.mSetData.Cdjzbc), 0);
                return;
            case 6:
                CanJni.CCH6CarSetRzc(55, Neg(this.mSetData.Jtbzxx), 0);
                return;
            case 7:
                CanJni.CCH6CarSetRzc(57, Neg(this.mSetData.Bxfz), 0);
                return;
            case 8:
                CanJni.CCH6CarSetRzc(58, Neg(this.mSetData.Kmyj), 0);
                return;
            case 9:
                CanJni.CCH6CarSetRzc(59, Neg(this.mSetData.Dccxfz), 0);
                return;
            case 10:
                CanJni.CCH6CarSetRzc(60, Neg(this.mSetData.Pljstx), 0);
                return;
            case 11:
                CanJni.CCH6CarSetRzc(61, Neg(this.mSetData.Cybh), 0);
                return;
            case 13:
                CanJni.CCH6CarSetRzc(68, Neg(this.mSetData.Jjzdsss), 0);
                return;
            case 14:
                CanJni.CCH6CarSetRzc(56, Neg(this.mSetData.Csbj), 0);
                return;
            case 16:
                CanJni.CCH6CarSetRzc(65, Neg(this.mSetData.Xhjs), 0);
                return;
            case 17:
                CanJni.CCH6CarSetRzc(63, Neg(this.mSetData.Dsjjzd), 0);
                return;
            case 18:
                CanJni.CCH6CarSetRzc(69, Neg(this.mSetData.Dtfxwdfz), 0);
                return;
            case 19:
                CanJni.CCH6CarSetRzc(70, Neg(this.mSetData.Qjbjkg), 0);
                return;
            case 22:
                CanJni.CCH6CarSetRzc(72, Neg(this.mSetData.Zyyb), 0);
                return;
            case 23:
                CanJni.CCH6CarSetRzc(71, Neg(this.mSetData.Ddct_Tsmssjy), 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_pzaqfzkg, R.string.can_xraqfzkg, R.string.can_yjlmd, R.string.can_jp_cdpljg, R.string.can_psa_wc_cdbcfz, R.string.can_cdjzbc, R.string.can_jtbzxx, R.string.can_bxfz, R.string.can_kmyj, R.string.can_dccxfz, R.string.can_fatigue_driving_tips, R.string.can_cybh, R.string.can_xhms, R.string.can_kaiyi_3x_jjzdss, R.string.can_tigger7_speed_warn, R.string.can_tigger7_speed_value, R.string.can_kaiyi_3x_xhjs, R.string.can_dsjjzd, R.string.can_dtfxwdfz, R.string.can_qjbjkg, R.string.can_df_jyx5_csss, R.string.can_zxms, R.string.can_chair_yinbing, R.string.can_cch9_electricsidepedalsystem, R.string.can_qxdgsjg, R.string.can_xcms, R.string.can_psa_2017_4008_ybbjys};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[2] = new int[]{R.string.can_ac_low, R.string.can_cdpyyzxt_1, R.string.can_cdjg};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 20;
        iArr2[2] = 1;
        iArr[15] = iArr2;
        this.mPopValueIds[12] = new int[]{R.string.can_znxhfz, R.string.can_zsyxh};
        this.mPopValueIds[20] = new int[]{R.array.can_CCH9_auto_speed_lock_array};
        this.mPopValueIds[24] = new int[]{R.array.can_CCH9_auto_speed_lock_array};
        this.mPopValueIds[21] = new int[]{R.string.can_light_qb, R.string.can_comfort, R.string.can_sport};
        this.mPopValueIds[25] = new int[]{R.string.can_sdmd, R.string.can_mingd, R.string.can_shand};
        this.mPopValueIds[26] = new int[]{R.string.can_color_blue, R.string.can_color_gold, R.string.can_color_red};
        this.mSetData = new CanDataInfo.H6CarSet();
    }

    public void ResetData(boolean check) {
        CanJni.CCH6GetCarSetRzc(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Pzanfz);
            updateItem(1, this.mSetData.Xraqfz);
            updateItem(2, this.mSetData.Yjlmd);
            updateItem(3, this.mSetData.Cdplyj);
            updateItem(4, this.mSetData.Cdbcfz);
            updateItem(5, this.mSetData.Cdjzbc);
            updateItem(6, this.mSetData.Jtbzxx);
            updateItem(7, this.mSetData.Bxfz);
            updateItem(8, this.mSetData.Kmyj);
            updateItem(9, this.mSetData.Dccxfz);
            updateItem(10, this.mSetData.Pljstx);
            updateItem(11, this.mSetData.Cybh);
            updateItem(12, this.mSetData.Xhms);
            updateItem(13, this.mSetData.Jjzdsss);
            updateItem(14, this.mSetData.Csbj);
            updateItem(15, this.mSetData.Csbjlmd, String.format("%dkm", new Object[]{Integer.valueOf(this.mSetData.Csbjlmd - 10)}));
            updateItem(16, this.mSetData.Xhjs);
            updateItem(17, this.mSetData.Dsjjzd);
            updateItem(18, this.mSetData.Dtfxwdfz);
            updateItem(19, this.mSetData.Qjbjkg);
            updateItem(20, this.mSetData.Csbs);
            updateItem(21, this.mSetData.Zxms);
            updateItem(22, this.mSetData.Zyyb);
            updateItem(23, this.mSetData.Ddct_Tsmssjy);
            updateItem(24, this.mSetData.Qxtgsjg);
            updateItem(25, this.mSetData.Xcms);
            updateItem(26, this.mSetData.Ybpyssz);
        }
    }

    public void QueryData() {
        CanJni.CCCarQueryRzc(49, 0);
    }
}
