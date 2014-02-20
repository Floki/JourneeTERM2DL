package com.nadt.drawandrace.game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.nadt.drawandrace.game.active.Sprite;
import com.nadt.drawandrace.game.active.TrackFinish;
import com.nadt.drawandrace.game.active.TrackStart;
import com.nadt.drawandrace.game.engine.GameEngine;
import com.nadt.drawandrace.utils.Constants;

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
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	private SurfaceHolder holder;
	private GameEngine gameEngine;
	private boolean canDraw;
	private long initTime;
	private File imageFile;
	private Bitmap bitmapTrack;
	private int widthRatio;
	
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
			
			int xPosition = gameEngine.getXPosition();
			int yPosition = gameEngine.getYPosition();
			
			float litleWidth = bitmapTrack.getWidth() / Constants.TRACK_SIZE_FACTOR;
			float litleHeight = bitmapTrack.getHeight() / Constants.TRACK_SIZE_FACTOR;
			
			canvas.drawBitmap( bitmapTrack,
								new Rect( (int)(xPosition/6), 
										  (int)(yPosition/6),
										  (int)(xPosition/6 + GameActivity.screenWidth/6), 
										  (int)(yPosition/6 + GameActivity.screenHeight/6) ),
								new Rect(0, 0, GameActivity.screenWidth, GameActivity.screenHeight),
								paint);
			

			RaceTrack map = gameEngine.getMap();
			paint.setColor(Color.GRAY);

			Sprite start = gameEngine.getStartSprite();
			Sprite finish = gameEngine.getFinishSprite();
			if(start != null) {
				start.draw(canvas, paint);
			}
			if(finish != null) {
				finish.draw(canvas, paint);
			}
			
			if(gameEngine != null) {
				if(gameEngine.getPlaySprite() != null) {
					gameEngine.getPlaySprite().draw(canvas, paint);
				}
			}
			paint.setColor(Color.WHITE);
			paint.setTextSize(GameActivity.virtualXToScreenX(50));
			canvas.drawText(""+gameEngine.getSpeed() + " km/h", GameActivity.virtualXToScreenX(50), GameActivity.virtualYToScreenY(50), paint);
			canvas.drawText(""+(gameEngine.getTimer()/1000) + "", GameActivity.virtualXToScreenX(900), GameActivity.virtualYToScreenY(50), paint);
		}
	}

	public void setGameEngine(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
  	}
	public void setImageTrackFile(File imageFile) {
		this.imageFile= imageFile;
		this.bitmapTrack = BitmapFactory.decodeFile( imageFile.getPath() );
		this.widthRatio = bitmapTrack.getHeight() * GameActivity.screenWidth / bitmapTrack.getWidth();
	}
}