package com.nadt.drawandrace.utils;

public class Constants {

	public static long FPS = 100;
	
	
	/*
	 *  Game folder
	 */
	public static String storage = ""; //should not be defined here, because of it's computed by Title.onCreate() - so it is hard to know real value of it... -_-'
	
	public static final String TMP_FILE_NAME = "/tmp.png";
	
	public static String getBlackWhiteFilePath() { return storage + TMP_FILE_NAME; }
	
	public static final int TRACK_SIZE_FACTOR = 6;

}
