package com.T.T.Tr;

import java.lang.reflect.Type;

/* compiled from: Proguard */
public class T5 {

    /* renamed from: T  reason: collision with root package name */
    private Object f175T;
    private Type Tn;
    private final T5 Tr;
    private final Object Ty;

    public T5(T5 parent, Object object, Object fieldName) {
        this.Tr = parent;
        this.f175T = object;
        this.Ty = fieldName;
    }

    public Type T() {
        return this.Tn;
    }

    public void T(Type type) {
        this.Tn = type;
    }

    public Object Tr() {
        return this.f175T;
    }

    public void T(Object object) {
        this.f175T = object;
    }

    public T5 Ty() {
        return this.Tr;
    }

    public String Tn() {
        if (this.Tr == null) {
            return "$";
        }
        if (this.Ty instanceof Integer) {
            return this.Tr.Tn() + "[" + this.Ty + "]";
        }
        return this.Tr.Tn() + "." + this.Ty;
    }

    public String toString() {
        return Tn();
    }
}
