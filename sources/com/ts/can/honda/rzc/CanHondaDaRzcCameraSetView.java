package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;

public class CanHondaDaRzcCameraSetView extends CanScrollCarInfoView {
    public static final int ITEM_CAMERA_STA = 0;
    public static final int ITEM_RIGHTCAMERA_STA = 1;
    protected int nCamerSta = 255;
    protected int nRightCamerSta = 255;

    public CanHondaDaRzcCameraSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                if (FtSet.Getlgb5() > 0) {
                    FtSet.Setlgb5(0);
                    return;
                } else {
                    FtSet.Setlgb5(1);
                    return;
                }
            case 1:
                FtSet.Setlgb4(Neg(FtSet.Getlgb4()));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_sxtqhzt, R.string.can_has_right_camera};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH};
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }

    public void ResetData(boolean check) {
        if (this.nCamerSta != FtSet.Getlgb5()) {
            this.nCamerSta = FtSet.Getlgb5();
            updateItem(0, this.nCamerSta);
        }
        if (this.nRightCamerSta != FtSet.Getlgb4()) {
            this.nRightCamerSta = FtSet.Getlgb4();
            updateItem(1, this.nRightCamerSta);
        }
    }

    public void QueryData() {
    }
}
