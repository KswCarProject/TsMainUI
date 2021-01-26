package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHearingAid;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class HearingAidProfile implements LocalBluetoothProfile {
    static final String NAME = "HearingAid";
    private static final int ORDINAL = 1;
    private static final String TAG = "HearingAidProfile";
    /* access modifiers changed from: private */
    public static boolean V = true;
    private Context mContext;
    /* access modifiers changed from: private */
    public final CachedBluetoothDeviceManager mDeviceManager;
    /* access modifiers changed from: private */
    public boolean mIsProfileReady;
    /* access modifiers changed from: private */
    public final LocalBluetoothAdapter mLocalAdapter;
    /* access modifiers changed from: private */
    public final LocalBluetoothProfileManager mProfileManager;
    /* access modifiers changed from: private */
    public BluetoothHearingAid mService;

    private final class HearingAidServiceListener implements BluetoothProfile.ServiceListener {
        private HearingAidServiceListener() {
        }

        /* synthetic */ HearingAidServiceListener(HearingAidProfile hearingAidProfile, HearingAidServiceListener hearingAidServiceListener) {
            this();
        }

        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (HearingAidProfile.V) {
                Log.d(HearingAidProfile.TAG, "Bluetooth service connected");
            }
            HearingAidProfile.this.mService = (BluetoothHearingAid) proxy;
            List<BluetoothDevice> deviceList = HearingAidProfile.this.mService.getConnectedDevices();
            while (!deviceList.isEmpty()) {
                BluetoothDevice nextDevice = deviceList.remove(0);
                CachedBluetoothDevice device = HearingAidProfile.this.mDeviceManager.findDevice(nextDevice);
                if (device == null) {
                    if (HearingAidProfile.V) {
                        Log.d(HearingAidProfile.TAG, "HearingAidProfile found new device: " + nextDevice);
                    }
                    device = HearingAidProfile.this.mDeviceManager.addDevice(HearingAidProfile.this.mLocalAdapter, HearingAidProfile.this.mProfileManager, nextDevice);
                }
                device.onProfileStateChanged(HearingAidProfile.this, 2);
                device.refresh();
            }
            HearingAidProfile.this.mDeviceManager.updateHearingAidsDevices(HearingAidProfile.this.mProfileManager);
            HearingAidProfile.this.mIsProfileReady = true;
        }

        public void onServiceDisconnected(int profile) {
            if (HearingAidProfile.V) {
                Log.d(HearingAidProfile.TAG, "Bluetooth service disconnected");
            }
            HearingAidProfile.this.mIsProfileReady = false;
        }
    }

    public boolean isProfileReady() {
        return this.mIsProfileReady;
    }

    public int getProfileId() {
        return 21;
    }

    HearingAidProfile(Context context, LocalBluetoothAdapter adapter, CachedBluetoothDeviceManager deviceManager, LocalBluetoothProfileManager profileManager) {
        this.mContext = context;
        this.mLocalAdapter = adapter;
        this.mDeviceManager = deviceManager;
        this.mProfileManager = profileManager;
        this.mLocalAdapter.getProfileProxy(context, new HearingAidServiceListener(this, (HearingAidServiceListener) null), 21);
    }

    public boolean isConnectable() {
        return false;
    }

    public boolean isAutoConnectable() {
        return true;
    }

    public List<BluetoothDevice> getConnectedDevices() {
        if (this.mService == null) {
            return new ArrayList(0);
        }
        return this.mService.getDevicesMatchingConnectionStates(new int[]{2, 1, 3});
    }

    public boolean connect(BluetoothDevice device) {
        if (this.mService == null) {
            return false;
        }
        return this.mService.connect(device);
    }

    public boolean disconnect(BluetoothDevice device) {
        if (this.mService == null) {
            return false;
        }
        if (this.mService.getPriority(device) > 100) {
            this.mService.setPriority(device, 100);
        }
        return this.mService.disconnect(device);
    }

    public int getConnectionStatus(BluetoothDevice device) {
        if (this.mService == null) {
            return 0;
        }
        return this.mService.getConnectionState(device);
    }

    public boolean setActiveDevice(BluetoothDevice device) {
        if (this.mService == null) {
            return false;
        }
        return this.mService.setActiveDevice(device);
    }

    public List<BluetoothDevice> getActiveDevices() {
        if (this.mService == null) {
            return new ArrayList();
        }
        return this.mService.getActiveDevices();
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

    public int getVolume() {
        if (this.mService == null) {
            return 0;
        }
        return this.mService.getVolume();
    }

    public void setVolume(int volume) {
        if (this.mService != null) {
            this.mService.setVolume(volume);
        }
    }

    public long getHiSyncId(BluetoothDevice device) {
        if (this.mService == null) {
            return 0;
        }
        return this.mService.getHiSyncId(device);
    }

    public int getDeviceSide(BluetoothDevice device) {
        if (this.mService == null) {
            return 0;
        }
        return this.mService.getDeviceSide(device);
    }

    public int getDeviceMode(BluetoothDevice device) {
        if (this.mService == null) {
            return 0;
        }
        return this.mService.getDeviceMode(device);
    }

    public String toString() {
        return NAME;
    }

    public int getOrdinal() {
        return 1;
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
                BluetoothAdapter.getDefaultAdapter().closeProfileProxy(21, this.mService);
                this.mService = null;
            } catch (Throwable t) {
                Log.w(TAG, "Error cleaning up Hearing Aid proxy", t);
            }
        }
    }
}
