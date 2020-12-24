package com.Ty.T.Ty;

import android.content.Context;
import android.os.Environment;
import com.txznet.sdk.tongting.IConstantData;
import java.io.File;
import java.io.IOException;

/* compiled from: Proguard */
public final class T9 {
    public static File T(Context context) {
        return T(context, true);
    }

    public static File T(Context context, boolean preferExternal) {
        String externalStorageState;
        File appCacheDir = null;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            externalStorageState = "";
        } catch (IncompatibleClassChangeError e2) {
            externalStorageState = "";
        }
        if (preferExternal && "mounted".equals(externalStorageState) && Tn(context)) {
            appCacheDir = Ty(context);
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        if (appCacheDir != null) {
            return appCacheDir;
        }
        String cacheDirPath = "/data/data/" + context.getPackageName() + "/cache/";
        Ty.Ty("Can't define system cache directory! '%s' will be used.", cacheDirPath);
        return new File(cacheDirPath);
    }

    public static File Tr(Context context) {
        return T(context, "uil-images");
    }

    public static File T(Context context, String cacheDir) {
        File appCacheDir = T(context);
        File individualCacheDir = new File(appCacheDir, cacheDir);
        if (individualCacheDir.exists() || individualCacheDir.mkdir()) {
            return individualCacheDir;
        }
        return appCacheDir;
    }

    private static File Ty(Context context) {
        File appCacheDir = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), IConstantData.KEY_DATA), context.getPackageName()), "cache");
        if (appCacheDir.exists()) {
            return appCacheDir;
        }
        if (!appCacheDir.mkdirs()) {
            Ty.Ty("Unable to create external cache directory", new Object[0]);
            return null;
        }
        try {
            new File(appCacheDir, ".nomedia").createNewFile();
            return appCacheDir;
        } catch (IOException e) {
            Ty.Tr("Can't create \".nomedia\" file in application external cache directory", new Object[0]);
            return appCacheDir;
        }
    }

    private static boolean Tn(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
}
