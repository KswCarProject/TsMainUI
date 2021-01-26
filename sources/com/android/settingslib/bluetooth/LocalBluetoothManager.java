package com.android.settingslib.bluetooth;

import android.content.Context;
import android.util.Log;

public class LocalBluetoothManager {
    private static final String TAG = "LocalBluetoothManager";
    private static LocalBluetoothManager sInstance;
    private final CachedBluetoothDeviceManager mCachedDeviceManager;
    private final Context mContext;
    private final BluetoothEventManager mEventManager;
    private Context mForegroundActivity;
    private final LocalBluetoothAdapter mLocalAdapter;
    private final LocalBluetoothProfileManager mProfileManager;

    public interface BluetoothManagerCallback {
        void onBluetoothManagerInitialized(Context context, LocalBluetoothManager localBluetoothManager);
    }

    public static synchronized LocalBluetoothManager getInstance(Context context, BluetoothManagerCallback onInitCallback) {
        LocalBluetoothManager localBluetoothManager;
        synchronized (LocalBluetoothManager.class) {
            if (sInstance == null) {
                LocalBluetoothAdapter adapter = LocalBluetoothAdapter.getInstance();
                if (adapter == null) {
                    localBluetoothManager = null;
                } else {
                    Context appContext = context.getApplicationContext();
                    sInstance = new LocalBluetoothManager(adapter, appContext);
                    if (onInitCallback != null) {
                        onInitCallback.onBluetoothManagerInitialized(appContext, sInstance);
                    }
                }
            }
            localBluetoothManager = sInstance;
        }
        return localBluetoothManager;
    }

    private LocalBluetoothManager(LocalBluetoothAdapter adapter, Context context) {
        this.mContext = context;
        this.mLocalAdapter = adapter;
        this.mCachedDeviceManager = new CachedBluetoothDeviceManager(context, this);
        this.mEventManager = new BluetoothEventManager(this.mLocalAdapter, this.mCachedDeviceManager, context);
        this.mProfileManager = new LocalBluetoothProfileManager(context, this.mLocalAdapter, this.mCachedDeviceManager, this.mEventManager);
        this.mEventManager.readPairedDevices();
    }

    public LocalBluetoothAdapter getBluetoothAdapter() {
        return this.mLocalAdapter;
    }

    public Context getContext() {
        return this.mContext;
    }

    public Context getForegroundActivity() {
        return this.mForegroundActivity;
    }

    public boolean isForegroundActivity() {
        return this.mForegroundActivity != null;
    }

    public synchronized void setForegroundActivity(Context context) {
        if (context != null) {
            Log.d(TAG, "setting foreground activity to non-null context");
            this.mForegroundActivity = context;
        } else if (this.mForegroundActivity != null) {
            Log.d(TAG, "setting foreground activity to null");
            this.mForegroundActivity = null;
        }
    }

    public CachedBluetoothDeviceManager getCachedDeviceManager() {
        return this.mCachedDeviceManager;
    }

    public BluetoothEventManager getEventManager() {
        return this.mEventManager;
    }

    public LocalBluetoothProfileManager getProfileManager() {
        return this.mProfileManager;
    }
}
