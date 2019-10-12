package com.engine.jsm.assets;

import com.engine.jsm.entities.Entity;
import com.engine.jsm.util.GeomUtil;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AssetQuad<T extends Entity> {

//	private enum Quadrant {
//		NW, NE, SW, SE
//	}

	private int maxEntities;
	private boolean subdivided;
	private int level;
	private int maxDepth;
	private double[] bounds;
	private CopyOnWriteArrayList<T> entities;
	private AssetQuad<T> nwQuad;
	private AssetQuad<T> neQuad;
	private AssetQuad<T> swQuad;
	private AssetQuad<T> seQuad;

	public AssetQuad(int level, int maxDepth, int maxEntites, double[] bounds) {
        this.entities = new CopyOnWriteArrayList<>();
		this.setLevel(level);
		this.setMaxDepth(maxDepth);
		this.maxEntities = maxEntites;
		this.bounds = bounds;
		this.subdivided = false;
	}

	public boolean isSubdivided() {
		return this.subdivided;
	}

	public boolean canSubdivide() {
		return this.getLevel() < this.getMaxDepth()-1 && !this.isSubdivided();
	}

	public boolean shouldSubdivide() {
		return this.entities.size() >= this.getMaxEntities();
	}

	public int getMaxEntities() {
		return this.maxEntities;
	}

	public int getDepth() {
		int depth = 1;
		if(this.isSubdivided()) {
			int nwd = this.nwQuad.getDepth();
			int ned = this.neQuad.getDepth();
			int swd = this.swQuad.getDepth();
			int sed = this.seQuad.getDepth();

			int maxInternalDepth = Math.max(Math.max(swd, sed), Math.max(nwd, ned));
			depth += maxInternalDepth;
		}
		return depth;
	}

	public int getTotalItems() {
		int items = 0;
		if(this.isSubdivided()) {
			items+=this.nwQuad.getTotalItems();
			items+=this.seQuad.getTotalItems();
			items+=this.swQuad.getTotalItems();
			items+=this.neQuad.getTotalItems();
		} else {
			items += this.entities.size();
		}
		return items;
	}

//	public Quadrant getQuadrant(double[] point) {
//		if(GeomUtil.contains(nwQuad.getBounds(), point)) {
//			return Quadrant.NW;
//		}
//		if(GeomUtil.contains(neQuad.getBounds(), point)) {
//			return Quadrant.NE;
//		}
//		if(GeomUtil.contains(swQuad.getBounds(), point)) {
//			return Quadrant.SW;
//		}
//		if(GeomUtil.contains(seQuad.getBounds(), point)) {
//			return Quadrant.SE;
//		}
//		return null;
//	}

	public void reset() {
        this.entities = new CopyOnWriteArrayList<>();
	    subdivided = false;
        this.nwQuad = null;
        this.neQuad = null;
        this.swQuad = null;
        this.seQuad = null;
	}

	public ArrayList<T> query(double[] bounds) {
	    ArrayList results = new ArrayList();
	    if (!GeomUtil.intersects(getBounds(), bounds)) {
	        return new ArrayList<>();
        }
	    for (T item : entities) {
	        if (GeomUtil.intersects(bounds, item.getBounds())) {
                results.add(item);
            }
        }
	    if (!this.isSubdivided()) {
	        return results;
        }
	    results.addAll(nwQuad.query(bounds));
	    results.addAll(neQuad.query(bounds));
	    results.addAll(swQuad.query(bounds));
        results.addAll(seQuad.query(bounds));
        return results;
	}

	public void insertAll(List<T> list) {
        for (T item : list) { insert(item); }
    }

	public boolean insert(T e) {
	    if (!GeomUtil.contains(getBounds(), e.getPosition())) {
	        return false;
        }
	    if (!this.isSubdivided() && entities.size() <= maxEntities) {
	        entities.add(e);
	        return true;
        }
        if (this.shouldSubdivide() && this.canSubdivide()) {
            this.subdivide();
        }
        if (this.nwQuad.insert(e)) { return true; }
        if (this.neQuad.insert(e)) { return true; }
        if (this.swQuad.insert(e)) { return true; }
        if (this.seQuad.insert(e)) { return true; }
        return false;
	}

	public void subdivide() {
		double[] bounds = getBounds();
		double x = bounds[0]; double y = bounds[1];
		double w = bounds[2]; double h = bounds[3];

		this.nwQuad = new AssetQuad<>(
				this.getLevel()+1,
				this.getMaxDepth(),
				this.getMaxEntities(),
                new double[] { x, y, w/2, h/2 }
		);
		this.neQuad = new AssetQuad<>(
				this.getLevel()+1,
				this.getMaxDepth(),
				this.getMaxEntities(),
                new double[] { x+w/2, y, w/2, h/2 }
		);
		this.swQuad = new AssetQuad<>(
				this.getLevel()+1,
				this.getMaxDepth(),
				this.getMaxEntities(),
                new double[] { x, y+h/2, w/2, h/2 }
		);
		this.seQuad = new AssetQuad<>(
				this.getLevel()+1,
				this.getMaxDepth(),
				this.getMaxEntities(),
				new double[] { x+w/2, y+h/2, w/2, h/2 }
		);

		this.subdivided = true;
	}

	public void printTree() {
		String spacer = new String(new char[(this.getLevel()+1)*2]).replace("\0", "-");
		System.out.println(spacer + " Bounds: " + this.getBounds() + ", Items: " + this.entities.size());
		if(this.isSubdivided()) {
			this.nwQuad.printTree();
			this.neQuad.printTree();
			this.swQuad.printTree();
			this.seQuad.printTree();
		}
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public synchronized double[] getBounds() {
		return bounds;
	}

	public synchronized void setBounds(double[] bounds) {
		this.bounds = bounds;
	}
}
