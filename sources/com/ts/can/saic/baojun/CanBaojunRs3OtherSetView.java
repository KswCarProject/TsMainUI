package com.ts.can.saic.baojun;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.txznet.sdk.TXZResourceManager;

public class CanBaojunRs3OtherSetView extends CanScrollCarInfoView {
    private CanDataInfo.Baojun_Info mInfo;

    public CanBaojunRs3OtherSetView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
        if (id == 4) {
            CanJni.BaojunCarSet(20, item + 1);
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 2) {
            CanJni.BaojunCarSet(18, pos);
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id == 0) {
            CanJni.BaojunCarSet(7, swValue(this.mInfo.Ykjc));
        } else if (id == 1) {
            CanJni.BaojunCarSet(17, swValue(this.mInfo.Ygwxgn));
        } else if (id == 3) {
            CanJni.BaojunCarSet(19, swValue(this.mInfo.Fpzyj));
        } else if (id == 5) {
            CanJni.BaojunCarSet(21, swValue(this.mInfo.Zdjjzdxt));
        }
    }

    private int swValue(int val) {
        return val == 1 ? 2 : 1;
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_carset_ykjc, R.string.can_ygwxgn, R.string.can_zsyxhmrsdsz, R.string.can_fpzyj, R.string.can_fpzyjlmd, R.string.can_zdjjzdxt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mItemVisibles[4] = 0;
        this.mProgressAttrs[2] = new int[]{1, 11, 1, 1};
        this.mPopValueIds[4] = new int[]{R.string.can_ac_high, R.string.can_ac_mid, R.string.can_ac_low};
        this.mInfo = new CanDataInfo.Baojun_Info();
    }

    public void ResetData(boolean check) {
        CanJni.BaojunGetCarSet(this.mInfo);
        if (!i2b(this.mInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mInfo.Update)) {
            this.mInfo.Update = 0;
            updateItem(new int[]{this.mInfo.Ykjc - 1, this.mInfo.Ygwxgn - 1, this.mInfo.Zsyxhmrsdsz, this.mInfo.Fpzyj - 1, this.mInfo.Fpzyjlmd - 1, this.mInfo.Zdjjzdxt - 1});
            showItem(4, this.mInfo.Fpzyj - 1);
            updateItem(2, this.mInfo.Zsyxhmrsdsz, getSpeedStr(this.mInfo.Zsyxhmrsdsz));
        }
    }

    private String getSpeedStr(int val) {
        if (val < 1 || val > 11) {
            return TXZResourceManager.STYLE_DEFAULT;
        }
        return String.valueOf(((val - 1) * 5) + 30) + " KM/H";
    }

    public void QueryData() {
    }
}
