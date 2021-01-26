package com.ts.can.saic.baojun;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBaojunRs3LightSetView extends CanScrollCarInfoView {
    private CanDataInfo.Baojun_Info mInfo;

    public CanBaojunRs3LightSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.BaojunCarSet(13, item + 1);
        } else if (id == 3) {
            CanJni.BaojunCarSet(15, item + 1);
        } else if (id == 4) {
            CanJni.BaojunCarSet(16, item + 1);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.BaojunCarSet(12, swValue(this.mInfo.Bdsd));
                return;
            case 2:
                CanJni.BaojunCarSet(14, swValue(this.mInfo.Bwhj));
                return;
            default:
                return;
        }
    }

    private int swValue(int val) {
        return val == 1 ? 2 : 1;
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_kaiyi_3x_bdsg, R.string.can_sndsc, R.string.can_bwhj, R.string.can_bwhj_light, R.string.can_xunche};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mItemVisibles[3] = 0;
        this.mPopValueIds[1] = new int[]{R.array.can_df_jyx5_jdsj};
        this.mPopValueIds[3] = new int[]{R.string.can_30s, R.string.can_50s, R.string.can_75s};
        this.mPopValueIds[4] = new int[]{R.string.can_shand, R.string.can_sdmd};
        this.mInfo = new CanDataInfo.Baojun_Info();
    }

    public void ResetData(boolean check) {
        CanJni.BaojunGetCarSet(this.mInfo);
        if (!i2b(this.mInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mInfo.Update)) {
            this.mInfo.Update = 0;
            updateItem(new int[]{this.mInfo.Bdsd - 1, this.mInfo.Sndys - 1, this.mInfo.Bwhj - 1, this.mInfo.Bwhjys - 1, this.mInfo.Xc - 1});
            showItem(3, this.mInfo.Bwhj - 1);
        }
    }

    public void QueryData() {
    }
}
