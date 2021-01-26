package com.T.T.Ty;

/* compiled from: Proguard */
public class TrB {

    /* renamed from: T  reason: collision with root package name */
    private final TrB f220T;
    private final Object Tr;
    private final Object Ty;

    public TrB(TrB parent, Object object, Object fieldName) {
        this.f220T = parent;
        this.Tr = object;
        this.Ty = fieldName;
    }

    public TrB T() {
        return this.f220T;
    }

    public Object Tr() {
        return this.Tr;
    }

    public String Ty() {
        if (this.f220T == null) {
            return "$";
        }
        if (this.Ty instanceof Integer) {
            return this.f220T.Ty() + "[" + this.Ty + "]";
        }
        return this.f220T.Ty() + "." + this.Ty;
    }

    public String toString() {
        return Ty();
    }
}
