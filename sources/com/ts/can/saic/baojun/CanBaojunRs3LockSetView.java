package com.ts.can.saic.baojun;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBaojunRs3LockSetView extends CanScrollCarInfoView {
    private CanDataInfo.Baojun_Info mInfo;

    public CanBaojunRs3LockSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.BaojunCarSet(8, item + 1);
        } else if (id == 1) {
            CanJni.BaojunCarSet(9, item + 1);
        } else if (id == 2) {
            CanJni.BaojunCarSet(10, item + 1);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int i;
        if (((Integer) v.getTag()).intValue() == 3) {
            if (this.mInfo.Xczdls == 1) {
                i = 2;
            } else {
                i = 1;
            }
            CanJni.BaojunCarSet(11, i);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ykjs, R.string.can_xhzdjs, R.string.can_lock_notice, R.string.can_xczdls};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_psa_wc_jsmjs, R.string.can_psa_wc_symjs};
        this.mPopValueIds[1] = new int[]{R.string.can_trunk_close, R.string.can_psa_wc_jsmjs, R.string.can_psa_wc_symjs};
        this.mPopValueIds[2] = new int[]{R.string.can_shand, R.string.can_sdmd};
        this.mInfo = new CanDataInfo.Baojun_Info();
    }

    public void ResetData(boolean check) {
        CanJni.BaojunGetCarSet(this.mInfo);
        if (!i2b(this.mInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mInfo.Update)) {
            this.mInfo.Update = 0;
            updateItem(new int[]{this.mInfo.YkZdjrsjscm - 1, this.mInfo.Xhzdjscm - 1, this.mInfo.Scts - 1, this.mInfo.Xczdls - 1});
        }
    }

    public void QueryData() {
    }
}
