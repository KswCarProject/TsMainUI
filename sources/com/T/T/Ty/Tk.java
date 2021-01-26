package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLongArray;

/* compiled from: Proguard */
public class Tk implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final Tk f212T = new Tk();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            AtomicLongArray array = (AtomicLongArray) object;
            int len = array.length();
            out.append('[');
            for (int i = 0; i < len; i++) {
                long val = array.get(i);
                if (i != 0) {
                    out.T(',');
                }
                out.T(val);
            }
            out.append(']');
        } else if (out.T(TrG.WriteNullListAsEmpty)) {
            out.write("[]");
        } else {
            out.T();
        }
    }
}
