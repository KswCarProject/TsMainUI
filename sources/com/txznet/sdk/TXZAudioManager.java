package com.txznet.sdk;

import android.text.TextUtils;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.media.AbsTXZAudioTool;
import com.txznet.sdk.media.InvokeConstants;
import com.txznet.sdk.media.PlayerLoopMode;
import com.txznet.sdk.media.TXZMediaModel;
import com.txznet.sdk.music.MusicInvokeConstants;

/* compiled from: Proguard */
public class TXZAudioManager {
    private static TXZAudioManager Ty = new TXZAudioManager();

    /* renamed from: T  reason: collision with root package name */
    AbsTXZAudioTool f707T;
    private Boolean T5;
    private boolean T9;
    private String TE;
    private String TZ;
    private String Tk;
    private AudioTool Tn;
    boolean Tr;

    /* compiled from: Proguard */
    public enum AudioTool {
        AUDIO_TXZ,
        AUDIO_KL,
        AUDIO_TT,
        AUDIO_XMLY
    }

    /* compiled from: Proguard */
    public interface AudioToolStatusListener {
        void onStatusChange();
    }

    private TXZAudioManager() {
    }

    public static TXZAudioManager getInstance() {
        return Ty;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.T9) {
            setDefaultAudioTool(this.Tn);
        }
        if (this.Tr) {
            setAudioTool(this.f707T);
        }
        if (!TextUtils.isEmpty(this.Tk)) {
            setXMLYAppkey(this.Tk, this.TZ, this.TE);
        }
        if (this.T5 != null) {
            showXmlySearchResult(this.T5.booleanValue());
        }
    }

    /* compiled from: Proguard */
    public static abstract class IAudioTool {
        public abstract void exit();

        public abstract void pause();

        public abstract void playFm(String str);

        public abstract void setAudioStatusListener(AudioToolStatusListener audioToolStatusListener);

        public abstract void start();

        public void next() {
        }

        public void prev() {
        }

        public String getCurrentFmName() {
            return TXZResourceManager.STYLE_DEFAULT;
        }

        public boolean isPlaying() {
            return false;
        }
    }

    public void setDefaultAudioTool(AudioTool at) {
        this.T9 = true;
        this.Tn = at;
        if (at == null) {
            Tn.Tr().T("com.txznet.txz", "txz.audio.cleartool", (byte[]) null, (Tn.Tr) null);
        } else {
            Tn.Tr().T("com.txznet.txz", "txz.audio.setInnerTool", this.Tn.name().getBytes(), (Tn.Tr) null);
        }
    }

    public void setXMLYAppkey(String appSecret, String appKey, String pkgName) {
        this.Tk = appSecret;
        this.TZ = appKey;
        this.TE = pkgName;
        Tr builder = new Tr();
        builder.T("appSecret", (Object) appSecret);
        builder.T("appKey", (Object) appKey);
        builder.T("pkgName", (Object) pkgName);
        Tn.Tr().T("com.txznet.txz", "txz.audio.setkey.xmly", builder.Ty(), (Tn.Tr) null);
    }

    public void setAudioTool(final AbsTXZAudioTool tool) {
        this.Tr = true;
        this.f707T = tool;
        if (tool == null) {
            Tn.Tr().T("com.txznet.txz", "txz.audio.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T(InvokeConstants.INVOKE_PREFIX_AUDIO, new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                Tr params = new Tr(data);
                if ("open".equals(command)) {
                    tool.open(((Boolean) params.T(InvokeConstants.INVOKE_PLAY, Boolean.TYPE)).booleanValue());
                    return null;
                } else if (InvokeConstants.INVOKE_PLAY.equals(command)) {
                    tool.play(TXZMediaModel.fromJSONObject(params.T()));
                    return null;
                } else if (InvokeConstants.INVOKE_CONTINUE_PLAY.equals(command)) {
                    tool.continuePlay();
                    return null;
                } else if (InvokeConstants.INVOKE_PAUSE.equals(command)) {
                    tool.pause();
                    return null;
                } else if (InvokeConstants.INVOKE_EXIT.equals(command)) {
                    tool.exit();
                    return null;
                } else if (InvokeConstants.INVOKE_NEXT.equals(command)) {
                    tool.next();
                    return null;
                } else if (InvokeConstants.INVOKE_PREV.equals(command)) {
                    tool.prev();
                    return null;
                } else if (InvokeConstants.INVOKE_SWITCH_LOOP_MODE.equals(command)) {
                    tool.switchLoopMode(PlayerLoopMode.fromModeStr((String) params.T("mode", String.class)));
                    return null;
                } else if (InvokeConstants.INVOKE_COLLECT.equals(command)) {
                    tool.collect();
                    return null;
                } else if (InvokeConstants.INVOKE_UNCOLLECT.equals(command)) {
                    tool.unCollect();
                    return null;
                } else if (InvokeConstants.INVOKE_PLAY_COLLECTION.equals(command)) {
                    tool.playCollection();
                    return null;
                } else if (InvokeConstants.INVOKE_SUBSCRIBE.equals(command)) {
                    tool.subscribe();
                    return null;
                } else if (InvokeConstants.INVOKE_UNSUBSCRIBE.equals(command)) {
                    tool.unSubscribe();
                    return null;
                } else if (InvokeConstants.INVOKE_PLAY_SUBSCRIBE.equals(command)) {
                    tool.playSubscribe();
                    return null;
                } else if (InvokeConstants.INVOKE_GET_PLAYER_STATUS.equals(command)) {
                    return tool.getStatus().toStatusString().getBytes();
                } else {
                    if (InvokeConstants.INVOKE_SUPPORT_LOOP_MODE.equals(command)) {
                        return (TXZResourceManager.STYLE_DEFAULT + tool.supportLoopMode(PlayerLoopMode.fromModeStr((String) params.T("mode", String.class)))).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_SUBSCRIBE.equals(command)) {
                        return (TXZResourceManager.STYLE_DEFAULT + tool.supportSubscribe()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_UNSUBSCRIBE.equals(command)) {
                        return (TXZResourceManager.STYLE_DEFAULT + tool.supportUnSubscribe()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_COLLECT.equals(command)) {
                        return (TXZResourceManager.STYLE_DEFAULT + tool.supportCollect()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_UNCOLLECT.equals(command)) {
                        return (TXZResourceManager.STYLE_DEFAULT + tool.supportUnCollect()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_PLAY_SUBSCRIBE.equals(command)) {
                        return (TXZResourceManager.STYLE_DEFAULT + tool.supportPlaySubscribe()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_PLAY_COLLECTION.equals(command)) {
                        return (TXZResourceManager.STYLE_DEFAULT + tool.supportPlayCollection()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_SEARCH.equals(command)) {
                        return (TXZResourceManager.STYLE_DEFAULT + tool.supportSearch()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_HAS_NEXT.equals(command)) {
                        return (TXZResourceManager.STYLE_DEFAULT + tool.hasNext()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_HAS_PREV.equals(command)) {
                        return (TXZResourceManager.STYLE_DEFAULT + tool.hasPrev()).getBytes();
                    }
                    return null;
                }
            }
        });
        Tr builder = new Tr();
        builder.T(MusicInvokeConstants.KEY_PUSH_VERSION, (Object) 1);
        builder.T("interceptTts", (Object) Boolean.valueOf(tool.interceptTts()));
        Tn.Tr().T("com.txznet.txz", "txz.audio.setTool", builder.Ty(), (Tn.Tr) null);
    }

    public boolean isPlaying() {
        byte[] data = Tn.Tr().T("txz.audio.isPlaying", (byte[]) null);
        if (data == null) {
            return false;
        }
        return Boolean.parseBoolean(new String(data));
    }

    public void play() {
        Tn.Tr().T("com.txznet.txz", "txz.audio.play", (byte[]) null, (Tn.Tr) null);
    }

    public void pause() {
        Tn.Tr().T("com.txznet.txz", "txz.audio.pause", (byte[]) null, (Tn.Tr) null);
    }

    public void playKeywords(String keywords) {
        Tn.Tr().T("com.txznet.txz", "txz.audio.playFm", keywords.getBytes(), (Tn.Tr) null);
    }

    public void exit() {
        Tn.Tr().T("com.txznet.txz", "txz.audio.exit", (byte[]) null, (Tn.Tr) null);
    }

    public void next() {
        Tn.Tr().T("com.txznet.txz", "txz.audio.next", (byte[]) null, (Tn.Tr) null);
    }

    public void prev() {
        Tn.Tr().T("com.txznet.txz", "txz.audio.prev", (byte[]) null, (Tn.Tr) null);
    }

    public void showXmlySearchResult(boolean show) {
        this.T5 = Boolean.valueOf(show);
        Tn.Tr().T("com.txznet.txz", "txz.audio.showSelect.xmly", this.T5.toString().getBytes(), (Tn.Tr) null);
    }
}
