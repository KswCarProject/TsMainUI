package com.ts.tsspeechlib.bt;

import android.os.Parcel;
import android.os.Parcelable;

public class ITsSpeechBtPbInfo implements Parcelable {
    public static final Parcelable.Creator<ITsSpeechBtPbInfo> CREATOR = new Parcelable.Creator<ITsSpeechBtPbInfo>() {
        public ITsSpeechBtPbInfo createFromParcel(Parcel source) {
            return new ITsSpeechBtPbInfo(source);
        }

        public ITsSpeechBtPbInfo[] newArray(int size) {
            return new ITsSpeechBtPbInfo[size];
        }
    };
    private String name;
    private String num;

    public ITsSpeechBtPbInfo() {
    }

    public ITsSpeechBtPbInfo(Parcel source) {
        setName(source.readString());
        setNum(source.readString());
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String num2) {
        this.num = num2;
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
    }
}
