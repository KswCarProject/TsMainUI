package com.T.T.Tr.T;

import com.T.T.Tn;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

/* compiled from: Proguard */
public class Tj extends T implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Tj f155T = new Tj();

    /* access modifiers changed from: protected */
    public <T> T T(Ty parser, Type clazz, Object fieldName, Object val) {
        if (val == null) {
            return null;
        }
        if (val instanceof String) {
            String strVal = (String) val;
            if (strVal.length() != 0) {
                return new SimpleDateFormat(strVal);
            }
            return null;
        }
        throw new Tn("parse error");
    }

    public int T() {
        return 4;
    }
}
