package com.hongfans.carmedia.processes.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.IOException;

public final class Stat extends ProcFile {
    public static final Parcelable.Creator<Stat> CREATOR = new Parcelable.Creator<Stat>() {
        public Stat createFromParcel(Parcel source) {
            return new Stat(source);
        }

        public Stat[] newArray(int size) {
            return new Stat[size];
        }
    };
    private final String[] fields;

    private Stat(String path) throws IOException {
        super(path);
        this.fields = this.content.split("\\s+");
    }

    private Stat(Parcel in) {
        super(in);
        this.fields = in.createStringArray();
    }

    public static Stat get(int pid) throws IOException {
        return new Stat(String.format("/proc/%d/stat", new Object[]{Integer.valueOf(pid)}));
    }

    public int getPid() {
        return Integer.parseInt(this.fields[0]);
    }

    public String getComm() {
        return this.fields[1].replace("(", "").replace(")", "");
    }

    public char state() {
        return this.fields[2].charAt(0);
    }

    public int ppid() {
        return Integer.parseInt(this.fields[3]);
    }

    public int pgrp() {
        return Integer.parseInt(this.fields[4]);
    }

    public int session() {
        return Integer.parseInt(this.fields[5]);
    }

    public int tty_nr() {
        return Integer.parseInt(this.fields[6]);
    }

    public int tpgid() {
        return Integer.parseInt(this.fields[7]);
    }

    public int flags() {
        return Integer.parseInt(this.fields[8]);
    }

    public long minflt() {
        return Long.parseLong(this.fields[9]);
    }

    public long cminflt() {
        return Long.parseLong(this.fields[10]);
    }

    public long majflt() {
        return Long.parseLong(this.fields[11]);
    }

    public long cmajflt() {
        return Long.parseLong(this.fields[12]);
    }

    public long utime() {
        return Long.parseLong(this.fields[13]);
    }

    public long stime() {
        return Long.parseLong(this.fields[14]);
    }

    public long cutime() {
        return Long.parseLong(this.fields[15]);
    }

    public long cstime() {
        return Long.parseLong(this.fields[16]);
    }

    public long priority() {
        return Long.parseLong(this.fields[17]);
    }

    public int nice() {
        return Integer.parseInt(this.fields[18]);
    }

    public long num_threads() {
        return Long.parseLong(this.fields[19]);
    }

    public long itrealvalue() {
        return Long.parseLong(this.fields[20]);
    }

    public long starttime() {
        return Long.parseLong(this.fields[21]);
    }

    public long vsize() {
        return Long.parseLong(this.fields[22]);
    }

    public long rss() {
        return Long.parseLong(this.fields[23]);
    }

    public long rsslim() {
        return Long.parseLong(this.fields[24]);
    }

    public long startcode() {
        return Long.parseLong(this.fields[25]);
    }

    public long endcode() {
        return Long.parseLong(this.fields[26]);
    }

    public long startstack() {
        return Long.parseLong(this.fields[27]);
    }

    public long kstkesp() {
        return Long.parseLong(this.fields[28]);
    }

    public long kstkeip() {
        return Long.parseLong(this.fields[29]);
    }

    public long signal() {
        return Long.parseLong(this.fields[30]);
    }

    public long blocked() {
        return Long.parseLong(this.fields[31]);
    }

    public long sigignore() {
        return Long.parseLong(this.fields[32]);
    }

    public long sigcatch() {
        return Long.parseLong(this.fields[33]);
    }

    public long wchan() {
        return Long.parseLong(this.fields[34]);
    }

    public long nswap() {
        return Long.parseLong(this.fields[35]);
    }

    public long cnswap() {
        return Long.parseLong(this.fields[36]);
    }

    public int exit_signal() {
        return Integer.parseInt(this.fields[37]);
    }

    public int processor() {
        return Integer.parseInt(this.fields[38]);
    }

    public int rt_priority() {
        return Integer.parseInt(this.fields[39]);
    }

    public int policy() {
        return Integer.parseInt(this.fields[40]);
    }

    public long delayacct_blkio_ticks() {
        return Long.parseLong(this.fields[41]);
    }

    public long guest_time() {
        return Long.parseLong(this.fields[42]);
    }

    public long cguest_time() {
        return Long.parseLong(this.fields[43]);
    }

    public long start_data() {
        return Long.parseLong(this.fields[44]);
    }

    public long end_data() {
        return Long.parseLong(this.fields[45]);
    }

    public long start_brk() {
        return Long.parseLong(this.fields[46]);
    }

    public long arg_start() {
        return Long.parseLong(this.fields[47]);
    }

    public long arg_end() {
        return Long.parseLong(this.fields[48]);
    }

    public long env_start() {
        return Long.parseLong(this.fields[49]);
    }

    public long env_end() {
        return Long.parseLong(this.fields[50]);
    }

    public int exit_code() {
        return Integer.parseInt(this.fields[51]);
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeStringArray(this.fields);
    }
}
