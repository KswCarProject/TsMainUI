package com.Tr.Tr.Tk.Tr;

import com.Tr.Tr.Tk.T.T;
import com.Tr.Tr.Tk.T.Tr;
import com.Tr.Tr.Tk.T.Ty;

/* compiled from: Proguard */
public final class Tk {

    /* renamed from: T  reason: collision with root package name */
    private Tr f269T;
    private Tr T9;
    private int Tn = -1;
    private T Tr;
    private Ty Ty;

    public Tr T() {
        return this.T9;
    }

    public String toString() {
        StringBuilder result = new StringBuilder(200);
        result.append("<<\n");
        result.append(" mode: ");
        result.append(this.f269T);
        result.append("\n ecLevel: ");
        result.append(this.Tr);
        result.append("\n version: ");
        result.append(this.Ty);
        result.append("\n maskPattern: ");
        result.append(this.Tn);
        if (this.T9 == null) {
            result.append("\n matrix: null\n");
        } else {
            result.append("\n matrix:\n");
            result.append(this.T9);
        }
        result.append(">>\n");
        return result.toString();
    }

    public void T(Tr value) {
        this.f269T = value;
    }

    public void T(T value) {
        this.Tr = value;
    }

    public void T(Ty version) {
        this.Ty = version;
    }

    public void T(int value) {
        this.Tn = value;
    }

    public void T(Tr value) {
        this.T9 = value;
    }

    public static boolean Tr(int maskPattern) {
        return maskPattern >= 0 && maskPattern < 8;
    }
}
