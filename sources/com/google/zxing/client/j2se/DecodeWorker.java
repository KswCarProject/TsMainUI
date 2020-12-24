package com.google.zxing.client.j2se;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Callable;
import javax.imageio.ImageIO;

final class DecodeWorker implements Callable<Integer> {
    private static final int BLACK = -16777216;
    private static final int RED = -65536;
    private static final int WHITE = -1;
    private final Config config;
    private final Queue<Path> inputs;

    DecodeWorker(Config config2, Queue<Path> inputs2) {
        this.config = config2;
        this.inputs = inputs2;
    }

    public Integer call() throws IOException {
        int successful = 0;
        while (true) {
            Path input = this.inputs.poll();
            if (input == null) {
                return Integer.valueOf(successful);
            }
            if (Files.exists(input, new LinkOption[0])) {
                if (this.config.isMulti()) {
                    Result[] results = decodeMulti(input.toUri(), this.config.getHints());
                    if (results != null) {
                        successful++;
                        if (this.config.isDumpResults()) {
                            dumpResultMulti(input, results);
                        }
                    }
                } else {
                    Result result = decode(input.toUri(), this.config.getHints());
                    if (result != null) {
                        successful++;
                        if (this.config.isDumpResults()) {
                            dumpResult(input, result);
                        }
                    }
                }
            } else if (decode(input.toUri(), this.config.getHints()) != null) {
                successful++;
            }
        }
    }

    private static void dumpResult(Path input, Result result) throws IOException {
        String name = input.getFileName().toString();
        int pos = name.lastIndexOf(46);
        if (pos > 0) {
            name = name.substring(0, pos) + ".txt";
        }
        Files.write(input.getParent().resolve(name), Collections.singleton(result.getText()), StandardCharsets.UTF_8, new OpenOption[0]);
    }

    private static void dumpResultMulti(Path input, Result[] results) throws IOException {
        String name = input.getFileName().toString();
        int pos = name.lastIndexOf(46);
        if (pos > 0) {
            name = name.substring(0, pos) + ".txt";
        }
        Path dumpFile = input.getParent().resolve(name);
        Collection<String> resultTexts = new ArrayList<>();
        for (Result result : results) {
            resultTexts.add(result.getText());
        }
        Files.write(dumpFile, resultTexts, StandardCharsets.UTF_8, new OpenOption[0]);
    }

    private Result decode(URI uri, Map<DecodeHintType, ?> hints) throws IOException {
        LuminanceSource source;
        BufferedImage image = ImageReader.readImage(uri);
        try {
            if (this.config.getCrop() == null) {
                source = new BufferedImageLuminanceSource(image);
            } else {
                int[] crop = this.config.getCrop();
                source = new BufferedImageLuminanceSource(image, crop[0], crop[1], crop[2], crop[3]);
            }
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            if (this.config.isDumpBlackPoint()) {
                dumpBlackPoint(uri, image, bitmap);
            }
            Result result = new MultiFormatReader().decode(bitmap, hints);
            if (this.config.isBrief()) {
                System.out.println(uri + ": Success");
                return result;
            }
            ParsedResult parsedResult = ResultParser.parseResult(result);
            System.out.println(uri + " (format: " + result.getBarcodeFormat() + ", type: " + parsedResult.getType() + "):\nRaw result:\n" + result.getText() + "\nParsed result:\n" + parsedResult.getDisplayResult());
            System.out.println("Found " + result.getResultPoints().length + " result points.");
            for (int i = 0; i < result.getResultPoints().length; i++) {
                ResultPoint rp = result.getResultPoints()[i];
                if (rp != null) {
                    System.out.println("  Point " + i + ": (" + rp.getX() + ',' + rp.getY() + ')');
                }
            }
            return result;
        } catch (NotFoundException e) {
            System.out.println(uri + ": No barcode found");
            return null;
        }
    }

