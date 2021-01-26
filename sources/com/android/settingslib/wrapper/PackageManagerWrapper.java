package com.android.settingslib.wrapper;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.os.storage.VolumeInfo;
import java.util.List;

public class PackageManagerWrapper {
    private final PackageManager mPm;

    public PackageManagerWrapper(PackageManager pm) {
        this.mPm = pm;
    }

    public PackageManager getPackageManager() {
        return this.mPm;
    }

    public List<ApplicationInfo> getInstalledApplicationsAsUser(int flags, int userId) {
        return this.mPm.getInstalledApplicationsAsUser(flags, userId);
    }

    public List<PackageInfo> getInstalledPackagesAsUser(int flags, int userId) {
        return this.mPm.getInstalledPackagesAsUser(flags, userId);
    }

    public boolean hasSystemFeature(String name) {
        return this.mPm.hasSystemFeature(name);
    }

    public List<ResolveInfo> queryIntentActivitiesAsUser(Intent intent, int flags, int userId) {
        return this.mPm.queryIntentActivitiesAsUser(intent, flags, userId);
    }

    public int getInstallReason(String packageName, UserHandle user) {
        return this.mPm.getInstallReason(packageName, user);
    }

    public ApplicationInfo getApplicationInfoAsUser(String packageName, int i, int userId) throws PackageManager.NameNotFoundException {
        return this.mPm.getApplicationInfoAsUser(packageName, i, userId);
    }

    public boolean setDefaultBrowserPackageNameAsUser(String packageName, int userId) {
        return this.mPm.setDefaultBrowserPackageNameAsUser(packageName, userId);
    }

    public String getDefaultBrowserPackageNameAsUser(int userId) {
        return this.mPm.getDefaultBrowserPackageNameAsUser(userId);
    }

    public ComponentName getHomeActivities(List<ResolveInfo> homeActivities) {
        return this.mPm.getHomeActivities(homeActivities);
    }

    public List<ResolveInfo> queryIntentServicesAsUser(Intent intent, int i, int user) {
        return this.mPm.queryIntentServicesAsUser(intent, i, user);
    }

    public List<ResolveInfo> queryIntentServices(Intent intent, int i) {
        return this.mPm.queryIntentServices(intent, i);
    }

    public void replacePreferredActivity(IntentFilter homeFilter, int matchCategoryEmpty, ComponentName[] componentNames, ComponentName component) {
        this.mPm.replacePreferredActivity(homeFilter, matchCategoryEmpty, componentNames, component);
    }

    public PackageInfo getPackageInfo(String packageName, int i) throws PackageManager.NameNotFoundException {
        return this.mPm.getPackageInfo(packageName, i);
    }

    public Drawable getUserBadgedIcon(ApplicationInfo info) {
        return this.mPm.getUserBadgedIcon(this.mPm.loadUnbadgedItemIcon(info, info), new UserHandle(UserHandle.getUserId(info.uid)));
    }

    public CharSequence loadLabel(ApplicationInfo app) {
        return app.loadLabel(this.mPm);
    }

    public List<ResolveInfo> queryIntentActivities(Intent intent, int flags) {
        return this.mPm.queryIntentActivities(intent, flags);
    }

    public VolumeInfo getPrimaryStorageCurrentVolume() {
        return this.mPm.getPrimaryStorageCurrentVolume();
    }

    public void deletePackageAsUser(String packageName, IPackageDeleteObserver observer, int flags, int userId) {
        this.mPm.deletePackageAsUser(packageName, observer, flags, userId);
    }

    public int getPackageUidAsUser(String pkg, int userId) throws PackageManager.NameNotFoundException {
        return this.mPm.getPackageUidAsUser(pkg, userId);
    }

    public void setApplicationEnabledSetting(String packageName, int newState, int flags) {
        this.mPm.setApplicationEnabledSetting(packageName, newState, flags);
    }

    public int getApplicationEnabledSetting(String packageName) {
        return this.mPm.getApplicationEnabledSetting(packageName);
    }

    public void setComponentEnabledSetting(ComponentName componentName, int newState, int flags) {
        this.mPm.setComponentEnabledSetting(componentName, newState, flags);
    }

    public ApplicationInfo getApplicationInfo(String packageName, int flags) throws PackageManager.NameNotFoundException {
        return this.mPm.getApplicationInfo(packageName, flags);
    }

    public CharSequence getApplicationLabel(ApplicationInfo info) {
        return this.mPm.getApplicationLabel(info);
    }

    public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int flags) {
        return this.mPm.queryBroadcastReceivers(intent, flags);
    }
}
