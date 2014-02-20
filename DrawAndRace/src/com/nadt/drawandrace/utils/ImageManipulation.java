package com.nadt.drawandrace.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public class ImageManipulation {
	
	/**
	 * Returns a black and white (only black and white, no grey) bitmap of
	 * the given bitmap
	 * 
	 * TODO : for now does a grayscale, change to do a real B&W 
	 * 
	 * @param bmpSrc
	 * @return
	 */
	public static Bitmap toBlackAndWhite(Bitmap bmpSrc) {
		int width, height;
	    height = bmpSrc.getHeight();
	    width = bmpSrc.getWidth();   
	    
	    Bitmap bmpBlackAndWhite = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
	    Canvas c = new Canvas(bmpBlackAndWhite);
	    Paint paint = new Paint();
	    ColorMatrix cm = new ColorMatrix();
	    cm.setSaturation(0);
	    ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
	    paint.setColorFilter(f);
	    c.drawBitmap(bmpSrc, 0, 0, paint);
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
