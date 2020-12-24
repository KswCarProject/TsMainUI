package com.google.zxing.client.j2se;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import java.nio.file.Paths;
import java.util.Locale;

public final class CommandLineEncoder {
    private static final BarcodeFormat DEFAULT_BARCODE_FORMAT = BarcodeFormat.QR_CODE;
    private static final int DEFAULT_HEIGHT = 300;
    private static final String DEFAULT_IMAGE_FORMAT = "PNG";
    private static final String DEFAULT_OUTPUT_FILE = "out";
    private static final int DEFAULT_WIDTH = 300;

    private CommandLineEncoder() {
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            printUsage();
            return;
        }
        BarcodeFormat barcodeFormat = DEFAULT_BARCODE_FORMAT;
        String imageFormat = DEFAULT_IMAGE_FORMAT;
        String outFileString = DEFAULT_OUTPUT_FILE;
        int width = 300;
        int height = 300;
        String contents = null;
        for (String arg : args) {
            String[] argValue = arg.split("=");
            String str = argValue[0];
            char c = 65535;
            switch (str.hashCode()) {
                case -1610568666:
                    if (str.equals("--width")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1220233989:
                    if (str.equals("--image_format")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1178984135:
                    if (str.equals("--height")) {
                        c = 4;
                        break;
                    }
                    break;
                case 1394501281:
                    if (str.equals("--output")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1921591862:
                    if (str.equals("--barcode_format")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    barcodeFormat = BarcodeFormat.valueOf(argValue[1]);
                    break;
                case 1:
                    imageFormat = argValue[1];
                    break;
                case 2:
                    outFileString = argValue[1];
                    break;
                case 3:
                    width = Integer.parseInt(argValue[1]);
                    break;
                case 4:
                    height = Integer.parseInt(argValue[1]);
                    break;
                default:
                    if (!arg.startsWith("-")) {
                        contents = arg;
                        break;
                    } else {
                        System.err.println("Unknown command line option " + arg);
                        printUsage();
                        return;
                    }
            }
        }
        if (contents == null) {
            printUsage();
            return;
        }
        if (DEFAULT_OUTPUT_FILE.equals(outFileString)) {
            outFileString = outFileString + '.' + imageFormat.toLowerCase(Locale.ENGLISH);
        }
        MatrixToImageWriter.writeToPath(new MultiFormatWriter().encode(contents, barcodeFormat, width, height), imageFormat, Paths.get(outFileString, new String[0]));
    }

    private static void printUsage() {
        System.err.println("Encodes barcode images using the ZXing library\n");
        System.err.println("usage: CommandLineEncoder [ options ] content_to_encode");
        System.err.println("  --barcode_format=format: Format to encode, from BarcodeFormat class. Not all formats are supported. Defaults to QR_CODE.");
        System.err.println("  --image_format=format: image output format, such as PNG, JPG, GIF. Defaults to PNG");
        System.err.println("  --output=filename: File to write to. Defaults to out.png");
        System.err.println("  --width=pixels: Image width. Defaults to 300");
        System.err.println("  --height=pixels: Image height. Defaults to 300");
    }
}
