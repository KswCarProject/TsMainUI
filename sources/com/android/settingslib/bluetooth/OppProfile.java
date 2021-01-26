package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;

final class OppProfile implements LocalBluetoothProfile {
    static final String NAME = "OPP";
    private static final int ORDINAL = 2;

    OppProfile() {
    }

    public boolean isConnectable() {
        return false;
    }

    public boolean isAutoConnectable() {
        return false;
    }

    public boolean connect(BluetoothDevice device) {
        return false;
    }

    public boolean disconnect(BluetoothDevice device) {
        return false;
    }

    public int getConnectionStatus(BluetoothDevice device) {
        return 0;
    }

    public boolean isPreferred(BluetoothDevice device) {
        return false;
    }

    public int getPreferred(BluetoothDevice device) {
        return 0;
    }

    public void setPreferred(BluetoothDevice device, boolean preferred) {
    }

    public boolean isProfileReady() {
        return true;
    }

    public int getProfileId() {
        return 20;
    }

    public String toString() {
        return NAME;
    }

    public int getOrdinal() {
        return 2;
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
}
