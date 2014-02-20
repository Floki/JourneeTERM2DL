package com.nadt.drawandrace.game;

import com.nadt.drawandrace.game.engine.GameEngine;
import com.nadt.drawandrace.utils.Constants;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;

public class GameThread extends Thread {
	
	private GameView view;
	private GameEngine gameEngine;
	private boolean running = false;

	public GameThread(GameView view, GameEngine gameEngine) {
		this.view = view;
		this.gameEngine = gameEngine;
	}

	public void setRunning(boolean run) {
		running = run;
		if(run) {
			start();
		}
		else {
			interrupt();
		}
	}

	@SuppressLint("WrongCall")
	@Override
	public void run() {
		long ticksPS = 1000 / Constants.FPS;
		long startTime;
		long sleepTime;
		while (running) {
			if(gameEngine.isEnded()) {
				running = false;
			}
			Canvas c = null;
			gameEngine.engineLoop();
			startTime = System.currentTimeMillis();
			try {
				c = view.getHolder().lockCanvas();
				if(c != null) {
					synchronized (view.getHolder()) {
						//Tools.log(this, "Draw");
						view.onDraw(c);
					}
				}
			} finally {
				if (c != null) {
					//Tools.log(this, "Free the canvas");
					view.getHolder().unlockCanvasAndPost(c);
				}
			}
			sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
			try {
				if (sleepTime > 0)
					sleep(sleepTime);
				else
					sleep(10);
			} catch (Exception e) {}
		}
	}
} 

