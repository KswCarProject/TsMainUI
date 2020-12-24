package com.ts.can.df.ax7;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanDFCarSetView extends CanScrollCarInfoView {
    private static final int ITEM_BWHJ = 2;
    private static final int ITEM_CSYXFZ = 11;
    private static final int ITEM_CYMS = 19;
    private static final int ITEM_DDWMGBZDLS = 28;
    private static final int ITEM_DDWMGN = 27;
    private static final int ITEM_DDWYCGBSJ = 30;
    private static final int ITEM_DPHJQD = 6;
    private static final int ITEM_FWDKG = 24;
    private static final int ITEM_FWDLD = 26;
    private static final int ITEM_FWDYS = 25;
    private static final int ITEM_GXYB_L = 21;
    private static final int ITEM_GXYB_R = 22;
    private static final int ITEM_HHBB = 18;
    private static final int ITEM_JTGYDDWM = 29;
    private static final int ITEM_KSJWMS = 14;
    private static final int ITEM_LANG = 13;
    private static final int ITEM_LCHZDLS = 9;
    private static final int ITEM_MAX = 31;
    private static final int ITEM_MDJK = 7;
    private static final int ITEM_PMJHGN = 10;
    private static final int ITEM_QXPZYJ = 5;
    private static final int ITEM_TCMS = 17;
    private static final int ITEM_WMMS = 16;
    private static final int ITEM_XCZDLS = 3;
    private static final int ITEM_XYZDGC = 8;
    private static final int ITEM_YBLD = 23;
    private static final int ITEM_YJWN = 15;
    private static final int ITEM_YXMS = 20;
    private static final int ITEM_YYKZTC = 12;
    private static final int ITEM_ZDJJZD = 4;
    private CanDataInfo.FengshenCarSet mSetData;
    private CanDataInfo.FengshenCarSet2 mSetData2;

    public CanDFCarSetView(Activity activity) {
        super(activity, 31);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.DfFengShenRzcCarSet2(1, item);
                return;
            case 13:
                CanJni.DfFengShenRzcCarSet2(13, item);
                return;
            case 21:
                CanJni.DfFengShenRzcCarSet2(14, item - 1);
                return;
            case 22:
                CanJni.DfFengShenRzcCarSet2(15, item - 1);
                return;
            case 25:
                CanJni.DfFengShenRzcCarSet2(25, item + 1);
                return;
            case 30:
                CanJni.DfFengShenRzcCarSet2(30, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 23:
                CanJni.DfFengShenRzcCarSet2(23, pos);
                return;
            case 26:
                CanJni.DfFengShenRzcCarSet2(26, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.DfFengShenRzcCarSet(0, Neg(this.mSetData.Zxkgzdkqcshm));
        } else if (id == 1) {
            CanJni.DfFengShenRzcCarSet(1, Neg(this.mSetData.Mqjc));
        }
        switch (id) {
            case 3:
                CanJni.DfFengShenRzcCarSet2(2, Neg(this.mSetData2.Xczdls));
                return;
            case 4:
                CanJni.DfFengShenRzcCarSet2(3, Neg(this.mSetData2.Zdjjzd));
                return;
            case 5:
                CanJni.DfFengShenRzcCarSet2(4, Neg(this.mSetData2.Qxpzyj));
                return;
            case 6:
                CanJni.DfFengShenRzcCarSet2(5, Neg(this.mSetData2.Dphjqd));
                return;
            case 7:
                CanJni.DfFengShenRzcCarSet2(6, Neg(this.mSetData2.Mdjk));
                return;
            case 8:
                CanJni.DfFengShenRzcCarSet2(7, Neg(this.mSetData2.Xyzdgc));
                return;
            case 9:
                CanJni.DfFengShenRzcCarSet2(8, Neg(this.mSetData2.Lchzdls));
                return;
            case 10:
                CanJni.DfFengShenRzcCarSet2(9, Neg(this.mSetData2.PMjhgn));
                return;
            case 11:
                CanJni.DfFengShenRzcCarSet2(11, Neg(this.mSetData2.Csyxfz));
                return;
            case 12:
                CanJni.DfFengShenRzcCarSet2(10, Neg(this.mSetData2.Yykztc));
                return;
            case 14:
                CanJni.DfFengShenRzcCarSet2(16, Neg(this.mSetData2.Ksjwms));
                return;
            case 15:
                CanJni.DfFengShenRzcCarSet2(17, Neg(this.mSetData2.Yjwn));
                return;
            case 16:
                CanJni.DfFengShenRzcCarSet2(18, Neg(this.mSetData2.Wmms));
                return;
            case 17:
                CanJni.DfFengShenRzcCarSet2(19, Neg(this.mSetData2.Tcms));
                return;
            case 18:
                CanJni.DfFengShenRzcCarSet2(20, Neg(this.mSetData2.Hhbb));
                return;
            case 19:
                CanJni.DfFengShenRzcCarSet2(21, Neg(this.mSetData2.Cyms));
                return;
            case 20:
                CanJni.DfFengShenRzcCarSet2(22, Neg(this.mSetData2.Fxms));
                return;
            case 24:
                CanJni.DfFengShenRzcCarSet2(24, Neg(this.mSetData2.Fwdkg));
                return;
            case 27:
                CanJni.DfFengShenRzcCarSet2(27, Neg(this.mSetData2.Ddwmgnkg));
                return;
            case 28:
                CanJni.DfFengShenRzcCarSet2(28, Neg(this.mSetData2.Ddwmgbhzdls));
                return;
            case 29:
                CanJni.DfFengShenRzcCarSet2(29, Neg(this.mSetData2.Jtgyddwm));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        int i;
        this.mItemTitleIds = new int[]{R.string.can_zxkgzdkqcshm, R.string.can_blind_spot_monitoring, R.string.can_bwhj, R.string.can_xczdls, R.string.can_zdjjzdxt, R.string.can_qpzyj, R.string.can_teramont_dphj_system, R.string.can_jp_mdjb, R.string.can_xyzdgc, R.string.can_lczdls, R.string.can_pm_25_jhgn, R.string.can_csyxfz, R.string.can_yykztc, R.string.can_language, R.string.can_ksjw, R.string.can_yjwn, R.string.can_wmms, R.string.can_tcms, R.string.can_hhbb, R.string.can_smoking_mode, R.string.can_yxms, R.string.can_gxhybpsz_left, R.string.can_gxhybpsz_right, R.string.can_ybld, R.string.can_hant_fwd, R.string.can_fwd_color, R.string.can_fwd_value, R.string.can_ddwmbj, R.string.can_ddwmgbzdls, R.string.can_jtgyddwm, R.string.can_ddwmycgbsj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        for (int i2 = 0; i2 < this.mItemVisibles.length; i2++) {
            if (i2 < 2) {
                int[] iArr = this.mItemVisibles;
                if (CanJni.GetSubType() == 5) {
                    i = 0;
                } else {
                    i = 1;
                }
                iArr[i2] = i;
            } else {
                this.mItemVisibles[i2] = CanJni.GetSubType() == 5 ? 1 : 0;
            }
        }
        this.mPopValueIds[2] = new int[]{R.string.can_15s, R.string.can_30s, R.string.can_time_45s, R.string.can_60s};
        this.mPopValueIds[13] = new int[]{R.string.can_lang_cn, R.string.can_lang_en};
        this.mPopValueIds[21] = new int[]{R.string.can_invalid, R.string.can_rpm, R.string.can_gps, R.string.can_out_temp};
        this.mPopValueIds[22] = new int[]{R.string.can_invalid, R.string.title_activity_meter, R.string.can_gps, R.string.can_out_temp};
        this.mProgressAttrs[23] = new int[]{1, 6, 1, 1};
        this.mPopValueIds[25] = new int[]{R.string.can_color_red, R.string.can_color_pink, R.string.can_color_gold, R.string.can_orange_color, R.string.can_color_blue, R.string.can_magoten_light_color_4, R.string.can_magoten_light_color_2};
        this.mProgressAttrs[26] = new int[]{1, 10, 1, 1};
        this.mPopValueIds[30] = new int[]{R.string.can_10s, R.string.can_15s, R.string.can_20s, R.string.can_30s};
        this.mSetData = new CanDataInfo.FengshenCarSet();
        this.mSetData2 = new CanDataInfo.FengshenCarSet2();
    }

    public void ResetData(boolean check) {
        CanJni.DfFengShenRzcGetCarSet(this.mSetData);
        if (i2b(this.mSetData.UpdateOnce) && (!check || i2b(this.mSetData.Update))) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Zxkgzdkqcshm);
            updateItem(1, this.mSetData.Mqjc);
        }
        CanJni.DfFengShenRzcGetCarSet2(this.mSetData2);
        if (!i2b(this.mSetData2.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData2.Update)) {
            this.mSetData2.Update = 0;
            updateItem(2, this.mSetData2.Bwhj);
            updateItem(3, this.mSetData2.Xczdls);
            updateItem(4, this.mSetData2.Zdjjzd);
            updateItem(5, this.mSetData2.Qxpzyj);
            updateItem(6, this.mSetData2.Dphjqd);
            updateItem(7, this.mSetData2.Mdjk);
            updateItem(8, this.mSetData2.Xyzdgc);
            updateItem(9, this.mSetData2.Lchzdls);
            updateItem(10, this.mSetData2.PMjhgn);
            updateItem(11, this.mSetData2.Csyxfz);
            updateItem(12, this.mSetData2.Yykztc);
            updateItem(13, this.mSetData2.Lang);
            updateItem(14, this.mSetData2.Ksjwms);
            updateItem(15, this.mSetData2.Yjwn);
            updateItem(16, this.mSetData2.Wmms);
            updateItem(17, this.mSetData2.Tcms);
            updateItem(18, this.mSetData2.Hhbb);
            updateItem(19, this.mSetData2.Cyms);
            updateItem(20, this.mSetData2.Fxms);
            String level = getString(R.string.can_level);
            updateItem(21, this.mSetData2.Gxyb_l);
            updateItem(22, this.mSetData2.Gxyb_r);
            updateItem(23, this.mSetData2.Ybld, String.valueOf(level) + " " + this.mSetData2.Ybld);
            updateItem(24, this.mSetData2.Fwdkg);
            updateItem(25, this.mSetData2.Fwdys - 1);
            updateItem(26, this.mSetData2.Fwdld, String.valueOf(level) + " " + this.mSetData2.Fwdld);
            updateItem(27, this.mSetData2.Ddwmgnkg);
            updateItem(28, this.mSetData2.Ddwmgbhzdls);
            updateItem(29, this.mSetData2.Jtgyddwm);
            updateItem(30, this.mSetData2.Ddwmycgbsj - 1);
        }
    }

    public void QueryData() {
        if (CanJni.GetSubType() == 5) {
            CanJni.DfFengShenRzcQuery(41);
        } else {
            CanJni.DfFengShenRzcQuery(39);
        }
    }
}
