package com.Ty.T.T.T.T.T;

import com.ts.main.common.MainSet;
import com.ts.main.common.ShellUtils;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* compiled from: Proguard */
final class T implements Closeable {

    /* renamed from: T  reason: collision with root package name */
    static final Pattern f295T = Pattern.compile("[a-z0-9_-]{1,64}");
    /* access modifiers changed from: private */
    public static final OutputStream TK = new OutputStream() {
        public void write(int b) throws IOException {
        }
    };
    private int T5;
    private int T6 = 0;
    private final File T9;
    private final Callable<Void> TB = new Callable<Void>() {
        /* renamed from: T */
        public Void call() throws Exception {
            synchronized (T.this) {
                if (T.this.Te != null) {
                    T.this.Th();
                    T.this.T6();
                    if (T.this.T5()) {
                        T.this.TE();
                        int unused = T.this.TF = 0;
                    }
                }
            }
            return null;
        }
    };
    private long TE;
    /* access modifiers changed from: private */
    public int TF;
    private final int TZ;
    /* access modifiers changed from: private */
    public Writer Te;
    private long Th = 0;
    private long Tj = 0;
    private final File Tk;
    private final File Tn;
    private final LinkedHashMap<String, Tr> Tq = new LinkedHashMap<>(0, 0.75f, true);
    final ThreadPoolExecutor Tr = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    /* access modifiers changed from: private */
    public final int Tv;
    /* access modifiers changed from: private */
    public final File Ty;

    private T(File directory, int appVersion, int valueCount, long maxSize, int maxFileCount) {
        this.Ty = directory;
        this.TZ = appVersion;
        this.Tn = new File(directory, "journal");
        this.T9 = new File(directory, "journal.tmp");
        this.Tk = new File(directory, "journal.bkp");
        this.Tv = valueCount;
        this.TE = maxSize;
        this.T5 = maxFileCount;
    }

