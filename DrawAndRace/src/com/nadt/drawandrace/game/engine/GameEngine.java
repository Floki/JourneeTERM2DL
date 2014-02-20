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
import com.nadt.drawandrace.game.active.TrackFinish;
import com.nadt.drawandrace.game.active.TrackStart;
import com.nadt.drawandrace.utils.Tools;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class GameEngine {
	
	// Position du touché de l'utilisateur
	private float userTouchX;
	private float lastUserTouchX;
	private float lastUserTouchY;
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
	private TrackStart start;
	private TrackFinish finish;
	// Capteur
	private SensorManager sensorManager;
	// Boost needed
	private boolean boost;
	// Speed of the car
	private int speed;
	private int maxSpeed;
	private int boostSpeed;
	// State
	private final int PUT_START = 0;
	private final int PUT_FINISH = 1;
	private final int RACE = 2;
	private final int FINISH = 3;
	private int gameState;
	private int touchTimer;
	
	public GameEngine() {
		track = new RaceTrack(GameActivity.virtualXToScreenX(300), 10, 10);
		boost = false;
		speed = 0;
		maxSpeed = GameActivity.virtualXToScreenX(50);
		boostSpeed = GameActivity.virtualXToScreenX(80);
		gameState = PUT_START;
		touchTimer = 0;
	}
	
	public void engineLoop() {
		Tools.log(this, gameState);
		if(userIsTouching && lastUserTouchX == userTouchX && lastUserTouchY == userTouchY) {
			touchTimer ++;
		}
		else {
			touchTimer = 0;
		}
		switch(gameState) {
		case PUT_START :
			putStart();
			break;
		case PUT_FINISH :
			putFinish();
			break;
		case RACE :
			playLoop();
			break;
		case FINISH :
			finish();
			break;
		}
		
	}
	
	private void playLoop() {
		if(playerShape == null) {
			xPosition = (int) start.getInitialX() - GameActivity.virtualXToScreenX(GameActivity.virtualSize / 2);
			yPosition = (int) start.getInitialY() - GameActivity.virtualYToScreenY(GameActivity.virtualSize / 2);
			move(0,0);
			playerShape = new CarActive(GameActivity.virtualXToScreenX(GameActivity.virtualSize/2), 
					   GameActivity.virtualYToScreenY(GameActivity.virtualSize/2),
					   0,
					   GameActivity.virtualXToScreenX(50), 
					   GameActivity.virtualXToScreenX(75));
		}
		if(userIsTouching) {
			if(speed < maxSpeed) {
				speed++;
			}
			else if(speed > maxSpeed) {
				speed--;
			}
			playerShape.setAngle(Tools.getAngle(userTouchX, userTouchY, playerShape.getInitialX(), playerShape.getInitialY()));
			float angle = playerShape.getAngle();
			float nextX = ((float)Math.cos(Math.toRadians(angle + 90))) * GameActivity.virtualXToScreenX(speed);
			float nextY = ((float)Math.sin(Math.toRadians(angle + 90))) * GameActivity.virtualXToScreenX(speed);
			Tools.log(this, "Try to move to : ( " + (getXInRace() - nextX) + " , " + (getYInRace() - nextY) + ")" );
			if(!track.collisionOn((int)(getXInRace() - nextX),(int)(getYInRace() - nextY))) {
				move((int)-nextX, (int)-nextY);
			}
			else {
				speed = 0;
			}
		}
		else if(speed > 0) {
			speed--;
		}
		if(boost) {
			boost = false;
			speed = boostSpeed;
		}
	}
	
	private void move(int moveX, int moveY) {
		xPosition += moveX;
		yPosition += moveY;
		if(start != null) {
			start.setPosition(start.getInitialX() - xPosition, start.getInitialY() - yPosition);
		}
		if(finish != null) {
			finish.setPosition(finish.getInitialX() - xPosition, finish.getInitialY() - yPosition);
		}
	}
	
	private void scroll() {
		Tools.log(this, "Last position : " + xPosition + " " + yPosition);
		if(lastUserTouchX != userTouchX || lastUserTouchY != userTouchY) {
			int differenceX = (int) (lastUserTouchX - userTouchX);
			int differenceY = (int) (lastUserTouchY - userTouchY);
			Tools.log(this, "Move : " + differenceX + " " + differenceY);
			move(differenceX, differenceY);
		}
		lastUserTouchX = userTouchX;
		lastUserTouchY = userTouchY;
	}
	
	private void putStart() {
		if(userIsTouching && touchTimer < 100 && start == null) {
			scroll();
		}
		else if(touchTimer > 10 && start == null) {
			start = new TrackStart(xPosition + userTouchX, yPosition + userTouchY, 0);
		}
		else if(start != null) {
			if(!userIsTouching) {
				gameState = PUT_FINISH;
			}
		}
	}
	


	private void putFinish() {
		if(userIsTouching && touchTimer < 100 && finish == null) {
			scroll();
		}
		else if(touchTimer > 10 && finish == null) {
			finish = new TrackFinish(xPosition + userTouchX, yPosition + userTouchY, 0);
		}
		else if(finish != null) {
			if(!userIsTouching) {
				gameState = RACE;
			}
		}
	}
	
	private void finish() {
		
	}

	public void setUserTouchPosition(float x, float y) {
		if(!userIsTouching) {
			lastUserTouchX = x;
			lastUserTouchY = y;
		}
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
	
	public Sprite getStartSprite() {
		return start;
	}
	
	public Sprite getFinishSprite() {
		return finish;
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
	
	public void setAccelerometerSensor(SensorManager sensorManager) {
		this.sensorManager = sensorManager;
		this.sensorManager.registerListener(new SensorEventListener() {
			
			@Override
			public void onSensorChanged(SensorEvent event) {
				if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
					int accelX = (int) event.values[0];
					int accelY = (int) event.values[1];
					int accelZ = (int) event.values[2];
					boost = Math.abs(accelX) > 10 || Math.abs(accelY) > 10 || Math.abs(accelZ) > 10;
				}
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {}
		}, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public int getSpeed() {
		return this.speed;
	}
}
