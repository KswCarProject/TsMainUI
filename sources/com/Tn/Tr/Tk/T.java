package com.Tn.Tr.Tk;

import com.Tn.Tr.T.T;
import com.Tr.T.T.T9;
import com.Tr.T.T.TZ;
import com.Tr.T.T.Tn;
import com.lgb.canmodule.Can;
import java.io.IOException;

/* compiled from: Proguard */
public interface T {

    /* compiled from: Proguard */
    public static final class Ty extends T9 {
        private static volatile Ty[] TN;
        public String T5;
        public Integer T6;
        public String[] T9;
        public String TB;
        public Integer TE;
        public Boolean TF;
        public Integer TK;
        public Long TO;
        public Integer TZ;
        public String[] Te;
        public String Th;
        public String[] Tj;
        public String Tk;
        public String Tn;
        public Integer Tq;
        public Integer Tr;
        public String Tv;
        public String Ty;

        public static Ty[] Tn() {
            if (TN == null) {
                synchronized (com.Tr.T.T.Ty.f241T) {
                    if (TN == null) {
                        TN = new Ty[0];
                    }
                }
            }
            return TN;
        }

        public Ty() {
            T9();
        }

        public Ty T9() {
            this.Tr = null;
            this.Ty = null;
            this.Tn = null;
            this.T9 = TZ.Tk;
            this.Tk = null;
            this.TZ = null;
            this.TE = null;
            this.T5 = null;
            this.Tv = null;
            this.Th = null;
            this.T6 = null;
            this.Te = TZ.Tk;
            this.Tq = null;
            this.TF = null;
            this.Tj = TZ.Tk;
            this.TB = null;
            this.TK = null;
            this.TO = null;
            this.f238T = -1;
            return this;
        }

        public void T(com.Tr.T.T.Tr output) throws IOException {
            if (this.Tr != null) {
                output.T(1, this.Tr.intValue());
            }
            if (this.Ty != null) {
                output.T(2, this.Ty);
            }
            if (this.Tn != null) {
                output.T(3, this.Tn);
            }
            if (this.T9 != null && this.T9.length > 0) {
                for (String element : this.T9) {
                    if (element != null) {
                        output.T(4, element);
                    }
                }
            }
            if (this.Tk != null) {
                output.T(5, this.Tk);
            }
            if (this.TZ != null) {
                output.Tr(6, this.TZ.intValue());
            }
            if (this.TE != null) {
                output.Tr(7, this.TE.intValue());
            }
            if (this.T5 != null) {
                output.T(8, this.T5);
            }
            if (this.Tv != null) {
                output.T(9, this.Tv);
            }
            if (this.Th != null) {
                output.T(10, this.Th);
            }
            if (this.T6 != null) {
                output.T(11, this.T6.intValue());
            }
            if (this.Te != null && this.Te.length > 0) {
                for (String element2 : this.Te) {
                    if (element2 != null) {
                        output.T(13, element2);
                    }
                }
            }
            if (this.Tq != null) {
                output.Tr(14, this.Tq.intValue());
            }
            if (this.TF != null) {
                output.T(19, this.TF.booleanValue());
            }
            if (this.Tj != null && this.Tj.length > 0) {
                for (String element3 : this.Tj) {
                    if (element3 != null) {
                        output.T(20, element3);
                    }
                }
            }
            if (this.TB != null) {
                output.T(21, this.TB);
            }
            if (this.TK != null) {
                output.Tr(22, this.TK.intValue());
            }
            if (this.TO != null) {
                output.T(23, this.TO.longValue());
            }
            super.T(output);
        }

