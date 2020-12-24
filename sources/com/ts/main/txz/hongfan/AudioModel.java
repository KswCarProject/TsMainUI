package com.ts.main.txz.hongfan;

import com.txznet.sdk.tongting.IConstantData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AudioModel {
    private String album;
    private JSONArray artists;
    private JSONArray keywords;

    public static AudioModel parse(JSONObject json) throws JSONException {
        AudioModel am = null;
        if (json != null) {
            am = new AudioModel();
            if (json.has("album")) {
                am.setAlbum(json.getString("album"));
            }
            if (json.has(IConstantData.KEY_ARTISTS)) {
                am.setArtists(json.getJSONArray(IConstantData.KEY_ARTISTS));
            }
            if (json.has("keywords")) {
                am.setKeywords(json.getJSONArray("keywords"));
            }
        }
        return am;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String album2) {
        this.album = album2;
    }

    public JSONArray getArtists() {
        return this.artists;
    }

    public void setArtists(JSONArray artists2) {
        this.artists = artists2;
    }

    public JSONArray getKeywords() {
        return this.keywords;
    }

    public void setKeywords(JSONArray keywords2) {
        this.keywords = keywords2;
    }
}
