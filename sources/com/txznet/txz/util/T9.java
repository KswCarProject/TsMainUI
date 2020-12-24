package com.txznet.txz.util;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.txznet.comm.Tr.Tr.Tn;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: Proguard */
public class T9 {
    public static void T(ClassLoader loader, String name, String libpath) {
        try {
            Field f = BaseDexClassLoader.class.getDeclaredField("pathList");
            f.setAccessible(true);
            Object pathList = f.get(loader);
            Field f2 = pathList.getClass().getDeclaredField(name);
            f2.setAccessible(true);
            Object fsobj = f2.get(pathList);
            if (fsobj instanceof File[]) {
                File[] fs = (File[]) fsobj;
                File[] nfs = new File[(fs.length + 1)];
                nfs[0] = new File(libpath);
                for (int i = 0; i < fs.length; i++) {
                    nfs[i + 1] = fs[i];
                }
                f2.set(pathList, nfs);
            } else if (fsobj instanceof String[]) {
                String[] fs2 = (String[]) fsobj;
                String[] nfs2 = new String[(fs2.length + 1)];
                nfs2[0] = libpath + "/";
                for (int i2 = 0; i2 < fs2.length; i2++) {
                    nfs2[i2 + 1] = fs2[i2];
                }
                f2.set(pathList, nfs2);
            } else if (fsobj instanceof List) {
                List fs3 = (List) fsobj;
                if (fs3.get(0) instanceof File) {
                    fs3.add(0, new File(libpath));
                } else if (fs3.get(0) instanceof String) {
                    fs3.add(0, libpath);
                }
            }
        } catch (Exception e) {
        }
    }

    @TargetApi(14)
    public static void T(ClassLoader loader, String libpath) {
        if (loader != null) {
            T(loader, "nativeLibraryDirectories", libpath);
            T(loader, "systemNativeLibraryDirectories", libpath);
            try {
                Field f = BaseDexClassLoader.class.getDeclaredField("pathList");
                f.setAccessible(true);
                Object pathList = f.get(loader);
                Field f2 = pathList.getClass().getDeclaredField("nativeLibraryPathElements");
                f2.setAccessible(true);
                Object elems = f2.get(pathList);
                Object elems_new = Array.newInstance(elems.getClass().getComponentType(), Array.getLength(elems) + 1);
                Constructor<?> con = elems.getClass().getComponentType().getDeclaredConstructors()[0];
                con.setAccessible(true);
                Array.set(elems_new, 0, con.newInstance(new Object[]{new File(libpath), true, null, null}));
                for (int i = 0; i < Array.getLength(elems); i++) {
                    Array.set(elems_new, i + 1, Array.get(elems, i));
                }
                f2.set(pathList, elems_new);
            } catch (Exception e) {
            }
            try {
                Field f3 = DexClassLoader.class.getDeclaredField("mLibPaths");
                f3.setAccessible(true);
                String[] mLibPaths = (String[]) f3.get(loader);
                String[] mLibPaths_new = new String[(mLibPaths.length + 1)];
                if (libpath.endsWith("/")) {
                    mLibPaths_new[0] = libpath;
                } else {
                    mLibPaths_new[0] = libpath + "/";
                }
                for (int i2 = 0; i2 < mLibPaths.length; i2++) {
                    mLibPaths_new[i2 + 1] = mLibPaths[i2];
                }
                f3.set(loader, mLibPaths_new);
            } catch (Exception e2) {
            }
            T(loader.getParent(), libpath);
        }
    }

    public static Object T(Object arrayLhs, Object arrayRhs) {
        if (arrayLhs.getClass().isArray()) {
            Class<?> localClass = arrayLhs.getClass().getComponentType();
            int i = Array.getLength(arrayLhs);
            int j = i + Array.getLength(arrayRhs);
            Object result = Array.newInstance(localClass, j);
            for (int k = 0; k < j; k++) {
                if (k < i) {
                    Array.set(result, k, Array.get(arrayLhs, k));
                } else {
                    Array.set(result, k, Array.get(arrayRhs, k - i));
                }
            }
            return result;
        }
        if (arrayLhs instanceof List) {
            try {
                Object newInstance = arrayLhs.getClass().newInstance();
                List lstResult = (List) newInstance;
                List<Object> lstRhs = (List) arrayRhs;
                for (Object o : (List) arrayLhs) {
                    lstResult.add(o);
                }
                for (Object o2 : lstRhs) {
                    lstResult.add(o2);
                }
                return newInstance;
            } catch (Exception e) {
            }
        }
        return arrayRhs;
    }

