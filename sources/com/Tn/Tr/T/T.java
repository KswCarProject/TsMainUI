package com.Tn.Tr.T;

import com.Tr.T.T.T9;
import com.Tr.T.T.TZ;
import com.Tr.T.T.Tr;
import com.Tr.T.T.Ty;
import java.io.IOException;

/* compiled from: Proguard */
public interface T {

    /* renamed from: com.Tn.Tr.T.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public static final class C0001T extends T9 {
        private static volatile C0001T[] Tk;
        public String T9;
        public Long Tn;
        public Integer Tr;
        public Integer Ty;

        public static C0001T[] Tn() {
            if (Tk == null) {
                synchronized (Ty.f244T) {
                    if (Tk == null) {
                        Tk = new C0001T[0];
                    }
                }
            }
            return Tk;
        }

        public C0001T() {
            T9();
        }

        public C0001T T9() {
            this.Tr = null;
            this.Ty = null;
            this.Tn = null;
            this.T9 = null;
            this.f241T = -1;
            return this;
        }

        public void T(Tr output) throws IOException {
            if (this.Tr != null) {
                output.Tr(1, this.Tr.intValue());
            }
            if (this.Ty != null) {
                output.Tr(2, this.Ty.intValue());
            }
            if (this.Tn != null) {
                output.T(3, this.Tn.longValue());
            }
            if (this.T9 != null) {
                output.T(4, this.T9);
            }
            super.T(output);
        }

        /* access modifiers changed from: protected */
        public int Ty() {
            int size = super.Ty();
            if (this.Tr != null) {
                size += Tr.Tn(1, this.Tr.intValue());
            }
            if (this.Ty != null) {
                size += Tr.Tn(2, this.Ty.intValue());
            }
            if (this.Tn != null) {
                size += Tr.Tr(3, this.Tn.longValue());
            }
            if (this.T9 != null) {
                return size + Tr.Tr(4, this.T9);
            }
            return size;
        }

        /* renamed from: Tr */
        public C0001T T(com.Tr.T.T.T input) throws IOException {
            while (true) {
                int tag = input.T();
                switch (tag) {
                    case 0:
                        break;
                    case 8:
                        this.Tr = Integer.valueOf(input.Tv());
                        continue;
                    case 16:
                        this.Ty = Integer.valueOf(input.Tv());
                        continue;
                    case 24:
                        this.Tn = Long.valueOf(input.T9());
                        continue;
                    case 34:
                        this.T9 = input.TE();
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
