package com.android.settingslib.wrapper;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothCodecStatus;
import android.bluetooth.BluetoothDevice;

public class BluetoothA2dpWrapper {
    private BluetoothA2dp mService;

    public BluetoothA2dpWrapper(BluetoothA2dp service) {
        this.mService = service;
    }

    public BluetoothA2dp getService() {
        return this.mService;
    }

    public BluetoothCodecStatus getCodecStatus(BluetoothDevice device) {
        return this.mService.getCodecStatus(device);
    }

    public int supportsOptionalCodecs(BluetoothDevice device) {
        return this.mService.supportsOptionalCodecs(device);
    }

    public int getOptionalCodecsEnabled(BluetoothDevice device) {
        return this.mService.getOptionalCodecsEnabled(device);
    }

    public void setOptionalCodecsEnabled(BluetoothDevice device, int value) {
        this.mService.setOptionalCodecsEnabled(device, value);
    }
}
