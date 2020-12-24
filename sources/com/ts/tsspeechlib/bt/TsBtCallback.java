package com.ts.tsspeechlib.bt;

import java.util.List;

public interface TsBtCallback {
    void onBtConnectStateChange(int i);

    void onBtPbListChange(List<ITsSpeechBtPbInfo> list);

    void onBtStateChange(int i, String str);
}
