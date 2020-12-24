package com.T.T.Tr;

import android.support.v4.widget.ExploreByTouchHelper;
import com.T.T.T;
import com.T.T.Tn;
import java.io.Closeable;
import java.lang.ref.SoftReference;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

/* compiled from: Proguard */
public abstract class T9 implements Closeable {
    protected static final int[] TF = new int[103];
    protected static final char[] Te = ("\"" + T.f128T + "\":\"").toCharArray();
    private static final ThreadLocal<SoftReference<char[]>> Tj = new ThreadLocal<>();
    protected static boolean[] Tq = new boolean[256];

    /* renamed from: T  reason: collision with root package name */
    protected int f176T;
    protected int T5;
    protected TE T6 = TE.f177T;
    protected int T9;
    protected int TE;
    protected char[] TZ;
    protected Calendar Th = null;
    protected int Tk;
    protected char Tn;
    protected int Tr;
    protected boolean Tv;
    protected int Ty = T.Tr;

    public abstract String T(int i, int i2, int i3, Th th);

    /* access modifiers changed from: protected */
    public abstract void T(int i, int i2, char[] cArr);

    /* access modifiers changed from: protected */
    public abstract void T(int i, char[] cArr, int i2, int i3);

    public abstract boolean T6();

    public abstract byte[] TO();

    public abstract String Tf();

    public abstract String Th();

    public abstract char Tq();

    public abstract char Ty(int i);

    static {
        Tq[32] = true;
        Tq[10] = true;
        Tq[13] = true;
        Tq[9] = true;
        Tq[12] = true;
        Tq[8] = true;
        for (int i = 48; i <= 57; i++) {
            TF[i] = i - 48;
        }
        for (int i2 = 97; i2 <= 102; i2++) {
            TF[i2] = (i2 - 97) + 10;
        }
        for (int i3 = 65; i3 <= 70; i3++) {
            TF[i3] = (i3 - 65) + 10;
        }
    }

    public T9() {
        SoftReference<char[]> sbufRef = Tj.get();
        if (sbufRef != null) {
            this.TZ = sbufRef.get();
            Tj.set((Object) null);
        }
        if (this.TZ == null) {
            this.TZ = new char[64];
        }
    }

    public final void T() {
        this.TE = 0;
        while (true) {
            this.Tr = this.T9;
            if (this.Tn == '\"') {
                Tj();
                return;
            } else if (this.Tn == ',') {
                Tq();
                this.f176T = 16;
                return;
            } else if (this.Tn >= '0' && this.Tn <= '9') {
                Tb();
                return;
            } else if (this.Tn == '-') {
                Tb();
                return;
            } else {
                switch (this.Tn) {
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                        Tq();
                    case '\'':
                        if (!T(Tn.AllowSingleQuotes)) {
                            throw new Tn("Feature.AllowSingleQuotes is false");
                        }
                        T0();
                        return;
                    case '(':
                        Tq();
                        this.f176T = 10;
                        return;
                    case ')':
                        Tq();
                        this.f176T = 11;
                        return;
                    case ':':
                        Tq();
                        this.f176T = 17;
                        return;
                    case 'S':
                        TV();
                        return;
                    case 'T':
                        TG();
                        return;
                    case '[':
                        Tq();
                        this.f176T = 14;
                        return;
                    case ']':
                        Tq();
                        this.f176T = 15;
                        return;
                    case 'f':
                        Tt();
                        return;
                    case 'n':
                        Tu();
                        return;
                    case 't':
                        Ts();
                        return;
                    case '{':
                        Tq();
                        this.f176T = 12;
                        return;
                    case '}':
                        Tq();
                        this.f176T = 13;
                        return;
                    default:
                        if (!T6()) {
                            this.f176T = 1;
                            Tq();
                            return;
                        } else if (this.f176T == 20) {
                            throw new Tn("EOF error");
                        } else {
                            this.f176T = 20;
                            int i = this.Tk;
                            this.T9 = i;
                            this.Tr = i;
                            return;
                        }
                }
            }
        }
    }

