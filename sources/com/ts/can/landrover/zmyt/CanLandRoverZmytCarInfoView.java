package com.ts.can.landrover.zmyt;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.can.CanScrollCarInfoView;

public class CanLandRoverZmytCarInfoView extends CanScrollCarInfoView {
    public static final int ITEM_CAR_INIT = 1;
    public static final int ITEM_FUNC = 0;
    public static final int ITEM_IAP = 2;
    private static final int ITEM_MAX = 2;
    private static final int ITEM_MIN = 0;
    private static int nDisCar = 0;
    private int nTouchTime = 0;

    public CanLandRoverZmytCarInfoView(Activity activity) {
        super(activity, 3);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_func_chos, R.string.can_host_init, R.string.can_can_iap};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.ICON_TOUCH, CanScrollCarInfoView.Item.ICON_TOUCH, CanScrollCarInfoView.Item.ICON_TOUCH};
        this.mItemIcons = new int[]{R.drawable.can_icon_light2, R.drawable.can_icon_service, R.drawable.can_golf_icon03};
        int[] iArr = new int[3];
        iArr[0] = 1;
        this.mItemVisibles = iArr;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public boolean onTouch(View arg0, MotionEvent event) {
        int id = ((Integer) arg0.getTag()).intValue();
        int action = event.getAction();
        if (action != 0) {
            if (action == 1) {
                switch (id) {
                    case 0:
                        if (this.nTouchTime > 0) {
                            this.nTouchTime = 0;
                            enterSubWin(CanCarInfoSub1Activity.class, id);
                            break;
                        }
                        break;
                }
            }
        } else {
            switch (id) {
                case 0:
                    this.nTouchTime = 150;
                    break;
                case 1:
                    CanFunc.showCanActivity(CanCarInfoSub1Activity.class, id);
                    break;
                case 2:
                    enterSubWin(CanCarInfoSub1Activity.class, id);
                    break;
            }
        }
        return false;
    }

    public void ResetData(boolean check) {
        if (this.nTouchTime > 0) {
            this.nTouchTime--;
            if (this.nTouchTime == 0) {
                nDisCar = 1;
                showItem(2, 1);
                showItem(1, 1);
            }
        }
        if (!check && nDisCar > 0) {
            showItem(2, 1);
            showItem(1, 1);
        }
    }

    public void QueryData() {
    }
}
