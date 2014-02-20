package com.nadt.drawandrace.game;

import java.util.ArrayList;
import java.util.List;

import com.nadt.drawandrace.game.engine.GameEngine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	private SurfaceHolder holder;
	private GameEngine gameEngine;
	
	public GameView(Context context) {
		super(context);
		init();
	}
	public GameView(Context context, AttributeSet attr) {
		super(context, attr);
		init();
	}
	
	@SuppressLint("WrongCall")
	private void init() {
		//setBackgroundColor(Color.BLUE);
	}

	protected void onDraw(Canvas canvas) {
		if(canvas == null) {
			return;
		}
		canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
		
	}

	public void setGameEngine(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
  	}
}