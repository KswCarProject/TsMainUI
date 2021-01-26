package com.T.T.Tr.T;

import com.T.T.T;
import com.T.T.Tn;
import com.T.T.Tn.TZ;
import com.T.T.Tr.T9;
import com.T.T.Tr.Tv;
import com.T.T.Tr.Ty;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Proguard */
public class TrZ extends TP {
    public TrZ(Tv mapping, Class<?> clazz) {
        super(mapping, clazz);
    }

    public <T> T T(Ty parser, Type type, Object fieldName) {
        Throwable ex;
        T9 lexer = parser.Th();
        if (lexer.Tn() == 8) {
            lexer.T();
            return null;
        }
        if (parser.Tn() == 2) {
            parser.T(0);
        } else if (lexer.Tn() != 12) {
            throw new Tn("syntax error");
        }
        Throwable cause = null;
        Class<?> exClass = null;
        if (type != null && (type instanceof Class)) {
            Class<?> clazz = (Class) type;
            if (Throwable.class.isAssignableFrom(clazz)) {
                exClass = clazz;
            }
        }
        String message = null;
        StackTraceElement[] stackTrace = null;
        Map<String, Object> otherValues = new HashMap<>();
        while (true) {
            String key = lexer.T(parser.Tr());
            if (key == null) {
                if (lexer.Tn() == 13) {
                    lexer.T(16);
                    break;
                } else if (lexer.Tn() == 16 && lexer.T(com.T.T.Tr.Tn.AllowArbitraryCommas)) {
                }
            }
            lexer.Tr(4);
            if (T.f131T.equals(key)) {
                if (lexer.Tn() == 4) {
                    exClass = TZ.T(lexer.Tf());
                    lexer.T(16);
                } else {
                    throw new Tn("syntax error");
                }
            } else if ("message".equals(key)) {
                if (lexer.Tn() == 8) {
                    message = null;
                } else if (lexer.Tn() == 4) {
                    message = lexer.Tf();
                } else {
                    throw new Tn("syntax error");
                }
                lexer.T();
            } else if ("cause".equals(key)) {
                cause = (Throwable) T(parser, (Type) null, (Object) "cause");
            } else if ("stackTrace".equals(key)) {
                stackTrace = (StackTraceElement[]) parser.T(StackTraceElement[].class);
            } else {
                otherValues.put(key, parser.Tv());
            }
            if (lexer.Tn() == 13) {
                lexer.T(16);
                break;
            }
        }
        if (exClass == null) {
            ex = new Exception(message, cause);
        } else {
            try {
                ex = T(message, cause, exClass);
                if (ex == null) {
                    ex = new Exception(message, cause);
                }
            } catch (Exception e) {
                throw new Tn("create instance error", e);
            }
        }
        if (stackTrace == null) {
            return ex;
        }
        ex.setStackTrace(stackTrace);
        return ex;
    }

    private Throwable T(String message, Throwable cause, Class<?> exClass) throws Exception {
        Constructor<?> defaultConstructor = null;
        Constructor<?> messageConstructor = null;
        Constructor<?> causeConstructor = null;
        for (Constructor<?> constructor : exClass.getConstructors()) {
            if (constructor.getParameterTypes().length == 0) {
                defaultConstructor = constructor;
            } else if (constructor.getParameterTypes().length == 1 && constructor.getParameterTypes()[0] == String.class) {
                messageConstructor = constructor;
            } else if (constructor.getParameterTypes().length == 2 && constructor.getParameterTypes()[0] == String.class && constructor.getParameterTypes()[1] == Throwable.class) {
                causeConstructor = constructor;
            }
        }
        if (causeConstructor != null) {
            return (Throwable) causeConstructor.newInstance(new Object[]{message, cause});
        } else if (messageConstructor != null) {
            return (Throwable) messageConstructor.newInstance(new Object[]{message});
        } else if (defaultConstructor != null) {
            return (Throwable) defaultConstructor.newInstance(new Object[0]);
        } else {
            return null;
        }
    }

    public int T() {
        return 12;
    }
}
