package com.T.T.Tr.T;

import com.T.T.Tn.TZ;
import com.T.T.Tr.T9;
import com.T.T.Tr.Tk;
import com.T.T.Tr.Tn;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public abstract class T implements T7 {
    /* access modifiers changed from: protected */
    public abstract <T> T T(Ty ty, Type type, Object obj, Object obj2);

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        Object Tv;
        T9 lexer = parser.Th();
        if (lexer.Tn() == 2) {
            Tv = Long.valueOf(lexer.TM());
            lexer.T(16);
        } else if (lexer.Tn() == 4) {
            String strVal = lexer.Tf();
            Tv = strVal;
            lexer.T(16);
            if (lexer.T(Tn.AllowISO8601DateFormat)) {
                Tk iso8601Lexer = new Tk(strVal);
                if (iso8601Lexer.TI()) {
                    Tv = iso8601Lexer.TB().getTime();
                }
                iso8601Lexer.close();
            }
        } else if (lexer.Tn() == 8) {
            lexer.T();
            Tv = null;
        } else if (lexer.Tn() == 12) {
            lexer.T();
            if (lexer.Tn() == 4) {
                if (com.T.T.T.f131T.equals(lexer.Tf())) {
                    lexer.T();
                    parser.Tr(17);
                    Class<?> type = TZ.T(lexer.Tf());
                    if (type != null) {
                        clazz = type;
                    }
                    parser.Tr(4);
                    parser.Tr(16);
                }
                lexer.Tr(2);
                if (lexer.Tn() == 2) {
                    long timeMillis = lexer.TM();
                    lexer.T();
                    Tv = Long.valueOf(timeMillis);
                    parser.Tr(13);
                } else {
                    throw new com.T.T.Tn("syntax error : " + lexer.T9());
                }
            } else {
                throw new com.T.T.Tn("syntax error");
            }
        } else if (parser.Tn() == 2) {
            parser.T(0);
            parser.Tr(16);
            if (lexer.Tn() != 4) {
                throw new com.T.T.Tn("syntax error");
            } else if (!"val".equals(lexer.Tf())) {
                throw new com.T.T.Tn("syntax error");
            } else {
                lexer.T();
                parser.Tr(17);
                Tv = parser.Tv();
                parser.Tr(13);
            }
        } else {
            Tv = parser.Tv();
        }
        return T(parser, clazz, fieldName, Tv);
    }
}
