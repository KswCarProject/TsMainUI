package com.suding.mirror.sdk;

import java.io.Serializable;

public class MirrorInitParams implements Serializable {
    private static final long serialVersionUID = 1;
    private String channelId;
    private String rfcommDevicePath;

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId2) {
        this.channelId = channelId2;
    }

    public String getRfcommDevicePath() {
        return this.rfcommDevicePath;
    }

    public void setRfcommDevicePath(String rfcommDevicePath2) {
        this.rfcommDevicePath = rfcommDevicePath2;
    }
}
