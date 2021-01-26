package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.regex.Pattern;

/* compiled from: Proguard */
public class Tr6 implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static final Tr6 f217T = new Tr6();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        if (object == null) {
            serializer.Th();
        } else {
            serializer.T(((Pattern) object).pattern());
        }
    }
}
