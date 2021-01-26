package com.Tr.Tr.Tr.T;

import java.util.ArrayList;
import java.util.List;

/* compiled from: Proguard */
public final class Ty {

    /* renamed from: T  reason: collision with root package name */
    private final T f288T;
    private final List<Tr> Tr = new ArrayList();

    public Ty(T field) {
        this.f288T = field;
        this.Tr.add(new Tr(field, new int[]{1}));
    }

    private Tr T(int degree) {
        if (degree >= this.Tr.size()) {
            Tr lastGenerator = this.Tr.get(this.Tr.size() - 1);
            for (int d = this.Tr.size(); d <= degree; d++) {
                Tr nextGenerator = lastGenerator.Tr(new Tr(this.f288T, new int[]{1, this.f288T.T((d - 1) + this.f288T.Tr())}));
                this.Tr.add(nextGenerator);
                lastGenerator = nextGenerator;
            }
        }
        return this.Tr.get(degree);
    }

    public void T(int[] toEncode, int ecBytes) {
        if (ecBytes == 0) {
            throw new IllegalArgumentException("No error correction bytes");
        }
        int dataBytes = toEncode.length - ecBytes;
        if (dataBytes <= 0) {
            throw new IllegalArgumentException("No data bytes provided");
        }
        Tr generator = T(ecBytes);
        int[] infoCoefficients = new int[dataBytes];
        System.arraycopy(toEncode, 0, infoCoefficients, 0, dataBytes);
        int[] coefficients = new Tr(this.f288T, infoCoefficients).T(ecBytes, 1).Ty(generator)[1].T();
        int numZeroCoefficients = ecBytes - coefficients.length;
        for (int i = 0; i < numZeroCoefficients; i++) {
            toEncode[dataBytes + i] = 0;
        }
        System.arraycopy(coefficients, 0, toEncode, dataBytes + numZeroCoefficients, coefficients.length);
    }
}
