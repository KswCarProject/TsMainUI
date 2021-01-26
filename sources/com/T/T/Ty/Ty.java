package com.T.T.Ty;

import com.android.SdkConstants;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Proguard */
public class Ty implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final Ty f239T = new Ty();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Trs out = serializer.Tv();
        if (((AtomicBoolean) object).get()) {
            out.append((CharSequence) SdkConstants.VALUE_TRUE);
        } else {
            out.append((CharSequence) SdkConstants.VALUE_FALSE);
        }
    }
}