        /* access modifiers changed from: protected */
        public int Ty() {
            int size = super.Ty();
            if (this.Tr != null) {
                size += com.Tr.T.T.Tr.Ty(1, this.Tr.intValue());
            }
            if (this.Ty != null) {
                size += com.Tr.T.T.Tr.Tr(2, this.Ty);
            }
            if (this.Tn != null) {
                size += com.Tr.T.T.Tr.Tr(3, this.Tn);
            }
            if (this.T9 != null && this.T9.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.T9) {
                    if (element != null) {
                        dataCount++;
                        dataSize += com.Tr.T.T.Tr.Tr(element);
                    }
                }
                size = size + dataSize + (dataCount * 1);
            }
            if (this.Tk != null) {
                size += com.Tr.T.T.Tr.Tr(5, this.Tk);
            }
            if (this.TZ != null) {
                size += com.Tr.T.T.Tr.Tn(6, this.TZ.intValue());
            }
            if (this.TE != null) {
                size += com.Tr.T.T.Tr.Tn(7, this.TE.intValue());
            }
            if (this.T5 != null) {
                size += com.Tr.T.T.Tr.Tr(8, this.T5);
            }
            if (this.Tv != null) {
                size += com.Tr.T.T.Tr.Tr(9, this.Tv);
            }
            if (this.Th != null) {
                size += com.Tr.T.T.Tr.Tr(10, this.Th);
            }
            if (this.T6 != null) {
                size += com.Tr.T.T.Tr.Ty(11, this.T6.intValue());
            }
            if (this.Te != null && this.Te.length > 0) {
                int dataCount2 = 0;
                int dataSize2 = 0;
                for (String element2 : this.Te) {
                    if (element2 != null) {
                        dataCount2++;
                        dataSize2 += com.Tr.T.T.Tr.Tr(element2);
                    }
                }
                size = size + dataSize2 + (dataCount2 * 1);
            }
            if (this.Tq != null) {
                size += com.Tr.T.T.Tr.Tn(14, this.Tq.intValue());
            }
            if (this.TF != null) {
                size += com.Tr.T.T.Tr.Tr(19, this.TF.booleanValue());
            }
            if (this.Tj != null && this.Tj.length > 0) {
                int dataCount3 = 0;
                int dataSize3 = 0;
                for (String element3 : this.Tj) {
                    if (element3 != null) {
                        dataCount3++;
                        dataSize3 += com.Tr.T.T.Tr.Tr(element3);
                    }
                }
                size = size + dataSize3 + (dataCount3 * 2);
            }
            if (this.TB != null) {
                size += com.Tr.T.T.Tr.Tr(21, this.TB);
            }
            if (this.TK != null) {
                size += com.Tr.T.T.Tr.Tn(22, this.TK.intValue());
            }
            if (this.TO != null) {
                return size + com.Tr.T.T.Tr.Tr(23, this.TO.longValue());
            }
            return size;
        }

