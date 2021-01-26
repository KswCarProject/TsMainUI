package com.ts.can.benc.withcd;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.lgb.canmodule.CanJni;

public class CanBencWithCDSoSync {
    public static final String KEY_SEAT_AM_FRONT = "key_seat_am_front";
    public static final String KEY_SEAT_AM_STATUS = "key_seat_am_status";
    public static final String KEY_SEAT_AM_TIME = "key_seat_am_time";
    public static final String KEY_SEAT_BREAK_FRONT_RADAR = "key_seat_break_front_radar";
    public static final String KEY_SEAT_BREAK_REAR_RADAR = "key_seat_break_rear_radar";
    public static final String KEY_SEAT_BREAK_SPEED_START = "key_seat_break_speed_start";
    public static final String KEY_SEAT_BREAK_SPEED_STOP = "key_seat_break_speed_stop";
    public static final String KEY_SEAT_BREAK_STATUS = "key_seat_break_status";
    public static final String KEY_SEAT_PM_FRONT = "key_seat_pm_front";
    public static final String KEY_SEAT_PM_STATUS = "key_seat_pm_status";
    public static final String KEY_SEAT_YB_LEVEL = "key_seat_yb_level";
    public static final String KEY_SEAT_YB_STATUS = "key_seat_yb_status";

    public static void SetValue(Context context, String[] key, int[] value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        for (int i = 0; i < key.length; i++) {
            editor.putInt(key[i], value[i]);
        }
        editor.apply();
    }

    public static int GetValue(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, 0);
    }

    public static void Init(Context context, int para) {
        switch (para) {
            case 1:
                CanJni.BencZmytDevCmd(1, 1, GetValue(context, KEY_SEAT_BREAK_STATUS), GetValue(context, KEY_SEAT_BREAK_SPEED_STOP), GetValue(context, KEY_SEAT_BREAK_SPEED_START), GetValue(context, KEY_SEAT_BREAK_REAR_RADAR), GetValue(context, KEY_SEAT_BREAK_FRONT_RADAR), 0, 0, 0, 0, 0, 0, 0, 0, 0);
                return;
            case 2:
                CanJni.BencZmytDevCmd(2, 1, GetValue(context, KEY_SEAT_PM_STATUS), GetValue(context, KEY_SEAT_PM_FRONT), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                return;
            case 3:
                CanJni.BencZmytDevCmd(3, 1, GetValue(context, KEY_SEAT_AM_STATUS), GetValue(context, KEY_SEAT_AM_FRONT), GetValue(context, KEY_SEAT_AM_TIME), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                return;
            case 4:
                CanJni.BencZmytDevCmd(4, 1, GetValue(context, KEY_SEAT_YB_STATUS), GetValue(context, KEY_SEAT_YB_LEVEL), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
                return;
            default:
                return;
        }
    }
}
