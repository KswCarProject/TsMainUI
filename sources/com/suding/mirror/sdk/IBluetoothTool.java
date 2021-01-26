package com.suding.mirror.sdk;

import java.util.List;

public interface IBluetoothTool {
    int onRequestBluetoothConnectState();

    void onRequestCloseRfcomm();

    void onRequestDisconnectBluetooth();

    byte[] onRequestLocalBluetoothMacAddress();

    int onRequestMpuBackCarStatus();

    void onRequestOpenRfcomm(byte[] bArr, String str);

    List<String> onRequestPairedBluetoothUuids();

    byte[] onRequestPairedPhoneBluetoothMacAddress();

    int onRequestRfcommConnectState();

    void onRequestWriteRfcomm(byte[] bArr);

    void onSpeedPlayConnectState(int i, String str);
}
