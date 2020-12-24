package net.easyconn.platform.wrc.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import java.util.UUID;

/* compiled from: WrcDbHelper */
public class i {
    private static i a;

    private i() {
    }

    static i a() {
        if (a == null) {
            synchronized (i.class) {
                if (a == null) {
                    a = new i();
                }
            }
        }
        return a;
    }

    private SQLiteDatabase b(Context context) {
        return k.a(context).getWritableDatabase();
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(Context context, List<UUID> list) {
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            try {
                SQLiteDatabase b = b(context);
                if (b.isOpen()) {
                    b.delete("uuids", (String) null, (String[]) null);
                    for (UUID uuid : list) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("uuid", uuid.toString());
                        b.insert("uuids", (String) null, contentValues);
                    }
                }
                if (b != null) {
                    if (b.isOpen()) {
                        b.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (sQLiteDatabase != null) {
                    if (sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                }
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    if (sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                }
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0049, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004a, code lost:
        r2 = r0;
        r0 = r1;
        r1 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00a2, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00a3, code lost:
        r2 = r0;
        r0 = r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x008e A[SYNTHETIC, Splitter:B:54:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00a2 A[ExcHandler: all (r1v9 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:9:0x0021] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<java.util.UUID> a(android.content.Context r11) {
        /*
            r10 = this;
            r9 = 0
            monitor-enter(r10)
            r1 = 0
            android.database.sqlite.SQLiteDatabase r0 = r10.b(r11)     // Catch:{ Exception -> 0x00a9, all -> 0x0089 }
            boolean r2 = r0.isOpen()     // Catch:{ Exception -> 0x00ad, all -> 0x009d }
            if (r2 == 0) goto L_0x0075
            java.lang.String r1 = "uuids"
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ Exception -> 0x00ad, all -> 0x009d }
            r3 = 0
            java.lang.String r4 = "*"
            r2[r3] = r4     // Catch:{ Exception -> 0x00ad, all -> 0x009d }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r3 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x00ad, all -> 0x009d }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Exception -> 0x0049, all -> 0x00a2 }
            r1.<init>()     // Catch:{ Exception -> 0x0049, all -> 0x00a2 }
        L_0x0026:
            boolean r2 = r3.moveToNext()     // Catch:{ Exception -> 0x0049, all -> 0x00a2 }
            if (r2 == 0) goto L_0x0063
            java.lang.String r2 = "uuid"
            int r2 = r3.getColumnIndex(r2)     // Catch:{ Exception -> 0x0049, all -> 0x00a2 }
            java.lang.String r2 = r3.getString(r2)     // Catch:{ Exception -> 0x0049, all -> 0x00a2 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x0049, all -> 0x00a2 }
            if (r4 != 0) goto L_0x0026
            java.util.UUID r2 = java.util.UUID.fromString(r2)     // Catch:{ Exception -> 0x0044, all -> 0x00a2 }
            r1.add(r2)     // Catch:{ Exception -> 0x0044, all -> 0x00a2 }
            goto L_0x0026
        L_0x0044:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ Exception -> 0x0049, all -> 0x00a2 }
            goto L_0x0026
        L_0x0049:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r3
        L_0x004d:
            r0.printStackTrace()     // Catch:{ all -> 0x00a6 }
            if (r1 == 0) goto L_0x0055
            r1.close()     // Catch:{ all -> 0x0086 }
        L_0x0055:
            if (r2 == 0) goto L_0x0060
            boolean r0 = r2.isOpen()     // Catch:{ all -> 0x0086 }
            if (r0 == 0) goto L_0x0060
            r2.close()     // Catch:{ all -> 0x0086 }
        L_0x0060:
            r0 = r9
        L_0x0061:
            monitor-exit(r10)
            return r0
        L_0x0063:
            if (r3 == 0) goto L_0x0068
            r3.close()     // Catch:{ all -> 0x0086 }
        L_0x0068:
            if (r0 == 0) goto L_0x0073
            boolean r2 = r0.isOpen()     // Catch:{ all -> 0x0086 }
            if (r2 == 0) goto L_0x0073
            r0.close()     // Catch:{ all -> 0x0086 }
        L_0x0073:
            r0 = r1
            goto L_0x0061
        L_0x0075:
            if (r9 == 0) goto L_0x007a
            r1.close()     // Catch:{ all -> 0x0086 }
        L_0x007a:
            if (r0 == 0) goto L_0x0060
            boolean r1 = r0.isOpen()     // Catch:{ all -> 0x0086 }
            if (r1 == 0) goto L_0x0060
            r0.close()     // Catch:{ all -> 0x0086 }
            goto L_0x0060
        L_0x0086:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        L_0x0089:
            r0 = move-exception
            r3 = r9
            r2 = r9
        L_0x008c:
            if (r3 == 0) goto L_0x0091
            r3.close()     // Catch:{ all -> 0x0086 }
        L_0x0091:
            if (r2 == 0) goto L_0x009c
            boolean r1 = r2.isOpen()     // Catch:{ all -> 0x0086 }
            if (r1 == 0) goto L_0x009c
            r2.close()     // Catch:{ all -> 0x0086 }
        L_0x009c:
            throw r0     // Catch:{ all -> 0x0086 }
        L_0x009d:
            r1 = move-exception
            r3 = r9
            r2 = r0
            r0 = r1
            goto L_0x008c
        L_0x00a2:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L_0x008c
        L_0x00a6:
            r0 = move-exception
            r3 = r1
            goto L_0x008c
        L_0x00a9:
            r0 = move-exception
            r1 = r9
            r2 = r9
            goto L_0x004d
        L_0x00ad:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r9
            goto L_0x004d
        */
        throw new UnsupportedOperationException("Method not decompiled: net.easyconn.platform.wrc.core.i.a(android.content.Context):java.util.List");
    }
}