    public final void T(int expect) {
        this.TE = 0;
        while (true) {
            switch (expect) {
                case 2:
                    if (this.Tn >= '0' && this.Tn <= '9') {
                        this.Tr = this.T9;
                        Tb();
                        return;
                    } else if (this.Tn == '\"') {
                        this.Tr = this.T9;
                        Tj();
                        return;
                    } else if (this.Tn == '[') {
                        this.f176T = 14;
                        Tq();
                        return;
                    } else if (this.Tn == '{') {
                        this.f176T = 12;
                        Tq();
                        return;
                    }
                    break;
                case 4:
                    if (this.Tn == '\"') {
                        this.Tr = this.T9;
                        Tj();
                        return;
                    } else if (this.Tn >= '0' && this.Tn <= '9') {
                        this.Tr = this.T9;
                        Tb();
                        return;
                    } else if (this.Tn == '[') {
                        this.f176T = 14;
                        Tq();
                        return;
                    } else if (this.Tn == '{') {
                        this.f176T = 12;
                        Tq();
                        return;
                    }
                    break;
                case 12:
                    if (this.Tn == '{') {
                        this.f176T = 12;
                        Tq();
                        return;
                    } else if (this.Tn == '[') {
                        this.f176T = 14;
                        Tq();
                        return;
                    }
                    break;
                case 14:
                    if (this.Tn == '[') {
                        this.f176T = 14;
                        Tq();
                        return;
                    } else if (this.Tn == '{') {
                        this.f176T = 12;
                        Tq();
                        return;
                    }
                    break;
                case 15:
                    if (this.Tn == ']') {
                        this.f176T = 15;
                        Tq();
                        return;
                    }
                    break;
                case 16:
                    if (this.Tn == ',') {
                        this.f176T = 16;
                        Tq();
                        return;
                    } else if (this.Tn == '}') {
                        this.f176T = 13;
                        Tq();
                        return;
                    } else if (this.Tn == ']') {
                        this.f176T = 15;
                        Tq();
                        return;
                    } else if (this.Tn == 26) {
                        this.f176T = 20;
                        return;
                    }
                    break;
                case 18:
                    Tr();
                    return;
                case 20:
                    break;
            }
            if (this.Tn == 26) {
                this.f176T = 20;
                return;
            }
            if (this.Tn == ' ' || this.Tn == 10 || this.Tn == 13 || this.Tn == 9 || this.Tn == 12 || this.Tn == 8) {
                Tq();
            } else {
                T();
                return;
            }
        }
    }

    public final void Tr() {
        while (Tr(this.Tn)) {
            Tq();
        }
        if (this.Tn == '_' || Character.isLetter(this.Tn)) {
            TD();
        } else {
            T();
        }
    }

    public final void Ty() {
        this.TE = 0;
        while (this.Tn != ':') {
            if (this.Tn == ' ' || this.Tn == 10 || this.Tn == 13 || this.Tn == 9 || this.Tn == 12 || this.Tn == 8) {
                Tq();
            } else {
                throw new Tn("not match ':' - " + this.Tn);
            }
        }
        Tq();
        T();
    }

    public final int Tn() {
        return this.f176T;
    }

    public final String T9() {
        return TZ.T(this.f176T);
    }

    public final int Tk() {
        return this.Tr;
    }

    public final int TZ() {
        return this.T9;
    }

    public final Number TE() throws NumberFormatException {
        long limit;
        int i;
        long result = 0;
        boolean negative = false;
        int i2 = this.T5;
        int max = this.T5 + this.TE;
        char type = ' ';
        switch (Ty(max - 1)) {
            case 'B':
                max--;
                type = 'B';
                break;
            case 'L':
                max--;
                type = 'L';
                break;
            case 'S':
                max--;
                type = 'S';
                break;
        }
        if (Ty(this.T5) == '-') {
            negative = true;
            limit = Long.MIN_VALUE;
            i = i2 + 1;
        } else {
            limit = -9223372036854775807L;
            i = i2;
        }
        if (negative) {
        }
        if (i < max) {
            result = (long) (-TF[Ty(i)]);
            i++;
        }
        while (i < max) {
            int i3 = i + 1;
            int digit = TF[Ty(i)];
            if (result < -922337203685477580L) {
                return new BigInteger(Th());
            }
            long result2 = result * 10;
            if (result2 < ((long) digit) + limit) {
                return new BigInteger(Th());
            }
            result = result2 - ((long) digit);
            i = i3;
        }
        if (!negative) {
            long result3 = -result;
            if (result3 > 2147483647L || type == 'L') {
                int i4 = i;
                return Long.valueOf(result3);
            } else if (type == 'S') {
                int i5 = i;
                return Short.valueOf((short) ((int) result3));
            } else if (type == 'B') {
                int i6 = i;
                return Byte.valueOf((byte) ((int) result3));
            } else {
                int i7 = i;
                return Integer.valueOf((int) result3);
            }
        } else if (i <= this.T5 + 1) {
            throw new NumberFormatException(Th());
        } else if (result < -2147483648L || type == 'L') {
            int i8 = i;
            return Long.valueOf(result);
        } else {
            int i9 = i;
            return Integer.valueOf((int) result);
        }
    }

