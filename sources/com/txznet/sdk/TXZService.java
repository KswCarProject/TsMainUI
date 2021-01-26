package com.txznet.sdk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.txznet.Tr.T;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.Tr.Ty;
import com.txznet.txz.Tr.T;
import java.util.Map;

/* compiled from: Proguard */
public class TXZService extends Service {

    /* renamed from: T  reason: collision with root package name */
    static boolean f820T = false;

    /* compiled from: Proguard */
    interface T extends T.C0013T {
    }

    /* compiled from: Proguard */
    static class Tr extends T.C0021T {
        Tr() {
        }

        public byte[] T(String packageName, String command, byte[] data) throws RemoteException {
            if (!com.txznet.comm.Tr.Tr.T(packageName, command, data) || TextUtils.isEmpty(command)) {
                return null;
            }
            if ("com.txznet.txz".equals(packageName)) {
                if ("comm.exitTXZ.exited".equals(command)) {
                    Tn.T(packageName + " comm.exitTXZ.exited");
                    TXZService.f820T = true;
                    synchronized (TXZPowerManager.class) {
                        if (TXZPowerManager.f788T.booleanValue()) {
                            com.txznet.comm.Tr.Tn.Tr().T9 = true;
                        }
                    }
                    return null;
                } else if ("comm.exitTXZ.inited".equals(command)) {
                    Tn.T(packageName + " comm.exitTXZ.inited");
                    if (TXZPowerManager.f788T == null || !TXZPowerManager.f788T.booleanValue()) {
                        TXZService.f820T = false;
                        TXZConfigManager.getInstance().Tr();
                        return null;
                    }
                    Tn.Ty("release already, but txz inited again, release it");
                    TXZPowerManager.getInstance().releaseTXZ();
                    return null;
                }
            }
            byte[] T2 = Ty.T(packageName, command, data);
            for (Map.Entry<String, T.C0013T> entry : com.txznet.Tr.T.f355T.entrySet()) {
                if (command.startsWith(entry.getKey())) {
                    if (entry.getValue() != null) {
                        return entry.getValue().T(packageName, command.substring(entry.getKey().length()), data);
                    }
                    return T2;
                }
            }
            return T2;
        }
    }

    public IBinder onBind(Intent intent) {
        return new Tr();
    }

    static void T(String prefix, T processor) {
        com.txznet.Tr.T.T(prefix, processor);
    }
}
