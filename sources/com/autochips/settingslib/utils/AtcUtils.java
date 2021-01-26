package com.autochips.settingslib.utils;

import android.os.SystemProperties;

public class AtcUtils {
    private static final boolean IS_ATC_AOSP = SystemProperties.getBoolean("ro.atc.aosp_enhancement", false);
    private final String TAG = AtcUtils.class.getSimpleName();

    public static boolean isAtcAOSPSupport() {
        return IS_ATC_AOSP;
    }
}