        /* renamed from: Tr */
        public Ty T(com.Tr.T.T.T input) throws IOException {
            while (true) {
                int tag = input.T();
                switch (tag) {
                    case 0:
                        break;
                    case 8:
                        this.Tr = Integer.valueOf(input.Tk());
                        continue;
                    case 18:
                        this.Ty = input.TE();
                        continue;
                    case 26:
                        this.Tn = input.TE();
                        continue;
                    case 34:
                        int arrayLength = TZ.Tr(input, 34);
                        int i = this.T9 == null ? 0 : this.T9.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.T9, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.TE();
                            input.T();
                            i++;
                        }
                        newArray[i] = input.TE();
                        this.T9 = newArray;
                        continue;
                    case 42:
                        this.Tk = input.TE();
                        continue;
                    case 48:
                        this.TZ = Integer.valueOf(input.Tv());
                        continue;
                    case 56:
                        this.TE = Integer.valueOf(input.Tv());
                        continue;
                    case 66:
                        this.T5 = input.TE();
                        continue;
                    case 74:
                        this.Tv = input.TE();
                        continue;
                    case 82:
                        this.Th = input.TE();
                        continue;
                    case 88:
                        this.T6 = Integer.valueOf(input.Tk());
                        continue;
                    case 106:
                        int arrayLength2 = TZ.Tr(input, 106);
                        int i2 = this.Te == null ? 0 : this.Te.length;
                        String[] newArray2 = new String[(i2 + arrayLength2)];
                        if (i2 != 0) {
                            System.arraycopy(this.Te, 0, newArray2, 0, i2);
                        }
                        while (i2 < newArray2.length - 1) {
                            newArray2[i2] = input.TE();
                            input.T();
                            i2++;
                        }
                        newArray2[i2] = input.TE();
                        this.Te = newArray2;
                        continue;
                    case 112:
                        this.Tq = Integer.valueOf(input.Tv());
                        continue;
                    case Can.CAN_AUDI_ZMYT /*152*/:
                        this.TF = Boolean.valueOf(input.TZ());
                        continue;
                    case 162:
                        int arrayLength3 = TZ.Tr(input, 162);
                        int i3 = this.Tj == null ? 0 : this.Tj.length;
                        String[] newArray3 = new String[(i3 + arrayLength3)];
                        if (i3 != 0) {
                            System.arraycopy(this.Tj, 0, newArray3, 0, i3);
                        }
                        while (i3 < newArray3.length - 1) {
                            newArray3[i3] = input.TE();
                            input.T();
                            i3++;
                        }
                        newArray3[i3] = input.TE();
                        this.Tj = newArray3;
                        continue;
                    case 170:
                        this.TB = input.TE();
                        continue;
                    case 176:
                        this.TK = Integer.valueOf(input.Tv());
                        continue;
                    case 184:
                        this.TO = Long.valueOf(input.T9());
                        continue;
                    default:
                        if (!TZ.T(input, tag)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }
    }

    /* renamed from: com.Tn.Tr.Tk.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public static final class C0005T extends T9 {
        private static volatile C0005T[] Tn;
        public Ty Tr;
        public Ty[] Ty;

        public static C0005T[] Tn() {
            if (Tn == null) {
                synchronized (com.Tr.T.T.Ty.f241T) {
                    if (Tn == null) {
                        Tn = new C0005T[0];
                    }
                }
            }
            return Tn;
        }

        public C0005T() {
            T9();
        }

        public C0005T T9() {
            this.Tr = null;
            this.Ty = Ty.Tn();
            this.f238T = -1;
            return this;
        }

        public void T(com.Tr.T.T.Tr output) throws IOException {
            if (this.Tr != null) {
                output.T(1, (T9) this.Tr);
            }
            if (this.Ty != null && this.Ty.length > 0) {
                for (Ty element : this.Ty) {
                    if (element != null) {
                        output.T(14, (T9) element);
                    }
                }
            }
            super.T(output);
        }

        /* access modifiers changed from: protected */
        public int Ty() {
            int size = super.Ty();
            if (this.Tr != null) {
                size += com.Tr.T.T.Tr.Tr(1, (T9) this.Tr);
            }
            if (this.Ty != null && this.Ty.length > 0) {
                for (Ty element : this.Ty) {
                    if (element != null) {
                        size += com.Tr.T.T.Tr.Tr(14, (T9) element);
                    }
                }
            }
            return size;
        }

        /* renamed from: Tr */
        public C0005T T(com.Tr.T.T.T input) throws IOException {
            int i;
            while (true) {
                int tag = input.T();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        if (this.Tr == null) {
                            this.Tr = new Ty();
                        }
                        input.T((T9) this.Tr);
                        continue;
                    case 114:
                        int arrayLength = TZ.Tr(input, 114);
                        if (this.Ty == null) {
                            i = 0;
                        } else {
                            i = this.Ty.length;
                        }
                        Ty[] newArray = new Ty[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.Ty, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new Ty();
                            input.T((T9) newArray[i]);
                            input.T();
                            i++;
                        }
                        newArray[i] = new Ty();
                        input.T((T9) newArray[i]);
                        this.Ty = newArray;
                        continue;
                    default:
                        if (!TZ.T(input, tag)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }
    }

    /* compiled from: Proguard */
    public static final class Tr extends T9 {
        public String T9;
        public T.C0001T[] TE;
        public String TZ;
        public Ty Tk;
        public Integer Tn;
        public C0005T[] Tr;
        public Integer Ty;

        public Tr() {
            Tn();
        }

        public Tr Tn() {
            this.Tr = C0005T.Tn();
            this.Ty = null;
            this.Tn = null;
            this.T9 = null;
            this.Tk = null;
            this.TZ = null;
            this.TE = T.C0001T.Tn();
            this.f238T = -1;
            return this;
        }

        public void T(com.Tr.T.T.Tr output) throws IOException {
            if (this.Tr != null && this.Tr.length > 0) {
                for (C0005T element : this.Tr) {
                    if (element != null) {
                        output.T(1, (T9) element);
                    }
                }
            }
            if (this.Ty != null) {
                output.T(2, this.Ty.intValue());
            }
            if (this.Tn != null) {
                output.T(3, this.Tn.intValue());
            }
            if (this.T9 != null) {
                output.T(4, this.T9);
            }
            if (this.Tk != null) {
                output.T(5, (T9) this.Tk);
            }
            if (this.TZ != null) {
                output.T(6, this.TZ);
            }
            if (this.TE != null && this.TE.length > 0) {
                for (T.C0001T element2 : this.TE) {
                    if (element2 != null) {
                        output.T(7, (T9) element2);
                    }
                }
            }
            super.T(output);
        }

        /* access modifiers changed from: protected */
        public int Ty() {
            int size = super.Ty();
            if (this.Tr != null && this.Tr.length > 0) {
                for (C0005T element : this.Tr) {
                    if (element != null) {
                        size += com.Tr.T.T.Tr.Tr(1, (T9) element);
                    }
                }
            }
            if (this.Ty != null) {
                size += com.Tr.T.T.Tr.Ty(2, this.Ty.intValue());
            }
            if (this.Tn != null) {
                size += com.Tr.T.T.Tr.Ty(3, this.Tn.intValue());
            }
            if (this.T9 != null) {
                size += com.Tr.T.T.Tr.Tr(4, this.T9);
            }
            if (this.Tk != null) {
                size += com.Tr.T.T.Tr.Tr(5, (T9) this.Tk);
            }
            if (this.TZ != null) {
                size += com.Tr.T.T.Tr.Tr(6, this.TZ);
            }
            if (this.TE != null && this.TE.length > 0) {
                for (T.C0001T element2 : this.TE) {
                    if (element2 != null) {
                        size += com.Tr.T.T.Tr.Tr(7, (T9) element2);
                    }
                }
            }
            return size;
        }

        /* renamed from: Tr */
        public Tr T(com.Tr.T.T.T input) throws IOException {
            int i;
            int i2;
            while (true) {
                int tag = input.T();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        int arrayLength = TZ.Tr(input, 10);
                        if (this.Tr == null) {
                            i2 = 0;
                        } else {
                            i2 = this.Tr.length;
                        }
                        C0005T[] newArray = new C0005T[(i2 + arrayLength)];
                        if (i2 != 0) {
                            System.arraycopy(this.Tr, 0, newArray, 0, i2);
                        }
                        while (i2 < newArray.length - 1) {
                            newArray[i2] = new C0005T();
                            input.T((T9) newArray[i2]);
                            input.T();
                            i2++;
                        }
                        newArray[i2] = new C0005T();
                        input.T((T9) newArray[i2]);
                        this.Tr = newArray;
                        continue;
                    case 16:
                        this.Ty = Integer.valueOf(input.Tk());
                        continue;
                    case 24:
                        this.Tn = Integer.valueOf(input.Tk());
                        continue;
                    case 34:
                        this.T9 = input.TE();
                        continue;
                    case 42:
                        if (this.Tk == null) {
                            this.Tk = new Ty();
                        }
                        input.T((T9) this.Tk);
                        continue;
                    case 50:
                        this.TZ = input.TE();
                        continue;
                    case 58:
                        int arrayLength2 = TZ.Tr(input, 58);
                        if (this.TE == null) {
                            i = 0;
                        } else {
                            i = this.TE.length;
                        }
                        T.C0001T[] newArray2 = new T.C0001T[(i + arrayLength2)];
                        if (i != 0) {
                            System.arraycopy(this.TE, 0, newArray2, 0, i);
                        }
                        while (i < newArray2.length - 1) {
                            newArray2[i] = new T.C0001T();
                            input.T((T9) newArray2[i]);
                            input.T();
                            i++;
                        }
                        newArray2[i] = new T.C0001T();
                        input.T((T9) newArray2[i]);
                        this.TE = newArray2;
                        continue;
                    default:
                        if (!TZ.T(input, tag)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public static Tr T(byte[] data) throws Tn {
            return (Tr) T9.T(new Tr(), data);
        }
    }
}
