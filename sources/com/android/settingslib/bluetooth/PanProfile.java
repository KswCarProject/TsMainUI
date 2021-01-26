package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothPan;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.util.Log;
import java.util.HashMap;
import java.util.List;

public class PanProfile implements LocalBluetoothProfile {
    static final String NAME = "PAN";
    private static final int ORDINAL = 4;
    private static final String TAG = "PanProfile";
    /* access modifiers changed from: private */
    public static boolean V = true;
    private final HashMap<BluetoothDevice, Integer> mDeviceRoleMap = new HashMap<>();
    /* access modifiers changed from: private */
    public boolean mIsProfileReady;
    private final LocalBluetoothAdapter mLocalAdapter;
    /* access modifiers changed from: private */
    public BluetoothPan mService;

    private final class PanServiceListener implements BluetoothProfile.ServiceListener {
        private PanServiceListener() {
        }

        /* synthetic */ PanServiceListener(PanProfile panProfile, PanServiceListener panServiceListener) {
            this();
        }

        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (PanProfile.V) {
                Log.d(PanProfile.TAG, "Bluetooth service connected");
            }
            PanProfile.this.mService = (BluetoothPan) proxy;
            PanProfile.this.mIsProfileReady = true;
        }

        public void onServiceDisconnected(int profile) {
            if (PanProfile.V) {
                Log.d(PanProfile.TAG, "Bluetooth service disconnected");
            }
            PanProfile.this.mIsProfileReady = false;
        }
    }

    public boolean isProfileReady() {
        return this.mIsProfileReady;
    }

    public int getProfileId() {
        return 5;
    }

    PanProfile(Context context, LocalBluetoothAdapter adapter) {
        this.mLocalAdapter = adapter;
        this.mLocalAdapter.getProfileProxy(context, new PanServiceListener(this, (PanServiceListener) null), 5);
    }

    public boolean isConnectable() {
        return true;
    }

    public boolean isAutoConnectable() {
        return false;
    }

    public boolean connect(BluetoothDevice device) {
        if (this.mService == null) {
            return false;
        }
        List<BluetoothDevice> sinks = this.mService.getConnectedDevices();
        if (sinks != null) {
            for (BluetoothDevice sink : sinks) {
                this.mService.disconnect(sink);
            }
        }
        return this.mService.connect(device);
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
        return this.mService.getConnectionState(device);
    }

    public boolean isPreferred(BluetoothDevice device) {
        return true;
    }

    public int getPreferred(BluetoothDevice device) {
        return -1;
    }

    public void setPreferred(BluetoothDevice device, boolean preferred) {
    }

    public String toString() {
        return NAME;
    }

    public int getOrdinal() {
        return 4;
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

    /* access modifiers changed from: package-private */
    public void setLocalRole(BluetoothDevice device, int role) {
        this.mDeviceRoleMap.put(device, Integer.valueOf(role));
    }

    /* access modifiers changed from: package-private */
    public boolean isLocalRoleNap(BluetoothDevice device) {
        if (!this.mDeviceRoleMap.containsKey(device)) {
            return false;
        }
        if (this.mDeviceRoleMap.get(device).intValue() == 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        if (V) {
            Log.d(TAG, "finalize()");
        }
        if (this.mService != null) {
            try {
                BluetoothAdapter.getDefaultAdapter().closeProfileProxy(5, this.mService);
                this.mService = null;
            } catch (Throwable t) {
                Log.w(TAG, "Error cleaning up PAN proxy", t);
            }
        }
    }
}
