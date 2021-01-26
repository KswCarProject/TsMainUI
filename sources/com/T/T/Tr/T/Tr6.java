package com.T.T.Tr.T;

import com.T.T.Tn;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;

/* compiled from: Proguard */
public class Tr6 implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Tr6 f164T = new Tr6();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        String url = (String) parser.Tv();
        if (url == null) {
            return null;
        }
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new Tn("create url error", e);
        }
    }

    public int T() {
        return 4;
    }
}
