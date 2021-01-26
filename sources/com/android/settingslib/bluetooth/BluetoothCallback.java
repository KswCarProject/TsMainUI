package com.android.settingslib.bluetooth;

public interface BluetoothCallback {
    void onActiveDeviceChanged(CachedBluetoothDevice cachedBluetoothDevice, int i);

    void onAudioModeChanged();

    void onBluetoothStateChanged(int i);

    void onConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i);

    void onDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice);

    void onDeviceBondStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i);

    void onDeviceDeleted(CachedBluetoothDevice cachedBluetoothDevice);

    void onProfileConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i, int i2);

    void onScanningStateChanged(boolean z);
}
