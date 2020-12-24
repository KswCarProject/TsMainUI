package com.hongfans.rearview.services.api;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ADModel implements Parcelable {
    public static final Parcelable.Creator<ADModel> CREATOR = new Parcelable.Creator<ADModel>() {
        public ADModel createFromParcel(Parcel in) {
            return new ADModel(in);
        }

        public ADModel[] newArray(int size) {
            return new ADModel[size];
        }
    };
    private String ad_type;
    private String video_text;
    private String video_url;

    public ADModel(Parcel pl) {
        this.ad_type = pl.readString();
        this.video_text = pl.readString();
        this.video_url = pl.readString();
    }

    public ADModel() {
    }

    public static ADModel parse(JSONObject jsonObject) throws Exception {
        if (jsonObject == null) {
            return null;
        }
        ADModel mode = new ADModel();
        if (jsonObject.has("ad_type")) {
            mode.ad_type = jsonObject.getString("ad_type");
        }
        if (jsonObject.has("video_text")) {
            mode.video_text = jsonObject.getString("video_text");
        }
        if (!jsonObject.has("video_url")) {
            return mode;
        }
        mode.video_url = jsonObject.getString("video_url");
        return mode;
    }

    public static List<ADModel> parseData(JSONArray data) throws Exception {
        if (data == null) {
            return null;
        }
        ArrayList<ADModel> datas = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            datas.add(parse((JSONObject) data.get(i)));
        }
        return datas;
    }

    public String getAd_type() {
        return this.ad_type;
    }

    public void setAd_type(String ad_type2) {
        this.ad_type = ad_type2;
    }

    public String getVideo_text() {
        return this.video_text;
    }

    public void setVideo_text(String video_text2) {
        this.video_text = video_text2;
    }

    public String getVideo_url() {
        return this.video_url;
    }

    public void setVideo_url(String video_url2) {
        this.video_url = video_url2;
    }

    public String toString() {
        return "ADModel [ad_type=" + this.ad_type + ", video_text=" + this.video_text + ", video_url=" + this.video_url + "]";
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ad_type", this.ad_type);
            jsonObject.put("video_text", this.video_text);
            jsonObject.put("video_url", this.video_url);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ad_type);
        dest.writeString(this.video_text);
        dest.writeString(this.video_url);
    }
}
