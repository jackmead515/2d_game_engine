package com.engine.jsm.ui;

import com.engine.jsm.images.ImageConstants;
import com.engine.jsm.images.ImageManager;

import java.awt.geom.Rectangle2D;

public class ThinFatScroll extends Button {
    public ThinFatScroll(int id) {
        super(id);
        setImage(ImageManager.get(ImageConstants.THIN_FAT_SCROLL_ICON)[0]);
        setDimensions(new double[] { 96, 96 });
    }
}
