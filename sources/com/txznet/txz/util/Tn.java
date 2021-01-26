package com.txznet.txz.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/* compiled from: Proguard */
public class Tn {

    /* renamed from: T  reason: collision with root package name */
    private static final char[] f906T = "0123456789abcdef".toCharArray();

    public static String T(File file) {
        if (!file.isFile()) {
            return null;
        }
        byte[] buffer = new byte[1024];
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            FileInputStream in = new FileInputStream(file);
            while (true) {
                try {
                    int len = in.read(buffer, 0, 1024);
                    if (len != -1) {
                        digest.update(buffer, 0, len);
                    } else {
                        in.close();
                        return Tr(digest.digest());
                    }
                } catch (Exception e) {
                    e = e;
                    FileInputStream fileInputStream = in;
                    e.printStackTrace();
                    return null;
                }
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return null;
        }
    }

    public static String T(String str) {
        if (str == null) {
            return null;
        }
        return T(str.getBytes());
    }

    public static String T(byte[] input) {
        if (input == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input);
            return Tr(md.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String Tr(byte[] bytes) {
        char[] hexChars = new char[(bytes.length * 2)];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = f906T[v >>> 4];
            hexChars[(j * 2) + 1] = f906T[v & 15];
        }
        return new String(hexChars);
    }
}
