package com.nadt.drawandrace.title;

import java.io.File;
import com.nadt.drawandrace.CustomActivity;
import com.nadt.drawandrace.gallery.GalleryActivty;
import com.nadt.drawandrace.game.GameActivity;
import com.nadt.drawandrace.utils.Constants;
import com.nadt.drawandrace.utils.FileAccess;
import com.nadt.drawandrace.utils.Tools;
import com.nadt.drawnandrace.R;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


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
		FileAccess.createPathIfNonExists( storage.getPath() );
		Constants.storage = storage.getPath();
		Tools.log(this, Constants.storage);

		setContentView(R.layout.title_activity);
		ImageView gallery = (ImageView) findViewById(R.id.btnPlay);
		ImageView exit = (ImageView) findViewById(R.id.btnExit);		

		gallery.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	
				Intent myIntent = new Intent(Title.this, GalleryActivty.class);
				startActivityForResult(myIntent, 0);
			}
		});

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
