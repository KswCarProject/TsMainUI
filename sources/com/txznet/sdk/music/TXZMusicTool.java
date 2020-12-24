package com.txznet.sdk.music;

import com.Tn.Tr.Tk.T;
import com.ts.can.bmw.mini.CanBMWMiniServiceDetailActivity;
import com.txznet.Tr.T;
import com.txznet.comm.Tr.T;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZMusicManager;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Proguard */
public class TXZMusicTool implements TXZMusicManager.MusicTool {

    /* renamed from: T  reason: collision with root package name */
    static TXZMusicTool f873T = new TXZMusicTool();
    static List<TXZMusicModel> T5 = new ArrayList();
    static boolean T9 = false;
    static PlayMode TE = PlayMode.PLAY_MODE_LOOP_SINGLE;
    static int TZ = 0;
    static double Tk = 0.0d;
    static boolean Tn = false;
    static TXZMusicManager.MusicToolStatusListener Tr = null;
    static Runnable Ty = new Runnable() {
        public void run() {
            Tn.Tr().T("com.txznet.music", "music.status.subscrib", (byte[]) null, (Tn.Tr) null);
            Tn.Tr().T("com.txznet.music", "music.isPlaying", (byte[]) null, (Tn.Tr) new Tn.Tr() {
                public void T(Tn.Ty data) {
                    TXZMusicTool.Tn = data.T9().booleanValue();
                    if (TXZMusicTool.Tr != null) {
                        TXZMusicTool.Tr.onStatusChange();
                    }
                }
            });
            Tn.Tr().T("com.txznet.music", "music.isBufferProccessing", (byte[]) null, (Tn.Tr) new Tn.Tr() {
                public void T(Tn.Ty data) {
                    TXZMusicTool.T9 = data.T9().booleanValue();
                    if (TXZMusicTool.Tr != null) {
                        TXZMusicTool.Tr.onStatusChange();
                    }
                }
            });
            Tn.Tr().T("com.txznet.music", "music.getProgress", (byte[]) null, (Tn.Tr) new Tn.Tr() {
                public void T(Tn.Ty data) {
                    TXZMusicTool.Tk = data.Tn().doubleValue();
                    if (TXZMusicTool.Tr != null && (TXZMusicTool.Tr instanceof TXZMusicStatusListener)) {
                        ((TXZMusicStatusListener) TXZMusicTool.Tr).onProgressChange();
                    }
                }
            });
            Tn.Tr().T("com.txznet.music", "music.getCurrentMusicIndex", (byte[]) null, (Tn.Tr) new Tn.Tr() {
                public void T(Tn.Ty data) {
                    TXZMusicTool.TZ = data.Ty().intValue();
                    if (TXZMusicTool.Tr != null) {
                        TXZMusicTool.Tr.onStatusChange();
                    }
                }
            });
            Tn.Tr().T("com.txznet.music", "music.getMusicList", (byte[]) null, (Tn.Tr) new Tn.Tr() {
                public void T(Tn.Ty data) {
                    TXZMusicTool.Tr(data.Tr());
                }
            });
            Tn.Tr().T("com.txznet.music", "music.getPlayMode", (byte[]) null, (Tn.Tr) new Tn.Tr() {
                public void T(Tn.Ty data) {
                    TXZMusicTool.T(data.Tr());
                }
            });
        }
    };

    /* compiled from: Proguard */
    public enum PlayMode {
        PLAY_MODE_LOOP_SINGLE,
        PLAY_MODE_RANDOM,
        PLAY_MODE_LOOP_ALL
    }

    /* compiled from: Proguard */
    public interface TXZMusicStatusListener extends TXZMusicManager.MusicToolStatusListener {
        void onListChange();

        void onModeChange();

        void onProgressChange();

        void onStatusChange();
    }

    private TXZMusicTool() {
    }

