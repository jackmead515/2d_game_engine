package com.engine.jsm.images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.util.LinkedList;

public class ImageUtil {

    public static BufferedImage[] loadFrames(File file, double[] dims) {
        ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
        BufferedImage original = toBufferedImage(imageIcon.getImage());
        LinkedList<BufferedImage> images = new LinkedList<>();
        int width = original.getWidth();
        int height = original.getHeight();
        for (int y = 0; y < height; y += dims[1]) {
            for (int x = 0; x < width; x += dims[0]) {
                BufferedImage sub = original.getSubimage(x, y, (int)dims[0], (int)dims[1]);
                images.add(sub);
            }
        }
        return images.toArray(new BufferedImage[images.size()]);
    }

    public static boolean hasAlpha(Image image) {
        if (image instanceof BufferedImage) {
            BufferedImage bimage = (BufferedImage)image;
            return bimage.getColorModel().hasAlpha();
        }
        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {}
        ColorModel cm = pg.getColorModel();
        return cm.hasAlpha();
    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }
        image = new ImageIcon(image).getImage();

        boolean hasAlpha = hasAlpha(image);
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            if (hasAlpha) {
                transparency = Transparency.BITMASK;
            }
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(
                    image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            e.printStackTrace();
        }

        if (bimage == null) {
            int type = BufferedImage.TYPE_INT_RGB;
            if (hasAlpha) {
                type = BufferedImage.TYPE_INT_ARGB;
            }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        Graphics g = bimage.createGraphics();

        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }
}
