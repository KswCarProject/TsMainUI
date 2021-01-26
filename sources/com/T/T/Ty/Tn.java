package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* compiled from: Proguard */
public class Tn implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final Tn f213T = new Tn();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            AtomicIntegerArray array = (AtomicIntegerArray) object;
            int len = array.length();
            out.append('[');
            for (int i = 0; i < len; i++) {
                int val = array.get(i);
                if (i != 0) {
                    out.T(',');
                }
                out.Tr(val);
            }
            out.append(']');
        } else if (out.T(TrG.WriteNullListAsEmpty)) {
            out.write("[]");
        } else {
            out.T();
        }
    }
}
