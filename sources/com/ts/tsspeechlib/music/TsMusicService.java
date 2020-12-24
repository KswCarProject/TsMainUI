package com.ts.tsspeechlib.music;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.main.common.WinShow;
import com.ts.tsspeechlib.music.ITsSpeechMusic;

public class TsMusicService extends Service {
    private static final String ACTION_CLOSE_MUSIC = "com.ts.speech.action.closemusic";
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
            WinShow.TsEnterMode(0);
            TsMusicService.this.sendBroadcast(new Intent(TsMusicService.ACTION_CLOSE_MUSIC));
        }

        public int getMusicState() throws RemoteException {
            return 0;
        }

        public void onMusicPlay() throws RemoteException {
        }

        public void onMusicPause() throws RemoteException {
        }

        public void onMusicPrev() throws RemoteException {
        }

        public void onMusicNext() throws RemoteException {
        }

        public void setMusicPlayMode(int mode) throws RemoteException {
        }

        public void openVideo() throws RemoteException {
            Intent intent = new Intent();
            intent.addFlags(337641472);
            intent.setComponent(new ComponentName("com.ts.MainUI", "com.ts.main.Media.USBMainActivity"));
            if (intent != null) {
                TsMusicService.this.startActivity(intent);
            }
        }

        public void closeVideo() throws RemoteException {
            WinShow.TsEnterMode(0);
            TsMusicService.this.sendBroadcast(new Intent(TsMusicService.ACTION_CLOSE_MUSIC));
        }
    }
}
