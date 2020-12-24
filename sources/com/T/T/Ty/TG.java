package com.T.T.Ty;

import com.T.T.T;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

/* compiled from: Proguard */
public class TG implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final TG f201T = new TG();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (object == null) {
            out.T();
            return;
        }
        String pattern = ((SimpleDateFormat) object).toPattern();
        if (!out.T(TrG.WriteClassName) || object.getClass() == fieldType) {
            out.T(pattern);
            return;
        }
        out.T('{');
        out.Tr(T.f128T);
        serializer.T(object.getClass().getName());
        out.T(',');
        out.Tr("val");
        out.T(pattern);
        out.T('}');
    }
}
