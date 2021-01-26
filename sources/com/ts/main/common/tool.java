package com.ts.main.common;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.os.SystemProperties;
import android.support.v4.view.ViewCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import com.android.SdkConstants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.qiner.appinfo.AppListUtil;
import com.ts.MainUI.R;
import com.ts.MainUI.TsFile;
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.factoryset.FsBaseActivity;
import com.ts.main.common.ShellUtils;
import com.ts.main.txz.TxzReg;
import com.txznet.sdk.TXZResourceManager;
import com.txznet.sdk.music.MusicInvokeConstants;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class tool {
    static final String DUMP_LOG_PATH = "/mnt/sdcard/TsLog/quickbootdump";
    static tool Mytool = null;
    static final String SLEEP_LOG_PATH = "/mnt/sdcard/TsLog/quickboot";
    static String StrFileName = null;
    private static final String TAG = "tool";
    public static Map<String, String> WhiteList = null;
    /* access modifiers changed from: private */
    public static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
    private static final String logopath = "tslogo";
    public static Context mContext = null;
    private static ExecutorService mWorkPool = Executors.newFixedThreadPool(5);
    String MapPath;
    String MountPath;
    String SrcPath = TXZResourceManager.STYLE_DEFAULT;
    String UcompressDst;
    String UcompressFile;
    public boolean bEnableLog = false;
    String installPath = TXZResourceManager.STYLE_DEFAULT;
    public long totalSize = 0;

    public static tool GetInstance() {
        if (Mytool == null) {
            Mytool = new tool();
        }
        return Mytool;
    }

    public void Inint(Context ctx) {
        mContext = ctx;
    }

    public void KillNavi() {
        killProcess("com.autonavi.amapauto");
    }

    public static int IsSysapk(String packName) {
        if (mContext == null) {
            return 0;
        }
        int appFlags = 0;
        try {
            appFlags = mContext.getPackageManager().getApplicationInfo(packName, 0).flags;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appFlags & 1;
    }

    /* access modifiers changed from: package-private */
    public List<String> GetAllProcess() {
        List<String> pbList = new ArrayList<>();
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : ((ActivityManager) mContext.getSystemService(SdkConstants.TAG_ACTIVITY)).getRunningAppProcesses()) {
            if (!appProcessInfo.processName.contains("com.android.system") && !appProcessInfo.processName.contains("com.ts.") && !appProcessInfo.processName.contains("com.forfan.") && appProcessInfo.pid != Process.myPid()) {
                String[] pkNameList = appProcessInfo.pkgList;
                for (int i = 0; i < pkNameList.length; i++) {
                    if (IsSysapk(pkNameList[i]) == 0 || pkNameList[i].equals("com.glsx.ddbox")) {
                        pbList.add(pkNameList[i]);
                    }
                }
            }
        }
        return pbList;
    }

    public void KillSystemProcess(String Process, int nMem) {
        ShellUtils.CommandResult result = ShellUtils.execCommand("ps |grep " + Process, true);
        if (result.successMsg != null && result.successMsg.length() > 0) {
            String[] infos = result.successMsg.split(" +");
            if (infos.length != 0 && Integer.parseInt(infos[4]) > nMem) {
                ShellUtils.execCommand("kill " + Integer.parseInt(infos[1]), true);
            }
        }
    }

    public void ChangePathToRoot(String Path) {
        DealSu("chmod -R 0777 " + Path);
        DealSu("chcon -R u:object_r:system_file:s0 " + Path);
    }

    /* access modifiers changed from: package-private */
    public int GetProcessID(String Pname) {
        if (mContext != null) {
            for (ActivityManager.RunningAppProcessInfo appProcessInfo : ((ActivityManager) mContext.getSystemService(SdkConstants.TAG_ACTIVITY)).getRunningAppProcesses()) {
                if (appProcessInfo.processName.contains(Pname)) {
                    return appProcessInfo.pid;
                }
            }
        }
        return 1;
    }

    public void KillOther(String packageName) {
        boolean bKill = false;
        Log.i(TAG, "KillOther==" + packageName);
        String[] AllKillStr = {"com.android.settings", "com.estrongs.android.pop", "es.voghdev.pdfviewpager", "com.android.browser", "com.android.chrome", "com.ts.apk", "com.iflytek.inputmentmethod", "com.ts.gpstest", "com.android.vending", "com.ts.gallery", "com.android.email"};
        String[] ValidStr = {"com.zbx.ct.tvzhibo", "com.txznet.music.close.app", "com.hongfans.rearview", "com.kugou.android", "com.tencent.qqmusic", "com.ximalaya.ting.android.car", "net.easyconn", "cn.kuwo.kwmusiccar", "com.mxtech.videoplayer.pro", "com.qiyi.video.pad", "com.google.android.youtube"};
        if (packageName.equals("com.ts.dvdplayer")) {
            for (String killProcess : ValidStr) {
                killProcess(killProcess);
            }
            for (String killProcess2 : AllKillStr) {
                killProcess(killProcess2);
            }
            return;
        }
        int i = 0;
        while (true) {
            if (i >= ValidStr.length) {
                break;
            } else if (ValidStr[i].equals(packageName)) {
                bKill = true;
                break;
            } else {
                i++;
            }
        }
        if (bKill) {
            for (int i2 = 0; i2 < ValidStr.length; i2++) {
                if (!ValidStr[i2].equals(packageName)) {
                    killProcess(ValidStr[i2]);
                }
            }
            for (String killProcess3 : AllKillStr) {
                killProcess(killProcess3);
            }
            return;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= AllKillStr.length) {
                break;
            } else if (AllKillStr[i3].equals(packageName)) {
                bKill = true;
                break;
            } else {
                i3++;
            }
        }
        if (bKill) {
            for (int i4 = 0; i4 < AllKillStr.length; i4++) {
                if (!AllKillStr[i4].equals(packageName)) {
                    killProcess(AllKillStr[i4]);
                }
            }
        }
    }

    public boolean killProcess(final String packageName) {
        if (GetProcessID(packageName) == 1) {
            return false;
        }
        Log.i(TAG, "kill process start==" + packageName);
        mWorkPool.submit(new Runnable() {
            public void run() {
                try {
                    if (tool.mContext != null) {
                        Class.forName("android.app.ActivityManager").getMethod("forceStopPackage", new Class[]{String.class}).invoke((ActivityManager) tool.mContext.getSystemService(SdkConstants.TAG_ACTIVITY), new Object[]{packageName});
                    }
                    Log.e(tool.TAG, "kill process by aios-adapter!!==" + packageName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return true;
    }

    public void killAllProcess(int nSate) {
        Log.i(TAG, "killAllProcess: start==" + nSate);
        List<String> WhiteValidStr = new ArrayList<>(Arrays.asList(new String[]{"com.mediatek.filemanager", "com.txznet.music.close.app", "com.hongfans.rearview", "com.kugou.android", "com.tencent.qqmusic", "com.into.stability", "com.ximalaya.ting.android.car", "com.didi365.miudrive.navi", "net.easyconn", "cn.kuwo.kwmusiccar", "com.mxtech.videoplayer.pro", "com.android.sdvdplayer", "com.iflytek.inputmethod", MainUI.TS_CARPLAY_PNAME, "com.autochips.avmplayer", "com.txznet.txz", "com.txznet.smartadapter", "com.glsx.ddbox", "com.autonavi.amapauto"}));
        List<String> pMybList = GetAllProcess();
        for (int i = 0; i < pMybList.size(); i++) {
            if (nSate <= 2 && !MainSet.GetInstance().IsBlackList(pMybList.get(i)) && !WhiteValidStr.contains(pMybList.get(i))) {
                if (nSate >= 1) {
                    killProcess(pMybList.get(i));
                } else if (!WinShow.getTopPackName().equals(pMybList.get(i))) {
                    killProcess(pMybList.get(i));
                }
            }
        }
        if (nSate == 2) {
            List<String> BlackbList = AppListUtil.getInstance().updateAppList();
            if (BlackbList.size() > 0) {
                int i2 = 0;
                while (i2 < BlackbList.size()) {
                    if (!killProcess(BlackbList.get(i2))) {
                        i2++;
                    } else {
                        return;
                    }
                }
            }
            if (WhiteValidStr.size() > 0) {
                int i3 = 0;
                while (i3 < WhiteValidStr.size() && !killProcess(WhiteValidStr.get(i3))) {
                    i3++;
                }
            }
        }
    }

    public void DealSu(String Str) {
        String[] commands = {TXZResourceManager.STYLE_DEFAULT};
        commands[0] = Str;
        ShellUtils.CommandResult result = ShellUtils.execCommand(commands, true);
        Log.v(TAG, "DealSu [" + Str + "] result==" + result.result);
        if (result.errorMsg != null && result.errorMsg.length() > 0) {
            Log.v(TAG, "DealSu errorMsg==" + result.errorMsg);
        }
    }

    public void RootSystem() {
        ShellUtils.CommandResult result = ShellUtils.execCommand(new String[]{"echo 0 > /sys/kernel/debug/mmc0/sw_wp_en", "mount  -o  remount,rw     -t ext4  /dev/root  /", "mount  -o  remount,rw     -t ext4  /dev/block/platform/bootdevice/by-name/vendor  /vendor"}, true);
        Log.v(TAG, "RootSystem result==" + result.result);
        if (result.errorMsg != null && result.errorMsg.length() > 0) {
            Log.v(TAG, "RootSystem errorMsg==" + result.errorMsg);
        }
    }

    public void UnRootSystem() {
        ShellUtils.CommandResult result = ShellUtils.execCommand(new String[]{"chmod 0751 /system", "mount -o ro,remount /system"}, true);
        Log.v(TAG, "UnRootSystem result==" + result.result);
        if (result.errorMsg != null && result.errorMsg.length() > 0) {
            Log.v(TAG, "UnRootSystem errorMsg==" + result.errorMsg);
        }
    }

    public static long getDirSize(File file) {
        if (!file.exists()) {
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
            return 0;
        } else if (!file.isDirectory()) {
            return file.length();
        } else {
            long size = 0;
            for (File f : file.listFiles()) {
                size += getDirSize(f);
            }
            return size;
        }
    }

    /* access modifiers changed from: package-private */
    public long readFreeSpace() {
        return Environment.getDataDirectory().getFreeSpace();
    }

    /* access modifiers changed from: package-private */
    public void ChannelCpyData(String src, String dest) {
        FileChannel srcChannel = null;
        FileChannel dstChannel = null;
        try {
            srcChannel = new FileInputStream(src).getChannel();
            dstChannel = new FileOutputStream(dest).getChannel();
            srcChannel.transferTo(0, srcChannel.size(), dstChannel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (srcChannel != null) {
            try {
                srcChannel.close();
            } catch (IOException e3) {
                e3.printStackTrace();
                return;
            }
        }
        if (dstChannel != null) {
            dstChannel.close();
        }
    }

    public void SucopyFolder(String oldPath, String newPath, int ntype) {
        File temp;
        try {
            new File(newPath).mkdirs();
            String[] file = new File(oldPath).list();
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(String.valueOf(oldPath) + file[i]);
                } else {
                    temp = new File(String.valueOf(oldPath) + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    if (ntype == 1) {
                        MainAlterwin.GetInstance().UpdateLoadWinStr(String.valueOf(temp.getName()) + " " + ((temp.length() / 1024) / 1024) + "MB");
                    } else if (ntype == 2) {
                        MainAlterwin.GetInstance().UpdateMessage(String.valueOf(mContext.getResources().getString(R.string.message_copyintdata)) + ":" + temp.getName() + " " + ((temp.length() / 1024) / 1024) + "MB");
                    }
                    ChannelCpyData(temp.getPath(), String.valueOf(newPath) + "/" + temp.getName().toString());
                }
                if (temp.isDirectory()) {
                    SucopyFolder(String.valueOf(oldPath) + "/" + file[i], String.valueOf(newPath) + "/" + file[i], ntype);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getExtensionName(String filename) {
        int dot;
        if (filename == null || filename.length() <= 0 || (dot = filename.lastIndexOf(46)) <= -1 || dot >= filename.length() - 1) {
            return filename;
        }
        return filename.substring(dot + 1);
    }

    public boolean CopyDemoData(String StrPath, final String NewPath) {
        Mcu.SendXKey(21);
        String[] strSDMountedPath = MainSet.GetInstance().GetMountedStorage();
        Log.i(TAG, "strSDMountedPath.length ==" + strSDMountedPath.length);
        boolean bRet = false;
        this.installPath = null;
        this.SrcPath = null;
        for (int i = 0; i < strSDMountedPath.length; i++) {
            Log.i(TAG, "strSDMountedPath[i] ==" + strSDMountedPath[i]);
            if (!strSDMountedPath[i].contains("sdcard0") && !strSDMountedPath[i].contains("ctfs")) {
                if (!MainSet.GetInstance().IsTwcjw() && TsFile.fileIsExists(String.valueOf(strSDMountedPath[i]) + "/tsoem")) {
                    this.installPath = String.valueOf(strSDMountedPath[i]) + "/tsoem";
                    bRet = true;
                }
                if (TsFile.fileIsExists(String.valueOf(strSDMountedPath[i]) + StrPath)) {
                    Log.i(TAG, "find the  ==" + strSDMountedPath[i] + StrPath);
                    bRet = true;
                    this.SrcPath = String.valueOf(strSDMountedPath[i]) + StrPath;
                }
            }
        }
        if (bRet) {
            if (this.SrcPath != null) {
                MainAlterwin.GetInstance().ShowMessageWin(mContext.getResources().getString(R.string.message_start_copydata));
            } else if (this.installPath != null) {
                MainAlterwin.GetInstance().ShowMessageWin(String.valueOf(mContext.getResources().getString(R.string.message_start_installapp)) + "APP");
            }
            MainAlterwin.GetInstance().SetMessWinEnClose(false);
            mWorkPool.submit(new Runnable() {
                public void run() {
                    try {
                        tool.this.SucopyFolder(tool.this.SrcPath, NewPath, 2);
                        Log.i(tool.TAG, "SucopyFolder end  ==" + NewPath);
                        if (tool.this.installPath != null && tool.this.installPath.length() > 0) {
                            File myfile = new File(tool.this.installPath);
                            if (myfile.isDirectory()) {
                                MainAlterwin.GetInstance().UpdateMessage(String.valueOf(tool.mContext.getResources().getString(R.string.message_start_installapp)) + "APP");
                                File[] lfileFiles = myfile.listFiles();
                                for (int i = 0; i < lfileFiles.length; i++) {
                                    if (lfileFiles[i].isFile() && tool.getExtensionName(lfileFiles[i].getPath()).equalsIgnoreCase(SdkConstants.EXT_ANDROID_PACKAGE)) {
                                        Log.i(tool.TAG, " lfileFiles[i].pathSeparator;" + lfileFiles[i].getPath());
                                        MainAlterwin.GetInstance().UpdateMessage(String.valueOf(tool.mContext.getResources().getString(R.string.message_start_installapp)) + ":" + lfileFiles[i].getName());
                                        tool.this.DealSu("pm install -r \"" + lfileFiles[i].getPath() + "\"");
                                    }
                                }
                            }
                        }
                        MainAlterwin.GetInstance().UpdateMessage(tool.mContext.getResources().getString(R.string.message_copydataend));
                        MainAlterwin.GetInstance().SetMessWinEnClose(true);
                    } catch (Exception e) {
                        Log.e("Exception when sendPointerSync", e.toString());
                    }
                }
            });
        }
        return bRet;
    }

    public void KillTxzBeforeSleep() {
        Log.d(TAG, "KillTxzBeforeSleep ");
        if (MainSet.GetInstance().IsHaveApk("com.txznet.txz")) {
            KillSystemProcess("com.txznet.txz", 0);
            Log.d(TAG, "KillTxzBeforeSleep ok");
        }
        TxzReg.GetInstance().ReSet();
    }

    public void KillAppBeforeSleep() {
        WhiteList = Utils.getWhiteList(mContext);
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : ((ActivityManager) mContext.getSystemService(SdkConstants.TAG_ACTIVITY)).getRunningAppProcesses()) {
            String[] pkNameList = appProcessInfo.pkgList;
            for (int i = 0; i < pkNameList.length; i++) {
                if (pkNameList[i] != null && !WhiteList.containsKey(pkNameList[i]) && !pkNameList[i].equals("com.autochips.quickbootmanager") && !pkNameList[i].equals("com.ts.dvdplayer")) {
                    killProcess(pkNameList[i]);
                    Log.i(TAG, "GetAllApp() KILL ==" + pkNameList[i]);
                }
            }
        }
    }

    public void CopyMapdata() {
        Mcu.SendXKey(21);
        TsFile.deleteFile(new File(GetMapPath()));
        mWorkPool.submit(new Runnable() {
            public void run() {
                try {
                    tool.this.SucopyFolder(tool.this.MapPath, tool.this.GetMapPath(), 1);
                } catch (Exception e) {
                    Log.e("Exception when sendPointerSync", e.toString());
                }
            }
        });
    }

    public void MounKeyTest() {
        mWorkPool.submit(new Runnable() {
            public void run() {
                try {
                    tool.this.DealSu("monkey  --throttle 200 --pct-touch 90 --pct-motion 10 -v -v 100000 >" + Environment.getExternalStorageDirectory() + "/monkey.txt");
                } catch (Exception e) {
                    Log.e("Exception when sendPointerSync", e.toString());
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public long GetSdMapSize() {
        return getDirSize(new File(GetMapPath()));
    }

    /* access modifiers changed from: package-private */
    public String GetMapFile() {
        if (MainSet.GetInstance().IsTwcjw()) {
            if (TsFile.fileIsExists(String.valueOf(this.MountPath) + "/TsMap/com.papago.s1OBU")) {
                return "/TsMap/com.papago.s1OBU";
            }
            return "/TsMap/com.kingwaytek";
        } else if (TsFile.fileIsExists(String.valueOf(this.MountPath) + "/TsMap/amapauto8")) {
            return "/TsMap/amapauto8";
        } else {
            return "/TsMap/amapauto9";
        }
    }

    /* access modifiers changed from: package-private */
    public String GetMapPath() {
        if (!MainSet.GetInstance().IsTwcjw()) {
            return "mnt/sdcard/amapauto8";
        }
        if (TsFile.fileIsExists(String.valueOf(this.MountPath) + "/TsMap/com.papago.s1OBU")) {
            return "mnt/sdcard/android/data/com.papago.s1OBU";
        }
        return "mnt/sdcard/android/data/com.kingwaytek";
    }

    public boolean CheckMapData() {
        if (mContext == null) {
            return false;
        }
        String[] strSDMountedPath = MainSet.GetInstance().GetMountedStorage();
        Log.i(TAG, "strSDMountedPath.length ==" + strSDMountedPath.length);
        for (int i = 0; i < strSDMountedPath.length; i++) {
            this.MountPath = strSDMountedPath[i];
            if (TsFile.fileIsExists(String.valueOf(strSDMountedPath[i]) + GetMapFile())) {
                this.MapPath = String.valueOf(strSDMountedPath[i]) + GetMapFile();
                this.totalSize = getDirSize(new File(String.valueOf(strSDMountedPath[i]) + GetMapFile()));
                if (TsFile.fileIsExists(GetMapPath())) {
                    if (GetSdMapSize() == this.totalSize) {
                        MainAlterwin.GetInstance().ShowLoadWin(mContext.getResources().getString(R.string.copy_data_is_eques), false);
                    } else {
                        MainAlterwin.GetInstance().ShowLoadWin(mContext.getResources().getString(R.string.copy_data_is_noteques), true);
                    }
                } else if (readFreeSpace() < this.totalSize) {
                    MainAlterwin.GetInstance().ShowLoadWin(mContext.getResources().getString(R.string.copy_data_space_notenough), false);
                } else {
                    MainAlterwin.GetInstance().ShowLoadWin(String.valueOf(mContext.getResources().getString(R.string.copy_data_size)) + ((getDirSize(new File(String.valueOf(strSDMountedPath[i]) + GetMapFile())) / 1024) / 1024) + "MB", true);
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkDiscUpdate(String path) {
        if (path == null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.android.sunoddtool", "com.android.sunoddtool.MainActivity"));
            intent.addFlags(268468224);
            mContext.startActivity(intent);
            return true;
        }
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files == null) {
                return false;
            }
            for (File tmp : files) {
                if (tmp.isFile() && tmp.getName().toUpperCase().startsWith("SUN51HAO")) {
                    try {
                        Intent intent2 = new Intent();
                        intent2.setComponent(new ComponentName("com.android.sunoddtool", "com.android.sunoddtool.MainActivity"));
                        intent2.putExtra("path", tmp.getAbsolutePath());
                        intent2.addFlags(268468224);
                        mContext.startActivity(intent2);
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    public void checkLogoFileUpdate(String path) {
        if (path != null) {
            File file = new File(String.valueOf(path) + "/" + logopath);
            Log.v(TAG, "size=" + getDirSize(file));
            if (file.exists() && getDirSize(file) < 10485760) {
                RootSystem();
                DealSu("rm -r system/tsoem/tslogo");
                DealSu("cp -r " + path + "/" + logopath + " system/tsoem");
                UnRootSystem();
                WinShow.GotoWin(17, 0);
            }
        }
    }

    public void checkDspUpdate(String path) {
        if (path != null) {
            File file = new File(String.valueOf(path) + "/tsdsp.bin");
            if (file.exists()) {
                try {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.ts.MainUI", "com.ts.main.dsp.DspUpdateActivity"));
                    intent.putExtra("path", file.getAbsolutePath());
                    intent.addFlags(268468224);
                    mContext.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String GetBranchVerSion() {
        File file = new File("system/ota-version.cfg");
        String Str = null;
        if (file.exists()) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            String[] strArr = new String[2];
            while (true) {
                try {
                    String s = bufferedReader.readLine();
                    if (s == null) {
                        break;
                    }
                    String[] p = s.split("=");
                    if (p[0] != null && p[0].equals("branch")) {
                        Str = p[1];
                    } else if (p[0] != null && p[0].equals(MusicInvokeConstants.KEY_PUSH_VERSION)) {
                        Str = String.valueOf(Str) + p[1];
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Str;
    }

    /* access modifiers changed from: package-private */
    public String GetFileType(String Path) {
        int nIndex = Path.lastIndexOf(".");
        Log.i(TAG, "Path==" + Path.substring(nIndex + 1));
        return Path.substring(nIndex + 1);
    }

    public void UnCompressFile() {
        mWorkPool.submit(new Runnable() {
            public void run() {
                try {
                    UnzipFile.decompressall(tool.this.UcompressFile, tool.this.UcompressDst);
                    TsFile.writeFileSdcardFile(String.valueOf(tool.this.UcompressDst) + "/" + MainSet.AUTOUPDATE_FLAG, "A");
                    new File(tool.this.UcompressFile).delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void SearchUpdateZipFile() {
        File[] lfileFiles;
        if (0 != 0) {
            Log.i(TAG, "OldV==" + null);
            String[] strSDMountedPath = MainSet.GetInstance().GetMountedStorage();
            Log.i(TAG, "strSDMountedPath.length ==" + strSDMountedPath.length);
            for (int i = 0; i < strSDMountedPath.length; i++) {
                if (!strSDMountedPath[i].contains("emulated") && !strSDMountedPath[i].contains("cdfs")) {
                    File myfile = new File(strSDMountedPath[i]);
                    if (myfile.isDirectory() && !TsFile.fileIsExists(String.valueOf(strSDMountedPath[i]) + "/" + MainSet.UPDATE_FLAG) && (lfileFiles = myfile.listFiles()) != null) {
                        int j = 0;
                        while (true) {
                            if (j >= lfileFiles.length) {
                                break;
                            } else if (!lfileFiles[j].getName().contains((CharSequence) null) || !GetFileType(lfileFiles[j].getPath()).equalsIgnoreCase("zip")) {
                                j++;
                            } else {
                                Log.i(TAG, " lfileFiles[i].pathSeparator;" + lfileFiles[j].getPath());
                                if (lfileFiles.length <= 3) {
                                    this.UcompressFile = lfileFiles[j].getPath();
                                    this.UcompressDst = strSDMountedPath[i];
                                    MainAlterwin.GetInstance().ShowUncompessWin();
                                } else if (mContext != null) {
                                    Toast.makeText(mContext, mContext.getResources().getString(R.string.comress_recopy_updatefile), 0).show();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void SetMainUIIcon() {
        int[] icon = new int[50];
        FtSet.GetIcon(icon);
        for (int i = 0; i < MainIconDef.packageStr.length; i++) {
            Log.i(TAG, "packages==" + MainIconDef.packageStr[i][0]);
            Log.i(TAG, "classname==" + MainIconDef.packageStr[i][1]);
            if (!"com.ts.main.vmcd.VMCDMainActivity".equals(MainIconDef.packageStr[i][1])) {
                if (icon[i] > 0) {
                    SetapkDisp(MainIconDef.packageStr[i][0], MainIconDef.packageStr[i][1], true);
                } else {
                    SetapkDisp(MainIconDef.packageStr[i][0], MainIconDef.packageStr[i][1], false);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void SetapkDisp(String packageName, String classname, boolean bShow) {
        if (mContext != null) {
            PackageManager pm = mContext.getPackageManager();
            if (!bShow) {
                pm.setComponentEnabledSetting(new ComponentName(packageName, classname), 2, 1);
            } else {
                pm.setComponentEnabledSetting(new ComponentName(packageName, classname), 1, 1);
            }
        }
    }

    public void SetapkDisp(String packageName, boolean bShow) {
        if (mContext != null) {
            Log.i(TAG, " packageName=" + packageName);
            Log.i(TAG, " bShow=" + bShow);
            PackageManager packageManager = mContext.getPackageManager();
            if (bShow) {
                packageManager.setApplicationEnabledSetting(packageName, 0, 0);
            } else {
                packageManager.setApplicationEnabledSetting(packageName, 2, 0);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public long readSDCard() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return 0;
        }
        StatFs sf = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long blockSize = (long) sf.getBlockSize();
        long blockCount = (long) sf.getBlockCount();
        long availCount = (long) sf.getAvailableBlocks();
        Log.d(TAG, "block大小:" + blockSize + ",block数目:" + blockCount + ",总大小:" + ((blockSize * blockCount) / 1024) + "KB");
        Log.d(TAG, "可用的block数目：:" + availCount + ",剩余空间:" + ((availCount * blockSize) / 1024) + "KB");
        return blockSize * blockCount;
    }

    /* access modifiers changed from: package-private */
    public long readSystem() {
        StatFs sf = new StatFs(Environment.getRootDirectory().getPath());
        long blockSize = (long) sf.getBlockSize();
        long blockCount = (long) sf.getBlockCount();
        long availCount = (long) sf.getAvailableBlocks();
        Log.d(TAG, "block大小:" + blockSize + ",block数目:" + blockCount + ",总大小:" + ((blockSize * blockCount) / 1024) + "KB");
        Log.d(TAG, "可用的block数目：:" + availCount + ",可用大小:" + ((availCount * blockSize) / 1024) + "KB");
        return blockSize * blockCount;
    }

    public int GetnEmmcSize() {
        long tSize = ((readSDCard() + readSystem()) / 1024) / 1024;
        if (tSize <= 16384) {
            return 16;
        }
        if (tSize > 16384 && tSize < 32768) {
            return 32;
        }
        if (tSize <= 32768 || tSize >= 65536) {
            return 128;
        }
        return 64;
    }

    public String GetEmmcSize() {
        long tSize = ((readSDCard() + readSystem()) / 1024) / 1024;
        if (tSize <= 16384) {
            return "16GB";
        }
        if (tSize > 16384 && tSize < 32768) {
            return "32GB";
        }
        if (tSize <= 32768 || tSize >= 65536) {
            return "128GB";
        }
        return "64GB";
    }

    public void SetScreenH() {
        Log.d(TAG, "MainUI.mUIDirect==" + MainUI.mUIDirect);
        Log.d(TAG, "MainUI.mBackCarDirect==" + MainUI.mBackCarDirect);
        switch (MainUI.mBackCarDirect) {
            case 0:
                SystemProperties.set("forfan.force_direct", "1");
                break;
            case 90:
                SystemProperties.set("forfan.force_direct", "3");
                break;
            case 270:
                SystemProperties.set("forfan.force_direct", "1");
                break;
        }
        if (AtcDisplaySettingsUtils.getInstance().readRotation() != 0) {
        }
        AtcDisplaySettingsUtils.getInstance().setScreenOrientation(0, MainUI.GetSrcW(), MainUI.GetSrcH());
        FsBaseActivity.ResetMdp();
        MainAlterwin.GetInstance().ShowScrolWin();
        MainAlterwin.GetInstance().HidenScrolWin();
    }

    public void SetScreenV() {
        Log.d(TAG, "MainUI.mUIDirect==" + MainUI.mUIDirect);
        Log.d(TAG, "MainUI.mBackCarDirect==" + MainUI.mBackCarDirect);
        switch (MainUI.mBackCarDirect) {
            case 0:
                if (FtSet.GetLcdLeftRight() != 0) {
                    SystemProperties.set("forfan.force_direct", "2");
                    if (AtcDisplaySettingsUtils.getInstance().readRotation() != 270) {
                    }
                    AtcDisplaySettingsUtils.getInstance().setScreenOrientation(4, MainUI.GetSrcW(), MainUI.GetSrcH());
                    FsBaseActivity.ResetMdp();
                    break;
                } else {
                    SystemProperties.set("forfan.force_direct", MainSet.SP_KS_QOROS);
                    if (AtcDisplaySettingsUtils.getInstance().readRotation() != 90) {
                    }
                    AtcDisplaySettingsUtils.getInstance().setScreenOrientation(2, MainUI.GetSrcW(), MainUI.GetSrcH());
                    FsBaseActivity.ResetMdp();
                    break;
                }
            case 90:
                SystemProperties.set("forfan.force_direct", "0");
                break;
            case 270:
                SystemProperties.set("forfan.force_direct", "0");
                break;
            default:
                SystemProperties.set("forfan.force_direct", "2");
                break;
        }
        MainAlterwin.GetInstance().ShowScrolWin();
        MainAlterwin.GetInstance().HidenScrolWin();
    }

    public static void WriteLog(String conent) {
        if (TsFile.fileIsExists(MainSet.TS_LOG_FILE)) {
            String time = formatter.format(new Date());
            int nNUM = 0;
            if (StrFileName == null) {
                StrFileName = "/mnt/sdcard/TsLog/quickboot-" + 0 + SdkConstants.DOT_TXT;
                while (TsFile.fileIsExists(StrFileName)) {
                    nNUM++;
                    StrFileName = "/mnt/sdcard/TsLog/quickboot-" + nNUM + SdkConstants.DOT_TXT;
                }
            }
            BufferedWriter out = null;
            try {
                BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(StrFileName, true)));
                try {
                    out2.write(String.valueOf(time) + ":" + conent + "\r\n");
                    try {
                        out2.close();
                        BufferedWriter bufferedWriter = out2;
                    } catch (IOException e) {
                        e.printStackTrace();
                        BufferedWriter bufferedWriter2 = out2;
                    }
                } catch (Exception e2) {
                    e = e2;
                    out = out2;
                    try {
                        e.printStackTrace();
                        try {
                            out.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    } catch (Throwable th) {
                        th = th;
                        try {
                            out.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    out = out2;
                    out.close();
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                e.printStackTrace();
                out.close();
            }
        }
    }

    public static void dumpWakelock() {
        if (TsFile.fileIsExists(MainSet.TS_LOG_FILE)) {
            mWorkPool.submit(new Runnable() {
                public void run() {
                    String time = tool.formatter.format(new Date());
                    ShellUtils.execCommand(new String[]{"dumpsys power ->sdcard/TsLog/dump-" + time + SdkConstants.DOT_TXT}, false);
                    ShellUtils.execCommand(new String[]{"ps >sdcard/TsLog/dump-ps" + time + SdkConstants.DOT_TXT}, false);
                }
            });
        }
    }

    public static Bitmap createBarCode(CharSequence content, int BAR_WIDTH, int BAR_HEIGHT) {
        int i;
        BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;
        BitMatrix result = null;
        try {
            result = new MultiFormatWriter().encode(new StringBuilder().append(content).toString(), barcodeFormat, BAR_WIDTH, BAR_HEIGHT, (Map<EncodeHintType, ?>) null);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[(width * height)];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                int i2 = offset + x;
                if (result.get(x, y)) {
                    i = ViewCompat.MEASURED_STATE_MASK;
                } else {
                    i = -1;
                }
                pixels[i2] = i;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    @TargetApi(26)
    public static String getIMEI(Context context) {
        String propImei;
        TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
        String imei = null;
        if (Build.VERSION.SDK_INT < 21) {
            if (tm.getDeviceId() != null && tm.getDeviceId().length() > 5) {
                imei = tm.getDeviceId();
            }
        } else if (tm.getImei() != null && tm.getImei().length() > 5) {
            imei = tm.getImei();
        }
        if (imei != null && ((propImei = SystemProperties.get("persist.forfan.IMEI", (String) null)) == null || !propImei.equals(imei))) {
            SystemProperties.set("persist.forfan.IMEI", imei);
        }
        return imei;
    }

    @TargetApi(26)
    public static String getICCID(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
        if (tm.getSimSerialNumber() != null) {
            return tm.getSimSerialNumber();
        }
        return null;
    }
}
