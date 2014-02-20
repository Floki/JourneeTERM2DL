package com.nadt.drawandrace.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Static methods to manipulate images
 * 
 */
public final class ImageManipulation {

	public static final String TMP_FILE_NAME = "/tmp.png";
	
	public static final String BLACK_WHITE_FILE_PATH = Constants.storage
			+ TMP_FILE_NAME;
	
	/** Empyrical value */
	public static int threshold = 0xff666666;
	
	/** Values should be tested */
	public static final int MAX_IMG_WIDTH = 500;
	public static final int MAX_IMG_HEIGHT = 1000;

	/**
	 * Private constructor to prevent class instanciation
	 */
	private ImageManipulation() {
	}

	/**
	 * Returns a black and white (only black and white, no grey) bitmap of the
	 * given bitmap
	 * 
	 * // TODO : optimize ? // TODO : log %
	 * 
	 * @param bmpSrc
	 * @return
	 */
	public static Bitmap toBlackAndWhite(final Bitmap bmpSrc) {
		final int width;
		final int height;
		height = bmpSrc.getHeight();
		width = bmpSrc.getWidth();

		final Bitmap bmpGrayScaled = toGrayscale(bmpSrc);

		Thread thread = new Thread() {
			@Override
			public void run() {
				int color;
				for (int i = width; i --> 0;) {
					for (int j = height; j --> 0;) {
						color = bmpSrc.getPixel(i, j) > threshold ? Color.WHITE
								: Color.BLACK;
						bmpGrayScaled.setPixel(i, j, color);
					}
				}

			}
		};

		thread.start();

		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bmpGrayScaled;
	}

	/**
	 * If given bmpSrc's height is > maxHeight OR its width is > maxWidth, 
	 * returns a scaled down bitmap with height = maxHeight, width = maxWidth.
	 * 
	 * NOT TESTED
	 * 
	 * If notthing > max, returns given bitmap
	 * @param bmpSrc
	 * @param maxWidth
	 * @param maxHeight
	 * @return
	 */
	private static Bitmap scaleDown(final Bitmap bmpSrc, int maxWidth, int maxHeight) {
		int height = bmpSrc.getHeight();
		int width = bmpSrc.getWidth(); 
		Bitmap scaled;
		
		boolean scaledDown = false;
		if(bmpSrc.getHeight() > maxHeight) {
			height = maxHeight;
			scaledDown = true;
		}
		if(bmpSrc.getWidth() > maxWidth) {
			width = maxWidth;
			scaledDown = true;
		}
		
		if(scaledDown) {
			scaled = Bitmap.createScaledBitmap(bmpSrc, height, width, true);
		} else {
			scaled = bmpSrc;
		}
		return scaled;
	}

	/**
	 * Returns a grayscale (monochrome) bitmap of the given bitmap
	 * 
	 * @param bmpSrc
	 * @return
	 */
	private static Bitmap toGrayscale(Bitmap bmpSrc) {
		int width, height;
		height = bmpSrc.getHeight();
		width = bmpSrc.getWidth();

		Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		Canvas c = new Canvas(bmpGrayscale);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmpSrc, 0, 0, paint);
		return bmpGrayscale;
	}

	/**
	 * Reads an image in the given File and returns another file containing a
	 * black & white version of the image
	 * 
	 * @param input
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File toBlackAndWhite(File input) throws FileNotFoundException {
		if (!input.exists()) {
			throw new FileNotFoundException("The given File does not exist : "
					+ input.getAbsolutePath());
		} else if (!input.isFile()) {
			throw new IllegalArgumentException(
					"The given File is NOT a file : " + input.getAbsolutePath());
		} else if (!input.canRead()) {
			throw new IllegalArgumentException("Can't read from File : "
					+ input.getAbsolutePath());
		}

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		Bitmap bitmap = BitmapFactory.decodeFile(input.getPath(), options);

		Bitmap blackAndWhite = toBlackAndWhite(bitmap);

		File f = saveBitmapToFile(BLACK_WHITE_FILE_PATH, blackAndWhite);

		return f;
	}

	/**
	 * Saves the given bitmap to a File at the given filepath
	 * 
	 * @param filePath
	 * @param bitmap
	 * @return
	 */
	private static File saveBitmapToFile(String filePath, Bitmap bitmap) {
		try {
			FileOutputStream out = new FileOutputStream(filePath);
			bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new File(filePath);
	}
	
	
	
}
