package com.hongfans.carmedia.processes.models;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.hongfans.carmedia.processes.AndroidProcesses;
import java.io.File;
import java.io.IOException;

public class AndroidAppProcess extends AndroidProcess {
    public static final Parcelable.Creator<AndroidAppProcess> CREATOR = new Parcelable.Creator<AndroidAppProcess>() {
        public AndroidAppProcess createFromParcel(Parcel source) {
            return new AndroidAppProcess(source);
        }

        public AndroidAppProcess[] newArray(int size) {
            return new AndroidAppProcess[size];
        }
    };
    private static final boolean SYS_SUPPORTS_SCHEDGROUPS = new File("/dev/cpuctl/tasks").exists();
    public final boolean foreground;
    public final int uid;

    public AndroidAppProcess(int pid) throws IOException, NotAndroidAppProcessException {
        super(pid);
        boolean foreground2;
        int uid2;
        int uid3;
        if (SYS_SUPPORTS_SCHEDGROUPS) {
            Cgroup cgroup = cgroup();
            ControlGroup cpuacct = cgroup.getGroup("cpuacct");
            ControlGroup cpu = cgroup.getGroup("cpu");
            if (Build.VERSION.SDK_INT >= 21) {
                if (cpu == null || cpuacct == null || !cpuacct.group.contains("pid_")) {
                    throw new NotAndroidAppProcessException(pid);
                }
                foreground2 = !cpu.group.contains("bg_non_interactive");
                try {
                    uid2 = Integer.parseInt(cpuacct.group.split("/")[1].replace("uid_", ""));
                } catch (Exception e) {
                    uid2 = status().getUid();
                }
                AndroidProcesses.log("name=%s, pid=%d, uid=%d, foreground=%b, cpuacct=%s, cpu=%s", this.name, Integer.valueOf(pid), Integer.valueOf(uid2), Boolean.valueOf(foreground2), cpuacct.toString(), cpu.toString());
            } else if (cpu == null || cpuacct == null || !cpu.group.contains("apps")) {
                throw new NotAndroidAppProcessException(pid);
            } else {
                foreground2 = !cpu.group.contains("bg_non_interactive");
                try {
                    uid3 = Integer.parseInt(cpuacct.group.substring(cpuacct.group.lastIndexOf("/") + 1));
                } catch (Exception e2) {
                    uid3 = status().getUid();
                }
                AndroidProcesses.log("name=%s, pid=%d, uid=%d foreground=%b, cpuacct=%s, cpu=%s", this.name, Integer.valueOf(pid), Integer.valueOf(uid2), Boolean.valueOf(foreground2), cpuacct.toString(), cpu.toString());
            }
        } else if (this.name.startsWith("/") || !new File("/data/data", getPackageName()).exists()) {
            throw new NotAndroidAppProcessException(pid);
        } else {
            Stat stat = stat();
            Status status = status();
            foreground2 = stat.policy() == 0;
            uid2 = status.getUid();
            AndroidProcesses.log("name=%s, pid=%d, uid=%d foreground=%b", this.name, Integer.valueOf(pid), Integer.valueOf(uid2), Boolean.valueOf(foreground2));
        }
        this.foreground = foreground2;
        this.uid = uid2;
    }

    protected AndroidAppProcess(Parcel in) {
        super(in);
        this.foreground = in.readByte() != 0;
        this.uid = in.readInt();
    }

    public String getPackageName() {
        return this.name.split(":")[0];
    }

    public PackageInfo getPackageInfo(Context context, int flags) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(getPackageName(), flags);
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeByte((byte) (this.foreground ? 1 : 0));
        dest.writeInt(this.uid);
    }

    public static final class NotAndroidAppProcessException extends Exception {
        public NotAndroidAppProcessException(int pid) {
            super(String.format("The process %d does not belong to any application", new Object[]{Integer.valueOf(pid)}));
        }
    }
}
