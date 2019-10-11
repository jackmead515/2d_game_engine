package com.engine.jsm.images;

import com.engine.jsm.main.Constants;

public class ImageConstants {

    public final static int LEAF = 0;
    public final static int LLAMA_SHEET = 1;
    public final static int GRASS_BACKGROUND = 2;
    public final static int SMALL_COIN = 3;
    public final static int BOW_ICON = 4;
    public final static int CHEST_ICON = 5;
    public final static int HEAL_ICON = 6;
    public final static int SHIELD_ICON = 7;
    public final static int SPELL_ICON = 8;
    public final static int SWORD_ICON = 9;
    public final static int THIN_FAT_SCROLL_ICON = 10;

    /**
     * Returns dimensions to load file from. Default
     * returns 32x32.
     * @param name
     * @return
     */
    public static double[] getDimensionsFromFileName(String name) {
        switch(name) {
            case "thin_fat_scroll.png": return new double[] {96, 96};
            default: return new double[] {Constants.IMAGE_REFERENCE_SIZE,  Constants.IMAGE_REFERENCE_SIZE};
        }
    }

    public static int getIdFromFileName(String name) {
        switch(name) {
            case "leaf.png": return LEAF;
            case "llama.png": return LLAMA_SHEET;
            case "grass_background.png": return GRASS_BACKGROUND;
            case "small_coin.png": return SMALL_COIN;
            case "bow_icon.png": return BOW_ICON;
            case "chest_icon.png": return CHEST_ICON;
            case "heal_icon.png": return HEAL_ICON;
            case "shield_icon.png": return SHIELD_ICON;
            case "spell_icon.png": return SPELL_ICON;
            case "sword_icon.png": return SWORD_ICON;
            case "thin_fat_scroll.png": return THIN_FAT_SCROLL_ICON;
            default: throw new NullPointerException("id not found for file: " + name);
        }
    }
}
