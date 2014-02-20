package com.nadt.drawandrace.utils;

import android.util.Log;

public class Tools {
	
	public static void log(Object origin, Object message) {
		Log.d(origin.getClass().getName(), "" + message);
	}
	
	public static int distanceBetweenPoints(Pair<Integer,Integer> p1, Pair<Integer,Integer> p2) {
		return distanceBetweenPosition(p1.getFirst(), p1.getSecond(), p2.getFirst(), p2.getSecond());
	}
	
	public static int distanceBetweenPosition(int x1, int y1, int x2, int y2) {
		return (int) Math.sqrt(square(x1 - x2) + square(y1 - y2));
	}
	
	public static int square(int n) {
		return n * n;
	}
	
	public static float getAngle(float x1, float y1, float x2, float y2) {
		float deltaY = y2 - y1;
		float deltaX = x2 - x1;

		float angleInDegrees = ((float) (Math.atan2(deltaY , deltaX) * 180 / 3.14)) - 90;
		return angleInDegrees;
	}
}
