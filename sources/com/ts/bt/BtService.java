package com.ts.bt;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.bt.ITsBtService;
import java.util.List;

public class BtService extends Service {
    /* access modifiers changed from: private */
    public BtExe bt = BtExe.getBtInstance();
    private BtServiceBinder mBinder = new BtServiceBinder();

    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    public class BtServiceBinder extends ITsBtService.Stub {
        public BtServiceBinder() {
        }

        public String getBtDevName() throws RemoteException {
            return BtService.this.bt.getDevName();
        }

        public String getBtPhoneName() throws RemoteException {
            return BtService.this.bt.getPhoneName();
        }

        public List<PbInfo> getPbInfo() throws RemoteException {
            return BtService.this.bt.getPbInfo();
        }

        public void connect() throws RemoteException {
            BtService.this.bt.connect();
        }

        public void disconnect() throws RemoteException {
            BtService.this.bt.disconnect();
        }

        public void dial(String callNumber) throws RemoteException {
            BtService.this.bt.dial(callNumber);
        }

        public void hangup() throws RemoteException {
            BtService.this.bt.hangup();
        }

        public void answer() throws RemoteException {
            BtService.this.bt.answer();
        }

        public void audioSwitch() throws RemoteException {
            BtService.this.bt.audioSwitch();
        }

        public void downLoad() throws RemoteException {
            BtService.this.bt.downLoad();
        }

        public void micMute() throws RemoteException {
            BtService.this.bt.micMute();
        }

        public void sendDTMFCode(byte code) throws RemoteException {
            BtService.this.bt.sendDTMFCode(code);
        }

        public int getSta() throws RemoteException {
            return BtService.this.bt.getSta();
        }

        public String getLastPhoneNo() throws RemoteException {
            return BtService.this.bt.getLastPhoneNo();
        }

        public boolean isConnected() throws RemoteException {
            return BtService.this.bt.isConnected();
        }

        public void updateLastPhoneNum() throws RemoteException {
            BtService.this.bt.updateLastPhoneNum();
        }
    }
}