    public static TXZMusicTool getInstance() {
        try {
            if (T.Tr().getPackageManager().getApplicationInfo("com.txznet.music", 8192) != null) {
                return f873T;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void play() {
        Tn.Tr().T("com.txznet.music", "music.play", (byte[]) null, (Tn.Tr) null);
    }

    public void continuePlay() {
        Tn.Tr().T("com.txznet.music", "music.play.extra", (byte[]) null, (Tn.Tr) null);
    }

    public void pause() {
        Tn.Tr().T("com.txznet.music", "music.pause", (byte[]) null, (Tn.Tr) null);
    }

    public void exit() {
        Tn.Tr().T("com.txznet.music", "music.exit", (byte[]) null, (Tn.Tr) null);
    }

    public void next() {
        Tn.Tr().T("com.txznet.music", "music.next", (byte[]) null, (Tn.Tr) null);
    }

    public void prev() {
        Tn.Tr().T("com.txznet.music", "music.prev", (byte[]) null, (Tn.Tr) null);
    }

    public void switchModeLoopAll() {
        Tn.Tr().T("com.txznet.music", "music.switchModeLoopAll", (byte[]) null, (Tn.Tr) null);
    }

    public void switchModeLoopOne() {
        Tn.Tr().T("com.txznet.music", "music.switchModeLoopOne", (byte[]) null, (Tn.Tr) null);
    }

    public void switchModeRandom() {
        Tn.Tr().T("com.txznet.music", "music.switchModeRandom", (byte[]) null, (Tn.Tr) null);
    }

    public void switchSong() {
        Tn.Tr().T("com.txznet.music", "music.switchSong", (byte[]) null, (Tn.Tr) null);
    }

    public void playRandom() {
        Tn.Tr().T("com.txznet.txz", "txz.music.txztool.playRandom", (byte[]) null, (Tn.Tr) null);
    }

    public void playFavourMusic() {
        Tn.Tr().T("com.txznet.txz", "txz.music.txztool.playFavourMusic", (byte[]) null, (Tn.Tr) null);
    }

    public void playMusic(TXZMusicManager.MusicModel musicModel) {
        Tn.Tr().T("com.txznet.txz", "txz.music.txztool.playMusic", musicModel.toString().getBytes(), (Tn.Tr) null);
    }

    public void favourMusic() {
        Tn.Tr().T("com.txznet.txz", "txz.music.txztool.favourMusic", (byte[]) null, (Tn.Tr) null);
    }

    public void unfavourMusic() {
        Tn.Tr().T("com.txznet.txz", "txz.music.txztool.unfavourMusic", (byte[]) null, (Tn.Tr) null);
    }

    public void setStatusListener(TXZMusicManager.MusicToolStatusListener listener) {
        Tr = listener;
        com.txznet.Tr.T.T("musicStatus.", new T.C0013T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("updateProgress")) {
                    TXZMusicTool.Tk = Double.parseDouble(new String(data));
                    if (TXZMusicTool.Tr != null && (TXZMusicTool.Tr instanceof TXZMusicStatusListener)) {
                        ((TXZMusicStatusListener) TXZMusicTool.Tr).onProgressChange();
                    }
                } else if (command.equals("isPlaying")) {
                    TXZMusicTool.Tn = Boolean.parseBoolean(new String(data));
                    Tn.Tr().T("com.txznet.music", "music.getCurrentMusicIndex", (byte[]) null, (Tn.Tr) new Tn.Tr() {
                        public void T(Tn.Ty data) {
                            TXZMusicTool.TZ = data.Ty().intValue();
                            if (TXZMusicTool.Tr != null) {
                                TXZMusicTool.Tr.onStatusChange();
                            }
                        }
                    });
                } else if (command.equals("isBufferProccessing")) {
                    TXZMusicTool.T9 = Boolean.parseBoolean(new String(data));
                    if (TXZMusicTool.Tr != null) {
                        TXZMusicTool.Tr.onStatusChange();
                    }
                } else if (command.equals("updateMusicList")) {
                    TXZMusicTool.Tr(data);
                } else if (command.equals("updatePlayMode")) {
                    TXZMusicTool.T(data);
                }
                return null;
            }
        });
        Tn.Tr().T("com.txznet.music", Ty);
        Ty.run();
    }

    public boolean isPlaying() {
        return Tn;
    }

    public boolean isBufferProccessing() {
        return T9;
    }

    public double getProgress() {
        return Tk;
    }

    public TXZMusicManager.MusicModel getCurrentMusicModel() {
        TXZMusicManager.MusicModel musicModel;
        try {
            synchronized (TXZMusicTool.class) {
                musicModel = T5.get(TZ);
            }
            return musicModel;
        } catch (Exception e) {
            return null;
        }
    }

