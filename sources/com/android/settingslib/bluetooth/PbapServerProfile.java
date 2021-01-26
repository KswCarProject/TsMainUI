package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothPbap;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;

public class PbapServerProfile implements LocalBluetoothProfile {
    @VisibleForTesting
    public static final String NAME = "PBAP Server";
    private static final int ORDINAL = 6;
    static final ParcelUuid[] PBAB_CLIENT_UUIDS = {BluetoothUuid.HSP, BluetoothUuid.Handsfree, BluetoothUuid.PBAP_PCE};
    private static final String TAG = "PbapServerProfile";
    /* access modifiers changed from: private */
    public static boolean V = true;
    /* access modifiers changed from: private */
    public boolean mIsProfileReady;
    /* access modifiers changed from: private */
    public BluetoothPbap mService;

    private final class PbapServiceListener implements BluetoothPbap.ServiceListener {
        private PbapServiceListener() {
        }

        /* synthetic */ PbapServiceListener(PbapServerProfile pbapServerProfile, PbapServiceListener pbapServiceListener) {
            this();
        }

        public void onServiceConnected(BluetoothPbap proxy) {
            if (PbapServerProfile.V) {
                Log.d(PbapServerProfile.TAG, "Bluetooth service connected");
            }
            PbapServerProfile.this.mService = proxy;
            PbapServerProfile.this.mIsProfileReady = true;
        }

        public void onServiceDisconnected() {
            if (PbapServerProfile.V) {
                Log.d(PbapServerProfile.TAG, "Bluetooth service disconnected");
            }
            PbapServerProfile.this.mIsProfileReady = false;
        }
    }

    public boolean isProfileReady() {
        return this.mIsProfileReady;
    }

    public int getProfileId() {
        return 6;
    }

    PbapServerProfile(Context context) {
        new BluetoothPbap(context, new PbapServiceListener(this, (PbapServiceListener) null));
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
        if (this.mService != null && this.mService.isConnected(device)) {
            return 2;
        }
        return 0;
    }

    public boolean isPreferred(BluetoothDevice device) {
        return false;
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
        return 6;
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
                this.mService.close();
                this.mService = null;
            } catch (Throwable t) {
                Log.w(TAG, "Error cleaning up PBAP proxy", t);
            }
        }
    }
}
