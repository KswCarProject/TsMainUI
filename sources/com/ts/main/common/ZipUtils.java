package com.ts.main.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    public static final String TAG = "ZIP";

    public static void ZipFolder(String srcFileString, String zipFileString) throws Exception {
        ZipOutputStream outZip = new ZipOutputStream(new FileOutputStream(zipFileString));
        File file = new File(srcFileString);
        ZipFiles(String.valueOf(file.getParent()) + File.separator, file.getName(), outZip);
        outZip.finish();
        outZip.close();
    }

    private static void ZipFiles(String folderString, String fileString, ZipOutputStream zipOutputSteam) throws Exception {
        if (zipOutputSteam != null) {
            File file = new File(String.valueOf(folderString) + fileString);
            if (file.isFile()) {
                ZipEntry zipEntry = new ZipEntry(fileString);
                FileInputStream inputStream = new FileInputStream(file);
                zipOutputSteam.putNextEntry(zipEntry);
                byte[] buffer = new byte[4096];
                while (true) {
                    int len = inputStream.read(buffer);
                    if (len == -1) {
                        zipOutputSteam.closeEntry();
                        return;
                    }
                    zipOutputSteam.write(buffer, 0, len);
                }
            } else {
                String[] fileList = file.list();
                if (fileList.length <= 0) {
                    zipOutputSteam.putNextEntry(new ZipEntry(String.valueOf(fileString) + File.separator));
                    zipOutputSteam.closeEntry();
                }
                for (int i = 0; i < fileList.length; i++) {
                    ZipFiles(folderString, String.valueOf(fileString) + File.separator + fileList[i], zipOutputSteam);
                }
            }
        }
    }

    public static InputStream UpZip(String zipFileString, String fileString) throws Exception {
        ZipFile zipFile = new ZipFile(zipFileString);
        return zipFile.getInputStream(zipFile.getEntry(fileString));
    }

    public static List<File> GetFileList(String zipFileString, boolean bContainFolder, boolean bContainFile) throws Exception {
        List<File> fileList = new ArrayList<>();
        ZipInputStream inZip = new ZipInputStream(new FileInputStream(zipFileString));
        while (true) {
            ZipEntry zipEntry = inZip.getNextEntry();
            if (zipEntry == null) {
                inZip.close();
                return fileList;
            }
            String szName = zipEntry.getName();
            if (zipEntry.isDirectory()) {
                File folder = new File(szName.substring(0, szName.length() - 1));
                if (bContainFolder) {
                    fileList.add(folder);
                }
            } else {
                File file = new File(szName);
                if (bContainFile) {
                    fileList.add(file);
                }
            }
        }
    }
}
