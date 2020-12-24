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

    /* JADX WARNING: Removed duplicated region for block: B:103:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00e7 A[SYNTHETIC, Splitter:B:58:0x00e7] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00ec A[Catch:{ IOException -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00f1 A[Catch:{ IOException -> 0x012b }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0117 A[SYNTHETIC, Splitter:B:76:0x0117] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x011c A[Catch:{ IOException -> 0x0130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0121 A[Catch:{ IOException -> 0x0130 }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0138 A[SYNTHETIC, Splitter:B:90:0x0138] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x013d A[Catch:{ IOException -> 0x014b }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0142 A[Catch:{ IOException -> 0x014b }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0147  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:55:0x00e2=Splitter:B:55:0x00e2, B:73:0x0112=Splitter:B:73:0x0112} */
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
            java.lang.Runtime r19 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0195, Exception -> 0x017f }
            if (r22 == 0) goto L_0x00c0
            java.lang.String r18 = "su"
        L_0x0029:
            r0 = r19
            r1 = r18
            java.lang.Process r11 = r0.exec(r1)     // Catch:{ IOException -> 0x0195, Exception -> 0x017f }
            java.io.DataOutputStream r10 = new java.io.DataOutputStream     // Catch:{ IOException -> 0x0195, Exception -> 0x017f }
            java.io.OutputStream r18 = r11.getOutputStream()     // Catch:{ IOException -> 0x0195, Exception -> 0x017f }
            r0 = r18
            r10.<init>(r0)     // Catch:{ IOException -> 0x0195, Exception -> 0x017f }
            r0 = r21
            int r0 = r0.length     // Catch:{ IOException -> 0x00e0, Exception -> 0x0181, all -> 0x0164 }
            r19 = r0
            r18 = 0
        L_0x0043:
            r0 = r18
            r1 = r19
            if (r0 < r1) goto L_0x00c4
            java.lang.String r18 = "exit\n"
            r0 = r18
            r10.writeBytes(r0)     // Catch:{ IOException -> 0x00e0, Exception -> 0x0181, all -> 0x0164 }
            r10.flush()     // Catch:{ IOException -> 0x00e0, Exception -> 0x0181, all -> 0x0164 }
            int r12 = r11.waitFor()     // Catch:{ IOException -> 0x00e0, Exception -> 0x0181, all -> 0x0164 }
            if (r23 == 0) goto L_0x0092
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00e0, Exception -> 0x0181, all -> 0x0164 }
            r15.<init>()     // Catch:{ IOException -> 0x00e0, Exception -> 0x0181, all -> 0x0164 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0198, Exception -> 0x0184, all -> 0x0167 }
            r6.<init>()     // Catch:{ IOException -> 0x0198, Exception -> 0x0184, all -> 0x0167 }
            java.io.BufferedReader r17 = new java.io.BufferedReader     // Catch:{ IOException -> 0x019d, Exception -> 0x0188, all -> 0x016b }
            java.io.InputStreamReader r18 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x019d, Exception -> 0x0188, all -> 0x016b }
            java.io.InputStream r19 = r11.getInputStream()     // Catch:{ IOException -> 0x019d, Exception -> 0x0188, all -> 0x016b }
            r18.<init>(r19)     // Catch:{ IOException -> 0x019d, Exception -> 0x0188, all -> 0x016b }
            r17.<init>(r18)     // Catch:{ IOException -> 0x019d, Exception -> 0x0188, all -> 0x016b }
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch:{ IOException -> 0x01a3, Exception -> 0x018d, all -> 0x0170 }
            java.io.InputStreamReader r18 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x01a3, Exception -> 0x018d, all -> 0x0170 }
            java.io.InputStream r19 = r11.getErrorStream()     // Catch:{ IOException -> 0x01a3, Exception -> 0x018d, all -> 0x0170 }
            r18.<init>(r19)     // Catch:{ IOException -> 0x01a3, Exception -> 0x018d, all -> 0x0170 }
            r0 = r18
            r8.<init>(r0)     // Catch:{ IOException -> 0x01a3, Exception -> 0x018d, all -> 0x0170 }
        L_0x0081:
            java.lang.String r13 = r17.readLine()     // Catch:{ IOException -> 0x00fe, Exception -> 0x010b, all -> 0x0177 }
            if (r13 != 0) goto L_0x00fa
        L_0x0087:
            java.lang.String r13 = r8.readLine()     // Catch:{ IOException -> 0x00fe, Exception -> 0x010b, all -> 0x0177 }
            if (r13 != 0) goto L_0x0106
            r5 = r6
            r14 = r15
            r7 = r8
            r16 = r17
        L_0x0092:
            if (r10 == 0) goto L_0x0097
            r10.close()     // Catch:{ IOException -> 0x0150 }
        L_0x0097:
            if (r16 == 0) goto L_0x009c
            r16.close()     // Catch:{ IOException -> 0x0150 }
        L_0x009c:
            if (r7 == 0) goto L_0x00a1
            r7.close()     // Catch:{ IOException -> 0x0150 }
        L_0x00a1:
            if (r11 == 0) goto L_0x00a6
            r11.destroy()
        L_0x00a6:
            r9 = r10
        L_0x00a7:
            com.ts.main.common.ShellUtils$CommandResult r19 = new com.ts.main.common.ShellUtils$CommandResult
            if (r14 != 0) goto L_0x0156
            r18 = 0
            r20 = r18
        L_0x00af:
            if (r5 != 0) goto L_0x015e
            r18 = 0
        L_0x00b3:
            r0 = r19
            r1 = r20
            r2 = r18
            r0.<init>(r12, r1, r2)
            r18 = r19
            goto L_0x0019
        L_0x00c0:
            java.lang.String r18 = "sh"
            goto L_0x0029
        L_0x00c4:
            r3 = r21[r18]     // Catch:{ IOException -> 0x00e0, Exception -> 0x0181, all -> 0x0164 }
            if (r3 != 0) goto L_0x00cc
        L_0x00c8:
            int r18 = r18 + 1
            goto L_0x0043
        L_0x00cc:
            byte[] r20 = r3.getBytes()     // Catch:{ IOException -> 0x00e0, Exception -> 0x0181, all -> 0x0164 }
            r0 = r20
            r10.write(r0)     // Catch:{ IOException -> 0x00e0, Exception -> 0x0181, all -> 0x0164 }
            java.lang.String r20 = "\n"
            r0 = r20
            r10.writeBytes(r0)     // Catch:{ IOException -> 0x00e0, Exception -> 0x0181, all -> 0x0164 }
            r10.flush()     // Catch:{ IOException -> 0x00e0, Exception -> 0x0181, all -> 0x0164 }
            goto L_0x00c8
        L_0x00e0:
            r4 = move-exception
            r9 = r10
        L_0x00e2:
            r4.printStackTrace()     // Catch:{ all -> 0x0135 }
            if (r9 == 0) goto L_0x00ea
            r9.close()     // Catch:{ IOException -> 0x012b }
        L_0x00ea:
            if (r16 == 0) goto L_0x00ef
            r16.close()     // Catch:{ IOException -> 0x012b }
        L_0x00ef:
            if (r7 == 0) goto L_0x00f4
            r7.close()     // Catch:{ IOException -> 0x012b }
        L_0x00f4:
            if (r11 == 0) goto L_0x00a7
            r11.destroy()
            goto L_0x00a7
        L_0x00fa:
            r15.append(r13)     // Catch:{ IOException -> 0x00fe, Exception -> 0x010b, all -> 0x0177 }
            goto L_0x0081
        L_0x00fe:
            r4 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            r7 = r8
            r16 = r17
            goto L_0x00e2
        L_0x0106:
            r6.append(r13)     // Catch:{ IOException -> 0x00fe, Exception -> 0x010b, all -> 0x0177 }
            goto L_0x0087
        L_0x010b:
            r4 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            r7 = r8
            r16 = r17
        L_0x0112:
            r4.printStackTrace()     // Catch:{ all -> 0x0135 }
            if (r9 == 0) goto L_0x011a
            r9.close()     // Catch:{ IOException -> 0x0130 }
        L_0x011a:
            if (r16 == 0) goto L_0x011f
            r16.close()     // Catch:{ IOException -> 0x0130 }
        L_0x011f:
            if (r7 == 0) goto L_0x0124
            r7.close()     // Catch:{ IOException -> 0x0130 }
        L_0x0124:
            if (r11 == 0) goto L_0x00a7
            r11.destroy()
            goto L_0x00a7
        L_0x012b:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x00f4
        L_0x0130:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0124
        L_0x0135:
            r18 = move-exception
        L_0x0136:
            if (r9 == 0) goto L_0x013b
            r9.close()     // Catch:{ IOException -> 0x014b }
        L_0x013b:
            if (r16 == 0) goto L_0x0140
            r16.close()     // Catch:{ IOException -> 0x014b }
        L_0x0140:
            if (r7 == 0) goto L_0x0145
            r7.close()     // Catch:{ IOException -> 0x014b }
        L_0x0145:
            if (r11 == 0) goto L_0x014a
            r11.destroy()
        L_0x014a:
            throw r18
        L_0x014b:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0145
        L_0x0150:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x00a1
        L_0x0156:
            java.lang.String r18 = r14.toString()
            r20 = r18
            goto L_0x00af
        L_0x015e:
            java.lang.String r18 = r5.toString()
            goto L_0x00b3
        L_0x0164:
            r18 = move-exception
            r9 = r10
            goto L_0x0136
        L_0x0167:
            r18 = move-exception
            r9 = r10
            r14 = r15
            goto L_0x0136
        L_0x016b:
            r18 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            goto L_0x0136
        L_0x0170:
            r18 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            r16 = r17
            goto L_0x0136
        L_0x0177:
            r18 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            r7 = r8
            r16 = r17
            goto L_0x0136
        L_0x017f:
            r4 = move-exception
            goto L_0x0112
        L_0x0181:
            r4 = move-exception
            r9 = r10
            goto L_0x0112
        L_0x0184:
            r4 = move-exception
            r9 = r10
            r14 = r15
            goto L_0x0112
        L_0x0188:
            r4 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            goto L_0x0112
        L_0x018d:
            r4 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            r16 = r17
            goto L_0x0112
        L_0x0195:
            r4 = move-exception
            goto L_0x00e2
        L_0x0198:
            r4 = move-exception
            r9 = r10
            r14 = r15
            goto L_0x00e2
        L_0x019d:
            r4 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            goto L_0x00e2
        L_0x01a3:
            r4 = move-exception
            r9 = r10
            r5 = r6
            r14 = r15
            r16 = r17
            goto L_0x00e2
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
