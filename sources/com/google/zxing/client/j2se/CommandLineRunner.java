package com.google.zxing.client.j2se;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.ts.bt.ContactInfo;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

public final class CommandLineRunner {
    private static final Pattern COMMA = Pattern.compile(ContactInfo.COMBINATION_SEPERATOR);

    private CommandLineRunner() {
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            printUsage();
            return;
        }
        Config config = new Config();
        Queue<Path> inputs = new ConcurrentLinkedQueue<>();
        for (String arg : args) {
            String[] argValue = arg.split("=");
            String str = argValue[0];
            char c = 65535;
            switch (str.hashCode()) {
                case -1629690150:
                    if (str.equals("--brief")) {
                        c = 6;
                        break;
                    }
                    break;
                case -1619438695:
                    if (str.equals("--multi")) {
                        c = 5;
                        break;
                    }
                    break;
                case -1341087876:
                    if (str.equals("--try_harder")) {
                        c = 0;
                        break;
                    }
                    break;
                case -878421031:
                    if (str.equals("--pure_barcode")) {
                        c = 1;
                        break;
                    }
                    break;
                case -214932473:
                    if (str.equals("--products_only")) {
                        c = 2;
                        break;
                    }
                    break;
                case 113896203:
                    if (str.equals("--possibleFormats")) {
                        c = 9;
                        break;
                    }
                    break;
                case 1332932656:
                    if (str.equals("--crop")) {
                        c = 8;
                        break;
                    }
                    break;
                case 1579122251:
                    if (str.equals("--dump_results")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1654174354:
                    if (str.equals("--recursive")) {
                        c = 7;
                        break;
                    }
                    break;
                case 1991318821:
                    if (str.equals("--dump_black_point")) {
                        c = 4;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    config.setTryHarder(true);
                    break;
                case 1:
                    config.setPureBarcode(true);
                    break;
                case 2:
                    config.setProductsOnly(true);
                    break;
                case 3:
                    config.setDumpResults(true);
                    break;
                case 4:
                    config.setDumpBlackPoint(true);
                    break;
                case 5:
                    config.setMulti(true);
                    break;
                case 6:
                    config.setBrief(true);
                    break;
                case 7:
                    config.setRecursive(true);
                    break;
                case 8:
                    int[] crop = new int[4];
                    String[] tokens = COMMA.split(argValue[1]);
                    for (int i = 0; i < crop.length; i++) {
                        crop[i] = Integer.parseInt(tokens[i]);
                    }
                    config.setCrop(crop);
                    break;
                case 9:
                    config.setPossibleFormats(COMMA.split(argValue[1]));
                    break;
                default:
                    if (!arg.startsWith("-")) {
                        addArgumentToInputs(Paths.get(arg, new String[0]), config, inputs);
                        break;
                    } else {
                        System.err.println("Unknown command line option " + arg);
                        printUsage();
                        return;
                    }
            }
        }
        config.setHints(buildHints(config));
        int numThreads = Math.min(inputs.size(), Runtime.getRuntime().availableProcessors());
        int successful = 0;
        if (numThreads > 1) {
            ExecutorService executor = Executors.newFixedThreadPool(numThreads);
            Collection<Future<Integer>> futures = new ArrayList<>(numThreads);
            for (int x = 0; x < numThreads; x++) {
                futures.add(executor.submit(new DecodeWorker(config, inputs)));
            }
            executor.shutdown();
            for (Future<Integer> future : futures) {
                successful += future.get().intValue();
            }
        } else {
            successful = 0 + new DecodeWorker(config, inputs).call().intValue();
        }
        int total = inputs.size();
        if (total > 1) {
            System.out.println("\nDecoded " + successful + " files out of " + total + " successfully (" + ((successful * 100) / total) + "%)\n");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
        r8 = r6;
        r6 = r5;
        r5 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0069, code lost:
        r5 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void addArgumentToInputs(java.nio.file.Path r9, com.google.zxing.client.j2se.Config r10, java.util.Queue<java.nio.file.Path> r11) throws java.io.IOException {
        /*
            r5 = 0
            java.nio.file.LinkOption[] r5 = new java.nio.file.LinkOption[r5]
            boolean r5 = java.nio.file.Files.isDirectory(r9, r5)
            if (r5 == 0) goto L_0x0085
            java.nio.file.DirectoryStream r2 = java.nio.file.Files.newDirectoryStream(r9)
            r6 = 0
            java.util.Iterator r1 = r2.iterator()     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
        L_0x0012:
            boolean r5 = r1.hasNext()     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            if (r5 == 0) goto L_0x006b
            java.lang.Object r3 = r1.next()     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            java.nio.file.Path r3 = (java.nio.file.Path) r3     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            java.nio.file.Path r5 = r3.getFileName()     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            java.util.Locale r7 = java.util.Locale.ENGLISH     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            java.lang.String r0 = r5.toLowerCase(r7)     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            java.lang.String r5 = "."
            boolean r5 = r0.startsWith(r5)     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            if (r5 != 0) goto L_0x0012
            r5 = 0
            java.nio.file.LinkOption[] r5 = new java.nio.file.LinkOption[r5]     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            boolean r5 = java.nio.file.Files.isDirectory(r3, r5)     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            if (r5 == 0) goto L_0x0055
            boolean r5 = r10.isRecursive()     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            if (r5 == 0) goto L_0x0012
            addArgumentToInputs(r3, r10, r11)     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            goto L_0x0012
        L_0x0047:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0049 }
        L_0x0049:
            r6 = move-exception
            r8 = r6
            r6 = r5
            r5 = r8
        L_0x004d:
            if (r2 == 0) goto L_0x0054
            if (r6 == 0) goto L_0x0081
            r2.close()     // Catch:{ Throwable -> 0x007c }
        L_0x0054:
            throw r5
        L_0x0055:
            java.lang.String r5 = ".txt"
            boolean r5 = r0.endsWith(r5)     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            if (r5 != 0) goto L_0x0012
            java.lang.String r5 = ".mono.png"
            boolean r5 = r0.contains(r5)     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            if (r5 != 0) goto L_0x0012
            r11.add(r3)     // Catch:{ Throwable -> 0x0047, all -> 0x0069 }
            goto L_0x0012
        L_0x0069:
            r5 = move-exception
            goto L_0x004d
        L_0x006b:
            if (r2 == 0) goto L_0x0072
            if (r6 == 0) goto L_0x0078
            r2.close()     // Catch:{ Throwable -> 0x0073 }
        L_0x0072:
            return
        L_0x0073:
            r4 = move-exception
            r6.addSuppressed(r4)
            goto L_0x0072
        L_0x0078:
            r2.close()
            goto L_0x0072
        L_0x007c:
            r4 = move-exception
            r6.addSuppressed(r4)
            goto L_0x0054
        L_0x0081:
            r2.close()
            goto L_0x0054
        L_0x0085:
            r11.add(r9)
            goto L_0x0072
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.j2se.CommandLineRunner.addArgumentToInputs(java.nio.file.Path, com.google.zxing.client.j2se.Config, java.util.Queue):void");
    }

    private static Map<DecodeHintType, ?> buildHints(Config config) {
        Collection<BarcodeFormat> possibleFormats = new ArrayList<>();
        String[] possibleFormatsNames = config.getPossibleFormats();
        if (possibleFormatsNames == null || possibleFormatsNames.length <= 0) {
            possibleFormats.add(BarcodeFormat.UPC_A);
            possibleFormats.add(BarcodeFormat.UPC_E);
            possibleFormats.add(BarcodeFormat.EAN_13);
            possibleFormats.add(BarcodeFormat.EAN_8);
            possibleFormats.add(BarcodeFormat.RSS_14);
            possibleFormats.add(BarcodeFormat.RSS_EXPANDED);
            if (!config.isProductsOnly()) {
                possibleFormats.add(BarcodeFormat.CODE_39);
                possibleFormats.add(BarcodeFormat.CODE_93);
                possibleFormats.add(BarcodeFormat.CODE_128);
                possibleFormats.add(BarcodeFormat.ITF);
                possibleFormats.add(BarcodeFormat.QR_CODE);
                possibleFormats.add(BarcodeFormat.DATA_MATRIX);
                possibleFormats.add(BarcodeFormat.AZTEC);
                possibleFormats.add(BarcodeFormat.PDF_417);
                possibleFormats.add(BarcodeFormat.CODABAR);
                possibleFormats.add(BarcodeFormat.MAXICODE);
            }
        } else {
            for (String format : possibleFormatsNames) {
                possibleFormats.add(BarcodeFormat.valueOf(format));
            }
        }
        Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.POSSIBLE_FORMATS, possibleFormats);
        if (config.isTryHarder()) {
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        }
        if (config.isPureBarcode()) {
            hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
        }
        return hints;
    }

    private static void printUsage() {
        System.err.println("Decode barcode images using the ZXing library");
        System.err.println();
        System.err.println("usage: CommandLineRunner { file | dir | url } [ options ]");
        System.err.println("  --try_harder: Use the TRY_HARDER hint, default is normal (mobile) mode");
        System.err.println("  --pure_barcode: Input image is a pure monochrome barcode image, not a photo");
        System.err.println("  --products_only: Only decode the UPC and EAN families of barcodes");
        System.err.println("  --dump_results: Write the decoded contents to input.txt");
        System.err.println("  --dump_black_point: Compare black point algorithms as input.mono.png");
        System.err.println("  --multi: Scans image for multiple barcodes");
        System.err.println("  --brief: Only output one line per file, omitting the contents");
        System.err.println("  --recursive: Descend into subdirectories");
        System.err.println("  --crop=left,top,width,height: Only examine cropped region of input image(s)");
        StringBuilder builder = new StringBuilder();
        builder.append("  --possibleFormats=barcodeFormat[,barcodeFormat2...] where barcodeFormat is any of: ");
        for (BarcodeFormat format : BarcodeFormat.values()) {
            builder.append(format).append(',');
        }
        builder.setLength(builder.length() - 1);
        System.err.println(builder);
    }
}
