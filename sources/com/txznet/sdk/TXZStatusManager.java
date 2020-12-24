package com.txznet.sdk;

import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Tr.Tr.T5;
import com.txznet.sdk.TXZService;

/* compiled from: Proguard */
public class TXZStatusManager {
    private static TXZStatusManager TE = new TXZStatusManager();

    /* renamed from: T  reason: collision with root package name */
    AudioLogicType f819T = null;
    Runnable T9 = null;
    boolean TZ = false;
    Runnable Tk = null;
    Integer Tn = null;
    AudioLogicType Tr = null;
    AudioLogicType Ty = null;

    /* compiled from: Proguard */
    public enum AudioLogicType {
        AUDIO_LOGIC_NONE,
        AUDIO_LOGIC_DUCK,
        AUDIO_LOGIC_PAUSE,
        AUDIO_LOGIC_STOP,
        AUDIO_LOGIC_MUTE
    }

    /* compiled from: Proguard */
    public interface StatusListener extends T5.Tr {
        void onBeepEnd();

        void onBeginAsr();

        void onBeginCall();

        void onBeginTts();

        void onEndAsr();

        void onEndCall();

        void onEndTts();

        void onMusicPause();

        void onMusicPlay();
    }

    private TXZStatusManager() {
    }

    public static TXZStatusManager getInstance() {
        return TE;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.f819T != null) {
            setAudioLogicWhenAsr(this.f819T);
        }
        if (this.Tr != null) {
            setAudioLogicWhenTts(this.Tr);
        }
        if (this.Ty != null) {
            setAudioLogicWhenCall(this.Ty);
        }
        if (this.Tn != null) {
            setAudioFocusStreamType(this.Tn.intValue());
        }
        if (this.TZ) {
            setAudioFocusLogic(this.T9, this.Tk);
        }
        T5.T((T5.T) new T5.T() {
            public void T() {
            }
        });
    }

    public boolean isAsrBusy() {
        return T5.T();
    }

    public boolean isTtsBusy() {
        return T5.Tr();
    }

    public boolean isCallBusy() {
        return T5.Ty();
    }

    public boolean isMusicPlaying() {
        return TXZMusicManager.getInstance().isPlaying();
    }

    public boolean isRecordUIShowed() {
        byte[] data = Tn.Tr().T("txz.record.ui.status.isShowing", (byte[]) null);
        boolean bRet = false;
        if (data == null) {
            return false;
        }
        try {
            bRet = Boolean.parseBoolean(new String(data));
        } catch (Exception e) {
        }
        return bRet;
    }

    public void addStatusListener(StatusListener listener) {
        T5.T((T5.Tr) listener);
    }

    public void removeStatusListener(StatusListener listener) {
        T5.Tr((T5.Tr) listener);
    }

    public void setAudioLogicWhenAsr(AudioLogicType logic) {
        this.f819T = logic;
        Tn.Tr().T("com.txznet.txz", "txz.music.audioLogic.asr", logic.name().getBytes(), (Tn.Tr) null);
    }

    public void setAudioLogicWhenTts(AudioLogicType logic) {
        this.Tr = logic;
        Tn.Tr().T("com.txznet.txz", "txz.music.audioLogic.tts", logic.name().getBytes(), (Tn.Tr) null);
    }

    public void setAudioLogicWhenCall(AudioLogicType logic) {
        this.Ty = logic;
        Tn.Tr().T("com.txznet.txz", "txz.music.audioLogic.call", logic.name().getBytes(), (Tn.Tr) null);
    }

    public void setAudioFocusStreamType(int stream) {
        this.Tn = Integer.valueOf(stream);
        Tn.Tr().T("com.txznet.txz", "txz.music.setAudioFocusStreamType", ("" + stream).getBytes(), (Tn.Tr) null);
    }

    public void setAudioFocusLogic(Runnable onRequestAudioFocus, Runnable onAbandonAudioFocus) {
        this.TZ = true;
        this.T9 = onRequestAudioFocus;
        this.Tk = onAbandonAudioFocus;
        if (this.T9 == null || this.Tk == null) {
            Tn.Tr().T("com.txznet.txz", "txz.music.clearAudioFocusLogic", (byte[]) null, (Tn.Tr) null);
            return;
        }
        Tn.Tr().T("com.txznet.txz", "txz.music.setAudioFocusLogic", (byte[]) null, (Tn.Tr) null);
        TXZService.T("status.focus.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if ("RequestAudioFocus".equals(command)) {
                    if (TXZStatusManager.this.T9 != null) {
                        TXZStatusManager.this.T9.run();
                    }
                } else if ("AbandonAudioFocus".equals(command) && TXZStatusManager.this.Tk != null) {
                    TXZStatusManager.this.Tk.run();
                }
                return null;
            }
        });
    }
}
