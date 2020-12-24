package com.txznet.sdk;

import android.util.Log;
import android.widget.Toast;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.Ty.T9;
import com.txznet.comm.Ty.Tr;
import com.txznet.comm.base.T.T;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.media.AbsTXZMusicTool;
import com.txznet.sdk.media.InvokeConstants;
import com.txznet.sdk.media.PlayerLoopMode;
import com.txznet.sdk.media.TXZMediaModel;
import com.txznet.sdk.music.MusicInvokeConstants;
import com.txznet.sdk.tongting.IConstantData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
public class TXZMusicManager {
    private static TXZMusicManager TZ = new TXZMusicManager();

    /* renamed from: T  reason: collision with root package name */
    List<MusicToolStatusListener> f748T;
    private Object T5 = null;
    Collection<MusicModel> T9 = null;
    private boolean TE = false;
    private String Th;
    Collection<MusicModel> Tk = null;
    Boolean Tn = null;
    AudioTool Tr;
    private Boolean Tv = null;
    boolean Ty;

    /* compiled from: Proguard */
    public enum AudioTool {
        AUDIO_TXZ,
        AUDIO_KL,
        AUDIO_TT,
        AUDIO_XMLY
    }

    /* compiled from: Proguard */
    public interface MusicTool {
        void continuePlay();

        void exit();

        void favourMusic();

        MusicModel getCurrentMusicModel();

        boolean isPlaying();

        void next();

        void pause();

        void play();

        void playFavourMusic();

        void playMusic(MusicModel musicModel);

        void playRandom();

        void prev();

        void setStatusListener(MusicToolStatusListener musicToolStatusListener);

        void switchModeLoopAll();

        void switchModeLoopOne();

        void switchModeRandom();

        void switchSong();

        void unfavourMusic();
    }

    /* compiled from: Proguard */
    public interface MusicToolEx extends MusicTool {
        boolean needTts();
    }

    /* compiled from: Proguard */
    public interface MusicToolStatusListener extends T {
        public static final int STATE_BUFFERING = 3;
        public static final int STATE_PAUSE_PLAY = 2;
        public static final int STATE_SONG_CHANGE = 4;
        public static final int STATE_START_PLAY = 1;
        public static final int STATE_UNKNOW = 0;

        void endMusic(MusicModel musicModel);

        void onStatusChange();

        void onStatusChange(int i);

        void playMusic(MusicModel musicModel);
    }

    /* compiled from: Proguard */
    public enum MusicToolType {
        MUSIC_TOOL_TXZ,
        MUSIC_TOOL_KUWO,
        MUSIC_TOOL_KAOLA
    }

    private TXZMusicManager() {
    }

    public static TXZMusicManager getInstance() {
        return TZ;
    }

    /* access modifiers changed from: package-private */
    public void T() {
        if (this.TE) {
            if (this.T5 == null) {
                setMusicTool((MusicToolType) null);
            } else if (this.T5 instanceof MusicToolType) {
                setMusicTool((MusicToolType) this.T5);
            } else if (this.T5 instanceof MusicTool) {
                setMusicTool((MusicTool) this.T5);
            } else if (this.T5 instanceof AbsTXZMusicTool) {
                setMusicTool((AbsTXZMusicTool) this.T5);
            }
        }
        if (this.Ty) {
            setDefaultAudioTool(this.Tr);
        }
        if (this.T9 != null) {
            syncMuicList(this.T9);
        }
        if (this.Tk != null) {
            syncExMuicList(this.Tk);
        }
        if (this.Tn != null) {
            showKuwoSearchResult(this.Tn.booleanValue());
        }
        if (this.Th != null) {
            setTTMusicControlTaskId(this.Th);
        }
    }

    /* compiled from: Proguard */
    public static class MusicModel {

        /* renamed from: T  reason: collision with root package name */
        protected String f754T;
        protected String T9;
        protected String TE;
        protected String TZ;
        protected int Tk;
        protected String[] Tn;
        protected String Tr;
        protected String[] Ty;

        public String getText() {
            return this.TZ;
        }

        public void setText(String text) {
            this.TZ = text;
        }

        public String getSubCategory() {
            return this.TE;
        }

        public void setSubCategory(String subCategory) {
            this.TE = subCategory;
        }

        public int getField() {
            return this.Tk;
        }

        public void setField(int field) {
            this.Tk = field;
        }

        public String getTitle() {
            return this.f754T;
        }

