package com.nadt.drawandrace.title;

import java.io.File;
import java.io.IOException;

import com.nadt.drawandrace.CustomActivity;
import com.nadt.drawandrace.gallery.GalleryActivty;
import com.nadt.drawandrace.game.GameActivity;
import com.nadt.drawandrace.utils.Constants;
import com.nadt.drawandrace.utils.FileAccess;
import com.nadt.drawandrace.utils.Tools;
import com.nadt.drawnandrace.R;


import android.content.Intent;
import android.database.Cursor;

import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
		FileAccess.createPathIfNonExists( storage.getPath() );
		Constants.storage = storage.getPath();
		Tools.log(this, Constants.storage);

		setContentView(R.layout.title_activity);
		Button start = (Button) findViewById(R.id.playButton);
		Button gallery = (Button) findViewById(R.id.galleryButton);
		Button exit = (Button) findViewById(R.id.exitButton);

		start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	
				Intent myIntent = new Intent(Title.this, GameActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
		
		Button imageCapture = (Button) findViewById( R.id.imageCapture );
		

		gallery.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	
				Intent myIntent = new Intent(Title.this, GalleryActivty.class);
				startActivityForResult(myIntent, 0);
			}
		});

		imageCapture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// Création de l'intent de type ACTION_IMAGE_CAPTURE
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// Création d'un fichier de type image
				File photo = new File(Constants.storage,  "course.jpg");
				if( !photo.exists() ) {
					//photo.mkdirs();
					try {
						photo.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Tools.log(this, photo.getPath() );
				// On fait le lien entre la photo prise et le fichier que l'on vient de créer
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
				// Lancement de l'intent
				startActivityForResult(intent, 1);
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
