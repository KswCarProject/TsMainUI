package com.txznet.sdk;

import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.TXZMusicManager;

/* compiled from: Proguard */
public class TXZTongTingManager {

    /* renamed from: T  reason: collision with root package name */
    private static volatile TXZTongTingManager f834T;

    private TXZTongTingManager() {
    }

    public static TXZTongTingManager getInstance() {
        if (f834T == null) {
            synchronized (TXZTongTingManager.class) {
                if (f834T == null) {
                    f834T = new TXZTongTingManager();
                }
            }
        }
        return f834T;
    }

    public boolean isPlaying() {
        Tn.Ty serviceData = Tn.Tr().T("com.txznet.music", "music.tongting.isPlaying", (byte[]) null);
        byte[] data = null;
        if (serviceData != null) {
            data = serviceData.Tr();
        }
        if (data == null) {
            return false;
        }
        return Boolean.parseBoolean(new String(data));
    }

    public boolean isBuffering() {
        Tn.Ty serviceData = Tn.Tr().T("com.txznet.music", "music.tongting.isBuffering", (byte[]) null);
        byte[] data = null;
        if (serviceData != null) {
            data = serviceData.Tr();
        }
        if (data == null) {
            return false;
        }
        return Boolean.parseBoolean(new String(data));
    }

    public boolean isShowUI() {
        Tn.Ty serviceData = Tn.Tr().T("com.txznet.music", "music.tongting.isShowUI", (byte[]) null);
        byte[] data = null;
        if (serviceData != null) {
            data = serviceData.Tr();
        }
        if (data == null) {
            return false;
        }
        return Boolean.parseBoolean(new String(data));
    }

    @Deprecated
    public void play() {
        Tn.Tr().T("com.txznet.music", "music.tongting.start", (byte[]) null, (Tn.Tr) null);
    }

    public void continuePlay() {
        Tn.Tr().T("com.txznet.music", "music.tongting.continuePlay", (byte[]) null, (Tn.Tr) null);
    }

    public void pause() {
        Tn.Tr().T("com.txznet.music", "music.tongting.pause", (byte[]) null, (Tn.Tr) null);
    }

    public void exit() {
        Tn.Tr().T("com.txznet.music", "music.tongting.exit", (byte[]) null, (Tn.Tr) null);
    }

    public void next() {
        Tn.Tr().T("com.txznet.music", "music.tongting.next", (byte[]) null, (Tn.Tr) null);
    }

    public void prev() {
        Tn.Tr().T("com.txznet.music", "music.tongting.prev", (byte[]) null, (Tn.Tr) null);
    }

    public void switchModeLoopAll() {
        Tn.Tr().T("com.txznet.music", "music.tongting.switchModeLoopAll", (byte[]) null, (Tn.Tr) null);
    }

    public void switchModeLoopOne() {
        Tn.Tr().T("com.txznet.music", "music.tongting.switchModeLoopOne", (byte[]) null, (Tn.Tr) null);
    }

    public void switchModeRandom() {
        Tn.Tr().T("com.txznet.music", "music.tongting.switchModeRandom", (byte[]) null, (Tn.Tr) null);
    }

    public void switchSong() {
        Tn.Tr().T("com.txznet.music", "music.tongting.switchSong", (byte[]) null, (Tn.Tr) null);
    }

    public void playRandom() {
        Tn.Tr().T("com.txznet.music", "music.tongting.playRandom", (byte[]) null, (Tn.Tr) null);
    }

    public TXZMusicManager.MusicModel getCurrentMusicModel() {
        try {
            Tn.Ty serviceData = Tn.Tr().T("com.txznet.music", "music.tongting.getCurrentMusicModel", (byte[]) null);
            byte[] data = null;
            if (serviceData != null) {
                data = serviceData.Tr();
            }
            if (data == null) {
                return null;
            }
            return TXZMusicManager.MusicModel.fromString(new String(data));
        } catch (Exception e) {
            return null;
        }
    }

    public void playFavourMusic() {
        Tn.Tr().T("com.txznet.music", "music.tongting.playFavourMusic", (byte[]) null, (Tn.Tr) null);
    }

    public void favourMusic() {
        Tn.Tr().T("com.txznet.music", "music.tongting.favourMusic", (byte[]) null, (Tn.Tr) null);
    }

    public void unfavourMusic() {
        Tn.Tr().T("com.txznet.music", "music.tongting.unfavourMusic", (byte[]) null, (Tn.Tr) null);
    }

    public void playMusic() {
        Tn.Tr().T("com.txznet.music", "music.tongting.play.inner", (byte[]) null, (Tn.Tr) null);
    }

    public void playRadio() {
        Tn.Tr().T("com.txznet.music", "audio.play", (byte[]) null, (Tn.Tr) null);
    }
}
