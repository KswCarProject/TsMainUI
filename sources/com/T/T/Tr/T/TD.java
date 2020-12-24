package com.T.T.Tr.T;

import com.T.T.Tn;
import com.T.T.Tr.T9;
import com.T.T.Tr.Ty;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/* compiled from: Proguard */
public class TD implements T7 {

    /* renamed from: T  reason: collision with root package name */
    public static final TD f146T = new TD();

    public <T> T T(Ty parser, Type clazz, Object fieldName) {
        T9 lexer = parser.Th();
        if (lexer.Tn() == 8) {
            lexer.T();
            return null;
        }
        parser.Tr(12);
        InetAddress address = null;
        int port = 0;
        while (true) {
            String key = lexer.Tf();
            lexer.T(17);
            if (key.equals("address")) {
                parser.Tr(17);
                address = (InetAddress) parser.T(InetAddress.class);
            } else if (key.equals("port")) {
                parser.Tr(17);
                if (lexer.Tn() != 2) {
                    throw new Tn("port is not int");
                }
                port = lexer.TK();
                lexer.T();
            } else {
                parser.Tr(17);
                parser.Tv();
            }
            if (lexer.Tn() == 16) {
                lexer.T();
            } else {
                parser.Tr(13);
                return new InetSocketAddress(address, port);
            }
        }
    }

    public int T() {
        return 12;
    }
}
