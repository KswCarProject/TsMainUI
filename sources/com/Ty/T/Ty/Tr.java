package com.Ty.T.Ty;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: Proguard */
public final class Tr {

    /* compiled from: Proguard */
    public interface T {
        boolean T(int i, int i2);
    }

    public static boolean T(InputStream is, OutputStream os, T listener, int bufferSize) throws IOException {
        int current = 0;
        int total = is.available();
        if (total <= 0) {
            total = 512000;
        }
        byte[] bytes = new byte[bufferSize];
        if (T(listener, 0, total)) {
            return false;
        }
        do {
            int count = is.read(bytes, 0, bufferSize);
            if (count != -1) {
                os.write(bytes, 0, count);
                current += count;
            } else {
                os.flush();
                return true;
            }
        } while (!T(listener, current, total));
        return false;
    }

    private static boolean T(T listener, int current, int total) {
        if (listener == null || listener.T(current, total) || (current * 100) / total >= 75) {
            return false;
        }
        return true;
    }

    public static void T(InputStream is) {
        do {
            try {
            } catch (IOException e) {
                return;
            } finally {
                T((Closeable) is);
            }
        } while (is.read(new byte[32768], 0, 32768) != -1);
    }

    public static void T(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }
}