    public final void Tr(int expect) {
        this.TE = 0;
        while (this.Tn != ':') {
            if (Tr(this.Tn)) {
                Tq();
            } else {
                throw new Tn("not match ':', actual " + this.Tn);
            }
        }
        Tq();
        while (true) {
            if (expect == 2) {
                if (this.Tn >= '0' && this.Tn <= '9') {
                    this.Tr = this.T9;
                    Tb();
                    return;
                } else if (this.Tn == '\"') {
                    this.Tr = this.T9;
                    Tj();
                    return;
                }
            } else if (expect == 4) {
                if (this.Tn == '\"') {
                    this.Tr = this.T9;
                    Tj();
                    return;
                } else if (this.Tn >= '0' && this.Tn <= '9') {
                    this.Tr = this.T9;
                    Tb();
                    return;
                }
            } else if (expect == 12) {
                if (this.Tn == '{') {
                    this.f176T = 12;
                    Tq();
                    return;
                } else if (this.Tn == '[') {
                    this.f176T = 14;
                    Tq();
                    return;
                }
            } else if (expect == 14) {
                if (this.Tn == '[') {
                    this.f176T = 14;
                    Tq();
                    return;
                } else if (this.Tn == '{') {
                    this.f176T = 12;
                    Tq();
                    return;
                }
            }
            if (Tr(this.Tn)) {
                Tq();
            } else {
                T();
                return;
            }
        }
    }

    public float T5() {
        return Float.parseFloat(Th());
    }

    public double Tv() {
        return Double.parseDouble(Th());
    }

    public final boolean T(Tn feature) {
        return Tn.T(this.Ty, feature);
    }

    public final char Te() {
        return this.Tn;
    }

    public final String T(Th symbolTable) {
        Tx();
        if (this.Tn == '\"') {
            return T(symbolTable, '\"');
        }
        if (this.Tn == '\'') {
            if (T(Tn.AllowSingleQuotes)) {
                return T(symbolTable, '\'');
            }
            throw new Tn("syntax error");
        } else if (this.Tn == '}') {
            Tq();
            this.f176T = 13;
            return null;
        } else if (this.Tn == ',') {
            Tq();
            this.f176T = 16;
            return null;
        } else if (this.Tn == 26) {
            this.f176T = 20;
            return null;
        } else if (T(Tn.AllowUnQuotedFieldNames)) {
            return Tr(symbolTable);
        } else {
            throw new Tn("syntax error");
        }
    }

