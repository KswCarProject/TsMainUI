package com.ts.can.gm.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGMSetLockView extends CanScrollCarInfoView {
    public static final int ITEM_CMJS = 6;
    public static final int ITEM_CXYKSZDKDM = 7;
    public static final int ITEM_FZKMZDLS = 0;
    public static final int ITEM_JCZDJS = 8;
    public static final int ITEM_JSDGFK = 4;
    public static final int ITEM_LCZDLS = 10;
    public static final int ITEM_LSYS = 1;
    private static final int ITEM_MAX = 13;
    private static final int ITEM_MIN = 0;
    public static final int ITEM_QBZDLS = 2;
    public static final int ITEM_SMDGLBFK = 5;
    public static final int ITEM_YKCCKZ = 11;
    public static final int ITEM_YKQDCL = 13;
    public static final int ITEM_YKZSM = 12;
    public static final int ITEM_YSYWTX = 9;
    public static final int ITEM_ZCZDJS = 3;
    private static final int[] mCmjsArr = {R.string.can_jsym, R.string.can_sym};
    private static final int[] mJczdjsArr = {R.string.can_sym, R.string.can_jsym};
    private static final int[] mLczdlsArr = {R.string.can_off, R.string.can_on, R.string.can_lbwjsqy};
    private static final int[] mSmdglbfkArr = {R.string.can_only_light, R.string.can_dghlb, R.string.can_only_lb, R.string.can_off};
    private static final int[] mZczdjs2Arr = {R.string.can_off, R.string.can_sym};
    private static final int[] mZczdjsArr = {R.string.can_off, R.string.can_jsym, R.string.can_sym};
    protected CanDataInfo.GM_CarSet mSetData = new CanDataInfo.GM_CarSet();

    public CanGMSetLockView(Activity activity) {
        super(activity, 14);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_fzkmzdll, R.string.can_ysll, R.string.can_qbzzll, R.string.can_zczdjs, R.string.can_ykjsdgfk, R.string.can_ykdglbfk, R.string.can_ykjssz, R.string.can_cxszdkdm, R.string.can_jczdjs, R.string.can_ysywtx, R.string.can_lczdls, R.string.can_ykcckz, R.string.can_gl8_2017_ykzsm, R.string.can_ykqdcl};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK};
        this.mPopValueIds[3] = mZczdjsArr;
        this.mPopValueIds[5] = mSmdglbfkArr;
        this.mPopValueIds[6] = mCmjsArr;
        this.mPopValueIds[8] = mJczdjsArr;
        this.mPopValueIds[10] = mLczdlsArr;
    }

    public void ResetData(boolean check) {
        GetSetData();
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.FZKMZDLS);
            updateItem(1, this.mSetData.YSLS);
            updateItem(2, this.mSetData.QBZDLS);
            updateItem(3, this.mSetData.ZCZDJS);
            updateItem(4, this.mSetData.YKJSDGFK);
            updateItem(5, this.mSetData.YKSMDGLBFK);
            updateItem(6, this.mSetData.YKJS);
            updateItem(7, this.mSetData.AutoRelock);
            updateItem(8, this.mSetData.JCJS);
            updateItem(9, this.mSetData.YSYWTX);
            updateItem(10, this.mSetData.LCZDLS);
            updateItem(11, this.mSetData.YKCCKZ);
            updateItem(12, this.mSetData.Ykzsm);
            updateItem(13, this.mSetData.Ykqdcl);
        }
    }

    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void GetSetData() {
        CanJni.GMGetCarSet(this.mSetData);
    }

    /* access modifiers changed from: protected */
    public void QuerySetData() {
        if (this.mSetData.UpdateOnce == 0) {
            CanJni.GMQuery(6);
        }
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
        CanJni.GMCarCtrl(cmd, para);
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CarSet(2, Neg(this.mSetData.FZKMZDLS));
                return;
            case 1:
                CarSet(5, Neg(this.mSetData.YSLS));
                return;
            case 2:
                CarSet(3, Neg(this.mSetData.QBZDLS));
                return;
            case 4:
                CarSet(6, Neg(this.mSetData.YKJSDGFK));
                return;
            case 7:
                CarSet(15, Neg(this.mSetData.AutoRelock));
                return;
            case 9:
                CarSet(13, Neg(this.mSetData.YSYWTX));
                return;
            case 11:
                CarSet(27, Neg(this.mSetData.YKCCKZ));
                return;
            case 12:
                CarSet(10, Neg(this.mSetData.Ykzsm));
                return;
            case 13:
                CarSet(11, Neg(this.mSetData.Ykqdcl));
                return;
            default:
                return;
        }
    }

    public void onItem(int Id, int item) {
        switch (Id) {
            case 3:
                CarSet(4, item);
                return;
            case 5:
                CarSet(7, item);
                return;
            case 6:
                CarSet(8, item);
                return;
            case 8:
                CarSet(12, item);
                return;
            case 10:
                CarSet(23, item);
                return;
            default:
                return;
        }
    }
}
