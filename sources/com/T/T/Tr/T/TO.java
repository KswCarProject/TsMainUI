package com.T.T.Tr.T;

import com.T.T.Tn;
import com.T.T.Tr.T9;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Proguard */
public class TO implements T7 {

    /* renamed from: T  reason: collision with root package name */
    private final Class<?> f152T;
    private final Map<Integer, Enum> Tr = new HashMap();
    private final Map<String, Enum> Ty = new HashMap();

    public TO(Class<?> enumClass) {
        this.f152T = enumClass;
        try {
            for (Object value : (Object[]) enumClass.getMethod("values", new Class[0]).invoke((Object) null, new Object[0])) {
                Enum e = (Enum) value;
                this.Tr.put(Integer.valueOf(e.ordinal()), e);
                this.Ty.put(e.name(), e);
            }
        } catch (Exception e2) {
            throw new Tn("init enum values error, " + enumClass.getName());
        }
    }

    public <T> T T(Ty parser, Type type, Object fieldName) {
        try {
            T9 lexer = parser.Th();
            if (lexer.Tn() == 2) {
                Integer value = Integer.valueOf(lexer.TK());
                lexer.T(16);
                T e = this.Tr.get(value);
                if (e != null) {
                    return e;
                }
                throw new Tn("parse enum " + this.f152T.getName() + " error, value : " + value);
            } else if (lexer.Tn() == 4) {
                String strVal = lexer.Tf();
                lexer.T(16);
                if (strVal.length() == 0) {
                    return (Object) null;
                }
                Enum enumR = this.Ty.get(strVal);
                return Enum.valueOf(this.f152T, strVal);
            } else if (lexer.Tn() == 8) {
                lexer.T(16);
                return null;
            } else {
                throw new Tn("parse enum " + this.f152T.getName() + " error, value : " + parser.Tv());
            }
        } catch (Tn e2) {
            throw e2;
        } catch (Throwable e3) {
            throw new Tn(e3.getMessage(), e3);
        }
    }

    public int T() {
        return 2;
    }
}
