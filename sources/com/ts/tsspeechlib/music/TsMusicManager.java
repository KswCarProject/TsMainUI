package com.ts.tsspeechlib.music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ts.tsspeechlib.ManagerInitListener;
import com.ts.tsspeechlib.music.ITsSpeechMusic;
import java.util.List;

public class TsMusicManager {
    public static final String TAG = "TsMusicManager";
    public static TsMusicManager musicManager;
    private Context mContext;
    /* access modifiers changed from: private */
    public ITsSpeechMusic mSpeechMusicService;
    /* access modifiers changed from: private */
    public ManagerInitListener musicListener;
    ServiceConnection sconn = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d(TsMusicManager.TAG, "鍒濆鍖栧け璐ワ紒");
            TsMusicManager.this.mSpeechMusicService = null;
            if (TsMusicManager.this.musicListener != null) {
                TsMusicManager.this.musicListener.initResult(2, false);
            }
        }

        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            Log.d(TsMusicManager.TAG, "鍒濆鍖栨垚鍔燂紒");
            TsMusicManager.this.mSpeechMusicService = ITsSpeechMusic.Stub.asInterface(binder);
            if (TsMusicManager.this.musicListener != null) {
                TsMusicManager.this.musicListener.initResult(2, true);
            }
        }
    };

    public static TsMusicManager getInstance() {
        if (musicManager == null) {
            musicManager = new TsMusicManager();
        }
        return musicManager;
    }

    public void initManager(Context context, ManagerInitListener listener) {
        this.mContext = context;
        this.musicListener = listener;
        bindMusicService();
    }

    public void openMusic() {
        try {
            if (this.mSpeechMusicService != null) {
                this.mSpeechMusicService.openMusic();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void closeMusic() {
        try {
            if (this.mSpeechMusicService != null) {
                this.mSpeechMusicService.closeMusic();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void openVideo() {
        try {
            if (this.mSpeechMusicService != null) {
                this.mSpeechMusicService.openVideo();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void closeVideo() {
        try {
            if (this.mSpeechMusicService != null) {
                this.mSpeechMusicService.closeVideo();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getMusicState() {
        try {
            if (this.mSpeechMusicService == null) {
                return 0;
            }
            int musicState = this.mSpeechMusicService.getMusicState();
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void onMusicPlay() {
        try {
            if (this.mSpeechMusicService != null) {
                this.mSpeechMusicService.onMusicPlay();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onMusicPause() {
        try {
            if (this.mSpeechMusicService != null) {
                this.mSpeechMusicService.onMusicPause();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onMusicPrev() {
        try {
            if (this.mSpeechMusicService != null) {
                this.mSpeechMusicService.onMusicPrev();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onMusicNext() {
        try {
            if (this.mSpeechMusicService != null) {
                this.mSpeechMusicService.onMusicNext();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setMusicPlayMode(int mode) {
        try {
            if (this.mSpeechMusicService != null) {
                this.mSpeechMusicService.setMusicPlayMode(mode);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void bindMusicService() {
        Intent intent = new Intent();
        intent.setAction("com.ts.tsspeechlib.music.TsMusicService");
        Intent intent2 = createExplicitFromImplicitIntent(this.mContext, intent);
        if (intent2 != null) {
            this.mContext.bindService(intent2, this.sconn, 1);
        } else {
            Log.d(TAG, "bindMusicService failed");
        }
    }

    private Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        List<ResolveInfo> resolveInfo = context.getPackageManager().queryIntentServices(implicitIntent, 0);
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }
        ResolveInfo serviceInfo = resolveInfo.get(0);
        ComponentName component = new ComponentName(serviceInfo.serviceInfo.packageName, serviceInfo.serviceInfo.name);
        Intent explicitIntent = new Intent(implicitIntent);
        explicitIntent.setComponent(component);
        return explicitIntent;
    }
}