        public void setTitle(String title) {
            this.f754T = title;
        }

        public String getAlbum() {
            return this.Tr;
        }

        public void setAlbum(String album) {
            this.Tr = album;
        }

        public String[] getArtist() {
            return this.Ty;
        }

        public void setArtist(String[] artist) {
            this.Ty = artist;
        }

        public String[] getKeywords() {
            return this.Tn;
        }

        public void setKeywords(String[] keywords) {
            this.Tn = keywords;
        }

        public String getPath() {
            return this.T9;
        }

        public void setPath(String path) {
            this.T9 = path;
        }

        public String toString() {
            try {
                JSONObject json = new JSONObject();
                json.put(IConstantData.KEY_TITLE, this.f754T);
                json.put("album", this.Tr);
                JSONArray jsonArtists = new JSONArray();
                if (this.Ty != null) {
                    for (int i = 0; i < this.Ty.length; i++) {
                        if (this.Ty[i] != null) {
                            jsonArtists.put(this.Ty[i]);
                        }
                    }
                }
                json.put("artist", jsonArtists);
                JSONArray jsonKeywords = new JSONArray();
                if (this.Tn != null) {
                    for (int i2 = 0; i2 < this.Tn.length; i2++) {
                        if (this.Tn[i2] != null) {
                            jsonKeywords.put(this.Tn[i2]);
                        }
                    }
                }
                json.put("keywords", jsonKeywords);
                json.put("field", this.Tk);
                json.put(TXZCameraManager.REMOTE_NAME_VIDEO_PATH, this.T9);
                json.put("text", this.TZ);
                json.put("subcategory", this.TE);
                return json.toString();
            } catch (Exception e) {
                return null;
            }
        }

        public static String collecionToString(Collection<MusicModel> musics) {
            JSONArray json = new JSONArray();
            for (MusicModel m : musics) {
                json.put(m.toString());
            }
            return json.toString();
        }

        public static Collection<MusicModel> collecionFromString(String data) {
            try {
                JSONArray json = new JSONArray(data);
                Collection<MusicModel> musics = new ArrayList<>(json.length());
                for (int i = 0; i < json.length(); i++) {
                    musics.add(fromString(json.getString(i)));
                }
                return musics;
            } catch (JSONException e) {
                return null;
            }
        }

        public static MusicModel fromString(String data) {
            try {
                MusicModel model = new MusicModel();
                Tr jsonBuilder = new Tr(data);
                model.f754T = (String) jsonBuilder.T(IConstantData.KEY_TITLE, String.class);
                model.Tr = (String) jsonBuilder.T("album", String.class);
                model.T9 = (String) jsonBuilder.T(TXZCameraManager.REMOTE_NAME_VIDEO_PATH, String.class);
                model.Ty = (String[]) jsonBuilder.T("artist", String[].class);
                model.Tn = (String[]) jsonBuilder.T("keywords", String[].class);
                model.TZ = (String) jsonBuilder.T("text", String.class, "");
                model.Tk = ((Integer) jsonBuilder.T("field", Integer.TYPE, 0)).intValue();
                return model;
            } catch (Exception e) {
                Tn.T("MusicModel from json error: " + data, (Throwable) e);
                return null;
            }
        }
    }

    public void setMusicTool(MusicToolType type) {
        this.TE = true;
        this.T5 = type;
        if (type == null) {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.cleartool", (byte[]) null, (Tn.Tr) null);
        } else {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.setInnerTool", type.name().getBytes(), (Tn.Tr) null);
        }
    }

