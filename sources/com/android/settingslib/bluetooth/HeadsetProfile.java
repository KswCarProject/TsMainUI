package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.Log;
import com.autochips.settingslib.utils.AtcUtils;
import java.util.ArrayList;
import java.util.List;

public class HeadsetProfile implements LocalBluetoothProfile {
    static final String NAME = "HEADSET";
    private static final int ORDINAL = 0;
    private static final String TAG = "HeadsetProfile";
    static final ParcelUuid[] UUIDS = {BluetoothUuid.HSP, BluetoothUuid.Handsfree};
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
    public BluetoothHeadset mService;

    private final class HeadsetServiceListener implements BluetoothProfile.ServiceListener {
        private HeadsetServiceListener() {
        }

        /* synthetic */ HeadsetServiceListener(HeadsetProfile headsetProfile, HeadsetServiceListener headsetServiceListener) {
            this();
        }

        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (HeadsetProfile.V) {
                Log.d(HeadsetProfile.TAG, "Bluetooth service connected");
            }
            HeadsetProfile.this.mService = (BluetoothHeadset) proxy;
            List<BluetoothDevice> deviceList = HeadsetProfile.this.mService.getConnectedDevices();
            while (!deviceList.isEmpty()) {
                BluetoothDevice nextDevice = deviceList.remove(0);
                CachedBluetoothDevice device = HeadsetProfile.this.mDeviceManager.findDevice(nextDevice);
                if (device == null) {
                    Log.w(HeadsetProfile.TAG, "HeadsetProfile found new device: " + nextDevice);
                    device = HeadsetProfile.this.mDeviceManager.addDevice(HeadsetProfile.this.mLocalAdapter, HeadsetProfile.this.mProfileManager, nextDevice);
                }
                device.onProfileStateChanged(HeadsetProfile.this, 2);
                device.refresh();
            }
            if (!AtcUtils.isAtcAOSPSupport()) {
                HeadsetProfile.this.mProfileManager.callServiceConnectedListeners();
            }
            HeadsetProfile.this.mIsProfileReady = true;
        }

        public void onServiceDisconnected(int profile) {
            if (HeadsetProfile.V) {
                Log.d(HeadsetProfile.TAG, "Bluetooth service disconnected");
            }
            if (!AtcUtils.isAtcAOSPSupport()) {
                HeadsetProfile.this.mProfileManager.callServiceDisconnectedListeners();
            }
            HeadsetProfile.this.mIsProfileReady = false;
        }
    }

    public boolean isProfileReady() {
        return this.mIsProfileReady;
    }

    public int getProfileId() {
        return 1;
    }

    HeadsetProfile(Context context, LocalBluetoothAdapter adapter, CachedBluetoothDeviceManager deviceManager, LocalBluetoothProfileManager profileManager) {
        this.mLocalAdapter = adapter;
        this.mDeviceManager = deviceManager;
        this.mProfileManager = profileManager;
        this.mLocalAdapter.getProfileProxy(context, new HeadsetServiceListener(this, (HeadsetServiceListener) null), 1);
    }

    public boolean isConnectable() {
        return true;
    }

    public boolean isAutoConnectable() {
        return true;
    }

    public boolean connect(BluetoothDevice device) {
        if (this.mService == null) {
            return false;
        }
        List<BluetoothDevice> sinks = this.mService.getConnectedDevices();
        if (sinks != null) {
            for (BluetoothDevice sink : sinks) {
                Log.d(TAG, "Not disconnecting device = " + sink);
            }
        }
        return this.mService.connect(device);
    }

    public boolean disconnect(BluetoothDevice device) {
        if (this.mService == null) {
            return false;
        }
        List<BluetoothDevice> deviceList = this.mService.getConnectedDevices();
        if (deviceList.isEmpty()) {
            return false;
        }
        for (BluetoothDevice dev : deviceList) {
            if (dev.equals(device)) {
                if (V) {
                    Log.d(TAG, "Downgrade priority as useris disconnecting the headset");
                }
                if (this.mService.getPriority(device) > 100) {
                    this.mService.setPriority(device, 100);
                }
                return this.mService.disconnect(device);
            }
        }
        return false;
    }

    public int getConnectionStatus(BluetoothDevice device) {
        if (this.mService == null) {
            return 0;
        }
        List<BluetoothDevice> deviceList = this.mService.getConnectedDevices();
        if (deviceList.isEmpty()) {
            return 0;
        }
        for (BluetoothDevice dev : deviceList) {
            if (dev.equals(device)) {
                return this.mService.getConnectionState(device);
            }
        }
        return 0;
    }

    public boolean setActiveDevice(BluetoothDevice device) {
        if (this.mService == null) {
            return false;
        }
        return this.mService.setActiveDevice(device);
    }

    public BluetoothDevice getActiveDevice() {
        if (this.mService == null) {
            return null;
        }
        return this.mService.getActiveDevice();
    }

    public boolean isAudioOn() {
        if (this.mService == null) {
            return false;
        }
        return this.mService.isAudioOn();
    }

    public int getAudioState(BluetoothDevice device) {
        if (this.mService == null) {
            return 10;
        }
        return this.mService.getAudioState(device);
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
        return 0;
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
                BluetoothAdapter.getDefaultAdapter().closeProfileProxy(1, this.mService);
                this.mService = null;
            } catch (Throwable t) {
                Log.w(TAG, "Error cleaning up HID proxy", t);
            }
        }
    }
}
