package com.T.T.Ty;

import com.T.T.T;
import com.T.T.Tn.Tn;
import com.T.T.Tr.Tr;
import java.io.IOException;
import java.io.Writer;
import java.lang.ref.SoftReference;
import net.easyconn.platform.wrc.core.WrcManager;

/* compiled from: Proguard */
public final class Trs extends Writer {
    private static final ThreadLocal<SoftReference<char[]>> Ty = new ThreadLocal<>();

    /* renamed from: T  reason: collision with root package name */
    protected char[] f225T;
    private final Writer T9;
    private int Tn;
    protected int Tr;

    public Trs() {
        this((Writer) null);
    }

    public Trs(Writer writer) {
        this.T9 = writer;
        this.Tn = T.Tn;
        SoftReference<char[]> ref = Ty.get();
        if (ref != null) {
            this.f225T = ref.get();
            Ty.set((Object) null);
        }
        if (this.f225T == null) {
            this.f225T = new char[1024];
        }
    }

    public void T(TrG feature, boolean state) {
        if (state) {
            this.Tn |= feature.T();
        } else {
            this.Tn &= feature.T() ^ -1;
        }
    }

    public boolean T(TrG feature) {
        return TrG.T(this.Tn, feature);
    }

    public void write(int c) {
        int newcount = this.Tr + 1;
        if (newcount > this.f225T.length) {
            if (this.T9 == null) {
                T(newcount);
            } else {
                flush();
                newcount = 1;
            }
        }
        this.f225T[this.Tr] = (char) c;
        this.Tr = newcount;
    }

    public void T(char c) {
        int newcount = this.Tr + 1;
        if (newcount > this.f225T.length) {
            if (this.T9 == null) {
                T(newcount);
            } else {
                flush();
                newcount = 1;
            }
        }
        this.f225T[this.Tr] = c;
        this.Tr = newcount;
    }

    public void write(char[] c, int off, int len) {
        if (off < 0 || off > c.length || len < 0 || off + len > c.length || off + len < 0) {
            throw new IndexOutOfBoundsException();
        } else if (len != 0) {
            int newcount = this.Tr + len;
            if (newcount > this.f225T.length) {
                if (this.T9 == null) {
                    T(newcount);
                } else {
                    do {
                        int rest = this.f225T.length - this.Tr;
                        System.arraycopy(c, off, this.f225T, this.Tr, rest);
                        this.Tr = this.f225T.length;
                        flush();
                        len -= rest;
                        off += rest;
                    } while (len > this.f225T.length);
                    newcount = len;
                }
            }
            System.arraycopy(c, off, this.f225T, this.Tr, len);
            this.Tr = newcount;
        }
    }

    public void T(int minimumCapacity) {
        int newCapacity = ((this.f225T.length * 3) / 2) + 1;
        if (newCapacity < minimumCapacity) {
            newCapacity = minimumCapacity;
        }
        char[] newValue = new char[newCapacity];
        System.arraycopy(this.f225T, 0, newValue, 0, this.Tr);
        this.f225T = newValue;
    }

    public void write(String str, int off, int len) {
        int newcount = this.Tr + len;
        if (newcount > this.f225T.length) {
            if (this.T9 == null) {
                T(newcount);
            } else {
                do {
                    int rest = this.f225T.length - this.Tr;
                    str.getChars(off, off + rest, this.f225T, this.Tr);
                    this.Tr = this.f225T.length;
                    flush();
                    len -= rest;
                    off += rest;
                } while (len > this.f225T.length);
                newcount = len;
            }
        }
        str.getChars(off, off + len, this.f225T, this.Tr);
        this.Tr = newcount;
    }

    /* renamed from: T */
    public Trs append(CharSequence csq) {
        String s = csq == null ? "null" : csq.toString();
        write(s, 0, s.length());
        return this;
    }

    /* renamed from: T */
    public Trs append(CharSequence csq, int start, int end) {
        if (csq == null) {
            csq = "null";
        }
        String s = csq.subSequence(start, end).toString();
        write(s, 0, s.length());
        return this;
    }

    /* renamed from: Tr */
    public Trs append(char c) {
        T(c);
        return this;
    }

    public String toString() {
        return new String(this.f225T, 0, this.Tr);
    }

    public void close() {
        if (this.T9 != null && this.Tr > 0) {
            flush();
        }
        if (this.f225T.length <= 8192) {
            Ty.set(new SoftReference(this.f225T));
        }
        this.f225T = null;
    }

    public void write(String text) {
        if (text == null) {
            T();
        } else {
            write(text, 0, text.length());
        }
    }

