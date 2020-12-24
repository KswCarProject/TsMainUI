package com.google.zxing;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringsResourceTranslator {
    private static final String APACHE_2_LICENSE = "<!--\n Copyright (C) 2014 ZXing authors\n\n Licensed under the Apache License, Version 2.0 (the \"License\");\n you may not use this file except in compliance with the License.\n You may obtain a copy of the License at\n\n      http://www.apache.org/licenses/LICENSE-2.0\n\n Unless required by applicable law or agreed to in writing, software\n distributed under the License is distributed on an \"AS IS\" BASIS,\n WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n See the License for the specific language governing permissions and\n limitations under the License.\n -->\n";
    private static final String API_KEY = System.getProperty("translateAPI.key");
    private static final Pattern ENTRY_PATTERN = Pattern.compile("<string name=\"([^\"]+)\".*>([^<]+)</string>");
    private static final Map<String, String> LANGUAGE_CODE_MASSAGINGS = new HashMap(3);
    private static final Pattern STRINGS_FILE_NAME_PATTERN = Pattern.compile("values-(.+)");
    private static final Pattern TRANSLATE_RESPONSE_PATTERN = Pattern.compile("translatedText\":\\s*\"([^\"]+)\"");
    /* access modifiers changed from: private */
    public static final Pattern VALUES_DIR_PATTERN = Pattern.compile("values-[a-z]{2}(-[a-zA-Z]{2,3})?");

    static {
        if (API_KEY == null) {
            throw new IllegalArgumentException("translateAPI.key is not specified");
        }
        LANGUAGE_CODE_MASSAGINGS.put("zh-rCN", "zh-cn");
        LANGUAGE_CODE_MASSAGINGS.put("zh-rTW", "zh-tw");
    }

    private StringsResourceTranslator() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0046, code lost:
        r12 = r10;
        r10 = r9;
        r9 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006b, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0045, code lost:
        r10 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r13) throws java.io.IOException {
        /*
            r10 = 0
            r9 = r13[r10]
            java.lang.String[] r10 = new java.lang.String[r10]
            java.nio.file.Path r5 = java.nio.file.Paths.get(r9, r10)
            java.lang.String r9 = "values"
            java.nio.file.Path r7 = r5.resolve(r9)
            java.lang.String r9 = "strings.xml"
            java.nio.file.Path r6 = r7.resolve(r9)
            java.util.List r9 = java.util.Arrays.asList(r13)
            r10 = 1
            int r11 = r13.length
            java.util.List r3 = r9.subList(r10, r11)
            com.google.zxing.StringsResourceTranslator$1 r2 = new com.google.zxing.StringsResourceTranslator$1
            r2.<init>()
            java.nio.file.DirectoryStream r1 = java.nio.file.Files.newDirectoryStream(r5, r2)
            r10 = 0
            java.util.Iterator r4 = r1.iterator()     // Catch:{ Throwable -> 0x0043, all -> 0x006b }
        L_0x002d:
            boolean r9 = r4.hasNext()     // Catch:{ Throwable -> 0x0043, all -> 0x006b }
            if (r9 == 0) goto L_0x0051
            java.lang.Object r0 = r4.next()     // Catch:{ Throwable -> 0x0043, all -> 0x006b }
            java.nio.file.Path r0 = (java.nio.file.Path) r0     // Catch:{ Throwable -> 0x0043, all -> 0x006b }
            java.lang.String r9 = "strings.xml"
            java.nio.file.Path r9 = r0.resolve(r9)     // Catch:{ Throwable -> 0x0043, all -> 0x006b }
            translate(r6, r9, r3)     // Catch:{ Throwable -> 0x0043, all -> 0x006b }
            goto L_0x002d
        L_0x0043:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r10 = move-exception
            r12 = r10
            r10 = r9
            r9 = r12
        L_0x0049:
            if (r1 == 0) goto L_0x0050
            if (r10 == 0) goto L_0x0067
            r1.close()     // Catch:{ Throwable -> 0x0062 }
        L_0x0050:
            throw r9
        L_0x0051:
            if (r1 == 0) goto L_0x0058
            if (r10 == 0) goto L_0x005e
            r1.close()     // Catch:{ Throwable -> 0x0059 }
        L_0x0058:
            return
        L_0x0059:
            r8 = move-exception
            r10.addSuppressed(r8)
            goto L_0x0058
        L_0x005e:
            r1.close()
            goto L_0x0058
        L_0x0062:
            r8 = move-exception
            r10.addSuppressed(r8)
            goto L_0x0050
        L_0x0067:
            r1.close()
            goto L_0x0050
        L_0x006b:
            r9 = move-exception
            goto L_0x0049
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.StringsResourceTranslator.main(java.lang.String[]):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00fd, code lost:
        r18 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00fe, code lost:
        r20 = r18;
        r18 = r17;
        r17 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x015a, code lost:
        r17 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void translate(java.nio.file.Path r21, java.nio.file.Path r22, java.util.Collection<java.lang.String> r23) throws java.io.IOException {
        /*
            java.util.Map r3 = readLines(r21)
            java.util.Map r13 = readLines(r22)
            java.nio.file.Path r17 = r22.getParent()
            java.nio.file.Path r17 = r17.getFileName()
            java.lang.String r10 = r17.toString()
            java.util.regex.Pattern r17 = STRINGS_FILE_NAME_PATTERN
            r0 = r17
            java.util.regex.Matcher r12 = r0.matcher(r10)
            r12.find()
            r17 = 1
            r0 = r17
            java.lang.String r7 = r12.group(r0)
            java.util.Map<java.lang.String, java.lang.String> r17 = LANGUAGE_CODE_MASSAGINGS
            r0 = r17
            java.lang.Object r8 = r0.get(r7)
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto L_0x0034
            r7 = r8
        L_0x0034:
            java.io.PrintStream r17 = java.lang.System.out
            java.lang.StringBuilder r18 = new java.lang.StringBuilder
            r18.<init>()
            java.lang.String r19 = "Translating "
            java.lang.StringBuilder r18 = r18.append(r19)
            r0 = r18
            java.lang.StringBuilder r18 = r0.append(r7)
            java.lang.String r18 = r18.toString()
            r17.println(r18)
            r17 = 0
            r18 = 0
            r19 = 0
            r0 = r19
            java.nio.file.attribute.FileAttribute[] r0 = new java.nio.file.attribute.FileAttribute[r0]
            r19 = r0
            java.nio.file.Path r11 = java.nio.file.Files.createTempFile(r17, r18, r19)
            r2 = 0
            java.nio.charset.Charset r17 = java.nio.charset.StandardCharsets.UTF_8
            r18 = 0
            r0 = r18
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            r18 = r0
            r0 = r17
            r1 = r18
            java.io.BufferedWriter r9 = java.nio.file.Files.newBufferedWriter(r11, r0, r1)
            r18 = 0
            java.lang.String r17 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            r0 = r17
            r9.write(r0)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.lang.String r17 = "<!--\n Copyright (C) 2014 ZXing authors\n\n Licensed under the Apache License, Version 2.0 (the \"License\");\n you may not use this file except in compliance with the License.\n You may obtain a copy of the License at\n\n      http://www.apache.org/licenses/LICENSE-2.0\n\n Unless required by applicable law or agreed to in writing, software\n distributed under the License is distributed on an \"AS IS\" BASIS,\n WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n See the License for the specific language governing permissions and\n limitations under the License.\n -->\n"
            r0 = r17
            r9.write(r0)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.lang.String r17 = "<resources>\n"
            r0 = r17
            r9.write(r0)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.util.Set r17 = r3.entrySet()     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.util.Iterator r5 = r17.iterator()     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
        L_0x0090:
            boolean r17 = r5.hasNext()     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            if (r17 == 0) goto L_0x010c
            java.lang.Object r4 = r5.next()     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.lang.Object r6 = r4.getKey()     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.lang.Object r15 = r4.getValue()     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.lang.String r17 = "  <string name=\""
            r0 = r17
            r9.write(r0)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            r9.write(r6)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            r17 = 34
            r0 = r17
            r9.write(r0)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.lang.String r17 = "%s"
            r0 = r17
            boolean r17 = r15.contains(r0)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            if (r17 != 0) goto L_0x00cd
            java.lang.String r17 = "%f"
            r0 = r17
            boolean r17 = r15.contains(r0)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            if (r17 == 0) goto L_0x00d4
        L_0x00cd:
            java.lang.String r17 = " formatted=\"false\""
            r0 = r17
            r9.write(r0)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
        L_0x00d4:
            r17 = 62
            r0 = r17
            r9.write(r0)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.lang.Object r14 = r13.get(r6)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            if (r14 == 0) goto L_0x00eb
            r0 = r23
            boolean r17 = r0.contains(r6)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            if (r17 == 0) goto L_0x00f0
        L_0x00eb:
            r2 = 1
            java.lang.String r14 = translateString(r15, r7)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
        L_0x00f0:
            r9.write(r14)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            java.lang.String r17 = "</string>\n"
            r0 = r17
            r9.write(r0)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            goto L_0x0090
        L_0x00fb:
            r17 = move-exception
            throw r17     // Catch:{ all -> 0x00fd }
        L_0x00fd:
            r18 = move-exception
            r20 = r18
            r18 = r17
            r17 = r20
        L_0x0104:
            if (r9 == 0) goto L_0x010b
            if (r18 == 0) goto L_0x0152
            r9.close()     // Catch:{ Throwable -> 0x0149 }
        L_0x010b:
            throw r17
        L_0x010c:
            java.lang.String r17 = "</resources>\n"
            r0 = r17
            r9.write(r0)     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            r9.flush()     // Catch:{ Throwable -> 0x00fb, all -> 0x015a }
            if (r9 == 0) goto L_0x011d
            if (r18 == 0) goto L_0x0145
            r9.close()     // Catch:{ Throwable -> 0x013c }
        L_0x011d:
            if (r2 == 0) goto L_0x0156
            java.io.PrintStream r17 = java.lang.System.out
            java.lang.String r18 = "  Writing translations"
            r17.println(r18)
            r17 = 1
            r0 = r17
            java.nio.file.CopyOption[] r0 = new java.nio.file.CopyOption[r0]
            r17 = r0
            r18 = 0
            java.nio.file.StandardCopyOption r19 = java.nio.file.StandardCopyOption.REPLACE_EXISTING
            r17[r18] = r19
            r0 = r22
            r1 = r17
            java.nio.file.Files.move(r11, r0, r1)
        L_0x013b:
            return
        L_0x013c:
            r16 = move-exception
            r0 = r18
            r1 = r16
            r0.addSuppressed(r1)
            goto L_0x011d
        L_0x0145:
            r9.close()
            goto L_0x011d
        L_0x0149:
            r16 = move-exception
            r0 = r18
            r1 = r16
            r0.addSuppressed(r1)
            goto L_0x010b
        L_0x0152:
            r9.close()
            goto L_0x010b
        L_0x0156:
            java.nio.file.Files.delete(r11)
            goto L_0x013b
        L_0x015a:
            r17 = move-exception
            goto L_0x0104
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.StringsResourceTranslator.translate(java.nio.file.Path, java.nio.file.Path, java.util.Collection):void");
    }

    static String translateString(String english, String language) throws IOException {
        if ("en".equals(language)) {
            return english;
        }
        String massagedLanguage = LANGUAGE_CODE_MASSAGINGS.get(language);
        if (massagedLanguage != null) {
            language = massagedLanguage;
        }
        System.out.println("  Need translation for " + english);
        CharSequence translateResult = fetch(URI.create("https://www.googleapis.com/language/translate/v2?key=" + API_KEY + "&q=" + URLEncoder.encode(english, "UTF-8") + "&source=en&target=" + language));
        Matcher m = TRANSLATE_RESPONSE_PATTERN.matcher(translateResult);
        if (!m.find()) {
            System.err.println("No translate result");
            System.err.println(translateResult);
            return english;
        }
        String translation = m.group(1).replaceAll("&quot;", "\"").replaceAll("&#39;", "'").replaceAll("&amp;quot;", "\"").replaceAll("&amp;#39;", "'");
        System.out.println("  Got translation " + translation);
        return translation;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0035, code lost:
        r9 = r7;
        r7 = r6;
        r6 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005a, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0034, code lost:
        r7 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.CharSequence fetch(java.net.URI r10) throws java.io.IOException {
        /*
            java.net.URL r6 = r10.toURL()
            java.net.URLConnection r2 = r6.openConnection()
            r2.connect()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r6 = 200(0xc8, float:2.8E-43)
            r4.<init>(r6)
            java.io.BufferedReader r3 = new java.io.BufferedReader
            java.io.InputStreamReader r6 = new java.io.InputStreamReader
            java.io.InputStream r7 = r2.getInputStream()
            java.nio.charset.Charset r8 = java.nio.charset.StandardCharsets.UTF_8
            r6.<init>(r7, r8)
            r3.<init>(r6)
            r7 = 0
            r6 = 8192(0x2000, float:1.14794E-41)
            char[] r0 = new char[r6]     // Catch:{ Throwable -> 0x0032, all -> 0x005a }
        L_0x0027:
            int r1 = r3.read(r0)     // Catch:{ Throwable -> 0x0032, all -> 0x005a }
            if (r1 <= 0) goto L_0x0040
            r6 = 0
            r4.append(r0, r6, r1)     // Catch:{ Throwable -> 0x0032, all -> 0x005a }
            goto L_0x0027
        L_0x0032:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0034 }
        L_0x0034:
            r7 = move-exception
            r9 = r7
            r7 = r6
            r6 = r9
        L_0x0038:
            if (r3 == 0) goto L_0x003f
            if (r7 == 0) goto L_0x0056
            r3.close()     // Catch:{ Throwable -> 0x0051 }
        L_0x003f:
            throw r6
        L_0x0040:
            if (r3 == 0) goto L_0x0047
            if (r7 == 0) goto L_0x004d
            r3.close()     // Catch:{ Throwable -> 0x0048 }
        L_0x0047:
            return r4
        L_0x0048:
            r5 = move-exception
            r7.addSuppressed(r5)
            goto L_0x0047
        L_0x004d:
            r3.close()
            goto L_0x0047
        L_0x0051:
            r5 = move-exception
            r7.addSuppressed(r5)
            goto L_0x003f
        L_0x0056:
            r3.close()
            goto L_0x003f
        L_0x005a:
            r6 = move-exception
            goto L_0x0038
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.StringsResourceTranslator.fetch(java.net.URI):java.lang.CharSequence");
    }

    private static Map<String, String> readLines(Path file) throws IOException {
        if (!Files.exists(file, new LinkOption[0])) {
            return Collections.emptyMap();
        }
        Map<String, String> entries = new TreeMap<>();
        for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
            Matcher m = ENTRY_PATTERN.matcher(line);
            if (m.find()) {
                entries.put(m.group(1), m.group(2));
            }
        }
        return entries;
    }
}
