package com.ts.can.gm.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMWcSetCDSView extends CanScrollCarInfoView {
    private CanDataInfo.GmWc_CheckData mCheckAdt;
    private CanDataInfo.GmWc_CheckData2 mCheckAdt2;
    private CanDataInfo.GmWc_CheckData mCheckData;
    private CanDataInfo.GmWc_CheckData2 mCheckData2;

    public CanGMWcSetCDSView(Activity activity) {
        super(activity, 11);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.GmWcCarCheckSet(4, item);
                return;
            case 3:
                CanJni.GmWcCarCheckSet(3, item);
                return;
            case 5:
                CanJni.GmWcCarCheckSet(6, item);
                return;
            case 7:
                CanJni.GmWcCarCheckSet(8, item);
                return;
            case 9:
                CanJni.GmWcCarCheckSet(10, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.GmWcCarCheckSet(1, Neg(this.mCheckData.Bcfzxt));
                return;
            case 1:
                CanJni.GmWcCarCheckSet(2, Neg(this.mCheckData.Cmqbjxt));
                return;
            case 4:
                CanJni.GmWcCarCheckSet(5, Neg(this.mCheckData.Radar24Ghz));
                return;
            case 6:
                CanJni.GmWcCarCheckSet(7, Neg(this.mCheckData2.Qczttz));
                return;
            case 8:
                CanJni.GmWcCarCheckSet(9, Neg(this.mCheckData2.Zsyxhqdtx));
                return;
            case 10:
                CanJni.GmWcCarCheckSet(11, Neg(this.mCheckData2.Ydzx));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_bcfz, R.string.can_cymqbj, R.string.can_fzkblx, R.string.can_bcfz_dtcbc, R.string.can_24ghz_radar, R.string.can_zdfzzb, R.string.can_qczttz, R.string.can_teramont_pdqbfz, R.string.can_gl8_2017_zsyxhzdtx, R.string.can_qzxrjc, R.string.can_sport_turn};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[2] = new int[]{R.string.can_zmgj, R.string.can_magoten_chair};
        this.mPopValueIds[3] = new int[]{R.string.can_off, R.string.can_on, R.string.can_tcbc};
        this.mPopValueIds[5] = new int[]{R.string.can_off, R.string.can_baojing, R.string.can_bjhzd};
        this.mPopValueIds[7] = new int[]{R.string.can_bzbc, R.string.can_zqbc};
        this.mPopValueIds[9] = new int[]{R.string.can_off, R.string.can_baojing, R.string.can_bjhzd};
        for (int i = 0; i < this.mItemVisibles.length; i++) {
            this.mItemVisibles[i] = 0;
        }
        this.mCheckAdt = new CanDataInfo.GmWc_CheckData();
        this.mCheckData = new CanDataInfo.GmWc_CheckData();
        this.mCheckAdt2 = new CanDataInfo.GmWc_CheckData2();
        this.mCheckData2 = new CanDataInfo.GmWc_CheckData2();
    }

    public void ResetData(boolean check) {
        CanJni.GmWcGetCarCheckSetAdt(this.mCheckAdt);
        CanJni.GmWcGetCarCheckSet(this.mCheckData);
        if (i2b(this.mCheckAdt.UpdateOnce) && (!check || i2b(this.mCheckAdt.Update))) {
            this.mCheckAdt.Update = 0;
            int[] values = {this.mCheckAdt.Bcfzxt, this.mCheckAdt.Cmqbjxt, this.mCheckAdt.Fzbjlx, this.mCheckAdt.BcfzxtTcbc, this.mCheckAdt.Radar24Ghz, this.mCheckAdt.Zdfzzb};
            for (int i = 0; i < values.length; i++) {
                showItem(i, values[i]);
            }
        }
        if (i2b(this.mCheckData.UpdateOnce) && (!check || i2b(this.mCheckData.Update))) {
            this.mCheckData.Update = 0;
            int[] values2 = {this.mCheckData.Bcfzxt, this.mCheckData.Cmqbjxt, this.mCheckData.Fzbjlx, this.mCheckData.BcfzxtTcbc, this.mCheckData.Radar24Ghz, this.mCheckData.Zdfzzb};
            for (int i2 = 0; i2 < values2.length; i2++) {
                updateItem(i2, values2[i2]);
            }
        }
        CanJni.GmWcGetCarCheckSet2Adt(this.mCheckAdt2);
        CanJni.GmWcGetCarCheckSet2(this.mCheckData2);
        if (i2b(this.mCheckAdt2.UpdateOnce) && (!check || i2b(this.mCheckAdt2.Update))) {
            this.mCheckAdt2.Update = 0;
            int[] values3 = {this.mCheckAdt2.Qczttz, this.mCheckAdt2.Pdqbfz, this.mCheckAdt2.Zsyxhqdtx, this.mCheckAdt2.Qzxrjc, this.mCheckAdt2.Ydzx};
            for (int i3 = 0; i3 < values3.length; i3++) {
                showItem(i3 + 6, values3[i3]);
            }
        }
        if (!i2b(this.mCheckData2.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCheckData2.Update)) {
            this.mCheckData2.Update = 0;
            int[] values4 = {this.mCheckData2.Qczttz, this.mCheckData2.Pdqbfz, this.mCheckData2.Zsyxhqdtx, this.mCheckData2.Qzxrjc, this.mCheckData2.Ydzx};
            for (int i4 = 0; i4 < values4.length; i4++) {
                updateItem(i4 + 6, values4[i4]);
            }
        }
    }

    public void QueryData() {
        CanJni.GmWcCarQuery(5, 1, 69);
        Sleep(5);
        CanJni.GmWcCarQuery(5, 1, 70);
    }
}
