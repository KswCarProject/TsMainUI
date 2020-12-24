package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Tt implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final Tt f231T = new Tt();

    public final void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            double[] array = (double[]) object;
            int end = array.length - 1;
            if (end == -1) {
                out.append((CharSequence) "[]");
                return;
            }
            out.append('[');
            for (int i = 0; i < end; i++) {
                double item = array[i];
                if (Double.isNaN(item)) {
                    out.T();
                } else {
                    out.append((CharSequence) Double.toString(item));
                }
                out.append(',');
            }
            double item2 = array[end];
            if (Double.isNaN(item2)) {
                out.T();
            } else {
                out.append((CharSequence) Double.toString(item2));
            }
            out.append(']');
        } else if (out.T(TrG.WriteNullListAsEmpty)) {
            out.write("[]");
        } else {
            out.T();
        }
    }
}
