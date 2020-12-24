package com.ts.can.honda.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHondaWcRemoteLockSetSetView extends CanScrollCarInfoView {
    private CanDataInfo.HondaWcRemoteSet mRemoteData;

    public CanHondaWcRemoteLockSetSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.HondaWcCarRemoteSet(2, item);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.HondaWcCarRemoteSet(1, Neg(this.mRemoteData.Ykqdxt));
                return;
            case 2:
                CanJni.HondaWcCarRemoteSet(3, Neg(this.mRemoteData.Ykmscbdts));
                return;
            case 3:
                CanJni.HondaWcCarRemoteSet(4, Neg(this.mRemoteData.Ykmsfmqts));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_remotesystem, R.string.can_yybjxtyl, R.string.can_keylessaccesslightflash, R.string.can_keylessaccessbeep};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[1] = new int[]{R.string.can_yybjxtyl_1, R.string.can_yybjxtyl_2};
        this.mRemoteData = new CanDataInfo.HondaWcRemoteSet();
    }

    public void ResetData(boolean check) {
        CanJni.HondaWcGetRemoteSet(this.mRemoteData);
        if (!i2b(this.mRemoteData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mRemoteData.Update)) {
            this.mRemoteData.Update = 0;
            updateItem(0, this.mRemoteData.Ykqdxt);
            updateItem(1, this.mRemoteData.Yybjxtyl);
            updateItem(2, this.mRemoteData.Ykmscbdts);
            updateItem(3, this.mRemoteData.Ykmsfmqts);
        }
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, 102);
    }
}
