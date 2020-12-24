package com.ts.can.cc.wc.h6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;

public class CanWcH6CarSetView extends CanScrollCarInfoView {
    private static final int ITEM_BXFZ = 9;
    private static final int ITEM_CDPLYJ = 3;
    private static final int ITEM_DCCXFZ = 10;
    private static final int ITEM_FZHSJ = 1;
    private static final int ITEM_ISRCAMERA = 0;
    private static final int ITEM_JTBZXX = 4;
    private static final int ITEM_KMYJ = 11;
    private static final int ITEM_PLJSTX = 5;
    private static final int ITEM_PZAQFZKG = 6;
    private static final int ITEM_XRAQFZKG = 7;
    private static final int ITEM_YBYSSZ = 12;
    private static final int ITEM_YJLMD = 8;
    private static final int ITEM_ZNQT = 2;
    private int mCameraStab = 255;
    private CanDataInfo.CcH6WcSet mSet;

    public CanWcH6CarSetView(Activity activity) {
        super(activity, 13);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CarSet(0, item);
                return;
            case 8:
                CarSet(11, item);
                return;
            case 12:
                CarSet(17, item);
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
                if (FtSet.Getlgb4() == 0) {
                    FtSet.Setlgb4(1);
                    return;
                } else {
                    FtSet.Setlgb4(0);
                    return;
                }
            case 2:
                CarSet(1, Neg(this.mSet.Znqt));
                return;
            case 3:
                CarSet(12, Neg(this.mSet.Cdplyj));
                return;
            case 4:
                CarSet(13, Neg(this.mSet.Jtbzxx));
                return;
            case 5:
                CarSet(8, Neg(this.mSet.Pljstx));
                return;
            case 6:
                CarSet(9, Neg(this.mSet.Pzaqfzkg));
                return;
            case 7:
                CarSet(10, Neg(this.mSet.Xraqfzkg));
                return;
            case 9:
                CarSet(14, Neg(this.mSet.Bxfz));
                return;
            case 10:
                CarSet(15, Neg(this.mSet.Dccxfz));
                return;
            case 11:
                CarSet(16, Neg(this.mSet.Kmyj));
                return;
            default:
                return;
        }
    }

    private void CarSet(int cmd, int para) {
        CanJni.CcH6WcCarSet(cmd, para, 255, 255);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_has_right_camera, R.string.can_fzhsj, R.string.can_smart_qt, R.string.can_jp_cdpljg, R.string.can_jtbzxx, R.string.can_fatigue_driving_tips, R.string.can_pzaqfzkg, R.string.can_xraqfzkg, R.string.can_yjlmd, R.string.can_bxfz, R.string.can_dccxfz, R.string.can_kmyj, R.string.can_yb_color};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_soudong, R.string.can_mzd_cx4_drive_auto};
        this.mPopValueIds[8] = new int[]{R.string.can_sensitivity_low, R.string.can_mode_normal, R.string.can_sensitivity_high};
        this.mPopValueIds[12] = new int[]{R.string.can_magoten_light_color_3, R.string.can_color_red, R.string.can_color_gold};
        this.mSet = new CanDataInfo.CcH6WcSet();
    }

    public void ResetData(boolean check) {
        CanJni.CcH6WcGetCarSet(this.mSet);
        if (i2b(this.mSet.UpdateOnce) && (!check || i2b(this.mSet.Update))) {
            this.mSet.Update = 0;
            updateItem(1, this.mSet.Hsjzd);
            updateItem(2, this.mSet.Znqt);
            updateItem(3, this.mSet.Cdplyj);
            updateItem(4, this.mSet.Jtbzxx);
            updateItem(5, this.mSet.Pljstx);
            updateItem(6, this.mSet.Pzaqfzkg);
            updateItem(7, this.mSet.Xraqfzkg);
            updateItem(8, this.mSet.Yjlmd);
            updateItem(9, this.mSet.Bxfz);
            updateItem(10, this.mSet.Dccxfz);
            updateItem(11, this.mSet.Kmyj);
            updateItem(12, this.mSet.Ybyssz);
        }
        if (this.mCameraStab != FtSet.Getlgb4() || !check) {
            this.mCameraStab = FtSet.Getlgb4();
            updateItem(0, this.mCameraStab);
        }
    }

    public void QueryData() {
    }
}
