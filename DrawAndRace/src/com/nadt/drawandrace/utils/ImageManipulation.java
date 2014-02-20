package com.nadt.drawandrace.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public final class ImageManipulation {
	
	public static int threshold = 127;
	
	/**
	 * Private constructor to prevent class instanciation
	 */
	private ImageManipulation(){}
	
	/**
	 * Returns a black and white (only black and white, no grey) bitmap of
	 * the given bitmap
	 * 
	 * @param bmpSrc
	 * @return
	 */
	public static Bitmap toBlackAndWhite(Bitmap bmpSrc) {
		int width, height;
	    height = bmpSrc.getHeight();
	    width = bmpSrc.getWidth();
	    
	    Bitmap bmpBlackAndWhite = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
	    int color;
	    for (int i = width; i --> 0 ;) {
			for (int j = height; j --> 0 ;) {
				color = bmpSrc.getPixel(i, j) > threshold ? Color.BLACK : Color.WHITE;
				bmpBlackAndWhite.setPixel(i, j, color);
			}
		}
	    
	    return bmpBlackAndWhite;
	}
	
	/**
	 * Returns a grayscale (monochrome) bitmap of the given bitmap
	 * 
	 * @param bmpSrc
	 * @return
	 */
	public static Bitmap toGrayscale(Bitmap bmpSrc) {        
	    int width, height;
	    height = bmpSrc.getHeight();
	    width = bmpSrc.getWidth();    

	    Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
	    Canvas c = new Canvas(bmpGrayscale);
	    Paint paint = new Paint();
	    ColorMatrix cm = new ColorMatrix();
	    cm.setSaturation(0);
	    ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
	    paint.setColorFilter(f);
	    c.drawBitmap(bmpSrc, 0, 0, paint);
	    return bmpGrayscale;
	}
}
