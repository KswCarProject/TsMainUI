package com.autochips.camera;

import android.os.Parcel;
import android.os.Parcelable;

public class Resolution implements Parcelable {
    public static final Parcelable.Creator<Resolution> CREATOR = new Parcelable.Creator<Resolution>() {
        public Resolution createFromParcel(Parcel in) {
            return new Resolution(in);
        }

        public Resolution[] newArray(int size) {
            return new Resolution[size];
        }
    };
    private int mHeight;
    private int mWidth;

    public Resolution(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
    }

    protected Resolution(Parcel in) {
        this.mWidth = in.readInt();
        this.mHeight = in.readInt();
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public String toString() {
        return String.valueOf(this.mWidth) + "x" + this.mHeight;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mWidth);
        parcel.writeInt(this.mHeight);
    }

    public void readFromParcel(Parcel source) {
        this.mWidth = source.readInt();
        this.mHeight = source.readInt();
    }

    public int describeContents() {
        return 0;
    }
}
