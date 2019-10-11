package com.engine.jsm.util;

import java.awt.geom.Point2D;

public class FloatRect {

	private float x;
	private float y;
	private float w;
	private float h;
	
	public static FloatRect from(float x, float y, float w, float h) {
		return new FloatRect(x, y, w, h);
	}
	
	public static FloatRect from(double x, double y, double w, double h) {
		return new FloatRect((float)x, (float)y, (float)w, (float)h);
	}
	
	public FloatRect(float x, float y, float w, float h) {
		this.setX(x);
		this.setY(y);
		this.setWidth(w);
		this.setHeight(h);
	}
	
	public synchronized float x() {
		return x;
	}
	
	public synchronized void setX(float x) {
		this.x = x;
	}
	
	public synchronized float y() {
		return y;
	}
	
	public synchronized void setY(float y) {
		this.y = y;
	}
	
	public synchronized float width() {
		return w;
	}
	
	public synchronized void setWidth(float w) {
		this.w = w;
	}
	
	public synchronized float height() {
		return h;
	}
	
	public synchronized void setHeight(float h) {
		this.h = h;
	}
	
	public synchronized Point2D center() {
		return new Point2D.Float(x()+width()/2, y()+height()/2);
	}
	
	public synchronized boolean intersects(FloatRect rect) {
		return !((x()+width() < rect.x()) ||
				(rect.x()+rect.width() < x()) ||
				(y()+height() < rect.y()) ||
				(rect.y()+rect.height() < y()));
	}
	
	/**
	 * Tests if a point is inside or touching the rectangle
	 */
	public synchronized boolean contains(Point2D point) {		
		return ((point.getX() >= x()) && (point.getX() <= x() + width()) &&
                (point.getY() >= y()) && (point.getY() <= y() + height()));
	}
	
	@Override
	public String toString() {
		return "[" + this.x() + "," + this.y() + "," + this.width() + "," + this.height() + "]";
	}
	
}