    public static void T(Object src, Object dst, String mem) {
        try {
            Field f = src.getClass().getDeclaredField(mem);
            f.setAccessible(true);
            f.set(src, T(f.get(dst), f.get(src)));
        } catch (Exception e) {
        }
    }

    public static void T(ClassLoader loader, ClassLoader dest, String libPath) {
        if (dest != loader) {
            try {
                Field f = BaseDexClassLoader.class.getDeclaredField("pathList");
                f.setAccessible(true);
                Object pathList_loader = f.get(loader);
                ArrayList<ClassLoader> loaders = new ArrayList<>();
                while (dest != null && dest != loader) {
                    loaders.add(dest);
                    dest = dest.getParent();
                }
                for (int i = loaders.size(); i > 0; i--) {
                    Object pathList_dest = f.get(loaders.get(i - 1));
                    T(pathList_loader, pathList_dest, "dexElements");
                    T(pathList_loader, pathList_dest, "nativeLibraryPathElements");
                    T(pathList_loader, pathList_dest, "nativeLibraryDirectories");
                    T(pathList_loader, pathList_dest, "systemNativeLibraryDirectories");
                }
                T(loader, libPath);
            } catch (Exception e) {
            }
        }
    }

    public static boolean T(String apkPath, String path) {
        boolean ret;
        try {
            ZipFile zipFile = new ZipFile(apkPath);
            if (zipFile.getEntry(path) != null) {
                ret = true;
            } else {
                ret = false;
            }
            zipFile.close();
            return ret;
        } catch (Exception e) {
            return false;
        }
    }

    static boolean T(T checkHandler, ZipFile zipFile, ZipEntry entry, String unzipPath, long delayCheck) {
        try {
            File f = new File(unzipPath);
            if (f.exists()) {
                File chk = new File(unzipPath + ".chk");
                if (chk.exists()) {
                    Properties properties = new Properties();
                    FileInputStream fis = new FileInputStream(chk);
                    properties.load(fis);
                    if (!properties.keySet().contains("size") || ((long) Integer.valueOf(properties.getProperty("size")).intValue()) != f.length()) {
                        chk.delete();
                        fis.close();
                    } else {
                        Tn.Ty("no need unzip new file: " + entry.getName());
                        Ty(checkHandler, zipFile, entry, unzipPath, delayCheck);
                        return true;
                    }
                } else if (f.length() == entry.getSize()) {
                    Tn.Ty("no need unzip file: " + entry.getName());
                    Tr(checkHandler, zipFile, entry, unzipPath, delayCheck);
                    return true;
                }
            }
            Tn.T("begin unzip " + entry.getName() + ": size=" + entry.getCompressedSize() + "/" + entry.getSize() + ",time=" + entry.getTime() + ",crc=" + entry.getCrc());
            f.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(f);
            InputStream in = zipFile.getInputStream(entry);
            byte[] buf = new byte[1048576];
            while (true) {
                int l = in.read(buf);
                if (l >= 0) {
                    out.write(buf, 0, l);
                } else {
                    out.close();
                    in.close();
                    return true;
                }
            }
        } catch (IOException e) {
            com.txznet.T.T.T5();
            throw new RuntimeException("Load app error: unzip file " + entry.getName() + " error: " + e.getMessage());
        }
    }

