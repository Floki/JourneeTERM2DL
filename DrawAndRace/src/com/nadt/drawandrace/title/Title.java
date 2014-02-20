package com.nadt.drawandrace.title;

import java.io.File;

import com.nadt.drawandrace.CustomActivity;
import com.nadt.drawandrace.game.GameActivity;
import com.nadt.drawnandrace.R;


import android.content.Intent;
import android.database.Cursor;

import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Title extends CustomActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Compute game path
		String packageName = Title.this.getPackageName();
		File externalPath = Environment.getExternalStorageDirectory();
		File storage = new File(externalPath.getAbsolutePath() + "/Android/data/" + packageName + "/files");
		if(storage == null) {
			storage = getApplication().getFilesDir();
		}

		setContentView(R.layout.title_activity);
		Button start = (Button) findViewById(R.id.playButton);
		Button exit = (Button) findViewById(R.id.exitButton);

		start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	
				Intent myIntent = new Intent(Title.this, GameActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});

		//		create.setOnClickListener(new OnClickListener() {
		//			@Override
		//			public void onClick(View v) {	
		//				Constants.mode = Constants.Mode.CREATE;
		//				Intent myIntent = new Intent(Title.this, MusicSelection.class);
		//				startActivityForResult(myIntent, 0);
		//			}
		//		});
		//		
		//		scores.setOnClickListener(new OnClickListener() {
		//			@Override
		//			public void onClick(View v) {	
		//				Intent myIntent = new Intent(Title.this, ScoreActivity.class);
		//				startActivityForResult(myIntent, 0);
		//			}
		//		});
		//		
		//		options.setOnClickListener(new OnClickListener() {
		//			@Override
		//			public void onClick(View v) {	
		//				Intent myIntent = new Intent(Title.this, Parameters.class);
		//				startActivityForResult(myIntent, 0);
		//			}
		//		});

		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}
}
