package com.nadt.keepthebeatsurvival.title;

import java.io.File;


import android.content.Intent;
import android.database.Cursor;

import com.nadt.keepthebeatsurvival.CustomActivity;
import com.nadt.keepthebeatsurvival.R;
import com.nadt.keepthebeatsurvival.utils.Constants;
import com.nadt.keepthebeatsurvival.utils.Tools;

import android.os.Bundle;
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
