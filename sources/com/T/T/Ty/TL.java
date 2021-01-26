package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class TL implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final TL f205T = new TL();

    public final void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object != null) {
            float[] array = (float[]) object;
            int end = array.length - 1;
            if (end == -1) {
                out.append((CharSequence) "[]");
                return;
            }
            out.append('[');
            for (int i = 0; i < end; i++) {
                float item = array[i];
                if (Float.isNaN(item)) {
                    out.T();
                } else {
                    out.append((CharSequence) Float.toString(item));
                }
                out.append(',');
            }
            float item2 = array[end];
            if (Float.isNaN(item2)) {
                out.T();
            } else {
                out.append((CharSequence) Float.toString(item2));
            }
            out.append(']');
        } else if (out.T(TrG.WriteNullListAsEmpty)) {
            out.write("[]");
        } else {
            out.T();
        }
    }
}