    public void Tr(int i) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }
        int size = i < 0 ? Tn.T(-i) + 1 : Tn.T(i);
        int newcount = this.Tr + size;
        if (newcount > this.f225T.length) {
            if (this.T9 == null) {
                T(newcount);
            } else {
                char[] chars = new char[size];
                Tn.T(i, size, chars);
                write(chars, 0, chars.length);
                return;
            }
        }
        Tn.T(i, newcount, this.f225T);
        this.Tr = newcount;
    }

    public void T(byte[] bytes) {
        int bytesLen = bytes.length;
        if (bytesLen == 0) {
            write("\"\"");
            return;
        }
        char[] CA = com.T.T.Tn.T.f129T;
        int eLen = (bytesLen / 3) * 3;
        int offset = this.Tr;
        int newcount = this.Tr + ((((bytesLen - 1) / 3) + 1) << 2) + 2;
        if (newcount > this.f225T.length) {
            if (this.T9 != null) {
                T('\"');
                int s = 0;
                while (s < eLen) {
                    int s2 = s + 1;
                    int s3 = s2 + 1;
                    int i = ((bytes[s] & 255) << WrcManager.WrcCallback.KEY_CENTER) | ((bytes[s2] & 255) << 8) | (bytes[s3] & 255);
                    T(CA[(i >>> 18) & 63]);
                    T(CA[(i >>> 12) & 63]);
                    T(CA[(i >>> 6) & 63]);
                    T(CA[i & 63]);
                    s = s3 + 1;
                }
                int left = bytesLen - eLen;
                if (left > 0) {
                    int i2 = ((bytes[eLen] & 255) << 10) | (left == 2 ? (bytes[bytesLen - 1] & 255) << 2 : 0);
                    T(CA[i2 >> 12]);
                    T(CA[(i2 >>> 6) & 63]);
                    T(left == 2 ? CA[i2 & 63] : '=');
                    T('=');
                }
                T('\"');
                return;
            }
            T(newcount);
        }
        this.Tr = newcount;
        this.f225T[offset] = '\"';
        int d = offset + 1;
        int s4 = 0;
        while (s4 < eLen) {
            int s5 = s4 + 1;
            int s6 = s5 + 1;
            int i3 = ((bytes[s4] & 255) << WrcManager.WrcCallback.KEY_CENTER) | ((bytes[s5] & 255) << 8) | (bytes[s6] & 255);
            int d2 = d + 1;
            this.f225T[d] = CA[(i3 >>> 18) & 63];
            int d3 = d2 + 1;
            this.f225T[d2] = CA[(i3 >>> 12) & 63];
            int d4 = d3 + 1;
            this.f225T[d3] = CA[(i3 >>> 6) & 63];
            d = d4 + 1;
            this.f225T[d4] = CA[i3 & 63];
            s4 = s6 + 1;
        }
        int left2 = bytesLen - eLen;
        if (left2 > 0) {
            int i4 = ((bytes[eLen] & 255) << 10) | (left2 == 2 ? (bytes[bytesLen - 1] & 255) << 2 : 0);
            this.f225T[newcount - 5] = CA[i4 >> 12];
            this.f225T[newcount - 4] = CA[(i4 >>> 6) & 63];
            this.f225T[newcount - 3] = left2 == 2 ? CA[i4 & 63] : '=';
            this.f225T[newcount - 2] = '=';
        }
        this.f225T[newcount - 1] = '\"';
    }

    public void T(int i, char c) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            T(c);
            return;
        }
        int newcount0 = this.Tr + (i < 0 ? Tn.T(-i) + 1 : Tn.T(i));
        int newcount1 = newcount0 + 1;
        if (newcount1 > this.f225T.length) {
            if (this.T9 != null) {
                Tr(i);
                T(c);
                return;
            }
            T(newcount1);
        }
        Tn.T(i, newcount0, this.f225T);
        this.f225T[newcount0] = c;
        this.Tr = newcount1;
    }

    public void T(long i, char c) throws IOException {
        if (i == Long.MIN_VALUE) {
            write("-9223372036854775808");
            T(c);
            return;
        }
        int newcount0 = this.Tr + (i < 0 ? Tn.T(-i) + 1 : Tn.T(i));
        int newcount1 = newcount0 + 1;
        if (newcount1 > this.f225T.length) {
            if (this.T9 != null) {
                T(i);
                T(c);
                return;
            }
            T(newcount1);
        }
        Tn.T(i, newcount0, this.f225T);
        this.f225T[newcount0] = c;
        this.Tr = newcount1;
    }

    public void T(long i) {
        if (i == Long.MIN_VALUE) {
            write("-9223372036854775808");
            return;
        }
        int size = i < 0 ? Tn.T(-i) + 1 : Tn.T(i);
        int newcount = this.Tr + size;
        if (newcount > this.f225T.length) {
            if (this.T9 == null) {
                T(newcount);
            } else {
                char[] chars = new char[size];
                Tn.T(i, size, chars);
                write(chars, 0, chars.length);
                return;
            }
        }
        Tn.T(i, newcount, this.f225T);
        this.Tr = newcount;
    }

    public void T() {
        write("null");
    }

    private void T(String text, char seperator) {
        T(text, seperator, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0417, code lost:
        if (T(com.T.T.Ty.TrG.WriteSlashAsSpecial) != false) goto L_0x0419;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0111, code lost:
        if (T(com.T.T.Ty.TrG.WriteSlashAsSpecial) != false) goto L_0x0113;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void T(java.lang.String r21, char r22, boolean r23) {
        /*
            r20 = this;
            if (r21 != 0) goto L_0x000f
            r20.T()
            if (r22 == 0) goto L_0x000e
            r0 = r20
            r1 = r22
            r0.T((char) r1)
        L_0x000e:
            return
        L_0x000f:
            int r10 = r21.length()
            r0 = r20
            int r15 = r0.Tr
            int r15 = r15 + r10
            int r11 = r15 + 2
            if (r22 == 0) goto L_0x001e
            int r11 = r11 + 1
        L_0x001e:
            r0 = r20
            char[] r15 = r0.f225T
            int r15 = r15.length
            if (r11 <= r15) goto L_0x0143
            r0 = r20
            java.io.Writer r15 = r0.T9
            if (r15 == 0) goto L_0x013e
            r15 = 34
            r0 = r20
            r0.T((char) r15)
            r7 = 0
        L_0x0033:
            int r15 = r21.length()
            if (r7 >= r15) goto L_0x012c
            r0 = r21
            char r4 = r0.charAt(r7)
            com.T.T.Ty.TrG r15 = com.T.T.Ty.TrG.BrowserCompatible
            r0 = r20
            boolean r15 = r0.T((com.T.T.Ty.TrG) r15)
            if (r15 == 0) goto L_0x00fa
            r15 = 8
            if (r4 == r15) goto L_0x0069
            r15 = 12
            if (r4 == r15) goto L_0x0069
            r15 = 10
            if (r4 == r15) goto L_0x0069
            r15 = 13
            if (r4 == r15) goto L_0x0069
            r15 = 9
            if (r4 == r15) goto L_0x0069
            r15 = 34
            if (r4 == r15) goto L_0x0069
            r15 = 47
            if (r4 == r15) goto L_0x0069
            r15 = 92
            if (r4 != r15) goto L_0x007c
        L_0x0069:
            r15 = 92
            r0 = r20
            r0.T((char) r15)
            char[] r15 = com.T.T.Tr.Tr.Tk
            char r15 = r15[r4]
            r0 = r20
            r0.T((char) r15)
        L_0x0079:
            int r7 = r7 + 1
            goto L_0x0033
        L_0x007c:
            r15 = 32
            if (r4 >= r15) goto L_0x00b5
            r15 = 92
            r0 = r20
            r0.T((char) r15)
            r15 = 117(0x75, float:1.64E-43)
            r0 = r20
            r0.T((char) r15)
            r15 = 48
            r0 = r20
            r0.T((char) r15)
            r15 = 48
            r0 = r20
            r0.T((char) r15)
            char[] r15 = com.T.T.Tr.Tr.TZ
            int r16 = r4 * 2
            char r15 = r15[r16]
            r0 = r20
            r0.T((char) r15)
            char[] r15 = com.T.T.Tr.Tr.TZ
            int r16 = r4 * 2
            int r16 = r16 + 1
            char r15 = r15[r16]
            r0 = r20
            r0.T((char) r15)
            goto L_0x0079
        L_0x00b5:
            r15 = 127(0x7f, float:1.78E-43)
            if (r4 < r15) goto L_0x0125
            r15 = 92
            r0 = r20
            r0.T((char) r15)
            r15 = 117(0x75, float:1.64E-43)
            r0 = r20
            r0.T((char) r15)
            char[] r15 = com.T.T.Tr.Tr.f181T
            int r16 = r4 >>> 12
            r16 = r16 & 15
            char r15 = r15[r16]
            r0 = r20
            r0.T((char) r15)
            char[] r15 = com.T.T.Tr.Tr.f181T
            int r16 = r4 >>> 8
            r16 = r16 & 15
            char r15 = r15[r16]
            r0 = r20
            r0.T((char) r15)
            char[] r15 = com.T.T.Tr.Tr.f181T
            int r16 = r4 >>> 4
            r16 = r16 & 15
            char r15 = r15[r16]
            r0 = r20
            r0.T((char) r15)
            char[] r15 = com.T.T.Tr.Tr.f181T
            r16 = r4 & 15
            char r15 = r15[r16]
            r0 = r20
            r0.T((char) r15)
            goto L_0x0079
        L_0x00fa:
            boolean[] r15 = com.T.T.Tr.Tr.Tn
            int r15 = r15.length
            if (r4 >= r15) goto L_0x0105
            boolean[] r15 = com.T.T.Tr.Tr.Tn
            boolean r15 = r15[r4]
            if (r15 != 0) goto L_0x0113
        L_0x0105:
            r15 = 47
            if (r4 != r15) goto L_0x0125
            com.T.T.Ty.TrG r15 = com.T.T.Ty.TrG.WriteSlashAsSpecial
            r0 = r20
            boolean r15 = r0.T((com.T.T.Ty.TrG) r15)
            if (r15 == 0) goto L_0x0125
        L_0x0113:
            r15 = 92
            r0 = r20
            r0.T((char) r15)
            char[] r15 = com.T.T.Tr.Tr.Tk
            char r15 = r15[r4]
            r0 = r20
            r0.T((char) r15)
            goto L_0x0079
        L_0x0125:
            r0 = r20
            r0.T((char) r4)
            goto L_0x0079
        L_0x012c:
            r15 = 34
            r0 = r20
            r0.T((char) r15)
            if (r22 == 0) goto L_0x000e
            r0 = r20
            r1 = r22
            r0.T((char) r1)
            goto L_0x000e
        L_0x013e:
            r0 = r20
            r0.T((int) r11)
        L_0x0143:
            r0 = r20
            int r15 = r0.Tr
            int r13 = r15 + 1
            int r5 = r13 + r10
            r0 = r20
            char[] r15 = r0.f225T
            r0 = r20
            int r0 = r0.Tr
            r16 = r0
            r17 = 34
            r15[r16] = r17
            r15 = 0
            r0 = r20
            char[] r0 = r0.f225T
            r16 = r0
            r0 = r21
            r1 = r16
            r0.getChars(r15, r10, r1, r13)
            r0 = r20
            r0.Tr = r11
            com.T.T.Ty.TrG r15 = com.T.T.Ty.TrG.BrowserCompatible
            r0 = r20
            boolean r15 = r0.T((com.T.T.Ty.TrG) r15)
            if (r15 == 0) goto L_0x034a
            r9 = -1
            r7 = r13
        L_0x0177:
            if (r7 >= r5) goto L_0x01b9
            r0 = r20
            char[] r15 = r0.f225T
            char r4 = r15[r7]
            r15 = 34
            if (r4 == r15) goto L_0x018b
            r15 = 47
            if (r4 == r15) goto L_0x018b
            r15 = 92
            if (r4 != r15) goto L_0x0191
        L_0x018b:
            r9 = r7
            int r11 = r11 + 1
        L_0x018e:
            int r7 = r7 + 1
            goto L_0x0177
        L_0x0191:
            r15 = 8
            if (r4 == r15) goto L_0x01a5
            r15 = 12
            if (r4 == r15) goto L_0x01a5
            r15 = 10
            if (r4 == r15) goto L_0x01a5
            r15 = 13
            if (r4 == r15) goto L_0x01a5
            r15 = 9
            if (r4 != r15) goto L_0x01a9
        L_0x01a5:
            r9 = r7
            int r11 = r11 + 1
            goto L_0x018e
        L_0x01a9:
            r15 = 32
            if (r4 >= r15) goto L_0x01b1
            r9 = r7
            int r11 = r11 + 5
            goto L_0x018e
        L_0x01b1:
            r15 = 127(0x7f, float:1.78E-43)
            if (r4 < r15) goto L_0x018e
            r9 = r7
            int r11 = r11 + 5
            goto L_0x018e
        L_0x01b9:
            r0 = r20
            char[] r15 = r0.f225T
            int r15 = r15.length
            if (r11 <= r15) goto L_0x01c5
            r0 = r20
            r0.T((int) r11)
        L_0x01c5:
            r0 = r20
            r0.Tr = r11
            r7 = r9
        L_0x01ca:
            if (r7 < r13) goto L_0x0316
            r0 = r20
            char[] r15 = r0.f225T
            char r4 = r15[r7]
            r15 = 8
            if (r4 == r15) goto L_0x01e6
            r15 = 12
            if (r4 == r15) goto L_0x01e6
            r15 = 10
            if (r4 == r15) goto L_0x01e6
            r15 = 13
            if (r4 == r15) goto L_0x01e6
            r15 = 9
            if (r4 != r15) goto L_0x0214
        L_0x01e6:
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 1
            r0 = r20
            char[] r0 = r0.f225T
            r17 = r0
            int r18 = r7 + 2
            int r19 = r5 - r7
            int r19 = r19 + -1
            java.lang.System.arraycopy(r15, r16, r17, r18, r19)
            r0 = r20
            char[] r15 = r0.f225T
            r16 = 92
            r15[r7] = r16
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 1
            char[] r17 = com.T.T.Tr.Tr.Tk
            char r17 = r17[r4]
            r15[r16] = r17
            int r5 = r5 + 1
        L_0x0211:
            int r7 = r7 + -1
            goto L_0x01ca
        L_0x0214:
            r15 = 34
            if (r4 == r15) goto L_0x0220
            r15 = 47
            if (r4 == r15) goto L_0x0220
            r15 = 92
            if (r4 != r15) goto L_0x0248
        L_0x0220:
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 1
            r0 = r20
            char[] r0 = r0.f225T
            r17 = r0
            int r18 = r7 + 2
            int r19 = r5 - r7
            int r19 = r19 + -1
            java.lang.System.arraycopy(r15, r16, r17, r18, r19)
            r0 = r20
            char[] r15 = r0.f225T
            r16 = 92
            r15[r7] = r16
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 1
            r15[r16] = r4
            int r5 = r5 + 1
            goto L_0x0211
        L_0x0248:
            r15 = 32
            if (r4 >= r15) goto L_0x02a9
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 1
            r0 = r20
            char[] r0 = r0.f225T
            r17 = r0
            int r18 = r7 + 6
            int r19 = r5 - r7
            int r19 = r19 + -1
            java.lang.System.arraycopy(r15, r16, r17, r18, r19)
            r0 = r20
            char[] r15 = r0.f225T
            r16 = 92
            r15[r7] = r16
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 1
            r17 = 117(0x75, float:1.64E-43)
            r15[r16] = r17
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 2
            r17 = 48
            r15[r16] = r17
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 3
            r17 = 48
            r15[r16] = r17
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 4
            char[] r17 = com.T.T.Tr.Tr.TZ
            int r18 = r4 * 2
            char r17 = r17[r18]
            r15[r16] = r17
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 5
            char[] r17 = com.T.T.Tr.Tr.TZ
            int r18 = r4 * 2
            int r18 = r18 + 1
            char r17 = r17[r18]
            r15[r16] = r17
            int r5 = r5 + 5
            goto L_0x0211
        L_0x02a9:
            r15 = 127(0x7f, float:1.78E-43)
            if (r4 < r15) goto L_0x0211
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 1
            r0 = r20
            char[] r0 = r0.f225T
            r17 = r0
            int r18 = r7 + 6
            int r19 = r5 - r7
            int r19 = r19 + -1
            java.lang.System.arraycopy(r15, r16, r17, r18, r19)
            r0 = r20
            char[] r15 = r0.f225T
            r16 = 92
            r15[r7] = r16
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 1
            r17 = 117(0x75, float:1.64E-43)
            r15[r16] = r17
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 2
            char[] r17 = com.T.T.Tr.Tr.f181T
            int r18 = r4 >>> 12
            r18 = r18 & 15
            char r17 = r17[r18]
            r15[r16] = r17
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 3
            char[] r17 = com.T.T.Tr.Tr.f181T
            int r18 = r4 >>> 8
            r18 = r18 & 15
            char r17 = r17[r18]
            r15[r16] = r17
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 4
            char[] r17 = com.T.T.Tr.Tr.f181T
            int r18 = r4 >>> 4
            r18 = r18 & 15
            char r17 = r17[r18]
            r15[r16] = r17
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r7 + 5
            char[] r17 = com.T.T.Tr.Tr.f181T
            r18 = r4 & 15
            char r17 = r17[r18]
            r15[r16] = r17
            int r5 = r5 + 5
            goto L_0x0211
        L_0x0316:
            if (r22 == 0) goto L_0x0338
            r0 = r20
            char[] r15 = r0.f225T
            r0 = r20
            int r0 = r0.Tr
            r16 = r0
            int r16 = r16 + -2
            r17 = 34
            r15[r16] = r17
            r0 = r20
            char[] r15 = r0.f225T
            r0 = r20
            int r0 = r0.Tr
            r16 = r0
            int r16 = r16 + -1
            r15[r16] = r22
            goto L_0x000e
        L_0x0338:
            r0 = r20
            char[] r15 = r0.f225T
            r0 = r20
            int r0 = r0.Tr
            r16 = r0
            int r16 = r16 + -1
            r17 = 34
            r15[r16] = r17
            goto L_0x000e
        L_0x034a:
            r12 = 0
            r9 = -1
            r6 = -1
            r8 = 0
            if (r23 == 0) goto L_0x038e
            r7 = r13
        L_0x0351:
            if (r7 >= r5) goto L_0x038e
            r0 = r20
            char[] r15 = r0.f225T
            char r4 = r15[r7]
            r15 = 93
            if (r4 < r15) goto L_0x0360
        L_0x035d:
            int r7 = r7 + 1
            goto L_0x0351
        L_0x0360:
            r15 = 32
            if (r4 == r15) goto L_0x035d
            r15 = 48
            if (r4 < r15) goto L_0x036c
            r15 = 92
            if (r4 != r15) goto L_0x035d
        L_0x036c:
            boolean[] r15 = com.T.T.Tr.Tr.Tn
            int r15 = r15.length
            if (r4 >= r15) goto L_0x0377
            boolean[] r15 = com.T.T.Tr.Tr.Tn
            boolean r15 = r15[r4]
            if (r15 != 0) goto L_0x0385
        L_0x0377:
            r15 = 47
            if (r4 != r15) goto L_0x035d
            com.T.T.Ty.TrG r15 = com.T.T.Ty.TrG.WriteSlashAsSpecial
            r0 = r20
            boolean r15 = r0.T((com.T.T.Ty.TrG) r15)
            if (r15 == 0) goto L_0x035d
        L_0x0385:
            int r12 = r12 + 1
            r9 = r7
            r8 = r4
            r15 = -1
            if (r6 != r15) goto L_0x035d
            r6 = r7
            goto L_0x035d
        L_0x038e:
            int r11 = r11 + r12
            r0 = r20
            char[] r15 = r0.f225T
            int r15 = r15.length
            if (r11 <= r15) goto L_0x039b
            r0 = r20
            r0.T((int) r11)
        L_0x039b:
            r0 = r20
            r0.Tr = r11
            r15 = 1
            if (r12 != r15) goto L_0x03ed
            r0 = r20
            char[] r15 = r0.f225T
            int r16 = r9 + 1
            r0 = r20
            char[] r0 = r0.f225T
            r17 = r0
            int r18 = r9 + 2
            int r19 = r5 - r9
            int r19 = r19 + -1
            java.lang.System.arraycopy(r15, r16, r17, r18, r19)
            r0 = r20
            char[] r15 = r0.f225T
            r16 = 92
            r15[r9] = r16
            r0 = r20
            char[] r15 = r0.f225T
            int r9 = r9 + 1
            char[] r16 = com.T.T.Tr.Tr.Tk
            char r16 = r16[r8]
            r15[r9] = r16
        L_0x03cb:
            if (r22 == 0) goto L_0x043e
            r0 = r20
            char[] r15 = r0.f225T
            r0 = r20
            int r0 = r0.Tr
            r16 = r0
            int r16 = r16 + -2
            r17 = 34
            r15[r16] = r17
            r0 = r20
            char[] r15 = r0.f225T
            r0 = r20
            int r0 = r0.Tr
            r16 = r0
            int r16 = r16 + -1
            r15[r16] = r22
            goto L_0x000e
        L_0x03ed:
            r15 = 1
            if (r12 <= r15) goto L_0x03cb
            int r14 = r6 - r13
            r2 = r6
            r7 = r14
        L_0x03f4:
            int r15 = r21.length()
            if (r7 >= r15) goto L_0x03cb
            r0 = r21
            char r4 = r0.charAt(r7)
            boolean[] r15 = com.T.T.Tr.Tr.Tn
            int r15 = r15.length
            if (r4 >= r15) goto L_0x040b
            boolean[] r15 = com.T.T.Tr.Tr.Tn
            boolean r15 = r15[r4]
            if (r15 != 0) goto L_0x0419
        L_0x040b:
            r15 = 47
            if (r4 != r15) goto L_0x0434
            com.T.T.Ty.TrG r15 = com.T.T.Ty.TrG.WriteSlashAsSpecial
            r0 = r20
            boolean r15 = r0.T((com.T.T.Ty.TrG) r15)
            if (r15 == 0) goto L_0x0434
        L_0x0419:
            r0 = r20
            char[] r15 = r0.f225T
            int r3 = r2 + 1
            r16 = 92
            r15[r2] = r16
            r0 = r20
            char[] r15 = r0.f225T
            int r2 = r3 + 1
            char[] r16 = com.T.T.Tr.Tr.Tk
            char r16 = r16[r4]
            r15[r3] = r16
            int r5 = r5 + 1
        L_0x0431:
            int r7 = r7 + 1
            goto L_0x03f4
        L_0x0434:
            r0 = r20
            char[] r15 = r0.f225T
            int r3 = r2 + 1
            r15[r2] = r4
            r2 = r3
            goto L_0x0431
        L_0x043e:
            r0 = r20
            char[] r15 = r0.f225T
            r0 = r20
            int r0 = r0.Tr
            r16 = r0
            int r16 = r16 + -1
            r17 = 34
            r15[r16] = r17
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.T.T.Ty.Trs.T(java.lang.String, char, boolean):void");
    }

    public void T(boolean value) {
        if (value) {
            write("true");
        } else {
            write("false");
        }
    }

    public void T(String text) {
        if (T(TrG.UseSingleQuotes)) {
            Ty(text);
        } else {
            T(text, 0);
        }
    }

    private void Ty(String text) {
        if (text == null) {
            int newcount = this.Tr + 4;
            if (newcount > this.f225T.length) {
                T(newcount);
            }
            "null".getChars(0, 4, this.f225T, this.Tr);
            this.Tr = newcount;
            return;
        }
        int len = text.length();
        int newcount2 = this.Tr + len + 2;
        if (newcount2 > this.f225T.length) {
            if (this.T9 != null) {
                T('\'');
                for (int i = 0; i < text.length(); i++) {
                    char ch = text.charAt(i);
                    if (ch <= 13 || ch == '\\' || ch == '\'' || (ch == '/' && T(TrG.WriteSlashAsSpecial))) {
                        T('\\');
                        T(Tr.Tk[ch]);
                    } else {
                        T(ch);
                    }
                }
                T('\'');
                return;
            }
            T(newcount2);
        }
        int start = this.Tr + 1;
        int end = start + len;
        this.f225T[this.Tr] = '\'';
        text.getChars(0, len, this.f225T, start);
        this.Tr = newcount2;
        int specialCount = 0;
        int lastSpecialIndex = -1;
        char lastSpecial = 0;
        for (int i2 = start; i2 < end; i2++) {
            char ch2 = this.f225T[i2];
            if (ch2 <= 13 || ch2 == '\\' || ch2 == '\'' || (ch2 == '/' && T(TrG.WriteSlashAsSpecial))) {
                specialCount++;
                lastSpecialIndex = i2;
                lastSpecial = ch2;
            }
        }
        int newcount3 = newcount2 + specialCount;
        if (newcount3 > this.f225T.length) {
            T(newcount3);
        }
        this.Tr = newcount3;
        if (specialCount == 1) {
            System.arraycopy(this.f225T, lastSpecialIndex + 1, this.f225T, lastSpecialIndex + 2, (end - lastSpecialIndex) - 1);
            this.f225T[lastSpecialIndex] = '\\';
            this.f225T[lastSpecialIndex + 1] = Tr.Tk[lastSpecial];
        } else if (specialCount > 1) {
            System.arraycopy(this.f225T, lastSpecialIndex + 1, this.f225T, lastSpecialIndex + 2, (end - lastSpecialIndex) - 1);
            this.f225T[lastSpecialIndex] = '\\';
            int lastSpecialIndex2 = lastSpecialIndex + 1;
            this.f225T[lastSpecialIndex2] = Tr.Tk[lastSpecial];
            int end2 = end + 1;
            for (int i3 = lastSpecialIndex2 - 2; i3 >= start; i3--) {
                char ch3 = this.f225T[i3];
                if (ch3 <= 13 || ch3 == '\\' || ch3 == '\'' || (ch3 == '/' && T(TrG.WriteSlashAsSpecial))) {
                    System.arraycopy(this.f225T, i3 + 1, this.f225T, i3 + 2, (end2 - i3) - 1);
                    this.f225T[i3] = '\\';
                    this.f225T[i3 + 1] = Tr.Tk[ch3];
                    end2++;
                }
            }
        }
        this.f225T[this.Tr - 1] = '\'';
    }

    public void Tr(String key) {
        T(key, false);
    }

    public void T(String key, boolean checkSpecial) {
        if (key == null) {
            write("null:");
        } else if (T(TrG.UseSingleQuotes)) {
            if (T(TrG.QuoteFieldNames)) {
                Ty(key);
                T(':');
                return;
            }
            T9(key);
        } else if (T(TrG.QuoteFieldNames)) {
            T(key, ':', checkSpecial);
        } else {
            Tn(key);
        }
    }

    private void Tn(String text) {
        boolean[] specicalFlags_doubleQuotes = Tr.Tn;
        int len = text.length();
        int newcount = this.Tr + len + 1;
        if (newcount > this.f225T.length) {
            if (this.T9 == null) {
                T(newcount);
            } else if (len == 0) {
                T('\"');
                T('\"');
                T(':');
                return;
            } else {
                boolean hasSpecial = false;
                int i = 0;
                while (true) {
                    if (i < len) {
                        char ch = text.charAt(i);
                        if (ch < specicalFlags_doubleQuotes.length && specicalFlags_doubleQuotes[ch]) {
                            hasSpecial = true;
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
                if (hasSpecial) {
                    T('\"');
                }
                for (int i2 = 0; i2 < len; i2++) {
                    char ch2 = text.charAt(i2);
                    if (ch2 >= specicalFlags_doubleQuotes.length || !specicalFlags_doubleQuotes[ch2]) {
                        T(ch2);
                    } else {
                        T('\\');
                        T(Tr.Tk[ch2]);
                    }
                }
                if (hasSpecial) {
                    T('\"');
                }
                T(':');
                return;
            }
        }
        if (len == 0) {
            if (this.Tr + 3 > this.f225T.length) {
                T(this.Tr + 3);
            }
            char[] cArr = this.f225T;
            int i3 = this.Tr;
            this.Tr = i3 + 1;
            cArr[i3] = '\"';
            char[] cArr2 = this.f225T;
            int i4 = this.Tr;
            this.Tr = i4 + 1;
            cArr2[i4] = '\"';
            char[] cArr3 = this.f225T;
            int i5 = this.Tr;
            this.Tr = i5 + 1;
            cArr3[i5] = ':';
            return;
        }
        int start = this.Tr;
        int end = start + len;
        text.getChars(0, len, this.f225T, start);
        this.Tr = newcount;
        boolean hasSpecial2 = false;
        int i6 = start;
        while (i6 < end) {
            char ch3 = this.f225T[i6];
            if (ch3 < specicalFlags_doubleQuotes.length && specicalFlags_doubleQuotes[ch3]) {
                if (!hasSpecial2) {
                    newcount += 3;
                    if (newcount > this.f225T.length) {
                        T(newcount);
                    }
                    this.Tr = newcount;
                    System.arraycopy(this.f225T, i6 + 1, this.f225T, i6 + 3, (end - i6) - 1);
                    System.arraycopy(this.f225T, 0, this.f225T, 1, i6);
                    this.f225T[start] = '\"';
                    int i7 = i6 + 1;
                    this.f225T[i7] = '\\';
                    i6 = i7 + 1;
                    this.f225T[i6] = Tr.Tk[ch3];
                    end += 2;
                    this.f225T[this.Tr - 2] = '\"';
                    hasSpecial2 = true;
                } else {
                    newcount++;
                    if (newcount > this.f225T.length) {
                        T(newcount);
                    }
                    this.Tr = newcount;
                    System.arraycopy(this.f225T, i6 + 1, this.f225T, i6 + 2, end - i6);
                    this.f225T[i6] = '\\';
                    i6++;
                    this.f225T[i6] = Tr.Tk[ch3];
                    end++;
                }
            }
            i6++;
        }
        this.f225T[this.Tr - 1] = ':';
    }

    private void T9(String text) {
        boolean[] specicalFlags_singleQuotes = Tr.T9;
        int len = text.length();
        int newcount = this.Tr + len + 1;
        if (newcount > this.f225T.length) {
            if (this.T9 == null) {
                T(newcount);
            } else if (len == 0) {
                T('\'');
                T('\'');
                T(':');
                return;
            } else {
                boolean hasSpecial = false;
                int i = 0;
                while (true) {
                    if (i < len) {
                        char ch = text.charAt(i);
                        if (ch < specicalFlags_singleQuotes.length && specicalFlags_singleQuotes[ch]) {
                            hasSpecial = true;
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
                if (hasSpecial) {
                    T('\'');
                }
                for (int i2 = 0; i2 < len; i2++) {
                    char ch2 = text.charAt(i2);
                    if (ch2 >= specicalFlags_singleQuotes.length || !specicalFlags_singleQuotes[ch2]) {
                        T(ch2);
                    } else {
                        T('\\');
                        T(Tr.Tk[ch2]);
                    }
                }
                if (hasSpecial) {
                    T('\'');
                }
                T(':');
                return;
            }
        }
        if (len == 0) {
            if (this.Tr + 3 > this.f225T.length) {
                T(this.Tr + 3);
            }
            char[] cArr = this.f225T;
            int i3 = this.Tr;
            this.Tr = i3 + 1;
            cArr[i3] = '\'';
            char[] cArr2 = this.f225T;
            int i4 = this.Tr;
            this.Tr = i4 + 1;
            cArr2[i4] = '\'';
            char[] cArr3 = this.f225T;
            int i5 = this.Tr;
            this.Tr = i5 + 1;
            cArr3[i5] = ':';
            return;
        }
        int start = this.Tr;
        int end = start + len;
        text.getChars(0, len, this.f225T, start);
        this.Tr = newcount;
        boolean hasSpecial2 = false;
        int i6 = start;
        while (i6 < end) {
            char ch3 = this.f225T[i6];
            if (ch3 < specicalFlags_singleQuotes.length && specicalFlags_singleQuotes[ch3]) {
                if (!hasSpecial2) {
                    newcount += 3;
                    if (newcount > this.f225T.length) {
                        T(newcount);
                    }
                    this.Tr = newcount;
                    System.arraycopy(this.f225T, i6 + 1, this.f225T, i6 + 3, (end - i6) - 1);
                    System.arraycopy(this.f225T, 0, this.f225T, 1, i6);
                    this.f225T[start] = '\'';
                    int i7 = i6 + 1;
                    this.f225T[i7] = '\\';
                    i6 = i7 + 1;
                    this.f225T[i6] = Tr.Tk[ch3];
                    end += 2;
                    this.f225T[this.Tr - 2] = '\'';
                    hasSpecial2 = true;
                } else {
                    newcount++;
                    if (newcount > this.f225T.length) {
                        T(newcount);
                    }
                    this.Tr = newcount;
                    System.arraycopy(this.f225T, i6 + 1, this.f225T, i6 + 2, end - i6);
                    this.f225T[i6] = '\\';
                    i6++;
                    this.f225T[i6] = Tr.Tk[ch3];
                    end++;
                }
            }
            i6++;
        }
        this.f225T[newcount - 1] = ':';
    }

    public void flush() {
        if (this.T9 != null) {
            try {
                this.T9.write(this.f225T, 0, this.Tr);
                this.T9.flush();
                this.Tr = 0;
            } catch (IOException e) {
                throw new com.T.T.Tn(e.getMessage(), e);
            }
        }
    }
}
