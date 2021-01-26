package com.ts.main.common;

import java.util.List;

public class ShellUtils {
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_SU = "su";

    private ShellUtils() {
        throw new AssertionError();
    }

    public static boolean checkRootPermission() {
        return execCommand("echo root", true, false).result == 0;
    }

    public static CommandResult execCommand(String command, boolean isRoot) {
        return execCommand(new String[]{command}, isRoot, true);
    }

    public static CommandResult execCommand(List<String> commands, boolean isRoot) {
        return execCommand(commands == null ? null : (String[]) commands.toArray(new String[0]), isRoot, true);
    }

    public static CommandResult execCommand(String[] commands, boolean isRoot) {
        return execCommand(commands, isRoot, true);
    }

    public static CommandResult execCommand(String command, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(new String[]{command}, isRoot, isNeedResultMsg);
    }

    public static CommandResult execCommand(List<String> commands, boolean isRoot, boolean isNeedResultMsg) {
        return execCommand(commands == null ? null : (String[]) commands.toArray(new String[0]), isRoot, isNeedResultMsg);
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00eb A[SYNTHETIC, Splitter:B:58:0x00eb] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00f0 A[Catch:{ IOException -> 0x012f }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00f5 A[Catch:{ IOException -> 0x012f }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x011b A[SYNTHETIC, Splitter:B:76:0x011b] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0120 A[Catch:{ IOException -> 0x0134 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0125 A[Catch:{ IOException -> 0x0134 }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x013c A[SYNTHETIC, Splitter:B:90:0x013c] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0141 A[Catch:{ IOException -> 0x014f }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0146 A[Catch:{ IOException -> 0x014f }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x014b  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:55:0x00e6=Splitter:B:55:0x00e6, B:73:0x0116=Splitter:B:73:0x0116} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.ts.main.common.ShellUtils.CommandResult execCommand(java.lang.String[] r21, boolean r22, boolean r23) {
        /*
            r12 = -1
            if (r21 == 0) goto L_0x000a
            r0 = r21
            int r0 = r0.length
            r18 = r0
            if (r18 != 0) goto L_0x001a
        L_0x000a:
            com.ts.main.common.ShellUtils$CommandResult r18 = new com.ts.main.common.ShellUtils$CommandResult
            r19 = 0
            r20 = 0
            r0 = r18
            r1 = r19
            r2 = r20
            r0.<init>(r12, r1, r2)
        L_0x0019:
            return r18
        L_0x001a:
            r11 = 0
            r16 = 0
            r7 = 0
            r14 = 0
            r5 = 0
            r9 = 0
            java.lang.Runtime r19 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0199, Exception -> 0x0183 }
            if (r22 == 0) goto L_0x00c2
            java.lang.String r18 = "su"
        L_0x002a:
            r0 = r19
            r1 = r18
            java.lang.Process r11 = r0.exec(r1)     // Catch:{ IOException -> 0x0199, Exception -> 0x0183 }
            java.io.DataOutputStream r10 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x0199, Exception -> 0x0183 }
            java.io.OutputStream r18 = r11.getOutputStream()     // Catch:{ IOException -> 0x0199, Exception -> 0x0183 }
            r0 = r18
            r10.<init>(r0)     // Catch:{ IOException -> 0x0199, Exception -> 0x0183 }
            r0 = r21
            int r0 = r0.length     // Catch:{ IOException -> 0x00e4, Exception -> 0x0185, all -> 0x0168 }
            r19 = r0
            r18 = 0
        L_0x0044:
            r0 = r18
            r1 = r19
            if (r0 < r1) goto L_0x00c7
            java.lang.String r18 = "exit\n"
            r0 = r18
            r10.writeBytes(r0)     // Catch:{ IOException -> 0x00e4, Exception -> 0x0185, all -> 0x0168 }
            r10.flush()     // Catch:{ IOException -> 0x00e4, Exception -> 0x0185, all -> 0x0168 }
            int r12 = r11.waitFor()     // Catch:{ IOException -> 0x00e4, Exception -> 0x0185, all -> 0x0168 }
            if (r23 == 0) goto L_0x0094
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00e4, Exception -> 0x0185, all -> 0x0168 }
            r15.<init>()     // Catch:{ IOException -> 0x00e4, Exception -> 0x0185, all -> 0x0168 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x019c, Exception -> 0x0188, all -> 0x016b }
            r6.<init>()     // Catch:{ IOException -> 0x019c, Exception -> 0x0188, all -> 0x016b }
            java.io.BufferedReader r17 = new java.io.BufferedReader     // Catch:{ IOException -> 0x01a1, Exception -> 0x018c, all -> 0x016f }
            java.io.InputStreamReader r18 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x01a1, Exception -> 0x018c, all -> 0x016f }
            java.io.InputStream r19 = r11.getInputStream()     // Catch:{ IOException -> 0x01a1, Exception -> 0x018c, all -> 0x016f }
            r18.<init>(r19)     // Catch:{ IOException -> 0x01a1, Exception -> 0x018c, all -> 0x016f }
            r17.<init>(r18)     // Catch:{ IOException -> 0x01a1, Exception -> 0x018c, all -> 0x016f }
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch:{ IOException -> 0x01a7, Exception -> 0x0191, all -> 0x0174 }
            java.io.InputStreamReader r18 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x01a7, Exception -> 0x0191, all -> 0x0174 }
            java.io.InputStream r19 = r11.getErrorStream()     // Catch:{ IOException -> 0x01a7, Exception -> 0x0191, all -> 0x0174 }
            r18.<init>(r19)     // Catch:{ IOException -> 0x01a7, Exception -> 0x0191, all -> 0x0174 }
            r0 = r18
            r8.<init>(r0)     // Catch:{ IOException -> 0x01a7, Exception -> 0x0191, all -> 0x0174 }
        L_0x0083:
            java.lang.String r13 = r17.readLine()     // Catch:{ IOException -> 0x0102, Exception -> 0x010f, all -> 0x017b }
            if (r13 != 0) goto L_0x00fe
        L_0x0089:
            java.lang.String r13 = r8.readLine()     // Catch:{ IOException -> 0x0102, Exception -> 0x010f, all -> 0x017b }
            if (r13 != 0) goto L_0x010a
            r5 = r6
            r14 = r15
            r7 = r8
            r16 = r17
        L_0x0094:
            if (r10 == 0) goto L_0x0099
            r10.close()     // Catch:{ IOException -> 0x0154 }
        L_0x0099:
            if (r16 == 0) goto L_0x009e
            r16.close()     // Catch:{ IOException -> 0x0154 }
        L_0x009e:
            if (r7 == 0) goto L_0x00a3
            r7.close()     // Catch:{ IOException -> 0x0154 }
        L_0x00a3:
            if (r11 == 0) goto L_0x00a8
            r11.destroy()
        L_0x00a8:
            r9 = r10
        L_0x00a9:
            com.ts.main.common.ShellUtils$CommandResult r19 = new com.ts.main.common.ShellUtils$CommandResult
            if (r14 != 0) goto L_0x015a
            r18 = 0
            r20 = r18
        L_0x00b1:
            if (r5 != 0) goto L_0x0162
            r18 = 0
        L_0x00b5:
            r0 = r19
            r1 = r20
            r2 = r18
            r0.<init>(r12, r1, r2)
            r18 = r19
            goto L_0x0019
        L_0x00c2:
            java.lang.String r18 = "sh"
            goto L_0x002a
        L_0x00c7:
            r3 = r21[r18]     // Catch:{ IOException -> 0x00e4, Exception -> 0x0185, all -> 0x0168 }
            if (r3 != 0) goto L_0x00cf
        L_0x00cb:
            int r18 = r18 + 1
            goto L_0x0044
        L_0x00cf:
            byte[] r20 = r3.getBytes()     // Catch:{ IOException -> 0x00e4, Exception -> 0x0185, all -> 0x0168 }
            r0 = r20
            r10.write(r0)     // Catch:{ IOException -> 0x00e4, Exception -> 0x0185, all -> 0x0168 }
            java.lang.String r20 = "\n"
            r0 = r20
            r10.writeBytes(r0)     // Catch:{ IOException -> 0x00e4, Exception -> 0x0185, all -> 0x0168 }
            r10.flush()     // Catch:{ IOException -> 0x00e4, Exception -> 0x0185, all -> 0x0168 }
            goto L_0x00cb
        L_0x00e4:
            r4 = move-exception
            r9 = r10
        L_0x00e6:
            r4.printStackTrace()     // Catch:{ all -> 0x0139 }
            if (r9 == 0) goto L_0x00ee
            r9.close()     // Catch:{ IOException -> 0x012f }
        L_0x00ee:
            if (r16 == 0) goto L_0x00f3
            r16.close()     // Catch:{ IOException -> 0x012f }
        L_0x00f3:
            if (r7 == 0) goto L_0x00f8
            r7.close()     // Catch:{ IOException -> 0x012f }
        L_0x00f8:
            if (r11 == 0) goto L_0x00a9
            r11.destroy()
            goto L_0x00a9
        L_0x00fe:
            r15.append(r13)     // Catch:{ IOException -> 0x0102, Exception -> 0x010f, all -> 0x017b }
            goto L_0x0083
        L_0x0102:
            r4 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            r7 = r8
            r16 = r17
            goto L_0x00e6
        L_0x010a:
            r6.append(r13)     // Catch:{ IOException -> 0x0102, Exception -> 0x010f, all -> 0x017b }
            goto L_0x0089
        L_0x010f:
            r4 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            r7 = r8
            r16 = r17
        L_0x0116:
            r4.printStackTrace()     // Catch:{ all -> 0x0139 }
            if (r9 == 0) goto L_0x011e
            r9.close()     // Catch:{ IOException -> 0x0134 }
        L_0x011e:
            if (r16 == 0) goto L_0x0123
            r16.close()     // Catch:{ IOException -> 0x0134 }
        L_0x0123:
            if (r7 == 0) goto L_0x0128
            r7.close()     // Catch:{ IOException -> 0x0134 }
        L_0x0128:
            if (r11 == 0) goto L_0x00a9
            r11.destroy()
            goto L_0x00a9
        L_0x012f:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x00f8
        L_0x0134:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0128
        L_0x0139:
            r18 = move-exception
        L_0x013a:
            if (r9 == 0) goto L_0x013f
            r9.close()     // Catch:{ IOException -> 0x014f }
        L_0x013f:
            if (r16 == 0) goto L_0x0144
            r16.close()     // Catch:{ IOException -> 0x014f }
        L_0x0144:
            if (r7 == 0) goto L_0x0149
            r7.close()     // Catch:{ IOException -> 0x014f }
        L_0x0149:
            if (r11 == 0) goto L_0x014e
            r11.destroy()
        L_0x014e:
            throw r18
        L_0x014f:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0149
        L_0x0154:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x00a3
        L_0x015a:
            java.lang.String r18 = r14.toString()
            r20 = r18
            goto L_0x00b1
        L_0x0162:
            java.lang.String r18 = r5.toString()
            goto L_0x00b5
        L_0x0168:
            r18 = move-exception
            r9 = r10
            goto L_0x013a
        L_0x016b:
            r18 = move-exception
            r9 = r10
            r14 = r15
            goto L_0x013a
        L_0x016f:
            r18 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            goto L_0x013a
        L_0x0174:
            r18 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            r16 = r17
            goto L_0x013a
        L_0x017b:
            r18 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            r7 = r8
            r16 = r17
            goto L_0x013a
        L_0x0183:
            r4 = move-exception
            goto L_0x0116
        L_0x0185:
            r4 = move-exception
            r9 = r10
            goto L_0x0116
        L_0x0188:
            r4 = move-exception
            r9 = r10
            r14 = r15
            goto L_0x0116
        L_0x018c:
            r4 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            goto L_0x0116
        L_0x0191:
            r4 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            r16 = r17
            goto L_0x0116
        L_0x0199:
            r4 = move-exception
            goto L_0x00e6
        L_0x019c:
            r4 = move-exception
            r9 = r10
            r14 = r15
            goto L_0x00e6
        L_0x01a1:
            r4 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            goto L_0x00e6
        L_0x01a7:
            r4 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            r16 = r17
            goto L_0x00e6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.main.common.ShellUtils.execCommand(java.lang.String[], boolean, boolean):com.ts.main.common.ShellUtils$CommandResult");
    }

    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(int result2) {
            this.result = result2;
        }

        public CommandResult(int result2, String successMsg2, String errorMsg2) {
            this.result = result2;
            this.successMsg = successMsg2;
            this.errorMsg = errorMsg2;
        }
    }
}
