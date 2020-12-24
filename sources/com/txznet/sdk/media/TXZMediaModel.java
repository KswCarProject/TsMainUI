package com.txznet.sdk.media;

import com.txznet.sdk.TXZMusicManager;
import com.txznet.sdk.tongting.IConstantData;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Proguard */
public class TXZMediaModel {

    /* renamed from: T  reason: collision with root package name */
    private String f872T;
    private String T9;
    private String TZ;
    private String Tk;
    private String Tn;
    private String[] Tr;
    private String[] Ty;

    public void setTitle(String title) {
        this.f872T = title;
    }

    public void setKeywords(String[] keywords) {
        this.Tr = keywords;
    }

    public void setKeyword(String keyword) {
        this.Tr = new String[]{keyword};
    }

    public void setArtists(String[] artists) {
        this.Ty = artists;
    }

    public void setArtist(String artist) {
        this.Ty = new String[]{artist};
    }

    public void setAsrText(String asrText) {
        this.Tn = asrText;
    }

    public void setAlbum(String album) {
        this.T9 = album;
    }

    public void setCategory(String category) {
        this.Tk = category;
    }

    public void setSubCategory(String subCategory) {
        this.TZ = subCategory;
    }

    public String getTitle() {
        return this.f872T;
    }

    public String[] getKeywords() {
        return this.Tr;
    }

    public String[] getArtists() {
        return this.Ty;
    }

    public String getAsrText() {
        return this.Tn;
    }

    public String getAlbum() {
        return this.T9;
    }

    public String getCategory() {
        return this.Tk;
    }

    public String getSubCategory() {
        return this.TZ;
    }

    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put(IConstantData.KEY_TITLE, this.f872T);
            obj.put("text", this.Tn);
            obj.put("album", this.T9);
            obj.put("category", this.Tk);
            obj.put("subcategory", this.TZ);
            JSONArray jsonArtists = new JSONArray();
            if (this.Ty != null) {
                for (int i = 0; i < this.Ty.length; i++) {
                    if (this.Ty[i] != null) {
                        jsonArtists.put(this.Ty[i]);
                    }
                }
            }
            obj.put("artist", jsonArtists);
            JSONArray jsonKeywords = new JSONArray();
            if (this.Tr != null) {
                for (int i2 = 0; i2 < this.Tr.length; i2++) {
                    if (this.Tr[i2] != null) {
                        jsonKeywords.put(this.Tr[i2]);
                    }
                }
            }
            obj.put("keywords", jsonKeywords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static TXZMediaModel fromJSONObject(JSONObject obj) {
        if (obj == null) {
            return null;
        }
        TXZMediaModel model = new TXZMediaModel();
        try {
            if (obj.has(IConstantData.KEY_TITLE)) {
                model.setTitle(obj.getString(IConstantData.KEY_TITLE));
            }
            if (obj.has("text")) {
                model.setAsrText(obj.getString("text"));
            }
            if (obj.has("album")) {
                model.setAlbum(obj.getString("album"));
            }
            if (obj.has("category")) {
                model.setCategory(obj.getString("category"));
            }
            if (obj.has("subcategory")) {
                model.setSubCategory(obj.getString("subcategory"));
            }
            if (obj.has("keywords")) {
                JSONArray jsonKeywords = obj.getJSONArray("keywords");
                String[] arrKeywords = new String[jsonKeywords.length()];
                int len = jsonKeywords.length();
                for (int i = 0; i < len; i++) {
                    arrKeywords[i] = jsonKeywords.getString(i);
                }
            }
            if (!obj.has("artist")) {
                return model;
            }
            JSONArray jsonArtists = obj.getJSONArray("artist");
            String[] arrArtists = new String[jsonArtists.length()];
            int len2 = jsonArtists.length();
            for (int i2 = 0; i2 < len2; i2++) {
                arrArtists[i2] = jsonArtists.getString(i2);
            }
            return model;
        } catch (Exception e) {
            return null;
        }
    }

    public static TXZMediaModel fromMusicModel(TXZMusicManager.MusicModel model) {
        TXZMediaModel ret = new TXZMediaModel();
        ret.setTitle(model.getTitle());
        ret.setKeywords(model.getKeywords());
        ret.setArtists(model.getArtist());
        ret.setAlbum(model.getAlbum());
        ret.setAsrText(model.getText());
        ret.setSubCategory(model.getSubCategory());
        return ret;
    }
}
