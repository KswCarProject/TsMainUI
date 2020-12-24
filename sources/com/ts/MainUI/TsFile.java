package com.ts.MainUI;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.util.EncodingUtils;

public class TsFile {
    public static void writer(String filename, int[] nData) {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(filename));
            for (int writeInt : nData) {
                out.writeInt(writeInt);
            }
            out.close();
        } catch (IOException e) {
            System.out.println("problem writing " + filename);
        }
    }

    public static void ReaderByte(String filename, byte[] Data) {
        try {
            DataInputStream instr = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
            for (int i = 0; i < Data.length; i++) {
                Data[i] = instr.readByte();
            }
            instr.close();
        } catch (IOException e) {
            System.out.println("problem reading " + filename);
        }
    }

    public static void reader(String filename, int[] nData) {
        try {
            DataInputStream instr = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
            for (int i = 0; i < nData.length; i++) {
                nData[i] = instr.readInt();
            }
            instr.close();
        } catch (IOException e) {
            System.out.println("problem reading " + filename);
        }
    }

    public static boolean fileIsExists(String fileName) {
        try {
            if (!new File(fileName).exists()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void NewDir(String sDir) {
        File destDir = new File(sDir);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
    }

    public static void writeFileSdcardFile(String fileName, String write_str) throws IOException {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
            out.write(write_str.getBytes());
            out.close();
        } catch (IOException e) {
        }
    }

    public static String readFileSdcardFile(String fileName) throws IOException {
        String res = "";
        try {
            FileInputStream fin = new FileInputStream(fileName);
            byte[] buffer = new byte[fin.available()];
            fin.read(buffer);
            res = EncodingUtils.getString(buffer, "UTF-8");
            fin.close();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return res;
        }
    }

    public static void deleteFile(File oldPath) {
        if (oldPath.isDirectory()) {
            for (File file : oldPath.listFiles()) {
                deleteFile(file);
                file.delete();
            }
            return;
        }
        oldPath.delete();
    }

    public static void copyFile(String oldPath, String newPath) {
        int bytesum = 0;
        try {
            if (new File(oldPath).exists()) {
                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while (true) {
                    int byteread = inStream.read(buffer);
                    if (byteread == -1) {
                        inStream.close();
                        return;
                    }
                    bytesum += byteread;
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        }
    }

    public static void copyFolder(String oldPath, String newPath) {
        File temp;
        try {
            new File(newPath).mkdirs();
            String[] file = new File(oldPath).list();
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(String.valueOf(oldPath) + file[i]);
                } else {
                    temp = new File(String.valueOf(oldPath) + File.separator + file[i]);
                }
                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(String.valueOf(newPath) + "/" + temp.getName().toString());
                    byte[] b = new byte[5120];
                    while (true) {
                        int len = input.read(b);
                        if (len == -1) {
                            break;
                        }
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {
                    copyFolder(String.valueOf(oldPath) + "/" + file[i], String.valueOf(newPath) + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();
        }
    }
}
