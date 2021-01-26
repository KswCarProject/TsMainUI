package com.T.T.Tr.T;

import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.net.URI;

/* compiled from: Proguard */
public class Trh implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Trh f167T = new Trh();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        String uri = (String) parser.Tv();
        if (uri == null) {
            return null;
        }
        return URI.create(uri);
    }

    public int T() {
        return 4;
    }
}
