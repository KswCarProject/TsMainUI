package com.ts;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import com.android.SdkConstants;
import com.ts.SystemConfigAdapter;
import com.ts.main.common.MainUI;
import com.ts.main.common.ShellUtils;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConfigUtils {
    /* access modifiers changed from: private */
    public static final String TAG = ConfigUtils.class.getSimpleName();
    public static final String TS_PICTURE_Dir = "/storage/emulated/0/Ts/Picture/";
    /* access modifiers changed from: private */
    public static List<String> mBootAcceptFiles = Arrays.asList(new String[]{"tsbootanimation.zip"});
    /* access modifiers changed from: private */
    public static List<String> mLogoAcceptFiles = Arrays.asList(new String[]{"tslogo.bmp", "tslogo.jpg", "tslogo.png"});
    /* access modifiers changed from: private */
    public static List<String> mWallPaperAcceptFiles = Arrays.asList(new String[]{"tswallpaper.bmp", "tswallpaper.jpg", "tswallpaper.png"});
    /* access modifiers changed from: private */
    public static ExecutorService mWorkPool = Executors.newFixedThreadPool(5);

    public static class FileDectedRunnable implements Runnable {
        private String mDiskPath;

        public FileDectedRunnable(String diskPath) {
            this.mDiskPath = diskPath;
        }

        public void run() {
            Log.d(ConfigUtils.TAG, "scan " + this.mDiskPath);
            List<String> wallpapers = ConfigUtils.getUpdateFile(this.mDiskPath, ConfigUtils.mWallPaperAcceptFiles);
            Log.d(ConfigUtils.TAG, "wallpapers == " + wallpapers.size());
            List<String> logos = ConfigUtils.getUpdateFile(this.mDiskPath, ConfigUtils.mLogoAcceptFiles);
            Log.d(ConfigUtils.TAG, "logos == " + logos.size());
            List<String> bootAnimations = ConfigUtils.getUpdateFile(this.mDiskPath, ConfigUtils.mBootAcceptFiles);
            Log.d(ConfigUtils.TAG, "bootAnimations == " + bootAnimations.size());
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.ts.MainUI", "com.ts.FsConfigActivity"));
            List<String> updateFiles = new ArrayList<>();
            if (wallpapers != null && !wallpapers.isEmpty()) {
                updateFiles.add(wallpapers.get(0));
            }
            if (logos != null && !logos.isEmpty()) {
                updateFiles.add(logos.get(0));
            }
            if (bootAnimations != null && !bootAnimations.isEmpty()) {
                updateFiles.add(bootAnimations.get(0));
            }
            if (updateFiles.size() != 0) {
                intent.addFlags(268435456);
                intent.putExtra("files", (String[]) updateFiles.toArray(new String[1]));
                MainUI.GetInstance().startActivity(intent);
            }
        }
    }

    public static class DeviceMountedReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String diskPath = intent.getData().getPath();
            if (diskPath.contains("udisk")) {
                ConfigUtils.mWorkPool.execute(new FileDectedRunnable(diskPath));
            }
            Log.d(ConfigUtils.TAG, "DeviceMountedReceiver receive broadcast");
        }
    }

    /* access modifiers changed from: private */
    public static List<String> getUpdateFile(String diskPath, List<String> acceptFiles) {
        if (diskPath == null || acceptFiles == null || acceptFiles.isEmpty()) {
            return null;
        }
        List<String> fileNames = new ArrayList<>();
        for (String name : acceptFiles) {
            File file = new File(String.valueOf(diskPath) + "/" + name);
            if (file.exists()) {
                fileNames.add(file.getAbsolutePath());
            }
        }
        Log.d(TAG, fileNames.toString());
        return fileNames;
    }

    public static int configWallpaper(String wallpaperPath) {
        try {
            WallpaperManager.getInstance(MainUI.GetInstance()).setStream(new FileInputStream(new File(wallpaperPath)));
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int updateLogo(String path) {
        try {
            DisplayMetrics metrics = MainUI.GetInstance().getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            if (width != options.outWidth || height != options.outHeight) {
                return -1;
            }
            String fileName = new File(path).getName();
            String fileName2 = fileName.subSequence(0, fileName.lastIndexOf(".")).toString();
            Context ctx = MainUI.GetInstance().createPackageContext("com.ts.logoset", 3);
            Class<?> cls = ctx.getClassLoader().loadClass("com.ts.logoset.ImageUtils");
            Object bmp = cls.getDeclaredMethod("getBitmapFromFile", new Class[]{String.class, Integer.TYPE, Integer.TYPE}).invoke((Object) null, new Object[]{path, Integer.valueOf(width), Integer.valueOf(height)});
            cls.getDeclaredMethod("file2Bmp", new Class[]{Bitmap.class, String.class, Integer.TYPE, Integer.TYPE}).invoke((Object) null, new Object[]{bmp, TS_PICTURE_Dir + fileName2 + SdkConstants.DOT_BMP, Integer.valueOf(width), Integer.valueOf(height)});
            Class<?> cls2 = ctx.getClassLoader().loadClass("com.ts.logoset.AtcLogoUtils");
            Method getInstanceMethod = cls2.getDeclaredMethod("getInstance", new Class[]{Integer.TYPE, Integer.TYPE});
            if (getInstanceMethod != null) {
                Object instance = getInstanceMethod.invoke((Object) null, new Object[]{Integer.valueOf(width), Integer.valueOf(height)});
                Method method = cls2.getDeclaredMethod("changeImageResolution", new Class[]{String.class});
                if (method != null) {
                    method.invoke(instance, new Object[]{TS_PICTURE_Dir + fileName2 + SdkConstants.DOT_BMP});
                    return 0;
                }
            }
            Log.d("wcb", "updateLogo finished");
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void checkFile(String filepath) {
        File file = new File(filepath);
        if (file.exists() && file.canRead()) {
            long tick = SystemClock.uptimeMillis();
            Log.d("wcb", String.valueOf(ShellUtils.execCommand(new String[]{"sha1sum " + file.getAbsolutePath()}, false).successMsg) + " cost " + (SystemClock.uptimeMillis() - tick));
        }
    }

    public static int copyFile(String srcPath, String desPath, String[] extraCmd) {
        File file = new File(srcPath);
        if (file.exists() && file.canRead()) {
            String[] commands = new String[(extraCmd != null ? extraCmd.length + 1 : 0)];
            commands[0] = "dd if=" + file.getAbsolutePath() + " of=" + desPath;
            int i = 0;
            while (extraCmd != null && i < extraCmd.length) {
                commands[i + 1] = extraCmd[i];
                i++;
            }
            ShellUtils.CommandResult result = ShellUtils.execCommand(commands, true);
            Log.d("wcb", result.successMsg);
            if (result.result == 0) {
                return 0;
            }
        }
        return -1;
    }

    public static void dumpWakelock() {
        Log.d("wcb", ShellUtils.execCommand(new String[]{"dumpsys power"}, false).successMsg);
    }

    public static void configBtnClick(SystemConfigAdapter adapter, Handler handler, int position) {
        if (adapter.getCount() > position && position >= 0) {
            SystemConfigAdapter.ConfigInfo info = (SystemConfigAdapter.ConfigInfo) adapter.getItem(position);
            info.setConfigStatus(1);
            mWorkPool.execute(new ConfigRunnable(handler, position, info));
            adapter.notifyDataSetChanged();
        }
    }

    public static class ConfigRunnable implements Runnable {
        private Handler mHandler;
        private SystemConfigAdapter.ConfigInfo mInfo;
        private int mWhat;

        public ConfigRunnable(Handler handler, int position, SystemConfigAdapter.ConfigInfo info) {
            this.mHandler = handler;
            this.mWhat = position;
            this.mInfo = info;
        }

        public void run() {
            int result = -1;
            switch (this.mInfo.getConfigType()) {
                case 0:
                    result = ConfigUtils.configWallpaper(this.mInfo.getConfigPath());
                    break;
                case 1:
                    result = ConfigUtils.copyFile(this.mInfo.getConfigPath(), "/data/media/bootanimation.zip", new String[]{"chmod 777 /data/media/bootanimation.zip", "sync"});
                    break;
                case 2:
                    result = ConfigUtils.updateLogo(this.mInfo.getConfigPath());
                    break;
            }
            Message msg = this.mHandler.obtainMessage(this.mWhat);
            msg.arg1 = result;
            this.mHandler.sendMessage(msg);
        }
    }
}
