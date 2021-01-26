package com.Tn.Tr.T9;

import com.Tr.T.T.TZ;
import java.io.IOException;

/* compiled from: Proguard */
public interface T {

    /* compiled from: Proguard */
    public static final class Tr extends com.Tr.T.T.T9 {
        public Integer T5;
        public Float T9;
        public Float TE;
        public Double TZ;
        public Float Tk;
        public Double Tn;
        public Integer Tr;
        public Double Ty;

        public Tr() {
            Tn();
        }

        public Tr Tn() {
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

        public void T(com.Tr.T.T.Tr output) throws IOException {
            if (this.Tr != null) {
                output.Tr(1, this.Tr.intValue());
            }
            if (this.Ty != null) {
                output.T(2, this.Ty.doubleValue());
            }
            if (this.Tn != null) {
                output.T(3, this.Tn.doubleValue());
            }
            if (this.T9 != null) {
                output.T(4, this.T9.floatValue());
            }
            if (this.Tk != null) {
                output.T(5, this.Tk.floatValue());
            }
            if (this.TZ != null) {
                output.T(6, this.TZ.doubleValue());
            }
            if (this.TE != null) {
                output.T(7, this.TE.floatValue());
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
                size += com.Tr.T.T.Tr.Tn(1, this.Tr.intValue());
            }
            if (this.Ty != null) {
                size += com.Tr.T.T.Tr.Tr(2, this.Ty.doubleValue());
            }
            if (this.Tn != null) {
                size += com.Tr.T.T.Tr.Tr(3, this.Tn.doubleValue());
            }
            if (this.T9 != null) {
                size += com.Tr.T.T.Tr.Tr(4, this.T9.floatValue());
            }
            if (this.Tk != null) {
                size += com.Tr.T.T.Tr.Tr(5, this.Tk.floatValue());
            }
            if (this.TZ != null) {
                size += com.Tr.T.T.Tr.Tr(6, this.TZ.doubleValue());
            }
            if (this.TE != null) {
                size += com.Tr.T.T.Tr.Tr(7, this.TE.floatValue());
            }
            if (this.T5 != null) {
                return size + com.Tr.T.T.Tr.Tn(8, this.T5.intValue());
            }
            return size;
        }

        /* renamed from: Tr */
        public Tr T(com.Tr.T.T.T input) throws IOException {
            while (true) {
                int tag = input.T();
                switch (tag) {
                    case 0:
                        break;
                    case 8:
                        this.Tr = Integer.valueOf(input.Tv());
                        continue;
                    case 17:
                        this.Ty = Double.valueOf(input.Ty());
                        continue;
                    case 25:
                        this.Tn = Double.valueOf(input.Ty());
                        continue;
                    case 37:
                        this.T9 = Float.valueOf(input.Tn());
                        continue;
                    case 45:
                        this.Tk = Float.valueOf(input.Tn());
                        continue;
                    case 49:
                        this.TZ = Double.valueOf(input.Ty());
                        continue;
                    case 61:
                        this.TE = Float.valueOf(input.Tn());
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

    /* renamed from: com.Tn.Tr.T9.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public static final class C0002T extends com.Tr.T.T.T9 {
        public String T9;
        public String TE;
        public String TZ;
        public String Tk;
        public String Tn;
        public String Tr;
        public String Ty;

        public C0002T() {
            Tn();
        }

        public C0002T Tn() {
            this.Tr = null;
            this.Ty = null;
            this.Tn = null;
            this.T9 = null;
            this.Tk = null;
            this.TZ = null;
            this.TE = null;
            this.f241T = -1;
            return this;
        }

        public void T(com.Tr.T.T.Tr output) throws IOException {
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
                output.T(5, this.Tk);
            }
            if (this.TZ != null) {
                output.T(6, this.TZ);
            }
            if (this.TE != null) {
                output.T(7, this.TE);
            }
            super.T(output);
        }

        /* access modifiers changed from: protected */
        public int Ty() {
            int size = super.Ty();
            if (this.Tr != null) {
                size += com.Tr.T.T.Tr.Tr(1, this.Tr);
            }
            if (this.Ty != null) {
                size += com.Tr.T.T.Tr.Tr(2, this.Ty);
            }
            if (this.Tn != null) {
                size += com.Tr.T.T.Tr.Tr(3, this.Tn);
            }
            if (this.T9 != null) {
                size += com.Tr.T.T.Tr.Tr(4, this.T9);
            }
            if (this.Tk != null) {
                size += com.Tr.T.T.Tr.Tr(5, this.Tk);
            }
            if (this.TZ != null) {
                size += com.Tr.T.T.Tr.Tr(6, this.TZ);
            }
            if (this.TE != null) {
                return size + com.Tr.T.T.Tr.Tr(7, this.TE);
            }
            return size;
        }

        /* renamed from: Tr */
        public C0002T T(com.Tr.T.T.T input) throws IOException {
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
                    case 34:
                        this.T9 = input.TE();
                        continue;
                    case 42:
                        this.Tk = input.TE();
                        continue;
                    case 50:
                        this.TZ = input.TE();
                        continue;
                    case 58:
                        this.TE = input.TE();
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
    public static final class Ty extends com.Tr.T.T.T9 {
        public C0002T Tn;
        public Integer Tr;
        public Tr Ty;

        public Ty() {
            Tn();
        }

        public Ty Tn() {
            this.Tr = null;
            this.Ty = null;
            this.Tn = null;
            this.f241T = -1;
            return this;
        }

        public void T(com.Tr.T.T.Tr output) throws IOException {
            if (this.Tr != null) {
                output.Tr(1, this.Tr.intValue());
            }
            if (this.Ty != null) {
                output.T(2, (com.Tr.T.T.T9) this.Ty);
            }
            if (this.Tn != null) {
                output.T(3, (com.Tr.T.T.T9) this.Tn);
            }
            super.T(output);
        }

        /* access modifiers changed from: protected */
        public int Ty() {
            int size = super.Ty();
            if (this.Tr != null) {
                size += com.Tr.T.T.Tr.Tn(1, this.Tr.intValue());
            }
            if (this.Ty != null) {
                size += com.Tr.T.T.Tr.Tr(2, (com.Tr.T.T.T9) this.Ty);
            }
            if (this.Tn != null) {
                return size + com.Tr.T.T.Tr.Tr(3, (com.Tr.T.T.T9) this.Tn);
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
                        this.Tr = Integer.valueOf(input.Tv());
                        continue;
                    case 18:
                        if (this.Ty == null) {
                            this.Ty = new Tr();
                        }
                        input.T((com.Tr.T.T.T9) this.Ty);
                        continue;
                    case 26:
                        if (this.Tn == null) {
                            this.Tn = new C0002T();
                        }
                        input.T((com.Tr.T.T.T9) this.Tn);
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

        public static Ty T(byte[] data) throws com.Tr.T.T.Tn {
            return (Ty) com.Tr.T.T.T9.T(new Ty(), data);
        }
    }

    /* compiled from: Proguard */
    public static final class T9 extends com.Tr.T.T.T9 {
        public String T5;
        public String T9;
        public Long TE;
        public Integer TZ;
        public Integer Tk;
        public Integer Tn;
        public Long Tr;
        public String Tv;
        public String Ty;

        public T9() {
            Tn();
        }

        public T9 Tn() {
            this.Tr = null;
            this.Ty = null;
            this.Tn = null;
            this.T9 = null;
            this.Tk = null;
            this.TZ = null;
            this.TE = null;
            this.T5 = null;
            this.Tv = null;
            this.f241T = -1;
            return this;
        }

        public void T(com.Tr.T.T.Tr output) throws IOException {
            if (this.Tr != null) {
                output.T(4, this.Tr.longValue());
            }
            if (this.Ty != null) {
                output.T(5, this.Ty);
            }
            if (this.Tn != null) {
                output.Tr(6, this.Tn.intValue());
            }
            if (this.T9 != null) {
                output.T(7, this.T9);
            }
            if (this.Tk != null) {
                output.Tr(8, this.Tk.intValue());
            }
            if (this.TZ != null) {
                output.Tr(9, this.TZ.intValue());
            }
            if (this.TE != null) {
                output.T(10, this.TE.longValue());
            }
            if (this.T5 != null) {
                output.T(11, this.T5);
            }
            if (this.Tv != null) {
                output.T(12, this.Tv);
            }
            super.T(output);
        }

        /* access modifiers changed from: protected */
        public int Ty() {
            int size = super.Ty();
            if (this.Tr != null) {
                size += com.Tr.T.T.Tr.Tr(4, this.Tr.longValue());
            }
            if (this.Ty != null) {
                size += com.Tr.T.T.Tr.Tr(5, this.Ty);
            }
            if (this.Tn != null) {
                size += com.Tr.T.T.Tr.Tn(6, this.Tn.intValue());
            }
            if (this.T9 != null) {
                size += com.Tr.T.T.Tr.Tr(7, this.T9);
            }
            if (this.Tk != null) {
                size += com.Tr.T.T.Tr.Tn(8, this.Tk.intValue());
            }
            if (this.TZ != null) {
                size += com.Tr.T.T.Tr.Tn(9, this.TZ.intValue());
            }
            if (this.TE != null) {
                size += com.Tr.T.T.Tr.Tr(10, this.TE.longValue());
            }
            if (this.T5 != null) {
                size += com.Tr.T.T.Tr.Tr(11, this.T5);
            }
            if (this.Tv != null) {
                return size + com.Tr.T.T.Tr.Tr(12, this.Tv);
            }
            return size;
        }

        /* renamed from: Tr */
        public T9 T(com.Tr.T.T.T input) throws IOException {
            while (true) {
                int tag = input.T();
                switch (tag) {
                    case 0:
                        break;
                    case 32:
                        this.Tr = Long.valueOf(input.T9());
                        continue;
                    case 42:
                        this.Ty = input.TE();
                        continue;
                    case 48:
                        this.Tn = Integer.valueOf(input.Tv());
                        continue;
                    case 58:
                        this.T9 = input.TE();
                        continue;
                    case 64:
                        this.Tk = Integer.valueOf(input.Tv());
                        continue;
                    case 72:
                        this.TZ = Integer.valueOf(input.Tv());
                        continue;
                    case 80:
                        this.TE = Long.valueOf(input.T9());
                        continue;
                    case 90:
                        this.T5 = input.TE();
                        continue;
                    case 98:
                        this.Tv = input.TE();
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
    public static final class Tn extends com.Tr.T.T.T9 {
        public String T5;
        public String T6;
        public String T9;
        public String TB;
        public String TE;
        public Integer TF;
        public String TZ;
        public String Te;
        public T9 Th;
        public Integer Tj;
        public String Tk;
        public Tr Tn;
        public String Tq;
        public String Tr;
        public String Tv;
        public String Ty;

        public Tn() {
            Tn();
        }

        public Tn Tn() {
            this.Tr = null;
            this.Ty = null;
            this.Tn = null;
            this.T9 = null;
            this.Tk = null;
            this.TZ = null;
            this.TE = null;
            this.T5 = null;
            this.Tv = null;
            this.Th = null;
            this.T6 = null;
            this.Te = null;
            this.Tq = null;
            this.TF = null;
            this.Tj = null;
            this.TB = null;
            this.f241T = -1;
            return this;
        }

        public void T(com.Tr.T.T.Tr output) throws IOException {
            if (this.Tr != null) {
                output.T(1, this.Tr);
            }
            if (this.Ty != null) {
                output.T(2, this.Ty);
            }
            if (this.Tn != null) {
                output.T(3, (com.Tr.T.T.T9) this.Tn);
            }
            if (this.T9 != null) {
                output.T(4, this.T9);
            }
            if (this.Tk != null) {
                output.T(5, this.Tk);
            }
            if (this.TZ != null) {
                output.T(6, this.TZ);
            }
            if (this.TE != null) {
                output.T(7, this.TE);
            }
            if (this.T5 != null) {
                output.T(8, this.T5);
            }
            if (this.Tv != null) {
                output.T(9, this.Tv);
            }
            if (this.Th != null) {
                output.T(10, (com.Tr.T.T.T9) this.Th);
            }
            if (this.T6 != null) {
                output.T(11, this.T6);
            }
            if (this.Te != null) {
                output.T(12, this.Te);
            }
            if (this.Tq != null) {
                output.T(13, this.Tq);
            }
            if (this.TF != null) {
                output.Tr(14, this.TF.intValue());
            }
            if (this.Tj != null) {
                output.Tr(15, this.Tj.intValue());
            }
            if (this.TB != null) {
                output.T(16, this.TB);
            }
            super.T(output);
        }

        /* access modifiers changed from: protected */
        public int Ty() {
            int size = super.Ty();
            if (this.Tr != null) {
                size += com.Tr.T.T.Tr.Tr(1, this.Tr);
            }
            if (this.Ty != null) {
                size += com.Tr.T.T.Tr.Tr(2, this.Ty);
            }
            if (this.Tn != null) {
                size += com.Tr.T.T.Tr.Tr(3, (com.Tr.T.T.T9) this.Tn);
            }
            if (this.T9 != null) {
                size += com.Tr.T.T.Tr.Tr(4, this.T9);
            }
            if (this.Tk != null) {
                size += com.Tr.T.T.Tr.Tr(5, this.Tk);
            }
            if (this.TZ != null) {
                size += com.Tr.T.T.Tr.Tr(6, this.TZ);
            }
            if (this.TE != null) {
                size += com.Tr.T.T.Tr.Tr(7, this.TE);
            }
            if (this.T5 != null) {
                size += com.Tr.T.T.Tr.Tr(8, this.T5);
            }
            if (this.Tv != null) {
                size += com.Tr.T.T.Tr.Tr(9, this.Tv);
            }
            if (this.Th != null) {
                size += com.Tr.T.T.Tr.Tr(10, (com.Tr.T.T.T9) this.Th);
            }
            if (this.T6 != null) {
                size += com.Tr.T.T.Tr.Tr(11, this.T6);
            }
            if (this.Te != null) {
                size += com.Tr.T.T.Tr.Tr(12, this.Te);
            }
            if (this.Tq != null) {
                size += com.Tr.T.T.Tr.Tr(13, this.Tq);
            }
            if (this.TF != null) {
                size += com.Tr.T.T.Tr.Tn(14, this.TF.intValue());
            }
            if (this.Tj != null) {
                size += com.Tr.T.T.Tr.Tn(15, this.Tj.intValue());
            }
            if (this.TB != null) {
                return size + com.Tr.T.T.Tr.Tr(16, this.TB);
            }
            return size;
        }

        /* renamed from: Tr */
        public Tn T(com.Tr.T.T.T input) throws IOException {
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
                        if (this.Tn == null) {
                            this.Tn = new Tr();
                        }
                        input.T((com.Tr.T.T.T9) this.Tn);
                        continue;
                    case 34:
                        this.T9 = input.TE();
                        continue;
                    case 42:
                        this.Tk = input.TE();
                        continue;
                    case 50:
                        this.TZ = input.TE();
                        continue;
                    case 58:
                        this.TE = input.TE();
                        continue;
                    case 66:
                        this.T5 = input.TE();
                        continue;
                    case 74:
                        this.Tv = input.TE();
                        continue;
                    case 82:
                        if (this.Th == null) {
                            this.Th = new T9();
                        }
                        input.T((com.Tr.T.T.T9) this.Th);
                        continue;
                    case 90:
                        this.T6 = input.TE();
                        continue;
                    case 98:
                        this.Te = input.TE();
                        continue;
                    case 106:
                        this.Tq = input.TE();
                        continue;
                    case 112:
                        this.TF = Integer.valueOf(input.Tv());
                        continue;
                    case 120:
                        this.Tj = Integer.valueOf(input.Tv());
                        continue;
                    case 130:
                        this.TB = input.TE();
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
