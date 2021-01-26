package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetAddress;

/* compiled from: Proguard */
public class TC implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static TC f200T = new TC();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        if (object == null) {
            serializer.Th();
        } else {
            serializer.T(((InetAddress) object).getHostAddress());
        }
    }
}
