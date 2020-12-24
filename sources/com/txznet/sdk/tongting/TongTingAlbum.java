package com.txznet.sdk.tongting;

import android.support.annotation.NonNull;
import com.txznet.comm.Ty.Tr;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Proguard */
public class TongTingAlbum {

    /* renamed from: T  reason: collision with root package name */
    private long f883T;
    private String T9;
    private int TZ;
    private long Tk;
    private String Tn;
    private int Tr;
    private String Ty;

    public long getId() {
        return this.f883T;
    }

    public void setId(long id) {
        this.f883T = id;
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

    public String getLogo() {
        return this.Tn;
    }

    public void setLogo(String logo) {
        this.Tn = logo;
    }

    public String getDesc() {
        return this.T9;
    }

    public void setDesc(String desc) {
        this.T9 = desc;
    }

    public long getCategoryId() {
        return this.Tk;
    }

    public void setCategoryId(long categoryId) {
        this.Tk = categoryId;
    }

    public int getFlag() {
        return this.TZ;
    }

    public void setFlag(int flag) {
        this.TZ = flag;
    }

    public TongTingAlbum(long id, int sid, String name, String logo, String desc, long categoryId, int flag) {
        this.f883T = id;
        this.Tr = sid;
        this.Ty = name;
        this.Tn = logo;
        this.T9 = desc;
        this.Tk = categoryId;
        this.TZ = flag;
    }

    @NonNull
    public static List<TongTingAlbum> createAlbums(JSONArray jsonArray) throws JSONException {
        List<TongTingAlbum> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Tr jsonBuilder1 = new Tr(jsonArray.getString(i));
            list.add(new TongTingAlbum(((Long) jsonBuilder1.T(IConstantData.KEY_ID, Long.class, 0L)).longValue(), ((Integer) jsonBuilder1.T(IConstantData.KEY_SID, Integer.class, 0)).intValue(), (String) jsonBuilder1.T(IConstantData.KEY_NAME, String.class, ""), (String) jsonBuilder1.T(IConstantData.KEY_LOGO, String.class, ""), (String) jsonBuilder1.T(IConstantData.KEY_DESC, String.class, ""), ((Long) jsonBuilder1.T(IConstantData.KEY_CATEGORYID, Long.class, 0L)).longValue(), ((Integer) jsonBuilder1.T(IConstantData.KEY_ISSUBSCRIBE, Integer.class, 0)).intValue()));
        }
        return list;
    }
}
