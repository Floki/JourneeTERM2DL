package com.nadt.drawandrace.game.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import android.graphics.Color;

public class GameEngine {
	
	// Position du touché de l'utilisateur
	private float userTouchX;
	private float userTouchY;
	private boolean userIsTouching;
	// Taille de l'aire de jeu
	private int gameWidth;
	private int gameHeight;
	private boolean reallyEnd;
	
	public GameEngine() {

	}
	
	public void engineLoop() {
		playLoop();
	}
	
	private void playLoop() {

	}

	public void setUserTouchPosition(float x, float y) {
		userTouchX = x;
		userTouchY = y;
	}
	
	public void isTouching(boolean touch) {
		userIsTouching = touch;
	}
	
	public boolean isEnded() {
		return reallyEnd;
	}
}
