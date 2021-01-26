package com.Tn.Tr.TZ;

import com.Tr.T.T.T9;
import com.Tr.T.T.TZ;
import com.Tr.T.T.Tr;
import java.io.IOException;

/* compiled from: Proguard */
public interface T {

    /* renamed from: com.Tn.Tr.TZ.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public static final class C0004T extends T9 {
        public Integer T5;
        public Integer T9;
        public Integer TE;
        public byte[] TZ;
        public Integer Tk;
        public Integer Tn;
        public Long Tr;
        public Boolean Ty;

        public C0004T() {
            Tn();
        }

        public C0004T Tn() {
            this.Tr = null;
            this.Ty = null;
            this.Tn = null;
            this.T9 = null;
            this.Tk = null;
            this.TZ = null;
            this.TE = null;
            this.T5 = null;
            this.f241T = -1;
            return this;
        }

        public void T(Tr output) throws IOException {
            if (this.Tr != null) {
                output.T(1, this.Tr.longValue());
            }
            if (this.Ty != null) {
                output.T(2, this.Ty.booleanValue());
            }
            if (this.Tn != null) {
                output.Tr(3, this.Tn.intValue());
            }
            if (this.T9 != null) {
                output.Tr(4, this.T9.intValue());
            }
            if (this.Tk != null) {
                output.Tr(5, this.Tk.intValue());
            }
            if (this.TZ != null) {
                output.T(6, this.TZ);
            }
            if (this.TE != null) {
                output.Tr(7, this.TE.intValue());
            }
            if (this.T5 != null) {
                output.Tr(8, this.T5.intValue());
            }
            super.T(output);
        }

        /* access modifiers changed from: protected */
        public int Ty() {
            int size = super.Ty();
            if (this.Tr != null) {
                size += Tr.Tr(1, this.Tr.longValue());
            }
            if (this.Ty != null) {
                size += Tr.Tr(2, this.Ty.booleanValue());
            }
            if (this.Tn != null) {
                size += Tr.Tn(3, this.Tn.intValue());
            }
            if (this.T9 != null) {
                size += Tr.Tn(4, this.T9.intValue());
            }
            if (this.Tk != null) {
                size += Tr.Tn(5, this.Tk.intValue());
            }
            if (this.TZ != null) {
                size += Tr.Tr(6, this.TZ);
            }
            if (this.TE != null) {
                size += Tr.Tn(7, this.TE.intValue());
            }
            if (this.T5 != null) {
                return size + Tr.Tn(8, this.T5.intValue());
            }
            return size;
        }

        /* renamed from: Tr */
        public C0004T T(com.Tr.T.T.T input) throws IOException {
            while (true) {
                int tag = input.T();
                switch (tag) {
                    case 0:
                        break;
                    case 8:
                        this.Tr = Long.valueOf(input.T9());
                        continue;
                    case 16:
                        this.Ty = Boolean.valueOf(input.TZ());
                        continue;
                    case 24:
                        this.Tn = Integer.valueOf(input.Tv());
                        continue;
                    case 32:
                        this.T9 = Integer.valueOf(input.Tv());
                        continue;
                    case 40:
                        this.Tk = Integer.valueOf(input.Tv());
                        continue;
                    case 50:
                        this.TZ = input.T5();
                        continue;
                    case 56:
                        this.TE = Integer.valueOf(input.Tv());
                        continue;
                    case 64:
                        this.T5 = Integer.valueOf(input.Tv());
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
