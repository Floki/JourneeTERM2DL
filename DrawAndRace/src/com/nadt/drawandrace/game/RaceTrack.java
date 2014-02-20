package com.nadt.drawandrace.game;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.nadt.drawandrace.utils.Constants;

import com.nadt.drawandrace.utils.Tools;

public class RaceTrack {
	// Taille des murs
	private int wallSize;
	// Taille tableau
	private int nbH;
	private int nbV;

	private boolean[][] map = {
			{false, false, false, false, false, false, false, false, false, false},
			{false, true,  true,  true,  true,  true,  true,  true,  true,  false},
			{false, true,  false, false, false, false, false, false, true,  false},
			{false, true,  false, false, false, false, false, true,  true,  false},
			{false, true,  true,  false, true,  true,  true,  true,  false, false},
			{false, false, true,  false, true,  false, false, false, false, false},
			{false, true,  true,  false, true,  true,  true,  true,  true,  false},
			{false, true,  false, false, false, false, false, true,  true,  false},
			{false, true,  true,  true,  true,  true,  true,  true,  false, false},
			{false, false, false, false, false, false, false, false, false, false}
	};
	private Bitmap blackAndWhiteBitmap;
	
	public RaceTrack(int tileSize, int nbHorizontal, int nbVertical) {
		wallSize = tileSize;
		nbH = nbHorizontal;
		nbV = nbVertical;
		
		blackAndWhiteBitmap = BitmapFactory.decodeFile( Constants.getBlackWhiteFilePath() );
	}
	
	public boolean collisionOn(int x, int y) {
//		int xInAray = Math.min(Math.max(x / wallSize, 0), nbH) ;
//		int yInAray = Math.min(Math.max(y / wallSize, 0), nbV) ;
//		Tools.log(this, "Test if there is obstacle in : [ " + xInAray + " , " + yInAray + "]");
//		return !map[yInAray][xInAray];
		
		float litleWidth = blackAndWhiteBitmap.getWidth() / Constants.TRACK_SIZE_FACTOR;
		float litleHeight = blackAndWhiteBitmap.getHeight() / Constants.TRACK_SIZE_FACTOR;
		
		if( blackAndWhiteBitmap.getPixel(x / Constants.TRACK_SIZE_FACTOR, y / Constants.TRACK_SIZE_FACTOR ) == Color.BLACK ) {
			Tools.err(this, "*** BOOM ***");
		}
		
		return false;
	}

	public boolean wallIn(int x, int y) {
		return !map[y][x];
	}

	public int getWallSize() {
		return wallSize;
	}
}
