package com.hongfans.carmedia.processes.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.ts.main.common.ShellUtils;
import java.io.IOException;

public final class Status extends ProcFile {
    public static final Parcelable.Creator<Status> CREATOR = new Parcelable.Creator<Status>() {
        public Status createFromParcel(Parcel source) {
            return new Status(source);
        }

        public Status[] newArray(int size) {
            return new Status[size];
        }
    };

    private Status(String path) throws IOException {
        super(path);
    }

    private Status(Parcel in) {
        super(in);
    }

    public static Status get(int pid) throws IOException {
        return new Status(String.format("/proc/%d/status", new Object[]{Integer.valueOf(pid)}));
    }

    public String getValue(String fieldName) {
        for (String line : this.content.split(ShellUtils.COMMAND_LINE_END)) {
            if (line.startsWith(fieldName + ":")) {
                return line.split(fieldName + ":")[1].trim();
            }
        }
        return null;
    }

    public int getUid() {
        try {
            return Integer.parseInt(getValue("Uid").split("\\s+")[0]);
        } catch (Exception e) {
            return -1;
        }
    }

    public int getGid() {
        try {
            return Integer.parseInt(getValue("Gid").split("\\s+")[0]);
        } catch (Exception e) {
            return -1;
        }
    }
}
