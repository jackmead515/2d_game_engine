package com.engine.jsm.world;

public class Layer {

    private int level;

    public static Layer from(int level) {
        return new Layer(level);
    }

    public Layer(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || obj.getClass() != this.getClass())
            return false;

        Layer layer = (Layer) obj;

        return layer.getLevel() == this.getLevel();
    }

    @Override
    public int hashCode() {
        return this.getLevel();
    }

}