    public final String T(Th symbolTable, char quote) {
        int hash = 0;
        this.T5 = this.T9;
        this.TE = 0;
        boolean hasSpecial = false;
        while (true) {
            int i = this.T9 + 1;
            this.T9 = i;
            char chLocal = Ty(i);
            if (chLocal == quote) {
                this.f176T = 4;
                Tq();
                if (!hasSpecial) {
                    return T(this.T5 + 1, this.TE, hash, symbolTable);
                }
                return symbolTable.T(this.TZ, 0, this.TE, hash);
            } else if (chLocal == 26) {
                throw new Tn("unclosed.str");
            } else if (chLocal == '\\') {
                if (!hasSpecial) {
                    hasSpecial = true;
                    if (this.TE >= this.TZ.length) {
                        int newCapcity = this.TZ.length * 2;
                        if (this.TE > newCapcity) {
                            newCapcity = this.TE;
                        }
                        char[] newsbuf = new char[newCapcity];
                        System.arraycopy(this.TZ, 0, newsbuf, 0, this.TZ.length);
                        this.TZ = newsbuf;
                    }
                    T(this.T5 + 1, this.TZ, 0, this.TE);
                }
                int i2 = this.T9 + 1;
                this.T9 = i2;
                char chLocal2 = Ty(i2);
                switch (chLocal2) {
                    case '\"':
                        hash = (hash * 31) + 34;
                        T('\"');
                        break;
                    case '\'':
                        hash = (hash * 31) + 39;
                        T('\'');
                        break;
                    case '/':
                        hash = (hash * 31) + 47;
                        T('/');
                        break;
                    case '0':
                        hash = (hash * 31) + chLocal2;
                        T(0);
                        break;
                    case '1':
                        hash = (hash * 31) + chLocal2;
                        T(1);
                        break;
                    case '2':
                        hash = (hash * 31) + chLocal2;
                        T(2);
                        break;
                    case '3':
                        hash = (hash * 31) + chLocal2;
                        T(3);
                        break;
                    case '4':
                        hash = (hash * 31) + chLocal2;
                        T(4);
                        break;
                    case '5':
                        hash = (hash * 31) + chLocal2;
                        T(5);
                        break;
                    case '6':
                        hash = (hash * 31) + chLocal2;
                        T(6);
                        break;
                    case '7':
                        hash = (hash * 31) + chLocal2;
                        T(7);
                        break;
                    case 'F':
                    case 'f':
                        hash = (hash * 31) + 12;
                        T(12);
                        break;
                    case '\\':
                        hash = (hash * 31) + 92;
                        T('\\');
                        break;
                    case 'b':
                        hash = (hash * 31) + 8;
                        T(8);
                        break;
                    case 'n':
                        hash = (hash * 31) + 10;
                        T(10);
                        break;
                    case 'r':
                        hash = (hash * 31) + 13;
                        T(13);
                        break;
                    case 't':
                        hash = (hash * 31) + 9;
                        T(9);
                        break;
                    case 'u':
                        int i3 = this.T9 + 1;
                        this.T9 = i3;
                        char c1 = Ty(i3);
                        int i4 = this.T9 + 1;
                        this.T9 = i4;
                        char c2 = Ty(i4);
                        int i5 = this.T9 + 1;
                        this.T9 = i5;
                        char c3 = Ty(i5);
                        int i6 = this.T9 + 1;
                        this.T9 = i6;
                        int val = Integer.parseInt(new String(new char[]{c1, c2, c3, Ty(i6)}), 16);
                        hash = (hash * 31) + val;
                        T((char) val);
                        break;
                    case 'v':
                        hash = (hash * 31) + 11;
                        T(11);
                        break;
                    case 'x':
                        int i7 = this.T9 + 1;
                        this.T9 = i7;
                        char x1 = Ty(i7);
                        this.Tn = x1;
                        int i8 = this.T9 + 1;
                        this.T9 = i8;
                        char x2 = Ty(i8);
                        this.Tn = x2;
                        char x_char = (char) ((TF[x1] * 16) + TF[x2]);
                        hash = (hash * 31) + x_char;
                        T(x_char);
                        break;
                    default:
                        this.Tn = chLocal2;
                        throw new Tn("unclosed.str.lit");
                }
            } else {
                hash = (hash * 31) + chLocal;
                if (!hasSpecial) {
                    this.TE++;
                } else {
                    if (this.TE == this.TZ.length) {
                        T(chLocal);
                    } else {
                        char[] cArr = this.TZ;
                        int i9 = this.TE;
                        this.TE = i9 + 1;
                        cArr[i9] = chLocal;
                    }
                }
            }
        }
    }

    public final void TF() {
        this.TE = 0;
    }

