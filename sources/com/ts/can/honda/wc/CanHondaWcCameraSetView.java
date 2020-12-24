package com.ts.can.honda.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;

public class CanHondaWcCameraSetView extends CanScrollCarInfoView {
    private static final int HONDA_DCCWKD = 7;
    private static final int HONDA_DCSFXSSXT = 6;
    private static final int HONDA_DSSSXTZDXS = 13;
    private static final int HONDA_DTYDX = 5;
    private static final int HONDA_HSDTTXXTSZ = 3;
    private static final int HONDA_ISRCAMERA = 0;
    private static final int HONDA_JTYDX = 4;
    private static final int HONDA_SXJQDHDDCHM = 10;
    private static final int HONDA_SZLKJSQ = 9;
    private static final int HONDA_XSZMRDSXTMS = 11;
    private static final int HONDA_YSTCMSSD = 12;
    private static final int HONDA_YZGBHXSCXSJ = 8;
    private static final int HONDA_YZXDLD = 1;
    private static final int HONDA_ZXDGBHXSCXSJ = 2;
    private CanDataInfo.HondaWcCameraSta mCameraData;

    public CanHondaWcCameraSetView(Activity activity) {
        super(activity, 14);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.HondaWcCameraSet(12, item + 4);
                return;
            case 7:
                CanJni.HondaWcCameraSet(13, item + 6);
                return;
            case 8:
                CanJni.HondaWcCameraSet(8, item);
                return;
            case 10:
                CanJni.HondaWcCameraSet(19, item);
                return;
            case 11:
                CanJni.HondaWcCameraSet(18, item);
                return;
            case 12:
                CanJni.HondaWcCameraSet(17, item);
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
                FtSet.Setlgb4(Neg(FtSet.Getlgb4()));
                return;
            case 1:
                CanJni.HondaWcCameraSet(12, Neg(this.mCameraData.LaneWatchYzxld));
                return;
            case 3:
                CanJni.HondaWcCameraSet(14, Neg(this.mCameraData.DynTx));
                return;
            case 4:
                CanJni.HondaWcCameraSet(13, Neg(this.mCameraData.StaticLine));
                return;
            case 5:
                CanJni.HondaWcCameraSet(13, Neg(this.mCameraData.DynLine) + 2);
                return;
            case 6:
                CanJni.HondaWcCameraSet(13, Neg(this.mCameraData.RvsCamera) + 4);
                return;
            case 9:
                CanJni.HondaWcCameraSet(20, Neg(this.mCameraData.Szlkjsq));
                return;
            case 13:
                CanJni.HondaWcCameraSet(16, Neg(this.mCameraData.Dsssxtzdxs));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_has_right_camera, R.string.can_yzxdld, R.string.can_zxdgbhxscxsj, R.string.can_hsdttxxtsz, R.string.can_jtydx, R.string.can_dtydx, R.string.can_dchsxt, R.string.can_dcrkcwkd, R.string.can_yzxgbhxscxsj, R.string.can_szlkjsq, R.string.can_sxjqdhddchm, R.string.can_xszmrdsxtms, R.string.can_ystcmssd, R.string.can_dsssxtzdxs};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[2] = new int[]{R.string.can_headlightautoofftime_0s, R.string.can_zxdgbhxscxsj_2s};
        this.mPopValueIds[7] = new int[]{R.string.can_dcrkcwkd_z, R.string.can_dcrkcwkd_k};
        this.mPopValueIds[8] = new int[]{R.string.can_headlightautoofftime_0s, R.string.can_zxdgbhxscxsj_2s};
        this.mPopValueIds[10] = new int[]{R.string.can_hptsj_qj, R.string.can_prepm, R.string.can_hksj, R.string.can_hptsj};
        this.mPopValueIds[11] = new int[]{R.string.can_qptsj_qj, R.string.can_prepm, R.string.can_qksj, R.string.can_qlbsj};
        this.mPopValueIds[12] = new int[]{R.string.can_fhtc, R.string.can_pxtc};
        this.mCameraData = new CanDataInfo.HondaWcCameraSta();
        if (4 != CanJni.GetSubType() && 5 != CanJni.GetSubType()) {
            this.mItemVisibles[3] = 0;
            this.mItemVisibles[4] = 0;
            this.mItemVisibles[5] = 0;
            this.mItemVisibles[6] = 0;
            this.mItemVisibles[7] = 0;
        }
    }

    public void ResetData(boolean check) {
        CanJni.HondaWcGetCameraSta(this.mCameraData);
        if (i2b(this.mCameraData.UpdateOnce) && (!check || i2b(this.mCameraData.Update))) {
            this.mCameraData.Update = 0;
            updateItem(1, this.mCameraData.LaneWatchYzxld);
            updateItem(2, this.mCameraData.LaneWatchTime);
            updateItem(3, this.mCameraData.DynTx);
            updateItem(4, this.mCameraData.StaticLine);
            updateItem(5, this.mCameraData.DynLine);
            updateItem(6, this.mCameraData.RvsCamera);
            updateItem(7, this.mCameraData.RvsRkkd);
            updateItem(9, this.mCameraData.Szlkjsq);
            updateItem(10, this.mCameraData.Sxjqdhddchm);
            updateItem(11, this.mCameraData.Xszmrdsxtms);
            updateItem(12, this.mCameraData.Ystcmssd);
            updateItem(13, this.mCameraData.Dsssxtzdxs);
        }
        updateItem(0, FtSet.Getlgb4());
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, Can.CAN_FLAT_WC);
    }
}
