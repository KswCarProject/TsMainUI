package com.ts.main.common;

import android.app.backup.BackupManager;
import android.content.res.Configuration;
import java.util.Locale;

public class LanguageUtils {
    public static void updateLanguage(Locale locale) {
        try {
            Class clzIActMag = Class.forName("android.app.IActivityManager");
            Class clzActMagNative = Class.forName("android.app.ActivityManagerNative");
            Object objIActMag = clzActMagNative.getDeclaredMethod("getDefault", new Class[0]).invoke(clzActMagNative, new Object[0]);
            Configuration config = (Configuration) clzIActMag.getDeclaredMethod("getConfiguration", new Class[0]).invoke(objIActMag, new Object[0]);
            config.locale = locale;
            Class.forName("android.content.res.Configuration").getField("userSetLocale").set(config, true);
            clzIActMag.getDeclaredMethod("updateConfiguration", new Class[]{Configuration.class}).invoke(objIActMag, new Object[]{config});
            BackupManager.dataChanged("com.android.providers.settings");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
