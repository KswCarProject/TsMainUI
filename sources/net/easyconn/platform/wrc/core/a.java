package net.easyconn.platform.wrc.core;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Base64Encoder */
public class a {
    private static Map<Character, Byte> a;
    private static final char[] b = {'4', 'W', 'T', '6', 'J', '0', '=', 'u', 'G', 'O', 'H', '7', 'r', '2', 'e', 't', 'w', '9', 'F', 'l', 'Z', '5', '/', 'U', 'Q', 'X', 'q', 'j', 'f', 'P', 'S', 'h', 'R', 'o', 'N', 'i', 'B', 'n', 'Y', 'y', 'a', 'c', 'b', 'D', 'K', 'C', '8', '1', 'E', '3', 'k', 'd', 'm', 'v', 'M', 'z', 'p', '+', 's', 'I', 'V', 'x', 'A', 'g', 'L'};

    public static synchronized void a() {
        synchronized (a.class) {
            if (a == null) {
                a = new HashMap(b.length);
                for (byte b2 = 0; b2 < b.length; b2 = (byte) (b2 + 1)) {
                    a.put(Character.valueOf(b[b2]), Byte.valueOf(b2));
                }
            }
        }
    }

    public static String a(String str) throws UnsupportedEncodingException {
        a();
        ByteBuffer allocate = ByteBuffer.allocate(str.length());
        for (int i = 0; i < str.length(); i += 4) {
            byte byteValue = a.get(Character.valueOf(str.charAt(i))).byteValue();
            byte byteValue2 = a.get(Character.valueOf(str.charAt(i + 1))).byteValue();
            byte byteValue3 = a.get(Character.valueOf(str.charAt(i + 2))).byteValue();
            byte byteValue4 = a.get(Character.valueOf(str.charAt(i + 3))).byteValue();
            allocate.put((byte) (((byteValue << 2) | (byteValue2 >> 4)) ^ 102));
            if (byteValue3 != 64) {
                allocate.put((byte) (((byteValue2 & 15) << 4) | (byteValue3 >> 2)));
                if (byteValue4 != 64) {
                    allocate.put((byte) (byteValue4 | ((byteValue3 & 3) << 6)));
                }
            }
        }
        return new String(allocate.array(), 0, allocate.position(), "UTF-8");
    }
}
