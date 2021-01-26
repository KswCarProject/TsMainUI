package com.T.T.Tr;

/* compiled from: Proguard */
public final class Tr {

    /* renamed from: T  reason: collision with root package name */
    public static final char[] f184T = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final boolean[] T9 = new boolean[128];
    public static final char[] TZ = {'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};
    public static final char[] Tk = new char[128];
    public static final boolean[] Tn = new boolean[128];
    public static final boolean[] Tr = new boolean[256];
    public static final boolean[] Ty = new boolean[256];

    static {
        for (char c = 0; c < Tr.length; c = (char) (c + 1)) {
            if (c >= 'A' && c <= 'Z') {
                Tr[c] = true;
            } else if (c >= 'a' && c <= 'z') {
                Tr[c] = true;
            } else if (c == '_') {
                Tr[c] = true;
            }
        }
        for (char c2 = 0; c2 < Ty.length; c2 = (char) (c2 + 1)) {
            if (c2 >= 'A' && c2 <= 'Z') {
                Ty[c2] = true;
            } else if (c2 >= 'a' && c2 <= 'z') {
                Ty[c2] = true;
            } else if (c2 == '_') {
                Ty[c2] = true;
            } else if (c2 >= '0' && c2 <= '9') {
                Ty[c2] = true;
            }
        }
        Tn[0] = true;
        Tn[1] = true;
        Tn[2] = true;
        Tn[3] = true;
        Tn[4] = true;
        Tn[5] = true;
        Tn[6] = true;
        Tn[7] = true;
        Tn[8] = true;
        Tn[9] = true;
        Tn[10] = true;
        Tn[11] = true;
        Tn[12] = true;
        Tn[13] = true;
        Tn[34] = true;
        Tn[92] = true;
        T9[0] = true;
        T9[1] = true;
        T9[2] = true;
        T9[3] = true;
        T9[4] = true;
        T9[5] = true;
        T9[6] = true;
        T9[7] = true;
        T9[8] = true;
        T9[9] = true;
        T9[10] = true;
        T9[11] = true;
        T9[12] = true;
        T9[13] = true;
        T9[39] = true;
        T9[92] = true;
        Tk[0] = '0';
        Tk[1] = '1';
        Tk[2] = '2';
        Tk[3] = '3';
        Tk[4] = '4';
        Tk[5] = '5';
        Tk[6] = '6';
        Tk[7] = '7';
        Tk[8] = 'b';
        Tk[9] = 't';
        Tk[10] = 'n';
        Tk[11] = 'v';
        Tk[12] = 'f';
        Tk[13] = 'r';
        Tk[34] = '\"';
        Tk[39] = '\'';
        Tk[47] = '/';
        Tk[92] = '\\';
    }
}
