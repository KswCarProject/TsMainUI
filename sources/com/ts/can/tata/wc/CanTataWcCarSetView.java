package com.ts.can.tata.wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanTataWcCarSetView extends CanScrollCarInfoView {
    public static final int ITEM_APP_LAMPS = 5;
    public static final int ITEM_DOOR_UNLOCKING = 3;
    public static final int ITEM_MAX = 11;
    public static final int ITEM_MOOD_LIGHT_COLOR = 9;
    public static final int ITEM_MOOD_LIGHT_FRONT = 7;
    public static final int ITEM_MOOD_LIGHT_LUMIN = 10;
    public static final int ITEM_MOOD_LIGHT_MODE = 6;
    public static final int ITEM_MOOD_LIGHT_REAR = 8;
    public static final int ITEM_PARK_ASSIST_DELAY_TIMER = 1;
    public static final int ITEM_PARK_ASSIST_TONE = 2;
    public static final int ITEM_PARK_ASSIST_VOL = 0;
    public static final int ITEM_VEH_AUTO_RELOCK = 4;
    private CanDataInfo.TaTaWc_SetData mSetData;

    public CanTataWcCarSetView(Activity activity) {
        super(activity, 11);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CanJni.TataWcCarSet(2, item, 255, 255);
                return;
            case 2:
                CanJni.TataWcCarSet(3, item, 255, 255);
                return;
            case 3:
                CanJni.TataWcCarSet(4, item, 255, 255);
                return;
            case 5:
                CanJni.TataWcCarSet(6, item, 255, 255);
                return;
            case 6:
                CanJni.TataWcCarSet(7, item, 255, 255);
                return;
            case 9:
                CanJni.TataWcCarSet(10, item, 255, 255);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.TataWcCarSet(1, pos, 255, 255);
                return;
            case 10:
                CanJni.TataWcCarSet(11, pos, 255, 255);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 4:
                CanJni.TataWcCarSet(5, Neg(this.mSetData.VehAutoRelock), 255, 255);
                return;
            case 7:
                CanJni.TataWcCarSet(8, Neg(this.mSetData.MoodLightFront), 255, 255);
                return;
            case 8:
                CanJni.TataWcCarSet(9, Neg(this.mSetData.MoodLightRear), 255, 255);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_park_assist_vol, R.string.can_park_assist_delay_timer, R.string.can_park_assist_tone, R.string.can_door_unlocking, R.string.can_vehicle_auto_relock, R.string.can_approarch_lamps, R.string.can_mood_lighting_mode, R.string.can_mood_lighting_front, R.string.can_mood_lighting_rear, R.string.can_mood_lighting_color, R.string.can_mood_lighting_Lumin};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 15;
        iArr2[2] = 1;
        iArr[0] = iArr2;
        this.mPopValueIds[1] = new int[]{R.string.can_0s, R.string.can_5s, R.string.can_10s};
        this.mPopValueIds[2] = new int[]{R.string.can_tone1, R.string.can_tone2, R.string.can_tone3};
        this.mPopValueIds[3] = new int[]{R.string.can_sym, R.string.can_jsym};
        this.mPopValueIds[5] = new int[]{R.string.can_30s, R.string.can_60s, R.string.can_90s};
        this.mPopValueIds[6] = new int[]{R.string.can_off, R.string.can_on, R.string.can_gl8_2017_ktycqd_auto};
        this.mPopValueIds[9] = new int[]{R.string.can_magoten_light_color_4, R.string.can_color_red, R.string.can_color_blue, R.string.can_orange_color, R.string.can_purple, R.string.can_gray, R.string.can_magoten_light_color_2, R.string.can_qingse};
        int[][] iArr3 = this.mProgressAttrs;
        int[] iArr4 = new int[4];
        iArr4[0] = 1;
        iArr4[1] = 5;
        iArr4[2] = 1;
        iArr3[10] = iArr4;
        this.mSetData = new CanDataInfo.TaTaWc_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.TataWcGetSetData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.ParkAssVol, " " + this.mSetData.ParkAssVol);
            updateItem(1, this.mSetData.ParkAssDelayTime);
            updateItem(2, this.mSetData.ParkAssTone);
            updateItem(3, this.mSetData.DoorUnlock);
            updateItem(4, this.mSetData.VehAutoRelock);
            updateItem(5, this.mSetData.AppLamps);
            updateItem(6, this.mSetData.MoodLightMode);
            updateItem(7, this.mSetData.MoodLightFront);
            updateItem(8, this.mSetData.MoodLightRear);
            updateItem(9, this.mSetData.MoodLightColor);
            updateItem(10, this.mSetData.MoodLightLumin, " " + this.mSetData.MoodLightLumin);
        }
    }

    public void QueryData() {
        CanJni.TataWcQuery(5, 1, 97);
    }
}
