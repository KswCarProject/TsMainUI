package com.txznet.sdk;

import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.tongting.IConstantCmd;
import com.txznet.sdk.tongting.IConstantData;
import com.txznet.sdk.tongting.TongTingAudio;
import com.txznet.sdk.tongting.TongTingPlayItem;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class TXZTongTingListenerManager implements IConstantCmd, IConstantData {

    /* renamed from: T  reason: collision with root package name */
    private static volatile TXZTongTingListenerManager f836T;
    private List<INotifyInfoListener> Tr = new ArrayList();

    /* compiled from: Proguard */
    public interface INotifyInfoListener {
        void notifyFavour(int i);

        void notifyPlayInfo(TongTingPlayItem tongTingPlayItem);

        void notifyPlaylist(List<TongTingAudio> list, boolean z);

        void notifyState(int i);
    }

    private TXZTongTingListenerManager() {
        T();
    }

    public static TXZTongTingListenerManager getInstance() {
        if (f836T == null) {
            synchronized (TXZTongTingListenerManager.class) {
                if (f836T == null) {
                    f836T = new TXZTongTingListenerManager();
                }
            }
        }
        return f836T;
    }

    public void addStatusListener(INotifyInfoListener listener) {
        this.Tr.add(listener);
        Tn.Tr().T("com.txznet.music", IConstantCmd.SEND_CMD_ADDLISTENER, (byte[]) null, (Tn.Tr) null);
    }

    /* access modifiers changed from: private */
    public void T(List<TongTingAudio> audios, boolean isAdded) {
        for (INotifyInfoListener iNotifyInfoListener : this.Tr) {
            iNotifyInfoListener.notifyPlaylist(audios, isAdded);
        }
    }

    /* access modifiers changed from: private */
    public void T(int state) {
        for (INotifyInfoListener iNotifyInfoListener : this.Tr) {
            iNotifyInfoListener.notifyState(state);
        }
    }

    /* access modifiers changed from: private */
    public void Tr(int favourState) {
        for (INotifyInfoListener iNotifyInfoListener : this.Tr) {
            iNotifyInfoListener.notifyFavour(favourState);
        }
    }

    /* access modifiers changed from: private */
    public void T(TongTingPlayItem playItem) {
        for (INotifyInfoListener iNotifyInfoListener : this.Tr) {
            iNotifyInfoListener.notifyPlayInfo(playItem);
        }
    }

    private void T() {
        TXZService.T(IConstantData.REC_CALLBACK_PERFIX, new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                JSONArray val;
                Tr tr = new Tr(data);
                if (IConstantData.CALLBACK_ONPLAYINFOUPDATED.equals(command)) {
                    long id = ((Long) tr.T("id", Long.class, null)).longValue();
                    TXZTongTingListenerManager.this.T(new TongTingPlayItem(((Integer) tr.T(IConstantData.KEY_SID, Integer.class, null)).intValue(), id, (String) tr.T("title", String.class, TXZResourceManager.STYLE_DEFAULT), (String) tr.T(IConstantData.KEY_LOGO, String.class, TXZResourceManager.STYLE_DEFAULT), (String) tr.T(IConstantData.KEY_SOURCE_FROM, String.class, TXZResourceManager.STYLE_DEFAULT), (String) tr.T(IConstantData.KEY_ARTISTS, String.class, TXZResourceManager.STYLE_DEFAULT), (String) tr.T(IConstantData.KEY_ALBUMNAME, String.class, TXZResourceManager.STYLE_DEFAULT), ((Integer) tr.T(IConstantData.KEY_FLAG, Integer.class, null)).intValue(), ((Integer) tr.T(IConstantData.KEY_STATE, Integer.class, null)).intValue()));
                } else if (IConstantData.CALLBACK_ONPROGRESSUPDATED.equals(command)) {
                    tr.T("progress", Integer.class, null);
                    tr.T(IConstantData.KEY_DURATION, Integer.class, null);
                } else if (IConstantData.CALLBACK_ONPLAYERMODEUPDATED.equals(command)) {
                    tr.T(IConstantData.KEY_AUDIO_MODE, Integer.class, null);
                } else if (IConstantData.CALLBACK_ONPLAYERSTATUSUPDATED.equals(command)) {
                    TXZTongTingListenerManager.this.T(((Integer) tr.T(IConstantData.KEY_STATE, Integer.class, null)).intValue());
                } else if (!IConstantData.CALLBACK_ONBUFFERPROGRESSUPDATED.equals(command)) {
                    if (IConstantData.CALLBACK_ONFAVOURSTATUSUPDATED.equals(command)) {
                        TXZTongTingListenerManager.this.Tr(((Integer) tr.T(IConstantData.KEY_FAVOUR, Integer.class, null)).intValue());
                    } else if (IConstantData.CALLBACK_ONPLAYLISTCHANGED.equals(command) && (val = (JSONArray) tr.T("data", JSONArray.class)) != null) {
                        try {
                            TXZTongTingListenerManager.this.T(TongTingAudio.createAudios(val), false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return new byte[0];
            }
        });
    }
}
