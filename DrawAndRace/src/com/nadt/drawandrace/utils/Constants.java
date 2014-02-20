package com.nadt.drawandrace.utils;

import java.util.Collection;

public class Constants {

	/*
	 * Levels
	 */
	public static final String EASY = "EASY";
	public static final String NORMAL = "NORMAL";
	public static final String HARD = "HARD";
	
	/*
	 * Thread running and shared object
	 */
	public static boolean running;
	public static int score;
	public static long FPS = 100;
	
	
	/*
	 *  Game folder
	 */
	public static String keepTheBeatFolder = "/sdcard/KeepTheBeat"; //should not be defined here, because of it's computed by Title.onCreate() - so it is hard to know real value of it... -_-' 

}
