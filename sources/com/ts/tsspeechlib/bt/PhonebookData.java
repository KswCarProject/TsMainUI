package com.ts.tsspeechlib.bt;

import android.os.Parcel;
import android.os.Parcelable;

public class PhonebookData implements Parcelable {
    public static final Parcelable.Creator<PhonebookData> CREATOR = new Parcelable.Creator<PhonebookData>() {
        public PhonebookData createFromParcel(Parcel source) {
            return new PhonebookData(source);
        }

        public PhonebookData[] newArray(int size) {
            return new PhonebookData[size];
        }
    };
    public String addr;
    public int collect;
    public String first_name;
    public String given_name;
    public String middle_name;
    public String name;
    public String num;
    public String pinyin;

    public PhonebookData() {
    }

    public PhonebookData(Parcel source) {
        setAddr(source.readString());
        setName(source.readString());
        setNum(source.readString());
        setPinyin(source.readString());
        setCollect(source.readInt());
        setFirst_name(source.readString());
        setMiddle_name(source.readString());
        setGiven_name(source.readString());
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr2) {
        this.addr = addr2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
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

    public int getCollect() {
        return this.collect;
    }

    public void setCollect(int collect2) {
        this.collect = collect2;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name2) {
        this.first_name = first_name2;
    }

    public String getMiddle_name() {
        return this.middle_name;
    }

    public void setMiddle_name(String middle_name2) {
        this.middle_name = middle_name2;
    }

    public String getGiven_name() {
        return this.given_name;
    }

    public void setGiven_name(String given_name2) {
        this.given_name = given_name2;
    }

    public String toString() {
        return "PhonebookData [addr=" + this.addr + ", name=" + this.name + ", num=" + this.num + ", pinyin=" + this.pinyin + ", collect=" + this.collect + ", first_name=" + this.first_name + ", middle_name=" + this.middle_name + ", given_name=" + this.given_name + "]";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.addr);
        dest.writeString(this.name);
        dest.writeString(this.num);
        dest.writeString(this.pinyin);
        dest.writeInt(this.collect);
        dest.writeString(this.first_name);
        dest.writeString(this.middle_name);
        dest.writeString(this.given_name);
    }
}
