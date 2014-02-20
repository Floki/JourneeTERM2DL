package com.nadt.drawandrace.game;

import com.nadt.drawandrace.game.engine.GameEngine;

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
		this.soundEngine = gameEngine.getSoundEngine();
		this.gameActivity = activity;
	}

	public void setRunning(boolean run) {
		running = run;
		soundEngine.playIfNeedToPlay(run);
		if(run) {
			start();
		}
		else {
			soundEngine.onDestroy();
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
				Pattern p = gameEngine.getPattern();
				saveScore( p.getMusicFile().getTitle(), p.getPatternName(), Game.level.getLevelName(), gameEngine.getScore() );
				gameActivity.backToTitle("Fin de partie" , "Score : " + gameEngine.getScore());
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

	private void saveScore(String musicTitle, String patternName, String level, int score) {
		ScoreDbHelper scoreDbHelper = new ScoreDbHelper(gameActivity);
		scoreDbHelper.insertScore(musicTitle, patternName, level, score);
	}
} 