    private static void Ty(final T checkHandler, ZipFile zipFile, ZipEntry entry, final String unzipPath, long delayCheck) {
        checkHandler.postDelayed(new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:64:0x0210 A[SYNTHETIC, Splitter:B:64:0x0210] */
            /* JADX WARNING: Removed duplicated region for block: B:69:0x021b A[SYNTHETIC, Splitter:B:69:0x021b] */
            /* JADX WARNING: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r18 = this;
                    r4 = 0
                    java.io.File r8 = new java.io.File
                    r0 = r18
                    java.lang.String r14 = r4
                    r8.<init>(r14)
                    java.io.File r3 = new java.io.File
                    java.lang.StringBuilder r14 = new java.lang.StringBuilder
                    r14.<init>()
                    r0 = r18
                    java.lang.String r15 = r4
                    java.lang.StringBuilder r14 = r14.append(r15)
                    java.lang.String r15 = ".chk"
                    java.lang.StringBuilder r14 = r14.append(r15)
                    java.lang.String r14 = r14.toString()
                    r3.<init>(r14)
                    r1 = 1
                    boolean r14 = r3.exists()     // Catch:{ Exception -> 0x01e3 }
                    if (r14 != 0) goto L_0x0057
                    java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e3 }
                    r14.<init>()     // Catch:{ Exception -> 0x01e3 }
                    java.lang.String r15 = "chk unzip file not exist: "
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x01e3 }
                    r0 = r18
                    java.lang.String r15 = r4     // Catch:{ Exception -> 0x01e3 }
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x01e3 }
                    java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x01e3 }
                    com.txznet.comm.Tr.Tr.Tn.Tn(r14)     // Catch:{ Exception -> 0x01e3 }
                    r8.delete()     // Catch:{ Exception -> 0x01e3 }
                    r0 = r18
                    com.txznet.txz.util.T9$T r14 = r1     // Catch:{ Exception -> 0x01e3 }
                    r15 = 1
                    r14.f899T = r15     // Catch:{ Exception -> 0x01e3 }
                    if (r4 == 0) goto L_0x0056
                    r4.close()     // Catch:{ IOException -> 0x021f }
                L_0x0056:
                    return
                L_0x0057:
                    java.util.Properties r12 = new java.util.Properties     // Catch:{ Exception -> 0x01e3 }
                    r12.<init>()     // Catch:{ Exception -> 0x01e3 }
                    java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Exception -> 0x01e3 }
                    r5.<init>(r3)     // Catch:{ Exception -> 0x01e3 }
                    r12.load(r5)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.util.Set r10 = r12.keySet()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r14 = "size"
                    boolean r14 = r10.contains(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    if (r14 == 0) goto L_0x0088
                    java.lang.String r14 = "key"
                    boolean r14 = r10.contains(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    if (r14 == 0) goto L_0x0088
                    java.lang.String r14 = "begin"
                    boolean r14 = r10.contains(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    if (r14 == 0) goto L_0x0088
                    java.lang.String r14 = "end"
                    boolean r14 = r10.contains(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    if (r14 != 0) goto L_0x00b6
                L_0x0088:
                    java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r14.<init>()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r15 = "chk file err: "
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r0 = r18
                    java.lang.String r15 = r4     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    com.txznet.comm.Tr.Tr.Tn.Tn(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r8.delete()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r3.delete()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r0 = r18
                    com.txznet.txz.util.T9$T r14 = r1     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r15 = 1
                    r14.f899T = r15     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    if (r5 == 0) goto L_0x00b4
                    r5.close()     // Catch:{ IOException -> 0x0222 }
                L_0x00b4:
                    r4 = r5
                    goto L_0x0056
                L_0x00b6:
                    java.lang.String r14 = "size"
                    java.lang.String r14 = r12.getProperty(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.Integer r14 = java.lang.Integer.valueOf(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    int r13 = r14.intValue()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    long r14 = (long) r13     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    long r16 = r8.length()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
                    if (r14 == 0) goto L_0x00fc
                    java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r14.<init>()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r15 = "check unzip file size not same: "
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r0 = r18
                    java.lang.String r15 = r4     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    com.txznet.comm.Tr.Tr.Tn.Tn(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r8.delete()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r3.delete()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r0 = r18
                    com.txznet.txz.util.T9$T r14 = r1     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r15 = 1
                    r14.f899T = r15     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    if (r5 == 0) goto L_0x00f9
                    r5.close()     // Catch:{ IOException -> 0x0225 }
                L_0x00f9:
                    r4 = r5
                    goto L_0x0056
                L_0x00fc:
                    java.lang.String r14 = "begin"
                    java.lang.String r2 = r12.getProperty(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r14 = "end"
                    java.lang.String r7 = r12.getProperty(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r14 = "key"
                    java.lang.String r9 = r12.getProperty(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r14 = com.txznet.comm.Tn.T.Tr     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    int r14 = com.txznet.txz.util.T9.Ty(r14, r2)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    if (r14 < 0) goto L_0x011e
                    java.lang.String r14 = com.txznet.comm.Tn.T.Tr     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    int r14 = com.txznet.txz.util.T9.Ty(r7, r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    if (r14 >= 0) goto L_0x014d
                L_0x011e:
                    java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r14.<init>()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r15 = "check unzip file version not right: "
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r0 = r18
                    java.lang.String r15 = r4     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    com.txznet.comm.Tr.Tr.Tn.Tn(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r8.delete()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r3.delete()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r0 = r18
                    com.txznet.txz.util.T9$T r14 = r1     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r15 = 1
                    r14.f899T = r15     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    if (r5 == 0) goto L_0x014a
                    r5.close()     // Catch:{ IOException -> 0x0228 }
                L_0x014a:
                    r4 = r5
                    goto L_0x0056
                L_0x014d:
                    java.lang.String r11 = com.txznet.txz.util.Tn.T((java.io.File) r8)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r14 = r9.toLowerCase()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r15.<init>()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r16 = "file"
                    java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.StringBuilder r15 = r15.append(r11)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.StringBuilder r15 = r15.append(r2)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.StringBuilder r15 = r15.append(r7)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    long r16 = r8.length()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r16 = r8.getName()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.StringBuilder r15 = r15.append(r16)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r15 = r15.toString()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r15 = com.txznet.txz.util.Tn.T((java.lang.String) r15)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r15 = r15.toLowerCase()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    boolean r14 = r14.equals(r15)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    if (r14 != 0) goto L_0x01bd
                    java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r14.<init>()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r15 = "check unzip file key not right: "
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r0 = r18
                    java.lang.String r15 = r4     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    com.txznet.comm.Tr.Tr.Tn.Tn(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r8.delete()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r3.delete()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r0 = r18
                    com.txznet.txz.util.T9$T r14 = r1     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r15 = 1
                    r14.f899T = r15     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    if (r5 == 0) goto L_0x01ba
                    r5.close()     // Catch:{ IOException -> 0x022b }
                L_0x01ba:
                    r4 = r5
                    goto L_0x0056
                L_0x01bd:
                    java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r14.<init>()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r15 = "check unzip file success: "
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    r0 = r18
                    java.lang.String r15 = r4     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    com.txznet.comm.Tr.Tr.Tn.Tn(r14)     // Catch:{ Exception -> 0x0232, all -> 0x022f }
                    if (r5 == 0) goto L_0x0235
                    r5.close()     // Catch:{ IOException -> 0x01df }
                    r4 = r5
                    goto L_0x0056
                L_0x01df:
                    r14 = move-exception
                    r4 = r5
                    goto L_0x0056
                L_0x01e3:
                    r6 = move-exception
                L_0x01e4:
                    java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0218 }
                    r14.<init>()     // Catch:{ all -> 0x0218 }
                    java.lang.String r15 = "check unzip file exception="
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x0218 }
                    r0 = r18
                    java.lang.String r15 = r4     // Catch:{ all -> 0x0218 }
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x0218 }
                    java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x0218 }
                    com.txznet.comm.Tr.Tr.Tn.Tn(r14)     // Catch:{ all -> 0x0218 }
                    r8.delete()     // Catch:{ all -> 0x0218 }
                    r3.delete()     // Catch:{ all -> 0x0218 }
                    r0 = r18
                    com.txznet.txz.util.T9$T r14 = r1     // Catch:{ all -> 0x0218 }
                    r15 = 1
                    r14.f899T = r15     // Catch:{ all -> 0x0218 }
                    r6.printStackTrace()     // Catch:{ all -> 0x0218 }
                    if (r4 == 0) goto L_0x0056
                    r4.close()     // Catch:{ IOException -> 0x0215 }
                    goto L_0x0056
                L_0x0215:
                    r14 = move-exception
                    goto L_0x0056
                L_0x0218:
                    r14 = move-exception
                L_0x0219:
                    if (r4 == 0) goto L_0x021e
                    r4.close()     // Catch:{ IOException -> 0x022d }
                L_0x021e:
                    throw r14
                L_0x021f:
                    r14 = move-exception
                    goto L_0x0056
                L_0x0222:
                    r14 = move-exception
                    goto L_0x00b4
                L_0x0225:
                    r14 = move-exception
                    goto L_0x00f9
                L_0x0228:
                    r14 = move-exception
                    goto L_0x014a
                L_0x022b:
                    r14 = move-exception
                    goto L_0x01ba
                L_0x022d:
                    r15 = move-exception
                    goto L_0x021e
                L_0x022f:
                    r14 = move-exception
                    r4 = r5
                    goto L_0x0219
                L_0x0232:
                    r6 = move-exception
                    r4 = r5
                    goto L_0x01e4
                L_0x0235:
                    r4 = r5
                    goto L_0x0056
                */
                throw new UnsupportedOperationException("Method not decompiled: com.txznet.txz.util.T9.AnonymousClass1.run():void");
            }
        }, delayCheck);
    }

    /* access modifiers changed from: private */
    public static int Ty(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < v1.length; i++) {
            int compare = Integer.valueOf(v1[i]).intValue() - Integer.valueOf(v2[i]).intValue();
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    /* compiled from: Proguard */
    public static class Tr {

        /* renamed from: T  reason: collision with root package name */
        String f900T;
        boolean T9 = false;
        boolean Tn = false;
        String Tr;
        boolean Ty = false;

        /* access modifiers changed from: package-private */
        public void T() {
            if (!this.T9) {
                if (!this.f900T.endsWith("/")) {
                    this.f900T += "/";
                }
                if (!this.Tr.endsWith("/")) {
                    this.Tr += "/";
                }
            }
        }

        public static Tr T(String dataPath, String unzipPath) {
            Tr opt = new Tr();
            opt.f900T = dataPath;
            opt.Tr = unzipPath;
            opt.Tn = false;
            opt.Ty = false;
            opt.T9 = false;
            opt.T();
            return opt;
        }

        /* access modifiers changed from: package-private */
        public String T(String entryName) {
            if (this.T9) {
                if (entryName.equals(this.f900T)) {
                    return this.Tr;
                }
                return null;
            } else if (!this.Ty) {
                String fileName = entryName.substring(entryName.lastIndexOf(47) + 1);
                if (entryName.equals(this.f900T + fileName)) {
                    return this.Tr + fileName;
                }
                return null;
            } else if (!entryName.startsWith(this.f900T)) {
                return null;
            } else {
                if (this.Tn) {
                    return this.Tr + entryName.substring(this.f900T.length());
                }
                return this.Tr + entryName.substring(entryName.lastIndexOf(47) + 1);
            }
        }
    }

    /* compiled from: Proguard */
    static class T extends Handler {

        /* renamed from: T  reason: collision with root package name */
        public boolean f899T = false;
        byte[] Tr = new byte[1048576];
        byte[] Ty = new byte[1048576];

        public T(Looper looper) {
            super(looper);
        }
    }

    public static List<String> T(String apkPath, Tr[] options, long delayCheck) {
        try {
            List<String> ret = new ArrayList<>();
            final HandlerThread checkThread = new HandlerThread("checkThread");
            checkThread.start();
            final T checkHandler = new T(checkThread.getLooper());
            final ZipFile zipFile = new ZipFile(apkPath);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                for (Tr opt : options) {
                    String unzipFile = opt.T(entry.getName());
                    if (unzipFile != null) {
                        T(checkHandler, zipFile, entry, unzipFile, delayCheck);
                        ret.add(unzipFile);
                    }
                }
            }
            checkHandler.postDelayed(new Runnable() {
                public void run() {
                    try {
                        zipFile.close();
                    } catch (IOException e) {
                    }
                    if (checkHandler.f899T) {
                        Tn.Tn("check unzip file failed, restarting...");
                        com.txznet.T.T.TE();
                    } else {
                        Tn.Tr("check unzip file end");
                    }
                    checkThread.quit();
                }
            }, delayCheck);
            return ret;
        } catch (IOException e) {
            com.txznet.T.T.T5();
            throw new RuntimeException("Load app error: unzip [" + apkPath + "] files error: " + e.getMessage());
        }
    }

    public static void Tr(final T checkHandler, final ZipFile zipFile, final ZipEntry entry, final String unzipPath, long delayCheck) {
        checkHandler.postDelayed(new Runnable() {
            /* JADX WARNING: Code restructure failed: missing block: B:47:0x0136, code lost:
                r4 = e;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:48:0x0137, code lost:
                r7 = r8;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
                r7.close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
                r9.close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:72:0x017d, code lost:
                r14 = th;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:73:0x017e, code lost:
                r7 = r8;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Removed duplicated region for block: B:12:0x0065  */
            /* JADX WARNING: Removed duplicated region for block: B:61:0x0168 A[SYNTHETIC, Splitter:B:61:0x0168] */
            /* JADX WARNING: Removed duplicated region for block: B:64:0x016d A[SYNTHETIC, Splitter:B:64:0x016d] */
            /* JADX WARNING: Removed duplicated region for block: B:72:0x017d A[ExcHandler: all (th java.lang.Throwable), PHI: r9 
              PHI: (r9v3 'inZip' java.io.InputStream) = (r9v0 'inZip' java.io.InputStream), (r9v5 'inZip' java.io.InputStream), (r9v5 'inZip' java.io.InputStream), (r9v5 'inZip' java.io.InputStream), (r9v5 'inZip' java.io.InputStream) binds: [B:16:0x0095, B:41:0x012a, B:45:0x0131, B:46:?, B:42:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:16:0x0095] */
            /* JADX WARNING: Removed duplicated region for block: B:87:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r18 = this;
                    r1 = 1
                    java.io.File r5 = new java.io.File
                    r0 = r18
                    java.lang.String r14 = r4
                    r5.<init>(r14)
                    r7 = 0
                    r9 = 0
                    long r14 = r5.length()     // Catch:{ Exception -> 0x0180 }
                    r0 = r18
                    java.util.zip.ZipEntry r0 = r3     // Catch:{ Exception -> 0x0180 }
                    r16 = r0
                    long r16 = r16.getSize()     // Catch:{ Exception -> 0x0180 }
                    int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
                    if (r14 == 0) goto L_0x008a
                    r1 = 0
                    java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0180 }
                    r14.<init>()     // Catch:{ Exception -> 0x0180 }
                    java.lang.String r15 = "check unzip file not same size["
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0180 }
                    long r15 = r5.length()     // Catch:{ Exception -> 0x0180 }
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0180 }
                    java.lang.String r15 = "/"
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0180 }
                    r0 = r18
                    java.util.zip.ZipEntry r15 = r3     // Catch:{ Exception -> 0x0180 }
                    long r15 = r15.getSize()     // Catch:{ Exception -> 0x0180 }
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0180 }
                    java.lang.String r15 = ": "
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0180 }
                    r0 = r18
                    java.lang.String r15 = r4     // Catch:{ Exception -> 0x0180 }
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0180 }
                    java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x0180 }
                    com.txznet.comm.Tr.Tr.Tn.Tn(r14)     // Catch:{ Exception -> 0x0180 }
                L_0x0059:
                    if (r7 == 0) goto L_0x005e
                    r7.close()     // Catch:{ IOException -> 0x0171 }
                L_0x005e:
                    if (r9 == 0) goto L_0x0063
                    r9.close()     // Catch:{ IOException -> 0x0174 }
                L_0x0063:
                    if (r1 != 0) goto L_0x0089
                    java.lang.StringBuilder r14 = new java.lang.StringBuilder
                    r14.<init>()
                    java.lang.String r15 = "check unzip file not same: "
                    java.lang.StringBuilder r14 = r14.append(r15)
                    r0 = r18
                    java.lang.String r15 = r4
                    java.lang.StringBuilder r14 = r14.append(r15)
                    java.lang.String r14 = r14.toString()
                    com.txznet.comm.Tr.Tr.Tn.Tn(r14)
                    r5.delete()
                    r0 = r18
                    com.txznet.txz.util.T9$T r14 = r1
                    r15 = 1
                    r14.f899T = r15
                L_0x0089:
                    return
                L_0x008a:
                    java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0180 }
                    r0 = r18
                    java.lang.String r14 = r4     // Catch:{ Exception -> 0x0180 }
                    r8.<init>(r14)     // Catch:{ Exception -> 0x0180 }
                    r0 = r18
                    java.util.zip.ZipFile r14 = r2     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    r0 = r18
                    java.util.zip.ZipEntry r15 = r3     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    java.io.InputStream r9 = r14.getInputStream(r15)     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    r0 = r18
                    com.txznet.txz.util.T9$T r14 = r1     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    byte[] r2 = r14.Tr     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    r0 = r18
                    com.txznet.txz.util.T9$T r14 = r1     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    byte[] r3 = r14.Ty     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    r12 = 0
                    r13 = 0
                L_0x00ad:
                    r10 = 0
                L_0x00ae:
                    if (r10 != 0) goto L_0x00b8
                    int r14 = r2.length     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    int r14 = r14 + 0
                    int r10 = r8.read(r2, r12, r14)     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    goto L_0x00ae
                L_0x00b8:
                    if (r12 != 0) goto L_0x00be
                    if (r10 >= 0) goto L_0x00be
                    r7 = r8
                    goto L_0x0059
                L_0x00be:
                    int r12 = r10 + 0
                L_0x00c0:
                    if (r13 >= r12) goto L_0x00e9
                    int r14 = r12 - r13
                    int r11 = r9.read(r3, r13, r14)     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    if (r11 >= 0) goto L_0x00ee
                    java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    r14.<init>()     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    java.lang.String r15 = "check unzip read zip file error: "
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    r0 = r18
                    java.util.zip.ZipEntry r15 = r3     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    java.lang.String r15 = r15.getName()     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    com.txznet.comm.Tr.Tr.Tn.Tn(r14)     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    r1 = 0
                L_0x00e9:
                    if (r1 != 0) goto L_0x00f0
                    r7 = r8
                    goto L_0x0059
                L_0x00ee:
                    int r13 = r13 + r11
                    goto L_0x00c0
                L_0x00f0:
                    r6 = 0
                L_0x00f1:
                    if (r6 >= r12) goto L_0x011e
                    byte r14 = r2[r6]     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    byte r15 = r3[r6]     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    if (r14 == r15) goto L_0x0123
                    r1 = 0
                    java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    r14.<init>()     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    java.lang.String r15 = "check unzip file not same byte at "
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    java.lang.StringBuilder r14 = r14.append(r6)     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    java.lang.String r15 = ": "
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    r0 = r18
                    java.lang.String r15 = r4     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    com.txznet.comm.Tr.Tr.Tn.Tn(r14)     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                L_0x011e:
                    if (r1 != 0) goto L_0x0126
                    r7 = r8
                    goto L_0x0059
                L_0x0123:
                    int r6 = r6 + 1
                    goto L_0x00f1
                L_0x0126:
                    r13 = 0
                    r12 = r13
                    r14 = 20
                    java.lang.Thread.sleep(r14)     // Catch:{ Exception -> 0x012e, all -> 0x017d }
                    goto L_0x00ad
                L_0x012e:
                    r4 = move-exception
                    java.lang.String r14 = "sleep exception"
                    com.txznet.comm.Tr.Tr.Tn.Tn(r14)     // Catch:{ Exception -> 0x0136, all -> 0x017d }
                    goto L_0x00ad
                L_0x0136:
                    r4 = move-exception
                    r7 = r8
                L_0x0138:
                    r1 = 0
                    java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0165 }
                    r14.<init>()     // Catch:{ all -> 0x0165 }
                    java.lang.String r15 = "check unzip file exception: "
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x0165 }
                    r0 = r18
                    java.lang.String r15 = r4     // Catch:{ all -> 0x0165 }
                    java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x0165 }
                    java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x0165 }
                    com.txznet.comm.Tr.Tr.Tn.Tn(r14)     // Catch:{ all -> 0x0165 }
                    r4.printStackTrace()     // Catch:{ all -> 0x0165 }
                    if (r7 == 0) goto L_0x015b
                    r7.close()     // Catch:{ IOException -> 0x0177 }
                L_0x015b:
                    if (r9 == 0) goto L_0x0063
                    r9.close()     // Catch:{ IOException -> 0x0162 }
                    goto L_0x0063
                L_0x0162:
                    r14 = move-exception
                    goto L_0x0063
                L_0x0165:
                    r14 = move-exception
                L_0x0166:
                    if (r7 == 0) goto L_0x016b
                    r7.close()     // Catch:{ IOException -> 0x0179 }
                L_0x016b:
                    if (r9 == 0) goto L_0x0170
                    r9.close()     // Catch:{ IOException -> 0x017b }
                L_0x0170:
                    throw r14
                L_0x0171:
                    r14 = move-exception
                    goto L_0x005e
                L_0x0174:
                    r14 = move-exception
                    goto L_0x0063
                L_0x0177:
                    r14 = move-exception
                    goto L_0x015b
                L_0x0179:
                    r15 = move-exception
                    goto L_0x016b
                L_0x017b:
                    r15 = move-exception
                    goto L_0x0170
                L_0x017d:
                    r14 = move-exception
                    r7 = r8
                    goto L_0x0166
                L_0x0180:
                    r4 = move-exception
                    goto L_0x0138
                */
                throw new UnsupportedOperationException("Method not decompiled: com.txznet.txz.util.T9.AnonymousClass3.run():void");
            }
        }, delayCheck);
    }
}