    public static T T(File directory, int appVersion, int valueCount, long maxSize, int maxFileCount) throws IOException {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (maxFileCount <= 0) {
            throw new IllegalArgumentException("maxFileCount <= 0");
        } else if (valueCount <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        } else {
            File backupFile = new File(directory, "journal.bkp");
            if (backupFile.exists()) {
                File journalFile = new File(directory, "journal");
                if (journalFile.exists()) {
                    backupFile.delete();
                } else {
                    T(backupFile, journalFile, false);
                }
            }
            T cache = new T(directory, appVersion, valueCount, maxSize, maxFileCount);
            if (cache.Tn.exists()) {
                try {
                    cache.Tk();
                    cache.TZ();
                    cache.Te = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cache.Tn, true), Tn.f301T));
                    return cache;
                } catch (IOException journalIsCorrupt) {
                    System.out.println("DiskLruCache " + directory + " is corrupt: " + journalIsCorrupt.getMessage() + ", removing");
                    cache.Tn();
                }
            }
            directory.mkdirs();
            T cache2 = new T(directory, appVersion, valueCount, maxSize, maxFileCount);
            cache2.TE();
            return cache2;
        }
    }

    private void Tk() throws IOException {
        int lineCount;
        Ty reader = new Ty(new FileInputStream(this.Tn), Tn.f301T);
        try {
            String magic = reader.T();
            String version = reader.T();
            String appVersionString = reader.T();
            String valueCountString = reader.T();
            String blank = reader.T();
            if (!"libcore.io.DiskLruCache".equals(magic) || !MainSet.SP_XPH5.equals(version) || !Integer.toString(this.TZ).equals(appVersionString) || !Integer.toString(this.Tv).equals(valueCountString) || !"".equals(blank)) {
                throw new IOException("unexpected journal header: [" + magic + ", " + version + ", " + valueCountString + ", " + blank + "]");
            }
            lineCount = 0;
            while (true) {
                Tn(reader.T());
                lineCount++;
            }
        } catch (EOFException e) {
            this.TF = lineCount - this.Tq.size();
            Tn.T((Closeable) reader);
        } catch (Throwable th) {
            Tn.T((Closeable) reader);
            throw th;
        }
    }

    private void Tn(String line) throws IOException {
        String key;
        int firstSpace = line.indexOf(32);
        if (firstSpace == -1) {
            throw new IOException("unexpected journal line: " + line);
        }
        int keyBegin = firstSpace + 1;
        int secondSpace = line.indexOf(32, keyBegin);
        if (secondSpace == -1) {
            key = line.substring(keyBegin);
            if (firstSpace == "REMOVE".length() && line.startsWith("REMOVE")) {
                this.Tq.remove(key);
                return;
            }
        } else {
            key = line.substring(keyBegin, secondSpace);
        }
        Tr entry = this.Tq.get(key);
        if (entry == null) {
            entry = new Tr(key);
            this.Tq.put(key, entry);
        }
        if (secondSpace != -1 && firstSpace == "CLEAN".length() && line.startsWith("CLEAN")) {
            String[] parts = line.substring(secondSpace + 1).split(" ");
            boolean unused = entry.Tn = true;
            C0009T unused2 = entry.T9 = null;
            entry.T(parts);
        } else if (secondSpace == -1 && firstSpace == "DIRTY".length() && line.startsWith("DIRTY")) {
            C0009T unused3 = entry.T9 = new C0009T(entry);
        } else if (secondSpace != -1 || firstSpace != "READ".length() || !line.startsWith("READ")) {
            throw new IOException("unexpected journal line: " + line);
        }
    }

    private void TZ() throws IOException {
        T(this.T9);
        Iterator<Tr> i = this.Tq.values().iterator();
        while (i.hasNext()) {
            Tr entry = i.next();
            if (entry.T9 == null) {
                for (int t = 0; t < this.Tv; t++) {
                    this.Th += entry.Ty[t];
                    this.T6++;
                }
            } else {
                C0009T unused = entry.T9 = null;
                for (int t2 = 0; t2 < this.Tv; t2++) {
                    T(entry.T(t2));
                    T(entry.Tr(t2));
                }
                i.remove();
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void TE() throws IOException {
        if (this.Te != null) {
            this.Te.close();
        }
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.T9), Tn.f301T));
        try {
            writer.write("libcore.io.DiskLruCache");
            writer.write(ShellUtils.COMMAND_LINE_END);
            writer.write(MainSet.SP_XPH5);
            writer.write(ShellUtils.COMMAND_LINE_END);
            writer.write(Integer.toString(this.TZ));
            writer.write(ShellUtils.COMMAND_LINE_END);
            writer.write(Integer.toString(this.Tv));
            writer.write(ShellUtils.COMMAND_LINE_END);
            writer.write(ShellUtils.COMMAND_LINE_END);
            for (Tr entry : this.Tq.values()) {
                if (entry.T9 != null) {
                    writer.write("DIRTY " + entry.Tr + 10);
                } else {
                    writer.write("CLEAN " + entry.Tr + entry.T() + 10);
                }
            }
            writer.close();
            if (this.Tn.exists()) {
                T(this.Tn, this.Tk, true);
            }
            T(this.T9, this.Tn, false);
            this.Tk.delete();
            this.Te = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.Tn, true), Tn.f301T));
        } catch (Throwable th) {
            writer.close();
            throw th;
        }
    }

    private static void T(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void T(File from, File to, boolean deleteDestination) throws IOException {
        if (deleteDestination) {
            T(to);
        }
        if (!from.renameTo(to)) {
            throw new IOException();
        }
    }

    public synchronized Ty T(String key) throws IOException {
        Ty ty = null;
        synchronized (this) {
            Tv();
            T9(key);
            Tr entry = this.Tq.get(key);
            if (entry != null) {
                if (entry.Tn) {
                    File[] files = new File[this.Tv];
                    InputStream[] ins = new InputStream[this.Tv];
                    int i = 0;
                    while (i < this.Tv) {
                        try {
                            File file = entry.T(i);
                            files[i] = file;
                            ins[i] = new FileInputStream(file);
                            i++;
                        } catch (FileNotFoundException e) {
                            int i2 = 0;
                            while (i2 < this.Tv && ins[i2] != null) {
                                Tn.T((Closeable) ins[i2]);
                                i2++;
                            }
                        }
                    }
                    this.TF++;
                    this.Te.append("READ " + key + 10);
                    if (T5()) {
                        this.Tr.submit(this.TB);
                    }
                    ty = new Ty(key, entry.Tk, files, ins, entry.Ty);
                }
            }
        }
        return ty;
    }

    public C0009T Tr(String key) throws IOException {
        return T(key, -1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0063, code lost:
        if (com.Ty.T.T.T.T.T.T.Tr.T(r1) != null) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized com.Ty.T.T.T.T.T.T.C0009T T(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            monitor-enter(r5)
            r5.Tv()     // Catch:{ all -> 0x005c }
            r5.T9((java.lang.String) r6)     // Catch:{ all -> 0x005c }
            java.util.LinkedHashMap<java.lang.String, com.Ty.T.T.T.T.T.T$Tr> r2 = r5.Tq     // Catch:{ all -> 0x005c }
            java.lang.Object r1 = r2.get(r6)     // Catch:{ all -> 0x005c }
            com.Ty.T.T.T.T.T.T$Tr r1 = (com.Ty.T.T.T.T.T.T.Tr) r1     // Catch:{ all -> 0x005c }
            r2 = -1
            int r2 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x0022
            if (r1 == 0) goto L_0x0020
            long r2 = r1.Tk     // Catch:{ all -> 0x005c }
            int r2 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r2 == 0) goto L_0x0022
        L_0x0020:
            monitor-exit(r5)
            return r0
        L_0x0022:
            if (r1 != 0) goto L_0x005f
            com.Ty.T.T.T.T.T.T$Tr r1 = new com.Ty.T.T.T.T.T.T$Tr     // Catch:{ all -> 0x005c }
            r2 = 0
            r1.<init>(r6)     // Catch:{ all -> 0x005c }
            java.util.LinkedHashMap<java.lang.String, com.Ty.T.T.T.T.T.T$Tr> r2 = r5.Tq     // Catch:{ all -> 0x005c }
            r2.put(r6, r1)     // Catch:{ all -> 0x005c }
        L_0x002f:
            com.Ty.T.T.T.T.T.T$T r0 = new com.Ty.T.T.T.T.T.T$T     // Catch:{ all -> 0x005c }
            r2 = 0
            r0.<init>(r1)     // Catch:{ all -> 0x005c }
            com.Ty.T.T.T.T.T.T.C0009T unused = r1.T9 = r0     // Catch:{ all -> 0x005c }
            java.io.Writer r2 = r5.Te     // Catch:{ all -> 0x005c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x005c }
            r3.<init>()     // Catch:{ all -> 0x005c }
            java.lang.String r4 = "DIRTY "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x005c }
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ all -> 0x005c }
            r4 = 10
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x005c }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x005c }
            r2.write(r3)     // Catch:{ all -> 0x005c }
            java.io.Writer r2 = r5.Te     // Catch:{ all -> 0x005c }
            r2.flush()     // Catch:{ all -> 0x005c }
            goto L_0x0020
        L_0x005c:
            r2 = move-exception
            monitor-exit(r5)
            throw r2
        L_0x005f:
            com.Ty.T.T.T.T.T.T$T r2 = r1.T9     // Catch:{ all -> 0x005c }
            if (r2 == 0) goto L_0x002f
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Ty.T.T.T.T.T.T.T(java.lang.String, long):com.Ty.T.T.T.T.T.T$T");
    }

    public File T() {
        return this.Ty;
    }

    public synchronized long Tr() {
        return this.TE;
    }

    public synchronized int Ty() {
        return this.T5;
    }

    /* access modifiers changed from: private */
    public synchronized void T(C0009T editor, boolean success) throws IOException {
        Tr entry = editor.Tr;
        if (entry.T9 != editor) {
            throw new IllegalStateException();
        }
        if (success) {
            if (!entry.Tn) {
                int i = 0;
                while (true) {
                    if (i >= this.Tv) {
                        break;
                    } else if (!editor.Ty[i]) {
                        editor.Tr();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                    } else if (!entry.Tr(i).exists()) {
                        editor.Tr();
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        for (int i2 = 0; i2 < this.Tv; i2++) {
            File dirty = entry.Tr(i2);
            if (!success) {
                T(dirty);
            } else if (dirty.exists()) {
                File clean = entry.T(i2);
                dirty.renameTo(clean);
                long oldLength = entry.Ty[i2];
                long newLength = clean.length();
                entry.Ty[i2] = newLength;
                this.Th = (this.Th - oldLength) + newLength;
                this.T6++;
            }
        }
        this.TF++;
        C0009T unused = entry.T9 = null;
        if (entry.Tn || success) {
            boolean unused2 = entry.Tn = true;
            this.Te.write("CLEAN " + entry.Tr + entry.T() + 10);
            if (success) {
                long j = this.Tj;
                this.Tj = 1 + j;
                long unused3 = entry.Tk = j;
            }
        } else {
            this.Tq.remove(entry.Tr);
            this.Te.write("REMOVE " + entry.Tr + 10);
        }
        this.Te.flush();
        if (this.Th > this.TE || this.T6 > this.T5 || T5()) {
            this.Tr.submit(this.TB);
        }
    }

    /* access modifiers changed from: private */
    public boolean T5() {
        return this.TF >= 2000 && this.TF >= this.Tq.size();
    }

    public synchronized boolean Ty(String key) throws IOException {
        boolean z;
        Tv();
        T9(key);
        Tr entry = this.Tq.get(key);
        if (entry == null || entry.T9 != null) {
            z = false;
        } else {
            int i = 0;
            while (i < this.Tv) {
                File file = entry.T(i);
                if (!file.exists() || file.delete()) {
                    this.Th -= entry.Ty[i];
                    this.T6--;
                    entry.Ty[i] = 0;
                    i++;
                } else {
                    throw new IOException("failed to delete " + file);
                }
            }
            this.TF++;
            this.Te.append("REMOVE " + key + 10);
            this.Tq.remove(key);
            if (T5()) {
                this.Tr.submit(this.TB);
            }
            z = true;
        }
        return z;
    }

    private void Tv() {
        if (this.Te == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void close() throws IOException {
        if (this.Te != null) {
            Iterator i$ = new ArrayList(this.Tq.values()).iterator();
            while (i$.hasNext()) {
                Tr entry = (Tr) i$.next();
                if (entry.T9 != null) {
                    entry.T9.Tr();
                }
            }
            Th();
            T6();
            this.Te.close();
            this.Te = null;
        }
    }

    /* access modifiers changed from: private */
    public void Th() throws IOException {
        while (this.Th > this.TE) {
            Ty(this.Tq.entrySet().iterator().next().getKey());
        }
    }

    /* access modifiers changed from: private */
    public void T6() throws IOException {
        while (this.T6 > this.T5) {
            Ty(this.Tq.entrySet().iterator().next().getKey());
        }
    }

    public void Tn() throws IOException {
        close();
        Tn.T(this.Ty);
    }

    private void T9(String key) {
        if (!f295T.matcher(key).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + key + "\"");
        }
    }

    /* compiled from: Proguard */
    public final class Ty implements Closeable {
        private final InputStream[] T9;
        private final long[] Tk;
        private File[] Tn;
        private final String Tr;
        private final long Ty;

        private Ty(String key, long sequenceNumber, File[] files, InputStream[] ins, long[] lengths) {
            this.Tr = key;
            this.Ty = sequenceNumber;
            this.Tn = files;
            this.T9 = ins;
            this.Tk = lengths;
        }

        public File T(int index) {
            return this.Tn[index];
        }

        public void close() {
            for (InputStream in : this.T9) {
                Tn.T((Closeable) in);
            }
        }
    }

    /* renamed from: com.Ty.T.T.T.T.T.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public final class C0009T {
        private boolean T9;
        /* access modifiers changed from: private */
        public boolean Tn;
        /* access modifiers changed from: private */
        public final Tr Tr;
        /* access modifiers changed from: private */
        public final boolean[] Ty;

        private C0009T(Tr entry) {
            this.Tr = entry;
            this.Ty = entry.Tn ? null : new boolean[T.this.Tv];
        }

        public OutputStream T(int index) throws IOException {
            OutputStream T92;
            FileOutputStream outputStream;
            synchronized (T.this) {
                if (this.Tr.T9 != this) {
                    throw new IllegalStateException();
                }
                if (!this.Tr.Tn) {
                    this.Ty[index] = true;
                }
                File dirtyFile = this.Tr.Tr(index);
                try {
                    outputStream = new FileOutputStream(dirtyFile);
                } catch (FileNotFoundException e) {
                    T.this.Ty.mkdirs();
                    try {
                        outputStream = new FileOutputStream(dirtyFile);
                    } catch (FileNotFoundException e2) {
                        T92 = T.TK;
                    }
                }
                T92 = new C0010T(outputStream);
            }
            return T92;
        }

        public void T() throws IOException {
            if (this.Tn) {
                T.this.T(this, false);
                T.this.Ty(this.Tr.Tr);
            } else {
                T.this.T(this, true);
            }
            this.T9 = true;
        }

        public void Tr() throws IOException {
            T.this.T(this, false);
        }

        /* renamed from: com.Ty.T.T.T.T.T.T$T$T  reason: collision with other inner class name */
        /* compiled from: Proguard */
        private class C0010T extends FilterOutputStream {
            private C0010T(OutputStream out) {
                super(out);
            }

            public void write(int oneByte) {
                try {
                    this.out.write(oneByte);
                } catch (IOException e) {
                    boolean unused = C0009T.this.Tn = true;
                }
            }

            public void write(byte[] buffer, int offset, int length) {
                try {
                    this.out.write(buffer, offset, length);
                } catch (IOException e) {
                    boolean unused = C0009T.this.Tn = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    boolean unused = C0009T.this.Tn = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    boolean unused = C0009T.this.Tn = true;
                }
            }
        }
    }

    /* compiled from: Proguard */
    private final class Tr {
        /* access modifiers changed from: private */
        public C0009T T9;
        /* access modifiers changed from: private */
        public long Tk;
        /* access modifiers changed from: private */
        public boolean Tn;
        /* access modifiers changed from: private */
        public final String Tr;
        /* access modifiers changed from: private */
        public final long[] Ty;

        private Tr(String key) {
            this.Tr = key;
            this.Ty = new long[T.this.Tv];
        }

        public String T() throws IOException {
            StringBuilder result = new StringBuilder();
            for (long size : this.Ty) {
                result.append(' ').append(size);
            }
            return result.toString();
        }

        /* access modifiers changed from: private */
        public void T(String[] strings) throws IOException {
            if (strings.length != T.this.Tv) {
                throw Tr(strings);
            }
            int i = 0;
            while (i < strings.length) {
                try {
                    this.Ty[i] = Long.parseLong(strings[i]);
                    i++;
                } catch (NumberFormatException e) {
                    throw Tr(strings);
                }
            }
        }

        private IOException Tr(String[] strings) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strings));
        }

        public File T(int i) {
            return new File(T.this.Ty, this.Tr + "" + i);
        }

        public File Tr(int i) {
            return new File(T.this.Ty, this.Tr + "" + i + ".tmp");
        }
    }
}
