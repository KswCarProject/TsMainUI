package com.txznet.txz.util;

import com.txznet.comm.Tr.Tr.Tn;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* compiled from: Proguard */
public class T {
    public static byte[] T(String key, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            Key secretKey = SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(key.getBytes()));
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(1, secretKey, new IvParameterSpec("12345678".getBytes()));
            return cipher.doFinal(data);
        } catch (Exception e) {
            Tn.Ty("DES enCrypt = " + e.getMessage());
            return data;
        }
    }
}
