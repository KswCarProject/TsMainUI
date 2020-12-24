package com.hongfans.rearview.services.api;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class VoiceSearchModel implements Parcelable {
    public static final Parcelable.Creator<VoiceSearchModel> CREATOR = new Parcelable.Creator<VoiceSearchModel>() {
        public VoiceSearchModel createFromParcel(Parcel in) {
            return new VoiceSearchModel(in);
        }

        public VoiceSearchModel[] newArray(int size) {
            return new VoiceSearchModel[size];
        }
    };
    private String appid;
    private String appsecret;
    private String keyword;
    private String pid;
    private String semantics;

    public String getAppid() {
        return this.appid;
    }

    public void setAppid(String appid2) {
        this.appid = appid2;
    }

    public String getAppsecret() {
        return this.appsecret;
    }

    public void setAppsecret(String appsecret2) {
        this.appsecret = appsecret2;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid2) {
        this.pid = pid2;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword2) {
        this.keyword = keyword2;
    }

    public String getSemantics() {
        return this.semantics;
    }

    public void setSemantics(String semantics2) {
        this.semantics = semantics2;
    }

    public VoiceSearchModel(Parcel pl) {
        this.appid = pl.readString();
        this.appsecret = pl.readString();
        this.pid = pl.readString();
        this.keyword = pl.readString();
        this.semantics = pl.readString();
    }

    public VoiceSearchModel() {
    }

    public VoiceSearchModel(Bundle bundle) {
        String bappid = bundle.getString("appid");
        String bappsecret = bundle.getString("appsecret");
        String bpid = bundle.getString("pid");
        String bkeyword = bundle.getString("keyword");
        String bsemantics = bundle.getString("semantics");
        if (bappid != null && bappid.length() > 0) {
            this.appid = bappid;
        }
        if (bappsecret != null && bappsecret.length() > 0) {
            this.appsecret = bappsecret;
        }
        if (bpid != null && bpid.length() > 0) {
            this.pid = bpid;
        }
        if (bkeyword != null && bkeyword.length() > 0) {
            this.keyword = bkeyword;
        }
        if (bsemantics != null && bsemantics.length() > 0) {
            this.semantics = bsemantics;
        }
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("appid", this.appid);
        bundle.putString("appsecret", this.appsecret);
        bundle.putString("pid", this.pid);
        bundle.putString("keyword", this.keyword);
        bundle.getString("semantics", this.semantics);
        return bundle;
    }

    public String toString() {
        return "VoiceSearchModel [appid=" + this.appid + ", appsecret=" + this.appsecret + ", pid=" + this.pid + ", keyword=" + this.keyword + ", semantics=" + this.semantics + "]";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appid);
        dest.writeString(this.appsecret);
        dest.writeString(this.pid);
        dest.writeString(this.keyword);
        dest.writeString(this.semantics);
    }
}
