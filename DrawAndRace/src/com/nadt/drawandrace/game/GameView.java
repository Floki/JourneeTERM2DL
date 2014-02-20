package com.nadt.drawandrace.game;

import java.util.ArrayList;
import java.util.List;

import com.nadt.drawandrace.game.active.Sprite;
import com.nadt.drawandrace.game.engine.GameEngine;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	private SurfaceHolder holder;
	private GameEngine gameEngine;
	private boolean canDraw;
	private long initTime;
	
	public GameView(Context context) {
		super(context);
		init();
	}
	public GameView(Context context, AttributeSet attr) {
		super(context, attr);
		init();
	}
	
	private void init() {
		canDraw = false;
		initTime = System.currentTimeMillis();
	}

	protected void onDraw(Canvas canvas) {
		if(canvas == null) {
			return;
		} 
		if(!canDraw) {
			canDraw = System.currentTimeMillis() - initTime > 1000;
		}
		else {
			canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
			Paint paint = new Paint();
			if(gameEngine != null) {
				if(gameEngine.getPlaySprite() != null) {
					gameEngine.getPlaySprite().draw(canvas, paint);
				}
			}
			RaceTrack map = gameEngine.getMap();
			paint.setColor(Color.GRAY);
			int xPosition = gameEngine.getXPosition();
			int yPosition = gameEngine.getYPosition();
			for(int y = 0; y < 10; y++) {
				for(int x = 0; x < 10; x++) {
					if(map.wallIn(x, y)) {
						Rect wall = new Rect(x * map.getWallSize() - xPosition,
											 y * map.getWallSize() - yPosition,
											 x * map.getWallSize() + map.getWallSize() - xPosition,
											 y * map.getWallSize() + map.getWallSize() - yPosition);
						canvas.drawRect(wall, paint);
					}
				}
			}
		}
	}

	public void setGameEngine(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
  	}
}