package com.Tr.T.T;

import java.io.IOException;
import net.easyconn.platform.wrc.core.WrcManager;

/* compiled from: Proguard */
public final class T {

    /* renamed from: T  reason: collision with root package name */
    private final byte[] f237T;
    private int T5 = 64;
    private int T9;
    private int TE;
    private int TZ = Integer.MAX_VALUE;
    private int Tk;
    private int Tn;
    private int Tr;
    private int Tv = 67108864;
    private int Ty;

    public static T T(byte[] bArr, int i, int i2) {
        return new T(bArr, i, i2);
    }

    public int T() throws IOException {
        if (Tj()) {
            this.Tk = 0;
            return 0;
        }
        this.Tk = Th();
        if (this.Tk != 0) {
            return this.Tk;
        }
        throw Tn.Tn();
    }

    public void T(int i) throws Tn {
        if (this.Tk != i) {
            throw Tn.T9();
        }
    }

    public boolean Tr(int i) throws IOException {
        switch (TZ.T(i)) {
            case 0:
                Tk();
                return true;
            case 1:
                Tq();
                return true;
            case 2:
                TZ(Th());
                return true;
            case 3:
                Tr();
                T(TZ.T(TZ.Tr(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                Te();
                return true;
            default:
                throw Tn.Tk();
        }
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void Tr() throws java.io.IOException {
        /*
            r1 = this;
        L_0x0000:
            int r0 = r1.T()
            if (r0 == 0) goto L_0x000c
            boolean r0 = r1.Tr(r0)
            if (r0 != 0) goto L_0x0000
        L_0x000c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Tr.T.T.T.Tr():void");
    }

    public double Ty() throws IOException {
        return Double.longBitsToDouble(Tq());
    }

    public float Tn() throws IOException {
        return Float.intBitsToFloat(Te());
    }

    public long T9() throws IOException {
        return T6();
    }

    public int Tk() throws IOException {
        return Th();
    }

    public boolean TZ() throws IOException {
        return Th() != 0;
    }

    public String TE() throws IOException {
        int Th = Th();
        if (Th > this.Ty - this.T9 || Th <= 0) {
            return new String(Tk(Th), "UTF-8");
        }
        String str = new String(this.f237T, this.T9, Th, "UTF-8");
        this.T9 = Th + this.T9;
        return str;
    }

    public void T(T9 t9) throws IOException {
        int Th = Th();
        if (this.TE >= this.T5) {
            throw Tn.TZ();
        }
        int Ty2 = Ty(Th);
        this.TE++;
        t9.T(this);
        T(0);
        this.TE--;
        Tn(Ty2);
    }

    public byte[] T5() throws IOException {
        int Th = Th();
        if (Th > this.Ty - this.T9 || Th <= 0) {
            return Tk(Th);
        }
        byte[] bArr = new byte[Th];
        System.arraycopy(this.f237T, this.T9, bArr, 0, Th);
        this.T9 = Th + this.T9;
        return bArr;
    }

    public int Tv() throws IOException {
        return Th();
    }

    public int Th() throws IOException {
        byte TK = TK();
        if (TK >= 0) {
            return TK;
        }
        byte b = TK & Byte.MAX_VALUE;
        byte TK2 = TK();
        if (TK2 >= 0) {
            return b | (TK2 << 7);
        }
        byte b2 = b | ((TK2 & Byte.MAX_VALUE) << 7);
        byte TK3 = TK();
        if (TK3 >= 0) {
            return b2 | (TK3 << 14);
        }
        byte b3 = b2 | ((TK3 & Byte.MAX_VALUE) << 14);
        byte TK4 = TK();
        if (TK4 >= 0) {
            return b3 | (TK4 << 21);
        }
        byte b4 = b3 | ((TK4 & Byte.MAX_VALUE) << 21);
        byte TK5 = TK();
        byte b5 = b4 | (TK5 << 28);
        if (TK5 >= 0) {
            return b5;
        }
        for (int i = 0; i < 5; i++) {
            if (TK() >= 0) {
                return b5;
            }
        }
        throw Tn.Ty();
    }

    public long T6() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte TK = TK();
            j |= ((long) (TK & Byte.MAX_VALUE)) << i;
            if ((TK & 128) == 0) {
                return j;
            }
        }
        throw Tn.Ty();
    }

    public int Te() throws IOException {
        return (TK() & 255) | ((TK() & 255) << 8) | ((TK() & 255) << WrcManager.WrcCallback.KEY_CENTER) | ((TK() & 255) << 24);
    }

    public long Tq() throws IOException {
        byte TK = TK();
        byte TK2 = TK();
        return ((((long) TK2) & 255) << 8) | (((long) TK) & 255) | ((((long) TK()) & 255) << 16) | ((((long) TK()) & 255) << 24) | ((((long) TK()) & 255) << 32) | ((((long) TK()) & 255) << 40) | ((((long) TK()) & 255) << 48) | ((((long) TK()) & 255) << 56);
    }

    private T(byte[] bArr, int i, int i2) {
        this.f237T = bArr;
        this.Tr = i;
        this.Ty = i + i2;
        this.T9 = i;
    }

    public int Ty(int i) throws Tn {
        if (i < 0) {
            throw Tn.Tr();
        }
        int i2 = this.T9 + i;
        int i3 = this.TZ;
        if (i2 > i3) {
            throw Tn.T();
        }
        this.TZ = i2;
        TO();
        return i3;
    }

    private void TO() {
        this.Ty += this.Tn;
        int i = this.Ty;
        if (i > this.TZ) {
            this.Tn = i - this.TZ;
            this.Ty -= this.Tn;
            return;
        }
        this.Tn = 0;
    }

    public void Tn(int i) {
        this.TZ = i;
        TO();
    }

    public int TF() {
        if (this.TZ == Integer.MAX_VALUE) {
            return -1;
        }
        return this.TZ - this.T9;
    }

    public boolean Tj() {
        return this.T9 == this.Ty;
    }

    public int TB() {
        return this.T9 - this.Tr;
    }

    public void T9(int i) {
        if (i > this.T9 - this.Tr) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.T9 - this.Tr));
        } else if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        } else {
            this.T9 = this.Tr + i;
        }
    }

    public byte TK() throws IOException {
        if (this.T9 == this.Ty) {
            throw Tn.T();
        }
        byte[] bArr = this.f237T;
        int i = this.T9;
        this.T9 = i + 1;
        return bArr[i];
    }

    public byte[] Tk(int i) throws IOException {
        if (i < 0) {
            throw Tn.Tr();
        } else if (this.T9 + i > this.TZ) {
            TZ(this.TZ - this.T9);
            throw Tn.T();
        } else if (i <= this.Ty - this.T9) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.f237T, this.T9, bArr, 0, i);
            this.T9 += i;
            return bArr;
        } else {
            throw Tn.T();
        }
    }

    public void TZ(int i) throws IOException {
        if (i < 0) {
            throw Tn.Tr();
        } else if (this.T9 + i > this.TZ) {
            TZ(this.TZ - this.T9);
            throw Tn.T();
        } else if (i <= this.Ty - this.T9) {
            this.T9 += i;
        } else {
            throw Tn.T();
        }
    }
}
