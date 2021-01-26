package com.autochips.camera.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class StorageUtil {
    private static final String IMAGE_SUFFIX = ".jpg";
    private static final int MAX_FILE_NUM = 149;
    private static final int MAX_NORMAL_SIZE = 8192;
    private static final int MAX_URGENT_SIZE = 2048;
    private static final int MIN_IMAGE_SIZE = 2;
    private static final int MIN_NORMAL_SIZE = 50;
    private static final int MIN_URGENT_SIZE = 50;
    private static final String MNT_MEDIA_RW = "/mnt/media_rw";
    private static final int REUSE_NORMAL_SIZE = 100;
    private static final String ROOT_DIR = "AtcCamera";
    private static final String SDCARD_MARK = "sdcard";
    private static final String STORAGE = "/storage";
    private static final String STORAGE_NUM = "atccamera.storage.num";
    private static final String TAG = "StorageUtil";
    private static final String UDISK1_MARK = "udisk1";
    private static final String UDISK2_MARK = "udisk2";
    private static final String VIDEO_SUFFIX = ".ts";
    public static String sCurrentPath;
    private final String IMAGE_DIR;
    /* access modifiers changed from: private */
    public final String NORMAL_DIR;
    private final String URGENT_DIR;
    private String mCameraTag;
    private Context mContext;
    private SpaceReusePolicy mImagePolicy = new DefaultImageReusePolicy();
    private SpaceReusePolicy mNormalPolicy = new DefaultNormalReusePolicy();
    private StorageManager mSm;
    private SpaceReusePolicy mUrgentPolicy = new DefaultUrgentReusePolicy();

    interface SpaceReusePolicy {
        boolean enoughSpaceAfterReuse(File file);

        void ensureFileNumWithinLimits(File file);
    }

    public StorageUtil(Context context, String cameraId) {
        this.mSm = (StorageManager) context.getSystemService("storage");
        this.mContext = context;
        this.NORMAL_DIR = "normal_cam" + cameraId;
        this.URGENT_DIR = "urgent_cam" + cameraId;
        this.IMAGE_DIR = "image_cam" + cameraId;
        this.mCameraTag = "_cam" + cameraId;
    }

    public boolean setNormalPolicy(SpaceReusePolicy policy) {
        if (policy == null) {
            return false;
        }
        this.mNormalPolicy = policy;
        return true;
    }

    public boolean setUrgentPolicy(SpaceReusePolicy policy) {
        if (policy == null) {
            return false;
        }
        this.mUrgentPolicy = policy;
        return true;
    }

    public boolean setImagePolicy(SpaceReusePolicy policy) {
        if (policy == null) {
            return false;
        }
        this.mImagePolicy = policy;
        return true;
    }

    private SpaceReusePolicy getSpaceReusePolicy(String type) {
        if (this.NORMAL_DIR.equalsIgnoreCase(type)) {
            return this.mNormalPolicy;
        }
        if (this.URGENT_DIR.equalsIgnoreCase(type)) {
            return this.mUrgentPolicy;
        }
        return this.mImagePolicy;
    }

    private boolean isVolumeMounted(String volumeMark) {
        String[] paths = this.mSm.getVolumePaths();
        if (paths == null || paths.length <= 0) {
            return false;
        }
        int len = paths.length;
        for (int i = 1; i < len; i++) {
            Log.d("HAHA", "i = " + i + " , path = " + paths[i]);
            if (paths[i] != null && paths[i].contains(volumeMark)) {
                LogUtil.i(TAG, "path: " + paths[i], new Object[0]);
                if ("mounted".equals(this.mSm.getVolumeState(paths[i]))) {
                    sCurrentPath = toPrivatePath(paths[i]);
                    Log.d("HAHA", "sCurrentPath = " + sCurrentPath);
                    return true;
                }
            }
        }
        return false;
    }

    public static String toPublicPath(String path) {
        if (path != null) {
            return path.replaceFirst(MNT_MEDIA_RW, STORAGE);
        }
        return null;
    }

    public static String toPrivatePath(String path) {
        if (path != null) {
            return path.replaceFirst(STORAGE, MNT_MEDIA_RW);
        }
        return null;
    }

    public int getDirSize(File dir) {
        int totalSize = 0;
        if (dir != null && dir.exists()) {
            File[] files = dir.listFiles();
            if (files == null || files.length <= 0) {
                LogUtil.d(TAG, "no file in " + dir.getAbsolutePath(), new Object[0]);
            } else {
                for (File f : files) {
                    if (f.isFile()) {
                        totalSize += (int) (f.length() / 1024);
                    } else {
                        totalSize += getDirSize(f);
                    }
                }
            }
        }
        return totalSize;
    }

    /* access modifiers changed from: private */
    public File getRootDir() throws NoSDCardMountedException, NoUdiskMountedException, FileAlreadyExistException {
        int num = SystemProperties.getInt(STORAGE_NUM, 2);
        if (num == 1) {
            if (!isVolumeMounted(UDISK1_MARK)) {
                throw new NoUdiskMountedException();
            }
        } else if (num == 2) {
            if (!isVolumeMounted(UDISK2_MARK)) {
                throw new NoUdiskMountedException();
            }
        } else if (!isVolumeMounted(SDCARD_MARK)) {
            throw new NoSDCardMountedException();
        }
        File rootFile = new File(sCurrentPath, ROOT_DIR);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        } else if (rootFile.isFile()) {
            throw new FileAlreadyExistException(ROOT_DIR);
        }
        return rootFile;
    }

    public File getOutputFile(String subDir) throws NoSDCardMountedException, NoUdiskMountedException, SpaceNotEnoughException, FileAlreadyExistException {
        String subDir2 = String.valueOf(subDir) + this.mCameraTag;
        File outputDir = new File(getRootDir(), subDir2);
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        } else if (outputDir.isFile()) {
            throw new FileAlreadyExistException(subDir2);
        }
        SpaceReusePolicy policy = getSpaceReusePolicy(subDir2);
        policy.ensureFileNumWithinLimits(outputDir);
        if (policy.enoughSpaceAfterReuse(outputDir)) {
            return createFile(outputDir, subDir2);
        }
        throw new SpaceNotEnoughException();
    }

    private File createFile(File parentDir, String fileType) {
        File file;
        LogUtil.d(TAG, "createFile()", new Object[0]);
        if (parentDir == null) {
            LogUtil.d(TAG, "createFile fail, parentDir is null", new Object[0]);
            return null;
        }
        String currentTimeStr = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        if (this.IMAGE_DIR.equalsIgnoreCase(fileType)) {
            file = new File(parentDir, String.valueOf(currentTimeStr) + ".jpg");
        } else {
            file = new File(parentDir, String.valueOf(currentTimeStr) + VIDEO_SUFFIX);
        }
        LogUtil.d(TAG, "createNewFile start", new Object[0]);
        try {
            file.createNewFile();
        } catch (IOException e) {
            LogUtil.e(TAG, "createNewFile error ," + e, new Object[0]);
        }
        LogUtil.d(TAG, "createNewFile end", new Object[0]);
        updateFileWithMediaScanner(file);
        return file;
    }

    public void updateFileWithMediaScanner(File file) {
        File updateFile = new File(file.getAbsolutePath().replaceFirst(MNT_MEDIA_RW, STORAGE));
        LogUtil.d(TAG, "updateFile:" + updateFile, new Object[0]);
        Intent scanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        scanIntent.setData(Uri.fromFile(updateFile));
        this.mContext.sendBroadcast(scanIntent);
    }

    /* access modifiers changed from: private */
    public void getAllFilesOfDir(File dir, List<FileWithLastModifiedTime> fileList) {
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File f : files) {
                if (f.isFile()) {
                    FileWithLastModifiedTime flmt = new FileWithLastModifiedTime((FileWithLastModifiedTime) null);
                    flmt.file = f;
                    long creationTime = 0;
                    try {
                        creationTime = f.lastModified();
                    } catch (Exception e) {
                        LogUtil.d(TAG, "getAllFilesOfDir fail: " + e, new Object[0]);
                    }
                    flmt.lastModifiedTime = creationTime;
                    fileList.add(flmt);
                }
            }
        }
    }

    public boolean reuseStorageSpace(File reusePath, File exceptFile) {
        int freeSize = (int) ((reusePath.getFreeSpace() / 1024) / 1024);
        int spaceUsed = getDirSize(reusePath) / 1024;
        if (spaceUsed > 8092) {
            deleteOldFiles(reusePath, exceptFile, (spaceUsed - 8192) + 100);
        } else if (freeSize < 100) {
            deleteOldFiles(reusePath, exceptFile, 100 - freeSize);
        }
        int freeSize2 = (int) ((reusePath.getFreeSpace() / 1024) / 1024);
        if (getDirSize(reusePath) / 1024 > 8142 || freeSize2 < 50) {
            return false;
        }
        return true;
    }

    private static class FileComparator implements Comparator<FileWithLastModifiedTime> {
        private FileComparator() {
        }

        /* synthetic */ FileComparator(FileComparator fileComparator) {
            this();
        }

        public int compare(FileWithLastModifiedTime file1, FileWithLastModifiedTime file2) {
            if (file1 == file2) {
                return 0;
            }
            if (file1 == null && file2 != null) {
                return -1;
            }
            if (file1 != null && file2 == null) {
                return 1;
            }
            if (file1.lastModifiedTime > file2.lastModifiedTime) {
                return 1;
            }
            if (file1.lastModifiedTime < file2.lastModifiedTime) {
                return -1;
            }
            return 0;
        }
    }

    public boolean deleteOldFiles(File dir, File exceptFile, int amount) {
        if (dir == null || dir.isFile() || !dir.exists()) {
            Log.d("HAHA", "deleteEarliestFile fail, dir is null or is not a directory");
            return false;
        }
        List<FileWithLastModifiedTime> fileList = new LinkedList<>();
        getAllFilesOfDir(dir, fileList);
        if (fileList.size() == 0 || (fileList.size() == 1 && exceptFile != null && fileList.get(0).file.getAbsolutePath().equals(exceptFile.getAbsolutePath()))) {
            return false;
        }
        Collections.sort(fileList, new FileComparator((FileComparator) null));
        int deletedAmount = 0;
        int size = fileList.size();
        for (int i = 0; i < size; i++) {
            File f = fileList.get(i).file;
            if (exceptFile == null || !f.getAbsolutePath().equals(exceptFile.getAbsolutePath())) {
                int fileSize = (int) ((f.length() / 1024) / 1024);
                Log.d("HAHA", "delete file: " + f.getAbsolutePath());
                try {
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(0);
                    fos.flush();
                    fos.close();
                    f.delete();
                    updateFileWithMediaScanner(f);
                    deletedAmount += fileSize;
                    if (deletedAmount >= amount) {
                        return true;
                    }
                } catch (Exception e) {
                    Log.d("HAHA", "deleteFile fail: " + e);
                }
            }
        }
        return false;
    }

    public boolean deleteOldFiles(List<FileWithLastModifiedTime> fileList, int deleteNum) {
        Collections.sort(fileList, new FileComparator((FileComparator) null));
        int deletedFileCount = 0;
        int size = fileList.size();
        int i = 0;
        while (i < size) {
            File f = fileList.get(i).file;
            LogUtil.d(TAG, "delete file: " + f.getAbsolutePath(), new Object[0]);
            try {
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(0);
                fos.flush();
                fos.close();
                f.delete();
                updateFileWithMediaScanner(f);
                deletedFileCount++;
                if (deletedFileCount >= deleteNum) {
                    return true;
                }
                i++;
            } catch (Exception e) {
                LogUtil.d(TAG, "deleteFile fail: " + e, new Object[0]);
            }
        }
        return false;
    }

    public void moveUrgentFileFromNormal(long time) {
        try {
            File rootDir = getRootDir();
            File normalDir = new File(rootDir, this.NORMAL_DIR);
            File urgentDir = new File(rootDir, this.URGENT_DIR);
            if (normalDir.exists()) {
                if (!urgentDir.exists()) {
                    urgentDir.mkdirs();
                }
                moveUrgentFilesInCertainTime(normalDir, time - 30000, 30000 + time);
            }
        } catch (Exception e) {
            LogUtil.d(TAG, "copy file fail: " + e, new Object[0]);
        }
    }

    private void moveUrgentFilesInCertainTime(File srcDir, long startTime, long endTime) {
        File[] files = srcDir.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                String absolutePath = f.getAbsolutePath();
                boolean isVideoFile = absolutePath.endsWith(VIDEO_SUFFIX);
                if (f.isFile() && isVideoFile) {
                    long lastModifiedTime = f.lastModified();
                    if (startTime <= lastModifiedTime && lastModifiedTime <= endTime) {
                        f.renameTo(new File(absolutePath.replace(this.NORMAL_DIR, this.URGENT_DIR)));
                        updateFileWithMediaScanner(f);
                    }
                }
            }
        }
    }

    class DefaultNormalReusePolicy implements SpaceReusePolicy {
        DefaultNormalReusePolicy() {
        }

        public boolean enoughSpaceAfterReuse(File parentDir) {
            int freeSpace = (int) ((parentDir.getFreeSpace() / 1024) / 1024);
            int spaceUsed = StorageUtil.this.getDirSize(parentDir) / 1024;
            LogUtil.d(StorageUtil.TAG, "enoughSpaceAtferReuse, freeSpace=" + freeSpace + ", spaceUsed=" + spaceUsed, new Object[0]);
            if (spaceUsed > 8142) {
                if (!StorageUtil.this.deleteOldFiles(parentDir, (File) null, (spaceUsed - 8192) + 50)) {
                    return false;
                }
                if (((int) ((parentDir.getFreeSpace() / 1024) / 1024)) >= 50) {
                    return true;
                }
                return false;
            } else if (freeSpace >= 50 || StorageUtil.this.deleteOldFiles(parentDir, (File) null, 50 - freeSpace)) {
                return true;
            } else {
                return false;
            }
        }

        public void ensureFileNumWithinLimits(File parentDir) {
            List<FileWithLastModifiedTime> fileList = new LinkedList<>();
            StorageUtil.this.getAllFilesOfDir(parentDir, fileList);
            int fileNum = fileList.size();
            LogUtil.d(StorageUtil.TAG, "isFileNumWithinLimitsAfterReuse, fileNum = " + fileNum, new Object[0]);
            if (fileNum > 149) {
                StorageUtil.this.deleteOldFiles(fileList, fileNum - 149);
            }
        }
    }

    class DefaultUrgentReusePolicy implements SpaceReusePolicy {
        DefaultUrgentReusePolicy() {
        }

        public boolean enoughSpaceAfterReuse(File parentDir) {
            int freeSpace = (int) ((parentDir.getFreeSpace() / 1024) / 1024);
            int spaceUsed = StorageUtil.this.getDirSize(parentDir) / 1024;
            LogUtil.d(StorageUtil.TAG, "enoughSpaceAtferReuse, freeSpace=" + freeSpace + ", spaceUsed=" + spaceUsed, new Object[0]);
            if (spaceUsed > 1998) {
                return false;
            }
            if (freeSpace >= 50) {
                return true;
            }
            File normalDir = null;
            try {
                normalDir = new File(StorageUtil.this.getRootDir(), StorageUtil.this.NORMAL_DIR);
            } catch (Exception e) {
                LogUtil.d(StorageUtil.TAG, e.toString(), new Object[0]);
            }
            if (normalDir == null || !StorageUtil.this.deleteOldFiles(normalDir, (File) null, 50 - freeSpace)) {
                return false;
            }
            return true;
        }

        public void ensureFileNumWithinLimits(File parentDir) {
            LogUtil.d(StorageUtil.TAG, "isFileNumWithinLimitsAfterReuse", new Object[0]);
        }
    }

    class DefaultImageReusePolicy implements SpaceReusePolicy {
        DefaultImageReusePolicy() {
        }

        public boolean enoughSpaceAfterReuse(File parentDir) {
            return ((int) ((parentDir.getFreeSpace() / 1024) / 1024)) >= 2;
        }

        public void ensureFileNumWithinLimits(File parentDir) {
            LogUtil.d(StorageUtil.TAG, "isFileNumWithinLimitsAfterReuse", new Object[0]);
        }
    }

    public static class NoSDCardMountedException extends Exception {
        public NoSDCardMountedException() {
            super("there is no SD card mounted");
        }
    }

    public static class NoUdiskMountedException extends Exception {
        public NoUdiskMountedException() {
            super("there is no UDISK mounted");
        }
    }

    public static class SpaceNotEnoughException extends Exception {
        public SpaceNotEnoughException() {
            super("storage free space is nout enough");
        }
    }

    public static class FileAlreadyExistException extends Exception {
        public FileAlreadyExistException(String name) {
            super(name);
        }
    }

    private static class FileWithLastModifiedTime {
        File file;
        long lastModifiedTime;

        private FileWithLastModifiedTime() {
        }

        /* synthetic */ FileWithLastModifiedTime(FileWithLastModifiedTime fileWithLastModifiedTime) {
            this();
        }
    }
}
