package com.txznet.T;

import android.util.Log;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.txz.util.T9;
import com.txznet.txz.util.TE;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: Proguard */
public class Tr extends DexClassLoader {
    private static Long Tr = null;

    /* renamed from: T  reason: collision with root package name */
    String f350T;

    public static ClassLoader T(String dexPath, String dexUnzipDir, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        if (!dexPath.endsWith(".apk")) {
            return parent;
        }
        if (Tr == null) {
            HashMap<String, String> config = TE.T("CheckhandlerThreadDelay");
            Tr = 0L;
            if (!(config == null || config.get("CheckhandlerThreadDelay") == null)) {
                try {
                    Tr = Long.valueOf(Long.parseLong(config.get("CheckhandlerThreadDelay")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Tn.T("TXZDexClassLoader::" + Tr);
        }
        ClassLoader ret = parent;
        Iterator<String> it = T9.T(dexPath, new T9.Tr[]{T9.Tr.T("assets/dexs/", dexUnzipDir)}, Tr.longValue() > 0 ? Tr.longValue() : 5000).iterator();
        while (true) {
            ClassLoader ret2 = ret;
            if (!it.hasNext()) {
                return ret2;
            }
            String f = it.next();
            Log.d("TXZAppLoader1.0", "add dex file: " + f);
            ret = new Tr(f, dexUnzipDir, optimizedDirectory, librarySearchPath, ret2);
        }
    }

    public static String T(String dexPath) {
        return dexPath;
    }

    public Tr(String dexPath, String dexUnzipDir, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(T(dexPath), optimizedDirectory, librarySearchPath, T(dexPath, dexUnzipDir, optimizedDirectory, librarySearchPath, parent));
        this.f350T = librarySearchPath;
    }

    public String findLibrary(String name) {
        File fLibPath = new File(this.f350T, "lib" + name + ".so");
        if (fLibPath.exists()) {
            String ret = fLibPath.getAbsolutePath();
            fLibPath.setExecutable(true, false);
            Log.d("TXZAppLoader1.0", "find solibs[" + name + "] library: " + ret);
            return ret;
        }
        String def = super.findLibrary(name);
        Log.d("TXZAppLoader1.0", "find default[" + name + "] library: " + def);
        return def;
    }
}