    public void addMusicStatusListener(MusicToolStatusListener mtsl) {
        if (mtsl != null) {
            if (this.f748T == null) {
                this.f748T = new ArrayList();
            }
            boolean needNotify = false;
            if (this.f748T.size() < 1) {
                needNotify = true;
            }
            this.f748T.add(mtsl);
            if (needNotify) {
                TXZService.T("tool.music.status.", new TXZService.T() {
                    public byte[] T(String packageName, String command, byte[] data) {
                        if (TXZMusicManager.this.f748T != null) {
                            if (command.equals("onStatusChange")) {
                                int state = 0;
                                try {
                                    state = Integer.parseInt(new String(data));
                                } catch (Exception e) {
                                }
                                for (MusicToolStatusListener listener : TXZMusicManager.this.f748T) {
                                    listener.onStatusChange(state);
                                }
                            }
                            if (command.equals("playMusic")) {
                                MusicModel mm = null;
                                try {
                                    mm = MusicModel.fromString(new String(data));
                                } catch (Exception e2) {
                                }
                                for (MusicToolStatusListener listener2 : TXZMusicManager.this.f748T) {
                                    listener2.playMusic(mm);
                                }
                            }
                            if (command.equals("endMusic")) {
                                MusicModel mm2 = null;
                                try {
                                    mm2 = MusicModel.fromString(new String(data));
                                } catch (Exception e3) {
                                }
                                for (MusicToolStatusListener listener3 : TXZMusicManager.this.f748T) {
                                    listener3.endMusic(mm2);
                                }
                            }
                            if (command.equals("onProgress")) {
                                Tr json = new Tr(data);
                                for (MusicToolStatusListener listener4 : TXZMusicManager.this.f748T) {
                                    listener4.T(((Integer) json.T(TXZCameraManager.REMOTE_NAME_CAMERA_POSITION, Integer.TYPE)).intValue(), ((Integer) json.T(IConstantData.KEY_DURATION, Integer.TYPE)).intValue());
                                }
                            }
                        }
                        return null;
                    }
                });
                com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.musiclistener.set", (byte[]) null, (Tn.Tr) null);
            }
        }
    }

    public void removeMusicStatusListener(MusicToolStatusListener listener) {
        if (listener != null) {
            if (this.f748T != null && this.f748T.contains(listener)) {
                this.f748T.remove(listener);
            }
            if (this.f748T.size() < 1) {
                com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.musiclistener.clear", (byte[]) null, (Tn.Tr) null);
            }
        }
    }

    public void setStartAppPlay(Boolean play) {
        this.Tv = play;
        com.txznet.comm.Tr.Tn Tr2 = com.txznet.comm.Tr.Tn.Tr();
        Object obj = play;
        if (play == null) {
            obj = "true";
        }
        Tr2.T("com.txznet.music", "music.startappplay", String.valueOf(obj).getBytes(), (Tn.Tr) null);
    }

    @Deprecated
    public void setFullScreen(Boolean play) {
        this.Tv = play;
        com.txznet.comm.Tr.Tn Tr2 = com.txznet.comm.Tr.Tn.Tr();
        Object obj = play;
        if (play == null) {
            obj = "true";
        }
        Tr2.T("com.txznet.music", "music.fullscreen", String.valueOf(obj).getBytes(), (Tn.Tr) null);
    }

    public void setBackbtnVisible(Boolean visible) {
        com.txznet.comm.Tr.Tn Tr2 = com.txznet.comm.Tr.Tn.Tr();
        Object obj = visible;
        if (visible == null) {
            obj = "true";
        }
        Tr2.T("com.txznet.music", "music.backVisible", String.valueOf(obj).getBytes(), (Tn.Tr) null);
    }

    public void setHideTips(Boolean visible) {
        com.txznet.comm.Tr.Tn Tr2 = com.txznet.comm.Tr.Tn.Tr();
        Object obj = visible;
        if (visible == null) {
            obj = "true";
        }
        Tr2.T("com.txznet.music", "music.param.tips.show", String.valueOf(obj).getBytes(), (Tn.Tr) null);
    }

    public void setTipShowPosition(String position) {
        if (T9.T(position)) {
            Log.e("newbie", "setTipShowPosition你设置了" + position + ".不符合规范,使用默认样式TOP");
        } else {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.tips.gravity", position.toUpperCase().getBytes(), (Tn.Tr) null);
        }
    }

    public void setEnableFloatingPlayer(Boolean enable) {
        com.txznet.comm.Tr.Tn Tr2 = com.txznet.comm.Tr.Tn.Tr();
        Object obj = enable;
        if (enable == null) {
            obj = "true";
        }
        Tr2.T("com.txznet.music", "music.enableFloatingPlayer", String.valueOf(obj).getBytes(), (Tn.Tr) null);
    }

    public void setEnableSplash(Boolean enable) {
        com.txznet.comm.Tr.Tn Tr2 = com.txznet.comm.Tr.Tn.Tr();
        Object obj = enable;
        if (enable == null) {
            obj = "true";
        }
        Tr2.T("com.txznet.music", "music.enableSplash", String.valueOf(obj).getBytes(), (Tn.Tr) null);
    }

