package com.ts.can.chana.wc.os;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanChanaWcCarSetView extends CanScrollCarInfoView {
    public static final int ITEM_BDDSGCS = 1;
    public static final int ITEM_CCDGN = 6;
    public static final int ITEM_CCYCSJ = 7;
    public static final int ITEM_CCYKGN = 9;
    public static final int ITEM_DLBSY = 5;
    public static final int ITEM_GSHJDG = 2;
    public static final int ITEM_JBSFKFS = 8;
    public static final int ITEM_MAX = 12;
    public static final int ITEM_XCZKZDLS = 4;
    public static final int ITEM_YSBSTF = 11;
    public static final int ITEM_YSCATF = 10;
    public static final int ITEM_YSJSTF = 0;
    public static final int ITEM_ZCZZDJS = 3;
    private CanDataInfo.ChanAWcSetData mSetAdt;
    private CanDataInfo.ChanAWcSetData mSetData;

    public CanChanaWcCarSetView(Activity activity) {
        super(activity, 12);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.ChanAWcCarSet(5, item);
                return;
            case 2:
                CanJni.ChanAWcCarSet(4, item);
                return;
            case 5:
                CanJni.ChanAWcCarSet(13, item);
                return;
            case 7:
                CanJni.ChanAWcCarSet(11, item);
                return;
            case 8:
                CanJni.ChanAWcCarSet(10, item);
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
                CanJni.ChanAWcCarSet(8, Neg(this.mSetData.Ysjstf));
                return;
            case 3:
                CanJni.ChanAWcCarSet(3, Neg(this.mSetData.Zczzdjs));
                return;
            case 4:
                CanJni.ChanAWcCarSet(2, Neg(this.mSetData.Xczkzdls));
                return;
            case 6:
                CanJni.ChanAWcCarSet(12, Neg(this.mSetData.Ccdgn));
                return;
            case 9:
                CanJni.ChanAWcCarSet(15, Neg(this.mSetData.Ccykgn));
                return;
            case 10:
                CanJni.ChanAWcCarSet(14, Neg(this.mSetData.Yscatfsz));
                return;
            case 11:
                CanJni.ChanAWcCarSet(9, Neg(this.mSetData.Ysbstf));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ysjstf, R.string.can_bddsgcs, R.string.can_cch6_gensuihuijia_delaytime, R.string.can_zczzdjs, R.string.can_xczdls, R.string.can_la_audio_set, R.string.can_sidewind_light, R.string.can_wind_delay, R.string.can_lock_feedback, R.string.can_wind_remote, R.string.can_yscatf, R.string.can_ysbstf};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[1] = new int[]{R.string.can_off, R.string.can_3c, R.string.can_5c, R.string.can_7c};
        this.mPopValueIds[2] = new int[]{R.string.can_off, R.string.can_10s, R.string.can_30s, R.string.can_60s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[5] = new int[]{R.string.can_xchmwgbs, R.string.can_xunche, R.string.can_mwgbs};
        this.mPopValueIds[7] = new int[]{R.string.can_off, R.string.can_10s, R.string.can_30s, R.string.can_60s, R.string.can_mzd_cx4_time_120s};
        this.mPopValueIds[8] = new int[]{R.string.can_dghlb, R.string.can_only_light, R.string.can_only_lb};
        for (int i = 0; i < this.mItemVisibles.length; i++) {
            this.mItemVisibles[i] = 0;
        }
        this.mSetAdt = new CanDataInfo.ChanAWcSetData();
        this.mSetData = new CanDataInfo.ChanAWcSetData();
    }

    public void ResetData(boolean check) {
        CanJni.ChanAWcGetCarAdtSet(this.mSetAdt);
        if (i2b(this.mSetAdt.UpdateOnce) && (!check || i2b(this.mSetAdt.Update))) {
            this.mSetAdt.Update = 0;
            showItem(new int[]{this.mSetAdt.Ysjstf, this.mSetAdt.Bddsgcs, this.mSetAdt.Gshjdg, this.mSetAdt.Zczzdjs, this.mSetAdt.Xczkzdls, this.mSetAdt.Dlbsy, this.mSetAdt.Ccdgn, this.mSetAdt.Ccycsj, this.mSetAdt.Jbsfkfs, this.mSetAdt.Ccykgn, this.mSetAdt.Yscatfsz, this.mSetAdt.Ysbstf});
        }
        CanJni.ChanAWcGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Ysjstf, this.mSetData.Bddsgcs, this.mSetData.Gshjdg, this.mSetData.Zczzdjs, this.mSetData.Xczkzdls, this.mSetData.Dlbsy, this.mSetData.Ccdgn, this.mSetData.Ccycsj, this.mSetData.Jbsfkfs, this.mSetData.Ccykgn, this.mSetData.Yscatfsz, this.mSetData.Ysbstf});
        }
    }

    public void QueryData() {
        CanJni.ChanAWcQuery(5, 1, 135);
    }
}
