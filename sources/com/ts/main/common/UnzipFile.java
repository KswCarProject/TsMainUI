package com.ts.main.common;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipFile {
    public static long NowSize;
    public static long TotalSize;

    public static void decompressall(String srcPath, String dest) throws Exception {
        File file = new File(srcPath);
        if (!file.exists()) {
            throw new RuntimeException(String.valueOf(srcPath) + "error");
        }
        TotalSize = 0;
        NowSize = 0;
        ZipFile zf = new ZipFile(file);
        Enumeration entries = zf.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            if (entry.isDirectory()) {
                new File(String.valueOf(dest) + File.separator + entry.getName()).mkdirs();
            } else {
                System.out.println(entry.getName());
                File f = new File(String.valueOf(dest) + File.separator + entry.getName());
                if (!f.exists()) {
                    new File(f.getParentFile().getAbsolutePath()).mkdirs();
                }
                f.createNewFile();
                InputStream is = zf.getInputStream(entry);
                TotalSize = entry.getSize();
                FileOutputStream fos = new FileOutputStream(f);
                byte[] buf = new byte[8192];
                NowSize = 0;
                while (true) {
                    int count = is.read(buf);
                    if (count == -1) {
                        break;
                    }
                    fos.write(buf, 0, count);
                    NowSize += (long) count;
                    Log.i("tool", String.valueOf(NowSize) + "/" + TotalSize);
                }
                is.close();
                fos.close();
            }
        }
        zf.close();
    }

    public static void decompress(String srcPath, String pathNeedtoUncompress, String dest) throws Exception {
        File file = new File(srcPath);
        if (!file.exists()) {
            throw new RuntimeException(String.valueOf(srcPath) + "error");
        }
        ZipFile zf = new ZipFile(file);
        Enumeration entries = zf.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            if (entry.isDirectory()) {
                if (pathNeedtoUncompress.equals(entry.getName())) {
                }
            }
            if (entry.getName().startsWith(pathNeedtoUncompress)) {
                System.out.println(entry.getName());
                File f = new File(String.valueOf(dest) + File.separator + entry.getName().substring(entry.getName().lastIndexOf("/")));
                if (!f.exists()) {
                    new File(f.getParentFile().getAbsolutePath()).mkdirs();
                }
                f.createNewFile();
                InputStream is = zf.getInputStream(entry);
                TotalSize = entry.getSize();
                FileOutputStream fos = new FileOutputStream(f);
                byte[] buf = new byte[8192];
                NowSize = 0;
                while (true) {
                    int count = is.read(buf);
                    if (count == -1) {
                        break;
                    }
                    NowSize += (long) count;
                    fos.write(buf, 0, count);
                }
                is.close();
                fos.close();
            }
        }
        zf.close();
    }
}
