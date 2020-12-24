package com.ts.can.benc.withcd;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanBencWithCDCarSetView extends CanScrollCarInfoView {
    private CanDataInfo.CanBcZmytLed mSetData;

    public CanBencWithCDCarSetView(Activity activity) {
        super(activity, 3);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View arg0) {
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int Id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (1 == action) {
                switch (Id) {
                    case 0:
                        CanJni.BencZmytWithCDSendKey(1, 0);
                        break;
                    case 1:
                        CanJni.BencZmytWithCDSendKey(2, 0);
                        break;
                    case 2:
                        CanJni.BencZmytWithCDSendKey(3, 0);
                        break;
                }
            }
        } else {
            switch (Id) {
                case 0:
                    CanJni.BencZmytWithCDSendKey(1, 1);
                    break;
                case 1:
                    CanJni.BencZmytWithCDSendKey(2, 1);
                    break;
                case 2:
                    CanJni.BencZmytWithCDSendKey(3, 1);
                    break;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_p_off_led, R.string.can_sport_led, R.string.can_car_up_led};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.SWITCH_TOUCH, CanScrollCarInfoView.Item.SWITCH_TOUCH, CanScrollCarInfoView.Item.SWITCH_TOUCH};
        this.mItemIcons = new int[]{R.drawable.can_icon_off, R.drawable.can_icon_sport, R.drawable.can_icon_car};
        this.mSetData = new CanDataInfo.CanBcZmytLed();
    }

    public void ResetData(boolean check) {
        CanJni.BencZmytWithCDCarLedInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Poff, this.mSetData.Sport, this.mSetData.CarUp});
        }
    }

    public void QueryData() {
        CanJni.BencZmytWithCDQuery(1);
    }
}
