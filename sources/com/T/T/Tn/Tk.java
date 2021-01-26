package com.T.T.Tn;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Tk implements ParameterizedType {

    /* renamed from: T  reason: collision with root package name */
    private final Type[] f136T;
    private final Type Tr;
    private final Type Ty;

    public Tk(Type[] actualTypeArguments, Type ownerType, Type rawType) {
        this.f136T = actualTypeArguments;
        this.Tr = ownerType;
        this.Ty = rawType;
    }

    public Type[] getActualTypeArguments() {
        return this.f136T;
    }

    public Type getOwnerType() {
        return this.Tr;
    }

    public Type getRawType() {
        return this.Ty;
    }
}
