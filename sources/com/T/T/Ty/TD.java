package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;

/* compiled from: Proguard */
public class TD implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final TD f198T = new TD();
    private DecimalFormat Tr = null;

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        String doubleText;
        Trs out = serializer.Tv();
        if (object != null) {
            double doubleValue = ((Double) object).doubleValue();
            if (Double.isNaN(doubleValue)) {
                out.T();
            } else if (Double.isInfinite(doubleValue)) {
                out.T();
            } else {
                if (this.Tr == null) {
                    doubleText = Double.toString(doubleValue);
                    if (doubleText.endsWith(".0")) {
                        doubleText = doubleText.substring(0, doubleText.length() - 2);
                    }
                } else {
                    doubleText = this.Tr.format(doubleValue);
                }
                out.append((CharSequence) doubleText);
                if (serializer.T(TrG.WriteClassName)) {
                    out.T('D');
                }
            }
        } else if (serializer.T(TrG.WriteNullNumberAsZero)) {
            out.T('0');
        } else {
            out.T();
        }
    }
}
