package com.T.T.Tr;

import com.T.T.T;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: Proguard */
public final class Tk extends T9 {
    public final int TB;
    public final int TK;
    private final String TO;
    public final int Tj;

    public Tk(String input) {
        this(input, T.Tr);
    }

    public Tk(String input, int features) {
        this.Tj = "0000-00-00".length();
        this.TB = "0000-00-00T00:00:00".length();
        this.TK = "0000-00-00T00:00:00.000".length();
        this.Ty = features;
        this.TO = input;
        this.T9 = -1;
        Tq();
        if (this.Tn == 65279) {
            Tq();
        }
    }

    public final char Ty(int index) {
        if (index >= this.TO.length()) {
            return 26;
        }
        return this.TO.charAt(index);
    }

    public final char Tq() {
        int i = this.T9 + 1;
        this.T9 = i;
        char Ty = Ty(i);
        this.Tn = Ty;
        return Ty;
    }

    /* access modifiers changed from: protected */
    public final void T(int offset, int count, char[] dest) {
        this.TO.getChars(offset, offset + count, dest, 0);
    }

    public final String T(int offset, int len, int hash, Th symbolTable) {
        return symbolTable.T(this.TO, offset, len, hash);
    }

    public byte[] TO() {
        return com.T.T.Tn.T.T(this.TO, this.T5 + 1, this.TE);
    }

    /* access modifiers changed from: protected */
    public void T(int srcPos, char[] dest, int destPos, int length) {
        this.TO.getChars(srcPos, srcPos + length, dest, destPos);
    }

    public final String Tf() {
        if (!this.Tv) {
            return this.TO.substring(this.T5 + 1, this.T5 + 1 + this.TE);
        }
        return new String(this.TZ, 0, this.TE);
    }

    public final String T(int offset, int count) {
        return this.TO.substring(offset, offset + count);
    }

