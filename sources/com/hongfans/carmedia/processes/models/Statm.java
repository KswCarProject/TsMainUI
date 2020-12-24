package com.hongfans.carmedia.processes.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.IOException;

public final class Statm extends ProcFile {
    public static final Parcelable.Creator<Statm> CREATOR = new Parcelable.Creator<Statm>() {
        public Statm createFromParcel(Parcel source) {
            return new Statm(source);
        }

        public Statm[] newArray(int size) {
            return new Statm[size];
        }
    };
    public final String[] fields;

    private Statm(String path) throws IOException {
        super(path);
        this.fields = this.content.split("\\s+");
    }

    private Statm(Parcel in) {
        super(in);
        this.fields = in.createStringArray();
    }

    public static Statm get(int pid) throws IOException {
        return new Statm(String.format("/proc/%d/statm", new Object[]{Integer.valueOf(pid)}));
    }

    public long getSize() {
        return Long.parseLong(this.fields[0]) * 1024;
    }

    public long getResidentSetSize() {
        return Long.parseLong(this.fields[1]) * 1024;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeStringArray(this.fields);
    }
}
