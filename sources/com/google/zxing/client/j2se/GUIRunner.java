package com.google.zxing.client.j2se;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.common.HybridBinarizer;
import com.ts.can.CanCameraUI;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public final class GUIRunner extends JFrame {
    private final JLabel imageLabel = new JLabel();
    private final JTextComponent textArea = new JTextArea();

    private GUIRunner() {
        this.textArea.setEditable(false);
        this.textArea.setMaximumSize(new Dimension(CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 200));
        Container panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(this.imageLabel);
        panel.add(this.textArea);
        setTitle("ZXing");
        setSize(CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1);
        setDefaultCloseOperation(3);
        setContentPane(panel);
        setLocationRelativeTo((Component) null);
    }

    public static void main(String[] args) throws MalformedURLException {
        GUIRunner runner = new GUIRunner();
        runner.setVisible(true);
        runner.chooseImage();
    }

    private void chooseImage() throws MalformedURLException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        Path file = fileChooser.getSelectedFile().toPath();
        Icon imageIcon = new ImageIcon(file.toUri().toURL());
        setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight() + 100);
        this.imageLabel.setIcon(imageIcon);
        this.textArea.setText(getDecodeText(file));
    }

    private static String getDecodeText(Path file) {
        try {
            try {
                return String.valueOf(new MultiFormatReader().decode(new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageReader.readImage(file.toUri()))))).getText());
            } catch (ReaderException re) {
                return re.toString();
            }
        } catch (IOException ioe) {
            return ioe.toString();
        }
    }
}
