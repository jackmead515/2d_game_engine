package com.engine.jsm.main;

import com.engine.jsm.assets.AssetQuad;
import com.engine.jsm.boundaries.Boundary;
import com.engine.jsm.creatures.Llama;
import com.engine.jsm.display.Display;
import com.engine.jsm.engine.EngineManager;
import com.engine.jsm.entities.Entity;
import com.engine.jsm.images.ImageConstants;
import com.engine.jsm.images.ImageManager;
import com.engine.jsm.sprites.Leaf;
import com.engine.jsm.sprites.SmallCoin;
import com.engine.jsm.ui.Button;
import com.engine.jsm.ui.GUIManager;
import com.engine.jsm.ui.ThinFatScroll;
import com.engine.jsm.world.World;

import java.util.ArrayList;

public class Main {

	public static EngineManager engine;
	public static GUIManager gui;
	public static Display display;
	public static World world;

	public static void main(String[] args) {
		engine = new EngineManager();
		gui = new GUIManager();
		display = new Display();
		world = new World();

		Stats.setDebug(true);

		ImageManager.load();
		display.init();
		world.init();

		test();
		testBoundaries();
		//testQuadTree();
		testGUI();

		engine.loop();
	}

	public static void testBoundaries() {
		for(int x = 160; x < 832; x+=32) {
			Boundary b = new Boundary(0);
			b.setBounds(new double[] { x, 160, 32, 32 });
			world.addBoundary(b);
		}
		for(int y = 160; y < 416; y+=32) {
			Boundary b = new Boundary(0);
			b.setBounds(new double[] { 832, y, 32, 32 });
			world.addBoundary(b);
		}
	}

	public static void testQuadTree() {
		AssetQuad<Entity> container = new AssetQuad<>(4, 100, 100, new double[] { 0, 0, 1000, 1000 });

		long now = System.nanoTime();
		int count = 0;
		for(int x = 10; x < 900; x+=10) {
			for(int y = 10; y < 900; y+=10) {
				Entity e = new Entity(0);
				e.setBounds(new double[] { x, y, 10, 10 });
				container.insert(e);
				count+=1;
			}
		}
		long elapsed = System.nanoTime()-now;

		System.out.println("Tree Depth: " + container.getDepth());
		System.out.println("Insertion Time: " + elapsed/1000000f + "ms");
		System.out.println("Total Counted Items: " + count);
		System.out.println("Total Inserted Items: " + container.getTotalItems());

		now = System.nanoTime();
		ArrayList<Entity> queried = container.query(new double[] { 50, 50, 10, 10 });
		elapsed = System.nanoTime()-now;

		System.out.println("Query Time: " + elapsed/1000000f + "ms");
		System.out.println("Queried Item Count: " + queried.size());

//		now = System.nanoTime();
//		container.getRoot().clear();
//		elapsed = System.nanoTime()-now;
//		System.out.println("Clear Time: " + elapsed/1000000f + "ms");

		container.printTree();
	}

	public static void testGUI() {
		double screenWidth = Stats.getScreenWidth();
		double screenHeight = Stats.getScreenHeight();
		double bb = 100;
		double[] center = Stats.getScreenCenter();

		Button swordIcon = new Button(0);
		swordIcon.setLayer(1);
		swordIcon.setImage(ImageManager.get(ImageConstants.SWORD_ICON)[0]);
		swordIcon.setBounds(new double[] {
				center[0] - (32/2)*5 - 3,
				center[1]+screenHeight/2-bb,
				32,
				32
		});
		gui.add(swordIcon);

		Button spellIcon = new Button(0);
		spellIcon.setLayer(1);
		spellIcon.setImage(ImageManager.get(ImageConstants.SPELL_ICON)[0]);
		spellIcon.setBounds(new double[] {
				center[0] - (32/2)*3 - 2,
				center[1]+screenHeight/2-bb,
				32,
				32
		});
		gui.add(spellIcon);

		Button bowIcon = new Button(0);
		bowIcon.setLayer(1);
		bowIcon.setImage(ImageManager.get(ImageConstants.BOW_ICON)[0]);
		bowIcon.setBounds(new double[] {
				center[0] - (32/2) - 1,
				center[1]+screenHeight/2-bb,
				32,
				32
		});
		gui.add(bowIcon);

		Button healIcon = new Button(0);
		healIcon.setLayer(1);
		healIcon.setImage(ImageManager.get(ImageConstants.HEAL_ICON)[0]);
		healIcon.setBounds(new double[] {
				center[0] + (32/2) + 1,
				center[1]+screenHeight/2-bb,
				32,
				32
		});
		gui.add(healIcon);

		Button shieldIcon = new Button(0);
		shieldIcon.setLayer(1);
		shieldIcon.setImage(ImageManager.get(ImageConstants.SHIELD_ICON)[0]);
		shieldIcon.setBounds(new double[] {
				center[0] + (32/2)*3 + 2,
				center[1]+screenHeight/2-bb,
				32,
				32
		});
		gui.add(shieldIcon);

		Button chestIcon = new Button(0);
		chestIcon.setLayer(1);
		chestIcon.setImage(ImageManager.get(ImageConstants.CHEST_ICON)[0]);
		chestIcon.setBounds(new double[] {
				center[0] + (32/2)*5 + 3,
				center[1]+screenHeight/2-bb,
				32,
				32
		});
		gui.add(chestIcon);

		ThinFatScroll scroll = new ThinFatScroll(0);
		scroll.setLayer(1);
		scroll.setPosition(new double[] { 500, 500 });
		gui.add(scroll);
	}

	public static void test() {
		Leaf leaf = new Leaf(0);
		leaf.setLayer(Constants.GROUND_LAYER);
		leaf.setBounds(new double[] {100, 100, 32, 32});

		SmallCoin coin = new SmallCoin(0);
		coin.setLayer(Constants.GROUND_LAYER);
		coin.setBounds(new double[] {200, 200, 32, 32});

		for(int i = 0; i < 10; i++) {
			Llama llama = new Llama(0);
			llama.setLayer(Constants.GROUND_LAYER);
			llama.setBounds(new double[] { Stats.getScreenWidth()/2, Stats.getScreenHeight()/2, 32, 32 });
			world.addCollisionEntity(llama);
		}

		world.addSprite(leaf);
		world.addSprite(coin);
	}
}
