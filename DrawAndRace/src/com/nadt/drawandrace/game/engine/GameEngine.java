package com.nadt.drawandrace.game.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.nadt.drawandrace.game.GameActivity;
import com.nadt.drawandrace.game.RaceTrack;
import com.nadt.drawandrace.game.active.CarActive;
import com.nadt.drawandrace.game.active.Sprite;
import com.nadt.drawandrace.utils.Tools;

import android.graphics.Color;

public class GameEngine {
	
	// Position du touché de l'utilisateur
	private float userTouchX;
	private float userTouchY;
	private boolean userIsTouching;
	// Taille de l'écran
	private int screenWidth;
	private int gameHeight;
	// Position dans la scene
	private int xPosition = 0;
	private int yPosition = 0;
	// Partie terminée
	private boolean reallyEnd;
	// Circuit
	private RaceTrack track;
	// Active sprite
	private CarActive playerShape;
	
	public GameEngine() {
		playerShape = new CarActive(GameActivity.virtualXToScreenX(GameActivity.virtualSize/2), 
										   GameActivity.virtualYToScreenY(GameActivity.virtualSize/2), 
										   GameActivity.virtualXToScreenX(50), 
										   GameActivity.virtualXToScreenX(75), 
										   0);
		track = new RaceTrack(GameActivity.virtualXToScreenX(300), 10, 10);
	}
	
	public void engineLoop() {
		playLoop();
	}
	
	private void playLoop() {
		if(userIsTouching) {
			playerShape.setAngle(Tools.getAngle(userTouchX, userTouchY, playerShape.getX(), playerShape.getY()));
			float angle = playerShape.getAngle();
			float nextX = ((float)Math.cos(Math.toRadians(angle + 90))) * 10;
			float nextY = ((float)Math.sin(Math.toRadians(angle + 90))) * 10;
			Tools.log(this, "Try to move to : ( " + (getXInRace() - nextX) + " , " + (getYInRace() - nextY) + ")" );
			if(!track.collisionOn((int)(getXInRace() - nextX),(int)(getYInRace() - nextY))) {
				xPosition -= nextX;
				yPosition -= nextY;
			}
		}
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
	
	public Sprite getPlaySprite() {
		return playerShape;
	}
	
	public RaceTrack getMap() {
		return track;
	}
	
	public int getXPosition() {
		return xPosition;
	}
	
	public int getXInRace() {
		return xPosition + GameActivity.virtualXToScreenX(GameActivity.virtualSize / 2);
	}
	
	public int getYPosition() {
		return yPosition;
	}
	
	public int getYInRace() {
		return yPosition + GameActivity.virtualYToScreenY(GameActivity.virtualSize / 2);
	}
}
