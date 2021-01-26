package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothCodecConfig;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.wrapper.BluetoothA2dpWrapper;
import com.txznet.sdk.TXZResourceManager;
import java.util.ArrayList;
import java.util.List;

public class A2dpProfile implements LocalBluetoothProfile {
    static final String NAME = "A2DP";
    private static final int ORDINAL = 1;
    static final ParcelUuid[] SINK_UUIDS = {BluetoothUuid.AudioSink, BluetoothUuid.AdvAudioDist};
    private static final String TAG = "A2dpProfile";
    /* access modifiers changed from: private */
    public static boolean V = false;
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
    public BluetoothA2dp mService;
    /* access modifiers changed from: private */
    public BluetoothA2dpWrapper mServiceWrapper;

    private final class A2dpServiceListener implements BluetoothProfile.ServiceListener {
        private A2dpServiceListener() {
        }

        /* synthetic */ A2dpServiceListener(A2dpProfile a2dpProfile, A2dpServiceListener a2dpServiceListener) {
            this();
        }

        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (A2dpProfile.V) {
                Log.d(A2dpProfile.TAG, "Bluetooth service connected");
            }
            A2dpProfile.this.mService = (BluetoothA2dp) proxy;
            A2dpProfile.this.mServiceWrapper = new BluetoothA2dpWrapper(A2dpProfile.this.mService);
            List<BluetoothDevice> deviceList = A2dpProfile.this.mService.getConnectedDevices();
            while (!deviceList.isEmpty()) {
                BluetoothDevice nextDevice = deviceList.remove(0);
                CachedBluetoothDevice device = A2dpProfile.this.mDeviceManager.findDevice(nextDevice);
                if (device == null) {
                    Log.w(A2dpProfile.TAG, "A2dpProfile found new device: " + nextDevice);
                    device = A2dpProfile.this.mDeviceManager.addDevice(A2dpProfile.this.mLocalAdapter, A2dpProfile.this.mProfileManager, nextDevice);
                }
                device.onProfileStateChanged(A2dpProfile.this, 2);
                device.refresh();
            }
            A2dpProfile.this.mIsProfileReady = true;
        }

        public void onServiceDisconnected(int profile) {
            if (A2dpProfile.V) {
                Log.d(A2dpProfile.TAG, "Bluetooth service disconnected");
            }
            A2dpProfile.this.mIsProfileReady = false;
        }
    }

    public boolean isProfileReady() {
        return this.mIsProfileReady;
    }

    public int getProfileId() {
        return 2;
    }

    A2dpProfile(Context context, LocalBluetoothAdapter adapter, CachedBluetoothDeviceManager deviceManager, LocalBluetoothProfileManager profileManager) {
        this.mContext = context;
        this.mLocalAdapter = adapter;
        this.mDeviceManager = deviceManager;
        this.mProfileManager = profileManager;
        this.mLocalAdapter.getProfileProxy(context, new A2dpServiceListener(this, (A2dpServiceListener) null), 2);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setBluetoothA2dpWrapper(BluetoothA2dpWrapper wrapper) {
        this.mServiceWrapper = wrapper;
    }

    public boolean isConnectable() {
        return true;
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
        List<BluetoothDevice> sinks;
        if (this.mService == null) {
            return false;
        }
        if (this.mLocalAdapter.getMaxConnectedAudioDevices() == 1 && (sinks = getConnectedDevices()) != null) {
            for (BluetoothDevice sink : sinks) {
                if (sink.equals(device)) {
                    Log.w(TAG, "Connecting to device " + device + " : disconnect skipped");
                } else {
                    this.mService.disconnect(sink);
                }
            }
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

    public BluetoothDevice getActiveDevice() {
        if (this.mService == null) {
            return null;
        }
        return this.mService.getActiveDevice();
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

    /* access modifiers changed from: package-private */
    public boolean isA2dpPlaying() {
        if (this.mService == null) {
            return false;
        }
        for (BluetoothDevice device : this.mService.getConnectedDevices()) {
            if (this.mService.isA2dpPlaying(device)) {
                return true;
            }
        }
        return false;
    }

    public boolean supportsHighQualityAudio(BluetoothDevice device) {
        if (this.mServiceWrapper.supportsOptionalCodecs(device) == 1) {
            return true;
        }
        return false;
    }

    public boolean isHighQualityAudioEnabled(BluetoothDevice device) {
        int enabled = this.mServiceWrapper.getOptionalCodecsEnabled(device);
        if (enabled != -1) {
            if (enabled == 1) {
                return true;
            }
            return false;
        } else if (getConnectionStatus(device) != 2 && supportsHighQualityAudio(device)) {
            return true;
        } else {
            BluetoothCodecConfig codecConfig = null;
            if (this.mServiceWrapper.getCodecStatus(device) != null) {
                codecConfig = this.mServiceWrapper.getCodecStatus(device).getCodecConfig();
            }
            if (codecConfig == null || codecConfig.isMandatoryCodec()) {
                return false;
            }
            return true;
        }
    }

    public void setHighQualityAudioEnabled(BluetoothDevice device, boolean enabled) {
        int prefValue;
        if (enabled) {
            prefValue = 1;
        } else {
            prefValue = 0;
        }
        this.mServiceWrapper.setOptionalCodecsEnabled(device, prefValue);
        if (getConnectionStatus(device) == 2) {
            if (enabled) {
                this.mService.enableOptionalCodecs(device);
            } else {
                this.mService.disableOptionalCodecs(device);
            }
        }
    }

    public String getHighQualityAudioOptionLabel(BluetoothDevice device) {
        return TXZResourceManager.STYLE_DEFAULT;
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
                BluetoothAdapter.getDefaultAdapter().closeProfileProxy(2, this.mService);
                this.mService = null;
            } catch (Throwable t) {
                Log.w(TAG, "Error cleaning up A2DP proxy", t);
            }
        }
    }
}
