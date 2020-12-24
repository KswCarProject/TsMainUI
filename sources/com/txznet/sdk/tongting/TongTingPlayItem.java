package com.txznet.sdk.tongting;

/* compiled from: Proguard */
public class TongTingPlayItem {

    /* renamed from: T  reason: collision with root package name */
    String f885T;
    long T5;
    String T9;
    int TE;
    int TZ;
    int Tk;
    String Tn;
    String Tr;
    String Ty;

    public TongTingPlayItem(int sid, long id, String title, String logo, String source, String artists, String albumName, int favourFlag, int playState) {
        this.T5 = id;
        this.TE = sid;
        this.Tk = favourFlag;
        this.f885T = title;
        this.Tr = logo;
        this.Ty = source;
        this.Tn = artists;
        this.T9 = albumName;
        this.TZ = playState;
    }

    public String toString() {
        return "PlayItem{title='" + this.f885T + '\'' + ", logo='" + this.Tr + '\'' + ", source='" + this.Ty + '\'' + ", artists='" + this.Tn + '\'' + ", albumName='" + this.T9 + '\'' + ", favourFlag=" + this.Tk + ", playState=" + this.TZ + ", sid=" + this.TE + ", id=" + this.T5 + '}';
    }

    public String getTitle() {
        return this.f885T;
    }

    public String getLogo() {
        return this.Tr;
    }

    public String getSource() {
        return this.Ty;
    }

    public String getArtists() {
        return this.Tn;
    }

    public String getAlbumName() {
        return this.T9;
    }

    public int getFavourFlag() {
        return this.Tk;
    }

    public void setFavourFlag(int favourFlag) {
        this.Tk = favourFlag;
    }

    public int getSid() {
        return this.TE;
    }

    public long getId() {
        return this.T5;
    }

    public int getPlayState() {
        return this.TZ;
    }

    public void setPlayState(int playState) {
        this.TZ = playState;
    }
}
