package com.txznet.sdk;

import com.txznet.comm.Tr.Tn;
import com.txznet.sdk.TXZMusicManager;

/* compiled from: Proguard */
public class TXZOwnerMusicManager {

    /* renamed from: T  reason: collision with root package name */
    private static volatile TXZOwnerMusicManager f780T;

    private TXZOwnerMusicManager() {
    }

    public static TXZOwnerMusicManager getInstance() {
        if (f780T == null) {
            synchronized (TXZOwnerMusicManager.class) {
                if (f780T == null) {
                    f780T = new TXZOwnerMusicManager();
                }
            }
        }
        return f780T;
    }

    public boolean isPlaying() {
        byte[] data = Tn.Tr().T("txz.music.tongting.isPlaying", (byte[]) null);
        if (data == null) {
            return false;
        }
        return Boolean.parseBoolean(new String(data));
    }

    public boolean isBuffering() {
        byte[] data = Tn.Tr().T("txz.music.tongting.isBuffering", (byte[]) null);
        if (data == null) {
            return false;
        }
        return Boolean.parseBoolean(new String(data));
    }

    public boolean isShowUI() {
        byte[] data = Tn.Tr().T("txz.music.tongting.isShowUI", (byte[]) null);
        if (data == null) {
            return false;
        }
        return Boolean.parseBoolean(new String(data));
    }

    public void play() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.play", (byte[]) null, (Tn.Tr) null);
    }

    public void continuePlay() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.play.extra", (byte[]) null, (Tn.Tr) null);
    }

    public void pause() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.pause", (byte[]) null, (Tn.Tr) null);
    }

    public void exit() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.exit", (byte[]) null, (Tn.Tr) null);
    }

    public void next() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.next", (byte[]) null, (Tn.Tr) null);
    }

    public void prev() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.prev", (byte[]) null, (Tn.Tr) null);
    }

    public void switchModeLoopAll() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.switchModeLoopAll", (byte[]) null, (Tn.Tr) null);
    }

    public void switchModeLoopOne() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.switchModeLoopOne", (byte[]) null, (Tn.Tr) null);
    }

    public void switchModeRandom() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.switchModeRandom", (byte[]) null, (Tn.Tr) null);
    }

    public void switchSong() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.switchSong", (byte[]) null, (Tn.Tr) null);
    }

    public void playRandom() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.playRandom", (byte[]) null, (Tn.Tr) null);
    }

    public TXZMusicManager.MusicModel getCurrentMusicModel() {
        try {
            byte[] data = Tn.Tr().T("txz.music.tongting.getCurrentMusicModel", (byte[]) null);
            if (data == null) {
                return null;
            }
            return TXZMusicManager.MusicModel.fromString(new String(data));
        } catch (Exception e) {
            return null;
        }
    }

    public void playFavourMusic() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.playFavourMusic", (byte[]) null, (Tn.Tr) null);
    }

    public void favourMusic() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.favourMusic", (byte[]) null, (Tn.Tr) null);
    }

    public void unfavourMusic() {
        Tn.Tr().T("com.txznet.txz", "txz.music.tongting.unfavourMusic", (byte[]) null, (Tn.Tr) null);
    }
}