    public final String Th() {
        char chLocal = Ty((this.T5 + this.TE) - 1);
        int sp = this.TE;
        if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B' || chLocal == 'F' || chLocal == 'D') {
            sp--;
        }
        return this.TO.substring(this.T5, this.T5 + sp);
    }

    public boolean TI() {
        return Tr(true);
    }

    public boolean Tr(boolean strict) {
        int hour;
        int minute;
        int seconds;
        int millis;
        int rest = this.TO.length() - this.T9;
        if (!strict && rest > 13) {
            char c0 = Ty(this.T9);
            char c1 = Ty(this.T9 + 1);
            char c2 = Ty(this.T9 + 2);
            char c3 = Ty(this.T9 + 3);
            char c4 = Ty(this.T9 + 4);
            char c5 = Ty(this.T9 + 5);
            char c_r0 = Ty((this.T9 + rest) - 1);
            char c_r1 = Ty((this.T9 + rest) - 2);
            if (c0 == '/' && c1 == 'D' && c2 == 'a' && c3 == 't' && c4 == 'e' && c5 == '(' && c_r0 == '/' && c_r1 == ')') {
                int plusIndex = -1;
                for (int i = 6; i < rest; i++) {
                    char c = Ty(this.T9 + i);
                    if (c != '+') {
                        if (c < '0' || c > '9') {
                            break;
                        }
                    } else {
                        plusIndex = i;
                    }
                }
                if (plusIndex == -1) {
                    return false;
                }
                int offset = this.T9 + 6;
                long millis2 = Long.parseLong(T(offset, plusIndex - offset));
                this.Th = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
                this.Th.setTimeInMillis(millis2);
                this.f179T = 5;
                return true;
            }
        }
        if (rest == 8 || rest == 14 || rest == 17) {
            if (strict) {
                return false;
            }
            char y0 = Ty(this.T9);
            char y1 = Ty(this.T9 + 1);
            char y2 = Ty(this.T9 + 2);
            char y3 = Ty(this.T9 + 3);
            char M0 = Ty(this.T9 + 4);
            char M1 = Ty(this.T9 + 5);
            char d0 = Ty(this.T9 + 6);
            char d1 = Ty(this.T9 + 7);
            if (!T(y0, y1, y2, y3, M0, M1, (int) d0, (int) d1)) {
                return false;
            }
            T(y0, y1, y2, y3, M0, M1, d0, d1);
            if (rest != 8) {
                char h0 = Ty(this.T9 + 8);
                char h1 = Ty(this.T9 + 9);
                char m0 = Ty(this.T9 + 10);
                char m1 = Ty(this.T9 + 11);
                char s0 = Ty(this.T9 + 12);
                char s1 = Ty(this.T9 + 13);
                if (!T(h0, h1, m0, m1, s0, s1)) {
                    return false;
                }
                if (rest == 17) {
                    char S0 = Ty(this.T9 + 14);
                    char S1 = Ty(this.T9 + 15);
                    char S2 = Ty(this.T9 + 16);
                    if (S0 < '0' || S0 > '9' || S1 < '0' || S1 > '9' || S2 < '0' || S2 > '9') {
                        return false;
                    }
                    millis = (TF[S0] * 100) + (TF[S1] * 10) + TF[S2];
                } else {
                    millis = 0;
                }
                hour = (TF[h0] * 10) + TF[h1];
                minute = (TF[m0] * 10) + TF[m1];
                seconds = (TF[s0] * 10) + TF[s1];
            } else {
                hour = 0;
                minute = 0;
                seconds = 0;
                millis = 0;
            }
            this.Th.set(11, hour);
            this.Th.set(12, minute);
            this.Th.set(13, seconds);
            this.Th.set(14, millis);
            this.f179T = 5;
            return true;
        } else if (rest < this.Tj) {
            return false;
        } else {
            if (Ty(this.T9 + 4) != '-') {
                return false;
            }
            if (Ty(this.T9 + 7) != '-') {
                return false;
            }
            char y02 = Ty(this.T9);
            char y12 = Ty(this.T9 + 1);
            char y22 = Ty(this.T9 + 2);
            char y32 = Ty(this.T9 + 3);
            char M02 = Ty(this.T9 + 5);
            char M12 = Ty(this.T9 + 6);
            char d02 = Ty(this.T9 + 8);
            char d12 = Ty(this.T9 + 9);
            if (!T(y02, y12, y22, y32, M02, M12, (int) d02, (int) d12)) {
                return false;
            }
            T(y02, y12, y22, y32, M02, M12, d02, d12);
            char t = Ty(this.T9 + 10);
            if (t == 'T' || (t == ' ' && !strict)) {
                if (rest < this.TB) {
                    return false;
                }
                if (Ty(this.T9 + 13) != ':') {
                    return false;
                }
                if (Ty(this.T9 + 16) != ':') {
                    return false;
                }
                char h02 = Ty(this.T9 + 11);
                char h12 = Ty(this.T9 + 12);
                char m02 = Ty(this.T9 + 14);
                char m12 = Ty(this.T9 + 15);
                char s02 = Ty(this.T9 + 17);
                char s12 = Ty(this.T9 + 18);
                if (!T(h02, h12, m02, m12, s02, s12)) {
                    return false;
                }
                int hour2 = (TF[h02] * 10) + TF[h12];
                int minute2 = (TF[m02] * 10) + TF[m12];
                int seconds2 = (TF[s02] * 10) + TF[s12];
                this.Th.set(11, hour2);
                this.Th.set(12, minute2);
                this.Th.set(13, seconds2);
                if (Ty(this.T9 + 19) != '.') {
                    this.Th.set(14, 0);
                    int i2 = this.T9 + 19;
                    this.T9 = i2;
                    this.Tn = Ty(i2);
                    this.f179T = 5;
                    return true;
                } else if (rest < this.TK) {
                    return false;
                } else {
                    char S02 = Ty(this.T9 + 20);
                    char S12 = Ty(this.T9 + 21);
                    char S22 = Ty(this.T9 + 22);
                    if (S02 < '0' || S02 > '9' || S12 < '0' || S12 > '9' || S22 < '0' || S22 > '9') {
                        return false;
                    }
                    this.Th.set(14, (TF[S02] * 100) + (TF[S12] * 10) + TF[S22]);
                    int i3 = this.T9 + 23;
                    this.T9 = i3;
                    this.Tn = Ty(i3);
                    this.f179T = 5;
                    return true;
                }
            } else if (t != '\"' && t != 26) {
                return false;
            } else {
                this.Th.set(11, 0);
                this.Th.set(12, 0);
                this.Th.set(13, 0);
                this.Th.set(14, 0);
                int i4 = this.T9 + 10;
                this.T9 = i4;
                this.Tn = Ty(i4);
                this.f179T = 5;
                return true;
            }
        }
    }

    private boolean T(char h0, char h1, char m0, char m1, char s0, char s1) {
        if (h0 == '0') {
            if (h1 < '0' || h1 > '9') {
                return false;
            }
        } else if (h0 == '1') {
            if (h1 < '0' || h1 > '9') {
                return false;
            }
        } else if (h0 != '2' || h1 < '0' || h1 > '4') {
            return false;
        }
        if (m0 < '0' || m0 > '5') {
            if (!(m0 == '6' && m1 == '0')) {
                return false;
            }
        } else if (m1 < '0' || m1 > '9') {
            return false;
        }
        if (s0 < '0' || s0 > '5') {
            if (!(s0 == '6' && s1 == '0')) {
                return false;
            }
        } else if (s1 < '0' || s1 > '9') {
            return false;
        }
        return true;
    }

    private void T(char y0, char y1, char y2, char y3, char M0, char M1, char d0, char d1) {
        this.Th = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        int year = (TF[y0] * 1000) + (TF[y1] * 100) + (TF[y2] * 10) + TF[y3];
        int month = ((TF[M0] * 10) + TF[M1]) - 1;
        int day = (TF[d0] * 10) + TF[d1];
        this.Th.set(1, year);
        this.Th.set(2, month);
        this.Th.set(5, day);
    }

    static boolean T(char y0, char y1, char y2, char y3, char M0, char M1, int d0, int d1) {
        if ((y0 != '1' && y0 != '2') || y1 < '0' || y1 > '9' || y2 < '0' || y2 > '9' || y3 < '0' || y3 > '9') {
            return false;
        }
        if (M0 == '0') {
            if (M1 < '1' || M1 > '9') {
                return false;
            }
        } else if (M0 != '1') {
            return false;
        } else {
            if (!(M1 == '0' || M1 == '1' || M1 == '2')) {
                return false;
            }
        }
        if (d0 == 48) {
            if (d1 < 49 || d1 > 57) {
                return false;
            }
        } else if (d0 == 49 || d0 == 50) {
            if (d1 < 48 || d1 > 57) {
                return false;
            }
        } else if (d0 != 51) {
            return false;
        } else {
            if (d1 == 48 || d1 == 49) {
                return true;
            }
            return false;
        }
        return true;
    }

    public boolean T6() {
        return this.T9 == this.TO.length() || (this.Tn == 26 && this.T9 + 1 == this.TO.length());
    }
}
