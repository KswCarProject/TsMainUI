package com.T.T.Tr.T;

import com.T.T.Tn;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* compiled from: Proguard */
public class Tt implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Tt f174T = new Tt();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        String host = (String) parser.Tv();
        if (host == null || host.length() == 0) {
            return null;
        }
        try {
            return InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            throw new Tn("deserialize error", e);
        }
    }

    public int T() {
        return 4;
    }
}
