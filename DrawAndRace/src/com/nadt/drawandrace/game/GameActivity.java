package com.nadt.drawandrace.game;

import java.io.File;
import java.io.ObjectInputStream;

import com.nadt.drawandrace.CustomActivity;
import com.nadt.drawandrace.game.engine.GameEngine;
import com.nadt.drawandrace.utils.Tools;
import com.nadt.drawnandrace.R;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends CustomActivity implements SurfaceHolder.Callback {

	public static int screenHeight;
	public static int screenWidth;
	
	// Taille virtuelle
	public final static int virtualSize = 1000;
	public static int screenYToVirtualY(int screenPosition)  {return (screenPosition * virtualSize) / screenHeight; }
	public static int screenXToVirtualX(int screenPosition)  {return (screenPosition * virtualSize) / screenWidth; }
	public static int virtualYToScreenY(int virtualPosition) {return (virtualPosition * screenHeight) / virtualSize; }
	public static int virtualXToScreenX(int virtualPosition) {return (virtualPosition * screenWidth) / virtualSize ; }
	
	// Attributs prives
	// Vue du jeu
	private GameView gameView;
	// Moteur du jeu
	private GameEngine gameEngine;
	// Thread principal
	private static GameThread gameThread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String[] xtras = getIntent().getExtras().getStringArray("SELECTED_TRACK_IMAGE");
		if( xtras == null ) {
			//here we fail :o
			Tools.log(this, "No Xtras");
		}
		
		String imagePath = xtras[0];
		File imageFile = new File( imagePath );

		// Full Screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Récuperation de la taille du device
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        
		// On cree la vue
		setContentView(R.layout.game_activity);
		gameView = (GameView)findViewById(R.id.gameView);
		gameView.getHolder().addCallback(this);
		gameView.setImageTrackFile( imageFile );
		//gameView.setBackgroundDrawable( Drawable.createFromPath( imagePath ) );
		
		// On crée le moteur du jeu
		gameEngine = new GameEngine();
		gameView.setGameEngine(gameEngine);
		
		// On envoie la position touché par l'utilisateur
		gameView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				final int action = event.getAction();
				switch(action) {
				case MotionEvent.ACTION_DOWN:
					gameEngine.setUserTouchPosition(event.getX(), event.getY());
					gameEngine.isTouching(true);
				case MotionEvent.ACTION_MOVE:
					gameEngine.setUserTouchPosition(event.getX(), event.getY());
					gameEngine.isTouching(true);
					break;
				case MotionEvent.ACTION_UP:
					gameEngine.isTouching(false);
					break;
				case MotionEvent.ACTION_CANCEL:
					gameEngine.isTouching(false);
					break;
				}
				return true;
			}
		});
		gameEngine.setAccelerometerSensor((SensorManager)getSystemService(SENSOR_SERVICE));
		gameThread = new GameThread(this, gameView, gameEngine);
	}

	@Override
	public void onDestroy() {
		if(gameThread != null) {
			gameThread.setRunning(false);
			gameThread.interrupt();
		}
		gameThread = null;
		gameEngine = null;
		gameView = null;
		super.onDestroy();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	onDestroy();
	        backToTitle();
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}
	
	public static void pauseThread(boolean pause) {
		gameThread.setRunning(!pause);
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		gameThread.setRunning(true);
		gameView.setWillNotDraw(false);
	}
	@Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