    public final String Tr(Th symbolTable) {
        boolean[] firstIdentifierFlags = Tr.Tr;
        char first = this.Tn;
        if (!(this.Tn >= firstIdentifierFlags.length || firstIdentifierFlags[first])) {
            throw new Tn("illegal identifier : " + this.Tn);
        }
        boolean[] identifierFlags = Tr.Ty;
        int hash = first;
        this.T5 = this.T9;
        this.TE = 1;
        while (true) {
            int i = this.T9 + 1;
            this.T9 = i;
            char chLocal = Ty(i);
            if (chLocal < identifierFlags.length && !identifierFlags[chLocal]) {
                break;
            }
            hash = (hash * 31) + chLocal;
            this.TE++;
        }
        this.Tn = Ty(this.T9);
        this.f176T = 18;
        if (this.TE == 4 && hash == 3392903 && Ty(this.T5) == 'n' && Ty(this.T5 + 1) == 'u' && Ty(this.T5 + 2) == 'l' && Ty(this.T5 + 3) == 'l') {
            return null;
        }
        return T(this.T5, this.TE, hash, symbolTable);
    }

    public final void Tj() {
        this.T5 = this.T9;
        this.Tv = false;
        while (true) {
            int i = this.T9 + 1;
            this.T9 = i;
            char ch = Ty(i);
            if (ch == '\"') {
                this.f176T = 4;
                int i2 = this.T9 + 1;
                this.T9 = i2;
                this.Tn = Ty(i2);
                return;
            } else if (ch == 26) {
                throw new Tn("unclosed string : " + ch);
            } else if (ch == '\\') {
                if (!this.Tv) {
                    this.Tv = true;
                    if (this.TE >= this.TZ.length) {
                        int newCapcity = this.TZ.length * 2;
                        if (this.TE > newCapcity) {
                            newCapcity = this.TE;
                        }
                        char[] newsbuf = new char[newCapcity];
                        System.arraycopy(this.TZ, 0, newsbuf, 0, this.TZ.length);
                        this.TZ = newsbuf;
                    }
                    T(this.T5 + 1, this.TE, this.TZ);
                }
                int i3 = this.T9 + 1;
                this.T9 = i3;
                char ch2 = Ty(i3);
                switch (ch2) {
                    case '\"':
                        T('\"');
                        break;
                    case '\'':
                        T('\'');
                        break;
                    case '/':
                        T('/');
                        break;
                    case '0':
                        T(0);
                        break;
                    case '1':
                        T(1);
                        break;
                    case '2':
                        T(2);
                        break;
                    case '3':
                        T(3);
                        break;
                    case '4':
                        T(4);
                        break;
                    case '5':
                        T(5);
                        break;
                    case '6':
                        T(6);
                        break;
                    case '7':
                        T(7);
                        break;
                    case 'F':
                    case 'f':
                        T(12);
                        break;
                    case '\\':
                        T('\\');
                        break;
                    case 'b':
                        T(8);
                        break;
                    case 'n':
                        T(10);
                        break;
                    case 'r':
                        T(13);
                        break;
                    case 't':
                        T(9);
                        break;
                    case 'u':
                        int i4 = this.T9 + 1;
                        this.T9 = i4;
                        char u1 = Ty(i4);
                        int i5 = this.T9 + 1;
                        this.T9 = i5;
                        char u2 = Ty(i5);
                        int i6 = this.T9 + 1;
                        this.T9 = i6;
                        char u3 = Ty(i6);
                        int i7 = this.T9 + 1;
                        this.T9 = i7;
                        T((char) Integer.parseInt(new String(new char[]{u1, u2, u3, Ty(i7)}), 16));
                        break;
                    case 'v':
                        T(11);
                        break;
                    case 'x':
                        int i8 = this.T9 + 1;
                        this.T9 = i8;
                        char x1 = Ty(i8);
                        int i9 = this.T9 + 1;
                        this.T9 = i9;
                        T((char) ((TF[x1] * 16) + TF[Ty(i9)]));
                        break;
                    default:
                        this.Tn = ch2;
                        throw new Tn("unclosed string : " + ch2);
                }
            } else if (!this.Tv) {
                this.TE++;
            } else if (this.TE == this.TZ.length) {
                T(ch);
            } else {
                char[] cArr = this.TZ;
                int i10 = this.TE;
                this.TE = i10 + 1;
                cArr[i10] = ch;
            }
        }
    }

    public Calendar TB() {
        return this.Th;
    }

