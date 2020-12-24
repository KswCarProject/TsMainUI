package com.ts.bt;

import android.os.Parcel;
import android.os.Parcelable;

public class PbInfo implements Parcelable {
    public static final Parcelable.Creator<PbInfo> CREATOR = new Parcelable.Creator<PbInfo>() {
        public PbInfo createFromParcel(Parcel source) {
            return new PbInfo(source);
        }

        public PbInfo[] newArray(int size) {
            return new PbInfo[size];
        }
    };
    private String name;
    private String num;
    private String pinyin;

    public PbInfo() {
    }

    public PbInfo(Parcel source) {
        setName(source.readString());
        setNum(source.readString());
        setPinyin(source.readString());
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String num2) {
        this.num = num2;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String pinyin2) {
        this.pinyin = pinyin2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.num);
        dest.writeString(this.pinyin);
    }
}
