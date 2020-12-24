package com.T.T.Tr.T;

import com.T.T.T;
import com.T.T.Tr.T9;
import com.T.T.Tr.TZ;
import com.T.T.Tr.Tn;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;

/* compiled from: Proguard */
public class Trn implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final Trn f165T = new Trn();

    public <T> T T(Ty parser, Type type, Object fieldName) {
        T9 lexer = parser.Th();
        if (lexer.Tn() == 8) {
            lexer.T();
            return null;
        } else if (lexer.Tn() == 12 || lexer.Tn() == 16) {
            String declaringClass = null;
            String methodName = null;
            String fileName = null;
            int lineNumber = 0;
            while (true) {
                String key = lexer.T(parser.Tr());
                if (key == null) {
                    if (lexer.Tn() == 13) {
                        lexer.T(16);
                        break;
                    } else if (lexer.Tn() == 16 && lexer.T(Tn.AllowArbitraryCommas)) {
                    }
                }
                lexer.Tr(4);
                if (key == "className") {
                    if (lexer.Tn() == 8) {
                        declaringClass = null;
                    } else if (lexer.Tn() == 4) {
                        declaringClass = lexer.Tf();
                    } else {
                        throw new com.T.T.Tn("syntax error");
                    }
                } else if (key == "methodName") {
                    if (lexer.Tn() == 8) {
                        methodName = null;
                    } else if (lexer.Tn() == 4) {
                        methodName = lexer.Tf();
                    } else {
                        throw new com.T.T.Tn("syntax error");
                    }
                } else if (key == "fileName") {
                    if (lexer.Tn() == 8) {
                        fileName = null;
                    } else if (lexer.Tn() == 4) {
                        fileName = lexer.Tf();
                    } else {
                        throw new com.T.T.Tn("syntax error");
                    }
                } else if (key == "lineNumber") {
                    if (lexer.Tn() == 8) {
                        lineNumber = 0;
                    } else if (lexer.Tn() == 2) {
                        lineNumber = lexer.TK();
                    } else {
                        throw new com.T.T.Tn("syntax error");
                    }
                } else if (key == "nativeMethod") {
                    if (lexer.Tn() == 8) {
                        lexer.T(16);
                    } else if (lexer.Tn() == 6) {
                        lexer.T(16);
                    } else if (lexer.Tn() == 7) {
                        lexer.T(16);
                    } else {
                        throw new com.T.T.Tn("syntax error");
                    }
                } else if (key != T.f128T) {
                    throw new com.T.T.Tn("syntax error : " + key);
                } else if (lexer.Tn() == 4) {
                    String elementType = lexer.Tf();
                    if (!elementType.equals("java.lang.StackTraceElement")) {
                        throw new com.T.T.Tn("syntax error : " + elementType);
                    }
                } else if (lexer.Tn() != 8) {
                    throw new com.T.T.Tn("syntax error");
                }
                if (lexer.Tn() == 13) {
                    lexer.T(16);
                    break;
                }
            }
            return new StackTraceElement(declaringClass, methodName, fileName, lineNumber);
        } else {
            throw new com.T.T.Tn("syntax error: " + TZ.T(lexer.Tn()));
        }
    }

    public int T() {
        return 12;
    }
}
