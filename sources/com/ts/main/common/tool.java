package com.ts.main.common;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import android.util.Xml;
import com.hongfans.carmedia.Constant;
import com.qiner.appinfo.AppListUtil;
import com.ts.MainUI.R;
import com.ts.MainUI.TsFile;
import com.ts.main.common.ShellUtils;
import com.txznet.sdk.TXZCameraManager;
import com.txznet.sdk.music.MusicInvokeConstants;
import com.yyw.ts70xhw.Mcu;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

public class tool {
    private static final String ACTION_WRITE_XML = "com.ts.action.launcher.action.writexml";
    static tool Mytool = null;
    private static final String TAG = "tool";
    static final String TS_MAP_FILE = "/TsMap/amapauto8";
    static final String TS_MAP_PATH = "mnt/sdcard/amapauto8";
    public static Context mContext = null;
    String MapPath;
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
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : ((ActivityManager) mContext.getSystemService("activity")).getRunningAppProcesses()) {
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

    /* access modifiers changed from: package-private */
    public int GetProcessID(String Pname) {
        if (mContext != null) {
            for (ActivityManager.RunningAppProcessInfo appProcessInfo : ((ActivityManager) mContext.getSystemService("activity")).getRunningAppProcesses()) {
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
        String[] AllKillStr = {"com.mediatek.filemanager", "com.android.settings", "com.android.browser", "es.voghdev.pdfviewpager", "com.ts.gpstest"};
        String[] ValidStr = {"com.zbx.ct.tvzhibo", "com.txznet.music.close.app", Constant.PACKAGE_NAME, "com.kugou.android", "com.tencent.qqmusic", "com.ximalaya.ting.android.car", "net.easyconn", "cn.kuwo.kwmusiccar", "com.mxtech.videoplayer.pro", "com.qiyi.video.pad", "com.edog.car"};
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
        }
    }

    public boolean killProcess(final String packageName) {
        if (GetProcessID(packageName) == 1) {
            return false;
        }
        Log.i(TAG, "kill process start==" + packageName);
        new Thread() {
            public void run() {
                try {
                    if (tool.mContext != null) {
                        Class.forName("android.app.ActivityManager").getMethod("forceStopPackage", new Class[]{String.class}).invoke((ActivityManager) tool.mContext.getSystemService("activity"), new Object[]{packageName});
                        Log.e(tool.TAG, "kill process by aios-adapter!!==" + packageName);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return true;
    }

    public void killAllProcess(int nSate) {
        Log.i(TAG, "killAllProcess: start==" + nSate);
        List<String> WhiteValidStr = new ArrayList<>(Arrays.asList(new String[]{"com.txznet.music.close.app", Constant.PACKAGE_NAME, "com.kugou.android", "com.tencent.qqmusic", "com.into.stability", "com.ximalaya.ting.android.car", "com.edog.car", "com.didi365.miudrive.navi", "net.easyconn", "cn.kuwo.kwmusiccar", "com.mxtech.videoplayer.pro", "com.zbx.ct.tvzhibo", "com.qiyi.video.pad", "com.android.sdvdplayer", "com.iflytek.inputmethod", "com.txznet.txz", "com.glsx.ddbox", "com.autonavi.amapauto"}));
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
        String[] commands = {""};
        commands[0] = Str;
        ShellUtils.CommandResult result = ShellUtils.execCommand(commands, true);
        Log.v(TAG, "DealSu [" + Str + "] result==" + result.result);
        if (result.errorMsg != null && result.errorMsg.length() > 0) {
            Log.v(TAG, "DealSu errorMsg==" + result.errorMsg);
        }
    }

    public void RootSystem() {
        ShellUtils.CommandResult result = ShellUtils.execCommand(new String[]{"mount -o rw,remount /system", "chmod 0755 /system"}, true);
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
    public long readSystem() {
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

    public void SucopyFolder(String oldPath, String newPath) {
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
                    MainAlterwin.GetInstance().UpdateLoadWinStr(String.valueOf(temp.getName()) + " " + ((temp.length() / 1024) / 1024) + "MB");
                    ChannelCpyData(temp.getPath(), String.valueOf(newPath) + "/" + temp.getName().toString());
                }
                if (temp.isDirectory()) {
                    SucopyFolder(String.valueOf(oldPath) + "/" + file[i], String.valueOf(newPath) + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();
        }
    }

    public void CopyMapdata() {
        Mcu.SendXKey(21);
        TsFile.deleteFile(new File(TS_MAP_PATH));
        new Thread() {
            public void run() {
                try {
                    tool.this.SucopyFolder(tool.this.MapPath, tool.TS_MAP_PATH);
                } catch (Exception e) {
                    Log.e("Exception when sendPointerSync", e.toString());
                }
            }
        }.start();
    }

    public void MounKeyTest() {
        new Thread() {
            public void run() {
                try {
                    tool.this.DealSu("monkey  --throttle 500 --pct-touch 100 --pct-motion 0 -v -v 100000 >" + Environment.getExternalStorageDirectory() + "/monkey.txt");
                } catch (Exception e) {
                    Log.e("Exception when sendPointerSync", e.toString());
                }
            }
        }.start();
    }

    /* access modifiers changed from: package-private */
    public long GetSdMapSize() {
        return getDirSize(new File(TS_MAP_PATH));
    }

    public boolean CheckMapData() {
        if (mContext == null) {
            return false;
        }
        String[] strSDMountedPath = MainSet.GetInstance().GetMountedStorage();
        Log.i(TAG, "strSDMountedPath.length ==" + strSDMountedPath.length);
        for (int i = 0; i < strSDMountedPath.length; i++) {
            if (TsFile.fileIsExists(String.valueOf(strSDMountedPath[i]) + TS_MAP_FILE)) {
                this.MapPath = String.valueOf(strSDMountedPath[i]) + TS_MAP_FILE;
                this.totalSize = getDirSize(new File(String.valueOf(strSDMountedPath[i]) + TS_MAP_FILE));
                if (TsFile.fileIsExists(TS_MAP_PATH)) {
                    if (GetSdMapSize() == this.totalSize) {
                        MainAlterwin.GetInstance().ShowLoadWin(mContext.getResources().getString(R.string.copy_data_is_eques), false);
                    } else {
                        MainAlterwin.GetInstance().ShowLoadWin(mContext.getResources().getString(R.string.copy_data_is_noteques), true);
                    }
                } else if (readSystem() < this.totalSize) {
                    MainAlterwin.GetInstance().ShowLoadWin(mContext.getResources().getString(R.string.copy_data_space_notenough), false);
                } else {
                    MainAlterwin.GetInstance().ShowLoadWin(String.valueOf(mContext.getResources().getString(R.string.copy_data_size)) + ((getDirSize(new File(String.valueOf(strSDMountedPath[i]) + TS_MAP_FILE)) / 1024) / 1024) + "MB", true);
                }
                return true;
            }
        }
        return false;
    }

    public void checkDspUpdate(String path) {
        if (path != null) {
            File file = new File(String.valueOf(path) + "/tsdsp.bin");
            if (file.exists()) {
                try {
                    Intent intent = new Intent();
                    intent.setComponent(new ComponentName("com.ts.MainUI", "com.ts.main.dsp.DspUpdateActivity"));
                    intent.putExtra(TXZCameraManager.REMOTE_NAME_VIDEO_PATH, file.getAbsolutePath());
                    intent.addFlags(268468224);
                    mContext.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void exportLauncherInfo(int sdorUsb) {
        String exportPath;
        if (sdorUsb == 1) {
            exportPath = getExportPath("/udisk");
        } else {
            exportPath = getExportPath("/sdcard");
        }
        if (exportPath != null) {
            Intent intent = new Intent(ACTION_WRITE_XML);
            intent.putExtra(MusicInvokeConstants.KEY_PUSH_VERSION, getHMIVersion());
            intent.putExtra(TXZCameraManager.REMOTE_NAME_VIDEO_PATH, exportPath);
            mContext.sendBroadcast(intent);
        }
    }

    private String getExportPath(String sortName) {
        for (File file : new File("/storage").listFiles()) {
            if (!file.isHidden() && file.isDirectory() && !file.getName().equals("sdcard0") && file.canWrite() && file.getAbsolutePath().contains(sortName)) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }

    public void importLauncherInfo() {
        File[] files = new File("/storage").listFiles();
        String filePath = "/sdcard/launcher.xml";
        int length = files.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            File file = files[i];
            if (file.isHidden() || !file.isDirectory() || !file.canRead()) {
                i++;
            } else {
                File file2 = new File(String.valueOf(file.getAbsolutePath()) + "/launcher.xml");
                if (file2.exists()) {
                    filePath = file2.getAbsolutePath();
                }
            }
        }
        if (filePath != null) {
            XmlPullParser parser = Xml.newPullParser();
            String HMIVersion = null;
            try {
                parser.setInput(new FileInputStream(new File(filePath)), "UTF-8");
                String tag = null;
                for (int evtType = parser.getEventType(); evtType != 1 && HMIVersion == null; evtType = parser.next()) {
                    switch (evtType) {
                        case 2:
                            tag = parser.getName();
                            break;
                        case 4:
                            if (tag != null && tag.equals(MusicInvokeConstants.KEY_PUSH_VERSION)) {
                                HMIVersion = parser.getText();
                                break;
                            }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String customHMI = getHMIVersion();
            if (HMIVersion != null && customHMI.equals(HMIVersion)) {
                RootSystem();
                DealSu("mkdir /system/tsoem");
                DealSu("cp " + filePath + " /system/tsoem/launcher.xml");
                UnRootSystem();
            }
        }
    }

    private String getHMIVersion() {
        String customShow = mContext.getResources().getString(R.string.custom_num_show);
        if (customShow.equals("TSKJ")) {
            customShow = mContext.getResources().getString(R.string.custom_num);
        }
        return String.valueOf(customShow) + MainSet.GetHMIVersion();
    }

    public void CheckAllPermision() {
        long ClockTime = SystemClock.uptimeMillis();
        List<PackageInfo> packs = mContext.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if (p.versionName != null && !p.packageName.startsWith("com.mediatek.") && !p.packageName.startsWith("com.ts.") && !p.packageName.startsWith("com.forfan.")) {
                Log.i(TAG, "==" + p.packageName);
                CheckPermision(p.packageName);
            }
        }
        Log.i(TAG, "CheckPermision time " + (SystemClock.uptimeMillis() - ClockTime));
    }

    public void CheckPermision(String packages) {
        String[] checkStr = {"android.permission.READ_CALENDAR", "android.permission.WRITE_CALENDAR", "android.permission.GET_ACCOUNTS", "android.permission.WRITE_CONTACTS", "android.permission.READ_CONTACTS", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.RECORD_AUDIO", "android.permission.READ_PHONE_STATE", "android.permission.CALL_PHONE", "android.permission.READ_CALL_LOG", "android.permission.WRITE_CALL_LOG", "android.permission.ADD_VOICEMAIL", "android.permission.USE_SIP", "android.permission.PROCESS_OUTGOING_CALLS", "android.permission.BODY_SENSORS", "android.permission.RECORD_AUDIO", "android.permission.CAMERA", "android.permission.SEND_SMS", "android.permission.RECEIVE_SMS", "android.permission.READ_SMS", "android.permission.RECEIVE_WAP_PUSH", "android.permission.RECEIVE_MMS", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        if (mContext != null) {
            PackageManager pm = mContext.getPackageManager();
            try {
                String[] permissions = pm.getPackageInfo(packages, 4096).requestedPermissions;
                if (permissions != null) {
                    for (String str : permissions) {
                        for (String equals : checkStr) {
                            if (equals.equals(str)) {
                                boolean permission = pm.checkPermission(str, packages) == 0;
                                if (!permission) {
                                    Log.i(TAG, String.valueOf(str) + "==" + permission);
                                    DealSu("pm grant " + packages + " " + str);
                                }
                            }
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
