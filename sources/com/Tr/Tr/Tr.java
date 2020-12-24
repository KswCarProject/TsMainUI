package com.Tr.Tr;

/* compiled from: Proguard */
public final class Tr {

    /* renamed from: T  reason: collision with root package name */
    private final int f281T;
    private final int Tr;

    public int T() {
        return this.f281T;
    }

    public int Tr() {
        return this.Tr;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Tr)) {
            return false;
        }
        Tr d = (Tr) other;
        if (this.f281T == d.f281T && this.Tr == d.Tr) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.f281T * 32713) + this.Tr;
    }

    public String toString() {
        return this.f281T + "x" + this.Tr;
    }
}
