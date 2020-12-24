package com.ts.can.chana.wc.cos;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanChanACosCarSetView extends CanScrollCarInfoView {
    private static final int COS_CCYCSJ = 8;
    private static final int COS_CCYKGN = 9;
    private static final int COS_DCHYGFZ = 2;
    private static final int COS_DLBSY = 7;
    private static final int COS_FWDLD = 16;
    private static final int COS_FWDYS = 15;
    private static final int COS_HSJZDZD = 1;
    private static final int COS_JSJCTF = 19;
    private static final int COS_JSZDHQ = 0;
    private static final int COS_KTZGZ = 11;
    private static final int COS_KZTCKG = 21;
    private static final int COS_LYTHZDJS = 18;
    private static final int COS_QZDYC = 4;
    private static final int COS_RJXCD = 17;
    private static final int COS_SKTC = 12;
    private static final int COS_XCZDLS = 6;
    private static final int COS_XHZDJS = 5;
    private static final int COS_YJZX = 3;
    private static final int COS_YKJSMS = 20;
    private static final int COS_YKTC = 13;
    private static final int COS_YLGYTC = 14;
    private static final int COS_YSCATF = 10;
    private CanDataInfo.Cos1WcCarSet mSetAdt;
    private CanDataInfo.Cos1WcCarSet mSetData;

    public CanChanACosCarSetView(Activity activity) {
        super(activity, 22);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                setCos1CarSet(5, item);
                return;
            case 4:
                setCos1CarSet(4, item);
                return;
            case 7:
                setCos1CarSet(13, item);
                return;
            case 8:
                setCos1CarSet(11, item);
                return;
            case 20:
                setCos1CarSet(1, item);
                return;
            case 21:
                setCos1CarSet(25, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 15:
                setCos1CarSet(21, pos);
                return;
            case 16:
                setCos1CarSet(22, pos);
                return;
            default:
                return;
        }
    }

    private void setCos1CarSet(int cmd, int para) {
        CanJni.ChanAWcCos1CarSet(cmd, para);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                setCos1CarSet(8, Neg(this.mSetData.Jszdhq));
                return;
            case 1:
                setCos1CarSet(7, Neg(this.mSetData.Hsjzdzd));
                return;
            case 2:
                setCos1CarSet(6, Neg(this.mSetData.Dchygfz));
                return;
            case 5:
                setCos1CarSet(3, Neg(this.mSetData.Xhzdjs));
                return;
            case 6:
                setCos1CarSet(2, Neg(this.mSetData.Xczdls));
                return;
            case 9:
                setCos1CarSet(15, Neg(this.mSetData.Ccykgn));
                return;
            case 10:
                setCos1CarSet(14, Neg(this.mSetData.Yscatf));
                return;
            case 11:
                setCos1CarSet(9, Neg(this.mSetData.Ktzgz));
                return;
            case 12:
                setCos1CarSet(18, Neg(this.mSetData.Sktc));
                return;
            case 13:
                setCos1CarSet(19, Neg(this.mSetData.Yktc));
                return;
            case 14:
                setCos1CarSet(20, Neg(this.mSetData.Ylgytc));
                return;
            case 17:
                setCos1CarSet(23, Neg(this.mSetData.Rjxcd));
                return;
            case 18:
                setCos1CarSet(24, Neg(this.mSetData.Lythzktzdjdfs));
                return;
            case 19:
                setCos1CarSet(16, Neg(this.mSetData.Jsjctf));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_jszdhq, R.string.can_zdhsjzd, R.string.can_dchygfz, R.string.can_yjzx, R.string.can_tigger7_light_delay, R.string.can_xhzdjs, R.string.can_xczdls, R.string.can_la_audio_set, R.string.can_wind_delay, R.string.can_wind_remote, R.string.can_yscatf, R.string.can_ktzgz, R.string.can_sktc, R.string.can_yktc, R.string.can_ylgytc, R.string.can_fwd_color, R.string.can_fwd_value, R.string.can_rjxcd, R.string.can_lythjfs, R.string.can_jsjctf, R.string.can_ykjs, R.string.can_kztckg};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[3] = new int[]{R.string.can_off, R.string.can_3c, R.string.can_5c, R.string.can_7c};
        this.mPopValueIds[4] = new int[]{R.string.can_off, R.string.can_10s, R.string.can_30s, R.string.can_60s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[8] = new int[]{R.string.can_off, R.string.can_10s, R.string.can_30s, R.string.can_60s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[7] = new int[]{R.string.can_xchmwgbs, R.string.can_xunche, R.string.can_mwgbs, R.string.can_trunk_close};
        this.mPopValueIds[20] = new int[]{R.string.can_door_unlock_key2, R.string.can_door_unlock_key1};
        this.mPopValueIds[21] = new int[]{R.string.can_cch9_seatmemory_key1, R.string.can_cch9_seatmemory_key2};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 6;
        iArr2[2] = 1;
        iArr[15] = iArr2;
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[1] = 6;
        iArr4[2] = 1;
        iArr3[16] = iArr4;
        for (int i = 0; i < this.mItemVisibles.length; i++) {
            this.mItemVisibles[i] = 0;
        }
        this.mItemVisibles[20] = 1;
        this.mItemVisibles[21] = 1;
        this.mSetAdt = new CanDataInfo.Cos1WcCarSet();
        this.mSetData = new CanDataInfo.Cos1WcCarSet();
    }

    public void ResetData(boolean check) {
        CanJni.ChanAWcCos1GetCarSet(this.mSetAdt, 0);
        if (i2b(this.mSetAdt.UpdateOnce) && (!check || i2b(this.mSetAdt.Update))) {
            this.mSetAdt.Update = 0;
            showItem(new int[]{this.mSetAdt.Jszdhq, this.mSetAdt.Hsjzdzd, this.mSetAdt.Dchygfz, this.mSetAdt.Yjzx, this.mSetAdt.Qzdys, this.mSetAdt.Xhzdjs, this.mSetAdt.Xczdls, this.mSetAdt.Dlbsy, this.mSetAdt.Ccycsj, this.mSetAdt.Ccykgn, this.mSetAdt.Yscatf, this.mSetAdt.Ktzgz, this.mSetAdt.Sktc, this.mSetAdt.Yktc, this.mSetAdt.Ylgytc, this.mSetAdt.Fwdys, this.mSetAdt.Fwdld, this.mSetAdt.Rjxcd, this.mSetAdt.Lythzktzdjdfs, this.mSetAdt.Jsjctf, this.mSetAdt.Ykjsms, 1});
        }
        CanJni.ChanAWcCos1GetCarSet(this.mSetData, 1);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Jszdhq, this.mSetData.Hsjzdzd, this.mSetData.Dchygfz, this.mSetData.Yjzx, this.mSetData.Qzdys, this.mSetData.Xhzdjs, this.mSetData.Xczdls, this.mSetData.Dlbsy, this.mSetData.Ccycsj, this.mSetData.Ccykgn, this.mSetData.Yscatf, this.mSetData.Ktzgz, this.mSetData.Sktc, this.mSetData.Yktc, this.mSetData.Ylgytc, this.mSetData.Fwdys, this.mSetData.Fwdld, this.mSetData.Rjxcd, this.mSetData.Lythzktzdjdfs, this.mSetData.Jsjctf, this.mSetData.Ykjsms, -1});
        }
    }

    public void QueryData() {
        CanJni.ChanAWcCos1Query(5, 1, 135);
    }
}