    public int getCurrentMusicIndex() {
        return TZ;
    }

    static boolean T(byte[] data) {
        try {
            String mode = new String(data);
            if (mode.equals("single")) {
                TE = PlayMode.PLAY_MODE_LOOP_SINGLE;
                if (Tr != null && (Tr instanceof TXZMusicStatusListener)) {
                    ((TXZMusicStatusListener) Tr).onModeChange();
                }
                return true;
            } else if (mode.equals("all")) {
                TE = PlayMode.PLAY_MODE_LOOP_ALL;
                if (Tr != null && (Tr instanceof TXZMusicStatusListener)) {
                    ((TXZMusicStatusListener) Tr).onModeChange();
                }
                return true;
            } else if (mode.equals("random")) {
                TE = PlayMode.PLAY_MODE_RANDOM;
                if (Tr != null && (Tr instanceof TXZMusicStatusListener)) {
                    ((TXZMusicStatusListener) Tr).onModeChange();
                }
                return true;
            } else {
                if (Tr != null && (Tr instanceof TXZMusicStatusListener)) {
                    ((TXZMusicStatusListener) Tr).onModeChange();
                }
                return false;
            }
        } catch (Exception e) {
            if (Tr != null && (Tr instanceof TXZMusicStatusListener)) {
                ((TXZMusicStatusListener) Tr).onModeChange();
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            if (Tr != null && (Tr instanceof TXZMusicStatusListener)) {
                ((TXZMusicStatusListener) Tr).onModeChange();
            }
            throw th2;
        }
    }

    public PlayMode getPlayMode() {
        return TE;
    }

    /* compiled from: Proguard */
    public static class TXZMusicModel extends TXZMusicManager.MusicModel {
        protected boolean T5;

        public boolean getFavour() {
            return this.T5;
        }

        public void setFavour(boolean favour) {
            this.T5 = favour;
        }
    }

    static boolean Tr(byte[] data) {
        boolean booleanValue;
        try {
            T.Tr lst = T.Tr.T(data);
            List<TXZMusicModel> ret = new ArrayList<>();
            for (T.C0005T item : lst.Tr) {
                TXZMusicModel mod = new TXZMusicModel();
                mod.setTitle(item.Tr.Ty);
                mod.setAlbum(item.Tr.Tn);
                mod.setArtist(item.Tr.T9);
                mod.setKeywords(item.Tr.Te);
                if (item.Tr.TF == null) {
                    booleanValue = false;
                } else {
                    booleanValue = item.Tr.TF.booleanValue();
                }
                mod.setFavour(booleanValue);
                ret.add(mod);
            }
            synchronized (TXZMusicTool.class) {
                T5 = ret;
            }
            if (Tr != null && (Tr instanceof TXZMusicStatusListener)) {
                ((TXZMusicStatusListener) Tr).onListChange();
            }
            return true;
        } catch (com.Tr.T.T.Tn e) {
            return false;
        }
    }

    public List<TXZMusicModel> getMusicList() {
        List<TXZMusicModel> list;
        synchronized (TXZMusicTool.class) {
            list = T5;
        }
        return list;
    }

    public void playIndex(int index) {
        Tn.Tr().T("com.txznet.music", "music.playIndex", ("" + index).getBytes(), (Tn.Tr) null);
    }

    public void favourIndex(int index, boolean favour) {
        Tr json = new Tr();
        json.T(CanBMWMiniServiceDetailActivity.KEY_INDEX, (Object) Integer.valueOf(index));
        json.T(IConstantData.KEY_FAVOUR, (Object) Boolean.valueOf(favour));
        Tn.Tr().T("com.txznet.music", "music.favourIndex", json.Ty(), (Tn.Tr) null);
    }

    public void deleteIndex(int index, boolean deleteFile) {
        Tr json = new Tr();
        json.T(CanBMWMiniServiceDetailActivity.KEY_INDEX, (Object) Integer.valueOf(index));
        json.T("deleteFile", (Object) Boolean.valueOf(deleteFile));
        Tn.Tr().T("com.txznet.music", "music.deleteIndex", json.Ty(), (Tn.Tr) null);
    }
}