    public void setEnableAudoJumpPlayerPage(Boolean enable) {
        com.txznet.comm.Tr.Tn Tr2 = com.txznet.comm.Tr.Tn.Tr();
        Object obj = enable;
        if (enable == null) {
            obj = "true";
        }
        Tr2.T("com.txznet.music", "music.autoJumpPlayerPage", String.valueOf(obj).getBytes(), (Tn.Tr) null);
    }

    public void setIsCloseVolume(Boolean close) {
        com.txznet.comm.Tr.Tn Tr2 = com.txznet.comm.Tr.Tn.Tr();
        Object obj = close;
        if (close == null) {
            obj = "false";
        }
        Tr2.T("com.txznet.music", "music.closeVolume", String.valueOf(obj).getBytes(), (Tn.Tr) null);
    }

    public void setNotOpenAppPName(String[] sContent) {
        Tr builder = new Tr();
        builder.T(IConstantData.KEY_DATA, (Object) sContent);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.notOpenAppPName", builder.Ty(), (Tn.Tr) null);
    }

    public void setExitWithPlay(boolean withplay) {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.setExitWithPlay", String.valueOf(withplay).getBytes(), (Tn.Tr) null);
    }

    public void setExtraTypeface(boolean needExtra) {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.setExtraTypeface", String.valueOf(needExtra).getBytes(), (Tn.Tr) null);
    }

    public void setReleaseAudioFocus(Boolean isRealse) {
        if (isRealse == null) {
            isRealse = true;
        }
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.releaseAudioFocus", String.valueOf(isRealse).getBytes(), (Tn.Tr) null);
    }

    public void setWakeupPlay(Boolean isPlay) {
        if (isPlay == null) {
            isPlay = true;
        }
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.wakeupPlay", String.valueOf(isPlay).getBytes(), (Tn.Tr) null);
    }

    public void setResumeAutoPlay(boolean set) {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.resume_auto_play", String.valueOf(set).getBytes(), (Tn.Tr) null);
    }

    public void setShortPlayEnable(boolean enable) {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.shortPlayEnable", String.valueOf(enable).getBytes(), (Tn.Tr) null);
    }

    public void setShortPlayNeedTrigger(boolean enable) {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.setShortPlayNeedTrigger", String.valueOf(enable).getBytes(), (Tn.Tr) null);
    }

