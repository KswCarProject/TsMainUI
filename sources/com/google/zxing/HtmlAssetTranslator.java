package com.google.zxing;

import com.ts.bt.ContactInfo;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.xml.sax.SAXException;

public final class HtmlAssetTranslator {
    private static final Pattern COMMA = Pattern.compile(ContactInfo.COMBINATION_SEPERATOR);

    private HtmlAssetTranslator() {
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.err.println("Usage: HtmlAssetTranslator android/assets/ (all|lang1[,lang2 ...]) (all|file1.html[ file2.html ...])");
            return;
        }
        Path assetsDir = Paths.get(args[0], new String[0]);
        Collection<String> languagesToTranslate = parseLanguagesToTranslate(assetsDir, args[1]);
        Collection<String> fileNamesToTranslate = parseFileNamesToTranslate(assetsDir, Arrays.asList(args).subList(2, args.length));
        for (String language : languagesToTranslate) {
            translateOneLanguage(assetsDir, language, fileNamesToTranslate);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003a, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
        r9 = r7;
        r7 = r6;
        r6 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006b, code lost:
        r6 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Collection<java.lang.String> parseLanguagesToTranslate(java.nio.file.Path r10, java.lang.CharSequence r11) throws java.io.IOException {
        /*
            java.lang.String r6 = "all"
            boolean r6 = r6.equals(r11)
            if (r6 == 0) goto L_0x0060
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            com.google.zxing.HtmlAssetTranslator$1 r1 = new com.google.zxing.HtmlAssetTranslator$1
            r1.<init>()
            java.nio.file.DirectoryStream r0 = java.nio.file.Files.newDirectoryStream(r10, r1)
            r7 = 0
            java.util.Iterator r2 = r0.iterator()     // Catch:{ Throwable -> 0x0038, all -> 0x006b }
        L_0x001b:
            boolean r6 = r2.hasNext()     // Catch:{ Throwable -> 0x0038, all -> 0x006b }
            if (r6 == 0) goto L_0x0046
            java.lang.Object r3 = r2.next()     // Catch:{ Throwable -> 0x0038, all -> 0x006b }
            java.nio.file.Path r3 = (java.nio.file.Path) r3     // Catch:{ Throwable -> 0x0038, all -> 0x006b }
            java.nio.file.Path r6 = r3.getFileName()     // Catch:{ Throwable -> 0x0038, all -> 0x006b }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x0038, all -> 0x006b }
            r8 = 5
            java.lang.String r6 = r6.substring(r8)     // Catch:{ Throwable -> 0x0038, all -> 0x006b }
            r4.add(r6)     // Catch:{ Throwable -> 0x0038, all -> 0x006b }
            goto L_0x001b
        L_0x0038:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x003a }
        L_0x003a:
            r7 = move-exception
            r9 = r7
            r7 = r6
            r6 = r9
        L_0x003e:
            if (r0 == 0) goto L_0x0045
            if (r7 == 0) goto L_0x005c
            r0.close()     // Catch:{ Throwable -> 0x0057 }
        L_0x0045:
            throw r6
        L_0x0046:
            if (r0 == 0) goto L_0x004d
            if (r7 == 0) goto L_0x0053
            r0.close()     // Catch:{ Throwable -> 0x004e }
        L_0x004d:
            return r4
        L_0x004e:
            r5 = move-exception
            r7.addSuppressed(r5)
            goto L_0x004d
        L_0x0053:
            r0.close()
            goto L_0x004d
        L_0x0057:
            r5 = move-exception
            r7.addSuppressed(r5)
            goto L_0x0045
        L_0x005c:
            r0.close()
            goto L_0x0045
        L_0x0060:
            java.util.regex.Pattern r6 = COMMA
            java.lang.String[] r6 = r6.split(r11)
            java.util.List r4 = java.util.Arrays.asList(r6)
            goto L_0x004d
        L_0x006b:
            r6 = move-exception
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.HtmlAssetTranslator.parseLanguagesToTranslate(java.nio.file.Path, java.lang.CharSequence):java.util.Collection");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003d, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003e, code lost:
        r8 = r7;
        r7 = r6;
        r6 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0065, code lost:
        r6 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Collection<java.lang.String> parseFileNamesToTranslate(java.nio.file.Path r9, java.util.List<java.lang.String> r10) throws java.io.IOException {
        /*
            java.lang.String r6 = "all"
            r7 = 0
            java.lang.Object r7 = r10.get(r7)
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0063
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r6 = "html-en"
            java.nio.file.Path r3 = r9.resolve(r6)
            java.lang.String r6 = "*.html"
            java.nio.file.DirectoryStream r2 = java.nio.file.Files.newDirectoryStream(r3, r6)
            r7 = 0
            java.util.Iterator r4 = r2.iterator()     // Catch:{ Throwable -> 0x003b, all -> 0x0065 }
        L_0x0023:
            boolean r6 = r4.hasNext()     // Catch:{ Throwable -> 0x003b, all -> 0x0065 }
            if (r6 == 0) goto L_0x0049
            java.lang.Object r0 = r4.next()     // Catch:{ Throwable -> 0x003b, all -> 0x0065 }
            java.nio.file.Path r0 = (java.nio.file.Path) r0     // Catch:{ Throwable -> 0x003b, all -> 0x0065 }
            java.nio.file.Path r6 = r0.getFileName()     // Catch:{ Throwable -> 0x003b, all -> 0x0065 }
            java.lang.String r6 = r6.toString()     // Catch:{ Throwable -> 0x003b, all -> 0x0065 }
            r1.add(r6)     // Catch:{ Throwable -> 0x003b, all -> 0x0065 }
            goto L_0x0023
        L_0x003b:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x003d }
        L_0x003d:
            r7 = move-exception
            r8 = r7
            r7 = r6
            r6 = r8
        L_0x0041:
            if (r2 == 0) goto L_0x0048
            if (r7 == 0) goto L_0x005f
            r2.close()     // Catch:{ Throwable -> 0x005a }
        L_0x0048:
            throw r6
        L_0x0049:
            if (r2 == 0) goto L_0x0050
            if (r7 == 0) goto L_0x0056
            r2.close()     // Catch:{ Throwable -> 0x0051 }
        L_0x0050:
            return r1
        L_0x0051:
            r5 = move-exception
            r7.addSuppressed(r5)
            goto L_0x0050
        L_0x0056:
            r2.close()
            goto L_0x0050
        L_0x005a:
            r5 = move-exception
            r7.addSuppressed(r5)
            goto L_0x0048
        L_0x005f:
            r2.close()
            goto L_0x0048
        L_0x0063:
            r1 = r10
            goto L_0x0050
        L_0x0065:
            r6 = move-exception
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.HtmlAssetTranslator.parseFileNamesToTranslate(java.nio.file.Path, java.util.List):java.util.Collection");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004a, code lost:
        r10 = r9;
        r9 = r8;
        r8 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006f, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0049, code lost:
        r9 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void translateOneLanguage(java.nio.file.Path r11, java.lang.String r12, final java.util.Collection<java.lang.String> r13) throws java.io.IOException {
        /*
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "html-"
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.StringBuilder r8 = r8.append(r12)
            java.lang.String r8 = r8.toString()
            java.nio.file.Path r5 = r11.resolve(r8)
            r8 = 0
            java.nio.file.attribute.FileAttribute[] r8 = new java.nio.file.attribute.FileAttribute[r8]
            java.nio.file.Files.createDirectories(r5, r8)
            java.lang.String r8 = "html-en"
            java.nio.file.Path r0 = r11.resolve(r8)
            java.lang.String r8 = "Translated by Google Translate."
            java.lang.String r6 = com.google.zxing.StringsResourceTranslator.translateString(r8, r12)
            com.google.zxing.HtmlAssetTranslator$2 r2 = new com.google.zxing.HtmlAssetTranslator$2
            r2.<init>(r13)
            java.nio.file.DirectoryStream r1 = java.nio.file.Files.newDirectoryStream(r0, r2)
            r9 = 0
            java.util.Iterator r3 = r1.iterator()     // Catch:{ Throwable -> 0x0047, all -> 0x006f }
        L_0x0037:
            boolean r8 = r3.hasNext()     // Catch:{ Throwable -> 0x0047, all -> 0x006f }
            if (r8 == 0) goto L_0x0055
            java.lang.Object r4 = r3.next()     // Catch:{ Throwable -> 0x0047, all -> 0x006f }
            java.nio.file.Path r4 = (java.nio.file.Path) r4     // Catch:{ Throwable -> 0x0047, all -> 0x006f }
            translateOneFile(r12, r5, r4, r6)     // Catch:{ Throwable -> 0x0047, all -> 0x006f }
            goto L_0x0037
        L_0x0047:
            r8 = move-exception
            throw r8     // Catch:{ all -> 0x0049 }
        L_0x0049:
            r9 = move-exception
            r10 = r9
            r9 = r8
            r8 = r10
        L_0x004d:
            if (r1 == 0) goto L_0x0054
            if (r9 == 0) goto L_0x006b
            r1.close()     // Catch:{ Throwable -> 0x0066 }
        L_0x0054:
            throw r8
        L_0x0055:
            if (r1 == 0) goto L_0x005c
            if (r9 == 0) goto L_0x0062
            r1.close()     // Catch:{ Throwable -> 0x005d }
        L_0x005c:
            return
        L_0x005d:
            r7 = move-exception
            r9.addSuppressed(r7)
            goto L_0x005c
        L_0x0062:
            r1.close()
            goto L_0x005c
        L_0x0066:
            r7 = move-exception
            r9.addSuppressed(r7)
            goto L_0x0054
        L_0x006b:
            r1.close()
            goto L_0x0054
        L_0x006f:
            r8 = move-exception
            goto L_0x004d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.HtmlAssetTranslator.translateOneLanguage(java.nio.file.Path, java.lang.String, java.util.Collection):void");
    }

    private static void translateOneFile(String language, Path targetHtmlDir, Path sourceFile, String translationTextTranslated) throws IOException {
        Path destFile = targetHtmlDir.resolve(sourceFile.getFileName());
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(sourceFile.toFile());
            Element rootElement = document.getDocumentElement();
            rootElement.normalize();
            Queue<Node> nodes = new LinkedList<>();
            nodes.add(rootElement);
            while (!nodes.isEmpty()) {
                Node node = nodes.poll();
                if (shouldTranslate(node)) {
                    NodeList children = node.getChildNodes();
                    for (int i = 0; i < children.getLength(); i++) {
                        nodes.add(children.item(i));
                    }
                }
                if (node.getNodeType() == 3) {
                    String text = node.getTextContent();
                    if (!text.trim().isEmpty()) {
                        node.setTextContent(' ' + StringsResourceTranslator.translateString(text, language) + ' ');
                    }
                }
            }
            Text createTextNode = document.createTextNode(translationTextTranslated);
            Node paragraph = document.createElement("p");
            paragraph.appendChild(createTextNode);
            rootElement.getElementsByTagName("body").item(0).appendChild(paragraph);
            try {
                Files.write(destFile, Collections.singleton(((DOMImplementationLS) DOMImplementationRegistry.newInstance().getDOMImplementation("LS")).createLSSerializer().writeToString(document).replaceAll("<\\?xml[^>]+>", "<!DOCTYPE HTML>")), StandardCharsets.UTF_8, new OpenOption[0]);
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                throw new IllegalStateException(e);
            }
        } catch (ParserConfigurationException pce) {
            throw new IllegalStateException(pce);
        } catch (SAXException sae) {
            throw new IOException(sae);
        }
    }

    private static boolean shouldTranslate(Node node) {
        String textContent;
        Node classAttribute;
        String textContent2;
        NamedNodeMap attributes = node.getAttributes();
        if ((attributes != null && (classAttribute = attributes.getNamedItem("class")) != null && (textContent2 = classAttribute.getTextContent()) != null && textContent2.contains("notranslate")) || "script".equalsIgnoreCase(node.getNodeName()) || (textContent = node.getTextContent()) == null) {
            return false;
        }
        for (int i = 0; i < textContent.length(); i++) {
            if (Character.isLetter(textContent.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
