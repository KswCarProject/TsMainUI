package com.Ty.T.T.T.T.T;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* compiled from: Proguard */
class Ty implements Closeable {

    /* renamed from: T  reason: collision with root package name */
    private final InputStream f303T;
    private int T9;
    private int Tn;
    /* access modifiers changed from: private */
    public final Charset Tr;
    private byte[] Ty;

    public Ty(InputStream in, Charset charset) {
        this(in, 8192, charset);
    }

    public Ty(InputStream in, int capacity, Charset charset) {
        if (in == null || charset == null) {
            throw new NullPointerException();
        } else if (capacity < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        } else if (!charset.equals(Tn.f301T)) {
            throw new IllegalArgumentException("Unsupported encoding");
        } else {
            this.f303T = in;
            this.Tr = charset;
            this.Ty = new byte[capacity];
        }
    }

    public void close() throws IOException {
        synchronized (this.f303T) {
            if (this.Ty != null) {
                this.Ty = null;
                this.f303T.close();
            }
        }
    }

    public String T() throws IOException {
        int i;
        String res;
        synchronized (this.f303T) {
            if (this.Ty == null) {
                throw new IOException("LineReader is closed");
            }
            if (this.Tn >= this.T9) {
                Tr();
            }
            int i2 = this.Tn;
            while (true) {
                if (i2 == this.T9) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream((this.T9 - this.Tn) + 80) {
                        public String toString() {
                            try {
                                return new String(this.buf, 0, (this.count <= 0 || this.buf[this.count + -1] != 13) ? this.count : this.count - 1, Ty.this.Tr.name());
                            } catch (UnsupportedEncodingException e) {
                                throw new AssertionError(e);
                            }
                        }
                    };
                    loop1:
                    while (true) {
                        out.write(this.Ty, this.Tn, this.T9 - this.Tn);
                        this.T9 = -1;
                        Tr();
                        i = this.Tn;
                        while (true) {
                            if (i != this.T9) {
                                if (this.Ty[i] == 10) {
                                    break loop1;
                                }
                                i++;
                            }
                        }
                    }
                    if (i != this.Tn) {
                        out.write(this.Ty, this.Tn, i - this.Tn);
                    }
                    this.Tn = i + 1;
                    res = out.toString();
                } else if (this.Ty[i2] == 10) {
                    res = new String(this.Ty, this.Tn, ((i2 == this.Tn || this.Ty[i2 + -1] != 13) ? i2 : i2 - 1) - this.Tn, this.Tr.name());
                    this.Tn = i2 + 1;
                } else {
                    i2++;
                }
            }
        }
        return res;
    }

    private void Tr() throws IOException {
        int result = this.f303T.read(this.Ty, 0, this.Ty.length);
        if (result == -1) {
            throw new EOFException();
        }
        this.Tn = 0;
        this.T9 = result;
    }
}
