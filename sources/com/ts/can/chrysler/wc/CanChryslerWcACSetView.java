package com.ts.can.chrysler.wc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanChryslerWcACSetView extends CanScrollCarInfoView {
    private CanDataInfo.ChrWcAirInfo mAirData;

    public CanChryslerWcACSetView(Activity activity) {
        super(activity, 1);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (1 == action) {
                switch (Id) {
                    case 0:
                        CanJni.ChryslerWcAirKey(22, 0);
                        break;
                }
            }
        } else {
            switch (Id) {
                case 0:
                    CanJni.ChryslerWcAirKey(22, 1);
                    break;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_jp_hsjtgj};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH_TOUCH};
        this.mItemIcons = new int[1];
        this.mAirData = new CanDataInfo.ChrWcAirInfo();
    }

    public void ResetData(boolean check) {
        CanJni.ChryslerWcGetAirInfo(this.mAirData);
        if (!i2b(this.mAirData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAirData.Update)) {
            this.mAirData.Update = 0;
            updateItem(0, this.mAirData.Hsjtgj);
        }
    }

    public void QueryData() {
        CanJni.ChryslerWcQuery(1, 49);
    }
}
