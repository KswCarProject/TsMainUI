package com.qiner.appinfo;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.ts.main.common.tool;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppListUtil {
    private static final String TAG = "AppListUtil";
    protected static AppListUtil mInstance = null;
    private AppListDatabaseHelper appListDbHelper;
    private Context mContext;
    String[] mUnInstalledAppList = {"com.ts.MainUI", "com.ts.dvdplayer", "com.android.soundrecorder", "com.android.sdvdplayer", "com.android.settings", "com.android.calculator", "com.android.calendar", "com.example.specision", "RepliGo Reader", "com.cerience.reader.app", "net.easyconn", "com.ts.gpstest", "com.android.providers.downloads.ui", "com.autonavi.amapauto", "com.glsx.ddbox"};
    private PackageManager pm;

    public static class AppList {
        public int ischecked;
        public String packagename;
    }

    public AppListUtil() {
        Log.d(TAG, TAG);
    }

    public static AppListUtil getInstance() {
        if (mInstance == null) {
            mInstance = new AppListUtil();
        }
        return mInstance;
    }

    public void init(Context context) {
        this.mContext = context;
        this.appListDbHelper = new AppListDatabaseHelper(context, "AppList.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    public void insertAppList(String packagename, int ischecked) {
        Log.d(TAG, "insertAppList");
        if (this.appListDbHelper != null) {
            SQLiteDatabase db = this.appListDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("packagename", packagename);
            values.put("ischecked", Integer.valueOf(ischecked));
            db.insert("applist", (String) null, values);
            values.clear();
        }
    }

    public void updateAppList(String packagename, int ischecked) {
        Log.d(TAG, "updateAppList");
        if (this.appListDbHelper != null) {
            SQLiteDatabase db = this.appListDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("ischecked", Integer.valueOf(ischecked));
            Log.d("lh", "isSuccess=" + db.update("applist", values, "packagename=?", new String[]{packagename}));
            values.clear();
        }
    }

    public List<String> updateAppList() {
        List<String> pbList = new ArrayList<>();
        if (this.appListDbHelper != null) {
            Cursor cursor1 = this.appListDbHelper.getWritableDatabase().query("applist", (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            if (cursor1.moveToLast()) {
                do {
                    String packagename = cursor1.getString(cursor1.getColumnIndex("packagename"));
                    if (cursor1.getInt(cursor1.getColumnIndex("ischecked")) == 1) {
                        if (packagename.equals("com.android.vending")) {
                            pbList.add("com.google.android.gms");
                        }
                        pbList.add(packagename);
                    }
                } while (cursor1.moveToPrevious());
                cursor1.close();
            }
        }
        return pbList;
    }

    public void selectAllAppList(int selected) {
        if (this.appListDbHelper != null) {
            Cursor cursor1 = this.appListDbHelper.getWritableDatabase().query("applist", (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            if (cursor1.moveToLast()) {
                do {
                    String packagename = cursor1.getString(cursor1.getColumnIndex("packagename"));
                    if (cursor1.getInt(cursor1.getColumnIndex("ischecked")) != selected) {
                        updateAppList(packagename, selected);
                    }
                } while (cursor1.moveToPrevious());
                cursor1.close();
            }
        }
    }

    public int isChecked(String packagename) {
        int ischecked = 0;
        if (this.appListDbHelper != null) {
            new ArrayList();
            Cursor cursor1 = this.appListDbHelper.getWritableDatabase().query("applist", (String[]) null, "packagename=?", new String[]{packagename}, (String) null, (String) null, (String) null);
            if (cursor1.moveToLast()) {
                ischecked = cursor1.getInt(cursor1.getColumnIndex("ischecked"));
            }
            cursor1.close();
        }
        return ischecked;
    }

    public boolean isExistAppName(String packagename) {
        if (this.appListDbHelper != null) {
            Cursor cursor1 = this.appListDbHelper.getWritableDatabase().query("applist", (String[]) null, "packagename=?", new String[]{packagename}, (String) null, (String) null, (String) null);
            Log.d(TAG, "size = " + cursor1.getCount());
            if (cursor1.moveToNext()) {
                cursor1.close();
                return true;
            }
            cursor1.close();
        }
        return false;
    }

    public List<AppInfo> queryFilterAppInfo() {
        this.pm = this.mContext.getPackageManager();
        List<ApplicationInfo> listAppcations = this.pm.getInstalledApplications(8192);
        Collections.sort(listAppcations, new ApplicationInfo.DisplayNameComparator(this.pm));
        List<AppInfo> appInfos = new ArrayList<>();
        appInfos.clear();
        for (ApplicationInfo app : listAppcations) {
            if (isTsApp(app)) {
                appInfos.add(getAppInfo(app));
                if (!isExistAppName(app.packageName)) {
                    insertAppList(app.packageName, 0);
                }
            }
        }
        return appInfos;
    }

    private AppInfo getAppInfo(ApplicationInfo app) {
        AppInfo appInfo = new AppInfo();
        appInfo.setAppLabel((String) app.loadLabel(this.pm));
        appInfo.setAppIcon(app.loadIcon(this.pm));
        appInfo.setPkgName(app.packageName);
        return appInfo;
    }

    private boolean isTsApp(ApplicationInfo app) {
        for (String equals : this.mUnInstalledAppList) {
            if (app.packageName.equals(equals)) {
                return false;
            }
        }
        tool.GetInstance();
        if (tool.IsSysapk(app.packageName) == 0 && this.mContext.getPackageManager().getLaunchIntentForPackage(app.packageName) != null) {
            return true;
        }
        return false;
    }
}
