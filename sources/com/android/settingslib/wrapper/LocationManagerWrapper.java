package com.android.settingslib.wrapper;

import android.location.LocationManager;
import android.os.UserHandle;

public class LocationManagerWrapper {
    private LocationManager mLocationManager;

    public LocationManagerWrapper(LocationManager locationManager) {
        this.mLocationManager = locationManager;
    }

    public LocationManager getLocationManager() {
        return this.mLocationManager;
    }

    public boolean isProviderEnabled(String provider) {
        return this.mLocationManager.isProviderEnabled(provider);
    }

    public void setProviderEnabledForUser(String provider, boolean enabled, UserHandle userHandle) {
        this.mLocationManager.setProviderEnabledForUser(provider, enabled, userHandle);
    }

    public boolean isLocationEnabled() {
        return this.mLocationManager.isLocationEnabled();
    }

    public boolean isLocationEnabledForUser(UserHandle userHandle) {
        return this.mLocationManager.isLocationEnabledForUser(userHandle);
    }

    public void setLocationEnabledForUser(boolean enabled, UserHandle userHandle) {
        this.mLocationManager.setLocationEnabledForUser(enabled, userHandle);
    }
}