    public final int TK() {
        int limit;
        int i;
        int i2;
        int result = 0;
        boolean negative = false;
        int i3 = this.T5;
        int max = this.T5 + this.TE;
        if (Ty(this.T5) == '-') {
            negative = true;
            limit = ExploreByTouchHelper.INVALID_ID;
            i = i3 + 1;
        } else {
            limit = -2147483647;
            i = i3;
        }
        if (negative) {
        }
        if (i < max) {
            result = -TF[Ty(i)];
            i++;
        }
        while (true) {
            if (i >= max) {
                i2 = i;
                break;
            }
            i2 = i + 1;
            char chLocal = Ty(i);
            if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B') {
                break;
            }
            int digit = TF[chLocal];
            if (result < -214748364) {
                throw new NumberFormatException(Th());
            }
            int result2 = result * 10;
            if (result2 < limit + digit) {
                throw new NumberFormatException(Th());
            }
            result = result2 - digit;
            i = i2;
        }
        if (!negative) {
            return -result;
        }
        if (i2 > this.T5 + 1) {
            return result;
        }
        throw new NumberFormatException(Th());
    }

    public void close() {
        if (this.TZ.length <= 8192) {
            Tj.set(new SoftReference(this.TZ));
        }
        this.TZ = null;
    }

    public final boolean TN() {
        if (this.TE == 4 && Ty(this.T5 + 1) == '$' && Ty(this.T5 + 2) == 'r' && Ty(this.T5 + 3) == 'e' && Ty(this.T5 + 4) == 'f') {
            return true;
        }
        return false;
    }

    public final void Ts() {
        if (this.Tn != 't') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn != 'r') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn != 'u') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn != 'e') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn == ' ' || this.Tn == ',' || this.Tn == '}' || this.Tn == ']' || this.Tn == 10 || this.Tn == 13 || this.Tn == 9 || this.Tn == 26 || this.Tn == 12 || this.Tn == 8) {
            this.f176T = 6;
            return;
        }
        throw new Tn("scan true error");
    }

    public final void TG() {
        if (this.Tn != 'T') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn != 'r') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn != 'e') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn != 'e') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn != 'S') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn != 'e') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn != 't') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn == ' ' || this.Tn == 10 || this.Tn == 13 || this.Tn == 9 || this.Tn == 12 || this.Tn == 8 || this.Tn == '[' || this.Tn == '(') {
            this.f176T = 22;
            return;
        }
        throw new Tn("scan set error");
    }

    public final void Tu() {
        if (this.Tn != 'n') {
            throw new Tn("error parse null or new");
        }
        Tq();
        if (this.Tn == 'u') {
            Tq();
            if (this.Tn != 'l') {
                throw new Tn("error parse true");
            }
            Tq();
            if (this.Tn != 'l') {
                throw new Tn("error parse true");
            }
            Tq();
            if (this.Tn == ' ' || this.Tn == ',' || this.Tn == '}' || this.Tn == ']' || this.Tn == 10 || this.Tn == 13 || this.Tn == 9 || this.Tn == 26 || this.Tn == 12 || this.Tn == 8) {
                this.f176T = 8;
                return;
            }
            throw new Tn("scan true error");
        } else if (this.Tn != 'e') {
            throw new Tn("error parse e");
        } else {
            Tq();
            if (this.Tn != 'w') {
                throw new Tn("error parse w");
            }
            Tq();
            if (this.Tn == ' ' || this.Tn == ',' || this.Tn == '}' || this.Tn == ']' || this.Tn == 10 || this.Tn == 13 || this.Tn == 9 || this.Tn == 26 || this.Tn == 12 || this.Tn == 8) {
                this.f176T = 9;
                return;
            }
            throw new Tn("scan true error");
        }
    }

    public final void Tt() {
        if (this.Tn != 'f') {
            throw new Tn("error parse false");
        }
        Tq();
        if (this.Tn != 'a') {
            throw new Tn("error parse false");
        }
        Tq();
        if (this.Tn != 'l') {
            throw new Tn("error parse false");
        }
        Tq();
        if (this.Tn != 's') {
            throw new Tn("error parse false");
        }
        Tq();
        if (this.Tn != 'e') {
            throw new Tn("error parse false");
        }
        Tq();
        if (this.Tn == ' ' || this.Tn == ',' || this.Tn == '}' || this.Tn == ']' || this.Tn == 10 || this.Tn == 13 || this.Tn == 9 || this.Tn == 26 || this.Tn == 12 || this.Tn == 8) {
            this.f176T = 7;
            return;
        }
        throw new Tn("scan false error");
    }

    public final void TD() {
        this.T5 = this.T9 - 1;
        this.Tv = false;
        do {
            this.TE++;
            Tq();
        } while (Character.isLetterOrDigit(this.Tn));
        Integer tok = this.T6.T(Tf());
        if (tok != null) {
            this.f176T = tok.intValue();
        } else {
            this.f176T = 18;
        }
    }

    public final boolean TA() {
        int i = 0;
        while (true) {
            char chLocal = Ty(i);
            if (chLocal == 26) {
                return true;
            }
            if (!Tr(chLocal)) {
                return false;
            }
            i++;
        }
    }

    public final void Tx() {
        while (Tq[this.Tn]) {
            Tq();
        }
    }

    public final void T0() {
        this.T5 = this.T9;
        this.Tv = false;
        while (true) {
            int i = this.T9 + 1;
            this.T9 = i;
            char chLocal = Ty(i);
            if (chLocal == '\'') {
                this.f176T = 4;
                Tq();
                return;
            } else if (chLocal == 26) {
                throw new Tn("unclosed single-quote string");
            } else if (chLocal == '\\') {
                if (!this.Tv) {
                    this.Tv = true;
                    if (this.TE > this.TZ.length) {
                        char[] newsbuf = new char[(this.TE * 2)];
                        System.arraycopy(this.TZ, 0, newsbuf, 0, this.TZ.length);
                        this.TZ = newsbuf;
                    }
                    T(this.T5 + 1, this.TE, this.TZ);
                }
                int i2 = this.T9 + 1;
                this.T9 = i2;
                char chLocal2 = Ty(i2);
                switch (chLocal2) {
                    case '\"':
                        T('\"');
                        break;
                    case '\'':
                        T('\'');
                        break;
                    case '/':
                        T('/');
                        break;
                    case '0':
                        T(0);
                        break;
                    case '1':
                        T(1);
                        break;
                    case '2':
                        T(2);
                        break;
                    case '3':
                        T(3);
                        break;
                    case '4':
                        T(4);
                        break;
                    case '5':
                        T(5);
                        break;
                    case '6':
                        T(6);
                        break;
                    case '7':
                        T(7);
                        break;
                    case 'F':
                    case 'f':
                        T(12);
                        break;
                    case '\\':
                        T('\\');
                        break;
                    case 'b':
                        T(8);
                        break;
                    case 'n':
                        T(10);
                        break;
                    case 'r':
                        T(13);
                        break;
                    case 't':
                        T(9);
                        break;
                    case 'u':
                        int i3 = this.T9 + 1;
                        this.T9 = i3;
                        char c1 = Ty(i3);
                        int i4 = this.T9 + 1;
                        this.T9 = i4;
                        char c2 = Ty(i4);
                        int i5 = this.T9 + 1;
                        this.T9 = i5;
                        char c3 = Ty(i5);
                        int i6 = this.T9 + 1;
                        this.T9 = i6;
                        T((char) Integer.parseInt(new String(new char[]{c1, c2, c3, Ty(i6)}), 16));
                        break;
                    case 'v':
                        T(11);
                        break;
                    case 'x':
                        int i7 = this.T9 + 1;
                        this.T9 = i7;
                        char x1 = Ty(i7);
                        int i8 = this.T9 + 1;
                        this.T9 = i8;
                        T((char) ((TF[x1] * 16) + TF[Ty(i8)]));
                        break;
                    default:
                        this.Tn = chLocal2;
                        throw new Tn("unclosed single-quote string");
                }
            } else if (!this.Tv) {
                this.TE++;
            } else if (this.TE == this.TZ.length) {
                T(chLocal);
            } else {
                char[] cArr = this.TZ;
                int i9 = this.TE;
                this.TE = i9 + 1;
                cArr[i9] = chLocal;
            }
        }
    }

    public final void TV() {
        if (this.Tn != 'S') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn != 'e') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn != 't') {
            throw new Tn("error parse true");
        }
        Tq();
        if (this.Tn == ' ' || this.Tn == 10 || this.Tn == 13 || this.Tn == 9 || this.Tn == 12 || this.Tn == 8 || this.Tn == '[' || this.Tn == '(') {
            this.f176T = 21;
            return;
        }
        throw new Tn("scan set error");
    }

    /* access modifiers changed from: protected */
    public final void T(char ch) {
        if (this.TE == this.TZ.length) {
            char[] newsbuf = new char[(this.TZ.length * 2)];
            System.arraycopy(this.TZ, 0, newsbuf, 0, this.TZ.length);
            this.TZ = newsbuf;
        }
        char[] cArr = this.TZ;
        int i = this.TE;
        this.TE = i + 1;
        cArr[i] = ch;
    }

    public final void Tb() {
        this.T5 = this.T9;
        if (this.Tn == '-') {
            this.TE++;
            Tq();
        }
        while (this.Tn >= '0' && this.Tn <= '9') {
            this.TE++;
            Tq();
        }
        boolean isDouble = false;
        if (this.Tn == '.') {
            this.TE++;
            Tq();
            isDouble = true;
            while (this.Tn >= '0' && this.Tn <= '9') {
                this.TE++;
                Tq();
            }
        }
        if (this.Tn == 'L') {
            this.TE++;
            Tq();
        } else if (this.Tn == 'S') {
            this.TE++;
            Tq();
        } else if (this.Tn == 'B') {
            this.TE++;
            Tq();
        } else if (this.Tn == 'F') {
            this.TE++;
            Tq();
            isDouble = true;
        } else if (this.Tn == 'D') {
            this.TE++;
            Tq();
            isDouble = true;
        } else if (this.Tn == 'e' || this.Tn == 'E') {
            this.TE++;
            Tq();
            if (this.Tn == '+' || this.Tn == '-') {
                this.TE++;
                Tq();
            }
            while (this.Tn >= '0' && this.Tn <= '9') {
                this.TE++;
                Tq();
            }
            if (this.Tn == 'D' || this.Tn == 'F') {
                this.TE++;
                Tq();
            }
            isDouble = true;
        }
        if (isDouble) {
            this.f176T = 3;
        } else {
            this.f176T = 2;
        }
    }

    public final long TM() throws NumberFormatException {
        long limit;
        int i;
        int i2;
        long result = 0;
        boolean negative = false;
        int i3 = this.T5;
        int max = this.T5 + this.TE;
        if (Ty(this.T5) == '-') {
            negative = true;
            limit = Long.MIN_VALUE;
            i = i3 + 1;
        } else {
            limit = -9223372036854775807L;
            i = i3;
        }
        if (negative) {
        }
        if (i < max) {
            result = (long) (-TF[Ty(i)]);
            i++;
        }
        while (true) {
            if (i >= max) {
                i2 = i;
                break;
            }
            i2 = i + 1;
            char chLocal = Ty(i);
            if (chLocal == 'L' || chLocal == 'S' || chLocal == 'B') {
                break;
            }
            int digit = TF[chLocal];
            if (result < -922337203685477580L) {
                throw new NumberFormatException(Th());
            }
            long result2 = result * 10;
            if (result2 < ((long) digit) + limit) {
                throw new NumberFormatException(Th());
            }
            result = result2 - ((long) digit);
            i = i2;
        }
        if (!negative) {
            return -result;
        }
        if (i2 > this.T5 + 1) {
            return result;
        }
        throw new NumberFormatException(Th());
    }

    public final Number T(boolean decimal) {
        char chLocal = Ty((this.T5 + this.TE) - 1);
        if (chLocal == 'F') {
            return Float.valueOf(Float.parseFloat(Th()));
        }
        if (chLocal == 'D') {
            return Double.valueOf(Double.parseDouble(Th()));
        }
        if (decimal) {
            return TX();
        }
        return Double.valueOf(Tv());
    }

    public final BigDecimal TX() {
        return new BigDecimal(Th());
    }

    public final Number TP() {
        char type = Ty((this.T5 + this.TE) - 1);
        String str = Th();
        switch (type) {
            case 'D':
                return Double.valueOf(Double.parseDouble(str));
            case 'F':
                return Float.valueOf(Float.parseFloat(str));
            default:
                return new BigDecimal(str);
        }
    }

    public static final boolean Tr(char ch) {
        return ch == ' ' || ch == 10 || ch == 13 || ch == 9 || ch == 12 || ch == 8;
    }
}
