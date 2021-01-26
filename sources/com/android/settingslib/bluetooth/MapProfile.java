package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothMap;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MapProfile implements LocalBluetoothProfile {
    static final String NAME = "MAP";
    private static final String TAG = "MapProfile";
    static final ParcelUuid[] UUIDS = {BluetoothUuid.MAP, BluetoothUuid.MNS, BluetoothUuid.MAS};
    /* access modifiers changed from: private */
    public static boolean V = true;
    /* access modifiers changed from: private */
    public final CachedBluetoothDeviceManager mDeviceManager;
    /* access modifiers changed from: private */
    public boolean mIsProfileReady;
    /* access modifiers changed from: private */
    public final LocalBluetoothAdapter mLocalAdapter;
    /* access modifiers changed from: private */
    public final LocalBluetoothProfileManager mProfileManager;
    /* access modifiers changed from: private */
    public BluetoothMap mService;

    private final class MapServiceListener implements BluetoothProfile.ServiceListener {
        private MapServiceListener() {
        }

        /* synthetic */ MapServiceListener(MapProfile mapProfile, MapServiceListener mapServiceListener) {
            this();
        }

        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (MapProfile.V) {
                Log.d(MapProfile.TAG, "Bluetooth service connected");
            }
            MapProfile.this.mService = (BluetoothMap) proxy;
            List<BluetoothDevice> deviceList = MapProfile.this.mService.getConnectedDevices();
            while (!deviceList.isEmpty()) {
                BluetoothDevice nextDevice = deviceList.remove(0);
                CachedBluetoothDevice device = MapProfile.this.mDeviceManager.findDevice(nextDevice);
                if (device == null) {
                    Log.w(MapProfile.TAG, "MapProfile found new device: " + nextDevice);
                    device = MapProfile.this.mDeviceManager.addDevice(MapProfile.this.mLocalAdapter, MapProfile.this.mProfileManager, nextDevice);
                }
                device.onProfileStateChanged(MapProfile.this, 2);
                device.refresh();
            }
            MapProfile.this.mProfileManager.callServiceConnectedListeners();
            MapProfile.this.mIsProfileReady = true;
        }

        public void onServiceDisconnected(int profile) {
            if (MapProfile.V) {
                Log.d(MapProfile.TAG, "Bluetooth service disconnected");
            }
            MapProfile.this.mProfileManager.callServiceDisconnectedListeners();
            MapProfile.this.mIsProfileReady = false;
        }
    }

    public boolean isProfileReady() {
        if (V) {
            Log.d(TAG, "isProfileReady(): " + this.mIsProfileReady);
        }
        return this.mIsProfileReady;
    }

    public int getProfileId() {
        return 9;
    }

    MapProfile(Context context, LocalBluetoothAdapter adapter, CachedBluetoothDeviceManager deviceManager, LocalBluetoothProfileManager profileManager) {
        this.mLocalAdapter = adapter;
        this.mDeviceManager = deviceManager;
        this.mProfileManager = profileManager;
        this.mLocalAdapter.getProfileProxy(context, new MapServiceListener(this, (MapServiceListener) null), 9);
    }

    public boolean isConnectable() {
        return true;
    }

    public boolean isAutoConnectable() {
        return true;
    }

    public boolean connect(BluetoothDevice device) {
        if (!V) {
            return false;
        }
        Log.d(TAG, "connect() - should not get called");
        return false;
    }

    public boolean disconnect(BluetoothDevice device) {
        if (this.mService == null) {
            return false;
        }
        List<BluetoothDevice> deviceList = this.mService.getConnectedDevices();
        if (deviceList.isEmpty() || !deviceList.get(0).equals(device)) {
            return false;
        }
        if (this.mService.getPriority(device) > 100) {
            this.mService.setPriority(device, 100);
        }
        return this.mService.disconnect(device);
    }

    public int getConnectionStatus(BluetoothDevice device) {
        int i;
        if (this.mService == null) {
            return 0;
        }
        List<BluetoothDevice> deviceList = this.mService.getConnectedDevices();
        if (V) {
            Log.d(TAG, "getConnectionStatus: status is: " + this.mService.getConnectionState(device));
        }
        if (deviceList.isEmpty() || !deviceList.get(0).equals(device)) {
            i = 0;
        } else {
            i = this.mService.getConnectionState(device);
        }
        return i;
    }

    public boolean isPreferred(BluetoothDevice device) {
        if (this.mService != null && this.mService.getPriority(device) > 0) {
            return true;
        }
        return false;
    }

    public int getPreferred(BluetoothDevice device) {
        if (this.mService == null) {
            return 0;
        }
        return this.mService.getPriority(device);
    }

    public void setPreferred(BluetoothDevice device, boolean preferred) {
        if (this.mService != null) {
            if (!preferred) {
                this.mService.setPriority(device, 0);
            } else if (this.mService.getPriority(device) < 100) {
                this.mService.setPriority(device, 100);
            }
        }
    }

    public List<BluetoothDevice> getConnectedDevices() {
        if (this.mService == null) {
            return new ArrayList(0);
        }
        return this.mService.getDevicesMatchingConnectionStates(new int[]{2, 1, 3});
    }

    public String toString() {
        return NAME;
    }

    public int getOrdinal() {
        return 9;
    }

    public int getNameResource(BluetoothDevice device) {
        return 0;
    }

    public int getSummaryResourceForDevice(BluetoothDevice device) {
        return 0;
    }

    public int getDrawableResource(BluetoothClass btClass) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        if (V) {
            Log.d(TAG, "finalize()");
        }
        if (this.mService != null) {
            try {
                BluetoothAdapter.getDefaultAdapter().closeProfileProxy(9, this.mService);
                this.mService = null;
            } catch (Throwable t) {
                Log.w(TAG, "Error cleaning up MAP proxy", t);
            }
        }
    }
}
