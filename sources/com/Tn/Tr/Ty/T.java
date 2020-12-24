package com.Tn.Tr.Ty;

import com.Tr.T.T.T9;
import com.Tr.T.T.TZ;
import com.Tr.T.T.Tr;
import java.io.IOException;

/* compiled from: Proguard */
public interface T {

    /* renamed from: com.Tn.Tr.Ty.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public static final class C0008T extends T9 {
        public String Tn;
        public String Tr;
        public String Ty;

        public C0008T() {
            Tn();
        }

        public C0008T Tn() {
            this.Tr = null;
            this.Ty = null;
            this.Tn = null;
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
                return size + Tr.Tr(3, this.Tn);
            }
            return size;
        }

        /* renamed from: Tr */
        public C0008T T(com.Tr.T.T.T input) throws IOException {
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
                        this.Tn = input.TE();
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
