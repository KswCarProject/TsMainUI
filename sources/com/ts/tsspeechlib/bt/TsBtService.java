package com.ts.tsspeechlib.bt;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.bt.BtExe;
import com.ts.tsspeechlib.bt.ITsSpeechBt;
import java.util.List;

public class TsBtService extends Service {
    private static final String ACTION_CLOSE_BT = "com.ts.speech.action.closebt";
    private static final String ACTION_CLOSE_BTMUISC = "com.ts.speech.action.closebtmusic";
    public static final String BROADCAST_LANCHER_FUNC_CLOSEMEDIA = "forfan.intent.action.CLOSEMEDIA";
    private mTsSpeechBt mBinder;

    public IBinder onBind(Intent arg0) {
        if (this.mBinder == null) {
            this.mBinder = new mTsSpeechBt();
        }
        return this.mBinder;
    }

    public class mTsSpeechBt extends ITsSpeechBt.Stub {
        public mTsSpeechBt() {
        }

        public void onCallPhone(String number) throws RemoteException {
            BtExe.getBtInstance().dial(number);
        }

        public void onAnswerPhone() throws RemoteException {
            BtExe.getBtInstance().answer();
        }

        public void onHangupPhone() throws RemoteException {
            BtExe.getBtInstance().hangup();
        }

        public int getBtConnectState() throws RemoteException {
            if (BtExe.getBtInstance().isConnected()) {
                return 1;
            }
            return 0;
        }

        public int getBtPhoneState() throws RemoteException {
            return BtExe.getBtInstance().getCallValue();
        }

        public String getBtDEV() throws RemoteException {
            return BtExe.getBtInstance().getDevName();
        }

        public int getBtMusicState() throws RemoteException {
            return BtExe.getBtInstance().getMusicState();
        }

        public void onBtMusicPlay() throws RemoteException {
            BtExe.getBtInstance().musicPlay();
        }

        public void onBtMusicPause() throws RemoteException {
            BtExe.getBtInstance().musicPause();
        }

        public void onBtMusicPrev() throws RemoteException {
            BtExe.getBtInstance().musicPrev();
        }

        public void onBtMusicNext() throws RemoteException {
            BtExe.getBtInstance().musicNext();
        }

        public List<ITsSpeechBtPbInfo> onBtPbListChange() throws RemoteException {
            return BtExe.getBtInstance().getTsSpeechBtPbInfo();
        }

        public void openBt() throws RemoteException {
            Intent intent = new Intent();
            intent.addFlags(337641472);
            intent.setComponent(new ComponentName("com.ts.MainUI", "com.ts.bt.BtConnectActivity"));
            if (intent != null) {
                TsBtService.this.startActivity(intent);
            }
        }

        public void closeBt() throws RemoteException {
            TsBtService.this.sendBroadcast(new Intent(TsBtService.ACTION_CLOSE_BT));
        }

        public void openBtMusic() throws RemoteException {
            Intent intent = new Intent();
            intent.addFlags(337641472);
            intent.setComponent(new ComponentName("com.ts.MainUI", "com.ts.bt.BtMusicActivity"));
            if (intent != null) {
                TsBtService.this.startActivity(intent);
            }
        }

        public void closeBtMusic() throws RemoteException {
            Intent intent = new Intent("forfan.intent.action.CLOSEMEDIA");
            intent.putExtra("mode", 5);
            TsBtService.this.sendBroadcast(intent);
        }

        public List<PhonebookData> getBtPbList() throws RemoteException {
            return BtExe.getBtInstance().getPbData();
        }
    }
}
