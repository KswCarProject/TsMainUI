package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHidDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.util.Log;
import java.util.List;

public class HidDeviceProfile implements LocalBluetoothProfile {
    private static final boolean DEBUG = true;
    static final String NAME = "HID DEVICE";
    private static final int ORDINAL = 18;
    private static final int PREFERRED_VALUE = -1;
    private static final String TAG = "HidDeviceProfile";
    /* access modifiers changed from: private */
    public final CachedBluetoothDeviceManager mDeviceManager;
    /* access modifiers changed from: private */
    public boolean mIsProfileReady;
    /* access modifiers changed from: private */
    public final LocalBluetoothAdapter mLocalAdapter;
    /* access modifiers changed from: private */
    public final LocalBluetoothProfileManager mProfileManager;
    /* access modifiers changed from: private */
    public BluetoothHidDevice mService;

    HidDeviceProfile(Context context, LocalBluetoothAdapter adapter, CachedBluetoothDeviceManager deviceManager, LocalBluetoothProfileManager profileManager) {
        this.mLocalAdapter = adapter;
        this.mDeviceManager = deviceManager;
        this.mProfileManager = profileManager;
        adapter.getProfileProxy(context, new HidDeviceServiceListener(this, (HidDeviceServiceListener) null), 19);
    }

    private final class HidDeviceServiceListener implements BluetoothProfile.ServiceListener {
        private HidDeviceServiceListener() {
        }

        /* synthetic */ HidDeviceServiceListener(HidDeviceProfile hidDeviceProfile, HidDeviceServiceListener hidDeviceServiceListener) {
            this();
        }

        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            Log.d(HidDeviceProfile.TAG, "Bluetooth service connected :-)");
            HidDeviceProfile.this.mService = (BluetoothHidDevice) proxy;
            for (BluetoothDevice nextDevice : HidDeviceProfile.this.mService.getConnectedDevices()) {
                CachedBluetoothDevice device = HidDeviceProfile.this.mDeviceManager.findDevice(nextDevice);
                if (device == null) {
                    Log.w(HidDeviceProfile.TAG, "HidProfile found new device: " + nextDevice);
                    device = HidDeviceProfile.this.mDeviceManager.addDevice(HidDeviceProfile.this.mLocalAdapter, HidDeviceProfile.this.mProfileManager, nextDevice);
                }
                Log.d(HidDeviceProfile.TAG, "Connection status changed: " + device);
                device.onProfileStateChanged(HidDeviceProfile.this, 2);
                device.refresh();
            }
            HidDeviceProfile.this.mIsProfileReady = true;
        }

        public void onServiceDisconnected(int profile) {
            Log.d(HidDeviceProfile.TAG, "Bluetooth service disconnected");
            HidDeviceProfile.this.mIsProfileReady = false;
        }
    }

    public boolean isProfileReady() {
        return this.mIsProfileReady;
    }

    public int getProfileId() {
        return 19;
    }

    public boolean isConnectable() {
        return true;
    }

    public boolean isAutoConnectable() {
        return false;
    }

    public boolean connect(BluetoothDevice device) {
        return false;
    }

    public boolean disconnect(BluetoothDevice device) {
        if (this.mService == null) {
            return false;
        }
        return this.mService.disconnect(device);
    }

    public int getConnectionStatus(BluetoothDevice device) {
        if (this.mService == null) {
            return 0;
        }
        List<BluetoothDevice> deviceList = this.mService.getConnectedDevices();
        if (deviceList.isEmpty() || !deviceList.contains(device)) {
            return 0;
        }
        return this.mService.getConnectionState(device);
    }

    public boolean isPreferred(BluetoothDevice device) {
        return getConnectionStatus(device) != 0;
    }

    public int getPreferred(BluetoothDevice device) {
        return -1;
    }

    public void setPreferred(BluetoothDevice device, boolean preferred) {
        if (!preferred) {
            this.mService.disconnect(device);
        }
    }

    public String toString() {
        return NAME;
    }

    public int getOrdinal() {
        return 18;
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
        Log.d(TAG, "finalize()");
        if (this.mService != null) {
            try {
                BluetoothAdapter.getDefaultAdapter().closeProfileProxy(19, this.mService);
                this.mService = null;
            } catch (Throwable t) {
                Log.w(TAG, "Error cleaning up HID proxy", t);
            }
        }
    }
}
