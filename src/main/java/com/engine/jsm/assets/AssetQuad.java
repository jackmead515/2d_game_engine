//package com.engine.jsm.assets;
//
//import com.engine.jsm.entities.Entity;
//
//import java.awt.geom.Point2D;
//import java.util.ArrayList;
//
//public class AssetQuad<T extends Entity> {
//
//	private enum Quadrant {
//		NW, NE, SW, SE
//	}
//
//	private int maxEntities;
//	private boolean subdivided;
//	private int level;
//	private int maxDepth;
//	private FloatRect bounds;
//	private ArrayList<T> entities;
//	private AssetQuad<T> nwQuad;
//	private AssetQuad<T> neQuad;
//	private AssetQuad<T> swQuad;
//	private AssetQuad<T> seQuad;
//
//	public AssetQuad(int level, int maxDepth, int maxEntites, FloatRect bounds) {
//		this.setLevel(level);
//		this.setMaxDepth(maxDepth);
//		this.maxEntities = maxEntites;
//		this.bounds = bounds;
//		this.subdivided = false;
//		this.entities = new ArrayList<T>(maxEntities+1);
//	}
//
//	public boolean isSubdivided() {
//		return this.subdivided;
//	}
//
//	public boolean canSubdivide() {
//		return this.getLevel() < this.getMaxDepth()-1 && !this.isSubdivided();
//	}
//
//	public boolean shouldSubdivide() {
//		return this.entities.size() >= this.getMaxEntities();
//	}
//
//	public int getMaxEntities() {
//		return this.maxEntities;
//	}
//
//	public int getDepth() {
//		int depth = 1;
//		if(this.isSubdivided()) {
//			int nwd = this.nwQuad.getDepth();
//			int ned = this.neQuad.getDepth();
//			int swd = this.swQuad.getDepth();
//			int sed = this.seQuad.getDepth();
//
//			int maxInternalDepth = Math.max(Math.max(swd, sed), Math.max(nwd, ned));
//			depth += maxInternalDepth;
//		}
//		return depth;
//	}
//
//	public int getTotalItems() {
//		int items = 0;
//		if(this.isSubdivided()) {
//			items+=this.nwQuad.getTotalItems();
//			items+=this.seQuad.getTotalItems();
//			items+=this.swQuad.getTotalItems();
//			items+=this.neQuad.getTotalItems();
//		} else {
//			items += this.entities.size();
//		}
//		return items;
//	}
//
//	public Quadrant getQuadrant(Point2D point) {
//		if(this.nwQuad.contains(point)) {
//			return Quadrant.NW;
//		}
//		if(this.neQuad.contains(point)) {
//			return Quadrant.NE;
//		}
//		if(this.swQuad.contains(point)) {
//			return Quadrant.SW;
//		}
//		if(this.seQuad.contains(point)) {
//			return Quadrant.SE;
//		}
//		return null;
//	}
//
//	public void clear() {
//		this.entities.clear();
//		if(this.isSubdivided()) {
//			this.nwQuad.clear();
//			this.neQuad.clear();
//			this.swQuad.clear();
//			this.seQuad.clear();
//		}
//	}
//
//	public ArrayList<T> retrieve(FloatRect bounds) {
//		if(this.isSubdivided()) {
//			Quadrant quadrant = this.getQuadrant(bounds.center());
//
//			if(quadrant == null) {
//				throw new NullPointerException();
//			}
//
//			System.out.println(this.getLevel() + ", " + quadrant);
//
//			switch(quadrant) {
//				case NW: return this.nwQuad.retrieve(bounds);
//				case NE: return this.neQuad.retrieve(bounds);
//				case SW: return this.swQuad.retrieve(bounds);
//				case SE: return this.seQuad.retrieve(bounds);
//				default: throw new NullPointerException();
//			}
//		} else {
//			return this.entities;
//		}
//	}
//
//	public void insert(T e) {
//		if(this.isSubdivided()) {
//			Quadrant quadrant = this.getQuadrant(e.getBounds().center());
//
//			if(quadrant == null) {
//
//				System.out.println("Bounds: " + this.getBounds().toString());
//				System.out.println("NW Bounds: " + this.nwQuad.getBounds());
//				System.out.println("NE Bounds: " + this.neQuad.getBounds());
//				System.out.println("SW Bounds: " + this.swQuad.getBounds());
//				System.out.println("SE Bounds: " + this.seQuad.getBounds());
//
//				System.out.println("Entity Bounds: " + e.getBounds().toString());
//				System.out.println("Entity Center: " + e.getBounds().center().toString());
//				throw new NullPointerException();
//			}
//
//			switch(quadrant) {
//				case NW: this.nwQuad.insert(e); break;
//				case NE: this.neQuad.insert(e); break;
//				case SW: this.swQuad.insert(e); break;
//				case SE: this.seQuad.insert(e); break;
//				default: throw new NullPointerException();
//			}
//		} else {
//			this.entities.add(e);
//
//			if(this.shouldSubdivide() && this.canSubdivide()) {
//				this.subdivide();
//				for(T e1 : this.entities) {
//					this.insert(e1);
//				}
//			}
//		}
//	}
//
//	public void subdivide() {
//		System.out.println("Subdividing: " + this.getBounds().toString());
//		float x = this.getBounds().x();
//		float y = this.getBounds().y();
//		float w = this.getBounds().width();
//		float h = this.getBounds().height();
//
//		this.nwQuad = new AssetQuad<>(
//				this.getLevel()+1,
//				this.getMaxDepth(),
//				this.getMaxEntities(),
//				FloatRect.from(x, y, w/2, h/2)
//		);
//		this.neQuad = new AssetQuad<>(
//				this.getLevel()+1,
//				this.getMaxDepth(),
//				this.getMaxEntities(),
//				FloatRect.from(x+w/2, y, w/2, h/2)
//		);
//		this.swQuad = new AssetQuad<>(
//				this.getLevel()+1,
//				this.getMaxDepth(),
//				this.getMaxEntities(),
//				FloatRect.from(x, y+h/2, w/2, h/2)
//		);
//		this.seQuad = new AssetQuad<>(
//				this.getLevel()+1,
//				this.getMaxDepth(),
//				this.getMaxEntities(),
//				FloatRect.from(x+w/2, y+h/2, w/2, h/2)
//		);
//
//		this.subdivided = true;
//	}
//
//	public void printTree() {
//		String spacer = new String(new char[(this.getLevel()+1)*2]).replace("\0", "-");
//		System.out.println(spacer + " Bounds: " + this.getBounds() + ", Items: " + this.entities.size());
//		if(this.isSubdivided()) {
//			this.nwQuad.printTree();
//			this.neQuad.printTree();
//			this.swQuad.printTree();
//			this.seQuad.printTree();
//		}
//	}
//
//	public boolean insersects(Entity e) {
//		return this.getBounds().intersects(e.getBounds());
//	}
//
//	public boolean contains(Point2D p) {
//		return this.getBounds().contains(p);
//	}
//
//	public int getLevel() {
//		return level;
//	}
//
//	public void setLevel(int level) {
//		this.level = level;
//	}
//
//	public int getMaxDepth() {
//		return maxDepth;
//	}
//
//	public void setMaxDepth(int maxDepth) {
//		this.maxDepth = maxDepth;
//	}
//
//	public synchronized FloatRect getBounds() {
//		return bounds;
//	}
//
//	public synchronized void setBounds(FloatRect bounds) {
//		this.bounds = bounds;
//	}
//}
