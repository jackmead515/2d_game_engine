package com.engine.jsm.images;

import com.engine.jsm.main.Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class ImageManager {

	private static ImageStore imageStore = new ImageStore();

	public synchronized static BufferedImage[] get(int imageId) {
		return imageStore.get(imageId);
	}
	
	public static void load() {
		URL imageFolder = Main.class.getClassLoader().getResource("images");
		File[] files = new File(imageFolder.getPath()).listFiles();

		if (files == null) {
			throw new NullPointerException("No images found in resources folder");
		}

		for (File file : files) {
			load(file);
		}
	}

	private static void load(File file) {
		if (file.isDirectory()) {
			for (File subFile : file.listFiles()) { load(subFile); }
		} else {
			String fileName = file.getName();
			int id = ImageConstants.getIdFromFileName(fileName);
			double[] dims = ImageConstants.getDimensionsFromFileName(fileName);
			BufferedImage[] frames = ImageUtil.loadFrames(file, dims);
			imageStore.put(id, frames);
		}
	}

}
