package net.easyconn.platform.wrc.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: BluetoothReceiver */
public class b extends BroadcastReceiver {
    private j a;

    public b(j jVar) {
        this.a = jVar;
    }

    public void onReceive(Context context, Intent intent) {
        switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1)) {
            case 10:
                if (this.a != null) {
                    this.a.e();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
