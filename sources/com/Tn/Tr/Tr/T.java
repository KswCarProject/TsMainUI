package com.Tn.Tr.Tr;

import com.Tr.T.T.T9;
import com.Tr.T.T.TZ;
import com.Tr.T.T.Ty;
import java.io.IOException;

/* compiled from: Proguard */
public interface T {

    /* renamed from: com.Tn.Tr.Tr.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public static final class C0007T extends T9 {
        private static volatile C0007T[] TE;
        public Integer T9;
        public Integer TZ;
        public Integer Tk;
        public Integer Tn;
        public String Tr;
        public String[] Ty;

        public static C0007T[] Tn() {
            if (TE == null) {
                synchronized (Ty.f244T) {
                    if (TE == null) {
                        TE = new C0007T[0];
                    }
                }
            }
            return TE;
        }

        public C0007T() {
            T9();
        }

        public C0007T T9() {
            this.Tr = null;
            this.Ty = TZ.Tk;
            this.Tn = null;
            this.T9 = null;
            this.Tk = null;
            this.TZ = null;
            this.f241T = -1;
            return this;
        }

        public void T(com.Tr.T.T.Tr output) throws IOException {
            if (this.Tr != null) {
                output.T(1, this.Tr);
            }
            if (this.Ty != null && this.Ty.length > 0) {
                for (String element : this.Ty) {
                    if (element != null) {
                        output.T(2, element);
                    }
                }
            }
            if (this.Tn != null) {
                output.T(3, this.Tn.intValue());
            }
            if (this.T9 != null) {
                output.Tr(4, this.T9.intValue());
            }
            if (this.Tk != null) {
                output.Tr(5, this.Tk.intValue());
            }
            if (this.TZ != null) {
                output.Tr(6, this.TZ.intValue());
            }
            super.T(output);
        }

        /* access modifiers changed from: protected */
        public int Ty() {
            int size = super.Ty();
            if (this.Tr != null) {
                size += com.Tr.T.T.Tr.Tr(1, this.Tr);
            }
            if (this.Ty != null && this.Ty.length > 0) {
                int dataCount = 0;
                int dataSize = 0;
                for (String element : this.Ty) {
                    if (element != null) {
                        dataCount++;
                        dataSize += com.Tr.T.T.Tr.Tr(element);
                    }
                }
                size = size + dataSize + (dataCount * 1);
            }
            if (this.Tn != null) {
                size += com.Tr.T.T.Tr.Ty(3, this.Tn.intValue());
            }
            if (this.T9 != null) {
                size += com.Tr.T.T.Tr.Tn(4, this.T9.intValue());
            }
            if (this.Tk != null) {
                size += com.Tr.T.T.Tr.Tn(5, this.Tk.intValue());
            }
            if (this.TZ != null) {
                return size + com.Tr.T.T.Tr.Tn(6, this.TZ.intValue());
            }
            return size;
        }

        /* renamed from: Tr */
        public C0007T T(com.Tr.T.T.T input) throws IOException {
            while (true) {
                int tag = input.T();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        this.Tr = input.TE();
                        continue;
                    case 18:
                        int arrayLength = TZ.Tr(input, 18);
                        int i = this.Ty == null ? 0 : this.Ty.length;
                        String[] newArray = new String[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.Ty, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = input.TE();
                            input.T();
                            i++;
                        }
                        newArray[i] = input.TE();
                        this.Ty = newArray;
                        continue;
                    case 24:
                        this.Tn = Integer.valueOf(input.Tk());
                        continue;
                    case 32:
                        this.T9 = Integer.valueOf(input.Tv());
                        continue;
                    case 40:
                        this.Tk = Integer.valueOf(input.Tv());
                        continue;
                    case 48:
                        this.TZ = Integer.valueOf(input.Tv());
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
        public C0007T[] Tr;

        public Tr() {
            Tn();
        }

        public Tr Tn() {
            this.Tr = C0007T.Tn();
            this.f241T = -1;
            return this;
        }

        public void T(com.Tr.T.T.Tr output) throws IOException {
            if (this.Tr != null && this.Tr.length > 0) {
                for (C0007T element : this.Tr) {
                    if (element != null) {
                        output.T(1, (T9) element);
                    }
                }
            }
            super.T(output);
        }

        /* access modifiers changed from: protected */
        public int Ty() {
            int size = super.Ty();
            if (this.Tr != null && this.Tr.length > 0) {
                for (C0007T element : this.Tr) {
                    if (element != null) {
                        size += com.Tr.T.T.Tr.Tr(1, (T9) element);
                    }
                }
            }
            return size;
        }

        /* renamed from: Tr */
        public Tr T(com.Tr.T.T.T input) throws IOException {
            int i;
            while (true) {
                int tag = input.T();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        int arrayLength = TZ.Tr(input, 10);
                        if (this.Tr == null) {
                            i = 0;
                        } else {
                            i = this.Tr.length;
                        }
                        C0007T[] newArray = new C0007T[(i + arrayLength)];
                        if (i != 0) {
                            System.arraycopy(this.Tr, 0, newArray, 0, i);
                        }
                        while (i < newArray.length - 1) {
                            newArray[i] = new C0007T();
                            input.T((T9) newArray[i]);
                            input.T();
                            i++;
                        }
                        newArray[i] = new C0007T();
                        input.T((T9) newArray[i]);
                        this.Tr = newArray;
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
}
