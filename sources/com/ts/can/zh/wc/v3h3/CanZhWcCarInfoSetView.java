package com.ts.can.zh.wc.v3h3;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanZhWcCarInfoSetView extends CanScrollCarInfoView {
    private static final int ITEM_DMJS = 3;
    private static final int ITEM_HSJZDZD = 6;
    private static final int ITEM_JSSS = 5;
    private static final int ITEM_LANG = 7;
    private static final int ITEM_LSSS = 4;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_ZDCS = 2;
    private static final int ITEM_ZDJS = 1;
    private static final int ITEM_ZDLS = 0;
    private CanDataInfo.ZhWcSetData mAdtData;
    private CanDataInfo.ZhWcSetData mSetData;

    public CanZhWcCarInfoSetView(Activity activity) {
        super(activity, 8);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 7:
                CanJni.ZhWcCarLangSet(1, item + 1);
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
                CanJni.ZhWcCarSet(1, Neg(this.mSetData.Zdls));
                return;
            case 1:
                CanJni.ZhWcCarSet(2, Neg(this.mSetData.Zdjs));
                return;
            case 2:
                CanJni.ZhWcCarSet(3, Neg(this.mSetData.Zdcs));
                return;
            case 3:
                CanJni.ZhWcCarSet(4, Neg(this.mSetData.Dmjs));
                return;
            case 4:
                CanJni.ZhWcCarSet(5, Neg(this.mSetData.Lsss));
                return;
            case 5:
                CanJni.ZhWcCarSet(6, Neg(this.mSetData.Jsss));
                return;
            case 6:
                CanJni.ZhWcCarSet(7, Neg(this.mSetData.Hsjzdzd));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_tigger7_auto_lock, R.string.can_zdjs, R.string.can_auto_lock_key, R.string.can_lock_dmjs, R.string.can_lock_lsss, R.string.can_lock_jsss, R.string.can_zdhsjzd, R.string.can_lang_set};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[7] = new int[]{R.string.lang_en_uk, R.string.lang_cn};
        this.mItemVisibles[7] = 1;
        this.mAdtData = new CanDataInfo.ZhWcSetData();
        this.mSetData = new CanDataInfo.ZhWcSetData();
    }

    public void ResetData(boolean check) {
        CanJni.ZhWcGetCarSet(this.mAdtData, 0);
        if (i2b(this.mAdtData.UpdateOnce) && (!check || i2b(this.mAdtData.Update))) {
            this.mAdtData.Update = 0;
            showItem(new int[]{this.mAdtData.Zdls, this.mAdtData.Zdjs, this.mAdtData.Zdcs, this.mAdtData.Dmjs, this.mAdtData.Lsss, this.mAdtData.Jsss, this.mAdtData.Hsjzdzd, 1});
        }
        CanJni.ZhWcGetCarSet(this.mSetData, 1);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Zdls, this.mSetData.Zdjs, this.mSetData.Zdcs, this.mSetData.Dmjs, this.mSetData.Lsss, this.mSetData.Jsss, this.mSetData.Hsjzdzd});
        }
    }

    public void QueryData() {
        CanJni.ZhWcQuery(5, 1, 120);
    }
}
