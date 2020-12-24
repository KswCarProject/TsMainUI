package com.ts.can.honda.bnr;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;

public class CanHondaBnrCarDrivAssistSetView extends CanScrollCarInfoView {
    public static final int ITEM_ACCQCTCTSY = 3;
    public static final int ITEM_CDPLFXTXSD = 6;
    public static final int ITEM_FDJJNZDQTXS = 2;
    public static final int ITEM_JTBZSBXT = 8;
    public static final int ITEM_MAX = 10;
    public static final int ITEM_RZXQHSXT = 0;
    public static final int ITEM_SDQFWXJGJL = 5;
    public static final int ITEM_YKQTXTKQ = 7;
    public static final int ITEM_YYBJXTYL = 1;
    public static final int ITEM_ZNYSQTZNTS = 9;
    public static final int ITEM_ZTLKASTSY = 4;
    private static int mRCamerab = 255;
    private CanDataInfo.AccordSetData mAssistData;

    public CanHondaBnrCarDrivAssistSetView(Activity activity) {
        super(activity, 10);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.AccordCarCtrl(30, item);
                return;
            case 5:
                CanJni.AccordCarCtrl(31, item);
                return;
            case 6:
                CanJni.AccordCarCtrl(34, item);
                return;
            case 8:
                CanJni.AccordCarCtrl(Can.CAN_ZH_H530, item);
                return;
            case 9:
                CanJni.AccordCarCtrl(Can.CAN_BENZ_SMART_OD, item);
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
            case 2:
                CanJni.AccordCarCtrl(29, Neg(this.mAssistData.Fdjjnzdqtxs));
                return;
            case 3:
                CanJni.AccordCarCtrl(32, Neg(this.mAssistData.AccQctztsy));
                return;
            case 4:
                CanJni.AccordCarCtrl(33, Neg(this.mAssistData.ZtLkasTsy));
                return;
            case 7:
                CanJni.AccordCarCtrl(24, Neg(this.mAssistData.Ykqdxt));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_rzxqhsxt, R.string.can_yybjxtyl, R.string.can_fdjjnzdqtxs, R.string.can_accqctztsy, R.string.can_ztlkastsy, R.string.can_sdqfwxjgjl, R.string.can_cdplfxxtsd, R.string.can_remotestartsystem, R.string.can_jtbssbxt, R.string.can_znysqdznxs};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[1] = new int[]{R.string.can_yybjxtyl_1, R.string.can_yybjxtyl_2};
        this.mPopValueIds[5] = new int[]{R.string.can_sdqfwxjgjl_1, R.string.can_sdqfwxjgjl_2, R.string.can_sdqfwxjgjl_3};
        this.mPopValueIds[6] = new int[]{R.string.can_cdplfxxtsd_1, R.string.can_cdplfxxtsd_2, R.string.can_cdplfxxtsd_3};
        this.mPopValueIds[8] = new int[]{R.string.can_off, R.string.can_on};
        this.mPopValueIds[9] = new int[]{R.string.can_off, R.string.can_on};
        this.mAssistData = new CanDataInfo.AccordSetData();
    }

    public void ResetData(boolean check) {
        CanJni.AccordGetSetData(this.mAssistData);
        if (i2b(this.mAssistData.UpdateOnce) && (!check || i2b(this.mAssistData.Update))) {
            this.mAssistData.Update = 0;
            updateItem(1, this.mAssistData.Yybjxtyl);
            updateItem(2, this.mAssistData.Fdjjnzdqtxs);
            updateItem(3, this.mAssistData.AccQctztsy);
            updateItem(4, this.mAssistData.ZtLkasTsy);
            updateItem(5, this.mAssistData.Sdqfwxjgjl);
            updateItem(6, this.mAssistData.Cdplfxxtsz);
            updateItem(7, this.mAssistData.Ykqdxt);
        }
        if (mRCamerab != FtSet.Getlgb4()) {
            mRCamerab = FtSet.Getlgb4();
            updateItem(0, mRCamerab);
        }
    }

    public void QueryData() {
        CanJni.AccordQuery(50, 0);
    }
}
