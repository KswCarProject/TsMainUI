package com.ts.can.cc.dj.hf;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemTextBtnList;

public class CanHfDjCarSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick, CanItemMsgBox.onMsgBoxClick2 {
    private static final int ITEM_RESET_SET = 15;
    private CanDataInfo.CcHfDj_CarSet mCarSet;

    public CanHfDjCarSetView(Activity activity) {
        super(activity, 16);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.CcHfDjCarSet(0, item);
                return;
            case 1:
                CanJni.CcHfDjCarSet(1, item);
                return;
            case 2:
                CanJni.CcHfDjCarSet(2, item);
                return;
            case 3:
                CanJni.CcHfDjCarSet(3, item);
                return;
            case 4:
                CanJni.CcHfDjCarSet(4, item);
                return;
            case 5:
                CanJni.CcHfDjCarSet(5, item);
                return;
            case 6:
                CanJni.CcHfDjCarSet(6, item);
                return;
            case 7:
                CanJni.CcHfDjCarSet(7, item);
                return;
            case 8:
                CanJni.CcHfDjCarSet(8, item);
                return;
            case 10:
                CanJni.CcHfDjCarSet(10, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 9:
                CanJni.CcHfDjCarSet(9, Neg(this.mCarSet.Zyznjygn));
                return;
            case 11:
                CanJni.CcHfDjCarSet(11, Neg(this.mCarSet.Ddctxt));
                return;
            case 12:
                CanJni.CcHfDjCarSet(12, Neg(this.mCarSet.Cdms));
                return;
            case 13:
                CanJni.CcHfDjCarSet(13, Neg(this.mCarSet.Qdxfg));
                return;
            case 14:
                CanJni.CcHfDjCarSet(14, Neg(this.mCarSet.Pljsts));
                return;
            case 15:
                new CanItemMsgBox(15, getActivity(), R.string.can_sure_setup, this, this);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_cch6_dingdeng_delaytime, R.string.can_cch6_gensuihuijia_delaytime, R.string.can_cch6_jiedian_setup, R.string.can_h6_coupe_ylgxcgq, R.string.can_cch9_headlightmode, R.string.can_anti_theft_settings, R.string.can_cch9_parkingsettings, R.string.can_rearview_mirror_settings, R.string.can_cch9_gatingsettings, R.string.can_cch9_seatmemory, R.string.can_psa_wc_lang_setup, R.string.can_cch9_electricsidepedalsystem, R.string.can_cch9_roofmode, R.string.can_all_terrain_coverage, R.string.can_fatigue_driving_tips, R.string.can_reset_set};
        int subType = CanJni.GetSubType();
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.TITLE};
        if (subType == 0) {
            this.mPopValueIds[0] = new int[]{R.array.can_cch6_dd_delay_array};
        } else {
            this.mPopValueIds[0] = new int[]{R.string.can_10s, R.string.can_30s, R.string.can_1min, R.string.can_3min};
        }
        this.mPopValueIds[1] = new int[]{R.string.can_30s, R.string.can_1min, R.string.can_2min, R.string.can_3min};
        this.mPopValueIds[2] = new int[]{R.array.can_df_jyx5_jdsj};
        this.mPopValueIds[3] = new int[]{R.string.can_h6_coupe_oz_mode, R.string.can_h6_coupe_yz_mode};
        this.mPopValueIds[4] = new int[]{R.string.can_normal_mode, R.string.can_temporary_mode};
        this.mPopValueIds[5] = new int[]{R.string.can_trunk_close, R.string.can_mzd_cx4_time_10min, R.string.can_mzd_cx4_time_20min, R.string.can_mzd_cx4_time_30min};
        this.mPopValueIds[6] = new int[]{R.string.can_zdjh, R.string.can_sdjh};
        this.mPopValueIds[7] = new int[]{R.string.can_zdzd, R.string.can_sdzd};
        this.mPopValueIds[8] = new int[]{R.string.can_cch9_gatingsettings_key1, R.string.can_cch9_gatingsettings_key2};
        this.mPopValueIds[10] = new int[]{R.string.can_language_chinese, R.string.can_language_english};
        this.mCarSet = new CanDataInfo.CcHfDj_CarSet();
    }

    public void ResetData(boolean check) {
        CanJni.CcHfDjGetCarData(this.mCarSet);
        if (!i2b(this.mCarSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSet.Update)) {
            this.mCarSet.Update = 0;
            updateItem(0, this.mCarSet.Ddys);
            updateItem(1, this.mCarSet.Gshjyssj);
            updateItem(2, this.mCarSet.Jdsz);
            updateItem(3, this.mCarSet.Ylgxcgqsz);
            updateItem(4, this.mCarSet.Qdmssz);
            updateItem(5, this.mCarSet.Fdsz);
            updateItem(6, this.mCarSet.Bcsz);
            updateItem(7, this.mCarSet.Hsjsz);
            updateItem(8, this.mCarSet.Mksz);
            updateItem(9, this.mCarSet.Zyznjygn);
            updateItem(10, this.mCarSet.Lang);
            updateItem(11, this.mCarSet.Ddctxt);
            updateItem(12, this.mCarSet.Cdms);
            updateItem(13, this.mCarSet.Qdxfg);
            updateItem(14, this.mCarSet.Pljsts);
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        CanItemTextBtnList mCanItemTextBtnList = (CanItemTextBtnList) this.mItemObjects[15];
        mCanItemTextBtnList.GetView().setTag(15);
        mCanItemTextBtnList.GetView().setOnClickListener(this);
    }

    public void QueryData() {
        CanJni.CcHfDjQuery(4);
    }

    public void onCancel(int param) {
    }

    public void onOK(int param) {
        if (param == 15) {
            CanJni.CcHfDjCarSet(255, 0);
        }
    }
}
