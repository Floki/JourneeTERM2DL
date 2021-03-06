package com.nadt.drawandrace.gallery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.nadt.drawandrace.CustomActivity;
import com.nadt.drawandrace.game.GameActivity;
import com.nadt.drawandrace.utils.Constants;
import com.nadt.drawandrace.utils.ImageManipulation;
import com.nadt.drawandrace.utils.Tools;
import com.nadt.drawnandrace.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class GalleryActivty extends CustomActivity {


	private static int RESULT_LOAD_IMAGE = 1;
	private static int RESULT_CAMERA_IMAGE = 2;
	private int screenHeight;
	private int screenWidth;
	private int angle;
	private float touchingX;
	private boolean alreadyChange;
	private File photo;
	private Button buttonSelect;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		angle = 0;
		setContentView(R.layout.gallery_activity);
		View galleryView = findViewById(R.id.imgView);
		// Récuperation de la taille du device
        screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        // Création d'un fichier de type image
		photo = new File(Constants.storage,  "course.jpg");
		Tools.log(this, Constants.storage);
		if( !photo.exists() ) {
			//photo.mkdirs();
			try {
				photo.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
		Button buttonCamera = (Button) findViewById(R.id.camera);
		buttonSelect = (Button) findViewById(R.id.buttonSelectPicture);
		
		buttonSelect.setVisibility( Button.INVISIBLE );
		
		buttonSelect.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(GalleryActivty.this, GameActivity.class);
				String[] trackImg = {photo.getPath()};
				intent.putExtra("SELECTED_TRACK_IMAGE", trackImg);
				startActivityForResult(intent, 0);
				finish();
			}
		});
		
		buttonLoadImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent i = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		});
		
		buttonCamera.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
					
					// Création de l'intent de type ACTION_IMAGE_CAPTURE
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					// On fait le lien entre la photo prise et le fichier que l'on vient de créer
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
					// Lancement de l'intent
					startActivityForResult(intent, RESULT_CAMERA_IMAGE);
					
			}
		});
		
		// On envoie la position touché par l'utilisateur
		galleryView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				final int action = event.getAction();
				switch(action) {
				case MotionEvent.ACTION_DOWN:
					touchingX = event.getX();
					alreadyChange = false;
					Tools.log(this, "Touch position : " + touchingX);
					return true;
				case MotionEvent.ACTION_MOVE:
//					if(alreadyChange) {
//						return true;
//					}
//					if(touchingX < event.getX() - 30) {
//						angle -= 90;
//						alreadyChange = true;
//					}
//					else if(touchingX > event.getX() + 30){
//						angle += 90;
//						alreadyChange = true;
//					}
//					Tools.log(this, "Rotate : " + angle);
//					ImageView imageView = (ImageView) findViewById(R.id.imgView);
//					Matrix matrix = new Matrix();
//					imageView.setScaleType(ScaleType.MATRIX);   //required
//					matrix.postRotate(angle, imageView.getDrawable().getBounds().width()/2, imageView.getDrawable().getBounds().height()/2);
//					imageView.setImageMatrix(matrix);
				}
				return true;
			}
		});
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			ImageView imageView = (ImageView) findViewById(R.id.imgView);
			Tools.log(this, picturePath );
			this.photo = new File(picturePath);
			imageView.setImageURI(Uri.fromFile(photo));
		}
		else if(requestCode == RESULT_CAMERA_IMAGE) {
			Tools.log(this, "Photo obtenue.");
			ImageView imageView = (ImageView) findViewById(R.id.imgView);
			imageView.setImageURI(Uri.fromFile(photo));
		}
		
		if( photo.exists() ) {
			try {
				File f2 = ImageManipulation.toBlackAndWhite( photo );
				if( f2.exists() ) {
					buttonSelect.setVisibility( Button.VISIBLE );
					( (ImageView) findViewById(R.id.imgView) ).setImageURI(Uri.fromFile(f2));
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
}