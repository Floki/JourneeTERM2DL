package com.nadt.drawandrace.game.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.nadt.drawandrace.game.GameActivity;
import com.nadt.drawandrace.game.active.CarActive;
import com.nadt.drawandrace.game.active.Sprite;
import com.nadt.drawandrace.utils.Tools;

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
	// Active sprite
	private CarActive playerShape;
	
	public GameEngine() {
		playerShape = new CarActive(GameActivity.virtualXToScreenX(GameActivity.virtualSize/2), 
										   GameActivity.virtualYToScreenY(GameActivity.virtualSize/2), 
										   GameActivity.virtualXToScreenX(50), 
										   GameActivity.virtualXToScreenX(75), 
										   0);
	}
	
	public void engineLoop() {
		playLoop();
	}
	
	private void playLoop() {
		if(userIsTouching) {
			playerShape.setAngle(Tools.getAngle(userTouchX, userTouchY, playerShape.getX(), playerShape.getY()));
			playerShape.moveWithSpeed(GameActivity.virtualXToScreenX(10));
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
}
