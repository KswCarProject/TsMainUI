package com.Tr.Tr.Ty.T;

import com.Tr.Tr.Tr;
import java.nio.charset.Charset;

/* compiled from: Proguard */
final class TE {

    /* renamed from: T  reason: collision with root package name */
    int f295T;
    private int T5;
    private Tr T9;
    private Th TE;
    private int TZ;
    private final StringBuilder Tk;
    private Tr Tn;
    private final String Tr;
    private T6 Ty;

    TE(String msg) {
        byte[] msgBinary = msg.getBytes(Charset.forName("ISO-8859-1"));
        StringBuilder sb = new StringBuilder(msgBinary.length);
        int i = 0;
        int c = msgBinary.length;
        while (i < c) {
            char ch = (char) (msgBinary[i] & 255);
            if (ch != '?' || msg.charAt(i) == '?') {
                sb.append(ch);
                i++;
            } else {
                throw new IllegalArgumentException("Message contains characters outside ISO-8859-1 encoding.");
            }
        }
        this.Tr = sb.toString();
        this.Ty = T6.FORCE_NONE;
        this.Tk = new StringBuilder(msg.length());
        this.TZ = -1;
    }

    public void T(T6 shape) {
        this.Ty = shape;
    }

    public void T(Tr minSize, Tr maxSize) {
        this.Tn = minSize;
        this.T9 = maxSize;
    }

    public String T() {
        return this.Tr;
    }

    public void T(int count) {
        this.T5 = count;
    }

    public char Tr() {
        return this.Tr.charAt(this.f295T);
    }

    public StringBuilder Ty() {
        return this.Tk;
    }

    public void T(String codewords) {
        this.Tk.append(codewords);
    }

    public void T(char codeword) {
        this.Tk.append(codeword);
    }

    public int Tn() {
        return this.Tk.length();
    }

    public int T9() {
        return this.TZ;
    }

    public void Tr(int encoding) {
        this.TZ = encoding;
    }

    public void Tk() {
        this.TZ = -1;
    }

    public boolean TZ() {
        return this.f295T < T6();
    }

    private int T6() {
        return this.Tr.length() - this.T5;
    }

    public int TE() {
        return T6() - this.f295T;
    }

    public Th T5() {
        return this.TE;
    }

    public void Tv() {
        Ty(Tn());
    }

    public void Ty(int len) {
        if (this.TE == null || len > this.TE.Tk()) {
            this.TE = Th.T(len, this.Ty, this.Tn, this.T9, true);
        }
    }

    public void Th() {
        this.TE = null;
    }
}
