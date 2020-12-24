package com.broadfly.settings;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.broadfly.settings.ISettingsService;
import com.ts.MainUI.Evc;
import com.ts.bt.BtExe;

public class SettingsService extends Service {
    private ISettings mISettings = new ISettings();

    class ISettings extends ISettingsService.Stub {
        ISettings() {
        }

        public boolean requestBTFocus() throws RemoteException {
            Log.i("xxx", "requestBTFocus");
            if (!BtExe.getBtInstance().isConnected()) {
                return false;
            }
            Evc.GetInstance().evol_workmode_set(5);
            return true;
        }

        public boolean abandonBTFocus() throws RemoteException {
            return false;
        }

        public void takePhone(String number) throws RemoteException {
            if (number != null) {
                BtExe.getBtInstance().dial(number);
            }
        }

        public boolean isBTconnect() throws RemoteException {
            return BtExe.getBtInstance().isConnected();
        }
    }

    public IBinder onBind(Intent arg0) {
        Log.i(" ISet", "ISettings");
        return this.mISettings;
    }
}
