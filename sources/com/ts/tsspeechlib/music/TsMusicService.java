package com.ts.tsspeechlib.music;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.main.common.MainUI;
import com.ts.main.common.WinShow;
import com.ts.tsspeechlib.music.ITsSpeechMusic;
import com.yyw.ts70xhw.Mcu;

public class TsMusicService extends Service {
    private static final String ACTION_CLOSE_MUSIC = "com.ts.speech.action.closemusic";
    public static final String BROADCAST_LANCHER_FUNC_CLOSEMEDIA = "forfan.intent.action.CLOSEMEDIA";
    private mTsSpeechMusic mBinder;

    public IBinder onBind(Intent arg0) {
        if (this.mBinder == null) {
            this.mBinder = new mTsSpeechMusic();
        }
        return this.mBinder;
    }

    public class mTsSpeechMusic extends ITsSpeechMusic.Stub {
        public mTsSpeechMusic() {
        }

        public void openMusic() throws RemoteException {
            WinShow.TsEnterMode(4);
        }

        public void closeMusic() throws RemoteException {
            Intent intent = new Intent("forfan.intent.action.CLOSEMEDIA");
            intent.putExtra("mode", 4);
            TsMusicService.this.sendBroadcast(intent);
        }

        public int getMusicState() throws RemoteException {
            return MainUI.GetInstance().UIGetMediaStatus();
        }

        public void onMusicPlay() throws RemoteException {
            Mcu.SetCkey(90);
        }

        public void onMusicPause() throws RemoteException {
            Mcu.SetCkey(91);
        }

        public void onMusicPrev() throws RemoteException {
            Mcu.SetCkey(45);
        }

        public void onMusicNext() throws RemoteException {
            Mcu.SetCkey(44);
        }

        public void setMusicPlayMode(int mode) throws RemoteException {
        }

        public void openVideo() throws RemoteException {
            WinShow.ShowVideoWin();
        }

        public void closeVideo() throws RemoteException {
            Intent intent = new Intent("forfan.intent.action.CLOSEMEDIA");
            intent.putExtra("mode", 3);
            TsMusicService.this.sendBroadcast(intent);
        }
    }
}