    public void triggerShortPlay() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.triggerShortPlay", "".getBytes(), (Tn.Tr) null);
    }

    public void setShowExitDialog(boolean enable) {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.showExitDialog", String.valueOf(enable).getBytes(), (Tn.Tr) null);
    }

    public void setLocalSearchSize(Integer minSize) {
        if (minSize == null || minSize.intValue() < 102400) {
            com.txznet.comm.Tr.Tr.Tn.T("本地扫描设置参数错误,支持的范围[100K~+]");
        } else {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.searchSize", String.valueOf(minSize).getBytes(), (Tn.Tr) null);
        }
    }

    public void setLocalPath(String[] paths) {
        if (paths == null || paths.length <= 0) {
            Toast.makeText(com.txznet.comm.Tr.T.Tr(), "本地扫描路径设置错误", 0).show();
            return;
        }
        Tr builder = new Tr();
        builder.T(IConstantData.KEY_DATA, (Object) paths);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.searchPath", builder.Ty(), (Tn.Tr) null);
    }

    public void setNeedAsr(Boolean need) {
        com.txznet.comm.Tr.Tn Tr2 = com.txznet.comm.Tr.Tn.Tr();
        Object obj = need;
        if (need == null) {
            obj = "true";
        }
        Tr2.T("com.txznet.music", "music.needAsr", String.valueOf(obj).getBytes(), (Tn.Tr) null);
    }

    public void setWakeupDefaultValue(boolean defaultValue) {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.music", "music.wakeup_default", String.valueOf(defaultValue).getBytes(), (Tn.Tr) null);
    }

    @Deprecated
    public void setMusicTool(final MusicTool tool) {
        this.TE = true;
        this.T5 = tool;
        if (tool == null) {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        tool.setStatusListener(new MusicToolStatusListener() {

            /* renamed from: T  reason: collision with root package name */
            Tr f750T = new Tr();

            public void onStatusChange() {
                com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.notifyMusicStatusChange", (byte[]) null, (Tn.Tr) null);
            }

            public void onStatusChange(int state) {
                com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.notifyMusicStatusChange", (state + "").getBytes(), (Tn.Tr) null);
            }

            public void playMusic(MusicModel mm) {
                com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.musiclistener.playMusic", mm.toString().getBytes(), (Tn.Tr) null);
            }

            public void endMusic(MusicModel nextMudule) {
                com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.musiclistener.endMusic", nextMudule.toString().getBytes(), (Tn.Tr) null);
            }

            public void T(int position, int duration) {
            }
        });
        TXZService.T("tool.music.", new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                if (command.equals("isPlaying")) {
                    return ("" + tool.isPlaying()).getBytes();
                }
                if (command.equals(InvokeConstants.INVOKE_PLAY)) {
                    if (((Boolean) new Tr(data).T("continue", Boolean.TYPE, false)).booleanValue()) {
                        tool.play();
                    } else {
                        tool.continuePlay();
                    }
                    return null;
                } else if (command.equals("pause")) {
                    tool.pause();
                    return null;
                } else if (command.equals(InvokeConstants.INVOKE_EXIT)) {
                    tool.exit();
                    return null;
                } else if (command.equals("playMusic")) {
                    tool.playMusic(MusicModel.fromString(new String(data)));
                    return null;
                } else if (command.equals("next")) {
                    tool.next();
                    return null;
                } else if (command.equals(InvokeConstants.INVOKE_PREV)) {
                    tool.prev();
                    return null;
                } else if (command.equals("switchSong")) {
                    tool.switchSong();
                    return null;
                } else if (command.equals("favourMusic")) {
                    tool.favourMusic();
                    return null;
                } else if (command.equals("unfavourMusic")) {
                    tool.unfavourMusic();
                    return null;
                } else if (command.equals("playFavourMusic")) {
                    tool.playFavourMusic();
                    return null;
                } else if (command.equals("playRandom")) {
                    tool.playRandom();
                    return null;
                } else if (command.equals("getCurrentMusicModel")) {
                    return tool.getCurrentMusicModel().toString().getBytes();
                } else {
                    if (command.equals("switchModeLoopAll")) {
                        tool.switchModeLoopAll();
                        return null;
                    } else if (command.equals("switchModeLoopOne")) {
                        tool.switchModeLoopOne();
                        return null;
                    } else if (!command.equals("switchModeRandom")) {
                        return null;
                    } else {
                        tool.switchModeRandom();
                        return null;
                    }
                }
            }
        });
        if (tool instanceof MusicToolEx) {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.settool", (((MusicToolEx) tool).needTts() + "").getBytes(), (Tn.Tr) null);
        } else {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.settool", (byte[]) null, (Tn.Tr) null);
        }
    }

    public void setMusicTool(final AbsTXZMusicTool tool) {
        this.TE = true;
        this.T5 = tool;
        if (tool == null) {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.cleartool", (byte[]) null, (Tn.Tr) null);
            return;
        }
        TXZService.T(InvokeConstants.INVOKE_PREFIX_MUSIC, new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                Tr params = new Tr(data);
                if (InvokeConstants.INVOKE_OPEN.equals(command)) {
                    tool.open(((Boolean) params.T(InvokeConstants.INVOKE_PLAY, Boolean.TYPE)).booleanValue());
                    return null;
                } else if (InvokeConstants.INVOKE_PLAY.equals(command)) {
                    tool.play(TXZMediaModel.fromJSONObject(params.T()));
                    return null;
                } else if (InvokeConstants.INVOKE_CONTINUE_PLAY.equals(command)) {
                    tool.continuePlay();
                    return null;
                } else if ("pause".equals(command)) {
                    tool.pause();
                    return null;
                } else if (InvokeConstants.INVOKE_EXIT.equals(command)) {
                    tool.exit();
                    return null;
                } else if ("next".equals(command)) {
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
                        return ("" + tool.supportLoopMode(PlayerLoopMode.fromModeStr((String) params.T("mode", String.class)))).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_SUBSCRIBE.equals(command)) {
                        return ("" + tool.supportSubscribe()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_UNSUBSCRIBE.equals(command)) {
                        return ("" + tool.supportUnSubscribe()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_COLLECT.equals(command)) {
                        return ("" + tool.supportCollect()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_UNCOLLECT.equals(command)) {
                        return ("" + tool.supportUnCollect()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_PLAY_SUBSCRIBE.equals(command)) {
                        return ("" + tool.supportPlaySubscribe()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_PLAY_COLLECTION.equals(command)) {
                        return ("" + tool.supportPlayCollection()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_SUPPORT_SEARCH.equals(command)) {
                        return ("" + tool.supportSearch()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_HAS_NEXT.equals(command)) {
                        return ("" + tool.hasNext()).getBytes();
                    }
                    if (InvokeConstants.INVOKE_HAS_PREV.equals(command)) {
                        return ("" + tool.hasPrev()).getBytes();
                    }
                    return null;
                }
            }
        });
        Tr builder = new Tr();
        builder.T(MusicInvokeConstants.KEY_PUSH_VERSION, (Object) 2);
        builder.T("interceptTts", (Object) Boolean.valueOf(tool.interceptTts()));
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.settool", builder.Ty(), (Tn.Tr) null);
    }

    @Deprecated
    public void setDefaultAudioTool(AudioTool at) {
        this.Ty = true;
        this.Tr = at;
        if (at == null) {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.audio.cleartool", (byte[]) null, (Tn.Tr) null);
        } else {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.audio.setInnerTool", this.Tr.name().getBytes(), (Tn.Tr) null);
        }
    }

    public void setTTMusicControlTaskId(String taskId) {
        this.Th = taskId;
        if (this.Th != null) {
            com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.setTTMusicControlTaskId", this.Th.getBytes(), (Tn.Tr) null);
        }
    }

    public boolean isPlaying() {
        byte[] data = com.txznet.comm.Tr.Tn.Tr().T("txz.music.isPlaying", (byte[]) null);
        if (data == null) {
            return false;
        }
        return Boolean.parseBoolean(new String(data));
    }

    public boolean isBuffering() {
        byte[] data = com.txznet.comm.Tr.Tn.Tr().T("txz.music.isBuffering", (byte[]) null);
        if (data == null) {
            return false;
        }
        return Boolean.parseBoolean(new String(data));
    }

    public void play() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.play", (byte[]) null, (Tn.Tr) null);
    }

    public void continuePlay() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.cont", (byte[]) null, (Tn.Tr) null);
    }

    public void pause() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.pause", (byte[]) null, (Tn.Tr) null);
    }

    public void exit() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.exit", (byte[]) null, (Tn.Tr) null);
    }

    public void exitAllMusicToolImmediately() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.exitAllMusicToolImmediately", (byte[]) null, (Tn.Tr) null);
    }

    public void next() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.next", (byte[]) null, (Tn.Tr) null);
    }

    public void prev() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.prev", (byte[]) null, (Tn.Tr) null);
    }

    public void switchModeLoopAll() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.switchModeLoopAll", (byte[]) null, (Tn.Tr) null);
    }

    public void switchModeLoopOne() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.switchModeLoopOne", (byte[]) null, (Tn.Tr) null);
    }

    public void switchModeRandom() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.switchModeRandom", (byte[]) null, (Tn.Tr) null);
    }

    public void switchSong() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.switchSong", (byte[]) null, (Tn.Tr) null);
    }

    public void playRandom() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.playRandom", (byte[]) null, (Tn.Tr) null);
    }

    public MusicModel getCurrentMusicModel() {
        try {
            byte[] data = com.txznet.comm.Tr.Tn.Tr().T("txz.music.getCurrentMusicModel", (byte[]) null);
            if (data == null) {
                return null;
            }
            return MusicModel.fromString(new String(data));
        } catch (Exception e) {
            return null;
        }
    }

    public void playFavourMusic() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.playFavourMusic", (byte[]) null, (Tn.Tr) null);
    }

    public void favourMusic() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.favourMusic", (byte[]) null, (Tn.Tr) null);
    }

    public void unfavourMusic() {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.unfavourMusic", (byte[]) null, (Tn.Tr) null);
    }

    public void showKuwoSearchResult(boolean show) {
        this.Tn = Boolean.valueOf(show);
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.showKuwoSearchResult", ("" + show).getBytes(), (Tn.Tr) null);
    }

    public void syncMuicList(Collection<MusicModel> musics) {
        this.T9 = musics;
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.syncMuicList", MusicModel.collecionToString(this.T9).getBytes(), (Tn.Tr) null);
    }

    public void syncExMuicList(Collection<MusicModel> musics) {
        this.Tk = musics;
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.syncExMuicList", MusicModel.collecionToString(this.Tk).getBytes(), (Tn.Tr) null);
    }

    public void syncExMuicListToCore(Collection<MusicModel> musics) {
        com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "txz.music.syncExMuicList", MusicModel.collecionToString(musics).getBytes(), (Tn.Tr) null);
    }
}
