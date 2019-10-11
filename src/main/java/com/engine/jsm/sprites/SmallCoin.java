package com.engine.jsm.sprites;

import com.engine.jsm.images.ImageConstants;

import java.util.concurrent.TimeUnit;

public class SmallCoin extends AnimatedSprite {

    public SmallCoin(int id) {
        super(id, ImageConstants.SMALL_COIN);
        this.setUpdateTime(TimeUnit.MILLISECONDS.toNanos(100));
    }


}
