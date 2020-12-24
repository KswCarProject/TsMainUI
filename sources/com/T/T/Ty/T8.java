package com.T.T.Ty;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/* compiled from: Proguard */
public class T8 implements Trh {

    /* renamed from: T  reason: collision with root package name */
    public static T8 f193T = new T8();

    public void T(T7 serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        if (object == null) {
            serializer.Th();
            return;
        }
        Trs out = serializer.Tv();
        InetSocketAddress address = (InetSocketAddress) object;
        InetAddress inetAddress = address.getAddress();
        out.T('{');
        if (inetAddress != null) {
            out.Tr("address");
            serializer.Tn(inetAddress);
            out.T(',');
        }
        out.Tr("port");
        out.Tr(address.getPort());
        out.T('}');
    }
}
