package com.ts.can.geely.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanGeelyRzcCarAvmView extends CanScrollCarInfoView {
    protected static final int ITEM_DLDCYX = 4;
    protected static final int ITEM_DTGJX = 1;
    protected static final int ITEM_FZSDZX = 5;
    protected static final int ITEM_JTGJX = 0;
    protected static final int ITEM_MAX = 6;
    protected static final int ITEM_MIN = 0;
    protected static final int ITEM_RDTC5SYC = 3;
    protected static final int ITEM_YYJZ = 2;
    private CanDataInfo.Geely_Camera m_AvmInfo;

    public CanGeelyRzcCarAvmView(Activity activity) {
        super(activity, 6);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                if (this.m_AvmInfo.Jtgjx == 0) {
                    CanJni.GeelyCarCameraSet(12);
                    return;
                } else {
                    CanJni.GeelyCarCameraSet(13);
                    return;
                }
            case 1:
                if (this.m_AvmInfo.Dtgjx == 0) {
                    CanJni.GeelyCarCameraSet(14);
                    return;
                } else {
                    CanJni.GeelyCarCameraSet(15);
                    return;
                }
            case 2:
                if (this.m_AvmInfo.Yyjz == 0) {
                    CanJni.GeelyCarCameraSet(16);
                    return;
                } else {
                    CanJni.GeelyCarCameraSet(17);
                    return;
                }
            case 3:
                if (this.m_AvmInfo.Rdtc5syc == 0) {
                    CanJni.GeelyCarCameraSet(18);
                    return;
                } else {
                    CanJni.GeelyCarCameraSet(19);
                    return;
                }
            case 4:
                if (this.m_AvmInfo.Dldcyx == 0) {
                    CanJni.GeelyCarCameraSet(20);
                    return;
                } else {
                    CanJni.GeelyCarCameraSet(21);
                    return;
                }
            case 5:
                if (this.m_AvmInfo.Fzsdzx == 0) {
                    CanJni.GeelyCarCameraSet(22);
                    return;
                } else {
                    CanJni.GeelyCarCameraSet(23);
                    return;
                }
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_geely_boy_jtgjx, R.string.can_geely_boy_dtgjx, R.string.can_geely_boy_yyjz, R.string.can_geely_boy_rdtcyc, R.string.can_geely_boy_dldcyx, R.string.can_geely_boy_fzsdzx};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.CHECK};
        this.m_AvmInfo = new CanDataInfo.Geely_Camera();
    }

    public void ResetData(boolean check) {
        CanJni.GeelyGetCameraSta(this.m_AvmInfo);
        if (!i2b(this.m_AvmInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_AvmInfo.Update)) {
            this.m_AvmInfo.Update = 0;
            updateItem(0, this.m_AvmInfo.Jtgjx);
            updateItem(1, this.m_AvmInfo.Dtgjx);
            updateItem(2, this.m_AvmInfo.Yyjz);
            updateItem(3, this.m_AvmInfo.Rdtc5syc);
            updateItem(4, this.m_AvmInfo.Dldcyx);
            updateItem(5, this.m_AvmInfo.Fzsdzx);
        }
    }

    public void QueryData() {
        CanJni.GeelyCarQuery(64, 0);
    }
}
