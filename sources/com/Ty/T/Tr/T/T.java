package com.Ty.T.Tr.T;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: Proguard */
public class T extends InputStream {

    /* renamed from: T  reason: collision with root package name */
    private final InputStream f308T;
    private final int Tr;

    public T(InputStream stream, int length) {
        this.f308T = stream;
        this.Tr = length;
    }

    public int available() {
        return this.Tr;
    }

    public void close() throws IOException {
        this.f308T.close();
    }

    public void mark(int readLimit) {
        this.f308T.mark(readLimit);
    }

    public int read() throws IOException {
        return this.f308T.read();
    }

    public int read(byte[] buffer) throws IOException {
        return this.f308T.read(buffer);
    }

    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        return this.f308T.read(buffer, byteOffset, byteCount);
    }

    public void reset() throws IOException {
        this.f308T.reset();
    }

    public long skip(long byteCount) throws IOException {
        return this.f308T.skip(byteCount);
    }

    public boolean markSupported() {
        return this.f308T.markSupported();
    }
}
