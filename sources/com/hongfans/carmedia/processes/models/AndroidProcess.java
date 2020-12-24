package com.hongfans.carmedia.processes.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.io.IOException;

public class AndroidProcess implements Parcelable {
    public static final Parcelable.Creator<AndroidProcess> CREATOR = new Parcelable.Creator<AndroidProcess>() {
        public AndroidProcess createFromParcel(Parcel source) {
            return new AndroidProcess(source);
        }

        public AndroidProcess[] newArray(int size) {
            return new AndroidProcess[size];
        }
    };
    public final String name;
    public final int pid;

    public AndroidProcess(int pid2) throws IOException {
        this.pid = pid2;
        this.name = getProcessName(pid2);
    }

    protected AndroidProcess(Parcel in) {
        this.name = in.readString();
        this.pid = in.readInt();
    }

    static String getProcessName(int pid2) throws IOException {
        String cmdline = null;
        try {
            cmdline = ProcFile.readFile(String.format("/proc/%d/cmdline", new Object[]{Integer.valueOf(pid2)})).trim();
        } catch (IOException e) {
        }
        if (TextUtils.isEmpty(cmdline)) {
            return Stat.get(pid2).getComm();
        }
        return cmdline;
    }

    public String read(String filename) throws IOException {
        return ProcFile.readFile(String.format("/proc/%d/%s", new Object[]{Integer.valueOf(this.pid), filename}));
    }

    public String attr_current() throws IOException {
        return read("attr/current");
    }

    public String cmdline() throws IOException {
        return read("cmdline");
    }

    public Cgroup cgroup() throws IOException {
        return Cgroup.get(this.pid);
    }

    public int oom_score() throws IOException {
        return Integer.parseInt(read("oom_score"));
    }

    public int oom_adj() throws IOException {
        return Integer.parseInt(read("oom_adj"));
    }

    public int oom_score_adj() throws IOException {
        return Integer.parseInt(read("oom_score_adj"));
    }

    public Stat stat() throws IOException {
        return Stat.get(this.pid);
    }

    public Statm statm() throws IOException {
        return Statm.get(this.pid);
    }

    public Status status() throws IOException {
        return Status.get(this.pid);
    }

    public String wchan() throws IOException {
        return read("wchan");
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.pid);
    }
}
