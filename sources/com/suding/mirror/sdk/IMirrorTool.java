package com.suding.mirror.sdk;

public interface IMirrorTool {
    void onMirrorSDKCoreConnected();

    void onMirrorSDKCoreDisconnected();

    void onSpeedPlayConnectState(int i, String str);
}
