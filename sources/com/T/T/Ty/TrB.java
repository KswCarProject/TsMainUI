package com.T.T.Ty;

/* compiled from: Proguard */
public class TrB {

    /* renamed from: T  reason: collision with root package name */
    private final TrB f217T;
    private final Object Tr;
    private final Object Ty;

    public TrB(TrB parent, Object object, Object fieldName) {
        this.f217T = parent;
        this.Tr = object;
        this.Ty = fieldName;
    }

    public TrB T() {
        return this.f217T;
    }

    public Object Tr() {
        return this.Tr;
    }

    public String Ty() {
        if (this.f217T == null) {
            return "$";
        }
        if (this.Ty instanceof Integer) {
            return this.f217T.Ty() + "[" + this.Ty + "]";
        }
        return this.f217T.Ty() + "." + this.Ty;
    }

    public String toString() {
        return Ty();
    }
}
