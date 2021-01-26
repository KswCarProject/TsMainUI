package com.suding.mirror.util;

import android.util.Log;
import com.suding.mirror.sdk.MirrorAdapterInitializer;
import com.ts.main.common.ShellUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LogUtils {
    private static final String BURT = "@BuTT@ ";
    private static final String CLARECHEN = "chen";
    public static final String D = "DEBUG";
    public static final String E = "ERROR";
    private static final String HELLO = "hello";
    public static final String I = "INFO";
    private static final String JDS = "jds";
    private static final String LENG = "@LengGX@ ";
    private static final String LI = "@LiYL@ ";
    private static final String LOL = "lol";
    private static final String LUWEI = "@LUWEI@";
    private static final String PENG = "@PengJF@ ";
    private static final String SJR = "sjr";
    private static final String SYSTEM = "@System@ ";
    public static final String V = "VERBOSE";
    public static final String W = "WARN";
    private static final String YUAN = "@YuanKL@ ";
    private static LogUtils blog;
    public static final boolean isJunitTest = false;
    private FileOutputStream out = null;
    private ConcurrentLinkedQueue<String> queue;

    public static LogUtils getLogger() {
        if (blog == null) {
            blog = new LogUtils();
        }
        return blog;
    }

    private void log(String name, String level, String content) {
        if (this.queue == null) {
            this.queue = new ConcurrentLinkedQueue<>();
        }
        if (V.equals(level) && MirrorAdapterInitializer.getInstance().isDebug()) {
            Log.v(name, String.valueOf(getFunctionName()) + ShellUtils.COMMAND_LINE_END + content);
        } else if (D.equals(level) && MirrorAdapterInitializer.getInstance().isDebug()) {
            Log.d(name, String.valueOf(getFunctionName()) + ShellUtils.COMMAND_LINE_END + content);
        } else if (I.equals(level) && MirrorAdapterInitializer.getInstance().isDebug()) {
            Log.i(name, String.valueOf(getFunctionName()) + ShellUtils.COMMAND_LINE_END + content);
        } else if (W.equals(level) && MirrorAdapterInitializer.getInstance().isDebug()) {
            Log.w(name, String.valueOf(getFunctionName()) + ShellUtils.COMMAND_LINE_END + content);
        } else if (E.equals(level) && MirrorAdapterInitializer.getInstance().isDebug()) {
            Log.e(name, String.valueOf(getFunctionName()) + ShellUtils.COMMAND_LINE_END + content);
        } else if (MirrorAdapterInitializer.getInstance().isDebug()) {
            Log.d(name, String.valueOf(getFunctionName()) + ShellUtils.COMMAND_LINE_END + content);
        }
    }

    private static int countDuplicates(StackTraceElement[] currentStack, StackTraceElement[] parentStack) {
        int duplicates = 0;
        int parentIndex = parentStack.length;
        int i = currentStack.length;
        while (true) {
            i--;
            if (i < 0 || parentIndex - 1 < 0 || !parentStack[parentIndex].equals(currentStack[i])) {
                return duplicates;
            }
            duplicates++;
        }
        return duplicates;
    }

    public static void systemLog(Object content) {
        try {
            if (content instanceof Throwable) {
                getLogger().log(SYSTEM, E, printStackTrace((Throwable) content));
            } else {
                getLogger().log(SYSTEM, E, new StringBuilder().append(content).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String printStackTrace(Throwable ex) throws Exception {
        StringBuilder sb = new StringBuilder();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        for (Throwable cause = ex.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        sb.append(writer.toString());
        return sb.toString();
    }

    public static void burtLog(String level, String content) {
        getLogger().log(BURT, level, content);
    }

    public static void burtLog(String content) {
        burtLog(E, content);
    }

    public static void yuanLog(String level, String content) {
        getLogger().log(YUAN, level, content);
    }

    public static void yuanLog(String content) {
        yuanLog(D, content);
    }

    public static void pengLog(String level, String content) {
        getLogger().log(PENG, level, content);
    }

    public static void pengLog(String content) {
        pengLog(D, content);
    }

    public static void liLog(String level, String content) {
        getLogger().log(LI, level, content);
    }

    public static void liLog(String content) {
        liLog(D, content);
    }

    public static void jdsLog(String level, String content) {
        getLogger().log(JDS, level, content);
    }

    public static void jdsLog(String content) {
        System.out.println(content);
        jdsLog(D, content);
    }

    public static void tagLog(String tag, String level, String content) {
        getLogger().log(tag, level, content);
    }

    public static void tagLog(String tag, String content) {
        tagLog(tag, D, content);
    }

    public static void helloLog(String level, String content) {
        getLogger().log(HELLO, level, content);
    }

    public static void helloLog(String content) {
        helloLog(D, content);
    }

    public static void sjrLog(String level, String content) {
        getLogger().log(SJR, level, content);
    }

    public static void sjrLog(String content) {
        sjrLog(D, content);
    }

    public static void Log(String level, String content) {
        getLogger().log(LOL, level, content);
    }

    public static void Log(String content) {
        helloLog(D, content);
    }

    public static void lengLog(String level, String content) {
        getLogger().log(LENG, level, content);
    }

    public static void luWeiLog(String level, String content) {
        getLogger().log(LUWEI, level, content);
    }

    public static void clareChenLog(String level, String content) {
        getLogger().log(CLARECHEN, level, content);
    }

    private String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (!st.isNativeMethod() && !st.getClassName().equals(Thread.class.getName()) && !st.getClassName().equals(getClass().getName())) {
                return String.valueOf(TimeUtils.getTime()) + "[ " + Thread.currentThread().getName() + ": " + st.getClassName() + ":" + st.getLineNumber() + " " + st.getMethodName() + " ]";
            }
        }
        return null;
    }

    private static String getStackInfo() {
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        int stacksLen = stacks.length;
        int i = 0;
        while (true) {
            if (i < stacksLen) {
                if (stacks[i].getClassName().equals(Log.class.getName()) && !stacks[i].getMethodName().equals("getStackInfo")) {
                    sb.append("类名: ").append(stacks[i + 1].getClassName()).append("; 方法: ").append(stacks[i + 1].getMethodName()).append("; 行数: ").append(stacks[i + 1].getLineNumber());
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        return sb.toString();
    }

    class DealFile implements Runnable {
        private FileOutputStream out;
        private ConcurrentLinkedQueue<String> queue;

        public DealFile() {
        }

        public DealFile(FileOutputStream out2, ConcurrentLinkedQueue<String> queue2) {
            this.out = out2;
            this.queue = queue2;
        }

        public void run() {
            while (true) {
                if (!this.queue.isEmpty()) {
                    try {
                        this.out.write(this.queue.poll().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private String getAbsoluteTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date());
    }

    public static String getTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        t.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.getBuffer().toString();
    }
}
