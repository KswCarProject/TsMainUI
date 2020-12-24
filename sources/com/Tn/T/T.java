package com.Tn.T;

import com.Tn.Tr.T9.T;
import com.Tr.T.T.T9;
import com.Tr.T.T.TZ;
import com.Tr.T.T.Tn;
import com.Tr.T.T.Tr;
import java.io.IOException;

/* compiled from: Proguard */
public interface T {

    /* renamed from: com.Tn.T.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public static final class C0000T extends T9 {
        public T.Tn T9;
        public byte[] Tn;
        public String Tr;
        public String Ty;

        public C0000T() {
            Tn();
        }

        public C0000T Tn() {
            this.Tr = null;
            this.Ty = null;
            this.Tn = null;
            this.T9 = null;
            this.f238T = -1;
            return this;
        }

        public void T(Tr output) throws IOException {
            if (this.Tr != null) {
                output.T(1, this.Tr);
            }
            if (this.Ty != null) {
                output.T(2, this.Ty);
            }
            if (this.Tn != null) {
                output.T(3, this.Tn);
            }
            if (this.T9 != null) {
                output.T(4, (T9) this.T9);
            }
            super.T(output);
        }

        /* access modifiers changed from: protected */
        public int Ty() {
            int size = super.Ty();
            if (this.Tr != null) {
                size += Tr.Tr(1, this.Tr);
            }
            if (this.Ty != null) {
                size += Tr.Tr(2, this.Ty);
            }
            if (this.Tn != null) {
                size += Tr.Tr(3, this.Tn);
            }
            if (this.T9 != null) {
                return size + Tr.Tr(4, (T9) this.T9);
            }
            return size;
        }

        /* renamed from: Tr */
        public C0000T T(com.Tr.T.T.T input) throws IOException {
            while (true) {
                int tag = input.T();
                switch (tag) {
                    case 0:
                        break;
                    case 10:
                        this.Tr = input.TE();
                        continue;
                    case 18:
                        this.Ty = input.TE();
                        continue;
                    case 26:
                        this.Tn = input.T5();
                        continue;
                    case 34:
                        if (this.T9 == null) {
                            this.T9 = new T.Tn();
                        }
                        input.T((T9) this.T9);
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

        public static C0000T T(byte[] data) throws Tn {
            return (C0000T) T9.T(new C0000T(), data);
        }
    }
}