    private Result[] decodeMulti(URI uri, Map<DecodeHintType, ?> hints) throws IOException {
        LuminanceSource source;
        BufferedImage image = ImageReader.readImage(uri);
        try {
            if (this.config.getCrop() == null) {
                source = new BufferedImageLuminanceSource(image);
            } else {
                int[] crop = this.config.getCrop();
                source = new BufferedImageLuminanceSource(image, crop[0], crop[1], crop[2], crop[3]);
            }
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            if (this.config.isDumpBlackPoint()) {
                dumpBlackPoint(uri, image, bitmap);
            }
            Result[] results = new GenericMultipleBarcodeReader(new MultiFormatReader()).decodeMultiple(bitmap, hints);
            if (this.config.isBrief()) {
                System.out.println(uri + ": Success");
                return results;
            }
            for (Result result : results) {
                ParsedResult parsedResult = ResultParser.parseResult(result);
                System.out.println(uri + " (format: " + result.getBarcodeFormat() + ", type: " + parsedResult.getType() + "):\nRaw result:\n" + result.getText() + "\nParsed result:\n" + parsedResult.getDisplayResult());
                System.out.println("Found " + result.getResultPoints().length + " result points.");
                for (int i = 0; i < result.getResultPoints().length; i++) {
                    ResultPoint rp = result.getResultPoints()[i];
                    System.out.println("  Point " + i + ": (" + rp.getX() + ',' + rp.getY() + ')');
                }
            }
            return results;
        } catch (NotFoundException e) {
            System.out.println(uri + ": No barcode found");
            return null;
        }
    }

    private static void dumpBlackPoint(URI uri, BufferedImage image, BinaryBitmap bitmap) {
        if (!uri.getPath().contains(".mono.png")) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int stride = width * 3;
            int[] pixels = new int[(stride * height)];
            int[] argb = new int[width];
            for (int y = 0; y < height; y++) {
                image.getRGB(0, y, width, 1, argb, 0, width);
                System.arraycopy(argb, 0, pixels, y * stride, width);
            }
            BitArray row = new BitArray(width);
            for (int y2 = 0; y2 < height; y2++) {
                try {
                    row = bitmap.getBlackRow(y2, row);
                    int offset = (y2 * stride) + width;
                    for (int x = 0; x < width; x++) {
                        pixels[offset + x] = row.get(x) ? -16777216 : -1;
                    }
                } catch (NotFoundException e) {
                    int offset2 = (y2 * stride) + width;
                    Arrays.fill(pixels, offset2, offset2 + width, -65536);
                }
            }
            int y3 = 0;
            while (y3 < height) {
                try {
                    BitMatrix matrix = bitmap.getBlackMatrix();
                    int offset3 = (y3 * stride) + (width * 2);
                    for (int x2 = 0; x2 < width; x2++) {
                        pixels[offset3 + x2] = matrix.get(x2, y3) ? -16777216 : -1;
                    }
                    y3++;
                } catch (NotFoundException e2) {
                }
            }
            writeResultImage(stride, height, pixels, uri, ".mono.png");
        }
    }

    private static void writeResultImage(int stride, int height, int[] pixels, URI uri, String suffix) {
        int pos;
        BufferedImage result = new BufferedImage(stride, height, 2);
        result.setRGB(0, 0, stride, height, pixels, 0, stride);
        String resultName = uri.getPath();
        if ("http".equals(uri.getScheme()) && (pos = resultName.lastIndexOf(47)) > 0) {
            resultName = '.' + resultName.substring(pos);
        }
        int pos2 = resultName.lastIndexOf(46);
        if (pos2 > 0) {
            resultName = resultName.substring(0, pos2);
        }
        String resultName2 = resultName + suffix;
        try {
            if (!ImageIO.write(result, "png", Paths.get(resultName2, new String[0]).toFile())) {
                System.err.println("Could not encode an image to " + resultName2);
            }
        } catch (IOException e) {
            System.err.println("Could not write to " + resultName2);
        }
    }
}
