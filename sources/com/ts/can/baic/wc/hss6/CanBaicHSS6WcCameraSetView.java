package com.ts.can.baic.wc.hss6;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBaicHSS6WcCameraSetView extends CanScrollCarInfoView {
    public CanBaicHSS6WcCameraSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.BaicWcS6CameraSet(10, 1);
                return;
            case 1:
                CanJni.BaicWcS6CameraSet(10, 2);
                return;
            case 2:
                CanJni.BaicWcS6CameraSet(10, 3);
                return;
            case 3:
                CanJni.BaicWcS6CameraSet(10, 4);
                return;
            case 4:
                CanJni.BaicWcS6CameraSet(10, 5);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_camera_qsxt, R.string.can_camera_hsxt, R.string.can_camera_zsxt, R.string.can_camera_ysxt, R.string.can_camera_qjsxt};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE, CanScrollCarInfoView.Item.TITLE};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
