package com.hongfans.carmedia.processes.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.File;
import java.io.IOException;

public class ProcFile extends File implements Parcelable {
    public static final Parcelable.Creator<ProcFile> CREATOR = new Parcelable.Creator<ProcFile>() {
        public ProcFile createFromParcel(Parcel in) {
            return new ProcFile(in);
        }

        public ProcFile[] newArray(int size) {
            return new ProcFile[size];
        }
    };
    public final String content;

    protected ProcFile(String path) throws IOException {
        super(path);
        this.content = readFile(path);
    }

    protected ProcFile(Parcel in) {
        super(in.readString());
        this.content = in.readString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.lang.String readFile(java.lang.String r6) throws java.io.IOException {
        /*
            r3 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0030 }
            r2.<init>()     // Catch:{ all -> 0x0030 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ all -> 0x0030 }
            java.io.FileReader r5 = new java.io.FileReader     // Catch:{ all -> 0x0030 }
            r5.<init>(r6)     // Catch:{ all -> 0x0030 }
            r4.<init>(r5)     // Catch:{ all -> 0x0030 }
            java.lang.String r0 = r4.readLine()     // Catch:{ all -> 0x0037 }
            java.lang.String r1 = ""
        L_0x0016:
            if (r0 == 0) goto L_0x0026
            java.lang.StringBuilder r5 = r2.append(r1)     // Catch:{ all -> 0x0037 }
            r5.append(r0)     // Catch:{ all -> 0x0037 }
            java.lang.String r1 = "\n"
            java.lang.String r0 = r4.readLine()     // Catch:{ all -> 0x0037 }
            goto L_0x0016
        L_0x0026:
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0037 }
            if (r4 == 0) goto L_0x002f
            r4.close()
        L_0x002f:
            return r5
        L_0x0030:
            r5 = move-exception
        L_0x0031:
            if (r3 == 0) goto L_0x0036
            r3.close()
        L_0x0036:
            throw r5
        L_0x0037:
            r5 = move-exception
            r3 = r4
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hongfans.carmedia.processes.models.ProcFile.readFile(java.lang.String):java.lang.String");
    }

    public long length() {
        return (long) this.content.length();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getAbsolutePath());
        dest.writeString(this.content);
    }
}
