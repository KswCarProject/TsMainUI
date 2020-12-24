package com.txznet.sdk;

import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZMusicManager;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.music.MusicInvokeConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class TXZTongTingPushManager {

    /* renamed from: T  reason: collision with root package name */
    private static volatile TXZTongTingPushManager f835T;
    /* access modifiers changed from: private */
    public PushTool Tn;
    /* access modifiers changed from: private */
    public PushStatusListener Tr;
    /* access modifiers changed from: private */
    public PushInfoListener Ty;

    /* compiled from: Proguard */
    public interface PushInfoListener {
        void onNextAudios(List<TXZMusicManager.MusicModel> list);
    }

    /* compiled from: Proguard */
    public interface PushStatusListener {
        void onInfoChange(String str);

        void onProgressChange(long j, long j2);

        void onStatusChange(int i);
    }

    private TXZTongTingPushManager() {
    }

    public static TXZTongTingPushManager getInstance() {
        if (f835T == null) {
            synchronized (TXZTongTingPushManager.class) {
                if (f835T == null) {
                    f835T = new TXZTongTingPushManager();
                }
            }
        }
        return f835T;
    }

    /* compiled from: Proguard */
    public static abstract class PushTool {
        public static final int INTERCEPT_AUDIO = 4;
        public static final int INTERCEPT_NEWS = 1;
        public static final int INTERCEPT_UPDATE = 2;

        public abstract int getInterceptType();

        public boolean showView() {
            return false;
        }

        public void dismissView() {
        }

        public boolean showData(TXZMusicManager.MusicModel data) {
            return false;
        }
    }

    public void setPushStatusListener(PushStatusListener pushStatusListener) {
        this.Tr = pushStatusListener;
        TXZService.T(MusicInvokeConstants.INVOKE_PREFIX_TONGTING_PUSH_CALLBACK_STATUS, new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                Tr jsonBuilder = new Tr(data);
                if (MusicInvokeConstants.PUSH_INFO.equals(command)) {
                    TXZTongTingPushManager.this.Tr.onInfoChange((String) jsonBuilder.T(MusicInvokeConstants.KEY_INFO, String.class));
                } else if ("status".equals(command)) {
                    TXZTongTingPushManager.this.Tr.onStatusChange(((Integer) jsonBuilder.T(MusicInvokeConstants.KEY_STATUS, Integer.TYPE, 0)).intValue());
                } else if ("progress".equals(command)) {
                    TXZTongTingPushManager.this.Tr.onProgressChange(((Long) jsonBuilder.T(MusicInvokeConstants.KEY_PROGRESS, Long.class, 0L)).longValue(), ((Long) jsonBuilder.T(MusicInvokeConstants.KEY_DURATION, Long.class, 0L)).longValue());
                }
                return null;
            }
        });
    }

    public void setPushInfoListener(PushInfoListener pushInfoListener) {
        this.Ty = pushInfoListener;
        TXZService.T(MusicInvokeConstants.INVOKE_PREFIX_TONGTING_PUSH_NEXT_AUDIOS, new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                Tr jsonBuilder = new Tr(data);
                if (MusicInvokeConstants.PUSH_NEXT_AUDIOS.equals(command)) {
                    JSONArray val = (JSONArray) jsonBuilder.T(MusicInvokeConstants.KEY_NEXT_AUDIOS, JSONArray.class);
                    ArrayList<TXZMusicManager.MusicModel> musicModels = new ArrayList<>();
                    for (int i = 0; i < val.length(); i++) {
                        try {
                            Tr jsonData = new Tr(val.getString(i));
                            TXZMusicManager.MusicModel model = new TXZMusicManager.MusicModel();
                            model.f754T = (String) jsonData.T(MusicInvokeConstants.KEY_TITLE, String.class);
                            musicModels.add(model);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    TXZTongTingPushManager.this.Ty.onNextAudios(musicModels);
                }
                return null;
            }
        });
        Tr jsonBuilder = new Tr();
        jsonBuilder.T(MusicInvokeConstants.KEY_PUSH_NEED_MORE_AUDIOS, (Object) true);
        Tn.Tr().T("com.txznet.music", "music.tongting.push.tool.need.audios", jsonBuilder.Ty(), (Tn.Tr) null);
    }

    public void setPushTool(PushTool tool) {
        this.Tn = tool;
        if (this.Tn == null) {
            clearTool();
            return;
        }
        TXZService.T(MusicInvokeConstants.INVOKE_PREFIX_TONGTING_PUSH, new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                Tr jsonBuilder = new Tr(data);
                if (MusicInvokeConstants.PUSH_SHOW_VIEW.equals(command)) {
                    return String.valueOf(TXZTongTingPushManager.this.Tn.showView()).getBytes();
                }
                if (MusicInvokeConstants.PUSH_DISMISS_VIEW.equals(command)) {
                    TXZTongTingPushManager.this.Tn.dismissView();
                    return null;
                } else if (!MusicInvokeConstants.PUSH_SHOW_DATA.equals(command)) {
                    return new byte[0];
                } else {
                    TXZMusicManager.MusicModel model = new TXZMusicManager.MusicModel();
                    model.f754T = (String) jsonBuilder.T(MusicInvokeConstants.KEY_TITLE, String.class);
                    model.Tr = (String) jsonBuilder.T(MusicInvokeConstants.KEY_ALBUM_NAME, String.class);
                    model.TE = (String) jsonBuilder.T(MusicInvokeConstants.KEY_SUB_TITLE, String.class);
                    model.T9 = (String) jsonBuilder.T(MusicInvokeConstants.KEY_PUSH_ICON, String.class);
                    return String.valueOf(TXZTongTingPushManager.this.Tn.showData(model)).getBytes();
                }
            }
        });
        Tr jsonBuilder = new Tr();
        jsonBuilder.T(MusicInvokeConstants.KEY_PUSH_VERSION, (Object) 1);
        jsonBuilder.T(MusicInvokeConstants.KEY_PUSH_INTERCEPT, (Object) Integer.valueOf(this.Tn.getInterceptType()));
        Tn.Tr().T("com.txznet.music", "music.tongting.push.tool.set", jsonBuilder.Ty(), (Tn.Tr) null);
    }

    public void clearTool() {
        this.Tn = null;
        Tn.Tr().T("com.txznet.music", "music.tongting.push.tool.clear", (byte[]) null, (Tn.Tr) null);
    }

    public void onClickContinue() {
        Tn.Tr().T("com.txznet.music", "music.tongting.push.click.continue", (byte[]) null, (Tn.Tr) null);
    }

    public void onClickCancel() {
        Tn.Tr().T("com.txznet.music", "music.tongting.push.click.cancel", (byte[]) null, (Tn.Tr) null);
    }
}
