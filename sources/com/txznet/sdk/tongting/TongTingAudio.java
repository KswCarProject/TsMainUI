package com.txznet.sdk.tongting;

import android.support.annotation.NonNull;
import com.txznet.comm.Ty.Tr;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class TongTingAudio {

    /* renamed from: T  reason: collision with root package name */
    private long f888T;
    private int Tn;
    private int Tr;
    private String Ty;

    public long getId() {
        return this.f888T;
    }

    public void setId(long id) {
        this.f888T = id;
    }

    public int getSid() {
        return this.Tr;
    }

    public void setSid(int sid) {
        this.Tr = sid;
    }

    public String getName() {
        return this.Ty;
    }

    public void setName(String name) {
        this.Ty = name;
    }

    public int getFlag() {
        return this.Tn;
    }

    public void setFlag(int flag) {
        this.Tn = flag;
    }

    public TongTingAudio(long id, int sid, String name, int flag) {
        this.f888T = id;
        this.Tr = sid;
        this.Ty = name;
        this.Tn = flag;
    }

    @NonNull
    public static List<TongTingAudio> createAudios(JSONArray jsonArray) throws JSONException {
        List<TongTingAudio> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Tr jsonBuilder1 = new Tr(jsonArray.getString(i));
            list.add(new TongTingAudio(((Long) jsonBuilder1.T("id", Long.class, 0L)).longValue(), ((Integer) jsonBuilder1.T(IConstantData.KEY_SID, Integer.class, 0)).intValue(), (String) jsonBuilder1.T("name", String.class, "无"), ((Integer) jsonBuilder1.T(IConstantData.KEY_FLAG, Integer.class, 0)).intValue()));
        }
        return list;
    }
}
