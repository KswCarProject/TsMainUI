package com.ts.can.hyundai.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHyundaiRzcNyxxView extends CanScrollCarInfoView {
    private static final int ITEM_DL = 0;
    private static final int ITEM_DLBFB = 3;
    private static final int ITEM_KXSZLC = 2;
    private static final int ITEM_QY = 1;
    private static final int ITEM_YJCDSXSJ_BXCD = 5;
    private static final int ITEM_YJCDSXSJ_ZCCD = 4;
    private CanDataInfo.HyRzcXnySet3 mSetData;

    public CanHyundaiRzcNyxxView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_dl, R.string.can_qy, R.string.can_kxszlc, R.string.can_dlbfb, R.string.can_yjcdsxsj_zccd, R.string.can_yjcdsxsj_bxcd};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mSetData = new CanDataInfo.HyRzcXnySet3();
    }

    public void ResetData(boolean check) {
        CanJni.HyundaiRzcGetXnySet3(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Dl, String.format("%dKM", new Object[]{Integer.valueOf(this.mSetData.Dl)}));
            updateItem(1, this.mSetData.Qy, String.format("%dKM", new Object[]{Integer.valueOf(this.mSetData.Qy)}));
            updateItem(2, this.mSetData.Kxszlc, String.format("%dKM", new Object[]{Integer.valueOf(this.mSetData.Kxszlc)}));
            updateItem(3, this.mSetData.Dlbfb, String.format("%d %%", new Object[]{Integer.valueOf(this.mSetData.Dlbfb)}));
            updateItem(4, this.mSetData.Yjcdsxsj_Zccd, String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mSetData.Yjcdsxsj_Zccd / 60), Integer.valueOf(this.mSetData.Yjcdsxsj_Zccd % 60)}));
            updateItem(5, this.mSetData.Yjcdsxsj_Bxcd, String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mSetData.Yjcdsxsj_Bxcd / 60), Integer.valueOf(this.mSetData.Yjcdsxsj_Bxcd % 60)}));
        }
    }

    public void QueryData() {
        CanJni.HyundaiRzcXnySet(255, 1, 0, 0);
        Sleep(10);
        CanJni.HyundaiRzcQuery(85, 0);
    }
}
