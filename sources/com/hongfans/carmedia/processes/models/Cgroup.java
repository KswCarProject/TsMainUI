package com.hongfans.carmedia.processes.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.ts.bt.ContactInfo;
import com.ts.main.common.ShellUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public final class Cgroup extends ProcFile {
    public static final Parcelable.Creator<Cgroup> CREATOR = new Parcelable.Creator<Cgroup>() {
        public Cgroup createFromParcel(Parcel source) {
            return new Cgroup(source);
        }

        public Cgroup[] newArray(int size) {
            return new Cgroup[size];
        }
    };
    public final ArrayList<ControlGroup> groups;

    private Cgroup(String path) throws IOException {
        super(path);
        String[] lines = this.content.split(ShellUtils.COMMAND_LINE_END);
        this.groups = new ArrayList<>();
        for (String line : lines) {
            try {
                this.groups.add(new ControlGroup(line));
            } catch (Exception e) {
            }
        }
    }

    private Cgroup(Parcel in) {
        super(in);
        this.groups = in.createTypedArrayList(ControlGroup.CREATOR);
    }

    public static Cgroup get(int pid) throws IOException {
        return new Cgroup(String.format("/proc/%d/cgroup", new Object[]{Integer.valueOf(pid)}));
    }

    public ControlGroup getGroup(String subsystem) {
        Iterator<ControlGroup> it = this.groups.iterator();
        while (it.hasNext()) {
            ControlGroup group = it.next();
            String[] systems = group.subsystems.split(ContactInfo.COMBINATION_SEPERATOR);
            int length = systems.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    if (systems[i].equals(subsystem)) {
                        return group;
                    }
                    i++;
                }
            }
        }
        return null;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.groups);
    }
}
