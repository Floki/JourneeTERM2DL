package com.nadt.drawandrace.game;

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
	
	public RaceTrack(int tileSize, int nbHorizontal, int nbVertical) {
		wallSize = tileSize;
		nbH = nbHorizontal;
		nbV = nbVertical;
	}
	
	public boolean collisionOn(int x, int y) {
//		int xInAray = Math.min(Math.max(x / wallSize, 0), nbH) ;
//		int yInAray = Math.min(Math.max(y / wallSize, 0), nbV) ;
//		Tools.log(this, "Test if there is obstacle in : [ " + xInAray + " , " + yInAray + "]");
//		return !map[yInAray][xInAray];
		return false;
	}

	public boolean wallIn(int x, int y) {
		return !map[y][x];
	}

	public int getWallSize() {
		return wallSize;
	}
}
