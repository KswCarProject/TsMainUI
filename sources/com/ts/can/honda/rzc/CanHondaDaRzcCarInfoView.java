package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanHondaDaRzcCarInfoView extends CanScrollCarInfoView {
    public static final int ITEM_360PANORAMA_SET = 15;
    public static final int ITEM_AMP_SET = 11;
    public static final int ITEM_CAMERA_SET = 12;
    public static final int ITEM_CAR_SET = 7;
    public static final int ITEM_COMPASS = 1;
    public static final int ITEM_CONSUMPTION = 2;
    public static final int ITEM_DISTANCE = 3;
    public static final int ITEM_DISTANCE_ILL = 4;
    public static final int ITEM_GLLXX = 16;
    public static final int ITEM_JSPZXTSZ = 8;
    public static final int ITEM_LOCK = 5;
    public static final int ITEM_MOTO_R_DOOR = 10;
    public static final int ITEM_QJXYSYSTEM_SET = 14;
    public static final int ITEM_REMOTE_LOCK = 6;
    public static final int ITEM_SYSTEM_SET = 9;
    public static final int ITEM_TYPE = 0;
    public static final int ITEM_YIBIAO_SET = 13;

    public CanHondaDaRzcCarInfoView(Activity activity) {
        super(activity, 17);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        if (id != 0) {
            enterSubWin(CanCarInfoSub1Activity.class, id);
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_compass, R.string.can_consumption, R.string.can_distance_sz, R.string.can_distanceill_sz, R.string.can_cssz, R.string.can_remote_lock, R.string.can_csshez, R.string.can_jsfzxysz, R.string.can_system_set, R.string.can_moto_rear_door, R.string.can_amp_set, R.string.can_camera_status, R.string.can_psa_2017_4008_gxhybsz, R.string.can_honda_qjyxxtsz, R.string.can_360qjsz, R.string.can_gllxx};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.CAR_TYPE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.array.can_fs_declare_288};
        this.mItemVisibles[11] = 0;
    }

    public void ResetData(boolean check) {
        updateItem(0, CanJni.GetSubType());
    }

    public void QueryData() {
    }

    public void doOnResume() {
        updateItem(0, CanJni.GetSubType());
    }
}
