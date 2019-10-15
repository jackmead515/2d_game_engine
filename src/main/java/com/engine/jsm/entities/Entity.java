package com.engine.jsm.entities;

import com.engine.jsm.abilities.CollisionType;
import com.engine.jsm.main.Constants;

import java.awt.*;

public class Entity implements IRenderable, IUpdateable {

	private int id;
	private double[] dimensions;
	private double[] position;
	private double[] nextPosition;
    private double[] scale;
	private int layer;
	
	public Entity(int id) {
		dimensions = new double[] { 0, 0 };
		position = new double[] { 0, 0 };
		nextPosition = new double[] { 0, 0 };
		scale = new double[] { 1, 1 };
		layer = Constants.GROUND_LAYER;
		this.id = id;
	}

	@Override
	public void render(Graphics2D g2, Canvas reference) {}

	@Override
	public void renderDebug(Graphics2D g2, Canvas reference) {
		double[] bounds = getBounds();
		double[] position = getPosition();
		g2.setColor(Color.GREEN);
		g2.drawRect((int)bounds[0], (int)bounds[1], (int)bounds[2], (int)bounds[3]);
		g2.fillOval((int)position[0]-2, (int)position[1]-2, 4, 4);
	}

	@Override
	public void update() {}

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

	/**
	 * Returns the center of this entity;
	 */
	public double[] getPosition() { return position; }

	public void setPosition(double[] position) {
		this.position = position;
	}

	public void setPositionX(double x) {
		this.position[0] = x;
	}

	public void setPositionY(double y) {
		this.position[1] = y;
	}

	public double[] getTopLeftCorner() {
		return new double[] {
			position[0]-dimensions[0]/2,
			position[1]-dimensions[1]/2
		};
	}

	public void setTopLeftCorner(double[] position) {
		setPositionX(position[0]+dimensions[0]/2);
		setPositionY(position[1]+dimensions[1]/2);
	}

	public void setTopLeftCornerX(double x) {
		setPositionX(x+dimensions[0]/2);
	}

	public void setTopLeftCornerY(double y) {
		setPositionY(y+dimensions[1]/2);
	}

	/**
	 * Gets the resulting rectangle from the position and dimensions.
	 * Position is translated by half the of the respecting dimension
	 * as the position is the center of this entity;
	 * @return
	 */
	public double[] getBounds() {
		double[] corner = getTopLeftCorner();
		double[] dimension = getDimensions();
		return new double[] {
			corner[0],
			corner[1],
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
