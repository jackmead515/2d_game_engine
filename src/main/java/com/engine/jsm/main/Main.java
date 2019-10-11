package com.engine.jsm.main;

import com.engine.jsm.creatures.Llama;
import com.engine.jsm.display.Display;
import com.engine.jsm.engine.EngineManager;
import com.engine.jsm.images.ImageConstants;
import com.engine.jsm.images.ImageManager;
import com.engine.jsm.sprites.Leaf;
import com.engine.jsm.sprites.SmallCoin;
import com.engine.jsm.ui.Button;
import com.engine.jsm.ui.GUIManager;
import com.engine.jsm.ui.ThinFatScroll;
import com.engine.jsm.world.World;

public class Main {

	public static EngineManager engineManager;
	public static GUIManager guiManager;
	public static Display display;
	public static World world;

	public static void main(String[] args) {
		engineManager = new EngineManager();
		guiManager = new GUIManager();
		display = new Display();
		world = new World();

		ImageManager.load();
		display.init();
		world.init();

		test();

		engineManager.loop();
	}

	public static void testQuadTree() {
		//		AssetContainer container = new AssetContainer(4, 100, FloatRect.from(0, 0, 1000, 1000));
//
//		long now = System.nanoTime();
//		int count = 0;
//		for(int x = 10; x < 900; x+=10) {
//			for(int y = 10; y < 900; y+=10) {
//				Entity e = new Entity();
//				e.setBounds(FloatRect.from(x, y, 10, 10));
//				count+=1;
//				container.getRoot().insert(e);
//			}
//		}
//		long elapsed = System.nanoTime()-now;
//
//		System.out.println("Tree Depth: " + container.getDepth());
//		System.out.println("Insertion Time: " + elapsed/1000000f + "ms");
//		System.out.println("Total Counted Items: " + count);
//		System.out.println("Total Inserted Items: " + container.getTotalItems());
//
//		now = System.nanoTime();
//		ArrayList<Entity> queried = container.getRoot().retrieve(FloatRect.from(50, 50, 10, 10));
//		elapsed = System.nanoTime()-now;
//
//		System.out.println("Query Time: " + elapsed/1000000f + "ms");
//		System.out.println("Queried Item Count: " + queried.size());
//
////		now = System.nanoTime();
////		container.getRoot().clear();
////		elapsed = System.nanoTime()-now;
////		System.out.println("Clear Time: " + elapsed/1000000f + "ms");
//
//		container.getRoot().printTree();
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
			llama.setBounds(new double[] {Stats.getScreenWidth()/2, Stats.getScreenHeight()/2, 32, 32});
			world.addSprite(llama);
		}

		Button button = new Button(0);
		button.setLayer(1);
		button.setImage(ImageManager.get(ImageConstants.SWORD_ICON)[0]);
		button.setSnapToCamera(true);
		button.setBounds(new double[] {100, 100, 32, 32});
		guiManager.add(button);

		ThinFatScroll scroll = new ThinFatScroll(0);
		scroll.setLayer(1);
		scroll.setSnapToCamera(true);
		scroll.setPosition(new double[] { 500, 500 });
		guiManager.add(scroll);

		world.addSprite(leaf);
		world.addSprite(coin);
	}
}
