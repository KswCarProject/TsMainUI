package com.ts.can.porsche.od;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanPorscheOdSetView extends CanScrollCarInfoView {
    public static final int ITEM_DCHCZYL = 12;
    public static final int ITEM_DCHSJJD = 10;
    public static final int ITEM_HYS = 15;
    public static final int ITEM_RXD = 14;
    public static final int ITEM_ZYKTLQQ = 13;
    public static final int ITEM_ZYXCD = 11;
    private CanDataInfo.PorscheCarSet mData;

    public CanPorscheOdSetView(Activity activity) {
        super(activity, 16);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 4:
                CanJni.PorscheOdCarSet(9, item);
                return;
            case 5:
                CanJni.PorscheOdCarSet(10, item);
                return;
            case 8:
                CanJni.PorscheOdCarSet(13, item);
                return;
            case 9:
                CanJni.PorscheOdCarSet(14, item);
                return;
            case 11:
                CanJni.PorscheOdCarSet(16, item);
                return;
            case 15:
                CanJni.PorscheOdCarSet(20, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 1:
                CanJni.PorscheOdCarSet(6, pos);
                return;
            case 2:
                CanJni.PorscheOdCarSet(7, pos);
                return;
            case 3:
                CanJni.PorscheOdCarSet(8, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.PorscheOdCarSet(5, Neg(this.mData.Jcfz));
                return;
            case 6:
                CanJni.PorscheOdCarSet(11, Neg(this.mData.Tfq));
                return;
            case 7:
                CanJni.PorscheOdCarSet(12, Neg(this.mData.Zdxhkq));
                return;
            case 10:
                CanJni.PorscheOdCarSet(15, Neg(this.mData.Dchsjjd));
                return;
            case 12:
                CanJni.PorscheOdCarSet(17, Neg(this.mData.Dchczyl));
                return;
            case 13:
                CanJni.PorscheOdCarSet(18, Neg(this.mData.Zyktlqq));
                return;
            case 14:
                CanJni.PorscheOdCarSet(19, Neg(this.mData.Rxd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_jcfz, R.string.can_outcarlightoftime, R.string.can_cndwdxmsj, R.string.can_cndwdld, R.string.can_ylcgq, R.string.can_wkms, R.string.can_tfq, R.string.can_auto_recirculate, R.string.can_psa_wc_cmjs, R.string.can_zdcmsd, R.string.can_dchsjzdqx, R.string.can_zyxcd, R.string.can_dchczyl, R.string.can_zyktlqq, R.string.can_light_rxd, R.string.can_rear_wiper};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[0] = 1;
        iArr2[1] = 12;
        iArr2[2] = 1;
        iArr[1] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 30;
        iArr4[2] = 1;
        iArr3[2] = iArr4;
        int[][] iArr5 = this.mProgressAttrs;
        int[] iArr6 = new int[4];
        iArr6[1] = 10;
        iArr6[2] = 1;
        iArr5[3] = iArr6;
        this.mPopValueIds[4] = new int[]{R.string.can_tripbresettiming_wc_4, R.string.can_auto_ylgyq};
        this.mPopValueIds[5] = new int[]{R.string.can_normal, R.string.can_rouruan, R.string.can_qiangjing};
        this.mPopValueIds[8] = new int[]{R.string.can_sym, R.string.can_jsym};
        this.mPopValueIds[9] = new int[]{R.string.can_mzd_cx4_mode_off, R.string.can_after_ignition_on, R.string.can_qibuhou};
        this.mPopValueIds[11] = new int[]{R.string.can_mode_normal, R.string.can_reverse};
        this.mPopValueIds[15] = new int[]{R.string.can_soudong, R.string.can_mzd_cx4_drive_auto};
        this.mData = new CanDataInfo.PorscheCarSet();
    }

    public void ResetData(boolean check) {
        CanJni.PorscheOdGetCarSet(this.mData);
        if (i2b(this.mData.JcfzUpdateOnce) && (!check || i2b(this.mData.JcfzUpdate))) {
            this.mData.JcfzUpdate = 0;
            updateItem(0, this.mData.Jcfz);
        }
        if (i2b(this.mData.CwdxmsjUpdateOnce) && (!check || i2b(this.mData.CwdxmsjUpdate))) {
            this.mData.CwdxmsjUpdate = 0;
            updateItem(1, this.mData.Cwdxmsj, String.valueOf(this.mData.Cwdxmsj * 10) + " S");
        }
        if (i2b(this.mData.CndwdxmsjUpdateOnce) && (!check || i2b(this.mData.CndwdxmsjUpdate))) {
            this.mData.CndwdxmsjUpdate = 0;
            updateItem(2, this.mData.Cndwdxmsj, String.valueOf(this.mData.Cndwdxmsj * 10) + " S");
        }
        if (i2b(this.mData.CndwdldUpdateOnce) && (!check || i2b(this.mData.CndwdldUpdate))) {
            this.mData.CndwdldUpdate = 0;
            updateItem(3, this.mData.Cndwdld, new StringBuilder(String.valueOf(this.mData.Cndwdld)).toString());
        }
        if (i2b(this.mData.YlgyqUpdateOnce) && (!check || i2b(this.mData.YlgyqUpdate))) {
            this.mData.YlgyqUpdate = 0;
            updateItem(4, this.mData.Ylgyq);
        }
        if (i2b(this.mData.WkmsUpdateOnce) && (!check || i2b(this.mData.WkmsUpdate))) {
            this.mData.WkmsUpdate = 0;
            updateItem(5, this.mData.Wkms);
        }
        if (i2b(this.mData.TfqUpdateOnce) && (!check || i2b(this.mData.TfqUpdate))) {
            this.mData.TfqUpdate = 0;
            updateItem(6, this.mData.Tfq);
        }
        if (i2b(this.mData.ZdxhkqUpdateOnce) && (!check || i2b(this.mData.ZdxhkqUpdate))) {
            this.mData.ZdxhkqUpdate = 0;
            updateItem(7, this.mData.Zdxhkq);
        }
        if (i2b(this.mData.CmjsUpdateOnce) && (!check || i2b(this.mData.CmjsUpdate))) {
            this.mData.CmjsUpdate = 0;
            updateItem(8, this.mData.Cmjs);
        }
        if (i2b(this.mData.CmsdUpdateOnce) && (!check || i2b(this.mData.CmsdUpdate))) {
            this.mData.CmsdUpdate = 0;
            updateItem(9, this.mData.Cmsd);
        }
        if (i2b(this.mData.DchsjjdUpdateOnce) && (!check || i2b(this.mData.DchsjjdUpdate))) {
            this.mData.DchsjjdUpdate = 0;
            updateItem(10, this.mData.Dchsjjd);
        }
        if (i2b(this.mData.ZyxcdUpdateOnce) && (!check || i2b(this.mData.ZyxcdUpdate))) {
            this.mData.ZyxcdUpdate = 0;
            updateItem(11, this.mData.Zyxcd);
        }
        if (i2b(this.mData.DchczylUpdateOnce) && (!check || i2b(this.mData.DchczylUpdate))) {
            this.mData.DchczylUpdate = 0;
            updateItem(12, this.mData.Dchczyl);
        }
        if (i2b(this.mData.ZyktlqqUpdateOnce) && (!check || i2b(this.mData.ZyktlqqUpdate))) {
            this.mData.ZyktlqqUpdate = 0;
            updateItem(13, this.mData.Zyktlqq);
        }
        if (i2b(this.mData.RxdUpdateOnce) && (!check || i2b(this.mData.RxdUpdate))) {
            this.mData.RxdUpdate = 0;
            updateItem(14, this.mData.Rxd);
        }
        if (!i2b(this.mData.HygUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mData.HygUpdate)) {
            this.mData.HygUpdate = 0;
            updateItem(15, this.mData.Hyg);
        }
    }

    public void QueryData() {
        CanJni.PorscheOdQuery(64, 6);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 7);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 8);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 9);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 10);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 11);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 12);
        Sleep(5);
        CanJni.PorscheOdQuery(64, 13);
    }
}
