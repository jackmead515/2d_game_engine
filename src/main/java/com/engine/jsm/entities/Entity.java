package com.engine.jsm.entities;

import com.engine.jsm.main.Constants;

import java.awt.*;

public class Entity implements IRenderable, IUpdateable {

	private int id;
	private boolean snapToCamera;
	private double[] dimensions;
	private double[] position;
    private double[] scale;
	private int layer;
	
	public Entity(int id) {
		dimensions = new double[] { 0, 0 };
		position = new double[] { 0, 0 };
		scale = new double[] { 1, 1 };
		layer = Constants.GROUND_LAYER;
		this.id = id;
	}

	@Override
	public void render(Graphics2D g2, Canvas reference) {}

	@Override
	public void update() {}

	public boolean isSnappedToCamera() {
		return snapToCamera;
	}

	public void setSnapToCamera(boolean snapToCamera) {
		this.snapToCamera = snapToCamera;
	}

	public double[] getDimensions() {
		return dimensions;
	}

	public void setDimensions(double[] dimensions) {
		this.dimensions = dimensions;
	}

	public void setWidth(double w) {
		this.dimensions[0] = w;
	}

	public void setHeight(double h) {
		this.dimensions[1] = h;
	}

	public double[] getPosition() {
		return position;
	}

	public void setPosition(double[] position) {
		this.position = position;
	}

	public void setPositionX(double x) {
		this.position[0] = x;
	}

	public void setPositionY(double x) {
		this.position[1] = x;
	}

	public double[] getBounds() {
		double[] position = getPosition();
		double[] dimension = getDimensions();
		return new double[] {
			position[0]-dimension[0]/2,
			position[1]-dimension[1]/2,
			dimension[0],
			dimension[1]
		};
	}

	public void setBounds(double[] bounds) {
		this.setPositionX(bounds[0]);
		this.setPositionY(bounds[1]);
		this.setWidth(bounds[2]);
		this.setHeight(bounds[3]);
	}

	public double[] getScaledBounds() {
		if (canScale()) {
			double[] position = getPosition();
			double[] dimension = getDimensions();
			double[] scale = getScale();
			return new double[] {
				position[0]-(dimension[0]*scale[0])/2,
				position[1]-(dimension[1]*scale[1])/2,
				dimension[0]*scale[0],
				dimension[1]*scale[1]
			};
		}

		return getBounds();
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public double[] getScale() {
        return scale;
    }

    public void setScale(double[] scale) {
		this.scale = scale;
	}

    public void setScaleX(double x) {
        this.scale[0] = x;
    }

	public void setScaleY(double y) {
		this.scale[1] = y;
	}

	public boolean canScale() {
		return this.scale[0] != 1 || this.scale[1] != 1;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null || obj.getClass() != this.getClass())
			return false;

		Entity entity = (Entity) obj;

		return entity.getId() == this.getId();
	}

	@Override
	public int hashCode() {
		return this.getId();
	}

	@Override
	public String toString() {
		return this.getBounds().toString();
	}
}
