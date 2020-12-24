package com.T.T.Tr.T;

import com.T.T.Tn.TZ;
import com.T.T.Tn.Ty;
import com.T.T.Tr.T9;
import com.T.T.Tr.Tv;
import java.lang.reflect.Type;
import java.util.Map;

/* compiled from: Proguard */
public class T8 extends Ts {
    private final T7 Ty;

    public T8(Tv mapping, Class<?> clazz, Ty fieldInfo) {
        super(clazz, fieldInfo);
        this.Ty = mapping.T(fieldInfo);
    }

    public void T(com.T.T.Tr.Ty parser, Object object, Type objectType, Map<String, Object> fieldValues) {
        Long value;
        T9 lexer = parser.Th();
        if (lexer.Tn() == 2) {
            long val = lexer.TM();
            lexer.T(16);
            if (object == null) {
                fieldValues.put(this.f170T.Ty(), Long.valueOf(val));
            } else {
                T(object, val);
            }
        } else {
            if (lexer.Tn() == 8) {
                value = null;
                lexer.T(16);
            } else {
                value = TZ.T6(parser.Tv());
            }
            if (value != null || Tn() != Long.TYPE) {
                if (object == null) {
                    fieldValues.put(this.f170T.Ty(), value);
                } else {
                    T(object, (Object) value);
                }
            }
        }
    }

    public int T() {
        return this.Ty.T();
    }
}
