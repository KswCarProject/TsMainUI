package com.Ty.T.Tr;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import com.Ty.T.T.T.Tr.Tr;
import com.Ty.T.Tr.T.T.Ty;
import com.Ty.T.Tr.T.TZ;
import com.Ty.T.Ty.T9;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: Proguard */
public class T {
    public static Executor T(int threadPoolSize, int threadPriority, TZ tasksProcessingType) {
        return new ThreadPoolExecutor(threadPoolSize, threadPoolSize, 0, TimeUnit.MILLISECONDS, tasksProcessingType == TZ.LIFO ? new Ty<>() : new LinkedBlockingQueue<>(), T(threadPriority, "uil-pool-"));
    }

    public static Executor T() {
        return Executors.newCachedThreadPool(T(5, "uil-pool-d-"));
    }

    public static com.Ty.T.T.T.Tr.T Tr() {
        return new Tr();
    }

    public static com.Ty.T.T.T.T T(Context context, com.Ty.T.T.T.Tr.T diskCacheFileNameGenerator, long diskCacheSize, int diskCacheFileCount) {
        File reserveCacheDir = Tr(context);
        if (diskCacheSize > 0 || diskCacheFileCount > 0) {
            try {
                return new com.Ty.T.T.T.T.T.Tr(T9.Tr(context), reserveCacheDir, diskCacheFileNameGenerator, diskCacheSize, diskCacheFileCount);
            } catch (IOException e) {
                com.Ty.T.Ty.Ty.T((Throwable) e);
            }
        }
        return new com.Ty.T.T.T.T.Tr(T9.T(context), reserveCacheDir, diskCacheFileNameGenerator);
    }

    private static File Tr(Context context) {
        File cacheDir = T9.T(context, false);
        File individualDir = new File(cacheDir, "uil-images");
        if (individualDir.exists() || individualDir.mkdir()) {
            return individualDir;
        }
        return cacheDir;
    }

    public static com.Ty.T.T.Tr.T T(Context context, int memoryCacheSize) {
        if (memoryCacheSize == 0) {
            ActivityManager am = (ActivityManager) context.getSystemService("activity");
            int memoryClass = am.getMemoryClass();
            if (Tn() && Ty(context)) {
                memoryClass = T(am);
            }
            memoryCacheSize = (1048576 * memoryClass) / 8;
        }
        return new com.Ty.T.T.Tr.T.Tr(memoryCacheSize);
    }

    private static boolean Tn() {
        return Build.VERSION.SDK_INT >= 11;
    }

    @TargetApi(11)
    private static boolean Ty(Context context) {
        return (context.getApplicationInfo().flags & 1048576) != 0;
    }

    @TargetApi(11)
    private static int T(ActivityManager am) {
        return am.getLargeMemoryClass();
    }

    public static com.Ty.T.Tr.Tn.Tr T(Context context) {
        return new com.Ty.T.Tr.Tn.T(context);
    }

    public static com.Ty.T.Tr.Tr.Tr T(boolean loggingEnabled) {
        return new com.Ty.T.Tr.Tr.T(loggingEnabled);
    }

    public static com.Ty.T.Tr.Ty.T Ty() {
        return new com.Ty.T.Tr.Ty.Tr();
    }

    private static ThreadFactory T(int threadPriority, String threadNamePrefix) {
        return new C0011T(threadPriority, threadNamePrefix);
    }

    /* renamed from: com.Ty.T.Tr.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    private static class C0011T implements ThreadFactory {

        /* renamed from: T  reason: collision with root package name */
        private static final AtomicInteger f307T = new AtomicInteger(1);
        private final int T9;
        private final String Tn;
        private final ThreadGroup Tr;
        private final AtomicInteger Ty = new AtomicInteger(1);

        C0011T(int threadPriority, String threadNamePrefix) {
            this.T9 = threadPriority;
            this.Tr = Thread.currentThread().getThreadGroup();
            this.Tn = threadNamePrefix + f307T.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(this.Tr, r, this.Tn + this.Ty.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            t.setPriority(this.T9);
            return t;
        }
    }
}
