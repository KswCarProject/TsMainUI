package com.T.T.Tr.T;

import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.util.Locale;

/* compiled from: Proguard */
public class Tw implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Tw f177T = new Tw();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        String text = (String) parser.Tv();
        if (text == null) {
            return null;
        }
        String[] items = text.split("_");
        if (items.length == 1) {
            return new Locale(items[0]);
        }
        if (items.length == 2) {
            return new Locale(items[0], items[1]);
        }
        return new Locale(items[0], items[1], items[2]);
    }

    public int T() {
        return 4;
    }
}
