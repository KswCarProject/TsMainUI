package com.T.T.Tr.T;

import com.T.T.Tn.Ty;
import com.T.T.Tr.T9;
import com.T.T.Tr.Tv;
import java.lang.reflect.Type;
import java.util.Map;

/* compiled from: Proguard */
public class TZ extends Ts {
    public TZ(Tv mapping, Class<?> clazz, Ty fieldInfo) {
        super(clazz, fieldInfo);
    }

    public void T(com.T.T.Tr.Ty parser, Object object, Type objectType, Map<String, Object> fieldValues) {
        boolean booleanValue = true;
        T9 lexer = parser.Th();
        if (lexer.Tn() == 6) {
            lexer.T(16);
            if (object == null) {
                fieldValues.put(this.f173T.Ty(), Boolean.TRUE);
            } else {
                T(object, true);
            }
        } else if (lexer.Tn() == 2) {
            int val = lexer.TK();
            lexer.T(16);
            if (val != 1) {
                booleanValue = false;
            }
            if (object == null) {
                fieldValues.put(this.f173T.Ty(), Boolean.valueOf(booleanValue));
            } else {
                T(object, booleanValue);
            }
        } else if (lexer.Tn() == 8) {
            lexer.T(16);
            if (Tn() != Boolean.TYPE && object != null) {
                T(object, (String) null);
            }
        } else if (lexer.Tn() == 7) {
            lexer.T(16);
            if (object == null) {
                fieldValues.put(this.f173T.Ty(), Boolean.FALSE);
            } else {
                T(object, false);
            }
        } else {
            Boolean value = com.T.T.Tn.TZ.Tq(parser.Tv());
            if (value != null || Tn() != Boolean.TYPE) {
                if (object == null) {
                    fieldValues.put(this.f173T.Ty(), value);
                } else {
                    T(object, (Object) value);
                }
            }
        }
    }

    public int T() {
        return 6;
    }
}
