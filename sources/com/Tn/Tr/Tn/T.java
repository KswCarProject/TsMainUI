package com.Tn.Tr.Tn;

import com.Tr.T.T.T9;
import com.Tr.T.T.TZ;
import com.Tr.T.T.Tn;
import com.Tr.T.T.Tr;
import java.io.IOException;

/* compiled from: Proguard */
public interface T {

    /* renamed from: com.Tn.Tr.Tn.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public static final class C0006T extends T9 {
        public Integer T5;
        public String T9;
        public Boolean TE;
        public byte[] TZ;
        public Integer Tk;
        public byte[] Tn;
        public String Tr;
        public byte[] Tv;
        public String Ty;

        public C0006T() {
            Tn();
        }

        public C0006T Tn() {
            this.Tr = null;
            this.Ty = null;
            this.Tn = null;
            this.T9 = null;
            this.Tk = null;
            this.TZ = null;
            this.TE = null;
            this.T5 = null;
            this.Tv = null;
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
                output.T(4, this.T9);
            }
            if (this.Tk != null) {
                output.Tr(5, this.Tk.intValue());
            }
            if (this.TZ != null) {
                output.T(6, this.TZ);
            }
            if (this.TE != null) {
                output.T(7, this.TE.booleanValue());
            }
            if (this.T5 != null) {
                output.Tr(8, this.T5.intValue());
            }
            if (this.Tv != null) {
                output.T(9, this.Tv);
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
                size += Tr.Tr(4, this.T9);
            }
            if (this.Tk != null) {
                size += Tr.Tn(5, this.Tk.intValue());
            }
            if (this.TZ != null) {
                size += Tr.Tr(6, this.TZ);
            }
            if (this.TE != null) {
                size += Tr.Tr(7, this.TE.booleanValue());
            }
            if (this.T5 != null) {
                size += Tr.Tn(8, this.T5.intValue());
            }
            if (this.Tv != null) {
                return size + Tr.Tr(9, this.Tv);
            }
            return size;
        }

        /* renamed from: Tr */
        public C0006T T(com.Tr.T.T.T input) throws IOException {
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
                        this.T9 = input.TE();
                        continue;
                    case 40:
                        this.Tk = Integer.valueOf(input.Tv());
                        continue;
                    case 50:
                        this.TZ = input.T5();
                        continue;
                    case 56:
                        this.TE = Boolean.valueOf(input.TZ());
                        continue;
                    case 64:
                        this.T5 = Integer.valueOf(input.Tv());
                        continue;
                    case 74:
                        this.Tv = input.T5();
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

        public static C0006T T(byte[] data) throws Tn {
            return (C0006T) T9.T(new C0006T(), data);
        }
    }
}
