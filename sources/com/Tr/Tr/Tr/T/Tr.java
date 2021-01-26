package com.Tr.Tr.Tr.T;

/* compiled from: Proguard */
final class Tr {

    /* renamed from: T  reason: collision with root package name */
    private final T f287T;
    private final int[] Tr;

    Tr(T field, int[] coefficients) {
        if (coefficients.length == 0) {
            throw new IllegalArgumentException();
        }
        this.f287T = field;
        int coefficientsLength = coefficients.length;
        if (coefficientsLength <= 1 || coefficients[0] != 0) {
            this.Tr = coefficients;
            return;
        }
        int firstNonZero = 1;
        while (firstNonZero < coefficientsLength && coefficients[firstNonZero] == 0) {
            firstNonZero++;
        }
        if (firstNonZero == coefficientsLength) {
            this.Tr = new int[]{0};
            return;
        }
        this.Tr = new int[(coefficientsLength - firstNonZero)];
        System.arraycopy(coefficients, firstNonZero, this.Tr, 0, this.Tr.length);
    }

    /* access modifiers changed from: package-private */
    public int[] T() {
        return this.Tr;
    }

    /* access modifiers changed from: package-private */
    public int Tr() {
        return this.Tr.length - 1;
    }

    /* access modifiers changed from: package-private */
    public boolean Ty() {
        return this.Tr[0] == 0;
    }

    /* access modifiers changed from: package-private */
    public int T(int degree) {
        return this.Tr[(this.Tr.length - 1) - degree];
    }

    /* access modifiers changed from: package-private */
    public Tr T(Tr other) {
        if (!this.f287T.equals(other.f287T)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (Ty()) {
            return other;
        } else {
            if (other.Ty()) {
                return this;
            }
            int[] smallerCoefficients = this.Tr;
            int[] largerCoefficients = other.Tr;
            if (smallerCoefficients.length > largerCoefficients.length) {
                int[] temp = smallerCoefficients;
                smallerCoefficients = largerCoefficients;
                largerCoefficients = temp;
            }
            int[] sumDiff = new int[largerCoefficients.length];
            int lengthDiff = largerCoefficients.length - smallerCoefficients.length;
            System.arraycopy(largerCoefficients, 0, sumDiff, 0, lengthDiff);
            for (int i = lengthDiff; i < largerCoefficients.length; i++) {
                sumDiff[i] = T.Tr(smallerCoefficients[i - lengthDiff], largerCoefficients[i]);
            }
            return new Tr(this.f287T, sumDiff);
        }
    }

    /* access modifiers changed from: package-private */
    public Tr Tr(Tr other) {
        if (!this.f287T.equals(other.f287T)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (Ty() || other.Ty()) {
            return this.f287T.T();
        } else {
            int[] aCoefficients = this.Tr;
            int aLength = aCoefficients.length;
            int[] bCoefficients = other.Tr;
            int bLength = bCoefficients.length;
            int[] product = new int[((aLength + bLength) - 1)];
            for (int i = 0; i < aLength; i++) {
                int aCoeff = aCoefficients[i];
                for (int j = 0; j < bLength; j++) {
                    product[i + j] = T.Tr(product[i + j], this.f287T.Ty(aCoeff, bCoefficients[j]));
                }
            }
            return new Tr(this.f287T, product);
        }
    }

    /* access modifiers changed from: package-private */
    public Tr T(int degree, int coefficient) {
        if (degree < 0) {
            throw new IllegalArgumentException();
        } else if (coefficient == 0) {
            return this.f287T.T();
        } else {
            int size = this.Tr.length;
            int[] product = new int[(size + degree)];
            for (int i = 0; i < size; i++) {
                product[i] = this.f287T.Ty(this.Tr[i], coefficient);
            }
            return new Tr(this.f287T, product);
        }
    }

    /* access modifiers changed from: package-private */
    public Tr[] Ty(Tr other) {
        if (!this.f287T.equals(other.f287T)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (other.Ty()) {
            throw new IllegalArgumentException("Divide by 0");
        } else {
            Tr quotient = this.f287T.T();
            Tr remainder = this;
            int inverseDenominatorLeadingTerm = this.f287T.Ty(other.T(other.Tr()));
            while (remainder.Tr() >= other.Tr() && !remainder.Ty()) {
                int degreeDifference = remainder.Tr() - other.Tr();
                int scale = this.f287T.Ty(remainder.T(remainder.Tr()), inverseDenominatorLeadingTerm);
                Tr term = other.T(degreeDifference, scale);
                quotient = quotient.T(this.f287T.T(degreeDifference, scale));
                remainder = remainder.T(term);
            }
            return new Tr[]{quotient, remainder};
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder(Tr() * 8);
        for (int degree = Tr(); degree >= 0; degree--) {
            int coefficient = T(degree);
            if (coefficient != 0) {
                if (coefficient < 0) {
                    result.append(" - ");
                    coefficient = -coefficient;
                } else if (result.length() > 0) {
                    result.append(" + ");
                }
                if (degree == 0 || coefficient != 1) {
                    int alphaPower = this.f287T.Tr(coefficient);
                    if (alphaPower == 0) {
                        result.append('1');
                    } else if (alphaPower == 1) {
                        result.append('a');
                    } else {
                        result.append("a^");
                        result.append(alphaPower);
                    }
                }
                if (degree != 0) {
                    if (degree == 1) {
                        result.append('x');
                    } else {
                        result.append("x^");
                        result.append(degree);
                    }
                }
            }
        }
        return result.toString();
    }
}
