package com.ts.can.tata.lz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanTataLzCarSetView extends CanScrollCarInfoView {
    public static final int ITEM_APP_LAMPS = 5;
    public static final int ITEM_DOOR_UNLOCKING = 4;
    public static final int ITEM_DRIVE_AWAY_LOCKING = 11;
    public static final int ITEM_MAX = 12;
    public static final int ITEM_MOOD_LIGHT_COLOR = 9;
    public static final int ITEM_MOOD_LIGHT_FRONT = 7;
    public static final int ITEM_MOOD_LIGHT_LUMIN = 10;
    public static final int ITEM_MOOD_LIGHT_MODE = 6;
    public static final int ITEM_MOOD_LIGHT_REAR = 8;
    public static final int ITEM_PARK_ASSIST = 2;
    public static final int ITEM_PARK_ASSIST_DELAY_TIMER = 1;
    public static final int ITEM_PARK_ASSIST_TONE = 0;
    public static final int ITEM_VEH_AUTO_RELOCK = 3;
    private CanDataInfo.TaTa_Lz_SetData mSetData;

    public CanTataLzCarSetView(Activity activity) {
        super(activity, 12);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.TaTaLzCarSet(1, item);
                return;
            case 1:
                CanJni.TaTaLzCarSet(2, item);
                return;
            case 4:
                CanJni.TaTaLzCarSet(4, item);
                return;
            case 5:
                CanJni.TaTaLzCarSet(5, item);
                return;
            case 6:
                CanJni.TaTaLzCarSet(6, item);
                return;
            case 9:
                CanJni.TaTaLzCarSet(10, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 10:
                CanJni.TaTaLzCarSet(11, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.TaTaLzCarSet(0, Neg(this.mSetData.ParkAssist));
                return;
            case 3:
                CanJni.TaTaLzCarSet(3, Neg(this.mSetData.VehicleAutoRelock));
                return;
            case 7:
                CanJni.TaTaLzCarSet(8, Neg(this.mSetData.MoodLightingFront));
                return;
            case 8:
                CanJni.TaTaLzCarSet(9, Neg(this.mSetData.MoodLightingRear));
                return;
            case 11:
                CanJni.TaTaLzCarSet(12, Neg(this.mSetData.DriveAwayLocking));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_park_assist_tone, R.string.can_park_assist_delay_timer, R.string.can_park_assist, R.string.can_vehicle_auto_relock, R.string.can_door_unlocking, R.string.can_approarch_lamps, R.string.can_mood_lighting_mode, R.string.can_mood_lighting_front, R.string.can_mood_lighting_rear, R.string.can_mood_lighting_color, R.string.can_mood_lighting_Lumin, R.string.can_drive_away_locking};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.PROGRESS, CanScrollCarInfoView.Item.SWITCH};
        this.mPopValueIds[0] = new int[]{R.string.can_tone1, R.string.can_tone2, R.string.can_tone3};
        this.mPopValueIds[1] = new int[]{R.string.can_0s, R.string.can_5s, R.string.can_10s};
        this.mPopValueIds[4] = new int[]{R.string.can_sym, R.string.can_jsym};
        this.mPopValueIds[5] = new int[]{R.string.can_30s, R.string.can_60s, R.string.can_90s};
        this.mPopValueIds[6] = new int[]{R.string.can_all, R.string.can_off, R.string.can_gl8_2017_ktycqd_auto};
        this.mPopValueIds[9] = new int[]{R.string.can_magoten_light_color_4, R.string.can_color_red, R.string.can_color_blue, R.string.can_orange_color, R.string.can_purple, R.string.can_gray, R.string.can_magoten_light_color_2};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 3;
        iArr2[2] = 1;
        iArr[10] = iArr2;
        this.mSetData = new CanDataInfo.TaTa_Lz_SetData();
    }

    public void ResetData(boolean check) {
        CanJni.TaTaLzGetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(2, this.mSetData.ParkAssist);
            updateItem(1, this.mSetData.ParkAssistDelayTimer);
            updateItem(0, this.mSetData.ParkAssistTone);
            updateItem(4, this.mSetData.DoorUnlocking);
            updateItem(3, this.mSetData.VehicleAutoRelock);
            updateItem(5, this.mSetData.ExteriorLightsApproachLamps);
            updateItem(6, this.mSetData.MoodLightingMode);
            updateItem(7, this.mSetData.MoodLightingFront);
            updateItem(8, this.mSetData.MoodLightingRear);
            updateItem(9, this.mSetData.MoodLightingColor);
            updateItem(10, this.mSetData.MoodLightingLumin, " " + this.mSetData.MoodLightingLumin);
            updateItem(11, this.mSetData.DriveAwayLocking);
        }
    }

    public void QueryData() {
        CanJni.TaTaLzQueryData(56, 0);
    }
}
