package com.ts.can.ford.rzc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanFordRzcZnxfSetView extends CanScrollCarInfoView {
    protected static final int ITEM_CNPM25 = 1;
    protected static final int ITEM_ZCXF = 0;
    private CanDataInfo.FordRzcZnxfInfo mData;
    private String[] mPm25Arr;

    public CanFordRzcZnxfSetView(Activity activity) {
        super(activity, 2);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        if (event.getAction() != 0) {
            if (event.getAction() == 1) {
                switch (id) {
                    case 0:
                        CanJni.FordRzcCarSet2(128, 0);
                        break;
                }
            }
        } else {
            switch (id) {
                case 0:
                    CanJni.FordRzcCarSet2(128, 1);
                    break;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_ford_zuocxinfeng, R.string.can_in_pm25};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.ICON_TOUCH, CanScrollCarInfoView.Item.VALUE};
        this.mItemIcons = new int[]{R.drawable.can_icon_light2, R.drawable.can_icon_light2};
        this.mPm25Arr = getStringArray(R.array.can_pm25_arrays);
        this.mData = new CanDataInfo.FordRzcZnxfInfo();
    }

    public void ResetData(boolean check) {
        CanJni.FordRzcGetZnxfInfo(this.mData);
        if (!i2b(this.mData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mData.Update)) {
            this.mData.Update = 0;
            String str = "--";
            if (this.mData.Pm25 <= 35) {
                str = String.format("%d  %s", new Object[]{Integer.valueOf(this.mData.Pm25), this.mPm25Arr[0]});
            } else if (this.mData.Pm25 <= 75) {
                str = String.format("%d  %s", new Object[]{Integer.valueOf(this.mData.Pm25), this.mPm25Arr[1]});
            } else if (this.mData.Pm25 <= 115) {
                str = String.format("%d  %s", new Object[]{Integer.valueOf(this.mData.Pm25), this.mPm25Arr[2]});
            } else if (this.mData.Pm25 <= 150) {
                str = String.format("%d  %s", new Object[]{Integer.valueOf(this.mData.Pm25), this.mPm25Arr[3]});
            } else if (this.mData.Pm25 <= 250) {
                str = String.format("%d  %s", new Object[]{Integer.valueOf(this.mData.Pm25), this.mPm25Arr[4]});
            } else if (this.mData.Pm25 <= 509) {
                str = String.format("%d  %s", new Object[]{Integer.valueOf(this.mData.Pm25), this.mPm25Arr[5]});
            }
            updateItem(1, this.mData.Pm25, str);
        }
    }

    public void QueryData() {
        CanJni.FordQuery(42, 0);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
    }
}
