package com.txznet.sdk;

import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.sdk.TXZService;
import com.txznet.sdk.tongting.IConstantCmd;
import com.txznet.sdk.tongting.IConstantData;
import com.txznet.sdk.tongting.TongTingAlbum;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class TXZTongTingDataManager implements IConstantCmd, IConstantData {
    private static volatile TXZTongTingDataManager Tr;
    private static int Ty = 0;

    /* renamed from: T  reason: collision with root package name */
    Map<Integer, ICallback<List<TongTingAlbum>>> f830T;

    /* compiled from: Proguard */
    public interface ICallback<T> {
        void onError(int i);

        void onSuccess(T t);
    }

    private TXZTongTingDataManager() {
        this.f830T = null;
        this.f830T = new HashMap();
        Tr();
    }

    public static TXZTongTingDataManager getInstance() {
        if (Tr == null) {
            synchronized (TXZTongTingDataManager.class) {
                if (Tr == null) {
                    Tr = new TXZTongTingDataManager();
                }
            }
        }
        return Tr;
    }

    /* access modifiers changed from: private */
    public void T(List<TongTingAlbum> listData, int sequenceId, int errorCode) {
        if (T(sequenceId) == null) {
            return;
        }
        if (errorCode == 0) {
            T(sequenceId).onSuccess(listData);
        } else {
            T(sequenceId).onError(errorCode);
        }
    }

    private void T(int sequenceId, ICallback<List<TongTingAlbum>> callback) {
        this.f830T.put(Integer.valueOf(sequenceId), callback);
    }

    private ICallback<List<TongTingAlbum>> T(int sequenceId) {
        return this.f830T.get(Integer.valueOf(sequenceId));
    }

    private synchronized int T() {
        int i = Ty + 1;
        Ty = i;
        if (i >= Integer.MAX_VALUE) {
            Ty = 0;
        }
        return Ty;
    }

    public void getRecommendList(long albumId, int limit, int direction, ICallback<List<TongTingAlbum>> callback) {
        int sequence = T();
        Tr jsonBuilder = new Tr();
        jsonBuilder.T(IConstantData.KEY_ALBUM_ID, (Object) Long.valueOf(albumId));
        jsonBuilder.T(IConstantData.KEY_LIMIT, (Object) Integer.valueOf(limit));
        jsonBuilder.T(IConstantData.KEY_DIRECTION, (Object) Integer.valueOf(direction));
        jsonBuilder.T(IConstantData.KEY_SEQUENCE, (Object) Integer.valueOf(sequence));
        jsonBuilder.T(IConstantData.KEY_TYPE, (Object) 0);
        Tn.Tr().T("com.txznet.music", IConstantCmd.SEND_CMD_GETRECOMMENDALBUM, jsonBuilder.Ty(), (Tn.Tr) null);
        T(sequence, callback);
    }

    public void getSubscribeList(long albumId, int limit, int up, ICallback<List<TongTingAlbum>> callback) {
        int sequence = T();
        Tr jsonBuilder = new Tr();
        jsonBuilder.T(IConstantData.KEY_ALBUM_ID, (Object) Long.valueOf(albumId));
        jsonBuilder.T(IConstantData.KEY_LIMIT, (Object) Integer.valueOf(limit));
        jsonBuilder.T(IConstantData.KEY_DIRECTION, (Object) Integer.valueOf(up));
        jsonBuilder.T(IConstantData.KEY_SEQUENCE, (Object) Integer.valueOf(sequence));
        jsonBuilder.T(IConstantData.KEY_TYPE, (Object) 1);
        Tn.Tr().T("com.txznet.music", IConstantCmd.SEND_CMD_GETRECOMMENDALBUM, jsonBuilder.Ty(), (Tn.Tr) null);
        T(sequence, callback);
    }

    public void playAudio(long id, int sid) {
        Tr jsonBuilder = new Tr();
        jsonBuilder.T(IConstantData.KEY_ID, (Object) Long.valueOf(id));
        jsonBuilder.T(IConstantData.KEY_SID, (Object) Integer.valueOf(sid));
        Tn.Tr().T("com.txznet.music", IConstantCmd.SEND_CMD_PLAY_AUDIO, jsonBuilder.Ty(), (Tn.Tr) null);
    }

    public void playAlbum(long albumId, int albumSid, long categoryId) {
        Tr jsonBuilder = new Tr();
        jsonBuilder.T(IConstantData.KEY_ID, (Object) Long.valueOf(albumId));
        jsonBuilder.T(IConstantData.KEY_SID, (Object) Integer.valueOf(albumSid));
        jsonBuilder.T(IConstantData.KEY_CATEGORYID, (Object) Long.valueOf(categoryId));
        Tn.Tr().T("com.txznet.music", IConstantCmd.SEND_CMD_PLAY_ALBUM, jsonBuilder.Ty(), (Tn.Tr) null);
    }

    private void Tr() {
        TXZService.T(IConstantData.REC_CMD_PERFIX, new TXZService.T() {
            public byte[] T(String packageName, String command, byte[] data) {
                Tr jsonBuilder = new Tr(data);
                if (IConstantData.REC_CMD_GETRECOMMENDALBUM.equals(command)) {
                    int sequenceId = ((Integer) jsonBuilder.T(IConstantData.KEY_SEQUENCE, Integer.class, 0)).intValue();
                    List<TongTingAlbum> list = null;
                    int errorCode = ((Integer) jsonBuilder.T(IConstantData.KEY_ERRORCODE, Integer.class, 0)).intValue();
                    if (errorCode == 0) {
                        try {
                            list = TongTingAlbum.createAlbums((JSONArray) jsonBuilder.T(IConstantData.KEY_DATA, JSONArray.class, new JSONArray()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            errorCode = -2;
                        }
                    }
                    TXZTongTingDataManager.this.T(list, sequenceId, errorCode);
                }
                return new byte[0];
            }
        });
    }
}
